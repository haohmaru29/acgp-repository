package Seguridad;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import Config.*;
import Proc.Login;
import utils.*;

public class Menu {
	
	private ArrayList hm = new ArrayList();
	
	public ConfigItem ProcesaXml ()
	  {
	    try
	    {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream in = loader.getResourceAsStream("/Menu.xml");	    	
			ConfigXMLHandler oHand = new ConfigXMLHandler();
	    	oHand.parseXML(in);
	    	return oHand.getObjectConfig();
	    }
	    catch (Exception e) {
	    	LoggerInstance.error(e.getStackTrace()[1] , e);
	    }
	    return null;
	  }
	 
		public ArrayList obtieneMenuUsuario(String sUsuario) throws SQLException, IOException, NamingException {
			ArrayList resultado = new ArrayList();
	        try {
	        		Proc.Login ObjProc = new Login();
	            	resultado = new ArrayList();
	            	
	            	boolean result = ObjProc.ObtieneMenuUsuario(sUsuario, resultado);
	                
	                 
	        } catch (Exception e){
	        	LoggerInstance.error(Thread.currentThread().getStackTrace()[2] , e);
	        }    
	        return (ArrayList)resultado.clone();
	    }

		
		public String genMenu(ConfigItem obj) {
			String sMenu ="";
			if (obj==null) return "[]";
			
			ArrayList arrItems = (ArrayList)obj.getArrayMenus();
			if (arrItems.size()>0){
				for (int i=0;i<arrItems.size();i++) { 
					if (i>0) sMenu += ",";
					obj = (ConfigItem)arrItems.get(i);
					sMenu += "new mnuMenu('" + obj.getDescripcion()+ "','" + obj.getPagina() + "'," + (arrItems.size()==0) + "," + genMenu(obj) + ")";
				}
				sMenu = "[" + sMenu + "]";

			}else
				sMenu = "[]";
						
			return  sMenu;
	   }

		
		private ConfigItem getMenu(ConfigItem obj) {
			ArrayList arrObj = obj.getArrayMenus();
			ConfigItem oItem = null;
			if (arrObj.size()<=0)  {//nodo hoja
					if (hm.contains(obj.getFuncionalidad().trim()))
						oItem = obj;
			} else {
				ArrayList arrNvo = new ArrayList(); //contiene solo los que tienen permiso
				for (int i=0;i<arrObj.size();i++){
					ConfigItem ci = getMenu((ConfigItem)arrObj.get(i));
					if (ci!=null)
						arrNvo.add(ci);
				}
				if (arrNvo.size()>0) {
					obj.setArrayMenu(arrNvo);
					oItem = obj;
				} else
					oItem = null;
				
			}
			
			return oItem;
		}
		
		public ConfigItem getAccesoMenu(String sUsuario) {
				ConfigItem ci = new ConfigItem();
				
				try{
					ArrayList arrMenu = obtieneMenuUsuario(sUsuario);
					hm.add("0"); //siempre se muestra
					for (int i=0;i<arrMenu.size();i++) {
						ArrayList arrPaso = new ArrayList();
						arrPaso = (ArrayList)arrMenu.get(i);
						String sFunc = arrPaso.get(1).toString().trim();
						if (!hm.contains(sFunc))
							hm.add(sFunc);
					}
										
					ConfigItem oItem = getMenu(ProcesaXml ());
								
					
					ci = oItem;
					
				}catch(Exception e) {};
				return ci;
		}
		
}
