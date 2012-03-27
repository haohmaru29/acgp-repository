package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class listadistribucion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public listadistribucion() {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        Tipo = "";
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
            String s1 = "select login from sgc.perfil where login='" + s + "' and administrador='S'";
            rs = new Vector();
        	rs2 = new Vector();
            ADatos.connect();
            int i = ADatos.ConsultaDB(s1);
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                printwriter.println("<html>");
                printwriter.println("<head>");
                printwriter.println("</head>");
                printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
                printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
                printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
                printwriter.println("<form name='frmlista' method='POST'>");
                AFunc.cargamenu(printwriter, 1);
                Tipo = httpservletrequest.getParameter("TIPO");
                if(Tipo == null || Tipo.length() == 0)
                    Tipo = "LISTA";
                if(Tipo.compareTo("LISTA") == 0)
                {
                    printwriter.println("<table border='0' width='80%' align='center'>");
                    printwriter.println("<tr><td align='center'  class='texttituloarea' colspan='2'>Listas de Distrubuci\363n</td></tr>");
                    printwriter.println("<tr><td align='center'>");
                    printwriter.println("<table border='1' width='90%' align='center'>");
                    printwriter.println("<tr>");
                    printwriter.println("<td width='65%' class='texttitulotabla'>Lista</td>");
                    printwriter.println("<td width='35%' class='texttitulotabla'>Estado</td>");
                    printwriter.println("</tr>");
                    CargaLista(printwriter);
                    printwriter.println("</table>");
                    printwriter.println("<br><br>");
                    printwriter.println("<center>");
                    printwriter.println("<input type='button' name='nuevo' value='Nueva' class='fondoinput' language='javascript' onclick='return AgregaLista();'>&nbsp;<input type='button' name='BtnImprimir' value='Imprimir' class='fondoinput' language='javascript' onclick='window.print()'></td>");
                    printwriter.println("</center>");
                }
                if(Tipo.compareTo("GRABANUEVO") == 0)
                {
                    //String s12 = httpservletrequest.getParameter("txtcodigo");
                    String sSQL_idlista = "SELECT MAX(idlista) FROM gdc.listadistribucion";
                    int jSQL_idlista = ADatos.ConsultaDB(sSQL_idlista);
                    int i_idlista = 0;
                    rs2 = ADatos.getResult();
                    
                    if(rs.size() > 0)
                    	i_idlista = Integer.parseInt((String)rs2.elementAt(0).toString());
                    
                    i_idlista=i_idlista + 1;
                    //String s12 = (String)i_idlista;
                    
                    String s15 = httpservletrequest.getParameter("txtnombre");
                    String s18 = httpservletrequest.getParameter("txtlista");
                    String s2 = "insert into gdc.listadistribucion (idlista,descripcion,estado) values (";
                    s2 = s2 + i_idlista + ",'";
                    s2 = s2 + s15 + "','A')";
                    int k2 = ADatos.ModificaDB(s2);
                    for(StringTokenizer stringtokenizer = new StringTokenizer(s18, ","); stringtokenizer.hasMoreTokens();)
                    {
                        String s3 = "insert into gdc.listadistribucion_contenido (idlista,usuario) values (";
                        s3 = s3 + i_idlista + ",'";
                        s3 = s3 + stringtokenizer.nextToken() + "')";
                        int l2 = ADatos.ModificaDB(s3);
                    }

                }
                if(Tipo.compareTo("ELIMINA") == 0)
                {
                    String s13 = httpservletrequest.getParameter("txtcodigo");
                    String s4 = "delete from gdc.listadistribucion_contenido where idlista = " + s13;
                    int i2 = ADatos.ModificaDB(s4);
                    if(i2 > 0)
                    {
                        String s5 = "delete from gdc.listadistribucion where idlista = " + s13;
                        int j2 = ADatos.ModificaDB(s5);
                    }
                }
                if(Tipo.compareTo("MODIFICA") == 0)
                {
                    String s14 = httpservletrequest.getParameter("txtcodigo");
                    String s16 = httpservletrequest.getParameter("txtnombre");
                    String s19 = httpservletrequest.getParameter("txtlista");
                    String s21 = httpservletrequest.getParameter("selEstado");
                    String s6 = "delete from gdc.listadistribucion_contenido where idlista = " + s14;
                    int i3 = ADatos.ModificaDB(s6);
                    if(i3 > 0)
                    {
                        String s7 = "update gdc.listadistribucion set ";
                        s7 = s7 + " descripcion = '" + s16 + "',";
                        s7 = s7 + " estado = '" + s21 + "'";
                        s7 = s7 + " where idlista = " + s14;
                        int j3 = ADatos.ModificaDB(s7);
                        for(StringTokenizer stringtokenizer1 = new StringTokenizer(s19, ","); stringtokenizer1.hasMoreTokens();)
                        {
                            String s8 = "insert into gdc.listadistribucion_contenido (idlista,usuario) values (";
                            s8 = s8 + s14 + ",'";
                            s8 = s8 + stringtokenizer1.nextToken() + "')";
                            int k3 = ADatos.ModificaDB(s8);
                        }

                    }
                }
                if(Tipo.compareTo("EDITAR") == 0)
                {
                    rs = new Vector();
                    Vector vector = new Vector();
                    String s20 = "";
                    String s22 = httpservletrequest.getParameter("ID");
                    String s9 = "SELECT DISTINCT u.login,u.nombre from gdc.listadistribucion_contenido ld, sgc.usuarios u ";
                    s9 = s9 + " where ld.usuario = u.login and ld.idlista = " + s22;
                    s9 = s9 + " order by u.nombre ";
                    int j = ADatos.ConsultaDB(s9);
                    rs = ADatos.getResult();
                    vector = (Vector)rs.clone();
                    for(int i1 = 0; i1 < vector.size(); i1 += 2)
                        if(s20.length() > 0)
                            s20 = s20 + ",'" + vector.elementAt(i1).toString() + "'";
                        else
                            s20 = s20 + "'" + vector.elementAt(i1).toString() + "'";

                    s9 = "select descripcion,estado from gdc.listadistribucion where idlista = " + s22;
                    j = ADatos.ConsultaDB(s9);
                    rs = ADatos.getResult();
                    if(rs.size() > 0)
                    {
                        printwriter.println("<input type='hidden' name='txtcodigo' value='" + s22 + "'>");
                        printwriter.println("<input type='hidden' name='txtlista' value=''>");
                        printwriter.println("<table border='0' width='95%' align='center'>");
                        printwriter.println("<tr><td align='center'  class='texttituloarea' colspan='2'>Lista de Distrubuci\363n</td></tr>");
                        printwriter.println("<tr><td align='left' class='textgral'>Nombre Lista</td>");
                        printwriter.println("    <td align='left' class='textgral'><input name='txtnombre' maxlength='60' style='WIDTH: 260px' value='" + ((String)rs.elementAt(0)).toString() + "' onkeypress='return ValidarCaracteres(9);'></td></tr>");
                        printwriter.println("<tr><td align='left' class='textgral'>Estado</td>");
                        printwriter.println("    <td align='left' class='textgral'>");
                        printwriter.println("<select name='selEstado'>");
                        if(((String)rs.elementAt(1)).toString().compareTo("A") == 0)
                        {
                            printwriter.println("   <option value='A' selected>Activa</option>");
                            printwriter.println("   <option value='I'>Ivactiva</option>");
                        } else
                        {
                            printwriter.println("   <option value='A'>Activa</option>");
                            printwriter.println("   <option value='I' selected>Ivactiva</option>");
                        }
                        printwriter.println("</select>");
                        printwriter.println("</td></tr>");
                        printwriter.println("<tr><td align='left' class='textgral'>Notifica a:</td>");
                        printwriter.println("<td>");
                        printwriter.println("  <table border='0' width='80%' align='center'>");
                        printwriter.println("    <tr><td align='left' class='texttitulotabla' width='48%'>Usuarios</td><td class='texttitulotabla' width='4%'>&nbsp;</td><td align='left' class='texttitulotabla' width='48%'>Seleccionados</td></tr>");
                        printwriter.println("    <tr><td align='left' class='textgral'>");
                        printwriter.println("<select name='selusuarios' multiple size='15' style='WIDTH: 250px'>");
                        String s10 = "select DISTINCT login,nombre FROM sgc.usuarios where estado='A' and login NOT IN (" + s20 + ") ORDER by nombre";
                        int k = ADatos.ConsultaDB(s10);
                        rs = ADatos.getResult();
                        if(rs.size() > 0)
                        {
                            for(int j1 = 0; j1 < rs.size(); j1 += 2)
                                printwriter.println("<option value='" + (String)rs.elementAt(j1) + "'>" + (String)rs.elementAt(j1 + 1) + "</option>");

                        }
                    }
                    printwriter.println("</select>");
                    printwriter.println("</td>");
                    printwriter.println("<td align='center' valign='center'><input class='fondoinput' type='button' style='WIDTH: 30px' name='btnmas' value='>>' language='javascript' onclick='return Agregar();'><BR><BR><BR><input class='fondoinput' type='button' style='WIDTH: 30px' name='btnmenos' value='<<' language='javascript' onclick='return Quitar();'></td>");
                    printwriter.println("        <td align='left' class='textgral'>");
                    printwriter.println("<select name='sellista' multiple size='15' style='WIDTH: 250px'>");
                    if(vector.size() > 0)
                    {
                        for(int k1 = 0; k1 < vector.size(); k1 += 2)
                            printwriter.println("<option value='" + (String)vector.elementAt(k1) + "'>" + (String)vector.elementAt(k1 + 1) + "</option>");

                    }
                    printwriter.println("</select>");
                    printwriter.println("</td></tr>");
                    printwriter.println("</table>");
                    printwriter.println("<br><br>");
                    printwriter.println("<center>");
                    printwriter.println("<input type='button' name='btnagregar' value='Modificar' class='fondoinput' language='javascript' onclick='return ModificaLista();'>&nbsp;<input type='button' name='btneliminar' value='Eliminar' class='fondoinput' language='javascript' onclick='return EliminaLista();'>&nbsp;<input type='button' name='btncancelar' value='Cancelar' class='fondoinput' language='javascript' onclick='window.open(\"listadistribucion.jsp?TIPO=LISTA\",\"datos\")'></td>");
                    printwriter.println("</center>");
                }
                if(Tipo.compareTo("NUEVO") == 0)
                {
                    rs = new Vector();
                    String s17 = httpservletrequest.getParameter("txtcodigo");
                    printwriter.println("<input type='hidden' name='txtcodigo' value='" + s17 + "'>");
                    printwriter.println("<input type='hidden' name='txtlista' value=''>");
                    printwriter.println("<table border='0' width='95%' align='center'>");
                    printwriter.println("<tr><td align='center'  class='texttituloarea' colspan='2'>Lista de Distrubuci\363n</td></tr>");
                    printwriter.println("<tr><td align='left' class='textgral'>Nombre Lista</td>");
                    printwriter.println("    <td align='left' class='textgral'><input name='txtnombre' maxlength='60' style='WIDTH: 260px' value='' onkeypress='return ValidarCaracteres(9);'></td></tr>");
                    printwriter.println("<tr><td align='left' class='textgral'>Notifica a:</td>");
                    printwriter.println("<td>");
                    printwriter.println("  <table border='0' width='80%' align='center'>");
                    printwriter.println("    <tr><td align='left' class='texttitulotabla' width='48%'>Usuarios</td><td class='texttitulotabla' width='4%'>&nbsp;</td><td align='left' class='texttitulotabla' width='48%'>Seleccionados</td></tr>");
                    printwriter.println("    <tr><td align='left' class='textgral'>");
                    printwriter.println("<select name='selusuarios' multiple size='15' style='WIDTH: 250px'>");
                    String s11 = "select DISTINCT login,nombre FROM sgc.usuarios where estado='A' ORDER by nombre";
                    int l = ADatos.ConsultaDB(s11);
                    rs = ADatos.getResult();
                    if(rs.size() > 0)
                    {
                        for(int l1 = 0; l1 < rs.size(); l1 += 2)
                            printwriter.println("<option value='" + (String)rs.elementAt(l1) + "'>" + (String)rs.elementAt(l1 + 1) + "</option>");

                    }
                    printwriter.println("</select>");
                    printwriter.println("</td>");
                    printwriter.println("<td align='center' valign='center'><input class='fondoinput' type='button' style='WIDTH: 30px' name='btnmas' value='>>' language='javascript' onclick='return Agregar();'><BR><BR><BR><input class='fondoinput' type='button' style='WIDTH: 30px' name='btnmenos' value='<<' language='javascript' onclick='return Quitar();'></td>");
                    printwriter.println("        <td align='left' class='textgral'>");
                    printwriter.println("<select name='sellista' multiple size='15' style='WIDTH: 250px'>");
                    printwriter.println("</select>");
                    printwriter.println("</td></tr>");
                    printwriter.println("</table>");
                    printwriter.println("<br><br>");
                    printwriter.println("<center>");
                    printwriter.println("<input type='button' name='btnagregar' value='Agregar' class='fondoinput' language='javascript' onclick='return NuevaLista();'>&nbsp;<input type='button' name='btncancelar' value='Cancelar' class='fondoinput' language='javascript' onclick='window.open(\"listadistribucion.jsp?TIPO=LISTA\",\"datos\")'></td>");
                    printwriter.println("</center>");
                }
                printwriter.println("</form>");
                CargaScript(printwriter);
                printwriter.println("</body>");
                printwriter.println("</html>");
            } else
            {
                AFunc.reindex(httpservletrequest, printwriter, 1, "CONF", 7);
            }
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "CONF", 6);
        }
    }

    void CargaScript(PrintWriter printwriter)
    {
        printwriter.println("<script language='javascript'>");
        if(Tipo.compareTo("ELIMINA") == 0 || Tipo.compareTo("GRABANUEVO") == 0 || Tipo.compareTo("MODIFICA") == 0)
            printwriter.println("window.open('listadistribucion.jsp?TIPO=LISTA','datos');");
        printwriter.println("function AgregaLista()");
        printwriter.println("{");
        printwriter.println("   document.frmlista.action = \"listadistribucion.jsp?TIPO=NUEVO\";");
        printwriter.println("   document.frmlista.submit();");
        printwriter.println("}");
        printwriter.println("function EliminaLista()");
        printwriter.println("{");
        printwriter.println("   if (confirm(\"\277Esta seguro que desea eliminar la lista?\") == true)");
        printwriter.println("   {");
        printwriter.println("      document.frmlista.action = \"listadistribucion.jsp?TIPO=ELIMINA\";");
        printwriter.println("      document.frmlista.submit();");
        printwriter.println("   }");
        printwriter.println("}");
        printwriter.println("function ModificaLista()");
        printwriter.println("{");
        printwriter.println("   var lista;");
        printwriter.println("   var nombre;");
        printwriter.println("   lista =  getLista(document.frmlista.sellista);");
        printwriter.println("   if(lista.length == 0)");
        printwriter.println("   {");
        printwriter.println("      alert(\"Debe seleccionar al menos un usuario a distrubuir\");");
        printwriter.println("   }");
        printwriter.println("   else");
        printwriter.println("   {");
        printwriter.println("      nombre = document.frmlista.txtnombre.value;");
        printwriter.println("      if(nombre.length == 0)");
        printwriter.println("      {");
        printwriter.println("         alert(\"Debe dar un nombre a la lista\");");
        printwriter.println("         document.frmlista.txtnombre.focus();");
        printwriter.println("      }");
        printwriter.println("      else");
        printwriter.println("      {");
        printwriter.println("         document.frmlista.txtlista.value = lista;");
        printwriter.println("         document.frmlista.action = \"listadistribucion.jsp?TIPO=MODIFICA\";");
        printwriter.println("         document.frmlista.submit();");
        printwriter.println("      }");
        printwriter.println("   }");
        printwriter.println("}");
        printwriter.println("function NuevaLista()");
        printwriter.println("{");
        printwriter.println("   var lista;");
        printwriter.println("   var nombre;");
        printwriter.println("   lista =  getLista(document.frmlista.sellista);");
        printwriter.println("   if(lista.length == 0)");
        printwriter.println("   {");
        printwriter.println("      alert(\"Debe seleccionar al menos un usuario a distrubuir\");");
        printwriter.println("   }");
        printwriter.println("   else");
        printwriter.println("   {");
        printwriter.println("      nombre = document.frmlista.txtnombre.value;");
        printwriter.println("      if(nombre.length == 0)");
        printwriter.println("      {");
        printwriter.println("         alert(\"Debe dar un nombre a la lista\");");
        printwriter.println("         document.frmlista.txtnombre.focus();");
        printwriter.println("      }");
        printwriter.println("      else");
        printwriter.println("      {");
        printwriter.println("         document.frmlista.txtlista.value = lista;");
        printwriter.println("         document.frmlista.action = \"listadistribucion.jsp?TIPO=GRABANUEVO\";");
        printwriter.println("         document.frmlista.submit();");
        printwriter.println("      }");
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
        printwriter.println("\t\t\t objDestino[objDestino.length] = new Option(Trim(objOrigen.options[i].text),objOrigen.options[i].value);");
        printwriter.println("\t\t\t objOrigen.options[i]=null;");
        printwriter.println("\t\t\t i=-1;");
        printwriter.println("\t\t}");
        printwriter.println("\t}");
        printwriter.println("}");
        printwriter.println("function Agregar()");
        printwriter.println("{");
        printwriter.println("   form = document.frmlista;");
        printwriter.println("   pasarItems(form.selusuarios,form.sellista)");
        printwriter.println("}");
        printwriter.println("function Quitar()");
        printwriter.println("{");
        printwriter.println("\tform = document.frmlista;");
        printwriter.println("\tpasarItems(form.sellista,form.selusuarios)");
        printwriter.println("}");
        printwriter.println("</script>");
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    void CargaLista(PrintWriter printwriter)
    {
        Integer integer = new Integer(0);
        String s = "";
        String s2 = "";
        rs = new Vector();
        String s4 = "select idlista,descripcion,estado from gdc.listadistribucion where estado <> 'E' order by descripcion";
        int i = ADatos.ConsultaDB(s4);
        rs = ADatos.getResult();
        if(rs.size() > 0)
        {
            for(int j = 0; j < rs.size(); j += 3)
            {
                Integer integer1 = (Integer)rs.elementAt(j);
                String s1 = (String)rs.elementAt(j + 1);
                String s3 = (String)rs.elementAt(j + 2);
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'><a href='listadistribucion.jsp?ID=" + integer1.toString() + "&TIPO=EDITAR'>" + s1 + "</a></td>");
                if(s3.compareTo("A") == 0)
                    printwriter.println("<td class='textdesttabla'>Activa</td>");
                else
                    printwriter.println("<td class='textdesttabla'>Inactiva</td>");
                printwriter.println("</td></tr>");
            }

            printwriter.println("<input type='hidden' name='txtcodigo' value='" + (rs.size() / 3 + 1) + "'>");
        } else
        {
            printwriter.println("<input type='hidden' name='txtcodigo' value='1'>");
            printwriter.println("<tr><td class='textdesttabla' colspan='3'>No existen Listas de distribuci\363n disponibles.</td></tr>");
        }
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    Vector rs2;
    String Tipo;
}