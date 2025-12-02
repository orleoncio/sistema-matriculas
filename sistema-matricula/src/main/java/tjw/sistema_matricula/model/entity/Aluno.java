package tjw.sistema_matricula.model.entity;

import jakarta.persistence.*;
import tjw.sistema_matricula.model.entity.register.RegisterCreate;
import tjw.sistema_matricula.model.enums.Status;

import java.time.LocalDate;

@Entity
@Table(name = "aluno")
public class Aluno extends RegisterCreate {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    @Column(unique = true)
    private String matricula;

    private String email;
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Long getId() {return id;}
    public void setNome(String nome) {this.nome = nome;}
    public String getNome() {return nome;}
    public void setMatricula(String matricula) {this.matricula = matricula;}
    public String getMatricula() {return matricula;}
    public void setEmail(String email) {this.email = email;}
    public String getEmail() {return email;}
    public void setDataNascimento(LocalDate dataNascimento) {this.dataNascimento = dataNascimento;}
    public LocalDate getDataNascimento() {return dataNascimento;}
    public void setStatus(Status status) {this.status = status;}
    public Status getStatus() {return status;}
}
