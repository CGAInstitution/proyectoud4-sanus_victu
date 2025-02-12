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
    private int peso;

    @Column(name = "edad", nullable = false)
    private int edad;

    @Column(name = "sexo", nullable = false)
    private String sexo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Dieta> dietas = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "nutricionista_id", nullable = false)
    private Nutricionista nutricionista;

    //Constructores

    public Usuario(int id, String nombre, String contraseña, String correo, Set<Mensaje> mensajesEnviados, Set<Mensaje> mensajesRecibidos, int peso, int edad, Set<Dieta> dietas, String sexo, Nutricionista nutricionista) {
        super(id, nombre, contraseña, correo, mensajesEnviados, mensajesRecibidos);
        this.peso = peso;
        this.edad = edad;
        this.dietas = dietas;
        this.sexo = sexo;
        this.nutricionista = nutricionista;
    }

    public Usuario() {}

    //Getters & Setters

    public int getPeso() { return peso; }
    public void setPeso(int peso) { this.peso = peso; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public Set<Dieta> getDietas() { return dietas; }
    public void setDietas(Set<Dieta> dietas) { this.dietas = dietas; }

    public Nutricionista getNutricionista() { return nutricionista; }
    public void setNutricionista(Nutricionista nutricionista) { this.nutricionista = nutricionista; }

}