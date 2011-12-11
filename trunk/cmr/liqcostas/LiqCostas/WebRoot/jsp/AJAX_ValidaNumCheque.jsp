<%@ page language='java' contentType="text/html"%>
<%@page import="utils.LoggerInstance"%>
<%@page import='Proc.General'%>
<%
	String total="";
	Seguridad.ValidaSesion val = new Seguridad.ValidaSesion();			
	int res = val.valida(request);
	if (res != 0) {
		total="-1";
	}
	else {
	    total="0";
		try {
			String sNumCheque = request.getParameter("NUMCHEQUE")!=null?request.getParameter("NUMCHEQUE").toString().trim():"";
		    General objGeneral = General.getInstance(); 
			total=objGeneral.ValidaNumCheque(sNumCheque);
			
		} catch(Exception ex) {
			LoggerInstance.error(Thread.currentThread().getStackTrace()[2] , ex);
		}
	}	
 %>
{"total":"<%=total%>"}
