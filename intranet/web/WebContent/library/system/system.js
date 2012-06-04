System.print = function() {
	window.print();
};
System.login= function login() {
	var nombre = $('#nombre').val() ;
	var clave = $('#clave').val() ;
	$('#bodyContent').mask('Iniciando sesion, espere un momento...');
	if(nombre != '' && clave != '') {
		$.ajax({
			  url: 'usuario/login',
			  data: {nombre: nombre, clave: clave},
			  type: "POST",
			  success: function( data ) {
				  if(data == "true") {
					  window.location.href = '';
				  } else { 
					  $('#contentLogin').html('errorres');
				  }
				  $('#bodyContent').unmask();
			  }
		});
	} else if(nombre == '' && clave == ''){
		$('#bodyContent').unmask();
	} else if(nombre == '' ) {
		$('#bodyContent').unmask();
	} else if(clave == '' ){
		$('#bodyContent').unmask();
	}
};
System.sessionClose = function() {
	Ajax.request('security/closeSession', null, function() {
		location.href = '';
	});
};
System.pdf = function(content) {
	document.document_pdf.content.value = content;
	document.document_pdf.submit();
};
System.maskMessage = "Cargando Datos...<b>Espere Por Favor</b>";
System.changePhoto = function(e, img) {
	var imgNode = e.firstChild;
	imgNode.src = img;
};
System.changeView = function(arr, view) {
	$.each(arr, function(i, item) {
		if (view == item)
			$('#' + view).css('display', '');
		else
			$('#' + item).css('display', 'none');
	});
};
System.parseObject = function(obj, partId) {
	$.each(obj, function(i, data) {
		if (data != null) {
			if (typeof (data) == 'object')
				System.parseObject(data, partId);
			else {
				if ($(partId + i).attr('type') == 'text'
						|| $(partId + i).attr('type') == 'hidden')
					$(partId + i).val(data);
				else
					$(partId + i).html(data);
			}
		}
	});
	return true;
};
System.serializeJson = function(obj) {
	var htmlVars = '';
	$.each(obj, function(key, value) {
		htmlVars += '&' + key + '=' + value;
	});
	return htmlVars;
};

System.loadContent = function(url) {
	if(url!='') {
		$('#contenido').mask("Cargando, espere por favor...");
		$.ajax({
		  url: url,
		  success: function(data) {
				$('#contenido').html(data);
				$('#contenido').unmask();
		  },
		  failure: function(param, request, response) {
			  alert("Errores");
			  $('#contenido').unmask();
		  }
		});		
	}else {
		
		$("#en_construccion").dialog({
			modal: true,
			height: 320,
			width: 590,
			resizable: false
		 });
	}
};

System.info = function(message) {
	$("#contentMessage").html(message);
	$("#messageBoxs").dialog({
		modal: true
	 });
	
};
System.uploadNumber=0;