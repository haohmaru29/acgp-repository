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
				}
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
				correoUsuario: "Requerido"
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

<div id="principal_mod_usuario">
	<div id="titulo">${titulo }</div>
	<div id="cuadro">
		<form method="post" id="formUsuarios">
			<div id="registro">
				<p id="campos">* Rut:</p>
				<input id="input_titulo" type="text" value="${usuarios.rutUsuario}" class="required" name="rutUsuario" size"40"/>
			</div>
			<div id="registro">
				<p id="campos">* Nickname:</p>
				<input id="input_titulo" type="text" value="${usuarios.nickname}" class="required" name="nickname" size"40"/>
			</div>
			<div id="registro">
				<p id="campos">* Nombre Completo:</p>
				<input id="textfield" type="text" name="nombreUsuario" class="required" value="${usuarios.nombreUsuario}" size"40"/>
			</div>
			<div id="registro">
				<p id="campos">* Contrase&ntilde;a:</p>
				<input name="claveUsuario" type="password" class="required" value="${usuarios.claveUsuario}"/>
			</div>
			<div id="registro">
				<p id="campos">* Correo:</p>
				<input id="input_titulo" type="text" class="required" name="correoUsuario" value="${usuarios.correoUsuario}" size"40"/>
			</div>
			<div id="registro">
				<p id="campos">&nbsp;Tel&eacute;fono:</p>
				<input id="input_titulo" type="text" name="telefono" value="${usuarios.telefono}" size"40"/>
			</div>
			<div id="registro">
				<p id="campos">* Perfil:</p>
				<select id="perfil" class="option" name="perfil">
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
			<div id="registro">
				<p id="campos"> Fecha Nacimiento:</p>
				<input type="text" name="fechaNacimiento" id="fechaNacimiento" value="<fmt:formatDate value="${usuarios.fechaNacimiento}" pattern="dd/MM/yyyy" />">
			</div>
			<div class="botones">
				<input type="submit" id="Registrar" value="Registrar" class=" btn-registro input-submit" />
				<input type="reset" class="input-submit btn-registro" />
			</div>
			<input type="hidden" name="mngr" value="Usuario">
			<c:if test="${usuarios.idusuario!=null }">
				<input type="hidden" name="idusuario" value="${usuarios.idusuario }">
			</c:if>
			<input type="hidden" name="fechaCreacion" id="fechaCreacion" value="<fmt:formatDate value="${usuarios.fechaCreacion}" pattern="dd/MM/yyyy" />">
		</form>
	</div>
</div>
