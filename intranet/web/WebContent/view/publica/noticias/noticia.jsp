<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<div id="titulos"><spring:message code="message.noticias.title"></spring:message></div>
<%
	if(request.getSession(false).getAttribute("usuario") != null) {
%>
<div id="insertar">
	<a href="javascript:void(0);" onclick="javascript:System.loadContent('publica/add');">
	<div id="anuncio_mas"></div><div id="agregar">Agregar</div></a>
</div>
<div id="publicacion">
	<a href="javascript:void(0);" onclick="javascript:System.loadContent('publica/mantenedor');">
		<div id="publish"></div> <div id="publicacion_texto">Mis Publicaciones</div>
	</a>
</div>
<%} %>
<div id="contenedor">
	<c:choose>
		<c:when test="${noticias != null }">
			<c:forEach items="${noticias}" var="noticias">
				 <div id="noticias_id">
					<div id="foto_thumb">
						<div align="center">
							<a href="contenido/noticias.php">
								<c:choose>
									<c:when test="${noticias.imagenes!=null }">
										<c:forEach items="${noticias.imagenes}" var="imagenes" varStatus="imagenesCount">
											<c:choose>
												<c:when test="${imagenesCount.count == 1 }">
													<div align="center">
													 	<a href="javascript:void(0);" onclick="javascript:System.loadContent('publica/mostrar?idNoticia=${noticias.idpublicacion }');">
													 		<img src="public/upload/thumbnail/${imagenes.nombreImagen}" alt="" width="100" height="100" border="0"/>
													 	</a>
													 </div>
												</c:when>
											</c:choose>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<img src="" alt="" width="100" height="100" border="0"/>	
									</c:otherwise>
								</c:choose>
							</a>
						</div>
					</div>
					<div id="ti_noticias"><a href="javascript:void(0);" onclick="javascript:System.loadContent('publica/mostrar?idNoticia=${noticias.idpublicacion }');">${noticias.tituloPublicacion }</a></div>
					<div id="bajada">
						<div id="bajada-text">
							${noticias.contenidoPublicacion }
						</div>
						<div id="cuadro-leer">
				 		 	<a href="javascript:void(0);" onclick="javascript:System.loadContent('publica/mostrar?idNoticia=${noticias.idpublicacion }');">
				 		 		<p id="leer">Leer m&aacute;s <div id="more"></div></p>
				 		 	</a>
				 		</div>
					</div>
	  			  	 <div id="linea">
	  			  	 	<div id="categoria">
							<div id="cat-img"></div>
							<div id="cat-titulo">Categoria:</div> 
							<div id="categoria_a">
								${noticias.categoria.nombreCategoria }
							</div>
						</div>
			   			 <div id="autor">
						  		<div id="autor-img"></div>
				   				<div id="autor-titulo"><spring:message code="message.noticias.autor"></spring:message>:</div> 
				   				<div id="autor_a">${noticias.usuario.nickname }</div>
			   			 </div>
			  			 <div id="space"></div>
			  			 <div id="fecha">
								<div id="fecha-img"></div>
				  				<div id="fecha-titulo-1"><spring:message code="message.noticias.fecha"></spring:message>:</div>
				  				<div id="fecha_a">
				  					<fmt:formatDate value="${noticias.fechaIngreso }"  pattern="dd-MM-yyyy h:mm "/>
				  				</div>
						</div>
		  			 </div>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise><h3><spring:message code="message.noticias.sin"></spring:message></h3></c:otherwise>
	</c:choose>
</div>
<div id="paginacion">
	<c:choose>
		<c:when test="${paginacion!= null && !empty paginacion }">
				<c:set var="pagi" value="${paginacion}"></c:set>
				<c:if test="${pagina!=0 && pagina!=null && pagina!=1 }">
					<a href="javascript:void(0);" onclick="loadNoticias(${pagina-1 });"> << </a>
				</c:if>
				
				<c:forEach  varStatus="paginacionCount" begin="1" end="${pagi }">
					<c:if test="${pagina==null && paginacionCount.count==1}">
						<a href="javascript:void(0);" style="color:#DF6800;"> ${paginacionCount.count } </a>
					</c:if>
					<c:if test="${pagina!=null && paginacionCount.count==pagina }">
						<a href="javascript:void(0);" style="color:#DF6800;"> ${paginacionCount.count } </a>
					</c:if>
					<c:if test="${pagina==null && paginacionCount.count!=1 }">
						<a href="javascript:void(0);" onclick="loadNoticias(${paginacionCount.count });"> ${paginacionCount.count } </a>
					</c:if>
					<c:if test="${pagina!=null  && paginacionCount.count!=pagina }">
						<a href="javascript:void(0);" onclick="loadNoticias(${paginacionCount.count });"> ${paginacionCount.count } </a>
					</c:if>
				</c:forEach>
				
				<c:if test="${pagina!=paginacion && pagina!=null}">
					<a href="javascript:void(0);" onclick="loadNoticias(${paginacion });"> >> </a>
				</c:if>
				<c:if test="${pagina==null}">
					
				</c:if>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
</div>