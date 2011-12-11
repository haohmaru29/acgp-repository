package cl.bicevida.envionominas.view.jsf.mb;

import cl.bicevida.core.view.jsf.mb.MbError;
import cl.bicevida.core.view.jsf.mb.MbUserInfo;
import cl.bicevida.core.view.jsf.utils.JsfUtils;
import cl.bicevida.core.view.utils.BvWebLog;
import cl.bicevida.envionominas.model.bo.BancoProcesoBO;
import cl.bicevida.envionominas.model.bo.ComisionBO;
import cl.bicevida.envionominas.model.bo.ParametroBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;

import java.math.BigDecimal;

import java.math.MathContext;

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

public class BackingMantenedorBancosProceso extends AbstractBacking {
    private MbUserInfo mbUserInfo;
    private MathContext mc = new MathContext(3);
    public static final String MENSAJE_RANGO_ERROR = "Rango de fechas incorrecto, verificar";
    public static final String MENSAJE_RANGO_FALTANTE = "Se debe completar el rango. ";
    private Boolean validada = Boolean.FALSE;

    /**
     * Lista de bancos de proceso.
     */
    private List<BancoProcesoBO> lista;

    /**
     * Lista de items banco seleccionables.
     */
    private List<SelectItem> bancos;

    /**
     * Item banco seleccionado del dropdown.
     */
    private String bancoSeleccionado;
    private String nombre;

    /**
     * Datos del formulario de ingreso/edicion de parametros.
     */
    private List<SelectItem> tiposParametro;
    private String claveParametro;
    private String valorParametro;
    private String descripcionParametro;
    private String tipoParametroSeleccionado;

    /**
     * Datos del formulario de ingreso/edicion de comisiones.
     */
    private Date fechaInicioVigencia;
    private Date fechaTerminoVigencia;
    private BigDecimal montoMismoBanco;
    private BigDecimal montoOtrosBancos;

    /**
     * Banco de proceso seleccionado.
     */
    private BancoProcesoBO bancoProcesoSeleccionado;

    /**
     * Controles dinamicos de la pagina.
     */
    private Boolean showTblComisiones = Boolean.FALSE;
    private Boolean showTblBancos = Boolean.FALSE;
    private Boolean showPfAgregarComision = Boolean.FALSE;
    private Boolean showBtAgregarBancoProceso = Boolean.FALSE;
    private Boolean showBtnEditaComision = Boolean.FALSE;
    private Boolean showBtnNuevaComision = Boolean.FALSE;
    private Boolean showBtnEditaParametro = Boolean.FALSE;
    private Boolean showBtnNuevoParametro = Boolean.FALSE;
    private Boolean showPfAgregarParametro = Boolean.FALSE;
    private Boolean showTblParametros = Boolean.FALSE;
    private Boolean showPfAgregarBancoProceso = Boolean.FALSE;
    private Boolean showPhTituloComisiones = Boolean.FALSE;
    private Boolean showPhTituloParametros = Boolean.FALSE;
    private Boolean showPfBancoSeleccionado = Boolean.FALSE;
    private Long registrosPagina;
    private ComisionBO comisionSeleccionada;
    private ParametroBO parametroSeleccionado;

    public BackingMantenedorBancosProceso() {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "BackingMantenedorBancosPago()" );
        mbUserInfo = ( MbUserInfo ) JsfUtils.getValue( "#{MbUserInfo}" );
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "USUARIO:" + mbUserInfo.getUserName() + "/RUT:" + mbUserInfo.getRut() + "/PERFIL:" + ( mbUserInfo.isUserInRole( "BICEVIDANET" ) ? "BICEVIDANET" : mbUserInfo.isUserInRole( "INTRANET" ) ? "INTRANET" : mbUserInfo.isUserInRole( "BICEVIDA" ) ? "BICEVIDA" : "ROL NO VALIDO" ) );
        EnvioNominasConfig parametros = new EnvioNominasConfig();
        registrosPagina = Long.valueOf( parametros.get( EnvioNominasConfig.REGISTROS_PAGINA ) );
        cargarPagina();
    }

    private void cargarPagina() {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "cargarPagina()" );
        try {
            tiposParametro = getItemsTipoParametro();
            bancos = getItemsBancosPago();
            lista = getFacade().obtenerBancosProceso();
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingMantenedorBancosProceso.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        tipoParametroSeleccionado = null;
        bancoProcesoSeleccionado = null;
        comisionSeleccionada = null;
        parametroSeleccionado = null;
        limpiarFormularioComisiones();
        limpiarFormularioParametros();
        showPfAgregarComision = Boolean.FALSE;
        showPfAgregarParametro = Boolean.FALSE;
        showPfAgregarBancoProceso = Boolean.FALSE;
        showTblComisiones = Boolean.FALSE;
        showTblParametros = Boolean.FALSE;
        showBtAgregarBancoProceso = Boolean.TRUE;
    }
    //EVENTOS

    private BancoProcesoBO obtenerBancoProcesoSeleccionado( String idComponente ) {
        BancoProcesoBO seleccion = null;
        CoreTable tabla = ( CoreTable ) JsfUtils.findComponent( idComponente );
        List<BancoProcesoBO> bancos = ( List<BancoProcesoBO> ) JsfUtils.getObjetosSeleccionados( tabla );
        if ( bancos != null && bancos.size() == 1 ) {
            seleccion = bancos.get( 0 );
        }
        return seleccion;
    }

    private ComisionBO obtenerComisionSeleccionada( String idComponente ) {
        ComisionBO seleccion = null;
        CoreTable tabla = ( CoreTable ) JsfUtils.findComponent( idComponente );
        List<ComisionBO> comisiones = ( List<ComisionBO> ) JsfUtils.getObjetosSeleccionados( tabla );
        if ( comisiones != null && comisiones.size() == 1 ) {
            seleccion = comisiones.get( 0 );
        }
        return seleccion;
    }

    private ParametroBO obtenerParametroSeleccionado( String idComponente ) {
        ParametroBO seleccion = null;
        CoreTable tabla = ( CoreTable ) JsfUtils.findComponent( idComponente );
        List<ParametroBO> parametros = ( List<ParametroBO> ) JsfUtils.getObjetosSeleccionados( tabla );
        if ( parametros != null && parametros.size() == 1 ) {
            seleccion = parametros.get( 0 );
        }
        return seleccion;
    }

    public void onClickEditarBancoProceso( ActionEvent actionEvent ) {
        bancoProcesoSeleccionado = obtenerBancoProcesoSeleccionado( "tblBancosProceso" );
        if ( bancoProcesoSeleccionado != null ) {
            try {
                bancoProcesoSeleccionado.setParametros( getFacade().obtenerParametrosByBancoProceso( bancoProcesoSeleccionado.getId() ) );
                bancoProcesoSeleccionado.setComisiones( getFacade().obtenerComisionesByBancoProceso( bancoProcesoSeleccionado.getId() ) );
            } catch ( Exception e ) {
                MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
                mbError.showError( BackingMantenedorBancosProceso.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
            }
            showTblComisiones = Boolean.TRUE;
            showTblParametros = Boolean.TRUE;
            showBtAgregarBancoProceso = Boolean.TRUE;
            showPhTituloComisiones = Boolean.TRUE;
            showPhTituloParametros = Boolean.TRUE;
            showPfBancoSeleccionado = Boolean.TRUE;
            showPfAgregarBancoProceso = Boolean.FALSE;
            showPfAgregarParametro = Boolean.FALSE;
            showPfAgregarComision = Boolean.FALSE;
        }
    }

    public void onClickAgregarBancoProceso( ActionEvent actionEvent ) {
        limpiarFormularioBanco();
        showPfAgregarBancoProceso = Boolean.TRUE;
        showBtAgregarBancoProceso = Boolean.FALSE;
        showPfAgregarComision = Boolean.FALSE;
        showPfAgregarParametro = Boolean.FALSE;
        showTblComisiones = Boolean.FALSE;
        showTblParametros = Boolean.FALSE;
        showPhTituloComisiones = Boolean.FALSE;
        showPhTituloParametros = Boolean.FALSE;
        showPfBancoSeleccionado = Boolean.FALSE;
    }

    public void ejecutarGuardarBancoProceso( ActionEvent actionEvent ) {
        System.out.println( "ejecutarGuardarBancoProceso([" + actionEvent + "])" );
        if ( bancoSeleccionado != null ) {
            try {
                getFacade().crearBancoProceso( nombre, Long.valueOf( bancoSeleccionado ) );
            } catch ( Exception e ) {
                MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
                mbError.showError( BackingMantenedorBancosProceso.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, "mantenedorBancoProceso", "bienvenida" );
            }
            try {
                lista = getFacade().obtenerBancosProceso();
            } catch ( Exception e ) {
                MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
                mbError.showError( BackingMantenedorBancosProceso.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
            }
        }
        showPfAgregarBancoProceso = Boolean.FALSE;
        showBtAgregarBancoProceso = Boolean.TRUE;
    }

    public void onClickEliminarBancoProceso( ActionEvent actionEvent ) {
        bancoProcesoSeleccionado = obtenerBancoProcesoSeleccionado( "tblBancosProceso" );
        if ( bancoProcesoSeleccionado != null ) {
            try {
                if ( !getFacade().isBancoProcesoEnUso( bancoProcesoSeleccionado.getId() ) ) {
                    getFacade().eliminarBancoProceso( bancoProcesoSeleccionado.getId() );
                    lista = getFacade().obtenerBancosProceso();
                    showPfAgregarComision = Boolean.FALSE;
                    showPfAgregarParametro = Boolean.FALSE;
                    showTblComisiones = Boolean.FALSE;
                    showTblParametros = Boolean.FALSE;
                    showPhTituloComisiones = Boolean.FALSE;
                    showPhTituloParametros = Boolean.FALSE;
                    showPfBancoSeleccionado = Boolean.FALSE;
                } else {
                    MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
                    mbError.showError( BackingMantenedorBancosProceso.class.getName(), BvWebLog.LEVEL_ERROR, null, "Imposible eliminar el banco de proceso ID : [" + bancoProcesoSeleccionado.getId() + "] ya que esta en uso.", null, "mantenedorBancoProceso", "bienvenida" );
                }
            } catch ( Exception e ) {
                MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
                mbError.showError( BackingMantenedorBancosProceso.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
            }
        }
    }

    public void onClickAgregarComision( ActionEvent actionEvent ) {
        limpiarFormularioComisiones();
        showPfAgregarParametro = Boolean.FALSE;
        showBtnEditaComision = Boolean.FALSE;
        showPfAgregarComision = Boolean.TRUE;
        showBtnNuevaComision = Boolean.TRUE;
    }

    public void ejecutarGuardarComision( ActionEvent actionEvent ) {
        System.out.println( "ejecutarGuardarComision([" + actionEvent + "])" );
        System.out.println( "montoMismoBanco="+montoMismoBanco);
        System.out.println( "montoMismoBanco.round(mc)="+montoMismoBanco.round(mc));
        System.out.println( "montoMismoBanco="+montoMismoBanco);
        try {
            getFacade().agregarComision( bancoProcesoSeleccionado.getId(), fechaInicioVigencia, fechaTerminoVigencia, montoMismoBanco.round(mc), montoOtrosBancos );
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingMantenedorBancosProceso.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        try {
            bancoProcesoSeleccionado.setComisiones( getFacade().obtenerComisionesByBancoProceso( bancoProcesoSeleccionado.getId() ) );
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingMantenedorBancosProceso.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        showPfAgregarComision = Boolean.FALSE;
        showBtnNuevaComision = Boolean.FALSE;
        limpiarFormularioComisiones();
    }

    private void limpiarFormularioComisiones() {
        if ( fechaInicioVigencia != null ) {
            fechaInicioVigencia = null;
        }
        if ( fechaTerminoVigencia != null ) {
            fechaTerminoVigencia = null;
        }
        if ( montoMismoBanco != null ) {
            montoMismoBanco = null;
        }
        if ( montoOtrosBancos != null ) {
            montoOtrosBancos = null;
        }
    }

    private void limpiarFormularioBanco() {
        if ( bancoSeleccionado != null ) {
            bancoSeleccionado = null;
        }
        if ( nombre != null ) {
            nombre = null;
        }
    }

    public void onClickEliminarComision( ActionEvent actionEvent ) {
        comisionSeleccionada = obtenerComisionSeleccionada( "tblComisiones" );
        if ( comisionSeleccionada != null ) {
            try {
                getFacade().eliminarComisionById( comisionSeleccionada.getId() );
            } catch ( Exception e ) {
                MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
                mbError.showError( BackingMantenedorBancosProceso.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
            }
        }
        try {
            bancoProcesoSeleccionado.setComisiones( getFacade().obtenerComisionesByBancoProceso( bancoProcesoSeleccionado.getId() ) );
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingMantenedorBancosProceso.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        showPfAgregarComision = Boolean.FALSE;
        limpiarFormularioComisiones();
    }

    public void onClickEditarComision( ActionEvent actionEvent ) {
        comisionSeleccionada = obtenerComisionSeleccionada( "tblComisiones" );
        if ( comisionSeleccionada != null ) {
            this.fechaInicioVigencia = comisionSeleccionada.getFechaInicioVigencia();
            this.fechaTerminoVigencia = comisionSeleccionada.getFechaTerminoVigencia();
            this.montoMismoBanco = comisionSeleccionada.getMontoMismoBanco();
            this.montoOtrosBancos = comisionSeleccionada.getMontoOtrosBancos();
            showBtnNuevaComision = Boolean.FALSE;
            showBtnEditaParametro = Boolean.FALSE;
            showPfAgregarComision = Boolean.TRUE;
            showBtnEditaComision = Boolean.TRUE;
            showPfAgregarParametro = Boolean.FALSE;
        }
    }

    public void ejecutarEditarComision( ActionEvent actionEvent ) {
        try {
            getFacade().guardarCambiosComision( comisionSeleccionada.getId(), fechaInicioVigencia, fechaTerminoVigencia, montoMismoBanco, montoOtrosBancos );
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingMantenedorBancosProceso.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        try {
            bancoProcesoSeleccionado.setComisiones( getFacade().obtenerComisionesByBancoProceso( bancoProcesoSeleccionado.getId() ) );
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingMantenedorBancosProceso.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        showPfAgregarComision = Boolean.FALSE;
        limpiarFormularioComisiones();
    }

    public void onClickAgregarParametro( ActionEvent actionEvent ) {
        showPfAgregarComision = Boolean.FALSE;
        showBtnEditaParametro = Boolean.FALSE;
        showPfAgregarParametro = Boolean.TRUE;
        showBtnNuevoParametro = Boolean.TRUE;
    }

    public void ejecutarGuardarParametro( ActionEvent actionEvent ) {
        System.out.println( "ejecutarGuardarParametro([" + actionEvent + "])" );
        String claveParametro = null;
        String valorParametro = null;
        String descripcionParametro = null;
        if ( getClaveParametro() != null ) {
            claveParametro = getClaveParametro().toString();
            if ( getValorParametro() != null ) {
                valorParametro = String.valueOf( getValorParametro() );
                if ( getDescripcionParametro() != null ) {
                    descripcionParametro = String.valueOf( getDescripcionParametro() );
                    try {
                        if ( getFacade().buscarParametro( claveParametro, bancoProcesoSeleccionado.getId() ) == null ) {
                            getFacade().agregarParametroBancoProceso( bancoProcesoSeleccionado.getId(), claveParametro, valorParametro, descripcionParametro, tipoParametroSeleccionado );
                            bancoProcesoSeleccionado.setParametros( getFacade().obtenerParametrosByBancoProceso( bancoProcesoSeleccionado.getId() ) );
                            limpiarFormularioParametros();
                        }
                    } catch ( Exception e ) {
                        MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
                        mbError.showError( BackingMantenedorBancosProceso.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
                    }
                }
            }
        }
        showPfAgregarParametro = Boolean.FALSE;
        showBtnNuevoParametro = Boolean.FALSE;
    }

    public void onClickEditarParametro( ActionEvent actionEvent ) {
        parametroSeleccionado = obtenerParametroSeleccionado( "tblParametrosBancoProceso" );
        if ( parametroSeleccionado != null ) {
            this.claveParametro = parametroSeleccionado.getClaveParametro();
            this.valorParametro = parametroSeleccionado.getValorParametro();
            this.descripcionParametro = parametroSeleccionado.getDescripcionParametro();
            this.tipoParametroSeleccionado = parametroSeleccionado.getTipoParametro();
            showBtnNuevoParametro = Boolean.FALSE;
            showPfAgregarComision = Boolean.FALSE;
            showPfAgregarParametro = Boolean.TRUE;
            showBtnEditaParametro = Boolean.TRUE;
        }
    }

    public void ejecutarEditarParametro( ActionEvent actionEvent ) {
        try {
            getFacade().guardarCambiosParametro( parametroSeleccionado.getId(), claveParametro, valorParametro, descripcionParametro, tipoParametroSeleccionado );
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingMantenedorBancosProceso.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        try {
            bancoProcesoSeleccionado.setParametros( getFacade().obtenerParametrosByBancoProceso( bancoProcesoSeleccionado.getId() ) );
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingMantenedorBancosProceso.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        limpiarFormularioParametros();
        showPfAgregarParametro = Boolean.FALSE;
    }

    private void limpiarFormularioParametros() {
        if ( claveParametro != null ) {
            claveParametro = null;
        }
        if ( valorParametro != null ) {
            valorParametro = null;
        }
        if ( descripcionParametro != null ) {
            descripcionParametro = null;
        }
        if ( fechaInicioVigencia != null ) {
            fechaInicioVigencia = null;
        }
        if ( fechaTerminoVigencia != null ) {
            fechaTerminoVigencia = null;
        }
        if ( tipoParametroSeleccionado != null ) {
            tipoParametroSeleccionado = null;
        }
    }

    public void onClickEliminarParametro( ActionEvent actionEvent ) {
        parametroSeleccionado = obtenerParametroSeleccionado( "tblParametrosBancoProceso" );
        if ( parametroSeleccionado != null ) {
            try {
                getFacade().eliminarParametro( parametroSeleccionado.getId() );
            } catch ( Exception e ) {
                MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
                mbError.showError( BackingMantenedorBancosProceso.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
            }
        }
        try {
            bancoProcesoSeleccionado.setParametros( getFacade().obtenerParametrosByBancoProceso( bancoProcesoSeleccionado.getId() ) );
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingMantenedorBancosProceso.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        limpiarFormularioParametros();
        showPfAgregarParametro = Boolean.FALSE;
    }

    public void validarFechas( FacesContext facesContext, UIComponent uiComponent, Object object ) {
        FacesMessage fv;
        Calendar calFechaDesde = Calendar.getInstance();
        Calendar calFechaHasta = Calendar.getInstance();
        CoreSelectInputDate fechaInicioVigencia = ( CoreSelectInputDate ) JsfUtils.findComponent( "sitInicioVigencia" );
        CoreSelectInputDate fechaTerminoVigencia = ( CoreSelectInputDate ) JsfUtils.findComponent( "sitFinVigencia" );
        String valorFechaDesde = ( String ) fechaInicioVigencia.getSubmittedValue();
        String valorFechaHasta = ( String ) fechaTerminoVigencia.getSubmittedValue();
        if ( !validada ) {
            if ( valorFechaDesde != null && !valorFechaDesde.equals( "" ) && valorFechaHasta != null && !valorFechaHasta.equals( "" ) ) {
                String[] arrayFechaDesde = valorFechaDesde.split( "-" );
                if(arrayFechaDesde.length > 1) {
                    calFechaDesde.set( Integer.valueOf( arrayFechaDesde[ 2 ] ).intValue(), Integer.valueOf( arrayFechaDesde[ 1 ] ).intValue(), Integer.valueOf( arrayFechaDesde[ 0 ] ).intValue() );
                    String[] arrayFechaHasta = valorFechaHasta.split( "-" );
                    calFechaHasta.set( Integer.valueOf( arrayFechaHasta[ 2 ] ).intValue(), Integer.valueOf( arrayFechaHasta[ 1 ] ).intValue(), Integer.valueOf( arrayFechaHasta[ 0 ] ).intValue() );
                    if ( calFechaDesde.compareTo( calFechaHasta ) > 0 ) {
                        fv = new FacesMessage( MENSAJE_RANGO_ERROR );
                        throw new ValidatorException( fv );
                        // la fecha desde no puede ser mayor que hasta     
                    }
                }
            } else {
                if ( valorFechaDesde == null && valorFechaHasta == null ) {
                    // cuando las 2 son nulas no se toma en cuenta poara la busqueda
                } else {
                    // cuando una es nula y la otra no
                    this.fechaInicioVigencia = null;
                    this.fechaTerminoVigencia = null;
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

    public void setShowPfAgregarComision( Boolean showPfAgregarComision ) {
        this.showPfAgregarComision = showPfAgregarComision;
    }

    public Boolean getShowPfAgregarComision() {
        return showPfAgregarComision;
    }

    public void setShowPfAgregarParametros( Boolean showPfAgregarParametros ) {
        this.showPfAgregarParametro = showPfAgregarParametros;
    }

    public Boolean getShowPfAgregarParametros() {
        return showPfAgregarParametro;
    }

    public void setShowTblComisiones( Boolean showTblComisiones ) {
        this.showTblComisiones = showTblComisiones;
    }

    public Boolean getShowTblComisiones() {
        return showTblComisiones;
    }

    public void setShowTblParametros( Boolean showTblParametros ) {
        this.showTblParametros = showTblParametros;
    }

    public Boolean getShowTblParametros() {
        return showTblParametros;
    }

    public void setBancos( List<SelectItem> bancos ) {
        this.bancos = bancos;
    }

    public List<SelectItem> getBancos() {
        return bancos;
    }

    public List<BancoProcesoBO> getLista() {
        return lista;
    }

    public void setLista( List<BancoProcesoBO> lista ) {
        this.lista = lista;
    }

    public void setBancoSeleccionado( String bancoSeleccionado ) {
        this.bancoSeleccionado = bancoSeleccionado;
    }

    public String getBancoSeleccionado() {
        return bancoSeleccionado;
    }

    public void setShowTblBancos( Boolean showTblBancos ) {
        this.showTblBancos = showTblBancos;
    }

    public Boolean getShowTblBancos() {
        return showTblBancos;
    }

    public void setShowBtAgregarBancoProceso( Boolean showBtAgregarBancoProceso ) {
        this.showBtAgregarBancoProceso = showBtAgregarBancoProceso;
    }

    public Boolean getShowBtAgregarBancoProceso() {
        return showBtAgregarBancoProceso;
    }

    public void setClaveParametro( String claveParametro ) {
        this.claveParametro = claveParametro;
    }

    public String getClaveParametro() {
        return claveParametro;
    }

    public void setValorParametro( String valorParametro ) {
        this.valorParametro = valorParametro;
    }

    public String getValorParametro() {
        return valorParametro;
    }

    public void setDescripcionParametro( String descripcionParametro ) {
        this.descripcionParametro = descripcionParametro;
    }

    public String getDescripcionParametro() {
        return descripcionParametro;
    }

    public void setFechaInicioVigencia( Date fechaInicioVigencia ) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public Date getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public void setBancoProcesoSeleccionado( BancoProcesoBO bancoProcesoSeleccionado ) {
        this.bancoProcesoSeleccionado = bancoProcesoSeleccionado;
    }

    public BancoProcesoBO getBancoProcesoSeleccionado() {
        return bancoProcesoSeleccionado;
    }

    public void setFechaTerminoVigencia( Date fechaTerminoVigencia ) {
        this.fechaTerminoVigencia = fechaTerminoVigencia;
    }

    public Date getFechaTerminoVigencia() {
        return fechaTerminoVigencia;
    }

    public void setMontoMismoBanco( BigDecimal montoMismoBanco ) {
        this.montoMismoBanco = montoMismoBanco;
    }

    public BigDecimal getMontoMismoBanco() {
        return montoMismoBanco;
    }

    public void setMontoOtrosBancos( BigDecimal montoOtrosBancos ) {
        this.montoOtrosBancos = montoOtrosBancos;
    }

    public BigDecimal getMontoOtrosBancos() {
        return montoOtrosBancos;
    }

    public void setShowBtnEditaComision( Boolean showBtnEditaComision ) {
        this.showBtnEditaComision = showBtnEditaComision;
    }

    public Boolean getShowBtnEditaComision() {
        return showBtnEditaComision;
    }

    public void setShowBtnNuevaComision( Boolean showBtnNuevaComision ) {
        this.showBtnNuevaComision = showBtnNuevaComision;
    }

    public Boolean getShowBtnNuevaComision() {
        return showBtnNuevaComision;
    }

    public void setComisionSeleccionada( ComisionBO comisionSeleccionada ) {
        this.comisionSeleccionada = comisionSeleccionada;
    }

    public ComisionBO getComisionSeleccionada() {
        return comisionSeleccionada;
    }

    public void setParametroSeleccionado( ParametroBO parametroSeleccionado ) {
        this.parametroSeleccionado = parametroSeleccionado;
    }

    public ParametroBO getParametroSeleccionado() {
        return parametroSeleccionado;
    }

    public void setShowBtnEditaParametro( Boolean showBtnEditaParametro ) {
        this.showBtnEditaParametro = showBtnEditaParametro;
    }

    public Boolean getShowBtnEditaParametro() {
        return showBtnEditaParametro;
    }

    public void setShowBtnNuevoParametro( Boolean showBtnNuevoParametro ) {
        this.showBtnNuevoParametro = showBtnNuevoParametro;
    }

    public Boolean getShowBtnNuevoParametro() {
        return showBtnNuevoParametro;
    }

    public void setShowPfAgregarParametro( Boolean showPfAgregarParametro ) {
        this.showPfAgregarParametro = showPfAgregarParametro;
    }

    public Boolean getShowPfAgregarParametro() {
        return showPfAgregarParametro;
    }

    public void setShowPfAgregarBancoProceso( Boolean showPfAgregarBancoProceso ) {
        this.showPfAgregarBancoProceso = showPfAgregarBancoProceso;
    }

    public Boolean getShowPfAgregarBancoProceso() {
        return showPfAgregarBancoProceso;
    }

    public void setRegistrosPagina( Long registrosPagina ) {
        this.registrosPagina = registrosPagina;
    }

    public Long getRegistrosPagina() {
        return registrosPagina;
    }

    public void setTipoParametroSeleccionado( String tipoParametroSeleccionado ) {
        this.tipoParametroSeleccionado = tipoParametroSeleccionado;
    }

    public String getTipoParametroSeleccionado() {
        return tipoParametroSeleccionado;
    }

    public void setTiposParametro( List<SelectItem> tiposParametro ) {
        this.tiposParametro = tiposParametro;
    }

    public List<SelectItem> getTiposParametro() {
        return tiposParametro;
    }

    public void setShowPhTituloComisiones( Boolean showPhTituloComisiones ) {
        this.showPhTituloComisiones = showPhTituloComisiones;
    }

    public Boolean getShowPhTituloComisiones() {
        return showPhTituloComisiones;
    }

    public void setShowPhTituloParametros( Boolean showPhTituloParametros ) {
        this.showPhTituloParametros = showPhTituloParametros;
    }

    public Boolean getShowPhTituloParametros() {
        return showPhTituloParametros;
    }

    public void setShowPfBancoSeleccionado( Boolean showPfBancoSeleccionado ) {
        this.showPfBancoSeleccionado = showPfBancoSeleccionado;
    }

    public Boolean getShowPfBancoSeleccionado() {
        return showPfBancoSeleccionado;
    }

    public void setNombre( String nombre ) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
