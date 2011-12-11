package cl.bicevida.envionominas.model.jobs;

import cl.bicevida.envionominas.model.exceptions.EnvioNominasException;
import cl.bicevida.envionominas.model.logic.EnvioNominasManager;

import javax.ejb.SessionContext;

import org.apache.log4j.Logger;

public class ReprocesoEnvioJOB implements Runnable {
    private static final Logger log = Logger.getLogger( ReprocesoEnvioJOB.class );
    private EnvioNominasManager manager;
    private SessionContext contextoEJB;
    private Long idProceso;

    public ReprocesoEnvioJOB( SessionContext _contexto, Long _idProceso ) {
        contextoEJB = _contexto;
        idProceso = _idProceso;
    }

    public void run() {
        manager = new EnvioNominasManager( contextoEJB );
        try {
            manager.reprocesarEnvio( idProceso );
        } catch ( EnvioNominasException e ) {
            log.error( "EXCEPTION:[" + e.getMessage() + "]", e );
        }
    }
}
