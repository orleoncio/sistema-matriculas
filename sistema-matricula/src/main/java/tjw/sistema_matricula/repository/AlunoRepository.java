package tjw.sistema_matricula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tjw.sistema_matricula.model.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    boolean existsByMatricula(String matricula);
}
