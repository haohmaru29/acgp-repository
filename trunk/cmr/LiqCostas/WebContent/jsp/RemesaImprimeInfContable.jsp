<%@ page language='java' session="false" contentType="text/html"%>
<%@page import='java.util.ArrayList'%>
<%@page import='Proc.Remesa'%>
<%@page import='java.text.DecimalFormat'%>
<%@page import='java.text.DecimalFormatSymbols'%>

<%  

	String iAno = new java.text.SimpleDateFormat("yyyy").format(new java.util.Date());
	String iDia = new java.text.SimpleDateFormat("dd").format(new java.util.Date());
	String iMes = new java.text.SimpleDateFormat("MM").format(new java.util.Date());
		
	DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
	unusualSymbols.setDecimalSeparator(',');
	unusualSymbols.setGroupingSeparator('.');
		
	DecimalFormat df = new DecimalFormat("###,###,###,###,###,###,##0;(###,###,###,###,###,###,##0)",unusualSymbols);	
	
	String FechaDesde = request.getParameter("CTxtFechaDesde")!=null?request.getParameter("CTxtFechaDesde").trim():"";
	String FechaHasta = request.getParameter("CTxtFechaHasta")!=null?request.getParameter("CTxtFechaHasta").trim():"";
	String NumRemesa = request.getParameter("CNumRemesa")!=null?request.getParameter("CNumRemesa").trim():"0";
	String CodAbogado = request.getParameter("CABOGADO")!=null?request.getParameter("CABOGADO").trim():"";
	String Boleta = request.getParameter("TxtBoleta")!=null?request.getParameter("TxtBoleta").trim():"";
	String MayorA = request.getParameter("TxtHMayor")!=null?request.getParameter("TxtHMayor").trim():"0";
	String MenorA = request.getParameter("TxtHMenor")!=null?request.getParameter("TxtHMenor").trim():"0";
	
		
	Remesa objRemesa = Remesa.getInstance();

	
	ArrayList resInfLiq = new ArrayList();
	ArrayList resAux = new ArrayList();
	String FechaIni=FechaDesde.substring(6,10) + FechaDesde.substring(3,5) + FechaDesde.substring(0,2);
	String FechaFin=FechaHasta.substring(6,10) + FechaHasta.substring(3,5) + FechaHasta.substring(0,2);
	int Iret = objRemesa.ConsultarInformeContable(NumRemesa,Integer.parseInt(FechaIni),Integer.parseInt(FechaFin),CodAbogado,Boleta,MayorA.replace(".","").replace(",",""),MenorA.replace(".","").replace(",",""),resInfLiq);
	
	response.setHeader("Cache-Control","no-store"); //HTTP 1.0
	response.setHeader("Pragma","no-cache"); //HTTP 1.1
	response.setDateHeader("Expires",0);	
%>
<html>
<head>
<TITLE><%=com.Errores.getDescription("VAR.TITULO")%></TITLE>
<META HTTP-EQUIV="Cache-Control" content="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-store">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Expires" CONTENT="0">
<LINK REL="stylesheet" TYPE="text/css" HREF="../style/imprimir.css">
<script type="text/javascript">
  function InicioPag() {  	
		window.parent.printPage(window.parent.frames['printInformeCont']);
	}
</script>
</head>
<body onload="javascript:InicioPag(); return false;">
<table width="994px" border="0" cellspacing="0" cellpadding="0" class="tablaprincipal">
  <tr>
    <td valign="top">
     <table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
        	<td align="CENTER" class="titulo">INFORME CONTABLE DE REMESAS&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=iDia + "/" + iMes + "/" + iAno%></td>
        </tr> 
        <tr>
          <td align="center">
          <table class="tablainterior2" align="left" border=0 cellpadding="0" cellspacing="0">    	
		 			<tr>		           	  
		             <td class="BORDEAZUL2"><div >Cod.Abogado</div></td>
				     <td class="BORDEAZUL2" ><div >Abogado</div></td>
		             <td class="BORDEAZUL2" ><div >Sucursal Abogado</div></td>
		             <td class="BORDEAZUL3"><div >N° Remesa</div></td>
		             <td class="BORDEAZUL3"><div >N° Boleta</div></td>
		             <td class="BORDEAZUL3"><div >Monto Honorarios</div></td>
		              	
		            </tr>
				    	<%	
				   		for(int i=0; i<resInfLiq.size(); i++) 
				   		{
							resAux = new ArrayList();
             				resAux = (ArrayList)resInfLiq.get(i);
             			%>             			  			
             			<tr>
             			<%if (resAux.get(4).toString().trim().equals("TOTAL")) 
		            	{%>
		            		<td class="clsNormalString"></td>
		            		<td class="clsNormalString"></td>	
		            		<td class="clsNormalString"></td>		
		            		<td class="clsNormal"></td>	
		            		<td class="clsNormal"><b><%=resAux.get(4).toString().trim()%></td>	
		            		<td class="clsNormal"><b><%=df.format(Double.parseDouble(resAux.get(5).toString().trim()))%></td>	
		            	<%}
		          			else {%>
		          			<td class="clsNormalString"><%=resAux.get(0).toString().trim()%></td>
		            		<td class="clsNormalString"><%=resAux.get(1).toString().trim()%></td>	
		            		<td class="clsNormalString"><%=resAux.get(2).toString().trim()%></td>		
		            		<td class="clsNormal"><%=resAux.get(3).toString().trim()%></td>	
		          			<td class="clsNormal"><%=resAux.get(4).toString().trim()%></td>
		          			<td class="clsNormal"><%=df.format(Double.parseDouble(resAux.get(5).toString().trim()))%></td>	
		          		<%}%>		            	              	
		              	</tr>	
			        <%}%>
			   </table>
			  </td>
			</tr>      
		</table>
	</td>
	</tr>
</table>		
</BODY>
</HTML>