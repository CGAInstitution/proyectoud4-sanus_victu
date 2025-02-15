package madstodolist.authentication;

import madstodolist.service.PersonaService;

public class LoginResponse {
    private PersonaService.LoginStatus status;
    private String redirectUrl;

    public LoginResponse(PersonaService.LoginStatus status, String redirectUrl) {
        this.status = status;
        this.redirectUrl = redirectUrl;
    }

    public PersonaService.LoginStatus getStatus() {
        return status;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }
}