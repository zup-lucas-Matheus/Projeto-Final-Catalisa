package br.com.zup.TaDentro.colaborador;

import br.com.zup.TaDentro.Usuario.Usuario;
import br.com.zup.TaDentro.enums.Cargo;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "/colaboradores")
public class Colaborador {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @NotBlank(message = "validacao.nome")
    @Size(min = 3, max = 20)
    private String nome;
    @Email(message = "validacao.email")
    private String email;
    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario loginUsuario;

    public Colaborador() {
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(Usuario loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
