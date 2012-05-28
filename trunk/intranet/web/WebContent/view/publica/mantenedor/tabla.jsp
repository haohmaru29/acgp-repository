<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<link rel="stylesheet" type="text/css" href="public/css/mantenedor_publicaciones.css" />

<c:forEach items="${publicacion }" var="publica">
	<div class="info-publicaciones fade">
		<div id="id_pub">${publica.idpublicacion }</div>
		
		<div id="titulo_pub">${publica.tituloPublicacion }</div>
		<div id="fecha_pub">
			<fmt:formatDate value="${publica.fechaIngreso }" pattern="dd-MM-yyyy" />
		</div>
		<div id="fecham_pub">
			<c:choose>
				<c:when test="${publica.fechaModificacion ==null}">
						Sin modificaci&oacute;n
				</c:when>
				<c:otherwise>
					<fmt:formatDate value="${publica.fechaModificacion }" pattern="dd-MM-yyyy" />
				</c:otherwise>
			</c:choose>
		</div>
		<div id="check">
			<input type="checkbox" name="checkbox" value="${publica.idpublicacion }">
		</div>
		<a href="javascript:void(0);" onclick="System.edita('1', 'publica/update?idNoticia=${publica.idpublicacion }' , 'editPublicacion');">
			<div id="modificar-edit2"></div>
		</a>
		<a href="javascript:void(0);" onclick="System.show('1', 'publica/mostrar?idNoticia=${publica.idpublicacion }' , 'editPublicacion');">
			<div id="ver-max2"></div>
		</a>
	</div>
</c:forEach>