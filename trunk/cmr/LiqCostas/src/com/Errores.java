package com;

import java.util.Properties;
import java.io.*;

public final class Errores implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	public Errores(){
	}
   	
   	public static final String getDescription(String Error) {
   		/*Properties props = (Properties)context.getAttribute("APPLICATION_ERROR");
   		if (props.containsKey(Error.trim())) {
   			return props.get(Error).toString().trim();
   		}*/
   		String [] s = {""};
   		return getDescription(Error,s) ;
   	}

   	public static final String getDescription(String Error, String sMensaje) {
   		String [] s = {sMensaje};
   		return getDescription(Error,s) ;
   	}

   	public static final String getDescription(String Error,String Mensaje[]) {
   		try {
   			ClassLoader loader = Thread.currentThread().getContextClassLoader();
   			InputStream in = loader.getResourceAsStream("/error.properties");
   						
   			Properties props = new Properties(); 
   			props.load(in);   		
   			//Properties props = (Properties)context.getAttribute("APPLICATION_ERROR");
   			if (props.containsKey(Error.trim())) {
   				String msg = props.get(Error.trim()).toString();
   				String [] sValor = msg.split("<\\d>");
   				msg ="";
   				for (int i=0;i<sValor.length;i++) {
   					if (Mensaje.length > i)
   						msg += sValor[i] + Mensaje[i]; 
   					else
   						msg += sValor[i];
   				}
   				return msg;
   			}
   		} catch(Exception e){};
   		return "";
   	}
   	
}
