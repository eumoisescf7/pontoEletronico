package br.com.ilia.pontoeletronico.repository;

import br.com.ilia.pontoeletronico.entity.RelatorioPonto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RelatorioPontoRepository extends JpaRepository<RelatorioPonto, Long> {

    List<RelatorioPonto> findAll();

    Optional<RelatorioPonto> findByDiaTrabalhadoAndUsuario(LocalDate data, String cpf);

}
