// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   encargados.java

package sad;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class encargados extends HttpServlet
{

    public encargados()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        TablaProcedimientos = "";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s = (String)httpsession.getValue("SerapisUser");
        boolean flag = false;
        if(s != null && s.length() > 0)
        {
            String s1 = "Select p.sigla,p.descripcion,p.definicion,u.nombre from gdc.procesos p, sgc.usuarios u where u.login = p.lider order by p.descripcion";
            rs = new Vector();
            ADatos.connect();
            int j = ADatos.ConsultaDB(s1);
            rs = ADatos.getResult();
            rsprocesos = (Vector)rs.clone();
            if(rs.size() > 0)
            {
                printwriter.println("<html>");
                printwriter.println("<head>");
                printwriter.println("</head>");
                printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
                printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
                AFunc.cargamenu(printwriter, 1);
                printwriter.println("<table border='0' width='95%' align='center'>");
                printwriter.println("<tr><td align='center'  class='texttitulomayor'>Encargados Sistema de Gesti\363n de calidad</td></tr>");
                printwriter.println("</table>");
                printwriter.println("<br>");
                for(int k = 0; k < rsprocesos.size(); k += 4)
                {
                    int i = CargaProcedimientos(printwriter, (String)rsprocesos.elementAt(k));
                    if(i == 0)
                        i = 1;
                    printwriter.println("<table border='1' align='center' width='95%'>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='texttituloarea' colspan='4'>" + (String)rsprocesos.elementAt(k + 1) + "</td>");
                    printwriter.println("</td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='texttitulotabla'>Procedimiento</td>");
                    printwriter.println("<td class='textdesttabla' colspan='3'>" + (String)rsprocesos.elementAt(k + 1) + "</td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='texttitulotabla'>Rol</td>");
                    printwriter.println("<td class='textdesttabla' colspan='3'>Lider de Proceso</td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='texttitulotabla'>Definici\363n</td>");
                    printwriter.println("<td class='textdesttabla' colspan='3'>" + (String)rsprocesos.elementAt(k + 2) + "</td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='texttitulotabla'>Responsable</td>");
                    printwriter.println("<td class='textdesttabla' colspan='3'>" + (String)rsprocesos.elementAt(k + 3) + "</td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='texttitulotabla' width='30%'>Procedimiento</td>");
                    printwriter.println("<td class='texttitulotabla' colspan='2' width='50%'>Indicador</td>");
                    printwriter.println("<td class='texttitulotabla' width='20%'>HOSHIN Meta</td>");
                    printwriter.println("</tr>");
                    printwriter.println(TablaProcedimientos);
                    printwriter.println("</table>");
                    printwriter.println("<BR>");
                }

                printwriter.println("</table>");
                printwriter.println("<br><br>");
                printwriter.println("<center>");
                printwriter.println("<input type='button' name='BtnImprimir' value='Imprimir' class='fondoinput' language='javascript' onclick='window.print()'></td>");
                printwriter.println("</center>");
                printwriter.println("</body>");
                printwriter.println("</html>");
            } else
            {
                printwriter.println("<html>");
                printwriter.println("<head>");
                printwriter.println("</head>");
                printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='gdc.css'>");
                printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
                AFunc.cargamenu(printwriter, 1);
                printwriter.println("<CENTER><FONT face=Tahoma size=2 color='#000066'><B>El usuario " + s + " no esta autorizado para ingresar a este mantenimiento.</B></font></CENTER>");
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

    int CargaProcedimientos(PrintWriter printwriter, String s)
    {
        String s1 = "";
        Vector vector = new Vector();
        boolean flag = false;
        int j = 1;
        int k = ADatos.ConsultaDB("select procedimiento,rol,definicionrol,responsables from gdc.procedimientos where proceso='" + s + "' order by procedimiento");
        rs = ADatos.getResult();
        vector = (Vector)rs.clone();
        TablaProcedimientos = "";
        for(int i = 0; i < vector.size(); i += 4)
        {
            String s2 = (String)vector.elementAt(i + 3);
            TablaProcedimientos = TablaProcedimientos + "<tr>";
            TablaProcedimientos = TablaProcedimientos + " <td class='textdesttabla'>" + (String)vector.elementAt(i) + "</td>";
            TablaProcedimientos = TablaProcedimientos + " <td class='textdesttabla' colspan='2'>" + (String)vector.elementAt(i + 2) + "</td>";
            TablaProcedimientos = TablaProcedimientos + " <td class='textdesttabla'>" + (String)vector.elementAt(i + 1) + "</td>";
            TablaProcedimientos = TablaProcedimientos + " </tr>";
            j++;
        }

        return j;
    }

    String ObtieneResponsables(PrintWriter printwriter, String s)
    {
        String s1 = "";
        s = s + ";";
        for(StringTokenizer stringtokenizer = new StringTokenizer(s, ";"); stringtokenizer.hasMoreTokens();)
        {
            String s2 = stringtokenizer.nextToken();
            String s3 = "select nombre from sgc.usuarios where login = '" + s2 + "'";
            s3 = s3 + " UNION ";
            s3 = s3 + "select nombre from gdc.usuariosad where login = '" + s2 + "'";
            int j = ADatos.ConsultaDB(s3);
            rs = ADatos.getResult();
            int i = 0;
            while(i < rs.size())
            {
                s1 = s1 + (String)rs.elementAt(i) + "<BR>";
                i++;
            }
        }

        return s1;
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    Vector rsprocesos;
    String TablaProcedimientos;
}