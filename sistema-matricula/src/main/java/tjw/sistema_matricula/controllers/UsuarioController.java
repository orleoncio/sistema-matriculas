package tjw.sistema_matricula.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tjw.sistema_matricula.model.dto.UsuarioDto;
import tjw.sistema_matricula.model.entity.Usuario;
import tjw.sistema_matricula.model.enums.Role;
import tjw.sistema_matricula.services.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String buscar(Model model) {
        model.addAttribute("usuarios", usuarioService.listarUsuarios());

        return "usuarios/usuarios";
    }

    @GetMapping("/register")
    public String register() {
        return "usuarios/register";
    }

    @PostMapping("/register")
    public String criar(@ModelAttribute UsuarioDto usuarioDto) {
        usuarioService.criarUsuario(usuarioDto);

        return "redirect:/login";
    }

    @GetMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Long id, Model model) {
        model.addAttribute("usuarioDto", usuarioService.buscarPorId(id));

        return "usuarios/update";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Long id, @ModelAttribute UsuarioDto usuarioDto) {
        usuarioService.atualizar(usuarioDto, id);

        return "redirect:/usuarios";
    }
}
