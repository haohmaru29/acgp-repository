package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class nuevousuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public nuevousuario() {
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
        if(s != null && s.length() > 0)
        {
            ADatos.connect();
            String s1 = httpservletrequest.getParameter("opcionadmin");
            String s2 = httpservletrequest.getParameter("opcion");
            String s3 = httpservletrequest.getParameter("Usuario");
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0' onload='return document.frmcliente.login.focus()'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<form name='frmcliente' method='POST' action='procesausuario.jsp'>");
            CargaForm(printwriter);
            printwriter.println("<BR>");
            CargaPerfil(printwriter);
            printwriter.println("</form>");
            printwriter.println("<script language=\"javascript\">");
            CargaScript(printwriter);
            printwriter.println("</script>");
            printwriter.println("</body>");
            printwriter.println("</html>");
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "CONF", 6);
        }
    }

    void CargaForm(PrintWriter printwriter)
    {
        printwriter.println("<table border='1' width='95%'  align='center'>");
        printwriter.println("<tr><td width='95%' colspan='4' class='texttituloarea'>Nuevo Usuario</td></tr>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla'>Login</td>");
        printwriter.println("<td class='textdesttabla'><input name='login' value='' language='javascript' style='WIDTH: 90px' onkeypress='return ValidarCaracteres(1);' maxlength='10'></td>");
        printwriter.println("<td class='textdesttabla'>Nombre</td>");
        printwriter.println("<td class='textdesttabla'><input name='nombre' value='' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(3);' maxlength='60'></td>");
        printwriter.println("</tr>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla'>Rut</td>");
        printwriter.println("<td class='textdesttabla'><input name='rut1' value='' language='javascript' style='WIDTH: 90px' onkeypress='return ValidarCaracteres(4);' maxlength='8'>&nbsp;-&nbsp;<input name='rut2' value='' language='javascript' style='WIDTH: 20px' onkeypress='return ValidarCaracteres(2);' maxlength='1'></td>");
        printwriter.println("<td class='textdesttabla'>Alias</td>");
        printwriter.println("<td class='textdesttabla'><input name='alias' value='' language='javascript' style='WIDTH: 180px' onkeypress='return ValidarCaracteres(3);' maxlength='20'></td>");
        printwriter.println("</tr>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla'>Fecha Nacimiento</td>");
        printwriter.println("<td class='textdesttabla'><input name='fecha1' value='' language='javascript' style='WIDTH: 25px' onkeypress='return ValidarCaracteres(4);' maxlength='2'>&nbsp;/&nbsp;<input name='fecha2' value='' language='javascript' style='WIDTH: 25px' onkeypress='return ValidarCaracteres(4);' maxlength='2'>&nbsp;/&nbsp;<input name='fecha3' value='' language='javascript' style='WIDTH: 45px' onkeypress='return ValidarCaracteres(4);' maxlength='4'></td>");
        printwriter.println("<td class='textdesttabla'>E-Mail</td>");
        printwriter.println("<td class='textdesttabla'><input name='mail' value='' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(6);' maxlength='60'></td>");
        printwriter.println("</tr>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla'>Cargo</td>");
        printwriter.println("<td class='textdesttabla'>");
        printwriter.println("<select name='cargo'>");
        String s = "SELECT ID,Descripcion from gdc.def_cargos order by Descripcion";
        int i = ADatos.ConsultaDB(s);
        rs = ADatos.getResult();
        for(int j = 0; j < rs.size(); j += 2)
            printwriter.println("<option value='" + (Integer)rs.elementAt(j) + "'>" + (String)rs.elementAt(j + 1) + "</option>");

        printwriter.println("</select>");
        printwriter.println("</td>");
        printwriter.println("<td class='textdesttabla' valign='top'>Tipo Usuario</td>");
        printwriter.println("<td class='textdesttabla' colspan='3'><input type='radio' value 'admin' name='tipouser' language='javascript' onclick='document.frmcliente.tipoadmin.value=\"S\";'>Administrador<BR><input type='radio' value 'normal' name='tipouser' language='javascript' onclick='document.frmcliente.tipoadmin.value=\"N\";' checked>Normal</td>");
        printwriter.println("</tr>");
        printwriter.println("</table>");
    }

    void CargaPerfil(PrintWriter printwriter)
    {
        String s1 = "";
        String s3 = "";
        printwriter.println("<table border='1' width='95%'  align='center'>");
        printwriter.println("<tr>");
        printwriter.println("<td width='35%' class='texttitulotabla'>Proceso</td>");
        printwriter.println("<td width='20%' class='texttitulotabla'>Lectura</td>");
        printwriter.println("<td width='20%' class='texttitulotabla'>Escritura</td>");
        printwriter.println("<td width='20%' class='texttitulotabla'>Sin Acceso</td>");
        printwriter.println("</tr>");
        String s = "select sigla,descripcion from gdc.procesos";
        int i = ADatos.ConsultaDB(s);
        rs = ADatos.getResult();
        for(int j = 0; j < rs.size(); j += 2)
        {
            String s2 = (String)rs.elementAt(j);
            String s4 = (String)rs.elementAt(j + 1);
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'>" + s4 + "(" + s2 + ")</td>");
            printwriter.println("<td class='textdesttabla'><input type='radio' value 'L" + s2 + "' name='opcion" + s2 + "' language='javascript' onclick='document.frmcliente." + s2 + ".value=\"L\";' checked></td>");
            printwriter.println("<td class='textdesttabla'><input type='radio' value 'E" + s2 + "' name='opcion" + s2 + "' language='javascript' onclick='document.frmcliente." + s2 + ".value=\"E\";'></td>");
            printwriter.println("<td class='textdesttabla'><input type='radio' value 'N" + s2 + "' name='opcion" + s2 + "' language='javascript' onclick='document.frmcliente." + s2 + ".value=\"N\";'></td>");
            printwriter.println("<input value='L' name='" + s2 + "' type='hidden'>");
            printwriter.println("</tr>");
        }

        printwriter.println("<input value='N' name='tipoadmin' type='hidden'>");
        printwriter.println("<input value='G' name='opcionsel' type='hidden'>");
        printwriter.println("<td class='textdesttabla' colspan='4' align='right'><input type='button' name='grabar' value='Grabar' class='fondoinput' language='javascript' onclick='return valida();'></td>");
        printwriter.println("</table>");
    }

    void CargaScript(PrintWriter printwriter)
    {
        printwriter.println("function EsNumero(Dato)");
        printwriter.println("{");
        printwriter.println("  var l=Dato;");
        printwriter.println("  if((l=='1') || (l=='2') || (l=='3') || (l=='4') || (l=='5') || (l=='6') || (l=='7') || (l=='8') || (l=='9') || (l=='0'))");
        printwriter.println("    return(1);");
        printwriter.println("  else");
        printwriter.println("    return(0);");
        printwriter.println("}");
        printwriter.println("function EsInvalido(Dato)");
        printwriter.println("{");
        printwriter.println("  var i=0;");
        printwriter.println("  var l;");
        printwriter.println("  var sigue=1;");
        printwriter.println("  var ret=0;");
        printwriter.println("  for(i=0;((i<Dato.length) && (sigue==1));i++)");
        printwriter.println("  {");
        printwriter.println("    l=Dato.charAt(i);");
        printwriter.println("    if((l=='\341') || (l=='\351') || (l=='\355') || (l=='\363') || (l=='\372') || (l=='\301') || (l=='\311') || (l=='\315') || (l=='\323') || (l=='\332') || (l=='\361') || (l=='\321'))");
        printwriter.println("    {");
        printwriter.println("      ret=1;");
        printwriter.println("      sigue=0;");
        printwriter.println("    }");
        printwriter.println("  }");
        printwriter.println("  return(ret);");
        printwriter.println("}");
        printwriter.println("function EsValorNum(Tipo,Dato)");
        printwriter.println("{");
        printwriter.println("  var i=0;");
        printwriter.println("  var l=\"\";");
        printwriter.println("  var sigue=1;");
        printwriter.println("  var ret=1;");
        printwriter.println("  if(Dato.length>0)");
        printwriter.println("  {");
        printwriter.println("    for(i=0;((i<Dato.length) && (sigue==1));i++)");
        printwriter.println("    {");
        printwriter.println("      l=Dato.charAt(i);");
        printwriter.println("      if(EsNumero(l)!=1)");
        printwriter.println("      {");
        printwriter.println("        if(Tipo=='D')");
        printwriter.println("        {");
        printwriter.println("          if((l=='.') || (l=','))");
        printwriter.println("            sigue=1;");
        printwriter.println("          else");
        printwriter.println("          {");
        printwriter.println("            sigue=0;");
        printwriter.println("            ret=0;");
        printwriter.println("          }");
        printwriter.println("        }");
        printwriter.println("        else");
        printwriter.println("        {");
        printwriter.println("          sigue=0;");
        printwriter.println("          ret=0;");
        printwriter.println("        }");
        printwriter.println("      }");
        printwriter.println("    }");
        printwriter.println("  }");
        printwriter.println("  else");
        printwriter.println("    ret=0;");
        printwriter.println("  return(ret);");
        printwriter.println("}");
        printwriter.println("function valida()");
        printwriter.println("{");
        printwriter.println("  document.frmcliente.opcionsel.value=\"G\";");
        printwriter.println("  if (document.frmcliente.login.value != \"\")");
        printwriter.println("  {");
        printwriter.println("    if (EsInvalido(document.frmcliente.login.value)==0)");
        printwriter.println("    {");
        printwriter.println("      if (document.frmcliente.nombre.value != \"\")");
        printwriter.println("      {");
        printwriter.println("        if (EsInvalido(document.frmcliente.login.value)==0)");
        printwriter.println("        {");
        printwriter.println("          if (document.frmcliente.rut1.value != \"\")");
        printwriter.println("          {");
        printwriter.println("            if (EsValorNum(\"E\",document.frmcliente.rut1.value)==1)");
        printwriter.println("            {");
        printwriter.println("              if (document.frmcliente.rut2.value != \"\")");
        printwriter.println("              {");
        printwriter.println("                if (EsInvalido(document.frmcliente.rut2.value)==0)");
        printwriter.println("                {");
        printwriter.println("                  if (document.frmcliente.fecha1.value != \"\")");
        printwriter.println("                  {");
        printwriter.println("                    if (document.frmcliente.fecha2.value != \"\")");
        printwriter.println("                    {");
        printwriter.println("                      if (document.frmcliente.fecha3.value != \"\")");
        printwriter.println("                      {");
        printwriter.println("                        if (EsValorNum(\"E\",document.frmcliente.fecha1.value)==1)");
        printwriter.println("                        {");
        printwriter.println("                          if (EsValorNum(\"E\",document.frmcliente.fecha2.value)==1)");
        printwriter.println("                          {");
        printwriter.println("                            if (EsValorNum(\"E\",document.frmcliente.fecha3.value)==1)");
        printwriter.println("                            {");
        printwriter.println("                              if (document.frmcliente.cargo.value != \"\")");
        printwriter.println("                              {");
        printwriter.println("                                if (EsInvalido(document.frmcliente.cargo.value)==0)");
        printwriter.println("                                {");
        printwriter.println("                                  if (document.frmcliente.mail.value != \"\")");
        printwriter.println("                                  {");
        printwriter.println("                                    if (EsInvalido(document.frmcliente.mail.value)==0)");
        printwriter.println("                                    {");
        printwriter.println("                                      if (document.frmcliente.fecha1.value.length ==2)");
        printwriter.println("                                      {");
        printwriter.println("                                        if (document.frmcliente.fecha2.value.length ==2)");
        printwriter.println("                                        {");
        printwriter.println("                                          if (document.frmcliente.fecha3.value.length ==4)");
        printwriter.println("                                          {");
        printwriter.println("                                            if (parseFloat(document.frmcliente.fecha3.value) > 1900)");
        printwriter.println("                                            {");
        printwriter.println("                                              if (parseFloat(document.frmcliente.fecha2.value) > 0)");
        printwriter.println("                                              {");
        printwriter.println("                                                if (parseFloat(document.frmcliente.fecha1.value) > 0)");
        printwriter.println("                                                {");
        printwriter.println("                                                   if (document.frmcliente.alias.value != \"\")");
        printwriter.println("                                                      document.frmcliente.submit();");
        printwriter.println("                                                   else");
        printwriter.println("                                                   {");
        printwriter.println("                                                      alert(\"Debe ingresar alias\");");
        printwriter.println("                                                      document.frmcliente.alias.focus();");
        printwriter.println("                                                   }");
        printwriter.println("                                                }");
        printwriter.println("                                                else");
        printwriter.println("                                                {");
        printwriter.println("                                                  alert(\"D\355a debe ser mayor a 0\");");
        printwriter.println("                                                  document.frmcliente.fecha1.focus();");
        printwriter.println("                                                }");
        printwriter.println("                                              }");
        printwriter.println("                                              else");
        printwriter.println("                                              {");
        printwriter.println("                                                alert(\"Mes debe ser mayor a 0\");");
        printwriter.println("                                                document.frmcliente.fecha2.focus();");
        printwriter.println("                                              }");
        printwriter.println("                                            }");
        printwriter.println("                                            else");
        printwriter.println("                                            {");
        printwriter.println("                                              alert(\"A\361o debe ser mayor a 1900\");");
        printwriter.println("                                              document.frmcliente.fecha3.focus();");
        printwriter.println("                                            }");
        printwriter.println("                                          }");
        printwriter.println("                                          else");
        printwriter.println("                                          {");
        printwriter.println("                                            alert(\"A\361o debe ser de 4 d\355gitos\");");
        printwriter.println("                                            document.frmcliente.fecha3.focus();");
        printwriter.println("                                          }");
        printwriter.println("                                        }");
        printwriter.println("                                        else");
        printwriter.println("                                        {");
        printwriter.println("                                          alert(\"Mes debe ser de 2 d\355gitos\");");
        printwriter.println("                                          document.frmcliente.fecha2.focus();");
        printwriter.println("                                        }");
        printwriter.println("                                      }");
        printwriter.println("                                      else");
        printwriter.println("                                      {");
        printwriter.println("                                        alert(\"D\355a debe ser de 2 d\355gitos\");");
        printwriter.println("                                        document.frmcliente.fecha1.focus();");
        printwriter.println("                                      }");
        printwriter.println("                                    }");
        printwriter.println("                                    else");
        printwriter.println("                                    {");
        printwriter.println("                                      alert(\"E-Mail Incorrecto\");");
        printwriter.println("                                      document.frmcliente.mail.focus();");
        printwriter.println("                                    }");
        printwriter.println("                                  }");
        printwriter.println("                                  else");
        printwriter.println("                                  {");
        printwriter.println("                                    alert(\"E-Mail Incorrecto\");");
        printwriter.println("                                    document.frmcliente.mail.focus();");
        printwriter.println("                                  }");
        printwriter.println("                                }");
        printwriter.println("                                else");
        printwriter.println("                                {");
        printwriter.println("                                  alert(\"Cargo Incorrecto\");");
        printwriter.println("                                  document.frmcliente.cargo.focus();");
        printwriter.println("                                }");
        printwriter.println("                              }");
        printwriter.println("                              else");
        printwriter.println("                              {");
        printwriter.println("                                alert(\"Cargo Incorrecto\");");
        printwriter.println("                                document.frmcliente.cargo.focus();");
        printwriter.println("                              }");
        printwriter.println("                            }");
        printwriter.println("                            else");
        printwriter.println("                            {");
        printwriter.println("                              alert(\"Fecha Incorrecta\");");
        printwriter.println("                              document.frmcliente.fecha3.focus();");
        printwriter.println("                            }");
        printwriter.println("                          }");
        printwriter.println("                          else");
        printwriter.println("                          {");
        printwriter.println("                            alert(\"Fecha Incorrecta\");");
        printwriter.println("                            document.frmcliente.fecha2.focus();");
        printwriter.println("                          }");
        printwriter.println("                        }");
        printwriter.println("                        else");
        printwriter.println("                        {");
        printwriter.println("                          alert(\"Fecha Incorrecta\");");
        printwriter.println("                          document.frmcliente.fecha1.focus();");
        printwriter.println("                        }");
        printwriter.println("                      }");
        printwriter.println("                      else");
        printwriter.println("                      {");
        printwriter.println("                        alert(\"Fecha Incorrecta\");");
        printwriter.println("                        document.frmcliente.fecha3.focus();");
        printwriter.println("                      }");
        printwriter.println("                    }");
        printwriter.println("                    else");
        printwriter.println("                    {");
        printwriter.println("                      alert(\"Fecha Incorrecta\");");
        printwriter.println("                      document.frmcliente.fecha2.focus();");
        printwriter.println("                    }");
        printwriter.println("                  }");
        printwriter.println("                  else");
        printwriter.println("                  {");
        printwriter.println("                    alert(\"Fecha Incorrecta\");");
        printwriter.println("                    document.frmcliente.fecha1.focus();");
        printwriter.println("                  }");
        printwriter.println("                }");
        printwriter.println("                else");
        printwriter.println("                {");
        printwriter.println("                  alert(\"Rut Incorrecto\");");
        printwriter.println("                  document.frmcliente.rut2.focus();");
        printwriter.println("                }");
        printwriter.println("              }");
        printwriter.println("              else");
        printwriter.println("              {");
        printwriter.println("                alert(\"Rut Incorrecto\");");
        printwriter.println("                document.frmcliente.rut2.focus();");
        printwriter.println("              }");
        printwriter.println("            }");
        printwriter.println("            else");
        printwriter.println("            {");
        printwriter.println("              alert(\"Rut Incorrecto\");");
        printwriter.println("              document.frmcliente.rut1.focus();");
        printwriter.println("            }");
        printwriter.println("          }");
        printwriter.println("          else");
        printwriter.println("          {");
        printwriter.println("            alert(\"Rut Incorrecto\");");
        printwriter.println("            document.frmcliente.rut1.focus();");
        printwriter.println("          }");
        printwriter.println("        }");
        printwriter.println("        else");
        printwriter.println("        {");
        printwriter.println("          alert(\"Nombre Incorrecto\");");
        printwriter.println("          document.frmcliente.nombre.focus();");
        printwriter.println("        }");
        printwriter.println("      }");
        printwriter.println("      else");
        printwriter.println("      {");
        printwriter.println("        alert(\"Nombre Incorrecto\");");
        printwriter.println("        document.frmcliente.nombre.focus();");
        printwriter.println("      }");
        printwriter.println("    }");
        printwriter.println("    else");
        printwriter.println("    {");
        printwriter.println("      alert(\"Login Incorrecto\");");
        printwriter.println("      document.frmcliente.login.focus();");
        printwriter.println("    }");
        printwriter.println("  }");
        printwriter.println("  else");
        printwriter.println("  {");
        printwriter.println("    alert(\"Login Incorrecto\");");
        printwriter.println("    document.frmcliente.login.focus();");
        printwriter.println("  }");
        printwriter.println("  return 0;");
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
}