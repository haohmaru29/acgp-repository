<%@ page language='java' contentType="text/html"%>
<%@page import="org.apache.log4j.Logger" %>
<%@page import='java.util.ArrayList'%>
<%@page import='cl.cmr.Proc.General'%>
<%@page import="cl.cmr.Seguridad.ValidaSesion"%>

<%
	String Strcadena="";
	ValidaSesion val = new ValidaSesion();			
	int res = val.valida(request);
	if (res != 0) {
		Strcadena="-1";
	}
	else {
	    int total = 0;
		ArrayList arrRes=new ArrayList();
		ArrayList resAux=new ArrayList();
		int ret;    
		try {
		    String sRut;
		    String sAbo;	   
		    int iSubProd=-1;
			General objGeneral = General.getInstance(); 
			sRut = request.getParameter("RUT")!=null?request.getParameter("RUT").toString().trim():"";
			ret = objGeneral.ObtenerJuicio(sRut, arrRes);	
		} catch(Exception ex) {
			Logger.getLogger("AJAX_NroTipoJuicio").error(" [LiqCostas] " , ex);
		}		
		
		total = arrRes.size();	
		if (total >0) {
			resAux = new ArrayList();
	        resAux = (ArrayList)arrRes.get(0);            
	        Strcadena=Strcadena + resAux.get(0).toString().trim() + "," + resAux.get(1).toString().trim();
			//Strcadena=Strcadena + ",";
		}
	}	
 %> 
{"total":"<%=Strcadena%>"}