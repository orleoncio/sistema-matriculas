package tjw.sistema_matricula.controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tjw.sistema_matricula.model.dto.AlunoDto;
import tjw.sistema_matricula.model.entity.Aluno;
import tjw.sistema_matricula.repository.AlunoRepository;
import tjw.sistema_matricula.services.AlunoService;
import tjw.sistema_matricula.services.UsuarioService;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    private final UsuarioService usuarioService;
    private final AlunoRepository alunoRepository;
    private AlunoService alunoService;

    public AlunoController(AlunoService alunoService, UsuarioService usuarioService, AlunoRepository alunoRepository) {
        this.alunoService = alunoService;
        this.usuarioService = usuarioService;
        this.alunoRepository = alunoRepository;
    }

    @GetMapping
    public String buscarAlunos(Model model) {
        model.addAttribute("alunos", alunoService.listaAlunos());
        return "alunos/alunos";
    }

    @GetMapping("/criar")
    public String criarAluno() {
        return "alunos/form-create";
    }

    @PostMapping("/criar")
    public String criarAluno(@ModelAttribute AlunoDto alunoDto) {
        alunoService.criaAluno(alunoDto);

        return "redirect:/alunos";
    }

    @GetMapping("/atualizar/{id}")
    public String atualizarAluno(@PathVariable Long id, Model model) {
        model.addAttribute("alunoDto", alunoService.buscaAlunoPorId(id));
        return "alunos/form-update";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarAluno(@ModelAttribute AlunoDto alunoDto, @PathVariable Long id) {
        alunoService.alteraAluno(alunoDto, id);

        return "redirect:/alunos";
    }

    @PostMapping("/excluir/{id}")
    public String deletarAluno(@PathVariable Long id, RedirectAttributes attributes) {
        alunoService.deletaAluno(id);
        attributes.addFlashAttribute("mensagemSucesso", "Aluno excluído com sucesso!");

        return "redirect:/alunos";
    }
}
