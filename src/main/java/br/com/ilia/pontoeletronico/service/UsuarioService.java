package br.com.ilia.pontoeletronico.service;


import br.com.ilia.pontoeletronico.entity.Usuario;
import br.com.ilia.pontoeletronico.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    Optional<Usuario> buscarUsuario(String cpf){
        return repository.findByCpf(cpf);
    }
}
