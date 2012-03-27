package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class listaclientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public listaclientes() {
        ADatos = new AccDataBase();
        AFunc = new funciones();
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s = (String)httpsession.getValue("SerapisUser");
        if(s != null && s.length() > 0)
        {
            String s1 = "select rutcliente,abreviatura,razonsocial,telefono,fax from sgc.clientes order by razonsocial";
            rs = new Vector();
            ADatos.connect();
            int i = ADatos.ConsultaDB(s1);
            rs = ADatos.getResult();
            if(rs.size() >= 0)
            {
                printwriter.println("<html>");
                printwriter.println("<head>");
                printwriter.println("</head>");
                printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
                printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
                AFunc.cargamenu(printwriter, 1);
                printwriter.println("<table border='0' width='80%'  align='center'>");
                printwriter.println("<tr><td align='center'  class='texttituloarea'>Clientes</td></tr>");
                printwriter.println("<tr><td align='center'>");
                printwriter.println("<table border='1' width='95%'  align='center'>");
                printwriter.println("<tr>");
                printwriter.println("<td class='texttitulotabla'>Cliente</td>");
                printwriter.println("<td class='texttitulotabla'>Abreviatura</td>");
                printwriter.println("<td class='texttitulotabla'>Tel\351fono</td>");
                printwriter.println("<td class='texttitulotabla'>Fax</td>");
                printwriter.println("</tr>");
                for(int j = 0; j < rs.size(); j += 5)
                {
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla'><a href='vercliente.jsp?CLI=" + (String)rs.elementAt(j) + "'>" + (String)rs.elementAt(j + 2) + "</a></td>");
                    printwriter.println("<td class='textdesttabla'>" + (String)rs.elementAt(j + 1) + "</td>");
                    printwriter.println("<td class='textdesttabla'>" + (String)rs.elementAt(j + 3) + "</td>");
                    printwriter.println("<td class='textdesttabla'>" + (String)rs.elementAt(j + 4) + "</td>");
                    printwriter.println("</tr>");
                }

                printwriter.println("</table>");
                printwriter.println("<br><center>");
                printwriter.println("<input type='button' name='nuevo' value='Nuevo' class='fondoinput'  language='javascript' onclick='window.open(\"nuevocliente.jsp\",\"datos\")'>&nbsp;<input type='button' name='BtnImprimir' value='Imprimir' class='fondoinput' language='javascript' onclick='window.print()'>");
                printwriter.println("</center>");
                printwriter.println("</body>");
                printwriter.println("</html>");
            } else
            {
                AFunc.reindex(httpservletrequest, printwriter, 1, "CONF", 7);
            }
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