package cl.bicevida.envionominas.model.logic;

import cl.bicevida.envionominas.model.bo.EventoBO;

import cl.bicevida.envionominas.model.config.EnvioNominasConfig;

import cl.bicevida.envionominas.model.enums.TipoEventoEnum;

import cl.bicevida.envionominas.model.jobs.BusquedaNuevasNominasJOB;
import cl.bicevida.envionominas.model.jobs.ProcesaNominaError;
import cl.bicevida.envionominas.model.jobs.RendicionCargaJOB;
import cl.bicevida.envionominas.model.jobs.RendicionPagoJOB;
import cl.bicevida.envionominas.model.jobs.ReprocesoEnvioJOB;
import cl.bicevida.envionominas.model.jobs.ReprocesoNominaJOB;

import cl.bicevida.envionominas.model.utils.SimpleDate;

import java.util.Date;

import javax.ejb.SessionContext;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import org.apache.log4j.Logger;

/**
 * Centraliza el agendamiento y manejo de eventos del proceso de envio de nominas.
 * 
 * Registro de Versiones
 * - 1.0 28/02/2011 Giogio Gortaire : Version inicial.
 */
public class SchedulerManager {
    static int busquedasActivas = 0;
    private static final Logger log = Logger.getLogger( SchedulerManager.class );
    private EnvioNominasConfig config;

    /**
     * Constructor por defecto.
     */
    public SchedulerManager() {
        config = new EnvioNominasConfig();
    }

    /**
     * Inicializa el scheduler cada vez que se reinicia la apliacion.
     * @param ctx Contexto EJB de la aplicacion.
     */
    public void inicializar( SessionContext ctx ) {
        BusquedaNuevasNominasJOB monitor = new BusquedaNuevasNominasJOB( ctx );
        ProcesaNominaError actualiza = new  ProcesaNominaError( ctx );
        monitor.run();
        actualiza.run();
        if(config.get(EnvioNominasConfig.LIMPIAR_SCHEDULER).equals("TRUE")){
            for ( Object obj: ctx.getTimerService().getTimers() ) {
                Timer timer = ( Timer ) obj;
                timer.cancel();
            }
        }
        agendarBusquedaNuevasNominas( ctx );
    }

    /**
     * Maneja el vencimiento de eventos.
     * @param ctx Contexto EJB.
     * @param timer Evento agendado.
     */
    public void handleTimeout( SessionContext ctx, Timer timer ) {
        EventoBO evento = ( EventoBO ) timer.getInfo();
        log.info( "handleTimeout([" + evento.getTarea() + "],[" + evento.getId() + "])" );
        if ( TipoEventoEnum.BUSQUEDA_NUEVAS_NOMINAS.equals( evento.getTarea() ) ) {
            timer.cancel();
            synchronized(this){
                busquedasActivas-=1;  
                if(busquedasActivas==0){
                    agendarBusquedaNuevasNominas( ctx );
                }
            }
            BusquedaNuevasNominasJOB monitor = new BusquedaNuevasNominasJOB( ctx );
            monitor.run();
        } else if ( TipoEventoEnum.REPROCESAR_NOMINA.equals( evento.getTarea() ) ) {
            ReprocesoNominaJOB reprocesador = new ReprocesoNominaJOB( ctx, evento.getId() );
            reprocesador.run();
        } else if ( TipoEventoEnum.REPROCESAR_ENVIO.equals( evento.getTarea() ) ) {
            ReprocesoEnvioJOB reprocesador = new ReprocesoEnvioJOB( ctx, evento.getId() );
            reprocesador.run();
        } else if ( TipoEventoEnum.RENDICION_CARGA.equals( evento.getTarea() ) ) {
            RendicionCargaJOB rendicionCarga = new RendicionCargaJOB( ctx, evento.getId() );
            rendicionCarga.run();
        } else if ( TipoEventoEnum.RENDICION_PAGO.equals( evento.getTarea() ) ) {
            RendicionPagoJOB rendicionPago = new RendicionPagoJOB( ctx, evento.getId() );
            rendicionPago.run();
        }
    }

    /**
     * 
     * @param ctx
     * @param initialExpiration
     * @param intervalDuration
     * @param evento
     */
    public void startTimer( SessionContext ctx, Date initialExpiration, Long intervalDuration, EventoBO evento ) {
        Timer timer = null;
        if ( initialExpiration != null && intervalDuration != null ) {
            timer = ctx.getTimerService().createTimer( initialExpiration, intervalDuration.longValue(), evento );
        }
        log.info( "evento [" + evento.getTarea() + "] agendado para [" + evento.getId() + "] en ["+ timer.getNextTimeout() +"]" );
    }

    /**
     * 
     * @param ctx
     * @param expiration
     * @param evento
     */
    public void startTimer( SessionContext ctx, Date expiration, EventoBO evento ) {
        Timer timer = null;
        if ( expiration != null ) {
            timer = ctx.getTimerService().createTimer( expiration, evento );
        }
        log.info( "evento [" + evento.getTarea() + "] agendado para [" + evento.getId() + "] en ["+ timer.getNextTimeout() +"]" );
    }

    /**
     * 
     * @param ctx
     * @param initialDuration
     * @param intervalDuration
     * @param evento
     */
    public void startTimer( SessionContext ctx, Long initialDuration, Long intervalDuration, EventoBO evento ) {
        Timer timer = null;
        if ( initialDuration != null && intervalDuration != null ) {
            timer = ctx.getTimerService().createTimer( initialDuration.longValue(), intervalDuration.longValue(), evento );
        }
        log.info( "evento [" + evento.getTarea() + "] agendado para [" + evento.getId() + "] en ["+ timer.getNextTimeout() +"]" );
    }

    /**
     * 
     * @param ctx
     * @param duration
     * @param evento
     */
    public void startTimer( SessionContext ctx, Long duration, EventoBO evento ) {
        Timer timer = null;
        if ( duration != null ) {
            timer = ctx.getTimerService().createTimer( duration.longValue(), evento );
        }
        log.info( "evento [" + evento.getTarea() + "] agendado para [" + evento.getId() + "] en ["+ timer.getNextTimeout() +"]" );
    }

    /**
     * 
     * @param ctx
     * @param _idProceso
     */
    public void cancelarTareasProceso( SessionContext ctx, Long _idProceso ) {
        TimerService timerService = ctx.getTimerService();
        for ( Object obj: timerService.getTimers() ) {
            Timer timer = ( Timer ) obj;
            EventoBO evento = ( EventoBO ) timer.getInfo();
            if ( evento != null ) {
                if ( _idProceso.equals( evento.getId() ) ) {
                    timer.cancel();
                }
            }
        }
    }

    /**
     * 
     * @param _contexto
     * @param _idNnomina
     */
    public void agendarReprocesoNomina( SessionContext _contexto, Long _idNnomina ) {
        SimpleDate fechaActual = new SimpleDate( new Date() );
        EventoBO evento = new EventoBO();
        evento.setTarea( TipoEventoEnum.REPROCESAR_NOMINA );
        evento.setId( _idNnomina );
        startTimer( _contexto, fechaActual.addMinutes( Double.valueOf( config.get( EnvioNominasConfig.INTERVALO_REPROCESO_ENVIO ) ) ).toDate(), evento );
    }

    /**
     * 
     * @param _contexto
     * @param _idProceso
     */
    public void agendarReproceso( SessionContext _contexto, Long _idProceso ) {
        SimpleDate fechaActual = new SimpleDate( new Date() );
        EventoBO evento = new EventoBO();
        evento.setTarea( TipoEventoEnum.REPROCESAR_ENVIO );
        evento.setId( _idProceso );
        startTimer( _contexto, fechaActual.addMinutes( Double.valueOf( config.get( EnvioNominasConfig.INTERVALO_REPROCESO_ENVIO ) ) ).toDate(), evento );
    }

    /**
     * 
     * @param _contexto
     * @param _idProceso
     */
    public void agendarRendicionCarga( SessionContext _contexto, Long _idProceso ) {
        SimpleDate fechaActual = new SimpleDate( new Date() );
        EventoBO evento = new EventoBO();
        evento.setTarea( TipoEventoEnum.RENDICION_CARGA );
        evento.setId( _idProceso );
        startTimer( _contexto, fechaActual.addMinutes( Double.valueOf( config.get( EnvioNominasConfig.INTERVALO_RENDICION_CARGA ) ) ).toDate(), evento );
    }

    /**
     * 
     * @param _contexto
     */
    private void agendarBusquedaNuevasNominas( SessionContext _contexto ) {
        SimpleDate fechaActual = new SimpleDate( new Date() );
        EventoBO evento = new EventoBO();
        evento.setTarea( TipoEventoEnum.BUSQUEDA_NUEVAS_NOMINAS );
        startTimer( _contexto, fechaActual.addMinutes( Double.valueOf( config.get( EnvioNominasConfig.INTERVALO_PROCESO_BUSQUEDA ) ) ).toDate(), evento );
        busquedasActivas+=1;
    }

    void agendarRendicionPago( SessionContext _contexto, Long _idNomina, Date _fechaPago ) {
        //SimpleDate fechaInicial = new SimpleDate(_fechaPago);
        SimpleDate fechaInicial = new SimpleDate(new Date());
        fechaInicial.setHour(Integer.parseInt(config.get(EnvioNominasConfig.HORA_INICIO_RENDICION)));
        fechaInicial.setMin(0);
        fechaInicial.setSec(0);
        EventoBO evento = new EventoBO();
        evento.setId(_idNomina);
        evento.setTarea(TipoEventoEnum.RENDICION_PAGO);
        startTimer(_contexto, fechaInicial.toDate(), evento);
    }

    void agendarRendicionPago( SessionContext _contexto, Long _idNomina ) {
        SimpleDate fechaInicial = new SimpleDate(new Date());
        EventoBO evento = new EventoBO();
        evento.setId(_idNomina);
        evento.setTarea(TipoEventoEnum.RENDICION_PAGO);
        startTimer(_contexto, fechaInicial.addMinutes(Double.valueOf(config.get(EnvioNominasConfig.INTERVALO_RENDICION_PAGO))).toDate(), evento);
    }
}
