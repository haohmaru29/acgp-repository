package cl.cmr.Seguridad;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cl.cmr.Proc.Login;

public class Acceso {

	private static final Logger logger = Logger.getLogger(Acceso.class);
    boolean result;
    ArrayList<ArrayList<String>> arrMenu = new ArrayList<ArrayList<String>>();
    HttpServletRequest request;
    
    public Acceso(HttpServletRequest request) {
	    this.request = request;
	    try {
	    	obtieneMenuUsuario(arrMenu);
	    } catch(Exception ex) {
	    	logger.error("[LiqCostas]" , ex);
	    }	    
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
	
	private boolean loadAcceso() {
		boolean response = false;
		ResourceBundle bundle = ResourceBundle.getBundle("menus");
		String sName = request.getServletPath();
		sName = getNameUri(sName).toUpperCase();
		String welcome = "";
		try {
			welcome = bundle.getString(sName);
		} catch(Exception e  ) { }
		if ("0".equals(welcome) )
			return true;
		else if(sName.equals("BLANK")) {
			return true;
		}
		List<?> arrAux = null;	
		for (int i=0;i<arrMenu.size();i++ ) {
			arrAux = (List<?>) arrMenu.get(i);
			if (Integer.parseInt(arrAux.get(1).toString()) == Integer.parseInt(welcome)) {
				return true;
			}
		}
		
		logger.info("[LiqCostas] " + sName);
		
		return response;
	}
	
	public boolean tieneAcceso() {
		//InputStream in = null;
		try {
   			//ClassLoader loader = Thread.currentThread().getContextClassLoader();
   			//in = loader.getResourceAsStream("/menus.properties");
   			//logger.info("Acceso : " + loadAcceso() );
			//Properties props = new Properties();
   			//props.load(in);
   			HttpSession sesion = request.getSession(false);
			 if (sesion!=null) {
				SessionUsuario  oSes =(SessionUsuario)sesion.getAttribute("usuario");
				if (oSes!=null) {
					/*
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
						*/
					
					return loadAcceso();
				} 
			 }			 
		}catch(Exception e){
			logger.error(" [LiqCostas] " , e);
		} 
		return false;
	}
	
	public boolean obtieneMenuUsuario(ArrayList<ArrayList<String>> resultado) throws SQLException, IOException, NamingException {
        try {
    		HttpSession sesion = request.getSession(false);
    		if (sesion!=null) {
    			Login ObjProc = new Login();
    			SessionUsuario oUsuario = (sesion.getAttribute("usuario")!=null)?(SessionUsuario)sesion.getAttribute("usuario"):null;
    			if (oUsuario==null) {
    				return false;
    			}
    			result=ObjProc.ObtieneMenuUsuario(oUsuario.getUsuario().trim(), resultado);
                return result;
    		} 
        } catch (Exception e){
        	logger.error(" [LiqCostas] " , e);
        }    
        return false;
    }
}
