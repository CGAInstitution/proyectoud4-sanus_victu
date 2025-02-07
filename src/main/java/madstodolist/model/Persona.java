package madstodolist.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "personas")
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_persona",discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue(value="PERSON")
public class Persona implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_persona", nullable = false)
    private int id;

    @Column(name="nombre", unique = true, nullable = false)
    private String nombre;

    @Column(name="contraseña", unique = false, nullable = false)
    private String contraseña;

    @Column(name="correo", nullable=false)
    private String correo;

    //Constructores

    public Persona(int id, String nombre, String contraseña, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.correo = correo;
    }

    public Persona() {}


    //Getters & Setters

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getContraseña() {return contraseña;}
    public void setContraseña(String contraseña) {this.contraseña = contraseña;}
}
