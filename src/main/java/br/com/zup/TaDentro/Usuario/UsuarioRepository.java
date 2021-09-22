package br.com.zup.TaDentro.Usuario;

import br.com.zup.TaDentro.colaborador.Colaborador;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario,Integer> {

    Optional<Usuario> findByEmail(String email);



}
