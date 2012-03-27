package conf;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Acc.funciones;

public class datgen_map_org extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public datgen_map_org() {
        AFunc = new funciones();
        IdDoc = "";
        Tipo = "";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException {
        String s = "";
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s1 = (String)httpsession.getValue("SerapisUser");
        if(s1 != null && s1.length() > 0)
        {
            Tipo = httpservletrequest.getParameter("TIPO");
            if(Tipo == null || Tipo.length() == 0)
                Tipo = "ORG";
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<form name='frmextension' method='POST' enctype='multipart/form-data' action='datgen_map_org_procesa.jsp'>");
            CargaForm(printwriter);
            printwriter.println("</form>");
            printwriter.println("</body>");
            printwriter.println("</html>");
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "CONF", 6);
        }
    }

    void CargaForm(PrintWriter printwriter)
    {
        boolean flag = false;
        String s = "";
        String s1 = "";
        boolean flag1 = false;
        printwriter.println("<table border='1' align='center' width='95%'>");
        if(Tipo.compareTo("MAPA") == 0)
            printwriter.println("<tr><td width='95%' colspan='4' class='texttituloarea'>Mapa de Procesos<input name='tipoarch' type='hidden' value='MAPA'></td></tr>");
        else
            printwriter.println("<tr><td width='95%' colspan='4' class='texttituloarea'>Organigrama<input name='tipoarch' type='hidden' value='ORG'></td></tr>");
        printwriter.println("</table><BR>");
        printwriter.println("<table border='1' width='95%' align='center'>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla' width='12%'>Archivo</td>");
        printwriter.println("<td class='textdesttabla'><input name='archivo' style='WIDTH: 420px' type='file'></td>");
        printwriter.println("</tr>");
        printwriter.println("</table>");
        printwriter.println("<br><center><input type='button' name='anadirtipodoc' value='Grabar' class='fondoinput' onclick='document.frmextension.submit();';>");
        printwriter.println("</center>");
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    funciones AFunc;
    Vector rs;
    String IdDoc;
    String Tipo;
}