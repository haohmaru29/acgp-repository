package cl.bicevida.envionominas.model.bo;

import java.io.Serializable;

/**
 * Representa un tipo de cuenta.
 * 
 * Registro de versiones:
 *      - 1.0 21/10/2010 Giorgio Gortaire (ACGP) : Version inicial.  
 */
public class TipoCuentaBO implements Serializable {
    private Long id;
    private String nombre;
    private String descripcion;

    public TipoCuentaBO() {
    }

    public TipoCuentaBO( Long _id, String _nombre ) {
        this.id = _id;
        this.nombre = _nombre;
    }

    public TipoCuentaBO( Long id, String descripcion, String nombre ) {
        this.id = id;
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public static TipoCuentaBO crear( Long id, String descripcion, String nombre ) {
        return new TipoCuentaBO( id, descripcion, nombre );
    }

    public TipoCuentaBO( Long id ) {
        this.id = id;
    }

    public static TipoCuentaBO crear( Long id ) {
        return new TipoCuentaBO( id );
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

    public static TipoCuentaBO crear( String nombre ) {
        return new TipoCuentaBO( nombre );
    }

    public TipoCuentaBO( String nombre ) {
        this.nombre = nombre;
    }

    public static TipoCuentaBO crear( Long _id, String _nombre ) {
        return new TipoCuentaBO( _id, _nombre );
    }

    public String toString() {
        return "TipoCuentaBO{" + "id=" + ", nombre=" + ", descripcion=" + "}";
    }
}
