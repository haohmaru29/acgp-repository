package sad;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class nuevopermiso extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public nuevopermiso() {
        ADatos = new AccDataBase();
        AFunc = new funciones();
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException {
        String s1 = "";
        String s3 = "";
        Integer integer = new Integer(0);
        String s5 = "";
        String s7 = "";
        String s9 = "";
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        UserReg = (String)httpsession.getValue("SerapisUser");
        if(UserReg != null && UserReg.length() > 0)
        {
            ADatos.connect();
            rs = new Vector();
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<form name='frmpermisos' action='' method='post'>");
            printwriter.println("<table border='1' width='95%' align='center'>");
            printwriter.println("<tr><td align='center' class='texttituloarea' colspan='2'>Permisos por Proceso</td></tr>");
            printwriter.println("<tr><td align='center' class='textdesttabla' colspan='2'>&nbsp;</td></tr>");
            printwriter.println("<tr><td align='left' class='textdesttabla'><B>Proceso</B></td>");
            printwriter.println("<td align='left' class='textdesttabla'>");
            String s = "SELECT sigla,descripcion from gdc.procesos where lider = '" + UserReg + "' or (backuplider = '" + UserReg + "' and validobackup = 'S') order by descripcion ";
            int l = ADatos.ConsultaDB(s);
            rs = ADatos.getResult();
            printwriter.println("<select name='lstprocesos'>");
            for(int i = 0; i < rs.size(); i += 2)
            {
                String s2 = (String)rs.elementAt(i);
                String s4 = (String)rs.elementAt(i + 1);
                printwriter.println("<option value='" + s2 + "'>" + s4 + "</option>");
            }

            printwriter.println("</select>");
            printwriter.println("</td>");
            printwriter.println("</tr>");
            printwriter.println("<tr><td align='left' class='textdesttabla'><B>Perfil</B></td>");
            printwriter.println("<td align='left' class='textdesttabla'>");
            s = "SELECT idperfil,perfil from sgc.perfiles where estado='A'";
            l = ADatos.ConsultaDB(s);
            rs = ADatos.getResult();
            printwriter.println("<select name='lstperfiles'>");
            printwriter.println("<option value=''>[Seleccione Perfil]</option>");
            for(int j = 0; j < rs.size(); j += 2)
            {
                Integer integer1 = (Integer)rs.elementAt(j);
                String s6 = (String)rs.elementAt(j + 1);
                printwriter.println("<option value='" + integer1 + "'>" + s6 + "</option>");
            }

            printwriter.println("</select>");
            printwriter.println("</td>");
            printwriter.println("</tr>");
            printwriter.println("<tr><td align='left' class='textdesttabla'><B>Usuarios</B></td>");
            printwriter.println("<td align='left' class='textdesttabla'>");
            s = "SELECT login,nombre from sgc.usuarios where estado='A' order by nombre";
            l = ADatos.ConsultaDB(s);
            rs = ADatos.getResult();
            printwriter.println("<select name='lstusuarios'>");
            printwriter.println("<option value=''>[Seleccione Usuario]</option>");
            for(int k = 0; k < rs.size(); k += 2)
            {
                String s8 = (String)rs.elementAt(k);
                String s10 = (String)rs.elementAt(k + 1);
                printwriter.println("<option value='" + s8 + "'>" + s10 + "</option>");
            }

            printwriter.println("</select>");
            printwriter.println("</td>");
            printwriter.println("</tr>");
            printwriter.println("<tr><td align='left' class='textdesttabla'><B>Acceso</B><input type='hidden' name='hddopcion' language='javascript' value='00100'></td>");
            printwriter.println("<td align='left' class='textdesttabla'>");
            printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"10000\";'>Control Total<BR>");
            printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"01000\";'>Escritura<BR>");
            printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"00100\";'checked>S\363lo Lectura<BR>");
            printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"00010\";'>Eliminaci\363n<BR>");
            printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"00001\";'>Sin acceso<BR>");
            printwriter.println("</td>");
            printwriter.println("</tr>");
            printwriter.println("</table>");
            printwriter.println("<BR><BR><CENTER>");
            printwriter.println("<input type='button' class='fondoinput' name='btnok' value='Aceptar' language='javascript' onclick='return GrabarProceso();'>");
            printwriter.println("<input type='button' class='fondoinput' name='btnvolver' value='Cancelar' language='javascript' onclick=\"window.open('permisos.jsp?TIPO=PERM&SUBTIPO=P','datos');\">");
            printwriter.println("</CENTER>");
            printwriter.println("</form>");
            printwriter.println("<script languaje='javascript'>");
            CargaScript(printwriter);
            printwriter.println("</script>");
            printwriter.println("</body>");
            printwriter.println("</html>");
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "SAD", 6);
        }
    }

    void CargaScript(PrintWriter printwriter) {
        printwriter.println("function GrabarProceso()");
        printwriter.println("{");
        printwriter.println("if (document.frmpermisos.lstprocesos.value != \"\")");
        printwriter.println("{");
        printwriter.println("if ((document.frmpermisos.lstperfiles.value != \"\") || (document.frmpermisos.lstusuarios.value != \"\"))");
        printwriter.println("{");
        printwriter.println("document.frmpermisos.action=\"procesapermisos.jsp?TIPO=1\";");
        printwriter.println("document.frmpermisos.submit();");
        printwriter.println("}");
        printwriter.println("else");
        printwriter.println("{");
        printwriter.println("alert(\"Debe seleccionar un perfil o usuario\");");
        printwriter.println("}");
        printwriter.println("}");
        printwriter.println("else");
        printwriter.println("{");
        printwriter.println("alert(\"Debe seleccionar un proceso\");");
        printwriter.println("}");
        printwriter.println("return true;");
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
    String UserReg;
}