// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   inicio.java

package gdp;

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
        httpsession.putValue("SerapisSis", "GDP");
        Usuario = (String)httpsession.getValue("SerapisUser");
        if(Usuario != null && Usuario.length() > 0)
        {
            String s = "select lider from gdc.procesos";
            s = s + " where lider='" + Usuario + "'";
            s = s + " or (backuplider = '" + Usuario + "' and validobackup='S')";
            rs = new Vector();
            ADatos.connect();
            int i = ADatos.ConsultaDB(s);
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                Licencia = (String)httpsession.getValue("SerapisLic");
                String s1 = httpservletrequest.getParameter("TIPO");
                String s2 = httpservletrequest.getParameter("AREA");
                String s3 = httpservletrequest.getParameter("TIPODOC");
                if(s1 == null || s1.length() == 0)
                    s1 = "PNC";
                if(s2 == null)
                    s2 = "";
                if(s3 == null)
                    s3 = "";
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
                    printwriter.println("  <frame name='menu' src='menu.jsp?TIPO=" + s1 + "&AREA=" + s2 + "&TIPODOC=" + s3 + "' scrolling='yes' noresize border='10'>");
                    if(s1.compareTo("PNC") == 0)
                        printwriter.println("  <frame name='datos' src='nc.jsp' scrolling='yes' noresize>");
                    if(s1.compareTo("PNCINC") == 0)
                        printwriter.println("  <frame name='datos' src='nc.jsp' scrolling='yes' noresize>");
                    if(s1.compareTo("PNCRCE") == 0)
                        printwriter.println("  <frame name='datos' src='nc_genera_informe.jsp' scrolling='yes' noresize>");
                    if(s1.compareTo("PAU") == 0)
                        printwriter.println("  <frame name='datos' src='plan_auditoria.jsp' scrolling='yes' noresize>");
                    if(s1.compareTo("RAU") == 0)
                        printwriter.println("  <frame name='datos' src='registro_auditoria.jsp' scrolling='yes' noresize>");
                    if(s1.compareTo("ACPGG") == 0)
                        printwriter.println("  <frame name='datos' src='gg_grafico_gestion.jsp' scrolling='yes' noresize>");
                    if(s1.compareTo("ACPTC") == 0)
                        printwriter.println("  <frame name='datos' src='gg_tablero_control.jsp' scrolling='yes' noresize>");
                    if(s1.compareTo("ACPMIN") == 0)
                        printwriter.println("  <frame name='datos' src='minuta_lista.jsp' scrolling='yes' noresize>");
                    if(s1.compareTo("ACP") == 0)
                        printwriter.println("  <frame name='datos' src='acp.jsp' scrolling='yes' noresize>");
                    if(s1.compareTo("VAC") == 0)
                        printwriter.println("  <frame name='datos' src='vacio.htm' scrolling='yes' noresize>");
                    printwriter.println("</frameset>");
                    printwriter.println("</body>");
                    printwriter.println("</html>");
                } else
                {
                    AFunc.reindex(httpservletrequest, printwriter, 1, "GDP", 8);
                }
            } else
            {
                printwriter.println("<HTML>");
                printwriter.println("<HEAD>");
                printwriter.println("<title>SERAPIS. Sistema de Gesti\363n de Calidad.</title>");
                printwriter.println("</HEAD>");
                printwriter.println("</HEAD>");
                printwriter.println("<script language=\"javascript\">");
                printwriter.println("alert(\"Debe ser lider o backup habilitado para ingresar a esta opci\363n.\");");
                printwriter.println("window.open(\"../cuerpo.jsp\",\"cuerpo\");");
                printwriter.println("</script>");
                printwriter.println("<body bgcolor='#FFFFFF'>");
                printwriter.println("</BODY>");
                printwriter.println("</HTML>");
            }
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "GDP", 0);
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