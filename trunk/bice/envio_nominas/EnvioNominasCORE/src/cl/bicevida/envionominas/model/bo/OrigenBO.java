package cl.bicevida.envionominas.model.bo;

import java.io.Serializable;

/**
 * Representa un origen.
 * 
 * Registro de versiones:
 *      - 1.0 21/10/2010 Giorgio Gortaire (ACGP) : Version inicial.  
 */
public class OrigenBO implements Serializable {
    private String codigo;
    private String nombre;

    public OrigenBO() {
    }

    public OrigenBO( String codigo ) {
        this.codigo = codigo;
    }

    public OrigenBO( String codigo, String nombre ) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public static OrigenBO crear( String codigo, String nombre ) {
        return new OrigenBO( codigo, nombre );
    }

    public static OrigenBO crear( String codigo ) {
        return new OrigenBO( codigo );
    }

    public void setCodigo( String codigo ) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setNombre( String nombre ) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String toString() {
        return "OrigenBO{" + "codigo=" + codigo + ", nombre=" + nombre + "}";
    }
}
