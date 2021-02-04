package br.com.ilia.pontoeletronico.repository;

import br.com.ilia.pontoeletronico.entity.Ponto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PontoRepository extends JpaRepository<Ponto, Long> {

    Optional<Ponto> findByDiaAndCpf(LocalDate now, String cpf);
}
