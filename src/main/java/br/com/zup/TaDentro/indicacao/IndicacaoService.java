package br.com.zup.TaDentro.indicacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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

        indicacaoSalva.setTelefone(indicacao.getTelefone());
        indicacaoSalva.setEmail(indicacao.getEmail());
        indicacaoSalva.setDataDaContratacao(indicacao.getDataDaContratacao());
        indicacaoSalva.setNome(indicacao.getNome());

        indicacaoRepository.save(indicacaoSalva);
    }

    //Metódo para trazer todas as indicações.
    public List<Indicacao> indicacaoList(){
        return (List<Indicacao>) indicacaoRepository.findAll();
    }


}
