package madstodolist.service;

import madstodolist.model.Mensaje;
import madstodolist.repository.mensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MensajeService {

    @Autowired
    private mensajeRepository mensajeRepo;
    @Autowired
    private PersonaService personaService;

    // Método para obtener mensajes entre dos personas
    @Transactional
    public Optional<List<Mensaje>> obtenerMensajes(Long id1, Long id2) {
        // Busca todos los mensajes y filtra por remitente y destinatario
        List<Mensaje> mensajes = mensajeRepo.findAll(); // Obtener todos los mensajes

        // Filtrar mensajes según remitente y destinatario
        List<Mensaje> mensajesFiltrados = mensajes.stream()
                .filter(m -> (m.getRemitente().getId() == id1 && m.getDestinatario().getId() == id2) ||
                        (m.getRemitente().getId() == id2 && m.getDestinatario().getId() == id1))
                .toList();

        return Optional.ofNullable(mensajesFiltrados.isEmpty() ? null : mensajesFiltrados);
    }

    // Método para enviar un nuevo mensaje
    @Transactional
    public void enviarMensaje(String texto, Long idRemitente, Long idDestinatario) {
        Mensaje nuevoMensaje = new Mensaje();
        nuevoMensaje.setTexto(texto);
        nuevoMensaje.setRemitente(personaService.findById(idRemitente)); // Asignar solo el ID
        nuevoMensaje.setDestinatario(personaService.findById(idDestinatario)); // Asignar solo el ID
        nuevoMensaje.setHora(new Date()); // Establecer la hora actual

        mensajeRepo.save(nuevoMensaje); // Guardar el nuevo mensaje en la base de datos
    }
}