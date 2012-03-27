// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   gg_tablero_control.java

package gdp;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class gg_tablero_control extends HttpServlet
{

    public gg_tablero_control()
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
        Double double1 = new Double(0.0D);
        Double double3 = new Double(0.0D);
        Double double7 = new Double(0.0D);
        Integer integer4 = new Integer(0);
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s12 = (String)httpsession.getValue("SerapisUser");
        if(s12 != null && s12.length() > 0)
        {
            ADatos.connect();
            int k1 = AFunc.ObtieneAnio();
            String s13 = httpservletrequest.getParameter("selAnio");
            String s14 = httpservletrequest.getParameter("selMes");
            if(s13 == null || s13.length() == 0)
                s13 = "" + AFunc.ObtieneAnio();
            if(s14 == null || s14.length() == 0)
                s14 = "" + AFunc.ObtieneMes();
            Integer integer1 = new Integer(s13);
            Integer integer3 = new Integer(s14);
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<form name='frmplan' action='gg_tablero_control.jsp' method='POST'>");
            printwriter.println("<table border='1' width='95%'  align='center'>");
            printwriter.println("<tr><td width='80%' colspan='2' class='texttituloarea' align='center'>Tablero de Control</td></tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='15%'>A\361o</td>");
            printwriter.println("<td class='textdesttabla' width='85%'>");
            printwriter.println("<select name='selAnio'>");
            for(int i = 2005; i <= k1; i++)
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
            printwriter.println("</td></tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' colspan='2' align='center'>");
            printwriter.println("<input type='submit' name='btnBuscar' value='Buscar' class='fondoinput'  language='javascript'>");
            printwriter.println("&nbsp;&nbsp;<input type='button' name='btnNuevo' value='Subir' class='fondoinput'  language='javascript' onclick='window.open(\"gg_subir.jsp\",\"datos\")'>");
            printwriter.println("</td></tr>");
            printwriter.println("</table>");
            printwriter.println("<table border='1' width='95%'  align='center'>");
            printwriter.println("<tr><td width='100%' colspan='8' class='texttituloarea' align='center'>Tablero de Control</td></tr>");
            rs = new Vector();
            String s = "select count(*) from gdc.tablero_control where anio = " + integer1.intValue();
            s = s + " and mes = " + integer3.intValue();
            int l = ADatos.ConsultaDB(s);
            rs = ADatos.getResult();
            if(rs.size() > 0)
                integer4 = (Integer)rs.elementAt(0);
            if(integer4.longValue() > 0L)
            {
                String s1 = "select anio,mes,campo1,campo2,campo3,campo4,campo5,campo6,campo7,campo8,color,fecha from gdc.tablero_control";
                s1 = s1 + " where anio = " + integer1.intValue();
                s1 = s1 + " and mes = " + integer3.intValue();
                int i1 = ADatos.ConsultaDB(s1);
                rs = ADatos.getResult();
                Integer integer5 = new Integer(0);
                String s15 = "";
                integer5 = (Integer)rs.elementAt(1);
                String s16 = (String)rs.elementAt(11);
                switch(integer5.intValue())
                {
                case 1: // '\001'
                    s15 = "Enero";
                    break;

                case 2: // '\002'
                    s15 = "Febrero";
                    break;

                case 3: // '\003'
                    s15 = "Marzo";
                    break;

                case 4: // '\004'
                    s15 = "Abril";
                    break;

                case 5: // '\005'
                    s15 = "Mayo";
                    break;

                case 6: // '\006'
                    s15 = "Junio";
                    break;

                case 7: // '\007'
                    s15 = "Julio";
                    break;

                case 8: // '\b'
                    s15 = "Agosto";
                    break;

                case 9: // '\t'
                    s15 = "Septiembre";
                    break;

                case 10: // '\n'
                    s15 = "Octubre";
                    break;

                case 11: // '\013'
                    s15 = "Noviembre";
                    break;

                case 12: // '\f'
                    s15 = "Dociembre";
                    break;
                }
                printwriter.println("<tr><td class='texttitulotabla' align='left' colspan='2'>Per\355odo</td>");
                printwriter.println("<td class='textdesttabla' align='left' colspan='6'>" + s15 + " " + ((Integer)rs.elementAt(0)).toString() + "</td></tr>");
                printwriter.println("<tr><td class='texttitulotabla' align='left' colspan='2'>Revisi\363n</td>");
                printwriter.println("<td class='textdesttabla' align='left' colspan='6'>" + AFunc.ConstruyeFecha(s16, "/", "dmy") + "</td></tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='texttitulotabla' align='center' width='2%'>&nbsp;</td>");
                printwriter.println("<td class='texttitulotabla' align='center' width='25%'>Procedimiento</td>");
                printwriter.println("<td class='texttitulotabla' align='center' width='25%'>Indicador&nbsp;de&nbsp;Gesti\363n</td>");
                printwriter.println("<td class='texttitulotabla' align='center' width='8%'>Meta</td>");
                printwriter.println("<td class='texttitulotabla' align='center' width='10%'>Valor<BR>Obtenido</td>");
                printwriter.println("<td class='texttitulotablarojo' align='center' width='10%'>Malo</td>");
                printwriter.println("<td class='texttitulotablaamarillo' align='center' width='10%'>Regular</td>");
                printwriter.println("<td class='texttitulotablaverde' align='center' width='10%'>Bueno</td>");
                printwriter.println("</tr>");
                int j = 0;
                for(int l1 = 1; j < rs.size(); l1++)
                {
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla' align='left'>" + (String)rs.elementAt(j + 2) + "</td>");
                    printwriter.println("<td class='textdesttabla' align='left'>" + (String)rs.elementAt(j + 3) + "</td>");
                    printwriter.println("<td class='textdesttabla' align='left'>" + (String)rs.elementAt(j + 4) + "</td>");
                    printwriter.println("<td class='textdesttabla' align='left'>" + (String)rs.elementAt(j + 5) + "</td>");
                    printwriter.println("<td class='textdesttabla" + (String)rs.elementAt(j + 10) + "' align='left'>" + (String)rs.elementAt(j + 6) + "</td>");
                    printwriter.println("<td class='textdesttabla' align='left'>" + (String)rs.elementAt(j + 7) + "</td>");
                    printwriter.println("<td class='textdesttabla' align='left'>" + (String)rs.elementAt(j + 8) + "</td>");
                    printwriter.println("<td class='textdesttabla' align='left'>" + (String)rs.elementAt(j + 9) + "</td>");
                    printwriter.println("<input type='hidden' name='campo1" + l1 + "' value='" + (String)rs.elementAt(j + 2) + "'>");
                    printwriter.println("<input type='hidden' name='campo2" + l1 + "' value='" + (String)rs.elementAt(j + 3) + "'>");
                    printwriter.println("<input type='hidden' name='campo3" + l1 + "' value='" + (String)rs.elementAt(j + 4) + "'>");
                    printwriter.println("<input type='hidden' name='campo4" + l1 + "' value='" + (String)rs.elementAt(j + 5) + "'>");
                    printwriter.println("<input type='hidden' name='campo5" + l1 + "' value='" + (String)rs.elementAt(j + 6) + "'>");
                    printwriter.println("<input type='hidden' name='campo6" + l1 + "' value='" + (String)rs.elementAt(j + 7) + "'>");
                    printwriter.println("<input type='hidden' name='campo7" + l1 + "' value='" + (String)rs.elementAt(j + 8) + "'>");
                    printwriter.println("<input type='hidden' name='campo8" + l1 + "' value='" + (String)rs.elementAt(j + 9) + "'>");
                    printwriter.println("<input type='hidden' name='campocolor" + l1 + "' value='" + (String)rs.elementAt(j + 10) + "'>");
                    printwriter.println("</tr>");
                    j += 12;
                }

                printwriter.println("<input type='hidden' name='nrofilas' value='" + rs.size() / 12 + "'>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla' align='center' colspan='8'>");
                printwriter.println("<input name='Grabar' value='Grabar' type='button' class='fondoinput' language='javascript' onclick='return Graba();'>");
                printwriter.println("</td></tr>");
                printwriter.println("</table>");
            } else
            {
                printwriter.println("<tr>");
                printwriter.println("<td class='texttitulotabla' align='center' width='2%'>&nbsp;</td>");
                printwriter.println("<td class='texttitulotabla' align='center' width='25%'>Procedimiento</td>");
                printwriter.println("<td class='texttitulotabla' align='center' width='25%'>Indicador&nbsp;de&nbsp;Gesti\363n</td>");
                printwriter.println("<td class='texttitulotabla' align='center' width='8%'>Meta</td>");
                printwriter.println("<td class='texttitulotabla' align='center' width='10%'>Valor<BR>Obtenido</td>");
                printwriter.println("<td class='texttitulotablarojo' align='center' width='10%'>Malo</td>");
                printwriter.println("<td class='texttitulotablaamarillo' align='center' width='10%'>Regular</td>");
                printwriter.println("<td class='texttitulotablaverde' align='center' width='10%'>Bueno</td>");
                printwriter.println("</tr>");
                rs = new Vector();
                String s2 = "select gg.idgg,gg.proceso,pp.descripcion,gg.procedimiento_indicador,p.definicionrol,gg.signo,gg.gap,";
                s2 = s2 + " p.indicador,p.bueno_minimo, p.bueno_maximo, p.regular_minimo, p.regular_maximo, p.malo_minimo, p.malo_maximo,p.rol";
                s2 = s2 + " from gdc.graficos_gestion gg, gdc.procedimientos p, gdc.procesos pp";
                s2 = s2 + " where p.idproc = gg.procedimiento_indicador";
                s2 = s2 + " and p.proceso =gg.proceso and pp.sigla = gg.proceso and pp.sigla = p.proceso";
                s2 = s2 + " and anio = " + integer1.intValue();
                s2 = s2 + " and mes = " + integer3.intValue();
                s2 = s2 + " order by gg.idgg";
                int j1 = ADatos.ConsultaDB(s2);
                rs = ADatos.getResult();
                int k = 0;
                for(int i2 = 1; k < rs.size(); i2++)
                {
                    String s10 = (String)rs.elementAt(k + 5);
                    String s3 = ((Double)rs.elementAt(k + 6)).toString();
                    String s4 = ((Integer)rs.elementAt(k + 8)).toString();
                    String s7 = ((Integer)rs.elementAt(k + 9)).toString();
                    Double double2 = new Double(s10 + s3);
                    Double double4 = new Double(s4);
                    Double double8 = new Double(s7);
                    String s11 = "rojo";
                    if(double2.doubleValue() <= double8.doubleValue() && double2.doubleValue() >= double4.doubleValue())
                    {
                        s11 = "verde";
                    } else
                    {
                        String s5 = ((Integer)rs.elementAt(k + 10)).toString();
                        String s8 = ((Integer)rs.elementAt(k + 11)).toString();
                        Double double5 = new Double(s5);
                        Double double9 = new Double(s8);
                        if(double2.doubleValue() <= double9.doubleValue() && double2.doubleValue() >= double5.doubleValue())
                        {
                            s11 = "amarillo";
                        } else
                        {
                            String s6 = ((Integer)rs.elementAt(k + 12)).toString();
                            String s9 = ((Integer)rs.elementAt(k + 13)).toString();
                            Double double6 = new Double(s6);
                            Double double10 = new Double(s9);
                            if(double2.doubleValue() <= double10.doubleValue() && double2.doubleValue() >= double6.doubleValue())
                                s11 = "rojo";
                        }
                    }
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla' align='left'>" + i2 + "</td>");
                    printwriter.println("<td class='textdesttabla' align='left'>" + (String)rs.elementAt(k + 2) + "</td>");
                    printwriter.println("<td class='textdesttabla' align='left'>" + (String)rs.elementAt(k + 4) + "</td>");
                    printwriter.println("<td class='textdesttabla' align='left'>" + (String)rs.elementAt(k + 14) + " " + (String)rs.elementAt(k + 7) + "</td>");
                    printwriter.println("<td class='textdesttabla" + s11 + "' align='left'>" + (String)rs.elementAt(k + 5) + " " + ((Double)rs.elementAt(k + 6)).toString() + " " + (String)rs.elementAt(k + 7) + "</td>");
                    printwriter.println("<td class='textdesttabla' align='left'>" + ((Integer)rs.elementAt(k + 12)).toString() + "-" + ((Integer)rs.elementAt(k + 13)).toString() + "</td>");
                    printwriter.println("<td class='textdesttabla' align='left'>" + ((Integer)rs.elementAt(k + 10)).toString() + "-" + ((Integer)rs.elementAt(k + 11)).toString() + "</td>");
                    printwriter.println("<td class='textdesttabla' align='left'>" + ((Integer)rs.elementAt(k + 8)).toString() + "-" + ((Integer)rs.elementAt(k + 9)).toString() + "</td>");
                    printwriter.println("<input type='hidden' name='campo1" + i2 + "' value='" + i2 + "'>");
                    printwriter.println("<input type='hidden' name='campo2" + i2 + "' value='" + (String)rs.elementAt(k + 2) + "'>");
                    printwriter.println("<input type='hidden' name='campo3" + i2 + "' value='" + (String)rs.elementAt(k + 4) + "'>");
                    printwriter.println("<input type='hidden' name='campo4" + i2 + "' value='" + (String)rs.elementAt(k + 14) + " " + (String)rs.elementAt(k + 7) + "'>");
                    printwriter.println("<input type='hidden' name='campo5" + i2 + "' value='" + (String)rs.elementAt(k + 5) + " " + ((Double)rs.elementAt(k + 6)).toString() + " " + (String)rs.elementAt(k + 7) + "'>");
                    printwriter.println("<input type='hidden' name='campo6" + i2 + "' value='" + ((Integer)rs.elementAt(k + 12)).toString() + "-" + ((Integer)rs.elementAt(k + 13)).toString() + "'>");
                    printwriter.println("<input type='hidden' name='campo7" + i2 + "' value='" + ((Integer)rs.elementAt(k + 10)).toString() + "-" + ((Integer)rs.elementAt(k + 11)).toString() + "'>");
                    printwriter.println("<input type='hidden' name='campo8" + i2 + "' value='" + ((Integer)rs.elementAt(k + 8)).toString() + "-" + ((Integer)rs.elementAt(k + 9)).toString() + "'>");
                    printwriter.println("<input type='hidden' name='campocolor" + i2 + "' value='" + s11 + "'>");
                    printwriter.println("</tr>");
                    k += 15;
                }

                printwriter.println("<input type='hidden' name='nrofilas' value='" + rs.size() / 15 + "'>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla' align='center' colspan='8'>");
                printwriter.println("<input name='Grabar' value='Grabar' type='button' class='fondoinput' language='javascript' onclick='return Graba();'>");
                printwriter.println("</td></tr>");
                printwriter.println("</table>");
            }
            printwriter.println("</form>");
            printwriter.println("<script language='javascript'>");
            printwriter.println("function Graba()");
            printwriter.println("{");
            printwriter.println("   if(confirm(\"Esta seguro que desea guardar el tablero de control\"))");
            printwriter.println("   {");
            printwriter.println("      document.frmplan.action=\"gg_tablero_procesa.jsp\";");
            printwriter.println("      document.frmplan.submit();");
            printwriter.println("   }");
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