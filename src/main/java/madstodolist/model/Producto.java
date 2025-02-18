package madstodolist.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
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
    private int valorEnergetico;

    @Column(name = "grasas", nullable = false)
    private float grasas;

    @Column(name = "hidratos_carbono", nullable = false)
    private float hidratosCarbono;

    @Column(name = "fibra_alimentaria", nullable = false)
    private float fibraAlimentaria;

    @Column(name = "proteinas", nullable = false)
    private float proteinas;

    @Column(name = "sal", nullable = false)
    private float sal;

    @ManyToMany(mappedBy = "productosLunes")
    private Set<Dieta> dietasLunes = new HashSet<>();

    @ManyToMany(mappedBy = "productosMartes")
    private Set<Dieta> dietasMartes = new HashSet<>();

    @ManyToMany(mappedBy = "productosMiercoles")
    private Set<Dieta> dietasMiercoles = new HashSet<>();

    @ManyToMany(mappedBy = "productosJueves")
    private Set<Dieta> dietasJueves = new HashSet<>();

    @ManyToMany(mappedBy = "productosViernes")
    private Set<Dieta> dietasViernes = new HashSet<>();

    @ManyToMany(mappedBy = "productosSabado")
    private Set<Dieta> dietasSabado = new HashSet<>();

    @ManyToMany(mappedBy = "productosDomingo")
    private Set<Dieta> dietasDomingo = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "producto_supermercado",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "supermercado_id")
    )
    private Set<Supermercado> supermercados = new HashSet<>();

    // Constructores, getters y setters
    public Producto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getValorEnergetico() { return valorEnergetico; }
    public void setValorEnergetico(int valorEnergetico) { this.valorEnergetico = valorEnergetico; }

    public float getGrasas() { return grasas; }
    public void setGrasas(float grasas) { this.grasas = grasas; }

    public float getHidratosCarbono() { return hidratosCarbono; }
    public void setHidratosCarbono(float hidratosCarbono) { this.hidratosCarbono = hidratosCarbono; }

    public float getFibraAlimentaria() { return fibraAlimentaria; }
    public void setFibraAlimentaria(float fibraAlimentaria) { this.fibraAlimentaria = fibraAlimentaria; }

    public float getProteinas() { return proteinas; }
    public void setProteinas(float proteinas) { this.proteinas = proteinas; }

    public float getSal() { return sal; }
    public void setSal(float sal) { this.sal = sal; }

    public Set<Dieta> getDietasLunes() { return dietasLunes; }
    public void setDietasLunes(Set<Dieta> dietasLunes) { this.dietasLunes = dietasLunes; }

    public Set<Dieta> getDietasMartes() { return dietasMartes; }
    public void setDietasMartes(Set<Dieta> dietasMartes) { this.dietasMartes = dietasMartes; }

    public Set<Dieta> getDietasMiercoles() { return dietasMiercoles; }
    public void setDietasMiercoles(Set<Dieta> dietasMiercoles) { this.dietasMiercoles = dietasMiercoles; }

    public Set<Dieta> getDietasJueves() { return dietasJueves; }
    public void setDietasJueves(Set<Dieta> dietasJueves) { this.dietasJueves = dietasJueves; }

    public Set<Dieta> getDietasViernes() { return dietasViernes; }
    public void setDietasViernes(Set<Dieta> dietasViernes) { this.dietasViernes = dietasViernes; }

    public Set<Dieta> getDietasSabado() { return dietasSabado; }
    public void setDietasSabado(Set<Dieta> dietasSabado) { this.dietasSabado = dietasSabado; }

    public Set<Dieta> getDietasDomingo() { return dietasDomingo; }
    public void setDietasDomingo(Set<Dieta> dietasDomingo) { this.dietasDomingo = dietasDomingo; }

    public Set<Supermercado> getSupermercados() { return supermercados; }
    public void setSupermercados(Set<Supermercado> supermercados) { this.supermercados = supermercados; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(id, producto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}



