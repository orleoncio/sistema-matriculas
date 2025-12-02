package tjw.sistema_matricula.model.dto;

import tjw.sistema_matricula.model.enums.Situacao;

import java.time.LocalDate;

public class MatriculaDto {
    private Long alunoId;
    private Long disciplinaId;
    private LocalDate dataMatricula;
    private Situacao situacao;
    private Double notaFinal;

    public void setAlunoId(Long alunoId){this.alunoId = alunoId;}
    public Long getAlunoId(){return alunoId;}
    public void setDisciplinaId(Long disciplinaId){this.disciplinaId = disciplinaId;}
    public Long getDisciplinaId(){return disciplinaId;}
    public void setDataMatricula(LocalDate dataMatricula){this.dataMatricula = dataMatricula;}
    public LocalDate getDataMatricula(){return dataMatricula;}
    public void setSituacao(Situacao situacao){this.situacao = situacao;}
    public Situacao getSituacao(){return situacao;}
    public void setNotaFinal(Double notaFinal){this.notaFinal = notaFinal;}
    public Double getNotaFinal(){return notaFinal;}
}
