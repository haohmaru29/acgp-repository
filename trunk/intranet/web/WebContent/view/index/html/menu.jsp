<script type="text/javascript">
	$.ajax({
	  url: 'security/menu',
	  success: function(data) {
	    $('#menuD').html(data);
	  }
	});
</script>
<div id="menuD"></div>
