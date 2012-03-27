package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class nuevoproyecto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public nuevoproyecto() {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        RutCliente = "";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s = (String)httpsession.getValue("SerapisUser");
        if(s != null && s.length() > 0)
        {
            RutCliente = httpservletrequest.getParameter("CLI");
            if(RutCliente != null)
            {
                printwriter.println("<html>");
                printwriter.println("<head>");
                printwriter.println("</head>");
                printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
                printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
                printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
                AFunc.cargamenu(printwriter, 1);
                printwriter.println("<form name='frmproyecto' method='POST' action=''>");
                CargaCliente(printwriter);
                printwriter.println("</form>");
                printwriter.println("<script language='javascript'>");
                printwriter.println("function CambiaUsuario()");
                printwriter.println("{");
                printwriter.println("var tipocargo;");
                printwriter.println("var indice;");
                printwriter.println("indice=0;");
                printwriter.println("tipocargo = document.frmproyecto.rolcargo.value;");
                printwriter.println("document.frmproyecto.jefeproyecto.options.length = 0;");
                int i = ADatos.ConsultaDB("select login,nombre,idcargo from sgc.usuarios order by nombre");
                rs = ADatos.getResult();
                for(int j = 0; j < rs.size(); j += 3)
                {
                    printwriter.println("if(tipocargo == \"" + (Integer)rs.elementAt(j + 2) + "\")");
                    printwriter.println("{");
                    printwriter.println("   variable = new Option(\"" + (String)rs.elementAt(j + 1) + "\",\"" + (String)rs.elementAt(j) + "\",\"\",\"\")");
                    printwriter.println("   document.frmproyecto.jefeproyecto.options[indice] = variable");
                    printwriter.println("   indice = indice + 1");
                    printwriter.println("}");
                }

                printwriter.println("}");
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
                printwriter.println("      document.frmproyecto.action=\"procesaproyecto.jsp?TIPO=1&CLI=" + RutCliente + "\";");
                printwriter.println("      document.frmproyecto.submit();");
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

    void CargaCliente(PrintWriter printwriter)
    {
        String s = "select abreviatura,razonsocial,direccion,telefono,fax,email,website,contacto1,fonocontacto1,faxcontacto1,mailcontacto1,contacto2,fonocontacto2,faxcontacto2,mailcontacto2 from sgc.clientes where rutcliente = '" + RutCliente + "'";
        //boolean flag = false;
        rs = new Vector();
        ADatos.connect();
        int k = ADatos.ConsultaDB(s);
        rs = ADatos.getResult();
        Integer integer = new Integer(0);
        if(rs.size() > 0)
        {
            printwriter.println("<table border='1' width='95%'  align='center'>");
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
            printwriter.println("<table border='1' width='95%'  align='center'>");
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
            printwriter.println("</tr>");
            printwriter.println("</table>");
            printwriter.println("<br>");
            printwriter.println("<table border='1' width='95%'  align='center'>");
            printwriter.println("<tr><td width='95%' colspan='4' class='texttituloarea'>Nuevo Proyecto</td></tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='15%'>Abreviatura</td>");
            printwriter.println("<td class='textdesttabla'><input name='abreviatura' value='' language='javascript' style='WIDTH: 100px' onkeypress='return ValidarCaracteres(12);' maxlength='15'></td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='15%'>Proyecto</td>");
            printwriter.println("<td class='textdesttabla'><input name='proyecto' value='' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(8);' maxlength='30'></td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='15%'>Jefe Proyecto</td>");
            printwriter.println("<td class='textdesttabla'>");
            printwriter.println("<select name='rolcargo' onchange='return CambiaUsuario();'>");
            int l = ADatos.ConsultaDB("SELECT ID,Descripcion from gdc.def_cargos ORDER by ID");
            rs = ADatos.getResult();
            for(int i = 0; i < rs.size(); i += 2)
                printwriter.println("<option value='" + (Integer)rs.elementAt(i) + "'>" + (String)rs.elementAt(i + 1) + "</option>");

            printwriter.println("</select>");
            printwriter.println("&nbsp;&nbsp;&nbsp;");
            Integer integer1 = (Integer)rs.elementAt(0);
            printwriter.println("<select name='jefeproyecto'>");
            l = ADatos.ConsultaDB("select login,nombre from sgc.usuarios where idcargo = " + integer1 + " order by nombre");
            rs = ADatos.getResult();
            for(int j = 0; j < rs.size(); j += 2)
                printwriter.println("<option value='" + (String)rs.elementAt(j) + "'>" + (String)rs.elementAt(j + 1) + "</option>");

            printwriter.println("</select></td>");
            printwriter.println("</tr>");
            printwriter.println("</table>");
            printwriter.println("<BR>");
            printwriter.println("<center><input type='button' name='grabar' value='Grabar' class='fondoinput' language='javascript' onclick='return valida();'></center");
        } else
        {
            printwriter.println("<table border='0' width='95%'  align='center'>");
            printwriter.println("<tr><td width='95%' colspan='4' class='texttituloarea'>Cliente no Existe</td></tr>");
            printwriter.println("<tr></table>");
        }
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    String RutCliente;
}