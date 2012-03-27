package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class llenacargos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public llenacargos() {
        AFunc = new funciones();
        ADatos = new AccDataBase();
        sql = "";
    }

    protected void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        UserGDC = (String)httpsession.getValue("SerapisUser");
        String s = "";
        String s1 = "";
        String s2 = "";
        String s3 = "";
        String s4 = "";
        String s5 = "";
        String s6 = "";
        String s7 = "";
        String s8 = "";
        String s9 = "";
        String s10 = "";
        if(UserGDC != null && UserGDC.length() > 0)
        {
            String s11 = httpservletrequest.getParameter("ID");
            String s12 = httpservletrequest.getParameter("TIPO");
            if(s12 == null || s12.length() == 0)
                s12 = "VER";
            if(s12.compareTo("VER") == 0)
            {
                sql = "select Descripcion,Departamento,Reporta,RangoSalarial,Objetivo,Edad,Idioma,OtrosCarGen,Viajes,Horario,OtrosReq from gdc.def_cargos where ID = " + s11;
                rs = new Vector();
                ADatos.connect();
                int i = ADatos.ConsultaDB(sql);
                rs = ADatos.getResult();
                if(rs.size() > 0)
                {
                    s = (String)rs.elementAt(0);
                    s1 = (String)rs.elementAt(1);
                    s2 = (String)rs.elementAt(2);
                    s3 = (String)rs.elementAt(3);
                    s4 = (String)rs.elementAt(4);
                    s5 = (String)rs.elementAt(5);
                    s6 = (String)rs.elementAt(6);
                    s7 = (String)rs.elementAt(7);
                    s8 = (String)rs.elementAt(8);
                    s9 = (String)rs.elementAt(9);
                    s10 = (String)rs.elementAt(10);
                }
            }
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("<title>SERAPIS. Sistema de Gesti\363n de Calidad.</title>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
            printwriter.println("<TITLE>SERAPIS. Sistema de Gesti\363n de Calidad</TITLE>");
            printwriter.println("<body leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<script type='text/javascript' language='JavaScript1.2' src='funciones.js'></script>");
            printwriter.println("<form name='cargo' method='POST' action=''>");
            printwriter.println("  <table cellpadding='0' cellspacing='0' border='1' width='80%' align='center'>");
            printwriter.println("    <tr>");
            printwriter.println("      <td colspan='2' class='texttitulotabla'><b>DEFINICI&Oacute;N DE CARGOS Y ROLES</b></td>");
            printwriter.println("    </tr>");
            printwriter.println("    <tr>");
            printwriter.println("      <td class='textdesttabla' width='300'><b>Nombre del Puesto</b></td>");
            printwriter.println("      <td class='textdesttabla'><input type='text' name='puesto' size='60' language='javascript' onkeypress='return ValidarCaracteres(9);' maxlength='60' value='" + s + "'></td>");
            printwriter.println("    </tr>");
            printwriter.println("    <tr>");
            printwriter.println("      <td class='textdesttabla' width='300'><b>Departamento</b></td>");
            printwriter.println("      <td class='textdesttabla'><input type='text' name='departamento' size='50' language='javascript' onkeypress='return ValidarCaracteres(9);' maxlength='50' value='" + s1 + "'></td>");
            printwriter.println("    </tr>");
            printwriter.println("    <tr>");
            printwriter.println("      <td class='textdesttabla' width='300'><b>Reporta a</b></td>");
            printwriter.println("      <td class='textdesttabla'><input type='text' name='reporta' size='50' maxlength='50' language='javascript' onkeypress='return ValidarCaracteres(9);' value='" + s2 + "'></td>");
            printwriter.println("    </tr>");
            printwriter.println("    <tr>");
            printwriter.println("      <td class='textdesttabla' width='300'><b>Rango Salarial</b></td>");
            printwriter.println("      <td class='textdesttabla'><input type='text' name='rango' size='50' maxlength='50' language='javascript' onkeypress='return ValidarCaracteres(8);' value='" + s3 + "'></td>");
            printwriter.println("    </tr>");
            printwriter.println("    <tr>");
            printwriter.println("      <td class='textdesttabla' width='300'><b>Objetivo General del Puesto</b></td>");
            printwriter.println("      <td class='textdesttabla'><input type='text' name='objetivo' size='70' language='javascript' onkeypress='return ValidarCaracteres(9);' maxlength='255' value='" + s4 + "'></td>");
            printwriter.println("    </tr>");
            printwriter.println("    <tr>");
            printwriter.println("      <td class='textdesttabla' width='300'><b>Funciones B&aacute;sicas</b></td>");
            printwriter.println("      <td class='textdesttabla'>");
            //printwriter.println("        <input type='text' name='funci' size='70' maxlength='255' language='javascript' onkeypress='agrega(event, document.cargo.funciones, document.cargo.funci)' onkeyup='agrega(document.cargo.funciones, document.cargo.funci)' class='campoancho'><br>");
            printwriter.println("        <input type='text' name='funci' size='70' maxlength='255' language='javascript' onkeypress='agrega(event, document.cargo.funciones, document.cargo.funci)' class='campoancho'><br>");
            printwriter.println("        <select multiple name='funciones' size='5' ondblclick='quita(document.cargo.funciones, document.cargo.funci)' class='campoancho'>");
            if(s12.compareTo("VER") == 0)
            {
                sql = "select Descripcion from gdc.descripciones_cargos where ID = " + s11 + " and ID_Tipo = 6 and ltrim(rtrim(Descripcion)) <> '' order by correlativo";
                rs = new Vector();
                ret = ADatos.ConsultaDB(sql);
                rs = ADatos.getResult();
                for(int j = 0; j < rs.size(); j++)
                    printwriter.println("<option value='" + (String)rs.elementAt(j) + "'>" + (String)rs.elementAt(j) + "</option>");

            }
            printwriter.println("</select>");
            printwriter.println("      </td>");
            printwriter.println("    </tr>");
            printwriter.println("    <tr>");
            printwriter.println("      <td colspan='2' class='texttitulotabla'><b>Conocimientos, destrezas, habilidades y requerimientos de selecci&oacute;n y capacitaci&oacute;n</b></td>");
            printwriter.println("    </tr>");
            printwriter.println("    <tr>");
            printwriter.println("      <td class='textdesttabla' width='300'><b>Conocimiento Formal</b></td>");
            printwriter.println("      <td class='textdesttabla'>");
            //printwriter.println("        <input type='text' name='conoc' size='70' maxlength='255' onkeypress='agrega(event, document.cargo.conocimiento, document.cargo.conoc)' onkeyup='agrega(document.cargo.funciones, document.cargo.funci)' class='campoancho'><br>");
            printwriter.println("        <input type='text' name='conoc' size='70' maxlength='255' onkeypress='agrega(event, document.cargo.conocimiento, document.cargo.conoc)' class='campoancho'><br>");
            printwriter.println("        <select multiple name='conocimiento' size='5' ondblclick='quita(document.cargo.conocimiento, document.cargo.conoc)' class='campoancho'>");
            if(s12.compareTo("VER") == 0)
            {
                sql = "select Descripcion from gdc.descripciones_cargos where ID = " + s11 + " and ID_Tipo = 7 and ltrim(rtrim(Descripcion)) <> '' order by correlativo";
                rs = new Vector();
                ret = ADatos.ConsultaDB(sql);
                rs = ADatos.getResult();
                for(int k = 0; k < rs.size(); k++)
                    printwriter.println("<option value='" + (String)rs.elementAt(k) + "'>" + (String)rs.elementAt(k) + "</option>");

            }
            printwriter.println("</select>");
            printwriter.println("      </td>");
            printwriter.println("    </tr>");
            printwriter.println("    <tr>");
            printwriter.println("      <td class='textdesttabla' width='300'><b>Destrezas Especificas</b></td>");
            printwriter.println("      <td class='textdesttabla'>");
            //printwriter.println("        <input type='text' name='destrez' size='70' maxlength='255' onkeypress='agrega(event, document.cargo.destrezas, document.cargo.destrez)' onkeyup='agrega(document.cargo.funciones, document.cargo.funci)' class='campoancho'><br>");
            printwriter.println("        <input type='text' name='destrez' size='70' maxlength='255' onkeypress='agrega(event, document.cargo.destrezas, document.cargo.destrez)' class='campoancho'><br>");
            printwriter.println("        <select multiple name='destrezas' size='5' ondblclick='quita(document.cargo.destrezas, document.cargo.destrez)' class='campoancho'>");
            if(s12.compareTo("VER") == 0)
            {
                sql = "select Descripcion from gdc.descripciones_cargos where ID = " + s11 + " and ID_Tipo = 8 and ltrim(rtrim(Descripcion)) <> '' order by correlativo";
                rs = new Vector();
                ret = ADatos.ConsultaDB(sql);
                rs = ADatos.getResult();
                for(int l = 0; l < rs.size(); l++)
                    printwriter.println("<option value='" + (String)rs.elementAt(l) + "'>" + (String)rs.elementAt(l) + "</option>");

            }
            printwriter.println("</select>");
            printwriter.println("      </td>");
            printwriter.println("    </tr>");
            printwriter.println("    <tr>");
            printwriter.println("      <td class='textdesttabla' width='300'><b>Habilidades</b></td>");
            printwriter.println("      <td class='textdesttabla'>");
            //printwriter.println("        <input type='text' name='habil' size='70' maxlength='255' onkeypress='agrega(event, document.cargo.habilidades, document.cargo.habil)' onkeyup='agrega(document.cargo.funciones, document.cargo.funci)' class='campoancho'><br>");
            printwriter.println("        <input type='text' name='habil' size='70' maxlength='255' onkeypress='agrega(event, document.cargo.habilidades, document.cargo.habil)' class='campoancho'><br>");
            printwriter.println("        <select multiple name='habilidades' size='5' ondblclick='quita(document.cargo.habilidades, document.cargo.habil)' class='campoancho'>");
            if(s12.compareTo("VER") == 0)
            {
                sql = "select Descripcion from gdc.descripciones_cargos where ID = " + s11 + " and ID_Tipo = 9 and ltrim(rtrim(Descripcion)) <> '' order by correlativo";
                rs = new Vector();
                ret = ADatos.ConsultaDB(sql);
                rs = ADatos.getResult();
                for(int i1 = 0; i1 < rs.size(); i1++)
                    printwriter.println("<option value='" + (String)rs.elementAt(i1) + "'>" + (String)rs.elementAt(i1) + "</option>");

            }
            printwriter.println("</select>");
            printwriter.println("      </td>");
            printwriter.println("    </tr>");
            printwriter.println("    <tr>");
            printwriter.println("      <td class='textdesttabla' width='300'><b>Requerimientos de Selecci&oacute;n</b></td>");
            printwriter.println("      <td class='textdesttabla'>");
            //printwriter.println("        <input type='text' name='reqsel' size='70' maxlength='255' onkeypress='agrega(event, document.cargo.reqseleccion, document.cargo.reqsel)' onkeyup='agrega(document.cargo.funciones, document.cargo.reqsel)' class='campoancho'><br>");
            printwriter.println("        <input type='text' name='reqsel' size='70' maxlength='255' onkeypress='agrega(event, document.cargo.reqseleccion, document.cargo.reqsel)' class='campoancho'><br>");
            printwriter.println("        <select multiple name='reqseleccion' size='5' ondblclick='quita(document.cargo.reqseleccion, document.cargo.reqsel)' class='campoancho'>");
            if(s12.compareTo("VER") == 0)
            {
                sql = "select Descripcion from gdc.descripciones_cargos where ID = " + s11 + " and ID_Tipo = 10 and ltrim(rtrim(Descripcion)) <> '' order by correlativo";
                rs = new Vector();
                ret = ADatos.ConsultaDB(sql);
                rs = ADatos.getResult();
                for(int j1 = 0; j1 < rs.size(); j1++)
                    printwriter.println("<option value='" + (String)rs.elementAt(j1) + "'>" + (String)rs.elementAt(j1) + "</option>");

            }
            printwriter.println("</select>");
            printwriter.println("      </td>");
            printwriter.println("    </tr>");
            printwriter.println("    <tr>");
            printwriter.println("      <td class='textdesttabla' width='300'><b>Requerimientos de Capacitaci&oacute;n</b></td>");
            printwriter.println("      <td class='textdesttabla'>");
            //printwriter.println("        <input type='text' name='reqcap' size='70' maxlength='255' onkeypress='agrega(event, document.cargo.reqcapacitacion, document.cargo.reqcap)' onkeyup='agrega(document.cargo.funciones, document.cargo.reqsel)' class='campoancho'><br>");
            printwriter.println("        <input type='text' name='reqcap' size='70' maxlength='255' onkeypress='agrega(event, document.cargo.reqcapacitacion, document.cargo.reqcap)' class='campoancho'><br>");
            printwriter.println("        <select multiple name='reqcapacitacion' size='5' ondblclick='quita(document.cargo.reqcapacitacion, document.cargo.reqcap)' class='campoancho'>");
            if(s12.compareTo("VER") == 0)
            {
                sql = "select Descripcion from gdc.descripciones_cargos where ID = " + s11 + " and ID_Tipo = 11 and ltrim(rtrim(Descripcion)) <> '' order by correlativo";
                rs = new Vector();
                ret = ADatos.ConsultaDB(sql);
                rs = ADatos.getResult();
                for(int k1 = 0; k1 < rs.size(); k1++)
                    printwriter.println("<option value='" + (String)rs.elementAt(k1) + "'>" + (String)rs.elementAt(k1) + "</option>");

            }
            printwriter.println("</select>");
            printwriter.println("      </td>");
            printwriter.println("    </tr>");
            printwriter.println("    <tr>");
            printwriter.println("      <td class='textdesttabla' width='300'><b>Experiencia</b></td>");
            printwriter.println("      <td class='textdesttabla'>");
            //printwriter.println("        <input type='text' name='exper' size='70' maxlength='255' onkeypress='agrega(event, document.cargo.experiencia, document.cargo.exper)' onkeyup='agrega(document.cargo.funciones, document.cargo.reqsel)' class='campoancho'><br>");
            printwriter.println("        <input type='text' name='exper' size='70' maxlength='255' onkeypress='agrega(event, document.cargo.experiencia, document.cargo.exper)' class='campoancho'><br>");
            printwriter.println("        <select multiple name='experiencia' size='5' ondblclick='quita(document.cargo.experiencia, document.cargo.exper)' class='campoancho'>");
            if(s12.compareTo("VER") == 0)
            {
                sql = "select Descripcion from gdc.descripciones_cargos where ID = " + s11 + " and ID_Tipo = 12 and ltrim(rtrim(Descripcion)) <> '' order by correlativo";
                rs = new Vector();
                ret = ADatos.ConsultaDB(sql);
                rs = ADatos.getResult();
                for(int l1 = 0; l1 < rs.size(); l1++)
                    printwriter.println("<option value='" + (String)rs.elementAt(l1) + "'>" + (String)rs.elementAt(l1) + "</option>");

            }
            printwriter.println("</select>");
            printwriter.println("      </td>");
            printwriter.println("    </tr>");
            printwriter.println("    <tr>");
            printwriter.println("      <td colspan='2' class='texttitulotabla'><b>Caracter&iacute;sticas Generales</b></td>");
            printwriter.println("    </tr>");
            printwriter.println("    <tr>");
            printwriter.println("      <td class='textdesttabla' width='300'><b>Edad</b></td>");
            printwriter.println("      <td class='textdesttabla'><input type='text' name='edad' size='30' language='javascript' onkeypress='return ValidarCaracteres(1);' maxlength='30' value = '" + s5 + "'></td>");
            printwriter.println("    </tr>");
            printwriter.println("    <tr>");
            printwriter.println("      <td class='textdesttabla' width='300'><b>Idioma</b></td>");
            printwriter.println("      <td class='textdesttabla'><input type='text' name='idioma' size='60' maxlength='60' language='javascript' onkeypress='return ValidarCaracteres(3);' value = '" + s6 + "'></td>");
            printwriter.println("    </tr>");
            printwriter.println("    <tr>");
            printwriter.println("      <td class='textdesttabla' width='300'><b>Otros</b></td>");
            printwriter.println("      <td class='textdesttabla'><textarea name='otros1' class='campoancho' language='javascript' onkeypress='return ValidarCaracteres(9);'>" + s7 + "</textarea></td>");
            printwriter.println("    </tr>");
            printwriter.println("    <tr>");
            printwriter.println("      <td colspan='2' class='texttitulotabla'><b>Requisitos Especiales</b></td>");
            printwriter.println("    </tr>");
            printwriter.println("    <tr>");
            printwriter.println("      <td class='textdesttabla' width='300'><b>Viajes</b></td>");
            printwriter.println("      <td class='textdesttabla'><input type='text' name='viajes' size='50' maxlength='50' language='javascript' onkeypress='return ValidarCaracteres(9);' value = '" + s8 + "'></td>");
            printwriter.println("    </tr>");
            printwriter.println("    <tr>");
            printwriter.println("      <td class='textdesttabla' width='300'><b>Horario</b></td>");
            printwriter.println("      <td class='textdesttabla'><input type='text' name='horario' size='50' maxlength='50' language='javascript' onkeypress='return ValidarCaracteres(9);' value = '" + s9 + "'></td>");
            printwriter.println("    </tr>");
            printwriter.println("    <tr>");
            printwriter.println("      <td class='textdesttabla' width='300'><b>Otros</b></td>");
            printwriter.println("      <td class='textdesttabla'><textarea name='otros2' class='campoancho' language='javascript' onkeypress='return ValidarCaracteres(9);'>" + s10 + "</textarea></td>");
            printwriter.println("    </tr>");
            printwriter.println("  </table>");
            printwriter.println("  <br>");
            printwriter.println("<center>");
            printwriter.println("<input type='button' class='fondoinput' name='ok' value='Aceptar' language='javascript' onclick='return valida(document.cargo);'>");
            if(s12.compareTo("VER") == 0)
                printwriter.println("<input type='button' class='fondoinput' name='elim' value='Eliminar' language='javascript' onclick='return eliminacargo(document.cargo);'>");
            printwriter.println("</center>");
            printwriter.println("<script languaje='javascript'>");
            printwriter.println("function valida(form)");
            printwriter.println("{");
            printwriter.println("  if (trim(form.puesto.value) != ''){");
            printwriter.println("    if (trim(form.departamento.value) != ''){");
            printwriter.println("      if (trim(form.reporta.value) != ''){");
            printwriter.println("        if (trim(form.objetivo.value) != ''){");
            printwriter.println("          if (form.funciones.length > 0){");
            printwriter.println("            if (form.conocimiento.length > 0){");
            printwriter.println("              if (form.destrezas.length > 0){");
            printwriter.println("                if (form.habilidades.length > 0){");
            printwriter.println("                  if (form.reqseleccion.length > 0){");
            printwriter.println("                    for (i = 0; i < form.funciones.length; i++)");
            printwriter.println("                      form.funciones.options[i].selected = true;");
            printwriter.println("                    for (i = 0; i < form.conocimiento.length; i++)");
            printwriter.println("                      form.conocimiento.options[i].selected = true;");
            printwriter.println("                    for (i = 0; i < form.destrezas.length; i++)");
            printwriter.println("                      form.destrezas.options[i].selected = true;");
            printwriter.println("                    for (i = 0; i < form.habilidades.length; i++)");
            printwriter.println("                      form.habilidades.options[i].selected = true;");
            printwriter.println("                    for (i = 0; i < form.reqseleccion.length; i++)");
            printwriter.println("                      form.reqseleccion.options[i].selected = true;");
            printwriter.println("                    for (i = 0; i < form.reqcapacitacion.length; i++)");
            printwriter.println("                      form.reqcapacitacion.options[i].selected = true;");
            printwriter.println("                    for (i = 0; i < form.experiencia.length; i++)");
            printwriter.println("                      form.experiencia.options[i].selected = true;");
            if(s12.compareTo("VER") == 0)
                printwriter.println("                    form.action=\"grabacargos.jsp?TIPO=UPD&ID=" + s11 + "\";");
            else
                printwriter.println("                    form.action=\"grabacargos.jsp?TIPO=ADD\";");
            printwriter.println("                    form.submit();");
            printwriter.println("                  }");
            printwriter.println("                  else{");
            printwriter.println("                    alert('Debe agregar al menos un Requerimiento de Selecci\363n');");
            printwriter.println("                    form.reqsel.focus();");
            printwriter.println("                  }");
            printwriter.println("                }");
            printwriter.println("                else{");
            printwriter.println("                  alert('Debe agregar al menos una Habilidad');");
            printwriter.println("                  form.habil.focus();");
            printwriter.println("                }");
            printwriter.println("              }");
            printwriter.println("              else{");
            printwriter.println("                alert('Debe agregar al menos una Destreza Especifica');");
            printwriter.println("                form.destrez.focus();");
            printwriter.println("              }");
            printwriter.println("            }");
            printwriter.println("            else{");
            printwriter.println("              alert('Debe agregar al menos un Conocimiento Formal');");
            printwriter.println("              form.conoc.focus();");
            printwriter.println("            }");
            printwriter.println("          }");
            printwriter.println("          else{");
            printwriter.println("            alert('Debe agregar al menos una Funci\363n B\341sica');");
            printwriter.println("            form.funci.focus();");
            printwriter.println("          }");
            printwriter.println("        }");
            printwriter.println("        else{");
            printwriter.println("          alert('Objetivo no puede estar vacio');");
            printwriter.println("          form.objetivo.focus();");
            printwriter.println("        }");
            printwriter.println("      }");
            printwriter.println("      else{");
            printwriter.println("        alert('Reporta a no puede estar vacio');");
            printwriter.println("        form.reporta.focus();");
            printwriter.println("      }");
            printwriter.println("    }");
            printwriter.println("    else{");
            printwriter.println("      alert('Departamento no puede estar vacio');");
            printwriter.println("      form.departamento.focus();");
            printwriter.println("    }");
            printwriter.println("  }");
            printwriter.println("  else{");
            printwriter.println("    alert('Nombre del Puesto no puede estar vacio');");
            printwriter.println("    form.puesto.focus();");
            printwriter.println("  }");
            printwriter.println("}");
            printwriter.println("function eliminacargo(form)");
            printwriter.println("{");
            printwriter.println(" form.action=\"grabacargos.jsp?TIPO=DEL&ID=" + s11 + "\";");
            printwriter.println(" form.submit();");
            printwriter.println(" return 0;");
            printwriter.println("}");
            printwriter.println("</script>");
            printwriter.println("</body>");
            printwriter.println("</html>");
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "CONF", 6);
        }
    }

    funciones AFunc;
    AccDataBase ADatos;
    String sql;
    Vector rs;
    int ret;
    String UserGDC;
}