package br.com.ilia.pontoeletronico.projetoPontoTest;

import br.com.ilia.pontoeletronico.entity.ProjetoPonto;
import br.com.ilia.pontoeletronico.repository.ProjetoPontoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProjetoPontoRepositoryTest {

    @Autowired
    private ProjetoPontoRepository projetoPontoRepository;


    @Test
    public void create_should_PersistData () {

        ProjetoPonto projetoPonto = projetoPontoRepository.save(ProjetoPonto.builder()
                .dia(LocalDate.now())
                .horasAtribuidas(10L)
                .build());

        assertEquals(LocalDate.now(), projetoPonto.getDia());
    }

    @Test
    public void delete_should_PersistData () {

        ProjetoPonto projetoPonto = projetoPontoRepository.save(ProjetoPonto.builder()
                .dia(LocalDate.now())
                .horasAtribuidas(10L)
                .build());

        projetoPontoRepository.delete(projetoPonto);
        assertTrue(projetoPontoRepository.findById(projetoPonto.getId()).isEmpty());
    }
}
