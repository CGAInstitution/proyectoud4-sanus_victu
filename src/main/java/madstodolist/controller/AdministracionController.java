package madstodolist.controller;

import madstodolist.authentication.ManagerUserSession;
import madstodolist.dto.ProductoData;
import madstodolist.model.Nutricionista;
import madstodolist.model.Producto;
import madstodolist.model.Supermercado;
import madstodolist.model.Usuario;
import madstodolist.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

    @PostMapping("/cambiarNutricionista")
    public String cambiarNutricionista(@PathVariable Long id,
                                       @RequestParam Long usuarioId,
                                       @RequestParam Long nutricionistaId) {
        if (!validarAcceso(id)) return "redirect:/login";

        Usuario usuario = usuarioService.buscarPorId(usuarioId).orElse(null);
        if (usuario != null) {
            Nutricionista nutricionista = nutricionistaService.buscarPorId(nutricionistaId).orElse(null);
            if (nutricionista != null) {
                usuario.setNutricionista(nutricionista);
                usuarioService.guardarUsuario(usuario);
            }
        }

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
        if (!validarAcceso(id)) return "redirect:/login";
        List<Producto> productos = productoService.obtenerTodosProductos();
        List<Supermercado> supermercados = supermercadoService.obtenerTodosSupermercados();
        model.addAttribute("supermercados", supermercados);
        model.addAttribute("productos", productos);
        model.addAttribute("adminId", id);
        return "listProductos";
    }

    @GetMapping("/registrarProducto")
    public String mostrarFormularioRegistroProducto(@PathVariable Long id, Model model) {
        if (!validarAcceso(id)) return "redirect:/login";

        model.addAttribute("producto", new Producto()); // Asegura que el producto esté vacío
        model.addAttribute("adminId", id);
        return "formProducto";
    }


    @PostMapping("/listaProductos/eliminar/{productoId}")
    public String eliminarProducto(@PathVariable Long id, @PathVariable Long productoId) {
        productoService.borrarProducto(productoId);
        return "redirect:/administracion/" + id + "/listaProductos";
    }

    @GetMapping("/registrarProducto/modificar/{productoId}")
    public String irModificarProducto(@PathVariable Long id, @PathVariable Long productoId, Model model) {
        if (!validarAcceso(id)) return "redirect:/login";

        Optional<Producto> productoOpt = productoService.obtenerProductoById(productoId);
        if (!productoOpt.isPresent()) {
            return "redirect:/administracion/" + id + "/listaProductos"; // Redirige si el producto no existe
        }

        model.addAttribute("producto", productoOpt.get());
        model.addAttribute("adminId", id);
        return "formProducto";
    }




    @PostMapping("/registrarProducto/modificar/{productoId}")
    public String modificarProducto(@PathVariable Long id, @PathVariable Long productoId, @Valid ProductoData productoData, BindingResult result, Model model) {
        if (!validarAcceso(id)) return "redirect:/login";

        if (result.hasErrors()) {
            model.addAttribute("producto", productoData);
            return "formProducto";
        }

        productoService.actualizarProducto(productoId, productoData);
        return "redirect:/administracion/" + id + "/listaProductos";
    }


    @PostMapping("/registrarProducto/nuevoProducto")
    public String registrarProducto(@Valid ProductoData productoData, @PathVariable Long id, BindingResult result, Model model ) {
        if (result.hasErrors()) {
            return "formProducto";
        }

        if (productoService.obtenerProductoPorNombre(productoData.getNombre()).isPresent()) {
            return "formProducto"; // Si el producto ya existe, no lo guardamos
        }

        productoService.guardarProducto(productoService.mapearProducto(productoData));
        return "redirect:/administracion/" + id + "/listaProductos";
    }

    @PostMapping("/resetBD")
    public String resetBD(@PathVariable Long id, Model model) {
        initDbService.delectDatabase();
        initDbService.initDatabase();
        return "redirect:/administracion/" + id + "/panel";
    }


}
