// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   gg_grafico_gestion.java

package gdp;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class gg_grafico_gestion extends HttpServlet
{

    public gg_grafico_gestion()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        EsAdmin = "N";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        Integer integer = new Integer(0);
        Integer integer2 = new Integer(0);
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s1 = (String)httpsession.getValue("SerapisUser");
        if(s1 != null && s1.length() > 0)
        {
            ADatos.connect();
            int l = AFunc.ObtieneAnio();
            String s2 = httpservletrequest.getParameter("selAnio");
            String s3 = httpservletrequest.getParameter("selMes");
            if(s2 == null || s2.length() == 0)
                s2 = "" + AFunc.ObtieneAnio();
            if(s3 == null || s3.length() == 0)
                s3 = "" + AFunc.ObtieneMes();
            Integer integer1 = new Integer(s2);
            Integer integer3 = new Integer(s3);
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<form name='frmplan' action='gg_grafico_gestion.jsp' method='POST'>");
            printwriter.println("<table border='0' width='80%'  align='center'>");
            printwriter.println("<tr><td width='80%' colspan='5' class='texttituloarea' align='center'>Gr\341ficos de Gesti\363n</td></tr>");
            printwriter.println("</table>");
            printwriter.println("<table border='1' width='80%' align='center'>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='15%'>A\361o</td>");
            printwriter.println("<td class='textdesttabla' width='85%'>");
            printwriter.println("<select name='selAnio'>");
            for(int i = 2005; i <= l; i++)
                if(integer1.intValue() == i)
                    printwriter.println("<option value='" + i + "' selected>" + i + "</option>");
                else
                    printwriter.println("<option value='" + i + "'>" + i + "</option>");

            printwriter.println("</select>");
            printwriter.println("</td></tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='15%'>Mes</td>");
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
            printwriter.println("&nbsp;&nbsp;<input type='submit' name='btnBuscar' value='Buscar' class='fondoinput'  language='javascript'>");
            printwriter.println("</td></tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' colspan='2' align='center'>");
            rs = new Vector();
            String s = "select gg.idgg,gg.proceso,pp.descripcion,gg.procedimiento_indicador,p.definicionrol,gg.signo,gg.gap";
            s = s + " from gdc.graficos_gestion gg, gdc.procedimientos p, gdc.procesos pp";
            s = s + " where p.idproc = gg.procedimiento_indicador";
            s = s + " and p.proceso =gg.proceso and pp.sigla = gg.proceso and pp.sigla = p.proceso";
            s = s + " and anio = " + integer1.intValue();
            s = s + " and mes = " + integer3.intValue();
            s = s + " order by gg.idgg";
            int k = ADatos.ConsultaDB(s);
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                printwriter.println("<table border='1' width='100%'  align='center'>");
                printwriter.println("<tr>");
                printwriter.println("<td class='texttitulotabla' align='center' width='25%'>Proceso</td>");
                printwriter.println("<td class='texttitulotabla' align='center' width='60%'>Indicador</td>");
                printwriter.println("<td class='texttitulotabla' align='center' width='15%'>Valor Obtenido</td>");
                printwriter.println("</tr>");
                for(int j = 0; j < rs.size(); j += 7)
                {
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla' align='left'>" + (String)rs.elementAt(j + 2) + "</td>");
                    printwriter.println("<td class='textdesttabla' align='left'>" + (String)rs.elementAt(j + 4) + "</td>");
                    printwriter.println("<td class='textdesttabla' align='left'>" + (String)rs.elementAt(j + 5) + " " + ((Double)rs.elementAt(j + 6)).toString() + "</td>");
                    printwriter.println("</tr>");
                }

                printwriter.println("</table>");
            }
            printwriter.println("</td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' colspan='2' align='center'>");
            printwriter.println("<input type='button' name='btnNuevo' value='Nuevo' class='fondoinput'  language='javascript' onclick='window.open(\"gg_subir.jsp\",\"datos\")'>");
            printwriter.println("&nbsp;&nbsp;<input type='button' name='btnVer' value='Ver Registros' class='fondoinput'  language='javascript' onclick='window.open(\"gg_verregistros.jsp\",\"datos\")'>");
            printwriter.println("</td></tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' colspan='2' align='left'>");
            printwriter.println("Si desea modificar un valor, ingreselo como un nuevo elemento.");
            printwriter.println("</td></tr>");
            printwriter.println("</table>");
            printwriter.println("</form>");
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