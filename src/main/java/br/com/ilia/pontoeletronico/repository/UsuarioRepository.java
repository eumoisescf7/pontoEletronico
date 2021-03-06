package br.com.ilia.pontoeletronico.repository;

import br.com.ilia.pontoeletronico.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
        Optional<Usuario> findByCpf(String cpf);
}
