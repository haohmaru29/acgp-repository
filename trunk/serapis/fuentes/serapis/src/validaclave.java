// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   validaclave.java

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class validaclave extends HttpServlet
{

    public validaclave()
    {
        TieneAviso = 0;
        ADatos = new AccDataBase();
        func = new funciones();
        NombreUsuario = "";
        Alias = "";
        login = "";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        login = httpservletrequest.getParameter("Login");
        String s = httpservletrequest.getParameter("Pass");
        int i = 0;
        printwriter.println("<HTML>");
        printwriter.println("<HEAD>");
        printwriter.println("<title>SERAPIS. Sistema de Gesti\363n de Calidad.</title>");
        printwriter.println("</HEAD>");
        printwriter.println("</HEAD>");
        i = ValidaClave(login, s, printwriter);
        printwriter.println("<script language=\"javascript\">");
        switch(i)
        {
        case 6: // '\006'
        case 7: // '\007'
        default:
            break;

        case 0: // '\0'
            HttpSession httpsession = httpservletrequest.getSession(true);
            httpsession.putValue("SerapisUser", login);
            httpsession.putValue("SerapisPass", s);
            httpsession.putValue("SerapisNombre", Alias);
            httpsession.putValue("SerapisLic", "1");
            String s1 = (String)httpsession.getValue("SerapisSis");
            printwriter.println("window.open(\"inicializa.jsp\",\"_top\");");
            if(TieneAviso == 1)
                printwriter.println("window.open(\"aviso.jsp\",\"_blank\",\"toolbar=no,left=0,top=20,width=750,height=400,directories=no,status=yes,scrollbars=yes,resize=yes,menubar=no\");");
            break;

        case 1: // '\001'
            printwriter.println("window.open(\"cambio.jsp?USER=" + login + "\",\"cuerpo\");");
            break;

        case 2: // '\002'
            printwriter.println("window.open(\"autentica.jsp?ERR=2\",\"cuerpo\");");
            break;

        case 3: // '\003'
            printwriter.println("window.open(\"autentica.jsp?ERR=3\",\"cuerpo\");");
            break;

        case 4: // '\004'
            printwriter.println("window.open(\"autentica.jsp?ERR=4\",\"cuerpo\");");
            break;

        case 5: // '\005'
            printwriter.println("window.open(\"autentica.jsp?ERR=5\",\"cuerpo\");");
            break;

        case 8: // '\b'
            printwriter.println("window.open(\"autentica.jsp?ERR=8\",\"cuerpo\");");
            break;

        case 9: // '\t'
            printwriter.println("alert(\"Su clave a caducado. Debe modificarla y autenticarse nuevamente.\");");
            printwriter.println("window.open(\"cambio.jsp?USER=" + login + "&ERR=5\",\"cuerpo\");");
            break;
        }
        printwriter.println("</script>");
        printwriter.println("<body bgcolor='#FFFFFF'>");
        printwriter.println("</BODY>");
        printwriter.println("</HTML>");
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    int ValidaClave(String s, String s1, PrintWriter printwriter)
    {
        byte byte0 = 0;
        String s2 = "";
        rs = new Vector();
        Integer integer = new Integer(0);
        Integer integer1 = new Integer(0);
        Integer integer2 = new Integer(0);
        Vector vector = new Vector();
        Vector vector1 = new Vector();
        String s3 = "select nombre,password,fechapass,estado,alias from sgc.usuarios where login='" + s + "'";
        ADatos.connect();
        int i = ADatos.ConsultaDB(s3);
        rs = ADatos.getResult();
        if(rs.size() > 0)
        {
            String s4 = (String)rs.elementAt(3);
            if(s4.compareTo("A") == 0)
            {
                NombreUsuario = (String)rs.elementAt(0);
                Alias = (String)rs.elementAt(4);
                String s5 = (String)rs.elementAt(1);
                if(s5.length() > 0)
                {
                    s5 = func.desencripta(s5);
                    if(func.EsValida(printwriter, s5) == 1)
                    {
                        s1 = s1.trim();
                        s5 = s5.trim();
                        if(s5.compareTo(s1) == 0)
                        {
                            Integer integer3 = (Integer)rs.elementAt(2);
                            Vector vector2 = func.AddFecha(integer3.toString(), 30);
                            Vector vector3 = func.ObtieneFecha();
                            Integer integer4 = new Integer((String)vector2.elementAt(0));
                            Integer integer5 = new Integer((String)vector3.elementAt(0));
                            if(integer4.longValue() < integer5.longValue())
                            {
                                byte0 = 9;
                            } else
                            {
                                String s6 = "select empresa,nrolic,version from sgc.licencia";
                                int j = ADatos.ConsultaDB(s6);
                                rs = ADatos.getResult();
                                if(rs.size() > 0)
                                {
                                    byte0 = 0;
                                    String s7 = "SELECT * from gdc.workflow_flujo where usuario='" + s + "' AND accion='P'";
                                    int k = ADatos.ConsultaDB(s7);
                                    rs = ADatos.getResult();
                                    if(rs.size() > 0)
                                        TieneAviso = 1;
                                } else
                                {
                                    byte0 = 8;
                                }
                            }
                        } else
                        {
                            byte0 = 2;
                        }
                    } else
                    {
                        byte0 = 4;
                    }
                } else
                {
                    byte0 = 1;
                }
            } else
            {
                byte0 = 5;
            }
        } else
        {
            byte0 = 3;
        }
        return byte0;
    }

    int TieneAviso;
    AccDataBase ADatos;
    funciones func;
    Vector rs;
    String NombreUsuario;
    String login;
    String Alias;
}