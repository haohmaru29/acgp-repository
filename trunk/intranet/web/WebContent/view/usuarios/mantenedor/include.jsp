<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<link rel="stylesheet" href="library/fileupload/css/bootstrap.min.css">
<link rel="stylesheet" href="library/fileupload/css/bootstrap-responsive.min.css">
<link rel="stylesheet" href="library/fileupload/css/bootstrap-image-gallery.min.css">
<link rel="stylesheet" href="library/fileupload/css/jquery.fileupload-ui.css">
<link rel="stylesheet" type="text/css" href="public/css/mantenedor_usuarios.css" />

<script type="text/javascript" src="view/usuarios/js/mantenedor.js"></script>

<div id="content_usuarios">
<div id="principal_usuario">
  		<div id="titulo">Mantenedor de Usuarios </div>
  				<div id="volver">
  					<a href="javascript:void(0);" onclick="System.loadContent('publica/show');">
	  					<div id="volver-img"></div>
	  					<div id="volver-text">Volver</div>
  					</a>
  				</div>
				  <div id="cuadro_usuarios">
				  <div id="botones">
				  		<div class="row fileupload-buttonbar">
			            <div class="span7">
			                <!-- The fileinput-button span is used to style the file input field as button -->
			                <span class="btn btn-success fileinput-button">
			                <a href="javascript:void(0);" id="links" onclick="agregar();">
			                    <i class="icon-plus icon-white"></i>
			                    <span>Agregar</span>
			                </a>    
			                </span>
			                <button class="btn btn-primary start" onclick="actualizar();">
			                  <a href="javascript:void(0);" id="links">	
			                    <i class="icon-refresh icon-white"></i>
			                    <span>Actualizar</span>
			                  </a>  
			                </button>
			               <!-- <button type="reset" class="btn btn-warning cancel">
			                    <i class="icon-ban-circle icon-white"></i>
			                    <span></span>
			               </button>-->
			                <button type="button" class="btn btn-danger delete" onclick="eliminar();">
			                    <i class="icon-trash icon-white"></i>
			                    <span>Eliminar</span>
			                </button>
								 <!--<input type="checkbox" class="toggle" id="check">-->
			            
			            <div class="span5">
			                <!-- The global progress bar -->
												
			             <!--   <div class="progress progress-success progress-striped active fade"></div>-->
							
			            </div>
			        </div>
	</div>
  </div>
		<div class="buscar">
			<input name="datepicker" type="text" id="input-buscar" />
			<button type="button" class="icon" onclick="actualizar();">
        </div>
	  	<div id="marco">
			<div class="bar-usuarios">
				<div id="id"> ID </div>
				<div id="nick">Nickname </div>
				<div id="nombre">Nombre </div>
				<div id="correo">Correo </div>
				<div id="fecha_b">Fecha Creaci&oacute;n</div>
				<div id="eliminar">Eliminar</div>
				<div id="modificar">Modificar</div>
				<div id="ver_u">Ver</div>
			</div>
			<div class="users" id="tabla_usuario">
				<jsp:include page="tabla.jsp"></jsp:include>
			</div>		
		</div>
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
						<a href="javascript:void(0);" style="color:#DF6800;" id="link"> ${paginacionCount.count } </a>
					</c:if>
					<c:if test="${pagina!=null && paginacionCount.count==pagina }">
						<a href="javascript:void(0);" id="link" style="color:#DF6800;"> ${paginacionCount.count } </a>
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

				
</div>
					     
</div>					  

<div id="form_registro" style="display:none;">
</div>   