package madstodolist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "administradores")
public class Administrador extends Persona {

    @Column(name = "nick_name", nullable = false)
    private String nickName;


    //Constructores


    public Administrador(int id, String nombre, String contraseña, String correo, Set<Mensaje> mensajesEnviados, Set<Mensaje> mensajesRecibidos, String nickName) {
        super(id, nombre, contraseña, correo, mensajesEnviados, mensajesRecibidos);
        this.nickName = nickName;
    }

    public Administrador() {}


    //Getters & Setters


    public String getNickName() {return nickName;}

    public void setNickName(String nickName) {this.nickName = nickName;}
}
