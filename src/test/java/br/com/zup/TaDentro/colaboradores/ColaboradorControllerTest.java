package br.com.zup.TaDentro.colaboradores;

import br.com.zup.TaDentro.colaborador.Colaborador;
import br.com.zup.TaDentro.colaborador.ColaboradorService;
import br.com.zup.TaDentro.enums.Cargo;

import br.com.zup.TaDentro.enums.PerfilDeSituacao;
import br.com.zup.TaDentro.indicacao.Indicacao;
import br.com.zup.TaDentro.indicacao.IndicacaoService;
import br.com.zup.TaDentro.jwt.filter.JwtComponent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.With;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import org.springframework.http.MediaType;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;


@WithMockUser(username = "xablau", password = "123")



@AutoConfigureMockMvc
@SpringBootTest
public class ColaboradorControllerTest {

    @MockBean
    private ColaboradorService colaboradorService;
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private Colaborador colaborador;
    private Usuario usuario;
    @SpyBean
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {

        usuario = new Usuario();
        usuario.setEmail("lucas@123");

        colaborador = new Colaborador();
        colaborador.setNome("André");
        colaborador.setCpf("12345678910");

        colaborador.setNome("Lucas");
        colaborador.setCargo(Cargo.DEV_JR);
        colaborador.setDataContratacao(LocalDate.now());
        colaborador.setLoginUsuario(usuario);


        colaborador.setCargo(Cargo.ASSISTENTE);
        colaborador.setDataContratacao(LocalDate.now());

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void testarMetodoCadastrarIndicacaoComSucesso() throws Exception {

        Mockito.when(colaboradorService.salvarColaborador(Mockito.anyString(), Mockito.any(Colaborador.class)))
                .thenReturn(colaborador);

        String json = objectMapper.writeValueAsString(colaborador);
      
      ResultActions resultadoDaRequisicao = mockMvc
                .perform(MockMvcRequestBuilders.post("/colaborador")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated());

}



