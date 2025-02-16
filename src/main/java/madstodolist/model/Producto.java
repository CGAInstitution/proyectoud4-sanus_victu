package madstodolist.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "valor_energetico", nullable = false)
    private int valor_energetico;

    @Column(name = "grasas", nullable = false)
    private float grasas;

    @Column(name = "hidratos_carbono", nullable = false)
    private float hidratos_carbono;

    @Column(name = "fibra_alimentaria", nullable = false)
    private float fibra_alimentaria;

    @Column(name = "proteinas", nullable = false)
    private float proteinas;

    @Column(name = "sal", nullable = false)
    private float sal;

    @Column(name = "fecha", nullable = true)
    private String fecha;

    @ManyToMany(mappedBy = "productos")
    private Set<Dieta> dietas = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "producto_supermercado",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "supermercado_id")
    )
    private Set<Supermercado> supermercados = new HashSet<>();

    // Constructores
    public Producto(Long id, String nombre, float grasas, int valor_energetico, float hidratos_carbono, float fibra_alimentaria, float proteinas, float sal, String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.grasas = grasas;
        this.valor_energetico = valor_energetico;
        this.hidratos_carbono = hidratos_carbono;
        this.fibra_alimentaria = fibra_alimentaria;
        this.proteinas = proteinas;
        this.sal = sal;
        this.fecha = fecha;
    }

    public Producto() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getValor_energetico() { return valor_energetico; }
    public void setValor_energetico(int valor_energetico) { this.valor_energetico = valor_energetico; }

    public float getHidratos_carbono() { return hidratos_carbono; }
    public void setHidratos_carbono(float hidratos_carbono) { this.hidratos_carbono = hidratos_carbono; }

    public float getGrasas() { return grasas; }
    public void setGrasas(float grasas) { this.grasas = grasas; }

    public float getFibra_alimentaria() { return fibra_alimentaria; }
    public void setFibra_alimentaria(float fibra_alimentaria) { this.fibra_alimentaria = fibra_alimentaria; }

    public float getProteinas() { return proteinas; }
    public void setProteinas(float proteinas) { this.proteinas = proteinas; }

    public float getSal() { return sal; }
    public void setSal(float sal) { this.sal = sal; }

    public Set<Dieta> getDietas() { return dietas; }
    public void setDietas(Set<Dieta> dietas) { this.dietas = dietas; }

    public Set<Supermercado> getSupermercados() { return supermercados; }
    public void setSupermercados(Set<Supermercado> supermercados) { this.supermercados = supermercados; }
}
