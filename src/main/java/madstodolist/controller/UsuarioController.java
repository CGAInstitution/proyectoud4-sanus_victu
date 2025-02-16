package madstodolist.controller;

import madstodolist.dto.LoginData;
import madstodolist.model.Usuario;
import madstodolist.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios/{id}")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/inicio")
    public String loginForm(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.buscarPorId(id).get();
        Long idNutricionista = usuario.getNutricionista().getId();
        model.addAttribute("idUsuario", id);
        model.addAttribute("idNutricionista", idNutricionista);
        return "formUsuario";
    }

    @GetMapping("/newDieta")
    public String mostrarNuevaDieta(@PathVariable Long id, Model model) {
        model.addAttribute("idUsuario", id);
        return "newDieta";
    }
}