package tjw.sistema_matricula.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tjw.sistema_matricula.model.dto.UsuarioDto;
import tjw.sistema_matricula.model.entity.Usuario;
import tjw.sistema_matricula.model.enums.Role;
import tjw.sistema_matricula.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario criarUsuario(UsuarioDto usuarioDto) {
        System.out.println(usuarioDto.getSenha());
        System.out.println(usuarioDto.getConfirmSenha());
        if(!usuarioDto.getSenha().equals(usuarioDto.getConfirmSenha())){
            throw new RuntimeException("Senhas não estão iguais");
        }

        Usuario usuario = new Usuario();
        usuario.setLogin(usuarioDto.getLogin());
        usuario.setSenha(usuarioDto.getSenha());
        usuario.setRole(Role.valueOf("ROLE_" + usuarioDto.getRole().toUpperCase()));

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Usuario atualizar(UsuarioDto usuarioDto, Long id) {
        Usuario usuario = buscarPorId(id);
        usuario.setLogin(usuarioDto.getLogin());
        usuario.setSenha(usuarioDto.getSenha());
        usuario.setRole(Role.valueOf(usuarioDto.getRole().toUpperCase()));

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não existe");
        }
        usuarioRepository.deleteById(id);
    }
}
