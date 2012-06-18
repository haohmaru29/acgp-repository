<%@ page language='java' contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" errorPage="error.jsp?ERR=ERR.GENERAL.PAGE"%>
<%@page import='cl.cmr.Seguridad.ValidaSesion' %>
<%@page import='cl.cmr.Seguridad.Acceso' %>
<%
	ValidaSesion val = new ValidaSesion();
			
	int res = val.valida(request);
	if (res != 0) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/error.jsp?ERR=ERR.ACCESO.SESSION");
			dispatcher.forward(request,response);
			 response.flushBuffer();
			 return;
	} else {
		Acceso ObjAcceso = new Acceso(request);
		boolean acc = ObjAcceso.tieneAcceso();
		if (!(acc)) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/error.jsp?ERR=ERR.ACCESO.AUTORIZA");
				dispatcher.forward(request,response);
				return;
		} 
	 }
	
	response.setHeader("Cache-Control","no-store"); //HTTP 1.0
	response.setHeader("Pragma","no-cache"); //HTTP 1.1
	response.setDateHeader("Expires",0);	

	
%>


