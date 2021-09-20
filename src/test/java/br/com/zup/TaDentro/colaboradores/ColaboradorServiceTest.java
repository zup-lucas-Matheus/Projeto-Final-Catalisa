package br.com.zup.TaDentro.colaboradores;

import br.com.zup.TaDentro.Usuario.Usuario;
import br.com.zup.TaDentro.colaborador.Colaborador;
import br.com.zup.TaDentro.colaborador.ColaboradorRepository;
import br.com.zup.TaDentro.colaborador.ColaboradorService;
import br.com.zup.TaDentro.enums.Cargo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ColaboradorServiceTest {

    @Autowired
    private ColaboradorService colaboradorService;

    @MockBean
    private ColaboradorRepository colaboradorRepository;

    private Colaborador colaborador;
    private Usuario usuario;


    @Test
    public void testarSalvarColaborador () {

        Colaborador colaborador = new Colaborador();

        Mockito.when(colaboradorRepository.save(Mockito.any(Colaborador.class)))
                .thenReturn(colaborador);

        Colaborador colaboradorTeste = colaboradorService.salvarColaborador(colaborador);

        Assertions.assertEquals(colaborador , colaboradorTeste);
    }

   /* @Test
    public void testarExibirTodosOsColaboradores () {

        Colaborador colaborador = new Colaborador();

        Iterable<Colaborador> listaDeColaboradores = Arrays.asList(colaborador);

        Mockito.when(colaboradorRepository.findAll()).thenReturn(listaDeColaboradores);

        Assertions.assertTrue(colaboradorService.exibirTodosOsColaboradores()instanceof List);
    }
*/


}
