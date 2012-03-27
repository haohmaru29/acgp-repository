// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   gg_tablero_procesa.java

package gdp;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class gg_tablero_procesa extends HttpServlet
{

    public gg_tablero_procesa()
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
            String s1 = httpservletrequest.getParameter("selAnio");
            String s2 = httpservletrequest.getParameter("selMes");
            String s3 = httpservletrequest.getParameter("nrofilas");
            String s4 = "";
            String s6 = "";
            String s8 = "";
            String s10 = "";
            String s12 = "";
            String s14 = "";
            String s16 = "";
            String s18 = "";
            String s20 = "";
            Integer integer = new Integer(0);
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            String s22 = " delete from gdc.tablero_control where anio = " + s1;
            s22 = s22 + " and mes = " + s2;
            ADatos.ModificaDB(s22);
            integer = new Integer(s3);
            Vector vector = new Vector();
            vector = AFunc.ObtieneFecha();
            for(int i = 1; i <= integer.intValue(); i++)
            {
                String s5 = httpservletrequest.getParameter("campo1" + i);
                String s7 = httpservletrequest.getParameter("campo2" + i);
                String s9 = httpservletrequest.getParameter("campo3" + i);
                String s11 = httpservletrequest.getParameter("campo4" + i);
                String s13 = httpservletrequest.getParameter("campo5" + i);
                String s15 = httpservletrequest.getParameter("campo6" + i);
                String s17 = httpservletrequest.getParameter("campo7" + i);
                String s19 = httpservletrequest.getParameter("campo8" + i);
                String s21 = httpservletrequest.getParameter("campocolor" + i);
                String s23 = "insert into gdc.tablero_control (anio,mes,campo1,campo2,campo3,campo4,campo5,campo6,campo7,campo8,color,fecha) values (";
                s23 = s23 + s1 + ",";
                s23 = s23 + s2 + ",";
                s23 = s23 + "'" + s5 + "',";
                s23 = s23 + "'" + s7 + "',";
                s23 = s23 + "'" + s9 + "',";
                s23 = s23 + "'" + s11 + "',";
                s23 = s23 + "'" + s13 + "',";
                s23 = s23 + "'" + s15 + "',";
                s23 = s23 + "'" + s17 + "',";
                s23 = s23 + "'" + s19 + "',";
                s23 = s23 + "'" + s21 + "',";
                s23 = s23 + "'" + (String)vector.elementAt(0) + "')";
                ADatos.ModificaDB(s23);
            }

            printwriter.println("<form name='frmgg'  method='POST'>");
            printwriter.println("<input type='hidden' name='selAnio' value='" + StrAnio + "'>");
            printwriter.println("<input type='hidden' name='selMes' value='" + StrMes + "'>");
            printwriter.println("</form>");
            printwriter.println("<script language='javascript'>");
            printwriter.println("   document.frmgg.action=\"gg_tablero_control.jsp\";");
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