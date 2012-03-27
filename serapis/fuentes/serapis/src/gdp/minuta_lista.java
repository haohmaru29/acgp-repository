// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   minuta_lista.java

package gdp;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class minuta_lista extends HttpServlet
{

    public minuta_lista()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        EsAdmin = "N";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        Integer integer = new Integer(0);
        String s3 = "";
        String s5 = "";
        String s7 = "";
        String s9 = "";
        String s11 = "";
        String s13 = "";
        Integer integer2 = new Integer(0);
        Integer integer4 = new Integer(0);
        String s15 = "";
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s17 = (String)httpsession.getValue("SerapisUser");
        if(s17 != null && s17.length() > 0)
        {
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
            
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<form name='frmplan' action='gg_grafico_gestion.jsp' method='POST'>");
            printwriter.println("<table border='1' width='95%'  align='center'>");
            printwriter.println("<tr><td width='80%' colspan='4' class='texttituloarea' align='center'>Minutas Comit\351</td></tr>");
            rs = new Vector();
            String s = "SELECT minuta from gdc.gestion_procesos";
            int j = ADatos.ConsultaDB(s);
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                Integer integer1 = (Integer)rs.elementAt(0);
                String s1 = "SELECT tipodocumento, proceso, descripcion,tipo from gdc.documentoscalidad where id = " + integer1.toString();
                rs.clear();
                rs = new Vector();
                int k = ADatos.ConsultaDB(s1);
                rs = ADatos.getResult();
                if(rs.size() > 0)
                {
                    String s4 = (String)rs.elementAt(0);
                    String s6 = (String)rs.elementAt(1);
                    String s8 = (String)rs.elementAt(2);
                    String s10 = (String)rs.elementAt(3);
                    String s2 = "SELECT id,nombrearchivo,comentario,fechapublica,extension from sad.documentos ";
                    s2 = s2 + " where tipodoc = '" + s4 + "'";
                    s2 = s2 + " and proceso = '" + s6 + "'";
                    s2 = s2 + " and descripcion = '" + s8 + "'";
                    s2 = s2 + " and estado = 'A'";
                    s2 = s2 + " order by fechapublica desc";
                    rs.clear();
                    rs = new Vector();
                    int l = ADatos.ConsultaDB(s2);
                    rs = ADatos.getResult();
                    if(rs.size() > 0)
                    {
                        printwriter.println("<tr>");
                        printwriter.println("<td class='texttitulotabla' align='left' width='5%'>&nbsp;</td>");
                        printwriter.println("<td class='texttitulotabla' align='left' width='40%'>Documento</td>");
                        printwriter.println("<td class='texttitulotabla' align='left' width='40%'>Descripci\363n</td>");
                        printwriter.println("<td class='texttitulotabla' align='left' width='15%'>Fecha Publicaci\363n</td>");
                        printwriter.println("</tr>");
                        for(int i = 0; i < rs.size(); i += 5)
                        {
                            Integer integer5 = (Integer)rs.elementAt(i);
                            String s12 = (String)rs.elementAt(i + 1);
                            String s14 = (String)rs.elementAt(i + 2);
                            Integer integer3 = (Integer)rs.elementAt(i + 3);
                            String s16 = (String)rs.elementAt(i + 4);
                            printwriter.println("<tr>");
                            if(AFunc.ExisteArchivo(sPath + "\\images\\ext_" + s16 + ".gif") == 1)
                                printwriter.println("<td class='textdesttabla'> <a href='verregistro.jsp?ID=" + integer5.toString() + "'><IMG src=../images/ext_" + s16 + ".gif width=32 height=32 border=0></a> </td>");
                            else
                                printwriter.println("<td class='textdesttabla'> <a href='verregistro.jsp?ID=" + integer5.toString() + "'><IMG src=../images/extdefault.gif width=32 height=32 border=0></a> </td>");
                            printwriter.println("<td class='textdesttabla' align='left'>" + s12 + "</td>");
                            if(s14.length() > 0)
                                printwriter.println("<td class='textdesttabla' align='left'>" + s14 + "</td>");
                            else
                                printwriter.println("<td class='textdesttabla' align='left'>&nbsp;</td>");
                            printwriter.println("<td class='textdesttabla' align='left'>" + AFunc.ConstruyeFecha(integer3.toString(), "/", "dmy") + "</td>");
                            printwriter.println("</tr>");
                        }

                    } else
                    {
                        printwriter.println("<tr>");
                        printwriter.println("<td class='textdesttabla' align='left' colspan='3'>No se encontraron registros de Minutas.</td>");
                        printwriter.println("</tr>");
                    }
                } else
                {
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla' align='left' colspan='3'>Registro no definido.</td>");
                    printwriter.println("</tr>");
                }
            } else
            {
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla' align='left' colspan='3'>No esta definido el registro de Minutas.</td>");
                printwriter.println("</tr>");
            }
            printwriter.println("</table>");
            printwriter.println("</form>");
            printwriter.println("</body>");
            printwriter.println("</html>");
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "CONF", 6);
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
    String EsAdmin;
}