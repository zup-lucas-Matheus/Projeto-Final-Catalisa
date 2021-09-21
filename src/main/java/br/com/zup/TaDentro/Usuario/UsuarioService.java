package br.com.zup.TaDentro.Usuario;

import br.com.zup.TaDentro.Usuario.exceptionUsuario.MensagemErroUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    public UsuarioRepository repository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //Usuário salvo
    public Usuario salvarUsuario(Usuario usuario){
        usuarioDuplicado(usuario.getEmail());
        String encode = bCryptPasswordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encode);
        return repository.save(usuario);
    }

    //Exibir Usuário cadastrado
    public List<Usuario> exibirUsuario(){
        return (List<Usuario>) repository.findAll();
    }

    //PesquisaUsuario
    public Usuario encontrarUsuario(int id){
        return repository.findById(id).orElseThrow(() -> new MensagemErroUsuario("Usuário não encontrado"));
    }

    public void deletarUsuario(int id){
        repository.delete(encontrarUsuario(id));
    }

    public Optional<Usuario> usuarioDuplicado(String email){

       Optional<Usuario> usuario =  repository.findByEmail(email);
        if (usuario.isPresent()) {
            throw new MensagemErroUsuario("Usuário já cadastrado!");
        }

        return usuario;

    }



}
