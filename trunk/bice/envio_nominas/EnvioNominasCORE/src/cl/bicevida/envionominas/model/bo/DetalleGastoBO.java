package cl.bicevida.envionominas.model.bo;

import java.io.Serializable;

import java.math.BigDecimal;

public class DetalleGastoBO implements Serializable {
    private BancoBO banco;
    private Long cantidadProcesados;
    private BigDecimal montoTotalProcesados;
    private Long cantidadNoProcesados;
    private BigDecimal montoTotalNoProcesados;
    private BigDecimal totalComision;

    public void setBanco( BancoBO banco ) {
        this.banco = banco;
    }

    public BancoBO getBanco() {
        return banco;
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

    public void setCantidadNoProcesados( Long cantidadNoProcesados ) {
        this.cantidadNoProcesados = cantidadNoProcesados;
    }

    public Long getCantidadNoProcesados() {
        return cantidadNoProcesados;
    }

    public void setMontoTotalNoProcesados( BigDecimal montoTotalNoProcesados ) {
        this.montoTotalNoProcesados = montoTotalNoProcesados;
    }

    public BigDecimal getMontoTotalNoProcesados() {
        return montoTotalNoProcesados;
    }

    public void setTotalComision( BigDecimal totalComision ) {
        this.totalComision = totalComision;
    }

    public BigDecimal getTotalComision() {
        return totalComision;
    }

    public String toString() {
        return "DetalleGastoBO{" + "banco=" + banco + ", cantidadProcesados=" + cantidadProcesados + ", montoTotalProcesados=" + montoTotalProcesados + ", cantidadNoProcesados=" + cantidadNoProcesados + ", montoTotalNoProcesados=" + montoTotalNoProcesados + ", totalComision=" + totalComision + "}";
    }
}
