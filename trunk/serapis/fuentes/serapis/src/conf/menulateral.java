package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class menulateral extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public menulateral() {
        ADatos = new AccDataBase();
        ADatosD = new AccDataBase();
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
            String s1 = httpservletrequest.getParameter("TIPO");
            String s2 = httpservletrequest.getParameter("TIPODOC");
            if(s1 == null)
                s1 = "";
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<BODY bgcolor='#FAE5B9' leftmargin='0' topmargin='0'>");
            printwriter.println("<table cellpadding='0' cellspacing='0' border='0' width='100%'>");
            printwriter.println("<tr>");
            printwriter.println("<td height='28px' class='fondogris' valign='middle'>&nbsp;&nbsp;<img src='../images/menu_conf.jpg' widht='129' height='26'></td>");
            printwriter.println("</tr>");
            printwriter.println("</table>");
            printwriter.println("<table border='0' width='100%' cellspacing='0' cellpadding='0'>");
            EscribeOpcion(printwriter, -1, "", "", "N");
            if(s1.compareTo("USR") == 0 || s1.compareTo("PEF") == 0 || s1.compareTo("CLI") == 0)
                EscribeOpcion(printwriter, 1, "Seguridad", "<a href='inicio.jsp?TIPO=USR' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 1, "Seguridad", "<a href='inicio.jsp?TIPO=USR' target = 'cuerpo'>", "N");
            if(s1.compareTo("USR") == 0)
                EscribeOpcion(printwriter, 2, "Usuarios", "<a href='inicio.jsp?TIPO=USR' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 2, "Usuarios", "<a href='inicio.jsp?TIPO=USR' target = 'cuerpo'>", "N");
            if(s1.compareTo("CLI") == 0)
                EscribeOpcion(printwriter, 2, "Clientes", "<a href='inicio.jsp?TIPO=CLI' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 2, "Clientes", "<a href='inicio.jsp?TIPO=CLI' target = 'cuerpo'>", "N");
            if(s1.compareTo("PEF") == 0)
                EscribeOpcion(printwriter, 2, "Perfiles", "<a href='inicio.jsp?TIPO=PEF' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 2, "Perfiles", "<a href='inicio.jsp?TIPO=PEF' target = 'cuerpo'>", "N");
            if(s1.compareTo("PROC") == 0 || s1.compareTo("TDOC") == 0 || s1.compareTo("LIMA") == 0 || s1.compareTo("DIST") == 0 || s1.compareTo("CARO") == 0 || s1.compareTo("GPROC") == 0)
                EscribeOpcion(printwriter, 1, "Definiciones", "<a href='inicio.jsp?TIPO=PROC' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 1, "Definiciones", "<a href='inicio.jsp?TIPO=PROC' target = 'cuerpo'>", "N");
            if(s1.compareTo("PROC") == 0)
                EscribeOpcion(printwriter, 2, "Procesos", "<a href='inicio.jsp?TIPO=PROC' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 2, "Procesos", "<a href='inicio.jsp?TIPO=PROC' target = 'cuerpo'>", "N");
            if(s1.compareTo("TDOC") == 0)
                EscribeOpcion(printwriter, 2, "Tipo Documentos", "<a href='inicio.jsp?TIPO=TDOC' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 2, "Tipo Documentos", "<a href='inicio.jsp?TIPO=TDOC' target = 'cuerpo'>", "N");
            if(s1.compareTo("LIMA") == 0)
                EscribeOpcion(printwriter, 2, "Lista Maestra", "<a href='inicio.jsp?TIPO=LIMA' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 2, "Lista Maestra", "<a href='inicio.jsp?TIPO=LIMA' target = 'cuerpo'>", "N");
            if(s1.compareTo("DIST") == 0)
                EscribeOpcion(printwriter, 2, "Listas de Distribuci\363n", "<a href='inicio.jsp?TIPO=DIST' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 2, "Listas de Distribuci\363n", "<a href='inicio.jsp?TIPO=DIST' target = 'cuerpo'>", "N");
            if(s1.compareTo("CARO") == 0)
                EscribeOpcion(printwriter, 2, "Cargos y Roles", "<a href='inicio.jsp?TIPO=CARO' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 2, "Cargos y Roles", "<a href='inicio.jsp?TIPO=CARO' target = 'cuerpo'>", "N");
            if(s1.compareTo("GPROC") == 0)
                EscribeOpcion(printwriter, 2, "Gesti\363n de Procesos", "<a href='inicio.jsp?TIPO=GPROC' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 2, "Gesti\363n de Procesos", "<a href='inicio.jsp?TIPO=GPROC' target = 'cuerpo'>", "N");
            if(s1.compareTo("DG") == 0 || s1.compareTo("EXT") == 0 || s1.compareTo("ORG") == 0 || s1.compareTo("MAPA") == 0 || s1.compareTo("LIDER") == 0)
                EscribeOpcion(printwriter, 1, "Generales", "<a href='inicio.jsp?TIPO=DG' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 1, "Generales", "<a href='inicio.jsp?TIPO=DG' target = 'cuerpo'>", "N");
            if(s1.compareTo("DG") == 0)
                EscribeOpcion(printwriter, 2, "Datos Generales", "<a href='inicio.jsp?TIPO=DG' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 2, "Datos Generales", "<a href='inicio.jsp?TIPO=DG' target = 'cuerpo'>", "N");
            if(s1.compareTo("ORG") == 0)
                EscribeOpcion(printwriter, 2, "Organigrama", "<a href='inicio.jsp?TIPO=ORG' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 2, "Organigrama", "<a href='inicio.jsp?TIPO=ORG' target = 'cuerpo'>", "N");
            if(s1.compareTo("MAPA") == 0)
                EscribeOpcion(printwriter, 2, "Mapa de Procesos", "<a href='inicio.jsp?TIPO=MAPA' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 2, "Mapa de Procesos", "<a href='inicio.jsp?TIPO=MAPA' target = 'cuerpo'>", "N");
            if(s1.compareTo("LIDER") == 0)
                EscribeOpcion(printwriter, 2, "L\355deres", "<a href='inicio.jsp?TIPO=LIDER' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 2, "L\355deres", "<a href='inicio.jsp?TIPO=LIDER' target = 'cuerpo'>", "N");
            if(s1.compareTo("EXT") == 0)
                EscribeOpcion(printwriter, 2, "Extensiones", "<a href='inicio.jsp?TIPO=EXT' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 2, "Extensiones", "<a href='inicio.jsp?TIPO=EXT' target = 'cuerpo'>", "N");
            printwriter.println("</table>");
            printwriter.println("</body>");
            printwriter.println("</html>");
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "CONF", 6);
        }
    }

    void ObtieneDocumentos(String s, PrintWriter printwriter)
    {
        rs = new Vector();
        ADatos.connect();
        int i = ADatos.ConsultaDB("select distinct td.sigla,td.descripcion from documentos d, tipodocumentos td where d.proceso='" + s + "' and td.sigla = d.tipodoc");
        rs = ADatos.getResult();
        for(int j = 0; j < rs.size(); j += 2)
        {
            printwriter.println("<tr><td bgcolor='#FAE5B9'>&nbsp;</td>");
            printwriter.println("<td bgcolor='#FAE5B9'>&nbsp;</td>");
            printwriter.println("<td bgcolor='#FAE5B9'  width='5%'><a href='inicio.jsp?TIPO=DOC&AREA=" + s + "&TIPODOC=" + (String)rs.elementAt(j) + "' target = 'contenido'><IMG src='images/folderterminal.gif' border=0 width=17 height=13></a></td>");
            printwriter.println("<td bgcolor='#FAE5B9' valign='top'><a href='inicio.jsp?TIPO=DOC&AREA=" + s + "&TIPODOC=" + (String)rs.elementAt(j) + "' target = 'contenido'>" + (String)rs.elementAt(1 + j) + "</a></td></tr>");
        }

    }

    void CargaMenuDinamico(String s, PrintWriter printwriter)
    {
        rsD = new Vector();
        String s1 = "select sigla,descripcion from procesos order by descripcion";
        ADatosD.connect();
        int i = ADatosD.ConsultaDB(s1);
        rsD = ADatosD.getResult();
        for(int j = 0; j < rsD.size(); j += 2)
        {
            String s2 = (String)rsD.elementAt(j);
            String s3 = (String)rsD.elementAt(j + 1);
            if(s2.compareTo(s) == 0)
            {
                printwriter.println("<tr><td bgcolor='#FAE5B9'>&nbsp;</td>");
                printwriter.println("<td bgcolor='#FAE5B9' width='5%'><a href='inicio.jsp?TIPO=DOC&AREA=" + s2 + "' target = 'contenido'><IMG src='images/folderlibro.gif' width=17 height=13 border=0></a></td>");
                printwriter.println("<td bgcolor='#FAE5B9' colspan='2' valign='top'><a href='inicio.jsp?TIPO=DOC&AREA=" + s2 + "' target = 'contenido'>" + s3 + "</a></td></tr>");
                ObtieneDocumentos(s, printwriter);
            } else
            {
                printwriter.println("<tr><td bgcolor='#FAE5B9'>&nbsp;</td>");
                printwriter.println("<td bgcolor='#FAE5B9' width='5%'><a href='inicio.jsp?TIPO=DOC&AREA=" + s2 + "' target = 'contenido'><IMG src='images/foldercerrado.gif' width=17 height=13 border=0></a></td>");
                printwriter.println("<td bgcolor='#FAE5B9' colspan='2' valign='top'><a href='inicio.jsp?TIPO=DOC&AREA=" + s2 + "' target = 'contenido'>" + s3 + "</a></td></tr>");
            }
        }

    }

    void EscribeOpcion(PrintWriter printwriter, int i, String s, String s1, String s2)
    {
        String s3;
        if(s2.compareTo("S") == 0)
            s3 = "folderabierto.gif";
        else
            s3 = "foldercerrado.gif";
        switch(i)
        {
        case 0: // '\0'
        default:
            break;

        case -1:
            printwriter.println("<tr>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' valign='center' align='left'>&nbsp;</td>");
            printwriter.println("</tr>");
            break;

        case 1: // '\001'
            printwriter.println("<tr>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='center' align='left'><IMG src='../images/" + s3 + "' width=17 height=13 border=0></td>");
            if(s1.length() > 0)
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='4' valign='center' align='left'>" + s1 + s + "</a></td>");
            else
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='4' valign='center' align='left'>" + s + "</a></td>");
            printwriter.println("</tr>");
            break;

        case 2: // '\002'
            printwriter.println("<tr>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='center' align='left'><IMG src='../images/" + s3 + "' width=17 height=13 border=0></td>");
            if(s1.length() > 0)
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='4' valign='center' align='left'>" + s1 + s + "</a></td>");
            else
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='4' valign='center' align='left'>" + s + "</a></td>");
            printwriter.println("</tr>");
            break;

        case 3: // '\003'
            printwriter.println("<tr>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='center' align='left'><IMG src='../images/" + s3 + "' width=17 height=13 border=0></td>");
            if(s1.length() > 0)
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='4' valign='center' align='left'>" + s1 + s + "</a></td>");
            else
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='4' valign='center' align='left'>" + s + "</a></td>");
            printwriter.println("</tr>");
            break;

        case 4: // '\004'
            printwriter.println("<tr>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='2%' valign='center' align='left'><IMG src='../images/" + s3 + "' width=17 height=13 border=0></td>");
            if(s1.length() > 0)
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='4' valign='center' align='left'>" + s1 + s + "</a></td>");
            else
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='4' valign='center' align='left'>" + s + "</a></td>");
            printwriter.println("</tr>");
            break;
        }
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    AccDataBase ADatos;
    Vector rs;
    AccDataBase ADatosD;
    Vector rsD;
    funciones AFunc;
}