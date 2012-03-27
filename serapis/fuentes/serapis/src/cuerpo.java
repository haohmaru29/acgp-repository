import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class cuerpo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public cuerpo() {
        ADatos = new AccDataBase();
        AFunc = new funciones();
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException {
        Vector vector = new Vector();
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        httpsession.putValue("SerapisLic", "0");
        printwriter.println("<html>");
        printwriter.println("<head>");
        printwriter.println("<title>SERAPIS. Sistema de Gesti\363n de Calidad.</title>");
        printwriter.println("</head>");
        printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='serapis.css'>");
        printwriter.println("<TITLE>SERAPIS. Sistema de Gesti\363n de Calidad</TITLE>");
        sql = "select empresa,nrolic,version,fechacad,glosaproy from sgc.licencia";
        ADatos.connect();
        int i = ADatos.ConsultaDB(sql);
        rs = new Vector();
        rs = ADatos.getResult();
        if(rs.size() > 0) {
            Vector vector1 = AFunc.ObtieneFecha();
            String s = (String)rs.elementAt(0);
            String s1 = (String)rs.elementAt(1);
            String s2 = (String)rs.elementAt(2);
            String s3 = (String)rs.elementAt(3);
            String s4 = (String)rs.elementAt(4);
            httpsession.putValue("SerapisGlosa", s4);
            s3 = AFunc.desencripta(s3);
            if(s3 == null || s3.length() == 0)
                s3 = "0";
            Integer integer = new Integer(s3);
            String s5 = (String)vector1.elementAt(0);
            Integer integer1 = new Integer(s5);
            if(integer1.longValue() <= integer.longValue()) {
                httpsession.putValue("SerapisLic", "1");
                printwriter.println("<body leftmargin='0' topmargin='0'>");
                AFunc.cargamenucompleto(printwriter, 0, "Inicio");
                s = AFunc.desencripta(s);
                s1 = AFunc.desencripta(s1);
                s2 = AFunc.desencripta(s2);
                sql = "SELECT mision,vision from gdc.calidad";
                int j = ADatos.ConsultaDB(sql);
                rs = new Vector();
                rs = ADatos.getResult();
                String s6 = "";
                String s7 = "";
                if(rs.size() > 0) {
                    s6 = (String)rs.elementAt(0);
                    s7 = (String)rs.elementAt(1);
                }
                printwriter.println("<table border='0' width='70%' align='center' cellspacing='0' cellpadding='0'>");
                printwriter.println("<TR><TD colspan='4'>&nbsp;</TD></TR>");
                printwriter.println("<TR><TD colspan='4'>&nbsp;</TD></TR>");
                printwriter.println("<TR><TD colspan='4'>&nbsp;</TD></TR>");
                printwriter.println("<TR><TD colspan='4'>&nbsp;</TD></TR>");
                printwriter.println("<TR>");
                printwriter.println("<TD width='50%' class='tituloespecial' align='center' colspan='2'>Misi\363n</TD>");
                printwriter.println("<TD width='4%' class='tituloborde' height='30' border='0'>&nbsp;</TD>");
                printwriter.println("<TD width='46%' class='logotipo' rowspan='9'>&nbsp;</TD>");
                printwriter.println("</TR>");
                printwriter.println("<TR><TD bgcolor='#F9F2D4' colspan='3'>&nbsp;</TD></TR>");
                printwriter.println("<TR>");
                printwriter.println("<TD bgcolor='#F9F2D4' width='5%'><img border='0' src='images/transparent.gif'></TD>");
                printwriter.println("<TD class='contenidoespecial'>" + s6 + "</TD>");
                printwriter.println("<TD class='contenidoespecial'>&nbsp;</TD>");
                printwriter.println("</TR>");
                printwriter.println("<TR><TD bgcolor='#F9F2D4' colspan='3'>&nbsp;</TD></TR>");
                printwriter.println("<TR><TD colspan='3'>&nbsp;</TD></TR>");
                printwriter.println("<TR>");
                printwriter.println("<TD class='tituloespecial' align='center' colspan='2'>Visi\363n</TD>");
                printwriter.println("<TD class='tituloborde' height='30'></TD>");
                printwriter.println("</TR>");
                printwriter.println("<TR><TD bgcolor='#F9F2D4' colspan='3'>&nbsp;</TD></TR>");
                printwriter.println("<TR>");
                printwriter.println("<TD bgcolor='#F9F2D4' width='5%'><img border='0' src='images/transparent.gif'></TD>");
                printwriter.println("<TD class='contenidoespecial'>" + s7 + "</TD>");
                printwriter.println("<TD class='contenidoespecial'>&nbsp;</TD>");
                printwriter.println("</TR>");
                printwriter.println("<TR><TD bgcolor='#F9F2D4' colspan='3'>&nbsp;</TD><TD>&nbsp;</TD></TR>");
                printwriter.println("<TR><TD class='contenidopie' align='center' colspan='4'><center><BR><BR><BR><BR>Copyright\251 ACGP Ingenier\355a de Software S.A.<BR>Todos los derechos reservados.<BR>SERAPIS " + s2 + ". Licencia: " + s1 + "</center></TD></TR>");
                printwriter.println("</table>");
            } else {
                printwriter.println("<table border='0' width='60%' align='center'>");
                printwriter.println("<TR><TD class='textpietabla' align='center'><B>Su licencia de SERAPIS ha caducado.</B></B></TD></TR>");
                printwriter.println("<TR><TD class='textpietabla' align='center'>Contacte a su proveedor de servicios para mayor informaci\363n</B></TD></TR>");
                printwriter.println("</table>");
            }
        } else {
            printwriter.println("<table border='0' width='60%' align='center'>");
            printwriter.println("<TR><TD class='textpietabla' align='center'><B>No esta autorizado para utilizar SERAPIS</B></B></TD></TR>");
            printwriter.println("<TR><TD class='textpietabla' align='center'>Contacte a su proveedor de servicios para mayor informaci\363n</B></TD></TR>");
            printwriter.println("</table>");
        }
        printwriter.println("</body>");
        printwriter.println("</html>");
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    String sql;
}