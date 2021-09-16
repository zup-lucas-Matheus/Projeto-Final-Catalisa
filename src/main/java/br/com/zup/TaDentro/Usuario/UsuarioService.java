package br.com.zup.TaDentro.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    public Repository repository;

    //Usuário salvo
    public Usuario salvarUsuario(Usuario usuario){
        return repository.save(usuario);
    }

    //Exibir Usuário cadastrado
    public List<Usuario> exibirUsuario(){
        return (List<Usuario>) repository.findAll();
    }

    //PesquisaUsuario
    public Usuario encontrarUsuario(int id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public void deletarUsuario(int id){
        repository.delete(encontrarUsuario(id));
    }


    public void atualizarUsuario(Usuario usuario){
        Usuario user = encontrarUsuario(usuario.getId());


        user.setNome(usuario.getNome());
        user.setEmail(usuario.getEmail());
        user.setSenha(usuario.getSenha());


    }

}
