package br.com.ilia.pontoeletronico.projetoPontoTest;

import br.com.ilia.pontoeletronico.entity.ProjetoPonto;
import br.com.ilia.pontoeletronico.service.ProjetoPontoService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class ProjetoPontoServiceTest {

    @Autowired
    ProjetoPontoService service;

    @BeforeAll
    public ProjetoPonto setup() throws Exception {
        var projetoPonto = (ResponseEntity<ProjetoPonto>) service
                .agregarDiaProjeto("2021-01-05", "11111111111", "1", "5");
        return projetoPonto.getBody();
    }

    @Test
    public void should_add_project_point_without_error() throws Exception {

        ProjetoPonto projetoPonto = setup();
        assertEquals(LocalDate.parse("2021-01-05"), projetoPonto.getDia());
        assertEquals(   "5", projetoPonto.getHorasAtribuidas().toString());
    }
}
