package br.com.zup.TaDentro.indicacao;

import br.com.zup.TaDentro.colaborador.Colaborador;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IndicacaoRepository extends CrudRepository<Indicacao, Integer> {

    Optional<Indicacao> findByCpf(String cpf);

    List<Indicacao> findByColaboradorAndDataDeCadastroBetween(Colaborador colaborador,
                                                              LocalDate dataInicial, LocalDate dataFinal);


    List<Indicacao> findByColaborador(Colaborador colaborador);

}
