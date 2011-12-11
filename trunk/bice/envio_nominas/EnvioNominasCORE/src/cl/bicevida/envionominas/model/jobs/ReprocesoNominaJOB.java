package cl.bicevida.envionominas.model.jobs;

import cl.bicevida.envionominas.model.exceptions.EnvioNominasException;
import cl.bicevida.envionominas.model.logic.EnvioNominasManager;

import javax.ejb.SessionContext;

import org.apache.log4j.Logger;

public class ReprocesoNominaJOB implements Runnable {
    private static final Logger log = Logger.getLogger( ReprocesoNominaJOB.class );
    private Long idNomina;
    private EnvioNominasManager manager;
    private SessionContext contextoEJB;

    public ReprocesoNominaJOB( SessionContext _contexto, Long _idNomina ) {
        contextoEJB = _contexto;
        idNomina = _idNomina;
    }

    public void run() {
        manager = new EnvioNominasManager( contextoEJB );
        try {
            manager.reprocesarNomina( idNomina );
        } catch ( EnvioNominasException e ) {
            log.error( "EXCEPTION:[" + idNomina + "]", e );
        }
    }
}
