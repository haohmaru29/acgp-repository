
<%@ page language='java' contentType="text/html"%>
<%@page import="utils.LoggerInstance"%>
<%@page import='Proc.General'%>
<%
	String Subtipo="";
	Seguridad.ValidaSesion val = new Seguridad.ValidaSesion();
	try {
		String codProducto= request.getParameter("codProducto")!=null?request.getParameter("codProducto").toString().trim():"";
		General objRemesa = General.getInstance();
		Subtipo = objRemesa.getJuicioProducto(codProducto);			
		
	} catch(Exception ex) {
		LoggerInstance.error(Thread.currentThread().getStackTrace()[2] , ex);
	}
 %>
{"total":"<%=Subtipo%>"}
