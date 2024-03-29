// This source file is generated by Oracle tools and is subject to change
// It is a utility client for invoking the operations of the Web service port.
// For reporting problems, use the following
// Version = Oracle WebServices (10.1.3.0.0, build 060119.1546.05277)

package cl.bicevida.core.model.services.ws.proxy.oid;

import java.io.BufferedInputStream;

import java.io.IOException;

import java.net.URL;

import java.rmi.RemoteException;

import java.util.Properties;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import oracle.webservices.transport.ClientTransport;
import oracle.webservices.OracleStub;
import javax.xml.rpc.ServiceFactory;
import javax.xml.rpc.Stub;

public class OidBasicWebServiceSoapHttpPortClient {
    private cl.bicevida.core.model.services.ws.proxy.oid.OidBasicWebService_PortType _port;
    
    public OidBasicWebServiceSoapHttpPortClient() throws Exception {
        ServiceFactory factory = ServiceFactory.newInstance();
        _port = ((cl.bicevida.core.model.services.ws.proxy.oid.OidBasicWebService_Service)factory.loadService(cl.bicevida.core.model.services.ws.proxy.oid.OidBasicWebService_Service.class)).getOidBasicWebServiceSoapHttpPort();
        
        loadResourceEndPoint();
    }
    
    public void loadResourceEndPoint() throws IOException {    
        /*URL url = this.getClass().getResource("/cl/bicevida/core/model/services/ws/proxy/WebServicesProxies.properties");
        BufferedInputStream bis = (BufferedInputStream)url.getContent();
        Properties prop = new Properties();
        prop.load(bis);
        String endpoint = prop.getProperty("cl.bicevida.oidservices.endpoint");*/
        String endpoint;
        ResourceBundle res = PropertyResourceBundle.getBundle("cl.bicevida.core.model.services.ws.proxy.WebServicesProxies");
        endpoint = res.getString("cl.bicevida.oidservices.endpoint");
        this.setEndpoint(endpoint);
    }
    /**
     * @param args
     */
    public static void main(String[] args) throws RemoteException, Exception {
        //*** INICIO: Llamada a Proxy de OidBasicWebService ***
        // El siguiente bloque de c�digo ejemplifica el uso del proxy del web service OidBasicWebService
        cl.bicevida.core.model.services.ws.proxy.oid.OidBasicWebServiceSoapHttpPortClient myPort = new cl.bicevida.core.model.services.ws.proxy.oid.OidBasicWebServiceSoapHttpPortClient();
            System.out.println("calling " + myPort.getEndpoint());
            System.out.println(myPort.existsUser("1-9"));
        //*** FIN: Llamada a Proxy de OidBasicWebService ***
    }
    
    /**
     * delegate all operations to the underlying implementation class.
     */
    
    public boolean allocateGroupToMainGroup(String groupMain, String groupChild) throws java.rmi.RemoteException {
        return _port.allocateGroupToMainGroup(groupMain, groupChild);
    }
    
    public boolean allocateUserMembership(String user, String[] gruposCN) throws java.rmi.RemoteException {
        return _port.allocateUserMembership(user, gruposCN);
    }
    
    public boolean createGroup(String groupName, String description) throws java.rmi.RemoteException {
        return _port.createGroup(groupName, description);
    }
    
    public boolean createUser(String uid, String cn, String sn, String mail, String givenname, String fechanacimiento, String accountname, String userpassword, String telefono) throws java.rmi.RemoteException {
        return _port.createUser(uid, cn, sn, mail, givenname, fechanacimiento, accountname, userpassword, telefono);
    }
    
    public boolean createUser2(String[] propertyNames, String[] propertyValues) throws java.rmi.RemoteException {
        return _port.createUser2(propertyNames, propertyValues);
    }
    
    public boolean deallocateGroupToMainGroup(String groupMain, String groupChild) throws java.rmi.RemoteException {
        return _port.deallocateGroupToMainGroup(groupMain, groupChild);
    }
    
    public boolean deallocateUserMembership(String user, String[] gruposCN) throws java.rmi.RemoteException {
        return _port.deallocateUserMembership(user, gruposCN);
    }
    
    public boolean deleteGroup(String[] groupName) throws java.rmi.RemoteException {
        return _port.deleteGroup(groupName);
    }
    
    public boolean deleteGroupsFromGroup(String group) throws java.rmi.RemoteException {
        return _port.deleteGroupsFromGroup(group);
    }
    
    public boolean deleteUsersFromGroup(String group) throws java.rmi.RemoteException {
        return _port.deleteUsersFromGroup(group);
    }
    
    public boolean existsUser(String user) throws java.rmi.RemoteException {
        return _port.existsUser(user);
    }
    
    public String[] findGroupsCn() throws java.rmi.RemoteException {
        return _port.findGroupsCn();
    }
    
    public String[] findGroupsCnByFilter(String filtro) throws java.rmi.RemoteException {
        return _port.findGroupsCnByFilter(filtro);
    }
    
    public String[] findGroupsDN() throws java.rmi.RemoteException {
        return _port.findGroupsDN();
    }
    
    public String[] findGroupsDnByFilter(String filtro) throws java.rmi.RemoteException {
        return _port.findGroupsDnByFilter(filtro);
    }
    
    public String[] getUserGroupsCN(String user) throws java.rmi.RemoteException {
        return _port.getUserGroupsCN(user);
    }
    
    public String[] getUserGroupsDN(String user) throws java.rmi.RemoteException {
        return _port.getUserGroupsDN(user);
    }
    
    public String[] getUserPropertyValues(String user, String propertyName) throws java.rmi.RemoteException {
        return _port.getUserPropertyValues(user, propertyName);
    }
    
    public boolean intelliActualizarUsuario(String pRut, String pNombres, String pApellidos, String pMail, String pFechaNacimiento, String pUserPassword, String pTelefono, String[] pGroups) throws java.rmi.RemoteException {
        return _port.intelliActualizarUsuario(pRut, pNombres, pApellidos, pMail, pFechaNacimiento, pUserPassword, pTelefono, pGroups);
    }
    
    public java.util.List listGroupsInGroup(String string_1) throws java.rmi.RemoteException {
        return _port.listGroupsInGroup(string_1);
    }
    
    public java.util.List listUsersInGroup(String string_1) throws java.rmi.RemoteException {
        return _port.listUsersInGroup(string_1);
    }
    
    public boolean modifyUserProperty(String user, String propertyName, String[] values) throws java.rmi.RemoteException {
        return _port.modifyUserProperty(user, propertyName, values);
    }
    
    public boolean validateUserPassword(String cn, String password) throws java.rmi.RemoteException {
        return _port.validateUserPassword(cn, password);
    }
    
    
    /**
     * used to access the JAX-RPC level APIs
     * returns the interface of the port instance
     */
    public cl.bicevida.core.model.services.ws.proxy.oid.OidBasicWebService_PortType getPort() {
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
