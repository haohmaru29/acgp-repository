// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   desestimados.java

package sad;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class desestimados extends HttpServlet
{

    public desestimados()
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
            int i = ObtieneDocumentos(printwriter);
            int j = ObtieneDocumentosp(printwriter);
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

    int ObtieneDocumentos(PrintWriter printwriter)
    {
        int i = 0;
        ADatos.connect();
        

        rs = new Vector();
        
    	String sPath = "";
    	String sqlDirSitio = "select dirsitio from gdc.datosgenerales";
        int iDirSitio = ADatos.ConsultaDB(sqlDirSitio);
        if (iDirSitio>0) {
	        rs = ADatos.getResult();
	        if (rs.size()>0) 
	        	sPath = (String)rs.elementAt(0);
	        else
	        	sPath = "C:\\Archivos de programa\\Apache Tomcat 4.0\\webapps\\serapis";
	        
	        rs.clear();
    	} 
        
        rs = new Vector();
        String s1 = "";
        String s3 = "";
        String s5 = "";
        String s6 = "";
        String s9 = "";
        String s12 = "";
        Integer integer = new Integer(0);
        Integer integer3 = new Integer(0);
        String s = "select td.descripcion desctipodoc,p.descripcion nombreproceso,d.version,d.descripcion,d.cliente,d.adicional,d.verdoc, ";
        s = s + " d.nombrearchivo,d.extension,d.comentario,d.id,d.fechapublica ";
        s = s + " from sad.documentos d, gdc.procesos p, gdc.tipodocumentos td ";
        s = s + " where d.proceso = p.sigla and d.tipodoc = td.sigla and d.estado = 'R' ";
        int j = ADatos.ConsultaDB(s);
        rs = ADatos.getResult();
        if(rs.size() > 0)
        {
            i = rs.size() / 12;
            printwriter.println("<table border='1' width='95%' align='center'>");
            printwriter.println("<tr>");
            printwriter.println("<td class='texttituloarea' align='center'>Registros Rechazados</td>");
            printwriter.println("<tr>");
            printwriter.println("</table>");
            for(int k = 0; k < rs.size(); k += 12)
            {
                String s2 = (String)rs.elementAt(k);
                String s4 = (String)rs.elementAt(k + 1);
                if(s4.compareTo(s5) == 0)
                {
                    String s7 = (String)rs.elementAt(7 + k);
                    String s10 = (String)rs.elementAt(8 + k);
                    String s13 = (String)rs.elementAt(9 + k);
                    Integer integer1 = (Integer)rs.elementAt(10 + k);
                    Integer integer4 = (Integer)rs.elementAt(11 + k);
                    printwriter.println("<tr>");
                    if(AFunc.ExisteArchivo(sPath + "\\images\\ext_" + s10 + ".gif") == 1)
                        printwriter.println("<td class='textdesttabla'> <a href='verdesestimado.jsp?ID=" + integer1.toString() + "&TIPO=N&BD=SAD','datos'><IMG src=../images/ext_" + s10 + ".gif width=32 height=32 border=0></a> </td>");
                    else
                        printwriter.println("<td class='textdesttabla'> <a href='verdesestimado.jsp?ID=" + integer1.toString() + "&TIPO=N&BD=SAD','datos'><IMG src=../images/extdefault.gif width=32 height=32 border=0></a> </td>");
                    printwriter.println("<td class='textdesttabla'> " + s2 + "</td>");
                    printwriter.println("<td class='textdesttabla'>" + s7 + "</td>");
                    if(s13.length() > 0)
                        printwriter.println("<td class='textdesttabla'>" + s13 + "</td>");
                    else
                        printwriter.println("<td class='textdesttabla'>&nbsp;</td>");
                    printwriter.println("</tr>");
                    continue;
                }
                if(s5.length() == 0)
                {
                    printwriter.println("<table border='1' width='95%' align='center'>");
                    printwriter.println("<tr><td class='texttituloarea' align='left' width='8%' colspan='4'>" + s4 + "</td>");
                }
                s5 = s4;
                printwriter.println("<tr>");
                printwriter.println("<td class='texttitulotabla' align='center' width='8%'>&nbsp;</td>");
                printwriter.println("<td class='texttitulotabla' align='center' width='8%'>Tipo Documento</td>");
                printwriter.println("<td class='texttitulotabla' align='center' width='40%'>Archivo</td>");
                printwriter.println("<td class='texttitulotabla' align='center' width='40%'>Descripci\363n</td>");
                printwriter.println("</tr>");
                String s8 = (String)rs.elementAt(7 + k);
                String s11 = (String)rs.elementAt(8 + k);
                String s14 = (String)rs.elementAt(9 + k);
                Integer integer2 = (Integer)rs.elementAt(10 + k);
                Integer integer5 = (Integer)rs.elementAt(11 + k);
                printwriter.println("<tr>");
                if(AFunc.ExisteArchivo(sPath + "\\images\\ext_" + s11 + ".gif") == 1)
                    printwriter.println("<td class='textdesttabla'> <a href='verdesestimado.jsp?ID=" + integer2.toString() + "&TIPO=N&BD=SAD','datos'><IMG src=../images/ext_" + s11 + ".gif width=32 height=32 border=0></a> </td>");
                else
                    printwriter.println("<td class='textdesttabla'> <a href='verdesestimado.jsp?ID=" + integer2.toString() + "&TIPO=N&BD=SAD','datos'><IMG src=../images/extdefault.gif width=32 height=32 border=0></a> </td>");
                printwriter.println("<td class='textdesttabla'> " + s2 + "</td>");
                printwriter.println("<td class='textdesttabla'>" + s8 + "</td>");
                if(s14.length() > 0)
                    printwriter.println("<td class='textdesttabla'>" + s14 + "</td>");
                else
                    printwriter.println("<td class='textdesttabla'>&nbsp;</td>");
                printwriter.println("</tr>");
            }

            printwriter.println("</table>");
        }
        return i;
    }

    int ObtieneDocumentosp(PrintWriter printwriter)
    {
        int i = 0;
        ADatos.connect();

        rs = new Vector();
        
    	String sPath = "";
    	String sqlDirSitio = "select dirsitio from gdc.datosgenerales";
        int iDirSitio = ADatos.ConsultaDB(sqlDirSitio);
        if (iDirSitio>0) {
	        rs = ADatos.getResult();
	        if (rs.size()>0) 
	        	sPath = (String)rs.elementAt(0);
	        else
	        	sPath = "C:\\Archivos de programa\\Apache Tomcat 4.0\\webapps\\serapis";
	        
	        rs.clear();
    	} 
        
        rs = new Vector();
        String s1 = "";
        String s3 = "";
        String s5 = "";
        String s6 = "";
        String s9 = "";
        String s12 = "";
        Integer integer = new Integer(0);
        Integer integer3 = new Integer(0);
        String s = "select td.descripcion desctipodoc,p.descripcion nombreproceso,d.version,d.descripcion,d.cliente,d.adicional,d.verdoc, ";
        s = s + " d.nombrearchivo,d.extension,d.comentario,d.id,d.fechapublica ";
        s = s + " from sad.documentosp d, gdc.procesos p, gdc.tipodocumentos td ";
        s = s + " where d.proceso = p.sigla and d.tipodoc = td.sigla and d.estado = 'R' ";
        int j = ADatos.ConsultaDB(s);
        rs = ADatos.getResult();
        if(rs.size() > 0)
        {
            i = rs.size() / 12;
            printwriter.println("<table border='1' width='95%' align='center'>");
            printwriter.println("<tr>");
            printwriter.println("<td class='texttituloarea' align='center'>Registros (de clientes) Rechazados</td>");
            printwriter.println("<tr>");
            printwriter.println("</table>");
            for(int k = 0; k < rs.size(); k += 12)
            {
                String s2 = (String)rs.elementAt(k);
                String s4 = (String)rs.elementAt(k + 1);
                if(s4.compareTo(s5) == 0)
                {
                    String s7 = (String)rs.elementAt(7 + k);
                    String s10 = (String)rs.elementAt(8 + k);
                    String s13 = (String)rs.elementAt(9 + k);
                    Integer integer1 = (Integer)rs.elementAt(10 + k);
                    Integer integer4 = (Integer)rs.elementAt(11 + k);
                    printwriter.println("<tr>");
                    if(AFunc.ExisteArchivo(sPath + "\\images\\ext_" + s10 + ".gif") == 1)
                        printwriter.println("<td class='textdesttabla'> <a href='verdesestimado.jsp?ID=" + integer1.toString() + "&TIPO=N&BD=SADP','datos'><IMG src=../images/ext_" + s10 + ".gif width=32 height=32 border=0></a> </td>");
                    else
                        printwriter.println("<td class='textdesttabla'> <a href='verdesestimado.jsp?ID=" + integer1.toString() + "&TIPO=N&BD=SADP','datos'><IMG src=../images/extdefault.gif width=32 height=32 border=0></a> </td>");
                    printwriter.println("<td class='textdesttabla'> " + s2 + "</td>");
                    printwriter.println("<td class='textdesttabla'>" + s7 + "</td>");
                    if(s13.length() > 0)
                        printwriter.println("<td class='textdesttabla'>" + s13 + "</td>");
                    else
                        printwriter.println("<td class='textdesttabla'>&nbsp;</td>");
                    printwriter.println("</tr>");
                    continue;
                }
                if(s5.length() == 0)
                {
                    printwriter.println("<table border='1' width='95%' align='center'>");
                    printwriter.println("<tr><td class='texttituloarea' align='left' width='8%' colspan='4'>" + s4 + "</td>");
                }
                s5 = s4;
                printwriter.println("<tr>");
                printwriter.println("<td class='texttitulotabla' align='center' width='8%'>&nbsp;</td>");
                printwriter.println("<td class='texttitulotabla' align='center' width='8%'>Tipo Documento</td>");
                printwriter.println("<td class='texttitulotabla' align='center' width='40%'>Archivo</td>");
                printwriter.println("<td class='texttitulotabla' align='center' width='40%'>Descripci\363n</td>");
                printwriter.println("</tr>");
                String s8 = (String)rs.elementAt(7 + k);
                String s11 = (String)rs.elementAt(8 + k);
                String s14 = (String)rs.elementAt(9 + k);
                Integer integer2 = (Integer)rs.elementAt(10 + k);
                Integer integer5 = (Integer)rs.elementAt(11 + k);
                printwriter.println("<tr>");
                if(AFunc.ExisteArchivo(sPath + "\\images\\ext_" + s11 + ".gif") == 1)
                    printwriter.println("<td class='textdesttabla'> <a href='verdesestimado.jsp?ID=" + integer2.toString() + "&TIPO=N&BD=SADP','datos'><IMG src=../images/ext_" + s11 + ".gif width=32 height=32 border=0></a> </td>");
                else
                    printwriter.println("<td class='textdesttabla'> <a href='verdesestimado.jsp?ID=" + integer2.toString() + "&TIPO=N&BD=SADP','datos'><IMG src=../images/extdefault.gif width=32 height=32 border=0></a> </td>");
                printwriter.println("<td class='textdesttabla'> " + s2 + "</td>");
                printwriter.println("<td class='textdesttabla'>" + s8 + "</td>");
                if(s14.length() > 0)
                    printwriter.println("<td class='textdesttabla'>" + s14 + "</td>");
                else
                    printwriter.println("<td class='textdesttabla'>&nbsp;</td>");
                printwriter.println("</tr>");
            }

            printwriter.println("</table>");
        }
        return i;
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
}