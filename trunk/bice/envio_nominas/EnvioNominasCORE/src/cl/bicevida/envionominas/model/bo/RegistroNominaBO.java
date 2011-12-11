package cl.bicevida.envionominas.model.bo;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;

public class RegistroNominaBO implements Serializable {
    private Long id;
    private String nombre;
    private Integer rut;
    private String dv;
    private String cuenta;
    private String oficinaOrigen;
    private String oficinaDestino;
    private BigDecimal monto;
    private Date fechaEstado;
    private BancoBO banco;
    private EstadoTransaccionBO estado;
    private NominaBO nomina;
    private TipoCuentaBO tipoCuenta;
    private ProcesoEnvioBO proceso;

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

    public Integer getRut() {
        return rut;
    }

    public void setRut( Integer newRut ) {
        rut = newRut;
    }

    public String getDv() {
        return dv;
    }

    public void setDv( String newDv ) {
        dv = newDv;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta( String newCuenta ) {
        cuenta = newCuenta;
    }

    public String getOficinaOrigen() {
        return oficinaOrigen;
    }

    public void setOficinaOrigen( String newOficinaOrigen ) {
        oficinaOrigen = newOficinaOrigen;
    }

    public String getOficinaDestino() {
        return oficinaDestino;
    }

    public void setOficinaDestino( String newOficinaDestino ) {
        oficinaDestino = newOficinaDestino;
    }

    public Date getFechaEstado() {
        return fechaEstado;
    }

    public void setFechaEstado( Date newFechaEstado ) {
        fechaEstado = newFechaEstado;
    }

    public BancoBO getBanco() {
        return banco;
    }

    public void setBanco( BancoBO newBanco ) {
        banco = newBanco;
    }

    public EstadoTransaccionBO getEstado() {
        return estado;
    }

    public void setEstado( EstadoTransaccionBO newEstado ) {
        estado = newEstado;
    }

    public NominaBO getNomina() {
        return nomina;
    }

    public void setNomina( NominaBO newNomina ) {
        nomina = newNomina;
    }

    public TipoCuentaBO getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta( TipoCuentaBO newTipoCuenta ) {
        tipoCuenta = newTipoCuenta;
    }

    public ProcesoEnvioBO getProceso() {
        return proceso;
    }

    public void setProceso( ProcesoEnvioBO newProceso ) {
        proceso = newProceso;
    }

    public void setMonto( BigDecimal monto ) {
        this.monto = monto;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public String toString() {
        return "RegistroNominaBO{" + "id=" + id + ", nombre=" + nombre + ", rut=" + rut + ", dv=" + dv + ", cuenta=" + cuenta + ", oficinaOrigen=" + oficinaOrigen + ", oficinaDestino=" + oficinaDestino + ", monto=" + monto + ", fechaEstado=" + fechaEstado + ", banco=" + banco + ", estado=" + estado + ", nomina=" + nomina + ", tipoCuenta=" + tipoCuenta + ", proceso=" + proceso + "}";
    }
}
