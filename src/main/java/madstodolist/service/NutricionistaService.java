package madstodolist.service;

import madstodolist.model.Nutricionista;
import madstodolist.model.Usuario;
import madstodolist.repository.nutricionistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NutricionistaService {

    @Autowired
    private nutricionistaRepository nutrep;

    @Autowired
    private UsuarioService usuarioService;

    // Obtener todos los nutricionistas
    public List<Nutricionista> obtenerTodos() {
        return nutrep.findAll();
    }

    // Eliminar un nutricionista por ID
    public void eliminar(Long id) {
        nutrep.deleteById(id);
    }
}
