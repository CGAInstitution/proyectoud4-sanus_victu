package madstodolist.controller;

import madstodolist.model.Nutricionista;
import madstodolist.model.Usuario;
import madstodolist.repository.nutricionistaRepository;
import madstodolist.repository.usuarioRepository;
import madstodolist.repository.usuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@Controller

public class NutricionistaController {

    @Autowired
    private nutricionistaRepository nutricionRepository;

    @Autowired
    private usuarioRepository userRepository;

    @GetMapping("/nutricionista/{id}/usuarios")
    public String verUsuarios(@PathVariable("id") long id, Model model) {
        // Obtenemos el nutricionista por su ID
        Nutricionista nutricionista = nutricionRepository.findById(id).orElse(null);

        // Si el nutricionista existe, mostramos sus pacientes (usuarios)
        if (nutricionista != null) {
            Set<Usuario> pacientes = nutricionista.getPacientes();
            model.addAttribute("pacientes", pacientes);
        }
        return "nutricionista";
    }
}
