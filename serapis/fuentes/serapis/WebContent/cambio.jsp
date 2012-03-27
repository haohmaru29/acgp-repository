<%
String login = request.getParameter("USER");
String Error = request.getParameter("ERR");
if(Error==null)
	Error="0";
%>

<html>
<head>
</head>
<LINK REL="stylesheet" TYPE="text/css" HREF="serapis.css">
<TITLE>SERAPIS. Sistema de Gestión de Calidad.</TITLE>
<body language="javascript" onload="return document.ident.Pass.focus()" leftmargin="0" topmargin="0">
<script type='text/javascript' language='JavaScript1.2' src='menu/stm31.js'></script>
<script type='text/javascript' language='JavaScript1.2' src='menu/menu01.js'></script>
<form name="ident" action="cambiaclave.jsp" method="post">
<table align="center" width="778" cellpadding="0" cellspacing="0" border="0">
<tr>
<%if(Error.compareTo("1")==0)
{%> 
	<td class="texttituloerror" align="center"><B>Problemas al Grabar en la Base de Datos. Intente Nuevamente.</B></td>
<%}%>
<%if(Error.compareTo("2")==0)
{%> 
	<td class="texttituloerror" align="center"><B>Nueva Clave no Válida</B></td>
<%}%>
<%if(Error.compareTo("3")==0) 
{%> 
	<td class="texttituloerror" align="center"><B>Clave Actual no Válida</B></td>
<%}%>
<%if(Error.compareTo("4")==0) 
{%> 
	<td class="texttituloerror" align="center"><B>Usuario no Existe</B></td>
<%}%>
<%if(Error.compareTo("5")==0) 
{%> 
	<td class="texttituloerror" align="center"><B>Clave Caducada. Cambie su clave y autentíquese.</B></td>
<%}%>
</tr>
</table>
<BR>
<table align="center" border="0" width="80%" bordercolor="black">
	<tr>
		<td align="middle" height="60" class="tituloespecial">CAMBIO DE CLAVE</td>
	</tr>
</table>
<br>
<table border="0" width="50%" align="center">
	<tr>
		<td class="textcampos">USUARIO</font>&nbsp;
		</td>
		<td>
			<input type="hidden" name="Login" language="javascript" style="WIDTH: 150px" onkeypress="" maxlength="10" value="<%=login%>">
			<input name="Loginn" language="javascript" style="WIDTH: 150px" onkeypress="" maxlength="10" value="<%=login%>" disabled>
		</td>
	</tr>
	<tr>
		<td  class="textcampos">PASSWORD ACTUAL&nbsp;
		</td>
		<td>
			<input type="password" name="Pass" maxlength="10" style="WIDTH: 150px" language="javascript" onkeypress="">
		</td>
	</tr>
	<tr>
		<td  class="textcampos">NUEVA PASSWORD&nbsp;
		</td>
		<td>
			<input type="password" name="NewPass" maxlength="10" style="WIDTH: 150px" language="javascript" onkeypress="">
		</td>
	</tr>
	<tr>
		<td  class="textcampos">REINGRESE NUEVA PASSWORD&nbsp;
		</td>
		<td>
			<input type="password" name="ReNewPass" maxlength="10" style="WIDTH: 150px" language="javascript" onkeypress="">
		</td>
	</tr>
</table>
<br>
<table border="0" width="80%" align="center">
	<tr>
		<td align="middle">
			<input class='fondoinput' type="button" name="ok" value="Aceptar" style="WIDTH: 80px" language="javascript" onclick="aceptar();">
			<input class='fondoinput' type="button" name="nook" value="Cancelar" style="WIDTH: 80px" language="javascript" onclick="window.open('index.jsp','_top');">
		</td>
	</tr>
</table>
</form>
<script language="javascript">
function aceptar()
{
	if (document.ident.Login.value != "")
	{
		if (document.ident.Pass.value != "")
		{
			if (document.ident.NewPass.value != "")
			{
				if (document.ident.ReNewPass.value != "")
				{
					if (document.ident.NewPass.value == document.ident.ReNewPass.value)
					{
						for (i = eval(document.ident.Pass.value.length + 1); i <= 10; i++)
						{
							document.ident.NewPass.value = document.ident.NewPass.value + ' ';
							document.ident.ReNewPass.value = document.ident.ReNewPass.value + ' ';
						}
						document.ident.submit();
					}
					else
					{
						alert("Password y Nueva Password deben ser iguales");
						document.ident.ReNewPass.value="";
						document.ident.NewPass.value="";
						document.ident.NewPass.focus();
					}
				}
				else
				{
					alert("Debe Ingresar Nueva Password");
					document.ident.ReNewPass.focus();
				}
			}
			else
			{
				alert("Debe Ingresar Nueva Password");
				document.ident.NewPass.focus();
			}
		}
		else
		{
			alert("Debe Ingresar Password");
			document.ident.Pass.focus();
		}
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
