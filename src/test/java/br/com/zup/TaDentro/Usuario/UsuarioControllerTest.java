package br.com.zup.TaDentro.Usuario;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBeans;

public class UsuarioControllerTest {

    private UsuarioServiceTest usuarioServiceTest;

    @Autowired
    private Usuario usuario;


    @BeforeEach
    public void setUp () {
        usuario = new Usuario();

       usuario.setId(123456789);
       usuario.setNome("Leticia");
       usuario.setEmail("lele@ola.com.br");
       usuario.setSenha("12345");
    }


}
