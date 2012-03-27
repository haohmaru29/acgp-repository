// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   grabacomentario.java

package gdc;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class grabacomentario extends HttpServlet
{

    public grabacomentario()
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
        String sTipo = (httpservletrequest.getParameter("T")!=null)?httpservletrequest.getParameter("T").toString().trim():"";
        if(s != null && s.length() > 0)
        {
            String s1 = httpservletrequest.getParameter("codigodocumento");
            String s2 = httpservletrequest.getParameter("comentario");
            String s3 = httpservletrequest.getParameter("tipodespliegue");
            if(s2 == null)
                s2 = "";
            if(s1 != null && s1.length() > 0)
            {
                String s4 = "update " + (sTipo.equals("R")?"sad":"gdc") + ".documentos set comentario='" + s2 + "' where id = " + s1;
                int i = ADatos.ModificaDB(s4);
                printwriter.println("<HTML>");
                printwriter.println("<HEAD>");
                printwriter.println("<title>SERAPIS. Sistema de Gesti\363n de Calidad.</title>");
                printwriter.println("</HEAD>");
                printwriter.println("</HEAD>");
                printwriter.println("<script language=\"javascript\">");
                printwriter.println("window.open(\"verarchivo.jsp?T=" + sTipo + "&TIPO=" + s3 + "&ID=" + s1 + "\",\"datos\");");
                printwriter.println("</script>");
                printwriter.println("<body bgcolor='#FFFFFF'>");
                printwriter.println("</BODY>");
                printwriter.println("</HTML>");
            } else
            {
                AFunc.reindex(httpservletrequest, printwriter, 1, "GDC", 6);
            }
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

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
}