package madstodolist.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "productos_supermercados")
public class Producto_Supermercado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "supermercado_id", nullable = false)
    private Supermercado supermercado;

    // Constructores
    public Producto_Supermercado(Producto producto, Supermercado supermercado) {
        this.producto = producto;
        this.supermercado = supermercado;
    }

    public Producto_Supermercado() {}

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public Supermercado getSupermercado() { return supermercado; }
    public void setSupermercado(Supermercado supermercado) { this.supermercado = supermercado; }
}