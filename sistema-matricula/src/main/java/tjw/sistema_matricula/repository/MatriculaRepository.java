package tjw.sistema_matricula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tjw.sistema_matricula.model.entity.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    boolean existsByAluno_IdAndDisciplina_Id(Long alunoId, Long disciplinaId);

    boolean existsByAluno_Id(Long alunoId);

    boolean existsByDisciplina_Id(Long disciplinaId);

    void deleteByAluno_Id(Long alunoId);

    void deleteByDisciplina_Id(Long disciplinaId);
}
