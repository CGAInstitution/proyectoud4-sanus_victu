package madstodolist.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "personas")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_persona", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "PERSON")
public class Persona implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona", nullable = false)
    private int id;

    @Column(name = "nombre", unique = true, nullable = false)
    private String nombre;

    @Column(name = "contraseña", unique = false, nullable = false)
    private String contraseña;

    @Column(name = "correo", nullable = false)
    private String correo;

    // Relación con mensajes
    @OneToMany(mappedBy = "remitente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Mensaje> mensajesEnviados = new HashSet<>();

    @OneToMany(mappedBy = "destinatario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Mensaje> mensajesRecibidos = new HashSet<>();

    // Constructores


    public Persona(int id, String nombre, String contraseña, String correo, Set<Mensaje> mensajesEnviados, Set<Mensaje> mensajesRecibidos) {
        this.id = id;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.correo = correo;
        this.mensajesEnviados = mensajesEnviados;
        this.mensajesRecibidos = mensajesRecibidos;
    }

    public Persona() {}

    // Getters & Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public Set<Mensaje> getMensajesEnviados() { return mensajesEnviados; }
    public void setMensajesEnviados(Set<Mensaje> mensajesEnviados) { this.mensajesEnviados = mensajesEnviados; }

    public Set<Mensaje> getMensajesRecibidos() { return mensajesRecibidos; }
    public void setMensajesRecibidos(Set<Mensaje> mensajesRecibidos) { this.mensajesRecibidos = mensajesRecibidos; }
}