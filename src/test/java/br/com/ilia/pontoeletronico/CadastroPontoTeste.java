package br.com.ilia.pontoeletronico;


import br.com.ilia.pontoeletronico.entity.Ponto;
import br.com.ilia.pontoeletronico.service.PontoService;
import org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class CadastroPontoTeste {

    @Autowired
    PontoService pontoService;

    @Test
    public void should_save_ponto_without_error() throws Exception {

        var ponto = (ResponseEntity<Ponto>) pontoService.cadastrarPonto("11111111111");
        assertEquals("11111111111", ponto.getBody().getCpf());
        assertEquals("Teste", ponto.getBody().getUsuario());
    }

    public void should_save_ponto_with_error() throws Exception {

        var ponto = (ResponseEntity<Ponto>) pontoService.cadastrarPonto("1111");
        assertEquals("1111", ponto.getBody().getCpf());
        assertEquals("Teste", ponto.getBody().getUsuario());
    }
}
