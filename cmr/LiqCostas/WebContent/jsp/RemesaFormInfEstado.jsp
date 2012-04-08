<%@ page language='java' session="false" contentType="text/html"%>
<%@page import='java.util.ArrayList'%>
<%@page import='java.util.Calendar'%>
<%@page import='Proc.Remesa'%>
<%@page import='java.text.DecimalFormat'%>
<%@page import='java.text.DecimalFormatSymbols'%>
<%
	DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
	unusualSymbols.setDecimalSeparator(',');
	unusualSymbols.setGroupingSeparator('.');

	DecimalFormat df = new DecimalFormat("###,###,###,###,###,###,##0;(###,###,###,###,###,###,##0)",unusualSymbols);

	String Origen = request.getParameter("HDDORIGEN") != null ? request.getParameter("HDDORIGEN").trim() : "INGRESO";
	String IdEntrada = request.getParameter("HDDENTRADA") != null ? request.getParameter("HDDENTRADA").trim(): "INICIO";
	String FechaDesde = request.getParameter("CTxtFechaDesde") != null ? request.getParameter("CTxtFechaDesde").trim(): "";
	String FechaHasta = request.getParameter("CTxtFechaHasta") != null ? request.getParameter("CTxtFechaHasta").trim(): "";
	String NumRemesa = request.getParameter("CNumRemesa") != null ? request.getParameter("CNumRemesa").trim() : "0";
	String CodSucursal = request.getParameter("CSUCURSAL") != null ? request.getParameter("CSUCURSAL").trim(): "";
	String CodAbogado = request.getParameter("CABOGADO") != null ? request.getParameter("CABOGADO").trim(): "";
	String CodProductoSel = request.getParameter("CPRODUCTOSEL") != null ? request.getParameter("CPRODUCTOSEL").trim(): "";
	String EstadoRem = request.getParameter("CESTADO") != null ? request.getParameter("CESTADO").trim()	: "0";
	String CodProducto = request.getParameter("CPRODUCTO") != null ? request.getParameter("CPRODUCTO").trim(): "0";
	String hddNumRemesa = request.getParameter("HDDNumRemesa") != null ? request.getParameter("HDDNumRemesa").trim()	: "0";
	String FechaDesc = request.getParameter("HDDFechaDesc") != null ? request.getParameter("HDDFechaDesc").trim(): "-1";
	String FechaPagoDesc = request.getParameter("HDDFechaPagoDesc") != null ? request.getParameter("HDDFechaPagoDesc").trim(): "-1";
	String FechaProc = request.getParameter("HDDFechaProc") != null ? request.getParameter("HDDFechaProc").trim(): "-1";
	String FechaInpu = request.getParameter("HDDFechaInpu") != null ? request.getParameter("HDDFechaInpu").trim(): "-1";
	String FechaConta = request.getParameter("HDDFechaConta") != null ? request.getParameter("HDDFechaConta").trim(): "-1";

	//out.println("EstadoRem=" + EstadoRem);
	
	Proc.General objGeneral = Proc.General.getInstance();
	Remesa objRemesa = Remesa.getInstance();

	ArrayList resAux = new ArrayList();
	ArrayList resInfRem = new ArrayList();

	if (FechaDesde.equals("") == false) 
	{
			String FechaIni = FechaDesde.substring(6, 10)+ FechaDesde.substring(3, 5)+ FechaDesde.substring(0, 2);
			String FechaFin = FechaHasta.substring(6, 10)+ FechaHasta.substring(3, 5)+ FechaHasta.substring(0, 2);
			String NewEstadoRem="0";
			if (EstadoRem.equals("0") == false)
				NewEstadoRem = EstadoRem.substring(2, EstadoRem.length());
				
			int Iret = objRemesa.ConsultarInformeEstado(NumRemesa,
					Integer.parseInt(FechaIni), Integer
							.parseInt(FechaFin), CodSucursal,
					CodAbogado, Integer.parseInt(NewEstadoRem), Integer
							.parseInt(CodProducto), Integer
							.parseInt(FechaDesc), Integer
							.parseInt(FechaPagoDesc), Integer
							.parseInt(FechaProc), Integer
							.parseInt(FechaInpu), Integer
							.parseInt(FechaConta), resInfRem);			
	}
%>


<jsp:include page="principal.jsp" flush="false" />

<SCRIPT LANGUAGE="JavaScript">	
  	
 	function cambiarImagen(cual,url) {
		  var imagen1 = "btn_dn.JPG";
		  var imagen2 = "btn_up.JPG";
		  if (cual.src.indexOf(imagen1)!=-1) {
		    cual.src = "../images/" + imagen2;
		  } else {
		    cual.src = "../images/" + imagen1;
		  }
		}
 	
       function OrdenaColumna(col,obj){	
       		//alert(obj.HDDFechaDesc.value);
        	if(col == '0'){
				if (obj.HDDFechaDesc.value == "0"){
					obj.HDDFechaDesc.value = "1";
				} else {
				   obj.HDDFechaDesc.value = "0";			
				}
				obj.HDDFechaPagoDesc.value = "-1";
				obj.HDDFechaProc.value = "-1";
				obj.HDDFechaInpu.value = "-1";
				obj.HDDFechaConta.value = "-1";
			}
			if(col == '1'){
				if (obj.HDDFechaPagoDesc.value == "0"){
					obj.HDDFechaPagoDesc.value = "1";
				} else {
				   obj.HDDFechaPagoDesc.value = "0";			
				}
				obj.HDDFechaDesc.value = "-1";
				obj.HDDFechaProc.value = "-1";
				obj.HDDFechaInpu.value = "-1";
				obj.HDDFechaConta.value = "-1";
			}
			if(col == '2'){
				if (obj.HDDFechaProc.value == "0"){
					obj.HDDFechaProc.value = "1";
				} else {
				   obj.HDDFechaProc.value = "0";			
				}
				obj.HDDFechaDesc.value = "-1";
				obj.HDDFechaPagoDesc.value = "-1";
				obj.HDDFechaInpu.value = "-1";
				obj.HDDFechaConta.value = "-1";
			}
			if(col == '3'){
				if (obj.HDDFechaInpu.value == "0"){
					obj.HDDFechaInpu.value = "1";
				} else {
				   obj.HDDFechaInpu.value = "0";			
				}
				obj.HDDFechaDesc.value = "-1";
				obj.HDDFechaPagoDesc.value = "-1";
				obj.HDDFechaProc.value = "-1";
				obj.HDDFechaConta.value = "-1";
			}
			if(col == '4'){
				if (obj.HDDFechaConta.value == "0"){
					obj.HDDFechaConta.value = "1";
				} else {
				   obj.HDDFechaConta.value = "0";			
				}
				obj.HDDFechaDesc.value = "-1";
				obj.HDDFechaPagoDesc.value = "-1";
				obj.HDDFechaProc.value = "-1";
				obj.HDDFechaInpu.value = "-1";
			}
			//alert(obj.CESTADO.value);
			document.FRMESTADOREM.action = "RemesaFormInfEstado.jsp";
			document.FRMESTADOREM.target = "iframeGrilla";			
			document.FRMESTADOREM.submit();
        } 		 		
		//fin funciones de validacion
		
		function Salir(NumRemesa,TipoProducto)
 		{
 			if (TipoProducto==1)
 				document.FRMESTADOREM.action = "RemesaTarjIngreso.jsp";
			else
				if (TipoProducto==2)
					document.FRMESTADOREM.action = "RemesaCheqIngreso.jsp";
				else
					document.FRMESTADOREM.action = "RemesaPreAboIngreso.jsp";
					
			document.FRMESTADOREM.target = "_parent";
			document.FRMESTADOREM.HDDENTRADA.value="GRABAREMESA";			
			document.FRMESTADOREM.HDDNumRemesa.value=NumRemesa;
			document.FRMESTADOREM.HDDORIGEN.value="INFORME";
			document.FRMESTADOREM.submit();	 
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
		
<BODY leftmargin="0" topmargin="0" >
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablaprincipalSinMargen">
	<FORM METHOD="POST" NAME="FRMESTADOREM">
		<INPUT TYPE="HIDDEN" NAME="HDDENTRADA" VALUE="<%=IdEntrada%>">
		<INPUT TYPE="HIDDEN" NAME="HDDNumRemesa" VALUE="<%=hddNumRemesa%>">
		<INPUT TYPE="HIDDEN" NAME="HDDORIGEN" VALUE="<%=Origen%>">
		<INPUT TYPE="HIDDEN" NAME="CPRODUCTOSEL" VALUE="<%=CodProductoSel%>">
		<INPUT TYPE="HIDDEN" NAME="CTxtFechaDesde" VALUE="<%=FechaDesde%>">
		<INPUT TYPE="HIDDEN" NAME="CTxtFechaHasta" VALUE="<%=FechaHasta%>">
		<INPUT TYPE="HIDDEN" NAME="CNumRemesa" VALUE="<%=NumRemesa%>">
		<INPUT TYPE="HIDDEN" NAME="CSUCURSAL" VALUE="<%=CodSucursal%>">
		<INPUT TYPE="HIDDEN" NAME="CABOGADO" VALUE="<%=CodAbogado%>">
		<INPUT TYPE="HIDDEN" NAME="CESTADO" VALUE="<%=EstadoRem%>">
		<INPUT TYPE="HIDDEN" NAME="CPRODUCTO" VALUE="<%=CodProducto%>">
		<INPUT TYPE="HIDDEN" NAME="HDDFechaDesc" VALUE="<%=FechaDesc%>">
		<INPUT TYPE="HIDDEN" NAME="HDDFechaPagoDesc" VALUE="<%=FechaPagoDesc%>">
		<INPUT TYPE="HIDDEN" NAME="HDDFechaProc" VALUE="<%=FechaProc%>">
		<INPUT TYPE="HIDDEN" NAME="HDDFechaInpu" VALUE="<%=FechaInpu%>">
		<INPUT TYPE="HIDDEN" NAME="HDDFechaConta" VALUE="<%=FechaConta%>">
	<tr>
		<td valign="top">
			<table id="tablaCentros" border=0 cellpadding="0" cellspacing="0">
				<tr>
					<td class="BORDEAZUL"><div style="width: 100px">N° Remesa	</div></td>
					<td class="BORDEAZUL"><div style="width: 200px">Abogado</div></td>
					<td class="BORDEAZUL"><div style="width: 200px">Sucursal</div></td>
					<td class="BORDEAZUL"><div style="width: 180px">Producto</div></td>
					<td class="BORDEAZUL"><div style="width: 115px">Monto<br>Remesa</div></td>
					<td class="BORDEAZUL"><div style="width: 100px">Estado</div></td>
					<%if (Integer.parseInt(FechaDesc) == 0) {
					%>
					<td class="BORDEAZUL">
						<div style="width: 100px">
							<table border="0">
								<tr>
									<td>
										<img src="../images/btn_dn.JPG"	onClick="cambiarImagen(this);OrdenaColumna(0,window.document.FRMESTADOREM);" />
									</td>
									<td class="BORDEAZUL" style="border: 0">Fecha<br>Digitación</td>
								</tr>
							</table>
						</div>
					</td>
					<%} else {
					%>
					<td class="BORDEAZUL">
						<div style="width: 100px">
							<table border="0">
								<tr>
									<td>
										<img src="../images/btn_up.JPG"	onClick="cambiarImagen(this);OrdenaColumna(0,window.document.FRMESTADOREM);" />
									</td>
									<td class="BORDEAZUL" style="border: 0">Fecha<br>Digitación	</td>
								</tr>
							</table>
						</div>
					</td>
					<%}
					%>
					<%if (Integer.parseInt(FechaPagoDesc) == 0) {
					%>
					<td class="BORDEAZUL">
						<div style="width: 100px">
							<table border="0">
								<tr>
									<td>
										<img src="../images/btn_dn.JPG"	onClick="cambiarImagen(this);OrdenaColumna(1,window.document.FRMESTADOREM);" />
									</td>
									<td class="BORDEAZUL" style="border: 0">Fecha<br>Pago</td>
								</tr>
							</table>
						</div>
					</td>
					<%} else {
					%>
					<td class="BORDEAZUL">
						<div style="width: 100px">
							<table border="0">
								<tr>
									<td>
										<img src="../images/btn_up.JPG"	onClick="cambiarImagen(this);OrdenaColumna(1,window.document.FRMESTADOREM);" />
									</td>
									<td class="BORDEAZUL" style="border: 0">Fecha<br>Pago</td>
								</tr>
							</table>
						</div>
					</td>
					<%}
					%>

					<%if (Integer.parseInt(FechaProc) == 0) {
					%>
					<td class="BORDEAZUL">
						<div style="width: 100px">
							<table border="0">
								<tr>
									<td>
										<img src="../images/btn_dn.JPG"	onClick="cambiarImagen(this);OrdenaColumna(2,window.document.FRMESTADOREM);" />
									</td>
									<td class="BORDEAZUL" style="border: 0">Fecha<br>Proceso CA	</td>
								</tr>
							</table>
						</div>
					</td>
					<%} else {
					%>
					<td class="BORDEAZUL">
						<div style="width: 100px">
							<table border="0">
								<tr>
									<td>
										<img src="../images/btn_up.JPG"	onClick="cambiarImagen(this);OrdenaColumna(2,window.document.FRMESTADOREM);" />
									</td>
									<td class="BORDEAZUL" style="border: 0">Fecha<br>Proceso CA	</td>
								</tr>
							</table>
						</div>
					</td>
					<%}%>

					<%if (Integer.parseInt(FechaInpu) == 0) {%>
					<td class="BORDEAZUL">
						<div style="width: 100px">
							<table border="0">
								<tr>
									<td>
										<img src="../images/btn_dn.JPG"	onClick="cambiarImagen(this);OrdenaColumna(3,window.document.FRMESTADOREM);" />
									</td>
									<td class="BORDEAZUL" style="border: 0">Fecha<br>Imputación</td>
								</tr>
							</table>
						</div>
					</td>
					<%} else {%>
					<td class="BORDEAZUL">
						<div style="width: 100px">
							<table border="0">
								<tr>
									<td>
										<img src="../images/btn_up.JPG" onClick="cambiarImagen(this);OrdenaColumna(3,window.document.FRMESTADOREM);" />
									</td>
									<td class="BORDEAZUL" style="border: 0">Fecha<br>Imputación</td>
								</tr>
							</table>
						</div>
					</td>
					<%}%>

					<%if (Integer.parseInt(FechaConta) == 0) {%>
					<td class="BORDEAZUL">
						<div style="width: 100px">
							<table border="0">
								<tr>
									<td>
										<img src="../images/btn_dn.JPG"	onClick="cambiarImagen(this);OrdenaColumna(4,window.document.FRMESTADOREM);" />
									</td>
									<td class="BORDEAZUL" style="border: 0">Fecha<br>Contable ISS</td>
								</tr>
							</table>
						</div>
					</td>
					<%
						} else {
					%>
					<td class="BORDEAZUL">
						<div style="width: 100px">
							<table border="0">
								<tr>
									<td>
										<img src="../images/btn_up.JPG"	onClick="cambiarImagen(this);OrdenaColumna(4,window.document.FRMESTADOREM);" />
									</td>
									<td class="BORDEAZUL" style="border: 0">Fecha<br>Contable ISS</td>
								</tr>
							</table>
						</div>
					</td>
					<%}%>
				</tr>
				<%
					for (int i = 0; i < resInfRem.size(); i++) {
						resAux = new ArrayList();
						resAux = (ArrayList) resInfRem.get(i);
				%>
				<tr>
					<td class="clsNormal">
						<a onmouseover="window.status ='';return true"
							onmouseout="window.status='';return true;"	href="javascript:Salir(<%=resAux.get(0).toString().trim()%>,<%=resAux.get(11).toString().trim()%>)"	target="_self"><u><%=resAux.get(0).toString().trim()%></u> </a>
					</td>
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
			</div>
		</td>
	</tr>
</FORM>
</table>
</BODY>
</HTML>
