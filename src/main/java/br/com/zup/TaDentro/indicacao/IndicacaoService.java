package br.com.zup.TaDentro.indicacao;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@org.springframework.stereotype.Service
public class IndicacaoService {

    @Autowired
    private IndicacaoRepository indicacaoRepository;

    //Metódo para cadastrar indicação.
    public Indicacao saveIndicacao(Indicacao indicado){
        return indicacaoRepository.save(indicado);
    }

    //Metódo para buscar indicado por id.
    public Indicacao findIndicacao(int id){
        return indicacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Indicação não encontrada"));

    }

    //Metódo para deletar indicaçao com exption.
    public void deleteIndicacao(int id){
        indicacaoRepository.delete(findIndicacao(id));
    }

    //Metódo para atualizar indicação...
    public void atualizarIndicacao(Indicacao indicacao){
        Indicacao indicacaoSalva = findIndicacao(indicacao.getId());

        indicacaoSalva.setMensagem(indicacao.getMensagem());
        indicacaoSalva.setDataDeIndicacao(indicacao.getDataDeIndicacao());
        indicacaoSalva.setNome(indicacao.getNome());

        indicacaoRepository.save(indicacaoSalva);

    }


}
