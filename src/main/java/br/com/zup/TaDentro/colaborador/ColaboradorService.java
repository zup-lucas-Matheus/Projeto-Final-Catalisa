package br.com.zup.TaDentro.colaborador;

import br.com.zup.TaDentro.colaborador.dtos.ColaboradorResumidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public Colaborador salvarColaborador(Colaborador colaborador) {
        return colaboradorRepository.save(colaborador);
    }

    public List<Colaborador> exibirTodosOsColaboradores() {
        return (List<Colaborador>) colaboradorRepository.findAll();
    }


    public Colaborador procurarSeColaboradorJaExiste(int id) {
        Optional<Colaborador> colaboradorOptional = colaboradorRepository.findById(id);

        if (colaboradorOptional.isPresent()) {
            return colaboradorOptional.get();
        }
        else {
            throw new RuntimeException("Colaborador já existe!");
        }

    }

    public Colaborador atualizarColaborador (Colaborador colaborador) {
        Colaborador objetoColaborador = procurarSeColaboradorJaExiste(colaborador.getId());

        colaborador.setNome(colaborador.getNome());
        colaborador.setId(colaborador.getId());
        colaborador.setCpf(colaborador.getCpf());
        colaborador.setLoginUsuario(colaborador.getLoginUsuario());
        colaborador.setDataContratacao(colaborador.getDataContratacao());

        return colaboradorRepository.save(colaborador);

    }

/*    public void deletarPorID(int id) {

        if (colaboradorExistente(id)) {
            colaboradorRepository.deleteById(id);
        } else {

            throw new RuntimeException("Colaborador não está na lista");
        }
    }*/

}














