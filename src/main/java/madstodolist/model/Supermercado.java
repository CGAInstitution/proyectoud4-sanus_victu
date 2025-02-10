package madstodolist.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "supermercados")
public class Supermercado implements Serializable {
    @Id
    @Column(name = "Id_supermercado")
    private String id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "localización", nullable = false)
    private String localizacion;

    @OneToMany(mappedBy = "supermercado", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Producto_Supermercado> productoSupermercados = new HashSet<>();

    // Constructores
    public Supermercado(String id, String nombre, String localizacion) {
        this.id = id;
        this.nombre = nombre;
        this.localizacion = localizacion;
    }

    public Supermercado() {}

    // Getters & Setters
    public String getLocalizacion() { return localizacion; }
    public void setLocalizacion(String localizacion) { this.localizacion = localizacion; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Set<Producto_Supermercado> getProductoSupermercados() { return productoSupermercados; }
    public void setProductoSupermercados(Set<Producto_Supermercado> productoSupermercados) { this.productoSupermercados = productoSupermercados; }
}