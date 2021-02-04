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
import org.springframework.expression.ExpressionException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PontoService {


    public static final int VALOR = 0;
    private final PontoRepository pontoRepository;
    private final UsuarioService usuarioService;
    private final RelatorioPontoRepository relatorioPontoRepository;

    public Ponto cadastrarPonto(String cpf) throws Exception {

        Optional<Ponto> responsePonto = pontoRepository.findByDiaAndCpf(LocalDate.now(), cpf);
        Usuario usuario = usuarioService.buscarUsuario(cpf).orElseThrow(() -> new Exception("Usuário não encontrado para o CPF: " + cpf));
        Integer valor = VALOR;
        Ponto ponto = new Ponto();

        try {
            if(responsePonto.isPresent())
                ponto = responsePonto.get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        if (!responsePonto.isPresent()) {

            return pontoRepository.save(Ponto.builder()
                    .dia(LocalDate.now())
                    .entrada1(LocalDateTime.now())
                    .cpf(cpf)
                    .usuario(usuario.getNome())
                    .build());
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
                return pontoRepository.save(ponto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return null;
    }

    public Ponto pontoFinal(Ponto ponto, Usuario usuario){

        LocalDateTime horaFinal = LocalDateTime.now();
        ponto.setSaida2(horaFinal);

        Integer pontoTrabalhado = 0;
        relatorioPontoRepository.save(RelatorioPonto.builder()
                .diaTrabalhado(ponto.getDia())
                .horaDevidas(pontoTrabalhado)
                .horasExcedentes(pontoTrabalhado)
                .horasTrabalhadas(pontoTrabalhado)
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
                    .plus(60, ChronoUnit.MINUTES);
            if (limiteTempoAlmoco.isAfter(LocalDateTime.now())) {
                throw new Exception("HORÁRIO DE ALMOÇO DENTRO DO PERÍDO ESTABELECIDO");
            } else if (ponto.getEntrada2() == null) {
                return StatusPontoEnum.detect(2).getCode();
            }
        } else if (ponto.getSaida2() == null)
            return StatusPontoEnum.detect(3).getCode();

        return StatusPontoEnum.detect(4).getCode();
    }
}
