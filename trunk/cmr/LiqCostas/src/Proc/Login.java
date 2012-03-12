package Proc;

import java.util.*;
import java.io.*; 
import javax.naming.*; 

import org.jboss.logging.Logger;

import java.sql.*;

import bd.DBAcceso;
import oracle.jdbc.OracleTypes;
import utils.LoggerInstance;

public class Login {

	 CallableStatement cStmt = null;
	 ResultSet rs = null;
	 Connection conn = null;
	 String nomPaquete="";
	 private static final Logger logger = Logger.getLogger(Login.class);
	    
	 public Login(){
		 cStmt = null;
	     rs = null;
	     nomPaquete="PaqLogin";
	}
	 
	public boolean ObtieneUsuario(String Usuario, ArrayList resultado) 
		throws SQLException, IOException, NamingException {
    	boolean ret = false;    	    	       
        try {
        	DBAcceso ObjBD = DBAcceso.getInstance();         	
        	conn = ObjBD.connect();       
        	if (conn!= null) {
	        	String LineaComando=DBAcceso.buildProcedureCall(nomPaquete, "obtieneUsuario",2);
	        	
	        	cStmt = conn.prepareCall(LineaComando);
	            cStmt.setString(1, Usuario.toString());
	            cStmt.registerOutParameter(2, OracleTypes.CURSOR);
	            cStmt.execute();
	            rs = (ResultSet) cStmt.getObject(2);   
	            //obtenemos la data
	            if (rs != null) {
		    		while (rs.next()) {
		    			ArrayList aux = new ArrayList();
		    			for (int j = 1; j <= rs.getMetaData().getColumnCount(); j++) {
		    				String valor = rs.getString(j);
		    	            if (rs.wasNull())
		    	            	valor = "";
		    	            else
		    	            	valor = valor.trim();
		    	            
		    	            aux.add(valor);
		    	        }
		    			resultado.add(aux);
		    		} 
		    		ret = true;
	    		} 
        	}        	
        } catch (Exception e) {
        	//LoggerInstance.error(Thread.currentThread().getStackTrace()[2] , e);
        	logger.error(" [LiqCostas] " , e);
        } finally {
        	DBAcceso.close(rs, cStmt, conn);
        }
        return ret;
      }
  
	public boolean ObtieneMenuUsuario(String Usuario, ArrayList resultado) 
		throws SQLException, IOException, NamingException {
    	boolean ret = false;    	    	       
        try {
        	DBAcceso ObjBD = DBAcceso.getInstance();        	
        	conn = ObjBD.connect();
        	if (conn!= null) {
	        	String LineaComando=DBAcceso.buildProcedureCall(nomPaquete, "obtieneMenuUsuario",2);
	        	cStmt = conn.prepareCall(LineaComando);
	            cStmt.setString(1, Usuario.toString());
	            cStmt.registerOutParameter(2, OracleTypes.CURSOR);
	            
	        	cStmt.execute();
	            rs = (ResultSet) cStmt.getObject(2);   
	            //obtenemos la data
	            if (rs != null) {
		    		while (rs.next()) {
		    			ArrayList aux = new ArrayList();
		    			for (int j = 1; j <= rs.getMetaData().getColumnCount(); j++) {
		    				String valor = rs.getString(j);
		    	            if (rs.wasNull())
		    	            	valor = "";
		    	            else
		    	            	valor = valor.trim();
		    	            
		    	            aux.add(valor);
		    	        }
		    			resultado.add(aux);
		    		}
		    		ret=true;
	    		} 
        	}
        } catch (Exception e) {
        	//LoggerInstance.error(Thread.currentThread().getStackTrace()[2] , e);
        	logger.error(" [LiqCostas] " , e);
        } finally {
        	DBAcceso.close(rs, cStmt, conn);
        }
        return ret;
      }
	
	public boolean ObtieneAcceso(String Usuario) 
		throws SQLException, IOException, NamingException {
    	boolean ret = false;    
    	int retorno =0;
        try {
        	DBAcceso ObjBD = DBAcceso.getInstance();         	
        	conn = ObjBD.connect();
        	if (conn!= null) {
	        	String LineaComando=DBAcceso.buildProcedureCall(nomPaquete, "obtieneAcceso",2);
	        	cStmt = conn.prepareCall(LineaComando);
	            cStmt.setString(1, Usuario.toString());
	            cStmt.registerOutParameter(2, OracleTypes.INTEGER);
	            cStmt.execute();
	        	retorno=(Integer) cStmt.getObject(2);
	        	if (retorno>0)
	        		ret=true;	        	
        	}        	
        } catch (Exception e) {
        	//LoggerInstance.error(Thread.currentThread().getStackTrace()[2] , e);
        	logger.error(" [LiqCostas] " , e);
        } finally {
        	DBAcceso.close(rs, cStmt, conn);
        }
        return ret;
      }
}