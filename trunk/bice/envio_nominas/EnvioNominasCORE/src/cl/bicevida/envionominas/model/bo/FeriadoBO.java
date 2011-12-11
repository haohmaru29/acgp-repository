package cl.bicevida.envionominas.model.bo;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeriadoBO implements Serializable {
    private Long id;
    private Date fechaFeriado;
    private String descripcionFeriado;
    private String tipoFeriado;
    private String descTipoFeriado;

    public FeriadoBO() {
    }

    public Long getId() {
        return id;
    }

    public void setId( Long newId ) {
        id = newId;
    }

    public Date getFechaFeriado() {
        return fechaFeriado;
    }

    public void setFechaFeriado( Date newFechaFeriado ) {
        fechaFeriado = newFechaFeriado;
    }

    public String getDescripcionFeriado() {
        return descripcionFeriado;
    }

    public void setDescripcionFeriado( String newDescripcionFeriado ) {
        descripcionFeriado = newDescripcionFeriado;
    }

    public String getTipoFeriado() {
        return tipoFeriado;
    }

    public void setTipoFeriado( String newTipo ) {
        tipoFeriado = newTipo;
    }

    public String getDescTipoFeriado() {
        return descTipoFeriado;
    }

    public void setDescTipoFeriado( String newDescTipo ) {
        descTipoFeriado = newDescTipo;
    }

    public List<FeriadoBO> getFeriados() {
        List<FeriadoBO> registros = new ArrayList<FeriadoBO>();
        return registros;
    }

    public static FeriadoBO crear( Long id, String tipo, String desctipo, String descripcionFeriado, Date fechaFeriado ) {
        return new FeriadoBO( id, tipo, desctipo, descripcionFeriado, fechaFeriado );
    }

    public FeriadoBO( Long id, String tipo, String desctipo, String descripcionFeriado, Date fechaFeriado ) {
        this.id = id;
        this.tipoFeriado = tipo;
        this.descTipoFeriado = desctipo;
        this.descripcionFeriado = descripcionFeriado;
        this.fechaFeriado = fechaFeriado;
    }

    public String toString() {
        return "FeriadoBO{" + "id=" + id + ", fechaFeriado=" + fechaFeriado + ", descripcionFeriado=" + descripcionFeriado + ", tipoFeriado=" + tipoFeriado + ", descTipoFeriado=" + descTipoFeriado + "}";
    }
}
