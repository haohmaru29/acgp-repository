<%@ page language='java' contentType="text/html"%>
<%@page import="org.apache.log4j.Logger" %>
<%@page import="cl.cmr.Proc.Costas"%>
<%@page import="cl.cmr.Seguridad.ValidaSesion"%>
<%
	String NumCosta="-1";
	ValidaSesion val = new ValidaSesion();			
	int res = val.valida(request);
	if (res != 0) {
		NumCosta="-2";
	} else {	
	    try {
			String sPrestador = request.getParameter("PRESTADOR")!=null?request.getParameter("PRESTADOR").toString().trim():"";
			String sTipoDoc = request.getParameter("TIPODOC")!=null?request.getParameter("TIPODOC").toString().trim():"";
			String sNumDoc = request.getParameter("NUMDOC")!=null?request.getParameter("NUMDOC").toString().trim():"";
			
			Costas objCostas = Costas.getInstance();
			NumCosta = objCostas.validaDocumento(sTipoDoc, sPrestador,sNumDoc);
			
		} catch(Exception ex) {
			Logger.getLogger("AJAX_GrabaEncabezadoCosta").error(" [LiqCostas] " , ex);	
		}	
	}	
 %> 
{"total":"<%=NumCosta%>"}