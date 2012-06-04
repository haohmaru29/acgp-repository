<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%
	response.setContentType("text/html; charset=UTF-8");
	String url =request.getRequestURL().toString();
	url = url.substring(0, url.indexOf(request.getContextPath()) + request.getContextPath().length());
%>
<link rel="stylesheet" type="text/css" href="public/css/noticias.css" />
<script type="text/javascript">
$(function() {
	$("a[rel^='prettyPhoto']").prettyPhoto({
		social_tools: false,
		overlay_gallery: false
	});	
});

</script>

<div id="content_noticias">
	<div id="principal_noticias">
	  	<div id="titulo_noticias"><c:out value="${publicacion.tituloPublicacion }"></c:out>  </div>
	  	<div id="volver_noticias">
			<a href="javascript:void(0);" onclick="System.loadContent('publica/show');">
				<div id="volver-img_noticias"></div>
  				<div id="volver-text_noticias">Volver</div>
			</a>
		</div>
	  	<div id="cuadro_noticias">
		  	<div id="seccion-img_noticias">
				<ul class="gallery clearfix">
					<c:forEach items="${publicacion.imagenes }" var="imagenes2" varStatus="imagenesCount2">
						<c:choose>
							<c:when test="${imagenesCount2.count==1 }">
								<li>
									<a href="<%=url %>/public/upload/normal/${imagenes2.nombreImagen }" rel="prettyPhoto[gallery2]" title="">
										<img src="<%=url %>/public/upload/normal/${imagenes2.nombreImagen }" alt="" width="235" height="225" border="0"/>
									</a>
								</li>
							</c:when>
						</c:choose>
						
					</c:forEach>
				</ul>
			  
			  <div align="center" id="galeria_noticias">
			  		<ul class="gallery clearfix">
			  			<c:forEach items="${publicacion.imagenes }" var="imagenes" varStatus="imagenesCount">
							<c:choose>
								<c:when test="${imagenesCount.count==1 }">
								</c:when>
								<c:otherwise>
									<li>
										<a href="public/upload/normal/${imagenes.nombreImagen }" rel="prettyPhoto[gallery2]" title="">
											<img src="public/upload/thumbnail/${imagenes.nombreImagen }" alt="" width="60" height="60" border="0"/>
										</a>
									</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
			  		</ul>
			  </div>
			  
		    </div>
	        <div id="info_noticias">
			    <p>
			    	<c:out value="${publicacion.contenidoPublicacion }"></c:out> 
			    </p>
	        </div> 
		
		  <div id="linea_noticias">
		  	<div id="categoria-noticias">
					<div id="cat-img-noticias"></div>
					<div id="cat-titulo-noticias">Categoria:</div> 
					<div id="categoria_a-noticias">${publicacion.categoria.nombreCategoria }</div>
			</div>
		  	  <div id="autor-img-noticias"></div>
			   <div id="autor-noticias">Autor:</div> 
			  	<div id="autor_a-noticias">
			  			<c:out value="${publicacion.usuario.nickname }"></c:out> 	
			  	</div>
			  <div id="fecha_a-noticias">
			  		<fmt:formatDate value="${publicacion.fechaIngreso }"  pattern="dd/MM/yyyy"/>
			  </div>
			  <div id="fecha-noticias">Fecha: </div>
			  <div id="fecha-img-noticias"></div>
		  </div>
		</div>	  
	</div>
</div>