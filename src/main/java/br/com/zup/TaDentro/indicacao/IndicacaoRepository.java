package br.com.zup.TaDentro.indicacao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IndicacaoRepository extends CrudRepository<Indicacao, Integer> {

    Optional<Indicacao> findByCpf(String cpf);
    void deleteById(int id);


    /*List<Indicacao> findByColaboradorAndDataDeCadastroBetween(Colaborador colaborador, LocalDate dataInicial, LocalDate dataFinal);*/
    List<Indicacao> findByColaboradorAndDataDeCadastroBetween(Colaborador colaborador,
                                                              LocalDate dataInicial, LocalDate dataFinal);


    List<Indicacao> findByColaborador(Colaborador colaborador);

}