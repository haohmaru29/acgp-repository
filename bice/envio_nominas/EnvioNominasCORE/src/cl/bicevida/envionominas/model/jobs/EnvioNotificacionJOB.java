package cl.bicevida.envionominas.model.jobs;

import cl.bicevida.envionominas.model.bo.NominaBO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.DAOFactory;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;
import cl.bicevida.envionominas.model.exceptions.EnvioNominasException;
import cl.bicevida.envionominas.model.logic.EnvioCorreosManager;

import org.apache.log4j.Logger;

public class EnvioNotificacionJOB implements Runnable {
    private Long idNomina;
    private DAOFactory factory = DAOFactory.getDAOFactory( DAOFactory.ORACLE );
    private static final Logger log = Logger.getLogger( EnvioNotificacionJOB.class );
    
    public EnvioNotificacionJOB(Long _idNomina){
        this.idNomina = _idNomina;
    }
    
    public void run() {
        NominaBO nomina = null;
        try {
            nomina = factory.getNominaDAO().queryNominaById( idNomina );
            nomina.setProcesos( factory.getProcesosEnvioDAO().queryProcesosByNomina( nomina.getId() ) );
        } catch ( DAOException e ) {
            log.error( "EXCEPTION: Ha ocurrido una excepcion al recuperar la nomina.[" + e.getMessage() + "]", e );
            
        }
        if ( nomina != null ) {
            EnvioCorreosManager correoHelper = new EnvioCorreosManager();
            try {
                correoHelper.enviaCorreo( nomina );
            } catch ( EnvioNominasException e ) {
                log.error( "EXCEPTION: Ha ocurrido una excepcion al enviar la notificacion.[" + e.getMessage() + "]", e );
                
            }
        } else {
            log.error( "La nomina id:[" + idNomina + "] no existe o no se logro recuperar." );
            
        }
        log.info( "Notificacion nomina [" + nomina.getId() + "], estado [" + nomina.getEstado().getNombre() + "] enviada OK" );
    }
}
