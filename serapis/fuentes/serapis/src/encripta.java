import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import Acc.AccDataBase.*;

public class encripta extends HttpServlet
{
  Acc.AccDataBase ADatos = new Acc.AccDataBase();
  Acc.funciones AFunc = new Acc.funciones();
  Vector rs;
  String sql;

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    String DatoFinalE="";
    String DatoFinalD="";


    String txtEncriptar = request.getParameter("txtDatoE");
    String txtDesEncriptar = request.getParameter("txtDatoD");
    if(txtEncriptar==null)
       txtEncriptar="";
    if(txtDesEncriptar==null)
       txtDesEncriptar="";

    out.println("<html>");
    out.println("<head>");
    out.println("<title>SERAPIS. Ayuda.</title>");
    out.println("</head>");
    out.println("<LINK REL='stylesheet' TYPE='text/css' HREF='serapis.css'>");
    out.println("<script type='text/javascript' language='JavaScript' src='js/funciones.js'></script>");
    out.println("<body leftmargin='0' topmargin='0' onload='document.frmhelp.txtBuscar.focus();'>");
    rs = new Vector();
    if(txtEncriptar.length()>0)
    {
       DatoFinalE = AFunc.encripta(txtEncriptar);
    }
    if(txtDesEncriptar.length()>0)
    {
       DatoFinalD = AFunc.desencripta(txtDesEncriptar);
    }

    out.println("<form name='frmencripta' action='encripta.jsp' method='post'>");
	out.println("<table align='left' width='100%' cellpadding='0' cellspacing='0' border='0'> ");
	out.println("<tr><td>");
	out.println("<table align='left' width='100%' cellpadding='1' cellspacing='13' border='0'>");
	out.println("<tr>");
	out.println("	<td width='20%' class='contenidoespecial'>A Encriptar</td>");
	out.println("	<td class='contenidoespecial'><input class='inputverde' name='txtDatoE' value='"+DatoFinalE+"' maxlength='200' language='javascript' onkeypress='return ValidarCaracteres(9);'>");
	out.println("	</td>");
	out.println("</tr>   ");
	out.println("<tr>");
	out.println("	<td width='20%' class='contenidoespecial'>A DesEncriptar</td>");
	out.println("	<td class='contenidoespecial'><input class='inputverde' name='txtDatoD' value='"+DatoFinalD+"' maxlength='200' language='javascript' onkeypress='return ValidarCaracteres(9);'>");
	out.println("	</td>");
	out.println("</tr>   ");
	out.println("<tr>");
	out.println("	<td class='contenidoespecial' colspan='2'>");
	out.println("		<input class='fondoinput' type='submit' name='btnbuscar' value ='Buscar' language='javascript' onclick=''>");
	out.println("	</td>");
	out.println("</tr>   ");
	out.println("</table>");
	out.println("</td></tr>");
	out.println("<tr><td>&nbsp;</td></tr>");
	out.println("<tr><td>");
    out.println("</td></tr>");
    out.println("</table>");
    out.println("</form>");

    out.println("</body>");
    out.println("</html>");
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    doGet(request, response);
  }
}