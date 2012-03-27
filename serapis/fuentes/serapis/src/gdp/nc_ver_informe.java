// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   nc_ver_informe.java

package gdp;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class nc_ver_informe extends HttpServlet
{

    public nc_ver_informe()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        StrMes = "";
        StrAnio = "";
        StrProceso = "";
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
            StrMes = httpservletrequest.getParameter("selMes");
            StrAnio = httpservletrequest.getParameter("selAnio");
            StrProceso = httpservletrequest.getParameter("selProceso");
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<form name='frmnc' action = 'nc.jsp' method='POST'>");
            printwriter.println("<input type='hidden' name='selMes' value='" + StrMes + "'>");
            printwriter.println("<input type='hidden' name='selAnio' value='" + StrAnio + "'>");
            printwriter.println("<input type='hidden' name='selProceso' value='" + StrProceso + "'>");
            int i = CargaNC(printwriter);
            if(i > 0)
            {
                printwriter.println("<BR><center><input type='submit' name='BtnVolver' value='Volver' class='fondoinput' language='javascript'>&nbsp;&nbsp;<input type='button' name='BtnImprimir' value='Imprimir' class='fondoinput' language='javascript' onclick='window.print()'></center></td>");
                printwriter.println("</form>");
                printwriter.println("<script language='javascript'>");
                printwriter.println("function Grabar()");
                printwriter.println("{");
                printwriter.println("   document.frmnc.action=\"nc_graba_informe.jsp\";");
                printwriter.println("   document.frmnc.submit();");
                printwriter.println("}");
                printwriter.println("function Volver()");
                printwriter.println("{");
                printwriter.println("   document.frmnc.action=\"nc.jsp\";");
                printwriter.println("   document.frmnc.submit();");
                printwriter.println("}");
                printwriter.println("</script>");
            } else
            {
                printwriter.println("<TABLE border='1' align='center' width='80%'>");
                printwriter.println("<tr>");
                printwriter.println("<td colspan='2' class='texttitulotabla'>Resumen Comit\351 Estrat\351gico</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla' colspan='2'>No se ha generado informe.</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla' colspan='2' align='center'><input type='button' name='BtnVolver' value='Volver' class='fondoinput' language='javascript' onclick='window.open(\"nc_genera_informe.jsp\",\"datos\");'></td>");
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

    int CargaNC(PrintWriter printwriter)
    {
        String s = "";
        String s1 = "";
        String s2 = "";
        String s3 = "";
        String s4 = "";
        Integer integer = new Integer(0);
        Integer integer1 = new Integer(0);
        String s5 = "";
        Integer integer2 = new Integer(0);
        ADatos.connect();
        rs = new Vector();
        s3 = StrProceso;
        s4 = StrMes;
        s = "select descripcion from gdc.procesos where sigla = '" + StrProceso + "'";
        int i = ADatos.ConsultaDB(s);
        rs = ADatos.getResult();
        if(rs.size() > 0)
            s3 = (String)rs.elementAt(0);
        integer = new Integer(StrMes);
        switch(integer.intValue())
        {
        case 1: // '\001'
            s4 = "Enero";
            break;

        case 2: // '\002'
            s4 = "Febrero";
            break;

        case 3: // '\003'
            s4 = "Marzo";
            break;

        case 4: // '\004'
            s4 = "Abril";
            break;

        case 5: // '\005'
            s4 = "Mayo";
            break;

        case 6: // '\006'
            s4 = "Junio";
            break;

        case 7: // '\007'
            s4 = "Julio";
            break;

        case 8: // '\b'
            s4 = "Agosto";
            break;

        case 9: // '\t'
            s4 = "Septiembre";
            break;

        case 10: // '\n'
            s4 = "Octubre";
            break;

        case 11: // '\013'
            s4 = "Noviembre";
            break;

        case 12: // '\f'
            s4 = "Diciembre";
            break;
        }
        s = "SELECT campo1,campo2,campo3,campo4,campo5,campo6,campo7,campo8,campo9,campo10,campo11,campo12 ";
        s = s + " from gdc.nc_informe where  ";
        s = s + " mes = " + StrMes;
        s = s + " and anio = " + StrAnio;
        s = s + " and proceso = '" + StrProceso + "'";
        s = s + " order by idinforme ";
        rs = new Vector();
        i = ADatos.ConsultaDB(s);
        rs = ADatos.getResult();
        Vector vector = new Vector();
        vector = (Vector)rs.clone();
        Porcentaje = new Vector();
        if(vector.size() > 0)
        {
            printwriter.println("<table border='1' align='center' width='95%'>");
            printwriter.println("<tr><td class='texttituloarea' width='100%' colspan='12'>Informe Comit\351 Estrat\351gico</td></tr>");
            printwriter.println("<tr>");
            printwriter.println("   <td class='textdesttabla' align='left' valign='top' colspan='2'><B>Proceso</B></td>");
            printwriter.println("   <td class='textdesttabla' align='left' valign='top' colspan='10'>" + s3 + "</td>");
            printwriter.println("<tr>");
            printwriter.println("<tr>");
            printwriter.println("   <td class='textdesttabla' align='left' valign='top' colspan='2'><B>Per\355odo</B></td>");
            printwriter.println("   <td class='textdesttabla' align='left' valign='top' colspan='10'>" + s4 + " / " + StrAnio + "</td>");
            printwriter.println("<tr>");
            printwriter.println("<td class='texttitulotabla' align='left' valign='top'>N\260&nbsp;&nbsp;</td>");
            printwriter.println("<td class='texttitulotabla' align='left' valign='top'>Fecha<BR>Cursada</td>");
            printwriter.println("<td class='texttitulotabla' align='left' valign='top'>Descripci\363n&nbsp;de&nbsp;la&nbsp;No&nbsp;Conformidad<BR>Proceso Afectado /Sugerencia</td>");
            printwriter.println("<td class='texttitulotabla' align='left' valign='top'>\277Corregida?</td>");
            printwriter.println("<td class='texttitulotabla' align='left' valign='top'>Fecha<BR>Correcci\363n</td>");
            printwriter.println("<td class='texttitulotabla' align='left' valign='top'>Depto&nbsp;que&nbsp;realiz\363<BR>la correcci\363n</td>");
            printwriter.println("<td class='texttitulotabla' align='left' valign='top'>An\341lisis&nbsp;de&nbsp;Causa (Origen)</td>");
            printwriter.println("<td class='texttitulotabla' align='left' valign='top'>Accion&nbsp;Inmediata</td>");
            printwriter.println("<td class='texttitulotabla' align='left' valign='top'>Accion&nbsp;Correctiva</td>");
            printwriter.println("<td class='texttitulotabla' align='left' valign='top'>Responsable<BR>AI</td>");
            printwriter.println("<td class='texttitulotabla' align='left' valign='top'>Fecha<BR>Verificaci\363n</td>");
            printwriter.println("<td class='texttitulotabla' align='left' valign='top'>Responsable<BR>Verificaci\363n A/C</td>");
            printwriter.println("</tr>");
            for(int j = 0; j < vector.size(); j += 12)
            {
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'>" + (String)vector.elementAt(j) + "</td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'>" + (String)vector.elementAt(j + 1) + "</td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'>" + (String)vector.elementAt(j + 2) + "</td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'>" + (String)vector.elementAt(j + 3) + "</td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'>" + (String)vector.elementAt(j + 4) + "</td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'>" + (String)vector.elementAt(j + 5) + "</td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'>" + (String)vector.elementAt(j + 6) + "</td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'>" + (String)vector.elementAt(j + 11) + "</td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'>" + (String)vector.elementAt(j + 7) + "</td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'>" + (String)vector.elementAt(j + 8) + "</td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'>" + (String)vector.elementAt(j + 9) + "</td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'>" + (String)vector.elementAt(j + 10) + "</td>");
                printwriter.println("</tr>");
                Calcula(printwriter, (String)vector.elementAt(j + 8));
            }

            Double double1 = new Double(0.0D);
            int l = vector.size() / 12;
            for(int k = 0; k < Porcentaje.size(); k += 2)
            {
                String s6 = (String)Porcentaje.elementAt(k);
                Integer integer3 = (Integer)Porcentaje.elementAt(k + 1);
                Double double2 = new Double((integer3.doubleValue() * 100D) / (double)l);
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top' colspan='3'>% Defectos <B>" + s6 + "</B></td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top' colspan='9'>" + double2.toString() + " %</td>");
                printwriter.println("</tr>");
            }

            printwriter.println("</table>");
        }
        return vector.size() / 11;
    }

    void Calcula(PrintWriter printwriter, String s)
    {
        boolean flag = false;
        String s1 = "";
        Integer integer = new Integer(0);
        Integer integer2 = new Integer(1);
        if(Porcentaje.size() == 0)
        {
            Porcentaje.add(s);
            Porcentaje.add(integer2);
        } else
        {
            for(int i = 0; i < Porcentaje.size() && !flag; i += 2)
            {
                String s2 = (String)Porcentaje.elementAt(i);
                Integer integer1 = (Integer)Porcentaje.elementAt(i + 1);
                if(s2.compareTo(s) == 0)
                {
                    int j = integer1.intValue();
                    j++;
                    Integer integer3 = new Integer(j);
                    Porcentaje.set(i + 1, integer3);
                    flag = true;
                }
            }

            if(!flag)
            {
                Integer integer4 = new Integer(1);
                Porcentaje.add(s);
                Porcentaje.add(integer4);
            }
        }
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    String IdNC;
    Vector Porcentaje;
    String StrMes;
    String StrAnio;
    String StrProceso;
}