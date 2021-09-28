package br.com.zup.TaDentro.formularioIndicacao;

import br.com.zup.TaDentro.Usuario.Usuario;
import br.com.zup.TaDentro.Usuario.UsuarioService;
import br.com.zup.TaDentro.colaborador.Colaborador;
import br.com.zup.TaDentro.colaborador.ColaboradorService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;

@WithMockUser(username = "xablau", password = "123")
@AutoConfigureMockMvc
@SpringBootTest
public class FormularioIndicacaoControllerTest {

    @MockBean
    private FormularioService formularioService;
    @Autowired
    private MockMvc mockMvc;
    private UsuarioService usuarioService;
    private ColaboradorService colaboradorService;
    private ObjectMapper objectMapper;
    private Colaborador colaborador;
    private Usuario usuario;


    @Test
    public void testarPesquisaPorDataEsitiacaoPositivo() throws Exception {

        Map<String, String> parametros =
                Map.of("dataInicial","2021-09-19", "dataFinal", "2021-10-10", "situacao", "CONTRATADO");

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();

        multiValueMap.add("dataInicial","2021-09-19");
        multiValueMap.add("dataFinal", "2021-10-10");
        multiValueMap.add("situacao", "CONTRATADO");
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/formulario")
                .params(multiValueMap).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }



}
