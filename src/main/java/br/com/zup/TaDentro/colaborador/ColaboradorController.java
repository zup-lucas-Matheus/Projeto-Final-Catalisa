package br.com.zup.TaDentro.colaborador;


import br.com.zup.TaDentro.colaborador.dtos.ColaboradorResumidoDTO;
import br.com.zup.TaDentro.jwt.filter.JwtComponent;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("colaborador")
public class ColaboradorController {


    private ColaboradorService colaboradorService;
    private JwtComponent jwtComponent;
    private ModelMapper modelMapper;

    @Autowired
    public ColaboradorController(ColaboradorService colaboradorService, JwtComponent jwtComponent, ModelMapper modelMapper) {
        this.colaboradorService = colaboradorService;
        this.jwtComponent = jwtComponent;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ColaboradorResumidoDTO salvarColaborador(@RequestBody @Valid Colaborador colaborador, Authentication authentication) {

        String email = authentication.getName();
        Colaborador colaboradorModel = colaboradorService.salvarColaborador(email,colaborador);
        return modelMapper.map(colaboradorModel, ColaboradorResumidoDTO.class);

    }

    @GetMapping
    public List<Colaborador> exibirTodosOsColaboradores() {

        return modelMapper.map(colaboradorService.exibirTodosOsColaboradores(), (Type) ColaboradorResumidoDTO.class);

    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPorID(@PathVariable int id) {
        colaboradorService.deletarColaborador(id);
    }


    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Colaborador atualizarColaborador() {

        ColaboradorResumidoDTO colaboradorResumidoDTO = new ColaboradorResumidoDTO();
        return modelMapper.map(colaboradorResumidoDTO, Colaborador.class);
    }

    @GetMapping
    public List<IndicacaoResumidaDTO> indicacaoList(@RequestBody IndicacaoPesquisaDto indicacaoPesquisaDto, Authentication authentication){
        String email = authentication.getName();
        List<Indicacao> retorno = colaboradorService.pesquisaPorData(email, indicacaoPesquisaDto.getDataInicial(), indicacaoPesquisaDto.getDataFinal());

        List<IndicacaoResumidaDTO> dtos = retorno
                .stream()
                .map(dto -> modelMapper.map(dto, IndicacaoResumidaDTO.class))
                .collect(Collectors.toList());
        return dtos;

    }


}
