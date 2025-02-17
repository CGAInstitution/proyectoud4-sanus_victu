package madstodolist.controller;

import madstodolist.model.Nutricionista;
import madstodolist.model.Producto;
import madstodolist.model.Supermercado;
import madstodolist.model.Usuario;
import madstodolist.service.NutricionistaService;
import madstodolist.service.Producto_SupermercadoService;
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
    @Autowired
    private Producto_SupermercadoService producto_supermercadoService;

    @GetMapping("/panel")
    public String administracion(@PathVariable Long id, Model model) {
        model.addAttribute("adminId", id);
        return "administracion";
    }

    @GetMapping("/listaUsuarios")
    public String listarUsuarios(@PathVariable Long id, Model model) {
        List<Usuario> usuarios = usuarioService.obtenerTodos();
        List<Nutricionista> nutricionistas = nutricionistaService.obtenerTodos();
        model.addAttribute("nutricionistas", nutricionistas);
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

    @GetMapping("/listaProductos")
    public String listarProductos(@PathVariable Long id, Model model) {
        List<Producto> productos = producto_supermercadoService.obtenerTodosProductos();
        List<Supermercado> supermercados = producto_supermercadoService.obtenerTodosSupermercados();
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
        producto_supermercadoService.guardarProducto(producto);
        return "redirect:/administracion/" + id + "/listaProductos";
    }

}
