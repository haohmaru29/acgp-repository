// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   permisos.java

package sad;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class permisos extends HttpServlet
{

    public permisos()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        RutaSitio = "";
        RutaDocumentos = "";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        int l = 0;
        String s2 = "";
        String s4 = "";
        Integer integer = new Integer(0);
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s6 = (String)httpsession.getValue("SerapisUser");
        if(s6 != null && s6.length() > 0)
        {
            ADatos.connect();
            rs = new Vector();
            String s = "select COUNT(*) from gdc.procesos where lider='" + s6 + "' or (backuplider = '" + s6 + "' and validobackup = 'S')";
            int j = ADatos.ConsultaDB(s);
            rs = ADatos.getResult();
            Integer integer1 = (Integer)rs.elementAt(0);
            if(integer1.intValue() > 0)
            {
                String s7 = httpservletrequest.getParameter("TIPO");
                String s8 = httpservletrequest.getParameter("SUBTIPO");
                printwriter.println("<html>");
                printwriter.println("<head>");
                printwriter.println("</head>");
                printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
                printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
                AFunc.cargamenu(printwriter, 1);
                printwriter.println("<form name='frmpermisos' action='' method='post'>");
                ADatos.connect();
                rs = new Vector();
                if(s8.compareTo("P") == 0)
                {
                    printwriter.println("<table border='0' width='95%' align='center'>");
                    printwriter.println("<tr><td align='center'  class='texttituloarea'>Permisos por Proceso</td></tr>");
                    printwriter.println("</table>");
                    String s1 = "SELECT sigla,descripcion from gdc.procesos where lider = '" + s6 + "' or (backuplider = '" + s6 + "' and validobackup = 'S') order by descripcion";
                    int k = ADatos.ConsultaDB(s1);
                    rs = ADatos.getResult();
                    rs3 = (Vector)rs.clone();
                    for(int i = 0; i < rs3.size(); i += 2)
                    {
                        String s3 = (String)rs3.elementAt(i);
                        String s5 = (String)rs3.elementAt(i + 1);
                        l += CargaProceso(printwriter, s3, s5);
                    }

                    if(l == 0)
                    {
                        printwriter.println("<table border='1' width='95%' align='center'>");
                        printwriter.println("<tr><td align='center' class='textdesttabla'>No existen permisos asignados al proceso</td></tr>");
                        printwriter.println("</table>");
                    }
                    printwriter.println("<BR><BR><center>");
                    printwriter.println("<input type='button' class='fondoinput' value='Nuevo' name='btnnuevo' language='javascript' onclick='return NuevoPermiso();'>");
                    printwriter.println("</center>");
                }
                printwriter.println("</form>");
                printwriter.println("<script languaje='javascript'>");
                CargaScript(printwriter);
                printwriter.println("</script>");
                printwriter.println("</body>");
                printwriter.println("</html>");
            } else
            {
                httpservletresponse.sendRedirect("sinaccesoproceso.jsp");
            }
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "SAD", 6);
        }
    }

    int CargaProceso(PrintWriter printwriter, String s, String s1)
    {
        int l = 0;
        String s4 = "";
        String s6 = "";
        String s8 = "";
        Integer integer = new Integer(0);
        Integer integer2 = new Integer(0);
        String s10 = "";
        String s12 = "";
        String s13 = "";
        String s2 = "SELECT p.descripcion,pp.tipo,pp.usuario,pp.idperfil,pp.tipopermiso, pp.idpermiso FROM sad.permisosproceso pp, gdc.procesos p where p.sigla = pp.proceso and pp.proceso = '" + s + "' order by pp.tipo";
        int i = ADatos.ConsultaDB(s2);
        rs = ADatos.getResult();
        rs2 = (Vector)rs.clone();
        if(rs.size() > 0)
        {
            l++;
            printwriter.println("<BR>");
            printwriter.println("<table border='1' width='95%' align='center'>");
            printwriter.println("<tr><td align='left'  class='texttituloarea' colspan='7'>" + s1 + "</td></tr>");
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
            for(int k = 0; k < rs2.size(); k += 6)
            {
                String s5 = (String)rs2.elementAt(k);
                String s7 = (String)rs2.elementAt(k + 1);
                String s9 = (String)rs2.elementAt(k + 2);
                Integer integer1 = (Integer)rs2.elementAt(k + 3);
                String s11 = (String)rs2.elementAt(k + 4);
                Integer integer3 = (Integer)rs2.elementAt(k + 5);
                String s3;
                if(s7.compareTo("U") == 0)
                    s3 = "select nombre from sgc.usuarios where login='" + s9 + "'";
                else
                    s3 = "select perfil from sgc.perfiles where idperfil=" + integer1.toString();
                int j = ADatos.ConsultaDB(s3);
                rs = ADatos.getResult();
                if(rs.size() > 0)
                    s12 = (String)rs.elementAt(0);
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla' align='left'><a href='verpermiso.jsp?ID=" + integer3.toString() + "'>" + s12 + "</a></td>");
                String s14 = s11.substring(0, 1);
                if(s14.compareTo("1") == 0)
                    printwriter.println("<td class='textdesttabla' align='center'>S\355</td>");
                else
                    printwriter.println("<td class='textdesttabla' align='center'>No</td>");
                s14 = s11.substring(1, 2);
                if(s14.compareTo("1") == 0)
                    printwriter.println("<td class='textdesttabla' align='center'>S\355</td>");
                else
                    printwriter.println("<td class='textdesttabla' align='center'>No</td>");
                s14 = s11.substring(2, 3);
                if(s14.compareTo("1") == 0)
                    printwriter.println("<td class='textdesttabla' align='center'>S\355</td>");
                else
                    printwriter.println("<td class='textdesttabla' align='center'>No</td>");
                s14 = s11.substring(3, 4);
                if(s14.compareTo("1") == 0)
                    printwriter.println("<td class='textdesttabla' align='center'>S\355</td>");
                else
                    printwriter.println("<td class='textdesttabla' align='center'>No</td>");
                s14 = s11.substring(4, 5);
                if(s14.compareTo("1") == 0)
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
    Vector rs3;
    String sql;
    String RutaSitio;
    String RutaDocumentos;
}