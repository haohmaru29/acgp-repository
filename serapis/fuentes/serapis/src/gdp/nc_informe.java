// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   nc_informe.java

package gdp;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class nc_informe extends HttpServlet
{

    public nc_informe()
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
            printwriter.println("<form name='frmnc' method='POST'>");
            printwriter.println("<input  type='hidden' name='selMes' value='" + StrMes + "'>");
            printwriter.println("<input  type='hidden' name='selAnio' value='" + StrAnio + "'>");
            printwriter.println("<input  type='hidden' name='selProceso' value='" + StrProceso + "'>");
            long l = CargaNC(printwriter);
            printwriter.println("<input  type='hidden' name='nrofilas' value='" + l + "'>");
            printwriter.println("<BR><center><input type='button' name='BtnGrabar' value='Grabar' class='fondoinput' language='javascript' onclick='return Grabar();'>&nbsp;&nbsp;<input type='button' name='BtnVolver' value='Volver' class='fondoinput' language='javascript' onclick='return Volver();'>&nbsp;&nbsp;<input type='button' name='BtnImprimir' value='Imprimir' class='fondoinput' language='javascript' onclick='window.print()'></center></td>");
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
        s = " select sigla,descripcion from gdc.procesos order by descripcion";
        int j = ADatos.ConsultaDB(s);
        rs = ADatos.getResult();
        Vector vector1 = new Vector();
        vector1 = (Vector)rs.clone();
        s = " select login,nombre from sgc.usuarios order by nombre";
        j = ADatos.ConsultaDB(s);
        rs = ADatos.getResult();
        Vector vector2 = new Vector();
        vector2 = (Vector)rs.clone();
        s = "SELECT fecha,txt_Identificacion,txt_Analisis,txt_Accion_Correctiva,txt_Accion_Inmediata ";
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
            printwriter.println("<tr><td class='texttituloarea' width='100%' colspan='12'>Resumen de No Conformidades</td></tr>");
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
            printwriter.println("<td class='texttitulotabla' align='left' valign='top'>Responsable<BR>Verificaci\363n AC</td>");
            printwriter.println("</tr>");
            int k = 0;
            for(i = 1; k < vector.size(); i++)
            {
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'>" + StrProceso + "&nbsp;" + i + "<input type='hidden' name='campo1" + i + "' value='" + StrProceso + "&nbsp;" + i + "'></td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'>" + AFunc.ConstruyeFecha(((Integer)vector.elementAt(k)).toString(), "/", "dmy") + "<input  type='hidden' name='campo2" + i + "' value='" + AFunc.ConstruyeFecha(((Integer)vector.elementAt(k)).toString(), "/", "dmy") + "'></td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'>" + (String)vector.elementAt(k + 1) + "<input  type='hidden' name='campo3" + i + "' value='" + (String)vector.elementAt(k + 1) + "'></td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'><select name='corregida" + i + "'><option value='SI'>Si</option><option value='NO'>No</option></select></td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'><input style='WIDTH: 70px' onkeypress='return ValidarCaracteres(11);' maxlength='10' name='fechacorr" + i + "'></td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'><select name='deptocorr" + i + "'>");
                for(int l = 0; l < vector1.size(); l += 2)
                    printwriter.println("<option value='" + (String)vector1.elementAt(l + 1) + "'>" + (String)vector1.elementAt(l + 1) + "</option>");

                printwriter.println("</select></td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'>" + (String)vector.elementAt(k + 2) + "<input type='hidden' name='campo7" + i + "' value='" + (String)vector.elementAt(k + 2) + "'></td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'>" + (String)vector.elementAt(k + 4) + "<input type='hidden' name='campo9" + i + "' value='" + (String)vector.elementAt(k + 4) + "'></td>");
                printwriter.println("<td class='textdesttabla' align='left' valign='top'>" + (String)vector.elementAt(k + 3) + "<input type='hidden' name='campo8" + i + "' value='" + (String)vector.elementAt(k + 3) + "'></td>");
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
                k += 5;
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
}