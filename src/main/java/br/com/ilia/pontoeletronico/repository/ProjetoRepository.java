package br.com.ilia.pontoeletronico.repository;

import br.com.ilia.pontoeletronico.entity.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    Optional<Projeto> findById(Long id);
}
