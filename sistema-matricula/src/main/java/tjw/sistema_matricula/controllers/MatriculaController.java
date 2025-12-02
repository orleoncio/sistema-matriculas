package tjw.sistema_matricula.controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tjw.sistema_matricula.model.dto.MatriculaDto;
import tjw.sistema_matricula.model.entity.Matricula;
import tjw.sistema_matricula.services.AlunoService;
import tjw.sistema_matricula.services.DisciplinaService;
import tjw.sistema_matricula.services.MatriculaService;

import java.util.List;

@Controller
@RequestMapping("/matriculas")
public class MatriculaController {

    private final MatriculaService matriculaService;
    private final AlunoService alunoService;
    private final DisciplinaService disciplinaService;

    public MatriculaController(MatriculaService matriculaService, AlunoService alunoService, DisciplinaService disciplinaService) {
        this.matriculaService = matriculaService;
        this.alunoService = alunoService;
        this.disciplinaService = disciplinaService;
    }

    @GetMapping
    public String buscaMatriculas(Model model) {
        model.addAttribute("matriculas", matriculaService.buscaMatriculas());
        return "matriculas/matriculas";
    }

    @GetMapping("/criar")
    public String criarMatricula(Model model) {
        model.addAttribute("listaAlunos", alunoService.listaAlunos());
        model.addAttribute("listaDisciplinas", disciplinaService.listaDisciplinas());
        return "matriculas/form-create";
    }

    @PostMapping("/criar")
    public String criarMatricula(@ModelAttribute MatriculaDto matriculaDto) {
        matriculaService.criaMatricula(matriculaDto);

        return "redirect:/matriculas";
    }

    @GetMapping("/atualizar/{id}")
    public String atualizarMatricula(@PathVariable Long id, Model model) {
        model.addAttribute("matriculaDto", matriculaService.buscaMatriculaPorId(id));
        return "matriculas/form-update";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarMatricula(@ModelAttribute MatriculaDto matriculaDto, @PathVariable Long id) {
        matriculaService.alteraMatricula(matriculaDto, id);

        return "redirect:/matriculas";
    }

    @PostMapping("/excluir/{id}")
    public String deletarMatricula(@PathVariable Long id, RedirectAttributes attributes) {
        matriculaService.deletaMatricula(id);
        attributes.addFlashAttribute("mensagemSucesso", "Matricula excluída com sucesso!");

        return "redirect:/matriculas";
    }
}
