<%@ page language='java' session="false" contentType="text/html"%>
<%@page import='java.util.ArrayList'%>
<%@page import='cl.cmr.Proc.Remesa'%>
<%@page import='cl.cmr.com.Errores' %>
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
	String CodSucursal = request.getParameter("CSUCURSAL")!=null?request.getParameter("CSUCURSAL").trim():"";
	String CodAbogado = request.getParameter("CABOGADO")!=null?request.getParameter("CABOGADO").trim():"";
	String EstadoRem = request.getParameter("CESTADO")!=null?request.getParameter("CESTADO").trim():"0";
	String CodProducto = request.getParameter("CPRODUCTO")!=null?request.getParameter("CPRODUCTO").trim():"0";
		
	Remesa objRemesa = Remesa.getInstance();

	
	ArrayList resInfRem = new ArrayList();
	ArrayList resAux = new ArrayList();
	String FechaIni=FechaDesde.substring(6,10) + FechaDesde.substring(3,5) + FechaDesde.substring(0,2);
	String FechaFin=FechaHasta.substring(6,10) + FechaHasta.substring(3,5) + FechaHasta.substring(0,2);
	int Iret = objRemesa.ConsultarInformeEstado(NumRemesa,Integer.parseInt(FechaIni),Integer.parseInt(FechaFin),CodSucursal,CodAbogado,Integer.parseInt(EstadoRem),Integer.parseInt(CodProducto),1,-1,-1,-1,-1,resInfRem);
	
	response.setHeader("Cache-Control","no-store"); //HTTP 1.0
	response.setHeader("Pragma","no-cache"); //HTTP 1.1
	response.setDateHeader("Expires",0);	
%>
<html>
<head>
<TITLE><%=Errores.getDescription("VAR.TITULO")%></TITLE>
<META HTTP-EQUIV="Cache-Control" content="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-store">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Expires" CONTENT="0">
<LINK REL="stylesheet" TYPE="text/css" HREF="../style/imprimirdos.css">
<script type="text/javascript">
  function InicioPag() {  	
		window.parent.printPage(window.parent.frames['printInformeEst']);
	}
</script>
</head>
<body onload="javascript:InicioPag(); return false;">
<table width="994px" border="0" cellspacing="0" cellpadding="0" class="tablaprincipal">
  <tr>
    <td valign="top">
     <table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
        	<td align="CENTER" class="titulo">INFORME ESTADO DE REMESAS&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=iDia + "/" + iMes + "/" + iAno%></td>
        </tr>
        <tr>
        <td align="center">
        <table class="tablainterior2" align="left" border=0 cellpadding="0" cellspacing="0"> 
     				<tr>
		           	  <td class="BORDEAZUL">N° Remesa</td>		           	  
		              <td class="BORDEAZUL">Abogado</td>
		              <td class="BORDEAZUL">Sucursal</td>
		              <td class="BORDEAZUL">Producto</td>
		              <td class="BORDEAZUL">Monto<br>Remesa</td>
		              <td class="BORDEAZUL">Estado</td>
		              <td class="BORDEAZUL">Fecha<br>Digitación</td>		              
		              <td class="BORDEAZUL">Fecha<br>Pago</td>
		              <td class="BORDEAZUL">Fecha<br>Proceso CA</td>
		              <td class="BORDEAZUL">Fecha<br>Imputación</td>
		              <td class="BORDEAZUL">Fecha<br>Contable ISS</td>
		            </tr>
				    	<%	
				   		for(int i=0; i<resInfRem.size(); i++) 
				   		{
							resAux = new ArrayList();
             				resAux = (ArrayList)resInfRem.get(i);
             			%>             			  			
             			<tr>
	             			<td class="clsNormal"><%=resAux.get(0).toString().trim()%></td>
			              	<td class="clsNormalString"><%=resAux.get(2).toString().trim()%></td>
			              	<td class="clsNormalString"><%=resAux.get(1).toString().trim()%></td>
			              	<td class="clsNormalString"><%=resAux.get(3).toString().trim()%></td>
			              	<td class="clsNormal"><%=df.format(Double.parseDouble(resAux.get(4).toString().trim()))%></td>
			              	<td class="clsNormalString"><%=resAux.get(5).toString().trim()%></td>
			              	<td class="clsNormal"><%=resAux.get(6).toString().trim()%></td>
			              	<td class="clsNormal"><%=resAux.get(7).toString().trim()%></td>
			              	<td class="clsNormal"><%=resAux.get(8).toString().trim()%></td>
			              	<td class="clsNormal"><%=resAux.get(9).toString().trim()%></td>
			              	<td class="clsNormal"><%=resAux.get(10).toString().trim()%></td>
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