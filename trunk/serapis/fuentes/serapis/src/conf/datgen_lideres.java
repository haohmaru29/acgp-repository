package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class datgen_lideres extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public datgen_lideres() {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        IdDoc = "";
        Tipo = "";
        IdArchivo = "";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException {
        String s = "";
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s1 = (String)httpsession.getValue("SerapisUser");
        if(s1 != null && s1.length() > 0)
        {
            ADatos.connect();
            Tipo = httpservletrequest.getParameter("TIPO");
            IdArchivo = httpservletrequest.getParameter("ID");
            if(Tipo == null || Tipo.length() == 0)
                Tipo = "LISTA";
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            CargaForm(printwriter);
            printwriter.println("</body>");
            printwriter.println("</html>");
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "CONF", 6);
        }
    }

    void CargaForm(PrintWriter printwriter)
    {
        //boolean flag = false;
        //String s3 = "";
        //String s4 = "";
        //boolean flag1 = false;
        if(Tipo.compareTo("LISTA") == 0)
        {
            String s = "select orden, linea1, linea2, mail, foto, ancho, alto,id from gdc.lideres order by orden,linea1";
            int i = ADatos.ConsultaDB(s);
            rs = new Vector();
            rs.clear();
            rs = ADatos.getResult();
            printwriter.println("<table border='1' width='80%' align='center'>");
            printwriter.println("<tr><td align='center' class='texttituloarea' colspan='6'>L\355deres</td></tr>");
            if(rs.size() > 0)
            {
                printwriter.println("<tr>");
                printwriter.println("<td width='5%' class='texttitulotabla'>Orden</td>");
                printwriter.println("<td width='25%' class='texttitulotabla'>Nombre</td>");
                printwriter.println("<td width='20%' class='texttitulotabla'>Adicional</td>");
                printwriter.println("<td width='20%' class='texttitulotabla'>Mail</td>");
                printwriter.println("<td width='20%' class='texttitulotabla'>Foto</td>");
                printwriter.println("<td width='10%' class='texttitulotabla'>Ancho/Alto</td>");
                printwriter.println("</tr>");
                for(int l = 0; l < rs.size(); l += 8)
                {
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla'><a href='datgen_lideres.jsp?ID=" + ((Integer)rs.elementAt(l + 7)).toString() + "&TIPO=VER'>" + ((Integer)rs.elementAt(l)).toString() + "</a></td>");
                    printwriter.println("<td class='textdesttabla'>" + (String)rs.elementAt(l + 1) + "</td>");
                    printwriter.println("<td class='textdesttabla'>" + (String)rs.elementAt(l + 2) + "</td>");
                    printwriter.println("<td class='textdesttabla'>" + (String)rs.elementAt(l + 3) + "</td>");
                    printwriter.println("<td class='textdesttabla'><a href='../images/" + (String)rs.elementAt(l + 4) + "' target='_blank'>" + (String)rs.elementAt(l + 4) + "</a></td>");
                    printwriter.println("<td class='textdesttabla'>" + ((Integer)rs.elementAt(l + 5)).toString() + " x " + ((Integer)rs.elementAt(l + 6)).toString() + "</td>");
                    printwriter.println("</tr>");
                }

            }
            printwriter.println("<tr><td align='center' class='textdesttabla' colspan='6'>&nbsp;</td></tr>");
            printwriter.println("<tr><td align='center' class='textdesttabla' colspan='6'>");
            printwriter.println("<input type='button' name='nuevo' value='Nuevo' class='fondoinput' language='javascript' onclick='window.open(\"datgen_lideres.jsp?TIPO=NUEVO\",\"datos\")'>&nbsp;&nbsp;");
            printwriter.println("<input type='button' name='BtnImprimir' value='Imprimir' class='fondoinput' language='javascript' onclick='window.print()'>");
            printwriter.println("</tr>");
            printwriter.println("</table>");
        }
        if(Tipo.compareTo("NUEVO") == 0)
        {
            Integer integer = new Integer(0);
            String s1 = "SELECT MAX(id) from gdc.lideres";
            int j = ADatos.ConsultaDB(s1);
            rs = new Vector();
            rs.clear();
            rs = ADatos.getResult();
            if(rs.size() > 0)
                integer = (Integer)rs.elementAt(0);
            printwriter.println("<form name='frmlideres' method='POST' enctype='multipart/form-data' action='datgen_lideres_procesa.jsp'>");
            printwriter.println("<table border='1' align='center' width='80%'>");
            printwriter.println("<tr><td width='80%' colspan='4' class='texttituloarea'>Lideres</td></tr>");
            printwriter.println("</table><BR>");
            printwriter.println("<table border='1' width='80%' align='center'>");
            printwriter.println("<input value='" + integer.intValue() + "' name='numero' style='WIDTH: 50px' type='hidden'>");
            printwriter.println("<input value='INSERT' name='tipo' type='hidden'>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='15%'>Indice</td>");
            printwriter.println("<td class='textdesttabla' width='85%'><input name='indice' style='WIDTH: 50px' type='text' language='javascript' onkeypress='return ValidarCaracteres(4);' maxlength='3'></td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='15%'>Nombre</td>");
            printwriter.println("<td class='textdesttabla' width='85%'><input name='nombre' style='WIDTH: 420px' type='text' language='javascript' onkeypress='return ValidarCaracteres(9);' maxlength='100'></td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'>Adicional</td>");
            printwriter.println("<td class='textdesttabla'><input name='adicional' style='WIDTH: 420px' type='text' language='javascript' onkeypress='return ValidarCaracteres(9);' maxlength='100'></td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'>Mail</td>");
            printwriter.println("<td class='textdesttabla'><input name='mail' style='WIDTH: 420px' type='text' language='javascript' onkeypress='return ValidarCaracteres(9);' maxlength='100'></td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'>Ancho</td>");
            printwriter.println("<td class='textdesttabla'><input name='ancho' style='WIDTH: 50px' type='text' language='javascript' onkeypress='return ValidarCaracteres(4);' maxlength='3'></td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'>Alto</td>");
            printwriter.println("<td class='textdesttabla'><input name='alto' style='WIDTH: 50px' type='text' language='javascript' onkeypress='return ValidarCaracteres(4);' maxlength='3'></td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'>Foto</td>");
            printwriter.println("<td class='textdesttabla'><input name='archivo' style='WIDTH: 420px' type='file'></td>");
            printwriter.println("</tr>");
            printwriter.println("</table>");
            printwriter.println("<br><center><input type='button' name='anadirtipodoc' value='Grabar' class='fondoinput' onclick='return Grabar();'>");
            printwriter.println("</center>");
            printwriter.println("</form>");
            CargaScript(printwriter);
        }
        if(Tipo.compareTo("VER") == 0)
            if(IdArchivo != null || IdArchivo.length() > 0)
            {
                String s2 = "SELECT id,orden,linea1,linea2,mail,foto,ancho,alto from gdc.lideres where id = " + IdArchivo;
                int k = ADatos.ConsultaDB(s2);
                rs = new Vector();
                rs.clear();
                rs = ADatos.getResult();
                if(rs.size() > 0)
                {
                    Integer integer1 = new Integer(0);
                    printwriter.println("<form name='frmlideres' method='POST' enctype='multipart/form-data' action='datgen_lideres_procesa.jsp'>");
                    printwriter.println("<table border='1' align='center' width='80%'>");
                    printwriter.println("<tr><td width='80%' colspan='4' class='texttituloarea'>Lideres</td></tr>");
                    printwriter.println("</table><BR>");
                    printwriter.println("<table border='1' width='80%' align='center'>");
                    integer1 = (Integer)rs.elementAt(0);
                    printwriter.println("<input value='" + (integer1.intValue() - 1) + "' name='numero' style='WIDTH: 50px' type='hidden'>");
                    printwriter.println("<input value='UPDATE' name='tipo' type='hidden'>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla' width='15%'>Indice</td>");
                    printwriter.println("<td class='textdesttabla' width='85%'><input value = '" + ((Integer)rs.elementAt(1)).toString() + "' name='indice' style='WIDTH: 50px' type='text' language='javascript' onkeypress='return ValidarCaracteres(4);' maxlength='3'></td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla' width='15%'>Nombre</td>");
                    printwriter.println("<td class='textdesttabla' width='85%'><input value = '" + (String)rs.elementAt(2) + "' name='nombre' style='WIDTH: 420px' type='text' language='javascript' onkeypress='return ValidarCaracteres(9);' maxlength='100'></td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla'>Adicional</td>");
                    printwriter.println("<td class='textdesttabla'><input value = '" + (String)rs.elementAt(3) + "' name='adicional' style='WIDTH: 420px' type='text' language='javascript' onkeypress='return ValidarCaracteres(9);' maxlength='100'></td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla'>Mail</td>");
                    printwriter.println("<td class='textdesttabla'><input value = '" + (String)rs.elementAt(4) + "' name='mail' style='WIDTH: 420px' type='text' language='javascript' onkeypress='return ValidarCaracteres(9);' maxlength='100'></td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla'>Imagen</td>");
                    printwriter.println("<td class='textdesttabla'><img src='../images/" + (String)rs.elementAt(5) + "' width='" + ((Integer)rs.elementAt(6)).toString() + "' height='" + ((Integer)rs.elementAt(7)).toString() + "'></td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla'>Ancho</td>");
                    printwriter.println("<td class='textdesttabla'><input value = '" + ((Integer)rs.elementAt(6)).toString() + "' name='ancho' style='WIDTH: 50px' type='text' language='javascript' onkeypress='return ValidarCaracteres(4);' maxlength='3'></td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla'>Alto</td>");
                    printwriter.println("<td class='textdesttabla'><input value = '" + ((Integer)rs.elementAt(7)).toString() + "' name='alto' style='WIDTH: 50px' type='text' language='javascript' onkeypress='return ValidarCaracteres(4);' maxlength='3'></td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla'>Foto</td>");
                    printwriter.println("<td class='textdesttabla'><input name='archivo' style='WIDTH: 420px' type='file'></td>");
                    printwriter.println("</tr>");
                    printwriter.println("</table>");
                    printwriter.println("<br><center><input type='button' name='anadirtipodoc' value='Grabar' class='fondoinput' onclick='return Grabar();'>");
                    printwriter.println("<input type='button' name='btneliminar' value='Eliminar' class='fondoinput' onclick='return Eliminar();'>");
                    printwriter.println("</center>");
                    printwriter.println("</form>");
                    CargaScript(printwriter);
                } else
                {
                    printwriter.println("<br><br><br><center>No se encontr\363 la informaci\363n del lider</center>");
                }
            } else
            {
                printwriter.println("<br><br><br><center>No se encontr\363 la informaci\363n del lider</center>");
            }
    }

    void CargaScript(PrintWriter printwriter)
    {
        printwriter.println("<script language='javascript'>");
        printwriter.println("function Grabar()");
        printwriter.println("{");
        printwriter.println("  if(document.frmlideres.numero.value != \"\")");
        printwriter.println("  {");
        printwriter.println("     if(document.frmlideres.indice.value != \"\")");
        printwriter.println("     {");
        printwriter.println("        if(document.frmlideres.nombre.value != \"\")");
        printwriter.println("        {");
        printwriter.println("           if(document.frmlideres.adicional.value != \"\")");
        printwriter.println("           {");
        printwriter.println("              if(document.frmlideres.mail.value != \"\")");
        printwriter.println("              {");
        printwriter.println("                 if(document.frmlideres.ancho.value != \"\")");
        printwriter.println("                 {");
        printwriter.println("                    if(document.frmlideres.alto.value != \"\")");
        printwriter.println("                    {");
        printwriter.println("                       if(document.frmlideres.archivo.value != \"\")");
        printwriter.println("                       {");
        printwriter.println("                          document.frmlideres.submit();");
        printwriter.println("                       }");
        printwriter.println("                       else");
        printwriter.println("                       {");
        printwriter.println("                          alert(\"Debe ingresar un archivo.\");");
        printwriter.println("                       }");
        printwriter.println("                    }");
        printwriter.println("                    else");
        printwriter.println("                    {");
        printwriter.println("                       alert(\"Debe ingresar un Alto.\");");
        printwriter.println("                       document.frmlideres.alto.focus();");
        printwriter.println("                    }");
        printwriter.println("                 }");
        printwriter.println("                 else");
        printwriter.println("                 {");
        printwriter.println("                    alert(\"Debe ingresar un Ancho.\");");
        printwriter.println("                    document.frmlideres.ancho.focus();");
        printwriter.println("                 }");
        printwriter.println("              }");
        printwriter.println("              else");
        printwriter.println("              {");
        printwriter.println("                 alert(\"Debe ingresar un Mail.\");");
        printwriter.println("                 document.frmlideres.mail.focus();");
        printwriter.println("              }");
        printwriter.println("           }");
        printwriter.println("           else");
        printwriter.println("           {");
        printwriter.println("              alert(\"Debe ingresar un Adicional.\");");
        printwriter.println("              document.frmlideres.adicional.focus();");
        printwriter.println("           }");
        printwriter.println("        }");
        printwriter.println("        else");
        printwriter.println("        {");
        printwriter.println("           alert(\"Debe ingresar un Nombre.\");");
        printwriter.println("           document.frmlideres.nombre.focus();");
        printwriter.println("        }");
        printwriter.println("     }");
        printwriter.println("     else");
        printwriter.println("     {");
        printwriter.println("        alert(\"Debe ingresar un indice.\");");
        printwriter.println("        document.frmlideres.indice.focus();");
        printwriter.println("     }");
        printwriter.println("  }");
        printwriter.println("  else");
        printwriter.println("  {");
        printwriter.println("     alert(\"No es posible almacenar la imagen. Intente m\341s tarde.\");");
        printwriter.println("  }");
        printwriter.println("}");
        printwriter.println("function Eliminar()");
        printwriter.println("{");
        printwriter.println("   if(confirm(\"\277Esta seguro que desea eliminar el lider?\"))");
        printwriter.println("   {");
        printwriter.println("      document.frmlideres.tipo.value=\"DELETE\";");
        printwriter.println("      document.frmlideres.submit();");
        printwriter.println("   }");
        printwriter.println("}");
        printwriter.println("</script>");
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    String IdDoc;
    String Tipo;
    String IdArchivo;
}