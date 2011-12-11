package cl.bicevida.envionominas.model.bo;

import java.io.Serializable;

import java.sql.Date;

public class ArchivoBO implements Serializable {
    private Long id;
    private String idTipo;
    private String nombre;
    private BancoProcesoBO bancoProceso;
    private String cuerpoArchivo;
    private Date fechaCreacion;
    private Long idEstado;

    public ArchivoBO() {
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

    public void setBancoProceso( BancoProcesoBO bancoProceso ) {
        this.bancoProceso = bancoProceso;
    }

    public BancoProcesoBO getBancoProceso() {
        return bancoProceso;
    }

    public void setCuerpoArchivo( String cuerpoArchivo ) {
        this.cuerpoArchivo = cuerpoArchivo;
    }

    public String getCuerpoArchivo() {
        return cuerpoArchivo;
    }

    public void setFechaCreacion( Date fechaCreacion ) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public String toString() {
        return "ArchivoBO {" + "id=" + id + ", nombre=" + nombre + ", bancoProceso=" + bancoProceso + ", cuerpoArchivo=" + cuerpoArchivo + ", fechaCreacion=" + fechaCreacion + "}";
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdTipo(String idTipo) {
        this.idTipo = idTipo;
    }

    public String getIdTipo() {
        return idTipo;
    }
}
