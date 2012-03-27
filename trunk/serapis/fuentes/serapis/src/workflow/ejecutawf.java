// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   ejecutawf.java

package workflow;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ejecutawf extends HttpServlet
{

    public ejecutawf()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s = (String)httpsession.getValue("SerapisUser");
        printwriter.println("<html>");
        printwriter.println("<head>");
        printwriter.println("</head>");
        printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
        printwriter.println("<title>SERAPIS. FLUJO DE APROBACI\323N</title>");
        printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
        printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0' action='document.frmwf.txtclave.focus();'>");
        if(s != null && s.length() > 0)
        {
            String s1 = httpservletrequest.getParameter("ID");
            String s2 = httpservletrequest.getParameter("IDDOC");
            String s3 = httpservletrequest.getParameter("IDWF");
            String s4 = httpservletrequest.getParameter("IDPASO");
            String s5 = httpservletrequest.getParameter("BD");
            String s6 = "";
            String s8 = "";
            if(s5.compareTo("gdc") == 0)
                s8 = "gdc.documentos";
            else
            if(s5.compareTo("sad") == 0)
                s8 = "sad.documentos";
            else
            if(s5.compareTo("sadp") == 0)
                s8 = "sad.documentosp";
            else
                s8 = "gdc.documentos";
            ADatos.connect();
            Integer integer = new Integer(s4);
            String s11 = "";
            if(integer.intValue() > 1)
            {
                String s9 = "select comentarios from gdc.workflow_flujo where idwf = " + s3;
                s9 = s9 + " and idpaso = " + (integer.intValue() - 1);
                s9 = s9 + " and iddocumento = " + s2;
                int i = ADatos.ConsultaDB(s9);
                rs = new Vector();
                rs = ADatos.getResult();
                if(rs.size() > 0)
                    s11 = (String)rs.elementAt(0);
            }
            String s10 = "SELECT w.nombre nombrewf, wp.nombre nombrepaso, wf.detalle,wf.fecha,wf.hora, d.nombrearchivo,d.usuario,d.comentario,u.nombre nombreuser, wp.idok, wp.idredireccion, wp.debefirmar, wp.usuariofirma, wf.tipodoc, wf.comentarios, d.proceso ";
            s10 = s10 + " from gdc.workflow_flujo wf," + s8 + " d,sgc.usuarios u,gdc.workflow w,gdc.workflow_pasos wp";
            s10 = s10 + " where wf.iddocumento = d.id";
            s10 = s10 + " and u.login = d.usuario";
            s10 = s10 + " and w.idwf = wf.idwf";
            s10 = s10 + " and wp.idwf = wf.idwf";
            s10 = s10 + " and wp.idpaso = wf.idpaso";
            s10 = s10 + " and wf.id_flujo = " + s1;
            Integer integer1 = new Integer(0);
            Integer integer3 = new Integer(0);
            String s12 = "";
            String s14 = "";
            String s16 = "";
            int j = ADatos.ConsultaDB(s10);
            rs = new Vector();
            rs.clear();
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                Integer integer2 = (Integer)rs.elementAt(9);
                Integer integer4 = (Integer)rs.elementAt(10);
                String s13 = (String)rs.elementAt(11);
                String s15 = (String)rs.elementAt(12);
                String s17 = (String)rs.elementAt(13);
                String s18 = (String)rs.elementAt(15);
                if(s11 == null)
                    s11 = "";
                printwriter.println("<form name='frmwf' method='POST' enctype='multipart/form-data'>");
                printwriter.println("<input name='txtIdFlujo' type='hidden' value='" + s1.toString() + "'>");
                printwriter.println("<input name='txtNroDoc' type='hidden' value='" + s2.toString() + "'>");
                printwriter.println("<input name='txtNroWf' type='hidden' value='" + s3.toString() + "'>");
                printwriter.println("<input name='txtNroPaso' type='hidden' value='" + s4.toString() + "'>");
                printwriter.println("<input name='txtTipoDoc' type='hidden' value='" + s17.toString() + "'>");
                printwriter.println("<input name='txtAccion' type='hidden' value=''>");
                printwriter.println("<input name='PROCESO' type='hidden' value='" + s18 + "'>");
                printwriter.println("<input name='ARCHIVO' type='hidden' value='" + (String)rs.elementAt(5) + "'>");
                printwriter.println("<input name='DIRECTORIO' type='hidden' value='" + s17 + "'>");
                printwriter.println("<input name='ID' type='hidden' value='" + s1 + "'>");
                printwriter.println("<input name='IDDOC' type='hidden' value='" + s2 + "'>");
                printwriter.println("<input name='IDWF' type='hidden' value='" + s3 + "'>");
                printwriter.println("<input name='IDPASO' type='hidden' value='" + s4 + "'>");
                printwriter.println("<input name='BD' type='hidden' value='" + s5 + "'>");
                printwriter.println("<table border='1' width='95%' align='center'>");
                printwriter.println("<tr><td align='center'  class='texttituloarea' colspan='2'>Detalle de Autorizacion</td></tr>");
                printwriter.println("<tr>");
                printwriter.println("   <td align='left' class='textdesttabla' width='25%'>WorkFlow</td>");
                printwriter.println("   <td align='left' class='textdesttabla' width='75%'>" + (String)rs.elementAt(0) + "</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("   <td align='left' class='textdesttabla'>Paso</td>");
                printwriter.println("   <td align='left' class='textdesttabla'>" + (String)rs.elementAt(1) + "</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("   <td align='left' class='textdesttabla'>Detalle</td>");
                printwriter.println("   <td align='left' class='textdesttabla'>" + (String)rs.elementAt(2) + "</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("   <td align='left' class='textdesttabla'>Fecha Solicitud</td>");
                printwriter.println("   <td align='left' class='textdesttabla'>");
                printwriter.println(AFunc.ConstruyeFecha(((Integer)rs.elementAt(3)).toString(), "/", "dmy"));
                String s7 = ((Integer)rs.elementAt(4)).toString();
                s7 = s7.substring(0, s7.length() - 4) + ":" + s7.substring(s7.length() - 4, s7.length() - 2) + ":" + s7.substring(s7.length() - 2, s7.length());
                printwriter.println("&nbsp;&nbsp;" + s7);
                printwriter.println("</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("   <td align='left' class='textdesttabla'>Documento</td>");
                printwriter.println("   <td align='left' class='textdesttabla'>" + (String)rs.elementAt(5) + "</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("   <td align='left' class='textdesttabla'>Descripci\363n</td>");
                if(((String)rs.elementAt(7)).length() > 0)
                    printwriter.println("   <td align='left' class='textdesttabla'>" + (String)rs.elementAt(7) + "</td>");
                else
                    printwriter.println("   <td align='left' class='textdesttabla'>&nbsp;</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("   <td align='left' class='textdesttabla'>Solicitante</td>");
                printwriter.println("   <td align='left' class='textdesttabla'>" + (String)rs.elementAt(8) + "</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("   <td align='center' class='textdesttabla' colspan='2'><input type='button' name='btnDecargar' value='Descargar' language='javascript' class='fondoinput' onclick='return Descargar();'></td>");
                printwriter.println("</tr>");
                printwriter.println("</table>");
                printwriter.println("<BR>");
                printwriter.println("<table border='1' width='95%' align='center'>");
                byte byte0 = 3;
                if(integer4.intValue() > 0)
                    byte0 = 4;
                printwriter.println("<tr><td align='center'  class='texttituloarea' colspan='3'>Acci\363n</td></tr>");
                printwriter.println("<tr>");
                printwriter.println("   <td align='left' class='textdesttabla' width='5%'>&nbsp;</td>");
                printwriter.println("   <td align='left' class='textdesttabla' width='30%'>Comentario Anterior</td>");
                if(s11.length() > 0)
                    printwriter.println("   <td align='left' class='textdesttabla' width='65%'>" + s11);
                else
                    printwriter.println("   <td align='left' class='textdesttabla' width='65%'>&nbsp;");
                printwriter.println("   </td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("   <td align='left' class='textdesttabla' width='5%'>&nbsp;</td>");
                printwriter.println("   <td align='left' class='textdesttabla' width='30%'>Clave de Autenticaci\363n</td>");
                printwriter.println("   <td align='left' class='textdesttabla' width='65%'><input type='password' value = '' name='txtclave' language='javascript' style='WIDTH: 180px' onkeypress='return ValidarCaracteres(9);' maxlength='10'>");
                printwriter.println("   </td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("   <td align='left' class='textdesttabla' width='5%'><INPUT type='radio' name='optaccion' value='A' language='javascript' onclick='return DesAparece();' checked></td>");
                printwriter.println("   <td align='left' class='textdesttabla' width='30%'>Aprobar</td>");
                printwriter.println("   <td align='left' class='textdesttabla' width='65%' rowspan='" + byte0 + "' valign='top'>");
                printwriter.println("<table border='1' width='100%' align='center'>");
                printwriter.println("   <tr><td align='center' class='texttitulotabla'>Comentarios</td></tr>");
                printwriter.println("   <tr><td align='left' class='textdesttabla'>");
                printwriter.println("   <TEXTAREA rows='3' cols='60' name=txtComentarios></TEXTAREA>");
                printwriter.println("   </td></tr>");
                printwriter.println("   <tr><td align='left' class='textdesttabla'>");
                printwriter.println("   <input type='file' class='btnNormal' name='txtarchivo' style='WIDTH: 300px'>");
                printwriter.println("   </td></tr>");
                printwriter.println("</table>");
                printwriter.println("   </td>");
                printwriter.println("   </td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("   <td align='left' class='textdesttabla' width='5%'><INPUT type='radio' name='optaccion' value='M' language='javascript' onclick='return Aparece();'></td>");
                printwriter.println("   <td align='left' class='textdesttabla' width='30%'>Aprobar con Modificaciones</td>");
                printwriter.println("   </td>");
                printwriter.println("</tr>");
                if(integer4.intValue() > 0)
                {
                    printwriter.println("<tr>");
                    printwriter.println("   <td align='left' class='textdesttabla' width='5%'><INPUT type='radio' name='optaccion' value='R' language='javascript' onclick='return DesAparece();'></td>");
                    printwriter.println("   <td align='left' class='textdesttabla' width='30%'>Redirecci\363n</td>");
                    printwriter.println("   </td>");
                    printwriter.println("</tr>");
                }
                printwriter.println("<tr>");
                printwriter.println("   <td align='left' class='textdesttabla' width='5%'><INPUT type='radio' name='optaccion' value='E' language='javascript' onclick='return DesAparece();'></td>");
                printwriter.println("   <td align='left' class='textdesttabla' width='30%'>Rechazar</td>");
                printwriter.println("   </td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("   <td align='center' class='textdesttabla' colspan='3'><input type='button' name='btnaceptar' value='Aceptar' class='fondoinput' language='javascript' onclick='return valida();'>&nbsp;<input type='button' name='btncancelar' value='Cancelar' class='fondoinput' language='javascript' onclick=\"window.open('../aviso.jsp','_self');\"></td>");
                printwriter.println("   </td>");
                printwriter.println("</tr>");
                printwriter.println("</table>");
                printwriter.println("</form>");
                printwriter.println("<script language='javascript'>");
                printwriter.println("document.frmwf.txtarchivo.style.visibility = \"hidden\";");
                printwriter.println("function Descargar()");
                printwriter.println("{");
                printwriter.println("document.frmwf.action=\"download.jsp\";");
                printwriter.println("document.frmwf.submit();");
                printwriter.println("}");
                printwriter.println("function Aparece()");
                printwriter.println("{");
                printwriter.println("document.frmwf.txtarchivo.style.visibility = \"visible\";");
                printwriter.println("}");
                printwriter.println("function DesAparece()");
                printwriter.println("{");
                printwriter.println("document.frmwf.txtarchivo.style.visibility = \"hidden\";");
                printwriter.println("}");
                printwriter.println("function valida()");
                printwriter.println("{");
                printwriter.println("    if(document.frmwf.optaccion(0).checked == true)");
                printwriter.println("       document.frmwf.txtAccion.value=\"A\";");
                printwriter.println("    if(document.frmwf.optaccion(1).checked == true)");
                printwriter.println("       document.frmwf.txtAccion.value=\"M\";");
                if(integer4.intValue() < 0)
                {
                    printwriter.println("    if(document.frmwf.optaccion(2).checked == true)");
                    printwriter.println("       document.frmwf.txtAccion.value=\"E\";");
                } else
                {
                    printwriter.println("    if(document.frmwf.optaccion(2).checked == true)");
                    printwriter.println("       document.frmwf.txtAccion.value=\"R\";");
                    printwriter.println("    if(document.frmwf.optaccion(3).checked == true)");
                    printwriter.println("       document.frmwf.txtAccion.value=\"E\";");
                }
                printwriter.println("    if(document.frmwf.txtclave.value != \"\")");
                printwriter.println("    {");
                printwriter.println("       if(document.frmwf.txtAccion.value != \"A\")");
                printwriter.println("       {");
                printwriter.println("           if(document.frmwf.txtComentarios.value != \"\")");
                printwriter.println("           {");
                printwriter.println("              if(document.frmwf.txtAccion.value == \"M\")");
                printwriter.println("              {");
                printwriter.println("                 if(document.frmwf.txtarchivo.value != \"\")");
                printwriter.println("                 {");
                printwriter.println("                    document.frmwf.action=\"procesawf.jsp\";");
                printwriter.println("                    document.frmwf.submit();");
                printwriter.println("                 }");
                printwriter.println("                 else");
                printwriter.println("                 {");
                printwriter.println("                    alert(\"Debe ingresar archivo.\");");
                printwriter.println("                 }");
                printwriter.println("               }");
                printwriter.println("               else");
                printwriter.println("               {");
                printwriter.println("                  document.frmwf.action=\"procesawf.jsp\";");
                printwriter.println("                  document.frmwf.submit();");
                printwriter.println("               }");
                printwriter.println("          }");
                printwriter.println("          else");
                printwriter.println("          {");
                printwriter.println("             alert(\"Debe ingresar comentarios acerca de su decisi\363n\");");
                printwriter.println("             document.frmwf.txtComentarios.focus();");
                printwriter.println("          }");
                printwriter.println("       }");
                printwriter.println("       else");
                printwriter.println("       {");
                printwriter.println("          document.frmwf.action=\"procesawf.jsp\";");
                printwriter.println("          document.frmwf.submit();");
                printwriter.println("       }");
                printwriter.println("    }");
                printwriter.println("    else");
                printwriter.println("    {");
                printwriter.println("       alert(\"Debe ingresar su clave\");");
                printwriter.println("       document.frmwf.txtclave.focus();");
                printwriter.println("    }");
                printwriter.println("}");
                printwriter.println("</script>");
            } else
            {
                printwriter.println("<BR><BR><BR><BR>");
                printwriter.println("<table border='0' width='99%' align='center'>");
                printwriter.println("<tr><td align='center' class='texttitulomayor'>Aviso</td></tr>");
                printwriter.println("<tr><td align='center' class='textgral'>&nbsp;</td></tr>");
                printwriter.println("<tr><td align='center' class='textgral'>Documento no encontrado</td></tr>");
                printwriter.println("<tr><td align='center' class='textgral'>Inicie su sesi\363n en SERAPIS para ver las autorizaciones pendientes.</td></tr>");
                printwriter.println("<tr><td align='center' class='textgral'>&nbsp;</td></tr>");
                printwriter.println("<tr><td align='center' class='textgral'><input class='fondoinput' name='btnCerrar' value='Aceptar' type='button' language='javascript' onclick='window.close();'></td></tr>");
                printwriter.println("</table>");
            }
        } else
        {
            printwriter.println("<BR><BR><BR><BR>");
            printwriter.println("<table border='0' width='99%' align='center'>");
            printwriter.println("<tr><td align='center' class='texttitulomayor'>Aviso</td></tr>");
            printwriter.println("<tr><td align='center' class='textgral'>&nbsp;</td></tr>");
            printwriter.println("<tr><td align='center' class='textgral'>No se encuentra su informaci\363n de sesi\363n.</td></tr>");
            printwriter.println("<tr><td align='center' class='textgral'>Inicie su sesi\363n en SERAPIS para ver las autorizaciones pendientes.</td></tr>");
            printwriter.println("<tr><td align='center' class='textgral'>&nbsp;</td></tr>");
            printwriter.println("<tr><td align='center' class='textgral'><input class='fondoinput' name='btnCerrar' value='Aceptar' type='button' language='javascript' onclick='window.close();'></td></tr>");
            printwriter.println("</table>");
        }
        printwriter.println("</body>");
        printwriter.println("</html>");
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
}