// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   publicararea.java

package sad;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Acc.AccDataBase;
import Acc.funciones;

public class publicararea extends HttpServlet
{

    public publicararea()
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
        UserReg = (String)httpsession.getValue("SerapisUser");
        if(UserReg != null && UserReg.length() > 0)
        {
            ADatos.connect();
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<form name='publica' method='POST' enctype='multipart/form-data' action='uploadfichero.jsp'>");
            PreparaPublica(printwriter);
            printwriter.println("</table>");
            printwriter.println("</form>");
            printwriter.println("<script language='javascript'>");
            printwriter.println("function EsNumero(Dato)");
            printwriter.println("{");
            printwriter.println("  var l=Dato;");
            printwriter.println("  if((l=='1') || (l=='2') || (l=='3') || (l=='4') || (l=='5') || (l=='6') || (l=='7') || (l=='8') || (l=='9') || (l=='0'))");
            printwriter.println("    return(1);");
            printwriter.println("  else");
            printwriter.println("    return(0);");
            printwriter.println("}");
            printwriter.println("function ConAcento(Dato)");
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
            printwriter.println("function valida2()");
            printwriter.println("{");
            printwriter.println("  var DirArchivo;");
            printwriter.println("  DirArchivo = document.publica.fichero.value ;");
            printwriter.println("  if (DirArchivo != \"\")");
            printwriter.println("  {");
            printwriter.println("  alert(DirArchivo.substring(DirArchivo.length-3 ,DirArchivo.length));  ");
            printwriter.println("  }");
            printwriter.println("  else");
            printwriter.println("  {");
            printwriter.println("    alert(\"Debe especificar un archivo\");");
            printwriter.println("  }");
            printwriter.println("}");
            printwriter.println("function valida()");
            printwriter.println("{");
            printwriter.println("  var DirArchivo;");
            printwriter.println("  if (document.publica.fichero.value != \"\")");
            printwriter.println("  {");
            printwriter.println("    if (document.publica.versionp.value != \"\")");
            printwriter.println("    {");
            printwriter.println("      if (EsValorNum(\"E\",document.publica.versionp.value)==1)");
            printwriter.println("      {");
            printwriter.println("        Dato = document.publica.versionp.value;");
            printwriter.println("        if(Dato.length == 2)");
            printwriter.println("        {");
            printwriter.println("          if (document.publica.descripcion.value != \"\")");
            printwriter.println("          {");
            printwriter.println("            if(ConAcento(document.publica.descripcion.value)==0)");
            printwriter.println("            {");
            printwriter.println("              if (document.publica.versiond.value != \"\")");
            printwriter.println("              {");
            printwriter.println("                if (EsValorNum(\"E\",document.publica.versiond.value)==1)");
            printwriter.println("                {");
            printwriter.println("                  //Dato = document.publica.versiond.value");
            printwriter.println("                  //if(Dato.length == 2)");
            printwriter.println("                  //{");
            printwriter.println("                    Dato = document.publica.adicional.value;");
            printwriter.println("                    if(Dato.length > 0)");
            printwriter.println("                    {");
            printwriter.println("                      if(ConAcento(document.publica.adicional.value)==0)");
            printwriter.println("                      {");
            printwriter.println("                        DirArchivo = document.publica.fichero.value ;");
            printwriter.println("                        //alert(DirArchivo); ");
            printwriter.println("                        document.publica.tipoextension.value = DirArchivo.substring(DirArchivo.length-3 ,DirArchivo.length); ");
            printwriter.println("                        document.publica.submit()");
            printwriter.println("                      }");
            printwriter.println("                      else");
            printwriter.println("                      {");
            printwriter.println("                        alert(\"La descripci\363n Adicional no debe contener acentos\");");
            printwriter.println("                        document.publica.adicional.focus();");
            printwriter.println("                      }");
            printwriter.println("                    }");
            printwriter.println("                    else");
            printwriter.println("                    {");
            printwriter.println("                      DirArchivo = document.publica.fichero.value ;");
            printwriter.println("                      document.publica.tipoextension.value =  DirArchivo.substring(DirArchivo.length-3 ,DirArchivo.length)");
            printwriter.println("                      document.publica.submit()");
            printwriter.println("                    }");
            printwriter.println("                  //}");
            printwriter.println("                  //else");
            printwriter.println("                  //{");
            printwriter.println("                  //  alert(\"Version del documento debe ser de largo 2\");");
            printwriter.println("                  //  document.publica.versiond.focus();");
            printwriter.println("                  //}");
            printwriter.println("                }");
            printwriter.println("                else");
            printwriter.println("                {");
            printwriter.println("                  alert(\"La versi\363n del documento debe ser num\351rica\");");
            printwriter.println("                  document.publica.versiond.focus();");
            printwriter.println("                }");
            printwriter.println("              }");
            printwriter.println("              else");
            printwriter.println("              {");
            printwriter.println("                alert(\"Debe especificar un correlativo\");");
            printwriter.println("                document.publica.versiond.focus()");
            printwriter.println("              }");
            printwriter.println("            }");
            printwriter.println("            else");
            printwriter.println("            {");
            printwriter.println("              alert(\"El nombre no debe contener acentos\");");
            printwriter.println("              document.publica.descripcion.focus();");
            printwriter.println("            }");
            printwriter.println("          }");
            printwriter.println("          else");
            printwriter.println("          {");
            printwriter.println("            alert(\"Debe especificar un nombre\");");
            printwriter.println("            document.publica.descripcion.focus();");
            printwriter.println("          }");
            printwriter.println("        }");
            printwriter.println("        else");
            printwriter.println("        {");
            printwriter.println("          alert(\"Versi\363n del documento debe ser al menos de largo 2\");");
            printwriter.println("          document.publica.versionp.focus();");
            printwriter.println("        }");
            printwriter.println("      }");
            printwriter.println("      else");
            printwriter.println("      {");
            printwriter.println("        alert(\"La versi\363n debe ser num\351rica\");");
            printwriter.println("        document.publica.versionp.focus();");
            printwriter.println("      }");
            printwriter.println("    }");
            printwriter.println("    else");
            printwriter.println("    {");
            printwriter.println("      alert(\"Debe especificar una versi\363n\");");
            printwriter.println("      document.publica.versionp.focus();");
            printwriter.println("    }");
            printwriter.println("  }");
            printwriter.println("  else");
            printwriter.println("  {");
            printwriter.println("    alert(\"Debe especificar un archivo\");");
            printwriter.println("  }");
            printwriter.println("}");
            printwriter.println("function cambiadescripcion()");
            printwriter.println("{");
            printwriter.println("var tipodoc;");
            printwriter.println("var proceso;");
            printwriter.println("var indice;");
            printwriter.println("indice=0;");
            printwriter.println("tipodoc = document.publica.tipodocumentos.value;");
            printwriter.println("proceso = document.publica.tipoprocesos.value;");
            printwriter.println("  document.publica.descripcion.options.length = 0;");
            int i = ADatos.ConsultaDB("select tipodocumento,proceso,descripcion from gdc.documentoscalidad where  tipo = 'R' order by tipodocumento,proceso,descripcion");
            rs = ADatos.getResult();
            for(int j = 0; j < rs.size(); j += 3)
            {
                printwriter.println("if((tipodoc == \"" + (String)rs.elementAt(j) + "\") && (proceso == \"" + (String)rs.elementAt(1 + j) + "\"))");
                printwriter.println("{");
                printwriter.println("variable = new Option(\"" + (String)rs.elementAt(j + 2) + "\",\"" + (String)rs.elementAt(j + 2) + "\",\"\",\"\")");
                printwriter.println("document.publica.descripcion.options[indice] = variable");
                printwriter.println("indice = indice + 1");
                printwriter.println("}");
            }

            printwriter.println("}");
            printwriter.println("</script>");
            printwriter.println("</body>");
            printwriter.println("</html>");
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

    void PreparaPublica(PrintWriter printwriter)
    {
        rs = new Vector();
        String s = "";
        String s1 = "";
        printwriter.println("<TABLE border='1' width='80%'  align='center'>");
        printwriter.println("<tr>");
        printwriter.println("<td colspan='2' class='texttitulotabla'>Archivo a Subir</td>");
        printwriter.println("</tr>");
        int i = ADatos.ConsultaDB("select sigla,descripcion from gdc.procesos order by descripcion");
        rs = ADatos.getResult();
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla'><B>Proceso</B></td>");
        printwriter.println("<td class='textdesttabla'>");
        printwriter.println("<select name='tipoprocesos' onchange='return cambiadescripcion();'>");
        if(rs.size() > 0)
            s = (String)rs.elementAt(0);
        for(int j = 0; j < rs.size(); j += 2)
            printwriter.println("<option value='" + (String)rs.elementAt(j) + "'>" + (String)rs.elementAt(j + 1) + "</option>");

        printwriter.println("</select>");
        printwriter.println("</td>");
        printwriter.println("</tr>");
        i = ADatos.ConsultaDB("select sigla,descripcion from gdc.tipodocumentos order by descripcion");
        rs = ADatos.getResult();
        
        printwriter.println("<tr>");
        printwriter.println("<td width='30%' class='textdesttabla'><B>Tipo Documento</B></td>");
        printwriter.println("<td class='textdesttabla'>");
        printwriter.println("<select name='tipodocumentos' onchange='return cambiadescripcion();'>");
        if(rs.size() > 0)
            s1 = (String)rs.elementAt(0);
        for(int k = 0; k < rs.size(); k += 2)
            printwriter.println("<option value='" + (String)rs.elementAt(k) + "'>" + (String)rs.elementAt(k + 1) + "</option>");

        printwriter.println("</select>");
        printwriter.println("</td>");
        printwriter.println("</tr>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla'><B>Nombre</B></td>");
        printwriter.println("<td class='textdesttabla'><select name='descripcion'>");
        i = ADatos.ConsultaDB("select descripcion from gdc.documentoscalidad where tipodocumento = '" + s1 + "' and proceso = '" + s + "' and tipo = 'R' order by descripcion");
        rs = ADatos.getResult();
        for(int l = 0; l < rs.size(); l++)
            printwriter.println("<option value='" + (String)rs.elementAt(l) + "'>" + (String)rs.elementAt(l) + "</option>");

        printwriter.println("</select>");
        printwriter.println("</td>");
        printwriter.println("</tr>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla'><B>Versi\363n Registro</B></td>");
        printwriter.println("<td class='textdesttabla'><input name='versionp' language='javascript' style='WIDTH: 70px' onkeypress='return ValidarCaracteres(4);' maxlength='2'></td>");
        printwriter.println("</tr>");
        printwriter.println("</TABLE>");
        printwriter.println("<BR>");
        printwriter.println("<TABLE border='1' width='80%'  align='center'>");
        i = ADatos.ConsultaDB("select abreviatura,razonsocial from sgc.clientes");
        rs = ADatos.getResult();
        printwriter.println("<tr>");
        printwriter.println("<td width='30%' class='textdesttabla'><B>Cliente</B></td>");
        printwriter.println("<td class='textdesttabla'>");
        printwriter.println("<select name='clientes'>");
        for(int i1 = 0; i1 < rs.size(); i1 += 2)
            printwriter.println("<option value='" + (String)rs.elementAt(i1) + "'>" + (String)rs.elementAt(i1 + 1) + "</option>");

        printwriter.println("</select>");
        printwriter.println("</td>");
        printwriter.println("</tr>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla'>Adicional</td>");
        printwriter.println("<td class='textdesttabla'><input name='adicional' language='javascript' style='WIDTH: 250px' onkeypress='return ValidarCaracteres(9);' maxlength='30'></td>");
        printwriter.println("</tr>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla'><B>Nro. Correlativo</B></td>");
        printwriter.println("<td class='textdesttabla'>");
        printwriter.println("<input name='versiond' language='javascript' style='WIDTH: 70px' onkeypress='return ValidarCaracteres(4);' maxlength='10'>");
        printwriter.println("</td>");
        printwriter.println("</tr>");
        printwriter.println("</TABLE>");
        printwriter.println("<input type='hidden' name='tipoextension' language='javascript' value=''>");
        printwriter.println("<BR>");
        printwriter.println("<TABLE border='1' width='80%'  align='center'>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla' width='30%'>Descripci\363n</td>");
        printwriter.println("<td class='textdesttabla'>");
        printwriter.println("<textarea rows=3 cols=50 name='comentario' onkeypress='return ValidarCaracteres(9);' maxlength='200'></textarea>");
        printwriter.println("</td>");
        printwriter.println("</tr>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla' width='30%'>Comentario Adicional</td>");
        printwriter.println("<td class='textdesttabla'>");
        printwriter.println("<textarea rows=3 cols=50 name='comadicional' onkeypress='return ValidarCaracteres(9);'></textarea>");
        printwriter.println("</td>");
        printwriter.println("</tr>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla'><B>Archivo</B></td>");
        printwriter.println("<td class='textdesttabla'><input type='file' class='btnNormal' name='fichero' style='WIDTH: 250px'></td>");
        printwriter.println("</tr>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla'><B>Usuario</B></td>");
        printwriter.println("<td class='textdesttabla'><select name='usuarioAsig'><option value='-1'>Seleccione campo</option>");
        i = ADatos.ConsultaDB("select rut,nombre from sgc.usuarios order by nombre");
        rs = ADatos.getResult();
        for(int i1 = 0; i1 < rs.size(); i1 += 2) {
        	System.out.println("<option value='" + (String)rs.elementAt(i1) + "'>" + (String)rs.elementAt(i1 + 1) + "</option>");
            printwriter.println("<option value='" + (String)rs.elementAt(i1) + "'>" + (String)rs.elementAt(i1 + 1) + "</option>");
        }
        printwriter.println("</select></td></tr>");
        
        printwriter.println("</TABLE>");
        printwriter.println("<BR>");
        printwriter.println("<TABLE border='0' width='80%'  align='center'>");
        printwriter.println("<tr>");
        printwriter.println("<td colspan='2' align='center'><input type='button' name='ok' value='Subir' class='fondoinput' language='javascript' onclick='return valida();'></td>");
        printwriter.println("</tr>");
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    String UserReg;
}