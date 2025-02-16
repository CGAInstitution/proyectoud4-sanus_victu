package madstodolist.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "mensajes")
public class Mensaje implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensaje")
    private Long id_mensaje;

    @Column(name = "texto", nullable = false)
    private String texto;

    @ManyToOne
    @JoinColumn(name = "remitente_id", nullable = false)
    private Persona remitente;

    @ManyToOne
    @JoinColumn(name = "destinatario_id", nullable = false)
    private Persona destinatario;

    @Column(name = "hora", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date hora;

    //Constructores

    public Mensaje(Long id_mensaje, String texto, Persona remitente, Persona destinatario, Date hora) {
        this.id_mensaje = id_mensaje;
        this.texto = texto;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.hora = hora;
    }

    public Mensaje() {}

    //Getters & Setters

    public Long getId_mensaje() { return id_mensaje; }
    public void setId_mensaje(Long id_mensaje) { this.id_mensaje = id_mensaje; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public Persona getRemitente() { return remitente; }
    public void setRemitente(Persona remitente) { this.remitente = remitente; }

    public Persona getDestinatario() { return destinatario; }
    public void setDestinatario(Persona destinatario) { this.destinatario = destinatario; }

    public Date getHora() { return hora; }
    public void setHora(Date hora) { this.hora = hora; }
}