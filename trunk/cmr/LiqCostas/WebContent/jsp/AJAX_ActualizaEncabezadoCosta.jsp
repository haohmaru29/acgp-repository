<%@ page language='java' contentType="text/html"%>
<%@page import="org.apache.log4j.Logger" %>
<%@page import="cl.cmr.Proc.Costas" %>
<%@page import='cl.cmr.Seguridad.ValidaSesion' %>
<%
	String NumCosta="-1";
	ValidaSesion val = new ValidaSesion();			
	int res = val.valida(request);
	if (res != 0) {
		NumCosta="-2";
	} else {	
	    try {
			String idSec = request.getParameter("idSec");
			Costas objCostas = Costas.getInstance();
			NumCosta = objCostas.actualizaEncCostas(idSec);
		} catch(Exception ex) {
			Logger.getLogger("AJAX_ActualizaEncabezadoRemesa").error(" [LiqCostas] " , ex);
		}	
	}	
 %> 
{"total":"<%=NumCosta%>"}