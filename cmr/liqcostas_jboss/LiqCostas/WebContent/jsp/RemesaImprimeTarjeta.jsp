<%@ page language='java' session="false" contentType="text/html"%>
<%@page import='java.util.ArrayList'%>
<%@page import='cl.cmr.Seguridad.SessionUsuario'%>
<%@page import='java.text.DecimalFormat'%>
<%@page import='java.text.DecimalFormatSymbols'%>
<%@page import='cl.cmr.Proc.Remesa'%>
<%@page import='cl.cmr.com.Errores'%>
<%  	
	DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
	unusualSymbols.setDecimalSeparator(',');
	unusualSymbols.setGroupingSeparator('.');
	
	String iAno = new java.text.SimpleDateFormat("yyyy").format(new java.util.Date());
	String iDia = new java.text.SimpleDateFormat("dd").format(new java.util.Date());
	String iMes = new java.text.SimpleDateFormat("MM").format(new java.util.Date());
	
	String sFechaActual=iDia + "/" + iMes + "/" + iAno;	
	DecimalFormat df = new DecimalFormat("###,###,###,###,###,###,##0;(###,###,###,###,###,###,##0)",unusualSymbols);	
	
	String RESPBD = request.getParameter("RESPBD")!=null?request.getParameter("RESPBD").trim():"";
	String TipoProducto = request.getParameter("TIPOPROD")!=null?request.getParameter("TIPOPROD").trim():"";
	String NumRemesa = request.getParameter("HDDNumRemesa")!=null?request.getParameter("HDDNumRemesa").trim():"0";
	
	ArrayList resAux = new ArrayList();
	ArrayList resEncRemesa = new ArrayList();
	ArrayList resDetRemesa = new ArrayList();	
	
	Remesa objRemesa = Remesa.getInstance();
		
	int Iret = objRemesa.ObtieneEncDetRemesa(NumRemesa,Integer.parseInt(TipoProducto),resEncRemesa,resDetRemesa);
	//colocar encabezado
	resAux = new ArrayList();
	resAux = (ArrayList)resEncRemesa.get(0);
	String sFechaIng=resAux.get(0).toString().trim();
	String CodAbogado=resAux.get(1).toString().trim();
	String NomAbogado=resAux.get(2).toString().trim();
	String DescripEstado=resAux.get(5).toString().trim();
	String MontoRemesa=df.format(Double.parseDouble(resAux.get(6).toString().trim()));
	String CodSucAbogado=resAux.get(7).toString().trim();
	String FechaRemesa=resAux.get(9).toString().trim();	     
	String NumInterno =resAux.get(10).toString().trim();
	String Moneda=resAux.get(12).toString().trim();
	
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

<script type="text/javascript" >
  	function InicioPag() {
		window.parent.printPageDetalle(window.parent.frames['printInforme']);
 	}
</script>
</head>
<body onload="javascript:InicioPag(); return false;">
<FORM METHOD="POST" NAME="FRMINGRESOCOS">		
<INPUT TYPE="HIDDEN" NAME="HDDENTRADA" VALUE="">
<INPUT TYPE="HIDDEN" NAME="HDDORIGEN" VALUE="">
<INPUT TYPE="HIDDEN" NAME="HDDNumRemesa" VALUE="<%=NumRemesa%>">
<INPUT TYPE="HIDDEN" id="TIPOPROD"  NAME="TIPOPROD" VALUE="<%=TipoProducto%>">
<INPUT TYPE="HIDDEN" id="RESPBD"  NAME="RESPBD" VALUE="<%=RESPBD%>">

<table width="994px" border="0" cellspacing="0" cellpadding="0" class="tablaprincipal">
  <tr>
   <td valign="top">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
      	<tr>
          <td align="center">
            <div class="tablainterior2">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablagris">
            	<%if (TipoProducto.equals("1"))
		      	 {%>
		       		<tr><td align="CENTER" class="titulo" colspan="8">INFORME REMESAS ABOGADO CRÉDITO Y TC</td></tr>
		       	<%}
		       	else 
		       	{
		       		if (TipoProducto.equals("2"))
		       		{%>
		       			<tr><td align="CENTER" class="titulo" colspan="8">INFORME REMESAS CHEQUES PROTESTADOS</td></tr>
		       	   <%}
		       	    else
		       	    {%>
		       	    	<tr><td align="CENTER" class="titulo" colspan="8">INFORME REMESAS ECE (PREABOGADO)</td></tr>
		       	  <%}
		       	}%>    	
		    	<tr>
		            <td class="titulo1" width="12%">Emitido</td>
		            <td class="titulo11" width="8%"><%=sFechaIng%></td>	            
		            <td class="titulo1" width="10%">Sucursal</td>
		            <td class="titulo11" width="25%"><%=CodSucAbogado%></td>
		            <%if (TipoProducto.equals("5"))
		            {%>
		            	<td class="titulo1" width="10%" >Agencia</td>
		            <%}
		             else
		            {%>
		            	<td class="titulo1" width="10%" >Abogado</td>
		            
		            <%}%>	
		            <td class="titulo11" width="8%" colspan="3"><%=NomAbogado%></td>
		           
				</tr>		
				<tr>
		            <td class="titulo1" width="12%">Fecha Remesa</td>
		            <td class="titulo11" width="8%"><%=FechaRemesa%></td>	            
		            <td class="titulo1" width="10%">N° Interno</td>
		            <td class="titulo11" width="25%"><%=NumInterno%></td>
		            <td class="titulo1" width="10%" >N° Remesa</td>
		            <td class="titulo11" width="10%" ><%=NumRemesa%></td>	
		            <td class="titulo1" width="12%" >Monto Remesa</td>
		            <td class="titulo11" width="8%" ><%=MontoRemesa%></td>	
				</tr>		
			  </table>
			<br />
			</div>
			<br />
			<table WIDTH="100%" id="tablaCentros" border=0 cellpadding="0" cellspacing="0">
				<%if (TipoProducto.equals("1") || TipoProducto.equals("2"))
      	 		{%>
					<tr>
					  <td class="BORDEAZUL2">Rut Cliente</td>
					  <%if (TipoProducto.equals("1") || TipoProducto.equals("5"))
      	 			  {%>
		              		<td class="BORDEAZUL2">Número Operación</td>
		              <%}
		              else
		              {	%>
		              		<td class="BORDEAZUL2">Número Único</td>
		              <%}%>		              		
		              <td class="BORDEAZUL3">Capital</td>
		              <td class="BORDEAZUL3">Interés</td>
		              <td class="BORDEAZUL3">Costa Procesal</td>
		              <td class="BORDEAZUL3">Honorario Abogado</td>
		              <td class="BORDEAZUL3">Boleta</td>
					  <td class="BORDEAZUL3">Total</td>		
				     </tr>
				<%}
				  else
				  {%>
				  	<tr>
				  	  <td class="BORDEAZUL2">Rut Cliente</td>	
		           	  <td class="BORDEAZUL2">Número Operación</td>
		              <td class="BORDEAZUL3">Capital</td>
		              <td class="BORDEAZUL3">Interés</td>
		              <td class="BORDEAZUL3">Total</td>				             
		             </tr>
				  <%}%>
				        
				   <%//out.println("resDetRemesa=" + resDetRemesa.size());
				   	for(int i=0; i<resDetRemesa.size(); i++) 
				   	{
						resAux = new ArrayList();
             			resAux = (ArrayList)resDetRemesa.get(i);
             			//out.println("i=" + i);             			
             			if (i < resDetRemesa.size()-1)
             			{
             				if (TipoProducto.equals("1") || TipoProducto.equals("2"))
             				{%>
	             				<tr>
		             			<td class="clsNormalString"><%=resAux.get(0).toString().trim()%></td>
				              	<td class="clsNormalString"><%=resAux.get(1).toString().trim()%></td>
				              	<td class="clsNormal"><%=df.format(Double.parseDouble(resAux.get(2).toString().trim()))%></td>
				              	<td class="clsNormal"><%=df.format(Double.parseDouble(resAux.get(3).toString().trim()))%></td>
				              	<td class="clsNormal"><%=df.format(Double.parseDouble(resAux.get(4).toString().trim()))%></td>
				              	<td class="clsNormal"><%=df.format(Double.parseDouble(resAux.get(5).toString().trim()))%></td>
				              	<td class="clsNormal"><%=resAux.get(6).toString().trim()%></td>
				              	<td class="clsNormal"><%=df.format(Double.parseDouble(resAux.get(7).toString().trim()))%></td>
				              	</tr>
				            <%}
				            else
				            {%>
				              	<tr>
		             			<td class="clsNormalString"><%=resAux.get(0).toString().trim()%></td>
				              	<td class="clsNormalString"><%=resAux.get(1).toString().trim()%></td>
				              	<td class="clsNormal"><%=df.format(Double.parseDouble(resAux.get(2).toString().trim()))%></td>
				              	<td class="clsNormal"><%=df.format(Double.parseDouble(resAux.get(3).toString().trim()))%></td>
				              	<td class="clsNormal"><%=df.format(Double.parseDouble(resAux.get(4).toString().trim()))%></td>
				              	</tr>
			           		<%}
			            }
						else
						{
							if (i>0)
							{
								if (TipoProducto.equals("1") || TipoProducto.equals("2"))
								{%>
								<tr>
			             			<td class="clsNormalString"></td>
					              	<td class="clsNormalString"><B>TOTAL</td>
					              	<td class="clsNormal"><B><%=df.format(Double.parseDouble(resAux.get(2).toString().trim()))%></td>
					              	<td class="clsNormal"><B><%=df.format(Double.parseDouble(resAux.get(3).toString().trim()))%></td>
					              	<td class="clsNormal"><B><%=df.format(Double.parseDouble(resAux.get(4).toString().trim()))%></td>
					              	<td class="clsNormal"><B><%=df.format(Double.parseDouble(resAux.get(5).toString().trim()))%></td>
					              	<td class="clsNormal"><B><%=resAux.get(6).toString().trim()%></td>
					              	<td class="clsNormal"><B><%=df.format(Double.parseDouble(resAux.get(7).toString().trim()))%></td>
					            </tr>
					          <%}
					            else
					            {%>
						      	  	<tr>
				             			<td class="clsNormalString"></td>
						              	<td class="clsNormalString"><B>TOTAL</td>
						              	<td class="clsNormal"><B><%=df.format(Double.parseDouble(resAux.get(2).toString().trim()))%></td>
						              	<td class="clsNormal"><B><%=df.format(Double.parseDouble(resAux.get(3).toString().trim()))%></td>
						              	<td class="clsNormal"><B><%=df.format(Double.parseDouble(resAux.get(4).toString().trim()))%></td>
						            </tr>
					      	  <%}
					      }	
			            }
			         }%>  	
		   </table>		
		</td>
	  </tr>
	</table>
	</td>
  </tr>
</table>
</FORM>
</BODY>
</HTML>