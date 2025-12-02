package tjw.sistema_matricula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tjw.sistema_matricula.model.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByLogin(String login);
}
