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

public class GetListaOcupacionResponseElement_LiteralSerializer extends LiteralObjectSerializerBase implements Initializable {
    private static final QName ns1_result_QNAME = new QName("http://param.ws.fasetres.actualizadatos.bicevida.cl/types/", "result");
    private static final QName ns1_RetListaOcupacion_TYPE_QNAME = new QName("http://param.ws.fasetres.actualizadatos.bicevida.cl/types/", "RetListaOcupacion");
    private CombinedSerializer myns1_RetListaOcupacion__RetListaOcupacion_LiteralSerializer;
    
    public GetListaOcupacionResponseElement_LiteralSerializer(QName type) {
        this(type,  false);
    }
    
    public GetListaOcupacionResponseElement_LiteralSerializer(QName type, boolean encodeType) {
        super(type, true, encodeType);
    }
    
    public void initialize(InternalTypeMappingRegistry registry) throws Exception {
        myns1_RetListaOcupacion__RetListaOcupacion_LiteralSerializer = (CombinedSerializer)registry.getSerializer("", cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.RetListaOcupacion.class, ns1_RetListaOcupacion_TYPE_QNAME);
    }
    
    public java.lang.Object doDeserialize(XMLReader reader,
        SOAPDeserializationContext context) throws Exception {
        cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.GetListaOcupacionResponseElement instance = new cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.GetListaOcupacionResponseElement();
        java.lang.Object member=null;
        QName elementName;
        List values;
        java.lang.Object value;
        
        reader.nextElementContent();
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if ( matchQName(elementName, ns1_result_QNAME) ) {
                myns1_RetListaOcupacion__RetListaOcupacion_LiteralSerializer.setNullable( true );
                member = myns1_RetListaOcupacion__RetListaOcupacion_LiteralSerializer.deserialize(ns1_result_QNAME, reader, context);
                instance.setResult((cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.RetListaOcupacion)member);
                context.setXmlFragmentWrapperName( null );
                reader.nextElementContent();
            } else {
                throw new DeserializationException("literal.unexpectedElementName", new java.lang.Object[] { ns1_result_QNAME, reader.getName() }, DeserializationException.FAULT_CODE_CLIENT);
            }
        }
        else {
            throw new DeserializationException("literal.expectedElementName", reader.getName().toString(), DeserializationException.FAULT_CODE_CLIENT );
        }
        
        if( reader.getState() != XMLReader.END)
        {
            reader.skipElement();
        }
        XMLReaderUtil.verifyReaderState(reader, XMLReader.END);
        return (java.lang.Object)instance;
    }
    
    public void doSerializeAttributes(java.lang.Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
        cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.GetListaOcupacionResponseElement instance = (cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.GetListaOcupacionResponseElement)obj;
        
    }
    public void doSerializeAnyAttributes(java.lang.Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
        cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.GetListaOcupacionResponseElement instance = (cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.GetListaOcupacionResponseElement)obj;
        
    }
    public void doSerialize(java.lang.Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
        cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.GetListaOcupacionResponseElement instance = (cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.GetListaOcupacionResponseElement)obj;
        
        myns1_RetListaOcupacion__RetListaOcupacion_LiteralSerializer.setNullable( true );
        myns1_RetListaOcupacion__RetListaOcupacion_LiteralSerializer.serialize(instance.getResult(), ns1_result_QNAME, null, writer, context);
    }
}
