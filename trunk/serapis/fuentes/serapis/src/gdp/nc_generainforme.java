// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   nc_generainforme.java

package gdp;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class nc_generainforme extends HttpServlet
{

    public nc_generainforme()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        IntMes = new Integer(0);
        IntAnio = new Integer(0);
        StrMes = "";
        StrAnio = "";
        StrProceso = "";
        HayInforme = new Integer(-1);
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
            HayInforme = new Integer(0);
            StrMes = httpservletrequest.getParameter("selMes");
            StrAnio = httpservletrequest.getParameter("selAnio");
            StrProceso = httpservletrequest.getParameter("selProceso");
            AnioActual = AFunc.ObtieneAnio();
            if(StrMes == null || StrMes.length() == 0)
            {
                StrMes = "00";
                int i = AFunc.ObtieneMes();
                if(i < 10)
                    StrMes = "0" + i;
                else
                    StrMes = "" + i;
            }
            if(StrAnio == null || StrAnio.length() == 0)
                StrAnio = "" + AnioActual;
            if(StrProceso == null || StrProceso.length() == 0)
                StrProceso = "";
            IntMes = new Integer(StrMes);
            IntAnio = new Integer(StrAnio);
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<form name='frmnc' action='nc_ver_informe.jsp' method='POST'>");
            printwriter.println("<table border='0' width='95%' align='center'>");
            printwriter.println("<tr><td align='center'  class='texttitulomayor'>Resumen Comit\351 Estrat\351gico</td></tr>");
            printwriter.println("</table>");
            long l = CargaDocumentos(printwriter);
            if(HayInforme.longValue() > 0L)
            {
                printwriter.println("<input type = 'hidden' name='selMes' value='" + StrMes + "'>");
                printwriter.println("<input type = 'hidden' name='selAnio' value='" + StrAnio + "'>");
                printwriter.println("<input type = 'hidden' name='selProceso' value='" + StrProceso + "'>");
            }
            printwriter.println("</form>");
            printwriter.println("<script language='javascript'>");
            if(HayInforme.longValue() > 0L)
            {
                printwriter.println("document.frmnc.action = \"nc_ver_informe.jsp\";");
                printwriter.println("document.frmnc.submit();");
            }
            printwriter.println("function Genera()");
            printwriter.println("{");
            printwriter.println("   document.frmnc.action = \"nc_genera_informe.jsp\";");
            printwriter.println("   document.frmnc.submit();");
            printwriter.println("}");
            printwriter.println("function GeneraInf()");
            printwriter.println("{");
            if(HayInforme.intValue() > 0)
            {
                printwriter.println("if(confirm(\"Ya existe un informe para el per\355odo seleccionado. Desea sobreescribirlo por uno nuevo?\"))");
                printwriter.println("{");
                printwriter.println("   document.frmnc.action = \"nc_informe.jsp\";");
                printwriter.println("   document.frmnc.submit();");
                printwriter.println("}");
            } else
            {
                printwriter.println("   document.frmnc.action = \"nc_informe.jsp\";");
                printwriter.println("   document.frmnc.submit();");
            }
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

    long CargaDocumentos(PrintWriter printwriter)
    {
        rs = new Vector();
        Integer integer = new Integer(0);
        Integer integer1 = new Integer(0);
        String s = "";
        String s1 = "";
        Vector vector = new Vector();
        long l = 0L;
        ADatos.connect();
        if(IntMes.longValue() > 0L && StrProceso.length() > 0)
        {
            String s2 = "SELECT count(*) from gdc.nc_informe where mes = " + StrMes + " and anio = " + StrAnio + " AND proceso = '" + StrProceso + "'";
            int i = ADatos.ConsultaDB(s2);
            rs = ADatos.getResult();
            if(rs.size() > 0)
                HayInforme = (Integer)rs.elementAt(0);
        }
        if(HayInforme.longValue() <= 0L)
        {
            String s3 = "SELECT sigla,descripcion from gdc.procesos order by descripcion";
            int j = ADatos.ConsultaDB(s3);
            rs = ADatos.getResult();
            Vector vector1 = (Vector)rs.clone();
            rs = new Vector();
            s3 = "SELECT nc.id,p.descripcion,nc.nombre nombrenc,nc.fecha,nc.hora,u.nombre nombreuser ";
            s3 = s3 + " from gdc.no_conformidad nc, sgc.usuarios u, gdc.procesos p ";
            s3 = s3 + " where u.login = nc.usuario ";
            s3 = s3 + " and p.sigla = nc.txt_Area ";
            if(IntMes.longValue() > 0L)
            {
                int k = AFunc.ObtieneAnio();
                s3 = s3 + " and (nc.fecha <= " + StrAnio + StrMes + "31) and (nc.fecha >= " + StrAnio + StrMes + "01) ";
            }
            if(StrProceso.length() > 0)
                s3 = s3 + " and nc.txt_Area = '" + StrProceso + "' ";
            s3 = s3 + " order by p.descripcion, id desc ";
            j = ADatos.ConsultaDB(s3);
            rs = ADatos.getResult();
            l = rs.size() / 6;
            printwriter.println("<table border='1' align='center' width='95%'>");
            if(StrProceso.length() > 0)
            {
                printwriter.println("<tr>");
                printwriter.println("<td colspan='2' align='center' class='textdesttabla'><B>No existe informe de acuerdo al filtro seleccionado.<BR>Seleccione un nuevo filtro.</B></td>");
                printwriter.println("</tr>");
            }
            printwriter.println("<tr>");
            printwriter.println("<td width='35%' class='textdesttabla'>Proceso</td>");
            printwriter.println("<td width='65%' class='textdesttabla'>");
            printwriter.println("<select name='selProceso'>");
            for(int i1 = 0; i1 < vector1.size(); i1 += 2)
                if(StrProceso.compareTo((String)vector1.elementAt(i1)) == 0)
                    printwriter.println("<option value='" + (String)vector1.elementAt(i1) + "' selected>" + (String)vector1.elementAt(i1 + 1) + "</option>");
                else
                    printwriter.println("<option value='" + (String)vector1.elementAt(i1) + "'>" + (String)vector1.elementAt(i1 + 1) + "</option>");

            printwriter.println("</select>");
            printwriter.println("</td></tr>");
            printwriter.println("<tr><td class='textdesttabla'>A\361o</td>");
            printwriter.println("<td class='textdesttabla'>");
            printwriter.println("<select name='selAnio'>");
            for(int j1 = 2005; j1 <= AnioActual; j1++)
                if(IntAnio.intValue() == j1)
                    printwriter.println("<option value='" + j1 + "' selected>" + j1 + "</option>");
                else
                    printwriter.println("<option value='" + j1 + "'>" + j1 + "</option>");

            printwriter.println("</select>");
            printwriter.println("</td></tr>");
            printwriter.println("<tr><td class='textdesttabla'>Mes</td>");
            printwriter.println("<td class='textdesttabla'><select name='selMes'>");
            if(IntMes.intValue() == 1)
                printwriter.println("<option value='01' selected>Enero</option>");
            else
                printwriter.println("<option value='01'>Enero</option>");
            if(IntMes.intValue() == 2)
                printwriter.println("<option value='02' selected>Febrero</option>");
            else
                printwriter.println("<option value='02'>Febrero</option>");
            if(IntMes.intValue() == 3)
                printwriter.println("<option value='03' selected>Marzo</option>");
            else
                printwriter.println("<option value='03'>Marzo</option>");
            if(IntMes.intValue() == 4)
                printwriter.println("<option value='04' selected>Abril</option>");
            else
                printwriter.println("<option value='04'>Abril</option>");
            if(IntMes.intValue() == 5)
                printwriter.println("<option value='05' selected>Mayo</option>");
            else
                printwriter.println("<option value='05'>Mayo</option>");
            if(IntMes.intValue() == 6)
                printwriter.println("<option value='06' selected>Junio</option>");
            else
                printwriter.println("<option value='06'>Junio</option>");
            if(IntMes.intValue() == 7)
                printwriter.println("<option value='07' selected>Julio</option>");
            else
                printwriter.println("<option value='07'>Julio</option>");
            if(IntMes.intValue() == 8)
                printwriter.println("<option value='08' selected>Agosto</option>");
            else
                printwriter.println("<option value='08'>Agosto</option>");
            if(IntMes.intValue() == 9)
                printwriter.println("<option value='09' selected>Septiembre</option>");
            else
                printwriter.println("<option value='09'>Septiembre</option>");
            if(IntMes.intValue() == 10)
                printwriter.println("<option value='10' selected>Octubre</option>");
            else
                printwriter.println("<option value='10'>Octubre</option>");
            if(IntMes.intValue() == 11)
                printwriter.println("<option value='11' selected>Noviembre</option>");
            else
                printwriter.println("<option value='11'>Noviembre</option>");
            if(IntMes.intValue() == 12)
                printwriter.println("<option value='12' selected>Diciembre</option>");
            else
                printwriter.println("<option value='12'>Diciembre</option>");
            printwriter.println("</select>");
            printwriter.println("</td></tr>");
            printwriter.println("<tr><td class='textdesttabla' align='center' colspan='2'>");
            printwriter.println("<input type='button' class='fondoinput' name='txtBuscar' value='Generar' language='javascript' onclick='return Genera();'>");
            printwriter.println("</td></tr>");
            printwriter.println("</table>");
        }
        return l;
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    Integer IntMes;
    Integer IntAnio;
    String StrMes;
    String StrAnio;
    String StrProceso;
    int AnioActual;
    Integer HayInforme;
}