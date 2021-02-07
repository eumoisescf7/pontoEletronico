package br.com.ilia.pontoeletronico.service;

import br.com.ilia.pontoeletronico.repository.RelatorioPontoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})

public class RelatorioPontoService {

    private final RelatorioPontoRepository repository;

    public ResponseEntity<?> gerarRelatorio(String dataInicio, String dataFim){
        return null;
    }
}
