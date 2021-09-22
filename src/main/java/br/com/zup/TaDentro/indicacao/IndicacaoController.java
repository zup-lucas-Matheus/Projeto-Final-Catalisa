package br.com.zup.TaDentro.indicacao;

import br.com.zup.TaDentro.indicacao.dtos.IndicacaoResumidaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
        return modelMapper.map(indicacaoModel , IndicacaoResumidaDTO.class);

    }

    @GetMapping
    public List<Indicacao> indicacaoList(){
        return indicacaoService.indicacaoList();
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
