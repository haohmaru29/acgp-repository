<%@ page language='java' contentType="text/html"%>
<%@page import="org.apache.log4j.Logger" %>
<%@page import='cl.cmr.Proc.General'%>
<%@page import="cl.cmr.Seguridad.ValidaSesion"%>
<%
	String Resp="-1";
	ValidaSesion val = new ValidaSesion();			
	int res = val.valida(request);
	if (res != 0) {
		Resp="-2";
	}
	else {	
	    try {
			String rutCliente = request.getParameter("rutCliente")!=null?request.getParameter("rutCliente").toString().trim():"";
			String codAbogado = request.getParameter("codAbogado")!=null?request.getParameter("codAbogado").toString().trim():"";
			String codProducto= request.getParameter("codProducto")!=null?request.getParameter("codProducto").toString().trim():"";
			General general = General.getInstance();
			Resp = general.validarSaldoCosta(codAbogado, rutCliente, codProducto);
						
		} catch(Exception ex) {
			Logger.getLogger("AJAX_ValidaSaldoCosta").error(" [LiqCostas] " , ex);
		}	
	}	
 %> 
{"total":"<%=Resp%>"}