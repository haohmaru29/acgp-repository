// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   desempresa.java

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class desempresa extends HttpServlet
{

    public desempresa()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
    	sPath = "";
    	String s0 = "select dirsitio from gdc.datosgenerales";
    	ADatos.connect();
        int k1 = ADatos.ConsultaDB(s0);
        if (k1>0) {
	        rs = ADatos.getResult();
	        if (rs.size()>0) {
	        	sPath = (String)rs.elementAt(0);
	        }
	        rs.clear();
    	}          
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        String s = httpservletrequest.getParameter("ID");
        Integer integer = new Integer(0);
        String s1 = "";
        String s2 = "";
        String s3 = "";
        String s4 = "";
        String s5 = "";
        if(s == null || s.length() == 0)
            s = "1";
        integer = new Integer(s);
        printwriter.println("<html>");
        printwriter.println("<head>");
        printwriter.println("<title>SERAPIS. Sistema de Gesti\363n de Calidad.</title>");
        printwriter.println("</head>");
        printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='serapis.css'>");
        printwriter.println("<TITLE>SERAPIS. Sistema de Gesti\363n de Calidad</TITLE>");
        printwriter.println("<body leftmargin='0' topmargin='0'>");
        if(integer.intValue() >= 3)
            AFunc.cargamenucompleto(printwriter, 0, "Organizaci\363n");
        else
            AFunc.cargamenucompleto(printwriter, 0, "Empresa");
        s1 = sPath + "\\serapis\\empresa\\";
        switch(integer.intValue())
        {
        default:
            break;

        case 3: // '\003'
            ADatos.connect();
            sql = "SELECT organigrama from gdc.datosgenerales";
            int i = ADatos.ConsultaDB(sql);
            rs = new Vector();
            rs = ADatos.getResult();
            if(rs.size() > 0)
                s4 = (String)rs.elementAt(0);
            printwriter.println("</body>");
            printwriter.println("<script language='javascript'>");
            printwriter.println("alert(\"Se abrir\341 una nueva ventana con el organigrama almacenado en SERAPIS\");");
            printwriter.println("window.open('cuerpo.jsp','cuerpo');");
            printwriter.println("window.open('images/" + s4 + "','_blank');");
            printwriter.println("</script>");
            printwriter.println("</body>");
            break;

        case 4: // '\004'
            ADatos.connect();
            sql = "SELECT linea1,linea2,mail,foto,ancho,alto from gdc.lideres order by orden,linea1";
            int j = ADatos.ConsultaDB(sql);
            rs = new Vector();
            rs.clear();
            rs = ADatos.getResult();
            printwriter.println("<table width='95%' cellpadding='0' cellspacing='0' border='0' align='center'>");
            printwriter.println("<tr><td>");
            printwriter.println("  <table width='95%' border='0' cellspacing='0' cellpadding='0'>");
            printwriter.println("  <tr align='left'> ");
            printwriter.println("      <td><img src='images/tit_lideresproceso.jpg' width='219' height='24'></td>");
            printwriter.println("  </tr>");
            printwriter.println("  <tr>");
            printwriter.println("      <td align='left'><img src='images/transparent.gif' width='10' height='2'></td>");
            printwriter.println("  </tr>");
            printwriter.println("  </table>");
            if(rs.size() > 0)
            {
                int k = 0;
                printwriter.println("  <table width='100%' border='0' align='left' valign='top' cellspacing='0' cellpadding='10' class='bordeempresa'>");
                for(int i1 = 0; i1 <= rs.size(); i1 += 6)
                {
                    if(k == 0 && rs.size() / 6 >= 1)
                    {
                        printwriter.println("    <tr>");
                        printwriter.println("       <td align='center' width='3%'>&nbsp;</td>");
                        printwriter.println("       <td align='center' width='30%' align='center'><img src='images/" + (String)rs.elementAt(i1 + 3) + "' width='" + ((Integer)rs.elementAt(i1 + 4)).toString() + "' height='" + ((Integer)rs.elementAt(i1 + 5)).toString() + "' align='center'><BR><a href='mailto:" + (String)rs.elementAt(i1 + 2) + "'>" + (String)rs.elementAt(i1) + "</a><BR>" + (String)rs.elementAt(i1 + 1) + "</td>");
                    }
                    if(k == 1 && rs.size() / 6 >= 2)
                    {
                        printwriter.println("       <td align='center' width='2%'>&nbsp;</td>");
                        printwriter.println("       <td align='center' width='30%' align='center'><img src='images/" + (String)rs.elementAt(i1 + 3) + "' width='" + ((Integer)rs.elementAt(i1 + 4)).toString() + "' height='" + ((Integer)rs.elementAt(i1 + 5)).toString() + "' align='center'><BR><a href='mailto:" + (String)rs.elementAt(i1 + 2) + "'>" + (String)rs.elementAt(i1) + "</a><BR>" + (String)rs.elementAt(i1 + 1) + "</td>");
                    }
                    if(k == 2 && rs.size() / 6 >= 3)
                    {
                        printwriter.println("       <td align='center' width='2%'>&nbsp;</td>");
                        printwriter.println("       <td align='center' width='30%' align='center'><img src='images/" + (String)rs.elementAt(i1 + 3) + "' width='" + ((Integer)rs.elementAt(i1 + 4)).toString() + "' height='" + ((Integer)rs.elementAt(i1 + 5)).toString() + "' align='center'><BR><a href='mailto:" + (String)rs.elementAt(i1 + 2) + "'>" + (String)rs.elementAt(i1) + "</a><BR>" + (String)rs.elementAt(i1 + 1) + "</td>");
                    }
                    if(++k > 2)
                    {
                        printwriter.println("       <td align='center' width='3%'>&nbsp;</td>");
                        printwriter.println("\t </tr>");
                        k = 0;
                    }
                }

                printwriter.println("  </table>");
            }
            printwriter.println("</td></tr>");
            printwriter.println("</table>");
            printwriter.println("</body>");
            break;

        case 5: // '\005'
            ADatos.connect();
            sql = "SELECT politica from gdc.calidad";
            int l = ADatos.ConsultaDB(sql);
            rs = new Vector();
            rs = ADatos.getResult();
            if(rs.size() > 0)
                s3 = (String)rs.elementAt(0);
            printwriter.println("<table width='70%' cellpadding='0' cellspacing='0' border='0' align='center'>");
            printwriter.println("<tr><td>&nbsp;</td></tr>");
            printwriter.println("<tr><td>&nbsp;</td></tr>");
            printwriter.println("<tr><td>&nbsp;</td></tr>");
            printwriter.println("<tr>");
            printwriter.println("<td>");
            printwriter.println("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
            printwriter.println("<tr align='left'>");
            printwriter.println("    <td><img src='images/tit_politicacalidad.jpg' width='213' height='24'></td>");
            printwriter.println("  </tr>");
            printwriter.println("  <tr>");
            printwriter.println("    <td align='left'><img src='images/transparent.gif' width='10' height='2'></td>");
            printwriter.println("  </tr>");
            printwriter.println("</table>");
            printwriter.println("<table width='100%' border='0' align='center' valign='center' cellspacing='0' cellpadding='10' class='bordeempresa'>");
            printwriter.println("  <tr><td align='center' valign='middle' class='contenidoespecial1' colspan='3'>&nbsp;</td></tr>");
            printwriter.println("  <tr>");
            printwriter.println("\t    <td align='center' valign='middle' class='contenidoespecial1' width='8%'>&nbsp;</td>");
            printwriter.println("\t    <td align='center' valign='middle' class='contenidoespecial1'>" + s3 + "</td>");
            printwriter.println("\t    <td align='center' valign='middle' class='contenidoespecial1' width='8%'>&nbsp;</td>");
            printwriter.println("\t  </tr>");
            printwriter.println("  <tr><td align='center' valign='middle' class='contenidoespecial1' colspan='3'>&nbsp;</td></tr>");
            printwriter.println("\t</table>");
            printwriter.println("</td>");
            printwriter.println("</tr>");
            printwriter.println("<tr><td>&nbsp;</td></tr>");
            printwriter.println("<tr><td>&nbsp;</td></tr>");
            printwriter.println("<tr><td align='center' class='logotipo'>&nbsp;</td></tr>");
            printwriter.println("</table>");
            printwriter.println("</body>");
            break;

        case 6: // '\006'
            ADatos.connect();
            sql = "SELECT mapa from gdc.datosgenerales";
            int j1 = ADatos.ConsultaDB(sql);
            rs = new Vector();
            rs = ADatos.getResult();
            if(rs.size() > 0)
                s5 = (String)rs.elementAt(0);
            printwriter.println("</body>");
            printwriter.println("<script language='javascript'>");
            printwriter.println("alert(\"Se abrir\341 una nueva ventana con el mapa de procesos almacenado en SERAPIS\");");
            printwriter.println("window.open('cuerpo.jsp','cuerpo');");
            printwriter.println("window.open('images/" + s5 + "','_blank');");
            printwriter.println("</script>");
            printwriter.println("</body>");
            break;
        }
        printwriter.println("</html>");
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    String sql;
    String sPath;
}