<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="anunciosBusqueda">
	<div id="titulo_anuncios">Anuncios</div>
	<div id="volver">
		<a href="javascript:void(0);" onclick="System.loadContent('publica/show');">
			<div id="volver-img"></div>
			<div id="volver-text">Volver</div>
		</a>
	</div>
	<c:choose>
		<c:when test="${publicacion != null && !empty publicacion  }">
			<c:forEach items="${publicacion}" var="publica" varStatus="galleria">
				<div id="anuncio">
					<div id="foto_thumb" align="center">
						<c:choose>
							<c:when test="${publica.imagenes != null}">
								<ul class="gallery clearfix">
									<c:forEach items="${publica.imagenes }" var="imagenes" varStatus="test">
										<li>
											<a href="public/upload/normal/${imagenes.nombreImagen }" rel="prettyPhoto[gallery-${galleria.count }]" title="">
											 	<c:choose>
													<c:when test="${test.count==1 }">
														<img src="public/upload/thumbnail/${imagenes.nombreImagen}" alt="" width="100" height="80" border="0" />
													</c:when>
													<c:otherwise>
														
													</c:otherwise>
												</c:choose>
											</a>
										</li>
									</c:forEach>
								</ul>
							</c:when>
						</c:choose>
					</div>
				
					<div id="ti_anuncio">${publica.tituloPublicacion }</div>
					<div id="info_anuncio">
						<p>
							${publica.contenidoPublicacion } 
						</p>
					</div>
					<div id="linea">
						<div id="autor-img"></div>
						<div id="autor">Autor:</div>
						<div id="autor_a">${publica.usuario.nickname }</div>
						<div id="fecha_a"><fmt:formatDate value="${publica.fechaIngreso}"  pattern="dd/MM/yyyy h:mm "/></div>
						<div id="fecha">Fecha:</div>
						<div id="fecha-img"></div>
					</div>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div id="ti_anuncio">No hay anuncios para la fecha solicitada</div>
		</c:otherwise>
	</c:choose>
	
</div>