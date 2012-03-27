// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   nc_procesa.java

package gdp;

import Acc.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class nc_procesa extends HttpServlet
{

    public nc_procesa()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        EnvMail = new correo();
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s3 = (String)httpsession.getValue("SerapisUser");
        if(s3 != null && s3.length() > 0)
        {
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);            
            String s4 = httpservletrequest.getParameter("txtnombre");
            
            String s5 = (httpservletrequest.getParameter("CHK_REAL")!=null)?httpservletrequest.getParameter("CHK_REAL"):"N";
            String s6 = (httpservletrequest.getParameter("CHK_POTENCIAL")!=null)?httpservletrequest.getParameter("CHK_POTENCIAL"):"N";
            String s7 = (httpservletrequest.getParameter("CHK_PROVEEDOR")!=null)?httpservletrequest.getParameter("CHK_PROVEEDOR"):"N";
            String s8 = (httpservletrequest.getParameter("CHK_PROCESO")!=null)?httpservletrequest.getParameter("CHK_PROCESO"):"N";
            String s9 = (httpservletrequest.getParameter("CHK_PRODUCTO")!=null)?httpservletrequest.getParameter("CHK_PRODUCTO"):"N";
            String s10 = (httpservletrequest.getParameter("CHK_INSUMO")!=null)?httpservletrequest.getParameter("CHK_INSUMO"):"N";
            String s11 = (httpservletrequest.getParameter("CHK_SOFT_CARGO")!=null)?httpservletrequest.getParameter("CHK_SOFT_CARGO"):"N";
            String s12 = (httpservletrequest.getParameter("CHK_RECLAMO")!=null)?httpservletrequest.getParameter("CHK_RECLAMO"):"N";

            String s13 = httpservletrequest.getParameter("txtI");
            String s14 = httpservletrequest.getParameter("selArea");
            String s15 = httpservletrequest.getParameter("txtIII");
            String s16 = httpservletrequest.getParameter("txtIV");
            String s17 = httpservletrequest.getParameter("txtV");
            String s18 = httpservletrequest.getParameter("ListaDistrubucion");
            Vector vector = new Vector();
            vector = AFunc.ObtieneFechaHora();
            String s = "insert into gdc.no_conformidad (nombre,fecha,hora,usuario,chk_real,chk_potencial,chk_Proveedor,chk_Proceso,chk_Producto,chk_Insumo,chk_Soft_Cargo,chk_Reclamo,txt_Identificacion,txt_Area,txt_Accion_Inmediata,txt_Analisis,txt_Accion_Correctiva,txt_notifica_a) values (";
            s = s + "'" + s4 + "',";
            s = s + "'" + vector.elementAt(0).toString() + "',";
            s = s + "'" + vector.elementAt(1).toString() + "',";
            s = s + "'" + s3 + "',";
            s = s + "'" + s5 + "',";
            s = s + "'" + s6 + "',";
            s = s + "'" + s7 + "',";
            s = s + "'" + s8 + "',";
            s = s + "'" + s9 + "',";
            s = s + "'" + s10 + "',";
            s = s + "'" + s11 + "',";
            s = s + "'" + s12 + "',";
            s = s + "'" + s13 + "',";
            s = s + "'" + s14 + "',";
            s = s + "'" + s15 + "',";
            s = s + "'" + s16 + "',";
            s = s + "'" + s17 + "',";
            s = s + "'" + s18 + "')";
            ADatos.connect();
            int i = ADatos.ModificaDB(s);
            String s2 = "<html><head></head><STYLE>";
            s2 = s2 + ".titulos { FONT-WEIGHT: bold; FONT-SIZE: 14px; VERTICAL-ALIGN: middle; COLOR: #953611; TEXT-INDENT: 10pt; FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif; HEIGHT: 25px; BACKGROUND-COLOR: #fae5b9; TEXT-DECORATION: none}";
            s2 = s2 + ".textogral {FONT-WEIGHT: normal; FONT-SIZE: 12px; COLOR: #333333; FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif; HEIGHT: 16px; TEXT-ALIGN: justify}";
            s2 = s2 + ".textoaviso {FONT-WEIGHT: normal; FONT-SIZE: 10px; COLOR: #333333; FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif; HEIGHT: 16px; TEXT-ALIGN: justify}</STYLE>";
            s2 = s2 + "<BODY bgcolor='white' leftmargin='0' topmargin='0'>";
            s2 = s2 + "<table border='0' align='center' width='95%'>";
            s2 = s2 + "<tr><td class='titulos' width='100%' colspan='2'>Informe de No Conformidades</td></tr>";
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
            	s2 += "<tr><td class='textdesttabla' width='100%' colspan='2'>";
            	s2 += "<table border='1' align='center' width='100%'>";        	
            	for (j=0;j<totalCol;j++){
            		if (h<rs.size()  ) {
            			String sChecked = "";
            			if ((s5.toString().compareTo("S")==0 && ((String)rs.elementAt(h)).equals("CHK_REAL"))
            					|| (s6.toString().compareTo("S")==0 && ((String)rs.elementAt(h)).equals("CHK_POTENCIAL")) 
            					|| (s7.toString().compareTo("S")==0 && ((String)rs.elementAt(h)).equals("CHK_PROVEEDOR")) 
            					|| (s8.toString().compareTo("S")==0 && ((String)rs.elementAt(h)).equals("CHK_PROCESO")) 
            					|| (s9.toString().compareTo("S")==0 && ((String)rs.elementAt(h)).equals("CHK_PRODUCTO")) 
            					|| (s10.toString().compareTo("S")==0 && ((String)rs.elementAt(h)).equals("CHK_INSUMO")) 
            					|| (s11.toString().compareTo("S")==0 && ((String)rs.elementAt(h)).equals("CHK_SOFT_CARGO")) 
            					|| (s12.toString().compareTo("S")==0 && ((String)rs.elementAt(h)).equals("CHK_RECLAMO")) )
            				sChecked = "Checked";            			
            			s2 += "  <tr><td colspan='" + (((h+2)>=rs.size())?Integer.toString((totalCol-j)):"1") + "' nowrap class='textdesttabla' width=''><INPUT type='checkbox' name='" + (String)rs.elementAt(h) + "' language='javascript' value='S' " + sChecked + ">" + (String)rs.elementAt(h + 1) + "</td></tr>";
            		}
            		h=h+2;
            	}
            	s2 += "</table>";
            	s2 += "</tr>";        	        		
            }            
            
            s2 = s2 + "<tr><td width='100%' colspan='2'>&nbsp;</td></tr>";
            s2 = s2 + "<tr><td width='100%' colspan='2'>";
            s2 = s2 + "<table border='0' align='center' width='100%'>";
            s2 = s2 + "<tr><td class='textogral' width='5%' valign='top'><B>I.</B></td><td class='textogral' width='95%' valign='top'><B>Identificaci\363n y Descripci\363n NC/Falla</B></td></tr>";
            s2 = s2 + "<tr><td class='textogral' width='5%' valign='top'>&#32</td><td class='textogral' width='95%' valign='top'>" + s13 + "</td><tr>";
            s2 = s2 + "<tr><td class='textogral' width='5%' valign='top'><B>II.</B></td><td class='textogral' valign='top'><B>\301rea a la que pertenece la NC/Falla</B></td></tr>";
            s = "select descripcion from gdc.procesos where sigla ='" + s14 + "'";
            i = ADatos.ConsultaDB(s);
            rs = new Vector();
            rs = ADatos.getResult();
            if(rs.size() > 0)
                s2 = s2 + "<tr><td class='textogral' width='5%' valign='top'>&#32</td><td class='textogral' valign='top'>" + (String)rs.elementAt(0) + "</td></tr>";
            else
                s2 = s2 + "<tr><td class='textogral' width='5%' valign='top'>&#32</td><td class='textogral' valign='top'>" + s14 + "</td></tr>";
            s2 = s2 + "<tr><td class='textogral' width='5%' valign='top'><B>III.</B></td><td class='textogral' valign='top'><B>Acci\363n Inmediata</B></td></tr>";
            s2 = s2 + "<tr><td class='textogral' width='5%' valign='top'>&#32</td><td class='textogral' valign='top'>" + s15 + "</td></tr>";
            s2 = s2 + "<tr><td class='textogral' width='5%' valign='top'><B>IV.</B></td><td class='textogral' valign='top'><B>An\341lisis de Causas(Origen)</B></td></tr>";
            s2 = s2 + "<tr><td class='textogral' width='5%' valign='top'>&#32</td><td class='textogral' valign='top'>" + s16 + "</td></tr>";
            s2 = s2 + "<tr><td class='textogral' width='5%' valign='top'><B>V.</B></td><td class='textogral' valign='top'><B>Acci\363n Correctiva</B></td></tr>";
            s2 = s2 + "<tr><td class='textogral' width='5%' valign='top'>&#32</td><td class='textogral' valign='top'>" + s17 + "</td></tr>";
            s2 = s2 + "</table>";
            s2 = s2 + "</td></tr>";
            s2 = s2 + "<TR><td class='textoaviso' width='100%' align='left' valign='top' colspan='2'><BR><BR>Importante: ";
            s2 = s2 + "<BR>Este mail es generado de manera autom\341tica, por favor no responda a este mensaje.</TD></TR>";
            s2 = s2 + "</table>";
            s2 = s2 + "</body>";
            s2 = s2 + "</html>";
            String s20 = "";
            StringTokenizer stringtokenizer = new StringTokenizer(s18, ",");
            do
            {
                if(!stringtokenizer.hasMoreTokens())
                    break;
                String s19 = stringtokenizer.nextToken();
                String s1 = "select mail from sgc.usuarios where login ='" + s19 + "'";
                j = ADatos.ConsultaDB(s1);
                rs = new Vector();
                rs = ADatos.getResult();
                if(rs.size() > 0)
                    if(s20.length() > 0)
                        s20 = s20 + "," + (String)rs.elementAt(0);
                    else
                        s20 = (String)rs.elementAt(0);
            } while(true);
            EnvMail.Envio(s20, "SERAPIS. No Conformidad", s2);
            printwriter.println("<script language='javascript'>");
            printwriter.println("window.open('nc.jsp','datos');");
            printwriter.println("</script>");
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
        String s1 = "";
        String s3 = "";
        s = "SELECT p.sigla,p.descripcion,p.lider, u.nombre from gdc.procesos p, sgc.usuarios u where p.lider = u.login";
        printwriter.println("<table border='1' align='center' width='95%'>");
        printwriter.println("<tr><td class='texttituloarea' width='100%' colspan='2'>Informe de No Conformidades</td></tr>");
        printwriter.println("<tr><td class='textdesttabla' width='35%'>Nombre</td>");
        printwriter.println("<td class='textdesttabla' width='65%'><input name='txtnombre' maxlength='40' style='WIDTH: 260px' value='' onkeypress='return ValidarCaracteres(9);'></td></tr>");
        printwriter.println("<tr><td class='textdesttabla' width='35%'>");
        printwriter.println("<table border='1' align='center' width='100%'>");
        printwriter.println("   <tr><td class='textdesttabla' width='100%'><INPUT type='checkbox' name='tipo1'>Real</td></tr>");
        printwriter.println("   <tr><td class='textdesttabla' width='100%'><INPUT type='checkbox' name='tipo2'>Potencial</td></tr>");
        printwriter.println("</table>");
        printwriter.println("</td>");
        printwriter.println("<td class='textdesttabla' width='65%'>");
        printwriter.println("<table border='1' align='center' width='100%'>");
        printwriter.println("   <tr>");
        printwriter.println("      <td class='textdesttabla' width='33%'><INPUT type='checkbox' name='tipo3'>Proveedor</td>");
        printwriter.println("      <td class='textdesttabla' width='33%'><INPUT type='checkbox' name='tipo4'>Proceso</td>");
        printwriter.println("      <td class='textdesttabla' width='33%'><INPUT type='checkbox' name='tipo5'>Producto</td>");
        printwriter.println("   <tr>");
        printwriter.println("   <tr>");
        printwriter.println("      <td class='textdesttabla' width='33%'><INPUT type='checkbox' name='tipo6'>Insumo</td>");
        printwriter.println("      <td class='textdesttabla' width='33%'><INPUT type='checkbox' name='tipo7'>Soft Cargo</td>");
        printwriter.println("      <td class='textdesttabla' width='33%'><INPUT type='checkbox' name='tipo8'>Reclamo</td>");
        printwriter.println("   <tr>");
        printwriter.println("</table>");
        printwriter.println("</td></tr>");
        printwriter.println("<tr><td class='texttitulotabla' width='100%' colspan='2'>I. Identificaci\363n y Descripci\363n NC/Falla</td></tr>");
        printwriter.println("<tr><td class='textdesttabla' width='100%' colspan='2'>");
        printwriter.println("<TEXTAREA rows='5' cols='90' name='txtI' onkeypress='return ValidarCaracteres(9);'></TEXTAREA>");
        printwriter.println("</td></tr>");
        printwriter.println("<tr><td class='texttitulotabla' width='100%' colspan='2'>II. \301rea a la que pertenece la NC/Falla</td></tr>");
        printwriter.println("<tr><td class='textdesttabla' width='100%' colspan='2'>");
        ADatos.connect();
        int i = ADatos.ConsultaDB(s);
        rs = ADatos.getResult();
        if(rs.size() > 0)
        {
            printwriter.println("<SELECT name='selArea'>");
            for(int j = 0; j < rs.size(); j += 4)
            {
                String s2 = (String)rs.elementAt(j);
                String s4 = (String)rs.elementAt(j + 1) + "-" + (String)rs.elementAt(j + 3);
                printwriter.println("<OPTION value='" + s2 + "'>" + s4 + "</OPTION>");
            }

            printwriter.println("</SELECT>");
        }
        printwriter.println("</td></tr>");
        printwriter.println("<tr><td class='texttitulotabla' width='100%' colspan='2'>III. Acci\363n Inmediata</td></tr>");
        printwriter.println("<tr><td class='textdesttabla' width='100%' colspan='2'>");
        printwriter.println("<TEXTAREA rows='5' cols='90' name='txtIII' onkeypress='return ValidarCaracteres(9);'></TEXTAREA>");
        printwriter.println("</td></tr>");
        printwriter.println("<tr><td class='texttitulotabla' width='100%' colspan='2'>IV. An\341lisis de Causas(Origen)</td></tr>");
        printwriter.println("<tr><td class='textdesttabla' width='100%' colspan='2'>");
        printwriter.println("<TEXTAREA rows='5' cols='90' name='txtIV' onkeypress='return ValidarCaracteres(9);'></TEXTAREA>");
        printwriter.println("</td></tr>");
        printwriter.println("<tr><td class='texttitulotabla' width='100%' colspan='2'>V. Acci\363n Correctiva</td></tr>");
        printwriter.println("<tr><td class='textdesttabla' width='100%' colspan='2'>");
        printwriter.println("<TEXTAREA rows='5' cols='90' name='txtV' onkeypress='return ValidarCaracteres(9);'></TEXTAREA>");
        printwriter.println("</td></tr>");
        printwriter.println("<tr><td class='texttitulotabla' width='100%' colspan='2'>Lista Distribuci\363n. Notifica a:</td></tr>");
        printwriter.println("<tr><td class='textdesttabla' width='100%' colspan='2'>");
        printwriter.println("<table border='0' width='100%' align='center'>");
        printwriter.println("<tr><td align='left' class='textgral'>");
        printwriter.println("  <table border='0' width='100%' align='left'>");
        printwriter.println("    <tr><td align='center' class='texttitulotabla' width='48%'>Usuarios</td><td class='texttitulotabla' width='4%'>&nbsp;</td><td align='center' class='texttitulotabla' width='48%'>Seleccionados</td></tr>");
        printwriter.println("    <tr><td align='center' class='textgral'>");
        printwriter.println("<select name='selusuarios' multiple size='15' style='WIDTH: 250px'>");
        s = "select login,nombre,mail FROM sgc.usuarios where estado='A' ORDER by nombre";
        i = ADatos.ConsultaDB(s);
        rs = ADatos.getResult();
        if(rs.size() > 0)
        {
            for(int k = 0; k < rs.size(); k += 3)
                printwriter.println("<option value='" + (String)rs.elementAt(k) + "'>" + (String)rs.elementAt(k + 1) + "</option>");

        }
        printwriter.println("</select>");
        printwriter.println("</td>");
        printwriter.println("<td align='center' valign='center'><input class='fondoinput' type='button' style='WIDTH: 30px' name='btnmas' value='>>' language='javascript' onclick='return Agregar();'><BR><BR><BR><input class='fondoinput' type='button' style='WIDTH: 30px' name='btnmenos' value='<<' language='javascript' onclick='return Quitar();'></td>");
        printwriter.println("        <td align='center' class='textgral'>");
        printwriter.println("<select name='sellista' multiple size='15' style='WIDTH: 250px'>");
        printwriter.println("</select>");
        printwriter.println("</td></tr>");
        printwriter.println("</table>");
        printwriter.println("</td></tr>");
        printwriter.println("</table>");
    }

    AccDataBase ADatos;
    funciones AFunc;
    correo EnvMail;
    Vector rs;
}