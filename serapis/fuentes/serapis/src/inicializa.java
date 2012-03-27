// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   inicializa.java

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class inicializa extends HttpServlet
{

    public inicializa()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        Licencia = "0";
        Usuario = "";
        Sistema = "GDC";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        Licencia = (String)httpsession.getValue("SerapisLic");
        Usuario = (String)httpsession.getValue("SerapisUser");
        Sistema = (String)httpsession.getValue("SerapisSis");
        if(Licencia == null || Licencia.length() == 0)
            Licencia = "";
        if(Usuario == null || Usuario.length() == 0)
            Usuario = "";
        if(Sistema == null || Sistema.length() == 0)
            Sistema = "";
        if(Usuario != null && Usuario.length() > 0)
        {
            if(Licencia.compareTo("1") == 0)
            {
                if(Sistema.compareTo("GDC") == 0)
                {
                    printwriter.println("<html>");
                    printwriter.println("<head>");
                    printwriter.println("</head>");
                    printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='serapis.css'>");
                    printwriter.println("<TITLE>SERAPIS. Sistema de Gesti\363n de Calidad.</TITLE>");
                    printwriter.println("<frameset framespacing='0' border='true' frameborder='0' rows='68px,*'>");
                    printwriter.println("  <frame name='encabezado' src='encabezado.jsp' scrolling='no' noresize>");
                    printwriter.println("  <frame name='cuerpo' src='gdc/inicio.jsp' noresize>");
                    printwriter.println("</frameset>");
                    printwriter.println("</body>");
                    printwriter.println("</html>");
                } else
                if(Sistema.compareTo("SAD") == 0)
                {
                    printwriter.println("<html>");
                    printwriter.println("<head>");
                    printwriter.println("</head>");
                    printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='serapis.css'>");
                    printwriter.println("<TITLE>SERAPIS. Sistema de Gesti\363n de Calidad.</TITLE>");
                    printwriter.println("<frameset framespacing='0' border='true' frameborder='0' rows='68px,*'>");
                    printwriter.println("  <frame name='encabezado' src='encabezado.jsp' scrolling='no' noresize>");
                    printwriter.println("  <frame name='cuerpo' src='sad/inicio.jsp' noresize>");
                    printwriter.println("</frameset>");
                    printwriter.println("</body>");
                    printwriter.println("</html>");
                } else
                if(Sistema.compareTo("CONF") == 0)
                {
                    printwriter.println("<html>");
                    printwriter.println("<head>");
                    printwriter.println("</head>");
                    printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='serapis.css'>");
                    printwriter.println("<TITLE>SERAPIS. Sistema de Gesti\363n de Calidad.</TITLE>");
                    printwriter.println("<frameset framespacing='0' border='true' frameborder='0' rows='68px,*'>");
                    printwriter.println("  <frame name='encabezado' src='encabezado.jsp' scrolling='no' noresize>");
                    printwriter.println("  <frame name='cuerpo' src='conf/inicio.jsp' noresize>");
                    printwriter.println("</frameset>");
                    printwriter.println("</body>");
                    printwriter.println("</html>");
                } else
                if(Sistema.compareTo("GDP") == 0)
                {
                    printwriter.println("<html>");
                    printwriter.println("<head>");
                    printwriter.println("</head>");
                    printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='serapis.css'>");
                    printwriter.println("<TITLE>SERAPIS. Sistema de Gesti\363n de Calidad.</TITLE>");
                    printwriter.println("<frameset framespacing='0' border='true' frameborder='0' rows='68px,*'>");
                    printwriter.println("  <frame name='encabezado' src='encabezado.jsp' scrolling='no' noresize>");
                    printwriter.println("  <frame name='cuerpo' src='gdp/inicio.jsp' noresize>");
                    printwriter.println("</frameset>");
                    printwriter.println("</body>");
                    printwriter.println("</html>");
                } else
                {
                    AFunc.cargainicio(printwriter, 0);
                }
            } else
            {
                AFunc.cargainicio(printwriter, 0);
            }
        } else
        {
            AFunc.cargainicio(printwriter, 0);
        }
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    String sql;
    String Licencia;
    String Usuario;
    String Sistema;
}