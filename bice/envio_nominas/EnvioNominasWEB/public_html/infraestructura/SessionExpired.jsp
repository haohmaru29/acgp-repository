<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page import="cl.bicevida.core.view.jsf.utils.*"%>

<%
    String initPage = session.getServletContext().getInitParameter("cl.bicevida.core.initPage");
%>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>SessionExpired</title>
  </head>
  <body><p align="center">
      La sesi&oacute;n ha expirado.
    </p><p align="center">
      <a href='<%=JsfUtils.getExternalContext().getRequestContextPath() + "/faces/" + initPage%>' >
        Volver</a>.
    </p></body>
</html>