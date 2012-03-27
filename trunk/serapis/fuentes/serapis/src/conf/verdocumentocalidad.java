package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class verdocumentocalidad extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public verdocumentocalidad() {
        ADatos = new AccDataBase();
        AFunc = new funciones();
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s = (String)httpsession.getValue("SerapisUser");
        if(s != null && s.length() > 0)
        {
            String s1 = httpservletrequest.getParameter("ID");
            if(s1 == null || s1.length() == 0)
                s1 = "0";
            Integer integer = new Integer(s1);
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<form name='doccalidad' method='POST' action=''>");
            printwriter.println("<input name='DOC' value='" + integer + "' type='hidden'>");
            PreparaPublica(printwriter, integer.intValue());
            printwriter.println("</form>");
            printwriter.println("<script language='javascript'>");
            
            printwriter.println("function fTipoGestion(objSelect)");
            printwriter.println("{");
            printwriter.println("  var objWD = document.doccalidad.selwfa;");
            printwriter.println("  var objWR = document.doccalidad.selwfe;");
            printwriter.println("  if (objSelect.value == 'R'){ ");
            printwriter.println("  		objWD.disabled = true;");
            printwriter.println("  		objWR.disabled = false;");
            printwriter.println("  		objWD.options(0).selected = true;");
            printwriter.println("  }else {");
            printwriter.println("  		objWR.disabled = true;");
            printwriter.println("  		objWD.disabled = false;");
            printwriter.println("  		objWR.options(0).selected = true;");
            printwriter.println("  }");
            printwriter.println("}");
            
            printwriter.println("function Elimina()");
            printwriter.println("{");
            printwriter.println("    document.doccalidad.action=\"procesadocumentocalidad.jsp?TIPO=E\";");
            printwriter.println("    document.doccalidad.submit();");
            printwriter.println("}");
            
            printwriter.println("function Modifica()");
            printwriter.println("{");
            printwriter.println("    document.doccalidad.action=\"procesadocumentocalidad.jsp?TIPO=M\";");
            printwriter.println("    document.doccalidad.submit();");
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
            
            printwriter.println("function valida()");
            printwriter.println("{");
            printwriter.println("  var Dato = document.doccalidad.descripcion.value;");
            printwriter.println("  if(ConAcento(Dato)!=0)");
            printwriter.println("  {");
            printwriter.println("    alert(\"La descripci\363n no puede tener letras acentuadas\");");
            printwriter.println("    document.doccalidad.descripcion.focus();");
            printwriter.println("  }");
            printwriter.println("  else");
            printwriter.println("  {");
            printwriter.println("  window.open(\"procesadocumentocalidad.jsp?TIPO=M&DOC=" + integer.intValue() + "\",\"datos\");");
            printwriter.println("  }");
            printwriter.println("}");
            printwriter.println("</script>");
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
        printwriter.println("<table border='1' align='center' width='95%'>");
        printwriter.println("<tr><td class='texttituloarea' colspan='4'>Editar Documento</td></tr>");
        printwriter.println("</table>");
        printwriter.println("<TABLE border='1' width='95%' align='center'>");
        printwriter.println("<tr>");
        printwriter.println("<td colspan='3' class='texttitulotabla'>Informaci\363n del Documento</td>");
        printwriter.println("</tr>");
        ADatos.connect();
        String s = "select tipodocumento,proceso,descripcion,tiemporetencion,tiempomedida,wf_aprobacion, wf_ejecucion, idlistadistribucion, tipo from gdc.documentoscalidad where id = " + i;
        int j = ADatos.ConsultaDB(s);
        rsfile = ADatos.getResult();
        if(j == 1)
        {
            String s1 = (String)rsfile.elementAt(0);
            String s2 = (String)rsfile.elementAt(1);
            String s3 = (String)rsfile.elementAt(2);
            Integer integer = (Integer)rsfile.elementAt(3);
            String s4 = (String)rsfile.elementAt(4);
            Integer integer1 = (Integer)rsfile.elementAt(5);
            Integer integer2 = (Integer)rsfile.elementAt(6);
            Integer integer3 = (Integer)rsfile.elementAt(7);
            String s5 = (String)rsfile.elementAt(8);
            
            /****************************************************************************************************/
            /* Proceso                                                                                          */
            /****************************************************************************************************/
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'>Proceso</td>");
            printwriter.println("<td class='textdesttabla'>");
            int k = ADatos.ConsultaDB("select sigla,descripcion from gdc.procesos where sigla = '" + s2.toString() + "'");
            rs = ADatos.getResult();
            if(rs.size() > 0) {
                String s6 = (String)rs.elementAt(1);
                printwriter.println(s6 + "<input name='tipoprocesos' value='" + s2.toString() + "' type='hidden'>");
            }
            printwriter.println("</td>");
            printwriter.println("</tr>");
            
            /****************************************************************************************************/
            /*Tipo Documento                                                                                    */
            /****************************************************************************************************/
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'>Tipo Documento</td>");
            printwriter.println("<td class='textdesttabla'>");
            k = ADatos.ConsultaDB("select sigla,descripcion from gdc.tipodocumentos where sigla = '" + s1.toString() + "'");
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                String s7 = (String)rs.elementAt(1);
                printwriter.println(s7 + "<input name='tipodocumentos' value='" + s1.toString() + "' type='hidden'>");
            }
            printwriter.println("</td>");
            printwriter.println("</tr>");
            
            /****************************************************************************************************/
            /* Nombre                                                                                           */
            /****************************************************************************************************/
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'>Nombre</td>");
            printwriter.println("<td class='textdesttabla'><input value = '" + s3 + "' name='descripcion' type='text' onkeypress='return ValidarCaracteres(9);' maxlength='100' style='WIDTH: 280px'>");
            printwriter.println("</td>");
            printwriter.println("</tr>");
            
            /****************************************************************************************************/
            /* Tipo Gesti�n                                                                                     */
            /****************************************************************************************************/
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'>Tipo</td>");
            printwriter.println("<td class='textdesttabla'>");
            printwriter.println("<select name='seltipo' onchange='javascript:fTipoGestion(this)'>");
            
            if(s5.compareTo("D") == 0)
                printwriter.println("<option value='D' selected>Documento</option>");
            else
                printwriter.println("<option value='D'>Documento</option>");
            
            if(s5.compareTo("R") == 0)
                printwriter.println("<option value='R' selected>Registro</option>");
            else
                printwriter.println("<option value='R'>Registro</option>");
            
            printwriter.println("</select>");
            printwriter.println("</td>");
            printwriter.println("</tr>");
            
            /****************************************************************************************************/
            /* WorkFlow Aprobaci�n de Documentos                                                                */
            /****************************************************************************************************/
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'>WorkFlow Aprobaci\363n de Documentos</td>");
            printwriter.println("<td class='textdesttabla'>");
            
            if(s5.compareTo("D") == 0)
            	printwriter.println("<select name='selwfa'>");
            else
            	printwriter.println("<select name='selwfa' disabled='true'>");
            	
            if(integer1.intValue() == 0)
                printwriter.println("<option value='0' selected>No Asociado</option>");
            else
                printwriter.println("<option value='0'>No Asociado</option>");
            
            k = ADatos.ConsultaDB("SELECT idwf,nombre from gdc.workflow where tipowf = 'A' and estado = 'A' and visible='S' ORDER by nombre");
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                for(int l = 0; l < rs.size(); l += 2)
                    if(integer1.intValue() == ((Integer)rs.elementAt(l)).intValue())
                        printwriter.println("<option value='" + ((Integer)rs.elementAt(l)).toString() + "' selected>" + ((String)rs.elementAt(l + 1)).toString() + "</selected>");
                    else
                        printwriter.println("<option value='" + ((Integer)rs.elementAt(l)).toString() + "'>" + ((String)rs.elementAt(l + 1)).toString() + "</selected>");

            }
            printwriter.println("</select>");
            printwriter.println("</td>");
            printwriter.println("</tr>");
            
            /****************************************************************************************************/
            /* WorkFlow Aprobaci�n de Registros                                                                 */
            /****************************************************************************************************/
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'>WorkFlow Aprobaci\363n de Registros</td>");
            printwriter.println("<td class='textdesttabla'>");
            
            if(s5.compareTo("D") == 0)
            	printwriter.println("<select name='selwfe' disabled='true'>");
            else
            	printwriter.println("<select name='selwfe'>");
            
            if(integer2.intValue() == 0)
                printwriter.println("<option value='0' selected>No Asociado</option>");
            else
                printwriter.println("<option value='0'>No Asociado</option>");
            
            k = ADatos.ConsultaDB("SELECT idwf,nombre from gdc.workflow where tipowf = 'E' and estado = 'A' and visible='S' ORDER by nombre");
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                for(int i1 = 0; i1 < rs.size(); i1 += 2)
                    if(integer2.intValue() == ((Integer)rs.elementAt(i1)).intValue())
                        printwriter.println("<option value='" + ((Integer)rs.elementAt(i1)).toString() + "' selected>" + ((String)rs.elementAt(i1 + 1)).toString() + "</selected>");
                    else
                        printwriter.println("<option value='" + ((Integer)rs.elementAt(i1)).toString() + "'>" + ((String)rs.elementAt(i1 + 1)).toString() + "</selected>");

            }
            printwriter.println("</select>");
            printwriter.println("</td>");
            printwriter.println("</tr>");
            
            /****************************************************************************************************/
            /* Lista Distribuci�n                                                                               */
            /****************************************************************************************************/
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'>Lista Distribuci\363n</td>");
            printwriter.println("<td class='textdesttabla'>");
            printwriter.println("<select name='sellista'>");
            if(integer3.intValue() == 0)
                printwriter.println("<option value='0' selected>No Asociado</option>");
            else
                printwriter.println("<option value='0'>No Asociado</option>");
            k = ADatos.ConsultaDB("SELECT idlista,descripcion from gdc.listadistribucion where estado = 'A' ORDER by descripcion");
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                for(int j1 = 0; j1 < rs.size(); j1 += 2)
                    if(integer3.intValue() == ((Integer)rs.elementAt(j1)).intValue())
                        printwriter.println("<option value='" + ((Integer)rs.elementAt(j1)).toString() + "' selected>" + ((String)rs.elementAt(j1 + 1)).toString() + "</selected>");
                    else
                        printwriter.println("<option value='" + ((Integer)rs.elementAt(j1)).toString() + "'>" + ((String)rs.elementAt(j1 + 1)).toString() + "</selected>");

            }
            printwriter.println("</select>");
            printwriter.println("</td>");
            printwriter.println("</tr>");
            
            /****************************************************************************************************/
            /* Tipo Retenci�n                                                                                   */
            /****************************************************************************************************/
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'>Tiempo Retenci\363n</td>");
            printwriter.println("<td class='textdesttabla'><input value = '" + integer.toString() + "' name='tiempo' language='javascript' style='WIDTH: 100px' onkeypress='return ValidarCaracteres(4);' maxlength='2'>");
            printwriter.println("&nbsp;");
            printwriter.println("<select name='tipotiempo'>");
            if(s4.toString().compareTo("D\355a") == 0)
                printwriter.println("<option value='D\355a' selected>D\355a</option>");
            else
                printwriter.println("<option value='D\355a'>D\355a</option>");
            if(s4.toString().compareTo("Mes") == 0)
                printwriter.println("<option value='Mes' selected>Mes</option>");
            else
                printwriter.println("<option value='Mes'>Mes</option>");
            if(s4.toString().compareTo("A\361o") == 0)
                printwriter.println("<option value='A\361o' selected>A\361o</option>");
            else
                printwriter.println("<option value='A\361o'>A\361o</option>");
            printwriter.println("</select>");
            printwriter.println("</td>");
            printwriter.println("</tr>");
            
            printwriter.println("<tr><td colspan='2' class='textdesttabla'>&nbsp;</td></tr>");
            printwriter.println("<tr><td colspan='2' align='center' class='textdesttabla'> ");
            printwriter.println("<input type='button' name='btnGrabar' value='Grabar' class='fondoinput' language='javascript' onclick='return Modifica();'>");
            printwriter.println("<input type='button' name='btnEliminar' value='Eliminar' class='fondoinput' language='javascript' onclick='return Elimina();'>");
            printwriter.println("</td></tr>");
            printwriter.println("</table>");
        } else
        {
            printwriter.println("<tr>");
            printwriter.println("<td colspan='2' class='textdesttabla' align='center'>Documento no existe</td>");
            printwriter.println("</td>");
            printwriter.println("</tr>");
        }
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    Vector rsfile;
}