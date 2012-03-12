<%@ page language='java' contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" errorPage="error.jsp?ERR=ERR.GENERAL.PAGE"%>
<%getServletContext().getRequestDispatcher("/jsp/acceso.jsp").include(request,response);%>
<jsp:include page="principal.jsp" flush="false"/>

<SCRIPT LANGUAGE='JavaScript'>
	document.onkeydown = function()
	{ 
		if (window.event)
		{
			switch (window.event.keyCode){
				case Sigma.Const.Key.ESC: 
				case Sigma.Const.Key.F1: 
				case Sigma.Const.Key.NUMLOCK: 
				case Sigma.Const.Key.F2: 
				case Sigma.Const.Key.F3: 
				case Sigma.Const.Key.ASTE: 
				case Sigma.Const.Key.F4:
				case Sigma.Const.Key.F5:
				case Sigma.Const.Key.F6:
				case Sigma.Const.Key.F7:
				case Sigma.Const.Key.F8:
				case Sigma.Const.Key.F9:
				case Sigma.Const.Key.F10:
				case Sigma.Const.Key.F11:
				case Sigma.Const.Key.F12:
					window.event.keyCode = 505; 
					break;
				}
			if(window.event && window.event.keyCode == 505) 
	     	{ 
	     		return false;    
	     	}
		}
	} 	
</SCRIPT>
	
<body leftmargin="0" topmargin="0" onhelp="return false;">
<jsp:include page="ColocaMenu.jsp" flush="false"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<FORM METHOD="POST" NAME="FRMWELCOME">
			<INPUT TYPE="HIDDEN" NAME="CODEJE" VALUE="1">
		  <br>
		  <br>
		  <br>
		  <br>
			<TABLE WIDTH="50%" height="200px" ALIGN="CENTER" BORDER="0" CELLPADDING="0" CELLSPACING="0" class="fondotabla">
				<TR>
					<TD height="10px" CLASS="clsTitleArea" ALIGN="LEFT">&nbsp;</TD>
				</TR>
				<TR>
					<TD class="labelUno" ALIGN="CENTER">&nbsp;</TD>
				</TR>
				<TR>
					<TD CLASS="clsbienvenida" ALIGN="CENTER">Bienvenidos al Sistema <%=com.Errores.getDescription("VAR.TITULO")%></TD>
				</TR>
				<TR>
					<TD class="labelUno" ALIGN="CENTER"><%=com.Errores.getDescription("VAR.COMPAÑIA")%></TD>
				</TR>
				<TR>
					<TD class="labelUno" ALIGN="CENTER"><%=com.Errores.getDescription("VAR.VERSION")%></TD>
				</TR>
				<TR>
					<TD class="labelUno" ALIGN="CENTER">&nbsp;</TD>
				</TR>
			</TABLE>
		</FORM>
</TABLE>		
</BODY>
</HTML>