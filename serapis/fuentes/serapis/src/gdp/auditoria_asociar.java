// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   auditoria_asociar.java

package gdp;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class auditoria_asociar extends HttpServlet
{

    public auditoria_asociar()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        EsAdmin = "N";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        Integer integer = new Integer(0);
        Integer integer2 = new Integer(0);
        Integer integer3 = new Integer(0);
        String s = "";
        Integer integer4 = new Integer(0);
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s2 = (String)httpsession.getValue("SerapisUser");
        if(s2 != null && s2.length() > 0)
        {
            ADatos.connect();
            int l = AFunc.ObtieneAnio();
            String s3 = httpservletrequest.getParameter("anio");
            String s4 = httpservletrequest.getParameter("ID");
            if(s3 == null || s3.length() == 0)
                s3 = "0";
            if(s4 == null || s4.length() == 0)
                s4 = "0";
            Integer integer5 = new Integer(s3);
            String s5 = "select area,fecha_programada,auditor_jefe,fecha_planificada,fecha_realizada,fecha_cerrada from gdc.plan_auditoria where anio = " + s3 + " order by id_plan";
            rs = new Vector();
            ADatos.connect();
            int i1 = ADatos.ConsultaDB(s5);
            rs = ADatos.getResult();
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<form name='frmplan' action='procesa_registro_auditoria.jsp' method='POST'>");
            printwriter.println("<INPUT type='hidden' name='nroaudit'>");
            printwriter.println("<INPUT type='hidden' name='nrodoc' value='" + s4.toString() + "'>");
            printwriter.println("<table border='0' width='80%'  align='center'>");
            printwriter.println("<tr><td width='80%' colspan='4' class='texttituloarea' align='center'>Asociar Registro de Auditoria</td></tr>");
            printwriter.println("</table>");
            printwriter.println("<table border='1' width='80%' align='center'>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' align='left' width='25%' valign='top'>A\361o</td>");
            printwriter.println("<td class='textdesttabla' align='left' width='75%' valign='top'><select name='anio' onchange='return Recarga();'>");
            s5 = "SELECT distinct anio from gdc.plan_auditoria ORDER by anio desc";
            i1 = ADatos.ConsultaDB(s5);
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                for(int i = 0; i < rs.size(); i++)
                {
                    Integer integer1 = (Integer)rs.elementAt(i);
                    if(integer2.longValue() == 0L)
                        integer2 = integer1;
                    if(integer1.compareTo(integer5) == 0)
                        printwriter.println("   <option value='" + integer1.toString() + "' selected>" + integer1.toString() + "</option>");
                    else
                        printwriter.println("   <option value='" + integer1.toString() + "'>" + integer1.toString() + "</option>");
                }

            } else
            {
                printwriter.println("   <option value='2005'>2005</option>");
            }
            printwriter.println("</select>");
            printwriter.println("</td></tr>");
            printwriter.println("<tr><td colspan='2'>&nbsp;</td></tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='texttituloarea' align='left' valign='top' colspan='2'>Auditor\355a</td>");
            printwriter.println("</td></tr>");
            printwriter.println("<tr><td colspan='2' class='textdesttabla'>");
            printwriter.println("<table border='1' width='100%'  align='center'>");
            printwriter.println("<tr>");
            printwriter.println("   <td width='5%' class='texttitulotabla'>&nbsp;</td>");
            printwriter.println("   <td width='15%' class='texttitulotabla'>Fecha Programada</td>");
            printwriter.println("   <td width='40%' class='texttitulotabla'>\301rea</td>");
            printwriter.println("   <td width='40%' class='texttitulotabla'>Auditor Jefe</td>");
            printwriter.println("</tr>");
            s5 = "SELECT sigla,descripcion from gdc.procesos order by descripcion";
            i1 = ADatos.ConsultaDB(s5);
            rs = ADatos.getResult();
            Vector vector = new Vector();
            vector = (Vector)rs.clone();
            s5 = "SELECT pa.id_plan, pa.fecha_programada, pa.area, pa.auditor_jefe, u.nombre ";
            s5 = s5 + " from gdc.plan_auditoria pa, sgc.usuarios u ";
            s5 = s5 + " where pa.auditor_jefe = u.login ";
            s5 = s5 + " and pa.fecha_programada >= " + integer2.toString() + "0101 and pa.fecha_programada <= " + integer2.toString() + "1231";
            i1 = ADatos.ConsultaDB(s5);
            rs = ADatos.getResult();
            for(int j = 0; j < rs.size(); j += 5)
            {
                String s1 = (String)rs.elementAt(j + 2); 
                printwriter.println("<tr>");
                printwriter.println("   <td width='5%' class='textdesttabla'><INPUT type='radio' name='nro_audit' onclick='document.frmplan.nroaudit.value = " + ((Integer)rs.elementAt(j)).toString() + "'></td>");
                printwriter.println("   <td width='15%' class='textdesttabla'>" + AFunc.ConstruyeFecha(((Integer)rs.elementAt(j + 1)).toString(), "/", "dmy") + "</td>");
                printwriter.println("   <td width='40%' class='textdesttabla'>");
                if(s1.compareTo("00") == 0)
                    printwriter.println("Todos los Procesos");
                else
                if(s1.compareTo("01") == 0)
                {
                    printwriter.println("Auditor\355a Externa");
                } else
                {
                    for(int k = 0; k < vector.size(); k += 2)
                        if(s1.compareTo((String)vector.elementAt(k)) == 0)
                            printwriter.println((String)vector.elementAt(k + 1));

                }
                printwriter.println("   </td>");
                printwriter.println("   <td width='40%' class='textdesttabla'>" + (String)rs.elementAt(j + 4) + "</td>");
                printwriter.println("</tr>");
            }

            printwriter.println("</table>");
            printwriter.println("</td><tr>");
            printwriter.println("</table>");
            printwriter.println("<br>");
            printwriter.println("<center>");
            printwriter.println("<input type='submit' name='btnGRabar' value='Grabar' class='fondoinput'>");
            printwriter.println("<input type='button' name='btnVolver' value='Volver' class='fondoinput' language='javascript' onclick='window.open(\"registro_auditoria.jsp\",\"datos\");'>");
            printwriter.println("</center>");
            printwriter.println("</form>");
            printwriter.println("<script language=\"javascript\">");
            printwriter.println("function Recarga()");
            printwriter.println("{");
            printwriter.println("   document.frmplan.action=\"auditoria_asociar.jsp\";");
            printwriter.println("   document.frmplan.submit();");
            printwriter.println("}");
            printwriter.println("</script>");
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