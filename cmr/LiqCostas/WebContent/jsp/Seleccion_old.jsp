<%@ page language='java' contentType="text/html"%>
<%@page import='java.util.ArrayList'%>
<%
    //String sTipo;
    //String sDescripcion;
    String sJuicio;
	//ArrayList arrRes=new ArrayList();
	//boolean ret;
	//Proc.General objGeneral = new Proc.General(); 
	sJuicio=request.getParameter("txtJuicio")!=null?request.getParameter("txtJuicio").toString().trim():"";
	//sTipo = request.getParameter("txtTipo")!=null?request.getParameter("txtTipo").toString().trim():"";
	//sDescripcion = request.getParameter("txtDescripcion")!=null?request.getParameter("txtDescripcion").toString().trim():"";
	//ret = objGeneral.obtieneListaTipoCargo(sJuicio,sTipo, sDescripcion, arrRes);
	//String arrDato[];
	//for(int i=0; i<arrRes.size(); i++) {
	//		   	ArrayList arrRow = (ArrayList)arrRes.get(i);
	//		   	String sValor = arrRow.get(0).toString();	
	//		   	arrDato=sValor.split(";");
	//	   		String sColTipo=arrDato[0];  		
	//		   	String sColDesc = arrDato[2];  
	//}	
	
	//out.println("sJuicio=" + sJuicio);	   	
 %>
<!DOCTYPE html>
<html>
<head>
<base target="_self" />
<META HTTP-EQUIV="Cache-Control" content="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-store">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Expires" CONTENT="0">

<title> Selecci&oacute;n Tipo de Cargo</title>
<LINK REL="stylesheet" TYPE="text/css" HREF="../style/seleccion.css">
<script type="text/javascript" language="javascript" src="../js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="../grid/gt_const.js"></script>
<script type="text/javascript">

jQuery(document).ready(function () {

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
					alert("entro");
					if (jQuery(this).attr('type')!='hidden'  && jQuery(this).attr('type') !="radio" && jQuery(this).attr('type') !="form")
					if ('select-one,button, text, checkbox'.indexOf(jQuery(this).attr('type'))>=0  || jQuery(this).get(0).nodeName.toUpperCase()=="A") 
					   /*if (jQuery(this).attr('id').substring(0,5)=="rollo" )
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
						}*/
						var variable1=Sigma.Const.Key.LEFT; 
						var variable2=Sigma.Const.Key.RIGHT;    	
						var variable3=Sigma.Const.Key.ENTER;  
						     	
				  	  jQuery(this).bind("keydown", function(event) 
					  {			
					  		alert("entro keydown");
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
								 		  	 alert("this=" + this)
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
										 		//f.NOMBREOBJETO.value=obj.attr('id');	
										 		obj.select();
										 		obj.focus();											 				
										 	}
										 else
										 {										
										 	//f.NOMBREOBJETO.value=arr[0].attr('id');
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
										 		//f.NOMBREOBJETO.value=obj.attr('id');	
										 		obj.select();									 								 		
										 		obj.focus();										 		
										 	}										 
										 else										 
										 {										   
										   //f.NOMBREOBJETO.value=arr[0].attr('id');		
										   arr[0].select();									   
										   arr[0].focus();										  	
									  	 }					  				 
									    }
									}   
								}catch(ex){}
					  	  }//if (event.keyCode==variable1 || event.keyCode==variable2)
					  }); //jQuery(this).bind("keydown", function(event) 					  
				});	//jQuery(':*[tabindex]').each( function () {		
						
							
		function buscar() {
		try {
			datastring="JUICIO=" + document.frm.txtJuicio.value;
			//alert(datastring);			
			jQuery.ajax({ 
					timeout: 10000,
					dataType: 'JSON',					
		            url: "seleccionAJAX.jsp",
		            data: datastring,
		            beforeSend:function(xhr){}, 
		            success:function(data){	    
		              try {      
		              		
		            	    var obj = jQuery.parseJSON(data);
		            	    var objTableContainer = document.getElementById("tbContainer");
		            	    //alert(obj);
		            	    if (obj.length>0) {
			            		
			            		objTableContainer.innerHTML=""; 				            	
								var tab1 =document.createElement('table');
				            	for (i=0;i<obj.length;i++) {
									tr1=tab1.insertRow(-1);
									td1=tr1.insertCell(0);
									td1.style.width="100px";								
									var s= "<div >";
									s+="<a " + " tabindex='" + i+1 + "' id='Refer' name='Refer' href='#'"
									//s+=" onclick='javascript:aceptar(\"" + obj[i].tipo + "|" + obj[i].subtipo + "\");'>" + obj[i].tipo.substring(3)  +  "</a>"
									//s+=" onclick='javascript:aceptar(\"" + obj[i].tipo + "|" + obj[i].subtipo + "\");'>" + obj[i].tipo  +  "</a>"
									s+=" onclick='javascript:aceptar(\"" + obj[i].tipo + "|" + obj[i].desc + "|" + obj[i].juicio + "\");'>" + obj[i].tipo  +  "</a>"
									s+="</div>"	
									//alert(s);						
									td1.innerHTML = s;
									td2=tr1.insertCell(1);									
									td2.innerHTML="<td><div>" + obj[i].desc + "</div></td>";
				            	}
				            	objTableContainer.appendChild(tab1);
			            	} else {
			            		objTableContainer.innerHTML="";
			            	}
			           } catch(ex) {
			           		objTableContainer.innerHTML="";
			           }			
		            }, 
		            error:function (xhr, ajaxOptions, thrownError){},
               		cache:false,
               		complete:function(xhr, status){
               		}
		        });
		} catch(ex) {}
		
		}
		
		buscar();
		//oSelect = document.getElementById("Refer");
		//oItems = oSelect.getElementsByTagName("Refer"); 
		//oItems.focus();	
		
		//alert(document.getElementById("Refer").parentNode);		
		//alert(document.getElementById("tbContainer"));
		
		//alert(document.frm.elements.length);
		//alert(document.frm.elements.item(2));
		//document.getElementById("tbContainer").getElementsByTagName("Refer1").focus();
		//alert(document.getElementById("tbContainer").getElementsByTagName("Refer1"));
		//alert(document.getElementById('tbContainer').getElementsByTagName('Refer1').focus()); //('Refer')[0].nextSibling);
		
});


function aceptar(valor) {
	window.returnValue = valor;
	window.close();
}

function cerrar() {
	window.close();
}


function buscar() {
document.frm.target="self";
  document.frm.submit();
}



</script>
</head>
<body onhelp="return false;">	
		 
<div id="result"></div>
	<div id="simplemodal-container">
	<div id="simplemodal-data">
	<FORM name="frm" id="frm" ACTION="Seleccion.jsp" method="post" target="_self">
		<table width="100%" height="100%" border=0 valign="top" cellspacing="0" class="principal">
		 <tr><td class="TITULO" height="40px">&nbsp;&nbsp;&nbsp;TIPO DE CARGO</td></tr>
		<tr>
			<td>
			
			<input type="HIDDEN" id="txtJuicio" name="txtJuicio" value="<%=sJuicio%>">
			
	 	 </td>
	  </tr>
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
		     </div>
		  </div>
		 </td>
		</tr>
		<tr>
			<td valign="bottom">
			<table class="botones">
				<tr>
					<td>
						<input type="button" name="btnCerrar" id="btnCerrar" value="Cerrar" onclick="javascript:cerrar();">
				  </td>				
			</tr>				
			</table>
		 </td>
		</tr>
	</table> 
	</FORM>
		</div>
</div>
</body>
</html>