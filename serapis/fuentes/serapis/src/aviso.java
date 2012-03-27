import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Acc.AccDataBase;
import Acc.funciones;

public class aviso extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public aviso() {
        ADatos = new AccDataBase();
        AFunc = new funciones();
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s = (String)httpsession.getValue("SerapisUser");
        printwriter.println("<html>");
        printwriter.println("<head>");
        printwriter.println("</head>");
        printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='serapis.css'>");
        printwriter.println("<title>SERAPIS. FLUJO DE APROBACI\323N</title>");
        printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
        if(s != null && s.length() > 0) {
            printwriter.println("<table border='0' width='99%' align='center'>");
            printwriter.println("<tr><td align='center'  class='texttitulomayor' colspan='6'>Detalle de Autorizaciones</td></tr>");
            String s1 = "SELECT wff.iddocumento,wff.idwf,wf.nombre nombrewf,wff.idpaso,wfp.nombre nombrepaso,wff.detalle,wff.fecha,wff.hora,id_flujo,wff.tipodoc";
            s1 = s1 + " from gdc.workflow_flujo wff, gdc.workflow wf, gdc.workflow_pasos wfp";
            s1 = s1 + " where wf.idwf = wff.idwf and wfp.idwf = wf.idwf and wfp.idwf = wff.idwf and wff.idpaso = wfp.idpaso";
            s1 = s1 + " AND wff.usuario='" + s + "' AND wff.accion='P'";
            ADatos.connect();
            int i = ADatos.ConsultaDB(s1);
            rs = new Vector();
            rs = ADatos.getResult();
            if(rs.size() > 0) {
                String s2 = "";
                printwriter.println("<tr>");
                printwriter.println("<td align='left' class='texttituloarea' width='23'% valign='top'>WorkFlow</td>");
                printwriter.println("<td align='center' class='texttituloarea' colspan='2' width='20%' valign='top'>Paso</td>");
                printwriter.println("<td align='left' class='texttituloarea' width='45%' valign='top'>Detalle</td>");
                printwriter.println("<td align='left' class='texttituloarea' width='12%' valign='top'>Fecha</td>");
                printwriter.println("</tr>");
                int j = 1;
                for(int k = 0; k < rs.size(); k += 10) {
                    if(j % 2 == 0)
                        printwriter.println("<tr bgcolor='#FAE5B9'>");
                    else
                        printwriter.println("<tr>");
                    printwriter.println("<td align='left' class='textgral' valign='top'><a href='workflow/ejecutawf.jsp?ID=" + ((Integer)rs.elementAt(k + 8)).toString() + "&IDDOC=" + ((Integer)rs.elementAt(k)).toString() + "&IDWF=" + ((Integer)rs.elementAt(k + 1)).toString() + "&IDPASO=" + ((Integer)rs.elementAt(k + 3)).toString() + "&BD=" + (String)rs.elementAt(k + 9) + "'>" + (String)rs.elementAt(k + 2) + "</a></td>");
                    printwriter.println("<td align='left' class='textgral' valign='top'>" + ((Integer)rs.elementAt(k + 3)).toString() + "</td>");
                    printwriter.println("<td align='left' class='textgral' valign='top'>" + (String)rs.elementAt(k + 4) + "</td>");
                    printwriter.println("<td align='left' class='textgral' valign='top'>" + (String)rs.elementAt(k + 5) + "</td>");
                    printwriter.println("<td align='left' class='textgral' valign='top'>");
                    printwriter.println(AFunc.ConstruyeFecha(((Integer)rs.elementAt(k + 6)).toString(), "/", "dmy"));
                    String s3 = ((Integer)rs.elementAt(k + 7)).toString();
                    s3 = s3.substring(0, s3.length() - 4) + ":" + s3.substring(s3.length() - 4, s3.length() - 2) + ":" + s3.substring(s3.length() - 2, s3.length());
                    printwriter.println("<BR>" + s3);
                    printwriter.println("</td>");
                    printwriter.println("</tr>");
                    j++;
                }

                printwriter.println("</table>");
                printwriter.println("<BR><center><input type='button' name='BtnImprimir' value='Imprimir' class='fondoinput' language='javascript' onclick='window.print()'></center></td>");
            } else {
                printwriter.println("<BR><BR><BR><BR>");
                printwriter.println("<table border='0' width='99%' align='center'>");
                printwriter.println("<tr><td align='center' class='texttitulomayor'>Aviso</td></tr>");
                printwriter.println("<tr><td align='center' class='textgral'>&nbsp;</td></tr>");
                printwriter.println("<tr><td align='center' class='textgral'>NO existen autorizaciones pendientes</td></tr>");
                printwriter.println("<tr><td align='center' class='textgral'><input class='fondoinput' name='btnCerrar' value='Aceptar' type='button' language='javascript' onclick='window.close();'></td></tr>");
                printwriter.println("</table>");
            }
        } else {
            printwriter.println("<BR><BR><BR><BR>");
            printwriter.println("<table border='0' width='99%' align='center'>");
            printwriter.println("<tr><td align='center' class='texttitulomayor'>Aviso</td></tr>");
            printwriter.println("<tr><td align='center' class='textgral'>&nbsp;</td></tr>");
            printwriter.println("<tr><td align='center' class='textgral'>No se encuentra su informaci\363n de sesi\363n.</td></tr>");
            printwriter.println("<tr><td align='center' class='textgral'>Inicie su sesi\363n en SERAPIS para ver las autorizaciones pendientes.</td></tr>");
            printwriter.println("<tr><td align='center' class='textgral'>&nbsp;</td></tr>");
            printwriter.println("<tr><td align='center' class='textgral'><input class='fondoinput' name='btnCerrar' value='Aceptar' type='button' language='javascript' onclick='window.close();'></td></tr>");
            printwriter.println("</table>");
        }
        printwriter.println("</body>");
        printwriter.println("</html>");
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException {
        doGet(httpservletrequest, httpservletresponse);
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
}