package cl.cmr.Proc;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;

import cl.cmr.bd.DBAcceso;
import cl.cmr.utils.AppenderUtils;

public class Remesa {

	private static final String NomPaqueteRemesa="PaqRemesa";
	private static final Logger logger = Logger.getLogger(Remesa.class);
	private static Remesa instance;
	
	
	public static Remesa getInstance() {
		if(instance == null) {
			instance = new Remesa();
		}
		return instance;
	} 
	    
	private Remesa() {
	}
	
	public String actualizaEncRemesas(String idSec) {
		 CallableStatement cStmt = null;
		 Connection conn = null;
		 String Sret ="-1";
		 String sql = "UPDATE LIQ_ENCLIQCOSTAS SET CODESTADO=2 WHERE IDSEC=" + idSec;
		 DBAcceso ObjBD = DBAcceso.getInstance();
		 PreparedStatement ps = null;
		 try {
			 conn = ObjBD.connect();
			 if (conn!= null) {
				 conn.setAutoCommit(false);
				 ps = conn.prepareStatement(sql);
				 ps.executeUpdate();
				 conn.commit();
				 Sret = "1";
			 }
		 } catch(Exception e) {
			 logger.error(" [LiqCostas] " , e);
			 ObjBD.rollback(conn);
		 } finally {
			 DBAcceso.close(cStmt, conn, ps);
		 }
		 
		 return Sret;
	 }
	 
	 public String IngresarEncRemesa(String User,String CodAbogado,String FechaRemesa,String NumInterno,String Moneda,String MontoCosta,int TipoProducto, int Grupo) 
	 	throws SQLException, IOException, NamingException {
		    String Sret ="-1";
		    CallableStatement cStmt = null;
			 Connection conn = null;
	    	try {
	    		DBAcceso ObjBD = DBAcceso.getInstance();         	
	        	conn = ObjBD.connect();       
	        	if (conn!= null) {
		        	String LineaComando=DBAcceso.buildProcedureCall(NomPaqueteRemesa,"IngresoENCRemesa",9);
		        	cStmt = conn.prepareCall(LineaComando);
		        	cStmt.setString(1, User.toString());
		            cStmt.setString(2, CodAbogado.toString());
		            cStmt.setString(3, FechaRemesa.toString());
		            cStmt.setString(4, NumInterno.toString());
		            cStmt.setString(5, Moneda.toString());
		            cStmt.setString(6, MontoCosta.toString());
		            cStmt.setInt(7, TipoProducto);
		            cStmt.setInt(8, Grupo);		            
		            cStmt.registerOutParameter(9, OracleTypes.VARCHAR);
		            cStmt.execute();
		            Sret=(String) cStmt.getObject(9);
	        	}
	        } catch (Exception e) {
	        	logger.error(" [LiqCostas] " , e);
	        } finally {
	        	DBAcceso.close(cStmt, conn);
	        }
	        return Sret;
	 } 
	 
	 public String IngresarDetRemesa(String NumRemesa,int TipoProducto,String CodAbogado, String RutCliente,String Operacion,String Capital, String Interes,String Costa,String Honorario,String Boleta,String Total) 
	 	throws SQLException, IOException, NamingException {
		    String Sret ="-1";
		    CallableStatement cStmt = null;
			 Connection conn = null;
	    	try {
	    		DBAcceso ObjBD = DBAcceso.getInstance();         	
	        	conn = ObjBD.connect();       
	        	if (conn!= null) {
		        	String LineaComando=DBAcceso.buildProcedureCall(NomPaqueteRemesa,"IngresoDETRemesa",12);
		        	cStmt = conn.prepareCall(LineaComando);
		        	cStmt.setString(1, NumRemesa.toString());
		        	cStmt.setInt(2, TipoProducto);
		        	cStmt.setString(3, CodAbogado.toString());
		            cStmt.setString(4, RutCliente.toString());
		            cStmt.setString(5, Operacion.toString());
		            cStmt.setString(6, Capital.toString());
		            cStmt.setString(7, Interes.toString());
		            cStmt.setString(8, Costa.toString());
		            cStmt.setString(9, Honorario.toString());
		            cStmt.setString(10, Boleta.toString());
		            cStmt.setString(11,Total.toString());
		            cStmt.registerOutParameter(12, OracleTypes.VARCHAR);
		            cStmt.execute();
		            Sret=(String) cStmt.getObject(12);
	        	}
	        } catch (Exception e) {
	        	logger.error(" [LiqCostas] " , e);
	        } finally {
	        	DBAcceso.close(cStmt, conn);
	        }
	        return Sret;
	 }
	 
	  public int ObtieneEncDetRemesa(String NumRemesa,int TipoProducto,ArrayList resEncRemesa,ArrayList resDetRemesa) 
	  	throws SQLException, IOException, NamingException {
	    	int ret = 0;   //ok
	    	ResultSet rsEnc = null;
	    	ResultSet rsDet = null;
	    	CallableStatement cStmt = null;
			 Connection conn = null;
	        try {
	        	DBAcceso ObjBD = DBAcceso.getInstance();         	
	        	conn = ObjBD.connect();
	        	if (conn!= null) {
		        	String LineaComando=DBAcceso.buildProcedureCall(NomPaqueteRemesa, "ObtenerEncDetRemesa",4);
		        	cStmt = conn.prepareCall(LineaComando);
		            cStmt.setString(1, NumRemesa.toString());
		            cStmt.setInt(2, TipoProducto);
		            cStmt.registerOutParameter(3, OracleTypes.CURSOR);
		            cStmt.registerOutParameter(4, OracleTypes.CURSOR);
		            cStmt.execute();	        	
		        	rsEnc = (ResultSet) cStmt.getObject(3);   
		        	rsDet = (ResultSet) cStmt.getObject(4); 
		            //obtenemos la data del encabezado
		        	ret=-3; //no existe liquidacion
		            if (rsEnc != null) {
			    		while (rsEnc.next()) {
			    			ArrayList aux = new ArrayList();
			    			for (int j = 1; j <= rsEnc.getMetaData().getColumnCount(); j++) {
			    				String valor = rsEnc.getString(j);
			    	            if (rsEnc.wasNull())
			    	            	valor = "";
			    	            else
			    	            	valor = valor.trim();
			    	            
			    	            aux.add(valor);
			    	        }
			    			resEncRemesa.add(aux);
			    			ret=0;
			    		}			    		
		    		} 		            
		            if (ret==0) {	
			            //obtenemos la data del detalle
			            if (rsDet != null) {
				    		while (rsDet.next()) {
				    			ArrayList aux = new ArrayList();
				    			for (int j = 1; j <= rsDet.getMetaData().getColumnCount(); j++) {
				    				String valor = rsDet.getString(j);
				    	            if (rsDet.wasNull())
				    	            	valor = "";
				    	            else
				    	            	valor = valor.trim();
				    	            
				    	            aux.add(valor);
				    	        }
				    			resDetRemesa.add(aux);
				    		}			    		
			    		} 
			            else
			            	ret=-4; //no existe detalle para la liquidacion
		            }     
	        	}
	        	else
	        		ret=-2; //error base de datos
	        } catch (Exception e) {
	        	logger.error(" [LiqCostas] " , e);
	        } finally {
	        	DBAcceso.close(rsEnc, rsDet,cStmt, conn);
	        }
	        return ret;
	      }
	 
	 public int ConsultarRemesa(String NumRemesa,String NumCheque,int FechaDesde,int FechaHasta,String CodSucursal,String CodAbogado,int CodEstado,int CodProducto,int FechaDesc,ArrayList resCosta) 
	 	throws SQLException, IOException, NamingException {
	    	int ret = 0;   //ok
	    	ResultSet rsDet = null;
	    	CallableStatement cStmt = null;
			 Connection conn = null;
	        try {
	        	DBAcceso ObjBD = DBAcceso.getInstance();        	
	        	conn = ObjBD.connect();
	        	if (conn!= null) {
		        	String LineaComando=DBAcceso.buildProcedureCall(NomPaqueteRemesa, "ObtenerConsultaRemesa",10);
		        	cStmt = conn.prepareCall(LineaComando);
		            if (NumRemesa.equals(""))
		            	cStmt.setString(1, "0");
		            else
		            	cStmt.setString(1, NumRemesa.toString());
		         
		            if (NumCheque.equals(""))
		            	cStmt.setString(2, "0");
		            else
		            	cStmt.setString(2, NumCheque.toString());
		         
		            cStmt.setInt(3, FechaDesde);
		            cStmt.setInt(4, FechaHasta);
		            
		            if (CodAbogado.equals(""))
		            	cStmt.setString(5, "0");
		            else
		            	cStmt.setString(5, CodAbogado.toString());
		            
		            if (CodSucursal.equals(""))
		            	cStmt.setString(6, "0");
		            else
		            	cStmt.setString(6, CodSucursal.toString());
		            
		            cStmt.setInt(7, CodEstado);
		            cStmt.setInt(8, CodProducto);
		            cStmt.setInt(9, FechaDesc);	 
		            cStmt.registerOutParameter(10, OracleTypes.CURSOR);
		        	cStmt.execute();
		        	rsDet = (ResultSet) cStmt.getObject(10);
		            //obtenemos la data del detalle
			        if (rsDet != null) {
				    	while (rsDet.next()) {
				    		ArrayList aux = new ArrayList();
				    		for (int j = 1; j <= rsDet.getMetaData().getColumnCount(); j++) {
				    			String valor = rsDet.getString(j);
				                if (rsDet.wasNull())
				                	valor = "";
				                else
				                	valor = valor.trim();
				                
				                aux.add(valor);
				            }
				    		resCosta.add(aux);
				    	}			    		
			    	}    
	        	}
	        	else
	        		ret=-2; //error base de datos
	        } catch (Exception e) {
	        	logger.error(" [LiqCostas] " , e);
	        } finally {
	        	DBAcceso.close(rsDet,cStmt, conn);
	        }
	        return ret;
	      }
	 
	 public int ConsultarInformeContable(String NumRemesa,int FechaDesde,int FechaHasta,String CodAbogado,String NumBoleta,String Mayor, String Menor,ArrayList resInfLiq) 
	 	throws SQLException, IOException, NamingException {
	    	int ret = 0;   //ok
	    	ResultSet rsDet = null;
	    	CallableStatement cStmt = null;
			 Connection conn = null;
	        try {
	        	DBAcceso ObjBD = DBAcceso.getInstance();         	
	        	conn = ObjBD.connect();
	        	if (conn!= null) {
		        	String LineaComando=DBAcceso.buildProcedureCall(NomPaqueteRemesa, "ObtenerInformeContable",8);
		        	cStmt = conn.prepareCall(LineaComando);
		            if (NumRemesa.equals(""))
		            	cStmt.setString(1, "0");
		            else
		            	cStmt.setString(1, NumRemesa.toString());
		            cStmt.setInt(2, FechaDesde);
		            cStmt.setInt(3, FechaHasta);		            
		            if (CodAbogado.equals(""))
		            	cStmt.setString(4, "0");
		            else
		            	cStmt.setString(4, CodAbogado.toString());
		            if (NumBoleta.equals(""))
		            	cStmt.setString(5, "0");
		            else
		            	cStmt.setString(5, NumBoleta.toString());
		            if (Mayor.equals(""))
		            	cStmt.setString(6, "0");
		            else
		            	cStmt.setString(6, Mayor.toString());
		            if (Menor.equals(""))
		            	cStmt.setString(7, "0");
		            else
		            	cStmt.setString(7, Menor.toString());
		            
		            cStmt.registerOutParameter(8, OracleTypes.CURSOR);
		            cStmt.execute();	       	
		        	rsDet = (ResultSet) cStmt.getObject(8); 
		           
		            //obtenemos la data del detalle
			        if (rsDet != null) {
				    	while (rsDet.next()) {
				    		ArrayList aux = new ArrayList();
				    		for (int j = 1; j <= rsDet.getMetaData().getColumnCount(); j++) {
				    			String valor = rsDet.getString(j);
				                if (rsDet.wasNull())
				                	valor = "";
				                else
				                	valor = valor.trim();
				                
				                aux.add(valor);
				            }
				    		resInfLiq.add(aux);
				    	}			    		
			    	}    
	        	}
	        	else
	        		ret=-2; //error base de datos
	        } catch (Exception e) {
	        	logger.error(" [LiqCostas] " , e);
	        } finally {
	        	DBAcceso.close(rsDet,cStmt, conn);
	        }
	        return ret;
	      }
	 
	 public int ConsultarInformeEstado(String NumRem,int FechaDesde,int FechaHasta,String CodSucursal,String CodAbogado,int CodEstado,int CodProducto,int FechaDesc,int FechaPagoDesc,int FechaProc,int FechaInpu,int FechaConta,ArrayList resInfRem) 
	 	throws SQLException, IOException, NamingException {
	    	int ret = 0;   //ok
	    	ResultSet rsDet = null;
	    	CallableStatement cStmt = null;
			 Connection conn = null;
	        try {
	        	DBAcceso ObjBD = DBAcceso.getInstance();         	
	        	conn = ObjBD.connect();   
	        	
	        	if (conn!= null) {
		        	String LineaComando=DBAcceso.buildProcedureCall(NomPaqueteRemesa, "ObtenerInformeEstado",13);
		        	cStmt = conn.prepareCall(LineaComando);
		        	if (NumRem.equals(""))
			          	cStmt.setString(1, "0");
			        else
			           	cStmt.setString(1, NumRem.toString());
		            cStmt.setInt(2, FechaDesde);
		            cStmt.setInt(3, FechaHasta);
		            if (CodAbogado.equals(""))
		            	cStmt.setString(4, "0");
		            else
		            	cStmt.setString(4, CodAbogado.toString());
		            if (CodSucursal.equals(""))
		            	cStmt.setString(5, "0");
		            else
		            	cStmt.setString(5, CodSucursal.toString());
		            
		            cStmt.setInt(6, CodEstado);
		            cStmt.setInt(7, CodProducto);
		            cStmt.setInt(8, FechaDesc);
		            cStmt.setInt(9, FechaPagoDesc);
		            cStmt.setInt(10, FechaProc);
		            cStmt.setInt(11, FechaInpu);
		            cStmt.setInt(12, FechaConta);
		            cStmt.registerOutParameter(13, OracleTypes.CURSOR);
		        	cStmt.execute();
		        	rsDet = (ResultSet) cStmt.getObject(13); 
		            //obtenemos la data del detalle
			        if (rsDet != null) {
				    	while (rsDet.next()) {
				    		ArrayList aux = new ArrayList();
				    		for (int j = 1; j <= rsDet.getMetaData().getColumnCount(); j++) {
				    			String valor = rsDet.getString(j);
				                if (rsDet.wasNull())
				                	valor = "";
				                else
				                	valor = valor.trim();
				                
				                aux.add(valor);
				            }
				    		resInfRem.add(aux);
				    	}			    		
			    	}    
	        	}
	        	else
	        		ret=-2; //error base de datos
	        } catch (Exception e) {
	        	logger.error(" [LiqCostas] " , e);
	        } finally {
	        	DBAcceso.close(rsDet,cStmt, conn);
	        }
	        return ret;
	      }
	 
	 public String EliminarRemesaCosta(String NumRemesaCosta,int TipoProducto,String CodAbogado) 
	 	throws SQLException, IOException, NamingException {
		 CallableStatement cStmt = null;
		 Connection conn = null;
		    String Sret ="-1";
	    	try {
	    		DBAcceso ObjBD = DBAcceso.getInstance();         	
	        	conn = ObjBD.connect();       
	        	if (conn!= null) {
		        	String LineaComando=DBAcceso.buildProcedureCall(NomPaqueteRemesa,"EliminarRemesaCosta",4);
		        	cStmt = conn.prepareCall(LineaComando);
		        	cStmt.setString(1, NumRemesaCosta.toString());
		        	cStmt.setInt(2, TipoProducto);
		        	cStmt.setString(3, CodAbogado.toString());
		            cStmt.registerOutParameter(4, OracleTypes.VARCHAR);
		            cStmt.execute();
		            Sret=(String) cStmt.getObject(4);
	        	}
	        } catch (Exception e) {
	        	logger.error(" [LiqCostas] " , e);
	        } finally {
	        	DBAcceso.close(cStmt, conn);
	        }
	        return Sret;
	 }
}