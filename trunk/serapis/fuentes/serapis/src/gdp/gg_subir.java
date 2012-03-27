// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   gg_subir.java

package gdp;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class gg_subir extends HttpServlet
{

    public gg_subir()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        String s = "";
        Integer integer = new Integer(0);
        Integer integer2 = new Integer(0);
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        UserGDC = (String)httpsession.getValue("SerapisUser");
        if(UserGDC != null && UserGDC.length() > 0)
        {
            ADatos.connect();
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<form name='frmgg' method='POST' action='gg_procesa.jsp'>");
            Integer integer1 = new Integer(AFunc.ObtieneAnio());
            Integer integer3 = new Integer(AFunc.ObtieneMes());
            int j1 = AFunc.ObtieneAnio();
            printwriter.println("<TABLE border='1' width='80%' align='center'>");
            printwriter.println("<tr>");
            printwriter.println("<td colspan='2' class='texttitulotabla'>Gr\341fico de Gesti\363n</td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='15%'><B>A\361o</B></td>");
            printwriter.println("<td class='textdesttabla' width='85%'>");
            printwriter.println("<select name='selAnio'>");
            for(int j = 2005; j <= j1; j++)
                if(integer1.intValue() == j)
                    printwriter.println("<option value='" + j + "' selected>" + j + "</option>");
                else
                    printwriter.println("<option value='" + j + "'>" + j + "</option>");

            printwriter.println("</select>");
            printwriter.println("</td></tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='15%'><B>Mes</B></td>");
            printwriter.println("<td class='textdesttabla' width='85%'>");
            printwriter.println("<select name='selMes'>");
            if(integer3.intValue() == 1)
                printwriter.println("   <option value='1' selected>Enero</option>");
            else
                printwriter.println("   <option value='1'>Enero</option>");
            if(integer3.intValue() == 2)
                printwriter.println("   <option value='2' selected>Febrero</option>");
            else
                printwriter.println("   <option value='2'>Febrero</option>");
            if(integer3.intValue() == 3)
                printwriter.println("   <option value='3' selected>Marzo</option>");
            else
                printwriter.println("   <option value='3'>Marzo</option>");
            if(integer3.intValue() == 4)
                printwriter.println("   <option value='4' selected>Abril</option>");
            else
                printwriter.println("   <option value='4'>Abril</option>");
            if(integer3.intValue() == 5)
                printwriter.println("   <option value='5' selected>Mayo</option>");
            else
                printwriter.println("   <option value='5'>Mayo</option>");
            if(integer3.intValue() == 6)
                printwriter.println("   <option value='6' selected>Junio</option>");
            else
                printwriter.println("   <option value='6'>Junio</option>");
            if(integer3.intValue() == 7)
                printwriter.println("   <option value='7' selected>Julio</option>");
            else
                printwriter.println("   <option value='7'>Julio</option>");
            if(integer3.intValue() == 8)
                printwriter.println("   <option value='8' selected>Agosto</option>");
            else
                printwriter.println("   <option value='8'>Agosto</option>");
            if(integer3.intValue() == 9)
                printwriter.println("   <option value='9' selected>Septiembre</option>");
            else
                printwriter.println("   <option value='9'>Septiembre</option>");
            if(integer3.intValue() == 10)
                printwriter.println("   <option value='10' selected>Octubre</option>");
            else
                printwriter.println("   <option value='10'>Octubre</option>");
            if(integer3.intValue() == 11)
                printwriter.println("   <option value='11' selected>Noviembre</option>");
            else
                printwriter.println("   <option value='11'>Noviembre</option>");
            if(integer3.intValue() == 12)
                printwriter.println("   <option value='12' selected>Diciembre</option>");
            else
                printwriter.println("   <option value='12'>Diciembre</option>");
            printwriter.println("</select>");
            printwriter.println("</td></tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'><B>Proceso</B></td>");
            printwriter.println("<td class='textdesttabla'>");
            int i = ADatos.ConsultaDB("select p.sigla,p.descripcion from gdc.procesos p, sgc.perfil u where u.proceso = p.sigla and u.perfil = 'E' and u.login = '" + UserGDC + "' order by p.descripcion,p.sigla");
            rs = ADatos.getResult();
            printwriter.println("<select name='proceso' onchange='return CambiaProcedimientos();'>");
            if(rs.size() > 0)
            {
                for(int k = 0; k < rs.size(); k += 2)
                {
                    if(s.length() == 0)
                        s = (String)rs.elementAt(k);
                    printwriter.println("<option value='" + (String)rs.elementAt(k) + "'>" + (String)rs.elementAt(k + 1) + "</option>");
                }

            }
            printwriter.println("</select>");
            printwriter.println("</td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'><B>Procedimiento</B></td>");
            printwriter.println("<td class='textdesttabla'>");
            i = ADatos.ConsultaDB("select DISTINCT proceso,procedimiento from gdc.procedimientos where proceso = '" + s + "' order by procedimiento");
            rs = ADatos.getResult();
            printwriter.println("<select name='procedimiento' onchange='return CambiaIndicador();'>");
            if(rs.size() > 0)
            {
                for(int l = 0; l < rs.size(); l += 2)
                    printwriter.println("<option value='" + (String)rs.elementAt(l + 1) + "'>" + (String)rs.elementAt(l + 1) + "</option>");

            }
            printwriter.println("</select>");
            printwriter.println("</td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'><B>Indicador</B></td>");
            printwriter.println("<td class='textdesttabla'>");
            rs = new Vector();
            i = ADatos.ConsultaDB("select idproc,procedimiento,definicionrol from gdc.procedimientos where proceso = '" + s + "' order by procedimiento");
            rs = ADatos.getResult();
            printwriter.println("<select name='indicador'>");
            if(rs.size() > 0)
            {
                for(int i1 = 0; i1 < rs.size(); i1 += 3)
                    printwriter.println("<option value='" + ((Integer)rs.elementAt(i1)).toString() + "'>" + (String)rs.elementAt(i1 + 2) + "</option>");

            }
            printwriter.println("</select>");
            printwriter.println("</td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'><B>Valor Obtenido</B></td>");
            printwriter.println("<td class='textdesttabla'>");
            printwriter.println("<select name='signo'><option value='+'>+</option><option value='-'>-</option></select>");
            printwriter.println("<input name='gap' language='javascript' style='WIDTH: 70px' value='' maxlength='10' onkeypress='return ValidarCaracteres(4);'>");
            printwriter.println("</td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' colspan='2' align='center'>");
            printwriter.println("<input type='button' name='btnSubir' class='fondoinput' value='Grabar' language='javascript' onclick='return Grabar();'>");
            printwriter.println("&nbsp;&nbsp;");
            printwriter.println("<input type='button' name='btnVolver' class='fondoinput' value='Volver' language='javascript' onclick='window.open(\"gg_grafico_gestion.jsp\",\"datos\")'>");
            printwriter.println("</tr>");
            printwriter.println("</tr>");
            printwriter.println("</form>");
            printwriter.println("<script language='javascript'>");
            CargScript(printwriter);
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

    void CargScript(PrintWriter printwriter)
    {
        printwriter.println("function Grabar()");
        printwriter.println("{");
        printwriter.println("   if(document.frmgg.selAnio.value != \"\")");
        printwriter.println("   {");
        printwriter.println("      if(document.frmgg.selMes.value != \"\")");
        printwriter.println("      {");
        printwriter.println("         if(document.frmgg.proceso.value != \"\")");
        printwriter.println("         {");
        printwriter.println("            if(document.frmgg.procedimiento.value != \"\")");
        printwriter.println("            {");
        printwriter.println("               if(document.frmgg.indicador.value != \"\")");
        printwriter.println("               {");
        printwriter.println("                   if(document.frmgg.signo.value != \"\")");
        printwriter.println("                   {");
        printwriter.println("                       if(document.frmgg.gap.value != \"\")");
        printwriter.println("                       {");
        printwriter.println("                            document.frmgg.submit();");
        printwriter.println("                       }");
        printwriter.println("                       else");
        printwriter.println("                       {");
        printwriter.println("                          alert(\"Debe ingresar valor obtenido\");");
        printwriter.println("                          document.frmgg.gap.setfocus();");
        printwriter.println("                       }");
        printwriter.println("                   }");
        printwriter.println("                   else");
        printwriter.println("                   {");
        printwriter.println("                      alert(\"Debe seleccionar signo\");");
        printwriter.println("                      document.frmgg.signo.setfocus();");
        printwriter.println("                   }");
        printwriter.println("               }");
        printwriter.println("               else");
        printwriter.println("               {");
        printwriter.println("                   alert(\"Debe seleccionar indicador\");");
        printwriter.println("                   document.frmgg.indicador.setfocus();");
        printwriter.println("               }");
        printwriter.println("            }");
        printwriter.println("            else");
        printwriter.println("            {");
        printwriter.println("               alert(\"Debe seleccionar procedimiento\");");
        printwriter.println("               document.frmgg.procedimiento.setfocus();");
        printwriter.println("            }");
        printwriter.println("         }");
        printwriter.println("         else");
        printwriter.println("         {");
        printwriter.println("            alert(\"Debe seleccionar proceso\");");
        printwriter.println("            document.frmgg.proceso.setfocus();");
        printwriter.println("         }");
        printwriter.println("      }");
        printwriter.println("      else");
        printwriter.println("      {");
        printwriter.println("         alert(\"Debe seleccionar Mes\");");
        printwriter.println("         document.frmgg.selMes.setfocus();");
        printwriter.println("      }");
        printwriter.println("   }");
        printwriter.println("   else");
        printwriter.println("   {");
        printwriter.println("      alert(\"Debe seleccionar a\361o\");");
        printwriter.println("      document.frmgg.selAnio.setfocus();");
        printwriter.println("   }");
        printwriter.println("}");
        printwriter.println("function CambiaProcedimientos()");
        printwriter.println("{");
        printwriter.println("var proceso;");
        printwriter.println("var indice;");
        printwriter.println("indice=0;");
        printwriter.println("proceso = document.frmgg.proceso.value;");
        printwriter.println("document.frmgg.procedimiento.options.length = 0;");
        int i = ADatos.ConsultaDB("select DISTINCT proceso,procedimiento from gdc.procedimientos order by idproc");
        rs = ADatos.getResult();
        for(int j = 0; j < rs.size(); j += 2)
        {
            printwriter.println("if(proceso == \"" + (String)rs.elementAt(j) + "\")");
            printwriter.println("{");
            printwriter.println("variable = new Option(\"" + (String)rs.elementAt(j + 1) + "\",\"" + (String)rs.elementAt(j + 1) + "\",\"\",\"\");");
            printwriter.println("document.frmgg.procedimiento.options[indice] = variable;");
            printwriter.println("indice = indice + 1;");
            printwriter.println("}");
        }

        printwriter.println("CambiaIndicador();");
        printwriter.println("}");
        printwriter.println("function CambiaIndicador()");
        printwriter.println("{");
        printwriter.println("var proceso;");
        printwriter.println("var indice;");
        printwriter.println("indice=0;");
        printwriter.println("proceso = document.frmgg.proceso.value;");
        printwriter.println("procedimiento = document.frmgg.procedimiento.value;");
        printwriter.println("document.frmgg.indicador.options.length = 0;");
        i = ADatos.ConsultaDB("select idproc,proceso,procedimiento,definicionrol from gdc.procedimientos order by idproc");
        rs = ADatos.getResult();
        for(int k = 0; k < rs.size(); k += 4)
        {
            printwriter.println("if((proceso == \"" + (String)rs.elementAt(k + 1) + "\") && (procedimiento == \"" + (String)rs.elementAt(k + 2) + "\"))");
            printwriter.println("{");
            printwriter.println("variable = new Option(\"" + (String)rs.elementAt(k + 3) + "\",\"" + ((Integer)rs.elementAt(k)).toString() + "\",\"\",\"\");");
            printwriter.println("document.frmgg.indicador.options[indice] = variable;");
            printwriter.println("indice = indice + 1;");
            printwriter.println("}");
        }

        printwriter.println("}");
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    String UserGDC;
}