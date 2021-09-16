package br.com.zup.TaDentro.colaborador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public Colaborador salvarColaborador (Colaborador colaborador) {
        return colaboradorRepository.save(colaborador);
    }

    public List <Colaborador> exibirTodosOsColaboradores () {
        return (List<Colaborador>) colaboradorRepository.findAll();
    }

    public boolean colaboradorExistente (String id) {
        return colaboradorRepository.existsById(id);
    }






}
