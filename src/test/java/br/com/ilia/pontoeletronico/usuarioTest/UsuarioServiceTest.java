package br.com.ilia.pontoeletronico.usuarioTest;

import br.com.ilia.pontoeletronico.entity.Usuario;
import br.com.ilia.pontoeletronico.service.UsuarioService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class UsuarioServiceTest {

    @Autowired
    UsuarioService service;

    @Test
    public void should_search_user_without_error() throws Exception {

        Optional<Usuario> usuario =  service.buscarUsuario("11111111111");
        assertEquals("11111111111", usuario.get().getCpf());
    }

    @Test
    public void could_not_find_user_should_exception() throws Exception {
        try {
            assertNotNull(service.buscarUsuario("1231231"));
        } catch (ResponseStatusException ex) {
            assertSame("Usuário não encontrado na base de dados",
                    HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
