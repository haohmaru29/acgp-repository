package cl.bicevida.envionominas.model.bo;

import java.io.Serializable;

/**
 * Representa una Empresa.
 * 
 * Registro de versiones:
 *      - 1.0 21/10/2010 Giorgio Gortaire (ACGP) : Version inicial.  
 */
public class EmpresaBO implements Serializable {
    private Long id;
    private String nombre;

    /**
     * Rut de la empresa.
     */
    private String rut;

    /**
     * Digito verificador del rut de la empresa.
     */
    private String dvRut;

    public EmpresaBO() {
    }

    public EmpresaBO( Long id ) {
        this.id = id;
    }

    public static EmpresaBO crear( Long id ) {
        return new EmpresaBO( id );
    }

    public void setRut( String rut ) {
        this.rut = rut;
    }

    public String getRut() {
        return rut;
    }

    public void setDvRut( String dvRut ) {
        this.dvRut = dvRut;
    }

    public String getDvRut() {
        return dvRut;
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
        return "EmpresaBO{" + "id=" + id + ", nombre=" + nombre + ", rut=" + rut + ", dvRut=" + dvRut + "}";
    }
}
