package tjw.sistema_matricula.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicController {

    @GetMapping("/login")
    public String login() {
        return "login"; // thymeleaf: src/main/resources/templates/login.html
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
