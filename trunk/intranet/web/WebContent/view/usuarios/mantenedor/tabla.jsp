<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<c:choose>
	<c:when test="${usuarios != null  }">
		<c:forEach items="${usuarios }" var="usuarios">
			<div class="info-usuarios_mant_us fade">
				<div id="id_us_mant_us">${usuarios.idusuario }</div>
				<div id="nick_us_mant_us">${usuarios.nickname }</div>
				<div id="nom_us_mant_us">${usuarios.nombreUsuario }</div>
				<div id="correo_us_mant_us">${usuarios.correoUsuario }</div>
				<div id="fecha_us_mant_us">
					<c:choose>
						<c:when test="${usuarios.fechaCreacion != null}">
							<fmt:formatDate value="${usuarios.fechaCreacion }" pattern="dd-MM-yyyy" />	
						</c:when>
						<c:otherwise>
							Sin informaci&oacute;n
						</c:otherwise>
					</c:choose>
				</div>
				<div id="estado_mant_us">${usuarios.estadoUsuario.nombreEstadousuario }</div>
				<div id="check_mant_us">
					<input type="checkbox" name="checkbox" value="${usuarios.idusuario }">
				</div>
				<a href="javascript:void(0);" onclick="editar(${usuarios.idusuario});"><div id="modificar-edit_mant_us"></div></a> 
				<a href="javascript:void(0);" onclick="mostrar(${usuarios.idusuario});"><div id="ver-max_mant_us"></div></a>
			</div>	
		</c:forEach>
	</c:when>
</c:choose>					