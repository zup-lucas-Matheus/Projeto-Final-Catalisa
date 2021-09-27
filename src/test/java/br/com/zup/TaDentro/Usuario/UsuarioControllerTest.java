package br.com.zup.TaDentro.Usuario;

public class UsuarioControllerTest {


    private Usuario usuario;


    public void setUp () {
        //usuario = new Usuario();
        usuario = new Usuario();

       usuario.setNome("Leticia");
       usuario.setEmail("lele@ola.com.br");
       usuario.setSenha("12345");


    }


}
