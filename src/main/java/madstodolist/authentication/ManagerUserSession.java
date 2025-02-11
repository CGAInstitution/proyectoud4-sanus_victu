package madstodolist.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class ManagerUserSession {

    @Autowired
    HttpSession session;

    // Añadimos el id de usuario en la sesión HTTP para hacer
    // una autorización sencilla. En los métodos de controllers
    // comprobamos si el id del usuario logeado coincide con el obtenido
    // desde la URL
    public void logearPersona(int idPersona) {
        session.setAttribute("idUsuarioLogeado", idPersona);
    }

    public int personaLogeado() {
        return (int) session.getAttribute("idPersonaLogeado");
    }

    public void logout() {
        session.setAttribute("idUsuarioLogeado", null);
    }
}
