package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class listaextensiones extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public listaextensiones() {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        EsAdmin = "N";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        String s = "";
        String s2 = "";
        String s4 = "";
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s6 = (String)httpsession.getValue("SerapisUser");
        if(s6 != null && s6.length() > 0)
        {
        	rs = new Vector();
            ADatos.connect();
        	
        	String sPath = "";
        	String sqlDirSitio = "select dirsitio from gdc.datosgenerales";
            int iDirSitio = ADatos.ConsultaDB(sqlDirSitio);
            if (iDirSitio>0) {
    	        rs = ADatos.getResult();
    	        if (rs.size()>0) 
    	        	sPath = (String)rs.elementAt(0);
    	        else
    	        	sPath = "C:\\Archivos de programa\\Apache Tomcat 4.0\\webapps\\serapis";
    	        
    	        rs.clear();
        	}           
        	
        	
            String s7 = "select sigla,descripcion,imagen from gdc.extension order by descripcion";
            
            int i = ADatos.ConsultaDB(s7);
            rs = ADatos.getResult();
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<table border='0' width='95%'  align='center'>");
            printwriter.println("<tr><td width='80%' colspan='4' class='texttituloarea' align='center'>Extensiones</td></tr>");
            printwriter.println("</table>");
            if(rs.size() > 0)
            {
                printwriter.println("<table border='1' width='80%' align='center'>");
                printwriter.println("<tr>");
                printwriter.println("<td class='texttitulotabla'>&nbsp;</td>");
                printwriter.println("<td class='texttitulotabla'>C\363digo</td>");
                printwriter.println("<td class='texttitulotabla'>Descripci\363n</td>");
                printwriter.println("</tr>");
                for(int j = 0; j < rs.size(); j += 3)
                {
                    String s1 = (String)rs.elementAt(j);
                    String s3 = (String)rs.elementAt(j + 1);
                    String s5 = (String)rs.elementAt(j + 2);
                    printwriter.println("<tr>");
                    if(AFunc.ExisteArchivo(sPath + "\\images\\ext_" + s5) == 1)
                        printwriter.println("<td class='textdesttabla'><a href='verextension.jsp?ID=" + s1 + "'><img src='../images/ext_" + s5 + "' width=32 height=32 border=0></a></td>");
                    else
                        printwriter.println("<td class='textdesttabla'><a href='verextension.jsp?ID=" + s1 + "'><img src='../images/extdefault.gif' width=32 height=32 border=0></a></td>");
                    printwriter.println("<td class='textdesttabla'><a href='verextension.jsp?ID=" + s1 + "'>" + s1 + "</a></td>");
                    printwriter.println("<td class='textdesttabla'>" + s3 + "</td>");
                    printwriter.println("</tr>");
                }

                printwriter.println("</table>");
            }
            printwriter.println("<br>");
            printwriter.println("<br><center>");
            printwriter.println("<input type='button' name='nuevo' value='Nuevo' class='fondoinput'  language='javascript' onclick='window.open(\"verextension.jsp?TIPO=ADD\",\"datos\")'>");
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