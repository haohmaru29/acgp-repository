package cl.bicevida.envionominas.model.bo;

import java.io.Serializable;

public class EstadoProcesoBO implements Serializable {
    private Long id;
    private String nombre;

    public EstadoProcesoBO() {
    }

    public EstadoProcesoBO( Long _idEstado ) {
        this.id = _idEstado;
    }

    public EstadoProcesoBO( Long _idEstado, String _nombre ) {
        this.id = _idEstado;
        this.nombre = _nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long newId ) {
        id = newId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre( String newNombre ) {
        nombre = newNombre;
    }

    public static EstadoProcesoBO crear( Long _idEstado ) {
        return new EstadoProcesoBO( _idEstado );
    }

    public static EstadoProcesoBO crear( Long _idEstado, String _nombre ) {
        return new EstadoProcesoBO( _idEstado, _nombre );
    }

    public String toString() {
        return "EstadoProcesoBO{" + "id=" + id + ", nombre=" + nombre + "}";
    }
}
