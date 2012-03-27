<!-- Forward to a servlet -->
<jsp:forward page="/servlet/sad.inicio" />

<%
HttpSession Session = request.getSession(true);
String UserGDC = (String)session.getValue("SerapisUser");
if((UserGDC!=null) && (UserGDC.length()>0))
{
	//Session.TimeOut = 60;
	
	String Tipo = request.getParameter("TIPO");
	String SubTipo = request.getParameter("SUBTIPO");
	
	if(Tipo == null)
		Tipo="DOC";
	if(SubTipo == null)
		SubTipo="A";
	%>
	<html>
	<head>
	</head>
	<LINK REL="stylesheet" TYPE="text/css" HREF="../sgcba.css">
	<TITLE>Sistema de registros - BYTE ACGP S.A.</TITLE>
	<frameset framespacing="0" border="true" frameborder="0" cols="21%,*">	  	  
		<frame name="menu" src="menu.jsp?TIPO=<%=Tipo%>&SUBTIPO=<%=SubTipo%>" scrolling="no" noresize>
		<%if(Tipo.compareTo("DOC")==0)
		  {%>
			<frame name="datos" src="muestradoc.jsp?TIPO=<%=Tipo%>&SUBTIPO=<%=SubTipo%>" scrolling="yes" noresize>
		<%}%>
		<%if(Tipo.compareTo("PUB")==0)
		  {
		  	if(SubTipo.compareTo("A")==0)%>
				<frame name="datos" src="publicaarea.jsp" scrolling="yes" noresize>
			<%else
				if(SubTipo.compareTo("P")==0)%>
					<frame name="datos" src="publicaproyecto.jsp" scrolling="yes" noresize>
				<%else%>
					<frame name="datos" src="muestradoc.jsp?TIPO=<%=Tipo%>&SUBTIPO=<%=SubTipo%>" scrolling="yes" noresize>
		<%}%>
		<%if(Tipo.compareTo("CLI")==0)
		  {%>
			<frame name="datos" src="vercliente.jsp" scrolling="yes" noresize>
		<%}%>
	</frameset>
	</body>
	</html>
<%
}
else
{%>
	<HTML>
	<HEAD>
	<title>BYTE ACGP S.A</title>
	<meta http-equiv="refresh" content="0;URL=../autentica.jsp">
	</HEAD>
	<body bgcolor="#FFFFFF">
	</body>
	</html>
<%}%>