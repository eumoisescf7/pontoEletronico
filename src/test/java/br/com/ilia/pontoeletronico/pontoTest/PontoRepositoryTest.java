package br.com.ilia.pontoeletronico.pontoTest;

import br.com.ilia.pontoeletronico.entity.Ponto;
import br.com.ilia.pontoeletronico.repository.PontoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PontoRepositoryTest {

    @Autowired
    private PontoRepository pontoRepository;


    @Test
    public void create_should_PersistData () {

        Ponto ponto = pontoRepository.save(Ponto.builder()
                .dia(LocalDate.now())
                .entrada1(LocalDateTime.now())
                .cpf("22222222222")
                .usuario("CAIO")
                .build());

        assertEquals("22222222222", ponto.getCpf());
        assertEquals("CAIO", ponto.getUsuario());

    }

    @Test
    public void delete_should_PersistData () {

        Ponto ponto = pontoRepository.save(Ponto.builder()
                .dia(LocalDate.now())
                .entrada1(LocalDateTime.now())
                .cpf("33333333333")
                .usuario("JOAO")
                .build());

        pontoRepository.delete(ponto);
        assertTrue(pontoRepository.findById(ponto.getId()).isEmpty());
    }
}
