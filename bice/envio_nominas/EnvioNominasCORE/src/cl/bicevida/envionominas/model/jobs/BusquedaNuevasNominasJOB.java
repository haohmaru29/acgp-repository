package cl.bicevida.envionominas.model.jobs;

import cl.bicevida.envionominas.model.bo.NominaBO;
import cl.bicevida.envionominas.model.exceptions.EnvioNominasException;
import cl.bicevida.envionominas.model.logic.EnvioNominasManager;

import java.util.List;

import javax.ejb.SessionContext;

import org.apache.log4j.Logger;

public class BusquedaNuevasNominasJOB implements Runnable {
    private static final Logger log = Logger.getLogger( BusquedaNuevasNominasJOB.class );
    private SessionContext contextoEJB;
    private EnvioNominasManager helper;

    public BusquedaNuevasNominasJOB( SessionContext _contexto ) {
        contextoEJB = _contexto;
    }

    public void run() {
        List<NominaBO> nominas = null;
        helper = new EnvioNominasManager( contextoEJB );
        try {
            nominas = helper.buscarNuevasNominas();
            log.info("La busqueda encontro [" + nominas.size() + "] nominas para procesar.");
        } catch ( EnvioNominasException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        if ( nominas != null ) {
            for ( NominaBO nomina: nominas ) {
                ProcesaNominaJOB envio = new ProcesaNominaJOB( contextoEJB, nomina.getId() );
                envio.run();
            }
        }
    }
}
