package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class listamaestra extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public listamaestra() {
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
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<table border='0' width='95%' align='center'>");
            printwriter.println("<tr><td align='center'  class='texttitulomayor'>Lista Maestra. Gesti\363n Documental de Calidad</td></tr>");
            printwriter.println("</table>");
            CargaDocumentos(printwriter);
            String s1 = "select login from sgc.perfil where login='" + s + "' and administrador='S'";
            rs = new Vector();
            ADatos.connect();
            int i = ADatos.ConsultaDB(s1);
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                printwriter.println("<br><br>");
                printwriter.println("<center>");
                printwriter.println("<input type='button' name='nuevo' value='Nuevo' class='fondoinput' language='javascript' onclick='window.open(\"nuevodocumentocalidad.jsp\",\"datos\")'>&nbsp;<input type='button' name='BtnImprimir' value='Imprimir' class='fondoinput' language='javascript' onclick='window.print()'></td>");
                printwriter.println("</center>");
            }
            printwriter.println("</body>");
            printwriter.println("</html>");
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "GDC", 6);
        }
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    void CargaDocumentos(PrintWriter printwriter)
    {
        rs = new Vector();
        Integer integer = new Integer(0);
        Integer integer2 = new Integer(0);
        String s = "";
        String s1 = "";
        String s3 = "";
        String sSQL = "";
        String sWorkFlowDes = "";
        ADatos.connect();
        sSQL = "select dc.id,p.descripcion,td.descripcion d1,dc.descripcion d2,dc.tiemporetencion,dc.tiempomedida, ";
        sSQL = sSQL + "     w.nombre workflow, dc.tipo   ";
        sSQL = sSQL + "from gdc.documentoscalidad dc,gdc.procesos p,gdc.tipodocumentos td, gdc.workflow w ";
        sSQL = sSQL + "where      dc.tipodocumento = td.sigla and dc.proceso = p.sigla and dc.wf_ejecucion = w.idwf ";
        sSQL = sSQL + "union ";
        sSQL = sSQL + "select dc.id,p.descripcion,td.descripcion d1,dc.descripcion d2,dc.tiemporetencion,dc.tiempomedida, ";
        sSQL = sSQL + "     w.nombre workflow, dc.tipo   ";
        sSQL = sSQL + "from gdc.documentoscalidad dc,gdc.procesos p,gdc.tipodocumentos td, gdc.workflow w ";
        sSQL = sSQL + "where      dc.tipodocumento = td.sigla and dc.proceso = p.sigla and dc.wf_aprobacion = w.idwf ";
        sSQL = sSQL + "union ";
        sSQL = sSQL + "select dc.id,p.descripcion,td.descripcion d1,dc.descripcion d2,dc.tiemporetencion,dc.tiempomedida, ";
        sSQL = sSQL + "     '' workflow, dc.tipo    ";
        sSQL = sSQL + "from gdc.documentoscalidad dc,gdc.procesos p,gdc.tipodocumentos td ";
        sSQL = sSQL + "where      dc.tipodocumento = td.sigla and dc.proceso = p.sigla and dc.wf_ejecucion = 0 and dc.wf_aprobacion = 0 ";
    	sSQL = sSQL + "order by descripcion,tipo,d1,d2  ";
        int i = ADatos.ConsultaDB(sSQL);
        rs = ADatos.getResult();
        if(rs.size() > 0)
        {
            for(int j = 0; j < rs.size(); j += 8)
            {
                if(s.compareTo((String)rs.elementAt(1 + j)) != 0)
                {
                    if(s.length() > 0)
                    {
                        printwriter.println("</table>");
                        printwriter.println("<BR>");
                    }
                    printwriter.println("<table border='1' align='center' width='95%'>");
                    printwriter.println("<tr><td class='texttituloarea' colspan='6'>" + (String)rs.elementAt(1 + j) + "</td></tr>");
                    printwriter.println("</table>");
                    printwriter.println("<table border='1' align='center' width='95%'>");
                    s = (String)rs.elementAt(1 + j);
                    printwriter.println("<tr>");
                    printwriter.println("<td class='texttitulotabla' width='5%'>&nbsp;</td>");
                    printwriter.println("<td class='texttitulotabla' width='10%' align='center'>Tipo Documento</td>");
                    printwriter.println("<td class='texttitulotabla' width='40%' align='center'>Nombre</td>");
                    printwriter.println("<td class='texttitulotabla' width='5%'  align='center'>Tipo</td>");
                    printwriter.println("<td class='texttitulotabla' width='30%' align='center'>WorkFlow</td>");
                    printwriter.println("<td class='texttitulotabla' width='10%' align='center'>Tiempo Retenci\363n</td>");
                    printwriter.println("</tr>");
                }
                Integer integer1 = (Integer)rs.elementAt(j);
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla' valign='top'><a href='verdocumentocalidad.jsp?ID=" + integer1.intValue() + "'><IMG src='../images/folderterminal.gif' width=16 height=16 border='0'></a></td>");
                printwriter.println("<td class='textdesttabla' valign='top'>" + (String)rs.elementAt(2 + j) + "</td>");
                printwriter.println("<td class='textdesttabla' valign='top'>" + (String)rs.elementAt(3 + j) + "</td>");
                
                String s4 = (String)rs.elementAt(7 + j);
                if(s4.compareTo("R") == 0)
                	printwriter.println("<td class='textdesttabla' valign='top'>Registro</td>");
                else
                	printwriter.println("<td class='textdesttabla' valign='top'>Documento</td>");
                
                printwriter.println("<td class='textdesttabla' valign='top'>" + (String)rs.elementAt(6 + j) + "</td>");
                
                /*if(s4.compareTo("R") == 0){
                    printwriter.println("<td class='textdesttabla' valign='top'>Registro</td>");
                    sWorkFlowDes = (String)rs.elementAt(7 + j);
                    if (sWorkFlowDes.trim()=="")
                    	printwriter.println("<td class='textdesttabla' valign='top'>&nbsp;</td>");
                    else
                    	printwriter.println("<td class='textdesttabla' valign='top'>" + (String)rs.elementAt(7 + j) + "</td>");
                } else {
                    printwriter.println("<td class='textdesttabla' valign='top'>Documento</td>");
                    sWorkFlowDes = (String)rs.elementAt(6 + j);
                    if (sWorkFlowDes.trim()=="")
                    	printwriter.println("<td class='textdesttabla' valign='top'>&nbsp;</td>");
                    else
                    	printwriter.println("<td class='textdesttabla' valign='top'>" + (String)rs.elementAt(6 + j) + "</td>");
                }*/
                Integer integer3 = (Integer)rs.elementAt(4 + j);
                if(integer3.intValue() > 0)
                {
                    String s2 = (String)rs.elementAt(5 + j);
                    if(s2.compareTo("A\361o") == 0)
                        printwriter.println("<td class='textdesttabla'>" + integer3.toString() + " A\361os</td>");
                    if(s2.compareTo("Mes") == 0)
                        printwriter.println("<td class='textdesttabla'>" + integer3.toString() + " Meses</td>");
                    if(s2.compareTo("D\355a") == 0)
                        printwriter.println("<td class='textdesttabla'>" + integer3.toString() + " Dias</td>");
                } else
                {
                    printwriter.println("<td class='textdesttabla'>&nbsp;</td>");
                }
                printwriter.println("</tr>");
            }

            printwriter.println("</table>");
        } else
        {
            printwriter.println("<table border='0' align='center' width='80%'>");
            printwriter.println("<tr>");
            printwriter.println("<td class='texttitulotabla' align='center'>No hay documentos por desplegar</td>");
            printwriter.println("<tr>");
            printwriter.println("</table>");
        }
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
}