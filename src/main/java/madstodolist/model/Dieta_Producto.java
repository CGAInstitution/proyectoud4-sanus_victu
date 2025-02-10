package madstodolist.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dietas_productos")
public class Dieta_Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "dieta_id", nullable = false)
    private Dieta dieta;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    // Constructores
    public Dieta_Producto(Dieta dieta, Producto producto) {
        this.dieta = dieta;
        this.producto = producto;
    }

    public Dieta_Producto() {}

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Dieta getDieta() { return dieta; }
    public void setDieta(Dieta dieta) { this.dieta = dieta; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
}