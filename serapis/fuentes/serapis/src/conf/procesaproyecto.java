package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class procesaproyecto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public procesaproyecto() {
        ADatos = new AccDataBase();
        func = new funciones();
        RutaSitio = "";
        RutaDocumentos = "";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        boolean flag = false;
        byte byte0 = 0;
        Vector vector = new Vector();
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s = (String)httpsession.getValue("SerapisUser");
        if(ObtieneRutas() == 1)
        {
            if(s != null && s.length() > 0)
            {
                String s1 = httpservletrequest.getParameter("TIPO");
                String s2 = httpservletrequest.getParameter("CLI");
                String s3 = httpservletrequest.getParameter("CODPROY");
                if(s1 != null)
                {
                    String s4 = httpservletrequest.getParameter("proyecto");
                    String s5 = httpservletrequest.getParameter("abreviatura");
                    String s6 = httpservletrequest.getParameter("jefeproyecto");
                    String s7 = httpservletrequest.getParameter("backupjp");
                    String s8 = httpservletrequest.getParameter("hddbackup");
                    String s9 = httpservletrequest.getParameter("lstestado");
                    rs = new Vector();
                    if(s1.compareTo("1") == 0)
                        if(s2 != null)
                        {
                            sql = "select abreviatura from sgc.clientes where rutcliente = '" + s2 + "'";
                            int i = ADatos.ConsultaDB(sql);
                            rs = ADatos.getResult();
                            if(rs.size() > 0)
                            {
                                String s10 = (String)rs.elementAt(0);
                                int l = func.CreaDirectorio(RutaDocumentos, s10, s5, printwriter);
                                if(l == 1)
                                {
                                    Vector vector1 = func.ObtieneFecha();
                                    int j = 0;
                                    sql = "insert into gdc.proyectos (cliente,proyecto,abreviatura,jefeproyecto,fecha,usuario,estado) values (";
                                    sql = sql + "'" + s2 + "',";
                                    sql = sql + "'" + s4 + "',";
                                    sql = sql + "'" + s5 + "',";
                                    sql = sql + "'" + s6 + "',";
                                    sql = sql + (String)vector1.elementAt(0) + ",";
                                    sql = sql + "'" + s + "','A')";
                                    j = ADatos.ModificaDB(sql);
                                    if(j != -1)
                                        byte0 = 0;
                                    else
                                        byte0 = 1;
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
                            byte0 = 3;
                        }
                    if(s1.compareTo("2") == 0)
                        if(s3 != null)
                        {
                            sql = "update gdc.proyectos set proyecto = '" + s4 + "',";
                            sql = sql + " jefeproyecto = '" + s6 + "',";
                            sql = sql + " jpbackup = '" + s7 + "',";
                            sql = sql + " validobackup = '" + s8 + "',";
                            sql = sql + " estado = '" + s9 + "'";
                            sql = sql + " where id = " + s3;
                            int k = ADatos.ModificaDB(sql);
                            if(k != -1)
                                byte0 = 0;
                            else
                                byte0 = 4;
                        } else
                        {
                            byte0 = 5;
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
                        printwriter.println("alert(\"Problemas al agregar en la Base de datos\");");
                        break;

                    case 2: // '\002'
                        printwriter.println("alert(\"Problemas al crear el directorio\");");
                        break;

                    case 3: // '\003'
                        printwriter.println("alert(\"Cliente no existe\");");
                        break;

                    case 4: // '\004'
                        printwriter.println("alert(\"Problemas al modificar la base de datos\");");
                        break;

                    case 5: // '\005'
                        printwriter.println("alert(\"Proyecto no existe (UPDATE)\");");
                        break;
                    }
                    printwriter.println("window.open(\"clientes.jsp\",\"datos\");");
                    printwriter.println("</script>");
                    printwriter.println("<body bgcolor='#FFFFFF'>");
                    printwriter.println("</BODY>");
                    printwriter.println("</HTML>");
                } else
                {
                    func.reindex(httpservletrequest, printwriter, 1, "CONF", 7);
                }
            } else
            {
                func.reindex(httpservletrequest, printwriter, 1, "CONF", 6);
            }
        } else
        {
            func.reindex(httpservletrequest, printwriter, 1, "CONF", 6);
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

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    AccDataBase ADatos;
    funciones func;
    Vector rs;
    String sql;
    String RutaSitio;
    String RutaDocumentos;
}