// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   detalleflujo.java

package gdc;

import Acc.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class detalleflujo extends HttpServlet
{

    public detalleflujo()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        ACopia = new CopiadorDeArchivos();
        UserGDC = "";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        String s = httpservletrequest.getParameter("ID");
        HttpSession httpsession = httpservletrequest.getSession(true);
        UserGDC = (String)httpsession.getValue("SerapisUser");
        if(UserGDC != null && UserGDC.length() > 0)
        {
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            ADatos.connect();
            AFunc.cargamenu(printwriter, 1);
            PreparaPublica(printwriter, s);
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

    void PreparaPublica(PrintWriter printwriter, String s)
    {
        rs = new Vector();
        rsfile = new Vector();
        String s2 = "";
        String s3 = "";
        String s4 = "N";
        boolean flag = false;
        String s5 = "";
        String s7 = "";
        printwriter.println("<TABLE border='1' align='center' width='80%'>");
        String s1 = "select wff.idwf,wf.nombre nombrewf,wff.idpaso,wp.nombre nombrep,wff.detalle,wff.accion,wff.usuario,u.nombre nombreusr1,wff.fecha,wff.hora,wff.decision,wff.usuarioautorizador,wff.fecha_decision,wff.hora_decision,wff.comentarios ";
        s1 = s1 + " from gdc.workflow_flujo wff, gdc.workflow wf, gdc.workflow_pasos wp, sgc.usuarios u ";
        s1 = s1 + " where wff.iddocumento = " + s;
        s1 = s1 + " and wf.idwf = wff.idwf ";
        s1 = s1 + " and wf.idwf = wp.idwf ";
        s1 = s1 + " and wff.idpaso = wp.idpaso ";
        s1 = s1 + " and u.login = wff.usuario ";
        s1 = s1 + " ORDER by wff.id_flujo desc ";
        int i = ADatos.ConsultaDB(s1);
        rsfile = ADatos.getResult();
        if(rsfile.size() > 0)
        {
            printwriter.println("<tr>");
            printwriter.println("<td colspan='4' class='texttitulotabla'>Informaci\363n del Flujo&nbsp;.&nbsp;" + (String)rsfile.elementAt(1) + "</td>");
            printwriter.println("</tr>");
            for(int j = 0; j < rsfile.size() - 1; j += 15)
            {
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla' width='15%'>Paso</td>");
                printwriter.println("<td class='textdesttabla'  width='35%'>" + ((Integer)rsfile.elementAt(j + 2)).toString() + "-" + (String)rsfile.elementAt(j + 3) + "</td>");
                printwriter.println("<td class='textdesttabla' width='15%'>Detalle</td>");
                printwriter.println("<td class='textdesttabla'  width='35%'>" + (String)rsfile.elementAt(j + 4) + "</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Estado</td>");
                if(((String)rsfile.elementAt(j + 5)).compareTo("P") == 0)
                    printwriter.println("<td class='textdesttabla'>Pendiente</td>");
                else
                    printwriter.println("<td class='textdesttabla'>Realizado</td>");
                printwriter.println("<td class='textdesttabla'>Encargado</td>");
                printwriter.println("<td class='textdesttabla'>" + (String)rsfile.elementAt(j + 7) + "</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Fecha</td>");
                printwriter.println("<td class='textdesttabla'>" + AFunc.ConstruyeFecha(((Integer)rsfile.elementAt(j + 8)).toString(), "/", "dmy") + "</td>");
                printwriter.println("<td class='textdesttabla'>Hora</td>");
                printwriter.println("<td class='textdesttabla'>" + AFunc.ConstruyeHora(((Integer)rsfile.elementAt(j + 9)).toString(), ":") + "</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                String s6 = (String)rsfile.elementAt(j + 10);
                printwriter.println("<td class='textdesttabla'>Decisi\363n Tomada</td>");
                if(s6.length() > 0)
                {
                    if(s6.compareTo("A") == 0)
                        printwriter.println("<td class='textdesttabla'>Autorizada</td>");
                    else
                    if(s6.compareTo("M") == 0)
                        printwriter.println("<td class='textdesttabla'>Autorizada con Modificaciones</td>");
                    else
                    if(s6.compareTo("R") == 0)
                        printwriter.println("<td class='textdesttabla'>Redirecci\363n</td>");
                    else
                        printwriter.println("<td class='textdesttabla'>Rechazado</td>");
                } else
                {
                    printwriter.println("<td class='textdesttabla'>No Realizada</td>");
                }
                printwriter.println("<td class='textdesttabla'>Fecha/Hora</td>");
                printwriter.println("<td class='textdesttabla'>&nbsp;");
                String s8 = ((Integer)rsfile.elementAt(j + 12)).toString();
                if(s8.length() == 8)
                    printwriter.println(AFunc.ConstruyeFecha(s8.toString(), "/", "dmy"));
                s8 = ((Integer)rsfile.elementAt(j + 13)).toString();
                if(s8.length() >= 5)
                {
                    printwriter.println("&nbsp;-&nbsp;");
                    printwriter.println(AFunc.ConstruyeHora(s8.toString(), ":"));
                }
                printwriter.println("</td>");
                printwriter.println("</tr>");
                String s9 = (String)rsfile.elementAt(j + 14);
                if(s9 != null && s9.length() > 0)
                {
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla'>Comentarios</td>");
                    printwriter.println("<td colspan='3' class='textdesttabla'>" + s9 + "</td>");
                    printwriter.println("</tr>");
                }
                printwriter.println("<tr>");
                printwriter.println("<td colspan='4' class='texttitulotabla'>&nbsp;</td>");
                printwriter.println("</tr>");
            }

            printwriter.println("<tr>");
            printwriter.println("<td colspan='4' class='textdesttabla' align='center'>");
            printwriter.println("<input type='button' name='btnImprimir' value='Imprimir' class='fondoinput' language='javascript' onclick='window.print();'>");
            printwriter.println("</td></tr>");
        } else
        {
            printwriter.println("<tr>");
            printwriter.println("<td colspan='2' class='textdesttabla' align='center'>Documento no existe</td>");
            printwriter.println("</td>");
            printwriter.println("</tr>");
        }
        printwriter.println("</table>");
    }

    AccDataBase ADatos;
    funciones AFunc;
    CopiadorDeArchivos ACopia;
    Vector rs;
    Vector rsfile;
    String UserGDC;
}