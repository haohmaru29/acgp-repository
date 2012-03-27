// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   verdesestimado.java

package sad;

import Acc.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class verdesestimado extends HttpServlet
{

    public verdesestimado()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        ACopia = new CopiadorDeArchivos();
        UserGDC = "";
        RutaSitio = "";
        RutaDocumentos = "";
        TipoDoc = "";
        TipoDespliegue = "N";
        TipoBD = "";
        RutaBase = "sad\\documentos";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        String s = httpservletrequest.getParameter("ID");
        TipoDespliegue = httpservletrequest.getParameter("TIPO");
        TipoBD = httpservletrequest.getParameter("BD");
        if(s == null || s.length() == 0)
            s = "0";
        if(TipoBD == null || TipoBD.length() == 0)
            TipoBD = "SAD";
        Integer integer = new Integer(s);
        HttpSession httpsession = httpservletrequest.getSession(true);
        UserGDC = (String)httpsession.getValue("SerapisUser");
        if(UserGDC != null && UserGDC.length() > 0)
        {
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            ADatos.connect();
            AFunc.cargamenu(printwriter, 1);
            if(ObtieneRutas() == 1)
            {
                printwriter.println("<form name='publica' method='POST' action='grabacomentario.jsp'>");
                PreparaPublica(printwriter, integer.intValue());
                printwriter.println("</form>");
                printwriter.println("<script language='javascript'>");
                printwriter.println("function Eliminar(Tipo , NroDoc)");
                printwriter.println("{");
                printwriter.println("   if (Tipo=='E')");
                printwriter.println("   {");
                printwriter.println("      if(confirm(\"\277Esta seguro que desea eliminar el archivo?\"))");
                printwriter.println("      {");
                printwriter.println("          window.open(\"elimina.jsp?TIPO=\" + Tipo + \"&DOC=\" + NroDoc + \"\",\"datos\");");
                printwriter.println("      }");
                printwriter.println("    }");
                printwriter.println("   else");
                printwriter.println("   {");
                printwriter.println("      if(confirm(\"\277Esta seguro que desea pasar a hist\363rico el archivo?\"))");
                printwriter.println("      {");
                printwriter.println("          window.open(\"elimina.jsp?TIPO=\" + Tipo + \"&DOC=\" + NroDoc + \"\",\"datos\");");
                printwriter.println("      }");
                printwriter.println("   }");
                printwriter.println("return(0);");
                printwriter.println("}");
                printwriter.println("</script>");
                CargaScript(printwriter);
            } else
            {
                printwriter.println("<TABLE border='1' align='center' width='80%'>");
                printwriter.println("<tr>");
                printwriter.println("<td colspan='2' class='texttitulotabla'>Informaci\363n de Publicaci\363n</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla' colspan='2'><B>No esta definido la ruta de destino de los archivos. <BR>Informe al administrador para solucionar el problema.</B></td>");
                printwriter.println("</tr>");
                printwriter.println("</table>");
            }
            printwriter.println("</body>");
            printwriter.println("</html>");
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "GDC", 6);
        }
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    void PreparaPublica(PrintWriter printwriter, int i)
    {
        rs = new Vector();
        rsfile = new Vector();
        String s1 = "";
        String s2 = "";
        String s3 = "N";
        boolean flag = false;
        printwriter.println("<input name='codigodocumento' value='" + i + "' type='hidden'>");
        printwriter.println("<input name='tipodespliegue' value='" + TipoDespliegue + "' type='hidden'>");
        printwriter.println("<TABLE border='1' align='center' width='80%'>");
        printwriter.println("<tr>");
        printwriter.println("<td colspan='2' class='texttitulotabla'>Informaci\363n del Archivo</td>");
        printwriter.println("</tr>");
        try
        {
            String s;
            if(TipoBD.compareTo("SADP") == 0)
            {
                String s4 = "sad.documentosp";
                RutaBase = "sad\\documentos";
                s = "select tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,extension,comentario,'' fuente,usuario,fechapublica,fechacaducidad,idwf,proyecto from sad.documentosp where id = " + i;
            } else
            {
                String s5 = "sad.documentos";
                RutaBase = "sad\\documentos";
                s = "select tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,extension,comentario,'' fuente,usuario,fechapublica,fechacaducidad,idwf,'' proyecto from sad.documentos where id = " + i;
            }
            int j = ADatos.ConsultaDB(s);
            rsfile = ADatos.getResult();
            if(j == 1)
            {
                Integer integer = new Integer(0);
                Integer integer1 = new Integer(0);
                String s6 = (String)rsfile.elementAt(0);
                String s7 = (String)rsfile.elementAt(1);
                String s8 = (String)rsfile.elementAt(2);
                String s9 = (String)rsfile.elementAt(3);
                String s10 = (String)rsfile.elementAt(4);
                String s11 = (String)rsfile.elementAt(5);
                String s12 = (String)rsfile.elementAt(6);
                String s13 = (String)rsfile.elementAt(7);
                String s14 = (String)rsfile.elementAt(8);
                String s15 = (String)rsfile.elementAt(9);
                String s16 = (String)rsfile.elementAt(10);
                String s17 = (String)rsfile.elementAt(11);
                integer1 = (Integer)rsfile.elementAt(12);
                integer = (Integer)rsfile.elementAt(14);
                String s18 = (String)rsfile.elementAt(15);
                String s19 = s13;
                String s20 = "";
                if(TipoBD.compareTo("SADP") == 0)
                    s20 = RutaDocumentos + "sad\\proyecto\\" + s10 + "\\" + s18 + "\\" + s19;
                else
                    s20 = RutaDocumentos + "sad\\documentos\\" + s7 + "\\" + s19;
                CopiadorDeArchivos _tmp = ACopia;
                CopiadorDeArchivos.copia(s20, RutaSitio + "WEB-INF\\downloadroot\\" + s13);
                int k = ADatos.ConsultaDB("select sigla,descripcion from gdc.procesos where sigla = '" + s7 + "' order by sigla");
                rs = ADatos.getResult();
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Proceso</td>");
                printwriter.println("<td class='textdesttabla'>");
                printwriter.println((String)rs.elementAt(1));
                printwriter.println("</td>");
                printwriter.println("</tr>");
                k = ADatos.ConsultaDB("select sigla,descripcion from gdc.tipodocumentos where sigla = '" + s6 + "'order by sigla");
                rs = ADatos.getResult();
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Tipo Documento</td>");
                printwriter.println("<td class='textdesttabla'>");
                printwriter.println((String)rs.elementAt(1));
                printwriter.println("</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Nombre</td>");
                printwriter.println("<td class='textdesttabla'>");
                printwriter.println(s9);
                printwriter.println("</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Version Plantilla</td>");
                printwriter.println("<td class='textdesttabla'>" + s8 + "</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Nombre Archivo</td>");
                printwriter.println("<td class='textdesttabla'>" + s13 + "</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Descripci\363n</td>");
                printwriter.println("<td class='textdesttabla'>");
                if(s15.length() > 0)
                    printwriter.println(s15);
                else
                    printwriter.println("&nbsp;");
                printwriter.println("</td>");
                printwriter.println("</tr>");
                printwriter.println("</table>");
                printwriter.println("<BR><center>");
                if(integer.intValue() > 0)
                    printwriter.println("<input type='button' name='workflow' value='WorkFlow' class='fondoinput' language='javascript' onclick=\"window.open('detalleflujo.jsp?ID=" + i + "','datos');\">");
                printwriter.println("<input type='button' name='descarga' value='Descargar' class='fondoinput' language='javascript' onclick='window.open(\"descarga.jsp?FILEDESC=" + s13 + "\",\"_blank\")'>");
                printwriter.println("</center>");
            } else
            {
                printwriter.println("<tr>");
                printwriter.println("<td colspan='2' class='textdesttabla' align='center'>Documento no existe</td>");
                printwriter.println("</td>");
                printwriter.println("</tr>");
                printwriter.println("</table>");
            }
        }
        catch(IOException ioexception)
        {
            printwriter.println("<tr>");
            printwriter.println("<td colspan='2' class='textdesttabla' align='center'>Documento solicitado no existe.</td>");
            printwriter.println("</td>");
            printwriter.println("</table>");
        }
    }

    private void CargaScript(PrintWriter printwriter)
    {
        printwriter.println("<script language='javascript'>");
        printwriter.println("function grabacomentario()");
        printwriter.println("{");
        printwriter.println("   document.publica.submit()");
        printwriter.println("    return(0);");
        printwriter.println("}");
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
        printwriter.println("    if((l=='\341') || (l=='\351') || (l=='\355') || (l=='\363') || (l=='\372') || (l=='\301') || (l=='\311') || (l=='\315') || (l=='\323') || (l=='\332'))");
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
        printwriter.println("  if (document.publica.fichero.value != \"\")");
        printwriter.println("  {");
        printwriter.println("  if (document.publica.versionp.value != \"\")");
        printwriter.println("  {");
        printwriter.println("    if (EsValorNum(\"E\",document.publica.versionp.value)==1)");
        printwriter.println("    {");
        printwriter.println("      Dato = document.publica.versionp.value;");
        printwriter.println("      if(Dato.length == 2)");
        printwriter.println("      {");
        printwriter.println("        if (document.publica.descripcion.value != \"\")");
        printwriter.println("        {");
        printwriter.println("          if(ConAcento(document.publica.descripcion.value)==0)");
        printwriter.println("          {");
        printwriter.println("            if (document.publica.versiond.value != \"\")");
        printwriter.println("            {");
        printwriter.println("              if (EsValorNum(\"E\",document.publica.versiond.value)==1)");
        printwriter.println("              {");
        printwriter.println("                Dato = document.publica.versiond.value");
        printwriter.println("                if(Dato.length == 2)");
        printwriter.println("                {");
        printwriter.println("                  Dato = document.publica.adicional.value;");
        printwriter.println("                  if(Dato.length > 0)");
        printwriter.println("                  {");
        printwriter.println("                    if(ConAcento(document.publica.adicional.value)==0)");
        printwriter.println("                    {");
        printwriter.println("                      document.publica.submit()");
        printwriter.println("                    }");
        printwriter.println("                    else");
        printwriter.println("                    {");
        printwriter.println("                      alert(\"La descripci\363n Adicional no debe contener acentos\");");
        printwriter.println("                      document.publica.adicional.focus();");
        printwriter.println("                    }");
        printwriter.println("                  }");
        printwriter.println("                  else");
        printwriter.println("                  {");
        printwriter.println("                    document.publica.submit()");
        printwriter.println("                  }");
        printwriter.println("                }");
        printwriter.println("                else");
        printwriter.println("                {");
        printwriter.println("                  alert(\"Version del documento debe ser de largo 2\");");
        printwriter.println("                  document.publica.versiond.focus();");
        printwriter.println("                }");
        printwriter.println("              }");
        printwriter.println("              else");
        printwriter.println("              {");
        printwriter.println("                alert(\"La versi\363n del documento debe ser num\351rica\");");
        printwriter.println("                document.publica.versiond.focus();");
        printwriter.println("              }");
        printwriter.println("            }");
        printwriter.println("            else");
        printwriter.println("            {");
        printwriter.println("              document.publica.submit()");
        printwriter.println("            }");
        printwriter.println("          }");
        printwriter.println("          else");
        printwriter.println("          {");
        printwriter.println("            alert(\"El nombre no debe contener acentos\");");
        printwriter.println("            document.publica.descripcion.focus();");
        printwriter.println("          }");
        printwriter.println("        }");
        printwriter.println("        else");
        printwriter.println("        {");
        printwriter.println("          alert(\"Debe especificar un nombre\");");
        printwriter.println("          document.publica.descripcion.focus();");
        printwriter.println("        }");
        printwriter.println("      }");
        printwriter.println("      else");
        printwriter.println("      {");
        printwriter.println("        alert(\"Version de la Plantilla debe ser de largo 2\");");
        printwriter.println("        document.publica.versionp.focus();");
        printwriter.println("      }");
        printwriter.println("    }");
        printwriter.println("    else");
        printwriter.println("    {");
        printwriter.println("      alert(\"La versi\363n debe ser num\351rica\");");
        printwriter.println("      document.publica.versionp.focus();");
        printwriter.println("    }");
        printwriter.println("  }");
        printwriter.println("  else");
        printwriter.println("  {");
        printwriter.println("    alert(\"Debe especificar una versi\363n\");");
        printwriter.println("    document.publica.versionp.focus();");
        printwriter.println("  }");
        printwriter.println("  }");
        printwriter.println("  else");
        printwriter.println("  {");
        printwriter.println("    alert(\"Debe especificar un archivo\");");
        printwriter.println("  }");
        printwriter.println("}");
        printwriter.println("</script>");
    }

    private int ObtieneRutas()
    {
        Vector vector = new Vector();
        int i = 0;
        String s = "select dirsitio,dirfiles from gdc.datosgenerales";
        ADatos.ConsultaDB(s);
        vector = ADatos.getResult();
        if(vector.size() > 0)
        {
            RutaSitio = (String)vector.elementAt(0);
            RutaDocumentos = (String)vector.elementAt(1);
            i = 1;
        }
        return i;
    }

    AccDataBase ADatos;
    funciones AFunc;
    CopiadorDeArchivos ACopia;
    Vector rs;
    Vector rsfile;
    String UserGDC;
    String RutaSitio;
    String RutaDocumentos;
    String TipoDoc;
    String TipoDespliegue;
    String TipoBD;
    String RutaBase;
}