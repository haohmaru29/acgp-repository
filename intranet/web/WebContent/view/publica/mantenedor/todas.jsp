<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<link rel="stylesheet" href="library/fileupload/css/bootstrap.min.css">
<link rel="stylesheet" href="library/fileupload/css/bootstrap-responsive.min.css">
<link rel="stylesheet" href="library/fileupload/css/bootstrap-image-gallery.min.css">
<link rel="stylesheet" href="library/fileupload/css/jquery.fileupload-ui.css">
<link rel="stylesheet" type="text/css" href="public/css/mantenedor_publicaciones.css" />

<script type="text/javascript" src="view/publica/js/todas.js"></script>

<div id="content_publicacion">
	<div id="principal_publicacion">
		<div id="titulo">Mantenedor Publicaciones</div>
		<div id="volver">
			<a href="javascript:void(0);" onclick="System.loadContent('publica/show');">
				<div id="volver-img_mantenedor"></div>
				<div id="volver-text">Volver</div>
			</a>
		</div>
		<div id="cuadro">
			<div id="filtro">
				<div id="text-filtro">Seleccione tipo de Publicaci&oacute;n:</div>
				<select class="option" id="idtipoPublicacion">
					<option value="-1">TODOS</option>
					<c:forEach items="${tipoPublicacion}" var="tipoPublicacion">
						<option value="${tipoPublicacion.idtipoPublicacion }">${tipoPublicacion.nombreTipopublicacion}</option>
					</c:forEach>
				</select>
			</div>
			<div id="botones">
				<div id="posicion">
	                <span class="btn btn-success fileinput-button">
	                <a href="javascript:void(0); " id="links" onclick="javascript:System.loadContent('publica/add');">
	                    <i class="icon-plus icon-white"></i>
	                    <span>Agregar</span>
	                </a>    
	                </span>
	                <button class="btn btn-primary start"  onclick="javascript:actualizar();">
	                  <a href="javascript:void(0);" id="links">	
	                    <i class="icon-refresh icon-white"></i>
	                    <span>Actualizar</span>
	                  </a>  
	                </button>
	                <button type="button" class="btn btn-danger delete" onclick="eliminar();">
	                    <i class="icon-trash icon-white"></i>
	                    <span>Eliminar</span>
	                </button>
	            </div>
	         </div>
		
			<div class="buscar">
				<input name="datepicker" type="text" id="input-buscar" />
				<button type="button" class="icon" onclick="buscar();">
			</div>
			<div id="marco">
				<div class="bar-publicaciones">
					<div id="id">ID</div>
					<div id="titulop">Titulo</div>
					<div id="fecha1">Fecha Creaci&oacute;n</div>
					<div id="fecha2">Fecha Modificaci&oacute;n</div>
					<div id="eliminar">Eliminar</div>
					<div id="modificar2">Modificar</div>
					<div id="ver2">Ver</div>
				</div>
				<div class="publicaciones" id="testingMantenedor">
					<jsp:include page="tabla.jsp"></jsp:include>
				</div>
			</div>
		</div>
		<div id="paginacion">
			<a href="#"> << </a> <a href="#" id="link">1</a>- <a href="#">2</a>-
			<a href="#">3</a> <a href="#"> >> </a>
		</div>
	</div>
</div>
<div style="display:none" id="editPublicacion">
	<jsp:include page="editar.jsp"></jsp:include>
</div>