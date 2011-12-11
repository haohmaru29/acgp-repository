<%@ page language='java' contentType="text/html"%>
<%@page import="utils.LoggerInstance"%>
<%@page import="Proc.Costas" %>
<%
	String NumCosta="-1";
	Seguridad.ValidaSesion val = new Seguridad.ValidaSesion();			
	int res = val.valida(request);
	if (res != 0) {
		NumCosta="-2";
	} else {	
	    try {
			String idSec = request.getParameter("idSec");
			
			Costas objCostas = Costas.getInstance();
			NumCosta = objCostas.actualizaEncCostas(idSec);
			
		} catch(Exception ex) {
			LoggerInstance.error(Thread.currentThread().getStackTrace()[2] , ex);	
		}	
	}	
 %> 
{"total":"<%=NumCosta%>"}