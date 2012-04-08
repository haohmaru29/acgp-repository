package Proc;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import oracle.jdbc.OracleTypes;

import org.jboss.logging.Logger;

import bd.DBAcceso;

public class Login {

	private static final String nomPaquete="PaqLogin";
	private static final Logger logger = Logger.getLogger(Login.class); 
	private CallableStatement cStmt = null;
	private ResultSet rs = null;
	private Connection conn = null;
	 
	    
	public Login(){
		cStmt = null;
		rs = null;
	}
	 
	public boolean ObtieneUsuario(String Usuario, ArrayList<ArrayList<String>> resultado) 
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
		    			ArrayList<String> aux = new ArrayList<String>();
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
        	logger.error(" [LiqCostas] " , e);
        } finally {
        	DBAcceso.close(rs, cStmt, conn);
        }
        return ret;
      }
  
	public boolean ObtieneMenuUsuario(String Usuario, ArrayList<ArrayList<String>> resultado) throws SQLException, IOException, NamingException {
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
		    			ArrayList<String> aux = new ArrayList<String>();
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
        	logger.error(" [LiqCostas] " , e);
        } finally {
        	DBAcceso.close(rs, cStmt, conn);
        }
        return ret;
      }
}