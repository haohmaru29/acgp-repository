package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class listado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public listado() {
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
            String s1 = "select login from sgc.perfil where login='" + s + "' and administrador='S'";
            rs = new Vector();
            ADatos.connect();
            int i = ADatos.ConsultaDB(s1);
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                printwriter.println("<html>");
                printwriter.println("<head>");
                printwriter.println("</head>");
                printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
                printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
                AFunc.cargamenu(printwriter, 1);
                printwriter.println("<table border='0' width='80%' align='center'>");
                printwriter.println("<tr><td align='center'  class='texttituloarea'>Usuarios Sistema Gesti\363n Documental de Calidad</td></tr>");
                printwriter.println("<tr><td align='center'>");
                printwriter.println("<table border='1' width='90%' align='center'>");
                printwriter.println("<tr>");
                printwriter.println("<td width='15%' class='texttitulotabla'>Usuario</td>");
                printwriter.println("<td width='40%' class='texttitulotabla'>Nombre</td>");
                printwriter.println("<td width='45%' class='texttitulotabla'>Cargo</td>");
                printwriter.println("</tr>");
                CargaUsuarios(printwriter);
                printwriter.println("</table>");
                printwriter.println("<br><br>");
                printwriter.println("<center>");
                printwriter.println("<input type='button' name='nuevo' value='Nuevo' class='fondoinput' language='javascript' onclick='window.open(\"nuevousuario.jsp\",\"datos\")'>&nbsp;<input type='button' name='BtnImprimir' value='Imprimir' class='fondoinput' language='javascript' onclick='window.print()'></td>");
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

    void CargaUsuarios(PrintWriter printwriter)
    {
        rs = new Vector();
        int i = ADatos.ConsultaDB("select u.login,u.nombre,c.Descripcion from sgc.usuarios u, gdc.def_cargos c where u.idcargo = c.ID order by u.nombre");
        rs = ADatos.getResult();
        for(int j = 0; j < rs.size(); j += 3)
        {
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla'><a href='verusuario.jsp?USR=" + (String)rs.elementAt(j) + "'>" + (String)rs.elementAt(j) + "</a></td>");
            printwriter.println("<td class='textdesttabla'>" + (String)rs.elementAt(1 + j) + "</td>");
            printwriter.println("<td class='textdesttabla'>" + (String)rs.elementAt(2 + j) + "</td>");
            printwriter.println("</tr>");
        }

    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
}