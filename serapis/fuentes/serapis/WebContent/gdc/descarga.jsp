<%
String Archivo = request.getParameter("FILEDESC");
if(Archivo==null)
	Archivo="";
%>
<html>
<jsp:useBean id="downloadbean" class="javazoom.download.DownloadBean" scope="application" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<LINK REL="stylesheet" TYPE="text/css" HREF="../serapis.css">
<TITLE>SERAPIS. Sistema de Gestión de Calidad.</TITLE>
<BODY bgcolor='white' leftmargin='0' topmargin='0' onload=''>
<BR><BR>
<table border="0" cellspacing="1" cellpadding="0" align="center">
    <tr><td class='tituloespecial' width='95%' height='60'>&nbsp;&nbsp;DESCARGA DE ARCHIVOS</td></tr>
</table>
<BR>
<table border="0" cellspacing="1" cellpadding="0" align="center">
    <tr><td width='95%'>Se ha solicitado la descarga de un archivo del sistema de Gestión Documental.</td></tr>
    <tr><td width='95%'>Presione sobre el link y guarde el archivo solicitado.</td></tr>
</table>
<BR><BR>
<table border="0" cellspacing="1" cellpadding="0" align="center">
    <tr>
    <td width='15%'><a href="<%= request.getContextPath() %>/download/<%=Archivo%>"><img src='../images/extdefault.gif' border='0'></a></td>
    <td width='80%'><b><a href="<%= request.getContextPath() %>/download/<%=Archivo%>"><%=Archivo%></a></b></td>
    </tr>
</table>
</body>
</html>
