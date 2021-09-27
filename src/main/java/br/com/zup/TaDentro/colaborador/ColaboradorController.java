package br.com.zup.TaDentro.colaborador;


import br.com.zup.TaDentro.Usuario.Usuario;
import br.com.zup.TaDentro.Usuario.dto.UsuarioDto;
import br.com.zup.TaDentro.colaborador.dtos.ColaboradorPUTDto;
import br.com.zup.TaDentro.colaborador.dtos.ColaboradorResumidoDTO;
import br.com.zup.TaDentro.jwt.filter.JwtComponent;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/colaborador")
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
    public ColaboradorResumidoDTO salvarColaborador(@RequestBody @Valid Colaborador colaborador, Authentication authentication) throws Exception {

        String email ="yanna@gmail.com";

        Colaborador colaboradorModel = colaboradorService.salvarColaborador(email, colaborador);

        return modelMapper.map(colaboradorModel, ColaboradorResumidoDTO.class);

    }

    @GetMapping
    public List<ColaboradorResumidoDTO> exibirTodosOsColaboradores() {

        List<Colaborador> listaDeColaboradores = colaboradorService.exibirTodosOsColaboradores();

        return listaDeColaboradores
                .stream()
                .map(colaborador -> modelMapper.map(colaborador, ColaboradorResumidoDTO.class))
                .collect(Collectors.toList());

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

    /*/Indicação de Pesquisa -
    @GetMapping
    public List<IndicacaoResumidaDTO> indicacaoList(@RequestBody IndicacaoPesquisaDto indicacaoPesquisaDto, Authentication authentication){
        String email = authentication.getName();
        List<Indicacao> retorno = colaboradorService.pesquisaPorData(email, indicacaoPesquisaDto.getDataInicial(), indicacaoPesquisaDto.getDataFinal());

        List<IndicacaoResumidaDTO> dtos = retorno
                .stream()
                .map(dto -> modelMapper.map(dto, IndicacaoResumidaDTO.class))
                .collect(Collectors.toList());
        return dtos;
        
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarColaborador(@RequestBody ColaboradorPUTDto colaboradorPUTDto) {


        Colaborador colaboradorModel = modelMapper.map(colaboradorPUTDto, Colaborador.class);
        colaboradorService.atualizarColaborador(colaboradorModel);
    }
}
