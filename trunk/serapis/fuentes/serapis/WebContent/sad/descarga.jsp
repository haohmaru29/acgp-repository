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
<BODY bgcolor='white' leftmargin='0' topmargin='0' onload='	'>
<!--
<table cellpadding='0' cellspacing='0' border='0' width='100%'>
<tr>
<td align='left' class='texttitulomenu' width='75%'>
<script type='text/javascript' language='JavaScript1.2' src='../menu/stm31.js'></script>
<script type='text/javascript' language='JavaScript1.2' src='../menu/menu01n1.js'></script>
</td>
<td align='right' class='texttitulomenu'>
<a href='../desempresa.jsp?ID=4' target='cuerpo' ><img src='../images/liderproceso.jpg' border='0' alt='Líderes de Proceso'></a>&nbsp;&nbsp;
<a href='../desempresa.jsp?ID=3' target='cuerpo' ><img src='../images/organigrama.jpg' border='0' alt='Organigrama'></a>&nbsp;&nbsp;
<a href='mailto:sgonzalez@byteacgp.cl'><img src='../images/email.jpg' border='0' alt='E-Mail'></a>&nbsp;&nbsp;
<a href='../index.jsp' target='_top' ><img src='../images/home.jpg' border='0' alt='Inicio'></a>&nbsp;&nbsp;
<a href='../SerapisAyuda.htm' target='_blank' ><img src='../images/ayuda.jpg' border='0' alt='Ayuda'></a>&nbsp;&nbsp;
</td></tr>
</tr>
</table>
-->
<BR><BR>
<table border="0" cellspacing="1" cellpadding="0" align="center">
    <tr><td class='tituloespecial' width='95%' height='60'>&nbsp;&nbsp;DESCARGA DE ARCHIVOS</td></tr>  
</table>
<BR>
<table border="0" cellspacing="1" cellpadding="0" align="center">
    <tr><td width='95%'>Se ha solicitado la descarga de un archivo del sistema de Administración.</td></tr>
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
