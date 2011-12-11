package cl.bicevida.envionominas.model.bo;

import java.io.Serializable;

public class TipoPagoBO implements Serializable {
    private Long id;
    private String nombre;
    private String descripcion;

    public TipoPagoBO() {
    }

    public TipoPagoBO( Long _idTipoPago, String _nombre, String _descripcion ) {
        this.id = _idTipoPago;
        this.nombre = _nombre;
        this.descripcion = _descripcion;
    }

    public static TipoPagoBO crear( Long _idTipoPago, String _nombre, String _descripcion ) {
        return new TipoPagoBO( _idTipoPago, _nombre, _descripcion );
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

    public void setDescripcion( String descripcion ) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String toString() {
        return "TipoPagoBO{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "}";
    }
}
