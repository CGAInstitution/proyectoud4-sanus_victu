package madstodolist.dto;

import java.util.Date;

public class MensajeData {
    private String texto;
    private PersonaData remitente;
    private PersonaData destinatario;
    private Date fecha;


    //Constructor

    public MensajeData(String texto, PersonaData remitente, PersonaData destinatario, Date fecha) {
        this.texto = texto;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.fecha = fecha;
    }

    //Getters & Setters

    public String getTexto() {return texto;}
    public void setTexto(String texto) {this.texto = texto;}

    public PersonaData getRemitente() {return remitente;}
    public void setRemitente(PersonaData remitente) {this.remitente = remitente;}

    public Date getFecha() {return fecha;}
    public void setFecha(Date fecha) {this.fecha = fecha;}

    public PersonaData getDestinatario() {return destinatario;}
    public void setDestinatario(PersonaData destinatario) {this.destinatario = destinatario;}
}
