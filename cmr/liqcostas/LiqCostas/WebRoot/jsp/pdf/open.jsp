<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String content = request.getParameter("content");
	if(content!= null )
		content = content.replace("\"", "'");
	
	System.out.println(content);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Visualizar cup&oacute;n</title>
  </head>
  
  <body  >
  		<object width="900px" height="717px" data="pdfengine?content=<%=content %>" type="application/pdf" id="pdfTest">
            alt : <a href="test.pdf">test.pdf</a>
        </object>
  </body>
</html>
