
package br.com.zup.TaDentro.Usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@WebMvcTest
public class UsuarioServiceTest {

    @Autowired
    private UsuarioServiceTest usuarioServiceTest;

    @Autowired
    private UsuarioService service;

    @MockBean
    private UsuarioRepository repository;

    @Autowired
    private UsuarioService usuarioservice;


    @Test
    public void savedUser() {

        Usuario user = new Usuario();

        Mockito.when(repository.save(Mockito.any(Usuario.class)))
                .thenReturn(user);

        Usuario usuarioTest = usuarioservice.salvarUsuario(user);
        Assertions.assertEquals(user, usuarioTest);
    }

    @Test
    public void testUsuario(){
        Usuario usuario = new Usuario();

        List<Usuario> usuarios = Arrays.asList(usuario);
        Mockito.when(repository.findAll()).thenReturn(usuarios);

        List<Usuario> usuariosTest = Arrays.asList(usuario);
        Assertions.assertEquals(usuarios, usuarioservice.exibirUsuario());
    }


}