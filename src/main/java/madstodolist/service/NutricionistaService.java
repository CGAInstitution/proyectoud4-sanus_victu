package madstodolist.service;

import madstodolist.model.Nutricionista;
import madstodolist.model.Usuario;
import madstodolist.repository.nutricionistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class NutricionistaService {

    @Autowired
    private nutricionistaRepository nutrep;

    @Autowired
    private UsuarioService usuarioService;

    // Obtener todos los nutricionistas
    @Transactional
    public List<Nutricionista> obtenerTodos() {
        return nutrep.findAll();
    }

    // Buscar un nutricionista por ID
    @Transactional
    public Optional<Nutricionista> buscarPorId(Long id) {
        return nutrep.findById(id);
    }

    // Eliminar un nutricionista por ID
    @Transactional
    public void eliminar(Long id) {
        nutrep.deleteById(id);
    }

    // Asignar un nutricionista a un usuario
    @Transactional
    public void asignarNutricionistaAUsuario(Long usuarioId, Long nutricionistaId) {
        Optional<Usuario> usuarioOpt = usuarioService.buscarPorId(usuarioId);
        Optional<Nutricionista> nutricionistaOpt = buscarPorId(nutricionistaId);

        if (usuarioOpt.isPresent() && nutricionistaOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            Nutricionista nutricionista = nutricionistaOpt.get();
            usuario.setNutricionista(nutricionista);
            usuarioService.guardarUsuario(usuario);
        }
    }
}
