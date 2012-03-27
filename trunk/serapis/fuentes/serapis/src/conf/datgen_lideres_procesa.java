// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   datgen_lideres_procesa.java

package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;

public class datgen_lideres_procesa extends HttpServlet
{

    public datgen_lideres_procesa()
    {
        ADatos  = new AccDataBase();
        func = new funciones();
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        boolean flag = false;
        long l = 1L;
        Vector vector = new Vector();
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s = (String)httpsession.getValue("SerapisUser");
        if(s != null && s.length() > 0)
        {
            String s1 = "";
            String s2 = "";
            String s3 = "";
            String s4 = "";
            String s5 = "";
            String s6 = "";
            String s7 = "";
            String s8 = "";
            String s9 = "";
            try
            {
            	String DriveTemporal = "";
            	String sqlDrive = "select dirsitio,dirfiles from gdc.datosgenerales";
                long lDrive = ADatos.ConsultaDB(sqlDrive);
                rs = ADatos.getResult();
                if(rs.size() > 0){
                	DriveTemporal = (String)rs.elementAt(0);
                	DriveTemporal = DriveTemporal.substring(0,1);
                } else
                	DriveTemporal = "C";
            	
                DiskFileUpload diskfileupload = new DiskFileUpload();
                diskfileupload.setSizeMax(0x1000000L);
                diskfileupload.setSizeThreshold(4096);
                diskfileupload.setRepositoryPath(DriveTemporal + ":\\");
                List list = diskfileupload.parseRequest(httpservletrequest);
                Iterator iterator = list.iterator();
                FileItem fileitem = null;
                do
                {
                    if(!iterator.hasNext())
                        break;
                    fileitem = (FileItem)iterator.next();
                    if(fileitem.getFieldName().equals("tipo"))
                        s1 = fileitem.getString();
                    if(fileitem.getFieldName().equals("numero"))
                        s3 = fileitem.getString();
                    if(fileitem.getFieldName().equals("indice"))
                        s2 = fileitem.getString();
                    if(fileitem.getFieldName().equals("nombre"))
                        s4 = fileitem.getString();
                    if(fileitem.getFieldName().equals("adicional"))
                        s5 = fileitem.getString();
                    if(fileitem.getFieldName().equals("mail"))
                        s6 = fileitem.getString();
                    if(fileitem.getFieldName().equals("ancho"))
                        s7 = fileitem.getString();
                    if(fileitem.getFieldName().equals("alto"))
                        s8 = fileitem.getString();
                    if(fileitem.getFieldName().equals("archivo"))
                        s9 = fileitem.getName();
                } while(true);
                Integer integer = new Integer(s3);
                if(s1.compareTo("DELETE") == 0)
                {
                    sql = "delete from gdc.lideres ";
                    sql = sql + " where id = " + (integer.intValue() + 1);
                    int i = ADatos.ModificaDB(sql);
                } else
                {
                    sql = "select dirsitio,dirfiles from gdc.datosgenerales";
                    ADatos.ConsultaDB(sql);
                    rs = ADatos.getResult();
                    if(rs.size() > 0)
                    {
                        String s10 = (String)rs.elementAt(0);
                        String s11 = (String)rs.elementAt(1);
                        String s12 = s9.substring(s9.length() - 3, s9.length());
                        File file = new File(s10 + "images\\lider_" + s3 + "." + s12);
                        if(s1.compareTo("INSERT") == 0)
                        {
                            sql = "insert into gdc.lideres (orden,linea1,linea2,mail,foto,ancho,alto) values (";
                            sql = sql + s2 + ",";
                            sql = sql + " '" + s4 + "',";
                            sql = sql + " '" + s5 + "',";
                            sql = sql + " '" + s6 + "',";
                            sql = sql + " 'lider_" + s3 + "." + s12 + "',";
                            sql = sql + s7 + ",";
                            sql = sql + s8 + ")";
                        } else
                        {
                            sql = "update gdc.lideres set ";
                            sql = sql + " orden = " + s2 + ",";
                            sql = sql + " linea1 = '" + s4 + "',";
                            sql = sql + " linea2 = '" + s5 + "',";
                            sql = sql + " mail = '" + s6 + "',";
                            sql = sql + " foto = 'lider_" + s3 + "." + s12 + "',";
                            sql = sql + " ancho = " + s7 + ",";
                            sql = sql + " alto = " + s8;
                            sql = sql + " where id = " + (integer.intValue() + 1);
                        }
                        int j = ADatos.ModificaDB(sql);
                        fileitem.write(file);
                    }
                }
            }
            catch(Exception exception)
            {
                printwriter.println("<BR> Error de Aplicaci\363n " + exception.getMessage());
            }
            printwriter.println("<HTML>");
            printwriter.println("<HEAD>");
            printwriter.println("<title>SERAPIS. Sistema de Gesti\363n de Calidad.</title>");
            printwriter.println("</HEAD>");
            printwriter.println("<script language=\"javascript\">");
            printwriter.println("window.open(\"datgen_lideres.jsp\",\"datos\");");
            printwriter.println("</script>");
            printwriter.println("<body bgcolor='#FFFFFF'>");
            printwriter.println("</BODY>");
            printwriter.println("</HTML>");
        } else
        {
            func.reindex(httpservletrequest, printwriter, 1, "CONF", 6);
        }
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    AccDataBase ADatos;
    funciones func;
    Vector rs;
    String sql;
}