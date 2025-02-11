package madstodolist.dto;

import javax.validation.constraints.Email;
import java.util.Date;

// Clase de datos para el formulario de registro
public class RegistroData {
    @Email
    private String correo;
    private String contraseña;
    private String nombre;

    //Getters & Setters


    public @Email String getCorreo() {return correo;}
    public void setCorreo(@Email String correo) {this.correo = correo;}

    public String getContraseña() {return contraseña;}
    public void setContraseña(String contraseña) {this.contraseña = contraseña;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}
}
