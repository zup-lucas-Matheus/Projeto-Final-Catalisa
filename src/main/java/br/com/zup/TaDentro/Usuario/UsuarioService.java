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
    public Usuario salvarUsuario(Usuario usuario) throws Exception {
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
    public Usuario encontrarUsuario(int id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new MensagemErroUsuario("Usuário não encontrado"));
    }

    public Optional<Usuario> usuarioDuplicado(String email) throws Exception {
        if (repository.findByEmail(email).isPresent()) {
            throw new MensagemErroUsuario("Usuario já existe com esse e-mail!");
        }
        return repository.findByEmail(email);
    }

    public void deletarUsuario(int id) throws Exception {
        repository.delete(encontrarUsuario(id));
    }

    public void atualizarUsuario(Usuario usuario) throws Exception {
        Usuario user = encontrarUsuario(usuario.getId());

        user.setEmail(usuario.getEmail());
        user.setSenha(usuario.getSenha());
        repository.save(user);
    }


}
