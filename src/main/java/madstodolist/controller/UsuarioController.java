package madstodolist.controller;

import madstodolist.authentication.ManagerUserSession;
import madstodolist.model.Producto;
import madstodolist.model.Supermercado;
import madstodolist.model.Usuario;
import madstodolist.service.ProductoService;
import madstodolist.service.SupermercadoService;
import madstodolist.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/usuarios/{id}")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ManagerUserSession managerUserSession;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private SupermercadoService supermercadoService;


    @GetMapping("/inicio")
    public String loginForm(@PathVariable Long id, Model model) {
        Long idSesion = managerUserSession.personaLogeado();

        if (idSesion == null || !idSesion.equals(id)) {
            return "redirect:/login"; // Redirigir si el usuario no está autenticado o intenta acceder a otro perfil
        }

        Usuario usuario = usuarioService.buscarPorId(id).orElse(null);
        if (usuario == null) {
            return "redirect:/login"; // Si el usuario no existe, redirigir al login
        }

        Long idNutricionista = usuario.getNutricionista().getId();
        model.addAttribute("idUsuario", id);
        model.addAttribute("idNutricionista", idNutricionista);

        return "formUsuario";
    }

    @GetMapping("/newDieta")
    public String mostrarNuevaDieta(@PathVariable Long id, Model model) {
        List<Supermercado> supermercados = supermercadoService.obtenerTodosSupermercados();
        List<Producto> productos = productoService.obtenerTodosProductos(); // Obtener todos los productos

        model.addAttribute("supermercados", supermercados);
        model.addAttribute("productos", productos); // Agregar la lista de productos al modelo

        Long idSesion = managerUserSession.personaLogeado();

        if (idSesion == null || !idSesion.equals(id)) {
            return "redirect:/login"; // Bloquear acceso si el usuario no está autenticado o intenta cambiar el ID
        }

        model.addAttribute("idUsuario", id);
        return "newDieta";
    }
}
