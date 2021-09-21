package br.com.zup.TaDentro.colaborador;

import br.com.zup.TaDentro.Usuario.Usuario;
import br.com.zup.TaDentro.enums.Cargo;

import javax.persistence.*;
<<<<<<< HEAD
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
=======
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
>>>>>>> cd55390c2f7d35ee984264b9d01df604bf600cb7

@Entity
@Table(name = "colaborador")
public class Colaborador {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
<<<<<<< HEAD
    @NotBlank(message = "validacao.nome")
    @Size(min = 3, max = 20)
    private String nome;
    @Email(message = "validacao.email")
    private String email;
=======
    private String nome;
    @NotNull
    private String cpf;
    @NotNull
>>>>>>> cd55390c2f7d35ee984264b9d01df604bf600cb7
    @Enumerated(EnumType.STRING)
    private Cargo cargo;
    @NotNull
    private LocalDate dataContratacao;

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
}
