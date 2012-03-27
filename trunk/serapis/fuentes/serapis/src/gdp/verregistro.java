// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   verregistro.java

package gdp;

import Acc.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class verregistro extends HttpServlet
{

    public verregistro()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        ACopia = new CopiadorDeArchivos();
        UserGDC = "";
        RutaSitio = "";
        RutaDocumentos = "";
        TipoDoc = "";
        TipoDespliegue = "N";
        TipoBD = "";
        RutaBase = "sad\\documentos";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        String s = httpservletrequest.getParameter("ID");
        Integer integer = new Integer(s);
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
            if(ObtieneRutas() == 1)
            {
                printwriter.println("<form name='publica' method='POST' action='grabacomentario.jsp'>");
                PreparaPublica(printwriter, integer.intValue());
                printwriter.println("</form>");
            } else
            {
                printwriter.println("<TABLE border='1' align='center' width='80%'>");
                printwriter.println("<tr>");
                printwriter.println("<td colspan='2' class='texttitulotabla'>Informaci\363n de Publicaci\363n</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla' colspan='2'><B>No esta definido la ruta de destino de los archivos. <BR>Informe al administrador para solucionar el problema.</B></td>");
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

    void PreparaPublica(PrintWriter printwriter, int i)
    {
        rs = new Vector();
        rsfile = new Vector();
        String s1 = "";
        String s2 = "";
        String s3 = "N";
        boolean flag = false;
        printwriter.println("<input name='codigodocumento' value='" + i + "' type='hidden'>");
        printwriter.println("<input name='tipodespliegue' value='" + TipoDespliegue + "' type='hidden'>");
        printwriter.println("<TABLE border='1' align='center' width='80%'>");
        printwriter.println("<tr>");
        printwriter.println("<td colspan='2' class='texttitulotabla'>Informaci\363n del Archivo</td>");
        printwriter.println("</tr>");
        try
        {
            RutaBase = "sad\\documentos";
            String s = "select tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,extension,comentario,'' fuente,usuario,fechapublica,fechacaducidad,idwf,'' proyecto from sad.documentos where id = " + i;
            int j = ADatos.ConsultaDB(s);
            rsfile = ADatos.getResult();
            if(j == 1)
            {
                Integer integer = new Integer(0);
                Integer integer1 = new Integer(0);
                String s4 = (String)rsfile.elementAt(0);
                String s5 = (String)rsfile.elementAt(1);
                String s6 = (String)rsfile.elementAt(2);
                String s7 = (String)rsfile.elementAt(3);
                String s8 = (String)rsfile.elementAt(4);
                String s9 = (String)rsfile.elementAt(5);
                String s10 = (String)rsfile.elementAt(6);
                String s11 = (String)rsfile.elementAt(7);
                String s12 = (String)rsfile.elementAt(8);
                String s13 = (String)rsfile.elementAt(9);
                String s14 = (String)rsfile.elementAt(10);
                String s15 = (String)rsfile.elementAt(11);
                integer1 = (Integer)rsfile.elementAt(12);
                integer = (Integer)rsfile.elementAt(14);
                String s16 = (String)rsfile.elementAt(15);
                String s17 = s11;
                String s18 = "";
                s18 = RutaDocumentos + "sad\\documentos\\" + s5 + "\\" + s17;
                //CopiadorDeArchivos _tmp = ACopia;
                CopiadorDeArchivos.copia(s18, RutaSitio + "\\WEB-INF\\downloadroot\\" + s11);
                int k = ADatos.ConsultaDB("select sigla,descripcion from gdc.procesos where sigla = '" + s5 + "' order by sigla");
                rs = ADatos.getResult();
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Proceso</td>");
                printwriter.println("<td class='textdesttabla'>");
                printwriter.println((String)rs.elementAt(1));
                printwriter.println("</td>");
                printwriter.println("</tr>");
                k = ADatos.ConsultaDB("select sigla,descripcion from gdc.tipodocumentos where sigla = '" + s4 + "'order by sigla");
                rs = ADatos.getResult();
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Tipo Documento</td>");
                printwriter.println("<td class='textdesttabla'>");
                printwriter.println((String)rs.elementAt(1));
                printwriter.println("</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Nombre</td>");
                printwriter.println("<td class='textdesttabla'>");
                printwriter.println(s7);
                printwriter.println("</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Version Plantilla</td>");
                printwriter.println("<td class='textdesttabla'>" + s6 + "</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Nombre Archivo</td>");
                printwriter.println("<td class='textdesttabla'>" + s11 + "</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Descripci\363n</td>");
                printwriter.println("<td class='textdesttabla'>");
                if(s13.length() > 0)
                    printwriter.println(s13);
                else
                    printwriter.println("&nbsp;");
                printwriter.println("</td>");
                printwriter.println("</tr>");
                printwriter.println("</table>");
                printwriter.println("<BR><center>");
                if(integer.intValue() > 0)
                    printwriter.println("<input type='button' name='workflow' value='WorkFlow' class='fondoinput' language='javascript' onclick=\"window.open('detalleflujo.jsp?ID=" + i + "','datos');\">");
                printwriter.println("<input type='button' name='descarga' value='Descargar' class='fondoinput' language='javascript' onclick='window.open(\"descarga.jsp?FILEDESC=" + s11 + "\",\"_blank\")'>");
                printwriter.println("</center>");
            } else
            {
                printwriter.println("<tr>");
                printwriter.println("<td colspan='2' class='textdesttabla' align='center'>Documento no existe</td>");
                printwriter.println("</td>");
                printwriter.println("</tr>");
                printwriter.println("</table>");
            }
        }
        catch(IOException ioexception)
        {
            printwriter.println("<tr>");
            printwriter.println("<td colspan='2' class='textdesttabla' align='center'>Documento solicitado no existe.</td>");
            printwriter.println("</td>");
            printwriter.println("</table>");
        }
    }

    private int ObtieneRutas()
    {
        Vector vector = new Vector();
        int i = 0;
        String s = "select dirsitio,dirfiles from gdc.datosgenerales";
        ADatos.ConsultaDB(s);
        vector = ADatos.getResult();
        if(vector.size() > 0)
        {
            RutaSitio = (String)vector.elementAt(0);
            RutaDocumentos = (String)vector.elementAt(1);
            i = 1;
        }
        return i;
    }

    AccDataBase ADatos;
    funciones AFunc;
    CopiadorDeArchivos ACopia;
    Vector rs;
    Vector rsfile;
    String UserGDC;
    String RutaSitio;
    String RutaDocumentos;
    String TipoDoc;
    String TipoDespliegue;
    String TipoBD;
    String RutaBase;
}