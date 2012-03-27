// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   procesa_registro_auditoria.java

package gdp;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class procesa_registro_auditoria extends HttpServlet
{

    public procesa_registro_auditoria()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        StrMes = "";
        StrAnio = "";
        StrProceso = "";
        NroFilas = "";
        IntNroFilas = new Integer(0);
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
            ADatos.connect();
            String s1 = httpservletrequest.getParameter("nroaudit");
            String s2 = httpservletrequest.getParameter("nrodoc");
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            String s3 = "update sad.documentos set id_auditoria = " + s1;
            s3 = s3 + " where id = " + s2;
            ADatos.ModificaDB(s3);
            printwriter.println("<script language='javascript'>");
            printwriter.println("   window.open('registro_auditoria.jsp','datos')");
            printwriter.println("</script>");
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

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    String IdNC;
    String StrMes;
    String StrAnio;
    String StrProceso;
    String NroFilas;
    Integer IntNroFilas;
}