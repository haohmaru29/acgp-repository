package cl.bicevida.envionominas.view.jsf.mb;

import cl.bicevida.core.view.jsf.mb.MbError;
import cl.bicevida.core.view.jsf.mb.MbUserInfo;
import cl.bicevida.core.view.jsf.utils.JsfUtils;
import cl.bicevida.core.view.utils.BvWebLog;
import cl.bicevida.envionominas.model.bo.FeriadoBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;

import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import oracle.adf.view.faces.component.core.data.CoreTable;

public class BackingMantenedorFeriados extends AbstractBacking {
    private MbUserInfo mbUserInfo;
    private List<FeriadoBO> lista;
    private List<SelectItem> tiposFeriado;
    private Date fechaFeriado;
    private String descripcionFeriado;
    private String tipoFeriado;
    private String descTipoFeriado;
    private String tipoFeriadoSeleccionado;
    private FeriadoBO feriadoSeleccionado;
    private Long idFeriado;
    private String paCommandButtonModificar;
    private String paCommandButtonAgregar;
    private Date fechaFeriadoOriginal;
    private String descripcionFeriadoOriginal;
    private String tipoFeriadoOriginal;
    private Long idFeriadoOriginal;
    private Long registrosPagina;

    public BackingMantenedorFeriados() {
        //BvWebLog.log(this.getClass().getName(),BvWebLog.LEVEL_INFO,"BackingMantenedorFeriados()");
        mbUserInfo = ( MbUserInfo ) JsfUtils.getValue( "#{MbUserInfo}" );
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "USUARIO:" + mbUserInfo.getUserName() + "/RUT:" + mbUserInfo.getRut() + "/PERFIL:" + ( mbUserInfo.isUserInRole( "BICEVIDANET" ) ? "BICEVIDANET" : mbUserInfo.isUserInRole( "INTRANET" ) ? "INTRANET" : mbUserInfo.isUserInRole( "BICEVIDA" ) ? "BICEVIDA" : "ROL NO VALIDO" ) );
        EnvioNominasConfig parametros = new EnvioNominasConfig();
        registrosPagina = Long.valueOf( parametros.get( EnvioNominasConfig.REGISTROS_PAGINA ) );
        cargarPagina();
    }

    private void cargarPagina() {
        //BvWebLog.log(this.getClass().getName(),BvWebLog.LEVEL_INFO,"cargarPagina()");
        try {
            tiposFeriado = getItemsTiposFeriados();
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingMantenedorFeriados.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        try {
            lista = getFacade().obtenerFeriado();
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingMantenedorFeriados.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        setPaCommandModificar( "Apagar" );
        setPaCommandAgregar( "Prender" );
    }
    //EVENTOS

    public void ejecutarBusqueda( ActionEvent actionEvent ) {
        //BvWebLog.log(this.getClass().getName(),BvWebLog.LEVEL_INFO,"ejecutarBusqueda(["+actionEvent+"])");
        Long tipoFeriado = null;
        Long descripcionFeriado = null;
        if ( getTipoFeriadoSeleccionado() != null ) {
            tipoFeriado = Long.valueOf( getTipoFeriadoSeleccionado() );
        }
        if ( getDescripcionFeriado() != null ) {
            descripcionFeriado = Long.valueOf( getDescripcionFeriado() );
        }
    }

    public void limpiarFormulario( ActionEvent actionEvent ) {
        if ( fechaFeriado != null ) {
            fechaFeriado = null;
        }
        if ( descripcionFeriado != null ) {
            descripcionFeriado = null;
        }
        if ( tipoFeriadoSeleccionado != null ) {
            tipoFeriadoSeleccionado = null;
        }
        setPaCommandModificar( "Apagar" );
        setPaCommandAgregar( "Prender" );
    }

    public void ejecutarAgregar( ActionEvent actionEvent ) {
        String tipoFeriado = null;
        String descripcionFeriado = null;
        if ( getTipoFeriadoSeleccionado() != null ) {
            tipoFeriado = getTipoFeriadoSeleccionado().toString();
            if ( getDescripcionFeriado() != null ) {
                descripcionFeriado = String.valueOf( getDescripcionFeriado() );
                if ( getFechaFeriado() != null ) {
                    //revisar si fecha ya esta ingresada
                    try {
                        if ( getFacade().buscarFeriado( getFechaFeriado() ) == null ) {
                            getFacade().agregarFeriado( tipoFeriado, descripcionFeriado, getFechaFeriado() );
                            lista = getFacade().obtenerFeriado();
                            setPaCommandModificar( "Apagar" );
                            setPaCommandAgregar( "Prender" );
                        }
                    } catch ( Exception e ) {
                        MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
                        mbError.showError( BackingMantenedorFeriados.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
                    }
                }
            }
        }
    }

    public void ejecutarModificar( ActionEvent actionEvent ) {
        Boolean cambio = false;
        String tipoFeriado = null;
        String descripcionFeriado = null;
        Long idEncontrado = null;
        if ( getTipoFeriadoSeleccionado() != null ) {
            //tipoFeriado = String.valueOf(getTipoFeriadoSeleccionado());
            tipoFeriado = getTipoFeriadoSeleccionado().toString();
            if ( getDescripcionFeriado() != null ) {
                descripcionFeriado = String.valueOf( getDescripcionFeriado() );
                if ( getFechaFeriado() != null ) {
                    //revisar si fecha ya esta ingresada
                    try {
                        idEncontrado = getFacade().buscarFeriado( getFechaFeriado() );
                    } catch ( Exception e ) {
                        MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
                        mbError.showError( BackingMantenedorFeriados.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
                    }
                    if ( idEncontrado == null || idEncontrado == idFeriado ) {
                        try {
                            getFacade().modificarFeriado( idFeriado, tipoFeriado, descripcionFeriado, getFechaFeriado() );
                        } catch ( Exception e ) {
                            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
                            mbError.showError( BackingMantenedorFeriados.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
                        }
                        try {
                            lista = getFacade().obtenerFeriado();
                        } catch ( Exception e ) {
                            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
                            mbError.showError( BackingMantenedorFeriados.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
                        }
                        cambio = true;
                    }
                }
            }
        }
        if ( cambio == false ) {
            fechaFeriado = fechaFeriadoOriginal;
            setDescripcionFeriado( descripcionFeriadoOriginal );
            tipoFeriadoSeleccionado = tipoFeriadoOriginal;
            //idFeriado = idFeriadoOriginal;
        }
    }

    public String onClickEditar() {
        fechaFeriadoOriginal = null;
        descripcionFeriadoOriginal = null;
        tipoFeriadoOriginal = null;
        idFeriadoOriginal = null;
        CoreTable tabla = ( CoreTable ) JsfUtils.findComponent( "tblFeriados" );
        List<FeriadoBO> listafr;
        listafr = JsfUtils.getObjetosSeleccionados( tabla );
        if ( listafr != null && listafr.size() == 1 ) {
            feriadoSeleccionado = listafr.get( 0 );
        }
        if ( feriadoSeleccionado != null ) {
            fechaFeriado = feriadoSeleccionado.getFechaFeriado();
            descripcionFeriado = feriadoSeleccionado.getDescripcionFeriado();
            tipoFeriadoSeleccionado = feriadoSeleccionado.getTipoFeriado();
            idFeriado = feriadoSeleccionado.getId();
            fechaFeriadoOriginal = fechaFeriado;
            descripcionFeriadoOriginal = descripcionFeriado;
            tipoFeriadoOriginal = tipoFeriadoSeleccionado;
            setPaCommandModificar( "Prender" );
            setPaCommandAgregar( "Apagar" );
        }
        // lista = getHelper().obtenerFeriado();
        return null;
    }

    public String onClickEliminar() {
        CoreTable tabla = ( CoreTable ) JsfUtils.findComponent( "tblFeriados" );
        List<FeriadoBO> listafr;
        listafr = JsfUtils.getObjetosSeleccionados( tabla );
        if ( listafr != null && listafr.size() == 1 ) {
            feriadoSeleccionado = listafr.get( 0 );
        }
        if ( feriadoSeleccionado != null ) {
            try {
                getFacade().eliminarFeriado( feriadoSeleccionado.getFechaFeriado() );
            } catch ( Exception e ) {
                MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
                mbError.showError( BackingMantenedorFeriados.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
            }
        }
        try {
            lista = getFacade().obtenerFeriado();
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingMantenedorFeriados.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        return null;
    }
    //ACCESORES    

    public List<FeriadoBO> getLista() {
        return this.lista;
    }

    public void setLista( List<FeriadoBO> lista ) {
        this.lista = lista;
    }

    public List<SelectItem> getTiposFeriado() {
        return this.tiposFeriado;
    }

    public void setTiposFeriado( List<SelectItem> tiposFeriado ) {
        this.tiposFeriado = tiposFeriado;
    }

    public String getTipoFeriadoSeleccionado() {
        return this.tipoFeriadoSeleccionado;
    }

    public void setTipoFeriadoSeleccionado( String tipoFeriadoSeleccionado ) {
        this.tipoFeriadoSeleccionado = tipoFeriadoSeleccionado;
    }

    public Date getFechaFeriado() {
        return this.fechaFeriado;
    }

    public void setFechaFeriado( Date fechaFeriado ) {
        this.fechaFeriado = fechaFeriado;
    }

    public String getDescripcionFeriado() {
        return this.descripcionFeriado;
    }

    public void setDescripcionFeriado( String descripcionFeriado ) {
        this.descripcionFeriado = descripcionFeriado;
    }

    public String getTipoFeriado() {
        return this.tipoFeriado;
    }

    public void setTipoFeriado( String tipoFeriado ) {
        this.tipoFeriado = tipoFeriado;
    }

    public String getDescTipoFeriado() {
        return this.descTipoFeriado;
    }

    public void setDescTipoFeriado( String descTipoFeriado ) {
        this.descTipoFeriado = descTipoFeriado;
    }

    public String getPaCommandModificar() {
        return this.paCommandButtonModificar;
    }

    public void setPaCommandModificar( String paCommandButtonModificar ) {
        this.paCommandButtonModificar = paCommandButtonModificar;
    }

    public String getPaCommandAgregar() {
        return this.paCommandButtonAgregar;
    }

    public void setPaCommandAgregar( String paCommandButtonAgregar ) {
        this.paCommandButtonAgregar = paCommandButtonAgregar;
    }

    public void setRegistrosPagina( Long registrosPagina ) {
        this.registrosPagina = registrosPagina;
    }

    public Long getRegistrosPagina() {
        return registrosPagina;
    }
}
