package madstodolist.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "administradores")
public class Administrador extends Persona {



    public Administrador(Integer id, String nombre, String contraseña) {
        super(id, nombre, contraseña);
    }

    public Administrador() {}


}
