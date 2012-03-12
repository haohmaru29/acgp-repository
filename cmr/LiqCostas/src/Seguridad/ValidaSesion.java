package Seguridad;

import javax.servlet.http.*;

public class ValidaSesion {
    
	public ValidaSesion() {
    }

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
    	System.out.println("session id -------->" + sesion.getId());
    	String value = (sesion.getAttribute("menu")==null)?"": sesion.getAttribute("menu").toString();
    	System.out.println(value);
    	return value;
    }
}