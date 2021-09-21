package br.com.zup.TaDentro.indicacao;

import br.com.zup.TaDentro.enums.PerfilDeSituacao;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "indicacao")
public class Indicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @NotBlank(message = "validacao.nome")
    private String nome;
    @NotNull
    private String cpf;
    private LocalDate dataDaContratacao;
    @Email(message = "validacao.email")
    private String email;
    @NotNull
    @Enumerated(EnumType.STRING)
    private PerfilDeSituacao situacao;

    public Indicacao() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataDaContratacao() {
        return dataDaContratacao;
    }

    public void setDataDaContratacao(LocalDate dataDaContratacao) {
        this.dataDaContratacao = dataDaContratacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PerfilDeSituacao getSituacao() {
        return situacao;
    }

    public void setSituacao(PerfilDeSituacao situacao) {
        this.situacao = situacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
