package br.com.zup.TaDentro.colaborador;

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

    public boolean colaboradorExistente(int id) {
        return colaboradorRepository.existsById(id);
    }

    public Colaborador procurarSeColaboradorJaExiste(int id) {
        Optional<Colaborador> colaboradorOptional = colaboradorRepository.findById(id);

        if (colaboradorOptional.isPresent()) {
            return colaboradorOptional.get();
        }

        throw new RuntimeException("Colaborador não trabalha aqui");

    }

    public void deletarPorID (int id) {
        colaboradorRepository.deleteById(id);
    }

}













