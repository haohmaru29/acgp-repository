// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   nc_nueva.java

package gdp;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class nc_nueva extends HttpServlet
{

    public nc_nueva()
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
            printwriter.println("                  if(document.frmnc.ListaDistrubucion.value!=\"\")");
            printwriter.println("                  {");
            printwriter.println("                     document.frmnc.action=\"nc_procesa.jsp\";");
            printwriter.println("                     document.frmnc.submit();");
            printwriter.println("                  }");
            printwriter.println("                  else");
            printwriter.println("                  {");
            printwriter.println("                     alert(\"Debe seleccionar la lista de distribuci\363n\");");
            printwriter.println("                  }");
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
        
        printwriter.println("<input type='hidden' name='ListaDistrubucion' value=''>");
       
        printwriter.println("<table border='1' align='center' width='95%'>");
        printwriter.println("<tr><td class='texttituloarea' width='100%' colspan='2'>Informe de No Conformidades</td></tr>");
        printwriter.println("<tr><td class='textdesttabla' width='35%'>Informado Por:</td>");
        printwriter.println("<td class='textdesttabla' width='65%'><input name='txtnombre' maxlength='40' style='WIDTH: 260px' value='' onkeypress='return ValidarCaracteres(9);'></td></tr>");
        
        ADatos.connect();
        s= "select upper(nom_campo), descripcion from gdc.descripcion_noconformidad order by correlativo";
        int rc = ADatos.ConsultaDB(s);
        rs = ADatos.getResult();
        int i=0;
        int totalCol = 4;
        int totalreg = rs.size() / 2;
        int totalFil = totalreg / totalCol + 1;
        int j=0, h=0, m=0;
        for (i=0;i<totalFil;i++){
        	m=0;
        	printwriter.println("<tr><td class='textdesttabla' width='100%' colspan='2'>");
        	printwriter.println("<table border='1' align='center' width='100%'>");        	
        	for (j=0;j<totalCol;j++){
        		if (h<rs.size()  )
        			printwriter.println("   <td colspan='" + (((h+2)>=rs.size())?Integer.toString((totalCol-j)):"1") + "' nowrap class='textdesttabla' width=''><INPUT type='checkbox' name='" + (String)rs.elementAt(h) + "' language='javascript' value='S'>" + (String)rs.elementAt(h + 1) + "</td>");
        		h=h+2;
        	}
        	printwriter.println("</table>");
        	printwriter.println("</tr>");        	        		
        }
        
        printwriter.println("<tr><td class='texttitulotabla' width='100%' colspan='2'>I. Identificaci\363n y Descripci\363n NC/Falla</td></tr>");
        printwriter.println("<tr><td class='textdesttabla' width='100%' colspan='2'>");
        printwriter.println("<TEXTAREA rows='5' cols='90' name='txtI' onkeypress='return ValidarCaracteres(9);'></TEXTAREA>");
        printwriter.println("</td></tr>");
        printwriter.println("<tr><td class='texttitulotabla' width='100%' colspan='2'>II. \301rea a la que pertenece la NC/Falla - Responsable</td></tr>");
        printwriter.println("<tr><td class='textdesttabla' width='100%' colspan='2'>");
        
        s = "SELECT p.sigla,p.descripcion,p.lider, u.nombre from gdc.procesos p, sgc.usuarios u where p.lider = u.login order by p.descripcion";
        ADatos.connect();
        i = ADatos.ConsultaDB(s);
        rs = ADatos.getResult();
        if(rs.size() > 0)
        {
            printwriter.println("<SELECT name='selArea'>");
            for(j = 0; j < rs.size(); j += 4)
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
        printwriter.println("<tr><td class='texttitulotabla' width='100%' colspan='2'>V. Acci\363n Preventiva / Correctiva</td></tr>");
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
        ADatos.connect();
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
}