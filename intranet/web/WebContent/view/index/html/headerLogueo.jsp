<%@page import="cl.intranet.domain.Usuario"%>
<script type="text/javascript" src="view/index/js/ddsmoothmenu.js"></script>
<script type="text/javascript" src="view/index/js/login.js"></script>

<%
	Usuario usuario = (Usuario)request.getSession(false).getAttribute("usuario");
%>

<script type="text/javascript">
ddsmoothmenu.init({
	mainmenuid: "smoothmenu1", //menu DIV id
	orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
	classname: 'ddsmoothmenu', //class added to menu's outer DIV
	//customtheme: ["#1c5a80", "#18374a"],
	contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
});

ddsmoothmenu.init({
	mainmenuid: "smoothmenu2", //Menu DIV id
	orientation: 'v', //Horizontal or vertical menu: Set to "h" or "v"
	classname: 'ddsmoothmenu-v', //class added to menu's outer DIV
	//customtheme: ["#804000", "#482400"],
	contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
});

</script>
<div class="examples">
	<div id="cuerpo">
			<div id="header">
				<!-- Login Starts Here -->
				<div class="container">
				<div id="loginContainer">		
				<div class="bienvenida">
					Bienvenido(a): 
					<span><%=usuario.getNombreUsuario()%></span>
				</div>
				<a href="#" id="loginButton" onclick="System.sessionClose();"><span>Salir</span><em></em></a>
				<div style="clear:both"></div>
						<div id="loginBox">  
						</div>
				</div>
				</div>   	
					<!-- Login Ends Here -->
					<div class="logo"><a href="index.html"></a></div>
			</div>
	</div>
</div>