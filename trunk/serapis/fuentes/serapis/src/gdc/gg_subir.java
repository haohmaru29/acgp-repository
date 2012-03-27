// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   gg_subir.java

package gdc;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class gg_subir extends HttpServlet
{

    public gg_subir()
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
        UserGDC = (String)httpsession.getValue("SerapisUser");
        if(UserGDC != null && UserGDC.length() > 0)
        {
            ADatos.connect();
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<form name='publica' method='POST' enctype='multipart/form-data' action='uploadfichero.jsp'>");
            printwriter.println("<TABLE border='1' width='80%' align='center'>");
            printwriter.println("<tr>");
            printwriter.println("<td colspan='2' class='texttitulotabla'>Archivo a Subir</td>");
            printwriter.println("</tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'><B>Proceso</B></td>");
            printwriter.println("<td class='textdesttabla'>");
            int i = ADatos.ConsultaDB("select p.sigla,p.descripcion from gdc.procesos p, sgc.perfil u where u.proceso = p.sigla and u.perfil = 'E' and u.login = '" + UserGDC + "' order by p.sigla");
            rs = ADatos.getResult();
            printwriter.println("<select name='proceso'>");
            if(rs.size() > 0)
            {
                for(int j = 0; j < rs.size(); j += 2)
                    printwriter.println("<option value='" + (String)rs.elementAt(j) + "'>" + (String)rs.elementAt(j + 1) + "</option>");

            }
            printwriter.println("</select>");
            printwriter.println("</td>");
            printwriter.println("</tr>");
            printwriter.println("</form>");
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

    void PreparaPublica(PrintWriter printwriter)
    {
        rs = new Vector();
        String s = "";
        String s1 = "";
        printwriter.println("<TABLE border='1' width='80%' align='center'>");
        printwriter.println("<tr>");
        printwriter.println("<td colspan='2' class='texttitulotabla'>Archivo a Subir</td>");
        printwriter.println("</tr>");
        int i = ADatos.ConsultaDB("select p.sigla,p.descripcion from gdc.procesos p, sgc.perfil u where u.proceso = p.sigla and u.perfil = 'E' and u.login = '" + UserGDC + "' order by p.sigla");
        rs = ADatos.getResult();
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla'><B>Proceso</B></td>");
        printwriter.println("<td class='textdesttabla'>");
        printwriter.println("<select name='tipoprocesos' onchange='return cambiadescripcion();'>");
        if(rs.size() > 0)
            s = (String)rs.elementAt(0);
        for(int j = 0; j < rs.size(); j += 2)
            printwriter.println("<option value='" + (String)rs.elementAt(j) + "'>" + (String)rs.elementAt(j + 1) + "</option>");

        printwriter.println("</select>");
        printwriter.println("</td>");
        printwriter.println("</tr>");
        i = ADatos.ConsultaDB("select sigla,descripcion from gdc.tipodocumentos order by sigla");
        rs = ADatos.getResult();
        printwriter.println("<tr>");
        printwriter.println("<td width='30%' class='textdesttabla'><B>Tipo Documento</B></td>");
        printwriter.println("<td class='textdesttabla'>");
        printwriter.println("<select name='tipodocumentos' onchange='return cambiadescripcion();'>");
        if(rs.size() > 0)
            s1 = (String)rs.elementAt(0);
        for(int k = 0; k < rs.size(); k += 2)
            printwriter.println("<option value='" + (String)rs.elementAt(k) + "'>" + (String)rs.elementAt(k + 1) + "</option>");

        printwriter.println("</select>");
        printwriter.println("</td>");
        printwriter.println("</tr>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla'><B>Nombre</B></td>");
        printwriter.println("<td class='textdesttabla'><select name='descripcion'>");
        i = ADatos.ConsultaDB("select descripcion from gdc.documentoscalidad where tipodocumento = '" + s1 + "' and proceso = '" + s + "' order by descripcion");
        rs = ADatos.getResult();
        for(int l = 0; l < rs.size(); l++)
            printwriter.println("<option value='" + (String)rs.elementAt(l) + "'>" + (String)rs.elementAt(l) + "</option>");

        printwriter.println("</select>");
        printwriter.println("</td>");
        printwriter.println("</tr>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla'><B>Version Plantilla</B></td>");
        printwriter.println("<td class='textdesttabla'><input name='versionp' language='javascript' style='WIDTH: 70px' onkeypress='' maxlength='2'></td>");
        printwriter.println("</tr>");
        i = ADatos.ConsultaDB("select abreviatura,razonsocial from sgc.clientes");
        rs = ADatos.getResult();
        if(rs.size() > 0)
            printwriter.println("<input name='clientes' language='javascript' style='WIDTH: 250px' onkeypress='' value='" + (String)rs.elementAt(0) + "' type='hidden'>");
        else
            printwriter.println("<input name='clientes' language='javascript' style='WIDTH: 250px' onkeypress='' value='' type='hidden'>");
        printwriter.println("<input name='versiond' language='javascript' style='WIDTH: 70px' onkeypress='' maxlength='2' type='hidden'>");
        printwriter.println("<input type='hidden' name='tipoextension' value=''>");
        printwriter.println("<input type='hidden' name='fuente' language='javascript' onclick='return establece(\"F\")'>");
        printwriter.println("<input name='esfuente' language='javascript' style='WIDTH: 70px' value='N' type='hidden'>");
        printwriter.println("<input name='sigue' language='javascript' style='WIDTH: 70px' value='N' type='hidden'>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla'>Descripci\363n</td>");
        printwriter.println("<td class='textdesttabla'>");
        printwriter.println("<textarea rows=3 cols=50 name='comentario' onkeypress='return ValidarCaracteres(9);' maxlength='200'></textarea>");
        printwriter.println("</td>");
        printwriter.println("</tr>");
        printwriter.println("<tr>");
        printwriter.println("</tr>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla'><B>Archivo</B></td>");
        printwriter.println("<td class='textdesttabla'><input type='file' name='fichero' style='WIDTH: 250px'></td>");
        printwriter.println("</tr>");
        printwriter.println("</table>");
        printwriter.println("<br><br>");
        printwriter.println("<center>");
        printwriter.println("<input type='button' class='fondoinput' name='ok' value='Subir' language='javascript' onclick='return valida();'>");
        printwriter.println("</center>");
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    String UserGDC;
}