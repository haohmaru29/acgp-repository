package cl.bicevida.envionominas.model.services.mdb;

import cl.bicevida.envionominas.model.services.ejb.EnvioNominasEJBLocal;
import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.CargaNominaInputType;

import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.RindeNominaInputType;

import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.RindeNominaOutputType;
import cl.bicevida.envionominas.model.utils.XMLUtils;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

@MessageDriven( messageListenerInterface = MessageListener.class, activationConfig = { 
              @ActivationConfigProperty( propertyName = "connectionFactoryJndiName", propertyValue = "jms/EnvioNominasQueueConnectionFactory" )
            , @ActivationConfigProperty( propertyName = "destinationType", propertyValue = "javax.jms.Queue" )
            , @ActivationConfigProperty( propertyName = "destinationName", propertyValue = "jms/RINDE_NOMINA_BICE_IN" )
            } )
public class RindeNominaBICEMDBBean implements MessageListener {
    
    @EJB
    private EnvioNominasEJBLocal envioNominasEJB;
    private static final Logger log = Logger.getLogger( RindeNominaBICEMDBBean.class );
    private XMLUtils util = new XMLUtils();
  
    public void onMessage(Message message) {
        System.out.println("MENSAJE RECOGIDO DE jms/RINDE_NOMINA_BICE_IN: ");
        TextMessage txtMsg = ( TextMessage ) message;
        RindeNominaOutputType _type = null;
        try {
            _type = util.rindeNominaOutputFromXML(txtMsg.getText());
            System.out.println("MENSAJE RECOGIDO DE jms/RINDE_NOMINA_BICE_IN: " + _type );
        } catch (JMSException e) {
             log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        System.out.println("_type:[]"+_type.getNominas().getNomina()[0]);
    }

}
