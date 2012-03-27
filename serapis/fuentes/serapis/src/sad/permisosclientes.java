// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   permisosclientes.java

package sad;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class permisosclientes extends HttpServlet
{

    public permisosclientes()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        lstrProyecto = "";
        lstrCliente = "";
        lstrJefeProyecto = "";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        String s = "";
        Integer integer = new Integer(0);
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s6 = (String)httpsession.getValue("SerapisUser");
        if(s6 != null && s6.length() > 0)
        {
            ADatos.connect();
            rs = new Vector();
            lstrProyecto = httpservletrequest.getParameter("lstproyectos");
            if(lstrProyecto == null)
                lstrProyecto = "";
            lstrCliente = httpservletrequest.getParameter("lstclientes");
            if(lstrCliente == null)
                lstrCliente = "";
            lstrJefeProyecto = httpservletrequest.getParameter("lstjefes");
            if(lstrJefeProyecto == null)
                lstrJefeProyecto = "";
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<form name='frmpermisos' method='POST' action='permisosclientes.jsp'>");
            printwriter.println("<table border='1' width='95%' align='center'>");
            printwriter.println("<tr><td align='center' class='texttituloarea' colspan='2'>Filtro de B\372squeda</td></tr>");
            rs.clear();
            String s4 = "select id,proyecto from gdc.proyectos order by proyecto";
            int i1 = ADatos.ConsultaDB(s4);
            rs = ADatos.getResult();
            printwriter.println("<tr><td align='left' class='textdesttabla' width='25%'>Proyecto</td>");
            printwriter.println("<td align='left' class='textdesttabla' width='75%'>");
            printwriter.println("<select name='lstproyectos'>");
            printwriter.println("<option value=''>[Seleccione Proyecto]</option>");
            for(int i = 0; i < rs.size(); i += 2)
            {
                Integer integer1 = (Integer)rs.elementAt(i);
                String s1 = integer1.toString();
                if(lstrProyecto.compareTo(s1) == 0)
                    printwriter.println("<option value='" + s1 + "' selected>" + rs.elementAt(i + 1) + "</option>");
                else
                    printwriter.println("<option value='" + s1 + "'>" + rs.elementAt(i + 1) + "</option>");
            }

            printwriter.println("</select>");
            printwriter.println("</td></tr>");
            rs.clear();
            s4 = "select rutcliente,razonsocial from sgc.clientes order by razonsocial";
            i1 = ADatos.ConsultaDB(s4);
            rs = ADatos.getResult();
            printwriter.println("<tr><td align='left' class='textdesttabla' width='25%'>Clientes</td>");
            printwriter.println("<td align='left' class='textdesttabla' width='75%'>");
            printwriter.println("<select name='lstclientes'>");
            printwriter.println("<option value=''>[Seleccione Cliente]</option>");
            for(int j = 0; j < rs.size(); j += 2)
            {
                String s2 = (String)rs.elementAt(j);
                if(lstrCliente.compareTo(s2) == 0)
                    printwriter.println("<option value='" + s2 + "' selected>" + rs.elementAt(j + 1) + "</option>");
                else
                    printwriter.println("<option value='" + s2 + "'>" + rs.elementAt(j + 1) + "</option>");
            }

            printwriter.println("</select>");
            printwriter.println("</td></tr>");
            rs.clear();
            s4 = "select DISTINCT u.login, u.nombre from gdc.proyectos p, sgc.usuarios u where p.jefeproyecto = u.login order by u.nombre";
            i1 = ADatos.ConsultaDB(s4);
            rs = ADatos.getResult();
            printwriter.println("<tr><td align='left' class='textdesttabla' width='25%'>Jefe de Proyecto</td>");
            printwriter.println("<td align='left' class='textdesttabla' width='75%'>");
            printwriter.println("<select name='lstjefes'>");
            printwriter.println("<option value=''>[Seleccione Jefe Proyecto]</option>");
            for(int k = 0; k < rs.size(); k += 2)
            {
                String s3 = (String)rs.elementAt(k);
                if(lstrJefeProyecto.compareTo(s3) == 0)
                    printwriter.println("<option value='" + s3 + "' selected>" + rs.elementAt(k + 1) + "</option>");
                else
                    printwriter.println("<option value='" + s3 + "'>" + rs.elementAt(k + 1) + "</option>");
            }

            printwriter.println("</select>");
            printwriter.println("</td></tr>");
            printwriter.println("<tr><td align='center' class='textdesttabla' colspan='2'><input type='submit' class='fondoinput' name='btnBuscar' value='Buscar' onclick=''></td></tr>");
            printwriter.println("</table>");
            printwriter.println("</form>");
            if(lstrProyecto.length() > 0 || lstrCliente.length() > 0 || lstrJefeProyecto.length() > 0)
            {
                String s5 = "select c.razonsocial,c.abreviatura,p.proyecto,p.abreviatura,u.nombre,p.id";
                s5 = s5 + " from gdc.proyectos p, sgc.usuarios u, sgc.clientes c";
                s5 = s5 + " where u.login = p.jefeproyecto and c.rutcliente = p.cliente";
                if(lstrProyecto.length() > 0)
                    s5 = s5 + " and p.id = " + lstrProyecto;
                else
                if(lstrCliente.length() > 0)
                    s5 = s5 + " and c.rutcliente = '" + lstrCliente + "'";
                else
                if(lstrJefeProyecto.length() > 0)
                    s5 = s5 + " and p.jefeproyecto = '" + lstrJefeProyecto + "'";
                ADatos.connect();
                rs = new Vector();
                int j1 = ADatos.ConsultaDB(s5);
                rs = ADatos.getResult();
                rs2 = (Vector)rs.clone();
                if(rs.size() >= 0)
                {
                    printwriter.println("<table border='0' width='95%' align='center'>");
                    printwriter.println("<tr><td align='center'  class='texttituloarea'>Clientes y Proyectos</td></tr>");
                    printwriter.println("</table>");
                    printwriter.println("<table border='1' width='95%' align='center'>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='texttitulotabla'>Proyecto</td>");
                    printwriter.println("<td class='texttitulotabla'>Abreviatura</td>");
                    printwriter.println("<td class='texttitulotabla'>Cliente</td>");
                    printwriter.println("<td class='texttitulotabla'>Abreviatura</td>");
                    printwriter.println("<td class='texttitulotabla'>Jefe Proyecto</td>");
                    printwriter.println("</tr>");
                    for(int l = 0; l < rs2.size(); l += 6)
                    {
                        printwriter.println("<tr>");
                        printwriter.println("<td class='textdesttabla'><a href='permisosproy.jsp?IDPROY=" + (Integer)rs2.elementAt(l + 5) + "'>" + (String)rs2.elementAt(l + 2) + "</a></td>");
                        printwriter.println("<td class='textdesttabla'>" + (String)rs2.elementAt(l + 3) + "</td>");
                        printwriter.println("<td class='textdesttabla'>" + (String)rs2.elementAt(l + 0) + "</td>");
                        printwriter.println("<td class='textdesttabla'>" + (String)rs2.elementAt(l + 1) + "</td>");
                        printwriter.println("<td class='textdesttabla'>" + (String)rs2.elementAt(l + 4) + "</td>");
                        printwriter.println("</tr>");
                    }

                    printwriter.println("</table>");
                    printwriter.println("</body>");
                    printwriter.println("</html>");
                }
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
    Vector rs2;
    String lstrProyecto;
    String lstrCliente;
    String lstrJefeProyecto;
}