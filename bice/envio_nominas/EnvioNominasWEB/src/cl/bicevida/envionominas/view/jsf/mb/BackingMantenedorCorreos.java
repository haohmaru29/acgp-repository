package cl.bicevida.envionominas.view.jsf.mb;

import cl.bicevida.core.view.jsf.mb.MbError;
import cl.bicevida.core.view.jsf.mb.MbUserInfo;
import cl.bicevida.core.view.jsf.utils.JsfUtils;
import cl.bicevida.core.view.utils.BvWebLog;
import cl.bicevida.envionominas.model.bo.CorreoBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;

import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import oracle.adf.view.faces.component.core.data.CoreTable;

public class BackingMantenedorCorreos extends AbstractBacking {
    private MbUserInfo mbUserInfo;
    private List<CorreoBO> lista;
    private List<SelectItem> estadosNomina;
    private List<SelectItem> tiposCorreo;
    private String tipoCorreoSeleccionado;
    private CorreoBO correoSeleccionado;
    private Long idCorreo;
    private String nombre;
    private String subject;
    private String mensaje;
    private Long idEstado;
    private String estadoSeleccionado;
    private Boolean showBtnModificaCorreo = Boolean.FALSE;
    private Boolean showBtnAgregaCorreo = Boolean.TRUE;
    private Long registrosPagina;

    public BackingMantenedorCorreos() {
        //BvWebLog.log(this.getClass().getName(),BvWebLog.LEVEL_INFO,"BackingMantenedorFeriados()");
        mbUserInfo = ( MbUserInfo ) JsfUtils.getValue( "#{MbUserInfo}" );
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "USUARIO:" + mbUserInfo.getUserName() + "/RUT:" + mbUserInfo.getRut() + "/PERFIL:" + ( mbUserInfo.isUserInRole( "BICEVIDANET" ) ? "BICEVIDANET" : mbUserInfo.isUserInRole( "INTRANET" ) ? "INTRANET" : mbUserInfo.isUserInRole( "BICEVIDA" ) ? "BICEVIDA" : "ROL NO VALIDO" ) );
        EnvioNominasConfig parametros = new EnvioNominasConfig();
        registrosPagina = Long.valueOf( parametros.get( EnvioNominasConfig.REGISTROS_PAGINA ) );
        cargarPagina();
    }

    private void cargarPagina() {
        //BvWebLog.log(this.getClass().getName(),BvWebLog.LEVEL_INFO,"cargarPagina()");
        tiposCorreo = getTiposCorreo();
        try {
            lista = getFacade().obtenerCorreos();
            estadosNomina = getItemsEstadosNomina();
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingMantenedorCorreos.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        showBtnModificaCorreo = Boolean.FALSE;
    }
    //EVENTOS

    public void limpiarFormularioCorreo() {
        if ( nombre != null ) {
            nombre = null;
        }
        if ( subject != null ) {
            subject = null;
        }
        if ( mensaje != null ) {
            mensaje = null;
        }
        if ( tipoCorreoSeleccionado != null ) {
            tipoCorreoSeleccionado = null;
        }
        if ( estadoSeleccionado != null ) {
            estadoSeleccionado = null;
        }
    }

    public void limpiarFormulario( ActionEvent actionEvent ) {
        if ( nombre != null ) {
            nombre = null;
        }
        if ( subject != null ) {
            subject = null;
        }
        if ( mensaje != null ) {
            mensaje = null;
        }
        if ( tipoCorreoSeleccionado != null ) {
            tipoCorreoSeleccionado = null;
        }
        if ( estadoSeleccionado != null ) {
            estadoSeleccionado = null;
        }
        showBtnModificaCorreo = Boolean.FALSE;
        showBtnAgregaCorreo = Boolean.TRUE;
    }

    public void ejecutarAgregar( ActionEvent actionEvent ) {
        System.out.println( "ejecutarAgregar([" + actionEvent + "])" );
        Long idEstado = null;
        String subject = null;
        String mensaje = null;
        if ( estadoSeleccionado != null ) {
            idEstado = Long.valueOf( estadoSeleccionado );
        }
        if ( getSubject() != null ) {
            subject = String.valueOf( getSubject() );
        }
        if ( getMensaje() != null ) {
            mensaje = String.valueOf( getMensaje() );
        }
        try {
            if ( getFacade().buscarCorreo( nombre ) == null ) {
                getFacade().agregarCorreo( nombre, subject, mensaje, tipoCorreoSeleccionado, idEstado );
                lista = getFacade().obtenerCorreos();
            }
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingMantenedorCorreos.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        try {
            lista = getFacade().obtenerCorreos();
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingMantenedorCorreos.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        limpiarFormularioCorreo();
    }

    public void ejecutarModificar( ActionEvent actionEvent ) {
        CoreTable tabla = ( CoreTable ) JsfUtils.findComponent( "tblCorreos" );
        List<CorreoBO> correos;
        correos = JsfUtils.getObjetosSeleccionados( tabla );
        if ( correos != null && correos.size() == 1 ) {
            correoSeleccionado = correos.get( 0 );
            System.out.println( "ejecutarModificar([" + actionEvent + "])" );
            if ( estadoSeleccionado != null ) {
                idEstado = Long.valueOf( estadoSeleccionado );
            }
            try {
                getFacade().modificarCorreo( correoSeleccionado.getId(), nombre, subject, mensaje, tipoCorreoSeleccionado, idEstado );
            } catch ( Exception e ) {
                MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
                mbError.showError( BackingMantenedorCorreos.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
            }
            try {
                lista = getFacade().obtenerCorreos();
            } catch ( Exception e ) {
                MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
                mbError.showError( BackingMantenedorCorreos.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
            }
        }
        limpiarFormularioCorreo();
    }

    public void onClickEditar( ActionEvent actionEvent ) {
        CoreTable tabla = ( CoreTable ) JsfUtils.findComponent( "tblCorreos" );
        List<CorreoBO> correos;
        correos = JsfUtils.getObjetosSeleccionados( tabla );
        if ( correos != null && correos.size() == 1 ) {
            correoSeleccionado = correos.get( 0 );
        }
        if ( estadoSeleccionado != null ) {
            idEstado = Long.valueOf( estadoSeleccionado );
        }
        if ( correoSeleccionado != null ) {
            this.nombre = correoSeleccionado.getNombre();
            this.subject = correoSeleccionado.getSubject();
            this.mensaje = correoSeleccionado.getMensaje();
            this.estadoSeleccionado = correoSeleccionado.getEstado().getId().toString();
            this.idEstado = correoSeleccionado.getEstado().getId();
            showBtnModificaCorreo = Boolean.TRUE;
            showBtnAgregaCorreo = Boolean.FALSE;
        }
    }

    public void onClickEliminar( ActionEvent actionEvent ) {
        CoreTable tabla = ( CoreTable ) JsfUtils.findComponent( "tblCorreos" );
        List<CorreoBO> correos;
        correos = JsfUtils.getObjetosSeleccionados( tabla );
        if ( correos != null && correos.size() == 1 ) {
            correoSeleccionado = correos.get( 0 );
        }
        if ( correoSeleccionado != null ) {
            try {
                getFacade().eliminarCorreo( correoSeleccionado.getId() );
            } catch ( Exception e ) {
                MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
                mbError.showError( BackingMantenedorCorreos.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
            }
            try {
                lista = getFacade().obtenerCorreos();
            } catch ( Exception e ) {
                MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
                mbError.showError( BackingMantenedorCorreos.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
            }
            showBtnModificaCorreo = Boolean.FALSE;
        }
    }
    //ACCESORES    

    public List<CorreoBO> getLista() {
        return this.lista;
    }

    public void setLista( List<CorreoBO> lista ) {
        this.lista = lista;
    }

    public void setEstadosCorreo( List<SelectItem> estadosCorreo ) {
        this.tiposCorreo = estadosCorreo;
    }

    public List<CorreoBO> get_lista() {
        return lista;
    }

    public void setCorreoSeleccionado( CorreoBO correoSeleccionado ) {
        this.correoSeleccionado = correoSeleccionado;
    }

    public CorreoBO getCorreoSeleccionado() {
        return correoSeleccionado;
    }

    public void setIdCorreo( Long idCorreo ) {
        this.idCorreo = idCorreo;
    }

    public Long getIdCorreo() {
        return idCorreo;
    }

    public void setNombre( String nombre ) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setSubject( String subject ) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setMensaje( String mensaje ) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setShowBtnModificaCorreo( Boolean showBtnModificaCorreo ) {
        this.showBtnModificaCorreo = showBtnModificaCorreo;
    }

    public Boolean getShowBtnModificaCorreo() {
        return showBtnModificaCorreo;
    }

    public void setTiposCorreo( List<SelectItem> tiposCorreo ) {
        this.tiposCorreo = tiposCorreo;
    }

    public List<SelectItem> getTiposCorreo() {
        return tiposCorreo;
    }

    public void setTipoCorreoSeleccionado( String tipoCorreoSeleccionado ) {
        this.tipoCorreoSeleccionado = tipoCorreoSeleccionado;
    }

    public String getTipoCorreoSeleccionado() {
        return tipoCorreoSeleccionado;
    }

    public void setEstadosNomina( List<SelectItem> estadosNomina ) {
        this.estadosNomina = estadosNomina;
    }

    public List<SelectItem> getEstadosNomina() {
        return estadosNomina;
    }

    public void setIdEstado( Long idEstado ) {
        this.idEstado = idEstado;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public void set_estadoSeleccionado( String _estadoSeleccionado ) {
        this.estadoSeleccionado = _estadoSeleccionado;
    }

    public String get_estadoSeleccionado() {
        return estadoSeleccionado;
    }

    public void setEstadoSeleccionado( String estadoSeleccionado ) {
        this.estadoSeleccionado = estadoSeleccionado;
    }

    public String getEstadoSeleccionado() {
        return estadoSeleccionado;
    }

    public void setRegistrosPagina( Long registrosPagina ) {
        this.registrosPagina = registrosPagina;
    }

    public Long getRegistrosPagina() {
        return registrosPagina;
    }

    public void setShowBtnAgregaCorreo( Boolean showBtnAgregaCorreo ) {
        this.showBtnAgregaCorreo = showBtnAgregaCorreo;
    }

    public Boolean getShowBtnAgregaCorreo() {
        return showBtnAgregaCorreo;
    }
}
