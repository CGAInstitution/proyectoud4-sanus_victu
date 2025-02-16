package madstodolist.controller;

import madstodolist.model.Mensaje;
import madstodolist.service.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/{persona1}/{id1}/mensaje")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    @GetMapping("/{persona2}/{id2}")
    public String mensaje(@PathVariable String persona1, @PathVariable Long id1,
                          @PathVariable String persona2, @PathVariable Long id2,
                          Model model) {
        // Obtener los mensajes entre las dos personas
        Optional<List<Mensaje>> mensajesOpt = mensajeService.obtenerMensajes(id1, id2);
        mensajesOpt.ifPresent(mensajes -> model.addAttribute("mensajes", mensajes));
        model.addAttribute("destinatarioId", id2);

        return "mensajes"; // Nombre de la vista
    }

    @PostMapping("/{persona2}/{id2}/enviarMensaje")
    public String enviarMensaje(@RequestParam String texto,
                                @PathVariable Long id1,
                                @PathVariable String persona2,
                                @PathVariable Long id2) {
        // Crear un nuevo mensaje y guardarlo en la base de datos
        mensajeService.enviarMensaje(texto, id1, id2);

        // Redirigir a la conversación
        return "redirect:/usuarios/" + id1 + "/mensaje/" + persona2 + "/" + id2; // Redirección explícita
    }
}