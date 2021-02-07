package br.com.ilia.pontoeletronico.service;


import br.com.ilia.pontoeletronico.entity.Usuario;
import br.com.ilia.pontoeletronico.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UsuarioService {

    private final UsuarioRepository repository;

    public Optional<Usuario> buscarUsuario(String cpf) throws Exception {

        try {
            var usuario = repository.findByCpf(cpf);
            if (usuario.isPresent()) {
                return usuario;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Usuario não consta na base de dados");
    }
}
