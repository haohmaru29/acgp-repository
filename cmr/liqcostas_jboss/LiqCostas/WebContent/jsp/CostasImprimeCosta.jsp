<%@ page language='java' session="false" contentType="text/html"%>
<%@page import='java.util.ArrayList'%>
<%@page import='cl.cmr.Seguridad.SessionUsuario'%>
<%@page import='cl.cmr.com.Errores' %>
<%@page import='java.text.DecimalFormat'%>
<%@page import='java.text.DecimalFormatSymbols'%>
<%@page import='cl.cmr.Proc.Costas'%>
<%  	
	HttpSession sesion = request.getSession(true);
    SessionUsuario  oSes =(SessionUsuario)sesion.getAttribute("usuario");
	
	DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
	unusualSymbols.setDecimalSeparator(',');
	unusualSymbols.setGroupingSeparator('.');
		
	DecimalFormat df = new DecimalFormat("###,###,###,###,###,###,##0;(###,###,###,###,###,###,##0)",unusualSymbols);	
	
	String RESPBD = request.getParameter("RESPBD")!=null?request.getParameter("RESPBD").trim():"";
	String TipoProducto = request.getParameter("TIPOPROD")!=null?request.getParameter("TIPOPROD").trim():"";
	String NumCosta = request.getParameter("HDDNUMCOSTA")!=null?request.getParameter("HDDNUMCOSTA").trim():"0";
	
	ArrayList resAux = new ArrayList();
	ArrayList resEncCosta = new ArrayList();
	ArrayList resDetCosta = new ArrayList();	
	Costas objCostas = Costas.getInstance();
		
	int Iret = objCostas.ObtieneEncDetCostas(NumCosta,Integer.parseInt(TipoProducto),resEncCosta,resDetCosta);
	//colocar encabezado
	resAux = new ArrayList();
	resAux = (ArrayList)resEncCosta.get(0);
	String sFechaIng=resAux.get(0).toString().trim();
	String CodAbogado=resAux.get(1).toString().trim();
	String NomAbogado=resAux.get(2).toString().trim();
	String Moneda=resAux.get(14).toString().trim();
	String DescripEstado=resAux.get(5).toString().trim();
	String MontoCosta=df.format(Double.parseDouble(resAux.get(6).toString().trim()));
	String CodSucAbogado=resAux.get(7).toString().trim();
	String TipoDocumento=resAux.get(13).toString().trim();
	String NumDocumento=resAux.get(10).toString().trim();	  
	String RutPrestador=resAux.get(12).toString().trim();	    
	
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
		window.parent.printPage(window.parent.frames['printInforme'])
		if('<%=RESPBD%>'=="1") {
			if ('<%=TipoProducto%>'=="3")
		 		document.FRMINGRESOCOS.action = "CostasTarjIngreso.jsp";
		 	else
		 		document.FRMINGRESOCOS.action = "CostasCheqIngreso.jsp";	
			document.FRMINGRESOCOS.target = "_parent";
			document.FRMINGRESOCOS.HDDORIGEN.value="";
			document.FRMINGRESOCOS.HDDENTRADA.value="GRABACOSTA";
			document.FRMINGRESOCOS.content.value= document.getElementsByTagName('body').innerHTML;			
			document.FRMINGRESOCOS.submit();
		}
	}
</script>
</head>
<body onload="javascript:InicioPag(); return false;">
<FORM METHOD="POST" NAME="FRMINGRESOCOS">
<INPUT TYPE="HIDDEN" NAME="content" VALUE="">		
<INPUT TYPE="HIDDEN" NAME="HDDENTRADA" VALUE="">
<INPUT TYPE="HIDDEN" NAME="HDDORIGEN" VALUE="">
<INPUT TYPE="HIDDEN" NAME="HDDNUMCOSTA" VALUE="<%=NumCosta%>">
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
            	<%if (TipoProducto.equals("3"))
		      	 {%>
		       		<tr><td align="CENTER" class="titulo" colspan="8">INFORME COSTAS PROCESALES TARJETA</td></tr>
		       	<%}
		       	else 
		       	{%>
		       		<tr><td align="CENTER" class="titulo" colspan="8">INFORME COSTAS PROCESALES CHEQUES PROTESTADOS</td></tr>
		       	<%} %>	
            	<tr>
		            <td class="titulo1" width="12%">Emitido</td>
		            <td class="titulo11" width="8%"><%=sFechaIng%></td>	            
		            <td class="titulo1" width="10%">Sucursal</td>
		            <td class="titulo11" width="25%"><%=CodSucAbogado%></td>
		            <td class="titulo1" width="10%" >Abogado</td>
		            <td class="titulo11" width="8%" colspan="3"><%=NomAbogado%></td>
				</tr>		
				<tr>
					<td class="titulo1" width="12%">Rut Prestador</td>
		            <td class="titulo11" width="8%"><%=RutPrestador%></td>	      
		            <td class="titulo1" width="10%"><%=TipoDocumento%></td>
		            <td class="titulo11" width="25%"><%=NumDocumento%></td>
		             <td class="titulo1" width="10%" >N° Costa</td>
		            <td class="titulo11" width="10%" ><%=NumCosta%></td>	
		            <td class="titulo1" width="15%" >Monto Documento</td>
		            <td class="titulo11" width="8%" ><%=MontoCosta%></td>			            
				</tr>	
			  </table>
			<br />
			</div>
			<br />
			<table WIDTH="80%" align="left" id="tablaCentros" border=0 cellpadding="0" cellspacing="0">
				<%if (TipoProducto.equals("3"))
      	 		{%>
					<tr>
					  <td class="BORDEAZUL2">Rut Cliente</td>
		              <td class="BORDEAZUL2">Numero Operacion</td>
		              <td class="BORDEAZUL3">Valor Costa</td>
		              <td class="BORDEAZUL3">Tipo Cargo</td>			                           
				     </tr>
				<%}
				  else
				  {%>
				  	<tr>
				  	  <td class="BORDEAZUL2">Rut Cliente</td>	
		           	  <td class="BORDEAZUL2">N° Cheque</td>
		              <td class="BORDEAZUL3">Valor Costa</td>
		              <td class="BORDEAZUL3">Tipo Cargo</td>			            			             
		             </tr>
				  <%}%>
				        
				   <%//out.println("resDetCosta=" + resDetCosta.size());
				   	for(int i=0; i<resDetCosta.size(); i++) 
				   	{
						resAux = new ArrayList();
             			resAux = (ArrayList)resDetCosta.get(i);
             			//out.println("i=" + i);             			
             			if (i < resDetCosta.size()-1)
             			{%>
             				<tr>
	             			<td class="clsNormalString"><%=resAux.get(0).toString().trim()%></td>
			              	<td class="clsNormalString"><%=resAux.get(1).toString().trim()%></td>
			              	<td class="clsNormal"><%=df.format(Double.parseDouble(resAux.get(2).toString().trim()))%></td>
			              	<td class="clsNormal"><%=resAux.get(3).toString().trim()%></td>			              	
			              	</tr>
			           <%}
						else
						{
							if (i>0)
							{%>
								<tr>
			             			<td class="clsNormalString"></td>
					              	<td class="clsNormalString"><B>TOTAL</td>
					              	<td class="clsNormal"><B><%=df.format(Double.parseDouble(resAux.get(2).toString().trim()))%></td>
					              	<td class="clsNormalString"></td>					              	
					            </tr>
					      	<%}
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