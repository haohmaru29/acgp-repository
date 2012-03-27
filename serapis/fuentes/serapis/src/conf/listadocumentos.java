package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class listadocumentos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public listadocumentos() {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        EsAdmin = "N";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        String s = "";
        String s2 = "";
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s4 = (String)httpsession.getValue("SerapisUser");
        if(s4 != null && s4.length() > 0)
        {
            String s5 = "select sigla,descripcion from gdc.tipodocumentos order by descripcion";
            rs = new Vector();
            ADatos.connect();
            int i = ADatos.ConsultaDB(s5);
            rs = ADatos.getResult();
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<table border='0' width='95%'  align='center'>");
            printwriter.println("<tr><td width='80%' colspan='4' class='texttituloarea' align='center'>Tipos de Documentos</td></tr>");
            printwriter.println("</table>");
            if(rs.size() > 0)
            {
                printwriter.println("<table border='1' width='80%' align='center'>");
                printwriter.println("<tr>");
                printwriter.println("<td class='texttitulotabla'>C\363digo</td>");
                printwriter.println("<td class='texttitulotabla'>Descripci\363n</td>");
                printwriter.println("</tr>");
                for(int j = 0; j < rs.size(); j += 2)
                {
                    String s1 = (String)rs.elementAt(j);
                    String s3 = (String)rs.elementAt(j + 1);
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla'><a href='vertipodocumento.jsp?ID=" + s1 + "'>" + s1 + "</a></td>");
                    printwriter.println("<td class='textdesttabla'>" + s3 + "</td>");
                    printwriter.println("</tr>");
                }

                printwriter.println("</table>");
            }
            printwriter.println("<br>");
            printwriter.println("<br><center>");
            printwriter.println("<input type='button' name='nuevo' value='Nuevo' class='fondoinput'  language='javascript' onclick='window.open(\"vertipodocumento.jsp?TIPO=ADD\",\"datos\")'>");
            printwriter.println("<input type='button' name='BtnImprimir' value='Imprimir' class='fondoinput' language='javascript' onclick='window.print()'>");
            printwriter.println("</center>");
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
    String EsAdmin;
}