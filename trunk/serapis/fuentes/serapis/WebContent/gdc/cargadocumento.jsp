<%
String Area = request.getParameter("AREA");
String TipoDocumento = request.getParameter("TIPODOC");
String Descripcion = request.getParameter("DESCRIPCION");
String sTipo = request.getParameter("TIPO")!=null?request.getParameter("TIPO"):"";
%>
<html>
<head>
	<LINK REL="stylesheet" TYPE="text/css" HREF="../sgcba.css">
	<TITLE>SERAPIS. Sistema de Gestión de Calidad.</TITLE>
</head>
<frameset framespacing='0' frameborder='0' cols='30%,*'>
	<frame name="menu" src="menu.jsp?TIPO=DOC&AREA=<%=Area%>&TIPODOC=<%=TipoDocumento%>" scrolling="no" noresize>
	<frame name="datos" src="muestradocespecial.jsp?AREA=<%=Area%>&TIPODOC=<%=TipoDocumento%>&DESCRIPCION=<%=Descripcion%>&TIPO=<%=sTipo%>" scrolling="yes" noresize>
</frameset>
</body>
</html>