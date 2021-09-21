package br.com.zup.TaDentro.indicacao;

import br.com.zup.TaDentro.indicacao.dtos.IndicacaoResumidaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/indicacao")
public class IndicacaoController {

    @Autowired
    private IndicacaoService indicacaoService;
    @Autowired
    private ModelMapper modelMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public IndicacaoResumidaDTO cadastrarIndicacao(@RequestBody @Valid Indicacao indicacao){

        Indicacao indicacaoModel = indicacaoService.saveIndicacao(indicacao);
        return modelMapper.map(indicacaoModel , IndicacaoResumidaDTO.class);
    }


}
