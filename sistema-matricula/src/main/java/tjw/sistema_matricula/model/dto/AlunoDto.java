package tjw.sistema_matricula.model.dto;

import tjw.sistema_matricula.model.enums.Status;

import java.time.LocalDate;

public class AlunoDto {
    private String nome;
    private String matricula;
    private String email;
    private LocalDate dataNascimento;
    private String status;

    public void setNome(String nome){this.nome = nome;}
    public String getNome(){return nome;}
    public void setMatricula(String matricula){this.matricula = matricula;}
    public String getMatricula(){return matricula;}
    public void setEmail(String email){this.email = email;}
    public String getEmail(){return email;}
    public void setDataNascimento(LocalDate data){this.dataNascimento = data;}
    public LocalDate getDataNascimento(){return dataNascimento;}
    public void setStatus(String status){this.status = status;}
    public String getStatus(){return status;}
}
