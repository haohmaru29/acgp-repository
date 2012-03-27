package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class perfiles extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public perfiles() {
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
            String s1 = "select login from sgc.perfil where login='" + s + "' and administrador='S'";
            rs = new Vector();
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
                AFunc.cargamenu(printwriter, 1);
                String s3 = httpservletrequest.getParameter("TIPO");
                if(s3 == null || s3.length() == 0)
                    s3 = "LISTA";
                if(s3.compareTo("NUEVO") == 0)
                {
                    printwriter.println("<form name='frmperfiles' method='POST' action='perfiles.jsp?TIPO=GRABAR'>");
                    printwriter.println("<table border='0' width='80%' align='center'>");
                    printwriter.println("<tr><td align='center'  class='texttituloarea'>Nuevo Perfiles de Usuarios</td></tr>");
                    printwriter.println("<tr><td align='center'>");
                    printwriter.println("<table border='1' width='90%' align='center'>");
                    printwriter.println("<tr>");
                    printwriter.println("<td width='15%' class='texttitulotabla'>Perfil</td>");
                    printwriter.println("<td class='textdesttabla'><INPUT type='text' name='txtperfil' value='' maxlength='30' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(9);'></td>");
                    printwriter.println("</tr>");
                    printwriter.println("</table>");
                    printwriter.println("<br><br>");
                    printwriter.println("<center>");
                    printwriter.println("<input type='submit' name='btnGrabar' value='Grabar' class='fondoinput' language='javascript'>&nbsp;&nbsp;<input type='button' name='btnBorrar' value='Eliminar' class='fondoinput' language='javascript' ></td>");
                    printwriter.println("</center>");
                    printwriter.println("</form>");
                }
                if(s3.compareTo("UPDATE") == 0)
                {
                    String s4 = httpservletrequest.getParameter("txtperfil");
                    String s8 = httpservletrequest.getParameter("idperfil");
                    String s10 = httpservletrequest.getParameter("estadoperfil");
                    if(s4 != null && s4.length() > 0)
                    {
                        UpdatePerfil(printwriter, s8, s4, s10);
                        printwriter.println("<table border='0' width='80%' align='center'>");
                        printwriter.println("<tr><td align='center'  class='texttituloarea'>Perfiles de Usuarios</td></tr>");
                        printwriter.println("<tr><td align='center'>");
                        printwriter.println("<table border='1' width='90%' align='center'>");
                        printwriter.println("<tr>");
                        printwriter.println("<td width='15%' class='texttitulotabla'>Id. Perfil</td>");
                        printwriter.println("<td width='65%' class='texttitulotabla'>Perfil</td>");
                        printwriter.println("<td width='20%' class='texttitulotabla'>Estado</td>");
                        printwriter.println("</tr>");
                        CargaPerfiles(printwriter);
                        printwriter.println("</table>");
                        printwriter.println("<br><br>");
                        printwriter.println("<center>");
                        printwriter.println("<input type='button' name='nuevo' value='Nuevo' class='fondoinput' language='javascript' onclick='window.open(\"perfiles.jsp?TIPO=NUEVO\",\"datos\")'>&nbsp;<input type='button' name='BtnImprimir' value='Imprimir' class='fondoinput' language='javascript' onclick='window.print()'></td>");
                        printwriter.println("</center>");
                    }
                }
                if(s3.compareTo("EDITAR") == 0)
                {
                    String s5 = httpservletrequest.getParameter("IDPERFIL");
                    String s2 = "select perfil,estado from sgc.perfiles where idperfil=" + s5;
                    int j = ADatos.ConsultaDB(s2);
                    if(j > 0)
                    {
                        printwriter.println("<form name='frmperfiles' method='POST' action='perfiles.jsp?TIPO=UPDATE'>");
                        printwriter.println("<table border='0' width='80%' align='center'>");
                        printwriter.println("<tr><td align='center'  class='texttituloarea'>Editar Perfil de Usuario</td></tr>");
                        printwriter.println("<tr><td align='center'>");
                        printwriter.println("<INPUT type='hidden' name='idperfil' value='" + s5 + "' >");
                        printwriter.println("<table border='1' width='90%' align='center'>");
                        printwriter.println("<tr>");
                        printwriter.println("<td width='15%' class='texttitulotabla'>Perfil</td>");
                        printwriter.println("<td class='textdesttabla'><INPUT type='text' name='txtperfil' value='" + (String)rs.elementAt(0) + "' maxlength='30' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(9);'></td>");
                        printwriter.println("</tr>");
                        printwriter.println("<tr>");
                        printwriter.println("<td width='15%' class='texttitulotabla'>Estado</td>");
                        printwriter.println("<td class='textdesttabla'>");
                        printwriter.println("<select name='estadoperfil'>");
                        String s9 = (String)rs.elementAt(1);
                        if(s9.compareTo("A") == 0)
                        {
                            printwriter.println("<option value='A' selected>Activo</option>");
                            printwriter.println("<option value='I'>Inactivo</option>");
                        } else
                        {
                            printwriter.println("<option value='A'>Activo</option>");
                            printwriter.println("<option value='I' selected>Inactivo</option>");
                        }
                        printwriter.println("</select>");
                        printwriter.println("</td>");
                        printwriter.println("</tr>");
                        printwriter.println("</table>");
                        printwriter.println("<br><br>");
                        printwriter.println("<center>");
                        printwriter.println("<input type='submit' name='btnGrabar' value='Grabar' class='fondoinput' language='javascript'>");
                        printwriter.println("<input type='button' name='btnEliminar' value='Eliminar' class='fondoinput' language='javascript' onclick='window.open(\"perfiles.jsp?TIPO=ELIMINAR&idperfil=" + s5 + "\",\"datos\")'></td>");
                        printwriter.println("</center>");
                        printwriter.println("</form>");
                    } else
                    {
                        printwriter.println("<table border='0' width='80%' align='center'>");
                        printwriter.println("<tr><td align='center'  class='texttituloarea'>Editar Perfil de Usuario</td></tr>");
                        printwriter.println("<tr><td align='center'>Perfil <" + s5 + "> no encontrado</td></tr>");
                        printwriter.println("</table>");
                        printwriter.println("<br><br><br><br>");
                        printwriter.println("<center>");
                        printwriter.println("<input type='button' name='btnvolver' value='Aceptar' class='fondoinput' language='javascript' onclick='window.open(\"perfiles.jsp?TIPO=LISTA\",\"datos\")'></td>");
                        printwriter.println("</center>");
                    }
                }
                if(s3.compareTo("GRABAR") == 0)
                {
                    String s6 = httpservletrequest.getParameter("txtperfil");
                    if(s6 != null && s6.length() > 0)
                    {
                        GrabaPerfil(printwriter, s6);
                        printwriter.println("<table border='0' width='80%' align='center'>");
                        printwriter.println("<tr><td align='center'  class='texttituloarea'>Perfiles de Usuarios</td></tr>");
                        printwriter.println("<tr><td align='center'>");
                        printwriter.println("<table border='1' width='90%' align='center'>");
                        printwriter.println("<tr>");
                        printwriter.println("<td width='15%' class='texttitulotabla'>Id. Perfil</td>");
                        printwriter.println("<td width='65%' class='texttitulotabla'>Perfil</td>");
                        printwriter.println("<td width='20%' class='texttitulotabla'>Estado</td>");
                        printwriter.println("</tr>");
                        CargaPerfiles(printwriter);
                        printwriter.println("</table>");
                        printwriter.println("<br><br>");
                        printwriter.println("<center>");
                        printwriter.println("<input type='button' name='nuevo' value='Nuevo' class='fondoinput' language='javascript' onclick='window.open(\"perfiles.jsp?TIPO=NUEVO\",\"datos\")'>&nbsp;<input type='button' name='BtnImprimir' value='Imprimir' class='fondoinput' language='javascript' onclick='window.print()'></td>");
                        printwriter.println("</center>");
                    }
                }
                if(s3.compareTo("ELIMINAR") == 0)
                {
                    String s7 = httpservletrequest.getParameter("idperfil");
                    String s11 = "";
                    if(s7 != null && s7.length() > 0)
                    {
                        int k = EliminaPerfil(printwriter, s7);
                        switch(k)
                        {
                        case 0: // '\0'
                            s11 = "El Prefil fue eliminado exitosamente";
                            break;

                        case -1:
                            s11 = "No es posible eliminar el perfil seleccionado";
                            break;

                        case -2:
                            s11 = "No es posible eliminar el perfil seleccionado ya que esta asociado al menos a un usuario.";
                            break;

                        case -3:
                            s11 = "No es posible eliminar el perfil seleccionado ya que esta asociado al menos a un proceso.";
                            break;

                        case -4:
                            s11 = "No es posible eliminar el perfil seleccionado ya que esta asociado al menos a un proyecto.";
                            break;
                        }
                        printwriter.println("<table border='0' width='80%' align='center'>");
                        printwriter.println("<tr><td align='center' class='texttituloarea'>Estado</td></tr>");
                        printwriter.println("<tr><td align='center' class='textgral'>&nbsp;</td></tr>");
                        printwriter.println("<tr><td align='center' class='textgral'>" + s11 + "</td></tr>");
                        printwriter.println("<tr><td align='center' class='textgral'>&nbsp;</td></tr>");
                        printwriter.println("<tr><td align='center' class='textgral'>");
                        printwriter.println("<input type='button' name='nuevo' value='Aceptar' class='fondoinput' language='javascript' onclick='window.open(\"perfiles.jsp?TIPO=LISTA\",\"datos\")'></td>");
                        printwriter.println("</td></tr></table>");
                    } else
                    {
                        printwriter.println("<table border='0' width='80%' align='center'>");
                        printwriter.println("<tr><td align='center' class='texttituloarea'>Estado</td></tr>");
                        printwriter.println("<tr><td align='center' class='textgral'>&nbsp;</td></tr>");
                        printwriter.println("<tr><td align='center' class='textgral'>El perfil no existe</td></tr>");
                        printwriter.println("<tr><td align='center' class='textgral'>&nbsp;</td></tr>");
                        printwriter.println("<tr><td align='center' class='textgral'>");
                        printwriter.println("<input type='button' name='nuevo' value='Aceptar' class='fondoinput' language='javascript' onclick='window.open(\"perfiles.jsp?TIPO=LISTA\",\"datos\")'></td>");
                        printwriter.println("</td></tr></table>");
                    }
                }
                if(s3.compareTo("LISTA") == 0)
                {
                    printwriter.println("<table border='0' width='80%' align='center'>");
                    printwriter.println("<tr><td align='center'  class='texttituloarea'>Perfiles de Usuarios</td></tr>");
                    printwriter.println("<tr><td align='center'>");
                    printwriter.println("<table border='1' width='90%' align='center'>");
                    printwriter.println("<tr>");
                    printwriter.println("<td width='15%' class='texttitulotabla'>Id. Perfil</td>");
                    printwriter.println("<td width='65%' class='texttitulotabla'>Perfil</td>");
                    printwriter.println("<td width='20%' class='texttitulotabla'>Estado</td>");
                    printwriter.println("</tr>");
                    CargaPerfiles(printwriter);
                    printwriter.println("</table>");
                    printwriter.println("<br><br>");
                    printwriter.println("<center>");
                    printwriter.println("<input type='button' name='nuevo' value='Nuevo' class='fondoinput' language='javascript' onclick='window.open(\"perfiles.jsp?TIPO=NUEVO\",\"datos\")'>&nbsp;<input type='button' name='BtnImprimir' value='Imprimir' class='fondoinput' language='javascript' onclick='window.print()'></td>");
                    printwriter.println("</center>");
                }
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

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    void CargaPerfiles(PrintWriter printwriter)
    {
        rs = new Vector();
        int i = ADatos.ConsultaDB("select idperfil,perfil,estado from sgc.perfiles order by idperfil");
        rs = ADatos.getResult();
        Integer integer = new Integer(0);
        for(int j = 0; j < rs.size(); j += 3)
        {
            printwriter.println("<tr>");
            Integer integer1 = new Integer(rs.elementAt(j).toString());
            printwriter.println("<td class='textdesttabla'><a href='perfiles.jsp?TIPO=EDITAR&IDPERFIL=" + integer1.toString() + "'>" + integer1.toString() + "</a></td>");
            printwriter.println("<td class='textdesttabla'>" + (String)rs.elementAt(1 + j) + "</td>");
            String s = (String)rs.elementAt(2 + j);
            if(s.compareTo("A") == 0)
                printwriter.println("<td class='textdesttabla'>Activo</td>");
            else
                printwriter.println("<td class='textdesttabla'>Inactivo</td>");
            printwriter.println("</tr>");
        }

    }

    void GrabaPerfil(PrintWriter printwriter, String s)
    {
        String s1 = "insert into sgc.perfiles (perfil,estado) values ('" + s + "','A')";
        int i = ADatos.ModificaDB(s1);
    }

    void UpdatePerfil(PrintWriter printwriter, String s, String s1, String s2)
    {
        String s3 = "update sgc.perfiles set perfil = '" + s1 + "', estado='" + s2 + "' where idperfil = " + s;
        int i = ADatos.ModificaDB(s3);
    }

    int EliminaPerfil(PrintWriter printwriter, String s)
    {
        Integer integer = new Integer(0);
        Vector vector = new Vector();
        byte byte0 = -1;
        String s1 = "SELECT count(*) from sgc.perfilusuario WHERE  idperfil = " + s;
        int i = ADatos.ConsultaDB(s1);
        vector = ADatos.getResult();
        if(vector.size() > 0)
        {
            Integer integer1 = (Integer)vector.elementAt(0);
            if(integer1.longValue() > 0L)
            {
                byte0 = -2;
            } else
            {
                Vector vector1 = new Vector();
                String s2 = "SELECT count(*) from sad.permisosproceso WHERE idperfil = " + s;
                int j = ADatos.ConsultaDB(s2);
                vector1 = ADatos.getResult();
                if(vector1.size() > 0)
                {
                    Integer integer2 = (Integer)vector1.elementAt(0);
                    if(integer2.longValue() > 0L)
                    {
                        byte0 = -3;
                    } else
                    {
                        Vector vector2 = new Vector();
                        String s3 = "SELECT count(*) from sad.permisosproyecto WHERE idperfil = " + s;
                        int k = ADatos.ConsultaDB(s3);
                        vector2 = ADatos.getResult();
                        if(vector2.size() > 0)
                        {
                            Integer integer3 = (Integer)vector2.elementAt(0);
                            if(integer3.longValue() > 0L)
                            {
                                byte0 = -4;
                            } else
                            {
                                String s4 = "delete from sgc.perfiles where idperfil = " + s;
                                int l = ADatos.ModificaDB(s4);
                                byte0 = 0;
                            }
                        }
                    }
                }
            }
        }
        return byte0;
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
}