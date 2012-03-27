// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   help.java

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class help extends HttpServlet
{

    public help()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        String s = "#FFFFFF";
        String s1 = httpservletrequest.getParameter("txtBuscar");
        if(s1 == null)
            s1 = "";
        printwriter.println("<html>");
        printwriter.println("<head>");
        printwriter.println("<title>SERAPIS. Ayuda.</title>");
        printwriter.println("</head>");
        printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='serapis.css'>");
        printwriter.println("<script type='text/javascript' language='JavaScript' src='js/funciones.js'></script>");
        printwriter.println("<body leftmargin='0' topmargin='0' onload='document.frmhelp.txtBuscar.focus();'>");
        rs = new Vector();
        if(s1.length() > 0)
        {
            sql = "select tema,claves,paginadestino from sgc.help where claves LIKE '%" + s1 + "%'";
            ADatos.connect();
            int i = ADatos.ConsultaDB(sql);
            rs = ADatos.getResult();
        }
        printwriter.println("<form name='frmhelp' action='help.jsp' method='post'>");
        printwriter.println("<table align='left' width='100%' cellpadding='0' cellspacing='0' border='0'> ");
        printwriter.println("<tr><td>");
        printwriter.println("<table align='left' width='100%' cellpadding='1' cellspacing='13' border='0'>");
        printwriter.println("<tr>");
        printwriter.println("\t<td width='20%' class='contenidoespecial'>Texto de B\372squeda</td>");
        printwriter.println("\t<td class='contenidoespecial'><input class='inputverde' name='txtBuscar' value='" + s1 + "' style='WIDTH: 420px' maxlength='100' language='javascript' onkeypress='return ValidarCaracteres(9);'>");
        printwriter.println("\t\t<input class='fondoinput' type='submit' name='btnbuscar' value ='Buscar' language='javascript' onclick=''>");
        printwriter.println("\t</td>");
        printwriter.println("</tr>   ");
        printwriter.println("</table>");
        printwriter.println("</td></tr>");
        printwriter.println("<tr><td>&nbsp;</td></tr>");
        printwriter.println("<tr><td>");
        if(rs.size() > 0)
        {
            printwriter.println("<table align='center' width='70%' cellpadding='0' cellspacing='0' border='0'> ");
            printwriter.println("<tr><td class='texttituloarea' colspan='2'>Resultados de B\372squeda</td></tr>");
            printwriter.println("<tr><td class='texttitulotabla' width='35%'>Tema</td><td class='texttitulotabla' width='65%'>Clave</td></tr>");
            for(int j = 0; j < rs.size(); j += 3)
            {
                printwriter.println("<tr bgcolor='" + s + "'>");
                if(s.compareTo("#FAE5B9") == 0)
                    s = "#FFFFFF";
                else
                    s = "#FAE5B9";
                printwriter.println("    <td valign='top'><a href='ver_help.jsp?PAGINA=" + (String)rs.elementAt(j + 2) + "&TEXTO=" + s1 + "'>" + (String)rs.elementAt(j) + "</a></td>");
                printwriter.println("    <td valign='top'>" + (String)rs.elementAt(j + 1) + "</td>");
                printwriter.println("</tr>");
            }

        }
        printwriter.println("</td></tr>");
        printwriter.println("</table>");
        printwriter.println("</form>");
        printwriter.println("</body>");
        printwriter.println("</html>");
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    String sql;
}