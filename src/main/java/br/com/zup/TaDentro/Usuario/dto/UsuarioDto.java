package br.com.zup.TaDentro.Usuario.dto;

public class UsuarioDto {

    private String email;
    private String senha;

    public UsuarioDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
