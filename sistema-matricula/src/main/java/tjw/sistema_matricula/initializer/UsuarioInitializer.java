package tjw.sistema_matricula.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tjw.sistema_matricula.model.entity.Usuario;
import tjw.sistema_matricula.model.enums.Role;
import tjw.sistema_matricula.repository.UsuarioRepository;

@Component
public class UsuarioInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioInitializer(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.findByLogin("admin").isEmpty()) {
            String loginPadrao = "admin";
            String senhaPadrao = "123456";

            Usuario adminUser = new Usuario();
            adminUser.setLogin(loginPadrao);
            adminUser.setSenha(passwordEncoder.encode(senhaPadrao));

            adminUser.setRole(Role.ROLE_ADMIN);

            usuarioRepository.save(adminUser);

            System.out.println(">>> Usuário ADMIN criado: " + loginPadrao + " / Senha: " + senhaPadrao);
        }
    }
}