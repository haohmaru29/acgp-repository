// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   inicio.java

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class inicio extends HttpServlet
{

    public inicio()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        Licencia = "0";
        Usuario = "";
        Sistema = "CONF";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        httpsession.putValue("SerapisLic", "0");
        Licencia = (String)httpsession.getValue("SerapisLic");
        httpsession.putValue("SepapisSis", "SAD");
        Usuario = (String)httpsession.getValue("SerapisUser");
        if(Licencia == null || Licencia.length() == 0)
            Licencia = "";
        if(Usuario != null && Usuario.length() > 0)
            if(Licencia.compareTo("1") == 0)
            {
                if(Licencia.compareTo("1") == 0)
                {
                    printwriter.println("<html>");
                    printwriter.println("<head>");
                    printwriter.println("<title>SERAPIS. Sistema de Gesti\363n de Calidad.</title>");
                    printwriter.println("</head>");
                    printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../sgc.css'>");
                    printwriter.println("<BODY bgcolor='white' topmargin='0'>");
                    printwriter.println("<frameset framespacing='0' border='true' frameborder='0' cols='21%,*'>");
                    printwriter.println("<frame name='menu' src='sad/menu.jsp?TIPO=<%=Tipo%>&AREA=<%=Area%>&TIPODOC=<%=TipoDoc%>' scrolling='no' noresize>");
                    printwriter.println("<frame name='datos' src='sad/muestradoc.jsp?TIPO=<%=Tipo%>&TIPODOC=<%=TipoDoc%>&AREA=<%=Area%>' scrolling='yes' noresize>");
                    printwriter.println("</frameset>");
                    printwriter.println("</body>");
                    printwriter.println("</html>");
                } else
                {
                    ProblemaLicencia(printwriter);
                }
            } else
            {
                httpsession.putValue("SerapisSis", "SAD");
                printwriter.println("<HTML>");
                printwriter.println("<HEAD>");
                printwriter.println("<title>SERAPIS. Sistema de Gesti\363n de Calidad.</title>");
                printwriter.println("</HEAD>");
                printwriter.println("</HEAD>");
                printwriter.println("<script language=\"javascript\">");
                printwriter.println("window.open(\"../autentica.jsp?ERR=6\",\"cuerpo\");");
                printwriter.println("</script>");
                printwriter.println("<body bgcolor='#FFFFFF'>");
                printwriter.println("</BODY>");
                printwriter.println("</HTML>");
            }
    }

    void CargaMenu(PrintWriter printwriter)
    {
        printwriter.println("<table cellpadding='0' cellspacing='0' border='0'>");
        printwriter.println("<tr>");
        printwriter.println("<td align='left'>");
        printwriter.println("<script type='text/javascript' language='JavaScript1.2' src='menu/stm31.js'></script>");
        printwriter.println("<script type='text/javascript' language='JavaScript1.2' src='menu/menu01.js'></script>");
        printwriter.println("</td>");
        printwriter.println("</tr>");
        printwriter.println("</table>");
    }

    void ProblemaLicencia(PrintWriter printwriter)
    {
        printwriter.println("<HTML>");
        printwriter.println("<HEAD>");
        printwriter.println("<title>SERAPIS. Sistema de Gesti\363n de Calidad.</title>");
        printwriter.println("</HEAD>");
        printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
        printwriter.println("<table border='0' width='60%' align='center'>");
        printwriter.println("<TR><TD class='textdesttabla' align='center'><B>Problemas con su Licencia.</B></B></TD></TR>");
        printwriter.println("<TR><TD class='textdesttabla' align='center'>Contacte a su proveedor de servicios para mayor informaci\363n</B></TD></TR>");
        printwriter.println("</table>");
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    String sql;
    String Licencia;
    String Usuario;
    String Sistema;
}