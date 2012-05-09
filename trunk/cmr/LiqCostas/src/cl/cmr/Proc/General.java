package cl.cmr.Proc;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;

import cl.cmr.bd.DBAcceso;
import cl.cmr.utils.AppenderUtils;

public class General {

	 private static final String nomPaquete="PaqGeneral";
	 private static General instance;
	 private static final Logger logger = Logger.getLogger(General.class);
	 	    
	 private General(){
		 AppenderUtils.getInstance().setParams(logger);
	 }
	 
	 public static General getInstance(){
		 if(instance==null) {
			 instance = new General();
		 }
		
		 return instance;
	 }
	 
	 public boolean ObtieneElemLista(String nomLista,String Elemento,ArrayList<ArrayList<String>> resultado) 
	 		throws SQLException, IOException, NamingException {
	    	boolean ret = false;    	    	
	    	CallableStatement cStmt = null;
	   	 	ResultSet rs = null;
	   	 	Connection conn = null;
	        try {
	        	DBAcceso ObjBD = DBAcceso.getInstance();         	
	        	conn = ObjBD.connect(); 
	        	if (conn != null) {
	        		String LineaComando=DBAcceso.buildProcedureCall(nomPaquete, "obtieneElemLista",3);
		        	cStmt = conn.prepareCall(LineaComando);
		            cStmt.setString(1, nomLista.toString());
		            cStmt.setString(2, Elemento.toString());
		            cStmt.registerOutParameter(3, OracleTypes.CURSOR);
		            cStmt.execute();
		            rs = (ResultSet) cStmt.getObject(3);   
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
			    			ret = true;
			    			resultado.add(aux);
			    		} 			    		
		    		} 
		            if (ret==false) {
		            	ArrayList<String> aux = new ArrayList<String>();
		            	resultado.add(aux);
		            }
	        	}   	
	        } catch (Exception e) {
	        	logger.error(" [LiqCostas] " , e);
	        } finally {
	        	DBAcceso.close(rs, cStmt, conn);
	        }
	        return ret;
	      }
	 
	
	 public boolean ObtieneElemListaRem(String nomLista,ArrayList<ArrayList<String>> resultado) 
	 	throws SQLException, IOException, NamingException {
	    	boolean ret = false;    	    	      
	    	CallableStatement cStmt = null;
	   	 	ResultSet rs = null;
	   	 	Connection conn = null;
	        try {
	        	DBAcceso ObjBD = DBAcceso.getInstance();       	
	        	conn = ObjBD.connect();   
	        	
	        	if (conn!= null)
	        	{
	        		String LineaComando=DBAcceso.buildProcedureCall(nomPaquete, "obtieneElemListaRem",2);
		        	
		        	cStmt = conn.prepareCall(LineaComando);
		            cStmt.setString(1, nomLista.toString());
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
			    			ret = true;
			    			resultado.add(aux);
			    		} 			    		
		    		} 
		            if (ret==false) {
		            	ArrayList<String> aux = new ArrayList<String>();
		            	resultado.add(aux);
		            }
	        	}   	
	        } catch (Exception e) {
	        	logger.error(" [LiqCostas] " , e);
	        } finally {
	        	DBAcceso.close(rs, cStmt, conn);
	        }
	        return ret;
	 }
	 
	 public boolean ObtieneElemListaCosta(String nomLista,ArrayList<ArrayList<String>> resultado) 
	 	throws SQLException, IOException, NamingException {
	    	boolean ret = false;    	    	
	    	CallableStatement cStmt = null;
		   	ResultSet rs = null;
		   	Connection conn = null;
	        try {
	        	DBAcceso ObjBD = DBAcceso.getInstance();        	
	        	conn = ObjBD.connect();
	        	if (conn!= null) {
	        		String LineaComando=DBAcceso.buildProcedureCall(nomPaquete, "obtieneElemListaCosta",2);
		        	
		        	cStmt = conn.prepareCall(LineaComando);
		            cStmt.setString(1, nomLista.toString());
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
			    			ret = true;
			    			resultado.add(aux);
			    		} 			    		
		    		} 
		            if (ret==false) {
		            	ArrayList<String> aux = new ArrayList<String>();
		            	resultado.add(aux);
		            }
	        	}   	
	        } catch (Exception e) {
	        	logger.error(" [LiqCostas] " , e);
	        } finally {
	        	DBAcceso.close(rs, cStmt, conn);
	        }
	        return ret;
	 }
	 
	 public int ObtenerAbogado(String nomPaquete,String nomSP,String CodAbogado,String NomAbogado, String SucAbogado) 
	 	throws SQLException, IOException, NamingException {
		    int ret =-2;
		    CallableStatement cStmt = null;
			Connection conn = null;
	    	try {
	    		DBAcceso ObjBD = DBAcceso.getInstance();        	
	        	conn = ObjBD.connect();       
	        	if (conn!= null) {
		        	String LineaComando=DBAcceso.buildProcedureCall(nomPaquete, "ObtenerAbogado",4);
		        	cStmt = conn.prepareCall(LineaComando);
		            cStmt.setString(1, CodAbogado.toString());
		            cStmt.registerOutParameter(2, OracleTypes.VARCHAR);
		            cStmt.registerOutParameter(3, OracleTypes.VARCHAR);
		            cStmt.registerOutParameter(4, OracleTypes.INTEGER);
		            cStmt.execute();
		        	ret=(Integer) cStmt.getObject(4);
		        	if (ret!=-1) {
		        		NomAbogado=(String) cStmt.getObject(2);
		        		SucAbogado=(String) cStmt.getObject(3);
		            }
	        	} 	
	        	else
	        		ret=-3; //error de conexi�n
	       
	        } catch (Exception e) {
	        	logger.error(" [LiqCostas] " , e);
	        } finally {
	        	DBAcceso.close(cStmt, conn);
	        }
	        return ret;
	      }
	    	
	 public boolean ObtenerListaAbogados(int TipoProducto,ArrayList<ArrayList<String>> resultado) 
	 	throws SQLException, IOException, NamingException {
	    	boolean ret = false;    	    	    
	    	CallableStatement cStmt = null;
	   	 	ResultSet rs = null;
	   	 	Connection conn = null;
	        try {
	        	DBAcceso ObjBD = DBAcceso.getInstance();         	
	        	conn = ObjBD.connect();
	        	if (conn!= null) {
	        		String LineaComando="";	
	        		if (TipoProducto==5) //pre abogado
	        			LineaComando=DBAcceso.buildProcedureCall(nomPaquete, "ObtenerListaPreAbogado",1);
	        		else
	        			LineaComando=DBAcceso.buildProcedureCall(nomPaquete, "ObtenerListaAbogado",1);
	        		
		        	cStmt = conn.prepareCall(LineaComando);
		        	cStmt.registerOutParameter(1, OracleTypes.CURSOR);
		            
		        	cStmt.execute();
		            rs = (ResultSet) cStmt.getObject(1);   
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
	
	 public int ObtenerSubProductos(String RutCliente,String CodAbogado,int Subprod,ArrayList<ArrayList<String>> resultado) 
	 	throws SQLException, IOException, NamingException {
	    	int ret = -1;    
	    	CallableStatement cStmt = null;
	   	 	ResultSet rs = null;
	   	 	Connection conn = null;
	        try {
	        	DBAcceso ObjBD = DBAcceso.getInstance();         	
	        	conn = ObjBD.connect();
	        	if (conn!= null) {
		        	String LineaComando=DBAcceso.buildProcedureCall(nomPaquete, "ObtenerSubProductos",3);		        	
		        	cStmt = conn.prepareCall(LineaComando);
		        	cStmt.setString(1, RutCliente.toString());
		        	cStmt.setString(2, CodAbogado.toString());	
		        	//cStmt.setInt(3, Subprod);
		        	cStmt.registerOutParameter(3, OracleTypes.CURSOR);
		        	cStmt.execute();
		        	rs = (ResultSet) cStmt.getObject(3);   
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
			    		ret = 0;
		    		}
	        	}   	
	        } catch (Exception e) {
	        	logger.error(" [LiqCostas] " , e);
	        } finally {
	        	DBAcceso.close(rs, cStmt, conn);
	        }
	        
	        return ret;
	 }
	 
	 public int ObtenerSubProductosCostas(String RutCliente,String CodAbogado,int Subprod,ArrayList<ArrayList<String>> resultado) 
	 	throws SQLException, IOException, NamingException {
	    	int ret = -1;  
	    	CallableStatement cStmt = null;
	   	 	ResultSet rs = null;
	   	 	Connection conn = null;
	        try {
	        	DBAcceso ObjBD = DBAcceso.getInstance();        	
	        	conn = ObjBD.connect();
	        	if (conn!= null) {
		        	String LineaComando=DBAcceso.buildProcedureCall(nomPaquete, "ObtenerSubProductosCostas",3);		        	
		        	cStmt = conn.prepareCall(LineaComando);
		        	cStmt.setString(1, RutCliente.toString());
		        	cStmt.setString(2, CodAbogado.toString());	
		        	//cStmt.setInt(3, Subprod);
		        	cStmt.registerOutParameter(3, OracleTypes.CURSOR);
		        	cStmt.execute();
		        	rs = (ResultSet) cStmt.getObject(3);   
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
			    		ret = 0;
		    		}
	        	}   	
	        } catch (Exception e) {
	        	logger.error(" [LiqCostas] " , e);
	        } finally {
	        	DBAcceso.close(rs, cStmt, conn);
	        }
	        
	        return ret;
	      }
	 
	 public String validarSaldoCosta(String CodAbogado, String RutCliente, String codProducto) 
	 	throws SQLException, IOException, NamingException {
		    String Sret ="-2";
		    RutCliente = RutCliente.replace(".", "").replace("-", "");
		    RutCliente = RutCliente.substring(0, RutCliente.length()-1);
		    CallableStatement cStmt = null;
			ResultSet rs = null;
			Connection conn = null;
	    	try {
	    		DBAcceso ObjBD = DBAcceso.getInstance();         	
	        	conn = ObjBD.connect();       
	        	if (conn!= null) {	        	
		        	String LineaComando=DBAcceso.buildProcedureCall(nomPaquete,"validaSaldoCosta",4);
		        	cStmt = conn.prepareCall(LineaComando);
		        	cStmt.setString(1, RutCliente.toString());
		        	cStmt.setString(2, CodAbogado.toString());
		        	cStmt.setString(3, codProducto.toString());
		            cStmt.registerOutParameter(4, OracleTypes.CURSOR);
		            
		            cStmt.execute();
		            rs = (ResultSet) cStmt.getObject(4);
		            if(rs.next()) {	
		            	if(rs.getInt("dmcurbal")>0) {
		            		Sret= "1";
		            	} else {
		            		Sret= "2";
		            	}
		            }
	        	}
	        } catch (Exception e) {
	        	logger.error(" [LiqCostas] " , e);
	        } finally {
	        	DBAcceso.close(rs, cStmt, conn);
	        }
	        return Sret;
	 }
	 
	 public int ObtenerSubProductosPreAbo(String RutCliente,int Subprod,ArrayList<ArrayList<String>> resultado) 
	 	throws SQLException, IOException, NamingException {
		 	CallableStatement cStmt = null;
		 	ResultSet rs = null;
		 	Connection conn = null;
	    	int ret = -1;    	    	       
	        try {
	        	DBAcceso ObjBD = DBAcceso.getInstance();         	
	        	conn = ObjBD.connect();   
	        	
	        	if (conn!= null) {
		        	String LineaComando=DBAcceso.buildProcedureCall(nomPaquete, "ObtenerSubProductosPreAbo",2);		        	
		        	cStmt = conn.prepareCall(LineaComando);
		        	cStmt.setString(1, RutCliente.toString());
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
			    		ret = 0;
		    		}
	        	}   	
	        } catch (Exception e) {
	        	logger.error(" [LiqCostas] " , e);
	        } finally {
	        	DBAcceso.close(rs, cStmt, conn);
	        }
	        return ret;
	      }
	 
	 public boolean ObtieneElemEstado(String nomLista,String Codigo,String Elemento,String ElementoSacar,ArrayList<ArrayList<String>> resultado) 
	 	throws SQLException, IOException, NamingException {
		 	CallableStatement cStmt = null;
		 	ResultSet rs = null;
		 	Connection conn = null;
	    	boolean ret = false;    	    	       
	        try {
	        	DBAcceso ObjBD = DBAcceso.getInstance();         	
	        	conn = ObjBD.connect();
	        	if (conn!= null) {
		        	String LineaComando=DBAcceso.buildProcedureCall(nomPaquete, "obtieneElemEstados",5);
		        	cStmt = conn.prepareCall(LineaComando);
		            cStmt.setString(1, nomLista.toString());
		            cStmt.setString(2, Codigo.toString());
		            cStmt.setString(3, Elemento.toString());
		            cStmt.setString(4, ElementoSacar.toString());
			        cStmt.registerOutParameter(5, OracleTypes.CURSOR);
		            cStmt.execute();
		            rs = (ResultSet) cStmt.getObject(5);   
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
	
	 public String ObtenerParametro(String NomParametro) 
	 	throws SQLException, IOException, NamingException {
		   String Valor="";
		   CallableStatement cStmt = null;
		   Connection conn = null;
		   try {			   
			   	DBAcceso ObjBD = DBAcceso.getInstance();       	
	        	conn = ObjBD.connect();       
	        	if (conn!= null) {	        	
		        	String LineaComando=DBAcceso.buildProcedureCall(nomPaquete, "obtieneParametro",2);		        	
		        	cStmt = conn.prepareCall(LineaComando);
		            cStmt.setString(1, NomParametro.toString());
		            cStmt.registerOutParameter(2, OracleTypes.VARCHAR);
		        	cStmt.execute();
		        	Valor=(String) cStmt.getObject(2);
		    	} 	
	       
	        } catch (Exception e) {
	        	logger.error(" [LiqCostas] " , e);
	        } finally {
	        	DBAcceso.close(cStmt, conn);
	        }
	        return Valor;
	      }
	
	 public boolean obtieneListaTipoCargo(String TipoJuicio,String sTipoCosta, String sDescrip,ArrayList<ArrayList<String>> resultado) 
	 	throws SQLException, IOException, NamingException {
	     boolean ret = false;    	    
	     CallableStatement cStmt = null;
		 ResultSet rs = null;
		 Connection conn = null;
	     try { 
	    		DBAcceso ObjBD = DBAcceso.getInstance();         	
		     	conn = ObjBD.connect();   
		     	
		     	if (conn!= null) {
		     		 String LineaComando=DBAcceso.buildProcedureCall(nomPaquete, "obtieneListaTipoCargo",4);
		         	 cStmt = conn.prepareCall(LineaComando);
		             if (TipoJuicio.equals(""))
				       	cStmt.setString(1, "0");
				     else
				       	cStmt.setString(1, TipoJuicio.toString());
		             
		             if (sTipoCosta.equals(""))
			           	cStmt.setString(2, "0");
			         else
			           	cStmt.setString(2, sTipoCosta.toString());
		             
		             if (sDescrip.equals(""))
				       	cStmt.setString(3, "0");
				     else
				       	cStmt.setString(3, sDescrip.toString());
			          
		             cStmt.registerOutParameter(4, OracleTypes.CURSOR);
		             
		         	cStmt.execute();
		             rs = (ResultSet) cStmt.getObject(4);   
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
		 	    			ret = true;
		 	    			resultado.add(aux);
		 	    			//listaTipoCargo.add(aux);
		 	    		} 	
		     		} 
		            
		     	} 
	     } catch (Exception e) {
	    	 logger.error(" [LiqCostas] " , e);
	     } finally {
	     	DBAcceso.close(rs, cStmt, conn);
	     }
	     
	     return ret;
	 }
	   
	 public boolean ValidaDigitoCheque(String SNumCheque) 
	 	throws SQLException, IOException, NamingException {
			int suma = 0;
			String dv="";
			String vv="";
			int v=0;
			int j=2;
			int valor=0;
			
			dv =  SNumCheque.substring(SNumCheque.length() - 2);
			SNumCheque = SNumCheque.substring(0,SNumCheque.length() - 2);
			
			for (int i=SNumCheque.length()-1;i>=0;i--) {			
				if (j > 7 )
					j =2;		
				valor =Integer.parseInt(String.valueOf(SNumCheque.charAt(i)));
				suma += j++ *  valor;
			}
			
			v = 11 - (suma % 11);
			if (v == 10)
				  vv = "11";
			else {	  
				if (v == 11)
		        	  vv = "00";
		    	else {
		    		if (String.valueOf(v).length()==1)
		    			vv="0" + String.valueOf(v);
		    		else
		    			vv=	String.valueOf(v);
		    	}
		    }
		  if(dv.equals(vv)) 
				return true;
		  
		  return false;
	 }
	
	 public String ValidaNumCheque(String SNumCheque) 
	 	throws SQLException, IOException, NamingException {
		   String Valor="0";
		   String RangoChequeIni="";
		   String RangoChequeFin="";
		   String n = "1234567890";
		   try {	
			   RangoChequeIni=ObtenerParametro("RCHEQUEINI");
			   RangoChequeFin=ObtenerParametro("RCHEQUEFIN");
			
			   while (SNumCheque.indexOf(".") >=0)
					SNumCheque = SNumCheque.replace(".","");
			   while (SNumCheque.indexOf("-") >=0)
					SNumCheque = SNumCheque.replace("-","");
			
			   for (int i=0;i<SNumCheque.length();i++) {
					if (n.indexOf(SNumCheque.charAt(i)) < 0 ) {
						Valor="-2"; //n� unico debe ser numerico
						break;
					}	
			   }
				
			   if (Valor.equals("0")) {
				   if (SNumCheque.length() != 10)
					   Valor="-3"; //N�mero de Cheque debe ser de largo 10
			   	   else {
				   		float valdigito=Float.parseFloat(SNumCheque);
				   		if ((valdigito >= Float.parseFloat(RangoChequeIni)) && (valdigito <= Float.parseFloat(RangoChequeFin))) {
							if (ValidaDigitoCheque(SNumCheque)==false)
								Valor="-4"; // Digito verificador del Cheque es incorrecto.
						}
						else
							Valor="-5"; // N�mero de Cheque no se encuentra dentro de los rangos definidos.
						
				   }	
			   }   
	        } catch (Exception e) {
	        	logger.error(" [LiqCostas] " , e);
	        } 
	        return Valor;
	      }
	 
	 public int ObtenerJuicio(String RutCliente,ArrayList<ArrayList<String>> resultado) 
	 	throws SQLException, IOException, NamingException {
		 	CallableStatement cStmt = null;
		 	ResultSet rs = null;
		 	Connection conn = null;
	    	int ret = -1;    	    	       
	        try {
	        	DBAcceso ObjBD = DBAcceso.getInstance();        	
	        	conn = ObjBD.connect();
	        	if (conn!= null) {
		        	String LineaComando=DBAcceso.buildProcedureCall(nomPaquete, "ObtenerJuicio",2);		        	
		        	cStmt = conn.prepareCall(LineaComando);
		        	cStmt.setString(1, RutCliente.toString());
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
			    		ret = 0;
		    		}
	        	}   	
	        } catch (Exception e) {
	        	logger.error(" [LiqCostas] " , e);
	        } finally {
	        	DBAcceso.close(rs, cStmt, conn);
	        }
	        return ret;
	      }
	 	
	public String Interleaved2of5( String DataToEncode ) {
		 String Printable_string = "";
	        String DataToPrint = "";
	        int CurrentChar;
	        int i=0;
	       
	        DataToEncode = DataToEncode.trim();
	        
	        for( i = 1; i <= DataToEncode.length(); i += 2 )
	        {
	           //Get the value of each number pair (ex: 5 and 6 = 5*10+6 =56)
	           CurrentChar = ((((int)DataToEncode.charAt(i-1))-48)*10) + (((int)DataToEncode.charAt(i))-48);
	           //Get the ASCII value of CurrentChar according to chart by adding to the value
	           // (old process) DataToPrint = DataToPrint + (char)(CurrentChar + 130);
	           if( CurrentChar < 50 )
	              DataToPrint = DataToPrint + (char)(CurrentChar + 48);
	           else
	         	  DataToPrint = DataToPrint + (char)(CurrentChar + 142);
	         }

	         //Get PrintableString
	         Printable_string = (char)(40) + DataToPrint + (char)(41);
	         //Return PrintableString
	         return Printable_string;
	    }
	 
	 public String getJuicioProducto(String producto) 
	 	throws SQLException, IOException, NamingException {
		 	CallableStatement cStmt = null;
			String juicioProducto="-2";
		 	ResultSet rs = null;
		 	Connection conn = null;
	    	try {
	    		DBAcceso ObjBD = DBAcceso.getInstance();       	
	        	conn = ObjBD.connect();    
	        	if (conn!= null) {
		        	String LineaComando=DBAcceso.buildProcedureCall(nomPaquete,"juicioPorProducto",2);
		        	cStmt = conn.prepareCall(LineaComando);
		        	cStmt.setString(1, producto.toString());
		        	cStmt.registerOutParameter(2, OracleTypes.CURSOR);
		            cStmt.execute();
		            rs = (ResultSet) cStmt.getObject(2);
		            if(rs.next() ) {
		            	juicioProducto = rs.getString("cetype");
		            } 
	        	}
	        } catch (Exception e) {
	        	logger.error(" [LiqCostas] " , e);
	        } finally {
	        	DBAcceso.close(rs, cStmt, conn);
	        }
	        return juicioProducto;
	 }

}