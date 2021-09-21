package br.com.zup.TaDentro.colaborador;

import br.com.zup.TaDentro.Usuario.Usuario;
import br.com.zup.TaDentro.Usuario.exceptionUsuario.MensagemErroUsuario;
import br.com.zup.TaDentro.colaborador.dtos.ColaboradorResumidoDTO;
import br.com.zup.TaDentro.colaborador.exceptionColaborador.MensagemErroColaborador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public Colaborador salvarColaborador(Colaborador colaborador) {
        colaboradorDuplicado(colaborador.getCpf());
        return colaboradorRepository.save(colaborador);
    }

    public List<Colaborador> exibirTodosOsColaboradores() {
        return (List<Colaborador>) colaboradorRepository.findAll();
    }


    public Colaborador procurarSeColaboradorJaExiste(int id) {
        Optional<Colaborador> colaboradorOptional = colaboradorRepository.findById(id);

        if (colaboradorOptional.isPresent()) {
            return colaboradorOptional.get();
        } else {
            throw new MensagemErroColaborador("Colaborador já existe!");
        }

    }

    public Colaborador atualizarColaborador(Colaborador colaborador) {
        Colaborador objetoColaborador = procurarSeColaboradorJaExiste(colaborador.getId());

        colaborador.setNome(colaborador.getNome());
        colaborador.setId(colaborador.getId());
        colaborador.setCpf(colaborador.getCpf());
        colaborador.setLoginUsuario(colaborador.getLoginUsuario());
        colaborador.setDataContratacao(colaborador.getDataContratacao());

        return colaboradorRepository.save(colaborador);

    }

    public void deletarColaborador(int id) {
        colaboradorRepository.delete(procurarSeColaboradorJaExiste(id));
    }

    public Optional<Colaborador> colaboradorDuplicado(String cpf) {
        Optional<Colaborador> colaborador = colaboradorRepository.findByCpf(cpf);

        if (colaborador.isPresent()) {
            throw new MensagemErroColaborador("Colaborador já cadastrado");
        }
        return colaborador;

    }

}














