package br.com.zup.TaDentro.indicacao;

import br.com.zup.TaDentro.enums.PerfilDeSituacao;
import br.com.zup.TaDentro.indicacao.dtos.IndicacaoPUTDto;
import br.com.zup.TaDentro.indicacao.dtos.IndicacaoResumidaDTO;
import br.com.zup.TaDentro.indicacao.exceptionIndicacao.MensagemErroIndicacao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;


@WithMockUser(username = "xablau", password = "123")
@AutoConfigureMockMvc
@SpringBootTest
public class IndicacaoTestController {

    @MockBean
    private IndicacaoService indicacaoService;
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private Indicacao indicacao;
    @SpyBean
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {

        indicacao = new Indicacao();
        indicacao.setEmail("lmatheus@123");
        indicacao.setCpf("12345678910");
        indicacao.setNome("Lucas");
        indicacao.setSituacao(PerfilDeSituacao.EM_PROCESSO_SELETIVO);
        objectMapper = new ObjectMapper();

    }

    @Test
    void testarMetodoCadastrarIndicacaoComSucesso() throws Exception {

        Indicacao indicacaoModel = modelMapper.map(indicacao, Indicacao.class);
        Mockito.when(indicacaoService.saveIndicacao(Mockito.anyString(), Mockito.any(Indicacao.class)))
                .thenReturn(indicacaoModel);
        String json = objectMapper.writeValueAsString(indicacao);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.post("/indicacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    void testarMetodoAtualizarIndicacaoComSucesso() throws Exception {

        Indicacao indicacaoSalva = modelMapper.map(indicacao, Indicacao.class);

        Mockito.when(indicacaoService.atualizarIndicacao(Mockito.any()))
                .thenReturn(indicacaoSalva);

        String json = objectMapper.writeValueAsString(indicacao);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.put("/indicacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }



    @Test
    public void testarDeleteDaIndicacaoCaminhoPositivo() throws Exception {

        Mockito.when(indicacaoService.findIndicacao(Mockito.anyInt()))
                .thenReturn(indicacao);

        indicacaoService.deleteIndicacao(1);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/indicacao/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    public void testarDeleteIndicacaoCaminhoNegativo() throws Exception {

        Mockito.doThrow(MensagemErroIndicacao.class).when(indicacaoService).deleteIndicacao(Mockito.anyInt());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/indicacao/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testarListagemDeIndicacaoPositivo() throws Exception {

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/indicacao")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}
