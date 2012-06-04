<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%response.setContentType("text/html; charset=UTF-8"); %>
<link rel="stylesheet" type="text/css" href="public/css/anuncios.css" />

<script type="text/javascript">
	$(function() {
		$("#datepicker").datepicker({
	          changeMonth: true,
	          changeYear: true,
	          monthNames: ['Enero','Febrero','Marzo','Abril','Mayo','Junio', 'Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
	          monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
	          dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
	          dayNamesShort: ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
	          dayNamesMin: ['Do','Lu','Ma','Mi','ju','Vi','Sa'] ,
	          dateFormat: 'dd/mm/yy',
	          firstDay: 1,
	          maxDate: +0,
	          showOtherMonths: true,
	          selectOtherMonths: true 
	    });
		$("a[rel^='prettyPhoto']").prettyPhoto({
			social_tools: false,
			overlay_gallery: false
		});
	});
	setTimeout("clearInterval("+Anuncio.timer+")",0);
	
	function buscarAnuncio() {
		var fecha = $('#datepicker').val();
		if(fecha == null || fecha == 'undefined' || fecha == '') {
			System.info('Ingrese una fecha para la busqueda');
			return false;
		}
		$('#anunciosBusqueda').mask("Buscando, espere por favor...");
		$.ajax({
		  url: 'publica/buscar',
		  data: { fecha: fecha , opcion: '2' },
		  type: 'POST',
		  success: function(data) {
				$('#anunciosBusqueda').html(data);
				$('#anunciosBusqueda').unmask();
		  }
		});
	}
	
</script>

<div id="content_anuncios">
	<div id="principal_anuncios">
		<div id="anunciosBusqueda">
			<div id="titulo_anuncios">Anuncios</div>
			<div id="volver_anuncios">
				<a href="javascript:void(0);" onclick="System.loadContent('publica/show');">
				<div id="volver-img_anuncios"></div>
				<div id="volver-text_anuncios">Volver</div>
				</a>
			</div>
			<div id="contenedor_anuncios">	
				<c:forEach items="${publicacion}" var="publica" varStatus="galleria">
					<div id="cuadro_anuncios">
						<div id="img-thumb_anuncios">
							<c:choose>
								<c:when test="${publica.imagenes != null }">
									<ul class="gallery clearfix">
										<c:forEach varStatus="test" items="${publica.imagenes }" var="imagenes" >
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
					
						<div id="ti_anuncios">${publica.tituloPublicacion }</div>
						<div id="info_anuncios">
							<p>
								${publica.contenidoPublicacion } 
							</p>
						</div>
						<div id="linea_anuncios">
						
							<div id="categoria-anuncios">
									<div id="cat-img-anuncios"></div>
									<div id="cat-titulo-anuncios">Categoria:</div> <div id="categoria_a-anuncios">${publica.categoria.nombreCategoria }</div>
							</div>
					  	   	<div id="autor-img-anuncios"></div>
						   	<div id="autor-anuncios">Autor:</div> <div id="autor_a-anuncios">${publica.usuario.nickname }</div>
						   	<div id="fecha_a-anuncios"><fmt:formatDate value="${publica.fechaIngreso}"  pattern="dd/MM/yyyy h:mm "/></div><div id="fecha-anuncios">Fecha: </div>
						   	<div id="fecha-img-anuncios"></div>					
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	
	
	<div class="busqueda_anuncios">
		<div id="contenido-demo_anuncios">
			<div id="titulo_anuncios">Buscar</div>
			<div id="icon-buscar"></div>
			<div id="buscar_anuncios">Buscar anuncios por fecha</div>
			<label id="buscar_anuncios"> Seleccionar Fecha:</label>
			<input name="datepicker" type="text" id="datepicker" size="13"  /> 
			<input type="button" value="Buscar" id="input-calendario_anuncios" onclick="javascript:buscarAnuncio();" />
			<div id="bcategorias_anuncios">
				<c:forEach items="${categorias }" var="categorias">
					<input type="checkbox" name="checkbox" value="${categorias.categoria.idcategoria }" id="check_anuncios">
					<div id="cat_anuncios">${categorias.categoria.nombreCategoria }</div>	
				</c:forEach>
			</div>
		</div>
	</div>
	
</div>
<!-- BUSQUEDA -->
