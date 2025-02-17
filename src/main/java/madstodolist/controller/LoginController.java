package madstodolist.controller;

import madstodolist.authentication.ManagerUserSession;
import madstodolist.authentication.LoginResponse;
import madstodolist.dto.LoginData;
import madstodolist.dto.RegistroData;
import madstodolist.dto.PersonaData;
import madstodolist.dto.UsuarioData;
import madstodolist.service.PersonaService;
import madstodolist.service.UsuarioService;
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

    @Autowired
    private UsuarioService usuarioService;

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
        LoginResponse loginResponse = personaService.login(loginData.geteMail(), loginData.getPassword());

        if (loginResponse.getStatus() == PersonaService.LoginStatus.LOGIN_OK) {
            PersonaData persona = personaService.findByEmail(loginData.geteMail());
            managerUserSession.logearPersona(persona.getId());

            // Guardamos el tipo de usuario en la sesión
            session.setAttribute("tipoUsuario", personaService.findById(managerUserSession.personaLogeado()).getTipoPersona());
            session.setAttribute("idUsuario", persona.getId());

            return "redirect:" + loginResponse.getRedirectUrl();
        }

        model.addAttribute("error", loginResponse.getStatus() == PersonaService.LoginStatus.USER_NOT_FOUND
                ? "No existe usuario"
                : "Contraseña incorrecta");

        return "formLogin";
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

        usuarioService.registrar(usuarioData);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout() {
        managerUserSession.logout();
        return "redirect:/login";
    }
}