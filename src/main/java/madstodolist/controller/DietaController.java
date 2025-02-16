package madstodolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DietaController {

    @GetMapping("/newDieta")
    public String mostrarNuevaDieta(Model model) {
        // Aquí puedes añadir atributos al modelo si es necesario
        return "newDieta"; // nombre de la vista a la que redirigir
    }
}