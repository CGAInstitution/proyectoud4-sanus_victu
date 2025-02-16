package madstodolist.controller;

import madstodolist.dto.LoginData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios/{id}")
public class UsuarioController {


    @GetMapping("/inicio")
    public String loginForm(@PathVariable Long id, Model model) {
        model.addAttribute("idUsuario", id);
        return "formUsuario";
    }

}
