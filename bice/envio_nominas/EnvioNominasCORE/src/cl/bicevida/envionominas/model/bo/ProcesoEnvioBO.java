package cl.bicevida.envionominas.model.bo;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProcesoEnvioBO implements Serializable {
    private Long id;
    private Date fechaCreacion;
    private Date fechaEnvio;
    private String folioProcesoExterno;
    private EstadoProcesoBO estado;
    private BancoProcesoBO bancoProceso;
    private NominaBO nomina;
    private Date fechaEstado;
    private String observaciones;
    private List<RegistroNominaBO> registros;
    private List<GastoNominaBO> gastos;

    public Long getId() {
        return id;
    }

    public void setId( Long newId ) {
        id = newId;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion( Date newFechaCreacion ) {
        fechaCreacion = newFechaCreacion;
    }

    public String getFolioProcesoExterno() {
        return folioProcesoExterno;
    }

    public void setFolioProcesoExterno( String newFolioProcesoExterno ) {
        folioProcesoExterno = newFolioProcesoExterno;
    }

    public EstadoProcesoBO getEstado() {
        return estado;
    }

    public void setEstado( EstadoProcesoBO newEstado ) {
        estado = newEstado;
    }

    public BancoProcesoBO getBancoProceso() {
        return bancoProceso;
    }

    public void setBancoProceso( BancoProcesoBO newBancoProceso ) {
        bancoProceso = newBancoProceso;
    }

    public NominaBO getNomina() {
        return nomina;
    }

    public void setNomina( NominaBO newNomina ) {
        nomina = newNomina;
    }

    public List<RegistroNominaBO> getRegistros() {
        if ( registros == null ) {
            registros = new ArrayList<RegistroNominaBO>();
        }
        return registros;
    }

    public void setRegistros( List<RegistroNominaBO> newRegistros ) {
        registros = newRegistros;
    }

    public List<GastoNominaBO> getGastos() {
        return gastos;
    }

    public void setGastos( List<GastoNominaBO> newGastos ) {
        gastos = newGastos;
    }

    public void addRegistro( RegistroNominaBO registro ) {
        if ( this.registros == null ) {
            this.registros = new ArrayList<RegistroNominaBO>();
        }
        registro.setProceso( this );
        this.registros.add( registro );
    }

    public static ProcesoEnvioBO crear( Date fechaCreacion, BancoProcesoBO bancoProceso ) {
        return new ProcesoEnvioBO( fechaCreacion, bancoProceso );
    }

    public ProcesoEnvioBO( Date fechaCreacion, BancoProcesoBO bancoProceso ) {
        this.fechaCreacion = fechaCreacion;
        this.bancoProceso = bancoProceso;
    }

    public ProcesoEnvioBO() {
    }

    public void setFechaEnvio( Date fechaEnvio ) {
        this.fechaEnvio = fechaEnvio;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEstado( Date fechaEstado ) {
        this.fechaEstado = fechaEstado;
    }

    public Date getFechaEstado() {
        return fechaEstado;
    }

    public void setObservaciones( String observaciones ) {
        this.observaciones = observaciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public String toString() {
        return "ProcesoEnvioBO{" + "id=" + id + ", fechaCreacion=" + fechaCreacion + ", fechaEnvio=" + fechaEnvio + ", folioProcesoExterno=" + folioProcesoExterno + ", estado=" + estado + ", bancoProceso=" + bancoProceso + ", nomina=" + nomina + ", fechaEstado=" + fechaEstado + ", observaciones=" + observaciones + ", registros=" + registros + ", gastos=" + gastos + "}";
    }
}
