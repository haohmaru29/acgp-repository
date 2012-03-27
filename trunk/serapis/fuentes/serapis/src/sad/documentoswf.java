// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   documentoswf.java

package sad;

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
            int i = CargaDocumentos(printwriter);
            int j = CargaDocumentosp(printwriter);
            if(i > 0 || j > 0)
            {
                printwriter.println("<BR><center><input type='button' name='BtnImprimir' value='Imprimir' class='fondoinput' language='javascript' onclick='window.print()'></center></td>");
            } else
            {
                printwriter.println("<table border='0' align='center' width='80%'>");
                printwriter.println("<tr>");
                printwriter.println("<td class='texttitulotabla' align='center'>No hay registros por desplegar</td>");
                printwriter.println("<tr>");
                printwriter.println("</table>");
            }
            printwriter.println("</body>");
            printwriter.println("</html>");
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "SAD", 6);
        }
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    int CargaDocumentos(PrintWriter printwriter)
    {
        int i = 0;
        rs = new Vector();
        Integer integer = new Integer(0);
        Integer integer2 = new Integer(0);
        String s = "";
        String s1 = "";
        ADatos.connect();
        String s2 = "select wff.iddocumento, p.descripcion descproceso, td.descripcion, d.descripcion desdoc, wff.idpaso, wff.detalle, wff.fecha, wff.hora, u.nombre ";
        s2 = s2 + " from gdc.workflow_flujo wff, sad.documentos d, gdc.documentoscalidad dc, gdc.procesos p, gdc.tipodocumentos td,sgc.usuarios u ";
        s2 = s2 + " where wff.accion='P' ";
        s2 = s2 + " AND wff.tipodoc='sad' ";
        s2 = s2 + " and wff.iddocumento = d.id ";
        s2 = s2 + " and dc.tipodocumento = d.tipodoc ";
        s2 = s2 + " and dc.proceso = d.proceso ";
        s2 = s2 + " and dc.descripcion = d.descripcion ";
        s2 = s2 + " and p.sigla = dc.proceso ";
        s2 = s2 + " and dc.tipodocumento = td.sigla ";
        s2 = s2 + " and wff.usuario = u.login ";
        s2 = s2 + " ORDER by p.descripcion ";
        int j = ADatos.ConsultaDB(s2);
        rs = ADatos.getResult();
        if(rs.size() > 0)
        {
            i = rs.size() / 9;
            printwriter.println("<table border='1' align='center' width='95%'>");
            printwriter.println("<tr><td class='texttituloarea' colspan='4'>Registros en Flujo</td></tr>");
            printwriter.println("</table>");
            for(int k = 0; k < rs.size(); k += 9)
            {
                if(s.compareTo((String)rs.elementAt(1 + k)) != 0)
                {
                    if(s.length() > 0)
                    {
                        printwriter.println("</table>");
                        printwriter.println("<BR>");
                    }
                    printwriter.println("<table border='1' align='center' width='95%'>");
                    printwriter.println("<tr><td class='texttituloarea' colspan='4'>" + (String)rs.elementAt(1 + k) + "</td></tr>");
                    printwriter.println("</table>");
                    printwriter.println("<table border='1' align='center' width='95%'>");
                    s = (String)rs.elementAt(1 + k);
                    printwriter.println("<tr>");
                    printwriter.println("<td class='texttitulotabla' valign='top'>&nbsp;</td>");
                    printwriter.println("<td class='texttitulotabla' valign='top'>Tipo Documento</a></td>");
                    printwriter.println("<td class='texttitulotabla' valign='top'>Nombre</td>");
                    printwriter.println("<td class='texttitulotabla' valign='top'>Encargado</td>");
                    printwriter.println("<td class='texttitulotabla' colspan='2' valign='top'>Paso</td>");
                    printwriter.println("<td class='texttitulotabla' valign='top'>Fecha</td>");
                    printwriter.println("</tr>");
                }
                Integer integer1 = (Integer)rs.elementAt(k);
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla' valign='top'><a href='detalleflujo.jsp?ID=" + integer1.intValue() + "'><IMG src='../images/folderterminal.gif' width=16 height=16 border='0'></a></td>");
                printwriter.println("<td class='textdesttabla' valign='top'>" + (String)rs.elementAt(2 + k) + "</td>");
                printwriter.println("<td class='textdesttabla' valign='top'>" + (String)rs.elementAt(3 + k) + "</td>");
                printwriter.println("<td class='textdesttabla' valign='top'>" + (String)rs.elementAt(8 + k) + "</td>");
                printwriter.println("<td class='textdesttabla' valign='top'>" + ((Integer)rs.elementAt(4 + k)).toString() + "</td>");
                printwriter.println("<td class='textdesttabla' valign='top'>" + (String)rs.elementAt(5 + k) + "</td>");
                printwriter.println("<td class='textdesttabla' valign='top'>" + AFunc.ConstruyeFecha(((Integer)rs.elementAt(6 + k)).toString(), "/", "dmy") + "</td>");
                printwriter.println("</tr>");
            }

            printwriter.println("</table>");
        }
        return i;
    }

    int CargaDocumentosp(PrintWriter printwriter)
    {
        int i = 0;
        rs = new Vector();
        Integer integer = new Integer(0);
        Integer integer2 = new Integer(0);
        String s = "";
        String s1 = "";
        ADatos.connect();
        String s2 = "select wff.iddocumento, p.descripcion descproceso, td.descripcion, d.descripcion desdoc, wff.idpaso, wff.detalle, wff.fecha, wff.hora, u.nombre ";
        s2 = s2 + " from gdc.workflow_flujo wff, sad.documentosp d, gdc.documentoscalidad dc, gdc.procesos p, gdc.tipodocumentos td,sgc.usuarios u ";
        s2 = s2 + " where wff.accion='P' ";
        s2 = s2 + " AND wff.tipodoc='sadp' ";
        s2 = s2 + " and wff.iddocumento = d.id ";
        s2 = s2 + " and dc.tipodocumento = d.tipodoc ";
        s2 = s2 + " and dc.proceso = d.proceso ";
        s2 = s2 + " and dc.descripcion = d.descripcion ";
        s2 = s2 + " and p.sigla = dc.proceso ";
        s2 = s2 + " and dc.tipodocumento = td.sigla ";
        s2 = s2 + " and wff.usuario = u.login ";
        s2 = s2 + " ORDER by p.descripcion ";
        int j = ADatos.ConsultaDB(s2);
        rs = ADatos.getResult();
        if(rs.size() > 0)
        {
            i = rs.size() / 9;
            printwriter.println("<table border='1' align='center' width='95%'>");
            printwriter.println("<tr><td class='texttituloarea' colspan='4'>Registros (de Clientes) en Flujo</td></tr>");
            printwriter.println("</table>");
            for(int k = 0; k < rs.size(); k += 9)
            {
                if(s.compareTo((String)rs.elementAt(1 + k)) != 0)
                {
                    if(s.length() > 0)
                    {
                        printwriter.println("</table>");
                        printwriter.println("<BR>");
                    }
                    printwriter.println("<table border='1' align='center' width='95%'>");
                    printwriter.println("<tr><td class='texttituloarea' colspan='4'>" + (String)rs.elementAt(1 + k) + "</td></tr>");
                    printwriter.println("</table>");
                    printwriter.println("<table border='1' align='center' width='95%'>");
                    s = (String)rs.elementAt(1 + k);
                    printwriter.println("<tr>");
                    printwriter.println("<td class='texttitulotabla' valign='top'>&nbsp;</td>");
                    printwriter.println("<td class='texttitulotabla' valign='top'>Tipo Documento</a></td>");
                    printwriter.println("<td class='texttitulotabla' valign='top'>Nombre</td>");
                    printwriter.println("<td class='texttitulotabla' valign='top'>Encargado</td>");
                    printwriter.println("<td class='texttitulotabla' colspan='2' valign='top'>Paso</td>");
                    printwriter.println("<td class='texttitulotabla' valign='top'>Fecha</td>");
                    printwriter.println("</tr>");
                }
                Integer integer1 = (Integer)rs.elementAt(k);
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla' valign='top'><a href='detalleflujo.jsp?ID=" + integer1.intValue() + "'><IMG src='../images/folderterminal.gif' width=16 height=16 border='0'></a></td>");
                printwriter.println("<td class='textdesttabla' valign='top'>" + (String)rs.elementAt(2 + k) + "</td>");
                printwriter.println("<td class='textdesttabla' valign='top'>" + (String)rs.elementAt(3 + k) + "</td>");
                printwriter.println("<td class='textdesttabla' valign='top'>" + (String)rs.elementAt(8 + k) + "</td>");
                printwriter.println("<td class='textdesttabla' valign='top'>" + ((Integer)rs.elementAt(4 + k)).toString() + "</td>");
                printwriter.println("<td class='textdesttabla' valign='top'>" + (String)rs.elementAt(5 + k) + "</td>");
                printwriter.println("<td class='textdesttabla' valign='top'>" + AFunc.ConstruyeFecha(((Integer)rs.elementAt(6 + k)).toString(), "/", "dmy") + "</td>");
                printwriter.println("</tr>");
            }

            printwriter.println("</table>");
        }
        return i;
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
}