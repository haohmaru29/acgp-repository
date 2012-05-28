$('#addForm').ajaxForm({ 
    success: function(responseText, statusText, xhr, $form) {
    	if(responseText==1) {
    		alert('Usuario ingresado con exito');
    		$('#addForm').reset();
    	} else {
    		alert(responseText);
    	} 
    }
    //timeout:   3000 
}); 