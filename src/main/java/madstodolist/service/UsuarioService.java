package madstodolist.service;

import madstodolist.model.Usuario;
import madstodolist.repository.usuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}

