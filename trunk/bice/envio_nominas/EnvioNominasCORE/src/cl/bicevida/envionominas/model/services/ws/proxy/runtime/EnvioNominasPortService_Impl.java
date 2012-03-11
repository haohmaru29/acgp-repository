// !DO NOT EDIT THIS FILE!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (10.1.3.5.0, build 090727.2000.36696)

package cl.bicevida.envionominas.model.services.ws.proxy.runtime;

import oracle.j2ee.ws.common.encoding.*;
import oracle.j2ee.ws.client.ServiceExceptionImpl;
import oracle.j2ee.ws.common.util.exception.*;
import oracle.j2ee.ws.common.soap.SOAPVersion;
import oracle.j2ee.ws.client.HandlerChainImpl;
import javax.xml.rpc.*;
import javax.xml.rpc.encoding.*;
import javax.xml.rpc.handler.HandlerChain;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.namespace.QName;

public class EnvioNominasPortService_Impl extends oracle.j2ee.ws.client.BasicService implements cl.bicevida.envionominas.model.services.ws.proxy.EnvioNominasPortService {
    private static final QName serviceName = new QName("http://oracle.com/esb/namespaces/EnvioNominas", "envioNominasPortService");
    private static final QName ns1___soap_EnvioNominas_RS_envioNominasPort_QNAME = new QName("http://oracle.com/esb/namespaces/EnvioNominas", "__soap_EnvioNominas_RS_envioNominasPort");
    private static final Class envioNominasPort_PortClass = cl.bicevida.envionominas.model.services.ws.proxy.EnvioNominasPort.class;
    
    public EnvioNominasPortService_Impl() {
        super(serviceName, new QName[] {
                        ns1___soap_EnvioNominas_RS_envioNominasPort_QNAME
                    },
            new cl.bicevida.envionominas.model.services.ws.proxy.runtime.EnvioNominasPortService_SerializerRegistry().getRegistry());
        
    }
    
    public java.rmi.Remote getPort(QName portName, Class serviceDefInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (portName.equals(ns1___soap_EnvioNominas_RS_envioNominasPort_QNAME) &&
                serviceDefInterface.equals(envioNominasPort_PortClass)) {
                return get__soap_EnvioNominas_RS_envioNominasPort();
            }
        } catch (Exception e) {
            throw new ServiceExceptionImpl(new LocalizableExceptionAdapter(e));
        }
        return super.getPort(portName, serviceDefInterface);
    }
    
    public java.rmi.Remote getPort(Class serviceDefInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (serviceDefInterface.equals(envioNominasPort_PortClass)) {
                return get__soap_EnvioNominas_RS_envioNominasPort();
            }
        } catch (Exception e) {
            throw new ServiceExceptionImpl(new LocalizableExceptionAdapter(e));
        }
        return super.getPort(serviceDefInterface);
    }
    
    public cl.bicevida.envionominas.model.services.ws.proxy.EnvioNominasPort get__soap_EnvioNominas_RS_envioNominasPort() {
        String[] roles = new String[] {};
        HandlerChainImpl handlerChain = new HandlerChainImpl(getHandlerRegistry().getHandlerChain(ns1___soap_EnvioNominas_RS_envioNominasPort_QNAME));
        handlerChain.setRoles(roles);
        cl.bicevida.envionominas.model.services.ws.proxy.runtime.__soap_EnvioNominas_RS_envioNominasPort_Stub stub = new cl.bicevida.envionominas.model.services.ws.proxy.runtime.__soap_EnvioNominas_RS_envioNominasPort_Stub(handlerChain);
        try {
            stub._initialize(super.internalTypeRegistry);
        } catch (JAXRPCException e) {
            throw e;
        } catch (Exception e) {
            throw new JAXRPCException(e.getMessage(), e);
        }
        return stub;
    }
}