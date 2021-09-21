package br.com.zup.TaDentro.indicacao;

import br.com.zup.TaDentro.enums.PerfilDeSituacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IndicacaoService {

    @Autowired
    private IndicacaoRepository indicacaoRepository;

    private List<Indicacao> indicacaoList = new ArrayList<>();

    //Metódo para cadastrar indicação.
    public Indicacao saveIndicacao(Indicacao indicado){
        indicacaoDuplicada(indicado.getCpf());
        indicado.setDataDaContratacao(LocalDate.now());
        if (!indicado.equals(PerfilDeSituacao.CONTRATADO)) {
            indicado.setSituacao(PerfilDeSituacao.EM_PROCESSO_SELETIVO);
        }
        else {
            indicacaoList.add(indicado);
        }

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

        indicacaoSalva.setEmail(indicacao.getEmail());
        indicacaoSalva.setDataDaContratacao(indicacao.getDataDaContratacao());
        indicacaoSalva.setNome(indicacao.getNome());

        indicacaoRepository.save(indicacaoSalva);
    }

    public Optional<Indicacao> indicacaoDuplicada(String cpf){
        Optional<Indicacao> indicacao = indicacaoRepository.findByCpf(cpf);

        if (indicacao.isPresent()) {
            throw new RuntimeException("Indicação já cadastrada");
        }
        return indicacao;
    }

    //Metódo para trazer todas as indicações.
    public List<Indicacao> indicacaoList(){
        return (List<Indicacao>) indicacaoRepository.findAll();
    }


}
