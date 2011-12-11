// This source file is generated by Oracle tools and is subject to change
// It is a utility client for invoking the operations of the Web service port.
// For reporting problems, use the following
// Version = Oracle WebServices (10.1.3.0.0, build 060119.1546.05277)

package cl.bicevida.core.model.services.ws.proxy.bdclientes.parametros;

import cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.TipoDireccionModel;
import cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.RetListaTipoDireccion;

import java.io.BufferedInputStream;

import java.io.IOException;

import java.net.URL;

import java.util.Properties;

import oracle.webservices.transport.ClientTransport;
import oracle.webservices.OracleStub;
import javax.xml.rpc.ServiceFactory;
import javax.xml.rpc.Stub;

public class WSClientesParamFaseTresSoapHttpPortClient {
    private cl.bicevida.core.model.services.ws.proxy.bdclientes.parametros.WSClientesParamFaseTres_PortType _port;
    
    public WSClientesParamFaseTresSoapHttpPortClient() throws Exception {
        ServiceFactory factory = ServiceFactory.newInstance();
        _port = ((cl.bicevida.core.model.services.ws.proxy.bdclientes.parametros.WSClientesParamFaseTres_Service)factory.loadService(cl.bicevida.core.model.services.ws.proxy.bdclientes.parametros.WSClientesParamFaseTres_Service.class)).getWSClientesParamFaseTresSoapHttpPort();
        loadResourceEndPoint();
    }
    
    public void loadResourceEndPoint() throws IOException {    
        URL url = this.getClass().getResource("../../WebServicesProxies.properties");
        BufferedInputStream bis = (BufferedInputStream)url.getContent();
        Properties prop = new Properties();
        prop.load(bis);
        String endpoint = prop.getProperty("cl.bicevida.bdclientes.parametros.endpoint");
        this.setEndpoint(endpoint);
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            cl.bicevida.core.model.services.ws.proxy.bdclientes.parametros.WSClientesParamFaseTresSoapHttpPortClient myPort = new cl.bicevida.core.model.services.ws.proxy.bdclientes.parametros.WSClientesParamFaseTresSoapHttpPortClient();
            System.out.println("calling " + myPort.getEndpoint());
            RetListaTipoDireccion rltd = myPort.getListaTipoDireccion();
            for (TipoDireccionModel tdm : rltd.getListaTipoDireccion()){
                System.out.println(tdm.getDescripcion());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * delegate all operations to the underlying implementation class.
     */
    
    public cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.RetListaAFP getListaAFP() throws java.rmi.RemoteException {
        return _port.getListaAFP();
    }
    
    public cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.RetListaActividad getListaActividad() throws java.rmi.RemoteException {
        return _port.getListaActividad();
    }
    
    public cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.RetListaCodigoArea getListaCodigosArea() throws java.rmi.RemoteException {
        return _port.getListaCodigosArea();
    }
    
    public cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.RetListaComuna getListaComuna() throws java.rmi.RemoteException {
        return _port.getListaComuna();
    }
    
    public cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.RetListaEstadoCivil getListaEstadoCivil() throws java.rmi.RemoteException {
        return _port.getListaEstadoCivil();
    }
    
    public cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.RetListaIsapre getListaIsapre() throws java.rmi.RemoteException {
        return _port.getListaIsapre();
    }
    
    public cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.RetListaLocalidad getListaLocalidad() throws java.rmi.RemoteException {
        return _port.getListaLocalidad();
    }
    
    public cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.RetListaNacionalidad getListaNacionalidad() throws java.rmi.RemoteException {
        return _port.getListaNacionalidad();
    }
    
    public cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.RetListaNivelEducacional getListaNivelEducacional() throws java.rmi.RemoteException {
        return _port.getListaNivelEducacional();
    }
    
    public cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.RetListaNivelIngreso getListaNivelIngreso() throws java.rmi.RemoteException {
        return _port.getListaNivelIngreso();
    }
    
    public cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.RetListaOcupacion getListaOcupacion() throws java.rmi.RemoteException {
        return _port.getListaOcupacion();
    }
    
    public cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.RetListaProfesion getListaProfesion() throws java.rmi.RemoteException {
        return _port.getListaProfesion();
    }
    
    public cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.RetListaRegion getListaRegion() throws java.rmi.RemoteException {
        return _port.getListaRegion();
    }
    
    public cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.RetListaSexo getListaSexo() throws java.rmi.RemoteException {
        return _port.getListaSexo();
    }
    
    public cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.RetListaTipoCalle getListaTipoCalle() throws java.rmi.RemoteException {
        return _port.getListaTipoCalle();
    }
    
    public cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.RetListaTipoDireccion getListaTipoDireccion() throws java.rmi.RemoteException {
        return _port.getListaTipoDireccion();
    }
    
    public cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.RetListaTipoPersona getListaTipoPersona() throws java.rmi.RemoteException {
        return _port.getListaTipoPersona();
    }
    
    public cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.RetListaTipoVivienda getListaTipoVivienda() throws java.rmi.RemoteException {
        return _port.getListaTipoVivienda();
    }
    
    
    /**
     * used to access the JAX-RPC level APIs
     * returns the interface of the port instance
     */
    public cl.bicevida.core.model.services.ws.proxy.bdclientes.parametros.WSClientesParamFaseTres_PortType getPort() {
        return _port;
    }
    
    public String getEndpoint() {
        return (String) ((Stub) _port)._getProperty(Stub.ENDPOINT_ADDRESS_PROPERTY);
    }
    
    public void setEndpoint(String endpoint) {
        ((Stub) _port)._setProperty(Stub.ENDPOINT_ADDRESS_PROPERTY, endpoint);
    }
    
    public String getPassword() {
        return (String) ((Stub) _port)._getProperty(Stub.PASSWORD_PROPERTY);
    }
    
    public void setPassword(String password) {
        ((Stub) _port)._setProperty(Stub.PASSWORD_PROPERTY, password);
    }
    
    public String getUsername() {
        return (String) ((Stub) _port)._getProperty(Stub.USERNAME_PROPERTY);
    }
    
    public void setUsername(String username) {
        ((Stub) _port)._setProperty(Stub.USERNAME_PROPERTY, username);
    }
    
    public void setMaintainSession(boolean maintainSession) {
        ((Stub) _port)._setProperty(Stub.SESSION_MAINTAIN_PROPERTY, Boolean.valueOf(maintainSession));
    }
    
    public boolean getMaintainSession() {
        return ((Boolean) ((Stub) _port)._getProperty(Stub.SESSION_MAINTAIN_PROPERTY)).booleanValue();
    }
    
    /**
     * returns the transport context
     */
    public ClientTransport getClientTransport() {
        return ((OracleStub) _port).getClientTransport();
    }
    
}
