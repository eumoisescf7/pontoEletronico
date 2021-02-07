package br.com.ilia.pontoeletronico.usuarioTest;

import br.com.ilia.pontoeletronico.entity.Usuario;
import br.com.ilia.pontoeletronico.repository.UsuarioRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UsuarioRepositoryTest {
    @Autowired
    private UsuarioRepository usuarioRepository;


    @BeforeAll
    public Usuario findUser() {
        return usuarioRepository.save(Usuario.builder()
                .nome("JHON")
                .cpf("33333333333")
                .build());
    }

    @Test
    public void create_should_PersistData () {

        Usuario usuario = findUser();
        assertEquals("33333333333", usuario.getCpf());
    }
}
