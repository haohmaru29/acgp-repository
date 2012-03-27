package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class nuevocliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public nuevocliente() {
        ADatos = new AccDataBase();
        AFunc = new funciones();
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s = (String)httpsession.getValue("SerapisUser");
        if(s != null && s.length() > 0)
        {
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<form name='frmcliente' method='POST' action=''>");
            CargaNuevoCliente(printwriter);
            printwriter.println("</form>");
            printwriter.println("<script language='javascript'>");
            printwriter.println("function ConAcento(Dato)");
            printwriter.println("{");
            printwriter.println("  var i=0;");
            printwriter.println("  var l;");
            printwriter.println("  var sigue=1;");
            printwriter.println("  var ret=0;");
            printwriter.println("  for(i=0;((i<Dato.length) && (sigue==1));i++)");
            printwriter.println("  {");
            printwriter.println("    l=Dato.charAt(i);");
            printwriter.println("    if((l=='\341') || (l=='\351') || (l=='\355') || (l=='\363') || (l=='\372') || (l=='\301') || (l=='\311') || (l=='\315') || (l=='\323') || (l=='\332'))");
            printwriter.println("    {");
            printwriter.println("      ret=1;");
            printwriter.println("      sigue=0;");
            printwriter.println("    }");
            printwriter.println("  }");
            printwriter.println("  return(ret);");
            printwriter.println("}");
            printwriter.println("function valida()");
            printwriter.println("{");
            printwriter.println("  if(document.frmcliente.rut.value != \"\")");
            printwriter.println("  {");
            printwriter.println("  if(document.frmcliente.abreviatura.value != \"\")");
            printwriter.println("  {");
            printwriter.println("  if(document.frmcliente.razonsocial.value != \"\")");
            printwriter.println("  {");
            printwriter.println("  if(document.frmcliente.direccion.value != \"\")");
            printwriter.println("  {");
            printwriter.println("  if(document.frmcliente.fono.value != \"\")");
            printwriter.println("  {");
            printwriter.println("  if(document.frmcliente.fax.value != \"\")");
            printwriter.println("  {");
            printwriter.println("  if(document.frmcliente.mail.value != \"\")");
            printwriter.println("  {");
            printwriter.println("  if(document.frmcliente.web.value != \"\")");
            printwriter.println("  {");
            printwriter.println("  if(document.frmcliente.contacto1.value != \"\")");
            printwriter.println("  {");
            printwriter.println("  if(document.frmcliente.fono1.value != \"\")");
            printwriter.println("  {");
            printwriter.println("  if(document.frmcliente.fax1.value != \"\")");
            printwriter.println("  {");
            printwriter.println("  if(document.frmcliente.mail1.value != \"\")");
            printwriter.println("  {");
            printwriter.println("  document.frmcliente.action=\"procesacliente.jsp?TIPO=1\";");
            printwriter.println("  document.frmcliente.submit();");
            printwriter.println("  }");
            printwriter.println("  else");
            printwriter.println("  {");
            printwriter.println("    alert(\"Debe ingresar E-Mail Contacto\");");
            printwriter.println("    document.frmcliente.mail1.focus()");
            printwriter.println("  }");
            printwriter.println("  }");
            printwriter.println("  else");
            printwriter.println("  {");
            printwriter.println("    alert(\"Debe ingresar Fax Contacto\");");
            printwriter.println("    document.frmcliente.fax1.focus()");
            printwriter.println("  }");
            printwriter.println("  }");
            printwriter.println("  else");
            printwriter.println("  {");
            printwriter.println("    alert(\"Debe ingresar Fono Contacto \");");
            printwriter.println("    document.frmcliente.fono1.focus()");
            printwriter.println("  }");
            printwriter.println("  }");
            printwriter.println("  else");
            printwriter.println("  {");
            printwriter.println("    alert(\"Debe ingresar Contacto\");");
            printwriter.println("    document.frmcliente.contacto1.focus()");
            printwriter.println("  }");
            printwriter.println("  }");
            printwriter.println("  else");
            printwriter.println("  {");
            printwriter.println("    alert(\"Debe ingresar Sitio Web del cliente\");");
            printwriter.println("    document.frmcliente.web.focus()");
            printwriter.println("  }");
            printwriter.println("  }");
            printwriter.println("  else");
            printwriter.println("  {");
            printwriter.println("    alert(\"Debe ingresar E-Mail del cliente\");");
            printwriter.println("    document.frmcliente.mail.focus()");
            printwriter.println("  }");
            printwriter.println("  }");
            printwriter.println("  else");
            printwriter.println("  {");
            printwriter.println("    alert(\"Debe ingresar Fax del cliente\");");
            printwriter.println("    document.frmcliente.fax.focus()");
            printwriter.println("  }");
            printwriter.println("  }");
            printwriter.println("  else");
            printwriter.println("  {");
            printwriter.println("    alert(\"Debe ingresar Fono del cliente\");");
            printwriter.println("    document.frmcliente.fono.focus()");
            printwriter.println("  }");
            printwriter.println("  }");
            printwriter.println("  else");
            printwriter.println("  {");
            printwriter.println("    alert(\"Debe ingresar Direcci\363n del cliente\");");
            printwriter.println("    document.frmcliente.direccion.focus()");
            printwriter.println("  }");
            printwriter.println("  }");
            printwriter.println("  else");
            printwriter.println("  {");
            printwriter.println("    alert(\"Debe ingresar Razon Social del cliente\");");
            printwriter.println("    document.frmcliente.razonsocial.focus()");
            printwriter.println("  }");
            printwriter.println("  }");
            printwriter.println("  else");
            printwriter.println("  {");
            printwriter.println("    alert(\"Debe ingresar abreviatura del cliente\");");
            printwriter.println("    document.frmcliente.abreviatura.focus()");
            printwriter.println("  }");
            printwriter.println("  }");
            printwriter.println("  else");
            printwriter.println("  {");
            printwriter.println("    alert(\"Debe ingresar rut del cliente\");");
            printwriter.println("    document.frmcliente.rut.focus()");
            printwriter.println("  }");
            printwriter.println("}");
            printwriter.println("</script>");
            printwriter.println("</body>");
            printwriter.println("</html>");
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "CONF", 6);
        }
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    void CargaNuevoCliente(PrintWriter printwriter)
    {
        printwriter.println("<table border='1' width='95%' align='center'>");
        printwriter.println("<tr><td width='95%' colspan='4' class='texttituloarea'>Nuevo Cliente</td></tr>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla' width='15%'>Rut</td>");
        printwriter.println("<td class='textdesttabla'><input name='rut' value='' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(5);' maxlength='15'></td>");
        printwriter.println("<td class='textdesttabla' width='15%'>Abreviatura</td>");
        printwriter.println("<td class='textdesttabla'><input name='abreviatura' value='' language='javascript' style='WIDTH: 100px' onkeypress='return ValidarCaracteres(12);' maxlength='15'></td>");
        printwriter.println("</tr>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla' width='15%'>Razon Social</td>");
        printwriter.println("<td class='textdesttabla'><input name='razonsocial' value='' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(8);' maxlength='60'></td>");
        printwriter.println("<td class='textdesttabla' width='15%'>Direcci\363n</td>");
        printwriter.println("<td class='textdesttabla'><input name='direccion' value='' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(8);' maxlength='100'></td>");
        printwriter.println("</tr>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla' width='15%'>Fono</td>");
        printwriter.println("<td class='textdesttabla'><input name='fono' value='' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(7);' maxlength='15'></td>");
        printwriter.println("<td class='textdesttabla' width='15%'>Fax</td>");
        printwriter.println("<td class='textdesttabla'><input name='fax' value='' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(7);' maxlength='15'></td>");
        printwriter.println("</tr>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla' width='15%'>E-Mail</td>");
        printwriter.println("<td class='textdesttabla'><input name='mail' value='' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(6);' maxlength='60'></td>");
        printwriter.println("<td class='textdesttabla' width='15%'>Web-Site</td>");
        printwriter.println("<td class='textdesttabla'><input name='web' value='' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(9);' maxlength='60'></td>");
        printwriter.println("</tr>");
        printwriter.println("</table>");
        printwriter.println("<br>");
        printwriter.println("<table border='1' width='95%'  align='center'>");
        printwriter.println("<tr>");
        printwriter.println("<td class='texttitulotabla' colspan='2' width='50%'>Contacto 1</td>");
        printwriter.println("<td class='texttitulotabla' colspan='2' width='50%'>Contacto 2</td>");
        printwriter.println("</tr>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla' width='15%'>Contacto</td>");
        printwriter.println("<td class='textdesttabla'><input name='contacto1' value='' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(3);' maxlength='60'></td>");
        printwriter.println("<td class='textdesttabla' width='15%'>Contacto</td>");
        printwriter.println("<td class='textdesttabla'><input name='contacto2' value='' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(3);' maxlength='60'></td>");
        printwriter.println("</tr>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla' width='15%'>Fono</td>");
        printwriter.println("<td class='textdesttabla'><input name='fono1' value='' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(7);' maxlength='15'></td>");
        printwriter.println("<td class='textdesttabla' width='15%'>Fono</td>");
        printwriter.println("<td class='textdesttabla'><input name='fono2' value='' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(7);' maxlength='15'></td>");
        printwriter.println("</tr>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla' width='15%'>Fax</td>");
        printwriter.println("<td class='textdesttabla'><input name='fax1' value='' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(7);' maxlength='15'></td>");
        printwriter.println("<td class='textdesttabla' width='15%'>Fax</td>");
        printwriter.println("<td class='textdesttabla'><input name='fax2' value='' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(7);' maxlength='15'></td>");
        printwriter.println("</tr>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla' width='15%'>E-Mail</td>");
        printwriter.println("<td class='textdesttabla'><input name='mail1' value='' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(6);' maxlength='60'></td>");
        printwriter.println("<td class='textdesttabla' width='15%'>E-Mail</td>");
        printwriter.println("<td class='textdesttabla'><input name='mail2' value='' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(6);' maxlength='60'></td>");
        printwriter.println("</tr>");
        printwriter.println("</table>");
        printwriter.println("<center><input type='button' name='grabar' value='Grabar' class='fondoinput' language='javascript' onclick='return valida();'></center>");
        printwriter.println("</table>");
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    Vector rsfile;
}