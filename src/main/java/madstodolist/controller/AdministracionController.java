package madstodolist.controller;

import madstodolist.model.Nutricionista;
import madstodolist.model.Usuario;
import madstodolist.service.NutricionistaService;
import madstodolist.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/administracion/{id}")
public class AdministracionController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private NutricionistaService nutricionistaService;

    @GetMapping("/panel")
    public String administracion(@PathVariable Long id, Model model) {
        model.addAttribute("adminId", id);
        return "administracion";
    }

    @GetMapping("/listaUsuarios")
    public String listarUsuarios(@PathVariable Long id, Model model) {
        List<Usuario> usuarios = usuarioService.obtenerTodos();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("adminId", id);
        return "listUsuarios";
    }

    @PostMapping("/listaUsuarios/eliminar/{userId}")
    public String eliminarUsuario(@PathVariable Long id, @PathVariable Long userId) {
        usuarioService.eliminar(userId);
        return "redirect:/administracion/" + id + "/listaUsuarios";
    }

    @GetMapping("/listaNutricionistas")
    public String listarNutricionistas(@PathVariable Long id, Model model) {
        List<Nutricionista> nutricionistas = nutricionistaService.obtenerTodos();
        model.addAttribute("nutricionistas", nutricionistas);
        model.addAttribute("adminId", id);
        return "listNutricionistas";
    }

    @PostMapping("/listaNutricionistas/eliminar/{nutriId}")
    public String eliminarNutricionista(@PathVariable Long id, @PathVariable Long nutriId) {
        usuarioService.desvincularNutricionista(nutriId);
        nutricionistaService.eliminar(nutriId);
        return "redirect:/administracion/" + id + "/listaNutricionistas";
    }
}
