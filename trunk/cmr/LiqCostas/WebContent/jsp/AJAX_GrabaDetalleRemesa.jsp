<%@ page language='java' contentType="text/html"%>
<%@page import="Proc.Remesa"%>
<%@page import="org.jboss.logging.Logger" %>
<%
	String Resp="-1";
	Seguridad.ValidaSesion val = new Seguridad.ValidaSesion();			
	int res = val.valida(request);
	if (res != 0) {
		Resp="-2";
	}
	else {	
	    try {
			String sTipoProd = request.getParameter("TIPOPROD")!=null?request.getParameter("TIPOPROD").toString().trim():"1";
			String sNumRemesa = request.getParameter("NUMREMESA")!=null?request.getParameter("NUMREMESA").toString().trim():"";
			String sAbogado= request.getParameter("ABO")!=null?request.getParameter("ABO").toString().trim():"";
			String sRutCliente = request.getParameter("RUT")!=null?request.getParameter("RUT").toString().trim():"";
			String sOperacion = request.getParameter("OPE")!=null?request.getParameter("OPE").toString().trim():"";
			String sCaptital = request.getParameter("CAPITAL")!=null?request.getParameter("CAPITAL").toString().trim():"0";
			String sInteres = request.getParameter("INTERES")!=null?request.getParameter("INTERES").toString().trim():"0";
			String sCosta = request.getParameter("COSTA")!=null?request.getParameter("COSTA").toString().trim():"0";
			String sHonorario = request.getParameter("HONO")!=null?request.getParameter("HONO").toString().trim():"0";
			String sBoleta = request.getParameter("BOLETA")!=null?request.getParameter("BOLETA").toString().trim():"0";
			String sTotal = request.getParameter("TOTAL")!=null?request.getParameter("TOTAL").toString().trim():"0";
			
			Remesa objRemesa = Remesa.getInstance();
			Resp = objRemesa.IngresarDetRemesa(sNumRemesa,Integer.parseInt(sTipoProd),sAbogado,sRutCliente,sOperacion,sCaptital,sInteres,sCosta,sHonorario,sBoleta,sTotal);
						
		} catch(Exception ex) {
			Logger.getLogger("AJAXGrabaDetalleRemesa").error(" [LiqCostas] " , ex);
		}
		
	}	
 %> 
{"total":"<%=Resp%>"}