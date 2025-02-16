package madstodolist.service;

import madstodolist.authentication.LoginResponse;
import madstodolist.dto.PersonaData;
import madstodolist.dto.UsuarioData;
import madstodolist.model.Persona;
import madstodolist.model.Usuario;
import madstodolist.repository.personaRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    Logger logger = LoggerFactory.getLogger(PersonaService.class);

    public enum LoginStatus {LOGIN_OK, USER_NOT_FOUND, ERROR_PASSWORD}

    public enum TipoPersonaStatus {USUARIO, NUTRICIONISTA, ADMINISTRADOR}

    @Autowired
    private personaRepository personaRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public LoginResponse login(String correo, String contraseña) {
        Optional<Persona> persona = personaRepository.findByCorreo(correo);
        if (!persona.isPresent()) {
            return new LoginResponse(LoginStatus.USER_NOT_FOUND, null);
        } else if (!persona.get().getContraseña().equals(contraseña)) {
            return new LoginResponse(LoginStatus.ERROR_PASSWORD, null);
        } else {
            TipoPersonaStatus tipoPersonaStatus = checkTipoPersona(persona.get());
            String redirectUrl = getRedirectUrl(tipoPersonaStatus, persona.get().getId());
            return new LoginResponse(LoginStatus.LOGIN_OK, redirectUrl);
        }
    }


    private String getRedirectUrl(TipoPersonaStatus tipoPersonaStatus, Long id) {
        switch (tipoPersonaStatus) {
            case ADMINISTRADOR:
                System.out.println("🔵 Redirigiendo a ADMINISTRADOR");
                return "/administracion/" + id + "/panel";
            case NUTRICIONISTA:
                System.out.println("🟢 Redirigiendo a NUTRICIONISTA");
                return "/nutricionista/dashboard";
            case USUARIO:
            default:
                System.out.println("🟠 Redirigiendo a USUARIO");
                return "/usuarios/" + id + "/inicio";
        }
    }



    @Transactional
    public TipoPersonaStatus checkTipoPersona(Persona persona) {
        String tipo = persona.getTipoPersona().trim().toLowerCase();

        if (tipo.equals("administrador")) {
            return TipoPersonaStatus.ADMINISTRADOR;
        } else if (tipo.equals("nutricionista")) {
            return TipoPersonaStatus.NUTRICIONISTA;
        } else {
            return TipoPersonaStatus.USUARIO;
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
    public PersonaData findById(Long personaId) {
        Persona persona = personaRepository.findById(personaId).orElse(null);
        if (persona == null) return null;
        else {
            return modelMapper.map(persona, PersonaData.class);
        }
    }

    public List<Persona> obtenerTodasPersonas() {
        return personaRepository.findAll();
    }
}
