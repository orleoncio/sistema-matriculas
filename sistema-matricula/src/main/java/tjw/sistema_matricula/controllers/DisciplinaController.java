package tjw.sistema_matricula.controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tjw.sistema_matricula.model.dto.DisciplinaDto;
import tjw.sistema_matricula.model.entity.Disciplina;
import tjw.sistema_matricula.services.DisciplinaService;

import java.util.List;

@Controller
@RequestMapping("/disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @GetMapping
    public String buscarDisciplinas(Model model) {
        model.addAttribute("disciplinas", disciplinaService.listaDisciplinas());

        return "disciplinas/disciplinas";
    }

    @GetMapping("/criar")
    public String criarDisciplina() {
        return "disciplinas/form-create";
    }

    @PostMapping("/criar")
    public String criarDisciplina(@ModelAttribute DisciplinaDto disciplinaDto) {
        disciplinaService.criaDisciplina(disciplinaDto);

        return "redirect:/disciplinas";
    }

    @GetMapping("/atualizar/{id}")
    public String atualizarDisciplina(@PathVariable Long id, Model model) {
        model.addAttribute("disciplinaDto", disciplinaService.buscaDisciplinaPorId(id));

        return "disciplinas/form-update";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarDisciplina(@ModelAttribute DisciplinaDto disciplinaDto, @PathVariable Long id) {
        disciplinaService.alteraDisciplina(disciplinaDto, id);

        return "redirect:/disciplinas";
    }

    @PostMapping("/excluir/{id}")
    public String deletarDisciplina(@PathVariable Long id, RedirectAttributes attributes) {
        disciplinaService.deletaDisciplina(id);
        attributes.addFlashAttribute("mensagemSucesso", "Disciplina excluída com sucesso!");

        return "redirect:/disciplinas";
    }
}
