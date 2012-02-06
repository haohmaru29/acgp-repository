<%@ page language='java' contentType="text/html"%>
<%@page import='java.util.ArrayList'%>
<%@page import='Proc.General'%>
<%
    String sJuicio;
    String sValor="";   
    String sTecla="";
    String arrDato[];
	ArrayList arrRes=new ArrayList();
	boolean ret;
	General objGeneral = General.getInstance(); 
	sJuicio=request.getParameter("txtJuicio")!=null?request.getParameter("txtJuicio").toString().trim():"";
	
	sTecla=request.getParameter("TECLA")!=null?request.getParameter("TECLA").toString().trim():"";
	ret = objGeneral.obtieneListaTipoCargo(sJuicio,"0", "0", arrRes);
		
 %>
<!DOCTYPE html>
<html>
<head>
<base target="_self" />
<META HTTP-EQUIV="Cache-Control" content="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-store">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Expires" CONTENT="0">
<%@page import='java.awt.Toolkit'%>
<%@page import='java.awt.event.KeyEvent'%>
<title> Selecci&oacute;n Tipo de Cargo</title>
<LINK REL="stylesheet" TYPE="text/css" HREF="../style/seleccion.css">
<script type="text/javascript" language="javascript" src="../js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="../grid/gt_const.js"></script>
<script type="text/javascript">
jQuery(document).ready(function () {
	
	$('.onfocusA').focus(function(){
		$($(this).parent().parent()).css('background-color', '#88AFD6');
	}).blur(function(){ 
		$($(this).parent().parent()).css('background-color', '#f5f5f5');
	});
	
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
		  jQuery(this).bind("keydown", function(event) 
		  {		
		  	  //alert("keydown=" + event.keyCode);		
		      if (event.keyCode===Sigma.Const.Key.UP || event.keyCode===Sigma.Const.Key.DOWN)
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
							   	{
							   		//alert(jQuery(this).attr('id'));
							   		arr[arr.length]=jQuery(this);										   
							   	}
							   }
							}
					});	
					
					try {
						if (event.keyCode==Sigma.Const.Key.UP)
						{
							 var obj = prev(arr,index );										 
							 if (obj!=null)
							 	if (!obj.focus);
							 	else
							 	{		
							 		obj.select();
							 		obj.focus();											 				
							 	}
							 else
							 {										
							 	arr[0].select();		 	
							 	arr[0].focus();										 				
							 }					  				 
						}									
						else
						{ 
							if (event.keyCode==Sigma.Const.Key.DOWN)
							{
							 var obj = next(arr,index );
							 if (obj!=null)
							 	if (!obj.focus);
							 	else
							 	{			
							 		obj.select();									 								 		
							 		obj.focus();										 		
							 	}										 
							 else										 
							 {										   
							   arr[0].select();									   
							   arr[0].focus();										  	
						  	 }					  				 
						    }
						}   
					}catch(ex){}
		  	  }//if (event.keyCode==variable1 || event.keyCode==variable2)
		  }); //jQuery(this).bind("keydown", function(event) 					  
	});	//jQuery(':*[tabindex]').each( function () {		
});

document.onkeydown = function()
{ 
  	if (window.event.keyCode == Sigma.Const.Key.NUMLOCK)
   	{
   		if (prueba.value=="INACTIVO")
   			prueba.value="ACTIVO";
   		else
   			prueba.value="INACTIVO";	
   		
   		if (window.event){
			if (window.event.stopPropagation){
				window.event.stopPropagation();
				window.event.preventDefault();
			} else {
				window.event.cancelBubble = true;
				window.event.returnValue = false;
			}
		}
   	}
}

function Salir() {
	if (window.returnValue==null || window.returnValue=='undefined')
		window.returnValue ='0|0|' + prueba.value;
	
	window.close();
}

function aceptar(valor) {
	window.returnValue = valor + '|' + prueba.value;	
	window.close();
}
function cerrar() {
	window.returnValue ='0|0|' + prueba.value;
	window.close();
}
</script>
</head>
<body  onhelp="return false;" onunload="javascript:Salir()">
<INPUT TYPE="HIDDEN" id="prueba"  NAME="prueba" VALUE="INACTIVO">		 
<div id="result"></div>
	<div id="simplemodal-container">
	<div id="simplemodal-data">
	<table width="100%" height="100%" border=0 valign="top" cellspacing="0" class="principal">
	 <tr><td class="TITULO" height="40px">&nbsp;&nbsp;&nbsp; TIPO DE CARGO</td></tr>
	  <tr>	  	
	  	<td>			
			<div class="grilla">
			<table class="cabecera" cellspacing="0" width="100%">
				<tr>
						<th width="100px" align="left" >Tipo</th>						
						<th align="left">Descripción</th>
			  	</tr>
			 </table>
			   	
			  	<div class="detalle" id="tbContainer">
		     	<table cellspacing="0" width="100%">
			  	<% 
			  	 	int i=0;
			  		String ValorId="";
					for(i=0; i<arrRes.size(); i++) 
				 	{
				 	   	ArrayList arrRow = (ArrayList)arrRes.get(i);
					   	sValor = arrRow.get(0).toString();
					   	arrDato=sValor.split(";");	
					   	ValorId="Refer" + (i+1);
					   	//Strcadena=Strcadena + arrDato[0] + ";" + arrDato[2] + "|";  	
					   	%>
					  	 	<tr class="csstest" >
					  	 		<td width="100px" align="left" >
									<a tabindex='<%=i+1%>' id='<%=ValorId%>' href="#" onClick="JavaScript:aceptar('<%=arrDato[0]%>' + '|' + '<%=arrDato[2]%>' );" class="onfocusA"><span><%=arrDato[0]%></span></a>
								</td>						
								<td align="left" ><%=arrDato[2]%></td>
			  				</tr>
					<%}		  	
			  	%>
			  	</table>
			  	</div>	
		  </div>
		 </td>
		</tr>
		<tr>
			<td valign="bottom">
			<table class="botones">
				<tr>
					<td>						
						<input type="button" tabindex='<%=i+1%>' name="btncerrar" id="btncerrar" value="Cerrar" onclick="javascript:cerrar();">
				  </td>				
			</tr>				
			</table>
		 </td>
		</tr>
	</table> 
	</div>
</div>
</body>
</html>