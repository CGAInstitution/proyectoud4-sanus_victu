package madstodolist.controller;

import madstodolist.model.Usuario;
import madstodolist.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/nutricionista")
public class NutricionistaController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{idNutricionista}/panel")
    public String mostrarPanel(@PathVariable Long idNutricionista, Model model) {
        model.addAttribute("nutricionistaId", idNutricionista );
        List<Usuario> pacientes = usuarioService.obtenerUsuariosPorNutricionista(idNutricionista);
        model.addAttribute("pacientes", pacientes);

        return "nutricionista"; // Nombre de la vista Thymeleaf (HTML)
    }
}
