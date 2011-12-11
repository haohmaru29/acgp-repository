package cl.bicevida.envionominas.model.jobs;

import cl.bicevida.envionominas.model.exceptions.EnvioNominasException;
import cl.bicevida.envionominas.model.logic.EnvioNominasManager;

import javax.ejb.SessionContext;

import org.apache.log4j.Logger;

public class ProcesaNominaJOB implements Runnable {
    private static final Logger log = Logger.getLogger( ProcesaNominaJOB.class );
    private EnvioNominasManager helper;
    private SessionContext contextoEJB;
    private Long idNomina;

    public ProcesaNominaJOB( SessionContext _contexto, Long _idNomina ) {
        this.contextoEJB = _contexto;
        this.idNomina = _idNomina;
    }

    public void run() {
        helper = new EnvioNominasManager( contextoEJB );
        try {
            helper.procesarNomina( idNomina );
        } catch ( EnvioNominasException e ) {
            log.error( "Error en procesamiento de nomina [" + idNomina + "] : [" + e.getMessage() + "]", e );
        }
    }
}
