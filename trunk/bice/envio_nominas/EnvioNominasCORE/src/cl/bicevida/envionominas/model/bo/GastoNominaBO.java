package cl.bicevida.envionominas.model.bo;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

public class GastoNominaBO implements Serializable {
    private Long Id;
    private NominaBO nomina;
    private BigDecimal comisionMismoBanco;
    private BigDecimal comisionOtrosBancos;
    private Long cantidadProcesados;
    private BigDecimal montoTotalProcesados;
    private Long cantidadRechazados;
    private BigDecimal montoTotalRechazados;
    private Long cantidadMismoBanco;
    private BigDecimal montoTotalMismoBanco;
    private Long cantidadOtrosBancos;
    private BigDecimal montoTotalOtrosBancos;
    private Date fechaInicioProceso;
    private Date fechaConciliacionProceso;
    private List<DetalleGastoBO> detalle;

    public void setNomina( NominaBO nomina ) {
        this.nomina = nomina;
    }

    public NominaBO getNomina() {
        return nomina;
    }

    public void setComisionMismoBanco( BigDecimal comisionMismoBanco ) {
        this.comisionMismoBanco = comisionMismoBanco;
    }

    public BigDecimal getComisionMismoBanco() {
        return comisionMismoBanco;
    }

    public void setComisionOtrosBancos( BigDecimal comisionOtrosBancos ) {
        this.comisionOtrosBancos = comisionOtrosBancos;
    }

    public BigDecimal getComisionOtrosBancos() {
        return comisionOtrosBancos;
    }

    public void setCantidadProcesados( Long cantidadProcesados ) {
        this.cantidadProcesados = cantidadProcesados;
    }

    public Long getCantidadProcesados() {
        return cantidadProcesados;
    }

    public void setMontoTotalProcesados( BigDecimal montoTotalProcesados ) {
        this.montoTotalProcesados = montoTotalProcesados;
    }

    public BigDecimal getMontoTotalProcesados() {
        return montoTotalProcesados;
    }

    public void setCantidadMismoBanco( Long cantidadMismoBanco ) {
        this.cantidadMismoBanco = cantidadMismoBanco;
    }

    public Long getCantidadMismoBanco() {
        return cantidadMismoBanco;
    }

    public void setMontoTotalMismoBanco( BigDecimal montoTotalMismoBanco ) {
        this.montoTotalMismoBanco = montoTotalMismoBanco;
    }

    public BigDecimal getMontoTotalMismoBanco() {
        return montoTotalMismoBanco;
    }

    public void setCantidadOtrosBancos( Long cantidadOtrosBancos ) {
        this.cantidadOtrosBancos = cantidadOtrosBancos;
    }

    public Long getCantidadOtrosBancos() {
        return cantidadOtrosBancos;
    }

    public void setMontoTotalOtrosBancos( BigDecimal montoTotalOtrosBancos ) {
        this.montoTotalOtrosBancos = montoTotalOtrosBancos;
    }

    public BigDecimal getMontoTotalOtrosBancos() {
        return montoTotalOtrosBancos;
    }

    public void setFechaInicioProceso( Date fechaInicioProceso ) {
        this.fechaInicioProceso = fechaInicioProceso;
    }

    public Date getFechaInicioProceso() {
        return fechaInicioProceso;
    }

    public void setFechaConciliacionProceso( Date fechaConciliacionProceso ) {
        this.fechaConciliacionProceso = fechaConciliacionProceso;
    }

    public Date getFechaConciliacionProceso() {
        return fechaConciliacionProceso;
    }

    public void setDetalle( List<DetalleGastoBO> detalle ) {
        this.detalle = detalle;
    }

    public List<DetalleGastoBO> getDetalle() {
        return detalle;
    }

    public BigDecimal getTotalComision() {
        return this.getTotalComisionMismoBanco().add( this.getTotalComisionOtrosBancos() );
    }

    public BigDecimal getTotalComisionMismoBanco() {
        return this.comisionMismoBanco.multiply( BigDecimal.valueOf( this.cantidadMismoBanco.longValue() ) );
    }

    public BigDecimal getTotalComisionOtrosBancos() {
        return this.comisionOtrosBancos.multiply( BigDecimal.valueOf( this.cantidadOtrosBancos.longValue() ) );
    }

    public void setId( Long id ) {
        this.Id = id;
    }

    public Long getId() {
        return Id;
    }

    public String toString() {
        return "GastoNominaBO{" + "Id=" + Id + ", nomina=" + nomina + ", comisionMismoBanco=" + comisionMismoBanco + ", comisionOtrosBancos=" + comisionOtrosBancos + ", cantidadProcesados=" + cantidadProcesados + ", montoTotalProcesados=" + montoTotalProcesados + ", cantidadMismoBanco=" + cantidadMismoBanco + ", montoTotalMismoBanco=" + montoTotalMismoBanco + ", cantidadOtrosBancos=" + cantidadOtrosBancos + ", montoTotalOtrosBancos=" + montoTotalOtrosBancos + ", fechaInicioProceso=" + fechaInicioProceso + ", fechaConciliacionProceso=" + fechaConciliacionProceso + ", detalle=" + detalle + "}";
    }

    public void setCantidadRechazados( Long cantidadRechazados ) {
        this.cantidadRechazados = cantidadRechazados;
    }

    public Long getCantidadRechazados() {
        return cantidadRechazados;
    }

    public void setMontoTotalRechazados( BigDecimal montoTotalRechazados ) {
        this.montoTotalRechazados = montoTotalRechazados;
    }

    public BigDecimal getMontoTotalRechazados() {
        return montoTotalRechazados;
    }
}
