// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   muestradoc.java

package gdc;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class muestradoc extends HttpServlet
{

    public muestradoc()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        NombreDoc = "";
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
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s = (String)httpsession.getValue("SerapisUser");
        if(s != null && s.length() > 0)
        {
            String s1 = httpservletrequest.getParameter("TIPO");
            String s2 = httpservletrequest.getParameter("TIPODOC");
            String s3 = httpservletrequest.getParameter("AREA");
            String s4 = "N";
            String s5 = "N";
            String s8 = "select perfil,administrador from sgc.perfil where proceso='" + s3 + "' and login='" + s + "'";
            rs = new Vector();
            ADatos.connect();
            int i = ADatos.ConsultaDB(s8);
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                s4 = (String)rs.elementAt(0);
                String s6 = (String)rs.elementAt(1);
            } else
            {
                s4 = "N";
                String s7 = "N";
            }
            if(s4.compareTo("N") != 0)
            {
                if(s1 == null)
                    s1 = "";
                if(s3 == null)
                    s3 = "";
                if(s2 == null)
                    s2 = "";
                printwriter.println("<html>");
                printwriter.println("<head>");
                printwriter.println("</head>");
                printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
                printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
                AFunc.cargamenu(printwriter, 1);
                ObtieneDocumentos(s2, s3, printwriter);
                if(s4.compareTo("E") == 0)
                {
                    ObtieneDocumentosFuente(s2, s3, printwriter);
                    printwriter.println("<BR>");
                    ObtieneDocumentosHistoricos(s2, s3, printwriter);
                }
                printwriter.println("</body>");
                printwriter.println("</html>");
            } else
            if(s3 == null || s3.length() == 0)
            {
                printwriter.println("<html>");
                printwriter.println("<head>");
                printwriter.println("</head>");
                printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
                printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
                AFunc.cargamenu(printwriter, 1);
                printwriter.println("</body>");
                printwriter.println("</html>");
            } else
            {
                printwriter.println("<html>");
                printwriter.println("<head>");
                printwriter.println("</head>");
                printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
                printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
                AFunc.cargamenu(printwriter, 1);
                printwriter.println("</body>");
                printwriter.println("</html>");
            }
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "GDC", 6);
        }
    }

    void ObtieneDocumentos(String s, String s1, PrintWriter printwriter)
    {
        rs = new Vector();
        int i = ADatos.ConsultaDB("select tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,extension,comentario,id,fechapublica from gdc.documentos where proceso='" + s1 + "' and fuente = 'N' and tipodoc = '" + s + "' and estado = 'A'");
        rs = ADatos.getResult();
        if(rs.size() > 0)
        {
            printwriter.println("<table border='1' width='95%' align='center'>");
            printwriter.println("<tr>");
            printwriter.println("<td class='texttituloarea' align='center'>Documentos Aprobados</td>");
            printwriter.println("<tr>");
            printwriter.println("</table>");
            printwriter.println("<table border='1' width='95%' align='center'>");
            printwriter.println("<tr>");
            printwriter.println("<td class='texttitulotabla' align='center' width='8%'>Tipo</td>");
            printwriter.println("<td class='texttitulotabla' align='center' width='40%'>Archivo</td>");
            printwriter.println("<td class='texttitulotabla' align='center' width='40%'>Descripci\363n</td>");
            printwriter.println("<td class='texttitulotabla' align='center' width='12%'>Fecha Publicaci\363n</td>");
            printwriter.println("</tr>");
            int j = 0;
            String s2 = "";
            String s4 = "";
            String s6 = "";
            Integer integer = new Integer(0);
            Integer integer2 = new Integer(0);
            for(int k = 0; k < rs.size(); k += 12)
            {
                String s3 = (String)rs.elementAt(7 + k);
                String s5 = (String)rs.elementAt(8 + k);
                String s7 = (String)rs.elementAt(9 + k);
                Integer integer1 = (Integer)rs.elementAt(10 + k);
                Integer integer3 = (Integer)rs.elementAt(11 + k);
                printwriter.println("<tr>");
                if(AFunc.ExisteArchivo(sPath + "\\images\\ext_" + s5 + ".gif") == 1)
                    printwriter.println("<td class='textdesttabla'> <a href='verarchivo.jsp?ID=" + integer1.toString() + "&TIPO=N','datos'><IMG src=../images/ext_" + s5 + ".gif width=32 height=32 border=0></a> </td>");
                else
                    printwriter.println("<td class='textdesttabla'> <a href='verarchivo.jsp?ID=" + integer1.toString() + "&TIPO=N','datos'><IMG src=../images/extdefault.gif width=32 height=32 border=0></a> </td>");
                printwriter.println("<td class='textdesttabla'> " + s3 + "</td>");
                if(s7.length() > 0)
                    printwriter.println("<td class='textdesttabla'>" + s7 + "</td>");
                else
                    printwriter.println("<td class='textdesttabla'>&nbsp;</td>");
                printwriter.println("<td class='textdesttabla'>" + AFunc.ConstruyeFecha(integer3.toString(), "/", "dmy") + "</td>");
                printwriter.println("</tr>");
                j++;
            }

            printwriter.println("</table>");
        }
    }

    void ObtieneDocumentosHistoricos(String s, String s1, PrintWriter printwriter)
    {
        rs = new Vector();
        int i = ADatos.ConsultaDB("select tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,extension,comentario,id,fechapublica from gdc.historico where proceso='" + s1 + "' and fuente = 'N' and tipodoc = '" + s + "'");
        rs = ADatos.getResult();
        if(rs.size() > 0)
        {
            printwriter.println("<table border='1' width='95%' align='center'>");
            printwriter.println("<tr>");
            printwriter.println("<td class='texttituloarea' align='center'>Documentos Hist\363ricos</td>");
            printwriter.println("<tr>");
            printwriter.println("</table>");
            printwriter.println("<table border='1' width='95%' align='center'>");
            printwriter.println("<tr>");
            printwriter.println("<td class='texttitulotabla' align='center' width='8%'>Tipo</td>");
            printwriter.println("<td class='texttitulotabla' align='center' width='40%'>Archivo</td>");
            printwriter.println("<td class='texttitulotabla' align='center' width='40%'>Descripci\363n</td>");
            printwriter.println("<td class='texttitulotabla' align='center' width='12%'>Fecha Publicaci\363n</td>");
            printwriter.println("</tr>");
            int j = 0;
            String s2 = "";
            String s4 = "";
            String s6 = "";
            Integer integer = new Integer(0);
            Integer integer2 = new Integer(0);
            for(int k = 0; k < rs.size(); k += 12)
            {
                String s3 = (String)rs.elementAt(7 + k);
                String s5 = (String)rs.elementAt(8 + k);
                String s7 = (String)rs.elementAt(9 + k);
                Integer integer1 = (Integer)rs.elementAt(10 + k);
                Integer integer3 = (Integer)rs.elementAt(11 + k);
                printwriter.println("<tr>");
                if(AFunc.ExisteArchivo(sPath + "\\images\\ext_" + s5 + ".gif") == 1)
                    printwriter.println("<td class='textdesttabla'> <a href='verarchivo.jsp?ID=" + integer1.toString() + "&TIPO=H','datos'><IMG src=../images/ext_" + s5 + ".gif width=32 height=32 border=0></a> </td>");
                else
                    printwriter.println("<td class='textdesttabla'> <a href='verarchivo.jsp?ID=" + integer1.toString() + "&TIPO=H','datos'><IMG src=../images/extdefault.gif width=32 height=32 border=0></a> </td>");
                printwriter.println("<td class='textdesttabla'> " + s3 + "</td>");
                if(s7.length() > 0)
                    printwriter.println("<td class='textdesttabla'>" + s7 + "</td>");
                else
                    printwriter.println("<td class='textdesttabla'>&nbsp;</td>");
                printwriter.println("<td class='textdesttabla'>" + AFunc.ConstruyeFecha(integer3.toString(), "/", "dmy") + "</td>");
                printwriter.println("</tr>");
                j++;
            }

            printwriter.println("</table>");
        }
    }

    void ObtieneDocumentosFuente(String s, String s1, PrintWriter printwriter)
    {
        rs = new Vector();
        String s2 = "";
        String s5 = "";
        String s8 = "";
        Integer integer = new Integer(0);
        Integer integer3 = new Integer(0);
        String s11 = "";
        Integer integer6 = new Integer(0);
        String s13 = "";
        int k = ADatos.ConsultaDB("select tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,extension,comentario,id,fechapublica from gdc.documentos where proceso='" + s1 + "' and fuente = 'S' and tipodoc = '" + s + "' and estado = 'A'");
        rs = ADatos.getResult();
        if(rs.size() > 0)
        {
            printwriter.println("<BR><BR>");
            printwriter.println("<table border='1' width='95%' align='center'>");
            printwriter.println("<tr>");
            printwriter.println("<td class='texttituloarea' align='center'>Archivos Fuentes del \301rea</td>");
            printwriter.println("<tr>");
            printwriter.println("</table>");
            printwriter.println("<table border='1' width='95%' align='center'>");
            printwriter.println("<tr>");
            printwriter.println("<td class='texttitulotabla' align='center' width='8%'>Tipo</td>");
            printwriter.println("<td class='texttitulotabla' align='center' width='40%'>Archivo</td>");
            printwriter.println("<td class='texttitulotabla' align='center' width='40%'>Descripci\363n</td>");
            printwriter.println("<td class='texttitulotabla' align='center' width='12%'>Fecha Publicaci\363n</td>");
            printwriter.println("</tr>");
            int i = 0;
            for(int i1 = 0; i1 < rs.size(); i1 += 12)
            {
                String s3 = (String)rs.elementAt(7 + i1);
                String s6 = (String)rs.elementAt(8 + i1);
                String s9 = (String)rs.elementAt(9 + i1);
                Integer integer1 = (Integer)rs.elementAt(10 + i1);
                Integer integer4 = (Integer)rs.elementAt(11 + i1);
                printwriter.println("<tr>");
                if(AFunc.ExisteArchivo(sPath + "images\\ext_" + s6 + ".gif") == 1)
                    printwriter.println("<td class='textdesttabla'> <a href='verarchivo.jsp?ID=" + integer1.toString() + "&TIPO=N','datos'><IMG src=../images/ext_" + s6 + ".gif width=32 height=32 border=0></a> </td>");
                else
                    printwriter.println("<td class='textdesttabla'> <a href='verarchivo.jsp?ID=" + integer1.toString() + "&TIPO=N','datos'><IMG src=../images/extdefault.gif width=32 height=32 border=0></a> </td>");
                printwriter.println("<td class='textdesttabla'> " + s3 + "</td>");
                if(s9.length() > 0)
                    printwriter.println("<td class='textdesttabla'>" + s9 + "</td>");
                else
                    printwriter.println("<td class='textdesttabla'>&nbsp;</td>");
                printwriter.println("<td class='textdesttabla'>" + AFunc.ConstruyeFecha(integer4.toString(), "/", "dmy") + "</td>");
                printwriter.println("</tr>");
                i++;
            }

            printwriter.println("</table>");
            rs = new Vector();
            int l = ADatos.ConsultaDB("select tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,extension,comentario,id,fechapublica,fuente,fechahistorico,usuariohistorico from gdc.historico where proceso='" + s1 + "' and tipodoc = '" + s + "'");
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                printwriter.println("<BR>");
                printwriter.println("<table border='1' width='95%' align='center'>");
                printwriter.println("<tr>");
                printwriter.println("<td class='texttituloarea' align='center'>Archivos Hist\363ricos \301rea</td>");
                printwriter.println("<tr>");
                printwriter.println("</table>");
                printwriter.println("<table border='1' width='95%' align='center'>");
                printwriter.println("<tr>");
                printwriter.println("<td class='texttitulotabla' align='center' width='5%'>Tipo</td>");
                printwriter.println("<td class='texttitulotabla' align='center' width='30%'>Archivo</td>");
                printwriter.println("<td class='texttitulotabla' align='center' width='5%'>Cat.</td>");
                printwriter.println("<td class='texttitulotabla' align='center' width='30%'>Descripci\363n</td>");
                printwriter.println("<td class='texttitulotabla' align='center' width='10%'>Fecha Publicaci\363n</td>");
                printwriter.println("<td class='texttitulotabla' align='center' width='10%'>Fecha Hist\363rico</td>");
                printwriter.println("<td class='texttitulotabla' align='center' width='10%'>Usuario</td>");
                printwriter.println("</tr>");
                int j = 0;
                for(int j1 = 0; j1 < rs.size(); j1 += 15)
                {
                    String s4 = (String)rs.elementAt(7 + j1);
                    String s7 = (String)rs.elementAt(8 + j1);
                    String s10 = (String)rs.elementAt(9 + j1);
                    Integer integer2 = (Integer)rs.elementAt(10 + j1);
                    Integer integer5 = (Integer)rs.elementAt(11 + j1);
                    String s12 = (String)rs.elementAt(12 + j1);
                    Integer integer7 = (Integer)rs.elementAt(13 + j1);
                    String s14 = (String)rs.elementAt(14 + j1);
                    printwriter.println("<tr>");
                    if(AFunc.ExisteArchivo(sPath + "\\images\\ext_" + s7 + ".gif") == 1)
                        printwriter.println("<td class='textdesttabla'> <a href='verarchivo.jsp?ID=" + integer2.toString() + "&TIPO=H','datos'><IMG src=../images/ext_" + s7 + ".gif width=32 height=32 border=0></a> </td>");
                    else
                        printwriter.println("<td class='textdesttabla'> <a href='verarchivo.jsp?ID=" + integer2.toString() + "&TIPO=H','datos'><IMG src=../images/extdefault.gif width=32 height=32 border=0></a> </td>");
                    printwriter.println("<td class='textdesttabla'> " + s4 + "</td>");
                    if(s12.compareTo("S") == 0)
                        printwriter.println("<td class='textdesttabla'>F</td>");
                    else
                        printwriter.println("<td class='textdesttabla'>P</td>");
                    if(s10.length() > 0)
                        printwriter.println("<td class='textdesttabla'>" + s10 + "</td>");
                    else
                        printwriter.println("<td class='textdesttabla'>&nbsp;</td>");
                    printwriter.println("<td class='textdesttabla'>" + AFunc.ConstruyeFecha(integer5.toString(), "/", "dmy") + "</td>");
                    if(integer7.longValue() > 0L)
                        printwriter.println("<td class='textdesttabla'>" + AFunc.ConstruyeFecha(integer7.toString(), "/", "dmy") + "</td>");
                    else
                        printwriter.println("<td class='textdesttabla'>&nbsp;</td>");
                    if(s14.length() > 0)
                        printwriter.println("<td class='textdesttabla'>" + s14 + "</td>");
                    else
                        printwriter.println("<td class='textdesttabla'>&nbsp;</td>");
                    printwriter.println("</tr>");
                    j++;
                }

                printwriter.println("</table>");
            }
        }
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    String NombreDoc;
    String sPath;
}