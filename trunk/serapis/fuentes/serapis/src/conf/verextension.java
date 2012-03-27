package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class verextension extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public verextension() {
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
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            if(Tipo.compareTo("ADD") == 0)
                printwriter.println("<form name='frmextension' method='POST' enctype='multipart/form-data'>");
            else
                printwriter.println("<form name='frmextension' action='' method='post'>");
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
        String s3 = "";
        boolean flag = false;
        if(Tipo.compareTo("VER") == 0)
        {
            String s = "select descripcion,imagen from gdc.extension where sigla = '" + IdDoc + "'";
            rs = new Vector();
            ADatos.connect();
            int j = ADatos.ConsultaDB(s);
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                String s2 = (String)rs.elementAt(0);
                String s4 = (String)rs.elementAt(1);
                printwriter.println("<table border='1' align='center' width='95%'>");
                printwriter.println("<tr><td width='95%' colspan='4' class='texttituloarea'>Editar Tipo Extensi\363n</td></tr>");
                printwriter.println("</table><BR>");
                printwriter.println("<table border='1' width='95%' align='center'>");
                printwriter.println("<td class='textdesttabla' width='15%'>Imagen</td>");
                printwriter.println("<td class='textdesttabla' width='85%'><img src='../images/ext_" + s4 + "' border='0'></td></tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>C\363digo</td>");
                printwriter.println("<td class='textdesttabla'><input name='idextension' value='" + IdDoc + "' language='javascript' style='WIDTH: 35px' onkeypress='' maxlength='1' type='hidden'>" + IdDoc + "</td></tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Descripci\363n</td>");
                printwriter.println("<td class='textdesttabla'><input name='descripcion' value='" + s2 + "' language='javascript' style='WIDTH: 220px' onkeypress='' maxlength='30'></td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla' colspan='2'>Recuerde que el nombre f\355sico de la imagen debe llevar el prefijo <B>ext_</B>.<BR>Si la imagen no es visible, SERAPIS desplegar\341 por defecto una imagen propia cuando sea usada.</td>");
                printwriter.println("</tr>");
                printwriter.println("</table>");
                printwriter.println("<br><center><input type='button' name='anadirtipodoc' value='Grabar' class='fondoinput'  language='javascript' onclick='return grabatipodoc();'>");
                printwriter.println("<input type='button' name='eliminarextension' value='Eliminar' class='fondoinput'  language='javascript' onclick='return eliminaextension();'>");
                printwriter.println("</center>");
            }
        }
        if(Tipo.compareTo("ADD") == 0)
        {
            printwriter.println("<table border='1' width='95%' align='center'>");
            printwriter.println("<tr><td width='95%' colspan='4' class='texttituloarea'>Nuevo Tipo de Extensi\363n</td></tr>");
            printwriter.println("</table><BR>");
            printwriter.println("<table border='1' width='95%' align='center'>");
            printwriter.println("<tr><td class='textdesttabla' width='12%'>C\363digo</td>");
            printwriter.println("<td class='textdesttabla'>");
            printwriter.println("<input name='idextension' value='' language='javascript' style='WIDTH: 35px' onkeypress='' maxlength='3'></tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='12%'>Descripci\363n</td>");
            printwriter.println("<td class='textdesttabla'><input name='descripcion' value='' language='javascript' style='WIDTH: 220px' onkeypress='' maxlength='30'></td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='12%'>Imagen</td>");
            printwriter.println("<td class='textdesttabla'><input name='archivo' style='WIDTH: 420px' type='file'></td>");
            printwriter.println("</tr>");
            printwriter.println("<tr><td class='textdesttabla' colspan='2'>Recuerde: SERAPIS cambiar\341 el nombre del archivo a la sigla correspondiente adosando el prefijo <B>ext_</B> a la imagen.<BR>Si el c\363digo ingresado existe, este ser\341 reemplazado.<BR>El tama\361o de la imagen no debe ser superior a 32x32 pixels.<BR>La extensi\363n de la imagen debe ser <b>.gif</b></td></tr></table>");
            printwriter.println("<br><center><input type='button' name='anadirtipodoc' value='Grabar' class='fondoinput'  language='javascript' onclick='return grabatipodoc();'></center>");
        }
        return i;
    }

    void CargaScript(PrintWriter printwriter)
    {
        printwriter.println("function grabatipodoc()");
        printwriter.println("{");
        printwriter.println("if(document.frmextension.idextension.value != \"\")");
        printwriter.println("{");
        printwriter.println("  if(document.frmextension.descripcion.value != \"\")");
        printwriter.println("  {");
        if(Tipo.compareTo("VER") == 0)
            printwriter.println("    document.frmextension.action=\"procesaextension.jsp?TIPO=UPD\";");
        else
            printwriter.println("    document.frmextension.action=\"procesaextension.jsp?TIPO=ADD\";");
        printwriter.println("    document.frmextension.submit();");
        printwriter.println("  }");
        printwriter.println("  else");
        printwriter.println("  {");
        printwriter.println("    alert(\"Debe especificar descripci\363n\")");
        printwriter.println("    document.frmextension.descripcion.focus();");
        printwriter.println("  }");
        printwriter.println("}");
        printwriter.println("else");
        printwriter.println("{");
        printwriter.println("    alert(\"Debe especificar c\363digo\")");
        printwriter.println("    document.frmextension.idextension.focus();");
        printwriter.println("}");
        printwriter.println("  return 0;");
        printwriter.println("}");
        printwriter.println("function eliminaextension()");
        printwriter.println("{");
        printwriter.println("  document.frmextension.action=\"procesaextension.jsp?TIPO=DEL\";");
        printwriter.println("  document.frmextension.submit();");
        printwriter.println("  return 0;");
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