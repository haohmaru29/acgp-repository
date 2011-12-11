<%@ page language='java' contentType="text/html"%>
<%@page import="utils.LoggerInstance"%>
<%
	String NumCosta="-1";
	Seguridad.ValidaSesion val = new Seguridad.ValidaSesion();			
	int res = val.valida(request);
	if (res != 0) {
		NumCosta="-2";
	} else {	
	    try {
			String sUsuario = request.getParameter("USUARIO")!=null?request.getParameter("USUARIO").toString().trim():"";
			String sPrestador = request.getParameter("PRESTADOR")!=null?request.getParameter("PRESTADOR").toString().trim():"";
			String sAbo = request.getParameter("ABO")!=null?request.getParameter("ABO").toString().trim():"";
			String sTipoDoc = request.getParameter("TIPODOC")!=null?request.getParameter("TIPODOC").toString().trim():"";
			String sNumDoc = request.getParameter("NUMDOC")!=null?request.getParameter("NUMDOC").toString().trim():"";
			String sMonto = request.getParameter("MONTO")!=null?request.getParameter("MONTO").toString().trim():"0";
			String sMoneda = request.getParameter("MONEDA")!=null?request.getParameter("MONEDA").toString().trim():"";
			String sTipoProd = request.getParameter("TIPOPROD")!=null?request.getParameter("TIPOPROD").toString().trim():"";
			String sGrupo = request.getParameter("GRUPO")!=null?request.getParameter("GRUPO").toString().trim():"";
			
			Proc.Costas objCostas = Proc.Costas.getInstance();
			NumCosta = objCostas.IngresarEncCostas(sUsuario,sAbo,sPrestador,sMoneda,sMonto,sTipoDoc,sNumDoc,Integer.parseInt(sTipoProd),Integer.parseInt(sGrupo));
			
		} catch(Exception ex) {
			LoggerInstance.error(Thread.currentThread().getStackTrace()[2] , ex);	
		}	
	}	
 %> 
{"total":"<%=NumCosta%>"}