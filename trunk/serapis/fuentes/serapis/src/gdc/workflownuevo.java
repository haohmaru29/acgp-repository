package gdc;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class workflownuevo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public workflownuevo() {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        NombreUsuario = "";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s2 = (String)httpsession.getValue("SerapisUser");
        NombreUsuario = (String)httpsession.getValue("SerapisNombre");
        if(s2 != null && s2.length() > 0)
        {
            String s = "select login from sgc.perfil where login='" + s2 + "' and administrador='S'";
            rs = new Vector();
            ADatos.connect();
            int j = ADatos.ConsultaDB(s);
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                String s1 = "SELECT login,nombre from sgc.usuarios where estado='A' order by nombre";
                rs = new Vector();
                ADatos.connect();
                int k = ADatos.ConsultaDB(s1);
                rs = ADatos.getResult();
                printwriter.println("<html>");
                printwriter.println("<head>");
                printwriter.println("</head>");
                printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
                printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
                printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
                AFunc.cargamenu(printwriter, 1);
                printwriter.println("<form name='frmworkflow' method='POST' action='workflowprocesa.jsp?TIPO=NUEVO'>");
                printwriter.println("<table border='1' align='center' width='95%'>");
                printwriter.println("<tr><td align='center' class='texttituloarea' colspan='3'>WorkFlow</td></tr>");
                printwriter.println("<tr>");
                printwriter.println("   <td class='textdesttabla' width='20%'>Nombre</td>");
                printwriter.println("   <td class='textdesttabla' colspan='2'>");
                printwriter.println("   <INPUT type='text' name='txtnombre' value='' maxlength='60' style='WIDTH: 260px' onkeypress='return ValidarCaracteres(9);'>");
                printwriter.println("   <INPUT type='hidden' name='txtidwf' value='1'></td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("   <td class='textdesttabla' width='20%'>Fecha Creaci\363n</td>");
                printwriter.println("   <td class='textdesttabla' colspan='2'>" + AFunc.ObtieneFechaActual() + "</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("   <td class='textdesttabla' width='20%'>Usuario</td>");
                printwriter.println("   <td class='textdesttabla' colspan='2'>" + NombreUsuario + "</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("   <td class='textdesttabla' width='20%'>Tipo</td>");
                printwriter.println("   <td class='textdesttabla' colspan='2'>");
                printwriter.println("       <select name='seltipo'>");
                printwriter.println("          <option value='A'>Aprobaci\363n de Documentos</option>");
                printwriter.println("          <option value='E'>Aprobaci\363n de Registros</option>");
                printwriter.println("       </select>");
                printwriter.println("   </td>");
                printwriter.println("</tr>");
                printwriter.println("<tr><td class='texttitulotabla' colspan='3'>Paso 1<INPUT type='hidden' name='txtidpaso' value='1'></td></tr>");
                printwriter.println("<tr>");
                printwriter.println("   <td class='textdesttabla' width='20%'>Nombre</td>");
                printwriter.println("   <td class='textdesttabla' colspan='2'><INPUT type='text' name='txtpaso' value='' maxlength='60' style='WIDTH: 260px' onkeypress='return ValidarCaracteres(9);'></td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("   <td class='textdesttabla' width='20%'>Autorizador</td>");
                printwriter.println("   <td class='textdesttabla' colspan='2'>");
                printwriter.println("       <select name='selautoriza'>");
                for(int i = 0; i < rs.size(); i += 2)
                    printwriter.println("          <option value='" + (String)rs.elementAt(i) + "'>" + (String)rs.elementAt(i + 1) + "</option>");

                printwriter.println("       </select>");
                printwriter.println("   </td>");
                printwriter.println("</tr>");
                printwriter.println("<input name='selbackup' value=' ' type='hidden'>");
                printwriter.println("<input name='selfirma' value='N' type='hidden'>");
                printwriter.println("<input name='seluserfirma' value='' type='hidden'>");
                printwriter.println("<tr>");
                printwriter.println("   <td class='textdesttabla' width='20%' rowspan='2' valign='top'>Autorizaciones</td>");
                printwriter.println("   <td class='textdesttabla' width='20%'>Si Aprueba</td>");
                printwriter.println("   <td class='textdesttabla'>");
                printwriter.println("       <select name='selaprueba'>");
                printwriter.println("          <option value='0'>Paso Siguiente</option>");
                printwriter.println("       </select>");
                printwriter.println("   </td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("   <td class='textdesttabla' width='20%'>Redirecciona</td>");
                printwriter.println("   <td class='textdesttabla'>");
                printwriter.println("       <select name='selrechaza'>");
                printwriter.println("          <option value='-1'>No Aplica</option>");
                printwriter.println("       </select>");
                printwriter.println("   </td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("   <td align='center' class='textdesttabla' width='100%' colspan='3'><input type='button' name='btnok' value='Aceptar' class='fondoinput' language='javascript' onclick='return checkAll();'></td>");
                printwriter.println("</tr>");
                printwriter.println("</table>");
                printwriter.println("</form>");
                printwriter.println("<script language='javascript'>");
                printwriter.println("document.frmworkflow.seluserfirma.style.visibility = \"hidden\";");
                printwriter.println("function checkAll()");
                printwriter.println("{");
                printwriter.println("   if (document.frmworkflow.txtnombre.value != \"\")");
                printwriter.println("   {");
                printwriter.println("      if (document.frmworkflow.txtpaso.value != \"\")");
                printwriter.println("      {");
                printwriter.println("         if (document.frmworkflow.selautoriza.value != \"\")");
                printwriter.println("         {");
                printwriter.println("            document.frmworkflow.submit();");
                printwriter.println("         }");
                printwriter.println("         else");
                printwriter.println("         {");
                printwriter.println("            alert(\"Debe seleccionar un usuario que autoriza\");");
                printwriter.println("            document.frmworkflow.selautoriza.focus();");
                printwriter.println("         }");
                printwriter.println("       }");
                printwriter.println("       else");
                printwriter.println("       {");
                printwriter.println("         alert(\"Debe asignar un nombre al paso\");");
                printwriter.println("         document.frmworkflow.txtpaso.focus();");
                printwriter.println("       }");
                printwriter.println("   }");
                printwriter.println("   else");
                printwriter.println("   {");
                printwriter.println("      alert(\"Debe asignar un nombre al WorkFlow\");");
                printwriter.println("      document.frmworkflow.txtnombre.focus();");
                printwriter.println("   }");
                printwriter.println("}");
                printwriter.println("function ListaFirmantes()");
                printwriter.println("{");
                printwriter.println("var ValorFirmante;");
                printwriter.println("ValorFirmante = document.frmworkflow.selfirma.value;");
                printwriter.println("if(ValorFirmante == \"S\")");
                printwriter.println("{");
                printwriter.println("   document.frmworkflow.seluserfirma.style.visibility = \"visible\";");
                printwriter.println("}");
                printwriter.println("else");
                printwriter.println("{");
                printwriter.println("   document.frmworkflow.seluserfirma.style.visibility = \"hidden\";");
                printwriter.println("}");
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

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    String NombreUsuario;
}