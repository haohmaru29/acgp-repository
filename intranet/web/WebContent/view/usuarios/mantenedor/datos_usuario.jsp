<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<link rel="stylesheet" type="text/css" href="public/css/datos_usuario.css">

<div id="principal_dat_us">
  		<div id="titulo_dat_us">Datos Usuario </div>
				  <div id="cuadro_dat_us">
					 <form action="" method="post" id="marco_dat_us">
					   <fieldset title="Información completa usuario">
				       <div id="registro_dat_us"> 
							<p id="campos_dat_us">Rut: </p>
							<label id="label_dat_us" title="rut del usuario"> ${usuario.rutUsuario } </label>
							</div>
						<div id="registro_dat_us">
							<p id="campos_dat_us">Nick:</p>
							<label id="label_dat_us" title="Nickname"> ${usuario.nickname } </label>
						</div>
						<div id="registro_dat_us">
							<p id="campos_dat_us">Nombre Completo:</p>
							<label id="label_dat_us" title="Nombre Completo"> ${usuario.nombreUsuario }</label>
						</div>
						<div id="registro_dat_us">
							<p id="campos_dat_us">Correo:</p>
							<label id="label_dat_us" title="Correo de e-mail"> ${usuario.correoUsuario } </label>
						</div>
						<div id="registro_dat_us">
							<p id="campos_dat_us">Fecha Nacimiento:</p>
							<label id="label_dat_us" title="Fecha de Nacimiento">
								<c:choose>
									<c:when test="${usuario.fechaCreacion != null}">
										<fmt:formatDate value="${usuario.fechaCreacion }" pattern="dd-MM-yyyy" />	
									</c:when>
									<c:otherwise>
										Sin informaci&oacute;n
									</c:otherwise>
								</c:choose>
							</label>
						</div>
						<div id="registro_dat_us">
						  <p id="campos_dat_us">Tel&eacute;fono:</p>
						 <label id="label_dat_us" title="Teléfono de contacto">${usuario.telefono } </label>
						</div>
						<div id="registro_dat_us">
							<p id="campos_dat_us">Tipo perfil:</p>
							<label id="label_dat_us" title="Tipo de Usuario"> ${usuario.perfil.nombrePerfil } </label>
						</div>
						<div id="registro2_dat_us">
						  <p id="campos2_dat_us">Publicaciones:</p>	
						  <p id="campos3_dat_us">Noticias:</p>
						  <label id="informacion_dat_us" title="Cantidad de Noticias">${noticias } </label>
						</div>						
						<div id="registro2_dat_us">
						  <p id="campos3_dat_us">Anuncios:</p>
						  <label id="informacion_dat_us" title="Cantidad de Anuncios" style="margin-left:15px;">${anuncios } </label>
						</div>
					   </fieldset>
                       </form>
		</div>
	</div>	
</div>					   