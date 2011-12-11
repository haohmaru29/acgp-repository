package cl.bicevida.envionominas.model.bo;

import java.io.Serializable;

public class BancoBO implements Serializable {
    private Long id;
    private String codigo;
    private String codigoMatriz;
    private String nombre;

    public BancoBO() {
    }

    public BancoBO( Long idBanco ) {
        this.id = idBanco;
    }

    public BancoBO( Long id, String codigo, String codigoMatriz, String nombre ) {
        this.id = id;
        this.codigo = codigo;
        this.codigoMatriz = codigoMatriz;
        this.nombre = nombre;
    }

    public static BancoBO crear( Long id, String codigo, String codigoMatriz, String nombre ) {
        return new BancoBO( id, codigo, codigoMatriz, nombre );
    }

    public static BancoBO crear( Long idBanco ) {
        return new BancoBO( idBanco );
    }

    public Long getId() {
        return id;
    }

    public void setId( Long newId ) {
        id = newId;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo( String newCodigo ) {
        codigo = newCodigo;
    }

    public String getCodigoMatriz() {
        return codigoMatriz;
    }

    public void setCodigoMatriz( String newCodigoMatriz ) {
        codigoMatriz = newCodigoMatriz;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre( String newNombre ) {
        nombre = newNombre;
    }

    public static BancoBO crear( String nombre ) {
        return new BancoBO( nombre );
    }

    public BancoBO( String nombre ) {
        this.nombre = nombre;
    }

    public String toString() {
        return "BancoBO {" + "id=" + id + ", codigo=" + codigo + ", codigoMatriz=" + codigoMatriz + ", nombre=" + nombre + "}";
    }
}
