package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class eliminaproceso extends HttpServlet
{

    public eliminaproceso()
    {
        ADatos = new AccDataBase();
        func = new funciones();
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s = (String)httpsession.getValue("SerapisSis");
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        if(s != null && s.length() > 0)
        {
            String s1 = "";
            boolean flag = false;
            Integer integer = new Integer(0);
            byte byte0 = 0;
            Vector vector = new Vector();
            String s10 = httpservletrequest.getParameter("CODPROCESO");
            s1 = "select count(*) from gdc.procedimientos where proceso = '" + s10 + "'";
            ADatos.ConsultaDB(s1);
            vector = ADatos.getResult();
            if(vector.size() > 0)
            {
                Integer integer1 = new Integer(0);
                try {
                	integer1 = (Integer)vector.elementAt(0);
                }catch(ClassCastException e ) {}
                if(integer1.longValue() > 0L) {
                    byte0 = -1;
                } else {
                    vector.clear();
                    String s2 = "select count(*) from gdc.documentos where proceso = '" + s10 + "'";
                    ADatos.ConsultaDB(s2);
                    vector = ADatos.getResult();
                    if(vector.size() > 0)
                    {
                        Integer integer2 = new Integer(0);
                        try {
                        	integer2 = (Integer)vector.elementAt(0);
                        }catch(ClassCastException e ) { }
                        if(integer2.longValue() > 0L)
                        {
                            byte0 = -2;
                        } else
                        {
                            vector.clear();
                            String s3 = "select count(*) from sad.documentos where proceso = '" + s10 + "'";
                            ADatos.ConsultaDB(s3);
                            vector = ADatos.getResult();
                            if(vector.size() > 0) {
                                Integer integer3 = new Integer(0);
                                try {
                                	integer3 = (Integer)vector.elementAt(0);
                                }catch(ClassCastException e ) { }
                                if(integer3.longValue() > 0L)
                                {
                                    byte0 = -3;
                                } else
                                {
                                    vector.clear();
                                    String s4 = "select count(*) from sad.documentosp where proceso = '" + s10 + "'";
                                    ADatos.ConsultaDB(s4);
                                    vector = ADatos.getResult();
                                    if(vector.size() > 0)
                                    {
                                        Integer integer4 = new Integer(0);
                                        try {
                                        	integer4 = (Integer)vector.elementAt(0);
                                        }catch(ClassCastException e ) {} 
                                        if(integer4.longValue() > 0L)
                                        {
                                            byte0 = -4;
                                        } else
                                        {
                                            vector.clear();
                                            String s5 = "select count(*) from gdc.documentoscalidad where proceso = '" + s10 + "'";
                                            ADatos.ConsultaDB(s5);
                                            vector = ADatos.getResult();
                                            if(vector.size() > 0)
                                            {
                                                Integer integer5 = new Integer(0);
                                                try {
                                                	integer5 = (Integer)vector.elementAt(0);
                                                } catch(ClassCastException e ) {}
                                                if(integer5.longValue() > 0L)
                                                {
                                                    byte0 = -5;
                                                } else
                                                {
                                                    vector.clear();
                                                    String s6 = "select count(*) from sad.permisosproceso where proceso = '" + s10 + "'";
                                                    ADatos.ConsultaDB(s6);
                                                    vector = ADatos.getResult();
                                                    if(vector.size() > 0)
                                                    {
                                                        Integer integer6 = new Integer(0); 
                                                        try {
                                                        	integer6 = (Integer)vector.elementAt(0);
                                                        } catch(ClassCastException e ) {} 
                                                        if(integer6.longValue() > 0L)
                                                        {
                                                            byte0 = -6;
                                                        } else
                                                        {
                                                            vector.clear();
                                                            String s7 = "select sigla,descripcion,definicion from gdc.procesos where sigla = '" + s10 + "'";
                                                            ADatos.ConsultaDB(s7);
                                                            vector = ADatos.getResult();
                                                            if(vector.size() > 0)
                                                            {
                                                                String s8 = "insert into gdc.his_procesos (sigla,descripcion,definicion) values ('";
                                                                s8 = s8 + (String)vector.elementAt(0) + "','";
                                                                s8 = s8 + (String)vector.elementAt(1) + "','";
                                                                s8 = s8 + (String)vector.elementAt(2) + "')";
                                                                int i = ADatos.ModificaDB(s8);
                                                                if(i > 0)
                                                                {
                                                                    String s9 = "delete  from gdc.procesos where sigla = '" + s10 + "'";
                                                                    int j = ADatos.ModificaDB(s9);
                                                                    s9 = "delete  from sgc.perfil where proceso = '" + s10 + "'";
                                                                    j = ADatos.ModificaDB(s9);
                                                                    byte0 = 0;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            printwriter.println("<HTML>");
            printwriter.println("<HEAD>");
            printwriter.println("<title>SERAPIS. Sistema de Gesti\363n de Calidad.</title>");
            printwriter.println("</HEAD>");
            printwriter.println("</HEAD>");
            printwriter.println("<script language=\"javascript\">");
            switch(byte0)
            {
            case 0: // '\0'
                printwriter.println("alert(\"Eliminaci\363n exitosa.\");");
                break;

            case -1:
                printwriter.println("alert(\"No es posoble eliminar el proceso ya que tiene procedimientos asociados.\");");
                break;

            case -2:
                printwriter.println("alert(\"No es posoble eliminar el proceso ya que esta asociado al menos a un documento de gesti\363n.\");");
                break;

            case -3:
                printwriter.println("alert(\"No es posoble eliminar el proceso ya que esta asociado al menos a un documento de operaci\363n.\");");
                break;

            case -4:
                printwriter.println("alert(\"No es posoble eliminar el proceso ya que esta asociado al menos a un documento de operaci\363n de proyectos.\");");
                break;

            case -5:
                printwriter.println("alert(\"No es posoble eliminar el proceso ya que esta asociado al menos a un documento en la lista maestra.\");");
                break;

            case -6:
                printwriter.println("alert(\"No es posoble eliminar el proceso ya que existen usuarios con persisos sobre \351l.\");");
                break;

            default:
                printwriter.println("alert(\"Problemas al eliminar el proceso.\");");
                break;
            }
            if(byte0 != 0)
                printwriter.println("window.open(\"verproceso.jsp?ID=" + s10 + "\",\"datos\");");
            else
                printwriter.println("window.open(\"procesos.jsp\",\"datos\");");
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
}