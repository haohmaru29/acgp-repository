package cl.bicevida.envionominas.view.jsf.mb;

import cl.bicevida.core.view.jsf.mb.MbError;
import cl.bicevida.core.view.jsf.mb.MbUserInfo;
import cl.bicevida.core.view.jsf.utils.JsfUtils;
import cl.bicevida.core.view.utils.BvWebLog;
import cl.bicevida.envionominas.model.bo.NominaBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;

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
import oracle.adf.view.faces.component.core.data.CoreTableSelectOne;
import oracle.adf.view.faces.component.core.input.CoreSelectInputDate;

/**
 * 
 */
public class BackingRegistrarFolio extends AbstractBacking {
    private MbUserInfo mbUserInfo;
    public static final String MENSAJE_RANGO_ERROR = "Rango de fechas incorrecto, verificar";
    public static final String MENSAJE_RANGO_FALTANTE = "Se debe completar el rango, o no se debe especificar ninguna fecha ";
    private Boolean validada = Boolean.FALSE;
    private List<NominaBO> lista;
    private CoreTableSelectOne tsoSelectorNominas;
    private CoreTable tabla;
    private List<SelectItem> tiposNomina;
    private List<SelectItem> bancosProceso;
    private List<SelectItem> tiposCuenta;
    private Date fechaDesde;
    private Date fechaHasta;
    private String loteNomina;
    private String numeroNominaSeleccionado;
    private String numeroFolio;
    private String tipoNominaSeleccionada;
    private String bancoProcesoSeleccionado;
    private NominaBO nominaSeleccionada;
    private String bancoPagoMuestra;
    private String mensajeSalida;
    private Long registrosPagina;

    /**
     * Controles diinamicos de la pagina.
     */
    private Boolean showPfRegistrarFolio = Boolean.FALSE;
    private Boolean showBtnGuardarFolio = Boolean.TRUE;
    private Boolean showBtnContinuar = Boolean.FALSE;
    private Boolean enableInputRegistrarFolio = Boolean.FALSE;

    /**
     * 
     */
    public BackingRegistrarFolio() {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "BackingRegistrarFolio()" );
        mbUserInfo = ( MbUserInfo ) JsfUtils.getValue( "#{MbUserInfo}" );
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "USUARIO:" + mbUserInfo.getUserName() + "/RUT:" + mbUserInfo.getRut() + "/PERFIL:" + ( mbUserInfo.isUserInRole( "BICEVIDANET" ) ? "BICEVIDANET" : mbUserInfo.isUserInRole( "INTRANET" ) ? "INTRANET" : mbUserInfo.isUserInRole( "BICEVIDA" ) ? "BICEVIDA" : "ROL NO VALIDO" ) );
        EnvioNominasConfig parametros = new EnvioNominasConfig();
        registrosPagina = Long.valueOf( parametros.get( EnvioNominasConfig.REGISTROS_PAGINA ) );
        cargarPagina();
    }

    /**
     * 
     */
    private void cargarPagina() {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "cargarPagina()" );
        try {
            tiposNomina = getItemsTiposNomina();
            bancosProceso = getItemsBancosProceso();
            tiposCuenta = getItemsTiposCuenta();
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingRegistrarFolio.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        showPfRegistrarFolio = Boolean.FALSE;
    }
    //EVENTOS

    /**
     * 
     * @param actionEvent
     */
    public void ejecutarBusqueda( ActionEvent actionEvent ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "ejecutarBusqueda([" + actionEvent + "])" );
        Long bancoProceso = null;
        String loteNomina = null;
        Long tipoCuenta = null;
        Long tipoNomina = null;
        if ( getBancoProcesoSeleccionado() != null ) {
            bancoProceso = Long.valueOf(getBancoProcesoSeleccionado());
        }
        if ( getLoteNomina() != null ) {
            loteNomina = getLoteNomina();
        }
        if ( getTipoNominaSeleccionada() != null ) {
            tipoNomina = Long.valueOf( getTipoNominaSeleccionada() );
        }
        lista = getFacade().obtenerNominasSinFolio( getFechaDesde(), getFechaHasta(), loteNomina, tipoNomina, bancoProceso );
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "lista:[" + lista + "]" );
        showPfRegistrarFolio = Boolean.FALSE;
        limpiarRegistrarFolio();
    }

    /**
     * 
     * @param actionEvent
     */
    public void limpiarFormulario() {
        if ( tipoNominaSeleccionada != null ) {
            tipoNominaSeleccionada = null;
        }
        if ( bancoProcesoSeleccionado != null ) {
            bancoProcesoSeleccionado = null;
        }
        if ( fechaDesde != null ) {
            fechaDesde = null;
        }
        if ( loteNomina != null ) {
            loteNomina = null;
        }
        if ( fechaHasta != null ) {
            fechaHasta = null;
        }
        showPfRegistrarFolio = Boolean.FALSE;
        limpiarRegistrarFolio();
        lista = null;
        cargarPagina();
    }

    public String onClickRegistrarFolio() {
        CoreTable tabla = ( CoreTable ) JsfUtils.findComponent( "tblNominas" );
        List<NominaBO> lista = ( List<NominaBO> ) JsfUtils.getObjetosSeleccionados( tabla );
        if ( lista != null && lista.size() == 1 ) {
            nominaSeleccionada = lista.get( 0 );
            numeroNominaSeleccionado = String.valueOf( nominaSeleccionada.getId() );
            bancoPagoMuestra = String.valueOf( nominaSeleccionada.getBancoProceso() );
        }
        if ( nominaSeleccionada != null ) {
            showPfRegistrarFolio = Boolean.TRUE;
            showBtnGuardarFolio = Boolean.TRUE;
            showBtnContinuar = Boolean.FALSE;
            enableInputRegistrarFolio = Boolean.FALSE;
        }
        return null;
    }

    public void ejecutarGuardarFolio( ActionEvent actionEvent ) {
        String numeroFolioIngresado;
        Long numeroNominaIngreso;
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "ejecutarGuardarFolio([" + actionEvent + "])" );
        mensajeSalida = null;
        bancoPagoMuestra = getBancoPagoMuestra();
        if ( getNumeroNominaSeleccionado() != null ) {
            numeroNominaIngreso = Long.valueOf( getNumeroNominaSeleccionado() );
            if ( getNumeroFolio() != null ) {
                numeroFolioIngresado = getNumeroFolio();
                getFacade().registrarFolio( numeroNominaIngreso, numeroFolioIngresado );
                enableInputRegistrarFolio = Boolean.TRUE;
                showBtnGuardarFolio = Boolean.FALSE;
                showBtnContinuar = Boolean.TRUE;
            }
        }
    }

    public void ejecutarContinuar( ActionEvent actionEvent ) {
        mensajeSalida = null;
        ejecutarBusquedaIngreso();
    }

    public void ejecutarBusquedaIngreso() {
        Long bancoProceso = null;
        String loteNomina = null;
        Long tipoCuenta = null;
        Long tipoNomina = null;
        if ( getBancoProcesoSeleccionado() != null ) {
            bancoProceso = Long.valueOf( getBancoProcesoSeleccionado() );
        }
        if ( getLoteNomina() != null ) {
            loteNomina = getLoteNomina();
        }
        if ( getTipoNominaSeleccionada() != null ) {
            tipoNomina = Long.valueOf( getTipoNominaSeleccionada() );
        }
        lista = getFacade().obtenerNominasSinFolio( getFechaDesde(), getFechaHasta(), loteNomina, tipoNomina, bancoProceso );
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "lista:[" + lista + "]" );
        showPfRegistrarFolio = Boolean.FALSE;
        limpiarRegistrarFolio();
    }

    private void limpiarRegistrarFolio() {
        if ( numeroFolio != null )
            numeroFolio = null;
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

    public List<NominaBO> getLista() {
        return this.lista;
    }

    public void setLista( List<NominaBO> lista ) {
        this.lista = lista;
    }

    public List<SelectItem> getTiposNomina() {
        return this.tiposNomina;
    }

    public void setTiposNomina( List<SelectItem> tiposNomina ) {
        this.tiposNomina = tiposNomina;
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

    public String getBancoPagoMuestra() {
        return this.bancoPagoMuestra;
    }

    public void setBancoPagoMuestra( String bancoPagoMuestra ) {
        this.bancoPagoMuestra = bancoPagoMuestra;
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

    public String getMensajeSalida() {
        return this.mensajeSalida;
    }

    public void setMensajeSalida( String mensajeSalida ) {
        this.mensajeSalida = mensajeSalida;
    }

    public String getNumeroNominaSeleccionado() {
        return this.numeroNominaSeleccionado;
    }

    public void setNumeroNominaSeleccionado( String numeroNominaSeleccionado ) {
        this.numeroNominaSeleccionado = numeroNominaSeleccionado;
    }

    public String getNumeroFolio() {
        return this.numeroFolio;
    }

    public void setNumeroFolio( String numeroFolio ) {
        this.numeroFolio = numeroFolio;
    }

    public CoreTableSelectOne getTsoSelectorNominas() {
        return this.tsoSelectorNominas;
    }

    public void setTsoSelectorNominas( CoreTableSelectOne tsoSelectorNominas ) {
        this.tsoSelectorNominas = tsoSelectorNominas;
    }

    public NominaBO getNominaSeleccionada() {
        return this.nominaSeleccionada;
    }

    public void setNominaSeleccionada( NominaBO nominaSeleccionada ) {
        this.nominaSeleccionada = nominaSeleccionada;
    }

    public CoreTable getTabla() {
        return this.tabla;
    }

    public void setTabla( CoreTable tabla ) {
        this.tabla = tabla;
    }

    public void setShowPfRegistrarFolio( Boolean showPfRegistrarFolio ) {
        this.showPfRegistrarFolio = showPfRegistrarFolio;
    }

    public Boolean getShowPfRegistrarFolio() {
        return showPfRegistrarFolio;
    }

    public void setShowBtnGuardarFolio( Boolean showBtnGuardarFolio ) {
        this.showBtnGuardarFolio = showBtnGuardarFolio;
    }

    public Boolean getShowBtnGuardarFolio() {
        return showBtnGuardarFolio;
    }

    public void setShowBtnContinuar( Boolean showBtnContinuar ) {
        this.showBtnContinuar = showBtnContinuar;
    }

    public Boolean getShowBtnContinuar() {
        return showBtnContinuar;
    }

    public void setEnableInputRegistrarFolio( Boolean enableInputRegistrarFolio ) {
        this.enableInputRegistrarFolio = enableInputRegistrarFolio;
    }

    public Boolean getEnableInputRegistrarFolio() {
        return enableInputRegistrarFolio;
    }

    public void setRegistrosPagina( Long registrosPagina ) {
        this.registrosPagina = registrosPagina;
    }

    public Long getRegistrosPagina() {
        return registrosPagina;
    }

    public void setLoteNomina(String loteNomina) {
        this.loteNomina = loteNomina;
    }

    public String getLoteNomina() {
        return loteNomina;
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
