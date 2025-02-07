package madstodolist.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mensajes")
public class Mensaje implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensaje")
    private int id_mensaje;

    @Column(name = "texto", nullable = false)
    private String texto;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    //Constructores

    public Mensaje(int id_mensaje, String texto, Usuario usuario) {
        this.id_mensaje = id_mensaje;
        this.texto = texto;
        this.usuario = usuario;
    }

    public Mensaje() {}

    //Getters & Setters

    public int getId_mensaje() { return id_mensaje; }
    public void setId_mensaje(int id_mensaje) { this.id_mensaje = id_mensaje; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}