package cl.bicevida.envionominas.model.logic;

import cl.bicevida.envionominas.model.bo.BancoProcesoBO;
import cl.bicevida.envionominas.model.bo.CorreoBO;
import cl.bicevida.envionominas.model.bo.ListaDistribucionBO;
import cl.bicevida.envionominas.model.bo.NominaBO;

import cl.bicevida.envionominas.model.bo.ProcesoEnvioBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.DAOFactory;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.CorreosDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.ListasDistribucionDAO;

import cl.bicevida.envionominas.model.exceptions.EnvioNominasException;

import cl.bicevida.envionominas.model.utils.SimpleDate;

import java.util.Date;
import java.util.List;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

public class EnvioCorreosManager {
    private static final Logger log = Logger.getLogger( EnvioCorreosManager.class );
    private EnvioNominasConfig config;
    private DAOFactory factory = DAOFactory.getDAOFactory( DAOFactory.ORACLE );

    public EnvioCorreosManager() {
        config = new EnvioNominasConfig();
    }

    public void enviaCorreo( NominaBO nomina ) throws EnvioNominasException {
        List<ListaDistribucionBO> listasDistribucion = null;
        BancoProcesoBO bancoProceso = null;
        try {
            listasDistribucion = factory.getListasDistribucionDAO().queryListasByOrigenNomina( nomina.getOrigen().getCodigo() );
        } catch ( DAOException e ) {
            throw new EnvioNominasException( e.getMessage() );
        }
        String[] destinatarios = new String[ listasDistribucion.size() ];
        int i = 0;
        for ( ListaDistribucionBO lista: listasDistribucion ) {
            destinatarios[ i ] = lista.getNombreLista();
            i++;
        }
        CorreoBO correo = null;
        try {
            correo = factory.getCorreosDAO().queryCorreoByEstadoNomina( nomina.getEstado().getId() );
        } catch ( DAOException e ) {
            log.error( "EXCEPTION:[" + e.getMessage() + "]", e );
            throw new EnvioNominasException( "Ha ocurrido un error al enviar el correo [" + e.getMessage() + "]", e );
        }
        if ( correo != null ) {
            StringBuffer mensaje = new StringBuffer();
            mensaje.append( "Estimado(s)" );
            mensaje.append( "\n" );
            int count = 0;
            for ( String destinatario: destinatarios ) {
                if ( count == 0 ) {
                    mensaje.append( "    " );
                } else {
                    mensaje.append( ", " );
                }
                mensaje.append( destinatario );
                count++;
            }
            mensaje.append( "\n\n" );
            mensaje.append( "Con fecha " + new SimpleDate( new Date() ).toString() + " se notifica que " );
            mensaje.append( "la nómina [" + nomina.getId() + "] se encuentra [" + nomina.getEstado().getNombre() + "]." );
            mensaje.append( "\n\n" );
            mensaje.append( correo.getMensaje() );
            mensaje.append( "\n" );
            if ( nomina.getProcesosEnviados().size() > 0 ) {
                try {
                    bancoProceso = factory.getBancosProcesoDAO().queryBancoProcesoById( nomina.getBancoProcesoNomina().getId() );
                } catch ( DAOException e ) {
                    throw new EnvioNominasException( "Ocurrio un problema al obtener el banco de proceso de la nomina : [" + e.getMessage() + "]" );
                }
                mensaje.append( "\nLa nomina esta siendo procesada con el convenio [" + bancoProceso.getNombre() + "] en el banco [" + bancoProceso.getBanco().getNombre() + "] y folio(s):\n" );
                for ( ProcesoEnvioBO proceso: nomina.getProcesosEnviados() ) {
                    mensaje.append( "\n" + proceso.getFolioProcesoExterno() + " enviado el " + new SimpleDate( proceso.getFechaEnvio() ).toString() );
                }
                mensaje.append( "\n" );
            }
            mensaje.append( "\nNOTA:Este mensaje ha sido generado de forma automatica por el proceso de envio de nominas." );
            send( config.get( EnvioNominasConfig.CORREO_ORIGEN ), destinatarios, "", "", correo.getSubject(), mensaje.toString() );
        } else {
            throw new EnvioNominasException( "No hay un correo definido para el estado : [" + nomina.getEstado().getNombre() + "]" );
        }
    }

    public void send( String p_from, String[] p_to, String p_cc, String p_bcc, String p_subject, String p_message ) throws EnvioNominasException {
        Properties props = new Properties();
        props.setProperty( "mail.transport.protocol", "smtp" );
        props.setProperty( "mail.host", config.get( EnvioNominasConfig.CORREO_SERVIDOR ) );
        props.setProperty( "mail.smtp.auth", "false" );
        Session mailSession = Session.getInstance( props, null );
        mailSession.setDebug( false );
        Transport transport;
        if ( p_to == null ) {
            throw new EnvioNominasException( "No hay destinatario para el correo" );
        }
        try {
            transport = mailSession.getTransport();
        } catch ( NoSuchProviderException e ) {
            throw new EnvioNominasException( "Ha ocurrido un error al preparar el transporte de correo [" + e.getMessage() + "]", e );
        }
        MimeMessage message = new MimeMessage( mailSession );
        try {
            message.setSubject( p_subject );
            message.setFrom( new InternetAddress( p_from ) );
            for ( int i = 0; i < p_to.length; i++ ) {
                message.addRecipient( Message.RecipientType.TO, new InternetAddress( p_to[ i ], false ) );
            }
            if ( p_cc != null && !p_cc.equals( "" ) ) {
                message.addRecipient( Message.RecipientType.CC, new InternetAddress( p_cc ) );
            }
            if ( p_bcc != null && !p_bcc.equals( "" ) ) {
                message.addRecipient( Message.RecipientType.BCC, new InternetAddress( p_bcc ) );
            }
            BodyPart msgBP = new MimeBodyPart();
            msgBP.setContent( p_message, "text/plain" );
            Multipart mPart = new MimeMultipart();
            mPart.addBodyPart( msgBP );
            message.setContent( mPart );
            transport.connect();
        } catch ( MessagingException e ) {
            throw new EnvioNominasException( "Ha ocurrido un error al preparar el mensaje de correo [" + e.getMessage() + "]", e );
        }
        try {
            transport.sendMessage( message, message.getRecipients( Message.RecipientType.TO ) );
            if ( p_cc != null && !p_cc.equals( "" ) ) {
                transport.sendMessage( message, message.getRecipients( Message.RecipientType.CC ) );
            }
            if ( p_bcc != null && !p_bcc.equals( "" ) ) {
                transport.sendMessage( message, message.getRecipients( Message.RecipientType.BCC ) );
            }
            transport.close();
        } catch ( MessagingException e ) {
            throw new EnvioNominasException( "Ha ocurrido un error al enviar el correo [" + e.getMessage() + "]", e );
        }
    }
}
