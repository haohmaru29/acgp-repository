package cl.bicevida.envionominas.model.services.mdb;

import cl.bicevida.envionominas.model.exceptions.EnvioNominasException;
import cl.bicevida.envionominas.model.services.ejb.EnvioNominasEJBLocal;
import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.CargaNominaInputType;
import cl.bicevida.envionominas.model.utils.XMLUtils;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

@MessageDriven( messageListenerInterface = MessageListener.class, activationConfig = { @ActivationConfigProperty( propertyName = "connectionFactoryJndiName", propertyValue = "jms/EnvioNominasQueueConnectionFactory" )
            , @ActivationConfigProperty( propertyName = "destinationType", propertyValue = "javax.jms.Queue" )
            , @ActivationConfigProperty( propertyName = "destinationName", propertyValue = "jms/CARGA_NOMINA_Q" )
            } )
public class CargaNominaMDBBean implements MessageListener {
    @EJB
    private EnvioNominasEJBLocal envioNominasEJB;
    private static final Logger log = Logger.getLogger( CargaNominaMDBBean.class );
    private XMLUtils util = new XMLUtils();

    public void onMessage( Message message ) {
        if ( message instanceof TextMessage ) {
            TextMessage txtMsg = ( TextMessage ) message;
            CargaNominaInputType _type = null;
            try {
                _type = util.cargaNominaInputFromXML( txtMsg.getText() );
            } catch ( JMSException e ) {
                log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
            }
            try {
                envioNominasEJB.ejecutarCargaProcesoEnvioNomina( _type );
            } catch ( EnvioNominasException e ) {
                log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
            }
        }
    }
}
