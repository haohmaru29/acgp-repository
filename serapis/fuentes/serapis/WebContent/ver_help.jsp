<%
String Pagina = request.getParameter("PAGINA");
String Texto = request.getParameter("TEXTO");
if(Pagina==null)
	Pagina="";
if(Texto==null)
	Texto="";
%>
<html>
<head>
</head>
<LINK REL="stylesheet" TYPE="text/css" HREF="../sgcba.css">
<TITLE>SERAPIS. Ayuda.</TITLE>
<frameset framespacing="0" border="true" frameborder="0" rows="10%,*">	  	  
	<frame name="encabezado" src="encabezado_help.jsp?TEXTO=<%=Texto%>" scrolling="no" noresize>
	<frame name="cuerpo" src="ayuda/<%=Pagina%>" scrolling="yes" noresize>
</frameset>
</body>
</html>