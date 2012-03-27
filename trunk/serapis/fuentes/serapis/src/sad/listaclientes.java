// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   listaclientes.java

package sad;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class listaclientes extends HttpServlet
{

    public listaclientes()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        String s = "";
        String s2 = "";
        String s3 = "";
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s5 = (String)httpsession.getValue("SerapisUser");
        if(s5 != null && s5.length() > 0)
        {
            String s6 = "select c.razonsocial,c.abreviatura,p.proyecto,p.abreviatura,u.nombre, p.estado";
            s6 = s6 + " from gdc.proyectos p, sgc.usuarios u, sgc.clientes c";
            s6 = s6 + " where u.login = p.jefeproyecto and c.rutcliente = p.cliente order by c.razonsocial, p.proyecto";
            ADatos.connect();
            rs = new Vector();
            int i = ADatos.ConsultaDB(s6);
            rs = ADatos.getResult();
            if(rs.size() >= 0)
            {
                printwriter.println("<html>");
                printwriter.println("<head>");
                printwriter.println("</head>");
                printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
                printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
                AFunc.cargamenu(printwriter, 1);
                printwriter.println("<table border='0' width='95%' align='center'>");
                printwriter.println("<tr><td align='center'  class='texttituloarea'>Clientes y Proyectos</td></tr>");
                printwriter.println("</table>");
                printwriter.println("<br>");
                printwriter.println("<table border='1' width='95%' align='center'>");
                printwriter.println("<tr>");
                printwriter.println("</tr>");
                for(int j = 0; j < rs.size(); j += 6)
                {
                    String s1 = (String)rs.elementAt(j);
                    String s4 = (String)rs.elementAt(j + 5);
                    if(s1.compareTo(s2) != 0)
                    {
                        printwriter.println("<tr><td class='texttituloarea' colspan='4'>" + s1 + " (" + (String)rs.elementAt(j + 1) + ")</td></tr>");
                        printwriter.println("<td class='texttitulotabla'>Proyecto</td>");
                        printwriter.println("<td class='texttitulotabla'>Abreviatura</td>");
                        printwriter.println("<td class='texttitulotabla'>Jefe Proyecto</td>");
                        printwriter.println("<td class='texttitulotabla'>Estado</td>");
                        printwriter.println("<tr>");
                        printwriter.println("   <td class='textdesttabla'>" + (String)rs.elementAt(j + 2) + "</td>");
                        printwriter.println("   <td class='textdesttabla'>" + (String)rs.elementAt(j + 3) + "</td>");
                        printwriter.println("   <td class='textdesttabla'>" + (String)rs.elementAt(j + 4) + "</td>");
                        if(s4.compareTo("A") == 0)
                            printwriter.println("<td class='textdesttabla'>Activo</td>");
                        else
                        if(s4.compareTo("D") == 0)
                            printwriter.println("<td class='textdesttabla'>Detenido</td>");
                        else
                        if(s4.compareTo("G") == 0)
                            printwriter.println("<td class='textdesttabla'>En Garant\355a</td>");
                        else
                            printwriter.println("<td class='textdesttabla'>Cerrado</td>");
                        printwriter.println("</tr>");
                        s2 = s1;
                        continue;
                    }
                    printwriter.println("<tr>");
                    printwriter.println("   <td class='textdesttabla'>" + (String)rs.elementAt(j + 2) + "</td>");
                    printwriter.println("   <td class='textdesttabla'>" + (String)rs.elementAt(j + 3) + "</td>");
                    printwriter.println("   <td class='textdesttabla'>" + (String)rs.elementAt(j + 4) + "</td>");
                    if(s4.compareTo("A") == 0)
                        printwriter.println("<td class='textdesttabla'>Activo</td>");
                    else
                    if(s4.compareTo("D") == 0)
                        printwriter.println("<td class='textdesttabla'>Detenido</td>");
                    else
                    if(s4.compareTo("G") == 0)
                        printwriter.println("<td class='textdesttabla'>En Garant\355a</td>");
                    else
                        printwriter.println("<td class='textdesttabla'>Cerrado</td>");
                    printwriter.println("</tr>");
                }

                printwriter.println("</table>");
                printwriter.println("<br>");
                printwriter.println("<br><center>");
                printwriter.println("<input type='button' name='BtnImprimir' value='Imprimir' class='fondoinput' language='javascript' onclick='window.print()'>");
                printwriter.println("</center>");
                printwriter.println("</body>");
                printwriter.println("</html>");
            }
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
}