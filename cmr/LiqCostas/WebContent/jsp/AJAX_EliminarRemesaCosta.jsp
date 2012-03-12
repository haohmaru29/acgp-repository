<%@ page language='java' contentType="text/html"%>
<%@page import="utils.LoggerInstance"%>
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
			
			Proc.Remesa objRemesa = new Proc.Remesa();
			Resp = objRemesa.EliminarRemesaCosta(sNumRemesa,Integer.parseInt(sTipoProd),sAbogado);
						
		} catch(Exception ex) {
			LoggerInstance.error(Thread.currentThread().getStackTrace()[2] , ex);	
		}		
	}	
 %> 
{"total":"<%=Resp%>"}