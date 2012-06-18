<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String numeroOperacion = request.getParameter("numOperacion");
	String fechaRemesa = request.getParameter("fechaRemesa");
 	String nombreRemesa = request.getParameter("nombreRemesa");
 	String montoRemesa = request.getParameter("montoRemesa");
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Visualizar cup&oacute;n</title>
  </head>
  
  <body  >
  		<iframe width="900px" height="717px" src="pdfengine?numeroOperacion=<%=numeroOperacion %>&fechaRemesa=<%=fechaRemesa %>&nombreRemesa=<%=nombreRemesa %>&montoRemesa=<%=montoRemesa %>" type="application/pdf">
        </iframe>
  </body>
</html>