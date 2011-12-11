package cl.bicevida.envionominas.model.bo;

import java.io.Serializable;

public class TipoFeriadoBO implements Serializable {
    private Long id;
    private String nombre;
    private String descripcion;

    public TipoFeriadoBO() {
    }

    public TipoFeriadoBO( Long id, String descripcion, String nombre ) {
        this.id = id;
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public static TipoFeriadoBO crear( Long id, String descripcion, String nombre ) {
        return new TipoFeriadoBO( id, descripcion, nombre );
    }

    public TipoFeriadoBO( Long id ) {
        this.id = id;
    }

    public static TipoFeriadoBO crear( Long id ) {
        return new TipoFeriadoBO( id );
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

    public static TipoFeriadoBO crear( String nombre ) {
        return new TipoFeriadoBO( nombre );
    }

    public TipoFeriadoBO( String nombre ) {
        this.nombre = nombre;
    }

    public String toString() {
        return "TipoFeriadoBO{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "}";
    }
}
