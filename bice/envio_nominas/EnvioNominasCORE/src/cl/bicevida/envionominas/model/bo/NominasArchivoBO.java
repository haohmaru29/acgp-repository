package cl.bicevida.envionominas.model.bo;

import java.io.Serializable;

public class NominasArchivoBO implements Serializable {
    
    private String idEstado;
    private String nombreArchivo;
    private String rutTitular;
    private String numCuenta;
    private String codBanco;
    private String movMonto;
    private String codTipoCuenta;
    private String codEstadoRen;
    private String glosaEstadoCargo;

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setRutTitular(String rutTitular) {
        this.rutTitular = rutTitular;
    }

    public String getRutTitular() {
        return rutTitular;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setCodBanco(String codBanco) {
        this.codBanco = codBanco;
    }

    public String getCodBanco() {
        return codBanco;
    }

    public void setMovMonto(String movMonto) {
        this.movMonto = movMonto;
    }

    public String getMovMonto() {
        return movMonto;
    }

    public void setCodTipoCuenta(String codTipoCuenta) {
        this.codTipoCuenta = codTipoCuenta;
    }

    public String getCodTipoCuenta() {
        return codTipoCuenta;
    }

    public void setCodEstadoRen(String codEstadoRen) {
        this.codEstadoRen = codEstadoRen;
    }

    public String getCodEstadoRen() {
        return codEstadoRen;
    }

    public void setGlosaEstadoCargo(String glosaEstadoCargo) {
        this.glosaEstadoCargo = glosaEstadoCargo;
    }

    public String getGlosaEstadoCargo() {
        return glosaEstadoCargo;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getIdEstado() {
        return idEstado;
    }
}
