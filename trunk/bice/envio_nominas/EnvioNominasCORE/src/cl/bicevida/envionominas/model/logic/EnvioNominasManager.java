package cl.bicevida.envionominas.model.logic;

import cl.bicevida.envionominas.model.bo.ArchivoBO;
import cl.bicevida.envionominas.model.bo.BancoProcesoBO;
import cl.bicevida.envionominas.model.bo.EstadoProcesoBO;
import cl.bicevida.envionominas.model.bo.FeriadoBO;
import cl.bicevida.envionominas.model.bo.NominaBO;
import cl.bicevida.envionominas.model.bo.ProcesoEnvioBO;
import cl.bicevida.envionominas.model.bo.RegistroNominaBO;
import cl.bicevida.envionominas.model.bo.RindeNominaOutputBO;
import cl.bicevida.envionominas.model.bo.TransaccionPagoBO;
import cl.bicevida.envionominas.model.bo.archivo.NominaErrores;
import cl.bicevida.envionominas.model.bo.archivo.RegistroError;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.DAOFactory;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;
import cl.bicevida.envionominas.model.exceptions.AtributoSinValorException;
import cl.bicevida.envionominas.model.exceptions.EnvioNominasException;
import cl.bicevida.envionominas.model.exceptions.EnvioNominasJMSException;
import cl.bicevida.envionominas.model.exceptions.FechaPagoInvalidaException;
import cl.bicevida.envionominas.model.exceptions.ParametroNoEncontradoException;
import cl.bicevida.envionominas.model.jobs.EnvioNotificacionJOB;
import cl.bicevida.envionominas.model.services.ws.proxy.EnvioNominasPort;
import cl.bicevida.envionominas.model.services.ws.proxy.EnvioNominasPortService;
import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.CargaNominaInputType;
import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.CargaNominaOutputType;
import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.ErrorType;
import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.NominaType;
import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.RegistroNominaType;
import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.RindeNominaInputType;
import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.RindeNominaOutputType;
import cl.bicevida.envionominas.model.utils.SimpleDate;
import cl.bicevida.envionominas.model.utils.TraductorWSTypes;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.SessionContext;

import javax.xml.rpc.ServiceException;
import javax.xml.rpc.ServiceFactory;

import org.apache.log4j.Logger;


/**
 * Clase encargada de implementar la logica del proceso de envio y rendicion de 
 * nóminas.
 * 
 * Registro de Versiones
 * - 1.0 28/02/2011 Giogio Gortaire : Version inicial.
 */
public class EnvioNominasManager {
    private static final Logger log = Logger.getLogger( EnvioNominasManager.class );
    private SessionContext contexto;
    private DAOFactory factory;
    private EnvioNominasConfig config;
    private SchedulerManager scheduler;
    private JMSManager jms;
    private BancoProcesoBO bancoProceso;

    /**
     * Constructor de la clase
     * @param _contexto SessionContext del EJB de EnvioNominas.
     */
    public EnvioNominasManager( SessionContext _contexto ) {
        contexto = _contexto;
        config = new EnvioNominasConfig();
        scheduler = new SchedulerManager();
        jms = new JMSManager();
        factory = DAOFactory.getDAOFactory( DAOFactory.ORACLE );
    }

    /**
     * Realiza la busqueda de nominas que no han sido procesadas y que son 
     * informadas con el estado "INGRESADA" desde el sistema EBS.
     * @return Lista de nominas no procesadas. En caso de no encontrar registros
     * retornara una lista nula.
     * @throws EnvioNominasException Excepcion lanzada en caso de ocurrir algun
     * problema en la busqueda.
     */
    public List<NominaBO> buscarNuevasNominas() throws EnvioNominasException {
        try {
            return factory.getNominaDAO().queryNuevasNominas();
        } catch ( DAOException e ) {
            throw new EnvioNominasException( "Ocurrio un error al realizar la busqueda de nuevas nominas : [" + e.getMessage() + "]", e );
        }
    }

    /**
     * Retorna el banco de proceso definido por configuracion.
     * @return Banco de proceso.
     * @throws EnvioNominasException
     */
    private BancoProcesoBO getBancoProceso() throws DAOException {
        if ( bancoProceso == null ) {
            bancoProceso = factory.getBancosProcesoDAO().queryBancoProcesoById( Long.valueOf( config.get( EnvioNominasConfig.BANCO_PROCESO ) ) );
        }
        return bancoProceso;
    }

    /**
     * Realiza el procesamiento de las nuevas nomnas que entran al sistema.
     * @param _idNomina Identificador de la nomina en proceso.
     * @throws EnvioNominasException Excepcion lanzada al ocurrir algun problema
     * en el procesamiento de la nomina.
     */
    public void procesarNomina( Long _idNomina ) throws EnvioNominasException {
        log.info( "Procesando nomina [" + _idNomina + "]" );
        NominaBO nomina = null;
        try {
            nomina = factory.getNominaDAO().queryNominaById( _idNomina );
        } catch ( DAOException e ) {
            scheduler.agendarReprocesoNomina( contexto, _idNomina );
            throw new EnvioNominasException( "Ocurrio un error al cargar la nomina [" + _idNomina + "] : [" + e.getMessage() + "]", e );
        }
        if ( nomina == null ) {
            scheduler.agendarReprocesoNomina( contexto, _idNomina );
            throw new EnvioNominasException( "No se logro obtener la nomina" );
        }
        try {
            validarFechaPago( nomina );
        } catch ( DAOException e ) {
            scheduler.agendarReprocesoNomina( contexto, _idNomina );
            throw new EnvioNominasException( "La nomina [" + _idNomina + "] no se pudo validar por un problema de acceso a datos : [" + e.getMessage() + "]", e );
        } catch ( ParametroNoEncontradoException e ) {
            scheduler.agendarReprocesoNomina( contexto, _idNomina );
            throw new EnvioNominasException( "La nomina [" + _idNomina + "] no se pudo validar por falta de un parametro del banco de proceso : [" + e.getMessage() + "]", e );
        } catch ( FechaPagoInvalidaException e ) {
            cancelarNomina( nomina.getId(), "La nomina no posee un fecha valida : [" + e.getMessage() + "]" );
            new EnvioNotificacionJOB( nomina.getId() ).run();
            throw new EnvioNominasException( "La nomina [" + nomina.getId() + "] posee una fecha de pago invalida : [" + e.getMessage() + "]", e );
        } catch ( AtributoSinValorException e ) {
            scheduler.agendarReprocesoNomina( contexto, _idNomina );
            throw new EnvioNominasException( "La nomina [" + nomina.getId() + "] no se pudo validar por un atributo sin valor : [" + e.getMessage() + "]", e );
        }
        try {
            crearProcesosEnvio( nomina, Boolean.TRUE );
            factory.getNominaDAO().queryActualizaEstadoNomina( _idNomina, Long.valueOf( config.get( EnvioNominasConfig.ID_NOMINA_PENDIENTE ) ) );
        } catch ( DAOException e ) {
            scheduler.agendarReprocesoNomina( contexto, _idNomina );
            throw new EnvioNominasException( "Fallo la creacion de procesos para la nomina [" + nomina.getId() + "] por un problema en el acceso a datos : [" + e.getMessage() + "]", e );
        } catch ( ParametroNoEncontradoException e ) {
            scheduler.agendarReprocesoNomina( contexto, _idNomina );
            throw new EnvioNominasException( "Fallo la creacion de procesos para la nomina [" + _idNomina + "] por falta de un parametro del banco de proceso : [" + e.getMessage() + "]", e );
        }
        for ( ProcesoEnvioBO proceso: nomina.getProcesos() ) {
            proceso.setNomina( nomina );
            try {
                jms.putCargaNomina( new TraductorWSTypes().generarEstructuraCarga( proceso, getBancoProceso()) );
            } catch ( EnvioNominasJMSException e ) {
                log.error( "Fallo el envio de mensaje JMS del proceso [" + proceso.getId() + "] : [" + e.getMessage() + "]", e );
                scheduler.agendarReproceso( contexto, proceso.getId() );
            } catch ( DAOException e ) {
                throw new EnvioNominasException( "Ocurrio un error al recuperar el banco de proceso [" + e.getMessage() + "]", e );
            }
        }
        log.info( "Nomina [" + _idNomina + "] procesada con exito." );
    }

    /**
     * Realiza la validacion de la fecha de envio de la nomina, verificando que
     * esta se encuentre dentro del periodo de envio valido, no coincida con fines
     * de semana ni tampoco con dias feriados.
     * @param _nomina La nomina a evaluar.
     * @throws EnvioNominasException lanzada en caso de que la fecha de pago no
     * sea valida segun los criterios descritos.
     * @throws DAOException lanzada al detectarse algun error en el acceso a datos.
     */
    private void validarFechaPago( NominaBO _nomina ) throws DAOException, ParametroNoEncontradoException, FechaPagoInvalidaException, AtributoSinValorException {
        log.info( "Validando fecha nomina [" + _nomina.getId() + "]" );
        if ( _nomina.getFechaPago() != null ) {
            SimpleDate fechaPago = new SimpleDate( _nomina.getFechaPago() );
            if ( fechaPago.getDayOfWeek() == Calendar.SATURDAY || fechaPago.getDayOfWeek() == Calendar.SUNDAY ) {
                throw new FechaPagoInvalidaException( "La fecha de pago [" + fechaPago.toString() + "] es un dia de fin de semana" );
            }
            for ( FeriadoBO feriado: factory.getFeriadoDAO().queryFeriado() ) {
                if ( fechaPago.equals( new SimpleDate( feriado.getFechaFeriado() ) ) ) {
                    throw new FechaPagoInvalidaException( "La fecha de pago [" + _nomina.getFechaPago() + "] coincide con un dia feriado [" + feriado.getFechaFeriado() + "]" );
                }
            }
            Integer periodoEnvioOtrosBancos = Integer.valueOf( getBancoProceso().getParametro( "periodoEnvioOtrosBancos" ) );
            Integer periodoEnvioMismoBanco = Integer.valueOf( getBancoProceso().getParametro( "periodoEnvioMismoBanco" ) );
            SimpleDate fechaActual = new SimpleDate( new Date() );
            fechaActual.setHour(0);
            fechaActual.setMin(0);
            fechaActual.setSec(0);
            fechaPago.setHour(0);
            fechaPago.setMin(0);
            fechaPago.setSec(0);
            long diasHastaFechaPago = fechaActual.getNumberOfDaysBetweenTwoDates( fechaPago );
            if ( diasHastaFechaPago < periodoEnvioOtrosBancos ) {
                if ( diasHastaFechaPago >= periodoEnvioMismoBanco ) {
                    Map<Long, String> bancosPago = obtenerBancosPagoNomina( _nomina );
                    if ( !( bancosPago.size() == 1 && bancosPago.containsKey( getBancoProceso().getBanco().getId() ) ) ) {
                        throw new FechaPagoInvalidaException( "La nomina [" + _nomina.getId() + "] con fecha [" + _nomina.getFechaPago() + "] se encuentra fuera del plazo de envio." );
                    }
                }else{
                        throw new FechaPagoInvalidaException( "La nomina [" + _nomina.getId() + "] con fecha [" + _nomina.getFechaPago() + "] se encuentra fuera del plazo de envio." );
                    }
            }
        } else {
            throw new AtributoSinValorException( "La fecha de pago [" + _nomina.getFechaPago() + "] no se puede evaluar" );
        }
        log.info( "Fecha de pago nomina [" + _nomina.getId() + "] OK" );
    }

    /**
     * Realiza la division de la nomina, creando procesos de envio de acuerdo a 
     * la restriccion impuesta por el banco de proceso en relacion a la cantidad 
     * de registros maximos por unidad de carga.
     * @param _nomina
     * @throws EnvioNominasException
     */
    private void crearProcesosEnvio( NominaBO _nomina, Boolean divisible ) throws DAOException, ParametroNoEncontradoException {
        log.info( "Creando procesos nomina [" + _nomina.getId() + "]" );
        Long cantidadMaximaRegistrosPorProceso = null;
        List<RegistroNominaBO> registros = null;
        long cantidadProcesosAGenerar = 1;
        registros = factory.getRegistroNominaDAO().queryRegistrosByNomina( _nomina.getId() );
        if ( divisible ) {
            cantidadMaximaRegistrosPorProceso = Long.valueOf( getBancoProceso().getParametro( "cantidadMaximaRegistros" ) );
            if ( ( registros.size() / cantidadMaximaRegistrosPorProceso ) >= Long.valueOf( 1 ) ) {
                cantidadProcesosAGenerar = registros.size() / cantidadMaximaRegistrosPorProceso;
                if ( ( registros.size() % cantidadMaximaRegistrosPorProceso.longValue() ) > 0 ) {
                    cantidadProcesosAGenerar += 1;
                }
            }
            log.info( "Nomina [" + _nomina.getId() + "] se divide en [" + cantidadProcesosAGenerar + "] procesos de envio" );
        }
        for ( int i = 0; i < cantidadProcesosAGenerar; i++ ) {
            ProcesoEnvioBO proceso = new ProcesoEnvioBO();
            proceso.setNomina( _nomina );
            proceso.setBancoProceso( getBancoProceso() );
            proceso.setEstado( EstadoProcesoBO.crear( Long.valueOf( config.get( EnvioNominasConfig.ID_PROCESO_PREPARANDO ) ) ) );
            if ( divisible ) {
                int primerRegistro = ( i * cantidadMaximaRegistrosPorProceso.intValue() ) + 1;
                for ( int j = primerRegistro; ( j < primerRegistro + cantidadMaximaRegistrosPorProceso ) && j <= registros.size(); j++ ) {
                    proceso.addRegistro( registros.get( j - 1 ) );
                }
            } else {
                proceso.setRegistros( registros );
            }
            _nomina.addProceso( proceso );
        }
        factory.getProcesosEnvioDAO().queryCrearProcesosEnvio( _nomina );
        log.info( "Nomina [" + _nomina.getId() + "] procesos creados OK" );
    }

    /**
     * Realiza el reintento de envio de un proceso de envio de nomina.
     * @param _idProceso
     * @throws EnvioNominasException
     */
    public void reprocesarEnvio( Long _idProceso ) throws EnvioNominasException {
        log.info( "Reprocesando envio proceso [" + _idProceso + "]" );
        ProcesoEnvioBO proceso = null;
        try {
            proceso = factory.getProcesosEnvioDAO().queryProcesoById( _idProceso );
            if ( proceso == null ) {
                throw new EnvioNominasException( "El proceso [" + _idProceso + "] no existe en base de datos." );
            }
            proceso.setRegistros( factory.getRegistroNominaDAO().queryRegistrosByProcesoId( proceso.getId() ) );
            proceso.setNomina( factory.getNominaDAO().queryNominaById( proceso.getNomina().getId() ) );
        } catch ( DAOException e ) {
            throw new EnvioNominasException( "Ocurrio un error al cargar el proceso [" + _idProceso + "] : [" + e.getMessage() + "]", e );
        }
        if ( !proceso.getNomina().getEstado().getId().toString().equals( config.get( EnvioNominasConfig.ID_NOMINA_ENVIADA_MANUAL ) ) ) {
            try {
                validarFechaPago( proceso.getNomina() );
            } catch ( DAOException e ) {
                scheduler.agendarReproceso( contexto, _idProceso );
                throw new EnvioNominasException( "La nomina [" + proceso.getNomina().getId() + "] no se pudo validar por un problema de acceso a datos : [" + e.getMessage() + "]", e );
            } catch ( ParametroNoEncontradoException e ) {
                scheduler.agendarReproceso( contexto, _idProceso );
                throw new EnvioNominasException( "La nomina [" + proceso.getNomina().getId() + "] no se pudo validar por falta de un parametro del banco de proceso : [" + e.getMessage() + "]", e );
            } catch ( FechaPagoInvalidaException e ) {
                cancelarNomina( proceso.getNomina().getId(), "La nomina no posee un fecha valida : [" + e.getMessage() + "]" );
                new EnvioNotificacionJOB( proceso.getNomina().getId() ).run();
                throw new EnvioNominasException( "La nomina [" + proceso.getNomina().getId() + "] posee una fecha de pago invalida : [" + e.getMessage() + "]", e );
            } catch ( AtributoSinValorException e ) {
                scheduler.agendarReproceso( contexto, _idProceso );
                throw new EnvioNominasException( "La nomina [" + proceso.getNomina().getId() + "] no se pudo validar por un atributo sin valor : [" + e.getMessage() + "]", e );
            }
            try {
                jms.putCargaNomina( new TraductorWSTypes().generarEstructuraCarga( proceso, getBancoProceso()) );
            } catch ( EnvioNominasJMSException e ) {
                scheduler.agendarReproceso( contexto, _idProceso );
                throw new EnvioNominasException( "Fallo el envio de mensaje JMS del proceso [" + proceso.getNomina().getId() + "] : [" + e.getMessage() + "]", e );
            } catch ( DAOException e ) {
                throw new EnvioNominasException( "Ocurrio un error al recuperar el banco de proceso [" + e.getMessage() + "]", e );
            }
        } else {
            log.info( "La nomina [" + proceso.getNomina().getId() + "] se encuentra en proceso de envio manual." );
        }
    }

    /**
     * Realiza el reproceso de una nomina completa, eliminando posibles procesos
     * creados y volviendo a invocar el proceso de la nomina.
     * @param _idNomina
     * @throws EnvioNominasException
     */
    public void reprocesarNomina( Long _idNomina ) throws EnvioNominasException {
        log.info( "Reprocesando nomina [" + _idNomina + "]" );
        try {
            factory.getProcesosEnvioDAO().queryEliminaProcesosByNomina( _idNomina );
        } catch ( DAOException e ) {
            throw new EnvioNominasException( "Ocurrio un error al eliminar los procesos de la nomina [" + _idNomina + "] : [" + e.getMessage() + "]" );
        }
        procesarNomina( _idNomina );
    }

    /**
     * Realiza el envio del proceso para consulta de rendicion de carga a la cola
     * JMS.
     * @param _idProceso
     * @throws EnvioNominasException
     */
    public void rindeCargaProceso( Long _idProceso ) throws EnvioNominasException {
        log.info( "Rindiendo carga proceso [" + _idProceso + "]" );
        ProcesoEnvioBO proceso = null;
        NominaBO nomina = null;
        try {
            proceso = factory.getProcesosEnvioDAO().queryProcesoById( _idProceso );
        } catch ( DAOException e ) {
            scheduler.agendarReproceso( contexto, _idProceso );
            throw new EnvioNominasException( "Ocurrio un error al cargar el proceso [" + _idProceso + "] : [" + e.getMessage() + "]" );
        }
        try {
            nomina = factory.getNominaDAO().queryNominaById( proceso.getNomina().getId() );
            proceso.setNomina( nomina );
        } catch ( DAOException e ) {
            throw new EnvioNominasException( "Ocurrio un error al cargar la nomina [" + proceso.getNomina().getId() + "] : [" + e.getMessage() + "]" );
        }
        if ( nomina != null ) {
            if ( nomina.getEstado().getId().toString().equals( config.get( EnvioNominasConfig.ID_NOMINA_ENVIADA_MANUAL ) ) ) {
                throw new EnvioNominasException( "La nomina [" + nomina.getId() + "] se encuentra en proceso de envio manual" );
            }
        }
        try {
            jms.putRendicionCarga( new TraductorWSTypes().generarEstructuraRendicion( proceso, getBancoProceso()) );
        } catch ( EnvioNominasJMSException e ) {
            scheduler.agendarReproceso( contexto, _idProceso );
            throw new EnvioNominasException( "Fallo el envio de mensaje JMS del proceso [" + _idProceso + "] : [" + e.getMessage() + "]", e );
        } catch ( DAOException e ) {
            throw new EnvioNominasException( "Ocurrio un error al recuperar el banco de proceso [" + e.getMessage() + "]", e );
        }
    }
    
    /**
     * Realiza el envio de la nomina para consulta de rendicion de pago a la cola
     * JMS.
     * @param _idNomina
     * @throws EnvioNominasException
     */
    public void rindePagoNomina( Long _idNomina ) throws EnvioNominasException {
        NominaBO nomina = null;
        try {
            nomina = factory.getNominaDAO().queryNominaById(_idNomina);
        } catch (DAOException e) {
            scheduler.agendarRendicionPago(contexto, _idNomina);
            throw new EnvioNominasException("Ocurrio un error al cargar la nomina [" + _idNomina + "] : [" + e.getMessage() + "]");
        }
        try {
            jms.putRendicionPago(new TraductorWSTypes().generarEstructuraRendicion( nomina, getBancoProceso()) );
        } catch (EnvioNominasJMSException e) {
            scheduler.agendarRendicionPago(contexto, _idNomina);
            throw new EnvioNominasException("Fallo el envio de mensaje JMS de la nomina [" + _idNomina + "] : [" + e.getMessage() + "]", e);
        } catch ( DAOException e ) {
             throw new EnvioNominasException( "Ocurrio un error al recuperar el banco de proceso [" + e.getMessage() + "]", e );
        }
    }

    /**
     * Realiza la extraccion manual de una nomina, generando a partir de los 
     * registros asociados a la nomina, el archivo de carga segun plantilla 
     * previamente definida con el banco de proceso. Esta extraccion genera un 
     * archivo en el servidor, el que luego sera consumido por un servicio a 
     * nivel de ESB el que finalmente persistira en base de datos, el contenido 
     * del archivo para su posterior descarga y procesamiento manual.
     * @param _idNomina
     * @throws EnvioNominasException
     */
    public void extraerNomina( Long _idNomina ) throws EnvioNominasException {
        log.info( "Extrayendo nomina [" + _idNomina + "] manualmente" );
        NominaBO nomina = null;
        try {
            nomina = factory.getNominaDAO().queryNominaById( _idNomina );
        } catch ( DAOException e ) {
            throw new EnvioNominasException( "Ocurrio un error al cargar la nomina [" + _idNomina + "] : [" + e.getMessage() + "]" );
        }
        try {
            factory.getProcesosEnvioDAO().queryEliminaProcesosByNomina( _idNomina );
        } catch ( DAOException e ) {
            throw new EnvioNominasException( "Ocurrio un error al eliminar los procesos de la nomina [" + _idNomina + "] : [" + e.getMessage() + "]" );
        }
        try {
            crearProcesosEnvio( nomina, Boolean.FALSE );
        } catch ( DAOException e ) {
            throw new EnvioNominasException( "Fallo la creacion de procesos para la nomina [" + _idNomina + "] por un problema en el acceso a datos : [" + e.getMessage() + "]", e );
        } catch ( ParametroNoEncontradoException e ) {
            throw new EnvioNominasException( "Fallo la creacion de procesos para la nomina [" + _idNomina + "] por falta de un parametro del banco de proceso : [" + e.getMessage() + "]", e );
        }
        try {
            obtenerServicePort().generarArchivo( new TraductorWSTypes().generarEstructuraCarga( nomina.getProcesos().get( 0 ), getBancoProceso()) );
        } catch ( RemoteException e ) {
            throw new EnvioNominasException( "Ocurrio un error al invocar la operacion 'generarArchivo' para la nomina [" + _idNomina + "] : [" + e.getMessage() + "]", e );
        } catch ( DAOException e ) {
            throw new EnvioNominasException( "Ocurrio un error al recuperar el banco de proceso [" + e.getMessage() + "]", e );
        }
        log.info( "Generacion archivo nomina [" + _idNomina + "] OK" );
        try {
            factory.getNominaDAO().queryActualizaEstadoNomina( _idNomina, Long.valueOf( config.get( EnvioNominasConfig.ID_NOMINA_ENVIADA_MANUAL ) ) );
            factory.getProcesosEnvioDAO().queryActualizarEstadoProcesosByNomina( _idNomina, Long.valueOf( config.get( EnvioNominasConfig.ID_PROCESO_ESPERANDO_FOLIO ) ), null );
            new EnvioNotificacionJOB( _idNomina ).run();
        } catch ( DAOException e ) {
            throw new EnvioNominasException( "Fallo la actualizacion de estado de la nomina [" + _idNomina + "] : [" + e.getMessage() + "]", e );
        }
    }

    /**
     * Obtiene un mapa de los bancos de pago asociados a los registros de la 
     * nomina, para cada entrada del mapa el key correspondera a el identificador
     * del banco, mientras que el value contendra el nombre del mismo. En caso 
     * de que no encontrar datos, se retornara un mapa nulo "NULL".
     * @param _nomina
     * @return
     */
    private Map<Long, String> obtenerBancosPagoNomina( NominaBO _nomina ) {
        Map<Long, String> bancosPago = null;
        try {
            bancosPago = factory.getRegistroNominaDAO().queryBancosPagoNomina( _nomina.getId() );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return bancosPago;
    }

    /**
     * Cancela una nomina y sus procesos asociados. 
     * @param _idNomina Identificador de la nomina que se desea cancelar.
     * @param _motivoCancelacion Observacion asociada al motivo de la cancelacion.
     * @throws DAOException
     */
    public void cancelarNomina( Long _idNomina, String _motivoCancelacion ) throws EnvioNominasException {
        try {
            factory.getRegistroNominaDAO().queryActualizarEstadoRegistrosByNomina( _idNomina, Integer.valueOf( config.get( EnvioNominasConfig.ID_REGISTRO_CANCELADO ) ) );
        } catch ( DAOException e ) {
            throw new EnvioNominasException( "Ha ocurrido un error al cancelar los registros de la nomina [" + _idNomina + "] : [" + e.getMessage() + "]", e );
        }
        try {
            factory.getProcesosEnvioDAO().queryActualizarEstadoProcesosByNomina( _idNomina, Long.valueOf( config.get( EnvioNominasConfig.ID_PROCESO_CANCELADO ) ), _motivoCancelacion );
        } catch ( DAOException e ) {
            throw new EnvioNominasException( "Ha ocurrido un error al cancelar los procesos de la nomina [" + _idNomina + "] : [" + e.getMessage() + "]", e );
        }
        try {
            factory.getNominaDAO().queryActualizaEstadoNomina( _idNomina, Long.valueOf( config.get( EnvioNominasConfig.ID_NOMINA_CANCELADA ) ) );
        } catch ( DAOException e ) {
            throw new EnvioNominasException( "Ha ocurrido un error al cancelar la nomina [" + _idNomina + "] : [" + e.getMessage() + "]", e );
        }
    }

    private EnvioNominasPort obtenerServicePort() {
        EnvioNominasPort _port = null;
        ServiceFactory serviceFactory = null;
        try {
            serviceFactory = ServiceFactory.newInstance();
        } catch ( ServiceException e ) {
            log.error( "EXCEPTION:[" + e.getMessage() + "]", e );
        }
        try {
            _port = ( ( EnvioNominasPortService ) serviceFactory.loadService( EnvioNominasPortService.class ) ).get__soap_EnvioNominas_RS_envioNominasPort();
        } catch ( ServiceException e ) {
            log.error( "EXCEPTION:[" + e.getMessage() + "]", e );
        }
        return _port;
    }

    public void ejecutarCargaProcesoEnvioNomina( CargaNominaInputType _type ) throws EnvioNominasException {
        log.info( "Ejecutando carga de proceso [" + _type.getNomina().getIdProceso() + "] de la nomina [" + _type.getNomina().getIdNomina() + "]" );
        ProcesoEnvioBO proceso = null;
        try {
            proceso = factory.getProcesosEnvioDAO().queryProcesoById( Long.valueOf( _type.getNomina().getIdProceso() ) );
        } catch ( DAOException e ) {
            scheduler.agendarReproceso( contexto, Long.valueOf( _type.getNomina().getIdProceso() ) );
            log.error( "Ocurrio un error al recuperar el proceso [" + _type.getNomina().getIdProceso() + "]" );
            throw new EnvioNominasException( "Ocurrio un error al recuperar el proceso [" + _type.getNomina().getIdProceso() + "] : [" + e.getMessage() + "]" );
        }
        if ( proceso != null && proceso.getEstado().getId().toString().equals( config.get( EnvioNominasConfig.ID_PROCESO_PREPARANDO ) ) ) {
            CargaNominaOutputType output = null;
            try {
                output = obtenerServicePort().cargar( _type );
                log.info( "Respuesta carga proceso [" + _type.getNomina().getIdProceso() + "], nomina [" + _type.getNomina().getIdNomina() + "] : [" + output.getRespuestaServicio() + "]" );
            } catch ( ErrorType e ) {
                scheduler.agendarReproceso( contexto, Long.valueOf( _type.getNomina().getIdProceso() ) );
                try {
                    factory.getNominaDAO().queryActualizaEstadoNomina( Long.valueOf( _type.getNomina().getIdNomina() ), Long.valueOf( config.get( EnvioNominasConfig.ID_NOMINA_REPROCESO ) ) );
                } catch ( DAOException f ) {
                    log.error( "Fallo la actualizacion de estado de la nomina [" + _type.getNomina().getIdNomina() + "]", f );
                }
                throw new EnvioNominasException( "La carga del proceso [" + _type.getNomina().getIdProceso() + "] no se realizo : [" + e.getCodigo() + "::" + e.getDescripcion() + "]", e );
            } catch ( RemoteException e ) {
                scheduler.agendarReproceso( contexto, Long.valueOf( _type.getNomina().getIdProceso() ) );
                try {
                    factory.getNominaDAO().queryActualizaEstadoNomina( Long.valueOf( _type.getNomina().getIdNomina() ), Long.valueOf( config.get( EnvioNominasConfig.ID_NOMINA_REPROCESO ) ) );
                } catch ( DAOException f ) {
                    log.error( "Fallo la actualizacion de estado de la nomina [" + _type.getNomina().getIdNomina() + "]", f );
                }
                throw new EnvioNominasException( "La carga del proceso [" + _type.getNomina().getIdProceso() + "] no se realizo : [" + e.getMessage() + "]", e );
            } catch ( Exception e ) {
                scheduler.agendarReproceso( contexto, Long.valueOf( _type.getNomina().getIdProceso() ) );
                try {
                    factory.getNominaDAO().queryActualizaEstadoNomina( Long.valueOf( _type.getNomina().getIdNomina() ), Long.valueOf( config.get( EnvioNominasConfig.ID_NOMINA_REPROCESO ) ) );
                } catch ( DAOException f ) {
                    log.error( "Fallo la actualizacion de estado de la nomina [" + _type.getNomina().getIdNomina() + "]", f );
                }
                throw new EnvioNominasException( "La carga del proceso [" + _type.getNomina().getIdProceso() + "] no se realizo : [" + e.getMessage() + "]" );
            }
            if ( !output.getRespuestaServicio().equals( config.get( EnvioNominasConfig.ENVIO_OK ) ) ) {
                scheduler.agendarReproceso( contexto, Long.valueOf( _type.getNomina().getIdProceso() ) );
                try {
                    factory.getNominaDAO().queryActualizaEstadoNomina( Long.valueOf( _type.getNomina().getIdNomina() ), Long.valueOf( config.get( EnvioNominasConfig.ID_NOMINA_REPROCESO ) ) );
                } catch ( DAOException e ) {
                    log.error( "Fallo la actualizacion de estado de la nomina [" + _type.getNomina().getIdNomina() + "]", e );
                }
                throw new EnvioNominasException( "La carga del proceso [" + _type.getNomina().getIdProceso() + "] no se realizo : [" + output.getRespuestaServicio() + "]" );
            }
            scheduler.agendarRendicionCarga( contexto, Long.valueOf( _type.getNomina().getIdProceso() ) );
            try {
                factory.getProcesosEnvioDAO().queryActualizaEstadoProceso( Long.valueOf( _type.getNomina().getIdProceso() ), Long.valueOf( config.get( EnvioNominasConfig.ID_PROCESO_ESPERANDO_FOLIO ) ), null );
            } catch ( DAOException e ) {
                log.error( "Fallo la actualizacion del estado del proceso [" + _type.getNomina().getIdProceso() + "]", e );
            }
        } else {
            log.error( "El estado [" + proceso.getEstado().getId() + "] del proceso [" + _type.getNomina().getIdProceso() + "] no es valido para realizar el envio." );
        }
    }

    public void ejecutarRendicionCargaProcesoEnvioNomina( RindeNominaInputType _type ) throws EnvioNominasException {
        log.info( "Ejecutando rendicion de carga de proceso [" + _type.getIdProceso() + "] de la nomina [" + _type.getIdNomina() + "]" );
        RindeNominaOutputType output = null;
        boolean repiteRendicion = true;
        try {
            output = obtenerServicePort().rendir( _type );
            log.info( "Respuesta rendicion carga proceso [" + _type.getIdProceso() + "], nomina [" + _type.getIdNomina() + "] : [" + output.getRespuestaServicio() + "]" );
        } catch ( ErrorType e ) {
            scheduler.agendarRendicionCarga( contexto, Long.valueOf( _type.getIdProceso() ) );
            throw new EnvioNominasException( "La rendicion de carga ha fallado : [" + e.getMessage() + "]" );
        } catch ( RemoteException e ) {
            scheduler.agendarRendicionCarga( contexto, Long.valueOf( _type.getIdProceso() ) );
            throw new EnvioNominasException( "La rendicion de carga ha fallado : [" + e.getMessage() + "]" );
        }
        if ( !output.getRespuestaServicio().equals( config.get( EnvioNominasConfig.RENDICION_OK ) ) ) {
            scheduler.agendarRendicionCarga( contexto, Long.valueOf( _type.getIdProceso() ) );
            throw new EnvioNominasException( "La rendicion de carga ha fallado : [" + output.getRespuestaServicio() + "]" );
        }
        for ( int i = 0; i < output.getNominas().getNomina().length; i++ ) {
            NominaType nomina = output.getNominas().getNomina()[ i ];
            ProcesoEnvioBO proceso = null;
            try {
                proceso = factory.getProcesosEnvioDAO().queryProcesoByIdRegistro( Long.valueOf( nomina.getRegistrosNomina().getRegistro()[ 0 ].getIdRegistro() ) );
                if ( proceso != null ) {
                    if ( proceso.getFolioProcesoExterno() == null && !proceso.getEstado().getId().toString().equals( config.get( EnvioNominasConfig.ID_PROCESO_CANCELADO ) ) && !proceso.getEstado().getId().toString().equals( config.get( EnvioNominasConfig.ID_PROCESO_ESPERANDO_AUTORIZACION ) ) ) {
                        factory.getProcesosEnvioDAO().queryRegistrarFolioProcesoExterno( proceso.getId(), nomina.getFolioProceso() );
                        log.info( "Folio externo [" + nomina.getFolioProceso() + "] asignado a proceso [" + proceso.getId() + "] y nomina [" + proceso.getNomina().getId() + "] OK" );
                        //Se revisa si la nomina aun tiene procesos pendientes de envio o folio.
                        factory.getProcesosEnvioDAO().queryActualizaEstadoProceso( proceso.getId(), Long.valueOf( config.get( EnvioNominasConfig.ID_PROCESO_ESPERANDO_AUTORIZACION ) ), null );
                        Long cantidadProcesosSinFolio = factory.getProcesosEnvioDAO().queryProcesosSinFolioByNomina( proceso.getNomina().getId() );
                        if ( cantidadProcesosSinFolio != null && cantidadProcesosSinFolio.equals( Long.valueOf( 0 ) ) ) {
                            log.info( "Envio de procesos nomina [" + _type.getIdNomina() + "] completado OK" );
                            factory.getNominaDAO().queryActualizaEstadoNomina( proceso.getNomina().getId(), Long.valueOf( config.get( EnvioNominasConfig.ID_NOMINA_ENVIADA ) ) );
                            scheduler.agendarRendicionPago( contexto, proceso.getNomina().getId(), proceso.getNomina().getFechaPago() );
                            new EnvioNotificacionJOB( proceso.getNomina().getId() ).run();
                        }
                    }
                    if ( proceso.getId().toString().equals( _type.getIdProceso() ) ) {
                        repiteRendicion = false;
                    }
                } else {
                    log.error( "No se encontro un proceso asociado al registro [" + nomina.getRegistrosNomina().getRegistro()[ 0 ].getIdRegistro() + "], el folio [" + nomina.getFolioProceso() + "] no ha logrado ser asignado" );
                }
            } catch ( DAOException e ) {
                log.error( "Ha ocurrido un error al obtener el proceso asociado al registro [" + nomina.getRegistrosNomina().getRegistro()[ 0 ].getIdRegistro() + "], el folio [" + nomina.getFolioProceso() + "] no ha logrado ser asignado", e );
            }
        }
        if ( repiteRendicion ) {
            scheduler.agendarRendicionCarga( contexto, Long.valueOf( _type.getIdProceso() ) );
        }
    }

    public void ejecutarRendicionPagoProcesoEnvioNomina( RindeNominaInputType _type ) throws EnvioNominasException {
        log.info( "Ejecutando rendicion de pago de nomina [" + _type.getIdNomina() + "]" );
        NominaBO nomina = null;
        try {
            nomina = factory.getNominaDAO().queryNominaById(Long.valueOf(_type.getIdNomina()));
        } catch ( DAOException e ) {
            scheduler.agendarRendicionPago( contexto, Long.valueOf( _type.getIdNomina() ) );
            throw new EnvioNominasException( "Ocurrio un error al recuperar la nomina : [" + _type.getIdNomina() + "]" );
        }
        if(nomina == null){
            scheduler.agendarRendicionPago( contexto, Long.valueOf( _type.getIdNomina() ) );
            throw new EnvioNominasException( "Ocurrio un error al recuperar la nomina : [" + _type.getIdNomina() + "]" );
        }
        if(!nomina.getEstado().getId().equals(Long.valueOf(config.get(EnvioNominasConfig.ID_NOMINA_EN_RENDICION)))) {
            try {
                factory.getNominaDAO().queryActualizaEstadoNomina(nomina.getId(),Long.valueOf(config.get(EnvioNominasConfig.ID_NOMINA_EN_RENDICION)));
            } catch ( DAOException e ) {
                scheduler.agendarRendicionPago( contexto, Long.valueOf( _type.getIdNomina() ) );
                throw new EnvioNominasException("Ocurrio un error al actualizar el estado de la nomina ["+nomina.getId()+"] : ["+e.getMessage()+"]",e);
            }
        }
        RindeNominaOutputType output = null;
        try {
            output = obtenerServicePort().rendir( _type );
            log.info( "Respuesta rendicion de pago nomina [" + _type.getIdNomina() + "] : [" + output.getRespuestaServicio() + "]" );
        } catch ( ErrorType e ) {
            scheduler.agendarRendicionPago( contexto, Long.valueOf( _type.getIdNomina() ) );
            throw new EnvioNominasException( "La rendicion de pago ha fallado : [" + e.getMessage() + "]" );
        } catch ( RemoteException e ) {
            scheduler.agendarRendicionPago( contexto, Long.valueOf( _type.getIdNomina() ) );
            throw new EnvioNominasException( "La rendicion de pago ha fallado : [" + e.getMessage() + "]" );
        }
        if ( !output.getRespuestaServicio().equals( config.get( EnvioNominasConfig.RENDICION_OK ) ) ) {
            scheduler.agendarRendicionPago( contexto, Long.valueOf( _type.getIdNomina() ) );
            throw new EnvioNominasException( "La rendicion de pago ha fallado : [" + output.getRespuestaServicio() + "]" );
        }
        
        procesarRespuestaRendicionPagoNomina(output, nomina);
    }    
    
    public void procesarRespuestaRendicionPagoNomina(RindeNominaOutputType output, NominaBO nomina) throws EnvioNominasException {
        for ( int i = 0; i < output.getNominas().getNomina().length; i++ ) {
            NominaType type = output.getNominas().getNomina()[ i ];
            ProcesoEnvioBO proceso = null;
            try {
                proceso = factory.getProcesosEnvioDAO().queryProcesoByFolioExterno( type.getFolioProceso() );
                if(proceso.getEstado().getId().equals(Long.valueOf(config.get(EnvioNominasConfig.ID_PROCESO_ESPERANDO_RENDICION)))) {
                    factory.getProcesosEnvioDAO().queryActualizaEstadoProceso(proceso.getId(),Long.valueOf(config.get(EnvioNominasConfig.ID_PROCESO_CONCILIANDO_REGISTROS)), null);                    
                }
                if ( proceso != null ) {
                    proceso.setRegistros(factory.getRegistroNominaDAO().queryRegistrosByProcesoId(proceso.getId()));
                    for(RegistroNominaBO registro : proceso.getRegistros()){
                        if(registro.getEstado().getId().equals(Long.valueOf(config.get(EnvioNominasConfig.ID_REGISTRO_PENDIENTE)))) {
                            for(int j = 0; j < type.getRegistrosNomina().getRegistro().length; j++) {
                                if(Long.valueOf(type.getRegistrosNomina().getRegistro()[j].getIdRegistro()).equals(registro.getId())){
                                    try {
                                        if(type.getRegistrosNomina().getRegistro()[j].getEstadoPago().equals(getBancoProceso().getParametro("codigoPagoOk"))) {
                                            factory.getRegistroNominaDAO().queryActualizarEstadoRegistro(registro.getId(),Long.valueOf(config.get(EnvioNominasConfig.ID_REGISTRO_PAGADO)),"Pago OK");
                                        } else if(type.getRegistrosNomina().getRegistro()[j].getEstadoPago().equals(getBancoProceso().getParametro("codigoPagoAnticipadoOk"))){
                                            factory.getRegistroNominaDAO().queryActualizarEstadoRegistro(registro.getId(),Long.valueOf(config.get(EnvioNominasConfig.ID_REGISTRO_PAGADO)),"Pago Anticipado OK");
                                        } else if(type.getRegistrosNomina().getRegistro()[j].getEstadoPago().equals(getBancoProceso().getParametro("codigoPagoRechazado"))) {
                                            factory.getRegistroNominaDAO().queryActualizarEstadoRegistro(registro.getId(),Long.valueOf(config.get(EnvioNominasConfig.ID_REGISTRO_RECHAZADO)),type.getRegistrosNomina().getRegistro()[j].getGlosa());
                                        }
                                    } catch ( ParametroNoEncontradoException e ) {
                                        throw new EnvioNominasException("Ocurrio un error al obtener un parametro del banco de proceso : ["+e.getMessage()+"]",e);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    throw new EnvioNominasException("Ocurrio un error al recuperar el proceso con folio externo : ["+type.getFolioProceso()+"] : ");
                }
            } catch ( DAOException e ) {
                throw new EnvioNominasException("Ocurrio un error al recuperar el proceso con folio externo : ["+type.getFolioProceso()+"] :: ["+e.getMessage()+"] : ",e);
            }
        }
        Long registrosPendientes;
        try {
            registrosPendientes = factory.getRegistroNominaDAO().queryRegistrosPendientesRendicionByNomina(nomina.getId());
        } catch ( DAOException e ) {
            throw new EnvioNominasException("Ocurrio un error al obtener la cantidad de registros pendientes de la nomina ["+nomina.getId()+"] : ["+e.getMessage()+"]",e);
        }
        SimpleDate fechaLimiteRendicion = new SimpleDate(nomina.getFechaPago());
        try {
            fechaLimiteRendicion.addDays(Long.valueOf(getBancoProceso().getParametro("periodoRendicion")));
        } catch ( ParametroNoEncontradoException e ) {
            throw new EnvioNominasException("Ocurrio un error al obtener un parametro del banco de proceso : ["+e.getMessage()+"]",e);
        } catch ( DAOException e ) {
            throw new EnvioNominasException("Ocurrio un error al recuperar el banco de proceso : ["+e.getMessage()+"]",e);
        }
        try {
            boolean conciliada = false;
            if(registrosPendientes.equals(Long.valueOf(0))) {
                factory.getNominaDAO().queryActualizaEstadoNomina(nomina.getId(),Long.valueOf(config.get(EnvioNominasConfig.ID_NOMINA_CONCILIADA)));
                conciliada = true;
            } else if(fechaLimiteRendicion.lessThan(new SimpleDate(new Date()))){
                factory.getNominaDAO().queryActualizaEstadoNomina(nomina.getId(),Long.valueOf(config.get(EnvioNominasConfig.ID_NOMINA_CONCILIADA_CON_ERROR)));
                conciliada = true;
            }
            if(!conciliada) {
                scheduler.agendarRendicionPago( contexto, Long.valueOf( nomina.getId() ) );
            } else {
                new EnvioNotificacionJOB( nomina.getId() ).run();
            }    
        } catch ( DAOException e ) {
            throw new EnvioNominasException("Ocurrio un error al actualizar el estado de la nomina ["+nomina.getId()+"] : ["+e.getMessage()+"]",e);
        }
    }

    public void registrarFolio( Long _idNomina, String _folioExterno ) throws EnvioNominasException {
        log.info("Registrando manualmente folio ["+_folioExterno+"] a nomina ["+_idNomina+"]");
        NominaBO nomina = null;
        try {
            nomina = factory.getNominaDAO().queryNominaById( _idNomina );
        } catch ( DAOException e ) {
            throw new EnvioNominasException( "Ocurrio un error al obtener la nomina [" + _idNomina + "] : [" + e.getMessage() + "]", e );
        }
        if ( nomina == null ) {
            throw new EnvioNominasException( "No se encontro la nomina [" + _idNomina + "] en base de datos" );
        }
        try {
            nomina.setProcesos( factory.getProcesosEnvioDAO().queryProcesosByNomina( nomina.getId() ) );
        } catch ( DAOException e ) {
            throw new EnvioNominasException( "No se logro obtener los procesos de la nomina [" + _idNomina + "] : [" + e.getMessage() + "]", e );
        }
        try {
            factory.getProcesosEnvioDAO().queryRegistrarFolioProcesoExterno( nomina.getProcesos().get( 0 ).getId(), _folioExterno );
        } catch ( DAOException e ) {
            throw new EnvioNominasException( "Ocurrio un error al registrar el folio del proceso [" + nomina.getProcesos().get( 0 ).getId() + "] : [" + e.getMessage() + "]", e );
        }
        try {
            factory.getProcesosEnvioDAO().queryActualizaEstadoProceso( nomina.getProcesos().get( 0 ).getId(), Long.valueOf( config.get( EnvioNominasConfig.ID_PROCESO_ESPERANDO_AUTORIZACION ) ), null );
        } catch ( DAOException e ) {
            throw new EnvioNominasException( "Ocurrio un error al actualizar el estado del proceso [" + nomina.getProcesos().get( 0 ).getId() + "] : [" + e.getMessage() + "]", e );
        }
        try {
            factory.getNominaDAO().queryActualizaEstadoNomina( nomina.getId(), Long.valueOf( config.get( EnvioNominasConfig.ID_NOMINA_ENVIADA ) ) );
        } catch ( DAOException e ) {
            throw new EnvioNominasException( "Ocurrio un error al actualizar el estado de la nomina[" + nomina.getId() + "] : [" + e.getMessage() + "]", e );
        }
        log.info("Folio ["+_folioExterno+"] asignado a nomina ["+_idNomina+"] OK");
        new EnvioNotificacionJOB( nomina.getId() ).run();
    }
    
    public List<ArchivoBO> queryArchivosNominasNotChecked() throws DAOException {
        return factory.getArchivosNominasDAO().queryArchivosNominasNotChecked();
    }
    
    public boolean actualizaEstadosArchivosNomina(RindeNominaOutputBO _type ) throws DAOException {
        boolean response = false;
        ArrayList<TransaccionPagoBO> transaccionPagos = _type.getTransaccionPagos();
        NominaBO nomina = null;
        for( TransaccionPagoBO transaccion: transaccionPagos ) {
            try {
                nomina = factory.getNominaDAO().queryNominaById(Long.valueOf(transaccion.getNumNomina() ) );
                if(nomina.getEstado().getId().equals(Long.valueOf(config.get(EnvioNominasConfig.ID_NOMINA_EN_RENDICION)))) {
                    try {
                         if(transaccion.getCodigoEstadoRendicion().equals("A")) {
                             factory.getRegistroNominaDAO().queryActualizarEstadoRegistro(Long.valueOf(nomina.getId()), Integer.parseInt(config.get( EnvioNominasConfig.ID_REGISTRO_PAGADO) ), transaccion.getRutTitular(), transaccion.getNombreTitular() );   
                         } else {
                            log.info("Transaccion con otro estado....");
                         }
                    } catch ( DAOException e ) {
                        log.error("Error al actualizar nomina " + nomina.getId() + "[ " + e.getMessage() +" ]" );
                    } catch(Exception e) {
                        log.error("Error al actualizar nomina " + nomina.getId() + "[ " + e.getMessage() +" ]" );
                    }
                }
                response = true;
            } catch(DAOException e) {
                response = false;
                log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
            }
        }
        
        return response;
    }

    public List<ArchivoBO> queryArchivosByTipo(String idTipo) throws DAOException {
        return factory.getArchivosNominasDAO().queryArchivosByTipo(idTipo);
    }
    
    public boolean ejecutarErroresNomina(NominaErrores _type) {
        boolean response = false;
        NominaBO nomina = null;
        for(RegistroError nominaError: _type.getRegistroErrores()) {
            try {
                nomina = factory.getNominaDAO().queryNominaById(Long.valueOf(nominaError.getNumNomina() ));
            } catch(DAOException e) {
                log.error("[EXCEPTION] : " + e.getMessage());
            }
            try {
                int values = factory.getArchivosNominasDAO().queryActualizarEstadoRegistroError(Long.parseLong(nominaError.getRegistro()), 
                                    Long.parseLong(config.ID_REGISTRO_ERROR), nominaError.getGlosaError(), nominaError.getCtaTitular(), 
                                    Long.parseLong(nominaError.getRutTitular()), Long.parseLong(nominaError.getMonto()), nomina.getId());
                if(values>0 ) {
                    try {
                        factory.getNominaDAO().queryActualizaEstadoNomina(Long.parseLong(nominaError.getNumNomina()), Long.parseLong(config.ID_NOMINA_CONCILIADA_CON_ERROR));
                    } catch(DAOException e ) {
                        log.error("[EXCEPTION] : " + e.getMessage());
                    }  
                }
                response = true;
            } catch(DAOException e) {
                log.error("[EXCEPTION] : " + e.getMessage());
                response = false;
            }
            
            return response;
        }
    }   
    
    public void queryActualizaEvmnArchivosNominas(java.sql.Date fechaCreacion, String  nombreArchivo) {
        try {
            factory.getArchivosNominasDAO().queryActualizaEvmnArchivosNominas(fechaCreacion, nombreArchivo);
        } catch (DAOException e) {
            log.error("[EXCEPTION] : " + e.getMessage());
        }
    }
}
