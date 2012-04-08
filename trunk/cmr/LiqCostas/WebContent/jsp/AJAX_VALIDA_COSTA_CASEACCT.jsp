<%@ page language='java' contentType="text/html"%>
<%@page import="org.jboss.logging.Logger" %>
<%@page import='Proc.General'%>
<%
	String Subtipo="";
	Seguridad.ValidaSesion val = new Seguridad.ValidaSesion();
	try {
		String codProducto= request.getParameter("codProducto")!=null?request.getParameter("codProducto").toString().trim():"";
		General objRemesa = General.getInstance();
		Subtipo = objRemesa.getJuicioProducto(codProducto);			
		
	} catch(Exception ex) {
		Logger.getLogger("AJAX_VALIDA_COSTA_CASEACCT").error(" [LiqCostas] " , ex);
	}
 %>
{"total":"<%=Subtipo%>"}
