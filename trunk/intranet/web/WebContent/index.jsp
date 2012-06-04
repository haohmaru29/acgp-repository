<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><spring:message code="message.appName"></spring:message></title>
	<jsp:include page="public/tpl/css.jsp"></jsp:include>
	<jsp:include page="public/tpl/javascript.jsp"></jsp:include>
</head>
<body>
	
	<%
		try {
			if(request.getSession(false).getAttribute("usuario") != null) {
				%><jsp:include page="view/index/logueado.jsp"></jsp:include> <%
			} else {
				%><jsp:include page="view/index/home.jsp"></jsp:include> <% 
			}
		} catch(Exception e ) {
			%><jsp:include page="view/index/home.jsp"></jsp:include> <%
		}
	%>
		
	<div style="display:none;"  id="messageBoxs" title="Atenci&oacute;n"> 
		<br> 
		<center> 
				<table class="mensajeBox" style="overflow:hidden; "> 
					<tr> 
						<td align="center" ><img src="public/img/1337712124_info.png"></td> 
						<td id="text_BOX"><div id="contentMessage" style="height:90px; padding:5px;"></div></td> 
					</tr> 
					<tr>
						<td colspan="2" >
							<center>
								<table>
									<tr>
										<td>
											<input type="button" value="Aceptar" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"  
											onclick="$('#messageBoxs').dialog( 'close' );">
										</td>
									</tr>
								</table>
							</center> 	
						</td>
					</tr>
				</table> 
		
		</center> 
	</div>	

	<div style="display:none;" id="en_construccion">
			<style>
				.mensaje_1{width:565px; height:280px;
						 border:1px solid #31508E;
						 border-radius:4px;}
				#img_mantn_1{	background-image:url(public/img/mantencion.png);
						   	background-repeat:no-repeat;
							width:245px; height:230px;
							float:left; margin:25px 0 25px 0 ;
							}
				#mensaje_mantn_1{float:right;	font-size:14px;
							   font-family:Verdana, Arial, Helvetica, sans-serif;
							   color:#31508E; font-weight:bold;
							   text-align:center; line-height:28px;
							   width:285px; margin:90px 15px 10px 5px;
							   text-shadow: 3px 2px 3px #cccccc; }			
			</style>
			<div class="mensaje_1">
				<div id="img_mantn_1"></div>
				<div id="mensaje_mantn_1">
					Esta p&aacute;gina se encuentra en construcci&oacute;n.
					Disculpe las molestias.
				</div>
			</div>	
	
	</div>
</body>
</html>