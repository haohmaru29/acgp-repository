package cl.bicevida.envionominas.view.jsf.mb;

import cl.bicevida.core.view.jsf.mb.MbError;
import cl.bicevida.core.view.jsf.utils.JsfUtils;
import cl.bicevida.core.view.utils.BvWebLog;
import cl.bicevida.envionominas.model.bo.ArchivoBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;
import cl.bicevida.envionominas.view.facade.EnvioNominasBusinessFacade;
import cl.bicevida.envionominas.view.facade.EnvioNominasBusinessFacadeImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletResponse;

import oracle.adf.view.faces.component.core.data.CoreTable;

public class BackingArchivosNominas extends AbstractBacking {
    private List<ArchivoBO> lista;
    private Long registrosPagina;
    private EnvioNominasBusinessFacade facade;
    private CoreTable tblArchivosNominas;

    public BackingArchivosNominas() {
        facade = new EnvioNominasBusinessFacadeImpl();
        EnvioNominasConfig parametros = new EnvioNominasConfig();
        registrosPagina = Long.valueOf( parametros.get( EnvioNominasConfig.REGISTROS_PAGINA ) );
        cargarPagina();
    }

    private void cargarPagina() {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "cargarPagina()" );
        try {
            lista = getFacade().obtenerArchivosNominas();
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingArchivosNominas.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
    }

    public void actualizaTabla( ActionEvent actionEvent ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "lista : [" + lista.size() + "]" );
        cargarPagina();
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "lista : [" + lista.size() + "]" );
    }

    public void descargarNomina( ActionEvent event ) {
        ArchivoBO archivoSeleccionado = null;
        CoreTable tabla = ( CoreTable ) JsfUtils.findComponent( "tblArchivosNominas" );
        List<ArchivoBO> lista = ( List<ArchivoBO> ) JsfUtils.getObjetosSeleccionados( tabla );
        if ( lista != null && lista.size() == 1 ) {
            archivoSeleccionado = lista.get( 0 );
        }
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = ( HttpServletResponse ) context.getExternalContext().getResponse();
        response.setHeader( "Content-Disposition", "attachment; filename=\"" + archivoSeleccionado.getNombre() + "\"" );
        response.setContentType( "text/plain" );
        InputStream stream = null;
        try {
            stream = facade.obtenerArchivoNomina( archivoSeleccionado.getNombre() );
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingArchivosNominas.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        int length = -1;
        int size = 1024;
        byte[] buffer = new byte[ size ];
        try {
            while ( ( length = stream.read( buffer ) ) != -1 ) {
                outStream.write( buffer, 0, length );
                outStream.flush();
            }
        } catch ( IOException e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingArchivosNominas.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        try {
            stream.close();
        } catch ( IOException e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingArchivosNominas.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        try {
            outStream.close();
        } catch ( IOException e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingArchivosNominas.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        try {
            outStream.writeTo( response.getOutputStream() );
        } catch ( IOException e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( BackingArchivosNominas.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        } finally {
            try {
                outStream.flush();
                outStream.close();
            } catch ( IOException e ) {
                MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
                mbError.showError( BackingArchivosNominas.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
            }
            context.responseComplete();
        }
    }
    //ACCESORES

    public void setLista( List lista ) {
        this.lista = lista;
    }

    public List getLista() {
        return lista;
    }

    public void setRegistrosPagina( Long registrosPagina ) {
        this.registrosPagina = registrosPagina;
    }

    public Long getRegistrosPagina() {
        return registrosPagina;
    }

    public void setTblArchivosNominas( CoreTable tblArchivosNominas ) {
        this.tblArchivosNominas = tblArchivosNominas;
    }

    public CoreTable getTblArchivosNominas() {
        return tblArchivosNominas;
    }
}
