package madstodolist.controller;

import madstodolist.authentication.ManagerUserSession;
import madstodolist.authentication.LoginResponse;
import madstodolist.dto.LoginData;
import madstodolist.dto.RegistroData;
import madstodolist.dto.PersonaData;
import madstodolist.dto.UsuarioData;
import madstodolist.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    PersonaService personaService;

    @Autowired
    ManagerUserSession managerUserSession;

    @GetMapping("/")
    public String home(Model model) {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginData", new LoginData());
        return "formLogin";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute LoginData loginData, Model model, HttpSession session) {
        // Llamada al servicio para comprobar si el login es correcto
        LoginResponse loginResponse = personaService.login(loginData.geteMail(), loginData.getPassword());

        System.out.println("Estado del login: " + loginResponse.getStatus());

        if (loginResponse.getStatus() == PersonaService.LoginStatus.LOGIN_OK) {
            PersonaData persona = personaService.findByEmail(loginData.geteMail());

            if (persona == null) {
                System.out.println("❌ ERROR: No se encontró la persona en la base de datos.");
                model.addAttribute("error", "Error al recuperar usuario.");
                return "formLogin";
            }

            System.out.println("✅ Usuario encontrado: " + persona.getId());
            managerUserSession.logearPersona(persona.getId());

            // Verificamos que el ID se guardó en la sesión
            Long idEnSesion = managerUserSession.personaLogeado();
            System.out.println("📝 ID guardado en sesión: " + idEnSesion);

            if (idEnSesion == null) {
                System.out.println("❌ ERROR: El ID de sesión es NULL.");
                model.addAttribute("error", "Error de sesión.");
                return "formLogin";
            }

            // Redirección según el tipo de persona
            System.out.println("🔄 Redirigiendo a: " + loginResponse.getRedirectUrl());
            return "redirect:" + loginResponse.getRedirectUrl();
        }

        if (loginResponse.getStatus() == PersonaService.LoginStatus.USER_NOT_FOUND) {
            model.addAttribute("error", "No existe usuario");
            return "formLogin";
        }

        if (loginResponse.getStatus() == PersonaService.LoginStatus.ERROR_PASSWORD) {
            model.addAttribute("error", "Contraseña incorrecta");
            return "formLogin";
        }

        return "formLogin";
    }


    @GetMapping("/registro")
    public String registroForm(Model model) {
        model.addAttribute("registroData", new RegistroData());
        return "formRegistro";
    }

    @PostMapping("/registro")
    public String registroSubmit(@Valid RegistroData registroData, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "formRegistro";
        }

        if (personaService.findByEmail(registroData.getCorreo()) != null) {
            model.addAttribute("registroData", registroData);
            model.addAttribute("error", "El usuarioData " + registroData.getCorreo() + " ya existe");
            return "formRegistro";
        }

        UsuarioData usuarioData = new UsuarioData();
        usuarioData.setCorreo(registroData.getCorreo());
        usuarioData.setContraseña(registroData.getContraseña());
        usuarioData.setNombre(registroData.getNombre());
        usuarioData.setEdad(registroData.getEdad());
        usuarioData.setPeso(registroData.getPeso());
        usuarioData.setSexo(registroData.getSexo());

        personaService.registrar(usuarioData);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        managerUserSession.logout();
        return "redirect:/login";
    }
}