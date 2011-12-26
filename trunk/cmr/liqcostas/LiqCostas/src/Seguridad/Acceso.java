package Seguridad;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.http.*;

import utils.LoggerInstance;

import java.util.Properties;
import java.util.logging.Level;

import Proc.Login;

public class Acceso {

    boolean result;
    ArrayList arrMenu = new ArrayList();
    HttpServletRequest request;
    
    public Acceso(HttpServletRequest request) {

	    this.request = request;
	    try {
	    	obtieneMenuUsuario(arrMenu);
	    } catch(Exception ex) {}	    
    }
	
	private String getNameUri(String sName) {
		if (sName.lastIndexOf("/") > -1 )
			sName = sName.substring(sName.lastIndexOf("/")+1);
		else if (sName.lastIndexOf("\\") > -1)
			sName = sName.substring(sName.lastIndexOf("\\")+1);
		
		if (sName.lastIndexOf(".") > -1 )
			sName = sName.substring(0,sName.lastIndexOf("."));
		return sName;
	}
	
	public boolean tieneAcceso() {
		try {
   			ClassLoader loader = Thread.currentThread().getContextClassLoader();
   			InputStream in = loader.getResourceAsStream("/menus.properties");
			Properties props = new Properties(); 
   			props.load(in);
			HttpSession sesion = request.getSession(false);
	
			 if (sesion!=null) {
				Seguridad.SessionUsuario  oSes =(Seguridad.SessionUsuario)sesion.getAttribute("usuario");
				if (oSes!=null) {
					String sName = request.getServletPath();
					sName = getNameUri(sName).toUpperCase();
					if (props.containsKey(sName)) {
						if (Integer.parseInt(props.getProperty(sName).toString()) == 0)
							return true;
						for (int i=0;i<arrMenu.size();i++ ) {
							ArrayList arrAux = ((ArrayList)arrMenu.get(i));
							if (Integer.parseInt(arrAux.get(1).toString()) == Integer.parseInt(props.get(sName).toString())) {
								return true;
							}
						}
					} else if (sName.equals("BLANK"))
						return true;
				} 
			 }			 
		}catch(Exception e){} 
		return false;
	}
	
	public boolean obtieneMenuUsuario(ArrayList resultado) throws SQLException, IOException, NamingException 
	{
        try {
    		HttpSession sesion = request.getSession(false);
    		if (sesion!=null) {
    			
    			Proc.Login ObjProc = new Login();
    			
    			SessionUsuario oUsuario = (sesion.getAttribute("usuario")!=null)?(SessionUsuario)sesion.getAttribute("usuario"):null;
    			
    			if (oUsuario==null)
    					return false;
    			
    			result=ObjProc.ObtieneMenuUsuario(oUsuario.getUsuario().trim(), resultado);
    			
                return result;
                
    		} 
        } catch (Exception e){
        	LoggerInstance.error(Thread.currentThread().getStackTrace()[2] , e);
        }    
        return false;
    }
}
