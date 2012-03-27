package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class verproyecto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public verproyecto() {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        RutCliente = "";
        IdProyecto = "";
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
            IdProyecto = httpservletrequest.getParameter("ID");
            if(RutCliente != null && RutCliente.length() > 0)
            {
                printwriter.println("<html>");
                printwriter.println("<head>");
                printwriter.println("</head>");
                printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
                printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
                printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
                AFunc.cargamenu(printwriter, 1);
                printwriter.println("<form name='frmproyecto' method='POST' action=''>");
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
                printwriter.println("  var abrevproyecto = document.frmproyecto.abreviatura.value;");
                printwriter.println("  var proyecto = document.frmproyecto.proyecto.value;");
                printwriter.println("  var sigue = false;");
                printwriter.println("  if(abrevproyecto == \"\")");
                printwriter.println("  {");
                printwriter.println("    alert(\"Debe ingresar abreviatura\");");
                printwriter.println("    document.frmproyecto.abreviatura.focus();");
                printwriter.println("  }");
                printwriter.println("  else");
                printwriter.println("  {");
                printwriter.println("    if(proyecto == \"\")");
                printwriter.println("    {");
                printwriter.println("      alert(\"Debe ingresar proyecto\");");
                printwriter.println("      document.frmproyecto.proyecto.focus();");
                printwriter.println("    }");
                printwriter.println("    else");
                printwriter.println("    {");
                printwriter.println("      if(document.frmproyecto.chkbackup.checked == true)");
                printwriter.println("      {");
                printwriter.println("        document.frmproyecto.hddbackup.value = \"S\";");
                printwriter.println("        if(document.frmproyecto.backupjp.value != \"\")");
                printwriter.println("          sigue = true;");
                printwriter.println("        else");
                printwriter.println("        {");
                printwriter.println("          alert(\"Debe especificar un Backup para Habilitarlo\")");
                printwriter.println("        }");
                printwriter.println("      }");
                printwriter.println("      else");
                printwriter.println("      {");
                printwriter.println("        document.frmproyecto.hddbackup.value = \"N\";");
                printwriter.println("        sigue = true;");
                printwriter.println("      }");
                printwriter.println("      if(sigue == true)");
                printwriter.println("      {");
                printwriter.println("        document.frmproyecto.action=\"procesaproyecto.jsp?TIPO=2&CODPROY=" + IdProyecto + "\";");
                printwriter.println("        document.frmproyecto.submit();");
                printwriter.println("      }");
                printwriter.println("    }");
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
        String s = "select abreviatura,razonsocial,direccion,telefono,fax,email,website,contacto1,fonocontacto1,faxcontacto1,mailcontacto1,contacto2,fonocontacto2,faxcontacto2,mailcontacto2 from sgc.clientes where rutcliente = '" + RutCliente + "'";
        boolean flag = false;
        String s2 = "";
        String s4 = "";
        String s6 = "";
        String s8 = "N";
        String s10 = "";
        rs = new Vector();
        ADatos.connect();
        int k = ADatos.ConsultaDB(s);
        rs = ADatos.getResult();
        if(rs.size() > 0)
        {
            printwriter.println("<table border='1' width='95%' align='center'>");
            printwriter.println("<tr><td width='95%' colspan='4' class='texttituloarea'>Editar Cliente</td></tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='15%'>Rut</td>");
            printwriter.println("<td class='textdesttabla'>" + RutCliente + "</td>");
            printwriter.println("<td class='textdesttabla' width='15%'>Abreviatura</td>");
            printwriter.println("<td class='textdesttabla'>" + (String)rs.elementAt(0) + "</td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='15%'>Razon Social</td>");
            printwriter.println("<td class='textdesttabla'>" + (String)rs.elementAt(1) + "</td>");
            printwriter.println("<td class='textdesttabla' width='15%'>Direcci\363n</td>");
            printwriter.println("<td class='textdesttabla'>" + (String)rs.elementAt(2) + "</td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='15%'>Fono</td>");
            printwriter.println("<td class='textdesttabla'>" + (String)rs.elementAt(3) + "</td>");
            printwriter.println("<td class='textdesttabla' width='15%'>Fax</td>");
            printwriter.println("<td class='textdesttabla'>" + (String)rs.elementAt(4) + "</td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='15%'>E-Mail</td>");
            printwriter.println("<td class='textdesttabla'>" + (String)rs.elementAt(5) + "</td>");
            printwriter.println("<td class='textdesttabla' width='15%'>Web-Site</td>");
            printwriter.println("<td class='textdesttabla'>" + (String)rs.elementAt(6) + "</td>");
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
            printwriter.println("<td class='textdesttabla'>" + (String)rs.elementAt(7) + "</td>");
            printwriter.println("<td class='textdesttabla' width='15%'>Contacto</td>");
            printwriter.println("<td class='textdesttabla'>" + (String)rs.elementAt(11) + "</td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='15%'>Fono</td>");
            printwriter.println("<td class='textdesttabla'>" + (String)rs.elementAt(8) + "</td>");
            printwriter.println("<td class='textdesttabla' width='15%'>Fono</td>");
            printwriter.println("<td class='textdesttabla'>" + (String)rs.elementAt(12) + "</td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='15%'>Fax</td>");
            printwriter.println("<td class='textdesttabla'>" + (String)rs.elementAt(9) + "</td>");
            printwriter.println("<td class='textdesttabla' width='15%'>Fax</td>");
            printwriter.println("<td class='textdesttabla'>" + (String)rs.elementAt(13) + "</td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='15%'>E-Mail</td>");
            printwriter.println("<td class='textdesttabla'>" + (String)rs.elementAt(10) + "</td>");
            printwriter.println("<td class='textdesttabla' width='15%'>E-Mail</td>");
            printwriter.println("<td class='textdesttabla'>" + (String)rs.elementAt(14) + "</td>");
            printwriter.println("</tr></table>");
            printwriter.println("<BR><center><input type='button' name='grabar' value='Grabar' class='fondoinput' language='javascript' onclick='return valida();'></center>");
            printwriter.println("</table>");
            String s1 = "select p.proyecto,p.abreviatura,u.nombre,p.jefeproyecto, p.jpbackup, p.validobackup, p.estado from gdc.proyectos p, sgc.usuarios u where p.jefeproyecto = u.login and id = " + IdProyecto;
            int l = ADatos.ConsultaDB(s1);
            rs = ADatos.getResult();
            printwriter.println("<BR>");
            if(rs.size() > 0)
            {
                String s7 = (String)rs.elementAt(4);
                String s9 = (String)rs.elementAt(5);
                String s11 = (String)rs.elementAt(6);
                printwriter.println("<table border='1' width='95%' align='center'>");
                printwriter.println("<tr><td width='95%' colspan='4' class='texttituloarea'>Editar Proyecto</td></tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla' width='15%'>Abreviatura</td>");
                printwriter.println("<td class='textdesttabla'><input name='abreviatura' value='" + (String)rs.elementAt(1) + "' language='javascript' style='WIDTH: 100px' onkeypress='return ValidarCaracteres(12);' maxlength='6' disabled></td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla' width='15%'>Proyecto</td>");
                printwriter.println("<td class='textdesttabla'><input name='proyecto' value='" + (String)rs.elementAt(0) + "' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(8);' maxlength='30'></td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                String s12 = (String)rs.elementAt(3);
                printwriter.println("<td class='textdesttabla' width='15%'>Jefe Proyecto</td>");
                printwriter.println("<td class='textdesttabla'>");
                printwriter.println("<select name='jefeproyecto'>");
                int i1 = ADatos.ConsultaDB("select login,nombre from sgc.usuarios order by nombre");
                rs = ADatos.getResult();
                for(int i = 0; i < rs.size(); i += 2)
                    if(s12.compareTo((String)rs.elementAt(i)) == 0)
                        printwriter.println("<option value='" + (String)rs.elementAt(i) + "' selected>" + (String)rs.elementAt(i + 1) + "</option>");
                    else
                        printwriter.println("<option value='" + (String)rs.elementAt(i) + "'>" + (String)rs.elementAt(i + 1) + "</option>");

                printwriter.println("</select>");
                printwriter.println("</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla' width='15%'>Backup Jefe Proyecto</td>");
                printwriter.println("<td class='textdesttabla' width='85%'>");
                printwriter.println("<select name='backupjp'>");
                printwriter.println("<option value=''>[Seleccione Backup]</option>");
                for(int j = 0; j < rs.size(); j += 2)
                {
                    String s3 = (String)rs.elementAt(j);
                    String s5 = (String)rs.elementAt(j + 1);
                    if(s3.compareTo(s7) == 0)
                        printwriter.println("<option value='" + s3 + "' selected>" + s5 + "</option>");
                    else
                        printwriter.println("<option value='" + s3 + "'>" + s5 + "</option>");
                }

                printwriter.println("</select>");
                printwriter.println("&nbsp;&nbsp;&nbsp;");
                if(s9.compareTo("S") == 0)
                    printwriter.println("<INPUT type='checkbox' name='chkbackup' checked>Habilitado");
                else
                    printwriter.println("<INPUT type='checkbox' name='chkbackup'>Habilitado");
                printwriter.println("<INPUT type='hidden' name='hddbackup' value=''>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla' width='15%'>Estado</td>");
                printwriter.println("<td class='textdesttabla' width='85%'>");
                printwriter.println("<select name='lstestado'>");
                if(s11.compareTo("A") == 0)
                    printwriter.println("  <option value='A' selected>Activo</option>");
                else
                    printwriter.println("  <option value='A'>Activo</option>");
                if(s11.compareTo("D") == 0)
                    printwriter.println("  <option value='D' selected>Detenido</option>");
                else
                    printwriter.println("  <option value='D'>Detenido</option>");
                if(s11.compareTo("G") == 0)
                    printwriter.println("  <option value='G' selected>En Garant\355a</option>");
                else
                    printwriter.println("  <option value='G'>En Garant\355a</option>");
                if(s11.compareTo("C") == 0)
                    printwriter.println("  <option value='C' selected>Cerrado</option>");
                else
                    printwriter.println("  <option value='C'>Cerrado</option>");
                printwriter.println("</select>");
                printwriter.println("</tr>");
                printwriter.println("</table>");
                printwriter.println("<BR><center><input type='button' name='grabar' value='Grabar' class='fondoinput' language='javascript' onclick='return valida();'></center>");
            } else
            {
                printwriter.println("<table border='0' width='95%'>");
                printwriter.println("<tr><td width='95%' colspan='4' class='texttituloarea'>No existen proyectos disponibles</td></tr></table>");
            }
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
    String IdProyecto;
}