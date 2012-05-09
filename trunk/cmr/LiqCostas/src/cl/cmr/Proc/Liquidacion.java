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

public class Liquidacion {

	 CallableStatement cStmt = null;
	 Connection conn = null;
	 private static final String NomPaquete="PaqLiquidacion";
	 private static final Logger logger = Logger.getLogger(Liquidacion.class);
	    
	 static {
		 AppenderUtils.getInstance().setParams(logger);
	 }
	 
	 public Liquidacion(){
		 cStmt = null;
	}
	
	 public String IngresarLiqEncTarjeta(String User,String CodAbogado,String NumLiq,String Moneda,String MontoLiq, int Estado,int TipoProducto, int SubTipoProd) throws SQLException, IOException, NamingException 
	 {
		    String Sret ="-2";
	    	try {
	    		DBAcceso ObjBD = DBAcceso.getInstance();      	
	        	conn = ObjBD.connect();       
	        	if (conn!= null)
	        	{
	        	
		        	String LineaComando=DBAcceso.buildProcedureCall(NomPaquete,"IngresoENCLIQ",9);
		        	
		        	cStmt = conn.prepareCall(LineaComando);
		        	cStmt.setString(1, User.toString());
		            cStmt.setString(2, CodAbogado.toString());
		            cStmt.setString(3, NumLiq.toString());
		            cStmt.setString(4, Moneda.toString());
		            cStmt.setString(5, MontoLiq.toString());
		            cStmt.setInt(6, Estado);
		            cStmt.setInt(7, TipoProducto);
		            cStmt.setInt(8, SubTipoProd);
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

	 public String IngresarLiqDetTarjeta(String NumLiq, String RutCliente,String Cuenta, String SubProducto,String Capital, String Interes, String CostasProc, String HonorariosAbo, String NumBoleta,String IdDetalle,ArrayList resDetLiq) throws SQLException, IOException, NamingException 
	 {
		    String Sret ="-2";
		    ResultSet rsDet = null;
	    	try {
	    		DBAcceso ObjBD = DBAcceso.getInstance();      	
	        	conn = ObjBD.connect();       
	        	if (conn!= null)
	        	{
	        	
		        	String LineaComando=DBAcceso.buildProcedureCall(NomPaquete,"IngresoDETLIQ",12);
		        	
		        	cStmt = conn.prepareCall(LineaComando);
		        	cStmt.setString(1, NumLiq.toString());
		            cStmt.setString(2, RutCliente.toString());
		            cStmt.setString(3, Cuenta.toString());
		            cStmt.setString(4, SubProducto.toString());
		            if (Capital.equals(""))
		            	cStmt.setString(5, "0");
		            else	
		            	cStmt.setString(5, Capital.toString());
		            if (Interes.equals(""))
		            	cStmt.setString(6, "0");
		            else	
		            	cStmt.setString(6, Interes.toString());
		            
		            if (CostasProc.equals(""))
		            	cStmt.setString(7, "0");
		            else	
		            	cStmt.setString(7, CostasProc.toString());
		            
		            if (HonorariosAbo.equals(""))
		            	cStmt.setString(8, "0");
		            else	
		            	cStmt.setString(8, HonorariosAbo.toString());
		            
		            if (NumBoleta.equals(""))
		            	cStmt.setString(9, "0");
		            else	
		            	cStmt.setString(9, NumBoleta.toString());
		            
		            cStmt.setString(10, IdDetalle.toString());
		            cStmt.registerOutParameter(11, OracleTypes.VARCHAR);
		            cStmt.registerOutParameter(12, OracleTypes.CURSOR);
		            
		            cStmt.execute();
		            Sret=(String) cStmt.getObject(11);
		            rsDet = (ResultSet) cStmt.getObject(12);
		            if (rsDet != null)
			        {
				    	while (rsDet.next())
				    	{
				    		ArrayList aux = new ArrayList();
				    		for (int j = 1; j <= rsDet.getMetaData().getColumnCount(); j++) 
				    		{
				    			String valor = rsDet.getString(j);
				                if (rsDet.wasNull())
				                	valor = "";
				                else
				                	valor = valor.trim();				                
				                aux.add(valor);
				            }
				    		resDetLiq.add(aux);
				    	}			    		
			    	}		            
	        	}
	        } catch (Exception e) {
	        	logger.error(" [LiqCostas] " , e);
	        } finally {
	        	DBAcceso.close(rsDet,cStmt, conn);
	        }
	        return Sret;
	 }

	  
	 public String IngresarLiqDetCheque(String NumLiq, String NumCheque,String MontoCheque, String CostasProc, String HonorariosAbo, String NumBoleta,String IdDetalle,String SubProducto,ArrayList resDetLiq) throws SQLException, IOException, NamingException 
	 {
		    String Sret ="-2";
		    ResultSet rsDet = null;
	    	try {
	    		DBAcceso ObjBD = DBAcceso.getInstance();        	
	        	conn = ObjBD.connect();       
	        	if (conn!= null)
	        	{
	        	
		        	String LineaComando=DBAcceso.buildProcedureCall(NomPaquete,"IngresoDETLIQCHEQ",10);
		        	
		        	cStmt = conn.prepareCall(LineaComando);
		        	cStmt.setString(1, NumLiq.toString());
		            cStmt.setString(2, NumCheque.toString());
		            if (MontoCheque.equals(""))
		            	cStmt.setString(3, "0");
		            else	
		            	cStmt.setString(3, MontoCheque.toString());
		            
		            if (CostasProc.equals(""))
		            	cStmt.setString(4, "0");
		            else	
		            	cStmt.setString(4, CostasProc.toString());
		            
		            if (HonorariosAbo.equals(""))
		            	cStmt.setString(5, "0");
		            else	
		            	cStmt.setString(5, HonorariosAbo.toString());
		            
		            if (NumBoleta.equals(""))
		            	cStmt.setString(6, "0");
		            else	
		            	cStmt.setString(6, NumBoleta.toString());
		            
		            cStmt.setString(7, IdDetalle.toString());
		            cStmt.setString(8, SubProducto.toString());
		            cStmt.registerOutParameter(9, OracleTypes.VARCHAR);
		            cStmt.registerOutParameter(10, OracleTypes.CURSOR);
		            
		            cStmt.execute();
		            Sret=(String) cStmt.getObject(9);
		            rsDet = (ResultSet) cStmt.getObject(10);
		            if (rsDet != null)
			        {
				    	while (rsDet.next())
				    	{
				    		ArrayList aux = new ArrayList();
				    		for (int j = 1; j <= rsDet.getMetaData().getColumnCount(); j++) 
				    		{
				    			String valor = rsDet.getString(j);
				                if (rsDet.wasNull())
				                	valor = "";
				                else
				                	valor = valor.trim();				                
				                aux.add(valor);
				            }
				    		resDetLiq.add(aux);
				    	}			    		
			    	}		            
	        	}
	        } catch (Exception e) {
	        	logger.error(" [LiqCostas] " , e);
	        } finally {
	        	DBAcceso.close(rsDet,cStmt, conn);
	        }
	        return Sret;
	 }

	 public String EliminarLiqDetTarjeta(String NumLiq, int IdDetalle,int TipoProducto,ArrayList resDetLiq) throws SQLException, IOException, NamingException 
	 {
		    String Sret ="-2";
		    ResultSet rsDet = null;
	    	try {
	    		DBAcceso ObjBD = DBAcceso.getInstance();        	
	        	conn = ObjBD.connect();       
	        	if (conn!= null)
	        	{	        	
		        	String LineaComando=DBAcceso.buildProcedureCall(NomPaquete,"EliminarDETLIQ",5);
		        	
		        	cStmt = conn.prepareCall(LineaComando);
		        	cStmt.setString(1, NumLiq.toString());
		            cStmt.setInt(2, IdDetalle);
		            cStmt.setInt(3, TipoProducto);
		            cStmt.registerOutParameter(4, OracleTypes.VARCHAR);
		            cStmt.registerOutParameter(5, OracleTypes.CURSOR);
		            
		            cStmt.execute();
		            Sret=(String) cStmt.getObject(4);
		            rsDet = (ResultSet) cStmt.getObject(5);
		            if (rsDet != null)
			        {
				    	while (rsDet.next())
				    	{
				    		ArrayList aux = new ArrayList();
				    		for (int j = 1; j <= rsDet.getMetaData().getColumnCount(); j++) 
				    		{
				    			String valor = rsDet.getString(j);
				                if (rsDet.wasNull())
				                	valor = "";
				                else
				                	valor = valor.trim();				                
				                aux.add(valor);
				            }
				    		resDetLiq.add(aux);
				    	}			    		
			    	}		            
	        	}
	        } catch (Exception e) {
	        	logger.error(" [LiqCostas] " , e);
	        } finally {
	        	DBAcceso.close(rsDet,cStmt, conn);
	        }
	        return Sret;
	 }

	 
	 
	 public int ObtieneEncDetLiquid(String NumLiq,int TipoProducto,ArrayList resEncLiq,ArrayList resDetLiq) throws SQLException, IOException, NamingException 
	    {
	    	int ret = 0;   //ok
	    	ResultSet rsEnc = null;
        	ResultSet rsDet = null;
	        try {
	        	
	        	DBAcceso ObjBD = DBAcceso.getInstance();         	
	        	conn = ObjBD.connect();   
	        	
	        	if (conn!= null)
	        	{
		        	String LineaComando=DBAcceso.buildProcedureCall(NomPaquete, "ObtenerEncDetLiq",4);
		        	
		        	cStmt = conn.prepareCall(LineaComando);
		            cStmt.setString(1, NumLiq.toString());
		            cStmt.setInt(2, TipoProducto);
		            cStmt.registerOutParameter(3, OracleTypes.CURSOR);
		            cStmt.registerOutParameter(4, OracleTypes.CURSOR);
		            
		        	cStmt.execute();	        	
		        	
		        	rsEnc = (ResultSet) cStmt.getObject(3);   
		        	rsDet = (ResultSet) cStmt.getObject(4); 
		            //obtenemos la data del encabezado
		        	ret=-3; //no existe liquidacion
		            if (rsEnc != null)
		            {
			    		while (rsEnc.next())
			    		{
			    			ArrayList aux = new ArrayList();
			    			for (int j = 1; j <= rsEnc.getMetaData().getColumnCount(); j++) 
			    			{
			    				String valor = rsEnc.getString(j);
			    	            if (rsEnc.wasNull())
			    	            	valor = "";
			    	            else
			    	            	valor = valor.trim();
			    	            
			    	            aux.add(valor);
			    	        }
			    			resEncLiq.add(aux);
			    			ret=0;
			    		}			    		
		    		} 		            
		            if (ret==0)
		            {	
			            //obtenemos la data del detalle
			            if (rsDet != null)
			            {
				    		while (rsDet.next())
				    		{
				    			ArrayList aux = new ArrayList();
				    			for (int j = 1; j <= rsDet.getMetaData().getColumnCount(); j++) 
				    			{
				    				String valor = rsDet.getString(j);
				    	            if (rsDet.wasNull())
				    	            	valor = "";
				    	            else
				    	            	valor = valor.trim();
				    	            
				    	            aux.add(valor);
				    	        }
				    			resDetLiq.add(aux);
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
	 
	 public int ObtieneDetLiquid(String NumLiq,int TipoProducto,ArrayList resDetLiq) throws SQLException, IOException, NamingException 
	    {
	    	int ret = 0;   //ok
	    	ResultSet rsDet = null;
	        try {
	        	
	        	DBAcceso ObjBD = DBAcceso.getInstance();         	
	        	conn = ObjBD.connect();   
	        	
	        	if (conn!= null)
	        	{
		        	String LineaComando=DBAcceso.buildProcedureCall(NomPaquete, "ObtenerDetLiq",3);
		        	
		        	cStmt = conn.prepareCall(LineaComando);
		            cStmt.setString(1, NumLiq.toString());
		            cStmt.setInt(2, TipoProducto);
		            cStmt.registerOutParameter(3, OracleTypes.CURSOR);
		            
		        	cStmt.execute();	       	
		        	
		        	rsDet = (ResultSet) cStmt.getObject(3); 
		           
		            //obtenemos la data del detalle
			        if (rsDet != null)
			        {
				    	while (rsDet.next())
				    	{
				    		ArrayList aux = new ArrayList();
				    		for (int j = 1; j <= rsDet.getMetaData().getColumnCount(); j++) 
				    		{
				    			String valor = rsDet.getString(j);
				                if (rsDet.wasNull())
				                	valor = "";
				                else
				                	valor = valor.trim();
				                
				                aux.add(valor);
				            }
				    		resDetLiq.add(aux);
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
	 
	
	 public int ConsultarLiquidacion(String NumLiq,String NumCheque,int FechaDesde,int FechaHasta,String CodSucursal,String CodAbogado,int CodEstado,int CodProducto,ArrayList resLiq) throws SQLException, IOException, NamingException 
	    {
	    	int ret = 0;   //ok
	    	ResultSet rsDet = null;
	        try {
	        	
	        	DBAcceso ObjBD = DBAcceso.getInstance();        	
	        	conn = ObjBD.connect();   
	        	
	        	if (conn!= null)
	        	{
		        	String LineaComando=DBAcceso.buildProcedureCall(NomPaquete, "ObtenerConsultaLiq",9);
		        	
		        	cStmt = conn.prepareCall(LineaComando);
		            if (NumLiq.equals(""))
		            	cStmt.setString(1, "0");
		            else
		            	cStmt.setString(1, NumLiq.toString());
		         
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
		            cStmt.registerOutParameter(9, OracleTypes.CURSOR);
		            
		        	cStmt.execute();	       	
		        	
		        	rsDet = (ResultSet) cStmt.getObject(9); 
		           
		            //obtenemos la data del detalle
			        if (rsDet != null)
			        {
				    	while (rsDet.next())
				    	{
				    		ArrayList aux = new ArrayList();
				    		for (int j = 1; j <= rsDet.getMetaData().getColumnCount(); j++) 
				    		{
				    			String valor = rsDet.getString(j);
				                if (rsDet.wasNull())
				                	valor = "";
				                else
				                	valor = valor.trim();
				                
				                aux.add(valor);
				            }
				    		resLiq.add(aux);
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
	 
	 public int ConsultarInformeEstado(String NumLiq,int FechaDesde,int FechaHasta,String CodSucursal,String CodAbogado,int CodEstado,int CodProducto,ArrayList resInfLiq) throws SQLException, IOException, NamingException 
	    {
	    	int ret = 0;   //ok
	    	ResultSet rsDet = null;
	        try {
	        	
	        	DBAcceso ObjBD = DBAcceso.getInstance();         	
	        	conn = ObjBD.connect();   
	        	
	        	if (conn!= null)
	        	{
		        	String LineaComando=DBAcceso.buildProcedureCall(NomPaquete, "ObtenerInformeEstado",8);
		        	
		        	cStmt = conn.prepareCall(LineaComando);
		        	if (NumLiq.equals(""))
			          	cStmt.setString(1, "0");
			        else
			           	cStmt.setString(1, NumLiq.toString());
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
		            cStmt.registerOutParameter(8, OracleTypes.CURSOR);
		            
		        	cStmt.execute();	       	
		        	
		        	rsDet = (ResultSet) cStmt.getObject(8); 
		           
		            //obtenemos la data del detalle
			        if (rsDet != null)
			        {
				    	while (rsDet.next())
				    	{
				    		ArrayList aux = new ArrayList();
				    		for (int j = 1; j <= rsDet.getMetaData().getColumnCount(); j++) 
				    		{
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
	 
	 public int ConsultarInformeContable(String NumLiq,int FechaDesde,int FechaHasta,String CodAbogado,String NumBoleta,String Mayor, String Menor,ArrayList resInfLiq) throws SQLException, IOException, NamingException 
	    {
	    	int ret = 0;   //ok
	    	ResultSet rsDet = null;
	        try {
	        	
	        	DBAcceso ObjBD = DBAcceso.getInstance();       	
	        	conn = ObjBD.connect();   
	        	
	        	if (conn!= null)
	        	{
		        	String LineaComando=DBAcceso.buildProcedureCall(NomPaquete, "ObtenerInformeContable",8);
		        	
		        	cStmt = conn.prepareCall(LineaComando);
		            if (NumLiq.equals(""))
		            	cStmt.setString(1, "0");
		            else
		            	cStmt.setString(1, NumLiq.toString());
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
			        if (rsDet != null)
			        {
				    	while (rsDet.next())
				    	{
				    		ArrayList aux = new ArrayList();
				    		for (int j = 1; j <= rsDet.getMetaData().getColumnCount(); j++) 
				    		{
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
	 
	 	public String RechazarLiqCosta(String NumLiqCosta,int Tipo,String MotivoRech) throws SQLException, IOException, NamingException 
	    {
		   String ret="-1";
		   try {			   
			   DBAcceso ObjBD = DBAcceso.getInstance();         	
	        	conn = ObjBD.connect();       
	        	if (conn!= null)
	        	{	        	
		        	String LineaComando=DBAcceso.buildProcedureCall(NomPaquete, "RechazarLIQCOSTA",4);		        	
		        	cStmt = conn.prepareCall(LineaComando);
		            cStmt.setString(1, NumLiqCosta.toString());
		            cStmt.setInt(2, Tipo);	
		            cStmt.setString(3, MotivoRech.toString());
		            cStmt.registerOutParameter(4, OracleTypes.VARCHAR);		            
		        	cStmt.execute();
		        	ret=(String) cStmt.getObject(4);
		    	} 	
	       
	        } catch (Exception e) {
	        	logger.error(" [LiqCostas] " , e);
	        } finally {
	        	DBAcceso.close(cStmt, conn);
	        }
	        return ret;
	      }
	 	
	 	public String EliminarCostasCEXPNS(String NumCosta) throws SQLException, IOException, NamingException 
		 {
			    String Sret ="-2";
		    	try {
		    		DBAcceso ObjBD = DBAcceso.getInstance();       	
		        	conn = ObjBD.connect();       
		        	if (conn!= null)
		        	{		        	
			        	String LineaComando=DBAcceso.buildProcedureCall(NomPaquete,"EliminarCAEXPNS",2);
			        	
			        	cStmt = conn.prepareCall(LineaComando);
			        	cStmt.setString(1, NumCosta.toString());
			        	cStmt.registerOutParameter(2, OracleTypes.VARCHAR);
			            
			            cStmt.execute();
			            Sret=(String) cStmt.getObject(2);
		        	}
		        } catch (Exception e) {
		        	logger.error(" [LiqCostas] " , e);
		        } finally {
		        	DBAcceso.close(cStmt, conn);
		        }
		        return Sret;
		 }	 	
}
