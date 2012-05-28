<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<link rel="stylesheet" type="text/css" href="public/css/mod_publicaciones.css" />

<script type="text/javascript">

$(function() {
	var contador = 0;
	$('#fileupload').fileupload({
		maxNumberOfFiles: 4,
		maxFileSize: 4194304,
		acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
		complete : function (result, textStatus, jqXHR) {
			//$('#fileupload').reset();
			contador++;
			if(System.uploadNumber == contador) {
				$("#editPublicacion").dialog("close");
				System.info("Actualizaci\xf3n realizada con exito.");
				System.uploadNumber=0;
				contador=0;
			} 
		}, 
		beforeSend: function(xhr) {
			/*alert(2);
			try {jqXHR.stop();}catch(e){}
			alert(3);
			xhr.stop();*/
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

<div id="content">
<div id="principal_modifica_public">
  		<div id="titulo">Modificar Publicaci&oacute;n </div>
				  <div id="cuadro">
				  	<div id="marco">
					  <form action="" method="get" enctype="multipart/form-data" >
					  	<div id="t_titulo">
					     	<p id="campos2">Titulo:</p>
					   		<input id="input_titulo" type="text" value="${publica.tituloPublicacion }" name="textfield" size"40"/>
					    </div>
					    <div id="t_contenido"> 
					    	 <p id="campos2">Contenido:</p>
					    	 <textarea  id="text_area" name="textarea">${publica.contenidoPublicacion }</textarea>
					    </div>
					    <div id="t_entrada">
							<p id="campos2"> Tipo de Entrada:</p>
							<select class="option2" name="idtipoPublicacion" id="idtipoPublicacion" >
								<option value=''>Seleccione publicaci&oacute;n</option>
								<c:forEach items="${tipoPublicacion}" var="tipoPublicacion">
									<c:choose>
										<c:when test="${tipoPublicacion.idtipoPublicacion!= publica.tipoPublicacion.idtipoPublicacion}">
											<option value="${tipoPublicacion.idtipoPublicacion }"> ${tipoPublicacion.nombreTipopublicacion } </option>
										</c:when>
										<c:otherwise>
											<option selected="selected" value="${tipoPublicacion.idtipoPublicacion }"> ${tipoPublicacion.nombreTipopublicacion } </option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
						<div id="t_categoria">	
							<p id="campos2">Categor&iacute;a:</p>
							<p>
								<select name="idCategoria" id="idCategoria" class="option2">
									<c:forEach items="${categorias }" var="categorias">
										<c:choose>
											<c:when test="${categorias.categoria.idcategoria==publica.categoria.idcategoria }">
												<option selected="selected" value="${categorias.categoria.idcategoria }"> ${categorias.categoria.nombreCategoria }</option>
											</c:when>
											<c:otherwise>
												<option value="${categorias.categoria.idcategoria }"> ${categorias.categoria.nombreCategoria }</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</p>
						</div>
					 </form>
					</div>
					       <!-- The file upload form used as target for the file upload widget -->
					     <form id="fileupload" action="php/" method="POST" enctype="multipart/form-data">
        <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
       <p id="campo_img">Imagenes:</p>	 
       <div class="row fileupload-buttonbar">
            <div class="botones">
                <!-- The fileinput-button span is used to style the file input field as button -->
                <button type="submit" class="btn btn-primary start">
                    <i class="icon-refresh icon-white"></i>
                    <span>Actualizar</span>
                </button>
                <button type="reset" class="btn btn-warning cancel">
                    <i class="icon-ban-circle icon-white"></i>
                    <span>Cancelar</span>
                </button>
                <button type="button" class="btn btn-danger delete">
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
        <!-- The loading indicator is shown during image processing -->
        <div class="fileupload-loading"></div>
        <br>
        <!-- The table listing the files available for upload/download -->
        <table class="table table-striped"><tbody class="files" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody></table>
    </form>
</div>
<!-- modal-gallery is the modal dialog used for the image gallery -->
<div id="modal-gallery" class="modal modal-gallery hide fade">
    <div class="modal-header">
        <a class="close" data-dismiss="modal">&times;</a>
        <h3 class="modal-title"></h3>
    </div>
    <div class="modal-body"><div class="modal-image"></div></div>
    <div class="modal-footer">
        <a class="btn modal-download" target="_blank">
            <i class="icon-download"></i>
            <span>Download</span>
        </a>
        <a class="btn btn-success modal-play modal-slideshow" data-slideshow="5000">
            <i class="icon-play icon-white"></i>
            <span>Slideshow</span>
        </a>
        <a class="btn btn-info modal-prev">
            <i class="icon-arrow-left icon-white"></i>
            <span>Previous</span>
        </a>
        <a class="btn btn-primary modal-next">
            <span>Next</span>
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
            <td class="start">{% if (!o.options.autoUpload) { %}
                <button class="btn btn-primary">
                    <i class="icon-upload icon-white"></i>
                    <span>{%=locale.fileupload.start%}</span>
                </button>
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