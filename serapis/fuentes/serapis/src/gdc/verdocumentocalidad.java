package gdc;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class verdocumentocalidad extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public verdocumentocalidad()
    {
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
            String s1 = httpservletrequest.getParameter("ID");
            if(s1 == null || s1.length() == 0)
                s1 = "0";
            Integer integer = new Integer(s1);
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<form name='doccalidad' method='POST' action='../conf/procesadocumentocalidad.jsp?TIPO=E&DOC=" + integer + "'>");
            PreparaPublica(printwriter, integer.intValue());
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
            printwriter.println("  var Dato = document.doccalidad.descripcion.value;");
            printwriter.println("  if(ConAcento(Dato)!=0)");
            printwriter.println("  {");
            printwriter.println("    alert(\"El nombre no puede tener letras acentuadas\");");
            printwriter.println("    document.doccalidad.descripcion.focus();");
            printwriter.println("  }");
            printwriter.println("  else");
            printwriter.println("  {");
            printwriter.println("  window.open(\"procesadocumentocalidad.jsp?TIPO=M&DOC=" + integer.intValue() + "\",\"datos\");");
            printwriter.println("  }");
            printwriter.println("}");
            printwriter.println("</script>");
            printwriter.println("</body>");
            printwriter.println("</html>");
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "GDC", 6);
        }
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    void PreparaPublica(PrintWriter printwriter, int i)
    {
        rs = new Vector();
        rsfile = new Vector();
        ADatos.connect();
        String s = "select tipodocumento,proceso,descripcion,tiemporetencion,tiempomedida,tipo from gdc.documentoscalidad where id = " + i;
        int j = ADatos.ConsultaDB(s);
        rsfile = ADatos.getResult();
        if(j == 1)
        {
            String s1 = (String)rsfile.elementAt(0);
            String s2 = (String)rsfile.elementAt(1);
            String s3 = (String)rsfile.elementAt(2);
            Integer integer = (Integer)rsfile.elementAt(3);
            String s4 = (String)rsfile.elementAt(4);
            String s5 = (String)rsfile.elementAt(5);
            printwriter.println("<table border='1' align='center' width='95%'>");
            if(s5.compareTo("D") == 0)
                printwriter.println("<tr><td class='texttituloarea' colspan='4'>Ver Documento</td></tr>");
            else
                printwriter.println("<tr><td class='texttituloarea' colspan='4'>Ver Registro</td></tr>");
            printwriter.println("</table>");
            printwriter.println("<TABLE border='1' width='95%' align='center'>");
            printwriter.println("<tr>");
            if(s5.compareTo("D") == 0)
                printwriter.println("<td colspan='3' class='texttitulotabla'>Informaci\363n del Documento</td>");
            else
                printwriter.println("<td colspan='3' class='texttitulotabla'>Informaci\363n del Registro</td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'>Proceso</td>");
            printwriter.println("<td class='textdesttabla'>");
            int k = ADatos.ConsultaDB("select sigla,descripcion from gdc.procesos where sigla = '" + s2 + "'");
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                String s6 = (String)rs.elementAt(1);
                printwriter.println(s6);
            }
            printwriter.println("</td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'>Tipo Documento</td>");
            printwriter.println("<td class='textdesttabla'>");
            rs = new Vector();
            rs.clear();
            k = ADatos.ConsultaDB("select sigla,descripcion from gdc.tipodocumentos where sigla = '" + s1 + "'");
            rs = ADatos.getResult();
            if(rs.size() > 0)
                printwriter.println((String)rs.elementAt(1));
            printwriter.println("</td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'>Nombre</td>");
            printwriter.println("<td class='textdesttabla'> " + s3 + "</td></tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'>Tiempo retenci\363n</td>");
            if(integer.intValue() > 0)
                printwriter.println("<td class='textdesttabla'>" + integer.toString() + "&nbsp;" + s4);
            else
                printwriter.println("<td class='textdesttabla'>&nbsp;");
            printwriter.println("</td>");
            printwriter.println("</tr>");
            printwriter.println("</table>");
        } else
        {
            printwriter.println("<tr>");
            printwriter.println("<td colspan='2' class='textdesttabla' align='center'>Documento no existe</td>");
            printwriter.println("</td>");
            printwriter.println("</tr>");
        }
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    Vector rsfile;
}