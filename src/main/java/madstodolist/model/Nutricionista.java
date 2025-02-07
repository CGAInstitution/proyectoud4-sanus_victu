package madstodolist.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table(name = "nutricionistas")
@PrimaryKeyJoinColumn(name="id_persona")
@DiscriminatorValue(value = "Nutricionista")
public class Nutricionista extends Persona {

    @OneToMany(mappedBy = "nutricionista")
    private ArrayList<Usuario> pacientes;

    @Column(name="mensajes", unique = true, nullable = false)
    private String nombre;

    //Constructores

    public Nutricionista(int id, String nombre, String contraseña, String correo, ArrayList<Usuario> pacientes) {
        super(id, nombre, contraseña, correo);
        this.pacientes = pacientes;
    }

    public Nutricionista() {}


    //Getters & Setters


    public ArrayList<Usuario> getPacientes() {return pacientes;}

    public void setPacientes(ArrayList<Usuario> pacientes) {this.pacientes = pacientes;}
}