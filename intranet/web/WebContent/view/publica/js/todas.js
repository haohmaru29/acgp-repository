setTimeout("clearInterval(" + Anuncio.timer + ")", 0);
	
function actualizar() {
	$("#testingMantenedor").mask("Cargando datos, espere...");
	$.ajax({
		url : 'publica/todas',
		data: {tipoPublicacion: $('#idtipoPublicacion').val() , tipoPublicacion:$('#idtipoPublicacion').val(), fecha: $("#input-buscar").val()},
		success : function(data) {
			$('#testingMantenedor').html(data);
			$("#testingMantenedor").unmask();
		},
		failure: function(x,y,z) {
			$("#testingMantenedor").unmask();
		}
	});
}

function buscar() {
	if($("#input-buscar").val()=='') {
		System.info("Debe ingresar una fecha");
	} else {
		$("#testingMantenedor").mask("Cargando datos, espere...");
		$.ajax({
			url : 'publica/todas',
			data: {tipoPublicacion: $('#idtipoPublicacion').val() , tipoPublicacion:$('#idtipoPublicacion').val(), fecha: $("#input-buscar").val()},
			success : function(data) {
				$('#testingMantenedor').html(data);
				$("#testingMantenedor").unmask();
			},
			failure: function(x,y,z) {
				$("#testingMantenedor").unmask();
			}
		});
	}
}

function eliminar() {
	$checkedCheckboxes = $("input:checkbox[name=checkbox]:checked");
	var selectedValues="";
	$checkedCheckboxes.each(function () {
		selectedValues +=  $(this).val() +",";
	});
	if(selectedValues!='') {
		$.ajax({
			url : 'publica/delete',
			data: {options: selectedValues},
			success : function(data) {
				if(data==1) {
					System.info("Publicaciones eliminadas con exito.");
					actualizar();
				}else {
					System.info(data);
				}
			},
			failure: function(x,y,z) {
				
			}
		});
		
	} else {
		System.info("Debe seleccionar alguna publicacion a eliminar");
	}
}

$("#input-buscar").datepicker({
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

$('#idtipoPublicacion').change(function() {
	$("#testingMantenedor").mask("Cargando datos, espere...");
	$.ajax({
		url : 'publica/todas',
		data: {tipoPublicacion: $('#idtipoPublicacion').val() },
		success : function(data) {
			$('#testingMantenedor').html(data);
			$("#testingMantenedor").unmask();
		},
		failure: function(x,y,z) {
			$("#testingMantenedor").unmask();
		}
	});
});

System.edita = function(id, url, div) {
	$( "#" + div ).mask('Cargando, espere por favor...');
	$.ajax({
	  url: url,
	  type: 'POST',
	  success: function(data) {
		  	$( "#" + div ).html(data);
		  	$( "#" + div ).unmask(); 
	  }, 
	  failure: function(data) {
		  $( "#" + div ).unmask();
	  }
	});
	
	$( "#" + div ).dialog({
		autoOpen: true,
		height: 466,
		width: 1100,
		modal: true,
		close: function() {
			
		}
	});
};

System.show = function(id, url, div) {
	$( "#" + div ).mask('Cargando, espere por favor...');
	$.ajax({
	  url: url,
	  type: 'POST',
	  success: function(data) {
		  	$( "#" + div ).html(data);
		  	$( "#" + div ).unmask(); 
	  }, 
	  failure: function(data) {
		  $( "#" + div ).unmask();
	  }
	});
	
	$( "#" + div ).dialog({
		autoOpen: true,
		height: 466,
		width: 1100,
		modal: true,
		close: function() {
			
		}
	});
};