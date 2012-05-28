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
</body>
</html>