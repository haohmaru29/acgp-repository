// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   listacargosroles.java

package sad;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class listacargosroles extends HttpServlet
{

    public listacargosroles()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        EsAdmin = "N";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        Integer integer = new Integer(0);
        String s = "";
        String s2 = "";
        String s4 = "";
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s6 = (String)httpsession.getValue("SerapisUser");
        if(s6 != null && s6.length() > 0)
        {
            String s7 = "select ID,Descripcion,Departamento,Reporta from gdc.def_cargos order by Descripcion";
            rs = new Vector();
            ADatos.connect();
            int i = ADatos.ConsultaDB(s7);
            rs = ADatos.getResult();
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<table border='0' width='95%'  align='center'>");
            printwriter.println("<tr><td width='80%' colspan='4' class='texttituloarea' align='center'>Cargos y Roles</td></tr>");
            printwriter.println("</table>");
            if(rs.size() > 0)
            {
                printwriter.println("<table border='1' width='80%' align='center'>");
                printwriter.println("<tr>");
                printwriter.println("<td class='texttitulotabla'>Descripci\363n</td>");
                printwriter.println("<td class='texttitulotabla'>Departamento</td>");
                printwriter.println("<td class='texttitulotabla'>Reporta</td>");
                printwriter.println("</tr>");
                for(int j = 0; j < rs.size(); j += 4)
                {
                    Integer integer1 = (Integer)rs.elementAt(j);
                    String s1 = (String)rs.elementAt(j + 1);
                    String s3 = (String)rs.elementAt(j + 2);
                    String s5 = (String)rs.elementAt(j + 3);
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla'><a href='llenacargos.jsp?ID=" + integer1 + "'>" + s1 + "</a></td>");
                    printwriter.println("<td class='textdesttabla'>" + s3 + "</td>");
                    printwriter.println("<td class='textdesttabla'>" + s5 + "</td>");
                    printwriter.println("</tr>");
                }

                printwriter.println("</table>");
            }
            printwriter.println("<br>");
            printwriter.println("<br><center>");
            printwriter.println("<input type='button' name='BtnImprimir' value='Imprimir' class='fondoinput' language='javascript' onclick='window.print()'>");
            printwriter.println("</center>");
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "SAD", 6);
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