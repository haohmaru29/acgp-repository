<%@ page language='java' session="false" contentType="text/html"%>
<%@page import='Proc.General'%>
<% 
String TipoProducto = request.getParameter("TIPOPROD")!=null?request.getParameter("TIPOPROD").trim():"";
String NumRemesa = request.getParameter("HDDNumRemesa")!=null?request.getParameter("HDDNumRemesa").trim():"0";
String NomAbogado = request.getParameter("HDDNOMABO")!=null?request.getParameter("HDDNOMABO").trim():"";
String sFechaIng = request.getParameter("HDDFechaIng")!=null?request.getParameter("HDDFechaIng").trim():"";
String MontoRemesa= request.getParameter("HDDMonto")!=null?request.getParameter("HDDMonto").trim():"0";

response.setHeader("Cache-Control","no-store"); //HTTP 1.0
response.setHeader("Pragma","no-cache"); //HTTP 1.1
response.setDateHeader("Expires",0);					
//out.println("NumRemesa=" + NumRemesa);	
//out.println("NomAbogado=" + NomAbogado);	
//out.println("sFechaIng=" + sFechaIng);
//out.println("MontoRemesa=" + MontoRemesa);
General objGeneral = General.getInstance();

String CodigoBarra=objGeneral.Interleaved2of5("0" + NumRemesa + "0");
%>

<html>
<head>
<TITLE><%=com.Errores.getDescription("VAR.TITULO")%></TITLE>
<META HTTP-EQUIV="Cache-Control" content="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-store">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Expires" CONTENT="0">
<LINK REL="stylesheet" TYPE="text/css" HREF="../style/imprimir.css">
<script language="javascript">
  function InicioPag() {
		window.parent.printPageCupon(window.parent.frames['printInforme'])
	}
</script>
</head>
<body class="body02" onload="javascript:InicioPag()">
      <table width="78%" border="0" cellspacing="0" cellpadding="0" align="center">
       	<tr><td align="CENTER" class="titulo">CUPÓN DE PAGO</td></tr>
        <tr>
          <td align="center">
            <div class="tablainterior3">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablagris">
               <tr>
		            <td width="17%" class="titulo">Fecha</td>
		            <td width="43%" class="titulo"><b><%=sFechaIng %></td>	            
		 	   </tr>
		 	   <tr>
		 	   		<%if (TipoProducto.equals("5"))
		            {%>
		            	<td width="17%" class="titulo">Nombre Agencia</td>
		            <%}
		             else
		            {%>
		            	<td width="17%" class="titulo">Nombre Abogado</td>		            
		            <%}%>		            
		            <td width="43%" class="titulo"><b><%=NomAbogado %></td>	            
		 	   </tr>
		 	    <tr>
		            <td width="17%" class="titulo">Monto $</td>
		            <td width="43%" class="titulo"><b><%=MontoRemesa %></td>	            
		 	   </tr>
		 	</table>
		 	</div>
		 	<!--
		 	<div>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
               <TR><BR><BR></TR>	
               <tr>               		
		            <td align="center">
		            <div style='font-size:72pt;font-family:PF Interleaved 2 of 5'><%=CodigoBarra%>
		            </div></td>
		       </tr>
		    </table>
		    </div>
		   -->
		    <div>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
               <TR><BR><BR></TR>	
               <tr>               		
		            <td align="center">
		            <div style='font-size:48pt;font-family:PF Interleaved 2 of 5 Wide'><%=CodigoBarra%>
		            </div></td>
		       </tr>
		    </table>
		    </div>   			
		 </td>
		</tr>
	</table>
		 
</body>
</html>
