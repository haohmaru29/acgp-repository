// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   llenacargos.java

package sad;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class llenacargos extends HttpServlet
{

    public llenacargos()
    {
        AFunc = new funciones();
        ADatos = new AccDataBase();
        sql = "";
        rs = new Vector();
        rs2 = new Vector();
        NombrePuesto = "";
        Departamento = "";
        Reporta = "";
        RangoSalarial = "";
        Objetivo = "";
        Edad = "";
        Idioma = "";
        Otros = "";
        Viajes = "";
        Horario = "";
        OtrosReq = "";
    }

    protected void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        UserGDC = (String)httpsession.getValue("SerapisUser");
        String s = "";
        String s1 = "";
        String s2 = "";
        String s3 = "";
        String s4 = "";
        String s5 = "";
        String s6 = "";
        String s7 = "";
        String s8 = "";
        String s9 = "";
        String s10 = "";
        String s11 = "";
        Integer integer = new Integer(0);
        String s12 = "";
        String s14 = "";
        String s16 = "";
        String s18 = "";
        String s20 = "";
        String s22 = "";
        String s24 = "";
        String s26 = "";
        String s28 = "";
        String s30 = "";
        Integer integer2 = new Integer(0);
        boolean flag = true;
        if(UserGDC != null && UserGDC.length() > 0)
        {
            String s31 = httpservletrequest.getParameter("ID");
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("<title>SERAPIS. Sistema de Gesti\363n de Calidad.</title>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<TITLE>SERAPIS. Sistema de Gesti\363n de Calidad</TITLE>");
            printwriter.println("<body leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<script type='text/javascript' language='JavaScript1.2' src='funciones.js'></script>");
            printwriter.println("<form name='cargo' method='POST' action=''>");
            sql = "SELECT idcargo from sgc.usuarios where login='" + UserGDC + "'";
            int k = ADatos.ConsultaDB(sql);
            rs = ADatos.getResult();
            if(rs.size() > 0)
                integer2 = (Integer)rs.elementAt(0);
            rs = new Vector();
            rs.clear();
            sql = "SELECT ID_Tipo,Descripcion,SeUtiliza,Privado,Visible,TipoCampo,NombreCampo,CampoPrevio,Grupo";
            sql = sql + " from gdc.tipo_descrip ORDER by ID_Tipo";
            k = ADatos.ConsultaDB(sql);
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                rs2 = (Vector)rs.clone();
                printwriter.println("<table cellpadding='0' cellspacing='0' border='1' width='85%' align='center'>");
                for(int i = 0; i < rs2.size(); i += 9)
                {
                    boolean flag1 = true;
                    Integer integer1 = (Integer)rs2.elementAt(i);
                    String s13 = (String)rs2.elementAt(i + 1);
                    String s19 = (String)rs2.elementAt(i + 2);
                    String s17 = (String)rs2.elementAt(i + 3);
                    String s15 = (String)rs2.elementAt(i + 4);
                    String s25 = (String)rs2.elementAt(i + 5);
                    String s21 = (String)rs2.elementAt(i + 6);
                    String s23 = (String)rs2.elementAt(i + 7);
                    String s29 = (String)rs2.elementAt(i + 8);
                    if(s17.compareTo("S") == 0)
                        if(s31.compareTo(integer2.toString()) == 0)
                            flag1 = true;
                        else
                            flag1 = false;
                    if(s19.compareTo("S") != 0 || s15.compareTo("S") != 0 || !flag1)
                        continue;
                    rs.clear();
                    rs = new Vector();
                    sql = "SELECT Descripcion from gdc.descripciones_cargos where ID = " + s31 + " and ID_Tipo = " + integer1.toString() + " ORDER by correlativo";
                    int l = ADatos.ConsultaDB(sql);
                    rs = ADatos.getResult();
                    if(rs.size() <= 0)
                        continue;
                    if(s30.compareTo(s29) != 0)
                    {
                        printwriter.println("<tr>");
                        printwriter.println("<td colspan='2' class='texttitulotabla'><b>" + s29 + "</b></td>");
                        printwriter.println("</tr>");
                        s30 = s29;
                    }
                    printwriter.println("    <tr>");
                    printwriter.println("      <td class='textdesttabla' width='25%'><b>" + s13 + "</b></td>");
                    if(s25.compareTo("M") == 0)
                        printwriter.println("      <td class='textdesttabla'>");
                    for(int j = 0; j < rs.size(); j++)
                    {
                        String s27 = (String)rs.elementAt(j);
                        if(s25.compareTo("T") == 0)
                            printwriter.println("      <td class='textdesttabla'>" + s27 + "</td>");
                        if(s25.compareTo("A") == 0)
                            printwriter.println("      <td class='textdesttabla'>" + s27 + "</td>");
                        if(s25.compareTo("M") == 0)
                            printwriter.println(s27 + "<BR>");
                    }

                    if(s25.compareTo("M") == 0)
                        printwriter.println("    </td>");
                    printwriter.println("    </tr>");
                }

                printwriter.println("<tr>");
                printwriter.println("<td colspan='2' class='textdesttabla' align='center'><input class='fondoinput' type='button' name='btnImprimir' value='Imprimir' onclick='window.print();'></td>");
                printwriter.println("</tr>");
                printwriter.println("<table>");
            }
            printwriter.println("</body>");
            printwriter.println("</html>");
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "SAD", 6);
        }
    }

    funciones AFunc;
    AccDataBase ADatos;
    String sql;
    Vector rs;
    Vector rs2;
    int ret;
    String NombrePuesto;
    String Departamento;
    String Reporta;
    String RangoSalarial;
    String Objetivo;
    String Edad;
    String Idioma;
    String Otros;
    String Viajes;
    String Horario;
    String OtrosReq;
    String UserGDC;
}