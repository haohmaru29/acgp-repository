<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<link rel="stylesheet" href="library/fileupload/css/bootstrap.min.css">
<link rel="stylesheet" href="library/fileupload/css/bootstrap-responsive.min.css">
<link rel="stylesheet" href="library/fileupload/css/bootstrap-image-gallery.min.css">
<link rel="stylesheet" href="library/fileupload/css/jquery.fileupload-ui.css">
<link rel="stylesheet" type="text/css" href="public/css/mantenedor_usuarios.css" />

<script type="text/javascript" src="view/usuarios/js/mantenedor.js"></script>

<div id="content_mant_us">
	<div id="principal_mant_us">
		<div id="titulo_mant_us">Mantenedor de Usuarios </div>
		<div id="volver_mant_us">
			<a href="javascript:void(0);" onclick="System.loadContent('publica/show');">
				<div id="volver-img_mant_us"></div>
				<div id="volver-text_mant_us">Volver</div>
			</a>
		</div>
		<div id="cuadro_mant_us">
			<div id="botones_mant_us">
				<div class="row fileupload-buttonbar">
					<div class="span7">
						<!-- The fileinput-button span is used to style the file input field as button -->
						<span class="btn btn-success fileinput-button"> <a
							href="javascript:void(0);" id="links_mant_us" onclick="agregar();"> <i
								class="icon-plus icon-white"></i> <span>Agregar</span>
						</a>
						</span>
						<button class="btn btn-primary start" onclick="actualizar();">
							<a href="javascript:void(0);" id="links_mant_us"> 
								<i class="icon-refresh icon-white"></i> <span>Actualizar</span>
							</a>
						</button>
						<!-- <button type="reset" class="btn btn-warning cancel">
			                    <i class="icon-ban-circle icon-white"></i>
			                    <span></span>
			               </button>-->
						<button type="button" class="btn btn-danger delete" onclick="eliminar();">
							<i class="icon-trash icon-white"></i> <span>Eliminar</span>
						</button>
						<div class="span5">
						</div>
					</div>
				</div>
			</div>
			<div class="buscar_mant_us">
				<input name="datepicker" type="text" id="input-buscar_mant_us" />
				<button type="button" class="icon_mant_us" onclick="actualizar();"></button>
			</div>
			<div id="marco_mant_us">
				<div class="bar-usuarios_mant_us">
					<div id="id_tus_mant_us">ID</div>
					<div id="nick_tus_mant_us">Nickname</div>
					<div id="nombre_tus_mant_us">Nombre</div>
					<div id="correo_tus_mant_us">Correo</div>
					<div id="estado_tus_mant_us">Fecha Creaci&oacute;n</div>
					<div id="estado_tus_mant_us">Estado</div>
					<div id="eliminar_tus_mant_us">Eliminar</div>
					<div id="modificar_tus_mant_us">Modificar</div>
					<div id="ver_tus_mant_us">Ver</div>
				</div>
				<div id=cuadro-scroll_mant_us>	
					<div class="users_mant_us" id="tabla_usuario">
						<jsp:include page="tabla.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
		<div id="paginacion_mant_us">
			<jsp:include page="paginacion.jsp"></jsp:include>
		</div>
	</div>
</div>

<div id="form_registro" style="display: none;"></div>
