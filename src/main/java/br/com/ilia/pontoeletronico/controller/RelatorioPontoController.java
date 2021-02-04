package br.com.ilia.pontoeletronico.controller;

import br.com.ilia.pontoeletronico.model.RelatorioPonto;
import br.com.ilia.pontoeletronico.service.RelatorioPontoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("relatorio-ponto")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RelatorioPontoController {

    RelatorioPontoService service;

    @GetMapping
    public List<RelatorioPonto> relatorioPontos(){
        log.info("entrou");
        return service.gerarRelatorio();
    }
}
