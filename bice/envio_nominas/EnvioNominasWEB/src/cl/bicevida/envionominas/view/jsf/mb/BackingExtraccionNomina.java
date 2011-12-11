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
import oracle.adf.view.faces.context.AdfFacesContext;
import oracle.adf.view.faces.event.LaunchEvent;
import oracle.adf.view.faces.event.ReturnEvent;

public class BackingExtraccionNomina extends AbstractBacking {
    private MbUserInfo mbUserInfo;
    public static final String MENSAJE_RANGO_ERROR = "Rango de fechas incorrecto, verificar";
    public static final String MENSAJE_RANGO_FALTANTE = "Se debe completar el rango, o no se debe especificar ninguna fecha ";
    private Boolean validada = Boolean.FALSE;
    private List<NominaBO> lista;
    private CoreTable tblNominas;
    private CoreTableSelectOne nomina;
    private List<SelectItem> tiposNomina;
    private List<SelectItem> bancosProceso;
    private Date fechaDesde;
    private Date fechaHasta;
    private String loteNomina;
    private String tipoNominaSeleccionada;
    private String bancoProcesoSeleccionado;
    private NominaBO nominaSeleccionada;
    private Long registrosPagina;

    public BackingExtraccionNomina() {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "BackingExtraccionNomina()" );
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
            bancosProceso = getItemsBancosProceso();
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingExtraccionNomina.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
    }

    public void ejecutarBusqueda( ActionEvent actionEvent ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "ejecutarBusqueda([" + actionEvent + "])" );
        Long bancoProceso = null;
        String loteNomina = null;
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
        try {
            lista = getFacade().obtenerNominasExtraibles( getFechaDesde(), getFechaHasta(), loteNomina, tipoNomina, bancoProceso );
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingExtraccionNomina.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "lista:[" + lista + "]" );
    }

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
        lista = null;
        cargarPagina();
    }

    public String onClickVerDetalle() {
        CoreTable tabla = ( CoreTable ) JsfUtils.findComponent( "tblNominas" );
        List<NominaBO> lista = ( List<NominaBO> ) JsfUtils.getObjetosSeleccionados( tabla );
        if ( lista != null && lista.size() == 1 ) {
            nominaSeleccionada = lista.get( 0 );
        }
        if ( nominaSeleccionada != null ) {
            try {
                nominaSeleccionada.setRegistros( getFacade().obtenerRegistrosPorNomina( nominaSeleccionada.getId() ) );
            } catch ( Exception e ) {
                MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
                mbError.showError( BackingExtraccionNomina.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
            }
            return ( "detalleExtraccionNomina" );
        }
        return null;
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

    private NominaBO obtenerNominaSeleccionada() {
        NominaBO nominaSeleccionada = null;
        CoreTable tabla = ( CoreTable ) JsfUtils.findComponent( "tblNominas" );
        List<NominaBO> lista = ( List<NominaBO> ) JsfUtils.getObjetosSeleccionados( tabla );
        if ( lista != null && lista.size() == 1 ) {
            nominaSeleccionada = lista.get( 0 );
        }
        return nominaSeleccionada;
    }

    public void onClickExtraerNomina( ActionEvent actionEvent ) {
        if ( nominaSeleccionada != null ) {
            try {
                getFacade().extraerNomina( nominaSeleccionada.getId() );
            } catch ( Exception e ) {
                MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
                mbError.showError( BackingMantenedorBancosProceso.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, "mantenedorBancoProceso", "bienvenida" );
            }
        }
        // AdfFacesContext.getCurrentInstance().returnFromDialog(nominaSeleccionada, null);
    }

    public void actualizarTabla( ActionEvent actionEvent ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "ejecutarBusqueda" );
        Long bancoPago = null;
        String loteNomina = null;
        Long bancoProceso = null;
        Long tipoNomina = null;
        try {
            lista = getFacade().obtenerNominasExtraibles( getFechaDesde(), getFechaHasta(), loteNomina, tipoNomina, bancoProceso );
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingExtraccionNomina.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        AdfFacesContext.getCurrentInstance().returnFromDialog( tblNominas, null );
    }

    public void launchListener( LaunchEvent launchEvent ) {
        nominaSeleccionada = obtenerNominaSeleccionada();
        try {
            getFacade().extraerNomina( nominaSeleccionada.getId() );
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingExtraccionNomina.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
    }

    public void returnListener( ReturnEvent returnEvent ) {
        AdfFacesContext.getCurrentInstance().addPartialTarget( tblNominas );
    }
    
    //ACCESORES     
    public void setLista( List<NominaBO> lista ) {
        this.lista = lista;
    }

    public List<NominaBO> getLista() {
        return lista;
    }

    public void setTblNominas( CoreTable tblNominas ) {
        this.tblNominas = tblNominas;
    }

    public CoreTable getTblNominas() {
        return tblNominas;
    }

    public void setNomina( CoreTableSelectOne nomina ) {
        this.nomina = nomina;
    }

    public CoreTableSelectOne getNomina() {
        return nomina;
    }

    public void setTiposNomina( List<SelectItem> tiposNomina ) {
        this.tiposNomina = tiposNomina;
    }

    public List<SelectItem> getTiposNomina() {
        return tiposNomina;
    }

    public void setFechaDesde( Date fechaDesde ) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaHasta( Date fechaHasta ) {
        this.fechaHasta = fechaHasta;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setTipoNominaSeleccionada( String tipoNominaSeleccionada ) {
        this.tipoNominaSeleccionada = tipoNominaSeleccionada;
    }

    public String getTipoNominaSeleccionada() {
        return tipoNominaSeleccionada;
    }
    
    public void setNominaSeleccionada( NominaBO nominaSeleccionada ) {
        this.nominaSeleccionada = nominaSeleccionada;
    }

    public NominaBO getNominaSeleccionada() {
        return nominaSeleccionada;
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
