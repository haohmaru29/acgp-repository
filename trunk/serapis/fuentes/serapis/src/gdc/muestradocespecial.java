// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   muestradocespecial.java

package gdc;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class muestradocespecial extends HttpServlet
{

    public muestradocespecial()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        NombreDoc = "";
    	sPath = "";
    	ADatos.connect();
    	String s0 = "select dirsitio from gdc.datosgenerales";
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
        String sTipo = (httpservletrequest.getParameter("TIPO")!=null)?httpservletrequest.getParameter("TIPO").toString().trim():"";
        if(s != null && s.length() > 0)
        {
            String s1 = "DOC";
            String s2 = httpservletrequest.getParameter("TIPODOC");
            String s3 = httpservletrequest.getParameter("AREA");
            String s4 = httpservletrequest.getParameter("DESCRIPCION");
            String s5 = "N";
            String s6 = "N";
            if (sTipo.equals("R"))
            {                    	
            	sad.muestradoc oAcc = new sad.muestradoc();
            	String sAcceso = oAcc.TieneAcceso(printwriter, "PROCESO", s3 , 0L, (String)httpservletrequest.getSession(true).getValue("SerapisUser"));
            	if(sAcceso.compareTo("00000") == 0 || sAcceso.compareTo("00001") == 0) {
                    printwriter.println("<html>");
                    printwriter.println("<head>");
                    printwriter.println("</head>");
                    printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
                    printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
                    printwriter.println("<center>No tiene permisos para ver documentos</center>");
                    printwriter.println("<BR><center><input type='button' name='BtnRegresar' value='Volver' class='fondoinput' language='javascript' onclick=\"window.open('inicio.jsp?TIPO=LM" + sTipo + "','cuerpo')\"></center></td>");
                    printwriter.println("</body>");
                    printwriter.println("</html>");
            		
            		return;
            	}
            		
            }
            
            String s9 = "select perfil,administrador from sgc.perfil where proceso='" + s3 + "' and login='" + s + "'";
            rs = new Vector();
            ADatos.connect();
            int i = ADatos.ConsultaDB(s9);
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                s5 = (String)rs.elementAt(0);
                //String s7 = (String)rs.elementAt(1);
            } else
            {
                s5 = "N";
                //String s8 = "N";
            }
            if(s5.compareTo("N") != 0)
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
                ObtieneDocumentos(s2, s3, s4, printwriter, sTipo);
                if(s5.compareTo("E") == 0)
                    ObtieneDocumentosFuente(s2, s3, s4, printwriter, sTipo);
               	printwriter.println("<BR><center><input type='button' name='BtnRegresar' value='Volver' class='fondoinput' language='javascript' onclick=\"window.open('inicio.jsp?TIPO=LM" + sTipo + "','cuerpo')\"></center></td>");
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

    void ObtieneDocumentos(String s, String s1, String s2, PrintWriter printwriter){
    	ObtieneDocumentos(s, s1, s2, printwriter, "" );
    }
    
    void ObtieneDocumentos(String s, String s1, String s2, PrintWriter printwriter, String sTipo )
    {
    	    	   	
        rs = new Vector();
        int i;
        if (sTipo.equals("R"))
        	i = ADatos.ConsultaDB("select tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,extension,comentario,id from sad.documentos where proceso='" + s1 + "' and tipodoc = '" + s + "' and descripcion = '" + s2 + "'");
        else
        	i = ADatos.ConsultaDB("select tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,extension,comentario,id from gdc.documentos where proceso='" + s1 + "' and fuente = 'N' and tipodoc = '" + s + "' and descripcion = '" + s2 + "'");
        
        rs = ADatos.getResult();
        if(rs.size() > 0)
        {
            printwriter.println("<table border='1' width='95%' align='center'>");
            printwriter.println("<tr>");
            printwriter.println("<td class='texttituloarea' align='center'>Archivos Publicados del \301rea</td>");
            printwriter.println("<tr>");
            printwriter.println("</table>");
            printwriter.println("<table border='1' width='95%' align='center'>");
            printwriter.println("<tr>");
            printwriter.println("<td class='texttitulotabla' align='center'>Tipo</td>");
            printwriter.println("<td class='texttitulotabla' align='center'>Archivo</td>");
            printwriter.println("<td class='texttitulotabla' align='center'>Descripci\363n</td>");
            printwriter.println("</tr>");
            int j = 0;
            String s3 = "";
            String s5 = "";
            String s7 = "";
            Integer integer = new Integer(0);
            for(int k = 0; k < rs.size(); k += 11)
            {
                String s4 = (String)rs.elementAt(7 + k);
                String s6 = (String)rs.elementAt(8 + k);
                String s8 = (String)rs.elementAt(9 + k);
                Integer integer1 = (Integer)rs.elementAt(10 + k);
                printwriter.println("<tr>");
                /*
                if(AFunc.ExisteArchivo(sPath + "\\images\\ext_" + s6 + ".gif") == 1)
                    printwriter.println("<td class='textdesttabla'> <a href='" + (sTipo.equals("R")?"../sad/":"") + "verarchivo.jsp?ID=" + integer1.toString() + "&T=" + sTipo + "','datos'><IMG src=../images/ext_" + s6 + ".gif width=32 height=32 border=0></a> </td>");
                else
                    printwriter.println("<td class='textdesttabla'> <a href='" + (sTipo.equals("R")?"../sad/":"") + "verarchivo.jsp?ID=" + integer1.toString() + "&T=" + sTipo + "','datos'><IMG src=../images/extdefault.gif width=32 height=32 border=0></a> </td>");
               */
                if(AFunc.ExisteArchivo(sPath + "\\images\\ext_" + s6.toLowerCase() + ".gif") == 1)
                	printwriter.println("<td class='textdesttabla'> <a href='verarchivo.jsp?ID=" + integer1.toString() + "&T=" + sTipo + "','datos'><IMG src=../images/ext_" + s6.toLowerCase() + ".gif width=32 height=32 border=0></a> </td>");
                else
                	printwriter.println("<td class='textdesttabla'> <a href='verarchivo.jsp?ID=" + integer1.toString() + "&T=" + sTipo + "','datos'><IMG src=../images/extdefault.gif width=32 height=32 border=0></a> </td>");
                
                printwriter.println("<td class='textdesttabla'> " + s4 + "</td>");
                if(s8.length() > 0)
                    printwriter.println("<td class='textdesttabla'>" + s8 + "</td>");
                else
                    printwriter.println("<td class='textdesttabla'>&nbsp;</td>");
                printwriter.println("</tr>");
                j++;
            }

            printwriter.println("</table>");
        }
    }
    
    void ObtieneDocumentosFuente(String s, String s1, String s2, PrintWriter printwriter) {
    	ObtieneDocumentosFuente(s, s1, s2, printwriter, "");
    }
    
    void ObtieneDocumentosFuente(String s, String s1, String s2, PrintWriter printwriter, String sTipo)
    {
        rs = new Vector();
        int i; 
        if (sTipo.equals("R"))
        	i = ADatos.ConsultaDB("select tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,extension,comentario,id from sad.documentos where proceso='" + s1 + "' and tipodoc = '" + s + "' and descripcion = '" + s2 + "'");
        else
        	i = ADatos.ConsultaDB("select tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,extension,comentario,id from gdc.documentos where proceso='" + s1 + "' and fuente = 'S' and tipodoc = '" + s + "' and descripcion = '" + s2 + "'");
        	
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
            printwriter.println("<td class='texttitulotabla' align='center'>Tipo</td>");
            printwriter.println("<td class='texttitulotabla' align='center'>Archivo</td>");
            printwriter.println("<td class='texttitulotabla' align='center'>Descripci\363n</td>");
            printwriter.println("</tr>");
            int j = 0;
            String s3 = "";
            String s5 = "";
            String s7 = "";
            Integer integer = new Integer(0);
            for(int k = 0; k < rs.size(); k += 11)
            {
                String s4 = (String)rs.elementAt(7 + k);
                String s6 = (String)rs.elementAt(8 + k);
                String s8 = (String)rs.elementAt(9 + k);
                Integer integer1 = (Integer)rs.elementAt(10 + k);
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'> <a href='verarchivo.jsp?ID=" + integer1.toString() + "&T=" + sTipo + "','datos'><IMG src=../images/ext_" + s6 + ".gif width=32 height=32 border=0></a> </td>");
                printwriter.println("<td class='textdesttabla'> " + s4 + "</td>");
                if(s8.length() > 0)
                    printwriter.println("<td class='textdesttabla'>" + s8 + "</td>");
                else
                    printwriter.println("<td class='textdesttabla'>&nbsp;</td>");
                printwriter.println("</tr>");
                j++;
            }

            printwriter.println("</table>");
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