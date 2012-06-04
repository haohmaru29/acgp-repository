<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<link rel="stylesheet" href="library/fileupload/css/bootstrap.min.css">
<link rel="stylesheet" href="library/fileupload/css/bootstrap-responsive.min.css">

<link rel="stylesheet" href="library/fileupload/css/bootstrap-image-gallery.min.css">
<link rel="stylesheet" href="library/fileupload/css/jquery.fileupload-ui.css">


<link rel="stylesheet" type="text/css" href="public/css/mantenedor.css" />

<script type="text/javascript">
$(function() {
	var contador = 0;
	$('#fileupload').fileupload({
		maxNumberOfFiles: 4,
		maxFileSize: 4194304,
		acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
		complete : function (result, textStatus, jqXHR) {
			contador++;
			if(System.uploadNumber == contador) {
				System.info("Publicaci\xf3n realizada con exito.");
				System.uploadNumber=0;
				contador=0;
			} 
		}, 
		beforeSend: function(xhr) {

		}
	});
	$('#idtipoPublicacion').change(function() {
		$.getJSON("publica/categorias",{
			tipoPublicacion : $('#idtipoPublicacion').val()
		},
	    function(data) {
				var html = '';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].categoria.idcategoria + '">'
							+ data[i].categoria.nombreCategoria
							+ '</option>';
				}
				$('#idCategoria').html(html);
		});
	});
	setTimeout("clearInterval("+Anuncio.timer+")",0);
});
</script>


<div id="content_mant">
	<div id="principal_mant">
		<div id="titulo_mant">A&ntilde;adir nueva entrada</div>
		<div id="volver_mant">
			<a href="javascript:void(0);" onclick="System.loadContent('${url}');">
				<div id="volver-img_mant"></div>
				<div id="volver-text_mant">Volver</div>
			</a>
		</div>
		<div id="cuadro_mant">
			<div id="marco_mant">
				<form  action="upload/upload" method="post" id="fileupload" enctype="multipart/form-data"  >
					<input type="hidden" name="idpublicTemporal" id="idpublicTemporal" value="${temporal.idpublicTemporal }">
					<div id="ti_titulo_mant">
						<p id="campos_mant">Titulo:</p>
						<input id="input_titulo_mant" type="text" name="tituloPublicacion" size="40" />
					</div>
					<div id="ti_contenido_mant"> 
						<p id="campos_mant">Contenido:</p>
						<textarea id="text_area_mant" name="contenidoPublicacion"></textarea>
					</div>
					<div id="ti_contenido_mant">
						<p id="campos_mant">Tipo de Entrada:</p>
						<select name="idtipoPublicacion" id="idtipoPublicacion" class="option_mant">
							<option value=''>Seleccione publicaci&oacute;n</option>
							<c:forEach items="${tipoPublicacion}" var="tipoPublicacion">
								<option value="${tipoPublicacion.idtipoPublicacion }">${tipoPublicacion.nombreTipopublicacion}</option>
							</c:forEach>
						</select>
					</div>
					<div id="ti_categoria_mant">	
						<p id="campos_mant">Categor&iacute;a:</p>
						<p>
							<select name="idCategoria" id="idCategoria" class="option_mant">
							</select>
						</p>
					</div>
					<p id="campo_img_mant">Imagenes:</p>
			
					<div class="row fileupload-buttonbar">
						<div class="botones_m_mant">
							<!-- The fileinput-button span is used to style the file input field as button -->
							<span class="btn btn-success fileinput-button"> <i
								class="icon-plus icon-white"></i> <span>Agregar</span> 
								<input type="file" name="files" multiple>
							</span>
							<button type="submit" class="btn btn-primary start">
								<i class="icon-upload icon-white"></i> <span>Enviar</span>
							</button>
							<button type="reset" class="btn btn-warning cancel">
								<i class="icon-ban-circle icon-white"></i> <span>Restablecer</span>
							</button>
							<!--
			                <button type="button" class="btn btn-danger delete">
			                    <i class="icon-trash icon-white"></i>
			                    <span>Eliminar</span>
			                </button>
			                -->
							<!--<input type="checkbox" class="toggle" id="check">-->
			
							<div class="span5">
								<!-- The global progress bar -->
			
								<!--   <div class="progress progress-success progress-striped active fade"></div>-->
			
							</div>
						</div>
					</div>
					<!-- The loading indicator is shown during image processing -->
					<div class="fileupload-loading"></div>
					<br>
					<!-- The table listing the files available for upload/download -->
					<table class="table table-striped">
						<tbody class="files" data-toggle="modal-gallery"
							data-target="#modal-gallery"></tbody>
					</table>
				</form>
			</div>
		</div>
		
	<!-- modal-gallery is the modal dialog used for the image gallery -->
	<div id="modal-gallery" class="modal modal-gallery hide fade">
		<div class="modal-header">
			<a class="close" data-dismiss="modal">&times;</a>
			<h3 class="modal-title"></h3>
		</div>
		<div class="modal-body">
			<div class="modal-image"></div>
		</div>
		<div class="modal-footer">
			<a class="btn modal-download" target="_blank"> 
				<i class="icon-download"></i> <span>Download</span>
			</a> 
			<a class="btn btn-success modal-play modal-slideshow" data-slideshow="5000"> 
				<i class="icon-play icon-white"></i> <span>Slideshow</span>
			</a> 
			<a class="btn btn-info modal-prev"> 
				<i class="icon-arrow-left icon-white"></i> <span>Previous</span>
			</a> 
			<a class="btn btn-primary modal-next"> <span>Next</span>
				<i class="icon-arrow-right icon-white"></i>
			</a>
		</div>
	</div>
	</div>
</div>
<!-- The template to display files available for upload -->
<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-upload fade">
        <td class="preview"><span class="fade"></span></td>
        <td class="name"><span>{%=file.name%}</span></td>
        <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>
        {% if (file.error) { %}
            <td class="error" colspan="2"><span class="label label-important">{%=locale.fileupload.error%}</span> {%=locale.fileupload.errors[file.error] || file.error%}</td>
        {% } else if (o.files.valid && !i) { %}
            <td>
                <div class="progress progress-success progress-striped active"><div class="bar" style="width:0%;"></div></div>
            </td>
            <td class="start">
				<div style="display:none;">{% if (!o.options.autoUpload) { %}
                <button class="btn btn-primary">
                    <i class="icon-upload icon-white"></i>
                    <span>{%=locale.fileupload.start%}</span>
                </button>
				</div>
            {% } %}</td>
        {% } else { %}
            <td colspan="2"></td>
        {% } %}
        <td class="cancel">{% if (!i) { %}
            <button class="btn btn-warning">
                <i class="icon-ban-circle icon-white"></i>
                <span>{%=locale.fileupload.cancel%}</span>
            </button>
        {% } %}</td>
    </tr>
{% } %}
</script>
<!-- The template to display files available for download -->
<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade">
        {% if (file.error) { %}
            <td></td>
            <td class="name"><span>{%=file.name%}</span></td>
            <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>
            <td class="error" colspan="2"><span class="label label-important">{%=locale.fileupload.error%}</span> {%=locale.fileupload.errors[file.error] || file.error%}</td>
        {% } else { %}
            <td class="preview">{% if (file.thumbnail_url) { %}
                <a href="{%=file.url%}" title="{%=file.name%}" rel="gallery" download="{%=file.name%}"><img src="{%=file.thumbnail_url%}"></a>
            {% } %}</td>
            <td class="name">
                <a href="{%=file.url%}" title="{%=file.name%}" rel="{%=file.thumbnail_url&&'gallery'%}" download="{%=file.name%}">{%=file.name%}</a>
            </td>
            <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>
            <td colspan="2"></td>
        {% } %}
        <td class="delete">
            <button class="btn btn-danger" data-type="{%=file.delete_type%}" data-url="{%=file.delete_url%}">
                <i class="icon-trash icon-white"></i>
                <span>{%=locale.fileupload.destroy%}</span>
            </button>
            <input type="checkbox" name="delete" value="1">
        </td>
    </tr>
{% } %}
</script>