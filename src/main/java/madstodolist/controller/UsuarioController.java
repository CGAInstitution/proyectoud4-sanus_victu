package madstodolist.controller;

import madstodolist.dto.LoginData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UsuarioController {


    @GetMapping("/usuarios/{id}/inicio")
    public String loginForm(@PathVariable Long id, Model model) {
        model.addAttribute("idUsuario", id); // Puedes usarlo en la vista si lo necesitas
        return "formUsuario";
    }

}
