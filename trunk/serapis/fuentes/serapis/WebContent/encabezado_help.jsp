<%
String Texto = request.getParameter("TEXTO");
if(Texto==null)
	Texto="";
%>
<html>
<head>
<title>SERAPIS. Ayuda.</title>
</head>
<LINK REL='stylesheet' TYPE='text/css' HREF='serapis.css'>
<script type='text/javascript' language='JavaScript' src='js/funciones.js'></script>
<body leftmargin='0' topmargin='0' onload='document.frmhelp.txtBuscar.focus();'>
<form name='frmhelp' action='help.jsp' target='_top' method='post'>
<table align='left' width='100%' cellpadding='0' cellspacing='0' border='0'> 
<tr><td>
<table align='left' width='100%' cellpadding='1' cellspacing='13' border='0'>
<tr>
	<td width='20%' class='contenidoespecial'>Texto de Búsqueda</td>
	<td class='contenidoespecial'><input class='inputverde' name='txtBuscar' value='<%=Texto%>' style='WIDTH: 420px' maxlength='100' language='javascript' onkeypress='return ValidarCaracteres(9);'>
		<input class='fondoinput' type='submit' name='btnbuscar' value ='Buscar' language='javascript'>
	</td>
</tr>   
</table>
</form>
</body>
</html>