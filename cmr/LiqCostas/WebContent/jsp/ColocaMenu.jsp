<%@ page language='java' session="false" contentType="text/html"%>
<%@page import='cl.cmr.Seguridad.ValidaSesion' %>
<%ValidaSesion oSes = new ValidaSesion();
String sMenu = oSes.getMenu(request);
%>
<script type="text/javascript">
	 var arrMenu =  <%=sMenu%>;
	 var g=0;	
 </script>
 	 
<form name="frm1">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td colspan="3" class="fondorepet"><img src="../images/encabezado_cmr.jpg" border="0" width="100%"></td>
  </tr>
  <tr bgcolor="#003355"> 
    <td width="98" align="right" bgcolor="#003355">
    <img src="../images/flechas_menu.jpg" width="28" height="24"/></td>
    <td width="584">
    		<script type="text/javascript" >
				CargaMenu(arrMenu);				
			</script>	 
    </td>
    <td width="342">&nbsp;</td>
  </tr>
</table>
</form>


