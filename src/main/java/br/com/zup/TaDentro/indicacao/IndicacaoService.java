package br.com.zup.TaDentro.indicacao;

import br.com.zup.TaDentro.Usuario.Usuario;
import br.com.zup.TaDentro.Usuario.UsuarioService;
import br.com.zup.TaDentro.colaborador.Colaborador;
import br.com.zup.TaDentro.colaborador.ColaboradorService;
import br.com.zup.TaDentro.enums.PerfilDeSituacao;
import br.com.zup.TaDentro.jwt.UsuarioLoginService;
import br.com.zup.TaDentro.indicacao.exceptionIndicacao.MensagemErroIndicacao;
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
    @Autowired
    private ColaboradorService colaboradorService;
    @Autowired
    private UsuarioService usuarioService;

    /**
     *
     * Vinculo as Indicações ao Colaborador da empresa
     * Para utilizar essas informações em relatórios.
     */
    public Indicacao saveIndicacao(String email, Indicacao indicado){
        Usuario usuario = usuarioService.encontrarUsuarioPorEmail(email);
        Colaborador colaborador = colaboradorService.buscarColaboradorPorUsuario(usuario);
        indicacaoDuplicada(indicado.getCpf());
        indicado.setColaborador(colaborador);
        indicado.setDataDaContratacao(LocalDate.now());
        indicado.setSituacao(PerfilDeSituacao.EM_PROCESSO_SELETIVO);
        return indicacaoRepository.save(indicado);
    }


    //Metódo para buscar indicado por id.
    public Indicacao findIndicacao(int id){
        return indicacaoRepository.findById(id)
                .orElseThrow(() -> new MensagemErroIndicacao("Indicação não encontrada"));

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

    private void ajustarSituacaoEdata(Indicacao indicacao, Indicacao indicacaoSalva) {
        if (!Objects.isNull(indicacao.getDataDaContratacao())) {
            indicacaoSalva.setSituacao(PerfilDeSituacao.CONTRATADO);
            indicacaoSalva.setDataDaContratacao(indicacao.getDataDaContratacao());
        }
        if (!Objects.isNull(indicacao.getSituacao())) {
            indicacaoSalva.setSituacao(indicacao.getSituacao());
            if (indicacaoSalva.getSituacao() == PerfilDeSituacao.EX_COLABORADOR)
                indicacaoSalva.setDataDaContratacao(null);
        }
    }

    public void validaSituacao(Indicacao indicacao){
        if (Objects.isNull(indicacao.getSituacao()) && Objects.isNull(indicacao.getDataDaContratacao())) {
            throw new MensagemErroIndicacao("O campo situação ou a data da contratação é obrigatório");
        }

    }


    public Optional<Indicacao> indicacaoDuplicada(String cpf){
        Optional<Indicacao> indicacao = indicacaoRepository.findByCpf(cpf);

        if (indicacao.isPresent()) {
            throw new MensagemErroIndicacao("Indicação já cadastrada");
        }
        return indicacao;
    }

    //Metódo para trazer todas as indicações.
    public List<Indicacao> indicacaoList(){
        return (List<Indicacao>) indicacaoRepository.findAll();
    }


}
