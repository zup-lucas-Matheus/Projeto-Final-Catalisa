package br.com.zup.TaDentro.indicacao;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;

@org.springframework.stereotype.Service
public class IndicacaoService {

    @Autowired
    private IndicacaoRepository indicacaoRepository;

    //Metódo para cadastrar indicação.
    public Indicacao saveIndicacao(Indicacao indicado){
        return indicacaoRepository.save(indicado);
    }


}
