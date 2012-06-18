// !DO NOT EDIT THIS FILE!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (10.1.3.0.0, build 060119.1546.05277)

package cl.bicevida.core.model.services.ws.types.oidservices.utils.types.runtime;

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

public class StringArray_LiteralSerializer extends LiteralObjectSerializerBase implements Initializable {
    private static final QName ns3_String_QNAME = new QName("http://utils.oidservices.bicevida.cl/types/", "String");
    private static final QName ns2_string_TYPE_QNAME = SchemaConstants.QNAME_TYPE_STRING;
    private CombinedSerializer myns2_string__java_lang_String_String_Serializer;
    
    public StringArray_LiteralSerializer(QName type) {
        this(type,  false);
    }
    
    public StringArray_LiteralSerializer(QName type, boolean encodeType) {
        super(type, true, encodeType);
    }
    
    public void initialize(InternalTypeMappingRegistry registry) throws Exception {
        myns2_string__java_lang_String_String_Serializer = (CombinedSerializer)registry.getSerializer("", java.lang.String.class, ns2_string_TYPE_QNAME);
    }
    
    public java.lang.Object doDeserialize(XMLReader reader,
        SOAPDeserializationContext context) throws Exception {
        cl.bicevida.core.model.services.ws.types.oidservices.utils.types.StringArray instance = new cl.bicevida.core.model.services.ws.types.oidservices.utils.types.StringArray();
        java.lang.Object member=null;
        QName elementName;
        List values;
        java.lang.Object value;
        
        reader.nextElementContent();
        elementName = reader.getName();
        if ((reader.getState() == XMLReader.START) && (matchQName(elementName, ns3_String_QNAME))) {
            values = new ArrayList();
            for(;;) {
                elementName = reader.getName();
                if ((reader.getState() == XMLReader.START) && (matchQName(elementName, ns3_String_QNAME))) {
                    myns2_string__java_lang_String_String_Serializer.setNullable( true );
                    value = myns2_string__java_lang_String_String_Serializer.deserialize(ns3_String_QNAME, reader, context);
                    values.add(value);
                    reader.nextElementContent();
                } else {
                    break;
                }
            }
            member = new java.lang.String[values.size()];
            member = values.toArray((java.lang.Object[]) member);
            instance.setString((java.lang.String[])member);
        }
        else {
            instance.setString(new java.lang.String[0]);
        }
        
        if( reader.getState() != XMLReader.END)
        {
            reader.skipElement();
        }
        XMLReaderUtil.verifyReaderState(reader, XMLReader.END);
        return (java.lang.Object)instance;
    }
    
    public void doSerializeAttributes(java.lang.Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
        cl.bicevida.core.model.services.ws.types.oidservices.utils.types.StringArray instance = (cl.bicevida.core.model.services.ws.types.oidservices.utils.types.StringArray)obj;
        
    }
    public void doSerializeAnyAttributes(java.lang.Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
        cl.bicevida.core.model.services.ws.types.oidservices.utils.types.StringArray instance = (cl.bicevida.core.model.services.ws.types.oidservices.utils.types.StringArray)obj;
        
    }
    public void doSerialize(java.lang.Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
        cl.bicevida.core.model.services.ws.types.oidservices.utils.types.StringArray instance = (cl.bicevida.core.model.services.ws.types.oidservices.utils.types.StringArray)obj;
        
        if (instance.getString() != null) {
            for (int i = 0; i < instance.getString().length; ++i) {
                myns2_string__java_lang_String_String_Serializer.setNullable( true );
                myns2_string__java_lang_String_String_Serializer.serialize(instance.getString()[i], ns3_String_QNAME, null, writer, context);
            }
        }
    }
}