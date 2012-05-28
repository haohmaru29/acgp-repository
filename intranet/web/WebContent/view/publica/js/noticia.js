function getNoticia(id) {
	$.ajax({
	  url: 'publica/anuncio',
	  success: function(data) {
	    $('#anuncio').html(data);
	    $("#menu_left").unmask();			    
	  }
	});
}