package madstodolist.controller;

import madstodolist.dto.LoginData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/administracion")
public class AdministracionController {
    @GetMapping("/panel")
    public String administracion() {
        return "administracion";
    }
    @GetMapping("/usuarios")
    public String usuarios() {
        return "listUsuarios";
    }

}
