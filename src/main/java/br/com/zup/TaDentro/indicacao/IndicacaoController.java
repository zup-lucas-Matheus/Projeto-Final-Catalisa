package br.com.zup.TaDentro.indicacao;

import br.com.zup.TaDentro.Usuario.dto.UsuarioDto;
import br.com.zup.TaDentro.indicacao.dtos.IndicacaoResumidaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/indicacao")
public class IndicacaoController {

    @Autowired
    private IndicacaoService indicacaoService;
    @Autowired
    private ModelMapper modelMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping

    public IndicacaoResumidaDTO cadastrarIndicacao(@RequestBody @Valid Indicacao indicacao, Authentication authentication){
        String cpf = authentication.getName();
        Indicacao indicacaoModel = indicacaoService.saveIndicacao(cpf,indicacao);
        return modelMapper.map(indicacaoModel ,  IndicacaoResumidaDTO.class);
    }

    @GetMapping
    public List<IndicacaoResumidaDTO> indicacaoList(){

        List<IndicacaoResumidaDTO> indicacaoResumidaDTOS = indicacaoService.indicacaoList()
                .stream()
                .map(user -> modelMapper.map(user, IndicacaoResumidaDTO.class))
                .collect(Collectors.toList());

        return indicacaoResumidaDTOS;
    }

    @DeleteMapping("/{id}")
    public void deletarIndicacao(int id){
        indicacaoService.deleteIndicacao(id);
    }

    @PutMapping
    public void atualizarIndicacao(@RequestBody Indicacao indicacao){
        indicacaoService.atualizarIndicacao(indicacao);
    }

}
