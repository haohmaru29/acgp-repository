<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
					
<c:choose>
	<c:when test="${usuarios != null  }">
		<c:forEach items="${usuarios }" var="usuarios">
			<div class="info-usuarios fade">	
				<div id="id_us">${usuarios.idusuario }</div>
				<div id="nick_us">${usuarios.nickname }</div>
				<div id="nom_us">${usuarios.nombreUsuario }</div>
				<div id="correo_us">${usuarios.correoUsuario }</div>
				<div id="fecha_us">
					<c:choose>
						<c:when test="${usuarios.fechaCreacion != null}">
							<fmt:formatDate value="${usuarios.fechaCreacion }" pattern="dd-MM-yyyy" />	
						</c:when>
						<c:otherwise>
							Sin informaci&oacute;n
						</c:otherwise>
					</c:choose>
					
				</div>
				<div id="check">
					<input type="checkbox" name="checkbox" value="${usuarios.idusuario }">
				</div>
				<a href="javascript:void(0);" onclick="editar(${usuarios.idusuario});"><div id="modificar-edit"></div></a> 
				<a href="javascript:void(0);" onclick="mostrar(${usuarios.idusuario});"><div id="ver-max"></div></a>
			</div>	
		</c:forEach>
	</c:when>
</c:choose>					