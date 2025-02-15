package madstodolist.service;

import madstodolist.model.Usuario;
import madstodolist.repository.usuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private usuarioRepository usre;


    // Obtener todos los usuarios
    public List<Usuario> obtenerTodos() {
        return usre.findAll();
    }

    // Eliminar un usuario por ID
    public void eliminar(Long id) {
        usre.deleteById(id);
    }

    @Transactional
    public void desvincularNutricionista(Long id_nutricionista) {
        // Buscar todos los usuarios asociados al nutricionista
        List<Usuario> usuarios = usre.findByNutricionistaId(id_nutricionista);

        // Modificar su campo nutricionista a null
        for (Usuario usuario : usuarios) {
            usuario.setNutricionista(null);
        }
        // Guardar los cambios en la base de datos
        usre.saveAll(usuarios);

    }
}

