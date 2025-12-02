package tjw.sistema_matricula.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tjw.sistema_matricula.model.dto.DisciplinaDto;
import tjw.sistema_matricula.model.entity.Disciplina;
import tjw.sistema_matricula.repository.DisciplinaRepository;
import tjw.sistema_matricula.repository.MatriculaRepository;

import java.util.List;

@Service
public class DisciplinaService {

    private DisciplinaRepository disciplinaRepository;
    private MatriculaRepository matriculaRepository;

    public DisciplinaService(DisciplinaRepository disciplinaRepository, MatriculaRepository matriculaRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.matriculaRepository = matriculaRepository;
    }

    public boolean existsByCodigo(String codigo){
        return disciplinaRepository.existsByCodigo(codigo);
    }

    public Disciplina criaDisciplina(DisciplinaDto disciplinaDto){
        if(existsByCodigo(disciplinaDto.getCodigo())){
            throw new RuntimeException("Codigo em uso por outra Disciplina");
        }

        Disciplina disciplina = new Disciplina();
        disciplina.setNome(disciplinaDto.getNome());
        disciplina.setCodigo(disciplinaDto.getCodigo());
        disciplina.setCargaHoraria(disciplinaDto.getCargaHoraria());
        disciplina.setSemestre(disciplinaDto.getSemestre());

        return disciplinaRepository.save(disciplina);
    }

    public List<Disciplina> listaDisciplinas(){
        return disciplinaRepository.findAll();
    }

    public Disciplina buscaDisciplinaPorId(Long id){
        return disciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina não existe"));
    }

    public Disciplina alteraDisciplina(DisciplinaDto dados, Long id){
        Disciplina disciplina = buscaDisciplinaPorId(id);
        disciplina.setNome(dados.getNome());
        disciplina.setCargaHoraria(dados.getCargaHoraria());
        disciplina.setSemestre(dados.getSemestre());
        if(!existsByCodigo(dados.getCodigo())){
            disciplina.setCodigo(dados.getCodigo());
        }
        return disciplinaRepository.save(disciplina);
    }

    @Transactional
    public void deletaDisciplina(Long id){
        if(buscaDisciplinaPorId(id) == null){
            throw new RuntimeException("Disciplina não encontrada");
        }

        matriculaRepository.deleteByDisciplina_Id(id);
        disciplinaRepository.deleteById(id);
    }
}
