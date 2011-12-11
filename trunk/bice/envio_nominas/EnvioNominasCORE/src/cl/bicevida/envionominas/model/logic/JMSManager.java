package cl.bicevida.envionominas.model.logic;

import cl.bicevida.envionominas.model.config.EnvioNominasConfig;

import cl.bicevida.envionominas.model.exceptions.EnvioNominasJMSException;
import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.CargaNominaInputType;

import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.RindeNominaInputType;
import cl.bicevida.envionominas.model.utils.XMLUtils;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

/**
 * Clase encargada de centralizar la comunicacion con los recursos JMS de la 
 * aplicacion, interactuando con las colas que se comuinican de forma asincrona 
 * con las operaciones del servicio de carga y rendicion de nominas.
 * 
 * Registro de Versiones
 * - 1.0 28/02/2011 Giogio Gortaire : Version inicial.
 */
public class JMSManager {
    private static final Logger log = Logger.getLogger( JMSManager.class );
    private EnvioNominasConfig config;
    private InitialContext initialContext;

    /**
     * Constructor por defecto.
     */
    public JMSManager() {
        config = new EnvioNominasConfig();
    }

    /**
     * Realiza el envio de un mensaje del tipo CargaNominaInputType a la cola de 
     * carga de nominas.
     * @param _type
     * @throws EnvioNominasJMSException
     */
    public void putCargaNomina( CargaNominaInputType _type ) throws EnvioNominasJMSException {
        XMLUtils xmlUtils = new XMLUtils();
        String mensaje = xmlUtils.toXML( _type );
        putMensaje( mensaje, config.get( EnvioNominasConfig.JMS_Q_CARGA_NOMINAS ) );
    }

    /**
     * Realiza el envio de un mensaje del tipo RindeNominaInputType a la cola de 
     * rendicion de nominas.
     * @param _type
     * @throws EnvioNominasJMSException
     */
    public void putRendicionPago( RindeNominaInputType _type ) throws EnvioNominasJMSException {
        XMLUtils xmlUtils = new XMLUtils();
        String mensaje = xmlUtils.toXML( _type );
        putMensaje( mensaje, config.get( EnvioNominasConfig.JMS_Q_RINDE_NOMINAS ) );
    }

    /**
     * Realiza el envio de un mensaje del tipo RindeNominaInputType a la cola de 
     * rendicion de carga de nominas.
     * @param _type
     * @throws EnvioNominasJMSException
     */
    public void putRendicionCarga( RindeNominaInputType _type ) throws EnvioNominasJMSException {
        XMLUtils xmlUtils = new XMLUtils();
        String mensaje = xmlUtils.toXML( _type );
        putMensaje( mensaje, config.get( EnvioNominasConfig.JMS_Q_RINDE_CARGA_NOMINAS ) );
    }

    /**
     * Inicializa la factoria de conexiones JMS para la interaccion con las colas
     * de la aplicacion.
     * @return QueueConnectionFactory
     * @throws EnvioNominasJMSException
     */
    private QueueConnectionFactory getQFactory() throws EnvioNominasJMSException {
        try {
            Hashtable env = new Hashtable();
            env.put( Context.INITIAL_CONTEXT_FACTORY, config.get( EnvioNominasConfig.CONTEXT_FACTORY_CLASS ) );
            env.put( Context.PROVIDER_URL, config.get( EnvioNominasConfig.CONTEXT_PROVIDER_URL ) );
            env.put( Context.SECURITY_PRINCIPAL, config.get( EnvioNominasConfig.CONTEXT_SECURITY_PRINCIPAL ) );
            env.put( Context.SECURITY_CREDENTIALS, config.get( EnvioNominasConfig.CONTEXT_SECURITY_CREDENTIAL ) );
            initialContext = new InitialContext();
        } catch ( NamingException e ) {
            throw new EnvioNominasJMSException( "Fallo la inicializacion de contexto JMS : [" + e.getMessage() + "]", e );
        }
        try {
            return ( QueueConnectionFactory ) initialContext.lookup( config.get( EnvioNominasConfig.JMS_Q_FACTORY ) );
        } catch ( NamingException e ) {
            throw new EnvioNominasJMSException( "Fallo la obtencion del JMS QFactory [" + e.getMessage() + "]", e );
        }
    }

    /**
     * Realiza el envio de mensajes a una cola JMS.
     * @param _mensaje Mensaje a enviar.
     * @param _jndiQueue JNDI de la cola a enviar.
     * @throws EnvioNominasJMSException
     */
    private void putMensaje( String _mensaje, String _jndiQueue ) throws EnvioNominasJMSException {
        QueueConnection connection = null;
        QueueSession session = null;
        QueueSender sender = null;
        Queue requestQ = null;
        QueueConnectionFactory qFactory = getQFactory();
        try {
            requestQ = ( Queue ) initialContext.lookup( _jndiQueue );
            connection = qFactory.createQueueConnection();
            connection.start();
            session = connection.createQueueSession( false, Session.AUTO_ACKNOWLEDGE );
            sender = session.createSender( requestQ );
        } catch ( NamingException e ) {
            throw new EnvioNominasJMSException( "Fallo el lookup del recurso JMS [" + e.getMessage() + "]", e );
        } catch ( JMSException e ) {
            throw new EnvioNominasJMSException( "Fallo el lookup del recurso JMS [" + e.getMessage() + "]", e );
        }
        TextMessage mensajeOut = null;
        try {
            mensajeOut = session.createTextMessage( _mensaje );
            mensajeOut.setJMSReplyTo( requestQ );
            sender.send( mensajeOut );
            log.info("El mensaje  [" + _mensaje + "]  se ha recibido con exito en " + requestQ);
        } catch ( JMSException e ) {
            throw new EnvioNominasJMSException( "Fallo el envio del mensaje [" + _mensaje + "] al recurso JMS [" + _jndiQueue + "] : [" + e.getMessage() + "]", e );
        } finally {
            try {
                sender.close();
                session.close();
                connection.close();
                connection = null;
            } catch ( Exception e ) {
                throw new EnvioNominasJMSException( "Ocurrio un error al liberar los recursos JMS [" + e.getMessage() + "]", e );
            }
        }
    }
}
