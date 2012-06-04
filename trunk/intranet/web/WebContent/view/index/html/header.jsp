<script type="text/javascript" src="view/index/js/ddsmoothmenu.js"></script>
<script type="text/javascript" src="view/index/js/login.js"></script>

<script type="text/javascript">
function logueo() {
	var nickname = $('#nickname').val();
	var clave = $('#clave').val();
	$.ajax({
	  url: 'security/login',
	  type: 'post',
	  data: {nickname: nickname, clave: clave},
	  success: function(data) {
		if(data == 1) {
	    	location.href='index.jsp';
	    } else if(data==2) {
	    	System.info('Usuario no esta activo, favor contacto al administrador.');
	    }else {
	    	System.info(data);
	    }
	  }
	});
}
</script>

<div class="body">
	<div id="header">
			<div class="container">
			<div id="loginContainer">
			<div class="bienvenida">
				<div id="text_welcome"></div>
				<span id="span_login"></span>
			</div>
			<a href="#" id="loginButton">
				<span>Login</span><em></em></a>
			<div style="clear:both"></div>
					<div id="loginBox">                
						<form id="loginForm" action="security/login" method="post">
						<fieldset id="body">
						<fieldset>
						<label for="email">Nickname</label>
						<input type="text" name="nickname" id="nickname" />
						</fieldset>
						<fieldset>
						<label for="password">Contrase&ntilde;a</label>
						<input type="password" name="clave" id="clave" />
						</fieldset>
						<input type="button" onclick="logueo();" id="login" value="Ingresar" />
						<label for="checkbox"><input type="checkbox" id="checkbox" />Recordar</label>
						</fieldset>
						<span><!--<a href="#">¿Olvido su contraseña?</a>--></span>
						<span><!--<a href="admin/registro.php" id="registrar-b">Registrar</a>--></span>
						</form>
					</div>
			</div>
			</div>   	
			<div class="logo"><a href="index.jsp"></a></div>
	</div>
</div>



<!--
<div id="header">
	<div class="container">
	<div id="loginContainer">
	<a href="#" id="loginButton"><span>Login</span><em></em></a>
	<div style="clear:both"></div>
			<div id="loginBox">                
				<form id="loginForm" action="security/login" method="post">
				<fieldset id="body">
				<fieldset>
				<label >NickName</label>
				<input type="text" name="nickname" id="nickname" />
				</fieldset>
				<fieldset>
				<label for="password">Clave</label>
				<input type="password" name="clave" id="clave" />
				</fieldset>
				<input type="button" onclick="logueo();" id="login" value="Ingresar" />
				<label for="checkbox"><input type="checkbox" id="checkbox" />Recordar</label>
				</fieldset>
				<span><a href="#">Olvid&oacute; su contrase&ntilde;a?</a></span>
				</form>
			</div>
	</div>
	</div>   	
		<div class="logo"><a href="index.jsp"></a></div>
</div>
-->