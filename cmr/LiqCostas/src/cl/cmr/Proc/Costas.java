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

public class Costas {

	 private static final String NomPaqueteCosta="PaqCostas";
	 private static Costas instance;
	 private static final Logger logger = Logger.getLogger(Costas.class);
		 
	public static Costas getInstance() {
		if(instance==null) {
			instance = new Costas();
		}
		
		return instance;
	} 
	
	 public String IngresarEncCostas(String User,String CodAbogado,String RutPrestador,String Moneda,String MontoCosta,String TipoDoc, String NumDoc,int TipoProducto, int Grupo) throws SQLException, IOException, NamingException {
		    String Sret ="-1";
		    CallableStatement cStmt = null;
    		Connection conn = null;
	    	try {
	    		DBAcceso ObjBD = DBAcceso.getInstance();        	
	        	conn = ObjBD.connect();       
	        	if (conn!= null) {
		        	String LineaComando=DBAcceso.buildProcedureCall(NomPaqueteCosta,"IngresoENCCostas",10);
		        	cStmt = conn.prepareCall(LineaComando);
		        	cStmt.setString(1, User.toString());
		            cStmt.setString(2, CodAbogado.toString());
		            cStmt.setString(3, RutPrestador.toString());
		            cStmt.setString(4, Moneda.toString());
		            cStmt.setString(5, MontoCosta.toString());
		            cStmt.setString(6, TipoDoc.toString());
		            cStmt.setString(7, NumDoc.toString());
		            cStmt.setInt(8, TipoProducto);
		            cStmt.setInt(9, Grupo);		            
		            cStmt.registerOutParameter(10, OracleTypes.VARCHAR);
		            cStmt.execute();
		            Sret=(String) cStmt.getObject(10);
	        	}
	        } catch (Exception e) {
	        	logger.error(" [LiqCostas] " , e);
	        } finally {
	        	DBAcceso.close(cStmt, conn);
	        }
    	return Sret;
	 }
	 
	 public String actualizaEncCostas(String idSec) {
		 String Sret ="-1";
		 String sql = "UPDATE LIQ_ENCLIQCOSTAS SET CODESTADO=2 WHERE IDSEC=" + idSec;
		 DBAcceso ObjBD = DBAcceso.getInstance();
		 PreparedStatement ps = null;
		 CallableStatement cStmt = null;
 		 Connection conn = null;
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
	 
	 public String IngresarDetCosta(String NumCosta,int TipoProducto,String CodAbogado, String RutCliente,String Operacion,String Monto, String TipoCargo,String SubTipoCargo, String NumJuicio, String TipoJuicio,String Usuario, String Supervisor) 
	 	throws SQLException, IOException, NamingException {
		    String Sret ="-1";
		    CallableStatement cStmt = null;
    		Connection conn = null;
	    	try {
	    		DBAcceso ObjBD = DBAcceso.getInstance();       	
	        	conn = ObjBD.connect();       
	        	if (conn!= null) {
		        	String LineaComando=DBAcceso.buildProcedureCall(NomPaqueteCosta,"IngresoDETCostas",13);
		        	cStmt = conn.prepareCall(LineaComando);
		        	cStmt.setString(1, NumCosta.toString());
		        	cStmt.setInt(2, TipoProducto);
		        	cStmt.setString(3, CodAbogado.toString());
		            cStmt.setString(4, RutCliente.toString());
		            cStmt.setString(5, Operacion.toString());
		            cStmt.setString(6, Monto.toString());
		            cStmt.setString(7, TipoCargo.toString());	
		            cStmt.setString(8, SubTipoCargo.toString());
		            cStmt.setString(9, NumJuicio.toString());
		            cStmt.setString(10, TipoJuicio.toString());
		            cStmt.setString(11, Usuario.toString());
		            cStmt.setString(12, Supervisor.toString());
		            cStmt.registerOutParameter(13, OracleTypes.VARCHAR);
		            cStmt.execute();
		            Sret=(String) cStmt.getObject(13);	            
	        	}
	        } catch (Exception e) {
	        	logger.error(" [LiqCostas] " , e);
	        } finally {
	        	DBAcceso.close(cStmt, conn);
	        }
	        return Sret;
	 }
	 public int ObtieneEncDetCostas(String NumCosta,int TipoProducto,ArrayList resEncCosta,ArrayList resDetCosta) 
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
		        	String LineaComando=DBAcceso.buildProcedureCall(NomPaqueteCosta, "ObtenerEncDetCosta",4);
		        	cStmt = conn.prepareCall(LineaComando);
		            cStmt.setString(1, NumCosta.toString());
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
			    			resEncCosta.add(aux);
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
				    			resDetCosta.add(aux);
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
	 
	 public int ConsultarCostas(String NumCosta,String NumCheque,int FechaDesde,int FechaHasta,String CodSucursal,String CodAbogado,int CodEstado,int CodProducto,String TipoDocumento,String NumDocumento,int FechaDesc,ArrayList resCosta) 
	 	throws SQLException, IOException, NamingException {
	    	int ret = 0;   //ok
	    	ResultSet rsDet = null;
	    	CallableStatement cStmt = null;
    		Connection conn = null;
	        try {
	        	DBAcceso ObjBD = DBAcceso.getInstance();         	
	        	conn = ObjBD.connect();
	        	if (conn!= null) {
		        	String LineaComando=DBAcceso.buildProcedureCall(NomPaqueteCosta, "ObtenerConsultaCosta",12);
		        	cStmt = conn.prepareCall(LineaComando);
		            if (NumCosta.equals(""))
		            	cStmt.setString(1, "0");
		            else
		            	cStmt.setString(1, NumCosta.toString());
		         
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
		            
		            if (TipoDocumento.equals(""))
		            	cStmt.setString(9, "0");
		            else
		            	cStmt.setString(9, TipoDocumento.toString());
		            
		            if (NumDocumento.equals(""))
		            	cStmt.setString(10, "0");
		            else
		            	cStmt.setString(10, NumDocumento.toString());
		            
		            cStmt.setInt(11, FechaDesc);
		            cStmt.registerOutParameter(12, OracleTypes.CURSOR);
		            cStmt.execute();	       	
		        	rsDet = (ResultSet) cStmt.getObject(12); 
		           
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
	 
	 public String IngresarCostasCEXPNS(String User,String SupUsers,String NumCosta) 
	 	throws SQLException, IOException, NamingException {
		 	CallableStatement cStmt = null;
 			Connection conn = null;
		    String Sret ="-2";
	    	try {
	    		DBAcceso ObjBD = DBAcceso.getInstance();       	
	        	conn = ObjBD.connect();       
	        	if (conn!= null) {
		        	String LineaComando=DBAcceso.buildProcedureCall(NomPaqueteCosta,"GrabarCAEXPNS",4);
		        	cStmt = conn.prepareCall(LineaComando);
		        	cStmt.setString(1, NumCosta.toString());
		        	cStmt.setString(2, User.toString());
		            cStmt.setString(3, SupUsers.toString());		           
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
	 
	 public int ConsultarResumenCostas(int FechaDesde,int FechaHasta,String CodSucursal,ArrayList resCosta) 
	 	throws SQLException, IOException, NamingException {
	    	int ret = 0;   //ok
	    	ResultSet rsDet = null;
	    	CallableStatement cStmt = null;
    		Connection conn = null;
	        try {
	        	DBAcceso ObjBD = DBAcceso.getInstance();       	
	        	conn = ObjBD.connect();
	        	if (conn!= null) {
		        	String LineaComando=DBAcceso.buildProcedureCall(NomPaqueteCosta, "ObtenerResumenCosta",4);
		        	cStmt = conn.prepareCall(LineaComando);
		            cStmt.setInt(1, FechaDesde);
		            cStmt.setInt(2, FechaHasta);
		            if (CodSucursal.equals(""))
		            	cStmt.setString(3, "0");
		            else
		            	cStmt.setString(3, CodSucursal.toString());
		            cStmt.registerOutParameter(4, OracleTypes.CURSOR);
		            cStmt.execute();	      	
		        	rsDet = (ResultSet) cStmt.getObject(4); 
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
	
	 
	 public int ConsultarDetalleCostas(int FechaDesde,int FechaHasta,String CodSucursal,String CodAbogado,int TipoProducto,ArrayList resCostaDet) 
	 	throws SQLException, IOException, NamingException {
	    	int ret = 0;   //ok
	    	ResultSet rsDet = null;
	    	CallableStatement cStmt = null;
    		Connection conn = null;
	    	try {
	    		DBAcceso ObjBD = DBAcceso.getInstance();        	
	        	conn = ObjBD.connect();   
	        	
	        	if (conn!= null) {
		        	String LineaComando=DBAcceso.buildProcedureCall(NomPaqueteCosta, "ObtenerDetalleCosta",6);
		        	cStmt = conn.prepareCall(LineaComando);
		            cStmt.setInt(1, FechaDesde);
		            cStmt.setInt(2, FechaHasta);
		            if (CodSucursal.equals(""))
		            	cStmt.setString(3, "0");
		            else
		            	cStmt.setString(3, CodSucursal.toString());
		            
		            if (CodAbogado.equals(""))
		            	cStmt.setString(4, "0");
		            else
		            	cStmt.setString(4, CodAbogado.toString());
		            
		            cStmt.setInt(5, TipoProducto);
		            cStmt.registerOutParameter(6, OracleTypes.CURSOR);
		            cStmt.execute();
		        	rsDet = (ResultSet) cStmt.getObject(6);
		            //obtenemos la data de tarjeta
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
				    		resCostaDet.add(aux);
				    	}			    		
			    	}    			     
	        	} else
	        		ret=-2; //error base de datos
	        } catch (Exception e) {
	        	logger.error(" [LiqCostas] " , e);
	        } finally {
	        	DBAcceso.close(rsDet,cStmt, conn);	        	
	        }
	        return ret;
	      }
	 
	 public String ValidaTipoCargo(int TipoProducto,String TipoCosta,String TipoJuicio) 
	 	throws SQLException, IOException, NamingException {
		    String Sret ="-2";
		    CallableStatement cStmt = null;
    		Connection conn = null;
	    	try {
	    		DBAcceso ObjBD = DBAcceso.getInstance();       	
	        	conn = ObjBD.connect();       
	        	if (conn!= null) {
		        	String LineaComando=DBAcceso.buildProcedureCall(NomPaqueteCosta,"ValidaCostaJuicio",4);
		        	cStmt = conn.prepareCall(LineaComando);
		        	cStmt.setInt(1, TipoProducto);
		        	if (TipoCosta.equals(""))
			           	cStmt.setString(2, "0");
			        else
			          	cStmt.setString(2, TipoCosta.toString());
		        	if (TipoJuicio.equals(""))
			           	cStmt.setString(3, "0");
			        else
			          	cStmt.setString(3, TipoJuicio.toString());    
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