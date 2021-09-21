package br.com.zup.TaDentro.colaborador;

import br.com.zup.TaDentro.Usuario.Usuario;
import br.com.zup.TaDentro.enums.Cargo;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "colaborador")
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @NotBlank(message = "{validacao.nome.colaborador}")
    @Size(min = 3, max = 20)
    private String nome;
    @NotNull
    @Length(min = 11,max = 11, message = "{validacao.cpf.digito}")
    private String cpf;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Cargo cargo;
    @NotNull
    private LocalDate dataContratacao;
    private String email;

    @OneToOne
//    @JoinColumn(name = "id_usuario")
    private Usuario loginUsuario;

    public Colaborador() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
