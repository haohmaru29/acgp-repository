package cl.bicevida.envionominas.model.services.mdb;

import cl.bicevida.envionominas.model.exceptions.EnvioNominasException;
import cl.bicevida.envionominas.model.services.ejb.EnvioNominasEJBLocal;
import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.RindeNominaInputType;
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
            , @ActivationConfigProperty( propertyName = "destinationName", propertyValue = "jms/RINDE_CARGA_NOMINA_Q" )
            } )
public class RendicionCargaNominaMDBBean implements MessageListener {
    @EJB
    private EnvioNominasEJBLocal envioNominasEJB;
    private static final Logger log = Logger.getLogger( RendicionCargaNominaMDBBean.class );
    private XMLUtils util;

    public void onMessage( Message message ) {
        util = new XMLUtils();
        if ( message instanceof TextMessage ) {
            TextMessage txtMsg = ( TextMessage ) message;
            RindeNominaInputType _type = null;
            try {
                _type = util.rindeNominaInputFromXML( txtMsg.getText() );
            } catch ( JMSException e ) {
                log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
            }
            try {
                envioNominasEJB.ejecutarRendicionCargaProcesoEnvioNomina( _type );
            } catch ( EnvioNominasException e ) {
                log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
            }
        }
    }
}
