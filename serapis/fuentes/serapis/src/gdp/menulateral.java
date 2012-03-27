// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   menulateral.java

package gdp;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class menulateral extends HttpServlet
{

    public menulateral()
    {
        ADatos = new AccDataBase();
        ADatosD = new AccDataBase();
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
            String s1 = "select login from sgc.perfil where login='" + s + "' and administrador='S'";
            rs = new Vector();
            ADatos.connect();
            int i = ADatos.ConsultaDB(s1);
            rs = ADatos.getResult();
            boolean flag = false;
            if(rs.size() > 0)
                flag = true;
            String s2 = httpservletrequest.getParameter("TIPO");
            String s3 = httpservletrequest.getParameter("TIPODOC");
            if(s2 == null)
                s2 = "";
            if(s3 == null)
                s3 = "";
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<BODY bgcolor='#FAE5B9' leftmargin='0' topmargin='0'>");
            printwriter.println("<table cellpadding='0' cellspacing='0' border='0' width='100%'>");
            printwriter.println("<tr class='fondogris'>");
            printwriter.println("<td height='28px' class='fondogris' valign='middle'><img src='../images/menu_gdp.jpg' widht='207' height='28'></td>");
            printwriter.println("</tr>");
            printwriter.println("</table>");
            printwriter.println("<table border='0' width='100%' cellspacing='0' cellpadding='0'>");
            EscribeOpcion(printwriter, -1, "", "", "N");
            if(s2.compareTo("PNC") == 0 || s2.compareTo("PNCINC") == 0 || s2.compareTo("PNCRCE") == 0)
            {
                EscribeOpcion(printwriter, 1, "Producto no Conforme", "<a href='inicio.jsp?TIPO=PNC' target = 'cuerpo'>", "S");
                if(s2.compareTo("PNCINC") == 0)
                    EscribeOpcion(printwriter, 2, "Informe de No Conformidades", "<a href='inicio.jsp?TIPO=PNCINC' target = 'cuerpo'>", "S");
                else
                    EscribeOpcion(printwriter, 2, "Informe de No Conformidades", "<a href='inicio.jsp?TIPO=PNCINC' target = 'cuerpo'>", "N");
                if(s2.compareTo("PNCRCE") == 0)
                    EscribeOpcion(printwriter, 2, "Resumen Comit\351 Estrat\351gico", "<a href='inicio.jsp?TIPO=PNCRCE' target = 'cuerpo'>", "S");
                else
                    EscribeOpcion(printwriter, 2, "Resumen Comit\351 Estrat\351gico", "<a href='inicio.jsp?TIPO=PNCRCE' target = 'cuerpo'>", "N");
            } else
            {
                EscribeOpcion(printwriter, 1, "Producto no Conforme", "<a href='inicio.jsp?TIPO=PNC' target = 'cuerpo'>", "N");
            }
            if(s2.compareTo("ACP") == 0 || s2.compareTo("ACPGG") == 0 || s2.compareTo("ACPMIN") == 0 || s2.compareTo("ACPTC") == 0)
            {
                EscribeOpcion(printwriter, 1, "Acciones Correctivas y Preventivas", "<a href='inicio.jsp?TIPO=ACP' target = 'cuerpo'>", "S");
                if(s2.compareTo("ACPGG") == 0)
                    EscribeOpcion(printwriter, 2, "Gr\341ficos de Gesti\363n", "<a href='inicio.jsp?TIPO=ACPGG' target = 'cuerpo'>", "S");
                else
                    EscribeOpcion(printwriter, 2, "Gr\341ficos de Gesti\363n", "<a href='inicio.jsp?TIPO=ACPGG' target = 'cuerpo'>", "N");
                if(s2.compareTo("ACPTC") == 0)
                    EscribeOpcion(printwriter, 2, "Tablero de Control", "<a href='inicio.jsp?TIPO=ACPTC' target = 'cuerpo'>", "S");
                else
                    EscribeOpcion(printwriter, 2, "Tablero de Control", "<a href='inicio.jsp?TIPO=ACPTC' target = 'cuerpo'>", "N");
                if(s2.compareTo("ACPMIN") == 0)
                    EscribeOpcion(printwriter, 2, "Minuta Reuni\363n Comit\351", "<a href='inicio.jsp?TIPO=ACPMIN' target = 'cuerpo'>", "S");
                else
                    EscribeOpcion(printwriter, 2, "Minuta Reuni\363n Comit\351", "<a href='inicio.jsp?TIPO=ACPMIN' target = 'cuerpo'>", "N");
            } else
            {
                EscribeOpcion(printwriter, 1, "Acciones Correctivas y Preventivas", "<a href='inicio.jsp?TIPO=ACP' target = 'cuerpo'>", "N");
            }
            if(s2.compareTo("AU") == 0 || s2.compareTo("RAU") == 0 || s2.compareTo("PAU") == 0)
            {
                EscribeOpcion(printwriter, 1, "Auditorias", "<a href='inicio.jsp?TIPO=PAU' target = 'cuerpo'>", "S");
                if(s2.compareTo("RAU") == 0)
                    EscribeOpcion(printwriter, 2, "Registros de Auditor\355a", "<a href='inicio.jsp?TIPO=RAU' target = 'cuerpo'>", "S");
                else
                    EscribeOpcion(printwriter, 2, "Registros de Auditor\355a", "<a href='inicio.jsp?TIPO=RAU' target = 'cuerpo'>", "N");
                if(s2.compareTo("PAU") == 0)
                    EscribeOpcion(printwriter, 2, "Plan de Auditor\355as", "<a href='inicio.jsp?TIPO=PAU' target = 'cuerpo'>", "S");
                else
                    EscribeOpcion(printwriter, 2, "Plan de Auditor\355as", "<a href='inicio.jsp?TIPO=PAU' target = 'cuerpo'>", "N");
            } else
            {
                EscribeOpcion(printwriter, 1, "Auditorias", "<a href='inicio.jsp?TIPO=PAU' target = 'cuerpo'>", "N");
            }
            printwriter.println("</body>");
            printwriter.println("</html>");
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "GDC", 6);
        }
    }

    void EscribeOpcion(PrintWriter printwriter, int i, String s, String s1, String s2)
    {
        String s3;
        if(s2.compareTo("S") == 0)
            s3 = "folderabierto.gif";
        else
            s3 = "foldercerrado.gif";
        switch(i)
        {
        case 0: // '\0'
        default:
            break;

        case -1:
            printwriter.println("<tr>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' valign='center' align='left'>&nbsp;</td>");
            printwriter.println("</tr>");
            break;

        case 1: // '\001'
            printwriter.println("<tr>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='center' align='left'><IMG src='../images/" + s3 + "' width=17 height=13 border=0></td>");
            if(s1.length() > 0)
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='4' valign='center' align='left'>" + s1 + s + "</a></td>");
            else
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='4' valign='center' align='left'>" + s + "</a></td>");
            printwriter.println("</tr>");
            break;

        case 2: // '\002'
            printwriter.println("<tr>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='center' align='left'><IMG src='../images/" + s3 + "' width=17 height=13 border=0></td>");
            if(s1.length() > 0)
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='4' valign='center' align='left'>" + s1 + s + "</a></td>");
            else
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='4' valign='center' align='left'>" + s + "</a></td>");
            printwriter.println("</tr>");
            break;

        case 3: // '\003'
            printwriter.println("<tr>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='center' align='left'><IMG src='../images/" + s3 + "' width=17 height=13 border=0></td>");
            if(s1.length() > 0)
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='4' valign='center' align='left'>" + s1 + s + "</a></td>");
            else
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='4' valign='center' align='left'>" + s + "</a></td>");
            printwriter.println("</tr>");
            break;

        case 4: // '\004'
            printwriter.println("<tr>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='center' align='left'><IMG src='../images/" + s3 + "' width=17 height=13 border=0></td>");
            if(s1.length() > 0)
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='4' valign='center' align='left'>" + s1 + s + "</a></td>");
            else
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='4' valign='center' align='left'>" + s + "</a></td>");
            printwriter.println("</tr>");
            break;
        }
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    AccDataBase ADatos;
    Vector rs;
    AccDataBase ADatosD;
    Vector rsD;
    funciones AFunc;
}