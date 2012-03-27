package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class vercliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public vercliente() {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        RutCliente = "";
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
            RutCliente = httpservletrequest.getParameter("CLI");
            if(RutCliente != null && RutCliente.length() > 0)
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
                printwriter.println("  document.frmcliente.action=\"procesacliente.jsp?TIPO=2\";");
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
                AFunc.reindex(httpservletrequest, printwriter, 1, "CONF", 7);
            }
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
        //String s = "";
        String s2 = "select abreviatura,razonsocial,direccion,telefono,fax,email,website,contacto1,fonocontacto1,faxcontacto1,mailcontacto1,contacto2,fonocontacto2,faxcontacto2,mailcontacto2 from sgc.clientes where rutcliente = '" + RutCliente + "'";
        boolean flag = false;
        rs = new Vector();
        ADatos.connect();
        int j = ADatos.ConsultaDB(s2);
        rs = ADatos.getResult();
        if(rs.size() > 0)
        {
            printwriter.println("<table border='1' width='95%' align='center'>");
            printwriter.println("<tr><td width='95%' colspan='4' class='texttituloarea'>Editar Cliente</td></tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='15%'>Rut</td>");
            printwriter.println("<td class='textdesttabla'><input name='rut' value='" + RutCliente + "' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(5);' maxlength='15' type='hidden'><input name='rutvisible' value='" + RutCliente + "' language='javascript' style='WIDTH: 220px' onkeypress='' maxlength='15' disabled></td>");
            printwriter.println("<td class='textdesttabla' width='15%'>Abreviatura</td>");
            printwriter.println("<td class='textdesttabla'><input type='hidden' name='abreviatura' value='" + (String)rs.elementAt(0) + "' language='javascript' style='WIDTH: 100px' onkeypress='return ValidarCaracteres(12);' maxlength='10'><input name='abreviaturavisible' value='" + (String)rs.elementAt(0) + "' language='javascript' style='WIDTH: 100px' onkeypress='return ValidarCaracteres(12);' maxlength='10' disabled></td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='15%'>Razon Social</td>");
            printwriter.println("<td class='textdesttabla'><input name='razonsocial' value='" + (String)rs.elementAt(1) + "' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(8);' maxlength='60'></td>");
            printwriter.println("<td class='textdesttabla' width='15%'>Direcci\363n</td>");
            printwriter.println("<td class='textdesttabla'><input name='direccion' value='" + (String)rs.elementAt(2) + "' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(8);' maxlength='100'></td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='15%'>Fono</td>");
            printwriter.println("<td class='textdesttabla'><input name='fono' value='" + (String)rs.elementAt(3) + "' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(7);' maxlength='15'></td>");
            printwriter.println("<td class='textdesttabla' width='15%'>Fax</td>");
            printwriter.println("<td class='textdesttabla'><input name='fax' value='" + (String)rs.elementAt(4) + "' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(7);' maxlength='15'></td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='15%'>E-Mail</td>");
            printwriter.println("<td class='textdesttabla'><input name='mail' value='" + (String)rs.elementAt(5) + "' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(6);' maxlength='60'></td>");
            printwriter.println("<td class='textdesttabla' width='15%'>Web-Site</td>");
            printwriter.println("<td class='textdesttabla'><input name='web' value='" + (String)rs.elementAt(6) + "' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(9);' maxlength='60'></td>");
            printwriter.println("</tr>");
            printwriter.println("</table>");
            printwriter.println("<br>");
            printwriter.println("<table border='1' width='95%' align='center'>");
            printwriter.println("<tr>");
            printwriter.println("<td class='texttitulotabla' colspan='2' width='50%'>Contacto 1</td>");
            printwriter.println("<td class='texttitulotabla' colspan='2' width='50%'>Contacto 2</td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='15%'>Contacto</td>");
            printwriter.println("<td class='textdesttabla'><input name='contacto1' value='" + (String)rs.elementAt(7) + "' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(3);' maxlength='60'></td>");
            printwriter.println("<td class='textdesttabla' width='15%'>Contacto</td>");
            printwriter.println("<td class='textdesttabla'><input name='contacto2' value='" + (String)rs.elementAt(11) + "' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(3);' maxlength='60'></td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='15%'>Fono</td>");
            printwriter.println("<td class='textdesttabla'><input name='fono1' value='" + (String)rs.elementAt(8) + "' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(7);' maxlength='15'></td>");
            printwriter.println("<td class='textdesttabla' width='15%'>Fono</td>");
            printwriter.println("<td class='textdesttabla'><input name='fono2' value='" + (String)rs.elementAt(12) + "' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(7);' maxlength='15'></td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='15%'>Fax</td>");
            printwriter.println("<td class='textdesttabla'><input name='fax1' value='" + (String)rs.elementAt(9) + "' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(7);' maxlength='15'></td>");
            printwriter.println("<td class='textdesttabla' width='15%'>Fax</td>");
            printwriter.println("<td class='textdesttabla'><input name='fax2' value='" + (String)rs.elementAt(13) + "' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(7);' maxlength='15'></td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='15%'>E-Mail</td>");
            printwriter.println("<td class='textdesttabla'><input name='mail1' value='" + (String)rs.elementAt(10) + "' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(6);' maxlength='60'></td>");
            printwriter.println("<td class='textdesttabla' width='15%'>E-Mail</td>");
            printwriter.println("<td class='textdesttabla'><input name='mail2' value='" + (String)rs.elementAt(14) + "' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(6);' maxlength='60'></td>");
            printwriter.println("</tr>");
            printwriter.println("</table>");
            printwriter.println("<BR>");
            printwriter.println("<center><input type='button' name='grabar' value='Grabar' class='fondoinput' language='javascript' onclick='return valida();'></center>");
            String s3 = "select p.id,p.proyecto,p.abreviatura,u.nombre, p.estado from gdc.proyectos p, sgc.usuarios u where p.jefeproyecto = u.login and p.cliente = '" + RutCliente + "' ";
            int k = ADatos.ConsultaDB(s3);
            rs = ADatos.getResult();
            printwriter.println("<BR>");
            if(rs.size() > 0)
            {
                printwriter.println("<table border='1' width='95%' align='center'>");
                printwriter.println("<tr><td width='95%' colspan='4' class='texttituloarea'>Proyectos</td></tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='texttitulotabla'>Proyecto</td>");
                printwriter.println("<td class='texttitulotabla'>Abreviatura</td>");
                printwriter.println("<td class='texttitulotabla'>Jefe Proyecto</td>");
                printwriter.println("<td class='texttitulotabla'>Estado</td>");
                printwriter.println("</tr>");
                for(int i = 0; i < rs.size(); i += 5)
                {
                    String s1 = (String)rs.elementAt(i + 4);
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla'><a href='verproyecto.jsp?ID=" + (Integer)rs.elementAt(i) + "&CLI=" + RutCliente + "' target='datos'>" + (String)rs.elementAt(i + 1) + "</a></td>");
                    printwriter.println("<td class='textdesttabla'>" + (String)rs.elementAt(i + 2) + "</td>");
                    printwriter.println("<td class='textdesttabla'>" + (String)rs.elementAt(i + 3) + "</td>");
                    if(s1.compareTo("A") == 0)
                        printwriter.println("<td class='textdesttabla'>Activo</td>");
                    else
                    if(s1.compareTo("D") == 0)
                        printwriter.println("<td class='textdesttabla'>Detenido</td>");
                    else
                    if(s1.compareTo("G") == 0)
                        printwriter.println("<td class='textdesttabla'>En Garant\355a</td>");
                    else
                        printwriter.println("<td class='textdesttabla'>Cerrado</td>");
                    printwriter.println("</tr>");
                }

                printwriter.println("</table>");
            } else
            {
                printwriter.println("<table border='1' width='95%'>");
                printwriter.println("<tr><td width='95%' colspan='4' class='texttituloarea'>No existen proyectos disponibles</td></tr></table>");
            }
            printwriter.println("<BR>");
            printwriter.println("<center><input type='button' name='nuevo' value='Nuevo' class='fondoinput'  language='javascript' onclick='window.open(\"nuevoproyecto.jsp?TIPO=1&CLI=" + RutCliente + "\",\"datos\")'></center>");
        } else
        {
            printwriter.println("<table border='0' width='95%'>");
            printwriter.println("<tr><td width='95%' colspan='4' class='texttituloarea'>Cliente no Existe</td></tr>");
            printwriter.println("<tr></table>");
        }
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    Vector rsfile;
    String RutCliente;
}