package br.com.ilia.pontoeletronico.service;

import br.com.ilia.pontoeletronico.entity.ProjetoPonto;
import br.com.ilia.pontoeletronico.entity.RelatorioPonto;
import br.com.ilia.pontoeletronico.repository.ProjetoPontoRepository;
import br.com.ilia.pontoeletronico.repository.ProjetoRepository;
import br.com.ilia.pontoeletronico.repository.RelatorioPontoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ProjetoService {

    private final RelatorioPontoRepository relatorioPontoRepository;
    private final ProjetoRepository projetoRepository;
    private final ProjetoPontoRepository projetoPontoRepository;

    public ResponseEntity<?> agregarDiaProjeto(String data, String cpf, String projeto, String hora) throws Exception {

        var responseProjeto = projetoRepository.findById(Long.parseLong(projeto)).orElseThrow(() -> new Exception("Projeto de ID: " + projeto + " não encontrado "));
        Optional<RelatorioPonto> relatorioPonto = Optional.ofNullable(relatorioPontoRepository.findByDiaTrabalhadoAndUsuario(LocalDate.parse(data), cpf).orElseThrow(() -> new Exception("Relatorio do dia " + data + " não encontrado")));
        List<ProjetoPonto> listaProjetoPonto = projetoPontoRepository.findByDia(LocalDate.parse(data));
        try {
            if (relatorioPonto.isPresent() && relatorioPonto.get().getHorasTrabalhadas() <= Long.parseLong(hora)) {

                var sum = listaProjetoPonto.stream()
                        .mapToLong(t -> t.getHorasAtribuidas())
                        .sum();

                var totalHoras = sum + Long.parseLong(hora);

                try {
                    if (totalHoras <= relatorioPonto.get().getHorasTrabalhadas()) {
                        return new ResponseEntity<>(projetoPontoRepository.save(ProjetoPonto.builder()
                                .dia(relatorioPonto.get().getDiaTrabalhado())
                                .projeto(responseProjeto)
                                .relatorioPonto(relatorioPonto.get())
                                .horasAtribuidas(Long.parseLong(hora))
                                .build()), HttpStatus.OK);
                    }
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
                }

                return new ResponseEntity<>("Limite de horas para esse dia excede: ", HttpStatus.INTERNAL_SERVER_ERROR);

            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possível alocar horas para esse projeto nesse dia" + e.getMessage());
        }
        return null;
    }
}
