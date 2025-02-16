package madstodolist.controller;

import madstodolist.model.Producto;
import madstodolist.repository.productoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductoController {

    @Autowired
    private productoRepository producRepository;

    @GetMapping("/nuevadieta")
    public String mostrarProductos(Model model) {
        List<Producto> productos = producRepository.findAll(); // Obtener todos los productos
        model.addAttribute("productos", productos); // Pasar la lista de productos a la vista
        return "newDieta"; // Nombre de la plantilla Thymeleaf
    }
}
