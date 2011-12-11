package cl.bicevida.envionominas.model.bo;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;

public class ComisionBO implements Serializable {
    private Long id;
    private BigDecimal montoMismoBanco;
    private BigDecimal montoOtrosBancos;
    private Date fechaInicioVigencia;
    private Date fechaTerminoVigencia;
    private BancoProcesoBO bancoProceso;

    public Long getId() {
        return id;
    }

    public void setId( Long newId ) {
        id = newId;
    }

    public BigDecimal getMontoMismoBanco() {
        return montoMismoBanco;
    }

    public void setMontoMismoBanco( BigDecimal newMontoMismoBanco ) {
        montoMismoBanco = newMontoMismoBanco;
    }

    public BigDecimal getMontoOtrosBancos() {
        return montoOtrosBancos;
    }

    public void setMontoOtrosBancos( BigDecimal newMontoOtrosBancos ) {
        montoOtrosBancos = newMontoOtrosBancos;
    }

    public Date getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public void setFechaInicioVigencia( Date newFechaInicioVigencia ) {
        fechaInicioVigencia = newFechaInicioVigencia;
    }

    public Date getFechaTerminoVigencia() {
        return fechaTerminoVigencia;
    }

    public void setFechaTerminoVigencia( Date newFechaTerminoVigencia ) {
        fechaTerminoVigencia = newFechaTerminoVigencia;
    }

    public BancoProcesoBO getBancoProceso() {
        return bancoProceso;
    }

    public void setBancoProceso( BancoProcesoBO newBancoProceso ) {
        bancoProceso = newBancoProceso;
    }

    public String toString() {
        return "ComisionBO{" + "id=" + id + ", montoMismoBanco=" + montoMismoBanco + ", montoOtrosBancos=" + montoOtrosBancos + ", fechaInicioVigencia=" + fechaInicioVigencia + ", fechaTerminoVigencia=" + fechaTerminoVigencia + ", bancoProceso=" + bancoProceso + "}";
    }
}
