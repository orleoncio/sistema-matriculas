package tjw.sistema_matricula.model.entity;

import jakarta.persistence.*;
import tjw.sistema_matricula.model.entity.register.RegisterCreate;
import tjw.sistema_matricula.model.enums.Situacao;

import java.time.LocalDate;

@Entity
@Table(
        name = "matricula",
        uniqueConstraints = @UniqueConstraint(
                name = "matricula_aluno_disciplina",
                columnNames = {"aluno_id", "disciplina_id"}
        )
)
public class Matricula extends RegisterCreate {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    private LocalDate dataMatricula;

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    private Double notaFinal;

    public Integer getId() {return id;}
    public void setAluno(Aluno aluno) {this.aluno = aluno;}
    public Aluno getAluno() {return aluno;}
    public void setDisciplina(Disciplina disciplina) {this.disciplina = disciplina;}
    public Disciplina getDisciplina() {return disciplina;}
    public void setDataMatricula(LocalDate dataMatricula) {this.dataMatricula = dataMatricula;}
    public LocalDate getDataMatricula() {return dataMatricula;}
    public void setSituacao(Situacao situacao) {this.situacao = situacao;}
    public Situacao getSituacao() {return situacao;}
    public void setNotaFinal(Double notaFinal) {this.notaFinal = notaFinal;}
    public Double getNotaFinal() {return notaFinal;}
}
