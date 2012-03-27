// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   permisoproyecto.java

package sad;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class permisoproyecto extends HttpServlet
{

    public permisoproyecto()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        String s6 = "";
        String s7 = "";
        Integer integer = new Integer(0);
        String s8 = "";
        String s10 = "";
        String s12 = "";
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        UserReg = (String)httpsession.getValue("SerapisUser");
        if(UserReg != null && UserReg.length() > 0)
        {
            String s14 = httpservletrequest.getParameter("TIPO");
            String s15 = httpservletrequest.getParameter("PROY");
            if(s14 != null && s14.length() > 0 && s15 != null && s15.length() > 0)
            {
                ADatos.connect();
                rs = new Vector();
                printwriter.println("<html>");
                printwriter.println("<head>");
                printwriter.println("</head>");
                printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
                printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
                AFunc.cargamenu(printwriter, 1);
                printwriter.println("<form name='frmpermisos' action='' method='post'>");
                if(s14.compareTo("NEW") == 0)
                {
                    String s = " select c.razonsocial,c.abreviatura,p.proyecto,p.abreviatura,u.nombre,p.id";
                    s = s + " from gdc.proyectos p, sgc.usuarios u, sgc.clientes c";
                    s = s + " where u.login = p.jefeproyecto and c.rutcliente = p.cliente";
                    s = s + " and p.id = " + s15;
                    int k = ADatos.ConsultaDB(s);
                    rs = ADatos.getResult();
                    rs2 = (Vector)rs.clone();
                    if(rs2.size() > 0)
                    {
                        printwriter.println("<input type='hidden' name='codproyecto' value='" + s15 + "'>");
                        printwriter.println("<table border='1' width='95%' align='center'>");
                        printwriter.println("<tr><td align='center' class='texttituloarea' colspan='2'>Permisos por Proyecto</td></tr>");
                        printwriter.println("<tr><td align='center' class='textdesttabla' colspan='2'>&nbsp;</td></tr>");
                        printwriter.println("<tr><td align='left' class='textdesttabla'><B>Cliente</B></td>");
                        printwriter.println("<td align='left' class='textdesttabla'>" + rs2.elementAt(0) + "</td></tr>");
                        printwriter.println("<tr><td align='left' class='textdesttabla'><B>Proyecto</B></td>");
                        printwriter.println("<td align='left' class='textdesttabla'>" + rs2.elementAt(2) + "</td></tr>");
                        printwriter.println("<tr><td align='left' class='textdesttabla'><B>Perfil</B></td>");
                        printwriter.println("<td align='left' class='textdesttabla'>");
                        String s1 = "SELECT idperfil,perfil from sgc.perfiles where estado='A'";
                        int l = ADatos.ConsultaDB(s1);
                        rs = ADatos.getResult();
                        printwriter.println("<select name='lstperfiles'>");
                        printwriter.println("<option value=''>[Seleccione Perfil]</option>");
                        for(int i = 0; i < rs.size(); i += 2)
                        {
                            Integer integer1 = (Integer)rs.elementAt(i);
                            String s9 = (String)rs.elementAt(i + 1);
                            printwriter.println("<option value='" + integer1 + "'>" + s9 + "</option>");
                        }

                        printwriter.println("</select>");
                        printwriter.println("</td>");
                        printwriter.println("</tr>");
                        printwriter.println("<tr><td align='left' class='textdesttabla'><B>Usuarios</B></td>");
                        printwriter.println("<td align='left' class='textdesttabla'>");
                        s1 = "SELECT login,nombre from sgc.usuarios where estado='A' order by nombre";
                        l = ADatos.ConsultaDB(s1);
                        rs = ADatos.getResult();
                        printwriter.println("<select name='lstusuarios'>");
                        printwriter.println("<option value=''>[Seleccione Usuario]</option>");
                        for(int j = 0; j < rs.size(); j += 2)
                        {
                            String s11 = (String)rs.elementAt(j);
                            String s13 = (String)rs.elementAt(j + 1);
                            printwriter.println("<option value='" + s11 + "'>" + s13 + "</option>");
                        }

                        printwriter.println("</select>");
                        printwriter.println("</td>");
                        printwriter.println("</tr>");
                        printwriter.println("<tr><td align='left' class='textdesttabla'><B>Acceso</B><input type='hidden' name='hddopcion' language='javascript' value='00100'></td>");
                        printwriter.println("<td align='left' class='textdesttabla'>");
                        printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"10000\";'>Control Total<BR>");
                        printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"01000\";'>Escritura<BR>");
                        printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"00100\";'checked>S\363lo Lectura<BR>");
                        printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"00010\";'>Eliminaci\363n<BR>");
                        printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"00001\";'>Sin acceso<BR>");
                        printwriter.println("</td>");
                        printwriter.println("</tr>");
                        printwriter.println("</table>");
                        printwriter.println("<BR><BR><CENTER>");
                        printwriter.println("<input type='button' class='fondoinput' name='btnok' value='Aceptar' language='javascript' onclick='return GrabarProceso();'>");
                        printwriter.println("<input type='button' class='fondoinput' name='btnvolver' value='Cancelar' language='javascript' onclick=\"window.open('permisosclientes.jsp','datos');\">");
                        printwriter.println("</CENTER>");
                        printwriter.println("</form>");
                        printwriter.println("<script languaje='javascript'>");
                        CargaScript(printwriter);
                        printwriter.println("</script>");
                        printwriter.println("</body>");
                        printwriter.println("</html>");
                    }
                }
                if(s14.compareTo("EDIT") == 0)
                {
                    String s16 = httpservletrequest.getParameter("IDPERM");
                    String s2 = " select c.razonsocial,c.abreviatura,p.proyecto,p.abreviatura,u.nombre,p.id";
                    s2 = s2 + " from gdc.proyectos p, sgc.usuarios u, sgc.clientes c";
                    s2 = s2 + " where u.login = p.jefeproyecto and c.rutcliente = p.cliente";
                    s2 = s2 + " and p.id = " + s15;
                    int i1 = ADatos.ConsultaDB(s2);
                    rs = ADatos.getResult();
                    rs2 = (Vector)rs.clone();
                    if(rs2.size() > 0)
                    {
                        printwriter.println("<input type='hidden' name='codproyecto' value='" + s15 + "'>");
                        printwriter.println("<input type='hidden' name='hddid' value='" + s16 + "'>");
                        printwriter.println("<table border='1' width='95%' align='center'>");
                        printwriter.println("<tr><td align='center' class='texttituloarea' colspan='2'>Permisos por Proyecto</td></tr>");
                        printwriter.println("<tr><td align='center' class='textdesttabla' colspan='2'>&nbsp;</td></tr>");
                        printwriter.println("<tr><td align='left' class='textdesttabla'><B>Cliente</B></td>");
                        printwriter.println("<td align='left' class='textdesttabla'>" + rs2.elementAt(0) + "</td></tr>");
                        printwriter.println("<tr><td align='left' class='textdesttabla'><B>Proyecto</B></td>");
                        printwriter.println("<td align='left' class='textdesttabla'>" + rs2.elementAt(2) + "</td></tr>");
                        String s3 = "SELECT tipo, usuario, idperfil, tipopermiso FROM sad.permisosproyecto where idpermiso = " + s16;
                        int j1 = ADatos.ConsultaDB(s3);
                        rs = ADatos.getResult();
                        Integer integer2 = new Integer(0);
                        String s17 = (String)rs.elementAt(0);
                        String s18 = (String)rs.elementAt(1);
                        integer2 = (Integer)rs.elementAt(2);
                        String s19 = (String)rs.elementAt(3);
                        if(s17.compareTo("P") == 0)
                        {
                            printwriter.println("<tr><td align='left' class='textdesttabla'><B>Perfil</B></td>");
                            printwriter.println("<td align='left' class='textdesttabla'>");
                            String s4 = "SELECT perfil from sgc.perfiles where idperfil = " + integer2.toString();
                            int k1 = ADatos.ConsultaDB(s4);
                            rs = ADatos.getResult();
                            printwriter.println(rs.elementAt(0) + "</td></tr>");
                        } else
                        {
                            printwriter.println("<tr><td align='left' class='textdesttabla'><B>Usuario</B></td>");
                            printwriter.println("<td align='left' class='textdesttabla'>");
                            String s5 = "SELECT nombre from sgc.usuarios where login = '" + s18 + "'";
                            int l1 = ADatos.ConsultaDB(s5);
                            rs = ADatos.getResult();
                            printwriter.println(rs.elementAt(0) + "</td></tr>");
                        }
                        printwriter.println("<tr><td align='left' class='textdesttabla'><B>Acceso</B><input type='hidden' name='hddopcion' language='javascript' value='" + s19 + "'></td>");
                        printwriter.println("<td align='left' class='textdesttabla'>");
                        if(s19.compareTo("10000") == 0)
                            printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"10000\";' checked>Control Total<BR>");
                        else
                            printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"10000\";'>Control Total<BR>");
                        if(s19.compareTo("01000") == 0)
                            printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"01000\";' checked>Escritura<BR>");
                        else
                            printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"01000\";'>Escritura<BR>");
                        if(s19.compareTo("00100") == 0)
                            printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"00100\";' checked>S\363lo Lectura<BR>");
                        else
                            printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"00100\";'>S\363lo Lectura<BR>");
                        if(s19.compareTo("00010") == 0)
                            printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"00010\";' checked>Eliminaci\363n<BR>");
                        else
                            printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"00010\";'>Eliminaci\363n<BR>");
                        if(s19.compareTo("00001") == 0)
                            printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"00001\";' checked>Sin acceso<BR>");
                        else
                            printwriter.println("<INPUT type='radio' name=optacceso onclick='document.frmpermisos.hddopcion.value=\"00001\";'>Sin acceso<BR>");
                        printwriter.println("</td>");
                        printwriter.println("</tr>");
                        printwriter.println("</table>");
                        printwriter.println("<BR><BR><CENTER>");
                        printwriter.println("<input type='button' class='fondoinput' name='btnok' value='Modificar' language='javascript' onclick='return ModificaProyecto();'>");
                        printwriter.println("<input type='button' class='fondoinput' name='btneliminar' value='Eliminar' language='javascript' onclick='return EliminaProyecto();','datos');\">");
                        printwriter.println("<input type='button' class='fondoinput' name='btnvolver' value='Volver' language='javascript' onclick=\"window.open('permisosclientes.jsp','datos');\">");
                        printwriter.println("</CENTER>");
                        printwriter.println("</form>");
                        printwriter.println("<script languaje='javascript'>");
                        CargaScript(printwriter);
                        printwriter.println("</script>");
                        printwriter.println("</body>");
                        printwriter.println("</html>");
                    }
                }
            } else
            {
                AFunc.reindex(httpservletrequest, printwriter, 1, "SAD", 6);
            }
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "SAD", 6);
        }
    }

    void CargaScript(PrintWriter printwriter)
    {
        printwriter.println("function GrabarProceso()");
        printwriter.println("{");
        printwriter.println("if (document.frmpermisos.codproyecto.value != \"\")");
        printwriter.println("{");
        printwriter.println("if ((document.frmpermisos.lstperfiles.value != \"\") || (document.frmpermisos.lstusuarios.value != \"\"))");
        printwriter.println("{");
        printwriter.println("document.frmpermisos.action=\"procesaproyecto.jsp?TIPO=NEW\";");
        printwriter.println("document.frmpermisos.submit();");
        printwriter.println("}");
        printwriter.println("else");
        printwriter.println("{");
        printwriter.println("alert(\"Debe seleccionar un perfil o usuario\");");
        printwriter.println("}");
        printwriter.println("}");
        printwriter.println("else");
        printwriter.println("{");
        printwriter.println("alert(\"Debe seleccionar un proyecto\");");
        printwriter.println("}");
        printwriter.println("return true;");
        printwriter.println("}");
        printwriter.println("function ModificaProyecto()");
        printwriter.println("{");
        printwriter.println("   document.frmpermisos.action=\"procesaproyecto.jsp?TIPO=MOD\";");
        printwriter.println("   document.frmpermisos.submit();");
        printwriter.println("return true;");
        printwriter.println("}");
        printwriter.println("function EliminaProyecto()");
        printwriter.println("{");
        printwriter.println("  if(confirm(\"\277Esta seguro que desea eliminar el permiso del proyecto?\"))");
        printwriter.println("  {");
        printwriter.println("    document.frmpermisos.action=\"procesaproyecto.jsp?TIPO=DEL\";");
        printwriter.println("    document.frmpermisos.submit();");
        printwriter.println("  }");
        printwriter.println("return true;");
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
    String UserReg;
}