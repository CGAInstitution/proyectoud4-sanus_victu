package madstodolist.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@PrimaryKeyJoinColumn(name="id_persona")
@DiscriminatorValue(value = "USUARIO")
public class Usuario extends Persona implements Serializable {

    @Column(name = "peso", nullable = false)
    private float peso;

    @Column(name = "edad", nullable = false)
    private int edad;

    @Column(name = "sexo", nullable = false)
    private String sexo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Dieta> dietas = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "nutricionista_id", nullable = false)
    private Nutricionista nutricionista;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Mensaje> mensajes = new HashSet<>();

    //Constructores

    public Usuario(int id, String nombre, String contraseña, String correo, float peso, int edad, String sexo, Set<Dieta> dietas, Nutricionista nutricionista, Set<Mensaje> mensajes) {
        super(id, nombre, contraseña, correo);
        this.peso = peso;
        this.edad = edad;
        this.sexo = sexo;
        this.dietas = dietas;
        this.nutricionista = nutricionista;
        this.mensajes = mensajes;
    }

    public Usuario() {}

    //Getters & Setters

    public float getPeso() { return peso; }
    public void setPeso(float peso) { this.peso = peso; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public Set<Dieta> getDietas() { return dietas; }
    public void setDietas(Set<Dieta> dietas) { this.dietas = dietas; }

    public Nutricionista getNutricionista() { return nutricionista; }
    public void setNutricionista(Nutricionista nutricionista) { this.nutricionista = nutricionista; }

    public Set<Mensaje> getMensajes() { return mensajes; }
    public void setMensajes(Set<Mensaje> mensajes) { this.mensajes = mensajes; }
}