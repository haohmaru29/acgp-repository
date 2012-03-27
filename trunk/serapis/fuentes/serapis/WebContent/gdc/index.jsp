<html>
<head>
<title>BYTE ACGP S.A</title>
<%
String login = request.getParameter("LOGIN");
HttpSession Session = request.getSession(true);
String ValidaOK = (String)session.getValue("IngresoOK");

if(ValidaOK != null)
{
	if(ValidaOK.compareTo("OK")==0)
	{
		session.putValue("UserGDC", (String)login);
		session.putValue("IngresoOK", (String)"NO");%>
		<meta http-equiv="refresh" content="0;URL=inicio.jsp">
	<%}
	else
	{%>
		<meta http-equiv="refresh" content="0;URL=../reindex.jsp">
	<%}
}
else
{%>
	<meta http-equiv="refresh" content="0;URL=../reindex.jsp">
<%}%>
</head>
<body bgcolor="#FFFFFF">
</body>
</html>
