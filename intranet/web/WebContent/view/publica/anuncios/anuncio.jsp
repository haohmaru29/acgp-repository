<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

	<div id="cuadro-titulo">
			<div id="titulos_2">
				<a href="javascript:void(0);"onclick="javascript:System.loadContent('publica/separate?opcion=2');"><spring:message code="message.anuncios.title"></spring:message></a>
			</div>
			<div id="pin"></div>	
	</div>
	<div id="anuncios">
		<c:choose>
			<c:when test="${anuncios != null && !empty anuncios }">
				<c:forEach items="${anuncios}" var="anuncios">
					<div id="cont_anuncios">
						<div id="titulo_anuncio">${anuncios.tituloPublicacion }</div>
						<div id="autor-2">							  
							   		<div id="autor-titulo"><spring:message code="message.anuncios.autor"></spring:message>:</div> 
									<div id="autor_a">${anuncios.usuario.nickname }</div>
						</div>
						<div id="fecha-2">
					  			<div id="fecha-titulo-2"><spring:message code="message.anuncios.fecha"></spring:message>: </div>
								<div id="fecha_a">
									<fmt:formatDate value="${anuncios.fechaIngreso}" pattern="dd/MM/yyyy h:mm "/>
								</div>
						</div>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div id="cont_anuncios">
						<div id="titulo_anuncio"><spring:message code="message.anuncios.sin"></spring:message></div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<%
		if(request.getSession(false).getAttribute("usuario") != null) {
	%>
	<a href="javascript:void(0);" onclick="javascript:System.loadContent('publica/add');">
		<div id="anuncio_mas"></div>
		<div id="agregar">Agregar</div>
	</a>
	<%} %>
	<a href="javascript:void(0);"onclick="javascript:System.loadContent('publica/separate?opcion=2');">
		<div id="todos">Ver Todos</div>
		<div id="ver"></div>	
	</a>


