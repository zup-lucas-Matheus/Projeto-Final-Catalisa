package br.com.zup.TaDentro.indicacao;

import br.com.zup.TaDentro.colaborador.Colaborador;
import br.com.zup.TaDentro.colaborador.ColaboradorService;
import br.com.zup.TaDentro.enums.PerfilDeSituacao;
import br.com.zup.TaDentro.jwt.UsuarioLoginService;
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

    private ColaboradorService colaboradorService;

    //Metódo para cadastrar indicação.
    public Indicacao saveIndicacao(int idColaborador, Indicacao indicado){

        Colaborador colaborador = colaboradorService.buscarColaboradorPorId(idColaborador);
        indicacaoDuplicada(indicado.getCpf());
        indicado.setColaborador(colaborador);
        indicado.setDataDaContratacao(LocalDate.now());
        indicado.setSituacao(PerfilDeSituacao.EM_PROCESSO_SELETIVO);
        return indicacaoRepository.save(indicado);
    }


    public Indicacao saveIndicacao(String cpf, Indicacao indicacao){
        Colaborador colaborador = colaboradorService.buscarColaboradorPorCpf(cpf);

        indicacao.setColaborador(colaborador);
        indicacao.setDataDaContratacao(LocalDate.now());

        return indicacaoRepository.save(indicacao);


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
