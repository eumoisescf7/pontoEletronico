package br.com.ilia.pontoeletronico.controller;

import br.com.ilia.pontoeletronico.service.PontoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cadastra-ponto")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PontoController {

    PontoService service;

    @PostMapping
    public void insertCard() {
        service.cadastrarPonto();
    }

}
