setTimeout("clearInterval(" + Anuncio.timer + ")", 0);

$(function(){
	$("#input-buscar_mant_us").datepicker({
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
	
});

function actualizar() {
	$("#tabla_usuario").mask("Cargando datos, espere...");
	$.ajax({
		url : 'usuario/load',
		data: {fecha: $("#input-buscar_mant_us").val()},
		success : function(data) {
			$('#tabla_usuario').html(data);
			$("#tabla_usuario").unmask();
		},
		failure: function(x,y,z) {
			$("#tabla_usuario").unmask();
		}
	});
}

function eliminar() {
	$checkedCheckboxes = $("input:checkbox[name=checkbox]:checked");
	var selectedValues="";
	$checkedCheckboxes.each(function () {
		selectedValues +=  $(this).val() +",";
	});
	if(selectedValues!='') {
		$("#tabla_usuario").mask("Eliminando, espere por favor...");
		$.ajax({
			url : 'usuario/delete',
			data: {options: selectedValues},
			success : function(data) {
				if(data==1) {
					System.info("Usuario(s) eliminado(s) con exito.");
					actualizar();
					
				}else {
					$("#tabla_usuario").unmask();
					System.info(data);
				}
			},
			failure: function(x,y,z) {
				
			}
		});
		
	} else {
		System.info("Debe seleccionar algun usuario a eliminar");
	}
}

function editar(idUsuario) {
	$( "#form_registro").mask('Cargando, espere por favor...');
	$( "#form_registro").html('');
	$.ajax({
	  url: 'usuario/edit',
	  type: 'POST',
	  data: {idUsuario: idUsuario},
	  success: function(data) {
		  	$( "#form_registro").html(data);
		  	$( "#form_registro" ).unmask(); 
	  }, 
	  failure: function(data) {
		  $( "#form_registro").unmask();
	  }
	});
	
	$( "#form_registro").dialog({
		autoOpen: true,
		height: 490,
		width: 690,
		resizable: false,
		modal: true,
		close: function() {
			
		}
	});
}

function agregar() {
	$.ajax({
		  url: 'usuario/edit',
		  type: 'POST',
		  data: {idUsuario: -1},
		  success: function(data) {
			  	$( "#form_registro").html(data);
			  	$( "#form_registro" ).unmask(); 
		  }, 
		  failure: function(data) {
			  $( "#form_registro").unmask();
		  }
		});
		
		$( "#form_registro").dialog({
			autoOpen: true,
			height: 490,
			width: 690,
			resizable: false,
			modal: true,
			close: function() {
				
			}
		});
}

function loadPagina(pagina) {
	$( "#tabla_usuario").mask('Cargando, espere por favor...');
	$( "#tabla_usuario").html('');
	$.ajax({
	  url: 'usuario/load',
	  type: 'POST',
	  data: {page: pagina},
	  success: function(data) {
		  	$( "#tabla_usuario").html(data);
		  	$( "#tabla_usuario" ).unmask(); 
	  }, 
	  failure: function(data) {
		  $( "#tabla_usuario").unmask();
	  }
	});
	
	$.ajax({
		  url: 'usuario/paginacion',
		  type: 'POST',
		  data: {page: pagina},
		  success: function(data) {
			  	$("#paginacion_mant_us").html(data);
		  }, 
		  failure: function(data) {
			  $("#paginacion_mant_us").html(data);
		  }
	});
}

function mostrar(idUsuario) {
	$( "#form_registro").mask('Cargando, espere por favor...');
	$( "#form_registro").html('');
	$.ajax({
	  url: 'usuario/mostrar',
	  type: 'POST',
	  data: {idUsuario: idUsuario},
	  success: function(data) {
		  	$( "#form_registro").html(data);
		  	$( "#form_registro" ).unmask(); 
	  }, 
	  failure: function(data) {
		  $( "#form_registro").unmask();
	  }
	});
	
	$( "#form_registro").dialog({
		autoOpen: true,
		height: 400,
		width: 600,
		modal: true,
		close: function() {
			
		}
	});
}