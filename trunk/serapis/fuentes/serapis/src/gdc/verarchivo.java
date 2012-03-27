// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   verarchivo.java

package gdc;

import Acc.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class verarchivo extends HttpServlet
{

    public verarchivo()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        ACopia = new CopiadorDeArchivos();
        UserGDC = "";
        RutaSitio = "";
        RutaDocumentos = "";
        DireccionArchivo = "";
        TipoDoc = "";
        TipoDespliegue = "N";
        Acceso = "00000";
        RutaBase = "gdc\\documentos";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        httpservletresponse.setContentType("text/html");
        String sTipo = (httpservletrequest.getParameter("T")!=null)?httpservletrequest.getParameter("T").toString().trim():"";
        PrintWriter printwriter = httpservletresponse.getWriter();
        String s = httpservletrequest.getParameter("ID");
        TipoDespliegue = httpservletrequest.getParameter("TIPO");
        Acceso = httpservletrequest.getParameter("P")!=null?httpservletrequest.getParameter("P").toString():"10000";
        if(s == null || s.length() == 0)
            s = "0";
        if(TipoDespliegue == null || TipoDespliegue.length() == 0)
            TipoDespliegue = "N";
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
                printwriter.println("<form name='publica' method='POST' action='grabacomentario.jsp?T=" + sTipo + "'>");
                PreparaPublica(printwriter, integer.intValue(),sTipo);
                printwriter.println("</form>");
                printwriter.println("<script language='javascript'>");
                printwriter.println("function Eliminar(Tipo , NroDoc)");
                printwriter.println("{");
                printwriter.println("   if (Tipo=='E')");
                printwriter.println("   {");
                printwriter.println("      if(confirm(\"\277Esta seguro que desea eliminar el archivo?\"))");
                printwriter.println("      {");
                printwriter.println("          window.open(\"elimina.jsp?T=" + sTipo + "&TIPO=\" + Tipo + \"&DOC=\" + NroDoc + \"\",\"datos\");");
                printwriter.println("      }");
                printwriter.println("    }");
                printwriter.println("   else");
                printwriter.println("   {");
                printwriter.println("      if(confirm(\"\277Esta seguro que desea pasar a hist\363rico el archivo?\"))");
                printwriter.println("      {");
                printwriter.println("          window.open(\"elimina.jsp?T=" + sTipo + "&TIPO=\" + Tipo + \"&DOC=\" + NroDoc + \"\",\"datos\");");
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

    void PreparaPublica(PrintWriter printwriter, int i) {
    	PreparaPublica(printwriter, i, "");
    }
    
    void PreparaPublica(PrintWriter printwriter, int i, String sTipo)
    {
        rs = new Vector();
        rsfile = new Vector();
        String s2 = "";
        String s3 = "";
        String s4 = "N";
        boolean flag = false;
        printwriter.println("<input name='codigodocumento' value='" + i + "' type='hidden'>");
        printwriter.println("<input type='hidden' value = '" + Acceso + "' name='P' language='javascript'>");
        printwriter.println("<input name='tipodespliegue' value='" + TipoDespliegue + "' type='hidden'>");
        printwriter.println("<TABLE border='1' align='center' width='80%'>");
        printwriter.println("<tr>");
        printwriter.println("<td colspan='2' class='texttitulotabla'>Informaci\363n del Archivo</td>");
        printwriter.println("</tr>");
        try
        {
            String s;
            if(TipoDespliegue.compareTo("N") == 0)
            {
            	if (sTipo.equals("R")) {
            		s = "select tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,extension,comentario,'',usuario,fechapublica,fechacaducidad,idwf from sad.documentos where id = " + i;
            		RutaBase = "sad\\documentos\\";
             	}else {
            		s = "select tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,extension,comentario,fuente,usuario,fechapublica,fechacaducidad,idwf from gdc.documentos where id = " + i;
                	RutaBase = "gdc\\documentos\\";
            	}
            } else
            if(TipoDespliegue.compareTo("E") == 0)
            {
            	if (sTipo.equals("R")){
            		s = "select tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,extension,comentario,'',archivofinal,fechahistorico,usuariohistorico from sad.reciclaje where id = " + i;
            		RutaBase = "sad\\reciclaje\\";
            	}else {
            		s = "select tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,extension,comentario,fuente,archivofinal,fechahistorico,usuariohistorico from gdc.reciclaje where id = " + i;
            		RutaBase = "gdc\\reciclaje\\";
            	}
            } else
            {
            	if (sTipo.equals("R")) {
            		s = "select tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,extension,comentario,'',archivofinal,fechahistorico,usuariohistorico from sad.historico where id = " + i;
            		RutaBase = "sad\\historico\\";
            	}else {
            		s = "select tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,extension,comentario,fuente,archivofinal,fechahistorico,usuariohistorico from gdc.historico where id = " + i;
            		RutaBase = "gdc\\historico\\";
            	}
            }
            int j = ADatos.ConsultaDB(s);
            rsfile = ADatos.getResult();
            if(j == 1)
            {
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
                String s17 = "";
                Integer integer = new Integer(0);
                String s18 = "";
                Integer integer1 = new Integer(0);
                if(TipoDespliegue.compareTo("N") != 0)
                {
                    s17 = (String)rsfile.elementAt(11);
                    integer = (Integer)rsfile.elementAt(12);
                    s18 = (String)rsfile.elementAt(13);
                } else
                {
                    s17 = s13;
                    integer = new Integer(0);
                    s18 = "";
                    integer1 = (Integer)rsfile.elementAt(14);
                }
                //CopiadorDeArchivos _tmp = ACopia;
                CopiadorDeArchivos.copia(RutaDocumentos + RutaBase + s7 + "\\" + s17, RutaSitio + "WEB-INF\\downloadroot\\" + s13);
                DireccionArchivo = RutaDocumentos + RutaBase + s7 + "\\" + s17;
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
                if(TipoDespliegue.compareTo("N") != 0)
                {
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla'>Origen</td>");
                    printwriter.println("<td class='textdesttabla'>");
                    if(TipoDespliegue.compareTo("E") == 0)
                        printwriter.println("Reciclaje");
                    else
                        printwriter.println("Hist\363rico");
                    printwriter.println("</td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    if(TipoDespliegue.compareTo("E") == 0)
                        printwriter.println("<td class='textdesttabla'>Fecha de Eliminaci\363n</td>");
                    else
                        printwriter.println("<td class='textdesttabla'>Fecha paso a Hist\363rico</td>");
                    printwriter.println("<td class='textdesttabla'>");
                    printwriter.println(AFunc.ConstruyeFecha(integer.toString(), "/", "dmy"));
                    printwriter.println("</td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla'>Usuario Traspaso</td>");
                    printwriter.println("<td class='textdesttabla'>");
                    printwriter.println(s18);
                    printwriter.println("</td>");
                    printwriter.println("</tr>");
                }
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Descripci\363n</td>");
                String s1 = "select perfil from sgc.perfil where proceso='" + s7 + "' and login='" + UserGDC + "'";
                rs = new Vector();
                k = ADatos.ConsultaDB(s1);
                rs = ADatos.getResult();
                String s5;
                if(rs.size() > 0)
                    s5 = (String)rs.elementAt(0);
                else
                    s5 = "N";
                printwriter.println("<BR><BR><center>");
                if(s5.compareTo("E") == 0)
                {
                    printwriter.println("<td class='textdesttabla'>");
                    if(TipoDespliegue.compareTo("N") == 0)
                        printwriter.println("<textarea rows=3 cols=50 name='comentario' onkeypress='return ValidarCaracteres(9);'>" + s15 + "</textarea>");
                    else
                        printwriter.println(s15);
                    printwriter.println("</td>");
                    printwriter.println("</tr>");
                    printwriter.println("</table>");
                    flag = true;
                    if(TipoDespliegue.compareTo("N") == 0)
                    {
                    	
                        if(integer1.intValue() > 0 || (sTipo.equals("R") &&  Acceso.compareTo("00001") != 0 ))
                            printwriter.println("<input type='button' name='workflow' value='WorkFlow' class='fondoinput' language='javascript' onclick=\"window.open('detalleflujo.jsp?ID=" + i + "','datos');\">");
                        if (!sTipo.equals("R"))
                        	printwriter.println("<input type='button' name='historico' value='Pasar Hist\363rico' class='fondoinput' language='javascript' onclick=\"return Eliminar('H'," + i + ");\">");
                        if (!sTipo.equals("R") || (Acceso.compareTo("10000") == 0 || Acceso.compareTo("00010") == 0) )
                        	printwriter.println("<input type='button' name='eliminar' value='Eliminar' class='fondoinput' language='javascript' onclick=\"return Eliminar('E'," + i + ");\">");
                        
                        if (!sTipo.equals("R") || (Acceso.compareTo("10000") == 0 || Acceso.compareTo("01000") == 0) )
                        	printwriter.println("<input type='button' name='grabar' value='Grabar' class='fondoinput' language='javascript' onclick='return grabacomentario();'>");
                    }
                }
                if(s5.compareTo("N") != 0)
                {
                    if(!flag)
                    {
                        printwriter.println("<td class='textdesttabla'>&nbsp;");
                        printwriter.println(s15);
                        printwriter.println("</td>");
                        printwriter.println("</tr>");
                        printwriter.println("</table>");
                    }
                    printwriter.println("<input type='button' name='descarga' value='Descargar' class='fondoinput' language='javascript' onclick='window.open(\"descarga.jsp?FILEDESC=" + s13 + "\",\"_blank\")'>");
                }
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
    String DireccionArchivo;
    String TipoDoc;
    String TipoDespliegue;
    String RutaBase;
    String Acceso;
}