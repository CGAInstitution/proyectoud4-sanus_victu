package madstodolist.controller;

import madstodolist.authentication.ManagerUserSession;
import madstodolist.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/nutricionista")
public class NutricionistaController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ManagerUserSession managerUserSession;

    @GetMapping("/{idNutricionista}/panel")
    public String mostrarPanel(@PathVariable Long idNutricionista, Model model) {
        Long idSesion = managerUserSession.personaLogeado();
        if (idSesion == null || !idSesion.equals(idNutricionista)) {
            return "redirect:/login";
        }
        model.addAttribute("nutricionistaId", idNutricionista);
        model.addAttribute("pacientes", usuarioService.obtenerUsuariosPorNutricionista(idNutricionista));
        return "nutricionista";
    }
}
