package cl.bicevida.envionominas.model.bo;

import java.io.Serializable;

/**
 * Representa una moneda.
 * 
 * Registro de versiones:
 *      - 1.0 21/10/2010 Giorgio Gortaire (ACGP) : Version inicial.  
 */
public class MonedaBO implements Serializable {
    private Long id;
    private String nombre;

    public MonedaBO() {
    }

    public MonedaBO( Long id ) {
        this.id = id;
    }

    public static MonedaBO crear( Long id ) {
        return new MonedaBO( id );
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

    public String toString() {
        return "MonedaBO{" + "id=" + id + ", nombre=" + nombre + "}";
    }
}
