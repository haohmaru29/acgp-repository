package cl.bicevida.envionominas.view.jsf.mb;

import cl.bicevida.core.view.jsf.mb.MbError;
import cl.bicevida.core.view.jsf.mb.MbUserInfo;
import cl.bicevida.core.view.jsf.utils.JsfUtils;
import cl.bicevida.core.view.utils.BvWebLog;
import cl.bicevida.envionominas.model.bo.ListaDistribucionBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;

import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import oracle.adf.view.faces.component.core.data.CoreTable;

public class BackingMantenedorListasDistribucion extends AbstractBacking {
    private MbUserInfo mbUserInfo;
    private List<ListaDistribucionBO> lista;
    private List<SelectItem> nombresOrigen;
    private String nombreListaDistribucion;
    private String origenLista;
    private String nombreLista;
    private String origenSeleccionado;
    private ListaDistribucionBO ListaDistribucionSeleccionada;
    private Long idLista;
    private String paCommandButtonModificar;
    private String paCommandButtonAgregar;
    private String origenListaOriginal;
    private String nombreListaOriginal;
    private Long registrosPagina;

    public BackingMantenedorListasDistribucion() {
        mbUserInfo = ( MbUserInfo ) JsfUtils.getValue( "#{MbUserInfo}" );
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "USUARIO:" + mbUserInfo.getUserName() + "/RUT:" + mbUserInfo.getRut() + "/PERFIL:" + ( mbUserInfo.isUserInRole( "BICEVIDANET" ) ? "BICEVIDANET" : mbUserInfo.isUserInRole( "INTRANET" ) ? "INTRANET" : mbUserInfo.isUserInRole( "BICEVIDA" ) ? "BICEVIDA" : "ROL NO VALIDO" ) );
        EnvioNominasConfig parametros = new EnvioNominasConfig();
        registrosPagina = Long.valueOf( parametros.get( EnvioNominasConfig.REGISTROS_PAGINA ) );
        cargarPagina();
    }

    private void cargarPagina() {
        try {
            nombresOrigen = getItemsOrigen();
            lista = getFacade().obtenerListasDistribucion();
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingMantenedorListasDistribucion.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        setPaCommandModificar( "Apagar" );
        setPaCommandAgregar( "Prender" );
    }
    //EVENTOS

    public void ejecutarBusqueda( ActionEvent actionEvent ) {
        Long origenLista = null;
        Long nombreLista = null;
        if ( getOrigenLista() != null ) {
            origenLista = Long.valueOf( getOrigenLista() );
        }
        if ( getNombreLista() != null ) {
            nombreLista = Long.valueOf( getNombreLista() );
        }
    }

    /**
     * 
     * @param actionEvent
     */
    public void limpiarFormulario( ActionEvent actionEvent ) {
        if ( nombreListaDistribucion != null ) {
            nombreListaDistribucion = null;
        }
        if ( origenSeleccionado != null ) {
            origenSeleccionado = null;
        }
        setPaCommandModificar( "Apagar" );
        setPaCommandAgregar( "Prender" );
    }

    public void ejecutarAgregar( ActionEvent actionEvent ) {
        String nombreLista = null;
        String origenLista = null;
        if ( getOrigenSeleccionado() != null ) {
            origenLista = getOrigenSeleccionado().toString();
            if ( getNombreListaDistribucion() != null ) {
                nombreLista = String.valueOf( getNombreListaDistribucion() );
                //revisar si origen ya esta ingresado
                try {
                    getFacade().agregarListaDistribucion( origenLista, nombreLista );
                    lista = getFacade().obtenerListasDistribucion();
                    if ( nombreListaDistribucion != null )
                        nombreListaDistribucion = null;
                    if ( origenSeleccionado != null )
                        origenSeleccionado = null;
                    setPaCommandModificar( "Apagar" );
                    setPaCommandAgregar( "Prender" );
                } catch ( Exception e ) {
                    MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
                    mbError.showError( BackingMantenedorListasDistribucion.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
                }
            }
        }
    }

    public void ejecutarModificar( ActionEvent actionEvent ) {
        Boolean cambio = false;
        String nombreLista = null;
        String origenLista = null;
        Long idEncontrado = null;
        if ( getOrigenSeleccionado() != null ) {
            origenLista = getOrigenSeleccionado().toString();
            if ( getNombreListaDistribucion() != null ) {
                nombreLista = String.valueOf( getNombreListaDistribucion() );
                //revisar si correo ya esta ingresada
                try {
                    idEncontrado = getFacade().buscarListaDistribucion( getOrigenSeleccionado() );
                } catch ( Exception e ) {
                    MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
                    mbError.showError( BackingMantenedorListasDistribucion.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
                }
                if ( idEncontrado == null || idEncontrado == idLista ) {
                    try {
                        getFacade().modificarListaDistribucion( idLista, origenLista, nombreLista );
                        lista = getFacade().obtenerListasDistribucion();
                    } catch ( Exception e ) {
                        MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
                        mbError.showError( BackingMantenedorListasDistribucion.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
                    }
                    cambio = true;
                    if ( nombreListaDistribucion != null )
                        nombreListaDistribucion = null;
                    if ( origenSeleccionado != null )
                        origenSeleccionado = null;
                    setPaCommandModificar( "Apagar" );
                    setPaCommandAgregar( "Prender" );
                }
            }
        }
        if ( cambio == false ) {
            setNombreListaDistribucion( nombreListaOriginal );
            origenSeleccionado = origenListaOriginal;
        }
    }

    public String onClickEditar() {
        nombreListaOriginal = null;
        origenListaOriginal = null;
        CoreTable tabla = ( CoreTable ) JsfUtils.findComponent( "tblListasDistribucion" );
        List<ListaDistribucionBO> listadis;
        listadis = JsfUtils.getObjetosSeleccionados( tabla );
        if ( listadis != null && listadis.size() == 1 ) {
            ListaDistribucionSeleccionada = listadis.get( 0 );
        }
        if ( ListaDistribucionSeleccionada != null ) {
            nombreListaDistribucion = ListaDistribucionSeleccionada.getNombreLista();
            origenSeleccionado = ListaDistribucionSeleccionada.getOrigenLista();
            idLista = ListaDistribucionSeleccionada.getId();
            nombreListaOriginal = nombreListaDistribucion;
            origenListaOriginal = origenSeleccionado;
            //para que aparezca boton modificar
            setPaCommandModificar( "Prender" );
            setPaCommandAgregar( "Apagar" );
        }
        return null;
    }

    public String onClickEliminar() {
        CoreTable tabla = ( CoreTable ) JsfUtils.findComponent( "tblListasDistribucion" );
        List<ListaDistribucionBO> listadis;
        listadis = JsfUtils.getObjetosSeleccionados( tabla );
        if ( listadis != null && listadis.size() == 1 ) {
            ListaDistribucionSeleccionada = listadis.get( 0 );
        }
        if ( ListaDistribucionSeleccionada != null ) {
            try {
                getFacade().eliminarListaDistribucion( ListaDistribucionSeleccionada.getId() );
            } catch ( Exception e ) {
                MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
                mbError.showError( BackingMantenedorListasDistribucion.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
            }
        }
        try {
            lista = getFacade().obtenerListasDistribucion();
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingMantenedorListasDistribucion.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        return null;
    }
    //ACCESORES

    public List<ListaDistribucionBO> getLista() {
        return this.lista;
    }

    public void setLista( List<ListaDistribucionBO> lista ) {
        this.lista = lista;
    }

    public List<SelectItem> getNombresOrigen() {
        return this.nombresOrigen;
    }

    public void setNombresOrigen( List<SelectItem> nombresOrigen ) {
        this.nombresOrigen = nombresOrigen;
    }

    public String getOrigenSeleccionado() {
        return this.origenSeleccionado;
    }

    public void setOrigenSeleccionado( String origenSeleccionado ) {
        this.origenSeleccionado = origenSeleccionado;
    }

    public String getNombreLista() {
        return this.nombreLista;
    }

    public void setNombreLista( String nombreLista ) {
        this.nombreLista = nombreLista;
    }

    public String getOrigenLista() {
        return this.origenLista;
    }

    public void setOrigenLista( String origenLista ) {
        this.origenLista = origenLista;
    }

    public String getNombreListaDistribucion() {
        return this.nombreListaDistribucion;
    }

    public void setNombreListaDistribucion( String nombreListaDistribucion ) {
        this.nombreListaDistribucion = nombreListaDistribucion;
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
