package br.com.zup.TaDentro.colaboradores;

import br.com.zup.TaDentro.Usuario.Usuario;
import br.com.zup.TaDentro.Usuario.UsuarioService;
import br.com.zup.TaDentro.colaborador.Colaborador;
import br.com.zup.TaDentro.colaborador.ColaboradorRepository;
import br.com.zup.TaDentro.colaborador.ColaboradorService;
import br.com.zup.TaDentro.colaborador.exceptionColaborador.MensagemErroColaborador;
import br.com.zup.TaDentro.enums.Cargo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ColaboradorServiceTest {

    @Autowired
    private ColaboradorService colaboradorService;

    @MockBean
    private ColaboradorRepository colaboradorRepository;

    private Colaborador colaborador = criarColaborador();
    private Usuario usuario;

    @Mock
    private UsuarioService usuarioService;

    private Colaborador criarColaborador() {
        colaborador = new Colaborador();

        Colaborador colaborador = new Colaborador();
        colaborador.setCpf("12345678910");
        colaborador.setNome("Teste");
        colaborador.setDataContratacao(LocalDate.now());
        colaborador.setCargo(Cargo.DEV_JR);

        return colaborador;
    }

    @Test
    public void testarSalvarColaborador() {

        Usuario usuario = new Usuario();
        usuario.setEmail("andre@123.com");

        Mockito.when(usuarioService.encontrarUsuarioPorEmail(usuario.getEmail()))
                .thenReturn(usuario);

        Mockito.when(colaboradorRepository.findByCpf(colaborador.getCpf()))
                .thenReturn(Optional.empty());

        colaborador.setLoginUsuario(usuario);
        Mockito.when(colaboradorRepository.save(Mockito.any()))
                .thenReturn(colaborador);

        Colaborador objetoColaborador = colaboradorService.salvarColaborador(usuario.getEmail(), colaborador);

        Assertions.assertEquals(colaborador, objetoColaborador);
        Assertions.assertEquals(colaborador.getCpf(), objetoColaborador.getCpf());
        Assertions.assertEquals(colaborador.getNome(), objetoColaborador.getNome());
        Assertions.assertEquals(colaborador.getDataContratacao(), objetoColaborador.getDataContratacao());
        Assertions.assertEquals(colaborador.getCargo(), objetoColaborador.getCargo());
        Assertions.assertNotNull(objetoColaborador.getCpf());
    }

    @Test
    public void testarExibirTodosOsColaboradores() {

        Colaborador colaborador = new Colaborador();
        Iterable<Colaborador> listaDeColaboradores = Arrays.asList(colaborador);

        Mockito.when(colaboradorRepository.findAll())
                .thenReturn(listaDeColaboradores);

        Assertions.assertTrue(colaboradorService.exibirTodosOsColaboradores() instanceof List);
    }

    @Test
    public void testarSeColaboradorJaExiste() {

        Colaborador colaborador = new Colaborador();
        Optional<Colaborador> colaboradorOptional = Optional.of(colaborador);

        Mockito.when(colaboradorRepository.findById(Mockito.anyInt()))
                .thenReturn(colaboradorOptional);

        Assertions.assertEquals(colaborador, colaboradorService.procurarSeColaboradorJaExiste(1));

    }


    @Test
    public void testarBuscarColaboradorPorCpfCaminhoPositivo() {

        Colaborador colaborador = new Colaborador();

        Mockito.when(colaboradorRepository.findByCpf(Mockito.anyString()))
                .thenReturn(Optional.of(colaborador));

        Assertions.assertEquals(colaborador, colaboradorService.buscarColaboradorPorCpf("12345678910"));
    }

    @Test
    public void testarBuscarIndicacaoPorCpfCaminhoNegativo() {

        Colaborador colaborador = new Colaborador();
        Optional<Colaborador> colaboradorOptional = Optional.empty();

        Mockito.when(colaboradorRepository.findByCpf(Mockito.anyString()))
                .thenReturn(colaboradorOptional);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            colaboradorService.buscarColaboradorPorCpf("12345678910");
        });

        Assertions.assertFalse(exception.getMessage().equals("Colaborador não cadastrado"));
    }

    @Test
    public void testarAtualizarColaborador() {

        Usuario usuario = new Usuario();
        usuario.setEmail("andre@123.com");
        colaborador.setCpf("09876543216");

        Mockito.when(usuarioService.encontrarUsuarioPorEmail(usuario.getEmail()))
                .thenReturn(usuario);

        Optional <Colaborador> colaboradorOptional = Optional.of(colaborador);
        Mockito.when(colaboradorRepository.findByCpf(colaborador.getCpf()))
                .thenReturn(colaboradorOptional);

        colaborador.setLoginUsuario(usuario);
        Mockito.when(colaboradorRepository.save(Mockito.any()))
                .thenReturn(colaborador);

        Colaborador objetoColaborador = colaboradorService.atualizarColaborador(colaborador);

        Assertions.assertEquals(colaborador.getDataContratacao(), objetoColaborador.getDataContratacao());
        Assertions.assertEquals(colaborador.getCargo(), objetoColaborador.getCargo());
        Assertions.assertNotNull(objetoColaborador.getCpf());
    }

    @Test
    public void testarDeletarPorIDCaminhoPositivo() {

        Mockito.when(colaboradorRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(colaborador));

        colaboradorService.deletarColaborador(1);

        Mockito.verify(colaboradorRepository).delete(colaborador);
    }

    @Test
    public void testrarDeletarPorIDCaminhoNegativo() {

        MensagemErroColaborador mensagemErroColaborador = new MensagemErroColaborador("Colaborador não encontrado");

        Mockito.when(colaboradorRepository.findById(Mockito.anyInt()))
                .thenThrow(mensagemErroColaborador);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            colaboradorService.deletarColaborador(1);
        });

        Assertions.assertNotNull(exception);

    }
}









