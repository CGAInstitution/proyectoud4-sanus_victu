package madstodolist.controller;

import madstodolist.authentication.ManagerUserSession;
import madstodolist.model.*;
import madstodolist.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private NutricionistaService nutricionistaService;
    @Autowired
    private SupermercadoService supermercadoService;
    @Autowired
    private DietaService dietaService;

    @GetMapping("/inicio")
    public String loginForm(@PathVariable Long id, Model model) {
        Long idSesion = managerUserSession.personaLogeado();

        if (idSesion == null || !idSesion.equals(id)) {
            return "redirect:/login";
        }

        Usuario usuario = usuarioService.buscarPorId(id).orElse(null);
        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("idUsuario", id);

        if (usuario.getNutricionista() == null) {
            model.addAttribute("hasNutricionista", false);
        } else {
            model.addAttribute("hasNutricionista", true);
            model.addAttribute("idNutricionista", usuario.getNutricionista().getId());
        }

        model.addAttribute("nutricionistas", nutricionistaService.obtenerTodos());

        return "formUsuario";
    }

    @GetMapping("/newDieta")
    public String mostrarNuevaDieta(@PathVariable Long id, Model model) {
        Long idSesion = managerUserSession.personaLogeado();
        if (idSesion == null || !idSesion.equals(id)) {
            return "redirect:/login";
        }
        model.addAttribute("supermercados", supermercadoService.obtenerTodosSupermercados());
        model.addAttribute("productos", productoService.obtenerTodosProductos());
        model.addAttribute("idUsuario", id);

        return "newDieta";
    }

    @PostMapping("/cambiarNutricionista")
    public String cambiarNutricionista(@PathVariable Long id,
                                       @RequestParam Long nutricionistaId) {
        Long idSesion = managerUserSession.personaLogeado();

        if (idSesion == null || !idSesion.equals(id)) {
            return "redirect:/login";
        }

        Usuario usuario = usuarioService.buscarPorId(id).orElse(null);
        if (usuario == null) {
            return "redirect:/login";
        }

        Nutricionista nutricionista = nutricionistaService.buscarPorId(nutricionistaId).orElse(null);
        if (nutricionista != null) {
            usuario.setNutricionista(nutricionista);
            usuarioService.guardarUsuario(usuario);
        }

        return "redirect:/usuarios/" + id + "/inicio";
    }

    @PostMapping("/guardar-producto")
    public String guardarDieta(@PathVariable Long id,
                               @RequestParam(required = false) String nombreDieta,
                               @RequestParam(required = false) List<String> diasSeleccionados,
                               @RequestParam(required = false) List<Long> productosSeleccionados,
                               Model model) {
        Long idSesion = managerUserSession.personaLogeado();

        if (idSesion == null || !idSesion.equals(id)) {
            return "redirect:/login";
        }

        // Comprobación de selección de días y productos
        if (diasSeleccionados == null || diasSeleccionados.isEmpty()) {
            model.addAttribute("error", "Debe seleccionar al menos un día de la semana.");
            return "newDieta"; // Redirigir de vuelta a la vista newDieta
        }

        if (productosSeleccionados == null || productosSeleccionados.isEmpty()) {
            model.addAttribute("error", "Debe seleccionar al menos un producto.");
            return "newDieta"; // Redirigir de vuelta a la vista newDieta
        }

        // Lógica para guardar la dieta...

        return "redirect:/usuarios/" + id + "/inicio"; // Redirigir a la página de inicio del usuario
    }

    @PostMapping("/guardarDieta")
    public String guardarDieta(@PathVariable Long id,
                               @RequestParam String nombreDieta, Model model) {
        Long idSesion = managerUserSession.personaLogeado();
        System.out.println("ID de sesión: " + idSesion);

        if (idSesion == null || !idSesion.equals(id)) {
            System.out.println("Sesión no válida o no coincide.");
            return "redirect:/login";
        }

        Usuario usuario = usuarioService.buscarPorId(idSesion).orElse(null);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return "redirect:/login";
        }

        Dieta nuevaDieta = new Dieta();
        nuevaDieta.setNombre(nombreDieta);
        nuevaDieta.setUsuario(usuario);

        dietaService.guardarDieta(nuevaDieta);
        System.out.println("Dieta guardada: " + nuevaDieta.getNombre());

        model.addAttribute("dietas", dietaService.obtenerDietasPorUsuario(idSesion));
        return "redirect:/usuarios/" + id + "/inicio";
    }



}
