package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class grabaproceso extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public grabaproceso() {
        RutaSitio = "";
        RutaDocumentos = "";
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
        if(ObtieneRutas() == 1)
        {
            if(s != null && s.length() > 0)
            {
                String s1 = "";
                boolean flag = false;
                byte byte0 = 0;
                String s4 = httpservletrequest.getParameter("ID");
                String s5 = httpservletrequest.getParameter("CODPROCESO");
                String s6 = httpservletrequest.getParameter("proceso");
                String s7 = httpservletrequest.getParameter("lider");
                String s8 = httpservletrequest.getParameter("backuplider");
                String s9 = httpservletrequest.getParameter("hddbackup");
                String s10 = httpservletrequest.getParameter("definicion");
                if(s4 != null && s4.length() > 0)
                {
                    if(s4.compareTo("1") == 0)
                    {
                        String s11 = httpservletrequest.getParameter("idproceso");
                        String s12 = RutaDocumentos + "sad\\documentos\\" + s11;
                        int k = func.CreaDir(s12, printwriter);
                        if(k <= 1)
                        {
                            String s13 = RutaDocumentos + "gdc\\documentos\\" + s11;
                            int l = func.CreaDir(s13, printwriter);
                            if(l <= 1)
                            {
                                String s14 = RutaDocumentos + "gdc\\historico\\" + s11;
                                int i1 = func.CreaDir(s14, printwriter);
                                if(i1 <= 1)
                                {
                                    String s15 = RutaDocumentos + "gdc\\reciclaje\\" + s11;
                                    int j1 = func.CreaDir(s15, printwriter);
                                    if(j1 <= 1)
                                    {
                                        String s2 = "Insert into gdc.procesos (sigla,descripcion,definicion,lider) values (";
                                        s2 = s2 + "'" + s11 + "',";
                                        s2 = s2 + "'" + s6 + "',";
                                        s2 = s2 + "'" + s10 + "',";
                                        s2 = s2 + "'" + s7 + "')";
                                        int i = ADatos.ModificaDB(s2);
                                        if(i < 0)
                                            byte0 = 5;
                                    } else
                                    {
                                        byte0 = 2;
                                    }
                                } else
                                {
                                    byte0 = 3;
                                }
                            } else
                            {
                                byte0 = 4;
                            }
                        } else
                        {
                            byte0 = 1;
                        }
                    }
                    if(s5 != null && s5.length() > 0 && s4.compareTo("2") == 0)
                    {
                        String s3 = "Update gdc.procesos set descripcion = '" + s6 + "',";
                        s3 = s3 + "definicion = '" + s10 + "',";
                        s3 = s3 + "lider = '" + s7 + "',";
                        s3 = s3 + "backuplider = '" + s8 + "',";
                        s3 = s3 + "validobackup = '" + s9 + "' ";
                        s3 = s3 + "where sigla = '" + s5 + "'";
                        int j = ADatos.ModificaDB(s3);
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
                case 1: // '\001'
                    printwriter.println("alert(\"No se creo el directorio documentos SAD. Es posible que el proceso ya exista o no tenga suficientes privilegios.\");");
                    break;

                case 2: // '\002'
                    printwriter.println("alert(\"No se creo el directorio de reciclaje GDC. Es posible que el proceso ya exista o no tenga suficientes privilegios.\");");
                    break;

                case 3: // '\003'
                    printwriter.println("alert(\"No se creo el directorio de historico GDC. Es posible que el proceso ya exista o no tenga suficientes privilegios.\");");
                    break;

                case 4: // '\004'
                    printwriter.println("alert(\"No se creo el directorio de documentos GDC. Es posible que el proceso ya exista o no tenga suficientes privilegios.\");");
                    break;

                case 5: // '\005'
                    printwriter.println("alert(\"Ocurrio un error al agregar el proceso. Es posible que el proceso ya exista.\");");
                    break;
                }
                printwriter.println("window.open(\"procesos.jsp\",\"datos\");");
                printwriter.println("</script>");
                printwriter.println("<body bgcolor='#FFFFFF'>");
                printwriter.println("</BODY>");
                printwriter.println("</HTML>");
            } else
            {
                func.reindex(httpservletrequest, printwriter, 1, "CONF", 6);
            }
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

    String RutaSitio;
    String RutaDocumentos;
    AccDataBase ADatos;
    funciones func;
}