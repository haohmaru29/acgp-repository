<%@ page language='java' contentType="text/html"%>
<%@page import="org.jboss.logging.Logger" %>
<%@page import='cl.cmr.Proc.Costas'%>
<%@page import="cl.cmr.Seguridad.ValidaSesion"%>
<%@page import="cl.cmr.Seguridad.SessionUsuario"%>
<%
	String Resp="-1";
	ValidaSesion val = new ValidaSesion();			
	int res = val.valida(request);
	if (res != 0) {
		Resp="-2";
	}
	else {	
	    try {
			String sTipoProd = request.getParameter("TIPOPROD")!=null?request.getParameter("TIPOPROD").toString().trim():"3";
			String sNumCosta = request.getParameter("NUMCOSTA")!=null?request.getParameter("NUMCOSTA").toString().trim():"";
			String sAbogado= request.getParameter("ABO")!=null?request.getParameter("ABO").toString().trim():"";
			String sRutCliente = request.getParameter("RUT")!=null?request.getParameter("RUT").toString().trim():"";
			String sOperacion = request.getParameter("OPE")!=null?request.getParameter("OPE").toString().trim():"";
			String sMonto = request.getParameter("MONTO")!=null?request.getParameter("MONTO").toString().trim():"0";
			String sTipoCargo = request.getParameter("TIPCARGO")!=null?request.getParameter("TIPCARGO").toString().trim():"";
			String sSubTipoCargo = request.getParameter("SUBTIPCARGO")!=null?request.getParameter("SUBTIPCARGO").toString().trim():"";
			String sNumJuicio = request.getParameter("NUMJUICIO")!=null?request.getParameter("NUMJUICIO").toString().trim():"";
			String sTipoJuicio = request.getParameter("TIPOJUICIO")!=null?request.getParameter("TIPOJUICIO").toString().trim():"";
			
			Costas objCostas = Costas.getInstance();			
			HttpSession sesion = request.getSession(true);
			SessionUsuario  oSes =(SessionUsuario)sesion.getAttribute("usuario");
			
			Resp = objCostas.IngresarDetCosta(sNumCosta,Integer.parseInt(sTipoProd),sAbogado,sRutCliente,sOperacion,sMonto,sTipoCargo,sSubTipoCargo,sNumJuicio,sTipoJuicio,oSes.getUsuario().toString(),oSes.getSupv().toString());
						
		} catch(Exception ex) {
			Logger.getLogger("AJAX_GrabaDetalleCosta").error(" [LiqCostas] " , ex);
		}	
	}	
 %> 
{"total":"<%=Resp%>"}