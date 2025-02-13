package madstodolist.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dietas")
public class Dieta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dieta")
    private int id_dieta;

    @Column(name= "nombre_dieta", nullable = false)
    private String nombre_dieta;

    @Column(name = "favotiro", nullable = false, columnDefinition = "boolean default false")
    private boolean favotiro;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "dieta", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Dieta_Producto> dietaProductos = new HashSet<>();

    // Constructores


    public Dieta(int id_dieta, String nombre_dieta, boolean favotiro, Usuario usuario, Set<Dieta_Producto> dietaProductos) {
        this.id_dieta = id_dieta;
        this.nombre_dieta = nombre_dieta;
        this.favotiro = favotiro;
        this.usuario = usuario;
        this.dietaProductos = dietaProductos;
    }

    public Dieta() {}

    // Getters & Setters
    public int getId_dieta() { return id_dieta; }
    public void setId_dieta(int id_dieta) { this.id_dieta = id_dieta; }

    public boolean isFavotiro() { return favotiro; }
    public void setFavotiro(boolean favotiro) { this.favotiro = favotiro; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Set<Dieta_Producto> getDietaProductos() { return dietaProductos; }
    public void setDietaProductos(Set<Dieta_Producto> dietaProductos) { this.dietaProductos = dietaProductos; }

    public String getNombre_dieta() {return nombre_dieta;}
    public void setNombre_dieta(String nombre_dieta) {this.nombre_dieta = nombre_dieta;}

}