package cl.bicevida.envionominas.model.bo;

import java.io.Serializable;

/**
 * Representa un estado de transaccion.
 * 
 * Registro de versiones:
 *      - 1.0 21/10/2010 Giorgio Gortaire (ACGP) : Version inicial.  
 */
public class EstadoTransaccionBO implements Serializable {
    private Long id;
    private String nombre;
    private String descripcion;

    public EstadoTransaccionBO() {
    }

    public EstadoTransaccionBO( Long id, String descripcion, String nombre ) {
        this.id = id;
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public static EstadoTransaccionBO crear( Long id, String descripcion, String nombre ) {
        return new EstadoTransaccionBO( id, descripcion, nombre );
    }

    public EstadoTransaccionBO( Long id ) {
        this.id = id;
    }

    public static EstadoTransaccionBO crear( Long id ) {
        return new EstadoTransaccionBO( id );
    }

    public void setDescripcion( String descripcion ) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
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

    public static EstadoTransaccionBO crear( String nombre ) {
        return new EstadoTransaccionBO( nombre );
    }

    public EstadoTransaccionBO( String nombre ) {
        this.nombre = nombre;
    }

    public String toString() {
        return "EstadoTransaccionBO{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "}";
    }
}
