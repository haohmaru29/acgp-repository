package cl.bicevida.core.view.jsf.mb;

import cl.bicevida.core.model.services.ws.proxy.oid.OidBasicWebServiceSoapHttpPortClient;
import cl.bicevida.core.utils.empresa.RutUtils;
import cl.bicevida.core.view.jsf.utils.JsfUtils;

import cl.bicevida.core.view.resources.messages.Resources;

import cl.bicevida.core.view.utils.BvWebLog;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class MbUserInfo {
    protected String userName, rut;
    public static final String GRUPO_PRUEBA = "TEST_USERS";
    protected boolean hayDatosBdClientes = false;

    public MbUserInfo() {
        //Observe lo siguiente: en los constructores del resto de los managed 
        //beans (excepto en el de Logout) se fuerza la carga del managed
        //bean MbUsuario para que se inicialicen los datos del usuario 
        //conectado.
        //Esto se logra con la siguiente línea:}
        //MbUserInfo mbUserInfo = (MbUserInfo)JsfUtils.getValue("#{MbUserInfo}");
        BvWebLog.log( MbUserInfo.class.getName(), BvWebLog.LEVEL_INFO, "Iniciando UserInfo." );
        loadUserInfo();
    }

    public boolean isUsuarioCambiado( String userName ) {
        return this.userName != userName;
    }

    public void loadUserInfo() {
        BvWebLog.log( MbUserInfo.class.getName(), BvWebLog.LEVEL_INFO, "Cargando información de usuario." );
        loadBasicUserInfo();
        loadDetailUserInfo();
        BvWebLog.log( MbUserInfo.class.getName(), BvWebLog.LEVEL_INFO, "Información de usuario cargada." );
    }

    private void loadBasicUserInfo() {
        //Obtiene info del usuario web conectado y consulta algunas propiedades
        //desde el OID.
        userName = JsfUtils.getExternalContext().getRemoteUser();
        if ( userName != null && userName.length() > 0 ) {
            if ( esUsuarioRut( userName ) ) {
                //Si el nombre del usuario es un RUT válido entonces se asigna
                //el mismo nombre de usuario a la propiedad RUT
                rut = userName;
            } else {
                //Si el nombre del usuario NO es un RUT válido entonces se
                //obtiene el valor de la propiedad employeenumber desde
                //el OID.  La propiedad empoyeenumber contiene el RUT de un 
                //usuario <nombre.apellido>.
                try {
                    OidBasicWebServiceSoapHttpPortClient myPort = new OidBasicWebServiceSoapHttpPortClient();
                    BvWebLog.log( MbUserInfo.class.getName(), BvWebLog.LEVEL_INFO, "Conectando con WS: " + myPort.getEndpoint() );
                    String val[] = myPort.getUserPropertyValues( userName, "employeenumber" );
                    if ( val != null && val.length > 0 ) {
                        rut = val[ 0 ];
                    }
                } catch ( Exception ex ) {
                    rut = "";
                    BvWebLog.log( MbUserInfo.class.getName(), BvWebLog.LEVEL_ERROR, "Error de acceso a OidBasicWebService", ex );
                }
            }
        }
    }

    private void loadDetailUserInfo() {
        hayDatosBdClientes = false;
        //TODO: Pong aquí el código para cargar información de usuario desde
        // la base de datos de clientes, utilizando el cliente proxy de WS
    }

    public boolean isUserInRole( String pRoleName ) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext ectx = ctx.getExternalContext();
        return ectx.isUserInRole( pRoleName );
    }

    public String getUserName() {
        return userName;
    }

    /**
     * Método que indica si el usuario que se logueo esta dentro del grupo de pruebas de la aplicación.
     * @return Boolean
     */
    public boolean isUsuarioPrueba() {
        return isUserInRole( "GRUPO_PRUEBA" );
    }

    private boolean esUsuarioRut( String p_UserName ) {
        return RutUtils.isRutMediumFormat( p_UserName );
    }

    public void setHayDatosBdClientes( boolean hayDatosBdClientes ) {
        this.hayDatosBdClientes = hayDatosBdClientes;
    }

    public boolean isHayDatosBdClientes() {
        return hayDatosBdClientes;
    }

    public void setRut( String rut ) {
        this.rut = rut;
    }

    public String getRut() {
        return rut;
    }

    public void setUserName( String userName ) {
        this.userName = userName;
    }
}
