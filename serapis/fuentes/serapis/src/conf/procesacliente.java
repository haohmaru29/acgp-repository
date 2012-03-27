package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class procesacliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public procesacliente() {
        ADatos = new AccDataBase();
        func = new funciones();
        MsgError = "";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        String s = "";
        String s4 = "";
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s6 = (String)httpsession.getValue("SerapisUser");
        if(s6 != null && s6.length() > 0)
        {
            String s5 = httpservletrequest.getParameter("TIPO");
            if(s5 != null)
            {
                if(s5.compareTo("1") == 0)
                {
                    String s7 = httpservletrequest.getParameter("rut");
                    String s9 = httpservletrequest.getParameter("abreviatura");
                    String s11 = httpservletrequest.getParameter("razonsocial");
                    String s13 = httpservletrequest.getParameter("direccion");
                    String s15 = httpservletrequest.getParameter("fono");
                    String s17 = httpservletrequest.getParameter("fax");
                    String s19 = httpservletrequest.getParameter("mail");
                    String s21 = httpservletrequest.getParameter("web");
                    String s23 = httpservletrequest.getParameter("contacto1");
                    String s25 = httpservletrequest.getParameter("fono1");
                    String s27 = httpservletrequest.getParameter("fax1");
                    String s29 = httpservletrequest.getParameter("mail1");
                    String s31 = httpservletrequest.getParameter("contacto2");
                    if(s31 == null)
                        s31 = "";
                    String s33 = httpservletrequest.getParameter("fono2");
                    if(s33 == null)
                        s33 = "";
                    String s35 = httpservletrequest.getParameter("fax2");
                    if(s35 == null)
                        s35 = "";
                    String s37 = httpservletrequest.getParameter("mail2");
                    if(s37 == null)
                        s37 = "";
                    String s1 = "select rutcliente from sgc.clientes where rutcliente = '" + s7 + "'";
                    int i = ADatos.ConsultaDB(s1);
                    rs = new Vector();
                    rs = ADatos.getResult();
                    if(rs.size() == 0)
                    {
                        String s2 = "insert into sgc.clientes (rutcliente,abreviatura,razonsocial,direccion,telefono,fax,email,website,contacto1,fonocontacto1,faxcontacto1,mailcontacto1,contacto2,fonocontacto2,faxcontacto2,mailcontacto2) values (";
                        s2 = s2 + "'" + s7 + "',";
                        s2 = s2 + "'" + s9 + "',";
                        s2 = s2 + "'" + s11 + "',";
                        s2 = s2 + "'" + s13 + "',";
                        s2 = s2 + "'" + s15 + "',";
                        s2 = s2 + "'" + s17 + "',";
                        s2 = s2 + "'" + s19 + "',";
                        s2 = s2 + "'" + s21 + "',";
                        s2 = s2 + "'" + s23 + "',";
                        s2 = s2 + "'" + s25 + "',";
                        s2 = s2 + "'" + s27 + "',";
                        s2 = s2 + "'" + s29 + "',";
                        s2 = s2 + "'" + s31 + "',";
                        s2 = s2 + "'" + s33 + "',";
                        s2 = s2 + "'" + s35 + "',";
                        s2 = s2 + "'" + s37 + "')";
                        int j = ADatos.ModificaDB(s2);
                    } else
                    {
                        MsgError = "Cliente " + s7 + " ya existe.";
                    }
                }
                if(s5.compareTo("2") == 0)
                {
                    String s8 = httpservletrequest.getParameter("rut");
                    String s10 = httpservletrequest.getParameter("abreviatura");
                    String s12 = httpservletrequest.getParameter("razonsocial");
                    String s14 = httpservletrequest.getParameter("direccion");
                    String s16 = httpservletrequest.getParameter("fono");
                    String s18 = httpservletrequest.getParameter("fax");
                    String s20 = httpservletrequest.getParameter("mail");
                    String s22 = httpservletrequest.getParameter("web");
                    String s24 = httpservletrequest.getParameter("contacto1");
                    String s26 = httpservletrequest.getParameter("fono1");
                    String s28 = httpservletrequest.getParameter("fax1");
                    String s30 = httpservletrequest.getParameter("mail1");
                    String s32 = httpservletrequest.getParameter("contacto2");
                    if(s32 == null)
                        s32 = "";
                    String s34 = httpservletrequest.getParameter("fono2");
                    if(s34 == null)
                        s34 = "";
                    String s36 = httpservletrequest.getParameter("fax2");
                    if(s36 == null)
                        s36 = "";
                    String s38 = httpservletrequest.getParameter("mail2");
                    if(s38 == null)
                        s38 = "";
                    String s3 = "Update sgc.clientes set ";
                    s3 = s3 + "abreviatura = '" + s10 + "',";
                    s3 = s3 + "razonsocial = '" + s12 + "',";
                    s3 = s3 + "direccion = '" + s14 + "',";
                    s3 = s3 + "telefono = '" + s16 + "',";
                    s3 = s3 + "fax = '" + s18 + "',";
                    s3 = s3 + "email = '" + s20 + "',";
                    s3 = s3 + "website = '" + s22 + "',";
                    s3 = s3 + "contacto1 = '" + s24 + "',";
                    s3 = s3 + "fonocontacto1 = '" + s26 + "',";
                    s3 = s3 + "faxcontacto1 = '" + s28 + "',";
                    s3 = s3 + "mailcontacto1 = '" + s30 + "',";
                    s3 = s3 + "contacto2 = '" + s32 + "',";
                    s3 = s3 + "fonocontacto2 = '" + s34 + "',";
                    s3 = s3 + "faxcontacto2 = '" + s36 + "',";
                    s3 = s3 + "mailcontacto2 = '" + s38 + "'";
                    s3 = s3 + "where rutcliente = '" + s8 + "'";
                    int k = ADatos.ModificaDB(s3);
                }
            }
            printwriter.println("<HTML>");
            printwriter.println("<HEAD>");
            printwriter.println("<title>SERAPIS. Sistema de Gesti\363n de Calidad.</title>");
            printwriter.println("</HEAD>");
            printwriter.println("</HEAD>");
            printwriter.println("<script language=\"javascript\">");
            if(MsgError.length() > 0)
                printwriter.println("alert(\"" + MsgError + "\");");
            printwriter.println("window.open(\"clientes.jsp\",\"datos\");");
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
    String MsgError;
}