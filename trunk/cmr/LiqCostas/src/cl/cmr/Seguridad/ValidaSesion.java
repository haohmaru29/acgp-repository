package cl.cmr.Seguridad;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class ValidaSesion {
	
	private static final Logger logger = Logger.getLogger(ValidaSesion.class);
	
    public int valida(HttpServletRequest request) {
        int ret = 1;
    	try { 
    		HttpSession sesion = request.getSession(false);
    		if (sesion.getAttribute("usuario") == null) {
    			sesion.invalidate();
    		} else
    			ret =0;
    	} catch (Exception e){};
    	return ret;
    }  

    public String getMenu(HttpServletRequest request) {
    	HttpSession sesion = request.getSession(false);
    	String value = (sesion.getAttribute("menu")==null)?"": sesion.getAttribute("menu").toString();
    	logger.info("Menu: " + value);
    	return value;
    }
}