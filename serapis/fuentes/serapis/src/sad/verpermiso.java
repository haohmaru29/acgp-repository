// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   verpermiso.java

package sad;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class verpermiso extends HttpServlet
{

    public verpermiso()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        String s3 = "";
        String s5 = "";
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        UserReg = (String)httpsession.getValue("SerapisUser");
        IdPermiso = httpservletrequest.getParameter("ID");
        if(UserReg != null && UserReg.length() > 0)
        {
            if(IdPermiso != null && IdPermiso.length() > 0)
            {
                ADatos.connect();
                rs = new Vector();
                printwriter.println("<html>");
                printwriter.println("<head>");
                printwriter.println("</head>");
                printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
                printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
                AFunc.cargamenu(printwriter, 1);
                ADatos.connect();
                rs = new Vector();
                String s = "select pp.tipo, p.sigla, p.descripcion, pp.usuario, pp.idperfil, pp.tipopermiso from sad.permisosproceso pp, gdc.procesos p where p.sigla = pp.proceso and pp.idpermiso = " + IdPermiso;
                int i = ADatos.ConsultaDB(s);
                rs = ADatos.getResult();
                rs2 = (Vector)rs.clone();
                printwriter.println("<form name='frmpermisos' action='procesapermisos.jsp' method='post'>");
                if(rs.size() > 0)
                {
                    String s7 = "";
                    String s8 = "";
                    String s9 = "";
                    String s10 = "";
                    Integer integer = new Integer(0);
                    String s11 = "";
                    s7 = (String)rs2.elementAt(0);
                    s8 = (String)rs2.elementAt(1);
                    s9 = (String)rs2.elementAt(2);
                    s10 = (String)rs2.elementAt(3);
                    integer = (Integer)rs2.elementAt(4);
                    s11 = (String)rs2.elementAt(5);
                    printwriter.println("<table border='1' width='95%' align='center'>");
                    printwriter.println("<tr><td align='left' class='texttituloarea' colspan='2'>Editar Permiso</td></tr>");
                    printwriter.println("<tr><td align='center' class='textdesttabla' colspan='2'>&nbsp;</td></tr>");
                    printwriter.println("<tr><td align='left' class='textdesttabla' width='20%'><B>Proceso</B></td>");
                    printwriter.println("<td align='left' class='textdesttabla' width='80%'>" + s9.toString() + "</td></tr>");
                    printwriter.println("<tr><td align='left' class='textdesttabla' width='20%'><B>Tipo</B></td>");
                    if(s7.compareTo("U") == 0)
                    {
                        String s1 = "select nombre from sgc.usuarios where login='" + s10.toString() + "'";
                        int j = ADatos.ConsultaDB(s1);
                        rs = ADatos.getResult();
                        String s4 = (String)rs.elementAt(0);
                        printwriter.println("<td align='left' class='textdesttabla' width='80%'>Usuario</td></tr>");
                        printwriter.println("<tr><td align='left' class='textdesttabla' width='20%'><B>Usuario</B></td>");
                        printwriter.println("<td align='left' class='textdesttabla' width='80%'>" + s4.toString() + "</td></tr>");
                    } else
                    {
                        String s2 = "select perfil from sgc.perfiles where idperfil = " + integer.toString();
                        int k = ADatos.ConsultaDB(s2);
                        rs = ADatos.getResult();
                        String s6 = (String)rs.elementAt(0);
                        printwriter.println("<td align='left' class='textdesttabla' width='80%'>Perfil</td></tr>");
                        printwriter.println("<tr><td align='left' class='textdesttabla' width='20%'><B>Perfil</B></td>");
                        printwriter.println("<td align='left' class='textdesttabla' width='80%'>" + s6.toString() + "</td></tr>");
                    }
                    printwriter.println("<tr><td align='left' class='textdesttabla' width='20%'><B>Acceso</B><input type='hidden' name='hddopcion' language='javascript' value='00100'></td>");
                    printwriter.println("<td align='left' class='textdesttabla' width='80%'>");
                    if(s11.compareTo("10000") == 0)
                        printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"10000\";' checked>Control Total<BR>");
                    else
                        printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"10000\";'>Control Total<BR>");
                    if(s11.compareTo("01000") == 0)
                        printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"01000\";' checked>Escritura<BR>");
                    else
                        printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"01000\";'>Escritura<BR>");
                    if(s11.compareTo("00100") == 0)
                        printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"00100\";' checked>S\363lo Lectura<BR>");
                    else
                        printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"00100\";'>S\363lo Lectura<BR>");
                    if(s11.compareTo("00010") == 0)
                        printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"00010\";' checked>Eliminaci\363n<BR>");
                    else
                        printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"00010\";'>Eliminaci\363n<BR>");
                    if(s11.compareTo("00001") == 0)
                        printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"00001\";' checked>Sin acceso<BR>");
                    else
                        printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"00001\";'>Sin acceso<BR>");
                    printwriter.println("</td>");
                    printwriter.println("</tr>");
                    printwriter.println("</table>");
                    printwriter.println("<BR><BR><CENTER>");
                    printwriter.println("<input type='hidden' name='TIPO' language='javascript' value=''>");
                    printwriter.println("<input type='hidden' name='hddid' language='javascript' value='" + IdPermiso.toString() + "'>");
                    printwriter.println("<input type='submit' class='fondoinput' name='btnok' value='Modificar' language='javascript' onclick='document.frmpermisos.TIPO.value=\"2\"'>");
                    printwriter.println("<input type='button' class='fondoinput' name='btneliminar' value='Eliminar' language='javascript' onclick='return eliminar();'>");
                    printwriter.println("<input type='button' class='fondoinput' name='btnvolver' value='Volver' language='javascript' onclick=\"window.open('permisos.jsp?TIPO=PERM&SUBTIPO=P','datos');\">");
                    printwriter.println("</CENTER>");
                    printwriter.println("</form>");
                    printwriter.println("<script language='javascript'>");
                    printwriter.println("function eliminar()");
                    printwriter.println("{");
                    printwriter.println("   if(confirm(\"\277Esta seguro que desea eliminar el permiso?\"))");
                    printwriter.println("   {");
                    printwriter.println("      document.frmpermisos.TIPO.value=\"3\"");
                    printwriter.println("      document.frmpermisos.submit();");
                    printwriter.println("   }");
                    printwriter.println("}");
                    printwriter.println("</script>");
                } else
                {
                    printwriter.println("<table border='1' width='95%' align='center'>");
                    printwriter.println("<tr><td align='left' class='texttituloarea' colspan='2'>Editar Permiso</td></tr>");
                    printwriter.println("<tr><td align='center' class='textdesttabla' colspan='2'>&nbsp;</td></tr>");
                    printwriter.println("<tr><td align='left' class='textdesttabla' width='20%'>Permiso no encontrado</td></tr>");
                    printwriter.println("</table>");
                }
                printwriter.println("</body>");
                printwriter.println("</html>");
            } else
            {
                AFunc.reindex(httpservletrequest, printwriter, 1, "SAD", 6);
            }
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
    Vector rs2;
    String UserReg;
    String IdPermiso;
}