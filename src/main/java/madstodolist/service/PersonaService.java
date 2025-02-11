package madstodolist.service;

import madstodolist.dto.PersonaData;
import madstodolist.model.Persona;
import madstodolist.model.Usuario;
import madstodolist.repository.personaRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PersonaService {

    Logger logger = LoggerFactory.getLogger(PersonaService.class);

    public enum LoginStatus {LOGIN_OK, USER_NOT_FOUND, ERROR_PASSWORD}

    @Autowired
    private personaRepository personaRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public LoginStatus login(String eMail, String password) {
        Optional<Persona> persona = personaRepository.findByCorreo(eMail);
        if (!persona.isPresent()) {
            return LoginStatus.USER_NOT_FOUND;
        } else if (!persona.get().getContraseña().equals(password)) {
            return LoginStatus.ERROR_PASSWORD;
        } else {
            return LoginStatus.LOGIN_OK;
        }
    }

    // Se añade un persona1 en la aplicación.
    // El email y password del persona1 deben ser distinto de null
    // El email no debe estar registrado en la base de datos
    @Transactional
    public PersonaData registrar(PersonaData persona1) {
        Optional<Persona> personaDB = personaRepository.findByCorreo(persona1.getCorreo());
        if (personaDB.isPresent())
            throw new UsuarioServiceException("El usuario " + persona1.getCorreo() + " ya está registrado");
        else if (persona1.getCorreo() == null)
            throw new UsuarioServiceException("El usuario no tiene email");
        else if (persona1.getContraseña() == null)
            throw new UsuarioServiceException("El usuario no tiene password");
        else {
            Usuario usuarioNuevo = modelMapper.map(persona1, Usuario.class);
            usuarioNuevo = personaRepository.save(usuarioNuevo);
            return modelMapper.map(usuarioNuevo, PersonaData.class);
        }
    }

    @Transactional(readOnly = true)
    public PersonaData findByEmail(String email) {
        Persona persona = personaRepository.findByCorreo(email).orElse(null);
        if (persona == null) return null;
        else {
            return modelMapper.map(persona, PersonaData.class);
        }
    }

    @Transactional(readOnly = true)
    public PersonaData findById(int personaId) {
        Persona persona = personaRepository.findById(personaId).orElse(null);
        if (persona == null) return null;
        else {
            return modelMapper.map(persona, PersonaData.class);
        }
    }
}
