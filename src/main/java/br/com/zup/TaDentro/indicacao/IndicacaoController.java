package br.com.zup.TaDentro.indicacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/indicacao")
public class IndicacaoController {

    @Autowired
    private IndicacaoService indicacaoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Indicacao cadastrarIndicacao(@RequestBody @Valid Indicacao indicacao){
        return indicacaoService.saveIndicacao(indicacao);
    }


}
