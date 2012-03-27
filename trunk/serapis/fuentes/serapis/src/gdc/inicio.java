// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   inicio.java

package gdc;

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
        httpsession.putValue("SerapisSis", "GDC");
        Usuario = (String)httpsession.getValue("SerapisUser");
        if(Usuario != null && Usuario.length() > 0)
        {
            Licencia = (String)httpsession.getValue("SerapisLic");
            String s = httpservletrequest.getParameter("TIPO");
            String s1 = httpservletrequest.getParameter("AREA");
            String s2 = httpservletrequest.getParameter("TIPODOC");
            if(s == null)
                s = "DOC";
            if(s1 == null)
                s1 = "";
            if(s2 == null)
                s2 = "";
            if(Licencia == null || Licencia.length() == 0)
                Licencia = "";
            if(Licencia.compareTo("1") == 0)
            {
                printwriter.println("<html>");
                printwriter.println("<head>");
                printwriter.println("</head>");
                printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='serapis.css'>");
                printwriter.println("<TITLE>SERAPIS. Sistema de Gesti\363n de Calidad.</TITLE>");
                printwriter.println("<frameset framespacing='0' frameborder='0' cols='30%,*'>");
                printwriter.println("  <frame name='menu' src='menu.jsp?TIPO=" + s + "&AREA=" + s1 + "&TIPODOC=" + s2 + "' scrolling='yes' noresize border='10'>");
                if(s.compareTo("DOC") == 0)
                    printwriter.println("  <frame name='datos' src='muestradoc.jsp?TIPO=" + s + "&TIPODOC=" + s2 + "&AREA=" + s1 + "' scrolling='yes' noresize>");
                if(s.compareTo("PUB") == 0)
                    printwriter.println("<frame name='datos' src='publicar.jsp' scrolling='yes' noresize>");
                if(s.compareTo("LMR") == 0)
                    printwriter.println("<frame name='datos' src='listamaestra.jsp?TIPO=R' scrolling='yes' noresize>");
                if(s.compareTo("LMD") == 0)
                    printwriter.println("<frame name='datos' src='listamaestra.jsp?TIPO=D' scrolling='yes' noresize>");
                if(s.compareTo("WF") == 0)
                    printwriter.println("<frame name='datos' src='workflow.jsp' scrolling='yes' noresize>");
                if(s.compareTo("ENC") == 0)
                    printwriter.println("  <frame name='datos' src='encargados.jsp' scrolling='yes' noresize>");
                if(s.compareTo("DWF") == 0)
                    printwriter.println("  <frame name='datos' src='documentoswf.jsp' scrolling='yes' noresize>");
                if(s.compareTo("ELIM") == 0)
                    printwriter.println("  <frame name='datos' src='desestimados.jsp' scrolling='yes' noresize>");
                if(s.compareTo("NC") == 0)
                    printwriter.println("  <frame name='datos' src='nc.jsp' scrolling='yes' noresize>");
                printwriter.println("</frameset>");
                printwriter.println("</body>");
                printwriter.println("</html>");
            } else
            {
                AFunc.reindex(httpservletrequest, printwriter, 1, "GDC", 8);
            }
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "GDC", 0);
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