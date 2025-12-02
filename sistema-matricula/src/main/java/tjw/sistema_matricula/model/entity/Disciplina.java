package tjw.sistema_matricula.model.entity;

import jakarta.persistence.*;
import tjw.sistema_matricula.model.entity.register.RegisterCreate;

@Entity
@Table(name = "disciplina")
public class Disciplina extends RegisterCreate {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String codigo;

    private String nome;
    private Integer cargaHoraria;
    private String semestre;

    public Long getId() {return id;}
    public void setCodigo(String codigo) {this.codigo = codigo;}
    public String getCodigo() {return codigo;}
    public void setNome(String nome) {this.nome = nome;}
    public String getNome() {return nome;}
    public void setCargaHoraria(Integer cargaHoraria) {this.cargaHoraria = cargaHoraria;}
    public Integer getCargaHoraria() {return cargaHoraria;}
    public void setSemestre(String semestre) {this.semestre = semestre;}
    public String getSemestre() {return semestre;}
}
