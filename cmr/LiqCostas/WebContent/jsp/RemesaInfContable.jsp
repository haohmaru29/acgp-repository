<%@ page language='java' contentType="text/html" errorPage="error.jsp?ERR=ERR.GENERAL.PAGE"%>
<%getServletContext().getRequestDispatcher("/jsp/acceso.jsp").include(request,response);%>
<%@page import='java.util.ArrayList'%>
<%@page import ='java.util.Calendar'%>
<%@page import='Proc.General'%>
<%@page import='Proc.Remesa'%>
<%@page import='java.text.DecimalFormat'%>
<%@page import='java.text.DecimalFormatSymbols'%>

<%  
	String iAno = new java.text.SimpleDateFormat("yyyy").format(new java.util.Date());
	//String iDia = new java.text.SimpleDateFormat("dd").format(new java.util.Date());
	String iMes = new java.text.SimpleDateFormat("MM").format(new java.util.Date());
		
	Calendar calFin = Calendar.getInstance();		
	calFin.set(Integer.parseInt(iAno), Integer.parseInt(iMes)-1, 1);
	calFin.set(Integer.parseInt(iAno), Integer.parseInt(iMes)-1, calFin.getActualMaximum(Calendar.DAY_OF_MONTH));
	String UltDia = new java.text.SimpleDateFormat("dd").format(calFin.getTime());

	//RequestDispatcher dispatcher;
	
	DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
	unusualSymbols.setDecimalSeparator(',');
	unusualSymbols.setGroupingSeparator('.');
		
	DecimalFormat df = new DecimalFormat("###,###,###,###,###,###,##0;(###,###,###,###,###,###,##0)",unusualSymbols);	
	
	String sMensaje="";

	String FechaDesde = request.getParameter("CTxtFechaDesde")!=null?request.getParameter("CTxtFechaDesde").trim():"";
	String FechaHasta = request.getParameter("CTxtFechaHasta")!=null?request.getParameter("CTxtFechaHasta").trim():"";
	String CodAbogado = request.getParameter("CABOGADO")!=null?request.getParameter("CABOGADO").trim():"";
	String NumRemesa = request.getParameter("CNumRemesa")!=null?request.getParameter("CNumRemesa").trim():"0";
	String hddNumRemesa =request.getParameter("HDDNumRemesa")!=null?request.getParameter("HDDNumRemesa").trim():"0";
	String Boleta = request.getParameter("TxtBoleta")!=null?request.getParameter("TxtBoleta").trim():"";
	String MayorA = request.getParameter("TxtMayorA")!=null?request.getParameter("TxtMayorA").trim():"0";
	String MenorA = request.getParameter("TxtMenorA")!=null?request.getParameter("TxtMenorA").trim():"0";
	
	//out.println("CodAbogado=" + CodAbogado);	
		
	if (FechaDesde.equals(""))
	{
		FechaDesde="01/" + iMes + "/" + iAno;
		FechaHasta=UltDia + "/" + iMes + "/" + iAno;
	}
	
		
	General objGeneral = General.getInstance();
	Remesa objRemesa = Remesa.getInstance();

	ArrayList resAbogado = new ArrayList();
	ArrayList resAux = new ArrayList();
	ArrayList resInfLiq = new ArrayList();
				
	boolean retD =false ;
	retD = objGeneral.ObtenerListaAbogados(1,resAbogado);
	
	if (FechaDesde.equals("")==false)
	{
		String FechaIni=FechaDesde.substring(6,10) + FechaDesde.substring(3,5) + FechaDesde.substring(0,2);
		String FechaFin=FechaHasta.substring(6,10) + FechaHasta.substring(3,5) + FechaHasta.substring(0,2);
		
		int Iret = objRemesa.ConsultarInformeContable(NumRemesa,Integer.parseInt(FechaIni),Integer.parseInt(FechaFin),CodAbogado,Boleta,MayorA.replace(".","").replace(",",""),MenorA.replace(".","").replace(",",""),resInfLiq);
		
		if (Iret==2)
			sMensaje=com.Errores.getDescription("ERR.GENERAL.BASE");
		else
			if (resInfLiq.size()<=0)
				sMensaje=com.Errores.getDescription("ERR.INFORME");
	}		
%>

<jsp:include page="principal.jsp" flush="false"/>	
 
  <SCRIPT LANGUAGE="JavaScript">	
 
 		
	 	function InicioPag() 
	 	{	
	 	  <%if (sMensaje!="") 
			  {%> 
					alert('<%=sMensaje%>');					
			<%}%>
			document.FRMCONTABLEREM.CTxtFechaDesde.select();
	 		document.FRMCONTABLEREM.CTxtFechaDesde.focus();	 		
		}
		  		  		
		//carga de arreglos		  		
		var arrAbogado = new Array();
		<%for(int i=0; i<resAbogado.size(); i++) {
				resAux = new ArrayList();
             	resAux = (ArrayList)resAbogado.get(i);%>
             
    	        arrPaso = new Array("<%=resAux.get(0).toString().trim()%>","<%=resAux.get(0).toString().trim()%>" + '-' + "<%=resAux.get(2).toString().trim()%>");
        	    arrAbogado[<%=i%>]=arrPaso;
		<%}%>					
		//fin carga de arreglos	
			
		//funciones de validacion	
		
		function Limpiar(obj)
		{
			LimpiarCamposInforme(obj,2);
			document.FRMCONTABLEREM.action = "RemesaInfContable.jsp";
			document.FRMCONTABLEREM.target = "_self";
			document.FRMCONTABLEREM.submit();
		}
		 	
		function ConsultarInforme(obj)
		{		
			if (ValidaFechasdeBusqueda(obj))
			{
				var oMonto1 = obj.TxtMayorA.value;
    			var oMonto2 = obj.TxtMenorA.value;
  				oMonto1=parseFloat(valJS.quitaMask(oMonto1));
    			oMonto2=parseFloat(valJS.quitaMask(oMonto2));
    			
    			if (ValidarNumeroBoleta(obj,true))
    			{	
    				if (ValidarNumeroRemesa(obj,true))
    				{
    					if (ValidarMayorA(obj,true))
    					{
    						if (ValidarMenorA(obj,true))
    						{
				    			if ((oMonto1>0 && oMonto2>0) && (oMonto2<oMonto1))
				    			{
				    				alert("Honorarios Menor a debe ser mayor a Honorarios Mayor a");
				    				obj.TxtMenorA.focus();
				    			}    
				    			else
				    			{
				    				//alert("realizar submit");
				    				document.FRMCONTABLEREM.action = "RemesaInfContable.jsp";
									document.FRMCONTABLEREM.target = "_self";
									document.FRMCONTABLEREM.submit();
				    			}
				    		}	
			    		}	
		    		}	
	    		}					
			}			
 		}		
 		
 		function ImprimirInforme()
 		{ 			
 			//alert("ImprimirInforme1");
			document.FRMCONTABLEREM.action = "RemesaImprimeInfContable.jsp";
			document.FRMCONTABLEREM.target = "printInformeCont";			
			document.FRMCONTABLEREM.submit();
			//alert("ImprimirInforme2");
 		}
 		
 		function printPage(ifr) 
 		{
 			if (ifr.location.href != "about:blank")
			{
	   		 ifr.focus(); 
	   		 ifr.print();
			}
			document.FRMCONTABLEREM.CTxtFechaDesde.select();
	 		document.FRMCONTABLEREM.CTxtFechaDesde.focus();	 	
  		} 		
		//fin funciones de validacion
		
		function SetearObjeto(valor)
		{
		  	document.FRMCONTABLEREM.NOMBREOBJETO.value=valor;
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
	     
	     function ValidarNumeroBoleta(obj,confoco)
		 {
			var flag=true;
			if (obj.TxtBoleta.value!="")
			{
				//validar que sea numerico
				 var valor=valJS.quitaMask(obj.TxtBoleta.value + '');
				 if (isNaN(valor/1))
				 {
				 	f.NOMBREOBJETO.value="TxtBoleta"; 			    	
					alert("Número Boleta debe ser numérico."); 
					obj.TxtBoleta.value="";
					obj.TxtBoleta.select();	
					obj.TxtBoleta.focus();	
					flag=false;	
				 }
			}	
			return flag;
		}
		
	    function ValidarNumeroRemesa(obj,confoco)
		{
			var flag=true;
			if (obj.CNumRemesa.value!="")
			{
				//validar que sea numerico
				 var valor=valJS.quitaMask(obj.CNumRemesa.value + '');
				 if (isNaN(valor/1))
				 {
				 	f.NOMBREOBJETO.value="CNumRemesa"; 			    	
					alert("Número Remesa debe ser numérico."); 
					obj.CNumRemesa.value="";
					obj.CNumRemesa.select();	
					obj.CNumRemesa.focus();	
					flag=false;	
				 }
			}	
			return flag;
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
		
		function ValidarMayorA(obj,confoco)
		{
			var flag=true;
			if (obj.TxtMayorA.value!="")
			{
				//validar que sea numerico
				 var valor=valJS.quitaMask(obj.TxtMayorA.value + '');
				 if (isNaN(valor/1))
				 {
				 	f.NOMBREOBJETO.value="TxtMayorA"; 			    	
					alert("Honorarios mayor a debe ser numérico."); 
					obj.TxtMayorA.value="";
					obj.TxtMayorA.select();	
					obj.TxtMayorA.focus();	
					flag=false;	
				 }
			}	
			return flag;
		}
		
		function ValidarMenorA(obj,confoco)
		{
			var flag=true;
			if (obj.TxtMenorA.value!="")
			{
				//validar que sea numerico
				 var valor=valJS.quitaMask(obj.TxtMenorA.value + '');
				 if (isNaN(valor/1))
				 {
				 	f.NOMBREOBJETO.value="TxtMenorA"; 			    	
					alert("Honorarios menor a debe ser numérico."); 
					obj.TxtMenorA.value="";
					obj.TxtMenorA.select();	
					obj.TxtMenorA.focus();	
					flag=false;	
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
						
				jQuery("#TxtBoleta").bind($.browser.opera ? "keypress" : "keydown" , function(event){
					if (event.keyCode==Sigma.Const.Key.ENTER || event.keyCode==Sigma.Const.Key.RIGHT || event.keyCode==Sigma.Const.Key.DOWN || event.keyCode==Sigma.Const.Key.TAB ) 
					{
						ValidarNumeroBoleta(f,true);
						event.preventDefault();
					}
				});
				
				jQuery("#CNumRemesa").bind($.browser.opera ? "keypress" : "keydown" , function(event){
					if (event.keyCode==Sigma.Const.Key.ENTER || event.keyCode==Sigma.Const.Key.RIGHT || event.keyCode==Sigma.Const.Key.DOWN || event.keyCode==Sigma.Const.Key.TAB ) 
					{
						ValidarNumeroRemesa(f,true);
						event.preventDefault();
					}
				});
				
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
				
				jQuery("#TxtMayorA").bind($.browser.opera ? "keypress" : "keydown" , function(event){
					if (event.keyCode==Sigma.Const.Key.ENTER || event.keyCode==Sigma.Const.Key.RIGHT || event.keyCode==Sigma.Const.Key.DOWN || event.keyCode==Sigma.Const.Key.TAB ) 
					{
						ValidarMayorA(f,true);
						event.preventDefault();
					}
				});
				jQuery("#TxtMenorA").bind($.browser.opera ? "keypress" : "keydown" , function(event){
					if (event.keyCode==Sigma.Const.Key.ENTER || event.keyCode==Sigma.Const.Key.RIGHT || event.keyCode==Sigma.Const.Key.DOWN || event.keyCode==Sigma.Const.Key.TAB ) 
					{
						ValidarMenorA(f,true);
						event.preventDefault();
					}
				});
		    }); // $(document).ready(function () {
		 </script>		
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<FORM METHOD="POST" NAME="FRMCONTABLEREM">		
<INPUT TYPE="HIDDEN" id="NOMBREOBJETO"  NAME="NOMBREOBJETO" VALUE="">
<INPUT TYPE="HIDDEN" NAME="HDDNumRemesa" VALUE="<%=hddNumRemesa%>">

<table width="994px" border="0" cellspacing="0" cellpadding="0" class="tablaprincipal">
    <td valign="top">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr><td align="CENTER" class="titulo">CONSULTA CONTABLE DE REMESAS</td></tr>
        <tr>
        	<DIV id=popCal 
			style="BORDER-RIGHT: 1px solid #999999; BORDER-TOP: 1px solid #999999; Z-INDEX: 100; VISIBILITY: hidden; BORDER-LEFT: 1px solid #999999; WIDTH: 10px; BORDER-BOTTOM: 1px solid #999999; POSITION: absolute" 
			onclick=event.cancelBubble=true>
			<IFRAME name=popFrame src="Calendario.htm" frameBorder=0 width=163 scrolling=no height=171></IFRAME>
			</DIV>
        	<SCRIPT event=onclick() for=document>popCal.style.visibility = "hidden";</SCRIPT>
        	
          <td align="center">
            <div class="tablainterior2">
            <table width="98%" border="0" cellspacing="0" cellpadding="0" class="tablagris" align="center"> 				             
              <tr>
		            <td width="12%" class="labelUno">Fecha Digitación</td>
		            <td width="5%" class="labelUno">Desde</td>		            
		            <td width="5%" class="labelDos"><input tabindex="1" id="CTxtFechaDesde" name="CTxtFechaDesde" maxlength="10" style="width: 75px" type="text" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('CTxtFechaDesde');" value="<%=FechaDesde%>"/>&nbsp;<img src="../images/icono_calendario3.gif" border="0" alt="Fecha Inicio" onclick="popFrame.fPopCalendar(CTxtFechaDesde,CTxtFechaDesde,popCal);return false" ></td>		            
		            <td width="5%" class="labelUno">Hasta</td>
		            <td width="18%" class="labelDos"><input tabindex="2" id="CTxtFechaHasta" name="CTxtFechaHasta" maxlength="10" style="width: 75px" type="text" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('CTxtFechaHasta');" value="<%=FechaHasta%>"/>&nbsp;<img src="../images/icono_calendario3.gif" border="0" alt="Fecha Término" onclick="popFrame.fPopCalendar(CTxtFechaHasta,CTxtFechaHasta,popCal);return false" ></td>
			 		<td width="10%" class="labelUno">&nbsp;</td>	
			 		<td width="10%" class="labelUno">&nbsp;</td>	
			  </tr>
			  <tr>
			       <td width="12%" class="labelUno">Abogado</td>
		            <td width="15%" colspan="4" class="labelDos">
		            		<SELECT tabindex="3" id="CABOGADO" NAME="CABOGADO" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('');" class="select1" style="width:390px"></SELECT>
							<SCRIPT LANGUAGE="JavaScript">
								valJS.LlenarLista(arrAbogado,window.document.FRMCONTABLEREM.CABOGADO,"S","<%=CodAbogado%>","Todos..."); 
							</SCRIPT>
		            </td>				          	      
				    <td width="10%" class="labelUno">Nro Boletas</td>
				    <td width="10%" class="labelDos"><input tabindex="4" id="TxtBoleta" name="TxtBoleta" maxlength="10" style="width: 168px" type="text" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('TxtBoleta');" value="<%=Boleta%>"/></td>
			 </tr>
			 <tr>
		        <td width="12%" class="labelUno">Honorarios</td>
				<td width="5%" class="labelUno">Mayor a</td>
				<td width="5%" class="labelDos"><input tabindex="5" id="TxtMayorA" name="TxtMayorA" maxlength="10" style="width: 105px" type="text" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('TxtMayorA');" value="<%=MayorA%>"/></td>		            
				<td width="5%" class="labelUno">Menor a</td>
				<td width="18%" class="labelDos"><input tabindex="6" id="TxtMenorA" name="TxtMenorA" maxlength="10" style="width: 105px" type="text" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('TxtMenorA');" value="<%=MenorA%>"/></td>				        
				<td width="10%" class="labelUno">Nro Remesa</td>
				<td width="10%" class="labelDos"><input tabindex="7" id="CNumRemesa" name="CNumRemesa" style="width: 168px" type="text" maxlength="16" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('CNumRemesa');" value="<%=NumRemesa%>"/></td>
			  </tr>
			 </table>
			  </div>
			  <br />
				 <div class="tablabotones">
				    <table width="82px" border="0" cellspacing="0" cellpadding="0" class="tablagris">
				      <tr>
		            	<td width="36px"><a tabindex="8" href="#" onClick="JavaScript:Limpiar(window.document.FRMCONTABLEREM);" id="rolloverlimpiar" title="Limpiar Datos"><span>limpiar</span></a></td>
			        	<td width="36px"><a tabindex="9" href="#" onClick="JavaScript:ImprimirInforme();" id="rolloverimprimir" title="Imprimir Informe"><span>imprimir</span></a></td>
			        	<td width="46px" align="left"><a tabindex="10" href="#" onClick="JavaScript:ConsultarInforme(window.document.FRMCONTABLEREM);" id="rolloverbuscar" title="Buscar"><span>buscar</span></a></td>		            
				      </tr> 
				    </table>
				 </div>    
				 <br/>
				 <div class="tablainterior950">
				      	<table width="100%" id="tablaCentros" border="0" cellpadding="0" cellspacing="0">
				        <tr>
				        <td class="BORDEAZUL" width="40%"><div style="width:100px">Cod.Abogado</div></td>
				        <td class="BORDEAZUL" width="40%"><div style="width:250px">Abogado</div></td>
		              	<td class="BORDEAZUL" width="15%"><div style="width:250px">Sucursal Abogado</div></td>
		              	<td class="BORDEAZUL" width="15%"><div style="width:100px">Nro<br>Remesa</div></td>
		              	<td class="BORDEAZUL" width="15%"><div style="width:100px">Nro Boleta</div></td>
		              	<td class="BORDEAZUL" width="15%"><div style="width:100px">Monto<br>Honorarios</div></td>
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
				    </div>
				    
				  </td>
				</tr>
		  </table>
		</td>
</table>
<div style="display">	    
	<iframe name="printInformeCont" src="black.html" style="width:0px;height:0px;" frameborder="0" width="0" height="0" align="left"></iframe>    
</div>   
</FORM>
</table>
<SCRIPT LANGUAGE="JavaScript">		
	var f=document.FRMCONTABLEREM;		
 	valJS.MaskInput(f.CNumRemesa,'9999999999');	
	valJS.MaskInput(f.CTxtFechaDesde,'09/99/9999' );
	valJS.MaskInput(f.CTxtFechaHasta,'09/99/9999' );	
	valJS.MaskNumber(f.TxtMayorA);
	valJS.MaskNumber(f.TxtMenorA);
	valJS.MaskInput(f.TxtBoleta,'9999999999');		
</SCRIPT>	
</BODY>
