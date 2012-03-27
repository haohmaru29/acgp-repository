// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   inicio.java

package sad;

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
        Sistema = "SAD";
        Tipo = "";
        SubTipo = "";
        Proceso = "";
        TipoDoc = "";
        Cliente = "";
        Proyecto = "";
        NroId = "";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        httpsession.putValue("SerapisSis", "SAD");
        Licencia = (String)httpsession.getValue("SerapisLic");
        Tipo = httpservletrequest.getParameter("TIPO");
        if(Tipo == null)
            Tipo = "";
        SubTipo = httpservletrequest.getParameter("SUBTIPO");
        if(SubTipo == null)
            SubTipo = "";
        Proceso = httpservletrequest.getParameter("PROCESO");
        if(Proceso == null)
            Proceso = "";
        TipoDoc = httpservletrequest.getParameter("TIPODOC");
        if(TipoDoc == null)
            TipoDoc = "";
        Cliente = httpservletrequest.getParameter("CLI");
        if(Cliente == null)
            Cliente = "";
        Proyecto = httpservletrequest.getParameter("PROY");
        if(Proyecto == null)
            Proyecto = "";
        NroId = httpservletrequest.getParameter("ID");
        if(NroId == null)
            NroId = "";
        if(Tipo.length() == 0)
            Tipo = "DOC";
        if(SubTipo.length() == 0)
            SubTipo = "A";
        if(Licencia == null || Licencia.length() == 0)
            Licencia = "";
        Usuario = (String)httpsession.getValue("SerapisUser");
        if(Usuario != null && Usuario.length() > 0)
        {
            if(Licencia.compareTo("1") == 0)
            {
                printwriter.println("<html>");
                printwriter.println("<head>");
                printwriter.println("</head>");
                printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='serapis.css'>");
                printwriter.println("<TITLE>SERAPIS. Sistema de Gesti\363n de Calidad.</TITLE>");
                printwriter.println("<frameset framespacing='0' border='true' frameborder='0' cols='30%,*'>");
                printwriter.println("  <frame name='menu' src='menu.jsp?TIPO=" + Tipo + "&SUBTIPO=" + SubTipo + "&PROCESO=" + Proceso + "&TIPODOC=" + TipoDoc + "&CLI=" + Cliente + "&PROY=" + Proyecto + "' scrolling='yes' noresize>");
                if(Tipo.compareTo("DOC") == 0)
                    printwriter.println("  <frame name='datos' src='vacio.htm' scrolling='yes' noresize>");
                if(Tipo.compareTo("PUB") == 0)
                    if(SubTipo.compareTo("A") == 0)
                        printwriter.println("<frame name='datos' src='publicaarea.jsp' scrolling='yes' noresize>");
                    else
                    if(SubTipo.compareTo("P") == 0)
                        printwriter.println("<frame name='datos' src='publicaproyecto.jsp' scrolling='yes' noresize>");
                    else
                        printwriter.println("  <frame name='datos' src='muestradoc.jsp?TIPO=" + Tipo + "&SUBTIPO=" + SubTipo + "' scrolling='yes' noresize>");
                if(Tipo.compareTo("RWF") == 0)
                    printwriter.println("<frame name='datos' src='documentoswf.jsp' scrolling='yes' noresize>");
                if(Tipo.compareTo("RRE") == 0)
                    printwriter.println("<frame name='datos' src='desestimados.jsp' scrolling='yes' noresize>");
                if(Tipo.compareTo("CLI") == 0)
                    printwriter.println("<frame name='datos' src='vercliente.jsp' scrolling='yes' noresize>");
                if(Tipo.compareTo("ENC") == 0)
                    printwriter.println("<frame name='datos' src='encargados.jsp' scrolling='yes' noresize>");
                if(Tipo.compareTo("CARO") == 0)
                    printwriter.println("<frame name='datos' src='listacargosroles.jsp' scrolling='yes' noresize>");
                if(Tipo.compareTo("PERM") == 0)
                    if(SubTipo.compareTo("P") == 0)
                        printwriter.println("<frame name='datos' src='permisos.jsp?TIPO=" + Tipo + "&SUBTIPO=" + SubTipo + "' scrolling='yes' noresize>");
                    else
                    if(SubTipo.compareTo("Y") == 0)
                        printwriter.println("<frame name='datos' src='permisosclientes.jsp' scrolling='yes' noresize>");
                    else
                        printwriter.println("<frame name='datos' src='permisos.jsp?TIPO=" + Tipo + "&SUBTIPO=P' scrolling='yes' noresize>");
                if(Tipo.compareTo("PERF") == 0)
                    printwriter.println("<frame name='datos' src='perfilesyusuarios.jsp' scrolling='yes' noresize>");
                printwriter.println("</frameset>");
                printwriter.println("</body>");
                printwriter.println("</html>");
            } else
            {
                AFunc.reindex(httpservletrequest, printwriter, 1, "SAD", 8);
            }
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "SAD", 0);
        }
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    String sql;
    String Licencia;
    String Usuario;
    String Sistema;
    String Tipo;
    String SubTipo;
    String Proceso;
    String TipoDoc;
    String Cliente;
    String Proyecto;
    String NroId;
}