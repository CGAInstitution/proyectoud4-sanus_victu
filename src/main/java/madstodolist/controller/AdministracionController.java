package madstodolist.controller;

import madstodolist.authentication.ManagerUserSession;
import madstodolist.model.Producto;
import madstodolist.model.Supermercado;
import madstodolist.service.*;
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
    @Autowired
    private ProductoService productoService;
    @Autowired
    private SupermercadoService supermercadoService;
    @Autowired
    private InitDbService initDbService;
    @Autowired
    private ManagerUserSession managerUserSession;

    private boolean validarAcceso(Long id) {
        Long idSesion = managerUserSession.personaLogeado();
        return idSesion != null && idSesion.equals(id);
    }

    @GetMapping("/panel")
    public String administracion(@PathVariable Long id, Model model) {
        if (!validarAcceso(id)) return "redirect:/login";
        model.addAttribute("adminId", id);
        return "administracion";
    }

    @GetMapping("/listaUsuarios")
    public String listarUsuarios(@PathVariable Long id, Model model) {
        if (!validarAcceso(id)) return "redirect:/login"; // Validación de acceso
        model.addAttribute("usuarios", usuarioService.obtenerTodos()); // Asegúrate de que esto devuelva una lista válida
        model.addAttribute("nutricionistas", nutricionistaService.obtenerTodos());
        model.addAttribute("adminId", id);
        return "listUsuarios"; // Asegúrate de que esta vista exista
    }

    @PostMapping("/listaUsuarios/eliminar/{userId}")
    public String eliminarUsuario(@PathVariable Long id, @PathVariable Long userId) {
        usuarioService.eliminar(userId);
        return "redirect:/administracion/" + id + "/listaUsuarios";
    }

    @GetMapping("/listaNutricionistas")
    public String listarNutricionistas(@PathVariable Long id, Model model) {
        if (!validarAcceso(id)) return "redirect:/login";
        model.addAttribute("nutricionistas", nutricionistaService.obtenerTodos());
        model.addAttribute("adminId", id);
        return "listNutricionistas";
    }

    @PostMapping("/listaNutricionistas/eliminar/{nutriId}")
    public String eliminarNutricionista(@PathVariable Long id, @PathVariable Long nutriId) {
        usuarioService.desvincularNutricionista(nutriId);
        nutricionistaService.eliminar(nutriId);
        return "redirect:/administracion/" + id + "/listaNutricionistas";
    }

    @GetMapping("/listaProductos")
    public String listarProductos(@PathVariable Long id, Model model) {
        List<Producto> productos = productoService.obtenerTodosProductos();
        List<Supermercado> supermercados = supermercadoService.obtenerTodosSupermercados();
        model.addAttribute("supermercados", supermercados);
        model.addAttribute("productos", productos);
        model.addAttribute("adminId", id);
        return "listProductos";
    }

    @GetMapping("/registrarProducto")
    public String mostrarFormularioRegistroProducto(@PathVariable Long id, Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("adminId", id);
        return "formProducto";
    }

    @PostMapping("/registrarProducto")
    public String registrarProducto(@PathVariable Long id, @ModelAttribute Producto producto, Model model,
                                    @RequestParam String nombre, @RequestParam float valor_energetico,
                                    @RequestParam float grasas, @RequestParam float hidratos_carbono,
                                    @RequestParam float fibra_alimentaria, @RequestParam float proteinas,
                                    @RequestParam float sal ) {
        productoService.guardarProducto(producto);
        return "redirect:/administracion/" + id + "/listaProductos";
    }

    @PostMapping("/resetBD")
    public String resetBD(@PathVariable Long id, Model model) {
        initDbService.delectDatabase();
        initDbService.initDatabase();
        return "redirect:/administracion/" + id + "/panel";
    }

}
