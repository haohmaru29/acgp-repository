<%
String Error = request.getParameter("ERR");
if(Error==null)
	Error="0";
%>
<html>
<head>
</head>
<LINK REL="stylesheet" TYPE="text/css" HREF="serapis.css">
<TITLE>SERAPIS. Sistema de Gestión de Calidad.</TITLE>
<BODY bgcolor='white' leftmargin='0' topmargin='0' onload='document.ident.Login.focus()'>

<script type='text/javascript' language='JavaScript1.2' src='menu/stm31.js'></script>
<script type='text/javascript' language='JavaScript1.2' src='menu/menu01.js'></script>
<BR><BR><BR>
<form name="ident" action="validaclave.jsp" method="post">
<table align="center" width="778" cellpadding="0" cellspacing="0" border="0">
<tr>
<%if(Error.compareTo("2")==0)
{%> 
	<td class="texttituloerror" align="center"><B>Clave Incorrecta</B></td>
<%}%>
<%if(Error.compareTo("3")==0) 
{%> 
	<td class="texttituloerror" align="center"><B>Usuario No Existe</B></td>
<%}%>
<%if(Error.compareTo("4")==0) 
{%> 
	<td class="texttituloerror" align="center"><B>Clave no Válida</B></td>
<%}%>
<%if(Error.compareTo("5")==0) 
{%> 
	<td class="texttituloerror" align="center"><B>Usuario Inactivo. Informe al Administrador para autorizar su ingreso.</B></td>
<%}%>
<%if(Error.compareTo("6")==0) 
{%> 
	<td class="texttituloerror" align="center"><B>Sesión Finalizada. Por seguridad ingrese su autenticación nuevamente</B></td>
<%}%>
<%if(Error.compareTo("7")==0) 
{%> 
	<td class="texttituloerror" align="center"><B>Usuario sin Autorización. Inicie Sesión con un usuario valido o acceda a un sistema autorizado para Usted..</B></td>
<%}%>
<%if(Error.compareTo("8")==0) 
{%> 
	<td class="texttituloerror" align="center"><B>Problemas con su Licencia. Contacte a su Proveedor.</B></td>
<%}%>
</tr>
</table>
<BR>
<table align="center" width="778" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td colspan="2" align="middle" height="60" class="tituloespecial">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sistema Gestión de Calidad</td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	<tr>
		<td colspan="2" align="middle" height="25" class="texttitulomayor">Ingrese su Usuario y Password para ingresar al Sistema</td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	<tr>
		<td class="textpietabla" align="right" width="45%">USUARIO</font>&nbsp;</td>
		<td align="left" width="55%"><input name="Login" language="javascript" style="WIDTH: 150px" onkeypress="" maxlength="20"></td>
	</tr>
	<tr>
		<td  class="textpietabla" align="right">PASSWORD&nbsp;</td>
		<td align="left"><input type="password" name="Pass" maxlength="10" style="WIDTH: 150px" language="javascript" onkeypress=""></td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	<tr>
		<td align="middle" colspan="2">
			<input class="fondoinput" type="button" name="ok" value="Aceptar" language="javascript" onclick="return aceptar();">&nbsp;&nbsp;
			<input class="fondoinput" type="button" name="btncambio" value="Cambiar Clave" language="javascript" onclick="return cambio();">
		</td>
	</tr>
</table>
<script language="javascript">
function aceptar()
{
	if (document.ident.Login.value != "")
	{
		for (i = eval(document.ident.Pass.value.length + 1); i <= 10; i++)
		{
			document.ident.Pass.value = document.ident.Pass.value + ' ';
		}
		document.ident.submit();
	}
	else
	{
		alert("Debe Ingresar Usuario");
		document.ident.Login.focus();
	}
}

function cambio()
{
	if (document.ident.Login.value != "")
	{
		var loginuser = document.ident.Login.value;
		window.open("cambio.jsp?USER=" + loginuser,"_self");	
	}
	else
	{
		alert("Debe Ingresar Usuario");
		document.ident.Login.focus();
	}
}
</script>
</body>
</html>
