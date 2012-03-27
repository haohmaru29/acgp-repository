// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   perfilesyusuarios.java

package sad;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class perfilesyusuarios extends HttpServlet
{

    public perfilesyusuarios()
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
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<table border='0' width='95%' align='center'>");
            printwriter.println("<tr><td align='center' class='texttituloarea'>Usuarios por Perfil</td></tr>");
            printwriter.println("</table>");
            CargaDocumentos(printwriter);
            printwriter.println("<BR><center><input type='button' name='BtnImprimir' value='Imprimir' class='fondoinput' language='javascript' onclick='window.print()'></center></td>");
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

    void CargaDocumentos(PrintWriter printwriter)
    {
        rs = new Vector();
        Integer integer = new Integer(0);
        Integer integer1 = new Integer(0);
        String s = "";
        String s1 = "";
        String s2 = "";
        String s3 = "";
        String s4 = "";
        ADatos.connect();
        s2 = "select pu.usuario, u.nombre, pu.idperfil, p.perfil";
        s2 = s2 + " from sgc.perfilusuario pu, sgc.usuarios u, sgc.perfiles p";
        s2 = s2 + " where pu.usuario = u.login and pu.idperfil = p.idperfil ORDER by p.perfil, u.nombre";
        int i = ADatos.ConsultaDB(s2);
        rs = ADatos.getResult();
        if(rs.size() > 0)
        {
            printwriter.println("<BR><table border='1' align='center' width='95%'>");
            for(int j = 0; j < rs.size(); j += 4)
            {
                String s5 = (String)rs.elementAt(j + 3);
                if(s5.compareTo(s3) != 0)
                {
                    printwriter.println("<tr><td class='texttituloarea'>" + s5 + " </td></tr>");
                    printwriter.println("<tr><td class='textdesttabla'>" + rs.elementAt(j + 1) + " </td></tr>");
                    s3 = s5;
                } else
                {
                    printwriter.println("<tr><td class='textdesttabla'>" + rs.elementAt(j + 1) + " </td></tr>");
                }
            }

            printwriter.println("</table>");
        } else
        {
            printwriter.println("<table border='0' align='center' width='80%'>");
            printwriter.println("<tr>");
            printwriter.println("<td class='texttitulotabla' align='center'>No hay informaci\363n por desplegar</td>");
            printwriter.println("<tr>");
            printwriter.println("</table>");
        }
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
}