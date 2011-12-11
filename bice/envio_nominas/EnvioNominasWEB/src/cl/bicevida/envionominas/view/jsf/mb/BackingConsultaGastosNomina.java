package cl.bicevida.envionominas.view.jsf.mb;

import cl.bicevida.core.view.jsf.mb.MbError;
import cl.bicevida.core.view.jsf.mb.MbUserInfo;
import cl.bicevida.core.view.jsf.utils.JsfUtils;
import cl.bicevida.core.view.utils.BvWebLog;
import cl.bicevida.envionominas.model.bo.GastoNominaBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import javax.faces.validator.ValidatorException;

import oracle.adf.view.faces.component.core.data.CoreTable;
import oracle.adf.view.faces.component.core.data.CoreTableSelectOne;
import oracle.adf.view.faces.component.core.input.CoreSelectInputDate;
import oracle.adf.view.faces.event.SelectionEvent;

/**
 * 
 */
public class BackingConsultaGastosNomina extends AbstractBacking {
    private MbUserInfo mbUserInfo;
    public static final String MENSAJE_RANGO_ERROR = "Rango de fechas incorrecto, verificar";
    public static final String MENSAJE_RANGO_FALTANTE = "Se debe completar el rango, o no se debe especificar ninguna fecha ";
    private Boolean validada = Boolean.FALSE;
    private List<GastoNominaBO> lista;
    private CoreTableSelectOne tsoSelectorNominas;
    private CoreTable tabla;
    private List<SelectItem> tiposNomina;
    private List<SelectItem> bancosPago;
    private List<SelectItem> tiposCuenta;
    private List<SelectItem> bancosProceso;
    private Date fechaDesde;
    private Date fechaHasta;
    private String loteNomina;
    private String tipoNominaSeleccionada;
    private String bancoPagoSeleccionado;
    private String bancoProcesoSeleccionado;
    private GastoNominaBO gastoSeleccionado;
    private Long registrosPagina;

    public BackingConsultaGastosNomina() {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "BackingConsultaGastosNomina()" );
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
            bancosProceso = getItemsBancosProceso();
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingConsultaGastosNomina.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
    }
    //EVENTOS

    public void ejecutarBusqueda( ActionEvent actionEvent ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "ejecutarBusqueda([" + actionEvent + "])" );
        Long bancoPago = null;
        String loteNomina = null;
        Long bancoProceso = null;
        Long tipoNomina = null;
        if ( getBancoPagoSeleccionado() != null ) {
            bancoPago = Long.valueOf( getBancoPagoSeleccionado() );
        }
        if ( getLoteNomina() != null && !"".equals(getLoteNomina().trim())) {
            loteNomina = getLoteNomina();
        }
        if ( getBancoProcesoSeleccionado() != null ) {
            bancoProceso = Long.valueOf( getBancoProcesoSeleccionado() );
        }
        if ( getTipoNominaSeleccionada() != null ) {
            tipoNomina = Long.valueOf( getTipoNominaSeleccionada() );
        }
        try {
            lista = getFacade().obtenerGastosNominas( bancoPago, getFechaDesde(), getFechaHasta(), loteNomina, bancoProceso, tipoNomina );
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingConsultaGastosNomina.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "lista:[" + lista + "]" );
    }

    public void limpiarFormulario( ) {
        if ( tipoNominaSeleccionada != null ) {
            tipoNominaSeleccionada = null;
        }
        if ( bancoPagoSeleccionado != null ) {
            bancoPagoSeleccionado = null;
        }
        if ( fechaDesde != null ) {
            fechaDesde = null;
        }
        if ( loteNomina != null && !"".equals(loteNomina.trim()) ){
            loteNomina = null;
        }
        if ( bancoProcesoSeleccionado != null ) {
            bancoProcesoSeleccionado = null;
        }
        if ( fechaHasta != null ) {
            fechaHasta = null;
        }
        lista=null;
        cargarPagina();
    }

    public String onClickVerDetalle() {
        CoreTable tabla = ( CoreTable ) JsfUtils.findComponent( "tblNominas" );
        List<GastoNominaBO> listaLocal = ( List<GastoNominaBO> ) JsfUtils.getObjetosSeleccionados( tabla );
        if ( listaLocal != null && listaLocal.size() == 1 ) {
            gastoSeleccionado = listaLocal.get( 0 );
        }
        if ( gastoSeleccionado != null ) {
            try {
                gastoSeleccionado.setDetalle( getFacade().obtenerDetalleGastoNomina( gastoSeleccionado.getNomina().getId() ) );
            } catch ( Exception e ) {
                MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
                mbError.showError( BackingMantenedorBancosProceso.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, "mantenedorBancoProceso", "bienvenida" );
            }
            return ( "detalleGastosNomina" );
        }
        return null;
    }

    /**
     * 
     * @param selectionEvent
     */
    public void makeCurrent( SelectionEvent selectionEvent ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "makeCurrent([" + selectionEvent + "])" );
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "selectionEvent.getSelectedKeys():[" + selectionEvent.getSelectedKeys().getSize() + "]" );
        Iterator iterador = selectionEvent.getSelectedKeys().getKeySet().iterator();
        Integer tableIndex = ( Integer ) tabla.getSelectionState().getKeySet().iterator().next();
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "tableIndex:[" + tableIndex + "]" );
        while ( iterador.hasNext() ) {
            Object obj = iterador.next();
            BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "obj:[" + obj + "]" );
        }
        for ( Object obj: selectionEvent.getSelectedKeys().getKeySet() ) {
            BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "obj:[" + obj + "]" );
        }
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
                    // cuando las 2 son nulas no se toma en cuenta poara la busqueda
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

    public List<GastoNominaBO> getLista() {
        return this.lista;
    }

    public void setListaNominasGasto( List<GastoNominaBO> lista ) {
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

    public List<SelectItem> getTiposCuenta() {
        return this.tiposCuenta;
    }

    public void setTiposCuenta( List<SelectItem> tiposCuenta ) {
        this.tiposCuenta = tiposCuenta;
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

    public void setLoteNomina( String loteNomina ) {
        this.loteNomina = loteNomina;
    }

    public CoreTableSelectOne getTsoSelectorNominas() {
        return this.tsoSelectorNominas;
    }

    public void setTsoSelectorNominas( CoreTableSelectOne tsoSelectorNominas ) {
        this.tsoSelectorNominas = tsoSelectorNominas;
    }

    public GastoNominaBO getGastoSeleccionado() {
        return this.gastoSeleccionado;
    }

    public void setGastoSeleccionado( GastoNominaBO gastoSeleccionado ) {
        this.gastoSeleccionado = gastoSeleccionado;
    }

    public CoreTable getTabla() {
        return this.tabla;
    }

    public void setTabla( CoreTable tabla ) {
        this.tabla = tabla;
    }

    public void setRegistrosPagina( Long registrosPagina ) {
        this.registrosPagina = registrosPagina;
    }

    public Long getRegistrosPagina() {
        return registrosPagina;
    }

    public void setBancoProcesoSeleccionado(String bancoProcesoSeleccionado) {
        this.bancoProcesoSeleccionado = bancoProcesoSeleccionado;
    }

    public String getBancoProcesoSeleccionado() {
        return bancoProcesoSeleccionado;
    }

    public void setBancosProceso(List<SelectItem> bancosProceso) {
        this.bancosProceso = bancosProceso;
    }

    public List<SelectItem> getBancosProceso() {
        return bancosProceso;
    }
}
