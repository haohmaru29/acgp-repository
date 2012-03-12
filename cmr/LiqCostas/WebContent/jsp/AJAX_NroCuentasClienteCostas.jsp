<%@ page language='java' contentType="text/html"%>
<%@page import="utils.LoggerInstance"%>
<%@page import='java.util.ArrayList'%>
<%@page import='Proc.General'%>
<%
	String Strcadena="";
	Seguridad.ValidaSesion val = new Seguridad.ValidaSesion();			
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
			sAbo = request.getParameter("CODABO")!=null?request.getParameter("CODABO").toString().trim():"";
			iSubProd = request.getParameter("SUBPROD")!=null?Integer.parseInt(request.getParameter("SUBPROD").toString().trim()):-1;
			ret = objGeneral.ObtenerSubProductosCostas(sRut, sAbo, iSubProd, arrRes);	
		} catch(Exception ex) {
			LoggerInstance.error(Thread.currentThread().getStackTrace()[2] , ex);
		}
		
		total = arrRes.size();	
		if (total >0) {
			for(int i=0; i<total; i++) {
				resAux = new ArrayList();
	            resAux = (ArrayList)arrRes.get(i);            
	            Strcadena=Strcadena + resAux.get(0).toString().trim();
				Strcadena=Strcadena + ",";
			}
		}
	}	
 %> 
{"total":"<%=Strcadena%>"}