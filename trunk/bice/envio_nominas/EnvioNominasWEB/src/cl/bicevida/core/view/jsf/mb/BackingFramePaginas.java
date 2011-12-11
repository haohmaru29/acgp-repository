package cl.bicevida.core.view.jsf.mb;

import cl.bicevida.core.view.jsf.utils.JsfUtils;

import java.util.Date;
import java.util.Map;
import java.util.Random;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;

public class BackingFramePaginas {
    public BackingFramePaginas() {
        MbUserInfo mbUserInfo = ( MbUserInfo ) JsfUtils.getValue( "#{MbUserInfo}" );
        MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
        String userName = JsfUtils.getExternalContext().getRemoteUser();
        if ( mbUserInfo.isUsuarioCambiado( userName ) ) {
            mbUserInfo.loadUserInfo();
        }
    }

    public String getExternalParameters() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext ectx = ctx.getExternalContext();
        Map m = ectx.getRequestParameterMap();
        Random rnd = new Random( ( new Date() ).getTime() );
        String pagina;
        if ( m.get( "pagina" ) != null ) {
            pagina = m.get( "pagina" ).toString();
        } else {
            //throw new RuntimeException ("No hay parámetros");
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( null, "No se ha especificado el parámetro requerido.", null, "", "startHere" );
            return "";
        }
        HttpServletRequest req = ( HttpServletRequest ) ectx.getRequest();
        return "http://" + req.getServerName() + ":" + req.getServerPort() + ectx.getRequestContextPath() + "/faces/" + pagina + "?nocache=" + rnd.nextInt();
    }

    public String getUrl() {
        return getExternalParameters();
    }
}
