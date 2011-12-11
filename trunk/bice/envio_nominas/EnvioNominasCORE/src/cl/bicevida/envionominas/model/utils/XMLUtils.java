package cl.bicevida.envionominas.model.utils;

import cl.bicevida.envionominas.model.bo.RindeNominaOutputBO;
import cl.bicevida.envionominas.model.bo.TransaccionPagoBO;
import cl.bicevida.envionominas.model.bo.archivo.NominaErrores;
import cl.bicevida.envionominas.model.bo.archivo.RegistroError;
import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.CargaNominaInputType;
import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.RindeNominaInputType;
import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.RindeNominaOutputType;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.util.ArrayList;


public class XMLUtils {
    private XStream xstream;

    public XMLUtils() {
        xstream = new XStream();
    }

    public String toXML( CargaNominaInputType _type ) {
        String xml = xstream.toXML( _type );
        return xml;
    }

    public String toXML( RindeNominaInputType _type ) {
        String xml = xstream.toXML( _type );
        return xml;
    }

    public CargaNominaInputType cargaNominaInputFromXML( String _xml ) {
        CargaNominaInputType _type = ( CargaNominaInputType ) xstream.fromXML( _xml );
        return _type;
    }

    public RindeNominaInputType rindeNominaInputFromXML( String _xml ) {
        RindeNominaInputType _type = ( RindeNominaInputType ) xstream.fromXML( _xml );
        return _type;
    }
    
    public RindeNominaOutputType rindeNominaOutputFromXML( String _xml ) {
        RindeNominaOutputType _type = ( RindeNominaOutputType ) xstream.fromXML( _xml );
        return _type;
    }
    
    public RindeNominaOutputBO archivoNominasOutput(String _xml) {
        //XStream xStream = new XStream(new DomDriver());
        xstream.alias("rindeNominaOutput", RindeNominaOutputBO.class);
        xstream.alias("transaccionPago", TransaccionPagoBO.class);
        xstream.addImplicitCollection(RindeNominaOutputBO.class, "transaccionPagos");
        RindeNominaOutputBO ob = (RindeNominaOutputBO) xstream.fromXML( _xml );
        
        return ob;
    }
    
    public NominaErrores archivoNominasErrores(String _xml) {
        //XStream xStream = new XStream(new DomDriver());
        xstream.alias("errorProveedores", NominaErrores.class);
        xstream.alias("registroError", RegistroError.class);
        xstream.addImplicitCollection(NominaErrores.class, "registroErrores");
        NominaErrores ob = (NominaErrores) xstream.fromXML( _xml );
        
        return ob;
    }
}
