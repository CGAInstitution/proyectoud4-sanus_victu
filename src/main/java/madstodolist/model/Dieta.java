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
    private Long id_dieta;

    @Column(name= "nombre_dieta", nullable = false)
    private String nombre_dieta;

    @Column(name = "favorito", nullable = false, columnDefinition = "boolean default false")
    private boolean favorito;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name = "dieta_producto",
            joinColumns = @JoinColumn(name = "dieta_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private Set<Producto> productos = new HashSet<>();

    // Constructores
    public Dieta(Long id_dieta, String nombre_dieta, boolean favorito, Usuario usuario, Set<Producto> productos) {
        this.id_dieta = id_dieta;
        this.nombre_dieta = nombre_dieta;
        this.favorito = favorito;
        this.usuario = usuario;
        this.productos = productos;
    }

    public Dieta() {}

    // Getters & Setters
    public Long getId_dieta() { return id_dieta; }
    public void setId_dieta(Long id_dieta) { this.id_dieta = id_dieta; }

    public boolean isFavorito() { return favorito; }
    public void setFavorito(boolean favorito) { this.favorito = favorito; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Set<Producto> getProductos() { return productos; }
    public void setProductos(Set<Producto> productos) { this.productos = productos; }

    public String getNombre_dieta() { return nombre_dieta; }
    public void setNombre_dieta(String nombre_dieta) { this.nombre_dieta = nombre_dieta; }
}
