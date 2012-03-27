// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   documentoswf.java

package gdc;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class documentoswf extends HttpServlet
{

    public documentoswf()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
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
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<table border='0' width='95%' align='center'>");
            printwriter.println("<tr><td align='center'  class='texttitulomayor'>Documentos en Flujo</td></tr>");
            printwriter.println("</table>");
            CargaDocumentos(printwriter);
            printwriter.println("<BR><center><input type='button' name='BtnImprimir' value='Imprimir' class='fondoinput' language='javascript' onclick='window.print()'></center></td>");
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

    void CargaDocumentos(PrintWriter printwriter)
    {
        rs = new Vector();
        Integer integer = new Integer(0);
        Integer integer2 = new Integer(0);
        String s = "";
        String s1 = "";
        ADatos.connect();
        String s2 = "select wff.iddocumento, p.descripcion descproceso, td.descripcion, d.descripcion desdoc, wff.idpaso, wff.detalle, wff.fecha, wff.hora, u.nombre ";
        s2 = s2 + " from gdc.workflow_flujo wff, gdc.documentos d, gdc.documentoscalidad dc, gdc.procesos p, gdc.tipodocumentos td,sgc.usuarios u ";
        s2 = s2 + " where wff.accion='P' ";
        s2 = s2 + " AND wff.tipodoc='gdc' ";
        s2 = s2 + " and wff.iddocumento = d.id ";
        s2 = s2 + " and dc.tipodocumento = d.tipodoc ";
        s2 = s2 + " and dc.proceso = d.proceso ";
        s2 = s2 + " and dc.descripcion = d.descripcion ";
        s2 = s2 + " and p.sigla = dc.proceso ";
        s2 = s2 + " and dc.tipodocumento = td.sigla ";
        s2 = s2 + " and wff.usuario = u.login ";
        s2 = s2 + " ORDER by p.descripcion ";
        int i = ADatos.ConsultaDB(s2);
        rs = ADatos.getResult();
        if(rs.size() > 0)
        {
            for(int j = 0; j < rs.size(); j += 9)
            {
                if(s.compareTo((String)rs.elementAt(1 + j)) != 0)
                {
                    if(s.length() > 0)
                    {
                        printwriter.println("</table>");
                        printwriter.println("<BR>");
                    }
                    printwriter.println("<table border='1' align='center' width='95%'>");
                    printwriter.println("<tr><td class='texttituloarea' colspan='4'>" + (String)rs.elementAt(1 + j) + "</td></tr>");
                    printwriter.println("</table>");
                    printwriter.println("<table border='1' align='center' width='95%'>");
                    s = (String)rs.elementAt(1 + j);
                    printwriter.println("<tr>");
                    printwriter.println("<td class='texttitulotabla' valign='top'>&nbsp;</td>");
                    printwriter.println("<td class='texttitulotabla' valign='top'>Tipo Documento</a></td>");
                    printwriter.println("<td class='texttitulotabla' valign='top'>Nombre</td>");
                    printwriter.println("<td class='texttitulotabla' valign='top'>Encargado</td>");
                    printwriter.println("<td class='texttitulotabla' colspan='2' valign='top'>Paso</td>");
                    printwriter.println("<td class='texttitulotabla' valign='top'>Fecha</td>");
                    printwriter.println("</tr>");
                }
                Integer integer1 = (Integer)rs.elementAt(j);
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla' valign='top'><a href='detalleflujo.jsp?ID=" + integer1.intValue() + "'><IMG src='../images/folderterminal.gif' width=16 height=16 border='0'></a></td>");
                printwriter.println("<td class='textdesttabla' valign='top'>" + (String)rs.elementAt(2 + j) + "</td>");
                printwriter.println("<td class='textdesttabla' valign='top'>" + (String)rs.elementAt(3 + j) + "</td>");
                printwriter.println("<td class='textdesttabla' valign='top'>" + (String)rs.elementAt(8 + j) + "</td>");
                printwriter.println("<td class='textdesttabla' valign='top'>" + ((Integer)rs.elementAt(4 + j)).toString() + "</td>");
                printwriter.println("<td class='textdesttabla' valign='top'>" + (String)rs.elementAt(5 + j) + "</td>");
                printwriter.println("<td class='textdesttabla' valign='top'>" + AFunc.ConstruyeFecha(((Integer)rs.elementAt(6 + j)).toString(), "/", "dmy") + "</td>");
                printwriter.println("</tr>");
            }

            printwriter.println("</table>");
        } else
        {
            printwriter.println("<table border='0' align='center' width='80%'>");
            printwriter.println("<tr>");
            printwriter.println("<td class='texttitulotabla' align='center'>No hay documentos por desplegar</td>");
            printwriter.println("<tr>");
            printwriter.println("</table>");
        }
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
}