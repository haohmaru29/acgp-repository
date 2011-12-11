package cl.bicevida.envionominas.model.bo;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NominaBO implements Serializable {
    private Long id;
    private String lote;
    private TipoNominaBO tipo;
    private EstadoNominaBO estado;
    private Date fechaInicioProceso;
    private Date fechaEnvio;
    private Date fechaPago;
    private OrigenBO origen;
    private String bancoProceso;
    private Long totalRegistros;
    private BigDecimal montoTotal;
    private List<ProcesoEnvioBO> procesos;
    private List<RegistroNominaBO> registros;
    private Date fechaConciliacion;
    private TipoPagoBO tipoPago;

    public Long getId() {
        return id;
    }

    public void setId( Long newId ) {
        id = newId;
    }

    public String getLote() {
        return lote;
    }

    public BancoProcesoBO getBancoProcesoNomina() {
        BancoProcesoBO bancoProceso = null;
        if ( procesos.size() > 0 ) {
            bancoProceso = procesos.get( 0 ).getBancoProceso();
        }
        return bancoProceso;
    }

    public void setLote( String newLote ) {
        lote = newLote;
    }

    public TipoNominaBO getTipo() {
        return tipo;
    }

    public void setTipo( TipoNominaBO newTipo ) {
        tipo = newTipo;
    }

    public EstadoNominaBO getEstado() {
        return estado;
    }

    public void setEstado( EstadoNominaBO newEstado ) {
        estado = newEstado;
    }

    public Date getFechaInicioProceso() {
        return fechaInicioProceso;
    }

    public void setFechaInicioProceso( Date newFechaInicioProceso ) {
        fechaInicioProceso = newFechaInicioProceso;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago( Date newFechaPago ) {
        fechaPago = newFechaPago;
    }

    public OrigenBO getOrigen() {
        return origen;
    }

    public void setOrigen( OrigenBO newOrigen ) {
        origen = newOrigen;
    }

    public List<ProcesoEnvioBO> getProcesos() {
        return procesos;
    }

    public void setProcesos( List<ProcesoEnvioBO> newProcesos ) {
        procesos = newProcesos;
    }

    public void addProceso( ProcesoEnvioBO proceso ) {
        if ( this.procesos == null ) {
            this.procesos = new ArrayList<ProcesoEnvioBO>();
        }
        this.procesos.add( proceso );
    }

    public List<RegistroNominaBO> getRegistros() {
        if ( this.registros == null ) {
            this.registros = new ArrayList<RegistroNominaBO>();
        }
        return this.registros;
    }

    public void setRegistros( List<RegistroNominaBO> registros ) {
        this.registros = registros;
    }

    public static NominaBO crear( Long id, TipoNominaBO tipo, EstadoNominaBO estado ) {
        return new NominaBO( id, tipo, estado );
    }
    
    public static NominaBO crear( Long id, String lote, OrigenBO origen, TipoNominaBO tipo, EstadoNominaBO estado ) {
        return new NominaBO( id, lote, origen, tipo, estado );
    }

    public static NominaBO crear( Long id, TipoNominaBO tipo, String bancoProceso, Date fechaInicioProceso, Date fechaConciliacion ) {
        return new NominaBO( id, tipo, bancoProceso, fechaInicioProceso, fechaConciliacion );
    }

    public static NominaBO crear( Long _idNomina ) {
        return new NominaBO( _idNomina );
    }

    public NominaBO( Long _id ) {
        this.id = _id;
    }

    public NominaBO( Long id, TipoNominaBO tipo, String bancoProceso, Date fechaInicioProceso, Date fechaConciliacion ) {
        this.id = id;
        this.tipo = tipo;
        this.bancoProceso = bancoProceso;
        this.fechaInicioProceso = fechaInicioProceso;
        this.fechaConciliacion = fechaConciliacion;
    }

    public NominaBO( Long id, TipoNominaBO tipo, EstadoNominaBO estado ) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
    }
    
    public NominaBO( Long id, String lote, OrigenBO origen, TipoNominaBO tipo, EstadoNominaBO estado ) {
        this.id = id;
        this.lote = lote;
        this.origen = origen;
        this.tipo = tipo;
        this.estado = estado;
    }

    public NominaBO() {
    }

    public void setBancoProceso( String bancoProceso ) {
        this.bancoProceso = bancoProceso;
    }

    public String getBancoProceso() {
        return bancoProceso;
    }

    public void setTotalRegistros( Long totalRegistros ) {
        this.totalRegistros = totalRegistros;
    }

    public Long getTotalRegistros() {
        if ( totalRegistros == null || totalRegistros.longValue() == 0 ) {
            long total = 0;
            for ( ProcesoEnvioBO proceso: getProcesos() ) {
                for ( RegistroNominaBO registro: proceso.getRegistros() ) {
                    total = total + registro.getMonto().longValue();
                }
            }
            this.totalRegistros = Long.valueOf( total );
        }
        return this.totalRegistros;
    }

    public void setMontoTotal( BigDecimal montoTotal ) {
        this.montoTotal = montoTotal;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setFechaEnvio( Date fechaEnvio ) {
        this.fechaEnvio = fechaEnvio;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaConciliacion( Date fechaConciliacion ) {
        this.fechaConciliacion = fechaConciliacion;
    }

    public Date getFechaConciliacion() {
        return fechaConciliacion;
    }

    public Long get_totalRegistros() {
        return totalRegistros;
    }

    public List<RegistroNominaBO> get_registros() {
        return registros;
    }

    public void setTipoPago( TipoPagoBO tipoPago ) {
        this.tipoPago = tipoPago;
    }

    public TipoPagoBO getTipoPago() {
        return tipoPago;
    }

    public List<ProcesoEnvioBO> getProcesosEnviados() {
        List<ProcesoEnvioBO> procesosEnviados = new ArrayList<ProcesoEnvioBO>();
        if ( procesos != null ) {
            for ( ProcesoEnvioBO proceso: procesos ) {
                if ( proceso.getFechaEnvio() != null && proceso.getFolioProcesoExterno() != null ) {
                    procesosEnviados.add( proceso );
                }
            }
        }
        return procesosEnviados;
    }

    public String toString() {
        return "NominaBO{" + "id=" + id + ", lote=" + lote + ", tipo=" + tipo + ", estado=" + estado + ", fechaInicioProceso=" + fechaInicioProceso + ", fechaEnvio=" + fechaEnvio + ", fechaPago=" + fechaPago + ", origen=" + origen + ", bancoProceso=" + bancoProceso + ", totalRegistros=" + totalRegistros + ", montoTotal=" + montoTotal + ", procesos=" + procesos + ", registros=" + registros + ", fechaConciliacion=" + fechaConciliacion + ", tipoPago=" + tipoPago + "}";
    }
}
