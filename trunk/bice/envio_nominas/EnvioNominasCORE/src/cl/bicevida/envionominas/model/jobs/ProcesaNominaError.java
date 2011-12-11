package cl.bicevida.envionominas.model.jobs;

import cl.bicevida.envionominas.model.bo.ArchivoBO;
import cl.bicevida.envionominas.model.bo.archivo.NominaErrores;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;
import cl.bicevida.envionominas.model.logic.EnvioNominasManager;

import cl.bicevida.envionominas.model.utils.XMLUtils;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.SessionContext;
import org.apache.log4j.Logger;

public class ProcesaNominaError implements Runnable {
    
    private static final Logger logger = Logger.getLogger(ProcesaNominaError.class);
    private SessionContext contextoEJB;
    private EnvioNominasManager helper;
    private XMLUtils util;
    
    public ProcesaNominaError(SessionContext _contexto ) {
        contextoEJB = _contexto;
        util = new XMLUtils();
    }
    
    public void run() {
        List<ArchivoBO> archivosErrores = new ArrayList<ArchivoBO>();
        String idTipo = "2";
        try {
            helper = new EnvioNominasManager( contextoEJB );
            archivosErrores = helper.queryArchivosByTipo(idTipo);
            logger.info("Se han encontrado las siguientes nominas [ " + archivosErrores.size() + " ]");
        } catch (DAOException e) {
            logger.error("[Exception] " + e.getMessage() );
        }
        if(archivosErrores != null) {
            NominaErrores _type=null;
            for(ArchivoBO archivo : archivosErrores) {
                _type = util.archivoNominasErrores(archivo.getCuerpoArchivo() );
                if(helper.ejecutarErroresNomina(_type)) {
                    helper.queryActualizaEvmnArchivosNominas(archivo.getFechaCreacion(), archivo.getNombre());
                } 
            }
        }
    }
}
