<%@ page language='java' session="false" contentType="text/html"%>
<%@page import='Proc.General'%>
<%
	String TipoProducto = request.getParameter("TIPOPROD") != null? request.getParameter("TIPOPROD").trim(): "";
	String NumRemesa = request.getParameter("HDDNumRemesa") != null? request.getParameter("HDDNumRemesa").trim(): "0";
	String NomAbogado = request.getParameter("HDDNOMABO") != null? request.getParameter("HDDNOMABO").trim(): "";
	String sFechaIng = request.getParameter("HDDFechaIng") != null? request.getParameter("HDDFechaIng").trim(): "";
	String MontoRemesa = request.getParameter("HDDMonto") != null? request.getParameter("HDDMonto").trim(): "0";
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.0
	response.setHeader("Pragma", "no-cache"); //HTTP 1.1
	response.setDateHeader("Expires", 0);
	//out.println("NumRemesa=" + NumRemesa);	
	//out.println("NomAbogado=" + NomAbogado);	
	//out.println("sFechaIng=" + sFechaIng);
	//out.println("MontoRemesa=" + MontoRemesa);
	//General objGeneral = General.getInstance();

	String CodigoBarra = "";//objGeneral.Interleaved2of5("0" + NumRemesa + "0");
%>

<html>
<head>
<TITLE><%=com.Errores.getDescription("VAR.TITULO")%></TITLE>
<META HTTP-EQUIV="Cache-Control" content="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-store">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<LINK REL="stylesheet" TYPE="text/css" HREF="../style/imprimir.css">
<script type="text/javascript" src="../js/jquery-1.4.2.js"></script>
<script type="text/javascript">
	function InicioPag() {
		//window.parent.printPageCupon(window.parent.frames['printInforme']);
		window.parent.printPageCuponPDF(window.parent.frames['printInforme']);
		return false;
	}
</script>
</head>
<body class="body02" onload="javascript:InicioPag(); return false;">
	<input type="hidden" value="<%=NumRemesa%>" name="numeroRemesa" id="numeroRemesa">
	<input type="hidden" value="<%=MontoRemesa%>" name="montoRemesa" id="montoRemesa">
	<input type="hidden" value="<%=NomAbogado%>" name="nombreRemesa" id="nombreRemesa">
	<input type="hidden" value="<%=sFechaIng%>" name="fechaRemesa" id="fechaRemesa">
	<input type="hidden" value="<%=TipoProducto%>" name="tipoProducto" id="tipoProducto">
	<div id="remesaCupon">
		<table border="0" cellspacing="0" cellpadding="0" align="center">
			<tr>
				<td colspan="2" align="center" style="text-align:center;FONT-SIZE: 14px; COLOR: black;font-weight: bold;FONT-FAMILY: Verdana;height: 23px;">
					CUP&OacuteN DE PAGO
				</td>
			</tr>
			<tr>
				<td width="160px" style="FONT-SIZE: 14px; COLOR: black;font-weight: bold;FONT-FAMILY: Verdana;height: 23px;" >
					Fecha
				</td>
				<td width="320px" style="FONT-SIZE: 14px; COLOR: black;font-weight: bold;FONT-FAMILY: Verdana;height: 23px;">
					<b><%=sFechaIng%></b>
				</td>
			</tr>
			<tr>
				<%
					if (TipoProducto.equals("5")) {
				%>
				<td width="160px" style="FONT-SIZE: 14px; COLOR: black;font-weight: bold;FONT-FAMILY: Verdana;height: 23px;">Nombre Agencia</td>
				<%
					} else {
				%>
				<td width="160px" style="FONT-SIZE: 14px; COLOR: black;font-weight: bold;FONT-FAMILY: Verdana;height: 23px;">Nombre Abogado</td>
				<%
					}
				%>
				<td width="320px" style="FONT-SIZE: 14px; COLOR: black;font-weight: bold;FONT-FAMILY: Verdana;height: 23px;"><b><%=NomAbogado%></b>
				</td>
			</tr>
			<tr>
	            <td width="160px" style="FONT-SIZE: 14px; COLOR: black;font-weight: bold;FONT-FAMILY: Verdana;height: 23px;">
	            	Monto $
	            </td>
	            <td width="320" style="FONT-SIZE: 14px; COLOR: black;font-weight: bold;FONT-FAMILY: Verdana;height: 23px;">
	            	<b><%=MontoRemesa %></b>
	            </td>	            
	 	   </tr>
			
			<tr>
	            <td width="160px" style="FONT-SIZE: 14px; COLOR: black;font-weight: bold;FONT-FAMILY: Verdana;height: 23px;">
	            	N° Remesa
	            </td>
	            <td width="320" style="FONT-SIZE: 14px; COLOR: black;font-weight: bold;FONT-FAMILY: Verdana;height: 23px;">
	            	<b><%=NumRemesa %></b>
	            </td>	            
	 	   </tr>
			
			<tr>
				<td align="center" style="text-align:center;" colspan="2">
					<div style='font-size:44px;font-family:PF Interleaved 2 of 5 Wide' id="codigo_barra">
						<%=CodigoBarra%>
					</div>
				</td>
			</tr>
		</table>
	</div>
	
</body>
</html>
