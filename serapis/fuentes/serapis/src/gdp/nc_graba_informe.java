// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   nc_graba_informe.java

package gdp;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class nc_graba_informe extends HttpServlet
{

    public nc_graba_informe()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        StrMes = "";
        StrAnio = "";
        StrProceso = "";
        NroFilas = "";
        IntNroFilas = new Integer(0);
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
            ADatos.connect();
            StrMes = httpservletrequest.getParameter("selMes");
            StrAnio = httpservletrequest.getParameter("selAnio");
            StrProceso = httpservletrequest.getParameter("selProceso");
            NroFilas = httpservletrequest.getParameter("nrofilas");
            if(NroFilas == null || NroFilas.length() == 0)
                NroFilas = "0";
            IntNroFilas = new Integer(NroFilas);
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            String s1 = "";
            String s3 = "";
            String s5 = "";
            String s7 = "";
            String s9 = "";
            String s11 = "";
            String s13 = "";
            String s15 = "";
            String s17 = "";
            String s19 = "";
            String s21 = "";
            String s23 = "";
            String s25 = "delete from gdc.nc_informe where mes = " + StrMes;
            s25 = s25 + " and anio = " + StrAnio;
            s25 = s25 + " and proceso = '" + StrProceso + "'";
            ADatos.ModificaDB(s25);
            for(int i = 1; i <= IntNroFilas.intValue() - 1; i++)
            {
                String s2 = httpservletrequest.getParameter("campo1" + i);
                String s4 = httpservletrequest.getParameter("campo2" + i);
                String s6 = httpservletrequest.getParameter("campo3" + i);
                String s8 = httpservletrequest.getParameter("corregida" + i);
                String s10 = httpservletrequest.getParameter("fechacorr" + i);
                String s12 = httpservletrequest.getParameter("deptocorr" + i);
                String s14 = httpservletrequest.getParameter("campo7" + i);
                String s16 = httpservletrequest.getParameter("campo8" + i);
                String s24 = httpservletrequest.getParameter("campo9" + i);
                String s18 = httpservletrequest.getParameter("resp" + i);
                String s20 = httpservletrequest.getParameter("fechaverif" + i);
                String s22 = httpservletrequest.getParameter("respverif" + i);
                String s26 = "insert into gdc.nc_informe (mes,anio,proceso,campo1,campo2,campo3,campo4,campo5,campo6,campo7,campo8,campo12,campo9,campo10,campo11) values ( ";
                s26 = s26 + StrMes + ",";
                s26 = s26 + StrAnio + ",";
                s26 = s26 + "'" + StrProceso + "',";
                s26 = s26 + "'" + s2 + "',";
                s26 = s26 + "'" + s4 + "',";
                s26 = s26 + "'" + s6 + "',";
                s26 = s26 + "'" + s8 + "',";
                s26 = s26 + "'" + s10 + "',";
                s26 = s26 + "'" + s12 + "',";
                s26 = s26 + "'" + s14 + "',";
                s26 = s26 + "'" + s16 + "',";
                s26 = s26 + "'" + s24 + "',";
                s26 = s26 + "'" + s18 + "',";
                s26 = s26 + "'" + s20 + "',";
                s26 = s26 + "'" + s22 + "')";
                ADatos.ModificaDB(s26);
            }

            printwriter.println("<TABLE border='1' align='center' width='80%'>");
            printwriter.println("<tr>");
            printwriter.println("<td colspan='2' class='texttitulotabla'>Resultados</td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' colspan='2' align='center'>El informe se ha almacenado correctamente.</td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' colspan='2' align='center'><input type='button' class='fondoinput' name='btnok' value='Aceptar' onclick='window.open(\"nc.jsp\",\"datos\");'></td>");
            printwriter.println("</tr>");
            printwriter.println("</table>");
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

    long CargaNC(PrintWriter printwriter)
    {
        String s = "";
        String s1 = "";
        String s2 = "";
        ADatos.connect();
        int i = 0;
        s = " select sigla,descripcion from gdc.procesos";
        int j = ADatos.ConsultaDB(s);
        rs = ADatos.getResult();
        Vector vector1 = new Vector();
        vector1 = (Vector)rs.clone();
        s = " select login,nombre from sgc.usuarios";
        j = ADatos.ConsultaDB(s);
        rs = ADatos.getResult();
        Vector vector2 = new Vector();
        vector2 = (Vector)rs.clone();
        s = "SELECT fecha,txt_Identificacion,txt_Analisis,txt_Accion_Correctiva ";
        s = s + " from gdc.no_conformidad where  ";
        s = s + " fecha <= " + StrAnio + StrMes + "31";
        s = s + " and fecha >= " + StrAnio + StrMes + "01";
        s = s + " and txt_Area = '" + StrProceso + "'";
        s = s + " order by id desc ";
        j = ADatos.ConsultaDB(s);
        rs = ADatos.getResult();
        Vector vector = new Vector();
        vector = (Vector)rs.clone();
        if(vector.size() > 0)
        {
            printwriter.println("<table border='1' align='center' width='95%'>");
            printwriter.println("<tr><td class='texttituloarea' width='100%' colspan='11'>Resumen de No Conformidades</td></tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='texttitulotabla' align='left' valign='top'>N\260&nbsp;&nbsp;</td>");
            printwriter.println("<td class='texttitulotabla' align='left' valign='top'>Fecha<BR>Cursada</td>");
            printwriter.println("<td class='texttitulotabla' align='left' valign='top'>Descripci\363n&nbsp;de&nbsp;la&nbsp;No&nbsp;Conformidad<BR>Proceso Afectado /Sugerencia</td>");
            printwriter.println("<td class='texttitulotabla' align='left' valign='top'>\277Corregida?</td>");
            printwriter.println("<td class='texttitulotabla' align='left' valign='top'>Fecha<BR>Correcci\363n</td>");
            printwriter.println("<td class='texttitulotabla' align='left' valign='top'>Depto&nbsp;que&nbsp;realiz\363<BR>la correcci\363n</td>");
            printwriter.println("<td class='texttitulotabla' align='left' valign='top'>An\341lisis&nbsp;d&nbsp;Causa (Origen)</td>");
            printwriter.println("<td class='texttitulotabla' align='left' valign='top'>Accion&nbsp;Correctiva</td>");
            printwriter.println("<td class='texttitulotabla' align='left' valign='top'>Responsable<BR>AC</td>");
            printwriter.println("<td class='texttitulotabla' align='left' valign='top'>Fecha<BR>Verificaci\363n</td>");
            printwriter.println("<td class='texttitulotabla' align='left' valign='top'>Responsable<BR>Verificaci\363n</td>");
            printwriter.println("</tr>");
            int k = 0;
            for(i = 1; k < vector.size(); i++)
            {
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'>" + StrProceso + "&nbsp;" + i + "</td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'>" + AFunc.ConstruyeFecha(((Integer)vector.elementAt(k)).toString(), "/", "dmy") + "</td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'>" + (String)vector.elementAt(k + 1) + "</td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'><select name='corregida" + i + "'><option value='SI'>Si</option><option value='NO'>No</option></select></td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'><input style='WIDTH: 70px' onkeypress='return ValidarCaracteres(11);' maxlength='10' name='fechacorr" + i + "'></td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'><select name='deptocorr" + i + "'>");
                for(int l = 0; l < vector1.size(); l += 2)
                    printwriter.println("<option value='" + (String)vector1.elementAt(l + 1) + "'>" + (String)vector1.elementAt(l + 1) + "</option>");

                printwriter.println("</select></td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'>" + (String)vector.elementAt(k + 2) + "</td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'>" + (String)vector.elementAt(k + 3) + "</td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'><select name='resp" + i + "'>");
                for(int i1 = 0; i1 < vector2.size(); i1 += 2)
                    printwriter.println("<option value='" + (String)vector2.elementAt(i1 + 1) + "'>" + (String)vector2.elementAt(i1 + 1) + "</option>");

                printwriter.println("</select></td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'><input style='WIDTH: 70px' onkeypress='return ValidarCaracteres(11);' maxlength='10' name='fechaverif" + i + "'></td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'><select name='respverif" + i + "'>");
                for(int j1 = 0; j1 < vector2.size(); j1 += 2)
                    printwriter.println("<option value='" + (String)vector2.elementAt(j1 + 1) + "'>" + (String)vector2.elementAt(j1 + 1) + "</option>");

                printwriter.println("</select></td>");
                printwriter.println("</tr>");
                k += 4;
            }

            printwriter.println("</table>");
        }
        return (long)i;
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    String IdNC;
    String StrMes;
    String StrAnio;
    String StrProceso;
    String NroFilas;
    Integer IntNroFilas;
}