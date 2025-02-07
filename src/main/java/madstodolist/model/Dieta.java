package madstodolist.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dietas")
public class Dieta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dieta")
    private int id_dieta;

    @Column(name = "favotiro", nullable = false, columnDefinition = "boolean default false")
    private boolean favotiro;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    //Constructores

    public Dieta(int id_dieta, boolean favotiro, Usuario usuario) {
        this.id_dieta = id_dieta;
        this.favotiro = favotiro;
        this.usuario = usuario;
    }

    public Dieta() {}

    //Getters & Setters

    public int getId_dieta() { return id_dieta; }
    public void setId_dieta(int id_dieta) { this.id_dieta = id_dieta; }

    public boolean isFavotiro() { return favotiro; }
    public void setFavotiro(boolean favotiro) { this.favotiro = favotiro; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}