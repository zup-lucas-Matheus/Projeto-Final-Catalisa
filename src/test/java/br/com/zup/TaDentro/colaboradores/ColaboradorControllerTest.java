package br.com.zup.TaDentro.colaboradores;

import br.com.zup.TaDentro.colaborador.Colaborador;
import br.com.zup.TaDentro.colaborador.ColaboradorService;
import br.com.zup.TaDentro.enums.Cargo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

@WithMockUser(username = "Teste", password = "123")
@AutoConfigureMockMvc
@SpringBootTest
public class ColaboradorControllerTest {

    @MockBean
    private ColaboradorService colaboradorService;
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private Colaborador colaborador;

    @SpyBean
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        colaborador = new Colaborador();
        colaborador.setNome("André");
        colaborador.setCpf("12345678910");
        colaborador.setCargo(Cargo.ASSISTENTE);
        colaborador.setDataContratacao(LocalDate.now());
        objectMapper = new ObjectMapper();
    }


}


