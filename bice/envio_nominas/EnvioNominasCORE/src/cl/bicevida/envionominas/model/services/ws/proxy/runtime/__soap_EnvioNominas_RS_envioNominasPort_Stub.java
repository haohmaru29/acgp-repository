// !DO NOT EDIT THIS FILE!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (10.1.3.5.0, build 090727.2000.36696)

package cl.bicevida.envionominas.model.services.ws.proxy.runtime;

import oracle.j2ee.ws.common.streaming.*;
import oracle.j2ee.ws.common.encoding.*;
import oracle.j2ee.ws.common.soap.SOAPEncodingConstants;
import oracle.j2ee.ws.common.encoding.literal.*;
import oracle.j2ee.ws.common.soap.streaming.*;
import oracle.j2ee.ws.common.soap.message.*;
import oracle.j2ee.ws.common.soap.SOAPVersion;
import oracle.j2ee.ws.common.soap.SOAPEnvelopeConstants;
import oracle.j2ee.ws.common.wsdl.document.schema.SchemaConstants;
import oracle.j2ee.ws.common.util.exception.JAXRPCExceptionBase;
import oracle.j2ee.ws.common.util.SoapWithAttachmentsUtil;
import javax.xml.namespace.QName;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.lang.reflect.*;
import oracle.j2ee.ws.client.*;
import oracle.j2ee.ws.client.http.*;
import oracle.webservices.transport.*;
import oracle.webservices.*;
import oracle.webservices.attachments.*;
import javax.xml.rpc.handler.*;
import javax.xml.rpc.JAXRPCException;
import javax.xml.rpc.soap.SOAPFaultException;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;

public class __soap_EnvioNominas_RS_envioNominasPort_Stub
    extends oracle.j2ee.ws.client.StubBase
    implements cl.bicevida.envionominas.model.services.ws.proxy.EnvioNominasPort {
	
	private EnvioNominasConfig config;
    
    /*
     *  public constructor
     */
    public __soap_EnvioNominas_RS_envioNominasPort_Stub(HandlerChain handlerChain) {
        super(handlerChain);
        _setProperty(ENDPOINT_ADDRESS_PROPERTY, config.get(EnvioNominasConfig.ENDPOINT_ENVIONOMINAS_RS));
        setSoapVersion(SOAPVersion.SOAP_11);
        setServiceName( new QName("http://oracle.com/esb/namespaces/EnvioNominas","envioNominasPortService"));
        setPortName( new QName("http://oracle.com/esb/namespaces/EnvioNominas","__soap_EnvioNominas_RS_envioNominasPort"));
        setupConfig("cl/bicevida/envionominas/model/services/ws/proxy/runtime/__soap_EnvioNominas_RS_envioNominasPort_Stub.xml");
    }
    
    /*
     *  implementation of generarArchivo
     */
    public void generarArchivo(cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.CargaNominaInputType cargaNominaInput)
         {
        
        StreamingSenderState _state = null;
        try {
            
            _state = _start(_handlerChain);
            if (_getProperty(ClientConstants.DIME_ENCODE_MESSAGES_WITH_ATTACHMENTS) != null) {
                _state.getMessageContext().getMessage().setProperty("DimeEncode",_getProperty(ClientConstants.DIME_ENCODE_MESSAGES_WITH_ATTACHMENTS));
            }
            
            InternalSOAPMessage _request = _state.getRequest();
            _request.setOperationCode(generarArchivo_OPCODE);
            _state.getMessageContext().setProperty("oracle.j2ee.ws.mgmt.interceptor.operation-qname",new QName("","generarArchivo"));
            
            
            SOAPBlockInfo _bodyBlock = new SOAPBlockInfo(ns1_generarArchivo_CargaNominaInput_QNAME);
            _bodyBlock.setValue(cargaNominaInput);
            _bodyBlock.setSerializer(myns1_CargaNominaInputType__CargaNominaInputType__LiteralSerializersrc);
            _request.setBody(_bodyBlock);
            
            _state.getMessageContext().setProperty("http.soap.action", "generarArchivo");
            
            _sendOneWay((String) _getProperty(ENDPOINT_ADDRESS_PROPERTY), _state);
        } catch (JAXRPCException e) {
            // let this one through unchanged, one-way operation
            throw e;
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw (RuntimeException)e;
            }
        }
    }
    
    /*
     *  implementation of cargar
     */
    public cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.CargaNominaOutputType cargar(cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.CargaNominaInputType cargaNominaInput)
        throws cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.ErrorType, java.rmi.RemoteException {
        
        StreamingSenderState _state = null;
        try {
            
            _state = _start(_handlerChain);
            if (_getProperty(ClientConstants.DIME_ENCODE_MESSAGES_WITH_ATTACHMENTS) != null) {
                _state.getMessageContext().getMessage().setProperty("DimeEncode",_getProperty(ClientConstants.DIME_ENCODE_MESSAGES_WITH_ATTACHMENTS));
            }
            
            InternalSOAPMessage _request = _state.getRequest();
            _request.setOperationCode(cargar_OPCODE);
            _state.getMessageContext().setProperty("oracle.j2ee.ws.mgmt.interceptor.operation-qname",new QName("","cargar"));
            
            
            SOAPBlockInfo _bodyBlock = new SOAPBlockInfo(ns1_cargar_CargaNominaInput_QNAME);
            _bodyBlock.setValue(cargaNominaInput);
            _bodyBlock.setSerializer(myns1_CargaNominaInputType__CargaNominaInputType__LiteralSerializersrc);
            _request.setBody(_bodyBlock);
            
            _state.getMessageContext().setProperty("http.soap.action", "cargar");
            
            _send((String) _getProperty(ENDPOINT_ADDRESS_PROPERTY), _state);
            
            cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.CargaNominaOutputType _result = null;
            Object _responseObj = _state.getResponse().getBody().getValue();
            if (_responseObj instanceof SOAPDeserializationState) {
                _result = (cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.CargaNominaOutputType)((SOAPDeserializationState) _responseObj).getInstance();
            } else {
                _result = (cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.CargaNominaOutputType)_responseObj;
            }
            
            return _result;
            
        } catch (cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.ErrorType e ) {
            throw e;
        } catch (RemoteException e) {
            if (e.detail instanceof oracle.j2ee.ws.common.util.HeaderFaultException) {
                oracle.j2ee.ws.common.util.HeaderFaultException hfe = (oracle.j2ee.ws.common.util.HeaderFaultException) e.detail;
                SOAPHeaderBlockInfo headerBlock = (SOAPHeaderBlockInfo) hfe.getObject();
                Object obj = headerBlock.getValue();
            }
            // let this one through unchanged
            throw e;
        } catch (ClientTransportException e) {
            throw new RemoteException( "", e );
        } catch (JAXRPCException e) {
            throw e;
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw (RuntimeException)e;
            } else {
                throw new RemoteException(e.getMessage(), e);
            }
        }
    }
    
    /*
     *  implementation of rendir
     */
    public cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.RindeNominaOutputType rendir(cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.RindeNominaInputType rindeNominaInput)
        throws cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.ErrorType, java.rmi.RemoteException {
        
        StreamingSenderState _state = null;
        try {
            
            _state = _start(_handlerChain);
            if (_getProperty(ClientConstants.DIME_ENCODE_MESSAGES_WITH_ATTACHMENTS) != null) {
                _state.getMessageContext().getMessage().setProperty("DimeEncode",_getProperty(ClientConstants.DIME_ENCODE_MESSAGES_WITH_ATTACHMENTS));
            }
            
            InternalSOAPMessage _request = _state.getRequest();
            _request.setOperationCode(rendir_OPCODE);
            _state.getMessageContext().setProperty("oracle.j2ee.ws.mgmt.interceptor.operation-qname",new QName("","rendir"));
            
            
            SOAPBlockInfo _bodyBlock = new SOAPBlockInfo(ns1_rendir_RindeNominaInput_QNAME);
            _bodyBlock.setValue(rindeNominaInput);
            _bodyBlock.setSerializer(myns1_RindeNominaInputType__RindeNominaInputType__LiteralSerializersrc);
            _request.setBody(_bodyBlock);
            
            _state.getMessageContext().setProperty("http.soap.action", "rendir");
            
            _send((String) _getProperty(ENDPOINT_ADDRESS_PROPERTY), _state);
            
            cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.RindeNominaOutputType _result = null;
            Object _responseObj = _state.getResponse().getBody().getValue();
            if (_responseObj instanceof SOAPDeserializationState) {
                _result = (cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.RindeNominaOutputType)((SOAPDeserializationState) _responseObj).getInstance();
            } else {
                _result = (cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.RindeNominaOutputType)_responseObj;
            }
            
            return _result;
            
        } catch (cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.ErrorType e ) {
            throw e;
        } catch (RemoteException e) {
            if (e.detail instanceof oracle.j2ee.ws.common.util.HeaderFaultException) {
                oracle.j2ee.ws.common.util.HeaderFaultException hfe = (oracle.j2ee.ws.common.util.HeaderFaultException) e.detail;
                SOAPHeaderBlockInfo headerBlock = (SOAPHeaderBlockInfo) hfe.getObject();
                Object obj = headerBlock.getValue();
            }
            // let this one through unchanged
            throw e;
        } catch (ClientTransportException e) {
            throw new RemoteException( "", e );
        } catch (JAXRPCException e) {
            throw e;
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw (RuntimeException)e;
            } else {
                throw new RemoteException(e.getMessage(), e);
            }
        }
    }
    
    
    /*
     *  this method deserializes the request/response structure in the body
     */
    protected void _readFirstBodyElement(XMLReader bodyReader, SOAPDeserializationContext deserializationContext, StreamingSenderState  state) throws Exception {
        int opcode = state.getRequest().getOperationCode();
        switch (opcode) {
            case cargar_OPCODE:
                _deserialize_cargar(bodyReader, deserializationContext, state);
                break;
            case rendir_OPCODE:
                _deserialize_rendir(bodyReader, deserializationContext, state);
                break;
            default:
                throw new SenderException("sender.response.unrecognizedOperation", Integer.toString(opcode));
        }
    }
    
    
    
    
    /*
     * This method deserializes the body of the cargar operation.
     */
    private void _deserialize_cargar(XMLReader bodyReader, SOAPDeserializationContext deserializationContext, StreamingSenderState state) throws Exception {
        try {
            Object myCargaNominaOutputTypeObj =
                myns1_CargaNominaOutputType__CargaNominaOutputType__LiteralSerializersrc.deserialize(ns1_cargar_CargaNominaOutput_QNAME,
                    bodyReader, deserializationContext);
            
            SOAPBlockInfo bodyBlock = new SOAPBlockInfo(ns1_cargar_CargaNominaOutput_QNAME);
            bodyBlock.setValue(myCargaNominaOutputTypeObj);
            state.getResponse().setBody(bodyBlock);
        } catch (DeserializationException e) {
            throw e;
        }
    }
    
    /*
     * This method deserializes the body of the rendir operation.
     */
    private void _deserialize_rendir(XMLReader bodyReader, SOAPDeserializationContext deserializationContext, StreamingSenderState state) throws Exception {
        try {
            Object myRindeNominaOutputTypeObj =
                myns1_RindeNominaOutputType__RindeNominaOutputType__LiteralSerializersrc.deserialize(ns1_rendir_RindeNominaOutput_QNAME,
                    bodyReader, deserializationContext);
            
            SOAPBlockInfo bodyBlock = new SOAPBlockInfo(ns1_rendir_RindeNominaOutput_QNAME);
            bodyBlock.setValue(myRindeNominaOutputTypeObj);
            state.getResponse().setBody(bodyBlock);
        } catch (DeserializationException e) {
            throw e;
        }
    }
    
    /*
     *  this method deserializes fault responses
     */
    protected Object _readBodyFaultElement(XMLReader bodyReader, SOAPDeserializationContext deserializationContext, StreamingSenderState state) throws Exception {
        Object faultInfo = null;
        int opcode = state.getRequest().getOperationCode();
        switch (opcode) {
            case cargar_OPCODE:
                faultInfo = myEnvioNominasPort_cargar_Fault_SOAPSerializer.deserialize(null, bodyReader, deserializationContext);
                break;
            case rendir_OPCODE:
                faultInfo = myEnvioNominasPort_rendir_Fault_SOAPSerializer.deserialize(null, bodyReader, deserializationContext);
                break;
            default:
                return super._readBodyFaultElement(bodyReader, deserializationContext, state);
        }
        return faultInfo;
    }
    
    
    
    public String _getEncodingStyle() {
        return SOAPEncodingConstants.getSOAPEncodingConstants(soapVersion).getURIEncoding();
    }
    
    public void _setEncodingStyle(String encodingStyle) {
        throw new UnsupportedOperationException("cannot set encoding style");
    }
    
    public ClientTransport getClientTransport() {
        return super._getTransport();
    }
    
    
    
    
    
    /*
     * This method returns an array containing (prefix, nsURI) pairs.
     */
    protected String[] _getNamespaceDeclarations() {
        return myNamespace_declarations;
    }
    
    /*
     * This method returns an array containing the names of the headers we understand.
     */
    public QName[] _getUnderstoodHeaders() {
        return understoodHeaderNames;
    }
    
    /*
     * This method handles the case of an empty SOAP body.
     */
    protected void _handleEmptyBody(XMLReader reader, SOAPDeserializationContext deserializationContext, StreamingSenderState state) throws Exception {
    }
    
    public void _initialize(InternalTypeMappingRegistry registry) throws Exception {
        super._initialize(registry);
        myns1_RindeNominaInputType__RindeNominaInputType__LiteralSerializersrc = (CombinedSerializer)registry.getSerializer("", cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.RindeNominaInputType.class, ns1_RindeNominaInputType_TYPE_QNAME);
        myns1_CargaNominaInputType__CargaNominaInputType__LiteralSerializersrc = (CombinedSerializer)registry.getSerializer("", cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.CargaNominaInputType.class, ns1_CargaNominaInputType_TYPE_QNAME);
        myns1_RindeNominaOutputType__RindeNominaOutputType__LiteralSerializersrc = (CombinedSerializer)registry.getSerializer("", cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.RindeNominaOutputType.class, ns1_RindeNominaOutputType_TYPE_QNAME);
        myns1_CargaNominaOutputType__CargaNominaOutputType__LiteralSerializersrc = (CombinedSerializer)registry.getSerializer("", cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.CargaNominaOutputType.class, ns1_CargaNominaOutputType_TYPE_QNAME);
        ((Initializable)myEnvioNominasPort_cargar_Fault_SOAPSerializer).initialize(registry);
        ((Initializable)myEnvioNominasPort_rendir_Fault_SOAPSerializer).initialize(registry);
    }
    
    private static final QName _portName = new QName("http://oracle.com/esb/namespaces/EnvioNominas", "__soap_EnvioNominas_RS_envioNominasPort");
    private static final int generarArchivo_OPCODE = 0;
    private static final int cargar_OPCODE = 1;
    private static final int rendir_OPCODE = 2;
    private final CombinedSerializer myEnvioNominasPort_cargar_Fault_SOAPSerializer = new ReferenceableSerializerImpl(DONT_SERIALIZE_AS_REF,
        new cl.bicevida.envionominas.model.services.ws.proxy.runtime.EnvioNominasPort_cargar_Fault_SOAPSerializer(ENCODE_TYPE, NOT_NULLABLE, SOAPVersion.SOAP_11), SOAPVersion.SOAP_11);
    private final CombinedSerializer myEnvioNominasPort_rendir_Fault_SOAPSerializer = new ReferenceableSerializerImpl(DONT_SERIALIZE_AS_REF,
        new cl.bicevida.envionominas.model.services.ws.proxy.runtime.EnvioNominasPort_rendir_Fault_SOAPSerializer(ENCODE_TYPE, NOT_NULLABLE, SOAPVersion.SOAP_11), SOAPVersion.SOAP_11);
    private static final QName ns1_generarArchivo_CargaNominaInput_QNAME = new QName("http://bicevida.ws/services/envionominas", "CargaNominaInput");
    private static final QName ns1_CargaNominaInputType_TYPE_QNAME = new QName("http://bicevida.ws/services/envionominas", "CargaNominaInputType");
    private CombinedSerializer myns1_CargaNominaInputType__CargaNominaInputType__LiteralSerializersrc;
    private static final QName ns1_cargar_CargaNominaInput_QNAME = new QName("http://bicevida.ws/services/envionominas", "CargaNominaInput");
    private static final QName ns1_cargar_CargaNominaOutput_QNAME = new QName("http://bicevida.ws/services/envionominas", "CargaNominaOutput");
    private static final QName ns1_CargaNominaOutputType_TYPE_QNAME = new QName("http://bicevida.ws/services/envionominas", "CargaNominaOutputType");
    private CombinedSerializer myns1_CargaNominaOutputType__CargaNominaOutputType__LiteralSerializersrc;
    private static final QName ns1_rendir_RindeNominaInput_QNAME = new QName("http://bicevida.ws/services/envionominas", "RindeNominaInput");
    private static final QName ns1_RindeNominaInputType_TYPE_QNAME = new QName("http://bicevida.ws/services/envionominas", "RindeNominaInputType");
    private CombinedSerializer myns1_RindeNominaInputType__RindeNominaInputType__LiteralSerializersrc;
    private static final QName ns1_rendir_RindeNominaOutput_QNAME = new QName("http://bicevida.ws/services/envionominas", "RindeNominaOutput");
    private static final QName ns1_RindeNominaOutputType_TYPE_QNAME = new QName("http://bicevida.ws/services/envionominas", "RindeNominaOutputType");
    private CombinedSerializer myns1_RindeNominaOutputType__RindeNominaOutputType__LiteralSerializersrc;
    private static final String[] myNamespace_declarations =
                                        new String[] {
                                            "ns0", "http://bicevida.ws/services/envionominas"
                                        };
    
    private static final QName[] understoodHeaderNames = new QName[] {  };
    
}
