// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   plan_auditoria.java

package gdp;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class plan_auditoria extends HttpServlet
{

    public plan_auditoria()
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
        Vector vector = new Vector();
        String s3 = "";
        String s5 = "";
        Integer integer1 = new Integer(0);
        String s7 = "";
        Integer integer3 = new Integer(0);
        Integer integer5 = new Integer(0);
        Integer integer7 = new Integer(0);
        Integer integer9 = new Integer(0);
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s9 = (String)httpsession.getValue("SerapisUser");
        if(s9 != null && s9.length() > 0)
        {
            int k = AFunc.ObtieneAnio();
            String s10 = httpservletrequest.getParameter("selAnio");
            if(s10 == null || s10.length() == 0)
                s10 = "" + k;
            String s11 = "select max(anio) from gdc.plan_auditoria";
            rs = new Vector();
            ADatos.connect();
            int l = ADatos.ConsultaDB(s11);
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                Integer integer11 = new Integer(0);
                integer11 = (Integer)rs.elementAt(0);
                if(integer11.longValue() > 0L)
                    k = integer11.intValue();
            }
            s11 = "select pa.area,pa.fecha_programada,u.nombre,pa.fecha_planificada,pa.fecha_realizada,pa.fecha_cerrada,pa.id_plan from gdc.plan_auditoria pa, sgc.usuarios u where pa.auditor_jefe = u.login and pa.anio = " + s10 + " order by pa.id_plan";
            rs = new Vector();
            rs.clear();
            ADatos.connect();
            l = ADatos.ConsultaDB(s11);
            rs = ADatos.getResult();
            Vector vector1 = (Vector)rs.clone();
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<form name='frmplan' action='plan_auditoria.jsp' method='POST'>");
            printwriter.println("<table border='0' width='80%'  align='center'>");
            printwriter.println("<tr><td width='80%' colspan='5' class='texttituloarea' align='center'>Plan de Auditorias</td></tr>");
            printwriter.println("</table>");
            printwriter.println("<table border='1' width='80%' align='center'>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'>A\361o</td>");
            printwriter.println("<td class='textdesttabla' colspan='6'>");
            printwriter.println("<select name='selAnio'>");
            for(int i = 2005; i <= k; i++) 
                if(Integer.valueOf(s10).intValue() == i)
                    printwriter.println("<option value='" + i + "' selected>" + i + "</option>");
                else
                    printwriter.println("<option value='" + i + "'>" + i + "</option>");

            printwriter.println("</select>&nbsp;&nbsp;<input name='btnBuscar' class='fondoinput' value='Buscar' type='submit'");
            printwriter.println("</td></tr>");
            if(vector1.size() > 0)
            {
                printwriter.println("<tr>");
                printwriter.println("<td class='texttitulotabla' align='center' width='3%' rowspan='2' valign='top'>&nbsp;</td>");
                printwriter.println("<td class='texttitulotabla' align='center' width='20%' rowspan='2' valign='top'>\301rea a auditar</td>");
                printwriter.println("<td class='texttitulotabla' align='center' width='10%' rowspan='2' valign='top'>Fecha Programada</td>");
                printwriter.println("<td class='texttitulotabla' align='center' width='22%' rowspan='2' valign='top'>Auditor Jefe</td>");
                printwriter.println("<td class='texttitulotabla' align='center' width='45%' colspan='3' valign='top'>Seguimiento</td>");
                printwriter.println("</tr><tr>");
                printwriter.println("<td class='texttitulotabla' align='center' width='15%' valign='top'>Planificada</td>");
                printwriter.println("<td class='texttitulotabla' align='center' width='15%' valign='top'>Realizada</td>");
                printwriter.println("<td class='texttitulotabla' align='center' width='15%' valign='top'>Cerrada</td>");
                printwriter.println("</tr>");
                for(int j = 0; j < vector1.size(); j += 7)
                {
                    String s4 = (String)vector1.elementAt(j);
                    Integer integer2 = (Integer)vector1.elementAt(j + 1);
                    String s8 = (String)vector1.elementAt(j + 2);
                    Integer integer4 = (Integer)vector1.elementAt(j + 3);
                    Integer integer6 = (Integer)vector1.elementAt(j + 4);
                    Integer integer8 = (Integer)vector1.elementAt(j + 5);
                    Integer integer10 = (Integer)vector1.elementAt(j + 6);
                    String s6 = "";
                    if(s4.compareTo("00") == 0)
                        s6 = "Todos los Procesos";
                    else
                    if(s4.compareTo("01") == 0)
                    {
                        s6 = "Auditor\355a Externa";
                    } else
                    {
                        String s12 = "SELECT descripcion from gdc.procesos where sigla = '" + s4 + "'";
                        rs = new Vector();
                        ADatos.connect();
                        int i1 = ADatos.ConsultaDB(s12);
                        rs = ADatos.getResult();
                        if(rs.size() > 0)
                            s6 = (String)rs.elementAt(0);
                    }
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla' align='center' valign='top'><a href='ver_plan.jsp?ID=" + integer10.toString() + "'>ver</a></td>");
                    printwriter.println("<td class='textdesttabla' align='center' valign='top'>" + s6 + "</td>");
                    printwriter.println("<td class='textdesttabla' align='center' valign='top'>" + AFunc.ConstruyeFecha(integer2.toString(), "/", "dmy") + "</td>");
                    printwriter.println("<td class='textdesttabla' align='center' valign='top'>" + s8 + "</td>");
                    if(integer4.longValue() > 0L)
                        printwriter.println("<td class='textdesttabla' align='center' valign='top'>" + AFunc.ConstruyeFecha(integer4.toString(), "/", "dmy") + "</td>");
                    else
                        printwriter.println("<td class='textdesttabla' align='center' valign='top'>&nbsp;</td>");
                    if(integer6.longValue() > 0L)
                        printwriter.println("<td class='textdesttabla' align='center' valign='top'>" + AFunc.ConstruyeFecha(integer6.toString(), "/", "dmy") + "</td>");
                    else
                        printwriter.println("<td class='textdesttabla' align='center' valign='top'>&nbsp;</td>");
                    if(integer8.longValue() > 0L)
                        printwriter.println("<td class='textdesttabla' align='center' valign='top'>" + AFunc.ConstruyeFecha(integer8.toString(), "/", "dmy") + "</td>");
                    else
                        printwriter.println("<td class='textdesttabla' align='center' valign='top'>&nbsp;</td>");
                    printwriter.println("</tr>");
                }

            }
            printwriter.println("</table>");
            printwriter.println("<br>");
            printwriter.println("<br><center>");
            printwriter.println("<input type='button' name='nuevo' value='Nuevo Plan' class='fondoinput'  language='javascript' onclick='window.open(\"agrega_plan.jsp\",\"datos\")'>");
            printwriter.println("<input type='button' name='BtnImprimir' value='Imprimir' class='fondoinput' language='javascript' onclick='window.print()'>");
            printwriter.println("</center>");
            printwriter.println("</form>");
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