package br.com.zup.TaDentro.jwt;

import br.com.zup.TaDentro.Usuario.Usuario;
import br.com.zup.TaDentro.Usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioLoginService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(userName);
        //mesma coisa do if pra verificar se tem o usuario no Optional
        usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        Usuario usuario = usuarioOptional.get();

        return new UsuarioLogin(usuario.getId(), usuario.getEmail(), usuario.getSenha());
    }

}

