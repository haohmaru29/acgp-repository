<%
HttpSession varsession;

varsession = request.getSession(true);
String Nombre = (String)varsession.getValue("SerapisNombre");
if(Nombre == null)
   Nombre = "&nbsp;";
%>
<html>
<head>
</head>
<LINK REL="stylesheet" TYPE="text/css" HREF="serapis.css">
<TITLE>SERAPIS. Sistema de Gestión de Calidad</TITLE>
<body leftmargin='0' topmargin='0' >
<table align="left" width="100%" cellpadding="0" cellspacing="0" border="0">
  <tr>
  	 <td width="168px" valign='top' align="left" class='fondoencabserapisp1'>
  	 	<table align="left" width="168px" cellpadding="0" cellspacing="0" border="0">
  	 		<tr><td height='46px'>&nbsp;</td></tr>
  	 		<tr><td height='22px'><B>&nbsp;
  	 			<%if(Nombre.length() > 0)%>
  	    			<%=Nombre%>
  	 		</B></td></tr>
  	 	</table>
  	 </td>
    <td align="left" valign='top' width="632px"><a name="principio"></a> 
    <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="632px" height="68px">
        <param name="movie" value="images/encab_parte02.swf">
        <param name="quality" value="high"><param name="SCALE" value="exactfit">
        <embed src="images/encab_parte02.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="100%" height="68px" scale="exactfit"></embed>
    </object>
    </td>
    <td valign='top' align="left"><img src='images/encab_parte03.jpg' border='0' height="68px"></td>
  </tr>
</table>
</body>
</html>
