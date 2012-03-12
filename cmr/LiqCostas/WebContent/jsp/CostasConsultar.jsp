<%@ page language='java' contentType="text/html" errorPage="error.jsp?ERR=ERR.GENERAL.PAGE"%>
<%getServletContext().getRequestDispatcher("/jsp/acceso.jsp").include(request,response);%>
<%@page import='java.util.ArrayList'%>
<%@page import ='java.util.Calendar'%>
<%@page import='Proc.General'%>
<%@page import='java.text.DecimalFormat'%>
<%@page import='java.text.DecimalFormatSymbols'%>

<%  
	//CONSULTA TARJETA O CHEQUE
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

	int TipoProducto = Integer.parseInt(request.getParameter("TIPOPROD")!=null?request.getParameter("TIPOPROD").trim():"3");
	String Origen = request.getParameter("HDDORIGEN")!=null?request.getParameter("HDDORIGEN").trim():"";
	String IdEntrada = request.getParameter("HDDENTRADA")!=null?request.getParameter("HDDENTRADA").trim():"";
	String FechaDesde = request.getParameter("CTxtFechaDesde")!=null?request.getParameter("CTxtFechaDesde").trim():"";
	String FechaHasta = request.getParameter("CTxtFechaHasta")!=null?request.getParameter("CTxtFechaHasta").trim():"";
	String NumCosta = request.getParameter("CNumCosta")!=null?request.getParameter("CNumCosta").trim():"0";
	String CodSucursal = request.getParameter("CSUCURSAL")!=null?request.getParameter("CSUCURSAL").trim():"";
	String CodAbogado = request.getParameter("CABOGADO")!=null?request.getParameter("CABOGADO").trim():"";
	String EstadoLiq = request.getParameter("CESTADO")!=null?request.getParameter("CESTADO").trim():"0";
	String hddNumCosta =request.getParameter("HDDNUMCOSTA")!=null?request.getParameter("HDDNUMCOSTA").trim():"0";
	String NumCheque = request.getParameter("CTxtNumCheque")!=null?request.getParameter("CTxtNumCheque").trim():"0";
	String TipoDocumento=request.getParameter("CTIPODOC")!=null?request.getParameter("CTIPODOC").trim():"";
	String NumDocumento=request.getParameter("CNumDocumento")!=null?request.getParameter("CNumDocumento").trim():"";
	//String FechaDesc   = request.getParameter("HDDFechaDesc")!=null?request.getParameter("HDDFechaDesc").trim():"0";
		
	if (FechaDesde.equals(""))
	{
		FechaDesde="01/" + iMes + "/" + iAno;
		FechaHasta=UltDia + "/" + iMes + "/" + iAno;
	}
	
	//out.println("TipoProducto=" + TipoProducto);
	//out.println("IdEntrada=" + IdEntrada);
	//out.println("FechaDesde=" + FechaDesde);
	//out.println("FechaHasta=" + FechaHasta);
	//out.println("NumCosta=" + NumCosta);
	//out.println("CodSucursal=" + CodSucursal);
	//out.println("CodAbogado=" + CodAbogado);
	//out.println("EstadoLiq=" + EstadoLiq);
	//out.println("TipoDocumento=" + TipoDocumento);
		
	Proc.General objGeneral = General.getInstance();

	ArrayList resAbogado = new ArrayList();
	ArrayList resSucursal = new ArrayList();
	ArrayList resEstado = new ArrayList();
	ArrayList resTipoDoc = new ArrayList();
	ArrayList resAux = new ArrayList();
			
	//obtener rango de chaeques validos
	String RangoCheqIni=objGeneral.ObtenerParametro("RCHEQUEINI");
	if (RangoCheqIni.equals(""))
		RangoCheqIni="0";
	String RangoCheqFin=objGeneral.ObtenerParametro("RCHEQUEFIN");
	if (RangoCheqFin.equals(""))
		RangoCheqFin="0";
	
	//out.println("RangoCheqIni=" + RangoCheqIni);
	//out.println("RangoCheqFin=" + RangoCheqFin);
	
					
	boolean retD =false ;
	retD = objGeneral.ObtenerListaAbogados(TipoProducto,resAbogado);
	retD = objGeneral.ObtieneElemLista("SUCURSAL","0",resSucursal);
	retD = objGeneral.ObtieneElemEstado("ESTLIQCP","01","0","0",resEstado);
	retD = objGeneral.ObtieneElemLista("TIPODOC","0",resTipoDoc);
	
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
	  		  		
		var arrAbogado = new Array();
		<%for(int i=0; i<resAbogado.size(); i++) {
				resAux = new ArrayList();
             	resAux = (ArrayList)resAbogado.get(i);%>
             
    	        arrPaso = new Array("<%=resAux.get(0).toString().trim()%>","<%=resAux.get(0).toString().trim()%>" + '-' + "<%=resAux.get(2).toString().trim()%>");
        	    arrAbogado[<%=i%>]=arrPaso;
		<%}%>					
		
		var arrEstado = new Array();
		<%for(int i=0; i<resEstado.size(); i++) {
				resAux = new ArrayList();
             	resAux = (ArrayList)resEstado.get(i);%>
             
    	        arrPaso = new Array("<%=resAux.get(0).toString().trim()%>","<%=resAux.get(1).toString().trim()%>");
        	    arrEstado[<%=i%>]=arrPaso;
		<%}%>	
		
		var arrTipoDoc= new Array();
		<%for(int i=0; i<resTipoDoc.size(); i++) {
				resAux = new ArrayList();
             	resAux = (ArrayList)resTipoDoc.get(i);%>
             
    	        arrPaso = new Array("<%=resAux.get(0).toString().trim()%>","<%=resAux.get(1).toString().trim()%>");
        	    arrTipoDoc[<%=i%>]=arrPaso;        	    	    
		<%
		}%>	
		//fin carga de arreglos	
			
		//funciones de validacion	
		
		function Limpiar(obj)
		{
			LimpiarCamposConsulta(obj,<%=TipoProducto%>);
			obj.CTxtFechaDesde.value="01/" + '<%=iMes%>' + "/" + '<%=iAno%>' ;
			obj.CTxtFechaHasta.value='<%=UltDia%>' + "/" + '<%=iMes%>' + "/" + '<%=iAno%>';
			obj.CTxtFechaDesde.select();
	 		obj.CTxtFechaDesde.focus();
			//actualizar iframe
			document.FRMCONSULTACOS.action = "CostasFormConsultar.jsp";
			document.FRMCONSULTACOS.target = "iframeGrilla";			
			document.FRMCONSULTACOS.submit();	
		}		 	
		
		function ConsultarCostas(obj)
		{		
			if (ValidaFechasdeBusqueda(obj))
			{
				if (ValidarNumeroCosta(obj,true))
				{
					if (ValidarNumeroDocumento(obj,true))
					{
						if (obj.CTxtNumCheque.value != "" && obj.CTxtNumCheque.value!="0")
						{
							if (ValidarNumeroCheque(obj,true))
							{
								//if (ValidaNumChequeConsulta(obj.CTxtNumCheque,'<%=RangoCheqIni%>','<%=RangoCheqFin%>'))
								//{	
									obj.CTxtFechaDesde.select();
				 					obj.CTxtFechaDesde.focus();
									document.FRMCONSULTACOS.action = "CostasFormConsultar.jsp";
									document.FRMCONSULTACOS.target = "iframeGrilla";						
									document.FRMCONSULTACOS.submit();	
				
								//}
							}
						}
						else
						{
							obj.CTxtFechaDesde.select();
			 				obj.CTxtFechaDesde.focus();
							document.FRMCONSULTACOS.action = "CostasFormConsultar.jsp";
							document.FRMCONSULTACOS.target = "iframeGrilla";							
							document.FRMCONSULTACOS.submit();	
						}
					}	
				}		
			}
			
 		} 		
		//fin funciones de validacion
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
	    
	    function ValidarNumeroCosta(obj,confoco)
		{
			var flag=true;
			if (obj.CNumCosta.value!="")
			{
				//validar que sea numerico
				 var valor=valJS.quitaMask(obj.CNumCosta.value + '');
				 if (isNaN(valor/1))
				 {
				 	f.NOMBREOBJETO.value="CNumCosta"; 			    	
					alert("Número de Costa debe ser numérico."); 
					obj.CNumCosta.value="";
					obj.CNumCosta.select();	
					obj.CNumCosta.focus();	
					flag=false;	
				 }
			}	
			return flag;
		}
		
		function ValidarNumeroDocumento(obj,confoco)
		{
			var flag=true;
			if (obj.CNumDocumento.value!="")
			{
				//validar que sea numerico
				 var valor=valJS.quitaMask(obj.CNumDocumento.value + '');
				 if (isNaN(valor/1))
				 {
				 	f.NOMBREOBJETO.value="CNumDocumento"; 			    	
					alert("Número de Documento debe ser numérico."); 
					obj.CNumDocumento.value="";
					obj.CNumDocumento.select();	
					obj.CNumDocumento.focus();	
					flag=false;	
				 }				 
			}	
			return flag;
		}
		
		function ValidarNumeroCheque(obj,confoco)
		{
			var flag=true;
			if (obj.CTxtNumCheque.value!="")
			{
				//validar que sea numerico
				 var valor=valJS.quitaMask(obj.CTxtNumCheque.value + '');
				 if (isNaN(valor/1))
				 {
				 	f.NOMBREOBJETO.value="CTxtNumCheque"; 			    	
					alert("Número de Cheque debe ser numérico."); 
					obj.CTxtNumCheque.value="";
					obj.CTxtNumCheque.select();	
					obj.CTxtNumCheque.focus();	
					flag=false;	
				 }
				 else
				 {
				 	if (!ValidaNumChequeConsulta(obj.CTxtNumCheque,'<%=RangoCheqIni%>','<%=RangoCheqFin%>'))
				 	{
				 		f.NOMBREOBJETO.value="CTxtNumCheque";
						flag=false;
					}	
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
		</SCRIPT>

<body onload="javascript:InicioPag()" leftmargin="0" topmargin="0" onhelp="return false;">

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
				
				jQuery("#CNumCosta").bind($.browser.opera ? "keypress" : "keydown" , function(event){
					if (event.keyCode==Sigma.Const.Key.ENTER || event.keyCode==Sigma.Const.Key.RIGHT || event.keyCode==Sigma.Const.Key.DOWN || event.keyCode==Sigma.Const.Key.TAB ) 
					{
						ValidarNumeroCosta(f,true);
						event.preventDefault();
					}
				});
				
				jQuery("#CNumDocumento").bind($.browser.opera ? "keypress" : "keydown" , function(event){
					if (event.keyCode==Sigma.Const.Key.ENTER || event.keyCode==Sigma.Const.Key.RIGHT || event.keyCode==Sigma.Const.Key.DOWN || event.keyCode==Sigma.Const.Key.TAB ) 
					{
						ValidarNumeroDocumento(f,true);
						event.preventDefault();
					}
				});
				
				jQuery("#CTxtNumCheque").bind($.browser.opera ? "keypress" : "keydown" , function(event){
					if (event.keyCode==Sigma.Const.Key.ENTER || event.keyCode==Sigma.Const.Key.RIGHT || event.keyCode==Sigma.Const.Key.DOWN || event.keyCode==Sigma.Const.Key.TAB ) 
					{
						ValidarNumeroCheque(f,true);
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
					
		    }); // $(document).ready(function () {
		 </script>
		
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<FORM METHOD="POST" NAME="FRMCONSULTACOS">		
<INPUT TYPE="HIDDEN" NAME="HDDENTRADA" VALUE="<%=IdEntrada%>">
<INPUT TYPE="HIDDEN" NAME="HDDORIGEN" VALUE="<%=Origen%>">
<INPUT TYPE="HIDDEN" NAME="HDDNUMCOSTA" VALUE="<%=hddNumCosta%>">
<INPUT TYPE="HIDDEN" NAME="TIPOPROD" VALUE="<%=TipoProducto%>">
<INPUT TYPE="HIDDEN" id="NOMBREOBJETO"  NAME="NOMBREOBJETO" VALUE="">

<table width="994px" border="0" cellspacing="0" cellpadding="0" class="tablaprincipal">
  <tr>
    <td valign="top">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
      	<%if (TipoProducto==3)
      	 {%>
        		<tr><td align="CENTER" class="titulo">CONSULTA COSTAS PROCESALES TARJETA</td></tr>
       <%}
       else
       { %>
       			<tr><td align="CENTER" class="titulo">CONSULTA COSTAS PROCESALES CHEQUES PROTESTADOS</td></tr>
       <%} %> 		
        <tr>
          <td align="center">
        	<DIV id=popCal 
			style="BORDER-RIGHT: 1px solid #999999; BORDER-TOP: 1px solid #999999; Z-INDEX: 100; VISIBILITY: hidden; BORDER-LEFT: 1px solid #999999; WIDTH: 10px; BORDER-BOTTOM: 1px solid #999999; POSITION: absolute" 
			onclick=event.cancelBubble=true>
			<IFRAME name=popFrame src="Calendario.htm" frameBorder=0 width=163 scrolling=no height=171></IFRAME>
			</DIV>
        	<SCRIPT event=onclick() for=document>popCal.style.visibility = "hidden";</SCRIPT>

            <div class="tablainterior2">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablagris">				             
              
              <tr>
		            <td width="15%" class="labelUno">Fecha Digitación</td>
		            <td width="5%" class="labelUno">Desde</td>		            
		            <td width="5%" class="labelDos" ><input tabindex="1" id="CTxtFechaDesde" name="CTxtFechaDesde" maxlength="10" style="width: 75px" type="text" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('CTxtFechaDesde');" value="<%=FechaDesde%>"/>&nbsp;<img src="../images/icono_calendario3.gif" border="0" alt="Fecha Inicio" onclick="popFrame.fPopCalendar(CTxtFechaDesde,CTxtFechaDesde,popCal);return false" ></td>		            
		            <td width="5%" class="labelUno">Hasta</td>
		            <td width="17%" class="labelDos"><input tabindex="2" id="CTxtFechaHasta" name="CTxtFechaHasta" maxlength="10" style="width: 75px" type="text" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('CTxtFechaHasta');" value="<%=FechaHasta%>"/>&nbsp;<img src="../images/icono_calendario3.gif" border="0" alt="Fecha Término" onclick="popFrame.fPopCalendar(CTxtFechaHasta,CTxtFechaHasta,popCal);return false" ></td>
		            <td width="12%" class="labelUno">Sucursal</td>
		            <td width="25%" class="labelDos">
		            	<SELECT tabindex="3" NAME="CSUCURSAL" id="CSUCURSAL" class="select1" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('');" ></SELECT>
							<SCRIPT LANGUAGE="JavaScript">
								valJS.LlenarLista(arrSucursal,window.document.FRMCONSULTACOS.CSUCURSAL,"S","<%=CodSucursal%>","Todos..."); 
						</SCRIPT>
		            	
		            </td>	                  
			   </tr>
			   <tr>  
			   		<td class="labelUno">Nro Costa</td>		            
		            <td class="labelDos" colspan="4"><input tabindex="4" id="CNumCosta" name="CNumCosta" style="width: 168px" type="text" maxlength="10" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('CNumCosta');" value="<%=NumCosta%>"/></td>
			        <td class="labelUno">Abogado</td>
		            <td class="labelDos">
		            		<SELECT tabindex="5" NAME="CABOGADO" id="CABOGADO" class="select1" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('');" ></SELECT>
							<SCRIPT LANGUAGE="JavaScript">
								valJS.LlenarLista(arrAbogado,window.document.FRMCONSULTACOS.CABOGADO,"S","<%=CodAbogado%>","Todos..."); 
							</SCRIPT>
		            </td>
		       </tr>
			   <tr>
			   		<%if (TipoProducto==3)
      	 			{%>
      	 				<td class="labelUno"></td>		            
		            	<td class="labelDos"colspan="4"><input tabindex="6" TYPE="HIDDEN" id="CTxtNumCheque" name="CTxtNumCheque" style="width: 168px" maxlength="10" type="text" value="0"/></td>
      	 			<%}
      	 			else
      	 			{ %>
		            	<td class="labelUno">Nro Cheque</td>		            
		            	<td class="labelDos" colspan="4"><input tabindex="6" id="CTxtNumCheque" name="CTxtNumCheque" style="width: 168px" maxlength="10" type="text" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('CTxtNumCheque');" value="<%=NumCheque%>"/></td>
			        <%}%>
		            	
		            <td class="labelUno">Estado</td>
		            <td class="labelDos">
		    	        <SELECT tabindex="7" NAME="CESTADO" id="CESTADO"  class="select1" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('');" ></SELECT>
						<SCRIPT LANGUAGE="JavaScript">
							valJS.LlenarLista(arrEstado,window.document.FRMCONSULTACOS.CESTADO,"S","<%=EstadoLiq%>","Todos..."); 
						</SCRIPT>
		            </td>		    	         		            
			   </tr>	
			   <tr>
		            <td class="labelUno">Tipo Documento</td>
		            <td class="labelDos" colspan="3" width="10%">
		    	       <SELECT tabindex="8" NAME="CTIPODOC" id="CTIPODOC" class="select1" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('');" ></SELECT>
							<SCRIPT LANGUAGE="JavaScript">
								valJS.LlenarLista(arrTipoDoc,window.document.FRMCONSULTACOS.CTIPODOC,"S","<%=TipoDocumento%>","Todos..."); 
						</SCRIPT>
		            
		            </td>		
		             <td width="10%"></td>		            
		            <td class="labelUno">Nro Documento</td>		            
		            <td class="labelDos"><input tabindex="9" id="CNumDocumento" name="CNumDocumento" style="width: 168px" type="text" maxlength="15" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('CNumDocumento');" value="<%=NumDocumento%>"/></td>
			           	         		            
			   </tr>				      		      
			</table>
			</div>
			<br />
			<div class="tablabotones">
			<table width="262px" border="0" cellspacing="0" cellpadding="0" class="tablagris">
			  <tr>
			  		<td width="170px"></td>
		            <td width="46px"><a tabindex="10" href="#" onClick="JavaScript:Limpiar(window.document.FRMCONSULTACOS);" NAME="rolloverlimpiar" id="rolloverlimpiar" title="Limpiar Datos"><span>limpiar</span></a></td>
			        <td width="46px" align="left"><a tabindex="11" href="#" onClick="JavaScript:ConsultarCostas(window.document.FRMCONSULTACOS);" NAME="rolloverbuscar" id="rolloverbuscar" title="Buscar"><span>buscar</span></a></td>
			  </tr> 	    
	<tr>
	
	 </tr> 	 
			</table>
			</div>    
			<br/>
		   	<IFRAME name="iframeGrilla" align="top"   width="98%" height="350px" frameborder="0" marginwidth="0" marginheight="0" >   	
			</IFRAME> 
			</td>
		</tr>
		</table>
	</td>
  </tr>
</table>
</FORM>
</table>
<SCRIPT LANGUAGE="JavaScript">	  
	document.FRMCONSULTACOS.action = "CostasFormConsultar.jsp";
	document.FRMCONSULTACOS.target = "iframeGrilla";							
	document.FRMCONSULTACOS.submit();	
					
    var f=document.FRMCONSULTACOS;	
    valJS.MaskInput(f.CTxtNumCheque,'9999999999');		
    valJS.MaskInput(f.CNumDocumento,'999999999999999');		
    valJS.MaskInput(f.CNumCosta,'9999999999');	
	valJS.MaskInput(f.CTxtFechaDesde,'09/99/9999' );
	valJS.MaskInput(f.CTxtFechaHasta,'09/99/9999' );

</SCRIPT>	
</BODY>
</HTML>