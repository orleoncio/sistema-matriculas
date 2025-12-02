package tjw.sistema_matricula.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tjw.sistema_matricula.model.entity.Aluno;
import tjw.sistema_matricula.model.dto.AlunoDto;
import tjw.sistema_matricula.model.enums.Status;
import tjw.sistema_matricula.repository.AlunoRepository;
import tjw.sistema_matricula.repository.DisciplinaRepository;
import tjw.sistema_matricula.repository.MatriculaRepository;

import java.util.List;

@Service
public class AlunoService {

    private AlunoRepository alunoRepository;
    private MatriculaRepository matriculaRepository;

    public AlunoService(AlunoRepository alunoRepository, MatriculaRepository matriculaRepository) {
        this.alunoRepository = alunoRepository;
        this.matriculaRepository = matriculaRepository;
    }

    public boolean existsByMatricula(String matricula) {
        return alunoRepository.existsByMatricula(matricula);
    }

    public Aluno criaAluno(AlunoDto alunoDto){
        if(existsByMatricula(alunoDto.getMatricula())){
            throw new RuntimeException("Matricula em uso");
        }

        Aluno aluno = new Aluno();
        aluno.setMatricula(alunoDto.getMatricula());
        aluno.setNome(alunoDto.getNome());
        aluno.setEmail(alunoDto.getEmail());
        aluno.setDataNascimento(alunoDto.getDataNascimento());
        aluno.setStatus(Status.valueOf(alunoDto.getStatus().toUpperCase()));

        return alunoRepository.save(aluno);
    }

    public List<Aluno> listaAlunos(){
        return alunoRepository.findAll();
    }

    public Aluno buscaAlunoPorId(Long id){
        return alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    public Aluno alteraAluno(AlunoDto dados, Long id){
        Aluno aluno = buscaAlunoPorId(id);
        aluno.setNome(dados.getNome());
        aluno.setEmail(dados.getEmail());
        aluno.setDataNascimento(dados.getDataNascimento());
        aluno.setStatus(Status.valueOf(dados.getStatus().toUpperCase()));
        if(!existsByMatricula(dados.getMatricula())){
            aluno.setMatricula(dados.getMatricula());
        }
        return alunoRepository.save(aluno);
    }

    @Transactional
    public void deletaAluno(Long id){
        if(buscaAlunoPorId(id) == null){
            throw new RuntimeException("Aluno não encontrado");
        }

        matriculaRepository.deleteByAluno_Id(id);
        alunoRepository.deleteById(id);
    }
}
