// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   nc_modifica.java

package gdp;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class nc_modifica extends HttpServlet
{

    public nc_modifica()
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
            printwriter.println("<BR><center><input type='button' name='BtnAceptar' value='Aceptar' class='fondoinput' language='javascript' onclick='return Valida();'>&nbsp;&nbsp;<input type='button' name='BtnImprimir' value='Imprimir' class='fondoinput' language='javascript' onclick='window.print()'></center></td>");
            printwriter.println("</form>");
            printwriter.println("<script language='JavaScript'>");
            printwriter.println("function Valida()");
            printwriter.println("{");
            printwriter.println("   document.frmnc.ListaDistrubucion.value = getLista(document.frmnc.sellista);");
            printwriter.println("   if(document.frmnc.txtnombre.value!=\"\")");
            printwriter.println("   {");
            printwriter.println("      if(document.frmnc.txtI.value!=\"\")");
            printwriter.println("      {");
            printwriter.println("         if(document.frmnc.txtIII.value!=\"\")");
            printwriter.println("         {");
            printwriter.println("            if(document.frmnc.txtIV.value!=\"\")");
            printwriter.println("            {");
            printwriter.println("               if(document.frmnc.txtV.value!=\"\")");
            printwriter.println("               {");
            printwriter.println("                  if(document.frmnc.ListaDistrubucion.value!=\"\")");
            printwriter.println("                  {");
            printwriter.println("                     document.frmnc.action=\"nc_procesamod.jsp\";");
            printwriter.println("                     document.frmnc.submit();");
            printwriter.println("                  }");
            printwriter.println("                  else");
            printwriter.println("                  {");
            printwriter.println("                     alert(\"Debe seleccionar la lista de distribuci\363n\");");
            printwriter.println("                  }");
            printwriter.println("                }");
            printwriter.println("                else");
            printwriter.println("                {");
            printwriter.println("                   alert(\"Debe ingresar Acci\363n Correctiva\");");
            printwriter.println("                   document.frmnc.txtV.focus();");
            printwriter.println("                }");
            printwriter.println("            }");
            printwriter.println("            else");
            printwriter.println("            {");
            printwriter.println("               alert(\"Debe ingresar An\341lisis de Causas(Origen)\");");
            printwriter.println("               document.frmnc.txtIV.focus();");
            printwriter.println("            }");
            printwriter.println("         }");
            printwriter.println("         else");
            printwriter.println("         {");
            printwriter.println("            alert(\"Debe ingresar Acci\363n Inmediata\");");
            printwriter.println("            document.frmnc.txtIII.focus();");
            printwriter.println("         }");
            printwriter.println("      }");
            printwriter.println("      else");
            printwriter.println("      {");
            printwriter.println("         alert(\"Debe ingresar Identificaci\363n y Descripci\363n NC/Falla\");");
            printwriter.println("         document.frmnc.txtI.focus();");
            printwriter.println("      }");
            printwriter.println("   }");
            printwriter.println("   else");
            printwriter.println("   {");
            printwriter.println("      alert(\"Debe ingresar nombre de la No Conformidad.\");");
            printwriter.println("         document.frmnc.txtnombre.focus();");
            printwriter.println("   }");
            printwriter.println("}");
            printwriter.println("function getLista(obj)");
            printwriter.println("{");
            printwriter.println("\tvar valor=\"\";");
            printwriter.println("\tif (!obj)");
            printwriter.println("\t\treturn \"\";");
            printwriter.println("\tif (!obj.length)");
            printwriter.println("\t\treturn \"\";");
            printwriter.println("\telse ");
            printwriter.println("   {");
            printwriter.println("\t\tfor (i=0;i<obj.length;i++)");
            printwriter.println("        {");
            printwriter.println("\t\t\ttexto= obj[i].text.split(\"-\")[1];");
            printwriter.println("\t\t\tvalor += obj[i].value + \",\";");
            printwriter.println("\t\t}");
            printwriter.println("\t}");
            printwriter.println("\treturn valor;");
            printwriter.println("}");
            printwriter.println("function pasarItems(objOrigen,objDestino)");
            printwriter.println("{");
            printwriter.println("\tfor (i=0;i < objOrigen.length;i++)");
            printwriter.println("    {");
            printwriter.println("\t\tif (objOrigen.options[i].selected)");
            printwriter.println("        {");
            printwriter.println("\t\t\t objDestino[objDestino.length] = new Option(objOrigen.options[i].text,objOrigen.options[i].value);");
            printwriter.println("\t\t\t objOrigen.options[i]=null;");
            printwriter.println("\t\t\t i=-1;");
            printwriter.println("\t\t}");
            printwriter.println("\t}");
            printwriter.println("}");
            printwriter.println("function Agregar()");
            printwriter.println("{");
            printwriter.println("   form = document.frmnc;");
            printwriter.println("   pasarItems(form.selusuarios,form.sellista)");
            printwriter.println("}");
            printwriter.println("function Quitar()");
            printwriter.println("{");
            printwriter.println("\tform = document.frmnc;");
            printwriter.println("\tpasarItems(form.sellista,form.selusuarios)");
            printwriter.println("}");
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
        String s5 = "";
        Integer integer = new Integer(0);
        Integer integer2 = new Integer(0);
        String s6 = "";
        String s8 = "";
        String s10 = "";
        String s11 = "";
        String s12 = "";
        String s13 = "";
        String s14 = "";
        String s15 = "";
        String s16 = "";
        String s17 = "";
        String s18 = "";
        String s19 = "";
        String s20 = "";
        String s21 = "";
        String s22 = "";
        String s23 = "";
        String s24 = "";
        String s25 = "";
        String s27 = "";
        ADatos.connect();
        s = "SELECT nc.nombre nombrenc, nc.fecha, nc.hora,nc.usuario, u.nombre nombreuser, ";
        s = s + " nc.chk_real,nc.chk_potencial,nc.chk_Proveedor,nc.chk_Proceso,nc.chk_Producto, ";
        s = s + " nc.chk_Insumo,nc.chk_Soft_Cargo,nc.chk_Reclamo,nc.txt_Identificacion,nc.txt_Area, ";
        s = s + " p.descripcion,nc.txt_Accion_Inmediata,nc.txt_Analisis,nc.txt_Accion_Correctiva,nc.txt_notifica_a ";
        s = s + " from gdc.no_conformidad nc, sgc.usuarios u, gdc.procesos p ";
        s = s + " where u.login = nc.usuario ";
        s = s + " and p.sigla = nc.txt_Area ";
        s = s + " and nc.id = " + IdNC;
        int i = ADatos.ConsultaDB(s);
        rs = ADatos.getResult();
        if(rs.size() > 0)
        {
            s5 = (String)rs.elementAt(0);
            Integer integer1 = (Integer)rs.elementAt(1);
            Integer integer3 = (Integer)rs.elementAt(2);
            String s7 = (String)rs.elementAt(3);
            String s9 = (String)rs.elementAt(4);
            s10 = (String)rs.elementAt(5);
            s11 = (String)rs.elementAt(6);
            s12 = (String)rs.elementAt(7);
            s13 = (String)rs.elementAt(8);
            s14 = (String)rs.elementAt(9);
            s15 = (String)rs.elementAt(10);
            s16 = (String)rs.elementAt(11);
            s17 = (String)rs.elementAt(12);
            s18 = (String)rs.elementAt(13);
            s19 = (String)rs.elementAt(14);
            s20 = (String)rs.elementAt(15);
            s21 = (String)rs.elementAt(16);
            s22 = (String)rs.elementAt(17);
            s23 = (String)rs.elementAt(18);
            s24 = (String)rs.elementAt(19);
        }
        printwriter.println("</td></tr>");
        printwriter.println("<input type='hidden' name='ID' value='" + IdNC + "'>");
        printwriter.println("<input type='hidden' name='ListaDistrubucion' value=''>");

        printwriter.println("<table border='1' align='center' width='95%'>");
        printwriter.println("<tr><td class='texttituloarea' width='100%' colspan='2'>No Conformidad Original</td></tr>");
        printwriter.println("<tr><td class='textdesttabla' width='35%'>Informado Por</td>");
        printwriter.println("<td class='textdesttabla' width='65%'>" + s5 + "</td></tr>");
        printwriter.println("<tr><td class='textdesttabla' width='35%'>Tipo</td>");
        printwriter.println("<td class='textdesttabla' width='65%'>");
        ADatos.connect();
        s= "select upper(nom_campo), descripcion from gdc.descripcion_noconformidad order by correlativo";
        int rc = ADatos.ConsultaDB(s);
        rs = ADatos.getResult();
                
        if(s10.compareTo("S") == 0 && rs.contains("CHK_REAL"))
            printwriter.println(rs.elementAt(rs.indexOf("CHK_REAL")+1).toString() + "<BR>");
        if(s11.compareTo("S") == 0 && rs.contains("CHK_POTENCIAL"))
        	printwriter.println(rs.elementAt(rs.indexOf("CHK_POTENCIAL")+1).toString() + "<BR>");
        if(s12.compareTo("S") == 0 && rs.contains("CHK_PROVEEDOR"))
        	printwriter.println(rs.elementAt(rs.indexOf("CHK_PROVEEDOR")+1).toString() + "<BR>");
        if(s13.compareTo("S") == 0 && rs.contains("CHK_PROCESO"))
        	printwriter.println(rs.elementAt(rs.indexOf("CHK_PROCESO")+1).toString() + "<BR>");
        if(s14.compareTo("S") == 0 && rs.contains("CHK_PRODUCTO"))
        	printwriter.println(rs.elementAt(rs.indexOf("CHK_PRODUCTO")+1).toString() + "<BR>");
        if(s15.compareTo("S") == 0 && rs.contains("CHK_INSUMO"))
        	printwriter.println(rs.elementAt(rs.indexOf("CHK_INSUMO")+1).toString() + "<BR>");
        if(s16.compareTo("S") == 0 && rs.contains("CHK_SOFT_CARGO"))
        	printwriter.println(rs.elementAt(rs.indexOf("CHK_SOFT_CARGO")+1).toString() + "<BR>");
        if(s17.compareTo("S") == 0 && rs.contains("CHK_RECLAMO"))
        	printwriter.println(rs.elementAt(rs.indexOf("CHK_RECLAMO")+1).toString() + "<BR>");
        printwriter.println("</td></tr>");
        printwriter.println("<tr><td class='textdesttabla' width='35%'>I. Identificaci\363n y Descripci\363n NC/Falla</td>");
        printwriter.println("<td class='textdesttabla' width='65%'>" + s18 + "</td></tr>");
        printwriter.println("<tr><td class='textdesttabla' width='35%'>II. \301rea a la que pertenece la NC/Falla</td>");
        printwriter.println("<td class='textdesttabla' width='65%'>" + s19 + " - " + s20 + "</td></tr>");
        printwriter.println("<tr><td class='textdesttabla' width='35%'>Notificado a:</td>");
        printwriter.println("<td class='textdesttabla' width='65%'>");
        StringTokenizer stringtokenizer = new StringTokenizer(s24, ",");
        do
        {
            if(!stringtokenizer.hasMoreTokens())
                break;
            String s26 = stringtokenizer.nextToken();
            s = "select nombre from sgc.usuarios where login ='" + s26 + "'";
            int l = ADatos.ConsultaDB(s);
            rs = new Vector();
            rs = ADatos.getResult();
            if(rs.size() > 0)
                printwriter.println((String)rs.elementAt(0) + "<BR>");
        } while(true);
        printwriter.println("</td>");
        printwriter.println("</table>");
        printwriter.println("<BR>");
        printwriter.println("<table border='1' align='center' width='95%'>");
        printwriter.println("<tr><td class='texttituloarea' width='100%' colspan='2'>Modificar No Conformidad</td></tr>");
        printwriter.println("<tr><td class='textdesttabla' width='35%'>Informado Por</td>");
        printwriter.println("<td class='textdesttabla' width='65%'><input name='txtnombre' maxlength='40' style='WIDTH: 260px' value='" + s5 + "' onkeypress='return ValidarCaracteres(9);'></td></tr>");

        s= "select upper(nom_campo), descripcion from gdc.descripcion_noconformidad order by correlativo";
        rc = ADatos.ConsultaDB(s);
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
        			if ((s10.toString().compareTo("S")==0 && ((String)rs.elementAt(h)).equals("CHK_REAL"))
        					|| (s11.toString().compareTo("S")==0 && ((String)rs.elementAt(h)).equals("CHK_POTENCIAL")) 
        					|| (s12.toString().compareTo("S")==0 && ((String)rs.elementAt(h)).equals("CHK_PROVEEDOR")) 
        					|| (s13.toString().compareTo("S")==0 && ((String)rs.elementAt(h)).equals("CHK_PROCESO")) 
        					|| (s14.toString().compareTo("S")==0 && ((String)rs.elementAt(h)).equals("CHK_PRODUCTO")) 
        					|| (s15.toString().compareTo("S")==0 && ((String)rs.elementAt(h)).equals("CHK_INSUMO")) 
        					|| (s16.toString().compareTo("S")==0 && ((String)rs.elementAt(h)).equals("CHK_SOFT_CARGO")) 
        					|| (s17.toString().compareTo("S")==0 && ((String)rs.elementAt(h)).equals("CHK_RECLAMO")) )
        				sChecked = "Checked";
        			
        			printwriter.println("   <td colspan='" + (((h+2)>=rs.size())?Integer.toString((totalCol-j)):"1") + "' nowrap class='textdesttabla' width=''><INPUT type='checkbox' name='" + (String)rs.elementAt(h) + "' language='javascript' value='S' " + sChecked + ">" + (String)rs.elementAt(h + 1) + "</td>");
        		}
        		h=h+2;
        	}
        	printwriter.println("</table>");
        	printwriter.println("</tr>");        	        		
        }         
        
        printwriter.println("<tr><td class='texttitulotabla' width='100%' colspan='2'>I. Identificaci\363n y Descripci\363n NC/Falla</td></tr>");
        printwriter.println("<tr><td class='textdesttabla' width='100%' colspan='2'>");
        printwriter.println("<TEXTAREA rows='5' cols='90' name='txtI' onkeypress='return ValidarCaracteres(9);'>" + s18 + "</TEXTAREA>");
        printwriter.println("</td></tr>");
        printwriter.println("<tr><td class='texttitulotabla' width='100%' colspan='2'>II. \301rea a la que pertenece la NC/Falla</td></tr>");
        printwriter.println("<tr><td class='textdesttabla' width='100%' colspan='2'>");
        ADatos.connect();
        rs.clear();
        rs = new Vector();
        s = "SELECT p.sigla,p.descripcion,p.lider, u.nombre from gdc.procesos p, sgc.usuarios u where p.lider = u.login";
        i = ADatos.ConsultaDB(s);
        rs = ADatos.getResult();
        if(rs.size() > 0)
        {
            printwriter.println("<SELECT name='selArea'>");
            for(j = 0; j < rs.size(); j += 4)
            {
                String s2 = (String)rs.elementAt(j);
                String s4 = (String)rs.elementAt(j + 1) + "-" + (String)rs.elementAt(j + 3);
                if(s19.compareTo(s2) == 0)
                    printwriter.println("<OPTION value='" + s2 + "' selected>" + s4 + "</OPTION>");
                else
                    printwriter.println("<OPTION value='" + s2 + "'>" + s4 + "</OPTION>");
            }

            printwriter.println("</SELECT>");
        }
        printwriter.println("</td></tr>");
        printwriter.println("<tr><td class='texttitulotabla' width='100%' colspan='2'>III. Acci\363n Inmediata</td></tr>");
        printwriter.println("<tr><td class='textdesttabla' width='100%' colspan='2'>");
        printwriter.println("<TEXTAREA rows='5' cols='90' name='txtIII' onkeypress='return ValidarCaracteres(9);'>" + s21 + "</TEXTAREA>");
        printwriter.println("</td></tr>");
        printwriter.println("<tr><td class='texttitulotabla' width='100%' colspan='2'>IV. An\341lisis de Causas(Origen)</td></tr>");
        printwriter.println("<tr><td class='textdesttabla' width='100%' colspan='2'>");
        printwriter.println("<TEXTAREA rows='5' cols='90' name='txtIV' onkeypress='return ValidarCaracteres(9);'>" + s22 + "</TEXTAREA>");
        printwriter.println("</td></tr>");
        printwriter.println("<tr><td class='texttitulotabla' width='100%' colspan='2'>V. Acci\363n Preventiva / Correctiva</td></tr>");
        printwriter.println("<tr><td class='textdesttabla' width='100%' colspan='2'>");
        printwriter.println("<TEXTAREA rows='5' cols='90' name='txtV' onkeypress='return ValidarCaracteres(9);'>" + s23 + "</TEXTAREA>");
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
    Vector rs;
    String IdNC;
}