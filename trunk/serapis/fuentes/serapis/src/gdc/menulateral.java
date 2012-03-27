// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   menulateral.java

package gdc;

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
            printwriter.println("<td height='28px' class='fondogris' valign='middle'><img src='../images/menu_sgd.jpg' widht='207' height='28'></td>");
            printwriter.println("</tr>");
            printwriter.println("</table>");
            printwriter.println("<table border='0' width='100%' cellspacing='0' cellpadding='0'>");
            EscribeOpcion(printwriter, -1, "", "", "N");
            if(s2.compareTo("DOC") == 0 || s2.compareTo("DWF") == 0 || s2.compareTo("ELIM") == 0 || s2.compareTo("PUB") == 0)
                EscribeOpcion(printwriter, 1, "Estado Documento", "<a href='inicio.jsp?TIPO=DOC' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 1, "Estado Documento", "<a href='inicio.jsp?TIPO=DOC' target = 'cuerpo'>", "N");
            if(s2.compareTo("DOC") == 0)
            {
                EscribeOpcion(printwriter, 2, "Documentos Aprobados", "<a href='inicio.jsp?TIPO=DOC' target = 'cuerpo'>", "S");
                String s4 = httpservletrequest.getParameter("AREA");
                if(s4 != null)
                    CargaMenuDinamico(s4, s3, printwriter);
            } else
            {
                EscribeOpcion(printwriter, 2, "Documentos Aprobados", "<a href='inicio.jsp?TIPO=DOC' target = 'cuerpo'>", "N");
            }
            if(s2.compareTo("DWF") == 0)
                EscribeOpcion(printwriter, 2, "Documentos Flujo", "<a href='inicio.jsp?TIPO=DWF' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 2, "Documentos Flujo", "<a href='inicio.jsp?TIPO=DWF' target = 'cuerpo'>", "N");
            if(s2.compareTo("ELIM") == 0)
                EscribeOpcion(printwriter, 2, "Documentos Rechazados", "<a href='inicio.jsp?TIPO=ELIM' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 2, "Documentos Rechazados", "<a href='inicio.jsp?TIPO=ELIM' target = 'cuerpo'>", "N");
            if(s2.compareTo("PUB") == 0)
                EscribeOpcion(printwriter, 2, "Publicar", "<a href='inicio.jsp?TIPO=PUB' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 2, "Publicar", "<a href='inicio.jsp?TIPO=PUB' target = 'cuerpo'>", "N");
            if(flag)
                if(s2.compareTo("WF") == 0)
                    EscribeOpcion(printwriter, 1, "Estado WorkFlow", "<a href='inicio.jsp?TIPO=WF' target = 'cuerpo'>", "S");
                else
                    EscribeOpcion(printwriter, 1, "Estado WorkFlow", "<a href='inicio.jsp?TIPO=WF' target = 'cuerpo'>", "N");
            if(s2.compareTo("LMD") == 0)
                EscribeOpcion(printwriter, 1, "Control de Documentos", "<a href='inicio.jsp?TIPO=LMD' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 1, "Control de Documentos", "<a href='inicio.jsp?TIPO=LMD' target = 'cuerpo'>", "N");
            if(s2.compareTo("LMR") == 0)
                EscribeOpcion(printwriter, 1, "Control de Registros", "<a href='inicio.jsp?TIPO=LMR' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 1, "Control de Registros", "<a href='inicio.jsp?TIPO=LMR' target = 'cuerpo'>", "N");
            printwriter.println("</table>");
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

    void ObtieneDocumentos(String s, String s1, PrintWriter printwriter)
    {
        rs = new Vector();
        Vector vector = new Vector();
        String s2 = "";
        ADatos.connect();
        int i = ADatos.ConsultaDB("select distinct td.sigla,td.descripcion from gdc.documentos d, gdc.tipodocumentos td where d.proceso='" + s + "' and td.sigla = d.tipodoc");
        rs = ADatos.getResult();
        vector = (Vector)rs.clone();
        for(int j = 0; j < vector.size(); j += 2)
        {
            String s3 = (String)vector.elementAt(j);
            String s4 = (String)vector.elementAt(j + 1);
            if(s3.compareTo(s1) == 0)
                EscribeOpcion(printwriter, 4, s4, "<a href='inicio.jsp?TIPO=DOC&AREA=" + s + "&TIPODOC=" + s3 + "' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 4, s4, "<a href='inicio.jsp?TIPO=DOC&AREA=" + s + "&TIPODOC=" + s3 + "' target = 'cuerpo'>", "N");
        }

    }

    void ObtieneDocumentos1(String s, String s1, PrintWriter printwriter)
    {
        rs = new Vector();
        Vector vector = new Vector();
        String s2 = "";
        ADatos.connect();
        int i = ADatos.ConsultaDB("select distinct td.sigla,td.descripcion from gdc.documentos d, gdc.tipodocumentos td where d.proceso='" + s + "' and td.sigla = d.tipodoc");
        rs = ADatos.getResult();
        vector = (Vector)rs.clone();
        for(int j = 0; j < vector.size(); j += 2)
        {
            String s3 = (String)vector.elementAt(j);
            String s4 = (String)vector.elementAt(j + 1);
            printwriter.println("<tr><td bgcolor='#FAE5B9'>&nbsp;</td>");
            printwriter.println("<td bgcolor='#FAE5B9'>&nbsp;</td>");
            if(s3.compareTo(s1) == 0)
            {
                printwriter.println("<td bgcolor='#FAE5B9'  width='3%'><a href='inicio.jsp?TIPO=DOC&AREA=" + s + "&TIPODOC=" + s3 + "' target = 'cuerpo'><IMG src='../images/folderabierto.gif' border=0 width=17 height=13></a></td>");
                printwriter.println("<td bgcolor='#FAE5B9' valign='top' colspan='2'><a href='inicio.jsp?TIPO=DOC&AREA=" + s + "&TIPODOC=" + s3 + "' target = 'cuerpo'>" + s4 + "</a></td></tr>");
            } else
            {
                printwriter.println("<td bgcolor='#FAE5B9'  width='3%'><a href='inicio.jsp?TIPO=DOC&AREA=" + s + "&TIPODOC=" + s3 + "' target = 'cuerpo'><IMG src='../images/foldercerrado.gif' border=0 width=17 height=13></a></td>");
                printwriter.println("<td bgcolor='#FAE5B9' valign='top' colspan='2'><a href='inicio.jsp?TIPO=DOC&AREA=" + s + "&TIPODOC=" + s3 + "' target = 'cuerpo'>" + s4 + "</a></td></tr>");
            }
        }

    }

    void CargaMenuDinamico(String s, String s1, PrintWriter printwriter)
    {
        rsD = new Vector();
        String s2 = "select sigla,descripcion from gdc.procesos order by descripcion";
        ADatosD.connect();
        int i = ADatosD.ConsultaDB(s2);
        rsD = ADatosD.getResult();
        for(int j = 0; j < rsD.size(); j += 2)
        {
            String s3 = (String)rsD.elementAt(j);
            String s4 = (String)rsD.elementAt(j + 1);
            if(s3.compareTo(s) == 0)
            {
                EscribeOpcion(printwriter, 3, s4, "<a href='inicio.jsp?TIPO=DOC&AREA=" + s3 + "' target = 'cuerpo'>", "S");
                ObtieneDocumentos(s, s1, printwriter);
            } else
            {
                EscribeOpcion(printwriter, 3, s4, "<a href='inicio.jsp?TIPO=DOC&AREA=" + s3 + "' target = 'cuerpo'>", "N");
            }
        }

    }

    void CargaMenuDinamico1(String s, String s1, PrintWriter printwriter)
    {
        rsD = new Vector();
        String s2 = "select sigla,descripcion from gdc.procesos order by descripcion";
        ADatosD.connect();
        int i = ADatosD.ConsultaDB(s2);
        rsD = ADatosD.getResult();
        for(int j = 0; j < rsD.size(); j += 2)
        {
            String s3 = (String)rsD.elementAt(j);
            String s4 = (String)rsD.elementAt(j + 1);
            if(s3.compareTo(s) == 0)
            {
                printwriter.println("<tr><td bgcolor='#FAE5B9'>&nbsp;</td>");
                printwriter.println("<td bgcolor='#FAE5B9' width='3%'><a href='inicio.jsp?TIPO=DOC&AREA=" + s3 + "' target = 'cuerpo'><IMG src='../images/folderlibro.gif' width=17 height=13 border=0></a></td>");
                printwriter.println("<td bgcolor='#FAE5B9' colspan='3' valign='top'><a href='inicio.jsp?TIPO=DOC&AREA=" + s3 + "' target = 'cuerpo'>" + s4 + "</a></td></tr>");
                ObtieneDocumentos(s, s1, printwriter);
            } else
            {
                printwriter.println("<tr><td bgcolor='#FAE5B9'>&nbsp;</td>");
                printwriter.println("<td bgcolor='#FAE5B9' width='3%'><a href='inicio.jsp?TIPO=DOC&AREA=" + s3 + "' target = 'cuerpo'><IMG src='../images/foldercerrado.gif' width=17 height=13 border=0></a></td>");
                printwriter.println("<td bgcolor='#FAE5B9' colspan='3' valign='top'><a href='inicio.jsp?TIPO=DOC&AREA=" + s3 + "' target = 'cuerpo'>" + s4 + "</a></td></tr>");
            }
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