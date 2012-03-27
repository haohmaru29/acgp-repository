// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   permisosproy.java

package sad;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class permisosproy extends HttpServlet
{

    public permisosproy()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        RutaSitio = "";
        RutaDocumentos = "";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        String s2 = "";
        String s4 = "";
        String s6 = "";
        String s8 = "";
        String s10 = "";
        Integer integer = new Integer(0);
        int k = 0;
        Integer integer2 = new Integer(0);
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s12 = (String)httpsession.getValue("SerapisUser");
        if(s12 != null && s12.length() > 0)
        {
            String s13 = httpservletrequest.getParameter("IDPROY");
            String s = "select COUNT(*) from gdc.proyectos where id = " + s13 + " and jefeproyecto = '" + s12 + "' or (id = " + s13 + " and jpbackup = '" + s12 + "' and validobackup = 'S')";
            int i = ADatos.ConsultaDB(s);
            rs = ADatos.getResult();
            Integer integer3 = (Integer)rs.elementAt(0);
            if(integer3.intValue() > 0)
            {
                printwriter.println("<html>");
                printwriter.println("<head>");
                printwriter.println("</head>");
                printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
                printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
                AFunc.cargamenu(printwriter, 1);
                printwriter.println("<form name='frmpermisos' action='' method='post'>");
                ADatos.connect();
                rs = new Vector();
                if(s13 != null && s13.length() > 0)
                {
                    printwriter.println("<table border='0' width='95%' align='center'>");
                    printwriter.println("<tr><td align='center'  class='texttituloarea'>Permisos por Proyecto</td></tr>");
                    printwriter.println("</table>");
                    String s1 = " select c.razonsocial,c.abreviatura,p.proyecto,p.abreviatura,u.nombre,p.id";
                    s1 = s1 + " from gdc.proyectos p, sgc.usuarios u, sgc.clientes c";
                    s1 = s1 + " where u.login = p.jefeproyecto and c.rutcliente = p.cliente";
                    s1 = s1 + " and p.id = " + s13;
                    int j = ADatos.ConsultaDB(s1);
                    rs = ADatos.getResult();
                    rs2 = (Vector)rs.clone();
                    String s3 = (String)rs2.elementAt(0);
                    String s5 = (String)rs2.elementAt(1);
                    String s7 = (String)rs2.elementAt(2);
                    String s9 = (String)rs2.elementAt(3);
                    String s11 = (String)rs2.elementAt(4);
                    Integer integer1 = (Integer)rs2.elementAt(5);
                    k += CargaProyecto(printwriter, integer1, s3, s7);
                    if(k == 0)
                    {
                        printwriter.println("<table border='1' width='95%' align='center'>");
                        printwriter.println("<tr><td align='center' class='textdesttabla'>No existen permisos asignados al proyecto</td></tr>");
                        printwriter.println("</table>");
                    }
                    printwriter.println("<BR><BR><center>");
                    printwriter.println("<input type='button' class='fondoinput' value='Nuevo' name='btnnuevo' language='javascript' onclick='window.open(\"permisoproyecto.jsp?TIPO=NEW&PROY=" + s13 + "\",\"datos\");'>");
                    printwriter.println("</center>");
                }
                printwriter.println("</form>");
                printwriter.println("<script languaje='javascript'>");
                printwriter.println("</script>");
                printwriter.println("</body>");
                printwriter.println("</html>");
            } else
            {
                httpservletresponse.sendRedirect("sinaccesoproyecto.jsp");
            }
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "SAD", 6);
        }
    }

    int CargaProyecto(PrintWriter printwriter, Integer integer, String s, String s1)
    {
        int l = 0;
        Integer integer1 = new Integer(0);
        Integer integer3 = new Integer(0);
        String s4 = "";
        String s6 = "";
        Integer integer4 = new Integer(0);
        String s8 = "";
        String s10 = "";
        String s11 = "";
        String s2 = " SELECT idpermiso, tipo, usuario, idperfil, tipopermiso";
        s2 = s2 + " FROM sad.permisosproyecto";
        s2 = s2 + " where idproyecto = " + integer.toString() + " order by tipo";
        int i = ADatos.ConsultaDB(s2);
        rs = ADatos.getResult();
        rs2 = (Vector)rs.clone();
        if(rs.size() > 0)
        {
            l++;
            printwriter.println("<BR>");
            printwriter.println("<table border='1' width='95%' align='center'>");
            printwriter.println("<tr><td align='left'  class='texttituloarea' colspan='7'>" + s + " - " + s1 + "</td></tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='texttitulotabla' rowspan='2' align='center' width='25%'>Usuario/Perfil</td>");
            printwriter.println("<td class='texttitulotabla' colspan='5' align='center' width='75%'>Accesos</td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='texttitulotabla' align='center'>Control Total</td>");
            printwriter.println("<td class='texttitulotabla' align='center'>Escritura</td>");
            printwriter.println("<td class='texttitulotabla' align='center'>S\363lo lectura</td>");
            printwriter.println("<td class='texttitulotabla' align='center'>Eliminaci\363n</td>");
            printwriter.println("<td class='texttitulotabla' align='center'>Sin Acceso</td>");
            printwriter.println("</tr>");
            for(int k = 0; k < rs2.size(); k += 5)
            {
                Integer integer2 = (Integer)rs2.elementAt(k);
                String s5 = (String)rs2.elementAt(k + 1);
                String s7 = (String)rs2.elementAt(k + 2);
                Integer integer5 = (Integer)rs2.elementAt(k + 3);
                String s9 = (String)rs2.elementAt(k + 4);
                String s3;
                if(s5.compareTo("U") == 0)
                    s3 = "select nombre from sgc.usuarios where login='" + s7 + "'";
                else
                    s3 = "select perfil from sgc.perfiles where idperfil=" + integer5.toString();
                int j = ADatos.ConsultaDB(s3);
                rs = ADatos.getResult();
                if(rs.size() > 0)
                    s10 = (String)rs.elementAt(0);
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla' align='left'><a href='permisoproyecto.jsp?TIPO=EDIT&PROY=" + integer.toString() + "&IDPERM=" + integer2.toString() + "'>" + s10 + "</a></td>");
                String s12 = s9.substring(0, 1);
                if(s12.compareTo("1") == 0)
                    printwriter.println("<td class='textdesttabla' align='center'>S\355</td>");
                else
                    printwriter.println("<td class='textdesttabla' align='center'>No</td>");
                s12 = s9.substring(1, 2);
                if(s12.compareTo("1") == 0)
                    printwriter.println("<td class='textdesttabla' align='center'>S\355</td>");
                else
                    printwriter.println("<td class='textdesttabla' align='center'>No</td>");
                s12 = s9.substring(2, 3);
                if(s12.compareTo("1") == 0)
                    printwriter.println("<td class='textdesttabla' align='center'>S\355</td>");
                else
                    printwriter.println("<td class='textdesttabla' align='center'>No</td>");
                s12 = s9.substring(3, 4);
                if(s12.compareTo("1") == 0)
                    printwriter.println("<td class='textdesttabla' align='center'>S\355</td>");
                else
                    printwriter.println("<td class='textdesttabla' align='center'>No</td>");
                s12 = s9.substring(4, 5);
                if(s12.compareTo("1") == 0)
                    printwriter.println("<td class='textdesttabla' align='center'>S\355</td>");
                else
                    printwriter.println("<td class='textdesttabla' align='center'>No</td>");
                printwriter.println("</tr>");
            }

            printwriter.println("</table>");
        }
        return l;
    }

    void CargaScript(PrintWriter printwriter)
    {
        printwriter.println("function NuevoPermiso(Dato)");
        printwriter.println("{");
        printwriter.println("   window.open('nuevopermiso.jsp','datos');");
        printwriter.println("}");
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    Vector rs2;
    String sql;
    String RutaSitio;
    String RutaDocumentos;
}