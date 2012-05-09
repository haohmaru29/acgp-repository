<%@ page language='java' session="false" contentType="text/html" errorPage="error.jsp?ERR=ERR.GENERAL.PAGE"%>
<%@ include file="../Menu/principalsinmenu.jsp"%>
<%@ page import='java.util.ArrayList'%>
<%@page import="cl.cmr.Seguridad.ValidaUsuario"%>
<%@page import="cl.cmr.com.Errores"%>
<%
	String codEje    = request.getParameter("CODEJE")!=null?request.getParameter("CODEJE").trim():"";
	String msgErr    = "";
	ArrayList arrErr = new ArrayList();

	String StrUsuario = request.getParameter("USUARIO")!=null?request.getParameter("USUARIO").trim():"";
	
	
	//out.println("eje=" + codEje);
	if (!codEje.equals("")){
		ValidaUsuario objVar = new ValidaUsuario();
		int rev = 0;

		rev = objVar.validaUsuario(request, arrErr);
		
				
		if (rev!=0)
		{
			if (rev==-1)
				msgErr = Errores.getDescription("ERR.USUARIOS.INFO");
			if (rev==-2)
			{
				StrUsuario="";
				msgErr = Errores.getDescription("ERR.LOGIN.USUARIO");
			}
			if (rev==-3)
				msgErr = Errores.getDescription("ERR.LOGIN.CLAVE");	
			if (rev==-4)
				msgErr = Errores.getDescription("ERR.GENERAL.BASE");	
			if (rev==-5)
				msgErr = Errores.getDescription("ERR.USUARIOS.ACCE");			
				
		} else {
			if (arrErr.size() == 0) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/welcome.jsp");
				dispatcher.forward(request,response);
				return;
			} else {
				msgErr = String.valueOf(arrErr.get(0));
			}
		}
	}
%>
	
	<SCRIPT type="text/javascript">
		
			function validaUsuario(flag){
				
				if (flag==0)
				{
					if (validaFormulario()){
						window.document.FRMLOGIN.action = "login.jsp";
						window.document.FRMLOGIN.target = "_self";
						window.document.FRMLOGIN.submit();
					}
				}
				else
				{
					if (window.event.keyCode==13)
					{
						if (validaFormulario()){
						window.document.FRMLOGIN.action = "login.jsp";
						window.document.FRMLOGIN.target = "_self";
						window.document.FRMLOGIN.submit();
					}
					}
				}	
			}

			function validaFormulario(){
				if (valJS.Trim(window.document.FRMLOGIN.USUARIO.value) == ""){
					alert("Debe ingresar el nombre de usuario   ");
					window.document.FRMLOGIN.USUARIO.focus();
					return false;
				}
				if (valJS.Trim(window.document.FRMLOGIN.CLAVE.value) == ""){
					alert("Debe ingresar la clave   ");
					window.document.FRMLOGIN.CLAVE.focus();
					return false;
				}
							
				return true;
			}
			
			function InicioPag() 
	 		{	
	 			var Strusu='<%=StrUsuario%>';
	 			if (Strusu=="")
	 				document.FRMLOGIN.USUARIO.focus();
	 			else
	 				document.FRMLOGIN.CLAVE.focus();	
	 		}
	 		
	 		document.onkeydown = function()
			{ 
				if (window.event)
				{
					switch (window.event.keyCode){
						case 27: 
						case 9: 
						case 112: 
						case 144: 
						case 113:
						case 106:  
						case 114: 
						case 115:
						case 116:
						case 117:
						case 118:
						case 119:
						case 120:
						case 121:
						case 122:
						case 123:
							window.event.keyCode = 505; 
							break;
						case 8:	
							if (document.FRMLOGIN.NOMBREOBJETO.value=="")
									window.event.keyCode = 505; 
							break;
						}
					if(window.event && window.event.keyCode == 505) 
			     	{ 
			     		return false;    
			     	}
				}
			   } 	
			   function SetearObjeto(valor)
			   {
		  		document.FRMLOGIN.NOMBREOBJETO.value=valor;
		  		return false;
	    	   }
		</SCRIPT>
	
	<body leftmargin="0" topmargin="0" onload="javascript:InicioPag()" onhelp="return false;">
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
					//alert(jQuery(this).attr('type'));
					if (jQuery(this).attr('type')!='hidden'  && jQuery(this).attr('type') !="radio" && jQuery(this).attr('type') !="form")
					if ('select-one,button, password , text, checkbox'.indexOf(jQuery(this).attr('type'))>=0  || jQuery(this).get(0).nodeName.toUpperCase()=="A") 
					  if (jQuery(this).attr('id')=="Ingresar" || jQuery(this).attr('id')=="CLAVE")
				  	   {
				  	   	   var variable1=37; 
						   var variable2=39;    	
						   var variable3=39; 
						   var variable4=9; 
				  	   }
				  	   else
				  	   {
					  	   var variable1=37; 
						   var variable2=39;    	
						   var variable3=13; 
						   var variable4=9;  
						}	
					  jQuery(this).bind("keydown", function(event) 
					  {			
					      if (event.keyCode==variable1 || event.keyCode==variable2 || event.keyCode==variable3 || event.keyCode==variable4)
					      {
					      	  	event.preventDefault();
								var arr = [];								
								var index = jQuery(this).attr("tabindex");								
								if (!index) return false;
								jQuery(':*[tabindex]').each( function () {
										
								 		if (jQuery(this).attr('id')!=null && jQuery(this).attr('tabindex')!=null && jQuery(this).attr('type')!='hidden' && jQuery(this).attr("disabled")==false)
								 		{							 		
								 		   if ('select-one,button, password, text, checkbox'.indexOf(jQuery(this).attr('type'))>=0 || jQuery(this).get(0).nodeName.toUpperCase()=="A" )
								 		   {
										   	if (jQuery(this).attr('id')!="")
										   		arr[arr.length]=jQuery(this);										   
										   }
										}
								});	
								
								try {
												  
									if (event.keyCode==37)
									{
										 var obj = prev(arr,index );										 
										 if (obj!=null)
										 	if (!obj.focus);
										 	else
										 	{		
										 		document.FRMLOGIN.NOMBREOBJETO.value=arr[0].attr('id');	
										 		obj.select();
										 		obj.focus();											 				
										 	}
										 else
										 {						
										 	document.FRMLOGIN.NOMBREOBJETO.value=arr[0].attr('id');					
										 	arr[0].select();		 	
										 	arr[0].focus();											 				
										 }					  				 
									}									
									else
									{ 
										if (event.keyCode==39 || event.keyCode==13 || event.keyCode==9)
										{
										 var obj = next(arr,index );
										 if (obj!=null)
										 	if (!obj.focus);
										 	else
										 	{			
										 		document.FRMLOGIN.NOMBREOBJETO.value=arr[0].attr('id');	
										 		obj.select();									 								 		
										 		obj.focus();										 		
										 	}										 
										 else										 
										 {		
										   document.FRMLOGIN.NOMBREOBJETO.value=arr[0].attr('id');											   
										   arr[0].select();									   
										   arr[0].focus();										  	
									  	 }					  				 
									    }
									}   
								}catch(ex){}
					  	  }//if (event.keyCode==variable1 || event.keyCode==variable2)
					  }); //jQuery(this).bind("keydown", function(event) 					  
				});	//jQuery(':*[tabindex]').each( function () {	
	 }); // $(document).ready(function () {
	</SCRIPT>
		<FORM METHOD="POST" NAME="FRMLOGIN">
			<INPUT TYPE="HIDDEN" NAME="CODEJE" VALUE="1">
			<INPUT TYPE="HIDDEN" id="NOMBREOBJETO"  NAME="NOMBREOBJETO" VALUE="">
			<br>
		
				<TABLE WIDTH="50%" ALIGN="CENTER" BORDER="0" CELLPADDING="0" CELLSPACING="0" class="fondotabla">
				<TR>
					<TD CLASS="clsTitleArea" COLSPAN="6" WIDTH="100%" ALIGN="LEFT">Login</TD>
				</TR>
				<TR>
					<TD COLSPAN="6" WIDTH="100%" ALIGN="CENTER">&nbsp;</TD>
				</TR>
				<TR>
					<TD COLSPAN="6" WIDTH="100%" ALIGN="CENTER">&nbsp;</TD>
				</TR>
				<TR>
					<TD WIDTH="34%">&nbsp;</TD>
					<TD COLSPAN="2" WIDTH="15%" ALIGN="right" CLASS="clsFields">Usuario</TD>
					<TD COLSPAN="3" WIDTH="51%" ALIGN="LEFT" CLASS="clsFields"><INPUT tabindex="1" TYPE="TEXT" id="USUARIO" name="USUARIO" MAXLENGTH="30" VALUE="<%=StrUsuario%>" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('USUARIO');"></TD>
				</TR>
				<TR>
					<TD WIDTH="34%">&nbsp;</TD>
					<TD COLSPAN="2" WIDTH="15%" ALIGN="right" CLASS="clsFields">Clave</TD>
					<TD COLSPAN="3" WIDTH="51%" ALIGN="LEFT" CLASS="clsFields"><INPUT tabindex="2" TYPE="PASSWORD" id="CLAVE" name="CLAVE" MAXLENGTH="10" VALUE="" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('CLAVE');" onkeypress="return validaUsuario(1)"></TD>
				</TR>
				<TR>
					<TD COLSPAN="6" WIDTH="50%" ALIGN="CENTER">&nbsp;</TD>
				</TR>
				<TR>
					<TD COLSPAN="6" WIDTH="100%" ALIGN="CENTER"><INPUT tabindex="3" TYPE="BUTTON" NAME="Ingresar" id="Ingresar" VALUE="Ingresar" ONCLICK="JavaScript:validaUsuario(0)" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('');"></TD>
				</TR>
				<TR>
					<TD COLSPAN="6" WIDTH="50%" ALIGN="CENTER">&nbsp;</TD>
				</TR>
				<TR>
					<TD COLSPAN="6" WIDTH="50%" ALIGN="CENTER" CLASS="clsMSG">
					<%=msgErr%>
					</TD>
				</TR>
				<TR>
					<TD COLSPAN="6" WIDTH="50%" ALIGN="CENTER">&nbsp;</TD>
				</TR>				
			</TABLE>
		</FORM>
	</BODY>
</HTML>	
