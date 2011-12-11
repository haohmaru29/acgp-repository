package cl.bicevida.envionominas.model.jobs;

import cl.bicevida.envionominas.model.bo.ArchivoBO;
import cl.bicevida.envionominas.model.bo.RindeNominaOutputBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;
import cl.bicevida.envionominas.model.logic.EnvioNominasManager;

import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.RindeNominaOutputType;
import cl.bicevida.envionominas.model.utils.XMLUtils;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;

import org.apache.log4j.Logger;

public class ActualizaNominasJOB implements Runnable {

    private static final Logger logger = Logger.getLogger(ActualizaNominasJOB.class);
    private SessionContext contextoEJB;
    private EnvioNominasManager helper;
    private XMLUtils util;

    public ActualizaNominasJOB( SessionContext _contexto ) {
        contextoEJB = _contexto;
        util = new XMLUtils();
    }
    
    public void run() {
        List<ArchivoBO> archivos = null;
        String idTipo = "1";
        try {
            helper = new EnvioNominasManager( contextoEJB );
            archivos = helper.queryArchivosByTipo(idTipo);
            logger.info("Se han encontrado las siguientes nominas [ " + archivos.size() + " ]");
        } catch (DAOException e) {
            logger.error("EXCEPTION [" + e.getMessage() + "]");
        }
        if(archivos != null ) {
            RindeNominaOutputBO _type=null;
            for(ArchivoBO archivo: archivos) {
                _type = util.archivoNominasOutput(archivo.getCuerpoArchivo());
                try {
                    if(helper.actualizaEstadosArchivosNomina( _type ) ) {
                        helper.queryActualizaEvmnArchivosNominas(archivo.getFechaCreacion(), archivo.getNombre());
                    }
                } catch (DAOException e) {
                    logger.error("EXCEPTION [" + e.getMessage() + "]");
                }
            }
        }
    }
    
}