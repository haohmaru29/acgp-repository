// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   gestion_procesos.java

package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class gestion_procesos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public gestion_procesos() {
        ADatos =new AccDataBase();
        AFunc = new funciones();
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        String s = "";
        String s2 = "";
        String s4 = "";
        Integer integer = new Integer(0);
        Integer integer1 = new Integer(0);
        String s6 = "";
        String s11 = httpservletrequest.getParameter("TIPO");
        if(s11 == null || s11.length() == 0)
            s11 = "EDITAR";
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        UserGDC = (String)httpsession.getValue("SerapisUser");
        if(UserGDC != null && UserGDC.length() > 0)
        {
            ADatos.connect();
            if(s11.compareTo("GRABAR") == 0)
            {
                String s1 = httpservletrequest.getParameter("doc_gg");
                String s3 = httpservletrequest.getParameter("doc_minuta");
                String s5 = httpservletrequest.getParameter("listadocumentos");
                
                String sC = "select grafico_gestion from  gdc.gestion_procesos";
                int j1 = ADatos.ConsultaDB(sC);
                rs = ADatos.getResult();
                j1 = (rs!=null?rs.size():0);
                if (j1>0) {
	                String s7 = "update gdc.gestion_procesos set ";
	                s7 = s7 + "grafico_gestion = " + s1 + ",";
	                s7 = s7 + "minuta = " + s3 + ",";
	                s7 = s7 + "doc_auditoria = '" + s5 + "'";
	                j1 = ADatos.ModificaDB(s7);
                } else {
                	String s7 = "insert into gdc.gestion_procesos(grafico_gestion, minuta, doc_auditoria)";
                	s7 += " values('" + s1 + "','" + s3 + "','" + s5 + "')";
                	j1 = ADatos.ModificaDB(s7);
                }
                	
                
            }
            String s8 = "select grafico_gestion, minuta, doc_auditoria from gdc.gestion_procesos";
            int k1 = ADatos.ConsultaDB(s8);
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                integer = (Integer)rs.elementAt(0);
                integer1 = (Integer)rs.elementAt(1);
                s6 = (String)rs.elementAt(2);
            }
            rs = new Vector();
            s8 = "SELECT dc.id,p.descripcion proceso, dc.descripcion documento ";
            s8 = s8 + " from gdc.procesos p, gdc.documentoscalidad dc ";
            s8 = s8 + " where p.sigla = dc.proceso ";
            s8 = s8 + " ORDER by p.descripcion,dc.descripcion ";
            k1 = ADatos.ConsultaDB(s8);
            rs = ADatos.getResult();
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<form name='frmgp' action='gestion_procesos.jsp?TIPO=GRABAR' method='post'>");
            printwriter.println("<table border='1' width='95%' align='center'>");
            printwriter.println("<tr><td width='95%' class='texttituloarea' colspan='2'>Gr\341ficos de Gesti\363n. Identificaci\363n de Registros.</td></tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='25%'><B>Gr\341ficos de Gesti\363n</B></td>");
            printwriter.println("<td class='textdesttabla' width='75%'>");
            printwriter.println("<select name='doc_gg'>");
            for(int i = 0; i < rs.size(); i += 3)
                if(integer.longValue() > 0L)
                {
                    if(((Integer)rs.elementAt(i)).longValue() == integer.longValue())
                        printwriter.println("<option value='" + ((Integer)rs.elementAt(i)).toString() + "' selected>" + (String)rs.elementAt(i + 1) + " - " + (String)rs.elementAt(i + 2) + "</option>");
                    else
                        printwriter.println("<option value='" + ((Integer)rs.elementAt(i)).toString() + "'>" + (String)rs.elementAt(i + 1) + " - " + (String)rs.elementAt(i + 2) + "</option>");
                } else
                {
                    printwriter.println("<option value='" + ((Integer)rs.elementAt(i)).toString() + "'>" + (String)rs.elementAt(i + 1) + " - " + (String)rs.elementAt(i + 2) + "</option>");
                }

            printwriter.println("</select>");
            printwriter.println("</td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'><B>Minuta Reuni\363n Comit\351</B></td>");
            printwriter.println("<td class='textdesttabla'>");
            printwriter.println("<select name='doc_minuta'>");
            for(int j = 0; j < rs.size(); j += 3)
                if(integer1.longValue() > 0L)
                {
                    if(((Integer)rs.elementAt(j)).longValue() == integer1.longValue())
                        printwriter.println("<option value='" + ((Integer)rs.elementAt(j)).toString() + "' selected>" + (String)rs.elementAt(j + 1) + " - " + (String)rs.elementAt(j + 2) + "</option>");
                    else
                        printwriter.println("<option value='" + ((Integer)rs.elementAt(j)).toString() + "'>" + (String)rs.elementAt(j + 1) + " - " + (String)rs.elementAt(j + 2) + "</option>");
                } else
                {
                    printwriter.println("<option value='" + ((Integer)rs.elementAt(j)).toString() + "'>" + (String)rs.elementAt(j + 1) + " - " + (String)rs.elementAt(j + 2) + "</option>");
                }

            printwriter.println("</select>");
            printwriter.println("</td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='texttitulotabla' colspan='2'>Registros de Auditor\355a</td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' colspan='2'>");
            printwriter.println("<table border='1' width='95%' align='center'>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' align='center' width='49%'><B>Registros</B></td>");
            printwriter.println("<td class='textdesttabla' align='center' width='2%'>&nbsp;</td>");
            printwriter.println("<td class='textdesttabla' align='center' width='49%'><B>Seleccionados</B></td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' align='center'>");
            printwriter.println("<select name='documentos' multiple size='15' style='WIDTH: 320px'>");
            if(s6.length() > 0)
            {
                rs = new Vector();
                String s9 = "SELECT dc.id,p.descripcion proceso, dc.descripcion documento ";
                s9 = s9 + " from gdc.procesos p, gdc.documentoscalidad dc ";
                s9 = s9 + " where p.sigla = dc.proceso ";
                s9 = s9 + " and dc.id NOT IN (" + s6 + ")";
                s9 = s9 + " ORDER by p.descripcion,dc.descripcion ";
                int l1 = ADatos.ConsultaDB(s9);
                rs = ADatos.getResult();
                for(int k = 0; k < rs.size(); k += 3)
                    printwriter.println("<option value='" + ((Integer)rs.elementAt(k)).toString() + "'>" + (String)rs.elementAt(k + 1) + " - " + (String)rs.elementAt(k + 2) + "</option>");

            } else
            {
                for(int l = 0; l < rs.size(); l += 3)
                    printwriter.println("<option value='" + ((Integer)rs.elementAt(l)).toString() + "'>" + (String)rs.elementAt(l + 1) + " - " + (String)rs.elementAt(l + 2) + "</option>");

            }
            printwriter.println("</td>");
            printwriter.println("<td class='textdesttabla'  align='center'>");
            printwriter.println("<input class='fondoinput' type='button' style='WIDTH: 30px' name='btnmas' value='>>' language='javascript' onclick='return Agregar();'><BR><BR><BR><input class='fondoinput' type='button' style='WIDTH: 30px' name='btnmenos' value='<<' language='javascript' onclick='return Quitar();'>");
            printwriter.println("</td>");
            printwriter.println("<td class='textdesttabla'  align='center'>");
            printwriter.println("<select name='auditoria' multiple size='15' style='WIDTH: 320px'>");
            if(s6.length() > 0)
            {
                rs = new Vector();
                String s10 = "SELECT dc.id,p.descripcion proceso, dc.descripcion documento ";
                s10 = s10 + " from gdc.procesos p, gdc.documentoscalidad dc ";
                s10 = s10 + " where p.sigla = dc.proceso ";
                s10 = s10 + " and dc.id IN (" + s6 + ")";
                s10 = s10 + " ORDER by p.descripcion,dc.descripcion ";
                int i2 = ADatos.ConsultaDB(s10);
                rs = ADatos.getResult();
                for(int i1 = 0; i1 < rs.size(); i1 += 3)
                    printwriter.println("<option value='" + ((Integer)rs.elementAt(i1)).toString() + "'>" + (String)rs.elementAt(i1 + 1) + " - " + (String)rs.elementAt(i1 + 2) + "</option>");

            }
            printwriter.println("</td>");
            printwriter.println("</tr>");
            printwriter.println("</td>");
            printwriter.println("</tr>");
            printwriter.println("</table>");
            printwriter.println("<input name='listadocumentos' value='' type='hidden'>");
            printwriter.println("<tr><td width='95%' class='textdesttabla' align='center' colspan='2'><input class='fondoinput' type='button' name='btnmas' value='Aceptar' onclick='return Grabar();'></td></tr>");
            printwriter.println("</table>");
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

    void CargaScript(PrintWriter printwriter)
    {
        printwriter.println("function Grabar()");
        printwriter.println("{");
        printwriter.println("    document.frmgp.listadocumentos.value =  getLista(document.frmgp.auditoria);");
        printwriter.println("    if(document.frmgp.listadocumentos.value != \"\")");
        printwriter.println("    {");
        printwriter.println("       document.frmgp.submit();");
        printwriter.println("    }");
        printwriter.println("    else");
        printwriter.println("    {");
        printwriter.println("       alert(\"Debe seleccionar documentos de auditor\355a\");");
        printwriter.println("    }");
        printwriter.println("}");
        printwriter.println("function getLista(obj)");
        printwriter.println("{");
        printwriter.println("\tvar valor=\"\";");
        printwriter.println("\tif (!obj)");
        printwriter.println("\t\treturn \"\";");
        printwriter.println("\tif (!obj.length)");
        printwriter.println("\t\treturn \"\";");
        printwriter.println("\telse ");
        printwriter.println("   {");
        printwriter.println("\t\tfor (i=0;i<obj.length;i++)");
        printwriter.println("        {");
        printwriter.println("\t\t\ttexto= obj[i].text.split(\"-\")[1];");
        printwriter.println("\t\t\tif(valor.length>0)");
        printwriter.println("\t\t\t   valor += \",\" + obj[i].value;");
        printwriter.println("\t\t\telse");
        printwriter.println("\t\t\t   valor += obj[i].value;");
        printwriter.println("\t\t }");
        printwriter.println("\t}");
        printwriter.println("\treturn valor;");
        printwriter.println("}");
        printwriter.println("function pasarItems(objOrigen,objDestino)");
        printwriter.println("{");
        printwriter.println("\tfor (i=0;i < objOrigen.length;i++)");
        printwriter.println("    {");
        printwriter.println("\t\tif (objOrigen.options[i].selected)");
        printwriter.println("        {");
        printwriter.println("\t\t\t objDestino[objDestino.length] = new Option(Trim(objOrigen.options[i].text),objOrigen.options[i].value);");
        printwriter.println("\t\t\t objOrigen.options[i]=null;");
        printwriter.println("\t\t\t i=-1;");
        printwriter.println("\t\t}");
        printwriter.println("\t}");
        printwriter.println("}");
        printwriter.println("function Agregar()");
        printwriter.println("{");
        printwriter.println("   form = document.frmgp;");
        printwriter.println("   pasarItems(form.documentos,form.auditoria)");
        printwriter.println("}");
        printwriter.println("function Quitar()");
        printwriter.println("{");
        printwriter.println("\tform = document.frmgp;");
        printwriter.println("\tpasarItems(form.auditoria,form.documentos)");
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
    String UserGDC;
}