// !DO NOT EDIT THIS FILE!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (10.1.3.0.0, build 060119.1546.05277)

package cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.runtime;

import oracle.j2ee.ws.common.encoding.*;
import oracle.j2ee.ws.common.encoding.literal.*;
import oracle.j2ee.ws.common.encoding.literal.DetailFragmentDeserializer;
import oracle.j2ee.ws.common.encoding.simpletype.*;
import oracle.j2ee.ws.common.soap.SOAPEncodingConstants;
import oracle.j2ee.ws.common.soap.SOAPEnvelopeConstants;
import oracle.j2ee.ws.common.soap.SOAPVersion;
import oracle.j2ee.ws.common.streaming.*;
import oracle.j2ee.ws.common.wsdl.document.schema.SchemaConstants;
import oracle.j2ee.ws.common.util.xml.UUID;
import javax.xml.namespace.QName;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.AttachmentPart;

public class GetListaOcupacionElement_LiteralSerializer extends LiteralObjectSerializerBase implements Initializable {
    
    public GetListaOcupacionElement_LiteralSerializer(QName type) {
        this(type,  false);
    }
    
    public GetListaOcupacionElement_LiteralSerializer(QName type, boolean encodeType) {
        super(type, true, encodeType);
    }
    
    public void initialize(InternalTypeMappingRegistry registry) throws Exception {
    }
    
    public java.lang.Object doDeserialize(XMLReader reader,
        SOAPDeserializationContext context) throws Exception {
        cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.GetListaOcupacionElement instance = new cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.GetListaOcupacionElement();
        java.lang.Object member=null;
        QName elementName;
        List values;
        java.lang.Object value;
        
        reader.nextElementContent();
        if( reader.getState() != XMLReader.END)
        {
            reader.skipElement();
        }
        XMLReaderUtil.verifyReaderState(reader, XMLReader.END);
        return (java.lang.Object)instance;
    }
    
    public void doSerializeAttributes(java.lang.Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
        cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.GetListaOcupacionElement instance = (cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.GetListaOcupacionElement)obj;
        
    }
    public void doSerializeAnyAttributes(java.lang.Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
        cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.GetListaOcupacionElement instance = (cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.GetListaOcupacionElement)obj;
        
    }
    public void doSerialize(java.lang.Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
        cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.GetListaOcupacionElement instance = (cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.GetListaOcupacionElement)obj;
        
    }
}
