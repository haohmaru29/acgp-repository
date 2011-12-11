package cl.bicevida.envionominas.view.jsf.mb;

import cl.bicevida.core.view.jsf.mb.MbError;
import cl.bicevida.core.view.jsf.mb.MbUserInfo;
import cl.bicevida.core.view.jsf.utils.JsfUtils;
import cl.bicevida.core.view.utils.BvWebLog;
import cl.bicevida.envionominas.model.bo.RegistroNominaBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import javax.faces.validator.ValidatorException;

import oracle.adf.view.faces.component.core.data.CoreTable;
import oracle.adf.view.faces.component.core.input.CoreSelectInputDate;

/**
 * Managed Bean para el control de los objetos que participan en la pantalla de
 * consulta de nominas / transacciones por estado.
 * 
 */
public class BackingConsultaNominasEstado extends AbstractBacking {
    private MbUserInfo mbUserInfo;
    public static final String MENSAJE_RANGO_ERROR = "Rango de fechas incorrecto, verificar";
    public static final String MENSAJE_RANGO_FALTANTE = "Se debe completar el rango, o no se debe especificar ninguna fecha ";
    private Boolean validada = Boolean.FALSE;
    private List<RegistroNominaBO> lista;
    private CoreTable tblNominas;
    private List<SelectItem> tiposNomina;
    private List<SelectItem> bancosPago;
    private List<SelectItem> estadosTransaccion;
    private List<SelectItem> bancosProceso;
    private List<SelectItem> estadosNomina;
    private Date fechaDesde;
    private Date fechaHasta;
    private String loteNomina;
    private String rutTitular;
    private String tipoNominaSeleccionada;
    private String bancoPagoSeleccionado;
    private String estadoTransaccionSeleccionado;
    private String bancoProcesoSeleccionado;
    private String estadoNominaSeleccionado;
    private Long registrosPagina;

    /**
     * 
     */
    public BackingConsultaNominasEstado() {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "BackingConsultaNominasEstado()" );
        mbUserInfo = ( MbUserInfo ) JsfUtils.getValue( "#{MbUserInfo}" );
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "USUARIO:" + mbUserInfo.getUserName() + "/RUT:" + mbUserInfo.getRut() + "/PERFIL:" + ( mbUserInfo.isUserInRole( "BICEVIDANET" ) ? "BICEVIDANET" : mbUserInfo.isUserInRole( "INTRANET" ) ? "INTRANET" : mbUserInfo.isUserInRole( "BICEVIDA" ) ? "BICEVIDA" : "ROL NO VALIDO" ) );
        EnvioNominasConfig parametros = new EnvioNominasConfig();
        registrosPagina = Long.valueOf( parametros.get( EnvioNominasConfig.REGISTROS_PAGINA ) );
        cargarPagina();
    }

    private void cargarPagina() {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "cargarPagina()" );
        try {
            tiposNomina = getItemsTiposNomina();
            bancosPago = getItemsBancosPago();
            estadosTransaccion = getItemsEstadosTransaccion();
            bancosProceso = getItemsBancosProceso();
            estadosNomina = getItemsEstadosNomina();
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingConsultaNominasEstado.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
    }
    //EVENTOS

    public void ejecutarBusqueda( ActionEvent actionEvent ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "ejecutarBusqueda([" + actionEvent + "])" );
        Long bancoPago = null;
        Long estadoNomina = null;
        Long estadoTransaccion = null;
        String loteNomina = null;
        Long bancoProceso = null;
        Long tipoNomina = null;
        if ( getBancoPagoSeleccionado() != null ) {
            bancoPago = Long.valueOf( getBancoPagoSeleccionado() );
        }
        if ( getEstadoNominaSeleccionado() != null ) {
            estadoNomina = Long.valueOf( getEstadoNominaSeleccionado() );
        }
        if ( getEstadoTransaccionSeleccionado() != null ) {
            estadoTransaccion = Long.valueOf( getEstadoTransaccionSeleccionado() );
        }
        if ( getLoteNomina() != null && !getLoteNomina().trim().equals("") ) {
            loteNomina = getLoteNomina();
        }
        if ( getBancoProcesoSeleccionado() != null ) {
            bancoProceso = Long.valueOf( getBancoProcesoSeleccionado() );
        }
        if ( getTipoNominaSeleccionada() != null ) {
            tipoNomina = Long.valueOf( getTipoNominaSeleccionada() );
        }
        try {
            lista = getFacade().consultaEstadoTransaccionesNomina( bancoPago, estadoNomina, estadoTransaccion, getFechaDesde(), getFechaHasta(), loteNomina, getRutTitular(), bancoProceso, tipoNomina );
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingConsultaNominasEstado.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "lista:[" + lista.size() + "]" );
    }

    public void limpiarFormulario( ActionEvent actionEvent ) {
        if ( tipoNominaSeleccionada != null ) {
            tipoNominaSeleccionada = null;
        }
        if ( bancoPagoSeleccionado != null ) {
            bancoPagoSeleccionado = null;
        }
        if ( fechaDesde != null ) {
            fechaDesde = null;
        }
        if ( rutTitular != null ) {
            rutTitular = null;
        }
        if ( loteNomina != null ) {
            loteNomina = null;
        }
        if ( bancoProcesoSeleccionado != null ) {
            bancoProcesoSeleccionado = null;
        }
        if ( fechaHasta != null ) {
            fechaHasta = null;
        }
        if ( estadoTransaccionSeleccionado != null ) {
            estadoTransaccionSeleccionado = null;
        }
        if ( estadoNominaSeleccionado != null ) {
            estadoNominaSeleccionado = null;
        }
        cargarPagina();
    }
    
    public void limpiarFormulario() {
        if ( tipoNominaSeleccionada != null ) {
            tipoNominaSeleccionada = null;
        }
        if ( bancoPagoSeleccionado != null ) {
            bancoPagoSeleccionado = null;
        }
        if ( fechaDesde != null ) {
            fechaDesde = null;
        }
        if ( rutTitular != null ) {
            rutTitular = null;
        }
        if ( loteNomina != null ) {
            loteNomina = null;
        }
        if ( bancoProcesoSeleccionado != null ) {
            bancoProcesoSeleccionado = null;
        }
        if ( fechaHasta != null ) {
            fechaHasta = null;
        }
        if ( estadoTransaccionSeleccionado != null ) {
            estadoTransaccionSeleccionado = null;
        }
        if ( estadoNominaSeleccionado != null ) {
            estadoNominaSeleccionado = null;
        }
        
        tblNominas = null;
        lista = null;
        cargarPagina();
    }

    public void validarFechas( FacesContext facesContext, UIComponent uiComponent, Object object ) {
        FacesMessage fv;
        Calendar calFechaDesde = Calendar.getInstance();
        Calendar calFechaHasta = Calendar.getInstance();
        CoreSelectInputDate fechaDesde = ( CoreSelectInputDate ) JsfUtils.findComponent( "sitFechaDesde" );
        CoreSelectInputDate fechaHasta = ( CoreSelectInputDate ) JsfUtils.findComponent( "sitFechaHasta" );
        String valorFechaDesde = ( String ) fechaDesde.getSubmittedValue();
        String valorFechaHasta = ( String ) fechaHasta.getSubmittedValue();
        if ( !validada ) {
            if ( valorFechaDesde != null && !valorFechaDesde.equals( "" ) && valorFechaHasta != null && !valorFechaHasta.equals( "" ) ) {
                String[] arrayFechaDesde = valorFechaDesde.split( "-" );
                calFechaDesde.set( Integer.valueOf( arrayFechaDesde[ 2 ] ).intValue(), Integer.valueOf( arrayFechaDesde[ 1 ] ).intValue(), Integer.valueOf( arrayFechaDesde[ 0 ] ).intValue() );
                String[] arrayFechaHasta = valorFechaHasta.split( "-" );
                calFechaHasta.set( Integer.valueOf( arrayFechaHasta[ 2 ] ).intValue(), Integer.valueOf( arrayFechaHasta[ 1 ] ).intValue(), Integer.valueOf( arrayFechaHasta[ 0 ] ).intValue() );
                if ( calFechaDesde.compareTo( calFechaHasta ) > 0 ) {
                    fv = new FacesMessage( MENSAJE_RANGO_ERROR );
                    throw new ValidatorException( fv );
                    // la fecha desde no puede ser mayor que hasta     
                }
            } else {
                if ( valorFechaDesde == null && valorFechaHasta == null ) {
                    // cuando las 2 son nulas no se toma en cuenta para la busqueda
                } else {
                    // cuando una es nula y la otra no
                    this.fechaDesde = null;
                    this.fechaHasta = null;
                    fv = new FacesMessage( MENSAJE_RANGO_FALTANTE );
                    throw new ValidatorException( fv );
                }
            }
            validada = Boolean.TRUE;
        } else {
            validada = Boolean.FALSE;
        }
    }
    //ACCESORES    

    public List<RegistroNominaBO> getLista() {
        return this.lista;
    }

    public void setLista( List<RegistroNominaBO> lista ) {
        this.lista = lista;
    }

    public List<SelectItem> getTiposNomina() {
        return this.tiposNomina;
    }

    public void setTiposNomina( List<SelectItem> tiposNomina ) {
        this.tiposNomina = tiposNomina;
    }

    public List<SelectItem> getBancosPago() {
        return this.bancosPago;
    }

    public void setBancosPago( List<SelectItem> bancosPago ) {
        this.bancosPago = bancosPago;
    }

    public List<SelectItem> getEstadosTransaccion() {
        return this.estadosTransaccion;
    }

    public void setEstadosTransaccion( List<SelectItem> estadosTransaccion ) {
        this.estadosTransaccion = estadosTransaccion;
    }

    public List<SelectItem> getEstadosNomina() {
        return this.estadosNomina;
    }

    public void setEstadosNomina( List<SelectItem> estadosNomina ) {
        this.estadosNomina = estadosNomina;
    }

    public String getTipoNominaSeleccionada() {
        return this.tipoNominaSeleccionada;
    }

    public void setTipoNominaSeleccionada( String tipoNominaSeleccionada ) {
        this.tipoNominaSeleccionada = tipoNominaSeleccionada;
    }

    public String getBancoPagoSeleccionado() {
        return this.bancoPagoSeleccionado;
    }

    public void setBancoPagoSeleccionado( String bancoPagoSeleccionado ) {
        this.bancoPagoSeleccionado = bancoPagoSeleccionado;
    }

    public String getEstadoTransaccionSeleccionado() {
        return this.estadoTransaccionSeleccionado;
    }

    public void setEstadoTransaccionSeleccionado( String estadoTransaccionSeleccionado ) {
        this.estadoTransaccionSeleccionado = estadoTransaccionSeleccionado;
    }

    public String getEstadoNominaSeleccionado() {
        return this.estadoNominaSeleccionado;
    }

    public void setEstadoNominaSeleccionado( String estadoNominaSeleccionado ) {
        this.estadoNominaSeleccionado = estadoNominaSeleccionado;
    }

    public Date getFechaDesde() {
        return this.fechaDesde;
    }

    public void setFechaDesde( Date fechaDesde ) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return this.fechaHasta;
    }

    public void setFechaHasta( Date fechaHasta ) {
        this.fechaHasta = fechaHasta;
    }

    public String getLoteNomina() {
        return this.loteNomina;
    }

    public void setLoteNomina( String numeroNomina ) {
        this.loteNomina = numeroNomina;
    }

    public String getRutTitular() {
        return rutTitular;
    }

    public void setRutTitular( String rutTitular ) {
        this.rutTitular = rutTitular;
    }

    public CoreTable getTblNominas() {
        return this.tblNominas;
    }

    public void setTblNominas( CoreTable tblNominas ) {
        this.tblNominas = tblNominas;
    }

    public void setRegistrosPagina( Long registrosPagina ) {
        this.registrosPagina = registrosPagina;
    }

    public Long getRegistrosPagina() {
        return registrosPagina;
    }

    public void setBancosProceso(List<SelectItem> bancosProceso) {
        this.bancosProceso = bancosProceso;
    }

    public List<SelectItem> getBancosProceso() {
        return bancosProceso;
    }

    public void setBancoProcesoSeleccionado(String bancoProcesoSeleccionado) {
        this.bancoProcesoSeleccionado = bancoProcesoSeleccionado;
    }

    public String getBancoProcesoSeleccionado() {
        return bancoProcesoSeleccionado;
    }
}
