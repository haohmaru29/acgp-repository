<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script type="text/javascript" src="view/usuarios/js/add.js"></script>

<form action="admin/save" id="addForm" method="post">
	<table>
		<tr>
			<td><spring:message code="message.usuario.rut"></spring:message>:</td>
			<td><input type="text" name="rutUsuario" id="rutUsuario"></td>
		</tr>
		<tr>
			<td><spring:message code="message.usuario.nombre"></spring:message>:</td>
			<td><input type="text" name="nombreUsuario" id="nombreUsuario"></td>
		</tr>
		<tr>
			<td><spring:message code="message.usuario.correo"></spring:message>:</td>
			<td><input type="text" name="correoUsuario" id="correoUsuario"></td>
		</tr>
		<tr>
			<td><spring:message code="message.usuario.clave"></spring:message>:</td>
			<td><input type="password" name="claveUsuario" id="claveUsuario"></td>
		</tr>
		<tr>
			<td><spring:message code="message.usuario.perfil"></spring:message>:</td>
			<td>
				<select id="perfil" name="perfil">
					<option value="">Seleccione Opcion</option>
					<option value="1">ADMINISTRADOR</option>
					<option value="2">NORMAL</option>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="<spring:message code="message.usuario.submit"></spring:message>"></td>
		</tr>
	</table>
	<input type="hidden" name="mngr" value="Usuario">
</form>