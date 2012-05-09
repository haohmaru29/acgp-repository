<%@ page language='java' isErrorPage="true" contentType="text/html" autoFlush="false"%>
<%@page import='cl.cmr.com.Errores'%>
<%@page import='cl.cmr.Seguridad.ValidaSesion'%>
<%

	//para desplegar la traza del error
	//*****************************
	boolean bTrazaError = true;
	//*****************************

  ValidaSesion val = new ValidaSesion();
  int res = val.valida(request);
  String sVolver = "login.jsp";
  String sMenu = "../Menu/principalsinmenu.jsp";
  String sErr = (request.getParameter("ERR")!=null)?request.getParameter("ERR").toString().trim():"ERR.GENERAL_DEFAULT";
  
  //out.println("sErr=" + sErr);
  
  if (res == 0 && !sErr.equals("ERR.GENERAL.PAGE_NO_ENCONTRADA")) { 
	 sMenu = "principal.jsp";
	 sVolver="";
	 
  } else if (sErr.equals("ERR.GENERAL.PAGE_NO_ENCONTRADA")) {
  	sVolver = "";
  }

	String msgError = Errores.getDescription(sErr);
	if (msgError.trim().equals("")) 
		msgError = "Se ha producido un problema!";
%>
<jsp:include page="<%=sMenu%>" flush="true"/>
<HTML>
	<HEAD>
		<TITLE><%=Errores.getDescription("VAR.TITULO")%></TITLE>
		<META HTTP-EQUIV="Cache-Control" content="no-cache"> 
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-store">
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache"> 
		<META HTTP-EQUIV="Expires" CONTENT="0">

		<LINK REL="stylesheet" TYPE="text/css" HREF="../style/Liquidacion.css">
		<jsp:include page="principal.jsp" ></jsp:include>	
		<SCRIPT type="text/javascript">
			function goInicio(){
				window.document.FRMERROR.action = "<%=sVolver%>";
				window.document.FRMERROR.target = "_self";
				window.document.FRMERROR.submit();
			}			
		</SCRIPT>
	</HEAD>
	<BODY>
		<FORM METHOD="POST" NAME="FRMERROR">
		<br>
			<TABLE WIDTH="50%" ALIGN="CENTER" BORDER="0" CELLPADDING="0" CELLSPACING="0" class="fondotabla">
				<TR>
					<TD CLASS="clsTitleArea" COLSPAN="6" WIDTH="100%" ALIGN="LEFT">
						Error
					</TD>
				</TR>
				<TR>
					<TD COLSPAN="6" WIDTH="100%" ALIGN="CENTER">
						&nbsp;
					</TD>
				</TR>
				<TR>
					<TD COLSPAN="6" WIDTH="100%" ALIGN="CENTER">
						&nbsp;
					</TD>
				</TR>
				<TR>
					<TD COLSPAN="6" WIDTH="100%" ALIGN="CENTER">
						<H4><%=msgError%></H4>
					</TD>
				</TR>
				<TR>
					<TD COLSPAN="6" WIDTH="100%" ALIGN="CENTER">
						&nbsp;
					</TD>
				</TR>
				<%if (bTrazaError) {%>
				<TR>
					<TD COLSPAN="6" WIDTH="100%" ALIGN="CENTER">
						<%
						if (exception!=null) {
							out.println(exception.toString());
						}
						%>
					</TD>
				</TR>				
				<%}%>
				<%if (!sVolver.equals("")) {%>
				<TR>
					<TD COLSPAN="6" WIDTH="100%" ALIGN="CENTER">
						<INPUT TYPE="BUTTON" NAME="Volver" VALUE="Volver" ONCLICK="JavaScript:goInicio()">
					</TD>
				</TR>
				<%}%>
			</TABLE>
		</FORM>
	</BODY>
</HTML>