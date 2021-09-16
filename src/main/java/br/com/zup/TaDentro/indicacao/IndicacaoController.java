package br.com.zup.TaDentro.indicacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/indicacao")
public class IndicacaoController {

    @Autowired
    private IndicacaoService indicacaoService;




}
