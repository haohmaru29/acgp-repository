// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   agrega_plan.java

package gdp;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class agrega_plan extends HttpServlet
{

    public agrega_plan()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        EsAdmin = "N";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        String s = "";
        String s1 = "";
        String s2 = "";
        Integer integer = new Integer(0);
        String s3 = "";
        Integer integer1 = new Integer(0);
        String s4 = "";
        Integer integer2 = new Integer(0);
        Integer integer3 = new Integer(0);
        Integer integer4 = new Integer(0);
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s5 = (String)httpsession.getValue("SerapisUser");
        if(s5 != null && s5.length() > 0)
        {
            ADatos.connect();
            int k = AFunc.ObtieneAnio();
            String s6 = httpservletrequest.getParameter("selAnio");
            if(s6 == null || s6.length() == 0)
                s6 = "" + k;
            String s7 = "select area,fecha_programada,auditor_jefe,fecha_planificada,fecha_realizada,fecha_cerrada from gdc.plan_auditoria where anio = " + s6 + " order by id_plan";
            rs = new Vector();
            ADatos.connect();
            int l = ADatos.ConsultaDB(s7);
            rs = ADatos.getResult();
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<form name='frmplan' action='procesa_plan.jsp' method='POST'>");
            printwriter.println("<table border='0' width='80%'  align='center'>");
            printwriter.println("<tr><td width='80%' colspan='4' class='texttituloarea' align='center'>Crear Plan de Auditorias</td></tr>");
            printwriter.println("</table>");
            printwriter.println("<table border='1' width='80%' align='center'>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' align='left' width='25%' valign='top'>A\361o</td>");
            printwriter.println("<td class='textdesttabla' align='left' width='75%' valign='top'><INPUT type='text' name='txtanio' value='' maxlength='4' style='WIDTH: 80px' onkeypress='return ValidarCaracteres(4);'></td>");
            printwriter.println("</tr>");
            s7 = "SELECT sigla,descripcion from gdc.procesos order by descripcion";
            l = ADatos.ConsultaDB(s7);
            rs = ADatos.getResult();
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' align='left' valign='top'>\301rea a Auditar</td>");
            printwriter.println("<td class='textdesttabla' align='left' valign='top'>");
            printwriter.println("<select name='selArea'>");
            printwriter.println("   <option value='00'>Todos los Procesos</option>");
            printwriter.println("   <option value='01'>Auditor\355a Externa</option>");
            for(int i = 0; i < rs.size(); i += 2)
                printwriter.println("   <option value='" + (String)rs.elementAt(i) + "'>" + (String)rs.elementAt(i + 1) + "</option>");

            printwriter.println("</select>");
            printwriter.println("</td></tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' align='left' valign='top'>Fecha Programada</td>");
            printwriter.println("<td class='textdesttabla' align='left' valign='top'><INPUT type='text' name='txtfecha' value='' maxlength='10' style='WIDTH: 80px' onkeypress='return ValidarCaracteres(11);'></td>");
            printwriter.println("</tr>");
            s7 = "SELECT login,nombre from sgc.usuarios order by nombre";
            l = ADatos.ConsultaDB(s7);
            rs = ADatos.getResult();
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' align='left' valign='top'>Auditor Jefe</td>");
            printwriter.println("<td class='textdesttabla' align='left' valign='top'>");
            printwriter.println("<select name='selAuditorJefe'>");
            for(int j = 0; j < rs.size(); j += 2)
                printwriter.println("   <option value='" + (String)rs.elementAt(j) + "'>" + (String)rs.elementAt(j + 1) + "</option>");

            printwriter.println("</select>");
            printwriter.println("</td></tr>");
            printwriter.println("</table>");
            printwriter.println("<br>");
            printwriter.println("<br><center>");
            printwriter.println("<input type='button' name='btnGRabar' value='Grabar' class='fondoinput'  language='javascript' onclick='return Valida();'>");
            printwriter.println("<input type='button' name='btnVolver' value='Volver' class='fondoinput' language='javascript' onclick='window.open(\"plan_auditoria.jsp\",\"datos\");'>");
            printwriter.println("</center>");
            printwriter.println("</form>");
            printwriter.println("<script language='javascript'>");
            printwriter.println("function Valida()");
            printwriter.println("{");
            printwriter.println("   var Anio;");
            printwriter.println("   var Fecha;");
            printwriter.println("   Anio = document.frmplan.txtanio.value;");
            printwriter.println("   Fecha = document.frmplan.txtfecha.value;");
            printwriter.println("   if (Anio.length==4)");
            printwriter.println("   {");
            printwriter.println("      if(Fecha.length==10)");
            printwriter.println("      {");
            printwriter.println("         if(EsFecha(Fecha)==1)");
            printwriter.println("         {");
            printwriter.println("            document.frmplan.submit();");
            printwriter.println("         }");
            printwriter.println("         else");
            printwriter.println("         {");
            printwriter.println("            alert(\"Fecha Inv\341lida. Formato dd/mm/yyyy\");");
            printwriter.println("            document.frmplan.txtfecha.focus();");
            printwriter.println("         }");
            printwriter.println("      }");
            printwriter.println("      else");
            printwriter.println("      {");
            printwriter.println("         alert(\"Fecha Inv\341lida. Formato dd/mm/yyyy\");");
            printwriter.println("         document.frmplan.txtfecha.focus();");
            printwriter.println("      }");
            printwriter.println("   }");
            printwriter.println("   else");
            printwriter.println("   {");
            printwriter.println("      alert(\"A\361o inv\341lido\");");
            printwriter.println("      document.frmplan.txtanio.focus();");
            printwriter.println("   }");
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

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    String EsAdmin;
}