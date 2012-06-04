<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<link rel="stylesheet" type="text/css" href="public/css/mod_usuario.css" />

<script type="text/javascript">
	$(function(){
		$("#formUsuarios").validate({
			rules: {
				rutUsuario: "required",
				nickname: "required",
				nombreUsuario: {
					required: true,
					minlength: 2
				},
				claveUsuario: {
					required: true
					//,minlength: 5
				},
				perfil: "required",
				correoUsuario: {
					required: true,
					email: true
				},
				estadoUsuario: "required"
			},
			messages: {
				rutUsuario: "Requerido",
				nickname: "Requerido",
				nombreUsuario: {
					required: "Requerido"
				},
				claveUsuario: {
					required: "Requerido"
					//minlength: "Your password must be at least 5 characters long"
				},
				perfil: {
					required: "Requerido",
				},
				correoUsuario: "Requerido",
				estadoUsuario: "Requerido"
			}
		});
		
	    $('#formUsuarios').ajaxForm({
	    	url: 'admin/save',
	    	beforeSubmit:  function (formData, jqForm, options) { 
	    	 	$("#form_registro").mask("Actualizando datos, espere por favor...");
	    	}, 
	        success: function showResponse(responseText, statusText, xhr, $form)  {
	        	if(responseText==1) {
	        		System.info("Usuario actualizado con exito");
	        		$("#form_registro").unmask();
	        		$( "#form_registro").dialog("close");
	        		$( "#form_registro" ).dialog( "destroy" );
	        		actualizar();
	        	} else {
	        		System.info(responseText);
	        		$("#form_registro").unmask();
	        		$( "#form_registro").dialog("close");
	        	}
	        	
	        	document.getElementById("formUsuarios").reset();
	        }  
	    }); 
		
		$("#fechaNacimiento").datepicker({
		    changeMonth: true,
		    changeYear: true,
		    monthNames: ['Enero','Febrero','Marzo','Abril','Mayo','Junio', 'Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
		    monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
		    dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
		    dayNamesShort: ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
		    dayNamesMin: ['Do','Lu','Ma','Mi','ju','Vi','Sa'] ,
		    dateFormat: 'dd/mm/yy',
		    firstDay: 1,
		    //yearRange: '1970:2012',
		    maxDate: +0,
		    showOtherMonths: true,
		    selectOtherMonths: true 
		});
	});
</script>

<div id="contenido_registro">
	<div id="principal_registro">
		<div id="titulo_registro">${titulo }</div>
		<div id="cuadro_registro">
			<form method="post" id="formUsuarios">
				<div id="registro_registro">
					<p id="campos_registro">* Rut:</p>
					<input  type="text" value="${usuarios.rutUsuario}" class="required" name="rutUsuario" size"40"/>
				</div>
				<div id="registro_registro">
					<p id="campos_registro">* Nickname:</p>
					<input type="text" value="${usuarios.nickname}" class="required" name="nickname" size"40"/>
				</div>
				<div id="registro_registro">
					<p id="campos_registro">* Nombre Completo:</p>
					<input  type="text" name="nombreUsuario" class="required" value="${usuarios.nombreUsuario}" size"40"/>
				</div>
				<div id="registro_registro">
					<p id="campos_registro">* Contrase&ntilde;a:</p>
					<input name="claveUsuario" type="password" class="required" value="${usuarios.claveUsuario}"/>
				</div>
				<div id="registro_registro">
					<p id="campos_registro">* Correo:</p>
					<input  type="text" class="required" name="correoUsuario" value="${usuarios.correoUsuario}" size"40"/>
				</div>
				<div id="registro_registro">
					<p id="campos_registro">&nbsp;Tel&eacute;fono:</p>
					<input  type="text" name="telefono" value="${usuarios.telefono}" size"40"/>
				</div>
				<div id="registro_registro">
					<p id="campos_registro">* Perfil:</p>
					<select id="perfil" style="width: 200px;"  name="perfil">
						<c:choose>
							<c:when test="${perfiles!=null }" >
								<c:forEach items="${perfiles }" var="perfiles">
									<c:choose>
										<c:when test="${usuarios.perfil.idperfil!=null }">
											<c:choose>
												<c:when test="${usuarios.perfil.idperfil==perfiles.idperfil }">
													<option selected="selected" value="${perfiles.idperfil }">${perfiles.nombrePerfil }</option>
												</c:when>
												<c:otherwise>
													<option value="${perfiles.idperfil }">${perfiles.nombrePerfil}</option>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<option value="${perfiles.idperfil }">${perfiles.nombrePerfil }</option>									
										</c:otherwise>
									</c:choose>
									
								</c:forEach>
							</c:when>
						</c:choose>
					</select>
				</div>
				
				<div id="registro_registro">
					<p id="campos_registro">* Estado:</p>
					<select id="estadoUsuario" style="width: 200px;" name="estadoUsuario">
						<c:choose>
							<c:when test="${estado!=null }" >
								<c:forEach items="${estado }" var="estado">
									<c:choose>
										<c:when test="${usuarios.estadoUsuario.idestadoUsuario!=null }">
											<c:choose>
												<c:when test="${usuarios.estadoUsuario.idestadoUsuario==estado.idestadoUsuario }">
													<option selected="selected" value="${estado.idestadoUsuario}">${estado.nombreEstadousuario }</option>
												</c:when>
												<c:otherwise>
													<option value="${estado.idestadoUsuario}">${estado.nombreEstadousuario }</option>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<option value="${estado.idestadoUsuario}">${estado.nombreEstadousuario }</option>									
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:when>
						</c:choose>
					</select>
				</div>
				
				
				
				
				
				<div id="registro_registro">
					<p id="campos_registro"> Fecha Nacimiento:</p>
					<input type="text" name="fechaNacimiento" id="fechaNacimiento" value="<fmt:formatDate value="${usuarios.fechaNacimiento}" pattern="dd/MM/yyyy" />">
				</div>
				<div class="botones_registro">
					<button class="btn btn-primary start" type="submit">
					<span>${boton }</span>
					</button>
					<c:if test="${usuarios.idusuario==null }">
						<button class="btn btn-warning cancel" type="reset">
						<span>Restablecer</span>
						</button>
					</c:if>
				</div>	
				<input type="hidden" name="mngr" value="Usuario">
				<c:if test="${usuarios.idusuario!=null }">
					<input type="hidden" name="idusuario" value="${usuarios.idusuario }">
					<input type="hidden" name="fechaCreacion" id="fechaCreacion" value="<fmt:formatDate value="${usuarios.fechaCreacion}" pattern="dd/MM/yyyy" />">
				</c:if>
			</form>
		</div>
	</div>
</div>