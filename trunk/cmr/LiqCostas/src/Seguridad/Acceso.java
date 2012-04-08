package Seguridad;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import Proc.Login;

public class Acceso {

	private static final Logger logger = Logger.getLogger(Acceso.class);
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
		InputStream in = null;
		try {
   			ClassLoader loader = Thread.currentThread().getContextClassLoader();
   			in = loader.getResourceAsStream("/menus.properties");
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
		}catch(Exception e){
			logger.error(" [LiqCostas] " , e);
		} finally {
			closeInput(in);
		}
		return false;
	}
	
	private void closeInput(InputStream in) {
		try {
			if(in != null)
			in.close();
		} catch (IOException e) {
			logger.error(" [LiqCostas] " , e);
		}
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
        	logger.error(" [LiqCostas] " , e);
        }    
        return false;
    }
}
