<%@ page language='java' contentType="text/html"%>
<%@page import="Proc.Remesa"%>
<%@page import="org.jboss.logging.Logger" %>
<%
	String Resp="-1";
	Seguridad.ValidaSesion val = new Seguridad.ValidaSesion();			
	int res = val.valida(request);
	if (res != 0) {
		Resp="-2";
	}
	else {	
	    try {
			String sTipoProd = request.getParameter("TIPOPROD")!=null?request.getParameter("TIPOPROD").toString().trim():"";
			String sNumRemesa = request.getParameter("NUMREMESA")!=null?request.getParameter("NUMREMESA").toString().trim():"";
			String sAbogado= request.getParameter("ABO")!=null?request.getParameter("ABO").toString().trim():"";
			
			Proc.Remesa objRemesa = Remesa.getInstance();
			Resp = objRemesa.EliminarRemesaCosta(sNumRemesa,Integer.parseInt(sTipoProd),sAbogado);
						
		} catch(Exception ex) {
			Logger.getLogger("AJAX_EliminarRemesaCosta").error(" [LiqCostas] " , ex);
		}		
	}	
 %> 
{"total":"<%=Resp%>"}