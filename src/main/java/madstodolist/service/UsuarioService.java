package madstodolist.service;

import madstodolist.dto.PersonaData;
import madstodolist.dto.UsuarioData;
import madstodolist.model.Persona;
import madstodolist.model.Usuario;
import madstodolist.repository.personaRepository;
import madstodolist.repository.usuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private usuarioRepository usre;
    @Autowired
    private personaRepository personaRepository;
    @Autowired
    private ModelMapper modelMapper;


    // Obtener todos los usuarios
    @Transactional
    public List<Usuario> obtenerTodos() {
        return usre.findAll();
    }

    @Transactional
    public Optional<Usuario> buscarPorId(Long id) {
        return usre.findById(id);
    }

    // Eliminar un usuario por ID
    @Transactional
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

    @Transactional
    public PersonaData registrar(UsuarioData usuarioData) {
        Optional<Persona> persona = personaRepository.findByCorreo(usuarioData.getCorreo());
        if (persona.isPresent()) {
            throw new PersonaServiceException("El usuario " + usuarioData.getCorreo() + " ya está registrado");
        } else if (usuarioData.getCorreo() == null) {
            throw new PersonaServiceException("El usuario no tiene email");
        } else if (usuarioData.getContraseña() == null) {
            throw new PersonaServiceException("El usuario no tiene password");
        } else {
            Usuario usuarioNuevo = modelMapper.map(usuarioData, Usuario.class);
            usuarioNuevo = personaRepository.save(usuarioNuevo);
            return modelMapper.map(usuarioNuevo, PersonaData.class);
        }
    }


    @Transactional
    public List<Usuario> obtenerUsuariosPorNutricionista(Long id_nutricionista) {
        // Buscar todos los usuarios asociados al nutricionista
        List<Usuario> usuarios = usre.findByNutricionistaId(id_nutricionista);
        return usuarios;
    }

    @Transactional
    public Usuario guardarUsuario(Usuario usuario) {
        return usre.save(usuario);
    }


}

