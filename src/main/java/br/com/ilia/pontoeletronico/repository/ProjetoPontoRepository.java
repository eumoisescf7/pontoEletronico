package br.com.ilia.pontoeletronico.repository;

import br.com.ilia.pontoeletronico.entity.ProjetoPonto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProjetoPontoRepository extends JpaRepository<ProjetoPonto, Long> {
    List<ProjetoPonto> findByDia(LocalDate data);

}
