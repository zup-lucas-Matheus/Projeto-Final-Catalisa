package br.com.zup.TaDentro.indicacao;

import br.com.zup.TaDentro.Usuario.Usuario;
import br.com.zup.TaDentro.Usuario.UsuarioService;
import br.com.zup.TaDentro.colaborador.Colaborador;
import br.com.zup.TaDentro.colaborador.ColaboradorService;
import br.com.zup.TaDentro.enums.Cargo;
import br.com.zup.TaDentro.enums.PerfilDeSituacao;
import br.com.zup.TaDentro.indicacao.exceptionIndicacao.MensagemErroFiltroIndicacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class IndicacaoServiceTest {

    @Autowired
    private IndicacaoService indicacaoService;
    @Mock
    private ColaboradorService colaboradorService;
    @Mock
    private UsuarioService usuarioService;

    @MockBean
    private IndicacaoRepository indicacaoRepository;

    private Colaborador colaborador = criarColaborador();
    private Indicacao indicacao = criaIndicacao();

    private Indicacao criaIndicacao() {
        indicacao = new Indicacao();
        indicacao.setCpf("12345678910");
        indicacao.setEmail("joao@123");
        indicacao.setNome("Joao");
        indicacao.setSituacao(PerfilDeSituacao.EM_PROCESSO_SELETIVO);

        return indicacao;
    }

    private Colaborador criarColaborador(){
        colaborador = new Colaborador();

        Colaborador colaborador = new Colaborador();
        colaborador.setCpf("12345678910");
        colaborador.setNome("Teste");
        colaborador.setDataContratacao(LocalDate.now());
        colaborador.setCargo(Cargo.DEV_JR);

        return colaborador;
    }


    @Test
    public void testarMetódoSalvarIndicacaoPositivo(){

        Usuario usuario = new Usuario();
        usuario.setEmail("andre@123.com");


        Mockito.when(usuarioService.encontrarUsuarioPorEmail(usuario.getEmail()))
                .thenReturn(usuario);
        Mockito.when(colaboradorService.buscarColaboradorPorUsuario(usuario))
                .thenReturn(colaborador);


        Mockito.when(indicacaoRepository.findByCpf(indicacao.getCpf()))
                .thenReturn(Optional.empty());

        indicacao.setColaborador(colaborador);

        Mockito.when(indicacaoRepository.save(Mockito.any())).thenReturn(indicacao);
        Indicacao indicacaoSalva = indicacaoService.saveIndicacao(usuario.getEmail(), indicacao);

        Assertions.assertEquals(indicacao.getCpf(), indicacaoSalva.getCpf());
        Assertions.assertEquals(indicacao.getEmail(), indicacaoSalva.getEmail());
        Assertions.assertEquals(indicacao.getNome(), indicacaoSalva.getNome());
        Assertions.assertNotNull(indicacaoSalva.getDataDeCadastro());
        Assertions.assertEquals(indicacaoSalva.getSituacao(), PerfilDeSituacao.EM_PROCESSO_SELETIVO);

    }

    @Test
    public void testePesquisarIndicacaoSemFiltroPositivo(){
        List<Indicacao> indicacaoList = List.of(indicacao);

       Mockito.when(indicacaoRepository.findByColaborador(colaborador)).thenReturn(indicacaoList);
       List<Indicacao> retorno = indicacaoService.pesquisarIndicacao(colaborador, null, null, null);

       Assertions.assertEquals(indicacaoList.size(), retorno.size());

    }
    @Test
    public void testePesquisarIndicacaoPorDataPositivo(){
        List<Indicacao> indicacaoList = List.of(indicacao);

        Mockito.when(indicacaoRepository.findByColaboradorAndDataDeCadastroBetween
                (colaborador, LocalDate.parse("2021-01-10"), LocalDate.parse("2021-10-01")))
                .thenReturn(indicacaoList);
        List<Indicacao> retorno = indicacaoService.pesquisarIndicacao
                (colaborador, "2021-01-10", "2021-10-01", null);

        Assertions.assertEquals(indicacaoList.size(), retorno.size());
    }
    @Test
    public void testePesquisarIndicacaoPorSituacaoPositivo(){
        List<Indicacao> indicacaoList = List.of(indicacao);

        Mockito.when(indicacaoRepository.findByColaborador(colaborador)).thenReturn(indicacaoList);
        List<Indicacao> retorno = indicacaoService.pesquisarIndicacao(colaborador, null, null, "EM_PROCESSO_SELETIVO");

        Assertions.assertEquals(indicacaoList.size(), retorno.size());

    }

    @Test
    public void testePesquisarIndicacaoPorDataNegativoLancaException(){

        List<Indicacao> indicacaoList = List.of(indicacao);

        Mockito.when(indicacaoRepository.findByColaboradorAndDataDeCadastroBetween
                (colaborador, LocalDate.parse("2021-01-10"), LocalDate.parse("2021-10-01")))
                .thenReturn(indicacaoList);

        RuntimeException exceptionDataInicial = Assertions
                .assertThrows(RuntimeException.class, () -> {indicacaoService.pesquisarIndicacao(colaborador, "2021-01-10", null, null);});
        Assertions.assertTrue(exceptionDataInicial.getMessage().equals("A data precisa ser preenchida corretamente"));

        RuntimeException exceptionDataFinal = Assertions
                .assertThrows(RuntimeException.class, () -> {indicacaoService.pesquisarIndicacao(colaborador, null, "2021-10-01", null);});
        Assertions.assertTrue(exceptionDataFinal.getMessage().equals("A data precisa ser preenchida corretamente"));

        RuntimeException exceptionDataInicialMaior = Assertions
                .assertThrows(RuntimeException.class, () -> {indicacaoService.pesquisarIndicacao(colaborador, "2021-10-02", "2021-10-01", null);});
        Assertions.assertTrue(exceptionDataInicialMaior.getMessage().equals("Data Inicial maior que Data Final"));

    }
    @Test
    public void testarPesquisarIndicacaoSemResultadoComFiltro(){
        MensagemErroFiltroIndicacao mensagemErroFiltroIndicacao
                = new MensagemErroFiltroIndicacao("Não existem Indicações cadastradas no período");

        Mockito.when(indicacaoRepository.findByColaboradorAndDataDeCadastroBetween
                (colaborador,LocalDate.parse("2021-01-10"), LocalDate.parse("2021-10-01")))
                .thenThrow(mensagemErroFiltroIndicacao);

        RuntimeException exceptionComData = Assertions
                .assertThrows(RuntimeException.class, () ->
                {indicacaoService
                        .pesquisarIndicacao
                        (colaborador, "2021-01-10", "2021-10-01", "EM_PROCESSO_SELETIVO");});
        Assertions.assertTrue(exceptionComData.getMessage().equals("Não existem Indicações cadastradas no período"));

    }
    @Test
    public void testarPesquisarIndicacaoResultadoSemFiltro(){

        MensagemErroFiltroIndicacao mensagemErroFiltroIndicacao
                = new MensagemErroFiltroIndicacao("Não existem Indicações cadastradas");

        Mockito.when(indicacaoRepository.findByColaborador(colaborador))
                .thenThrow(mensagemErroFiltroIndicacao);

        RuntimeException exceptionComData = Assertions
                .assertThrows(RuntimeException.class, () ->
                {indicacaoService
                        .pesquisarIndicacao
                                (colaborador, null, null, null);});
        Assertions.assertTrue(exceptionComData.getMessage().equals("Não existem Indicações cadastradas"));

    }

    @Test
    public void testarEncontrarIndicacaoPorCPFpositivo(){

        Indicacao indicacao = new Indicacao();

        Mockito.when(indicacaoRepository.findByCpf(Mockito.anyString())).thenReturn(Optional.of(indicacao));
        Assertions.assertEquals(indicacao, indicacaoService.findIndicacaoPorCpf("12345678781"));
    }

    @Test
    public void testarEncontrarIndicacaoPorCFPnegativoParaLancaExcecao(){
        Indicacao indicacao = new Indicacao();
        Optional<Indicacao> optionalIndicacao = Optional.empty();
        Mockito.when(indicacaoRepository.findByCpf(Mockito.anyString())).thenReturn(optionalIndicacao)
                .thenReturn(optionalIndicacao);

        RuntimeException exception = Assertions
                .assertThrows(RuntimeException.class, () -> {indicacaoService.findIndicacaoPorCpf("1234567881");});
        Assertions.assertTrue(exception.getMessage().equals("CPF da indicação é obrigatório"));

    }

    @Test
    public void testarEncontrarIndicacaoPorIdPositivo(){
        Indicacao indicacao = new Indicacao();

        Mockito.when(indicacaoRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(indicacao));
        Assertions.assertEquals(indicacao, indicacaoService.findIndicacao(1));

    }

    @Test
    public void testarEncontrarIndicacaoPorIdNegativoParaLancaExecao(){

        Indicacao indicacao = new Indicacao();
        Optional<Indicacao> indicacaoOptional = Optional.empty();

        Mockito.when(indicacaoRepository.findById(Mockito.anyInt()))
                .thenReturn(indicacaoOptional);

        RuntimeException exception = Assertions
                .assertThrows(RuntimeException.class, () -> {indicacaoService.findIndicacao(1);});

        Assertions.assertTrue(exception.getMessage().equals("Indicação não encontrada"));


    }

    @Test
    public void testeParaValidacaoDeDataInicialMaiorQueDataFinal(){

        LocalDate date = LocalDate.now().plusDays(1);
        LocalDate dateFinal = LocalDate.now();

        RuntimeException exception = Assertions
                .assertThrows(RuntimeException.class, () ->
                {indicacaoService.validacaoPorDataInicialMaiorQueDataFinal(date,dateFinal);});

        Assertions.assertTrue(exception.getMessage().equals("Data Inicial maior que Data Final"));
    }

    @Test
    public void testeParavalidacaoPorDataInicial(){

        String dateFinal = "2021-02-12";

        RuntimeException exception = Assertions
                .assertThrows(RuntimeException.class, () ->
                {indicacaoService.validacaoPorDataInicialORdataFinal(null,dateFinal);});

        Assertions.assertTrue(exception.getMessage().equals("A data precisa ser preenchida corretamente"));
    }

    @Test
    public void testeAtualizarSituacaPositivo(){

        indicacao.setSituacao(PerfilDeSituacao.NAO_CONTRATADO);
        Mockito.when(indicacaoRepository.findByCpf(Mockito.anyString()))
                .thenReturn(Optional.of(indicacao));

        indicacaoService.atualizarIndicacao(indicacao);
        Mockito.verify(indicacaoRepository).save(indicacao);
    }

    @Test
    public void testeAtualizarDataContratacaoPositivo(){

        indicacao.setDataDaContratacao(LocalDate.now());
        Mockito.when(indicacaoRepository.findByCpf(Mockito.anyString()))
                .thenReturn(Optional.of(indicacao));

        indicacaoService.atualizarIndicacao(indicacao);
        Mockito.when(indicacaoRepository.save(indicacao))
                .thenReturn(indicacao);

        Assertions.assertEquals(indicacao.getSituacao(),PerfilDeSituacao.CONTRATADO);

    }

    @Test
    public void testeAtualizarDataContratacaoParaNull(){
        var indicacaoSalva = criaIndicacao();

        indicacao.setSituacao(PerfilDeSituacao.EX_COLABORADOR);
        Mockito.when(indicacaoRepository.findByCpf(Mockito.anyString()))
                .thenReturn(Optional.of(indicacao));

        Mockito.when(indicacaoRepository.save(indicacao))
                .thenReturn(indicacaoSalva);

        indicacaoSalva = indicacaoService.atualizarIndicacao(indicacao);

        Assertions.assertNull(indicacaoSalva.getDataDaContratacao());
    }



    @Test
    public void testeAtualizarSituacaoNegativo(){

        indicacao.setSituacao(null);
        indicacao.setDataDaContratacao(null);
        RuntimeException exception = Assertions
                .assertThrows(RuntimeException.class, () ->
                {indicacaoService.atualizarIndicacao(indicacao);});

        Assertions.assertTrue(exception.getMessage().equals("O campo situação ou a data da contratação é obrigatório"));
    }

}
