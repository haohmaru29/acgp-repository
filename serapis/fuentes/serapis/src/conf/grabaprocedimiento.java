package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class grabaprocedimiento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public grabaprocedimiento() {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        rs = new Vector();
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
            String s7 = httpservletrequest.getParameter("ID");
            String s8 = httpservletrequest.getParameter("CODPROCESO");
            String s9 = httpservletrequest.getParameter("procedimiento");
            String s10 = httpservletrequest.getParameter("rol");
            String s11 = httpservletrequest.getParameter("definicionrol");
            String s12 = httpservletrequest.getParameter("userresp");
            String s13 = httpservletrequest.getParameter("IDPROC");
            String s14 = httpservletrequest.getParameter("IDROL");
            Integer integer = new Integer(0);
            String s16 = httpservletrequest.getParameter("indicador");
            String s15 = httpservletrequest.getParameter("rango_minimo");
            Integer integer2 = new Integer(s15);
            s15 = httpservletrequest.getParameter("rango_maximo");
            Integer integer3 = new Integer(s15);
            s15 = httpservletrequest.getParameter("bueno1");
            Integer integer4 = new Integer(s15);
            s15 = httpservletrequest.getParameter("bueno2");
            Integer integer5 = new Integer(s15);
            s15 = httpservletrequest.getParameter("regular1");
            Integer integer6 = new Integer(s15);
            s15 = httpservletrequest.getParameter("regular2");
            Integer integer7 = new Integer(s15);
            s15 = httpservletrequest.getParameter("malo1");
            Integer integer8 = new Integer(s15);
            s15 = httpservletrequest.getParameter("malo2");
            Integer integer9 = new Integer(s15);
            if(s7 != null && s7.length() > 0)
            {
                if(s7.compareTo("1") == 0)
                {
                    String s2 = "select max(idrol) from gdc.procedimientos where procedimiento = '" + s9 + "'";
                    rs = new Vector();
                    int i = ADatos.ConsultaDB(s2);
                    rs = ADatos.getResult();
                    Integer integer1 = (Integer)rs.elementAt(0);
                    if(integer1 == null)
                        integer1 = (Integer)rs.elementAt(1);
                    s2 = "Insert into gdc.procedimientos (proceso,procedimiento,idrol,rol,definicionrol,responsables,indicador,rangominimo,rangomaximo,bueno_minimo,bueno_maximo,regular_minimo,regular_maximo,malo_minimo,malo_maximo) values (";
                    s2 = s2 + "'" + s8 + "',";
                    s2 = s2 + "'" + s9 + "',";
                    s2 = s2 + (integer1.intValue() + 1) + ",";
                    s2 = s2 + "'" + s10 + "',";
                    s2 = s2 + "'" + s11 + "',";
                    s2 = s2 + "'" + s12 + "',";
                    s2 = s2 + "'" + s16 + "',";
                    s2 = s2 + integer2.toString() + ",";
                    s2 = s2 + integer3.toString() + ",";
                    s2 = s2 + integer4.toString() + ",";
                    s2 = s2 + integer5.toString() + ",";
                    s2 = s2 + integer6.toString() + ",";
                    s2 = s2 + integer7.toString() + ",";
                    s2 = s2 + integer8.toString() + ",";
                    s2 = s2 + integer9.toString() + ")";
                    i = ADatos.ModificaDB(s2);
                }
                if(s7.compareTo("2") == 0)
                {
                    String s3 = "select proceso,procedimiento from gdc.procedimientos where idproc = " + s13;
                    rs = new Vector();
                    int j = ADatos.ConsultaDB(s3);
                    rs = ADatos.getResult();
                    if(rs.size() > 0)
                    {
                        String s17 = (String)rs.elementAt(0);
                        String s18 = (String)rs.elementAt(1);
                        String s4 = "update gdc.procedimientos set ";
                        s4 = s4 + "procedimiento = '" + s9 + "', ";
                        s4 = s4 + "rol = '" + s10 + "', ";
                        s4 = s4 + "definicionrol = '" + s11 + "', ";
                        s4 = s4 + "responsables = '" + s12 + "', ";
                        s4 = s4 + "indicador = '" + s16 + "', ";
                        s4 = s4 + "rangominimo = " + integer2.toString() + ", ";
                        s4 = s4 + "rangomaximo = " + integer3.toString() + ", ";
                        s4 = s4 + "bueno_minimo = " + integer4.toString() + ", ";
                        s4 = s4 + "bueno_maximo = " + integer5.toString() + ", ";
                        s4 = s4 + "regular_minimo = " + integer6.toString() + ", ";
                        s4 = s4 + "regular_maximo = " + integer7.toString() + ", ";
                        s4 = s4 + "malo_minimo = " + integer8.toString() + ", ";
                        s4 = s4 + "malo_maximo = " + integer9.toString() + " ";
                        s4 = s4 + "where idproc = " + s13;
                        int k = ADatos.ModificaDB(s4);
                        if(s9.compareTo(s18) != 0)
                        {
                            String s5 = "update gdc.procedimientos set ";
                            s5 = s5 + "procedimiento = '" + s9 + "' ";
                            s5 = s5 + "where proceso = '" + s17 + "'";
                            s5 = s5 + " and procedimiento = '" + s18 + "'";
                            int l = ADatos.ModificaDB(s5);
                        }
                    }
                }
                if(s7.compareTo("3") == 0)
                {
                    String s6 = "delete from gdc.procedimientos where idproc = " + s13;
                    int i1 = ADatos.ModificaDB(s6);
                }
            }
            printwriter.println("<HTML>");
            printwriter.println("<HEAD>");
            printwriter.println("<title>SERAPIS. Sistema de Gesti\363n de Calidad.</title>");
            printwriter.println("</HEAD>");
            printwriter.println("</HEAD>");
            printwriter.println("<script language=\"javascript\">");
            printwriter.println("window.open(\"verproceso.jsp?ID=" + s8 + "\",\"datos\");");
            printwriter.println("</script>");
            printwriter.println("<body bgcolor='#FFFFFF'>");
            printwriter.println("</BODY>");
            printwriter.println("</HTML>");
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
}