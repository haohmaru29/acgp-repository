// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   procesa_plan.java

package gdp;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class procesa_plan extends HttpServlet
{

    public procesa_plan()
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
            ADatos.connect();
            String s1 = httpservletrequest.getParameter("txtanio");
            String s2 = httpservletrequest.getParameter("selArea");
            String s3 = httpservletrequest.getParameter("txtfecha");
            s3 = s3.substring(6, 10) + s3.substring(3, 5) + s3.substring(0, 2);
            String s4 = httpservletrequest.getParameter("selAuditorJefe");
            String s5 = httpservletrequest.getParameter("id_plan");
            Integer integer = new Integer(0);
            if(s5 == null || s5.length() == 0)
                s5 = "0";
            integer = new Integer(s5);
            String s6;
            if(integer.longValue() == 0L)
            {
                s6 = "insert into gdc.plan_auditoria (anio,cambiadopor,area,fecha_programada,auditor_jefe,fecha_planificada,fecha_realizada,fecha_cerrada) values (";
                s6 = s6 + s1 + ",";
                s6 = s6 + "'" + s + "',";
                s6 = s6 + "'" + s2 + "',";
                s6 = s6 + s3 + ",";
                s6 = s6 + "'" + s4 + "',";
                s6 = s6 + "0,0,0)";
            } else
            {
                String s7 = httpservletrequest.getParameter("txtfechaP");
                if(s7 == null || s7.length() == 0)
                    s7 = "0";
                else
                    s7 = s7.substring(6, 10) + s7.substring(3, 5) + s7.substring(0, 2);
                String s8 = httpservletrequest.getParameter("txtfechaR");
                if(s8 == null || s8.length() == 0)
                    s8 = "0";
                else
                    s8 = s8.substring(6, 10) + s8.substring(3, 5) + s8.substring(0, 2);
                String s9 = httpservletrequest.getParameter("txtfechaC");
                if(s9 == null || s9.length() == 0)
                    s9 = "0";
                else
                    s9 = s9.substring(6, 10) + s9.substring(3, 5) + s9.substring(0, 2);
                s6 = "update gdc.plan_auditoria set anio = " + s1;
                s6 = s6 + ", cambiadopor = '" + s + "'";
                s6 = s6 + ", area = '" + s2 + "'";
                s6 = s6 + ", fecha_programada = " + s3;
                s6 = s6 + ", auditor_jefe = '" + s4 + "'";
                s6 = s6 + ", fecha_planificada = " + s7;
                s6 = s6 + ", fecha_realizada = " + s8;
                s6 = s6 + ", fecha_cerrada = " + s9;
                s6 = s6 + " where id_plan = " + integer.toString();
            }
            int i = ADatos.ModificaDB(s6);
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            printwriter.println("<script language='javascript'>");
            printwriter.println("window.open('plan_auditoria.jsp','datos');");
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
}