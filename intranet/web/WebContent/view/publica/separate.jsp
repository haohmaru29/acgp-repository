<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

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

<div id="content">
	<div id="principal">
		<div id="anunciosBusqueda">
			<div id="titulo_anuncios">Anuncios</div>
			<div id="volver">
				<a href="javascript:void(0);" onclick="System.loadContent('publica/show');">
				<div id="volver-img"></div>
				<div id="volver-text">Volver</div>
				</a>
			</div>
			<div id="contenedor">
				<c:forEach items="${publicacion}" var="publica" varStatus="galleria">
					<div id="anuncio">
						<div id="foto_thumb" align="center">
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
			</div>
		</div>
	</div>
	
	
	<div class="busqueda">
		<div id="contenido-demo">
			<div id="titulo_anuncios">Buscar</div>
			<div id="icon-buscar"></div>
			<div id="buscar">Buscar anuncios por fecha</div>
			<label id="buscar"> Seleccione Fecha:</label> 
			<input name="datepicker" type="text" id="datepicker" size="13"  /> 
			<input type="button" value="Buscar" id="input-calendario" onclick="javascript:buscarAnuncio();" />
			<div id="bcategorias">
				<c:forEach items="${categorias }" var="categorias">
					<input type="checkbox" name="checkbox" value="${categorias.categoria.idcategoria }" id="check"><div id="cat">${categorias.categoria.nombreCategoria }</div>	
				</c:forEach>
			</div>
		</div>
	</div>
	
</div>
<!-- BUSQUEDA -->
