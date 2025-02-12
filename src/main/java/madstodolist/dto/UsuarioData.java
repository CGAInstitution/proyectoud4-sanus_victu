package madstodolist.dto;

import madstodolist.model.Mensaje;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


// Data Transfer Object para la clase Usuario
public class UsuarioData {

    private int id;
    private String correo;
    private String nombre;
    private String contraseña;
    private int peso;
    private int edad;
    private String sexo;

    // Getters y setters

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getPeso() {return peso;}
    public void setPeso(int peso) {this.peso = peso;}

    public int getEdad() {return edad;}
    public void setEdad(int edad) {this.edad = edad;}

    public String getSexo() {return sexo;}
    public void setSexo(String sexo) {this.sexo = sexo;}

    // Sobreescribimos equals y hashCode para que dos usuarios sean iguales
    // si tienen el mismo ID (ignoramos el resto de atributos)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioData)) return false;
        UsuarioData that = (UsuarioData) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
