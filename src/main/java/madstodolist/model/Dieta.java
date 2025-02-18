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
    private Long id;

    @Column(name= "nombre_dieta", nullable = false)
    private String nombre;

    @Column(name = "favorito", nullable = false, columnDefinition = "boolean default false")
    private boolean favorito;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToMany
    @JoinTable(name = "dieta_producto_lunes", joinColumns = @JoinColumn(name = "dieta_id"), inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private Set<Producto> productosLunes = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "dieta_producto_martes", joinColumns = @JoinColumn(name = "dieta_id"), inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private Set<Producto> productosMartes = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "dieta_producto_miercoles", joinColumns = @JoinColumn(name = "dieta_id"), inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private Set<Producto> productosMiercoles = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "dieta_producto_jueves", joinColumns = @JoinColumn(name = "dieta_id"), inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private Set<Producto> productosJueves = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "dieta_producto_viernes", joinColumns = @JoinColumn(name = "dieta_id"), inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private Set<Producto> productosViernes = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "dieta_producto_sabado", joinColumns = @JoinColumn(name = "dieta_id"), inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private Set<Producto> productosSabado = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "dieta_producto_domingo", joinColumns = @JoinColumn(name = "dieta_id"), inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private Set<Producto> productosDomingo = new HashSet<>();

    // Constructores, getters y setters
    public Dieta() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public boolean isFavorito() { return favorito; }
    public void setFavorito(boolean favorito) { this.favorito = favorito; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Set<Producto> getProductosLunes() { return productosLunes; }
    public void setProductosLunes(Set<Producto> productosLunes) { this.productosLunes = productosLunes; }

    public Set<Producto> getProductosMartes() { return productosMartes; }
    public void setProductosMartes(Set<Producto> productosMartes) { this.productosMartes = productosMartes; }

    public Set<Producto> getProductosMiercoles() { return productosMiercoles; }
    public void setProductosMiercoles(Set<Producto> productosMiercoles) { this.productosMiercoles = productosMiercoles; }

    public Set<Producto> getProductosJueves() { return productosJueves; }
    public void setProductosJueves(Set<Producto> productosJueves) { this.productosJueves = productosJueves; }

    public Set<Producto> getProductosViernes() { return productosViernes; }
    public void setProductosViernes(Set<Producto> productosViernes) { this.productosViernes = productosViernes; }

    public Set<Producto> getProductosSabado() { return productosSabado; }
    public void setProductosSabado(Set<Producto> productosSabado) { this.productosSabado = productosSabado; }

    public Set<Producto> getProductosDomingo() { return productosDomingo; }
    public void setProductosDomingo(Set<Producto> productosDomingo) { this.productosDomingo = productosDomingo; }
}
