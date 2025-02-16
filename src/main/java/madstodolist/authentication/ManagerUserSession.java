package madstodolist.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class ManagerUserSession {

    @Autowired
    HttpSession session;

    // Inicia sesión guardando el ID en la sesión
    public void logearPersona(Long idPersona) {
        session.setAttribute("idPersonaLogeada", idPersona);
    }

    // Obtiene el ID de la persona logeada (maneja el caso de null)
    public Long personaLogeado() {
        Object id = session.getAttribute("idPersonaLogeada");

        return id != null ? (Long) id : null;
    }

    // Cierra la sesión eliminando el atributo o invalidando la sesión
    public void logout() {
        session.invalidate();
    }
}
