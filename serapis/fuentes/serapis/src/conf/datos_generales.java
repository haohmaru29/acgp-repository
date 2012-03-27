package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class datos_generales extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public datos_generales() {
        ADatos = new AccDataBase();
        AFunc = new funciones();
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        String s = "";
        String s1 = "";
        String s2 = "";
        String s5 = httpservletrequest.getParameter("TIPO");
        if(s5 == null || s5.length() == 0)
            s5 = "EDITAR";
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        UserGDC = (String)httpsession.getValue("SerapisUser");
        if(UserGDC != null && UserGDC.length() > 0)
        {
            ADatos.connect();
            if(s5.compareTo("GRABAR") == 0)
            {
                s = httpservletrequest.getParameter("txtmision");
                s1 = httpservletrequest.getParameter("txtvision");
                s2 = httpservletrequest.getParameter("txtpolitica");
                
                int i = ADatos.ConsultaDB("select mision from gdc.calidad");
                if (i>0) {
                	rs = ADatos.getResult();
                	if(rs.size() > 0) {
		                String s3 = "update gdc.calidad set ";
		                s3 = s3 + "mision = '" + s + "',";
		                s3 = s3 + "vision = '" + s1 + "',";
		                s3 = s3 + "politica = '" + s2 + "'";
		                
		                i = ADatos.ModificaDB(s3);
                	} else {
                		String s3 = "insert into gdc.calidad(mision, vision, politica) values('" + s + "','" + s1 + "','" + s2 + "')";
                		i = ADatos.ModificaDB(s3);
                	}
                	rs.clear();                	                			
                }
            } else
            {
                String s4 = "select mision, vision, politica from gdc.calidad";
                int j = ADatos.ConsultaDB(s4);
                rs = ADatos.getResult();
                if(rs.size() > 0)
                {
                    s = (String)rs.elementAt(0);
                    s1 = (String)rs.elementAt(1);
                    s2 = (String)rs.elementAt(2);
                }
            }
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<form name='frmcliente' action='datos_generales.jsp?TIPO=GRABAR' method='post'>");
            printwriter.println("<table border='1' width='95%' align='center'>");
            printwriter.println("<tr><td width='95%' class='texttituloarea'>Datos Generales</td></tr>");
            printwriter.println("<tr><td width='95%' class='texttitulotabla'>Misi\363n</td></tr>");
            printwriter.println("<tr><td width='95%' class='textdesttabla'><TEXTAREA rows='5' cols='90' name='txtmision' onkeypress='return ValidarCaracteres(9);'>" + s.toString() + "</TEXTAREA></td></tr>");
            printwriter.println("<tr><td width='95%' class='texttitulotabla'>Visi\363n</td></tr>");
            printwriter.println("<tr><td width='95%' class='textdesttabla'><TEXTAREA rows='5' cols='90' name='txtvision' onkeypress='return ValidarCaracteres(9);'>" + s1.toString() + "</TEXTAREA></td></tr>");
            printwriter.println("<tr><td width='95%' class='texttitulotabla'>Pol\355tica</td></tr>");
            printwriter.println("<tr><td width='95%' class='textdesttabla'><TEXTAREA rows='5' cols='90' name='txtpolitica' onkeypress='return ValidarCaracteres(9);'>" + s2.toString() + "</TEXTAREA></td></tr>");
            printwriter.println("<tr><td width='95%' class='textdesttabla'>&nbsp;</td></tr>");
            printwriter.println("<tr><td width='95%' class='textdesttabla' align='center'><input class='fondoinput' type='submit' name='btnmas' value='Aceptar'></td></tr>");
            printwriter.println("</table>");
            printwriter.println("</form>");
            printwriter.println("<script language=\"javascript\">");
            printwriter.println("</script>");
            printwriter.println("</body>");
            printwriter.println("</html>");
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "CONF", 6);
        }
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException {
        doGet(httpservletrequest, httpservletresponse);
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    String UserGDC;
}