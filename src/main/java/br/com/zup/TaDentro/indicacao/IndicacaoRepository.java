package br.com.zup.TaDentro.indicacao;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IndicacaoRepository extends CrudRepository<Indicacao, Integer> {

    Optional<Indicacao> findByCpf(String cpf);


}
