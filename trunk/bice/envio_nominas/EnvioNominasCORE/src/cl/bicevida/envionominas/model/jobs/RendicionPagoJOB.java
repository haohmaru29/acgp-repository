package cl.bicevida.envionominas.model.jobs;

import cl.bicevida.envionominas.model.exceptions.EnvioNominasException;
import cl.bicevida.envionominas.model.logic.EnvioNominasManager;

import javax.ejb.SessionContext;

import org.apache.log4j.Logger;

public class RendicionPagoJOB implements Runnable {
    private static final Logger log = Logger.getLogger( RendicionCargaJOB.class );
    private SessionContext contexto;
    private Long idProceso;

    public RendicionPagoJOB( SessionContext _contexto, Long _idProceso ) {
        contexto = _contexto;
        idProceso = _idProceso;
    }

    public void run() {
        EnvioNominasManager manager = new EnvioNominasManager( contexto );
        try {
            manager.rindePagoNomina( idProceso );
        } catch ( EnvioNominasException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
    }
}
