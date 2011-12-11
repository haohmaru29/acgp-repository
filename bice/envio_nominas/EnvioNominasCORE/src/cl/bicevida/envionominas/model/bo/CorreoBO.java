package cl.bicevida.envionominas.model.bo;

import java.io.Serializable;

/**
 * Representa un correo.
 * 
 * Registro de versiones:
 *      - 1.0 21/10/2010 Giorgio Gortaire (ACGP) : Version inicial.  
 */
public class CorreoBO implements Serializable {
    private Long id;
    private String nombre;
    private String subject;
    private String mensaje;
    private String tipo;
    private EstadoNominaBO estado;

    public CorreoBO() {
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNombre( String nombre ) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setSubject( String subject ) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setMensaje( String mensaje ) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setTipo( String tipo ) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setEstado( EstadoNominaBO estado ) {
        this.estado = estado;
    }

    public EstadoNominaBO getEstado() {
        return estado;
    }

    public String toString() {
        return "CorreoBO{" + "id=" + id + ", nombre=" + nombre + ", subject=" + subject + ", mensaje=" + mensaje + ", tipo=" + tipo + ", estado=" + estado + "}";
    }
}
