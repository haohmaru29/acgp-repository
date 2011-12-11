<%@ page language='java' contentType="text/html" errorPage="error.jsp?ERR=ERR.GENERAL.PAGE"%>
<%getServletContext().getRequestDispatcher("/jsp/acceso.jsp").include(request,response);%>
<%@page import='java.util.ArrayList'%>
<%@page import ='java.util.Calendar'%>
<%@page import='Proc.General'%>
<%@page import='Proc.Costas'%>
<%@page import='java.text.DecimalFormat'%>
<%@page import='java.text.DecimalFormatSymbols'%>

<%  
	
	String iAno = new java.text.SimpleDateFormat("yyyy").format(new java.util.Date());
	String iDia = new java.text.SimpleDateFormat("dd").format(new java.util.Date());
	String iMes = new java.text.SimpleDateFormat("MM").format(new java.util.Date());
		
	Calendar calFin = Calendar.getInstance();		
	calFin.set(Integer.parseInt(iAno), Integer.parseInt(iMes)-1, 1);
	calFin.set(Integer.parseInt(iAno), Integer.parseInt(iMes)-1, calFin.getActualMaximum(Calendar.DAY_OF_MONTH));
	String UltDia = new java.text.SimpleDateFormat("dd").format(calFin.getTime());

	RequestDispatcher dispatcher;
	
	DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
	unusualSymbols.setDecimalSeparator(',');
	unusualSymbols.setGroupingSeparator('.');
		
	DecimalFormat df = new DecimalFormat("###,###,###,###,###,###,##0;(###,###,###,###,###,###,##0)",unusualSymbols);	
	
	String sMensaje="";

	String IdEntrada = request.getParameter("HDDENTRADA")!=null?request.getParameter("HDDENTRADA").trim():"INICIO";
	String FechaDesde = request.getParameter("CTxtFechaDesde")!=null?request.getParameter("CTxtFechaDesde").trim():"";
	String FechaHasta = request.getParameter("CTxtFechaHasta")!=null?request.getParameter("CTxtFechaHasta").trim():"";
	String CodSucursal = request.getParameter("CSUCURSAL")!=null?request.getParameter("CSUCURSAL").trim():"";
		
	if (FechaDesde.equals(""))
	{
		FechaDesde="01/" + iMes + "/" + iAno;
		FechaHasta=UltDia + "/" + iMes + "/" + iAno;
	}
	//out.println("IdEntrada=" + IdEntrada);
	//out.println("FechaDesde=" + FechaDesde);
	//out.println("FechaHasta=" + FechaHasta);
	//out.println("CodSucursal=" + CodSucursal);
		
	Proc.General objGeneral = General.getInstance();
	Proc.Costas objCosta = Costas.getInstance();

	ArrayList resSucursal = new ArrayList();
	ArrayList resAux = new ArrayList();
	ArrayList resEncCosta = new ArrayList();
			
	boolean retD =false ;
	retD = objGeneral.ObtieneElemLista("SUCURSAL","0",resSucursal);
	
	if (IdEntrada.equals("INICIO") || IdEntrada.equals("VOLVERBUSQ") || IdEntrada.equals("LIMPIAR"))
	{
		if (FechaDesde.equals("")==false)
		{
			String FechaIni=FechaDesde.substring(6,10) + FechaDesde.substring(3,5) + FechaDesde.substring(0,2);
			String FechaFin=FechaHasta.substring(6,10) + FechaHasta.substring(3,5) + FechaHasta.substring(0,2);
			int Iret = objCosta.ConsultarResumenCostas(Integer.parseInt(FechaIni),Integer.parseInt(FechaFin),CodSucursal,resEncCosta);
			if (Iret==2)
				sMensaje=com.Errores.getDescription("ERR.GENERAL.BASE");
			else
				if (resEncCosta.size()<=0)
					sMensaje=com.Errores.getDescription("ERR.RESUMEN.COSTA");
		}
	}
	else
	{
		if (IdEntrada.equals("CONSULTAR") || IdEntrada.equals("INGRESO"))
		{
			dispatcher = getServletContext().getRequestDispatcher("/jsp/CostasResumen.jsp");
			dispatcher.forward(request,response);	
		}
	}
	
%>

<jsp:include page="principal.jsp" flush="false"/>	
 
  <SCRIPT LANGUAGE="JavaScript">		
 
	 	function InicioPag() 
	 	{		
			document.FRMCONSULTACOS.CTxtFechaDesde.select();
	 		document.FRMCONSULTACOS.CTxtFechaDesde.focus();	 		
		}
		  		  		
		//carga de arreglos		  		
		 var arrSucursal = new Array();
		 var arrPaso;
		<%for(int i=0; i<resSucursal.size(); i++) {
				resAux = new ArrayList();
             	resAux = (ArrayList)resSucursal.get(i);%>             
    	        arrPaso = new Array("<%=resAux.get(0).toString().trim()%>","<%=resAux.get(0).toString().trim()%>"+ '-' + "<%=resAux.get(1).toString().trim()%>");
        	    arrSucursal[<%=i%>]=arrPaso;
		<%}%>			
		//fin carga de arreglos	
			
		//funciones de validacion	
		
		function Limpiar(obj)
		{
			LimpiarCamposConsulta(obj,7);
			f.action = "CostasResumen.jsp";
			f.target = "_self";
			f.HDDENTRADA.value="LIMPIAR";			
			f.submit();
		}		 	
		 	
		function ConsultarCostas(obj)
		{		
			if (ValidaFechasdeBusqueda(obj))
			{
				f.action = "CostasResumen.jsp";
				f.target = "_self";
				f.HDDENTRADA.value="VOLVERBUSQ";			
				f.submit();
			}
			
 		}		
 		//fin funciones de validacion
		
		function ImprimirInforme()
 		{ 			 			
			document.FRMCONSULTACOS.action = "CostasImprimeResumen.jsp";
			document.FRMCONSULTACOS.target = "printInforme";			
			document.FRMCONSULTACOS.submit();			
 		}
 		
 		function printPage(ifr) 
 		{
 			if (ifr.location.href != "about:blank")
			{
	   		 ifr.focus(); 
	   		 ifr.print();
			}
			document.FRMCONSULTACOS.CTxtFechaDesde.select();
	 		document.FRMCONSULTACOS.CTxtFechaDesde.focus();	 	
  		} 	
  		
  		function SetearObjeto(valor)
		{
		  	document.FRMCONSULTACOS.NOMBREOBJETO.value=valor;
		  	return false;
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
							case Sigma.Const.Key.NUMLOCK: 
							case Sigma.Const.Key.F2: 
							case Sigma.Const.Key.F3: 
							case Sigma.Const.Key.ASTE: 
							case Sigma.Const.Key.F4:
							case Sigma.Const.Key.F5:
							case Sigma.Const.Key.F6:
							case Sigma.Const.Key.F7:
							case Sigma.Const.Key.F8:
							case Sigma.Const.Key.F9:
							case Sigma.Const.Key.F10:
							case Sigma.Const.Key.F11:
							case Sigma.Const.Key.F12:			
								window.event.keyCode = 505; 
								break;					
							case Sigma.Const.Key.BACKSPACE:	
								//alert(f.NOMBREOBJETO.value);
								if (f.NOMBREOBJETO.value=="" || f.NOMBREOBJETO.value.substring(0,5)=="rollo")
									window.event.keyCode = 505; 
								break;	
						}
						if(window.event && window.event.keyCode == 505) 
	     				{ 
	     					return false;    
	     				}
					}
	     }
	     function ValidarFechaDesde(obj,confoco)
		{
			var flag=true;	
			if (obj.CTxtFechaDesde.value!="")
			{
				var f1=obj.CTxtFechaDesde.value.split("/");
				if (f1.length==3)
					var fechaaux=f1[0]+f1[1]+f1[2];
				else	
					var fechaaux=f1[0];
				if (valJS.isFechaValida(fechaaux,"ddmmyyyy")==false) 
        		{
        		   f.NOMBREOBJETO.value="CTxtFechaDesde"; 		
		           alert("Fecha Desde no válida");           
		           obj.CTxtFechaDesde.value="";
		           obj.CTxtFechaDesde.select();   
		           obj.CTxtFechaDesde.focus();   
		           flag= false;
		       }    
			}	
			return flag;
		}
		function ValidarFechaHasta(obj,confoco)
		{
			var flag=true;			
			if (obj.CTxtFechaHasta.value!="")
			{
				var f1=obj.CTxtFechaHasta.value.split("/");
				if (f1.length==3)
					var fechaaux=f1[0]+f1[1]+f1[2];
				else	
					var fechaaux=f1[0];				
				if (valJS.isFechaValida(fechaaux,"ddmmyyyy")==false) 
        		{
        		   f.NOMBREOBJETO.value="CTxtFechaHasta"; 
		           alert("Fecha Hasta no válida");           
		           obj.CTxtFechaHasta.value="";
		           obj.CTxtFechaHasta.select();   
		           obj.CTxtFechaHasta.focus();   
		           flag= false;
		       }    
			}	
			return flag;
		}
		</SCRIPT>

<body leftmargin="0" topmargin="0" onload="javascript:InicioPag()" onhelp="return false;">

<jsp:include page="ColocaMenu.jsp" flush="false"/>
<script type="text/javascript">

		 $(document).ready(function () {
		 
		    	function prev(arr, index) {
				  	arr.sort(
     					function(a,b){
     		  				return Math.abs(a.attr('tabindex')) < Math.abs(b.attr('tabindex')) ? 1 : -1;
     					}
   					);
   					for (var i=0;i<arr.length;i++){
   					
   						if (Math.abs(arr[i].attr('tabindex'))<Math.abs(index)) return arr[i];
   					}
   					
   					return null;
				}
				
				function next(arr, index) {
				  	arr.sort(
     					function(a,b){
							
     		  				return Math.abs(a.attr('tabindex')) > Math.abs(b.attr('tabindex')) ? 1 : -1;
     					}
   					);
   					for (var i=0;i<arr.length;i++){
   						if (Math.abs(arr[i].attr('tabindex'))>Math.abs(index)) return arr[i];
   					}
   					return null;
				}
									
					jQuery(':*[tabindex]').each( function () {
					if (jQuery(this).attr('type')!='hidden'  && jQuery(this).attr('type') !="radio" && jQuery(this).attr('type') !="form")
					if ('select-one,button, text, checkbox'.indexOf(jQuery(this).attr('type'))>=0  || jQuery(this).get(0).nodeName.toUpperCase()=="A") 
					   if (jQuery(this).attr('id').substring(0,5)=="rollo" )
				  	   {
				  	   	   var variable1=Sigma.Const.Key.LEFT; 
						   var variable2=Sigma.Const.Key.RIGHT;    	
						   var variable3=Sigma.Const.Key.RIGHT; 
				  	   }
				  	   else
				  	   {
					  	   var variable1=Sigma.Const.Key.LEFT; 
						   var variable2=Sigma.Const.Key.RIGHT;    	
						   var variable3=Sigma.Const.Key.ENTER;  
						}	     	
				  	  jQuery(this).bind("keydown", function(event) 
					  {			
					      if (event.keyCode==variable1 || event.keyCode==variable2 || event.keyCode==variable3)
					      {
					      	  	event.preventDefault();
								var arr = [];								
								var index = jQuery(this).attr("tabindex");								
								if (!index) return false;
								jQuery(':*[tabindex]').each( function () {
										
								 		if (jQuery(this).attr('id')!=null && jQuery(this).attr('tabindex')!=null && jQuery(this).attr('type')!='hidden' && jQuery(this).attr("disabled")==false)
								 		{							 		
								 		   if ('select-one,button, text, checkbox'.indexOf(jQuery(this).attr('type'))>=0 || jQuery(this).get(0).nodeName.toUpperCase()=="A" )
								 		   {
										   	if (jQuery(this).attr('id')!="")
										   		arr[arr.length]=jQuery(this);										   
										   }
										}
								});	
								
								try {
												  
									if (event.keyCode==Sigma.Const.Key.LEFT)
									{
										 var obj = prev(arr,index );										 
										 if (obj!=null)
										 	if (!obj.focus);
										 	else
										 	{		
										 		f.NOMBREOBJETO.value=obj.attr('id');	
										 		obj.select();
										 		obj.focus();											 				
										 	}
										 else
										 {										
										 	f.NOMBREOBJETO.value=arr[0].attr('id');
										 	arr[0].select();		 	
										 	arr[0].focus();											 				
										 }					  				 
									}									
									else
									{ 
										if (event.keyCode==Sigma.Const.Key.RIGHT || event.keyCode==Sigma.Const.Key.ENTER)
										{
										 var obj = next(arr,index );
										 if (obj!=null)
										 	if (!obj.focus);
										 	else
										 	{			
										 		f.NOMBREOBJETO.value=obj.attr('id');	
										 		obj.select();									 								 		
										 		obj.focus();										 		
										 	}										 
										 else										 
										 {										   
										   f.NOMBREOBJETO.value=arr[0].attr('id');		
										   arr[0].select();									   
										   arr[0].focus();										  	
									  	 }					  				 
									    }
									}   
								}catch(ex){}
					  	  }//if (event.keyCode==variable1 || event.keyCode==variable2)
					  }); //jQuery(this).bind("keydown", function(event) 					  
				});	//jQuery(':*[tabindex]').each( function () {	
				
				jQuery("#CTxtFechaDesde").bind($.browser.opera ? "keypress" : "keydown" , function(event){
					if (event.keyCode==Sigma.Const.Key.ENTER || event.keyCode==Sigma.Const.Key.RIGHT || event.keyCode==Sigma.Const.Key.DOWN || event.keyCode==Sigma.Const.Key.TAB ) 
					{
						ValidarFechaDesde(f,true);
						event.preventDefault();
					}
				});
				jQuery("#CTxtFechaHasta").bind($.browser.opera ? "keypress" : "keydown" , function(event){
					if (event.keyCode==Sigma.Const.Key.ENTER || event.keyCode==Sigma.Const.Key.RIGHT || event.keyCode==Sigma.Const.Key.DOWN || event.keyCode==Sigma.Const.Key.TAB ) 
					{
						ValidarFechaHasta(f,true);
						event.preventDefault();
					}
				});	
		    }); // $(document).ready(function () {
		 </script>
		 		
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<FORM METHOD="POST" NAME="FRMCONSULTACOS">		
<INPUT TYPE="HIDDEN" NAME="HDDENTRADA" VALUE="<%=IdEntrada%>">
<INPUT TYPE="HIDDEN" id="NOMBREOBJETO"  NAME="NOMBREOBJETO" VALUE="">

<table width="994px" border="0" cellspacing="0" cellpadding="0" class="tablaprincipal">
  <tr>
    <td valign="top">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
   		<tr><td align="CENTER" class="titulo">RESUMEN COSTAS PROCESALES</td></tr>
        <tr>
          <td align="center">
        	<DIV id=popCal 
			style="BORDER-RIGHT: 1px solid #999999; BORDER-TOP: 1px solid #999999; Z-INDEX: 100; VISIBILITY: hidden; BORDER-LEFT: 1px solid #999999; WIDTH: 10px; BORDER-BOTTOM: 1px solid #999999; POSITION: absolute" 
			onclick=event.cancelBubble=true>
			<IFRAME name=popFrame src="Calendario.htm" frameBorder=0 width=163 scrolling=no height=171></IFRAME>
			</DIV>
        	<SCRIPT event=onclick() for=document>popCal.style.visibility = "hidden";</SCRIPT>

            <div class="tablainterior2">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablagris" align="center"> 				             
              
      
              <tr>
		            <td width="15%" class="labelUno">Fecha Digitación</td>
		            <td width="5%" class="labelUno">Desde</td>		            
		            <td width="5%" class="labelDos"><input tabindex="1" id="CTxtFechaDesde" name="CTxtFechaDesde" maxlength="10" style="width: 75px" type="text" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('CTxtFechaDesde');" value="<%=FechaDesde%>"/>&nbsp;<img src="../images/icono_calendario3.gif" border="0" alt="Fecha Inicio" onclick="popFrame.fPopCalendar(CTxtFechaDesde,CTxtFechaDesde,popCal);return false" ></td>		            
		            <td width="5%" class="labelUno">Hasta</td>
		            <td width="17%" class="labelDos"><input tabindex="2" id="CTxtFechaHasta" name="CTxtFechaHasta" maxlength="10" style="width: 75px" type="text" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('CTxtFechaHasta');" value="<%=FechaHasta%>"/>&nbsp;<img src="../images/icono_calendario3.gif" border="0" alt="Fecha Término" onclick="popFrame.fPopCalendar(CTxtFechaHasta,CTxtFechaHasta,popCal);return false" ></td>
		            <td width="12%" class="labelUno">Sucursal</td>
		            <td width="45%" class="labelDos">
		            	<SELECT tabindex="3" NAME="CSUCURSAL" id="CSUCURSAL" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('');" class="select1"></SELECT>
							<SCRIPT LANGUAGE="JavaScript">
								valJS.LlenarLista(arrSucursal,window.document.FRMCONSULTACOS.CSUCURSAL,"S","<%=CodSucursal%>","Todos..."); 
						</SCRIPT>
		            	
		            </td>	                  
			   </tr>			  
			</table>
			</div>
			<br />
			<div class="tablabotones">
			<table width="262px" border="0" cellspacing="0" cellpadding="0" class="tablagris">
			  <tr>
			  		<td width="124px"></td>
		            <td width="46px"><a tabindex="4" href="#" onClick="JavaScript:Limpiar(window.document.FRMCONSULTACOS);" id="rolloverlimpiar" title="Limpiar Datos"><span>limpiar</span></a></td>
			        <td width="46px"><a tabindex="5" href="#" onClick="JavaScript:ImprimirInforme();" id="rolloverimprimir" title="Imprimir Informe"><span>imprimir</span></a></td>
			        <td width="46px" align="left"><a tabindex="6" href="#" onClick="JavaScript:ConsultarCostas(window.document.FRMCONSULTACOS);" id="rolloverbuscar" title="Buscar"><span>buscar</span></a></td>
			  </tr> 	  
			  
	<tr>
	
	 </tr> 
	 
			</table>
			</div>    
			<br/>
			<div class="tablainterior950">
			<table width="100%" border=0 cellpadding="0" cellspacing="0">
			<tr>
		           	  <td class="BORDEAZUL"><div style="width:150px">Código Abogado</div></td>
		              <td class="BORDEAZUL"><div style="width:200px"">Nombre Abogado</div></td>
		              <td class="BORDEAZUL"><div style="width:200px">Sucursal Abogado</div></td>
		              <td class="BORDEAZUL"><div style="width:120px">Monto</div></td>
		              </tr>				      
				   		<%	
				   		for(int i=0; i<resEncCosta.size(); i++) 
				   		{
							resAux = new ArrayList();
             				resAux = (ArrayList)resEncCosta.get(i);
             			%>             			  			
             			<tr>
	             			<td class="clsNormalString"><%=resAux.get(0).toString().trim()%></td>
			              	<td class="clsNormalString"><%=resAux.get(1).toString().trim()%></td>
			              	<%if (resAux.get(2).toString().trim().equals("TOTAL")) 
		            		{%>
		            			<td class="clsNormal"><b><%=resAux.get(2).toString().trim()%></td>
		            			<td class="clsNormal"><b><%=df.format(Double.parseDouble(resAux.get(3).toString().trim()))%></td>
		            		<%}
		          			else {%>
		          				<td class="clsNormalString"><%=resAux.get(2).toString().trim()%></td>
		          				<td class="clsNormal"><%=df.format(Double.parseDouble(resAux.get(3).toString().trim()))%></td>
		          			<%}%>	
			            </tr>	
			        <%}%>
			</table>
			</div>
			</td>
		</tr>
		</table>
	</td>
  </tr>
</table>
<div style="display">	    
	<iframe name="printInforme" src="black.html" style="width:0px;height:0px;" frameborder="0" width="0" height="0" align="left"></iframe>    
</div>  
</FORM>
</table>
<SCRIPT LANGUAGE="JavaScript">		
    var f=document.FRMCONSULTACOS;	
    valJS.MaskInput(f.CTxtFechaDesde,'09/99/9999' );
	valJS.MaskInput(f.CTxtFechaHasta,'09/99/9999' );	
</SCRIPT>	
</BODY>
</HTML>