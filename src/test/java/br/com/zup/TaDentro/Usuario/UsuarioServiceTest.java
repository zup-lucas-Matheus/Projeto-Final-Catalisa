
package br.com.zup.TaDentro.Usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UsuarioServiceTest {


    @MockBean
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioservice;


    @Test
    public void savedUser() {

        Usuario user = new Usuario();

        Mockito.when(usuarioRepository.save(Mockito.any(Usuario.class)))
                .thenReturn(user);

        Usuario usuarioTest = usuarioservice.salvarUsuario(user);
        Assertions.assertEquals(user, usuarioTest);
    }

    @Test
    public void testarMetodoQueRetornaTodosOsUsuarios(){
        Usuario usuario = new Usuario();

        List<Usuario> usuarios = Arrays.asList(usuario);
        Mockito.when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> usuariosTest = Arrays.asList(usuario);
        Assertions.assertEquals(usuarios, usuarioservice.exibirUsuario());
    }

    @Test
    public void testarMetodoEncontrarUsuarioPorIdPositivo() throws Exception {
        Usuario usuario = new Usuario();
        Optional<Usuario> usuarioOptional = Optional.of(usuario);

        Mockito.when(usuarioRepository.findById(Mockito.anyInt())).thenReturn(usuarioOptional);
        Assertions.assertEquals(usuario, usuarioservice.encontrarUsuarioPorEmail("leticia@oi.com.br"));
    }

    @Test
    public void testarMetodoEncontrarUsuarioPorIdNegativoParaLancaExecao() throws Exception {
        Usuario usuario = new Usuario();
        Optional<Usuario> usuarioOptional = Optional.empty();

        Mockito.when(usuarioRepository.findById(Mockito.anyInt())).thenReturn(usuarioOptional);

        RuntimeException exception = Assertions
                .assertThrows(RuntimeException.class, () -> {usuarioservice.encontrarUsuarioPorEmail("324e3");});

        Assertions.assertTrue(exception.getMessage().equals("Usuario não encontrado"));

    }

    @Test
    public void testarMetodoParaExcluirUsuarioPorIdPositivo() throws Exception {
        Usuario usuario = new Usuario();
        Mockito.when(usuarioRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(usuario));
        usuarioservice.deletarUsuario(1234567);

        Mockito.verify(usuarioRepository).delete(usuario);
    }


}