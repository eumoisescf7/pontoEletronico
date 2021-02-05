package br.com.ilia.pontoeletronico.repository;

import br.com.ilia.pontoeletronico.entity.ProjetoPonto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProjetoPontoRepository extends JpaRepository<ProjetoPonto, Long> {
    List<ProjetoPonto> findByDia(LocalDate data);

}
