<%@ page language='java' contentType="text/html"%>
<%@page import="utils.LoggerInstance"%>
<%@page import='Proc.Costas'%>
<%
	String Subtipo="";
	Seguridad.ValidaSesion val = new Seguridad.ValidaSesion();			
	int res = val.valida(request);
	if (res != 0) {
		Subtipo="-1";
	}
	else {
		try {
		    Costas objCosta = Costas.getInstance(); 
			String sTipoProd = request.getParameter("TIPOPROD")!=null?request.getParameter("TIPOPROD").toString().trim():"";
			String sTipoCosta = request.getParameter("TIPOCOSTA")!=null?request.getParameter("TIPOCOSTA").toString().trim():"";
			String sJuicio = request.getParameter("JUICIO")!=null?request.getParameter("JUICIO").toString().trim():"";
			Subtipo = objCosta.ValidaTipoCargo(Integer.parseInt(sTipoProd),sTipoCosta,sJuicio);			
			
		} catch(Exception ex) {
			LoggerInstance.error(Thread.currentThread().getStackTrace()[2] , ex);
		}
		Subtipo=Subtipo + "";		
	}	
 %>
{"total":"<%=Subtipo%>"}