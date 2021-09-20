package br.com.zup.TaDentro.Usuario.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UsuarioDto {

    private String nome;
    @Email(message = "Email obrigatória")
    private String email;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
