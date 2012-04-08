<%@ page language='java' contentType="text/html"%>
<%@page import="org.jboss.logging.Logger" %>
<%@page import='Proc.General'%>
<%
	String Resp="-1";
	Seguridad.ValidaSesion val = new Seguridad.ValidaSesion();			
	int res = val.valida(request);
	if (res != 0) {
		Resp="-2";
	}
	else {	
	    try {
			String rutCliente = request.getParameter("rutCliente")!=null?request.getParameter("rutCliente").toString().trim():"";
			String codAbogado = request.getParameter("codAbogado")!=null?request.getParameter("codAbogado").toString().trim():"";
			String codProducto= request.getParameter("codProducto")!=null?request.getParameter("codProducto").toString().trim():"";
			General objRemesa = General.getInstance();
			Resp = objRemesa.validarSaldoCosta(codAbogado, rutCliente, codProducto);
						
		} catch(Exception ex) {
			Logger.getLogger("AJAX_ValidaSaldoCosta").error(" [LiqCostas] " , ex);
		}	
	}	
 %> 
{"total":"<%=Resp%>"}