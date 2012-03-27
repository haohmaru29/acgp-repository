// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   registro_auditoria.java

package gdp;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class registro_auditoria extends HttpServlet
{

    public registro_auditoria()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        EsAdmin = "N";

    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        String s4 = "";
        String s6 = "";
        String s8 = "";
        String s10 = "";
        String s12 = "";
        String s13 = "";
        String s15 = "";
        Integer integer = new Integer(0);
        Integer integer2 = new Integer(0);
        Integer integer4 = new Integer(0);
        String s17 = "";
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s19 = (String)httpsession.getValue("SerapisUser");
        if(s19 != null && s19.length() > 0)
        {
            ADatos.connect();
            
        	String sPath = "";
        	String s0 = "select dirsitio from gdc.datosgenerales";
            int k1 = ADatos.ConsultaDB(s0);
            if (k1>0) {
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
            printwriter.println("<tr><td width='80%' colspan='5' class='texttituloarea' align='center'>Registros de Auditor\355a</td></tr>");
            rs = new Vector();
            String s = "SELECT doc_auditoria from gdc.gestion_procesos";
            int l = ADatos.ConsultaDB(s);
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                String s5 = (String)rs.elementAt(0);
                String s1 = "SELECT tipodocumento,proceso,descripcion from gdc.documentoscalidad where id IN (" + s5 + ")";
                rs.clear();
                rs = new Vector();
                int i1 = ADatos.ConsultaDB(s1);
                rs = ADatos.getResult();
                if(rs.size() > 0)
                {
                    String s2 = "";
                    for(int i = 0; i < rs.size(); i += 3)
                    {
                        String s7 = (String)rs.elementAt(i);
                        String s9 = (String)rs.elementAt(i + 1);
                        String s11 = (String)rs.elementAt(i + 2);
                        if(s2.length() > 0)
                            s2 = s2 + " or (tipodoc = '" + s7 + "' and proceso = '" + s9 + "' and descripcion='" + s11 + "')";
                        else
                            s2 = s2 + "(tipodoc = '" + s7 + "' and proceso = '" + s9 + "' and descripcion='" + s11 + "')";
                    }

                    s2 = "SELECT id from sad.documentos where ( " + s2 + ")";
                    rs = new Vector();
                    rs.clear();
                    int j1 = ADatos.ConsultaDB(s2);
                    rs = ADatos.getResult();
                    if(rs.size() > 0)
                    {
                        String s20 = "";
                        for(int j = 0; j < rs.size(); j++)
                            if(s20.length() > 0)
                                s20 = s20 + "," + ((Integer)rs.elementAt(j)).toString();
                            else
                                s20 = ((Integer)rs.elementAt(j)).toString();

                        
                        String s3 = "SELECT id,extension,nombrearchivo,comentario,fechapublica,id_auditoria from sad.documentos";
                        s3 = s3 + " WHERE estado = 'A' and id IN (" + s20 + ") order by id_auditoria";
                        rs = new Vector();
                        rs.clear();
                        k1 = ADatos.ConsultaDB(s3);
                        rs = ADatos.getResult();
                        if(rs.size() > 0)
                        {
                        	
                            printwriter.println("<tr>");
                            printwriter.println("<td class='texttitulotabla' align='left' width='5%'>&nbsp;</td>");
                            printwriter.println("<td class='texttitulotabla' align='left' width='5%'>&nbsp;</td>");
                            printwriter.println("<td class='texttitulotabla' align='left' width='40%'>Documento</td>");
                            printwriter.println("<td class='texttitulotabla' align='left' width='40%'>Descripci\363n</td>");
                            printwriter.println("<td class='texttitulotabla' align='left' width='10%'>Fecha Publicaci\363n</td>");
                            printwriter.println("</tr>");
                            for(int k = 0; k < rs.size(); k += 6)
                            {
                                Integer integer3 = (Integer)rs.elementAt(k);
                                String s18 = (String)rs.elementAt(k + 1);
                                String s14 = (String)rs.elementAt(k + 2);
                                String s16 = (String)rs.elementAt(k + 3);
                                Integer integer1 = (Integer)rs.elementAt(k + 4);
                                Integer integer5 = (Integer)rs.elementAt(k + 5);
                                printwriter.println("<tr>");
                                //if(AFunc.ExisteArchivo("C:\\Archivos de programa\\Apache Tomcat 4.0\\webapps\\serapis\\images\\ext_" + s18 + ".gif") == 1)
                                //httpservletrequest.getContextPath().;
                                if(AFunc.ExisteArchivo(sPath + "\\images\\ext_" + s18 + ".gif") == 1)
                                    printwriter.println("<td class='textdesttabla'> <a href='auditoria_asociar.jsp?ID=" + integer3.toString() + "'><IMG src=../images/ext_" + s18 + ".gif width=32 height=32 border=0></a> </td>");
                                else
                                    printwriter.println("<td class='textdesttabla'> <a href='auditoria_asociar.jsp?ID=" + integer3.toString() + "'><IMG src=../images/extdefault.gif width=32 height=32 border=0></a> </td>");
                                if(integer5.longValue() > 0L)
                                    printwriter.println("<td class='textdesttabla' align='left'><img src='../images/ok.jpg' border='0'></td>");
                                else
                                    printwriter.println("<td class='textdesttabla' align='left'>&nbsp;</td>");
                                printwriter.println("<td class='textdesttabla' align='left'>" + s14 + "</td>");
                                System.out.println("s16=" + s16);
                                if(s16.length() > 0)
                                    printwriter.println("<td class='textdesttabla' align='left'>" + s16 + "</td>");
                                else
                                    printwriter.println("<td class='textdesttabla' align='left'>&nbsp;</td>");
                                printwriter.println("<td class='textdesttabla' align='left'>" + AFunc.ConstruyeFecha(integer1.toString(), "/", "dmy") + "</td>");
                                printwriter.println("</tr>");
                            }

                        }
                    } else
                    {
                        printwriter.println("<tr>");
                        printwriter.println("<td class='textdesttabla' align='left' colspan='3'>No se encontraron registros de Auditor\355a.</td>");
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
                printwriter.println("<td class='textdesttabla' align='left' colspan='3'>No estan definidos los registros de Auditor\355a.</td>");
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