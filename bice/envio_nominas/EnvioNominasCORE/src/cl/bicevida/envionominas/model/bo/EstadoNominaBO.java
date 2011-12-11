package cl.bicevida.envionominas.model.bo;

import java.io.Serializable;

public class EstadoNominaBO implements Serializable {
    private Long id;
    private String nombre;
    private String descripcion;

    public EstadoNominaBO() {
    }

    public EstadoNominaBO( Long idEstado ) {
        this.id = idEstado;
    }

    public EstadoNominaBO( Long id, String nombre, String descripcion ) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public EstadoNominaBO( String nombre ) {
        this.nombre = nombre;
    }

    public static EstadoNominaBO crear( Long id, String nombre ) {
        return new EstadoNominaBO( id, nombre, null );
    }

    public static EstadoNominaBO crear( Long id, String nombre, String descripcion ) {
        return new EstadoNominaBO( id, nombre, descripcion );
    }

    public static EstadoNominaBO crear( Long idEstado ) {
        return new EstadoNominaBO( idEstado );
    }

    public Long getId() {
        return id;
    }

    public void setId( Long newId ) {
        id = newId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion( String newDescripcion ) {
        descripcion = newDescripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre( String newNombre ) {
        nombre = newNombre;
    }

    public static EstadoNominaBO crear( String nombre ) {
        return new EstadoNominaBO( nombre );
    }

    public String toString() {
        return "EstadoNominaBO{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "}";
    }
}
