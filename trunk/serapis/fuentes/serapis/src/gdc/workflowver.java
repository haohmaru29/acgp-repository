package gdc;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class workflowver extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public workflowver() {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        NombreUsuario = "";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        String s6 = httpservletrequest.getParameter("ID");
        String s7 = httpservletrequest.getParameter("TIPO");
        String s8 = httpservletrequest.getParameter("PASO");
        if(s7 == null || s7.length() == 0)
            s7 = "AGREGAPASO";
        Integer integer = new Integer(-1);
        String s9 = "N";
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s10 = (String)httpsession.getValue("SerapisUser");
        NombreUsuario = (String)httpsession.getValue("SerapisNombre");
        if(s10 != null && s10.length() > 0 && s6 != null)
        {
            String s = "select login from sgc.perfil where login='" + s10 + "' and administrador='S'";
            rs = new Vector();
            ADatos.connect();
            int l = ADatos.ConsultaDB(s);
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                ADatos.connect();
                Integer integer1 = new Integer(s8);
                String s1 = "SELECT nombre,estado,tipowf FROM gdc.workflow where idwf = " + s6.toString();
                rs = new Vector();
                int i1 = ADatos.ConsultaDB(s1);
                rs = ADatos.getResult();
                if(rs.size() > 0)
                {
                    String s11 = (String)rs.elementAt(0);
                    String s12 = (String)rs.elementAt(1);
                    String s13 = (String)rs.elementAt(2);
                    String s14 = "";
                    Integer integer2 = new Integer(0);
                    Integer integer3 = new Integer(0);
                    String s15 = "";
                    String s16 = "";
                    String s17 = "";
                    String s19 = "";
                    printwriter.println("<html>");
                    printwriter.println("<head>");
                    printwriter.println("</head>");
                    printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
                    printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
                    printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
                    AFunc.cargamenu(printwriter, 1);
                    printwriter.println("<form name='frmworkflow' method='POST' action='workflowprocesa.jsp'>");
                    printwriter.println("<input name='TIPO' type='hidden' value='" + s7 + "'>");
                    printwriter.println("<table border='1' align='center' width='95%'>");
                    printwriter.println("<tr><td align='center' class='texttituloarea' colspan='3'>WorkFlow</td></tr>");
                    printwriter.println("<tr>");
                    printwriter.println("   <td class='textdesttabla' width='20%'>Nombre</td>");
                    printwriter.println("   <td class='textdesttabla' colspan='2'>");
                    printwriter.println("   <INPUT type='text' name='txtnombre' value='" + s11 + "' maxlength='60' style='WIDTH: 260px' onkeypress='return ValidarCaracteres(9);'>");
                    printwriter.println("   <INPUT type='hidden' name='txtidwf' value='" + s6.toString() + "'></td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("   <td class='textdesttabla' width='20%'>Estado</td>");
                    printwriter.println("   <td class='textdesttabla' colspan='2'>");
                    if(s12.compareTo("A") == 0)
                        printwriter.println("Cerrado");
                    else
                        printwriter.println("En Construcci\363n");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("   <td class='textdesttabla' width='20%'>Usuario</td>");
                    printwriter.println("   <td class='textdesttabla' colspan='2'>" + NombreUsuario + "</td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("   <td class='textdesttabla' width='20%'>Tipo</td>");
                    printwriter.println("   <td class='textdesttabla' colspan='2'>");
                    if(s12.compareTo("A") == 0)
                    {
                        printwriter.println("       <input name='seltipo' value='" + s13.toString() + "' type='hidden'>");
                        if(s13.compareTo("A") == 0)
                            printwriter.println("Aprobaci\363n de Documentos");
                        if(s13.compareTo("E") == 0)
                            printwriter.println("Aprobaci\363n de Registros");
                        if(s13.compareTo("C") == 0)
                            printwriter.println("No Conformidad");
                    } else
                    {
                        printwriter.println("       <select name='seltipo'>");
                        if(s13.compareTo("A") == 0)
                            printwriter.println("          <option value='A' selected>Aprobaci\363n de Documentos</option>");
                        else
                            printwriter.println("          <option value='A'>Aprobaci\363n de Documentos</option>");
                        if(s13.compareTo("E") == 0)
                            printwriter.println("          <option value='E' selected>Aprobaci\363n de Registros</option>");
                        else
                            printwriter.println("          <option value='E'>Aprobaci\363n de Registros</option>");
                        printwriter.println("       </select>");
                    }
                    printwriter.println("   </td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("   <td class='textdesttabla' align='center' colspan='3'>");
                    printwriter.println("      <input type='button' name='btnnook' value='Aceptar' class='fondoinput' language='javascript' onclick='return grabawf();'>");
                    printwriter.println("   <input type='button' name='btnnook' value='Eliminar WorkFlow' class='fondoinput' language='javascript' onclick='return eliminar();'>");
                    printwriter.println("   <td>");
                    printwriter.println("</tr>");
                    if(integer1.intValue() > 0)
                    {
                        String s2 = "SELECT nombre,idok,idredireccion,tipo,usuarioautoriza,usuariobackup,debefirmar,usuariofirma FROM gdc.workflow_pasos where idwf = " + s6 + " and idpaso = " + s8;
                        rs.clear();
                        rs = new Vector();
                        int j1 = ADatos.ConsultaDB(s2);
                        rs = ADatos.getResult();
                        if(rs.size() > 0)
                        {
                            s14 = (String)rs.elementAt(0);
                            integer2 = (Integer)rs.elementAt(1);
                            integer3 = (Integer)rs.elementAt(2);
                            s15 = (String)rs.elementAt(3);
                            s16 = (String)rs.elementAt(4);
                            String s18 = (String)rs.elementAt(5);
                            s9 = (String)rs.elementAt(6);
                            String s20 = (String)rs.elementAt(7);
                        }
                        s2 = "SELECT login,nombre from sgc.usuarios where estado='A' order by nombre";
                        rs = new Vector();
                        j1 = ADatos.ConsultaDB(s2);
                        rs = ADatos.getResult();
                        printwriter.println("<tr><td class='texttitulotabla' colspan='3'>Paso " + s8.toString() + "<INPUT type='hidden' name='txtidpaso' value='" + s8.toString() + "'></td></tr>");
                        printwriter.println("<tr>");
                        printwriter.println("   <td class='textdesttabla' width='20%'>Nombre</td>");
                        printwriter.println("   <td class='textdesttabla' colspan='2'><INPUT type='text' name='txtpaso' value='" + s14.toString() + "' maxlength='60' style='WIDTH: 260px' onkeypress='return ValidarCaracteres(9);'></td>");
                        printwriter.println("</tr>");
                        printwriter.println("<tr>");
                        printwriter.println("   <td class='textdesttabla' width='20%'>Autorizador</td>");
                        printwriter.println("   <td class='textdesttabla' colspan='2'>");
                        printwriter.println("       <select name='selautoriza'>");
                        for(int i = 0; i < rs.size(); i += 2)
                            if(s16.compareTo((String)rs.elementAt(i)) == 0)
                                printwriter.println("          <option value='" + (String)rs.elementAt(i) + "' selected>" + (String)rs.elementAt(i + 1) + "</option>");
                            else
                                printwriter.println("          <option value='" + (String)rs.elementAt(i) + "'>" + (String)rs.elementAt(i + 1) + "</option>");

                        printwriter.println("       </select>");
                        printwriter.println("   </td>");
                        printwriter.println("</tr>");
                        printwriter.println("<input name='selbackup' value=' ' type='hidden'>");
                        printwriter.println("<input name='selfirma' value='N' type='hidden'>");
                        printwriter.println("<input name='seluserfirma' value='' type='hidden'>");
                        printwriter.println("<tr>");
                        printwriter.println("   <td class='textdesttabla' width='20%' rowspan='2' valign='top'>Autorizaciones</td>");
                        printwriter.println("   <td class='textdesttabla' width='20%'>Si Aprueba</td>");
                        printwriter.println("   <td class='textdesttabla'>");
                        printwriter.println("       <select name='selaprueba'>");
                        if(integer2.intValue() > 0)
                            printwriter.println("          <option value='0'>Paso Siguiente</option>");
                        else
                        if(integer2.intValue() == 0)
                            printwriter.println("          <option value='0' selected>Paso Siguiente</option>");
                        else
                            printwriter.println("          <option value='0'>Paso Siguiente</option>");
                        printwriter.println("       </select>");
                        printwriter.println("   </td>");
                        printwriter.println("</tr>");
                        printwriter.println("<tr>");
                        printwriter.println("   <td class='textdesttabla' width='20%'>Redirecciona</td>");
                        printwriter.println("   <td class='textdesttabla'>");
                        printwriter.println("       <select name='selrechaza'>");
                        if(integer3.intValue() > 0)
                            printwriter.println("          <option value='-1'>No Aplica</option>");
                        else
                            printwriter.println("          <option value='-1' selected>No Aplica</option>");
                        s2 = "SELECT idpaso,nombre FROM gdc.workflow_pasos WHERE idwf = " + s6.toString() + " and idpaso < " + integer1.toString();
                        rs.clear();
                        rs = new Vector();
                        j1 = ADatos.ConsultaDB(s2);
                        rs = ADatos.getResult();
                        if(rs.size() > 0)
                        {
                            for(int j = 0; j < rs.size(); j += 2)
                                if(integer3.intValue() == ((Integer)rs.elementAt(j)).intValue())
                                    printwriter.println("          <option value='" + ((Integer)rs.elementAt(j)).toString() + " selected'>" + ((String)rs.elementAt(j + 1)).toString() + "</option>");
                                else
                                    printwriter.println("          <option value='" + ((Integer)rs.elementAt(j)).toString() + "'>" + ((String)rs.elementAt(j + 1)).toString() + "</option>");

                        }
                        printwriter.println("       </select>");
                        printwriter.println("   </td>");
                        printwriter.println("</tr>");
                        printwriter.println("<tr>");
                        printwriter.println("   <td class='textdesttabla'>Paso Final</td>");
                        printwriter.println("   <td class='textdesttabla'>");
                        printwriter.println("       <select name='selfinal'>");
                        if(s15.compareTo("F") == 0)
                        {
                            printwriter.println("          <option value='S' selected>Si</option>");
                            printwriter.println("          <option value='N'>No</option>");
                        } else
                        {
                            printwriter.println("          <option value='S'>Si</option>");
                            printwriter.println("          <option value='N' selected>No</option>");
                        }
                        printwriter.println("       </select>");
                        printwriter.println("</tr>");
                        printwriter.println("<tr>");
                        printwriter.println("   <td align='center' class='textdesttabla' width='100%' colspan='3'>");
                        printwriter.println("<input type='button' name='btnok' value='Aceptar' class='fondoinput' language='javascript' onclick='return checkAll();'>");
                        printwriter.println("</td>");
                        printwriter.println("</tr>");
                    }
                    printwriter.println("<tr>");
                    printwriter.println("   <td align='center' class='textdesttabla' width='100%' colspan='3'>&nbsp;</td>");
                    printwriter.println("</tr>");
                    String s3 = "SELECT idpaso,nombre,idok,idredireccion,usuarioautoriza,usuariobackup,debefirmar,usuariofirma from gdc.workflow_pasos where idwf=" + s6 + " order by idpaso";
                    rs = new Vector();
                    int k1 = ADatos.ConsultaDB(s3);
                    rs = ADatos.getResult();
                    Vector vector = new Vector();
                    vector = (Vector)rs.clone();
                    int j2 = vector.size() / 8;
                    if(k1 > 0)
                    {
                        printwriter.println("<tr><td colspan='3' align='center' class='textdesttabla' width='100%'>");
                        printwriter.println("<table border='1' align='center' width='100%'>");
                        printwriter.println("<tr><td class='texttitulotabla' colspan='6' align='left'>Pasos WorkFlow</td></tr>");
                        printwriter.println("<tr><td class='texttitulotabla' align='left'>Nombre</td>");
                        printwriter.println("<td class='texttitulotabla' align='left'>Encargado</td>");
                        printwriter.println("<td class='texttitulotabla' align='left'>Aprueba</td>");
                        printwriter.println("<td class='texttitulotabla' align='left'>Redirecciona</td>");
                        for(int k = 0; k < vector.size(); k += 8)
                        {
                            printwriter.println("<tr>");
                            if(s12.compareTo("A") == 0)
                                printwriter.println("<td class='textdesttabla' align='left'>" + (String)vector.elementAt(k + 1) + "</td>");
                            else
                                printwriter.println("<td class='textdesttabla' align='left'><a href='workflowver.jsp?ID=" + s6 + "&TIPO=EDITAR&PASO=" + ((Integer)vector.elementAt(k)).toString() + "'>" + (String)vector.elementAt(k + 1) + "</a></td>");
                            printwriter.println("<td class='textdesttabla' align='left'>" + (String)vector.elementAt(k + 4) + "</td>");
                            if(((Integer)vector.elementAt(k + 2)).intValue() > 0)
                            {
                                String s4 = "select nombre from gdc.workflow_pasos where idwf = " + s6 + " and idpaso = " + ((Integer)vector.elementAt(k + 2)).toString();
                                rs.clear();
                                rs = new Vector();
                                int l1 = ADatos.ConsultaDB(s4);
                                rs = ADatos.getResult();
                                if(rs.size() > 0)
                                    printwriter.println("<td class='textdesttabla' align='left'>" + rs.elementAt(0).toString() + "</td>");
                                else
                                    printwriter.println("<td class='textdesttabla' align='left'>Paso&nbsp;" + ((Integer)vector.elementAt(k + 2)).toString() + "</td>");
                            } else
                            {
                                printwriter.println("<td class='textdesttabla' align='left'>&nbsp;</td>");
                            }
                            if(((Integer)vector.elementAt(k + 3)).intValue() > 0)
                            {
                                String s5 = "select nombre from gdc.workflow_pasos where idwf = " + s6 + " and idpaso = " + ((Integer)vector.elementAt(k + 3)).toString();
                                rs.clear();
                                rs = new Vector();
                                int i2 = ADatos.ConsultaDB(s5);
                                rs = ADatos.getResult();
                                if(rs.size() > 0)
                                    printwriter.println("<td class='textdesttabla' align='left'>" + rs.elementAt(0).toString() + "</td>");
                                else
                                    printwriter.println("<td class='textdesttabla' align='left'>" + ((Integer)vector.elementAt(k + 3)).toString() + "</td>");
                            } else
                            {
                                printwriter.println("<td class='textdesttabla' align='left'>&nbsp;</td>");
                            }
                            printwriter.println("<tr>");
                        }

                        printwriter.println("</td></tr>");
                        printwriter.println("<tr><td align='center' class='textdesttabla' colspan='6'>");
                        if(s12.compareTo("A") != 0 && integer1.intValue() <= 0)
                            printwriter.println("<input class='fondoinput' name='btnAgregar' value='Agregar Paso' type='button' language='javascript' onclick=\"window.open('workflowver.jsp?ID=" + s6.toString() + "&TIPO=AGREGAPASO&PASO=" + (j2 + 1) + "','datos')\";>");
                        printwriter.println("</td></tr>");
                        printwriter.println("</table>");
                    }
                } else
                {
                    printwriter.println("<tr>");
                    printwriter.println("   <td align='center' class='textdesttabla' width='100%' colspan='3'>No existe el WorkFlow solicitado</td>");
                    printwriter.println("</tr>");
                }
                printwriter.println("</table>");
                printwriter.println("</form>");
                printwriter.println("<script language='javascript'>");
                printwriter.println("function grabawf()");
                printwriter.println("{");
                printwriter.println("   document.frmworkflow.action=\"workflowprocesa.jsp?TIPO=EDITAWF\";");
                printwriter.println("   document.frmworkflow.submit();");
                printwriter.println("}");
                printwriter.println("function eliminar()");
                printwriter.println("{");
                printwriter.println("   if (confirm(\"\277Esta seguro que desea eliminar el WorkFlow?\") == true)");
                printwriter.println("   {");
                printwriter.println("      document.frmworkflow.action=\"workflowprocesa.jsp?TIPO=ELIMINAR\";");
                printwriter.println("      document.frmworkflow.submit();");
                printwriter.println("   }");
                printwriter.println("}");
                if(integer1.intValue() > 0)
                {
                    if(s9.compareTo("N") == 0)
                        printwriter.println("document.frmworkflow.seluserfirma.style.visibility = \"hidden\";");
                    printwriter.println("function ListaFirmantes()");
                    printwriter.println("{");
                    printwriter.println("var ValorFirmante;");
                    printwriter.println("ValorFirmante = document.frmworkflow.selfirma.value;");
                    printwriter.println("if(ValorFirmante == \"S\")");
                    printwriter.println("{");
                    printwriter.println("   document.frmworkflow.seluserfirma.style.visibility = \"visible\";");
                    printwriter.println("}");
                    printwriter.println("else");
                    printwriter.println("{");
                    printwriter.println("   document.frmworkflow.seluserfirma.style.visibility = \"hidden\";");
                    printwriter.println("}");
                    printwriter.println("}");
                    printwriter.println("function checkAll()");
                    printwriter.println("{");
                    printwriter.println("   if (document.frmworkflow.txtnombre.value != \"\")");
                    printwriter.println("   {");
                    printwriter.println("      if (document.frmworkflow.txtpaso.value != \"\")");
                    printwriter.println("      {");
                    printwriter.println("         if (document.frmworkflow.selautoriza.value != \"\")");
                    printwriter.println("         {");
                    printwriter.println("            if (document.frmworkflow.selfinal.value == \"S\")");
                    printwriter.println("            {");
                    printwriter.println("               if (confirm(\"Al grabar el paso final el WorkFlow no podr\341 ser modificado posteriormente. \277Esta seguro que desea grabar el paso final?\") == true)");
                    printwriter.println("               {");
                    printwriter.println("                  document.frmworkflow.submit();");
                    printwriter.println("               }");
                    printwriter.println("            }");
                    printwriter.println("            else");
                    printwriter.println("            {");
                    printwriter.println("               document.frmworkflow.submit();");
                    printwriter.println("            }");
                    printwriter.println("         }");
                    printwriter.println("         else");
                    printwriter.println("         {");
                    printwriter.println("            alert(\"Debe seleccionar un usuario que autoriza\");");
                    printwriter.println("            document.frmworkflow.selautoriza.focus();");
                    printwriter.println("         }");
                    printwriter.println("       }");
                    printwriter.println("       else");
                    printwriter.println("       {");
                    printwriter.println("         alert(\"Debe asignar un nombre al paso\");");
                    printwriter.println("         document.frmworkflow.txtpaso.focus();");
                    printwriter.println("       }");
                    printwriter.println("   }");
                    printwriter.println("   else");
                    printwriter.println("   {");
                    printwriter.println("      alert(\"Debe asignar un nombre al WorkFlow\");");
                    printwriter.println("      document.frmworkflow.txtnombre.focus();");
                    printwriter.println("   }");
                    printwriter.println("}");
                }
                printwriter.println("</script>");
                printwriter.println("</body>");
                printwriter.println("</html>");
            } else
            {
                AFunc.reindex(httpservletrequest, printwriter, 1, "CONF", 7);
            }
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "CONF", 6);
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
    String NombreUsuario;
}