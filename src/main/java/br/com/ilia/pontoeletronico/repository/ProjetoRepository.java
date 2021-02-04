package br.com.ilia.pontoeletronico.repository;

import br.com.ilia.pontoeletronico.entity.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    Optional<Projeto> findById(Long id);
}
