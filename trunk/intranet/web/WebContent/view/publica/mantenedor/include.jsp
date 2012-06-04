<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<link rel="stylesheet" href="library/fileupload/css/bootstrap.min.css">
<link rel="stylesheet" href="library/fileupload/css/bootstrap-responsive.min.css">
<link rel="stylesheet" href="library/fileupload/css/bootstrap-image-gallery.min.css">
<link rel="stylesheet" href="library/fileupload/css/jquery.fileupload-ui.css">
<link rel="stylesheet" type="text/css" href="public/css/mantenedor_publicaciones.css" />

<script type="text/javascript" src="view/publica/js/publica.js"></script>

<div id="content_mant_pub">
	<div id="principal_mant_pub">
		<div id="titulo_mant_pub">Mantenedor Publicaciones</div>
		<div id="volver_mant_pub">
			<a href="javascript:void(0);" onclick="System.loadContent('publica/show');">
				<div id="volver-img_mant_pub"></div>
				<div id="volver-text_mant_pub">Volver</div>
			</a>
		</div>
		<div id="cuadro_mant_pub">
			<div id="filtro_mant_pub">
				<div id="text-filtro_mant_pub">Seleccione tipo de Publicaci&oacute;n:</div>
				<select class="option_mant_pub" id="idtipoPublicacion">
					<option value="-1">TODOS</option>
					<c:forEach items="${tipoPublicacion}" var="tipoPublicacion">
						<option id="opciones_mant_pub" value="${tipoPublicacion.idtipoPublicacion }">${tipoPublicacion.nombreTipopublicacion}</option>
					</c:forEach>
				</select>
			</div>
			<div id="botones_mant_pub">
				<div id="posicion_mant_pub">
	                <span class="btn btn-success fileinput-button">
	                <a href="javascript:void(0); " id="links_mant_pub" onclick="javascript:System.loadContent('publica/add?option=1');">
	                    <i class="icon-plus icon-white"></i>
	                    <span>Agregar</span>
	                </a>    
	                </span>
	                <button class="btn btn-primary start"  onclick="javascript:actualizar();">
	                  <a href="javascript:void(0);" id="links_mant_pub">	
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
		
			<div class="buscar_mant_pub">
				<input name="datepicker" type="text" id="input-buscar_mant_pub" />
				<button type="button" class="icon_mant_pub" onclick="buscar();">
			</div>
			<div id="marco_mant_pub">
				<div class="bar-publicaciones_mant_pub">
					<div id="id_titulo_mant_pub">ID</div>
					<div id="titulo_titulo_mant_pub">Titulo</div>
					<div id="fecha1_titulo_mant_pub">Fecha Creaci&oacute;n</div>
					<div id="fecha2_titulo_mant_pub">Fecha Modificaci&oacute;n</div>
					<div id="eliminar_titulo_mant_pub">Eliminar</div>
					<div id="modificar_titulo_mant_pub">Modificar</div>
					<div id="ver_titulo_mant_pub">Ver</div>
				</div>
				<div id="cuadro-scroll_mant_pub">
					<div class="publicaciones_mant_pub" id="testingMantenedor">
						<jsp:include page="tabla.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
		<div id="paginacion_mant_pub">
			<jsp:include page="paginacion.jsp"></jsp:include>
		</div>
	</div>
</div>
<div style="display:none" id="editPublicacion">
	
</div>