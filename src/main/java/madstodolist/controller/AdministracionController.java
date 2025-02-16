package madstodolist.controller;

import madstodolist.dto.LoginData;
import madstodolist.model.Nutricionista;
import madstodolist.model.Usuario;
import madstodolist.service.NutricionistaService;
import madstodolist.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping ("/administracion")
public class AdministracionController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private NutricionistaService nutricionistaService;



    @GetMapping("/panel")
    public String administracion() {
        return "administracion";
    }
    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.obtenerTodos();
        model.addAttribute("usuarios", usuarios);
        return "listUsuarios";
    }
    // Ruta para eliminar un usuario
    @PostMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return "redirect:/administracion/usuarios";
    }

    @GetMapping("/nutricionistas")
    public String listarNutricionistas(Model model) {
        List<Nutricionista> nutricionistas = nutricionistaService.obtenerTodos();
        model.addAttribute("nutricionistas", nutricionistas);
        return "listNutricionistas";
    }
    @PostMapping("/nutricionistas/eliminar/{id}")
    public String eliminarNuticionista(@PathVariable Long id) {
        nutricionistaService.eliminar(id);
        return "redirect:/administracion/nutricionistas";
    }
    @GetMapping("/mensajes")
    public String mensajes(Model model) {
        return "mensajes";
    }

}
