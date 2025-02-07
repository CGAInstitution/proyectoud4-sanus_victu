package madstodolist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "administradores")
public class Administrador extends Persona {

    @Column(name = "nick_name", nullable = false)
    private String nickName;


    //Constructores


    public Administrador(Integer id, String nombre, String contraseña, String nickName) {
        super(id, nombre, contraseña);
        this.nickName = nickName;
    }

    public Administrador() {}


    //Getters & Setters


    public String getNickName() {return nickName;}

    public void setNickName(String nickName) {this.nickName = nickName;}
}
