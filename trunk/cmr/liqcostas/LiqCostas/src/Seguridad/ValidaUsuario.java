package Seguridad;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import utils.LoggerInstance;
import Proc.Login;
import javax.naming.NamingException;
import javax.servlet.http.*;

public class ValidaUsuario
{
    public ValidaUsuario(){
    }

    public int validaUsuario(HttpServletRequest request, ArrayList arrErr) throws SQLException, IOException, NamingException 
    {
    	int res = 0;
    	
        try {
	        Proc.Login ObjProc = new Login();
	        ArrayList result  = new ArrayList();
	        ArrayList resultMenu  = new ArrayList();	        
	        ArrayList aux	  = new ArrayList();
	        
	        String usuario = "";
	        String claveBD = "";
	        String clave = "";
	        String ClaveEncrip="";
	        String StrUsuario="";
	        
	        usuario = request.getParameter("USUARIO")!=null?request.getParameter("USUARIO").toString().trim():"";
	        clave   = request.getParameter("CLAVE")!=null?request.getParameter("CLAVE").toString().trim():"";
	        
	        //encriptar clave obtenida	        
	        MD5 md = new MD5();
	        ClaveEncrip=md.EncriptarMD5(clave);
	        clave=ClaveEncrip;
	        
	        boolean rest = ObjProc.ObtieneUsuario(usuario, result);
	        
	        if (rest==true)
	        {
	        	if (result.size() > 0) {
		            aux = (ArrayList)result.get(0);
		            claveBD = (String)aux.get(2);
		               
		            if (claveBD.equals(clave)){
		            	
		            	//validar si tiene acceso definidos
		            	rest = ObjProc.ObtieneAcceso(usuario);
		            	if (rest==true)
		            	{
		            		//VALIDAR SI EXISTE OTRA SESION ABIERTA PARA EL USUARIO	
		            		HttpSession sesion = request.getSession(true);
		            		if (sesion.getAttribute("usuario") == null) 
		            			StrUsuario="no hay conectados";
		            		else
		            			StrUsuario=sesion.getAttribute("usuario").toString();
		            		
		            		System.out.println("StrUsuario:"+StrUsuario+"");
		            		
		            		sesion.setAttribute("usuario",new SessionUsuario((String)aux.get(0),(String)aux.get(1),(String)aux.get(3),(String)aux.get(4),(String)aux.get(5),(String)aux.get(6),String.valueOf(request.getRemoteAddr()),(String)aux.get(7)));
			            	Menu oMenu = new Menu();
			            	String sMenu =oMenu.genMenu(oMenu.getAccesoMenu(usuario));
			            	sesion.setAttribute("menu",sMenu);
		            	}
		            	else
		            	{
		            		arrErr.add("Usuario sin acceso para ingresar al sistema.");
		            		res=-5;	
		            	}	
		            } else {
		            	arrErr.add("La clave no corresponde a la registrada en la base de datos.");
		            	res = -3;
			        }
		        } else {
		        	arrErr.add("El usuario no se encuentra registrado en la base de datos.");
		        	res = -2;
		        }
	        }
	        else {
	        	arrErr.add("Error de Conexión a la base de datos. Consulte con su Administrador.");
	        	res = -4;
	        }
	        
	        } catch (Exception e){
	        	LoggerInstance.error(Thread.currentThread().getStackTrace()[2] , e);
	            res = -1;
	        }
	        
	        return res;
	}
    
}