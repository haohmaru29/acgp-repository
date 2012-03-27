// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   procesaproyecto.java

package sad;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class procesaproyecto extends HttpServlet
{

    public procesaproyecto()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        Tipo = "";
        CodigoProyecto = "";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        String s = "";
        String s3 = "";
        String s4 = "";
        httpservletresponse.setContentType("text/html");
        java.io.PrintWriter printwriter = httpservletresponse.getWriter();
        Tipo = httpservletrequest.getParameter("TIPO");
        if(Tipo != null && Tipo.length() > 0)
        {
            HttpSession httpsession = httpservletrequest.getSession(true);
            UserReg = (String)httpsession.getValue("SerapisUser");
            if(UserReg != null && UserReg.length() > 0)
            {
                ADatos.connect();
                rs = new Vector();
                if(Tipo.compareTo("NEW") == 0)
                {
                    CodigoProyecto = httpservletrequest.getParameter("codproyecto");
                    String s10 = httpservletrequest.getParameter("lstperfiles");
                    String s11 = httpservletrequest.getParameter("lstusuarios");
                    String s1 = httpservletrequest.getParameter("hddopcion");
                    byte byte0 = 0;
                    String s12 = "";
                    String s16 = "U";
                    if(CodigoProyecto.length() > 0)
                    {
                        if(s10.length() > 0 || s11.length() > 0)
                        {
                            if(s1.length() > 0)
                            {
                                byte0 = 1;
                            } else
                            {
                                String s13 = "Debe seleccionar una opci\363n de acceso";
                                byte0 = 2;
                            }
                        } else
                        {
                            String s14 = "Debe seleccionar un perfil o usuario";
                            byte0 = 2;
                        }
                    } else
                    {
                        String s15 = "Debe seleccionar un proyecto";
                        byte0 = 2;
                    }
                    if(byte0 == 1)
                    {
                        if(s10.length() > 0)
                            s16 = "P";
                        else
                            s10 = "0";
                        String s7 = "insert into sad.permisosproyecto (idproyecto,tipo,usuario,idperfil,tipopermiso) values (";
                        s7 = s7 + "'" + CodigoProyecto + "',";
                        s7 = s7 + "'" + s16 + "',";
                        s7 = s7 + "'" + s11 + "',";
                        s7 = s7 + s10 + ",";
                        s7 = s7 + "'" + s1 + "')";
                        int i = ADatos.ModificaDB(s7);
                    }
                }
                if(Tipo.compareTo("MOD") == 0)
                {
                    CodigoProyecto = httpservletrequest.getParameter("codproyecto");
                    String s2 = httpservletrequest.getParameter("hddopcion");
                    String s5 = httpservletrequest.getParameter("hddid");
                    String s8 = "update sad.permisosproyecto set tipopermiso='" + s2 + "' where idpermiso = " + s5.toString();
                    int j = ADatos.ModificaDB(s8);
                }
                if(Tipo.compareTo("DEL") == 0)
                {
                    CodigoProyecto = httpservletrequest.getParameter("codproyecto");
                    String s6 = httpservletrequest.getParameter("hddid");
                    String s9 = "delete from sad.permisosproyecto where idpermiso = " + s6.toString();
                    int k = ADatos.ModificaDB(s9);
                }
            }
            httpservletresponse.sendRedirect("permisosproy.jsp?IDPROY=" + CodigoProyecto.toString());
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "SAD", 6);
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
    String UserReg;
    String Tipo;
    String CodigoProyecto;
}