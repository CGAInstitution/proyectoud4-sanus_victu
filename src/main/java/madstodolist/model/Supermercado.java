package madstodolist.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "supermercados")
public class Supermercado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_supermercado")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @ManyToMany(mappedBy = "supermercados")
    private Set<Producto> productos = new HashSet<>();


    // Constructores

    public Supermercado(Long id, String nombre, String localizacion) {
        this.id = id;
        this.nombre = nombre;
    }

    public Supermercado() {}


    // Getters & Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Set<Producto> getProductos() { return productos; }
    public void setProductos(Set<Producto> productos) { this.productos = productos; }
}
