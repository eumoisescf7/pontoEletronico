package br.com.ilia.pontoeletronico.service;

import br.com.ilia.pontoeletronico.entity.Ponto;
import br.com.ilia.pontoeletronico.entity.RelatorioPonto;
import br.com.ilia.pontoeletronico.entity.Usuario;
import br.com.ilia.pontoeletronico.entity.enums.StatusPontoEnum;
import br.com.ilia.pontoeletronico.repository.PontoRepository;
import br.com.ilia.pontoeletronico.repository.RelatorioPontoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Service
public class PontoService {

    public static final int VALOR = 0;
    public static final long LONG_ZERO = 0L;
    private final PontoRepository pontoRepository;
    private final UsuarioService usuarioService;
    private final RelatorioPontoRepository relatorioPontoRepository;

    public ResponseEntity<?> cadastrarPonto(String cpf) throws Exception {

        Optional<Ponto> responsePonto = pontoRepository.findByDiaAndCpf(LocalDate.now(), cpf);
        Usuario usuario = usuarioService.buscarUsuario(cpf).orElseThrow(() -> new Exception("Usuário não encontrado para o CPF: " + cpf));
        var valor = VALOR;
        Ponto ponto = new Ponto();

        try {
            if(responsePonto.isPresent())
                ponto = responsePonto.get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        try {
            var verificarFinalDeSemana = this.FinalSemana(LocalDate.now());
           if (verificarFinalDeSemana == true) {
               return new ResponseEntity("Ponto não permitido, final de semana!", HttpStatus.INTERNAL_SERVER_ERROR);
           }
        } catch (Exception e) {
           throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }


        if (!responsePonto.isPresent()) {
            return new ResponseEntity(pontoRepository.save(Ponto.builder()
                    .dia(LocalDate.now())
                    .entrada1(LocalDateTime.now())
                    .cpf(cpf)
                    .usuario(usuario.getNome())
                    .build()), HttpStatus.OK);
        }

        valor = this.verificarPonto(ponto);

        switch (valor) {
            case 1:
                ponto.setSaida1(LocalDateTime.now());
                break;
            case 2:
                ponto.setEntrada2(LocalDateTime.now());
                break;
            case 3:
                ponto = this.pontoFinal(ponto, usuario);
                break;
            default:
                break;
        }

        try {
            if (valor != StatusPontoEnum.NAO_ENCONTRADO.getCode())
                return new ResponseEntity(pontoRepository.save(ponto), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return null;
    }

    private Ponto pontoFinal(Ponto ponto, Usuario usuario){

        LocalDateTime horaFinal = LocalDateTime.now();
        ponto.setSaida2(horaFinal);

        relatorioPontoRepository.save(RelatorioPonto.builder()
                .diaTrabalhado(ponto.getDia())
                .horasDevidas(calcHoras(ponto,  horaFinal, 1))
                .horasExcedentes(calcHoras(ponto, horaFinal, 2))
                .horasTrabalhadas(calcHoras(ponto, horaFinal, 3))
                .usuario(usuario.getCpf())
                .ponto(ponto).build());
        return pontoRepository.save(ponto);

    }

    private Integer verificarPonto(Ponto ponto) throws Exception {
        if (ponto.getSaida1() == null) {
            return StatusPontoEnum.detect(1).getCode();
        } else if (ponto.getSaida1() != null && ponto.getEntrada2() == null) {
            LocalDateTime limiteTempoAlmoco = ponto
                    .getSaida1()
                    .plus(60, ChronoUnit.MINUTES).plus(1, ChronoUnit.SECONDS);
            if (limiteTempoAlmoco.isBefore(LocalDateTime.now())) {
                throw new Exception("Horário de almoço dentro do período estabelecido, hora: "+limiteTempoAlmoco);
            } else if (ponto.getEntrada2() == null) {
                return StatusPontoEnum.detect(2).getCode();
            }
        } else if (ponto.getSaida2() == null)
            return StatusPontoEnum.detect(3).getCode();

        return StatusPontoEnum.detect(4).getCode();
    }

    public static boolean FinalSemana(LocalDate data) {
        DayOfWeek dia = data.getDayOfWeek();
            return dia == DayOfWeek.SATURDAY || dia == DayOfWeek.SUNDAY;
    }


    public Long calcHoras(Ponto ponto, LocalDateTime horaFinal, int type) {

        var valorFinal = LONG_ZERO;
        var primeiroPeriodo = ChronoUnit.HOURS.between(ponto.getEntrada1(), ponto.getSaida1());
        var segundoPeriodo = ChronoUnit.HOURS.between(ponto.getEntrada2(), horaFinal);

        var total = primeiroPeriodo + segundoPeriodo;

        switch (type) {

            case 1:
                valorFinal = total - 8L;
                if (valorFinal >= LONG_ZERO);
                    valorFinal = LONG_ZERO;
                break;
            case 2:
                valorFinal = total - 8L;
                break;
            case 3:
                valorFinal = total;
                break;
        }
        return valorFinal;
    }
}
