package cl.bicevida.envionominas.model.jobs;

import cl.bicevida.envionominas.model.exceptions.EnvioNominasException;
import cl.bicevida.envionominas.model.logic.EnvioNominasManager;

import javax.ejb.SessionContext;

import org.apache.log4j.Logger;

public class RendicionCargaJOB implements Runnable {
    private static final Logger log = Logger.getLogger( RendicionCargaJOB.class );
    private SessionContext contexto;
    private Long idProceso;

    public RendicionCargaJOB( SessionContext _contexto, Long _idProceso ) {
        contexto = _contexto;
        idProceso = _idProceso;
    }

    public void run() {
        EnvioNominasManager manager = new EnvioNominasManager( contexto );
        try {
            manager.rindeCargaProceso( idProceso );
        } catch ( EnvioNominasException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
    }
}
