package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class vertipodocumento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public vertipodocumento() {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        IdDoc = "";
        Tipo = "";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        String s = "";
        String s1 = "";
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s2 = (String)httpsession.getValue("SerapisUser");
        if(s2 != null && s2.length() > 0)
        {
            IdDoc = httpservletrequest.getParameter("ID");
            Tipo = httpservletrequest.getParameter("TIPO");
            if(Tipo == null || Tipo.length() == 0)
                Tipo = "VER";
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<form name='frmtipodoc' action='' method='post'>");
            CargaForm(printwriter);
            printwriter.println("<BR>");
            printwriter.println("</form>");
            printwriter.println("<script language=\"javascript\">");
            CargaScript(printwriter);
            printwriter.println("</script>");
            printwriter.println("</body>");
            printwriter.println("</html>");
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "CONF", 6);
        }
    }

    int CargaForm(PrintWriter printwriter)
    {
        int i = 0;
        String s1 = "";
        boolean flag = false;
        if(Tipo.compareTo("VER") == 0)
        {
            String s = "select descripcion from gdc.tipodocumentos where sigla = '" + IdDoc + "'";
            rs = new Vector();
            ADatos.connect();
            int j = ADatos.ConsultaDB(s);
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                String s2 = (String)rs.elementAt(0);
                printwriter.println("<table border='1' align='center' width='100%'>");
                printwriter.println("<tr><td width='95%' colspan='2' class='texttituloarea'>Editar Tipo Documento</td></tr>");
                printwriter.println("</table><BR>");
                printwriter.println("<table border='1' width='95%' align='center'>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla' width='25%'>C\363digo</td>");
                printwriter.println("<td class='textdesttabla' width='75%'><input name='idtipodoc' value='" + IdDoc + "' language='javascript' style='WIDTH: 35px' onkeypress='return ValidarCaracteres(12);' maxlength='1' type='hidden'>" + IdDoc + "</td></tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla' width='25%'>Descripci\363n</td>");
                printwriter.println("<td class='textdesttabla' width='75%'><input name='descripcion' value='" + s2 + "' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(3);' maxlength='30'></td>");
                printwriter.println("</tr></table>");
                printwriter.println("<br><center>");
                printwriter.println("<input type='button' name='anadirtipodoc' value='Grabar' class='fondoinput'  language='javascript' onclick='return grabatipodoc();'>");
                printwriter.println("<input type='button' name='eliminartipodoc' value='Eliminar' class='fondoinput'  language='javascript' onclick='return eliminatipodoc();'>");
                printwriter.println("</center>");
            }
        }
        if(Tipo.compareTo("ADD") == 0)
        {
            printwriter.println("<table border='1' width='95%' align='center'>");
            printwriter.println("<tr><td width='95%' colspan='2' class='texttituloarea'>Nuevo Tipo de Documento</td></tr>");
            printwriter.println("</table><BR>");
            printwriter.println("<table border='1' width='95%' align='center'>");
            printwriter.println("<tr><td class='textdesttabla' width='25%'>C\363digo</td>");
            printwriter.println("<td class='textdesttabla'>");
            printwriter.println("<input name='idtipodoc' value='' language='javascript' style='WIDTH: 35px' onkeypress='return ValidarCaracteres(12);' maxlength='1'></tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='25%'>Descripci\363n</td>");
            printwriter.println("<td class='textdesttabla'><input name='descripcion' value='' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(3);' maxlength='30'></td>");
            printwriter.println("</tr></table>");
            printwriter.println("<BR><center><input type='button' name='anadirtipodoc' value='Grabar' class='fondoinput'  language='javascript' onclick='return grabatipodoc();'></center>");
        }
        return i;
    }

    void CargaScript(PrintWriter printwriter)
    {
        printwriter.println("function grabatipodoc()");
        printwriter.println("{");
        printwriter.println("if(document.frmtipodoc.idtipodoc.value != \"\")");
        printwriter.println("{");
        printwriter.println("  if(document.frmtipodoc.descripcion.value != \"\")");
        printwriter.println("  {");
        if(Tipo.compareTo("VER") == 0)
            printwriter.println("    document.frmtipodoc.action=\"procesatipodoc.jsp?TIPO=UPD\";");
        else
            printwriter.println("    document.frmtipodoc.action=\"procesatipodoc.jsp?TIPO=ADD\";");
        printwriter.println("    document.frmtipodoc.submit();");
        printwriter.println("  }");
        printwriter.println("  else");
        printwriter.println("  {");
        printwriter.println("    alert(\"Debe especificar descripci\363n\")");
        printwriter.println("    document.frmtipodoc.descripcion.focus();");
        printwriter.println("  }");
        printwriter.println("}");
        printwriter.println("else");
        printwriter.println("{");
        printwriter.println("    alert(\"Debe especificar c\363digo\")");
        printwriter.println("    document.frmtipodoc.proceso.focus();");
        printwriter.println("}");
        printwriter.println("  return 0;");
        printwriter.println("}");
        printwriter.println("function eliminatipodoc()");
        printwriter.println("{");
        printwriter.println("  document.frmtipodoc.action=\"procesatipodoc.jsp?TIPO=DEL\";");
        printwriter.println("  document.frmtipodoc.submit();");
        printwriter.println("}");
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    String IdDoc;
    String Tipo;
}