package br.com.ilia.pontoeletronico.service;

import br.com.ilia.pontoeletronico.entity.dto.RelatorioPontoDTO;
import br.com.ilia.pontoeletronico.repository.RelatorioPontoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Tuple;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;


@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})

public class RelatorioPontoService {

    public static final String ID = "id";
    public static final String DIA_TRABALHADO = "dia_trabalhado";
    public static final String NOME = "nome";
    public static final String CPF = "cpf";
    public static final String HORAS_DEVIDAS = "horas_devidas";
    public static final String HORAS_EXCEDENTES = "horas_excedentes";
    public static final String HORAS_TRABALHADAS = "horas_trabalhadas";
    public static final String PONTO_ID = "ponto_id";
    public static final String HORAS_ATRIBUIDAS = "horas_atribuidas";
    public static final String RELATORIO_PONTO_ID = "relatorio_ponto_id";
    public static final String ENTRADA_1 = "entrada1";
    public static final String SAIDA_1 = "saida1";
    public static final String ENTRADA_2 = "entrada2";
    public static final String SAIDA_2 = "saida2";
    public static final String NOME_PROJETO = "nome_projeto";
    private final RelatorioPontoRepository repository;

    public ResponseEntity<?> gerarRelatorio(String data) {

        List<RelatorioPontoDTO> relatorioPontoDTOS = new ArrayList<>();
        var mesAtual = LocalDate.parse(data);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, mesAtual.getMonthValue() - 1);
        int primeiroDia = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        int ultimoDia = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        var dataInicial = mesAtual.withDayOfMonth(primeiroDia);
        var dataFinal = mesAtual.withDayOfMonth(ultimoDia);

        List<Tuple> result = repository.getCurrentRevenue(dataInicial, dataFinal);

        try {
            result.forEach(r -> {

                BigInteger id = Objects.nonNull(r.get(ID)) ? (BigInteger) r.get(ID) : null;
                Date diaTrabalhado = Objects.nonNull(r.get(DIA_TRABALHADO)) ? (Date) r.get(DIA_TRABALHADO) : null;
                String cpf = Objects.nonNull(r.get(CPF)) ? r.get(CPF).toString() : null;
                String nome = Objects.nonNull(r.get(NOME)) ? r.get(NOME).toString() : null;
                BigInteger horasDevidas = Objects.nonNull(r.get(HORAS_DEVIDAS)) ? (BigInteger) r.get(HORAS_DEVIDAS) : null;
                BigInteger horasExcedentes = Objects.nonNull(r.get(HORAS_EXCEDENTES)) ? (BigInteger) r.get(HORAS_EXCEDENTES) : null;
                BigInteger horasTrabalhadas = Objects.nonNull(r.get(HORAS_TRABALHADAS)) ? (BigInteger) r.get(HORAS_TRABALHADAS) : null;
                BigInteger pontoId = Objects.nonNull(r.get(PONTO_ID)) ? (BigInteger) r.get(PONTO_ID) : null;
                BigInteger horasAtribuidas = Objects.nonNull(r.get(HORAS_ATRIBUIDAS)) ? (BigInteger) r.get(HORAS_ATRIBUIDAS) : null;
                BigInteger relatorioPontoId = Objects.nonNull(r.get(RELATORIO_PONTO_ID)) ? (BigInteger) r.get(RELATORIO_PONTO_ID) : null;
                Timestamp entrada1 = Objects.nonNull(r.get(ENTRADA_1)) ? (Timestamp) r.get(ENTRADA_1) : null;
                Timestamp saida1 = Objects.nonNull(r.get(SAIDA_1)) ? (Timestamp) r.get(SAIDA_1) : null;
                Timestamp entrada2 = Objects.nonNull(r.get(ENTRADA_2)) ? (Timestamp) r.get(ENTRADA_2) : null;
                Timestamp saida2 = Objects.nonNull(r.get(SAIDA_2)) ? (Timestamp) r.get(SAIDA_2) : null;
                String nomeProjeto = Objects.nonNull(r.get(NOME_PROJETO)) ? r.get(NOME_PROJETO).toString() : null;

                RelatorioPontoDTO relatorioPontoDTO = new RelatorioPontoDTO(id, diaTrabalhado, cpf, nome, horasDevidas, horasExcedentes,
                        horasTrabalhadas, pontoId, horasAtribuidas, relatorioPontoId, entrada1, saida1, entrada2, saida2, nomeProjeto);

                relatorioPontoDTOS.add(relatorioPontoDTO);
            });

            return new ResponseEntity<>(relatorioPontoDTOS, HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possível gerar o relatório para o mês informado.");
        }
    }
}
