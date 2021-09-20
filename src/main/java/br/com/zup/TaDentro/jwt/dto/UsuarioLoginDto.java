package br.com.zup.TaDentro.jwt.dto;

public class UsuarioLoginDto {

    private String email;
    private String senha;

    public UsuarioLoginDto() {
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
