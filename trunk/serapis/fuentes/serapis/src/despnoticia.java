// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   despnoticia.java

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class despnoticia extends HttpServlet
{

    public despnoticia()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
    	sPath = "";
    	String s0 = "select dirsitio from gdc.datosgenerales";
    	ADatos.connect();
        int k1 = ADatos.ConsultaDB(s0);
        if (k1>0) {
	        rs = ADatos.getResult();
	        if (rs.size()>0) 
	        	sPath = (String)rs.elementAt(0);
	        else
	        	sPath = "C:\\Archivos de programa\\Apache Tomcat 4.0\\webapps\\serapis";
	        
	        rs.clear();
    	}          
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        String s = httpservletrequest.getParameter("ID");
        Integer integer = new Integer(0);
        String s1 = "";
        String s2 = "";
        if(s == null || s.length() == 0)
            s = "1";
        integer = new Integer(s);
        printwriter.println("<html>");
        printwriter.println("<head>");
        printwriter.println("<title>SERAPIS. Sistema de Gesti\363n de Calidad.</title>");
        printwriter.println("</head>");
        printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='serapis.css'>");
        printwriter.println("<TITLE>SERAPIS. Sistema de Gesti\363n de Calidad</TITLE>");
        printwriter.println("<body leftmargin='0' topmargin='0'>");
        AFunc.cargamenucompleto(printwriter, 0, "Noticias");
        //s1 = "C:\\Archivos de programa\\Apache Tomcat 4.0\\webapps\\serapis\\noticias\\";
        s1 = sPath + "\\webapps\\serapis\\noticias\\";
        switch(integer.intValue())
        {
        case 1: // '\001'
            s1 = s1 + "noticia1\\noticia.html";
            break;

        case 2: // '\002'
            s1 = s1 + "noticia2\\noticia.html";
            break;

        case 3: // '\003'
            s1 = s1 + "noticia3\\noticia.html";
            break;

        case 4: // '\004'
            s1 = s1 + "noticia4\\noticia.html";
            break;
        }
        String s3 = AFunc.leer(s1);
        printwriter.println(s3);
        printwriter.println("</body>");
        printwriter.println("</html>");
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    String sql;
    String sPath;
}