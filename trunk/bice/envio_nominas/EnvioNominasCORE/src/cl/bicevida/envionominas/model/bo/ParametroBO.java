package cl.bicevida.envionominas.model.bo;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParametroBO implements Serializable {
    private Long id;
    private String claveParametro;
    private String valorParametro;
    private String descripcionParametro;
    private String tipoParametro;

    public ParametroBO() {
    }

    public Long getId() {
        return id;
    }

    public void setId( Long newId ) {
        id = newId;
    }

    public String getClaveParametro() {
        return claveParametro;
    }

    public void setClaveParametro( String newClaveParametro ) {
        claveParametro = newClaveParametro;
    }

    public String getValorParametro() {
        return valorParametro;
    }

    public void setValorParametro( String newValorParametro ) {
        valorParametro = newValorParametro;
    }

    public String getDescripcionParametro() {
        return descripcionParametro;
    }

    public void setDescripcionParametro( String newDescripcionParametro ) {
        descripcionParametro = newDescripcionParametro;
    }

    public List<ParametroBO> getParametros() {
        List<ParametroBO> registros = new ArrayList<ParametroBO>();
        return registros;
    }

    public static ParametroBO crear( Long id, String claveParametro, String valorParametro, String descripcionParametro ) {
        return new ParametroBO( id, claveParametro, valorParametro, descripcionParametro );
    }

    public static ParametroBO crear( String clave, String valor, String tipo ) {
        return new ParametroBO( clave, valor, tipo );
    }

    public ParametroBO( String clave, String valor, String tipo ) {
        this.claveParametro = clave;
        this.valorParametro = valor;
        this.tipoParametro = tipo;
    }

    public ParametroBO( Long id, String claveParametro, String valorParametro, String descripcionParametro ) {
        this.id = id;
        this.claveParametro = claveParametro;
        this.valorParametro = valorParametro;
        this.descripcionParametro = descripcionParametro;
    }

    public void setTipoParametro( String tipoParametro ) {
        this.tipoParametro = tipoParametro;
    }

    public String getTipoParametro() {
        return tipoParametro;
    }

    public String toString() {
        return "ParametroBO{" + "id=" + id + ", claveParametro=" + claveParametro + ", valorParametro=" + valorParametro + ", descripcionParametro=" + descripcionParametro + ", tipoParametro=" + tipoParametro + "}";
    }
}
