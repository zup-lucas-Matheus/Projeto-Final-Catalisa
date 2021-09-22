package br.com.zup.TaDentro.colaborador;

import br.com.zup.TaDentro.Usuario.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface ColaboradorRepository extends CrudRepository <Colaborador , Integer> {

    Optional<Colaborador> findByCpf(String cpf);

    Optional<Colaborador> findByLoginUsuario(Usuario usuario);

}
