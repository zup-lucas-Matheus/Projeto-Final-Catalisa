package br.com.zup.TaDentro.colaboradores;

import br.com.zup.TaDentro.Usuario.Usuario;
import br.com.zup.TaDentro.colaborador.Colaborador;
import br.com.zup.TaDentro.colaborador.ColaboradorController;
import br.com.zup.TaDentro.colaborador.ColaboradorService;
import br.com.zup.TaDentro.enums.Cargo;
import br.com.zup.TaDentro.jwt.filter.JwtComponent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.With;
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
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;

import javax.annotation.security.RunAs;
import java.beans.BeanProperty;
import java.time.LocalDate;


@AutoConfigureMockMvc
@SpringBootTest
class ColaboradorControllerTest {

    @MockBean
    private ColaboradorService colaboradorService;
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private JwtComponent jwtComponent;
    private Colaborador colaborador;
    private Usuario usuario;
    @Mock
    private ObjectMapper objectMapper;
    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {

        usuario = new Usuario();
        usuario.setEmail("lucas@123.com");

        colaborador = new Colaborador();
        colaborador.setCpf("12345678910");
        colaborador.setNome("Lucas");
        colaborador.setCargo(Cargo.DEV_JR);
        colaborador.setLoginUsuario(usuario);

        objectMapper = new ObjectMapper();
    }

    @WithMockUser(username = "xablau", password = "123")
    @Test
    void testarMetodoCadastrarColaboradorComSucesso() throws Exception {

        Mockito.when(colaboradorService.salvarColaborador(Mockito.anyString(), Mockito.any(Colaborador.class)))
                .thenReturn(colaborador);

        String json = objectMapper.writeValueAsString(colaborador);

        ResultActions resultadoDaRequisicao = mockMvc
                .perform(MockMvcRequestBuilders.post("/colaborador")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

}
