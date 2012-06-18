<%@ page language='java' contentType="text/html"%>
<%@page import="org.apache.log4j.Logger" %>
<%@page import='cl.cmr.Proc.General'%>
<%@page import="cl.cmr.Seguridad.ValidaSesion"%>
<%
	String Subtipo="";
	ValidaSesion val = new ValidaSesion();
	try {
		String codProducto= request.getParameter("codProducto")!=null?request.getParameter("codProducto").toString().trim():"";
		General objRemesa =General.getInstance();
		Subtipo = objRemesa.getJuicioProducto(codProducto);	
	} catch(Exception ex) {
		Logger.getLogger("AJAX_Tipo_Juicio_Producto").error(" [LiqCostas] " , ex);
	}
 %>
{"total":"<%=Subtipo%>"}
