package br.com.zup.TaDentro.formularioIndicacao;

import br.com.zup.TaDentro.indicacao.Indicacao;
import br.com.zup.TaDentro.indicacao.dtos.IndicacaoResumidaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/formulario")
public class FormularioController {

    @Autowired
    private FormularioService formularioService;
    @Autowired
    private ModelMapper modelMapper;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<IndicacaoResumidaDTO> indicacaoList(@RequestParam(required = false) String dataInicial,
                                                    @RequestParam(required = false) String dataFinal,
                                                    @RequestParam(required = false) String situacao,
                                                    Authentication authentication){
        String email = authentication.getName();
        List<Indicacao> retorno =
                formularioService.pesquisaPorDataESituacao(email, dataInicial,dataFinal, situacao);

        List<IndicacaoResumidaDTO> dtos = retorno
                .stream()
                .map(dto -> modelMapper.map(dto, IndicacaoResumidaDTO.class))
                .collect(Collectors.toList());
        return dtos;

    }

}
