package madstodolist.controller;

import madstodolist.authentication.ManagerUserSession;
import madstodolist.dto.LoginData;
import madstodolist.dto.RegistroData;
import madstodolist.dto.PersonaData;
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
        PersonaService.LoginStatus loginStatus = personaService.login(loginData.geteMail(), loginData.getPassword());

        if (loginStatus == PersonaService.LoginStatus.LOGIN_OK) {
            PersonaData usuario = personaService.findByEmail(loginData.geteMail());

            managerUserSession.logearPersona(usuario.getId());

            return "redirect:/usuarios/" + usuario.getId() + "/inicio";
        } else if (loginStatus == PersonaService.LoginStatus.USER_NOT_FOUND) {
            model.addAttribute("error", "No existe usuario");
            return "formLogin";
        } else if (loginStatus == PersonaService.LoginStatus.ERROR_PASSWORD) {
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
            model.addAttribute("error", "El personaData " + registroData.getCorreo() + " ya existe");
            return "formRegistro";
        }

        PersonaData personaData = new PersonaData();
        personaData.setCorreo(registroData.getCorreo());
        personaData.setContraseña(registroData.getContraseña());
        personaData.setNombre(registroData.getNombre());

        personaService.registrar(personaData);
        return "redirect:/login";
   }

   @GetMapping("/logout")
   public String logout(HttpSession session) {
        managerUserSession.logout();
        return "redirect:/login";
   }
}
