<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<link rel="stylesheet" type="text/css" href="public/css/datos_usuario.css">

<div id="principal_datos">
  		<div id="titulo">Datos Usuario </div>
				  <div id="cuadro_usuario">
					 <form action="" method="get" enctype="multipart/form-data" id="marco">
					   <fieldset title="Información completa usuario">
				       <div id="registro"> 
							<p id="campos_datos_usuario">Rut: </p>
							<label id="label_datos" title="rut del usuario"> ${usuario.rutUsuario } </label>
							</div>
						<div id="registro">
							<p id="campos_datos_usuario">Nick:</p>
							<label id="label_datos" title="Nickname"> ${usuario.nickname } </label>
						</div>
						<div id="registro">
							<p id="campos_datos_usuario">Nombre Completo:</p>
							<label id="label_datos" title="Nombre Completo"> ${usuario.nombreUsuario }</label>
						</div>
						<div id="registro">
							<p id="campos_datos_usuario">Correo:</p>
							<label id="label_datos" title="Correo de e-mail"> ${usuario.correoUsuario } </label>
						</div>
						<div id="registro">
							<p id="campos_datos_usuario">Fecha Nacimiento:</p>
							<label id="label_datos" title="Fecha de Nacimiento">
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
						<div id="registro">
						  <p id="campos_datos_usuario">Tel&eacute;fono:</p>
						 <label id="label_datos" title="Teléfono de contacto">${usuario.telefono } </label>
						</div>
						<div id="registro">
							<p id="campos_datos_usuario">Tipo perfil:</p>
							<label id="label_datos" title="Tipo de Usuario"> ${usuario.perfil.nombrePerfil } </label>
						</div>
						<div id="registro2">
						  <p id="campos_datos_usuario">Publicaciones:</p>	
						  <p id="campos3">Noticias:</p>
						  <label id="informacion-campo" title="Cantidad de Noticias">${noticias } </label>
						</div>						
						<div id="registro2">
						  <p id="campos3">Anuncios:</p>
						  <label id="informacion-campo" title="Cantidad de Anuncios" style="margin-left:15px;">${anuncios } </label>
						</div>
					   </fieldset>
                       </form>
		</div>
	</div>	
</div>					   