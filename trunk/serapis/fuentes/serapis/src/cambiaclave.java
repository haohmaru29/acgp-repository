// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   cambiaclave.java

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class cambiaclave extends HttpServlet
{

    public cambiaclave()
    {
        ADatos = new AccDataBase();
        func = new funciones();
        NombreUsuario = "";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        byte byte0 = 0;
        boolean flag = false;
        boolean flag1 = false;
        byte byte1 = 0;
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        String s = httpservletrequest.getParameter("Login");
        String s1 = httpservletrequest.getParameter("Pass");
        String s2 = httpservletrequest.getParameter("NewPass");
        String s3 = httpservletrequest.getParameter("ReNewPass");
        int k = 0;
        s = s.trim();
        s1 = s1.trim();
        s2 = s2.trim();
        s3 = s3.trim();
        k = ValidaClave(s, s1, printwriter);
        printwriter.println("<HTML>");
        printwriter.println("<HEAD>");
        printwriter.println("<title>SERAPIS. Sistema de Gesti\363n de Calidad.</title>");
        printwriter.println("</HEAD>");
        printwriter.println("</HEAD>");
        printwriter.println("<script language=\"javascript\">");
        switch(k)
        {
        case 0: // '\0'
        case 1: // '\001'
            int j = func.EsValida(printwriter, s2);
            if(j == 1)
            {
                int i = CambiaClave(s, s2);
                if(i == 0)
                {
                    printwriter.println("alert(\"Clave mdificada exitosamente. Debe autenticarse para accesar el sitio.\");");
                    byte0 = 1;
                } else
                {
                    printwriter.println("alert(\"Problemas al cambiar la clave. Intente mas tarde\");");
                    byte1 = 1;
                }
            } else
            {
                byte0 = 2;
                byte1 = 2;
            }
            break;

        case 2: // '\002'
            byte0 = 2;
            byte1 = 3;
            break;

        case 3: // '\003'
            printwriter.println("alert(\"Usuario no existe\");");
            byte1 = 4;
            break;

        case 4: // '\004'
            byte1 = 3;
            byte0 = 2;
            break;
        }
        switch(byte0)
        {
        case 0: // '\0'
            printwriter.println("window.open(\"inicializa.jsp\",\"_top\");");
            break;

        case 1: // '\001'
            printwriter.println("window.open(\"autentica.jsp\",\"cuerpo\");");
            break;

        case 2: // '\002'
            printwriter.println("window.open(\"cambio.jsp?USER=" + s + "&ERR=" + byte1 + "\",\"cuerpo\");");
            break;

        default:
            printwriter.println("window.open(\"inicializa.jsp\",\"_top\");");
            break;
        }
        printwriter.println("</script>");
        printwriter.println("<body bgcolor='#FFFFFF'>");
        printwriter.println("</BODY>");
        printwriter.println("</HTML>");
    }

    public int CambiaClave(String s, String s1)
    {
        byte byte0 = 0;
        Vector vector = new Vector();
        vector = func.ObtieneFecha();
        String s3 = vector.elementAt(0).toString();
        s1 = func.encripta(s1);
        String s2 = "Update sgc.usuarios set password = '" + s1 + "', fechapass=" + s3 + " where login = '" + s + "'";
        ADatos.connect();
        int i = ADatos.ModificaDB(s2);
        if(i == -1)
            byte0 = -1;
        return byte0;
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    int ValidaClave(String s, String s1, PrintWriter printwriter)
    {
        byte byte0 = 0;
        rs = new Vector();
        String s3 = "select nombre,password,fechapass from sgc.usuarios where login='" + s + "'";
        ADatos.connect();
        int i = ADatos.ConsultaDB(s3);
        rs = ADatos.getResult();
        if(rs.size() > 0)
        {
            NombreUsuario = (String)rs.elementAt(0);
            String s2 = (String)rs.elementAt(1);
            if(s2.length() > 0)
            {
                s2 = func.desencripta(s2);
                s1 = s1.trim();
                s2 = s2.trim();
                if(func.EsValida(printwriter, s2) == 1)
                {
                    if(s2.compareTo(s1) == 0)
                        byte0 = 0;
                    else
                        byte0 = 2;
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
            byte0 = 3;
        }
        return byte0;
    }

    AccDataBase ADatos;
    funciones func;
    Vector rs;
    String NombreUsuario;
}