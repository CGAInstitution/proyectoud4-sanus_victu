package madstodolist.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "nutricionistas")
@PrimaryKeyJoinColumn(name="id_persona")
@DiscriminatorValue(value = "Nutricionista")
public class Nutricionista extends Persona {

    @OneToMany(mappedBy = "nutricionista", fetch = FetchType.EAGER)
    private Set<Usuario> pacientes = new HashSet<Usuario>();


    //Constructores


    public Nutricionista(int id, String nombre, String contraseña, String correo, Set<Mensaje> mensajesEnviados, Set<Mensaje> mensajesRecibidos, Set<Usuario> pacientes) {
        super(id, nombre, contraseña, correo, mensajesEnviados, mensajesRecibidos);
        this.pacientes = pacientes;
    }

    public Nutricionista() {
    }


    //Getters & Setters


    public Set<Usuario> getPacientes() {
        return pacientes;
    }

    public void setPacientes(Set<Usuario> pacientes) {
        this.pacientes = pacientes;
    }
}

