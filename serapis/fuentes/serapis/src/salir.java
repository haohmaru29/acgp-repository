// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   salir.java

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class salir extends HttpServlet
{

    public salir()
    {
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        printwriter.println("<HTML>");
        printwriter.println("<HEAD>");
        printwriter.println("<title>SERAPIS. Sistema de Gesti\363n de Calidad.</title>");
        printwriter.println("</HEAD>");
        printwriter.println("</HEAD>");
        printwriter.println("<script language=\"javascript\">");
        HttpSession httpsession = httpservletrequest.getSession(true);
        httpsession.putValue("SerapisUser", "");
        httpsession.putValue("SerapisPass", "");
        httpsession.putValue("SerapisNombre", "");
        httpsession.putValue("SerapisLic", "");
        printwriter.println("window.open(\"index.jsp\",\"_top\");");
        printwriter.println("</script>");
        printwriter.println("<body bgcolor='#FFFFFF'>");
        printwriter.println("</BODY>");
        printwriter.println("</HTML>");
    }
}