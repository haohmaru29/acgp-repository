package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class nuevodocumentocalidad extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public nuevodocumentocalidad() {
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
            printwriter.println("<form name='doccalidad' method='POST' action='procesadocumentocalidad.jsp?TIPO=N&DOC=0'>");
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
            
            printwriter.println("function EsNumero(Dato)");
            printwriter.println("{");
            printwriter.println("  var l=Dato;");
            printwriter.println("  if((l=='1') || (l=='2') || (l=='3') || (l=='4') || (l=='5') || (l=='6') || (l=='7') || (l=='8') || (l=='9') || (l=='0'))");
            printwriter.println("    return(1);");
            printwriter.println("  else");
            printwriter.println("    return(0);");
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
            
            printwriter.println("function ConAcento(Dato)");
            printwriter.println("{");
            printwriter.println("  var i=0;");
            printwriter.println("  var l;");
            printwriter.println("  var sigue=1;");
            printwriter.println("  var ret=0;");
            printwriter.println("  for(i=0;((i<Dato.length) && (sigue==1));i++)");
            printwriter.println("  {");
            printwriter.println("    l=Dato.charAt(i);");
            printwriter.println("    if((l=='\341') || (l=='\351') || (l=='\355') || (l=='\363') || (l=='\372') || (l=='\301') || (l=='\311') || (l=='\315') || (l=='\323') || (l=='\332') || (l=='\321')  || (l=='\361'))");
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
            printwriter.println("  if (EsValorNum(\"E\",document.doccalidad.tiempo.value)==1)");
            printwriter.println("  {");
            printwriter.println("    if(Dato!=\"\")");
            printwriter.println("    {");
            printwriter.println("      if(ConAcento(Dato)!=0)");
            printwriter.println("      {");
            printwriter.println("        alert(\"La descripci\363n no puede tener letras acentuadas o \361\");");
            printwriter.println("        document.doccalidad.descripcion.focus();");
            printwriter.println("      }");
            printwriter.println("      else");
            printwriter.println("      {");
            printwriter.println("        document.doccalidad.submit();");
            printwriter.println("      }");
            printwriter.println("    }");
            printwriter.println("    else");
            printwriter.println("    {");
            printwriter.println("      alert(\"Especifique una descripci\363n\");");
            printwriter.println("      document.doccalidad.descripcion.focus();");
            printwriter.println("    }");
            printwriter.println("  }");
            printwriter.println("  else");
            printwriter.println("  {");
            printwriter.println("    alert(\"Especifique un tiempo v\341lido\");");
            printwriter.println("    document.doccalidad.tiempo.focus();");
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
        printwriter.println("<TABLE border='1' align='center' width='80%'>");
        printwriter.println("<tr>");
        printwriter.println("<td colspan='3' class='texttitulotabla'>Informaci\363n del Documento</td>");
        printwriter.println("</tr>");
        ADatos.connect();
        
        /****************************************************************************************************/
        /*Tipo Documento                                                                                    */
        /****************************************************************************************************/
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla'>Tipo Documento</td>");
        printwriter.println("<td class='textdesttabla'>");
        printwriter.println("<select name='tipodocumentos'>");
        int j = ADatos.ConsultaDB("select sigla,descripcion from gdc.tipodocumentos order by descripcion");
        rs = ADatos.getResult();
        printwriter.println((String)rs.elementAt(0));
        for(int k = 0; k < rs.size(); k += 2)
        {
            String s = (String)rs.elementAt(k);
            printwriter.println("<option value='" + s + "'>" + (String)rs.elementAt(k + 1) + "</option>");
        }
        printwriter.println("</select>");
        printwriter.println("</td>");
        printwriter.println("</tr>");
        
        /****************************************************************************************************/
        /* Proceso                                                                                          */
        /****************************************************************************************************/
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla'>Proceso</td>");
        printwriter.println("<td class='textdesttabla'>");
        j = ADatos.ConsultaDB("select sigla,descripcion from gdc.procesos order by descripcion");
        rs = ADatos.getResult();
        printwriter.println("<select name='tipoprocesos'>");
        for(int l = 0; l < rs.size(); l += 2)
        {
            String s1 = (String)rs.elementAt(l);
            printwriter.println("<option value='" + s1 + "'>" + (String)rs.elementAt(l + 1) + "</option>");
        }
        printwriter.println("</select>");
        printwriter.println("</td>");
        printwriter.println("</tr>");
        
        /****************************************************************************************************/
        /* Nombre                                                                                           */
        /****************************************************************************************************/
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla'>Nombre</td>");
        printwriter.println("<td class='textdesttabla'><input value = '' name='descripcion' language='javascript' style='WIDTH: 250px' onkeypress='return ValidarCaracteres(9);' maxlength='100'>");
        printwriter.println("</td>");
        printwriter.println("</tr>");

        /****************************************************************************************************/
        /* Tipo Gesti�n                                                                                     */
        /****************************************************************************************************/
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla'>Tipo Gesti\363n</td>");
        printwriter.println("<td class='textdesttabla'>");
        printwriter.println("<select name='seltipo' onchange='javascript:fTipoGestion(this)'>");
        printwriter.println("<option value='D'>Documento</option>");
        printwriter.println("<option value='R'>Registro</option>");
        printwriter.println("</select>");
        printwriter.println("</td>");
        printwriter.println("</tr>");
        
        /****************************************************************************************************/
        /* WorkFlow Aprobaci�n de Documentos                                                                */
        /****************************************************************************************************/
        printwriter.println("<tr>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla'>WorkFlow Aprobaci\363n de Documentos</td>");
        printwriter.println("<td class='textdesttabla'>");
        printwriter.println("<select name='selwfa'>");
        printwriter.println("<option value='0'>No Asociado</option>");
        j = ADatos.ConsultaDB("SELECT idwf,nombre from gdc.workflow where tipowf = 'A' and estado = 'A' and visible='S' ORDER by nombre");
        rs = ADatos.getResult();
        if(rs.size() > 0){
            for(int i1 = 0; i1 < rs.size(); i1 += 2)
                printwriter.println("<option value='" + ((Integer)rs.elementAt(i1)).toString() + "'>" + ((String)rs.elementAt(i1 + 1)).toString() + "</selected>");
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
        printwriter.println("<select name='selwfe' disabled='true'>");
        printwriter.println("<option value='0'>No Asociado</option>");
        j = ADatos.ConsultaDB("SELECT idwf,nombre from gdc.workflow where tipowf = 'E' and estado = 'A' and visible='S' ORDER by nombre");
        rs = ADatos.getResult();
        if(rs.size() > 0)
        {
            for(int j1 = 0; j1 < rs.size(); j1 += 2)
                printwriter.println("<option value='" + ((Integer)rs.elementAt(j1)).toString() + "'>" + ((String)rs.elementAt(j1 + 1)).toString() + "</selected>");

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
        printwriter.println("<option value='0'>No Asociado</option>");
        j = ADatos.ConsultaDB("SELECT idlista,descripcion from gdc.listadistribucion where estado = 'A' ORDER by descripcion");
        rs = ADatos.getResult();
        if(rs.size() > 0)
        {
            for(int k1 = 0; k1 < rs.size(); k1 += 2)
                printwriter.println("<option value='" + ((Integer)rs.elementAt(k1)).toString() + "'>" + ((String)rs.elementAt(k1 + 1)).toString() + "</selected>");

        }
        printwriter.println("</select>");
        printwriter.println("</td>");
        printwriter.println("</tr>");
                
        /****************************************************************************************************/
        /* Tipo Retenci�n                                                                                   */
        /****************************************************************************************************/
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla'>Tiempo Retenci\363n</td>");
        printwriter.println("<td class='textdesttabla'><input value = '' name='tiempo' language='javascript' style='WIDTH: 100px' onkeypress='return ValidarCaracteres(4);' maxlength='2'>");
        printwriter.println("&nbsp;");
        printwriter.println("<select name='tipotiempo'>");
        printwriter.println("<option value='D\355a'>D\355a</option>");
        printwriter.println("<option value='Mes'>Mes</option>");
        printwriter.println("<option value='A\361o' selected>A\361o</option>");
        printwriter.println("</select>");
        printwriter.println("</td>");
        printwriter.println("</tr>");
        
        printwriter.println("<tr></table>");
        printwriter.println("<BR><center><input type='button' name='Agregar' value='Agregar' class='fondoinput' language='javascript' onclick='return valida();'>&nbsp;<input type='button' name='btnCancelar' value='Cancelar' class='fondoinput' language='javascript' onclick=\"window.open('listamaestra.jsp','datos');\"></center>");
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    Vector rsfile;
}