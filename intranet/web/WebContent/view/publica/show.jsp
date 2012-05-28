<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script type="text/javascript">
	function anunciosUpdate() {
		$("#menu").mask('Actualizando informacion...');
		$.ajax({
			  url: 'publica/anuncio',
			  success: function(data) {
			    $('#anuncio').html(data);
			    $("#menu").unmask();			    
			  }
		});
	}
	
	function loadNoticias(pagina) {	
		$.ajax({
			  url: 'publica/noticia?page='+pagina,
			  success: function(data) {
				$('#principal').html(data);
			    $("#principal").unmask();			    
			  }
		});
	}	
	Anuncio.timer = setInterval(anunciosUpdate, 60 * 1000);
</script>

<div id="content">

	<div id="principal">
		<jsp:include page="noticias/noticia.jsp"></jsp:include>
	</div>

	<div  id="menu">
		<div id="anuncio">
			<jsp:include page="anuncios/anuncio.jsp"></jsp:include>
		</div>
	</div>
</div>
