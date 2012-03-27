// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   gg_procesa.java

package gdp;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class gg_procesa extends HttpServlet
{

    public gg_procesa()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        StrMes = "";
        StrAnio = "";
        StrProceso = "";
        NroFilas = "";
        IntNroFilas = new Integer(0);
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
            String s1 = httpservletrequest.getParameter("selMes");
            String s2 = httpservletrequest.getParameter("selAnio");
            String s3 = httpservletrequest.getParameter("proceso");
            String s4 = httpservletrequest.getParameter("procedimiento");
            String s5 = httpservletrequest.getParameter("indicador");
            String s6 = httpservletrequest.getParameter("signo");
            String s7 = httpservletrequest.getParameter("gap");
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            String s8 = "delete from gdc.graficos_gestion";
            s8 = s8 + " where anio = " + s2;
            s8 = s8 + " and mes = " + s1;
            s8 = s8 + " and proceso = '" + s3 + "'";
            s8 = s8 + " and procedimiento_indicador = " + s5;
            ADatos.ModificaDB(s8);
            s8 = "insert into gdc.graficos_gestion (anio,mes,proceso,procedimiento_indicador,signo,gap) values (";
            s8 = s8 + s2 + ",";
            s8 = s8 + s1 + ",";
            s8 = s8 + "'" + s3 + "',";
            s8 = s8 + s5 + ",";
            s8 = s8 + "'" + s6 + "',";
            s8 = s8 + s7 + ")";
            ADatos.ModificaDB(s8);
            printwriter.println("<form name='frmgg'  method='POST'>");
            printwriter.println("<input type='hidden' name='selAnio' value='" + s2 + "'>");
            printwriter.println("<input type='hidden' name='selMes' value='" + s1 + "'>");
            printwriter.println("</form>");
            printwriter.println("<script language='javascript'>");
            printwriter.println("   document.frmgg.action=\"gg_grafico_gestion.jsp\";");
            printwriter.println("   document.frmgg.submit();");
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

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    String IdNC;
    String StrMes;
    String StrAnio;
    String StrProceso;
    String NroFilas;
    Integer IntNroFilas;
}