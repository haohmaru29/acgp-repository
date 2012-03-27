// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   verusuario.java

package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class verusuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public verusuario() {
        ADatos = new AccDataBase();
        ADatos1 = new AccDataBase();
        AFunc = new funciones();
        Usuario = "";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        UserGDC = (String)httpsession.getValue("SerapisUser");
        if(UserGDC != null && UserGDC.length() > 0)
        {
            String s = httpservletrequest.getParameter("opcionadmin");
            String s1 = httpservletrequest.getParameter("opcion");
            Usuario = httpservletrequest.getParameter("USR");
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<form name='frmcliente' action='procesausuario.jsp' method='post'>");
            printwriter.println("<input type='hidden' name='hddperfiles' value='' language='javascript'>");
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

    int CargaForm(PrintWriter printwriter)
    {
        int i = 0;
        Vector vector = new Vector();
        String s2 = "";
        Integer integer = new Integer(0);
        String s = "select nombre,idcargo,rut,fechapass,mail,fechanac,estado,alias from sgc.usuarios where login = '" + Usuario + "'";
        rs = new Vector();
        ADatos.connect();
        int j = ADatos.ConsultaDB(s);
        rs = ADatos.getResult();
        if(rs.size() > 0)
        {
            i = 1;
            String s3 = (String)rs.elementAt(6);
            printwriter.println("<table border='1' width='95%' align='center'>");
            printwriter.println("<tr><td width='95%' colspan='4' class='texttituloarea'>Editar Usuario</td></tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'>Login</td>");
            printwriter.println("<td class='textdesttabla'><input name='login' value='" + Usuario + "' language='javascript' style='WIDTH: 90px' onkeypress='return ValidarCaracteres(1);' maxlength='10' disabled><input name='loginuser' value='" + Usuario + "' language='javascript' style='WIDTH: 90px' onkeypress='' maxlength='10' type='hidden'></td>");
            printwriter.println("<td class='textdesttabla'>Nombre</td>");
            printwriter.println("<td class='textdesttabla'><input name='nombre' value='" + (String)rs.elementAt(0) + "' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(3);' maxlength='60'></td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'>Rut</td>");
            printwriter.println("<td class='textdesttabla'><input name='rut1' value='" + (String)rs.elementAt(2) + "' language='javascript' style='WIDTH: 90px' onkeypress='' maxlength='8' disabled><input name='rut2' value='0' language='javascript' style='WIDTH: 90px' onkeypress='' maxlength='8' type='hidden'></td>");
            printwriter.println("<td class='textdesttabla'>Alias</td>");
            printwriter.println("<td class='textdesttabla'><input name='alias' value='" + (String)rs.elementAt(7) + "' language='javascript' style='WIDTH: 180px' onkeypress='return ValidarCaracteres(3);' maxlength='20'></td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'>Fecha Nacimiento</td>");
            String s4 = rs.elementAt(5).toString();
            Vector vector1 = AFunc.ConstruyeFecha(s4);
            printwriter.println("<td class='textdesttabla'><input name='fecha1' value='" + (String)vector1.elementAt(0) + "' language='javascript' style='WIDTH: 25px' onkeypress='return ValidarCaracteres(4);' maxlength='2'>&nbsp;/&nbsp;<input name='fecha2' value='" + (String)vector1.elementAt(1) + "' language='javascript' style='WIDTH: 25px' onkeypress='return ValidarCaracteres(4);' maxlength='2'>&nbsp;/&nbsp;<input name='fecha3' value='" + (String)vector1.elementAt(2) + "' language='javascript' style='WIDTH: 45px' onkeypress='return ValidarCaracteres(4);' maxlength='4'></td>");
            printwriter.println("<td class='textdesttabla'>E-Mail</td>");
            printwriter.println("<td class='textdesttabla'><input name='mail' value='" + (String)rs.elementAt(4) + "' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(6);' maxlength='60'></td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'>Cargo</td>");
            Integer integer1 = (Integer)rs.elementAt(1);
            printwriter.println("<td class='textdesttabla'><select name='cargo'>");
            String s1 = "SELECT ID,Descripcion from gdc.def_cargos order by Descripcion";
            i = ADatos1.ConsultaDB(s1);
            rs1 = ADatos1.getResult();
            for(int l = 0; l < rs1.size(); l += 2)
                if(integer1.compareTo((Integer)rs1.elementAt(l)) == 0)
                    printwriter.println("<option value='" + (Integer)rs1.elementAt(l) + "' selected>" + (String)rs1.elementAt(l + 1) + "</option>");
                else
                    printwriter.println("<option value='" + (Integer)rs1.elementAt(l) + "'>" + (String)rs1.elementAt(l + 1) + "</option>");

            printwriter.println("</select>");
            printwriter.println("</td>");
            printwriter.println("<td class='textdesttabla' valign='top' colspan='2'>&nbsp;</td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' valign='top'>Tipo Usuario</td>");
            printwriter.println("<td class='textdesttabla'>");
            s1 = "select distinct login from sgc.perfil where login='" + Usuario + "' and administrador='S'";
            rs = new Vector();
            ADatos.connect();
            int k = ADatos.ConsultaDB(s1);
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                printwriter.println("<input type='radio' value 'admin' name='tipouser' language='javascript' onclick='document.frmcliente.tipoadmin.value=\"S\";' checked>Administrador&nbsp;&nbsp;");
                printwriter.println("<input type='radio' value 'normal' name='tipouser' language='javascript' onclick='document.frmcliente.tipoadmin.value=\"N\";'>Normal</td>");
                printwriter.println("<input value='S' name='tipoadmin' type='hidden'>");
            } else
            {
                printwriter.println("<input type='radio' value 'admin' name='tipouser' language='javascript' onclick='document.frmcliente.tipoadmin.value=\"S\";'>Administrador&nbsp;&nbsp;");
                printwriter.println("<input type='radio' value 'normal' name='tipouser' language='javascript' onclick='document.frmcliente.tipoadmin.value=\"N\";' checked>Normal</td>");
                printwriter.println("<input value='N' name='tipoadmin' type='hidden'>");
            }
            printwriter.println("<td class='textdesttabla' valign='top'>Estado</td>");
            printwriter.println("<td class='textdesttabla'>");
            if(s3.compareTo("A") == 0)
            {
                printwriter.println("<input type='radio' value 'activo' name='estado' language='javascript' onclick='document.frmcliente.tipoestado.value=\"A\";' checked>Activo&nbsp;&nbsp;");
                printwriter.println("<input type='radio' value 'inactivo' name='estado' language='javascript' onclick='document.frmcliente.tipoestado.value=\"D\";'>Inactivo</td>");
                printwriter.println("<input value='A' name='tipoestado' type='hidden'>");
            } else
            {
                printwriter.println("<input type='radio' value 'activo' name='estado' language='javascript' onclick='document.frmcliente.tipoestado.value=\"A\";'>Activo&nbsp;&nbsp;");
                printwriter.println("<input type='radio' value 'inactivo' name='estado' language='javascript' onclick='document.frmcliente.tipoestado.value=\"D\";' checked>Inactivo</td>");
                printwriter.println("<input value='D' name='tipoestado' type='hidden'>");
            }
            printwriter.println("</tr>");
            printwriter.println("</table>");
        }
        return i;
    }

    void CargaPerfil(PrintWriter printwriter)
    {
        String s1 = "";
        String s3 = "";
        String s5 = "";
        Vector vector = new Vector();
        printwriter.println("<table border='1' width='95%' align='center'>");
        printwriter.println("<tr>");
        printwriter.println("<td width='35%' class='texttitulotabla'>Proceso</td>");
        printwriter.println("<td width='20%' class='texttitulotabla'>Lectura</td>");
        printwriter.println("<td width='20%' class='texttitulotabla'>Escritura</td>");
        printwriter.println("<td width='20%' class='texttitulotabla'>Sin Acceso</td>");
        printwriter.println("</tr>");
        String s = "select sigla,descripcion from gdc.procesos order by descripcion";
        int l = ADatos.ConsultaDB(s);
        rs = ADatos.getResult();
        vector = (Vector)rs.clone();
        for(int i = 0; i < vector.size(); i += 2)
        {
            String s2 = (String)vector.elementAt(i);
            String s4 = (String)vector.elementAt(i + 1);
            s = "select perfil from sgc.perfil where login='" + Usuario + "' and proceso='" + s2 + "'";
            l = ADatos.ConsultaDB(s);
            rs = ADatos.getResult();
            String s6;
            if(rs.size() > 0)
                s6 = (String)rs.elementAt(0);
            else
                s6 = "N";
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'>" + s4 + "(" + s2 + ")</td>");
            if(s6.compareTo("E") == 0)
            {
                printwriter.println("<td class='textdesttabla'><input type='radio' value 'L" + s2 + "' name='opcion" + s2 + "' language='javascript' onclick='document.frmcliente." + s2 + ".value=\"L\";'></td>");
                printwriter.println("<td class='textdesttabla'><input type='radio' value 'E" + s2 + "' name='opcion" + s2 + "' language='javascript' onclick='document.frmcliente." + s2 + ".value=\"E\";' checked></td>");
                printwriter.println("<td class='textdesttabla'><input type='radio' value 'N" + s2 + "' name='opcion" + s2 + "' language='javascript' onclick='document.frmcliente." + s2 + ".value=\"N\";'></td>");
                printwriter.println("<input value='E' name='" + s2 + "' type='hidden'>");
            }
            if(s6.compareTo("L") == 0)
            {
                printwriter.println("<td class='textdesttabla'><input type='radio' value 'L" + s2 + "' name='opcion" + s2 + "' language='javascript' onclick='document.frmcliente." + s2 + ".value=\"L\";' checked></td>");
                printwriter.println("<td class='textdesttabla'><input type='radio' value 'E" + s2 + "' name='opcion" + s2 + "' language='javascript' onclick='document.frmcliente." + s2 + ".value=\"E\";'></td>");
                printwriter.println("<td class='textdesttabla'><input type='radio' value 'N" + s2 + "' name='opcion" + s2 + "' language='javascript' onclick='document.frmcliente." + s2 + ".value=\"N\";'></td>");
                printwriter.println("<input value='L' name='" + s2 + "' type='hidden'>");
            }
            if(s6.compareTo("N") == 0)
            {
                printwriter.println("<td class='textdesttabla'><input type='radio' value 'L" + s2 + "' name='opcion" + s2 + "' language='javascript' onclick='document.frmcliente." + s2 + ".value=\"L\";'></td>");
                printwriter.println("<td class='textdesttabla'><input type='radio' value 'E" + s2 + "' name='opcion" + s2 + "' language='javascript' onclick='document.frmcliente." + s2 + ".value=\"E\";'></td>");
                printwriter.println("<td class='textdesttabla'><input type='radio' value 'N" + s2 + "' name='opcion" + s2 + "' language='javascript' onclick='document.frmcliente." + s2 + ".value=\"N\";' checked></td>");
                printwriter.println("<input value='N' name='" + s2 + "' type='hidden'>");
            }
            printwriter.println("</tr>");
        }

        printwriter.println("<input value='G' name='opcionsel' type='hidden'>");
        printwriter.println("</table>");
        printwriter.println("<BR>");
        printwriter.println("<table border='1' width='95%' align='center'>");
        printwriter.println("<tr>");
        printwriter.println("<td width='45%' align='center' class='texttitulotabla'>Perfiles</td>");
        printwriter.println("<td width='10%' align='center' class='texttitulotabla'>&nbsp;</td>");
        printwriter.println("<td width='45%' align='center' class='texttitulotabla'>Asignados al Usuario</td>");
        printwriter.println("</tr>");
        printwriter.println("<tr>");
        printwriter.println("<td width='45%' align='left' class='textdesttabla'>");
        s = "SELECT idperfil,perfil from sgc.perfiles where estado='A'";
        l = ADatos.ConsultaDB(s);
        rs = ADatos.getResult();
        vector = (Vector)rs.clone();
        Integer integer = new Integer(0);
        Integer integer3 = new Integer(0);
        printwriter.println("<select name='lstperfiles' size='5'>");
        for(int j = 0; j < vector.size(); j += 2)
        {
            Integer integer1 = (Integer)vector.elementAt(j);
            String s7 = (String)vector.elementAt(j + 1);
            s = "select count(*) from sgc.perfilusuario where idperfil = " + integer1.toString() + " and usuario='" + Usuario + "'";
            l = ADatos.ConsultaDB(s);
            rs = ADatos.getResult();
            Integer integer4 = (Integer)rs.elementAt(0);
            if(integer4.intValue() == 0)
                printwriter.println("<option value='" + integer1.toString() + "'>" + s7.toString() + "</option>");
        }

        printwriter.println("</select>");
        printwriter.println("</td>");
        printwriter.println("<td width='10%' align='left' class='textdesttabla'>");
        printwriter.println("<input type='button' name='btnpasar' value='->' style='WIDTH: 40px' class='fondoinput' language='javascript' onclick='javascript:Agregar()'><BR>");
        printwriter.println("<input type='button' name='btnsacar' value='<-' style='WIDTH: 40px' class='fondoinput' language='javascript' onclick='javascript:Quitar()'>");
        printwriter.println("</td>");
        printwriter.println("<td width='45%' align='left' class='textdesttabla'>");
        s = "SELECT p.idperfil, p.perfil from sgc.perfiles p, sgc.perfilusuario pu where p.idperfil = pu.idperfil and p.estado='A' and pu.usuario = '" + Usuario + "'";
        l = ADatos.ConsultaDB(s);
        rs = ADatos.getResult();
        printwriter.println("<select name='lstperfilesusuario' size='5'>");
        for(int k = 0; k < rs.size(); k += 2)
        {
            Integer integer2 = (Integer)rs.elementAt(k);
            String s8 = (String)rs.elementAt(k + 1);
            printwriter.println("<option value='" + integer2.toString() + "'>" + s8.toString() + "</option>");
        }

        printwriter.println("</select>");
        printwriter.println("</td>");
        printwriter.println("</tr>");
        printwriter.println("</table>");
        printwriter.println("<BR><BR><center>");
        printwriter.println("<input type='button' name='grabar' value='Grabar' class='fondoinput' language='javascript' onclick='return valida();'>");
        printwriter.println("<input type='button' name='reiniciar' value='Reiniciar' class='fondoinput' language='javascript' onclick='return reinicia();'>");
        printwriter.println("<input type='button' name='eliminarUser' value='Eliminar' class='fondoinput' language='javascript' onclick='return eliminar();'>");
        printwriter.println("</center>");
    }

    void CargaPerfiles(PrintWriter printwriter)
    {
        String s2 = "";
        String s4 = "";
        String s6 = "";
        Vector vector = new Vector();
        printwriter.println("<table border='1' width='95%' align='center'>");
        printwriter.println("<tr>");
        printwriter.println("<td width='35%' class='texttitulotabla'>Proceso</td>");
        printwriter.println("<td width='20%' class='texttitulotabla'>Lectura</td>");
        printwriter.println("<td width='20%' class='texttitulotabla'>Escritura</td>");
        printwriter.println("<td width='20%' class='texttitulotabla'>Sin Acceso</td>");
        printwriter.println("</tr>");
        String s = "select sigla,descripcion from gdc.procesos";
        int i = ADatos.ConsultaDB(s);
        rs = ADatos.getResult();
        vector = (Vector)rs.clone();
        for(int k = 0; k < vector.size(); k += 2)
        {
            String s3 = (String)vector.elementAt(k);
            String s5 = (String)vector.elementAt(k + 1);
            String s1 = "select perfil from sgc.perfil where login='" + Usuario + "' and proceso='" + s3 + "'";
            int j = ADatos.ConsultaDB(s1);
            rs = ADatos.getResult();
            String s7;
            if(rs.size() > 0)
                s7 = (String)rs.elementAt(0);
            else
                s7 = "N";
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'>" + s5 + "(" + s3 + ")</td>");
            if(s7.compareTo("E") == 0)
            {
                printwriter.println("<td class='textdesttabla'><input type='radio' value 'L" + s3 + "' name='opcion" + s3 + "' language='javascript' onclick='document.frmcliente." + s3 + ".value=\"L\";'></td>");
                printwriter.println("<td class='textdesttabla'><input type='radio' value 'E" + s3 + "' name='opcion" + s3 + "' language='javascript' onclick='document.frmcliente." + s3 + ".value=\"E\";' checked></td>");
                printwriter.println("<td class='textdesttabla'><input type='radio' value 'N" + s3 + "' name='opcion" + s3 + "' language='javascript' onclick='document.frmcliente." + s3 + ".value=\"N\";'></td>");
                printwriter.println("<input value='E' name='" + s3 + "' type='hidden'>");
            }
            if(s7.compareTo("L") == 0)
            {
                printwriter.println("<td class='textdesttabla'><input type='radio' value 'L" + s3 + "' name='opcion" + s3 + "' language='javascript' onclick='document.frmcliente." + s3 + ".value=\"L\";' checked></td>");
                printwriter.println("<td class='textdesttabla'><input type='radio' value 'E" + s3 + "' name='opcion" + s3 + "' language='javascript' onclick='document.frmcliente." + s3 + ".value=\"E\";'></td>");
                printwriter.println("<td class='textdesttabla'><input type='radio' value 'N" + s3 + "' name='opcion" + s3 + "' language='javascript' onclick='document.frmcliente." + s3 + ".value=\"N\";'></td>");
                printwriter.println("<input value='L' name='" + s3 + "' type='hidden'>");
            }
            if(s7.compareTo("N") == 0)
            {
                printwriter.println("<td class='textdesttabla'><input type='radio' value 'L" + s3 + "' name='opcion" + s3 + "' language='javascript' onclick='document.frmcliente." + s3 + ".value=\"L\";'></td>");
                printwriter.println("<td class='textdesttabla'><input type='radio' value 'E" + s3 + "' name='opcion" + s3 + "' language='javascript' onclick='document.frmcliente." + s3 + ".value=\"E\";'></td>");
                printwriter.println("<td class='textdesttabla'><input type='radio' value 'N" + s3 + "' name='opcion" + s3 + "' language='javascript' onclick='document.frmcliente." + s3 + ".value=\"N\";' checked></td>");
                printwriter.println("<input value='N' name='" + s3 + "' type='hidden'>");
            }
            printwriter.println("</tr>");
        }

        printwriter.println("<input value='G' name='opcionsel' type='hidden'>");
        printwriter.println("</table>");
        printwriter.println("<BR>");
        printwriter.println("<center>");
        printwriter.println("<input type='button' name='grabar' value='Grabar' class='fondoinput' language='javascript' onclick='return valida();'>");
        printwriter.println("<input type='button' name='reiniciar' value='Reiniciar' class='fondoinput' language='javascript' onclick='return reinicia();'>");
        printwriter.println("<input type='button' name='eliminarUser' value='Eliminar' class='fondoinput' language='javascript' onclick='return eliminar();'>");
        printwriter.println("</center>");
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
        printwriter.println("function reinicia()");
        printwriter.println("{");
        printwriter.println("  document.frmcliente.opcionsel.value=\"R\";");
        printwriter.println("document.frmcliente.submit();");
        printwriter.println("}");
        printwriter.println("function eliminar()");
        printwriter.println("{");
        printwriter.println("  document.frmcliente.opcionsel.value=\"E\";");
        printwriter.println("  document.frmcliente.submit();");
        printwriter.println("}");
        printwriter.println("function getLista(obj)");
        printwriter.println("{");
        printwriter.println("  var valor=\"\";");
        printwriter.println("  if (!obj)");
        printwriter.println("    return \"\";");
        printwriter.println("  if (!obj.length)");
        printwriter.println("    return \"\";");
        printwriter.println("  else {");
        printwriter.println("    for (i=0;i<obj.length;i++) {");
        printwriter.println("      //texto= obj[i].text.split(\"-\")[1];");
        printwriter.println("      valor += obj[i].value + \";\";");
        printwriter.println("    }");
        printwriter.println("  }");
        printwriter.println("  return valor;");
        printwriter.println("}");
        printwriter.println("function valida()");
        printwriter.println("{");
        printwriter.println("  document.frmcliente.hddperfiles.value = getLista(document.frmcliente.lstperfilesusuario);");
        printwriter.println("  document.frmcliente.opcionsel.value=\"M\";");
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
        printwriter.println("                                                     document.frmcliente.alias.focus();");
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
        printwriter.println("function pasarItems(objOrigen,objDestino)");
        printwriter.println("{");
        printwriter.println("  for (i=0;i < objOrigen.length;i++)");
        printwriter.println("    {");
        printwriter.println("    if (objOrigen.options[i].selected)");
        printwriter.println("        {");
        printwriter.println("       objDestino[objDestino.length] = new Option(objOrigen.options[i].text,objOrigen.options[i].value);");
        printwriter.println("       objOrigen.options[i]=null;");
        printwriter.println("       i=-1;");
        printwriter.println("    }");
        printwriter.println("  }");
        printwriter.println("}");
        printwriter.println("function Agregar()");
        printwriter.println("{");
        printwriter.println("  form = document.frmcliente;");
        printwriter.println("  pasarItems(form.lstperfiles,form.lstperfilesusuario);");
        printwriter.println("}");
        printwriter.println("function Quitar()");
        printwriter.println("{");
        printwriter.println("  form = document.frmcliente;");
        printwriter.println("  pasarItems(form.lstperfilesusuario,form.lstperfiles);");
        printwriter.println("}");
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    AccDataBase ADatos;
    AccDataBase ADatos1;
    funciones AFunc;
    Vector rs;
    Vector rs1;
    String Usuario;
    String UserGDC;
}