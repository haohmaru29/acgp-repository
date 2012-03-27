// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   ver_plan.java

package gdp;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ver_plan extends HttpServlet
{

    public ver_plan()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        EsAdmin = "N";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        String s = "";
        String s1 = "";
        String s2 = "";
        Integer integer = new Integer(0);
        Integer integer1 = new Integer(0);
        String s3 = "";
        Integer integer2 = new Integer(0);
        String s4 = "";
        Integer integer3 = new Integer(0);
        Integer integer4 = new Integer(0);
        Integer integer5 = new Integer(0);
        String s5 = "";
        String s7 = "";
        Integer integer6 = new Integer(0);
        Integer integer8 = new Integer(0);
        String s9 = "";
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s11 = (String)httpsession.getValue("SerapisUser");
        if(s11 != null && s11.length() > 0)
        {
            ADatos.connect();
            
            rs = new Vector();
            
        	String sPath = "";
        	String sqlDirSitio = "select dirsitio from gdc.datosgenerales";
            int iDirSitio = ADatos.ConsultaDB(sqlDirSitio);
            if (iDirSitio>0) {
    	        rs = ADatos.getResult();
    	        if (rs.size()>0) 
    	        	sPath = (String)rs.elementAt(0);
    	        else
    	        	sPath = "C:\\Archivos de programa\\Apache Tomcat 4.0\\webapps\\serapis";
    	        
    	        rs.clear();
        	} 
            
            
            String s12 = httpservletrequest.getParameter("ID");
            String s13 = "select anio,area,fecha_programada,auditor_jefe,fecha_planificada,fecha_realizada,fecha_cerrada from gdc.plan_auditoria where id_plan = " + s12;
            rs = new Vector();
            ADatos.connect();
            int l = ADatos.ConsultaDB(s13);
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                integer1 = (Integer)rs.elementAt(0);
                s3 = (String)rs.elementAt(1);
                integer2 = (Integer)rs.elementAt(2);
                s4 = (String)rs.elementAt(3);
                integer3 = (Integer)rs.elementAt(4);
                integer4 = (Integer)rs.elementAt(5);
                integer5 = (Integer)rs.elementAt(6);
            }
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<form name='frmplan' action='procesa_plan.jsp' method='POST'>");
            printwriter.println("<input name='id_plan' type='hidden' value='" + s12.toString() + "'>");
            printwriter.println("<table border='0' width='80%'  align='center'>");
            printwriter.println("<tr><td width='80%' colspan='4' class='texttituloarea' align='center'>Crear Plan de Auditorias</td></tr>");
            printwriter.println("</table>");
            printwriter.println("<table border='1' width='80%' align='center'>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' align='left' width='25%' valign='top'>A\361o</td>");
            printwriter.println("<td class='textdesttabla' align='left' width='75%' valign='top'><INPUT type='text' name='txtanio' value='" + integer1.toString() + "' maxlength='4' style='WIDTH: 80px' onkeypress='return ValidarCaracteres(4);'></td>");
            printwriter.println("</tr>");
            s13 = "SELECT sigla,descripcion from gdc.procesos order by descripcion";
            l = ADatos.ConsultaDB(s13);
            rs = ADatos.getResult();
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' align='left' valign='top'>\301rea a Auditar</td>");
            printwriter.println("<td class='textdesttabla' align='left' valign='top'>");
            printwriter.println("<select name='selArea'>");
            if(s3.compareTo("00") == 0)
                printwriter.println("   <option value='00' selected>Todos los Procesos</option>");
            else
                printwriter.println("   <option value='00'>Todos los Procesos</option>");
            if(s3.compareTo("01") == 0)
                printwriter.println("   <option value='01' selected>Auditor\355a Externa</option>");
            else
                printwriter.println("   <option value='01'>Auditor\355a Externa</option>");
            for(int i = 0; i < rs.size(); i += 2)
                if(s3.compareTo((String)rs.elementAt(i)) == 0)
                    printwriter.println("   <option value='" + (String)rs.elementAt(i) + "' selected>" + (String)rs.elementAt(i + 1) + "</option>");
                else
                    printwriter.println("   <option value='" + (String)rs.elementAt(i) + "'>" + (String)rs.elementAt(i + 1) + "</option>");

            printwriter.println("</select>");
            printwriter.println("</td></tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' align='left' valign='top'>Fecha Programada</td>");
            printwriter.println("<td class='textdesttabla' align='left' valign='top'><INPUT type='text' name='txtfecha' value='" + AFunc.ConstruyeFecha(integer2.toString(), "/", "dmy") + "' maxlength='10' style='WIDTH: 80px' onkeypress='return ValidarCaracteres(11);'></td>");
            printwriter.println("</tr>");
            s13 = "SELECT login,nombre from sgc.usuarios";
            l = ADatos.ConsultaDB(s13);
            rs = ADatos.getResult();
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' align='left' valign='top'>Auditor Jefe</td>");
            printwriter.println("<td class='textdesttabla' align='left' valign='top'>");
            printwriter.println("<select name='selAuditorJefe'>");
            for(int j = 0; j < rs.size(); j += 2)
                if(s4.compareTo((String)rs.elementAt(j)) == 0)
                    printwriter.println("   <option value='" + (String)rs.elementAt(j) + "' selected>" + (String)rs.elementAt(j + 1) + "</option>");
                else
                    printwriter.println("   <option value='" + (String)rs.elementAt(j) + "'>" + (String)rs.elementAt(j + 1) + "</option>");

            printwriter.println("</select>");
            printwriter.println("</td></tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' align='left' valign='top'>Fecha Planificacion</td>");
            if(integer3.longValue() > 0L)
                printwriter.println("<td class='textdesttabla' align='left' valign='top'><INPUT type='text' name='txtfechaP' value='" + AFunc.ConstruyeFecha(integer3.toString(), "/", "dmy") + "' maxlength='10' style='WIDTH: 80px' onkeypress='return ValidarCaracteres(11);'></td>");
            else
                printwriter.println("<td class='textdesttabla' align='left' valign='top'><INPUT type='text' name='txtfechaP' value='' maxlength='10' style='WIDTH: 80px' onkeypress='return ValidarCaracteres(11);'></td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' align='left' valign='top'>Fecha Realizaci\363n</td>");
            if(integer4.longValue() > 0L)
                printwriter.println("<td class='textdesttabla' align='left' valign='top'><INPUT type='text' name='txtfechaR' value='" + AFunc.ConstruyeFecha(integer4.toString(), "/", "dmy") + "' maxlength='10' style='WIDTH: 80px' onkeypress='return ValidarCaracteres(11);'></td>");
            else
                printwriter.println("<td class='textdesttabla' align='left' valign='top'><INPUT type='text' name='txtfechaR' value='' maxlength='10' style='WIDTH: 80px' onkeypress='return ValidarCaracteres(11);'></td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' align='left' valign='top'>Fecha Realizada</td>");
            if(integer5.longValue() > 0L)
                printwriter.println("<td class='textdesttabla' align='left' valign='top'><INPUT type='text' name='txtfechaC' value='" + AFunc.ConstruyeFecha(integer5.toString(), "/", "dmy") + "' maxlength='10' style='WIDTH: 80px' onkeypress='return ValidarCaracteres(11);'></td>");
            else
                printwriter.println("<td class='textdesttabla' align='left' valign='top'><INPUT type='text' name='txtfechaC' value='' maxlength='10' style='WIDTH: 80px' onkeypress='return ValidarCaracteres(11);'></td>");
            printwriter.println("</tr>");
            printwriter.println("<tr><td colspan='2' class='textdesttabla'>");
            printwriter.println("<table border='1' width='100%' align='center'>");
            s13 = "SELECT id,nombrearchivo,comentario,fechapublica,extension from sad.documentos ";
            s13 = s13 + " where estado = 'A'";
            s13 = s13 + " and id_auditoria = " + s12;
            s13 = s13 + " order by fechapublica desc";
            rs = new Vector();
            rs.clear();
            l = ADatos.ConsultaDB(s13);
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                printwriter.println("<tr>");
                printwriter.println("<td class='texttitulotabla' align='left' width='5%'>&nbsp;</td>");
                printwriter.println("<td class='texttitulotabla' align='left' width='40%'>Documento</td>");
                printwriter.println("<td class='texttitulotabla' align='left' width='40%'>Descripci\363n</td>");
                printwriter.println("<td class='texttitulotabla' align='left' width='15%'>Fecha Publicaci\363n</td>");
                printwriter.println("</tr>");
                for(int k = 0; k < rs.size(); k += 5)
                {
                    Integer integer9 = (Integer)rs.elementAt(k);
                    String s6 = (String)rs.elementAt(k + 1);
                    String s8 = (String)rs.elementAt(k + 2);
                    Integer integer7 = (Integer)rs.elementAt(k + 3);
                    String s10 = (String)rs.elementAt(k + 4);
                    printwriter.println("<tr>");
                    if(AFunc.ExisteArchivo(sPath + "\\images\\ext_" + s10 + ".gif") == 1)
                        printwriter.println("<td class='textdesttabla'> <a href='verregistro.jsp?ID=" + integer9.toString() + "'><IMG src=../images/ext_" + s10 + ".gif width=32 height=32 border=0></a> </td>");
                    else
                        printwriter.println("<td class='textdesttabla'> <a href='verregistro.jsp?ID=" + integer9.toString() + "'><IMG src=../images/extdefault.gif width=32 height=32 border=0></a> </td>");
                    printwriter.println("<td class='textdesttabla' align='left'>" + s6 + "</td>");
                    if(s8.length() > 0)
                        printwriter.println("<td class='textdesttabla' align='left'>" + s8 + "</td>");
                    else
                        printwriter.println("<td class='textdesttabla' align='left'>&nbsp;</td>");
                    printwriter.println("<td class='textdesttabla' align='left'>" + AFunc.ConstruyeFecha(integer7.toString(), "/", "dmy") + "</td>");
                    printwriter.println("</tr>");
                }

            } else
            {
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla' align='left' colspan='3'>No se encontraron registros asociados a la auditor\355a.</td>");
                printwriter.println("</tr>");
            }
            printwriter.println("</table>");
            printwriter.println("</td></tr>");
            printwriter.println("</table>");
            printwriter.println("<br>");
            printwriter.println("<br><center>");
            printwriter.println("<input type='button' name='btnGRabar' value='Grabar' class='fondoinput'  language='javascript' onclick='return Valida();'>");
            printwriter.println("<input type='button' name='btnVolver' value='Volver' class='fondoinput' language='javascript' onclick='window.open(\"plan_auditoria.jsp\",\"datos\");'>");
            printwriter.println("</center>");
            printwriter.println("</form>");
            printwriter.println("<script language='javascript'>");
            printwriter.println("function Valida()");
            printwriter.println("{");
            printwriter.println("   var Anio;");
            printwriter.println("   var Fecha;");
            printwriter.println("   var FechaP;");
            printwriter.println("   var FechaR;");
            printwriter.println("   var FechaC;");
            printwriter.println("   var sigue;");
            printwriter.println("   sigue = true;");
            printwriter.println("   Anio = document.frmplan.txtanio.value;");
            printwriter.println("   Fecha = document.frmplan.txtfecha.value;");
            printwriter.println("   FechaP = document.frmplan.txtfechaP.value;");
            printwriter.println("   FechaR = document.frmplan.txtfechaR.value;");
            printwriter.println("   FechaC = document.frmplan.txtfechaC.value;");
            printwriter.println("if(FechaP.length>0)");
            printwriter.println("{");
            printwriter.println("   if(EsFecha(FechaP)==0)");
            printwriter.println("   {");
            printwriter.println("      sigue = false;");
            printwriter.println("      alert(\"Fecha planificada incorrecta. Formato: dd/mm/yyyy\");");
            printwriter.println("      document.frmplan.txtfechaP.focus();");
            printwriter.println("   }");
            printwriter.println("} ");
            printwriter.println("if((FechaR.length>0) && (sigue == true))");
            printwriter.println("{");
            printwriter.println("   if(EsFecha(FechaR)==1)");
            printwriter.println("   {");
            printwriter.println("      sigue = true;");
            printwriter.println("   }");
            printwriter.println("   else");
            printwriter.println("   {");
            printwriter.println("      sigue = false;");
            printwriter.println("      alert(\"Fecha de realizaci\363n incorrecta. Formato: dd/mm/yyyy\");");
            printwriter.println("      document.frmplan.txtfechaR.focus();");
            printwriter.println("   }");
            printwriter.println("} ");
            printwriter.println("if((FechaC.length>0) && (sigue == true))");
            printwriter.println("{");
            printwriter.println("   if(EsFecha(FechaC)==1)");
            printwriter.println("   {");
            printwriter.println("      sigue = true;");
            printwriter.println("   }");
            printwriter.println("   else");
            printwriter.println("   {");
            printwriter.println("      sigue = false;");
            printwriter.println("      alert(\"Fecha de cierre incorrecta. Formato: dd/mm/yyyy\");");
            printwriter.println("      document.frmplan.txtfechaC.focus();");
            printwriter.println("   }");
            printwriter.println("} ");
            printwriter.println(" if (sigue == true)");
            printwriter.println(" {");
            printwriter.println("   if (Anio.length==4)");
            printwriter.println("   {");
            printwriter.println("      if(Fecha.length==10)");
            printwriter.println("      {");
            printwriter.println("         if(EsFecha(Fecha)==1)");
            printwriter.println("         {");
            printwriter.println("            document.frmplan.submit();");
            printwriter.println("         }");
            printwriter.println("         else");
            printwriter.println("         {");
            printwriter.println("            alert(\"Fecha Inv\341lida. Formato dd/mm/yyyy\");");
            printwriter.println("            document.frmplan.txtfecha.focus();");
            printwriter.println("         }");
            printwriter.println("      }");
            printwriter.println("      else");
            printwriter.println("      {");
            printwriter.println("         alert(\"Fecha Inv\341lida. Formato dd/mm/yyyy\");");
            printwriter.println("         document.frmplan.txtfecha.focus();");
            printwriter.println("      }");
            printwriter.println("   }");
            printwriter.println("   else");
            printwriter.println("   {");
            printwriter.println("      alert(\"A\361o inv\341lido\");");
            printwriter.println("      document.frmplan.txtanio.focus();");
            printwriter.println("   }");
            printwriter.println(" }");
            printwriter.println("}");
            printwriter.println("</script>");
            printwriter.println("</body>");
            printwriter.println("</html>");
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
    String EsAdmin;
}