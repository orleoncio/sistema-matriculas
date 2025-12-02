package tjw.sistema_matricula.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tjw.sistema_matricula.model.dto.MatriculaDto;
import tjw.sistema_matricula.model.entity.Aluno;
import tjw.sistema_matricula.model.entity.Disciplina;
import tjw.sistema_matricula.model.entity.Matricula;
import tjw.sistema_matricula.repository.AlunoRepository;
import tjw.sistema_matricula.repository.DisciplinaRepository;
import tjw.sistema_matricula.repository.MatriculaRepository;

import java.util.List;

@Service
public class MatriculaService {

    private MatriculaRepository matriculaRepository;
    private AlunoRepository alunoRepository;
    private DisciplinaRepository disciplinaRepository;

    public MatriculaService(MatriculaRepository matriculaRepository,
                            AlunoRepository alunoRepository,
                            DisciplinaRepository disciplinaRepository) {
        this.matriculaRepository = matriculaRepository;
        this.alunoRepository = alunoRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    public boolean existsByAlunoAndDisciplina(Long alunoId, Long disciplinaId) {
        return matriculaRepository.existsByAluno_IdAndDisciplina_Id(alunoId, disciplinaId);
    }

    public Matricula criaMatricula(MatriculaDto matriculaDto){
        if(existsByAlunoAndDisciplina(matriculaDto.getAlunoId(), matriculaDto.getDisciplinaId())){
            throw new RuntimeException("Aluno ja matriculado na disciplina");
        }

        Aluno aluno = alunoRepository.findById(matriculaDto.getAlunoId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        Disciplina disciplina = disciplinaRepository.findById(matriculaDto.getDisciplinaId())
                .orElseThrow(() -> new RuntimeException("Disciplina não existe"));

        Matricula matricula = new Matricula();
        matricula.setAluno(aluno);
        matricula.setDisciplina(disciplina);
        matricula.setDataMatricula(matriculaDto.getDataMatricula());
        matricula.setSituacao(matriculaDto.getSituacao());
        matricula.setNotaFinal(matriculaDto.getNotaFinal());

        return matriculaRepository.save(matricula);
    }

    public List<Matricula> buscaMatriculas(){
        return matriculaRepository.findAll();
    }

    public Matricula buscaMatriculaPorId(Long id){
        return matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matricula não encontrada"));
    }

    public Matricula alteraMatricula(MatriculaDto dados, Long id){
        Matricula matricula = buscaMatriculaPorId(id);
        matricula.setDataMatricula(dados.getDataMatricula());
        matricula.setSituacao(dados.getSituacao());
        matricula.setNotaFinal(dados.getNotaFinal());

        if(!existsByAlunoAndDisciplina(dados.getAlunoId(), dados.getDisciplinaId())){
            Aluno aluno = alunoRepository.findById(dados.getAlunoId())
                    .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
            Disciplina disciplina = disciplinaRepository.findById(dados.getDisciplinaId())
                    .orElseThrow(() -> new RuntimeException("Disciplina não existe"));

            matricula.setAluno(aluno);
            matricula.setDisciplina(disciplina);
        }

        return matriculaRepository.save(matricula);
    }

    @Transactional
    public void deletaMatricula(Long id){
        if(buscaMatriculaPorId(id) == null){
            throw new RuntimeException("Matricula não existe");
        }
        matriculaRepository.deleteById(id);
    }
}
