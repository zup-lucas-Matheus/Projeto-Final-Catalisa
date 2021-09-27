package br.com.zup.TaDentro.colaboradores;

import br.com.zup.TaDentro.Usuario.Usuario;
import br.com.zup.TaDentro.colaborador.Colaborador;
import br.com.zup.TaDentro.colaborador.ColaboradorRepository;
import br.com.zup.TaDentro.colaborador.ColaboradorService;
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
public class ColaboradorServiceTest {

    @Autowired
    private ColaboradorService colaboradorService;

    @MockBean
    private ColaboradorRepository colaboradorRepository;

    private Colaborador colaborador;
    private Usuario usuario;


    @Test
    public void testarSalvarColaborador() {

        Colaborador colaborador = new Colaborador();

        Mockito.when(colaboradorRepository.save(Mockito.any(Colaborador.class)))
                .thenReturn(colaborador);

        Colaborador colaboradorTeste = colaboradorService.salvarColaborador(usuario.getEmail(), colaborador);

        Assertions.assertEquals(colaborador, colaboradorTeste);
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
    public void testarDeletarPorID() {

        Colaborador colaborador = new Colaborador();

        Optional <Colaborador> colaboradorOptional = Optional.of(colaborador);

       Mockito.when(colaboradorRepository.deleteById(Mockito.anyInt())).thenReturn(colaboradorOptional);

        Assertions.assertEquals(colaborador , colaboradorService.deletarColaborador(1);

    }


    @Test
    public void testarMetodoBuscarContatoPorIDCaminhoPositivo(){
        Contato contato = new Contato();
        Optional<Contato> contatoOptional = Optional.of(contato);
        Mockito.when(contatoRepository.findById(Mockito.anyInt())).thenReturn(contatoOptional);

        Assertions.assertEquals(contato, contatoService.buscarContatoPeloId(12));
    }


}
