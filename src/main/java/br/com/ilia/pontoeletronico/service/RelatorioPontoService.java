package br.com.ilia.pontoeletronico.service;

import br.com.ilia.pontoeletronico.entity.RelatorioPonto;
import br.com.ilia.pontoeletronico.repository.RelatorioPontoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Service
public class RelatorioPontoService {

    private final RelatorioPontoRepository repository;


    public List<RelatorioPonto> gerarRelatorio(){
        return repository.findAll();
    }
}
