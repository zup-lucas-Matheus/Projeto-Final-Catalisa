package br.com.zup.TaDentro.colaborador;


import br.com.zup.TaDentro.colaborador.dtos.ColaboradorPUTDto;
import br.com.zup.TaDentro.colaborador.dtos.ColaboradorResumidoDTO;
import br.com.zup.TaDentro.jwt.filter.JwtComponent;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.ArrayList;
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
    public ColaboradorResumidoDTO salvarColaborador(@RequestBody @Valid Colaborador colaborador, Authentication authentication) {

        String email = authentication.getName();

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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarColaborador(@RequestBody ColaboradorPUTDto colaboradorPUTDto) {

        Colaborador colaboradorModel = modelMapper.map(colaboradorPUTDto, Colaborador.class);
        colaboradorService.atualizarColaborador(colaboradorModel);
    }

}
