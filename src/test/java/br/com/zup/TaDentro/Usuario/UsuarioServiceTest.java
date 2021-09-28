package br.com.zup.TaDentro.Usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UsuarioServiceTest {


    @Mock
    private UsuarioRepository usuarioRepository;
    @InjectMocks
    private UsuarioService usuarioservice;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void savedUser() {


        Usuario user = new Usuario();
        user.setSenha("12345");
        Mockito.when(bCryptPasswordEncoder.encode(user.getSenha())).thenReturn("12345");

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
        Assertions.assertEquals(usuario, usuarioservice.encontrarUsuario(1));
    }

    @Test
    public void testarMetodoEncontrarUsuarioPorIdNegativoParaLancaExecao() throws Exception {
        Usuario usuario = new Usuario();
        Optional<Usuario> usuarioOptional = Optional.empty();

        Mockito.when(usuarioRepository.findById(Mockito.anyInt())).thenReturn(usuarioOptional);

        RuntimeException exception = Assertions
                .assertThrows(RuntimeException.class, () -> {usuarioservice.encontrarUsuarioPorEmail("lucs@123");});

        Assertions.assertTrue(exception.getMessage().equals("Usuário não encontrado"));

    }

    @Test
    public void testarMetodoEncontrarUsuarioPorEmailPositivo() throws Exception {
        Usuario usuario = new Usuario();
        Optional<Usuario> usuarioOptional = Optional.of(usuario);

        Mockito.when(usuarioRepository.findByEmail(Mockito.anyString())).thenReturn(usuarioOptional);
        Assertions.assertEquals(usuario, usuarioservice.encontrarUsuarioPorEmail("lmatheus@123"));
    }

    @Test
    public void testarMetodoEncontrarUsuarioPorEmailNegativoParaLancaExecao() throws Exception {
        Usuario usuario = new Usuario();
        Optional<Usuario> usuarioOptional = Optional.empty();

        Mockito.when(usuarioRepository.findByEmail(Mockito.anyString())).thenReturn(usuarioOptional);

        RuntimeException exception = Assertions
                .assertThrows(RuntimeException.class, () -> {usuarioservice.encontrarUsuarioPorEmail("lucs@123");});

        Assertions.assertTrue(exception.getMessage().equals("Usuário não encontrado"));

    }


    @Test
    public void testarMetodoParaExcluirUsuarioPorIdPositivo() throws Exception {
        Usuario usuario = new Usuario();
        Mockito.when(usuarioRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(usuario));
        usuarioservice.deletarUsuario(1234567);

        Mockito.verify(usuarioRepository).delete(usuario);
    }


}