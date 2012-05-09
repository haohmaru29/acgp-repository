<%@ page language='java' session="false" contentType="text/html"%>
<%@page import='java.util.ArrayList'%>
<%@page import='cl.cmr.Proc.Costas'%>
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
	
	String IdEntrada = request.getParameter("HDDENTRADA")!=null?request.getParameter("HDDENTRADA").trim():"INICIO";
	String FechaDesde = request.getParameter("CTxtFechaDesde")!=null?request.getParameter("CTxtFechaDesde").trim():"";
	String FechaHasta = request.getParameter("CTxtFechaHasta")!=null?request.getParameter("CTxtFechaHasta").trim():"";
	String CodSucursal = request.getParameter("CSUCURSAL")!=null?request.getParameter("CSUCURSAL").trim():"";
	String CodAbogado = request.getParameter("CABOGADO")!=null?request.getParameter("CABOGADO").trim():"";
	String CodProducto = request.getParameter("CPRODUCTO")!=null?request.getParameter("CPRODUCTO").trim():"3";
	
	Costas objCosta = Costas.getInstance();

	ArrayList resAux = new ArrayList();
	ArrayList resCosta = new ArrayList();
	
	String FechaIni=FechaDesde.substring(6,10) + FechaDesde.substring(3,5) + FechaDesde.substring(0,2);
	String FechaFin=FechaHasta.substring(6,10) + FechaHasta.substring(3,5) + FechaHasta.substring(0,2);
	int Iret = objCosta.ConsultarDetalleCostas(Integer.parseInt(FechaIni),Integer.parseInt(FechaFin),CodSucursal,CodAbogado,Integer.parseInt(CodProducto),resCosta);

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
<LINK REL="stylesheet" TYPE="text/css" HREF="../style/imprimir.css">
<script language="javascript">
  function InicioPag() {  	
		window.parent.printPage(window.parent.frames['printInforme']);
	}
</script>
</head>
<body class="body02" onload="javascript:InicioPag();return false;">
<table width="994px" border="0" cellspacing="0" cellpadding="0" class="tablaprincipal">
  <tr>
    <td valign="top">
      <table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
      	<%if (CodProducto.equals("3"))  //tarjeta
		{%>
   			<tr><td align="CENTER" class="titulo">DETALLE COSTA PROCESAL TARJETA&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=iDia + "/" + iMes + "/" + iAno%></td></tr>
        <%}
        else
        { %>
        	<tr><td align="CENTER" class="titulo">DETALLE COSTA PROCESAL CHEQUE PROTESTADO&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=iDia + "/" + iMes + "/" + iAno%></td></tr>
        <%} %>
        <tr>
          <td align="center">
        	<%if (CodProducto.equals("3"))  //tarjeta
			{%>
			<table class="tablainterior2" align="left" border=0 cellpadding="0" cellspacing="0">
					  <tr>
		           	  <td class="BORDEAZUL2"><div style="width:130px">Producto</div></td>
		           	  <td class="BORDEAZUL2"><div style="width:110px"">Rut Cliente</div></td>
		              <td class="BORDEAZUL2"><div style="width:150px">N° Operación</div></td>
		              <td class="BORDEAZUL3"><div style="width:120px">Valor Costa</div></td>
		              <td class="BORDEAZUL2"><div >Tipo Cargo</div></td>
		              </tr>				      
				   	  <%					   	
				   	  for(int i=0; i<resCosta.size(); i++) 
				   	  {
							resAux = new ArrayList();
             				resAux = (ArrayList)resCosta.get(i);
             			    %>
             			  	<tr>
			             	<td class="clsNormalString"><%=resAux.get(1).toString().trim()%></td>
					        <td class="clsNormalString"><%=resAux.get(2).toString().trim()%></td>					        
					        <%if (resAux.get(3).toString().trim().equals("")) 
					        {%>
					        	<td class="clsNormal"><B>TOTAL</td>
					        	<td class="clsNormal"><B><%=df.format(Double.parseDouble(resAux.get(4).toString().trim()))%></td>
					        <%}
					        else
					        { %>
					        	<td class="clsNormalString"><%=resAux.get(3).toString().trim()%></td>
					        	<td class="clsNormal"><%=df.format(Double.parseDouble(resAux.get(4).toString().trim()))%></td>
					        <%}%>
					        <td class="clsNormalString"><%=resAux.get(5).toString().trim()%></td>
					        </tr>
		              <%}%>
			</table>			
			<%}
			else //cheque
			{%>
			<table class="tablainterior2" align="left" border=0 cellpadding="0" cellspacing="0">
					  <tr>
		           	  <td class="BORDEAZUL2"><div style="width:130px">Producto</div></td>
		           	  <td class="BORDEAZUL2"><div style="width:110px">Rut Cliente</div></td>
		           	  <td class="BORDEAZUL2"><div style="width:150px">Nro Cheque</div></td>
		              <td class="BORDEAZUL3"><div style="width:120px">Valor Costa</div></td>
		              <td class="BORDEAZUL2"><div >Tipo Cargo</div></td>
		              </tr>				      
				   	  <%					   	
				   	  for(int i=0; i<resCosta.size(); i++) 
				   	  {
							resAux = new ArrayList();
             				resAux = (ArrayList)resCosta.get(i);
             			    %>
             			  	<tr>
			             	<td class="clsNormalString"><%=resAux.get(1).toString().trim()%></td>
		  		         	<%if (resAux.get(2).toString().trim().equals("0")) 
					        {%>
					        	<td class="clsNormal"></td>
					        	<td class="clsNormal"><B>TOTAL</td>
					        	<td class="clsNormal"><B><%=df.format(Double.parseDouble(resAux.get(3).toString().trim()))%></td>
					        <%}
					        else
					        { %>
					        	<td class="clsNormalString"><%=resAux.get(5).toString().trim()%></td>
					        	<td class="clsNormalString"><%=resAux.get(2).toString().trim()%></td>
					        	<td class="clsNormal"><%=df.format(Double.parseDouble(resAux.get(3).toString().trim()))%></td>
					        <%}%>					        
					        <td class="clsNormalString"><%=resAux.get(4).toString().trim()%></td>
					        </tr>
		              <%}%>
			</table>		
			<%}%>
		</td>
		</tr>
		</table>
	</td>
  </tr>
</table>
</BODY>
