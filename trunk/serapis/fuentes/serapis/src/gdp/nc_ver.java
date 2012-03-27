// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   nc_ver.java

package gdp;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class nc_ver extends HttpServlet
{

    public nc_ver()
    {
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
            IdNC = httpservletrequest.getParameter("ID");
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<form name='frmnc'>");
            CargaNC(printwriter);
            printwriter.println("<BR><center>");
            printwriter.println("<input type='button' name='BtnModificar' value='Modificar' class='fondoinput' language='javascript' onclick='window.open(\"nc_modifica.jsp?ID=" + IdNC.toString() + "\",\"datos\");'>&nbsp&nbsp;");
            printwriter.println("<input type='button' name='BtnVolver' value='Volver' class='fondoinput' language='javascript' onclick='window.open(\"nc.jsp\",\"datos\");'>&nbsp;&nbsp;");
            printwriter.println("<input type='button' name='BtnImprimir' value='Imprimir' class='fondoinput' language='javascript' onclick='window.print()'>");
            printwriter.println("</center>");
            printwriter.println("</form>");
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

    void CargaNC(PrintWriter printwriter)
    {
        String s = "";
        String s2 = "";
        String s3 = "";
        s = "SELECT nc.id,nc.nombre nombrenc, nc.fecha, nc.hora,nc.usuario, u.nombre nombreuser, ";
        s = s + " nc.chk_real,nc.chk_potencial,nc.chk_Proveedor,nc.chk_Proceso,nc.chk_Producto, ";
        s = s + " nc.chk_Insumo,nc.chk_Soft_Cargo,nc.chk_Reclamo,nc.txt_Identificacion,nc.txt_Area, ";
        s = s + " p.descripcion,nc.txt_Accion_Inmediata,nc.txt_Analisis,nc.txt_Accion_Correctiva,nc.txt_notifica_a ";
        s = s + " from gdc.no_conformidad nc, sgc.usuarios u, gdc.procesos p ";
        s = s + " where u.login = nc.usuario ";
        s = s + " and p.sigla = nc.txt_Area ";
        s = s + " and nc.id = " + IdNC;
        ADatos.connect();
        int i = ADatos.ConsultaDB(s);
        rs = ADatos.getResult();
        Vector vector = new Vector();
        vector = (Vector)rs.clone();
        if(vector.size() > 0)
        {
            printwriter.println("<table border='1' align='center' width='95%'>");
            printwriter.println("<tr><td class='texttituloarea' width='100%' colspan='2'>Informe de No Conformidades</td></tr>");
            printwriter.println("<tr><td class='textdesttabla' width='35%'>Informado Por</td>");
            printwriter.println("<td class='textdesttabla' width='65%'>" + (String)vector.elementAt(1) + "</td></tr>");
            printwriter.println("<tr><td class='textdesttabla' width='35%'>Fecha</td>");
            printwriter.println("<td class='textdesttabla' width='65%'>" + AFunc.ConstruyeFecha(((Integer)vector.elementAt(2)).toString(), "/", "dmy") + "</td></tr>");
            printwriter.println("<tr><td class='textdesttabla' width='35%'>Hora</td>");
            printwriter.println("<td class='textdesttabla' width='65%'>" + AFunc.ConstruyeHora(((Integer)vector.elementAt(3)).toString(), ":") + "</td></tr>");
            printwriter.println("<tr><td class='textdesttabla' width='35%'>Creado Por</td>");
            printwriter.println("<td class='textdesttabla' width='65%'>" + (String)vector.elementAt(5) + "</td></tr>");

            s= "select upper(nom_campo), descripcion from gdc.descripcion_noconformidad order by correlativo";
            int rc = ADatos.ConsultaDB(s);
            rs = ADatos.getResult();
            
            i=0;
            int totalCol = 4;
            int totalreg = rs.size() / 2;
            int totalFil = totalreg / totalCol + 1;
            int j=0, h=0, m=0;
            for (i=0;i<totalFil;i++){
            	m=0;
            	printwriter.println("<tr><td class='textdesttabla' width='100%' colspan='2'>");
            	printwriter.println("<table border='1' align='center' width='100%'>");        	
            	for (j=0;j<totalCol;j++){
            		if (h<rs.size()  ) {
            			String sChecked = "";
            			if ((vector.elementAt(6).toString().compareTo("S")==0 && ((String)rs.elementAt(h)).equals("CHK_REAL"))
            					|| (vector.elementAt(7).toString().compareTo("S")==0 && ((String)rs.elementAt(h)).equals("CHK_POTENCIAL")) 
            					|| (vector.elementAt(8).toString().compareTo("S")==0 && ((String)rs.elementAt(h)).equals("CHK_PROVEEDOR")) 
            					|| (vector.elementAt(9).toString().compareTo("S")==0 && ((String)rs.elementAt(h)).equals("CHK_PROCESO")) 
            					|| (vector.elementAt(10).toString().compareTo("S")==0 && ((String)rs.elementAt(h)).equals("CHK_PRODUCTO")) 
            					|| (vector.elementAt(11).toString().compareTo("S")==0 && ((String)rs.elementAt(h)).equals("CHK_INSUMO")) 
            					|| (vector.elementAt(12).toString().compareTo("S")==0 && ((String)rs.elementAt(h)).equals("CHK_SOFT_CARGO")) 
            					|| (vector.elementAt(13).toString().compareTo("S")==0 && ((String)rs.elementAt(h)).equals("CHK_RECLAMO")) )
            				sChecked = "Checked";
            			
            			printwriter.println("   <td colspan='" + (((h+2)>=rs.size())?Integer.toString((totalCol-j)):"1") + "' nowrap class='textdesttabla' width=''><INPUT type='checkbox' name='" + (String)rs.elementAt(h) + "' language='javascript' value='S' " + sChecked + ">" + (String)rs.elementAt(h + 1) + "</td>");
            		}
            		h=h+2;
            	}
            	printwriter.println("</table>");
            	printwriter.println("</tr>");        	        		
            }            
            printwriter.println("<tr><td class='texttitulotabla' width='100%' colspan='2'>I. Identificaci\363n y Descripci\363n NC/Falla</td></tr>");
            printwriter.println("<tr><td class='textdesttabla' width='100%' colspan='2'>" + (String)vector.elementAt(14) + "</td></tr>");
            printwriter.println("<tr><td class='texttitulotabla' width='100%' colspan='2'>II. \301rea a la que pertenece la NC/Falla</td></tr>");
            printwriter.println("<tr><td class='textdesttabla' width='100%' colspan='2'>" + (String)vector.elementAt(16) + "</td></tr>");
            printwriter.println("<tr><td class='texttitulotabla' width='100%' colspan='2'>III. Acci\363n Inmediata</td></tr>");
            printwriter.println("<tr><td class='textdesttabla' width='100%' colspan='2'>" + (String)vector.elementAt(17) + "</td></tr>");
            printwriter.println("<tr><td class='texttitulotabla' width='100%' colspan='2'>IV. An\341lisis de Causas(Origen)</td></tr>");
            printwriter.println("<tr><td class='textdesttabla' width='100%' colspan='2'>" + (String)vector.elementAt(18) + "</td></tr>");
            printwriter.println("<tr><td class='texttitulotabla' width='100%' colspan='2'>V. Acci\363n Preventiva / Correctiva</td></tr>");
            printwriter.println("<tr><td class='textdesttabla' width='100%' colspan='2'>" + (String)vector.elementAt(19) + "</td></tr>");
            printwriter.println("<tr><td class='texttitulotabla' width='100%' colspan='2'>Lista Distribuci\363n. Notifica a:</td></tr>");
            printwriter.println("<tr><td class='textdesttabla' width='100%' colspan='2'>");
            String s5 = "";
            StringTokenizer stringtokenizer = new StringTokenizer((String)vector.elementAt(20), ",");
            do
            {
                if(!stringtokenizer.hasMoreTokens())
                    break;
                String s4 = stringtokenizer.nextToken();
                String s1 = "select nombre from sgc.usuarios where login ='" + s4 + "'";
                j = ADatos.ConsultaDB(s1);
                rs = new Vector();
                rs = ADatos.getResult();
                if(rs.size() > 0)
                    printwriter.println((String)rs.elementAt(0) + "<BR>");
            } while(true);
            printwriter.println("</td></tr>");
            printwriter.println("</table>");
        }
    }
    
    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    String IdNC;
}