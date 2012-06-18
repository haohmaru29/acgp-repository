<%@ page language='java' session="false" contentType="text/html"%>
<%@page import='java.util.ArrayList'%>
<%@page import ='java.util.Calendar'%>
<%@page import='cl.cmr.Proc.Costas'%>
<%@page import='java.text.DecimalFormat'%>
<%@page import='java.text.DecimalFormatSymbols'%>

<% 
	//CONSULTA TARJETA O CHEQUE	
	RequestDispatcher dispatcher;
	
	DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
	unusualSymbols.setDecimalSeparator(',');
	unusualSymbols.setGroupingSeparator('.');
		
	DecimalFormat df = new DecimalFormat("###,###,###,###,###,###,##0;(###,###,###,###,###,###,##0)",unusualSymbols);	
	
	int TipoProducto = Integer.parseInt(request.getParameter("TIPOPROD")!=null?request.getParameter("TIPOPROD").trim():"3");
	String Origen = request.getParameter("HDDORIGEN")!=null?request.getParameter("HDDORIGEN").trim():"";
	String IdEntrada = request.getParameter("HDDENTRADA")!=null?request.getParameter("HDDENTRADA").trim():"";
	String FechaDesde = request.getParameter("CTxtFechaDesde")!=null?request.getParameter("CTxtFechaDesde").trim():"";
	String FechaHasta = request.getParameter("CTxtFechaHasta")!=null?request.getParameter("CTxtFechaHasta").trim():"";
	String NumCosta = request.getParameter("CNumCosta")!=null?request.getParameter("CNumCosta").trim():"0";
	String CodSucursal = request.getParameter("CSUCURSAL")!=null?request.getParameter("CSUCURSAL").trim():"";
	String CodAbogado = request.getParameter("CABOGADO")!=null?request.getParameter("CABOGADO").trim():"";
	String EstadoLiq = request.getParameter("CESTADO")!=null?request.getParameter("CESTADO").trim():"0";
	String NumCheque = request.getParameter("CTxtNumCheque")!=null?request.getParameter("CTxtNumCheque").trim():"0";
	String TipoDocumento=request.getParameter("CTIPODOC")!=null?request.getParameter("CTIPODOC").trim():"";
	String NumDocumento=request.getParameter("CNumDocumento")!=null?request.getParameter("CNumDocumento").trim():"";
	String FechaDesc   = request.getParameter("HDDFechaDesc")!=null?request.getParameter("HDDFechaDesc").trim():"0";
	String hddNumCosta =request.getParameter("HDDNUMCOSTA")!=null?request.getParameter("HDDNUMCOSTA").trim():"0";
			
	//out.println("EstadoLiq=" + EstadoLiq);
	Costas objCosta = Costas.getInstance();
	
	ArrayList resEncCosta = new ArrayList();
	ArrayList resAux = new ArrayList();
	
	if (FechaDesde.equals("")==false)
	{
		String FechaIni=FechaDesde.substring(6,10) + FechaDesde.substring(3,5) + FechaDesde.substring(0,2);
		String FechaFin=FechaHasta.substring(6,10) + FechaHasta.substring(3,5) + FechaHasta.substring(0,2);
		String NewEstadoLiq="0";
		if (EstadoLiq.equals("0")==false)
			NewEstadoLiq=EstadoLiq.substring(2,EstadoLiq.length());
		//para tarjeta 1	
		int Iret = objCosta.ConsultarCostas(NumCosta,NumCheque,Integer.parseInt(FechaIni),Integer.parseInt(FechaFin),CodSucursal,CodAbogado,Integer.parseInt(NewEstadoLiq),TipoProducto,TipoDocumento,NumDocumento,Integer.parseInt(FechaDesc),resEncCosta);
	}			
%>

<jsp:include page="principal.jsp" flush="false"/>	
 
  <SCRIPT LANGUAGE="JavaScript">		
 		
 		function cambiarImagen(cual,url) 
		{
		  var imagen1 = "btn_dn.JPG";
		  var imagen2 = "btn_up.JPG";
		  if (cual.src.indexOf(imagen1)!=-1) 
		  {
		    cual.src = "../images/" + imagen2;
		    OrdenaColumna(window.document.FRMCONSULTACOS);
		  } else  
		  {
		    cual.src = "../images/" + imagen1;
		    OrdenaColumna(window.document.FRMCONSULTACOS);
		  }
		}
 		
		
		function OrdenaColumna(obj)
        {	
			if (obj.HDDFechaDesc.value == "0")
			{
				obj.HDDFechaDesc.value = "1";
			    document.FRMCONSULTACOS.action = "CostasFormConsultar.jsp";
				document.FRMCONSULTACOS.target = "iframeGrilla";			
				document.FRMCONSULTACOS.submit();	
			    
			}
			 else
			 {
			    obj.HDDFechaDesc.value = "0";			
			    document.FRMCONSULTACOS.action = "CostasFormConsultar.jsp";
				document.FRMCONSULTACOS.target = "iframeGrilla";			
				document.FRMCONSULTACOS.submit();	
			    
			 }
        }			
        
        function Salir(NumCostaSel,TipoProducto)
 		{
 			if (TipoProducto==3)
 				document.FRMCONSULTACOS.action = "CostasTarjIngreso.jsp";
			else
				document.FRMCONSULTACOS.action = "CostasCheqIngreso.jsp";
				
			document.FRMCONSULTACOS.target = "_parent";
			document.FRMCONSULTACOS.HDDENTRADA.value="GRABACOSTA";			
			document.FRMCONSULTACOS.HDDNUMCOSTA.value=NumCostaSel;
			document.FRMCONSULTACOS.HDDORIGEN.value="CONSULTAR";
			document.FRMCONSULTACOS.submit();	 
 		}
 		
 		 document.onkeydown = function()
		{ 
				//Sigma.U.stopEvent(event)
		    	if (window.event)
				{
						//alert(window.event.keyCode);
						switch (window.event.keyCode){
							case Sigma.Const.Key.TAB:
							case Sigma.Const.Key.ESC: 
							case Sigma.Const.Key.F1: 
							case Sigma.Const.Key.F2: 
							case Sigma.Const.Key.F3: 
							case Sigma.Const.Key.F4:
							case Sigma.Const.Key.F5:
							case Sigma.Const.Key.F6:
							case Sigma.Const.Key.F7:
							case Sigma.Const.Key.F8:
							case Sigma.Const.Key.F9:
							case Sigma.Const.Key.F10:
							case Sigma.Const.Key.F11:							
							case Sigma.Const.Key.F12:		
							case Sigma.Const.Key.BACKSPACE:			
								window.event.keyCode = 505; 
								break;	
						}
						if(window.event && window.event.keyCode == 505) 
	     				{ 
	     					return false;    
	     				}
					}
	     }
		</SCRIPT>
<BODY leftmargin="0" topmargin="0" onhelp="return false;">		
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
<FORM METHOD="POST" NAME="FRMCONSULTACOS">		
<INPUT TYPE="HIDDEN" NAME="HDDENTRADA" VALUE="<%=IdEntrada%>">
<INPUT TYPE="HIDDEN" NAME="HDDORIGEN" VALUE="<%=Origen%>">
<INPUT TYPE="HIDDEN" NAME="HDDNUMCOSTA" VALUE="<%=hddNumCosta%>">
<INPUT TYPE="HIDDEN" NAME="TIPOPROD" VALUE="<%=TipoProducto%>">
<INPUT TYPE="HIDDEN" NAME="HDDFechaDesc" VALUE="<%=FechaDesc%>">
<INPUT TYPE="HIDDEN" NAME="CTxtFechaDesde" VALUE="<%=FechaDesde%>">
<INPUT TYPE="HIDDEN" NAME="CTxtFechaHasta" VALUE="<%=FechaHasta%>">
<INPUT TYPE="HIDDEN" NAME="CNumCosta" VALUE="<%=NumCosta%>">
<INPUT TYPE="HIDDEN" NAME="CSUCURSAL" VALUE="<%=CodSucursal%>">
<INPUT TYPE="HIDDEN" NAME="CABOGADO" VALUE="<%=CodAbogado%>">
<INPUT TYPE="HIDDEN" NAME="CESTADO" VALUE="<%=EstadoLiq%>">
<INPUT TYPE="HIDDEN" NAME="CTxtNumCheque" VALUE="<%=NumCheque%>">
<INPUT TYPE="HIDDEN" NAME="CTIPODOC" VALUE="<%=TipoDocumento%>">
<INPUT TYPE="HIDDEN" NAME="CNumDocumento" VALUE="<%=NumDocumento%>">

<table width="994px" border="0" cellspacing="0" cellpadding="0" class="tablainterior950">
  <tr>
    <td valign="top">
			<table width="100%%" border=0 cellpadding="0" cellspacing="0" >
			<tr>
		           <td class="BORDEAZUL"><div style="width:100px">Nro Costa</div></td>
		           <%if (Integer.parseInt(FechaDesc)== 0)
      	 			{%>
		            <td class="BORDEAZUL">
		              	<div style="width:130px">
		              		<table border="0"><tr>
		              		<td>
		               		<img src="../images/btn_dn.JPG" onClick="cambiarImagen(this);" />
		              		</td>
		              		<td class="BORDEAZUL" style="border:0">Fecha Ingreso</td></tr></table>
		              	</div>
		              </td>
		            <%}
      	 			else
      	 			{ %>
      	 			 <td class="BORDEAZUL">
		              	<div style="width:130px">
		              		<table border="0"><tr>
		              		<td>
		               		<img src="../images/btn_up.JPG" onClick="cambiarImagen(this);" />
		              		</td>
		              		<td class="BORDEAZUL" style="border:0">Fecha Ingreso</td></tr></table>
		              	</div>
		              </td>
      	 			 <%}%>
		           	  <td class="BORDEAZUL"><div style="width:90px">Código<br>Abogado</div></td>
		              <td class="BORDEAZUL"><div style="width:200px"">Nombre Abogado</div></td>
		              <td class="BORDEAZUL"><div style="width:200px">Sucursal Abogado</div></td>
		              <td class="BORDEAZUL"><div style="width:106px">Tipo Documento</div></td>
		              <td class="BORDEAZUL"><div style="width:100px">Nro Documento</div></td>
		              <td class="BORDEAZUL"><div style="width:130px">Monto Documento</div></td>
		              <td class="BORDEAZUL"><div style="width:100px">Estado</div></td>	            
		            </tr>
				      
				   		<%	
				   		for(int i=0; i<resEncCosta.size(); i++) 
				   		{
							resAux = new ArrayList();
             				resAux = (ArrayList)resEncCosta.get(i);
             			%>             			  			
             			<tr>
	             			<td class="clsNormal"><a onmouseover="window.status ='';return true" onmouseout="window.status='';return true;" href="javascript:Salir(<%=resAux.get(0).toString().trim()%>,<%=TipoProducto%>)" target="_self"><u><%=resAux.get(0).toString().trim()%></u></a></td>
			              	<td class="clsNormal"><%=resAux.get(6).toString().trim()%></td>
			              	<td class="clsNormalString"><%=resAux.get(1).toString().trim()%></td>
			              	<td class="clsNormalString"><%=resAux.get(2).toString().trim()%></td>
			              	<td class="clsNormalString"><%=resAux.get(3).toString().trim()%></td>
			              	
			              	<td class="clsNormalString"><%=resAux.get(9).toString().trim()%></td>
			              	<td class="clsNormalString"><%=resAux.get(10).toString().trim()%></td>
			              	
			              	<td class="clsNormal"><%=df.format(Double.parseDouble(resAux.get(4).toString().trim()))%></td>
			              	<td class="clsNormalString"><%=resAux.get(5).toString().trim()%></td>			              	
			          	</tr>	
			        <%}%>
			</table>
		    </td>
		</tr>
</FORM>
</table>
	
</BODY>
</HTML>