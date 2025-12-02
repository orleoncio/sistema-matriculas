package tjw.sistema_matricula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tjw.sistema_matricula.model.entity.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    boolean existsByCodigo(String codigo);

}
