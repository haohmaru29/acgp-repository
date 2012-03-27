package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class verproceso extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public verproceso() {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        IdProceso = "";
        Tipo = "";
        IdProc = new Integer(0);
        totalusuarios = 0;
        SiglaProceso = "";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException {
        String s = "";
        String s1 = "";
        String s2 = "";
        String s3 = "";
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s5 = (String)httpsession.getValue("SerapisUser");
        if(s5 != null && s5.length() > 0)
        {
            IdProceso = httpservletrequest.getParameter("ID");
            String s4 = httpservletrequest.getParameter("IDPROC");
            if(s4 != null && s4.length() > 0)
                IdProc = new Integer(s4);
            Tipo = httpservletrequest.getParameter("TIPO");
            if(Tipo == null || Tipo.length() == 0)
                Tipo = "VER";
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<form name='frmproceso' action='' method='post'>");
            CargaForm(printwriter);
            printwriter.println("<BR>");
            printwriter.println("</form>");
            printwriter.println("<script language=\"javascript\">");
            CargaScript(printwriter);
            printwriter.println("</script>");
            printwriter.println("</body>");
            printwriter.println("</html>");
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "CONF", 6);
        }
    }

    int CargaForm(PrintWriter printwriter)
    {
        int i = 0;
        String s9 = "";
        String s12 = "";
        String s14 = "";
        String s16 = "";
        String s18 = "";
        String s25 = "";
        String s29 = "";
        String s30 = "N";
        String s32 = "";
        String s33 = "";
        String s37 = "";
        String s41 = "";
        int j = 0;
        if(Tipo.compareTo("VER") == 0 || Tipo.compareTo("ADD") == 0 || Tipo.compareTo("ADDPROC") == 0 || Tipo.compareTo("ADDROL") == 0)
        {
            String s = "select p.descripcion,p.lider,p.definicion,u.nombre,p.sigla,p.backuplider,p.validobackup from gdc.procesos p, sgc.usuarios u where u.login = p.lider and sigla = '" + IdProceso + "'";
            rs = new Vector();
            ADatos.connect();
            int k = ADatos.ConsultaDB(s);
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                i = 1;
                String s10 = (String)rs.elementAt(0);
                String s13 = (String)rs.elementAt(1);
                String s17 = (String)rs.elementAt(2);
                String s26 = (String)rs.elementAt(3);
                SiglaProceso = (String)rs.elementAt(4);
                String s15 = (String)rs.elementAt(5);
                String s31 = (String)rs.elementAt(6);
                if(s15.length() > 0)
                {
                    String s1 = "select nombre from sgc.usuarios u where login = '" + s15 + "'";
                    rs = new Vector();
                    int l = ADatos.ConsultaDB(s1);
                    rs = ADatos.getResult();
                    if(rs.size() > 0)
                        s29 = (String)rs.elementAt(0);
                    else
                        s29 = "";
                }
                printwriter.println("<table border='1' align='center' width='95%'>");
                printwriter.println("<tr><td width='95%' colspan='4' class='texttituloarea'>Editar Proceso (" + IdProceso + ")</td></tr>");
                printwriter.println("<tr>");
                if(Tipo.compareTo("VER") == 0)
                {
                    printwriter.println("<td class='textdesttabla' width='12%'>Proceso</td>");
                    printwriter.println("<td class='textdesttabla' width='38%'>");
                    printwriter.println("<input name='proceso' value='" + s10 + "' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(9);' maxlength='50'></td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla' width='12%'>Lider</td>");
                    printwriter.println("<td class='textdesttabla' width='38%'>");
                    printwriter.println("<select name='lider' language='javascript' onkeypress='return ValidarCaracteres(12);'>");
                    String s2 = "select login,nombre from sgc.usuarios order by login";
                    rs = new Vector();
                    ADatos.connect();
                    int i1 = ADatos.ConsultaDB(s2);
                    rs = ADatos.getResult();
                    for(int i2 = 0; i2 < rs.size(); i2 += 2)
                    {
                        String s19 = (String)rs.elementAt(i2);
                        s26 = (String)rs.elementAt(i2 + 1);
                        if(s19.compareTo(s13) == 0)
                            printwriter.println("<option value='" + s19 + "' selected>" + s26 + "</option>");
                        else
                            printwriter.println("<option value='" + s19 + "'>" + s26 + "</option>");
                    }

                    printwriter.println("</select>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla' width='12%'>Backup Lider</td>");
                    printwriter.println("<td class='textdesttabla' width='38%'>");
                    printwriter.println("<select name='backuplider'>");
                    printwriter.println("<option value=''>[Seleccione Backup]</option>");
                    for(int j2 = 0; j2 < rs.size(); j2 += 2)
                    {
                        String s20 = (String)rs.elementAt(j2);
                        s26 = (String)rs.elementAt(j2 + 1);
                        if(s20.compareTo(s15) == 0)
                            printwriter.println("<option value='" + s20 + "' selected>" + s26 + "</option>");
                        else
                            printwriter.println("<option value='" + s20 + "'>" + s26 + "</option>");
                    }

                    printwriter.println("</select>");
                    printwriter.println("&nbsp;&nbsp;&nbsp;");
                    if(s31.compareTo("S") == 0)
                        printwriter.println("<INPUT type='checkbox' name='chkbackup' checked>Habilitado");
                    else
                        printwriter.println("<INPUT type='checkbox' name='chkbackup'>Habilitado");
                    printwriter.println("<INPUT type='hidden' name='hddbackup' value=''>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla' width='12%' valign='top'>Definici\363n</td>");
                    printwriter.println("<td class='textdesttabla' colspan='3'>");
                    printwriter.println("<textarea rows=4 cols=50 name='definicion' onkeypress='return ValidarCaracteres(9);'>" + s17 + "</textarea>");
                    printwriter.println("</td></tr></table>");
                    printwriter.println("<BR><center><input type='button' name='addproceso' value='Grabar Proceso' class='fondoinput'  language='javascript' onclick='return grabaproceso();'>&nbsp;&nbsp;<input type='button' name='delproceso' value='Eliminar Proceso' class='fondoinput'  language='javascript' onclick='return borraproceso();'></center>");
                    s2 = "select idproc,procedimiento,rol,definicionrol from gdc.procedimientos where proceso = '" + IdProceso + "' order by proceso,procedimiento,idrol";
                    rs = new Vector();
                    ADatos.connect();
                    i = ADatos.ConsultaDB(s2);
                    rs = ADatos.getResult();
                    if(rs.size() > 0)
                    {
                        printwriter.println("<br>");
                        printwriter.println("<table border='1' align='center' width='95%'>");
                        printwriter.println("<tr><td class='texttitulotabla' colspan='4'>Procedimientos</td></tr>");
                        printwriter.println("<tr>");
                        printwriter.println("<td class='texttitulotabla' width='25%'>Procedimiento</td>");
                        printwriter.println("<td class='texttitulotabla' width='25%'>HOSHIN Meta</td>");
                        printwriter.println("<td class='texttitulotabla' width='25%'>Indicador</td>");
                        printwriter.println("</tr>");
                        for(int k2 = 0; k2 < rs.size(); k2 += 4)
                        {
                            IdProc = (Integer)rs.elementAt(k2);
                            String s34 = (String)rs.elementAt(k2 + 1);
                            String s38 = (String)rs.elementAt(k2 + 2);
                            String s42 = (String)rs.elementAt(k2 + 3);
                            if(s42.length() > 30)
                                s42 = s42.substring(0, 30) + "...";
                            printwriter.println("<tr>");
                            printwriter.println("<td class='textdesttabla'><a href='verproceso.jsp?ID=" + IdProceso + "&TIPO=ADDPROC&IDPROC=" + IdProc + "'>" + s34 + "</a></td>");
                            printwriter.println("<td class='textdesttabla'>" + s38 + "</td>");
                            printwriter.println("<td class='textdesttabla'>" + s42 + "</td>");
                            printwriter.println("</tr>");
                        }

                        printwriter.println("</table>");
                    } else
                    {
                        printwriter.println("<br>");
                        printwriter.println("<table border='1' width='95%' align='center'>");
                        printwriter.println("<tr><td class='texttitulotabla' colspan='4'>Procedimientos</td></tr>");
                        printwriter.println("<tr>");
                        printwriter.println("<td class='textdesttabla' width='100%'>No hay procedimientos disponibles</td>");
                        printwriter.println("</tr>");
                        printwriter.println("</table>");
                    }
                    printwriter.println("<BR><center><input type='button' name='agregarproc' value='Nuevo' class='fondoinput'  language='javascript' onclick='window.open(\"verproceso.jsp?ID=" + IdProceso + "&TIPO=ADD\",\"datos\");'></center>");
                }
                if(Tipo.compareTo("ADD") == 0)
                {
                    printwriter.println("<td class='textdesttabla' width='12%'><B>Proceso</B></td>");
                    printwriter.println("<td class='textdesttabla' width='38%'>" + s10 + "</td></tr>");
                    printwriter.println("<tr><td class='textdesttabla' width='12%'><B>Lider</B></td>");
                    printwriter.println("<td class='textdesttabla' width='38%'>" + s26 + "</td></tr>");
                    printwriter.println("<tr><td class='textdesttabla' width='12%'><B>Backup Lider</B></td>");
                    printwriter.println("<td class='textdesttabla' width='38%'>" + s29 + "</td></tr>");
                    printwriter.println("<tr><td class='textdesttabla' width='12%'><B>Definici\363n</B></td>");
                    printwriter.println("<td class='textdesttabla' colspan='3'>" + s17 + "</td></tr></table>");
                    printwriter.println("<BR>");
                    printwriter.println("<table border='1' width='95%' align='center'>");
                    printwriter.println("<tr><td width='95%' colspan='4' class='texttitulotabla'>Procedimiento</td></tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla' width='12%'>Procedimiento</td>");
                    printwriter.println("<td class='textdesttabla' width='38%'><input name='procedimiento' value='' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(9);' maxlength='60'></td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla' valign='top'>Indicador</td>");
                    printwriter.println("<td class='textdesttabla' colspan='3'>");
                    printwriter.println("<input name='definicionrol' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(9);'>");
                    printwriter.println("</td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla' width='12%'>HOSHIN Meta</td>");
                    printwriter.println("<td class='textdesttabla' width='38%'><input name='rol' value='' language='javascript' style='WIDTH: 50px' maxlength='60' onkeypress='return ValidarCaracteres(9);'></td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='texttitulotabla' valign='top' colspan='4'>Indicador</td>");
                    printwriter.println("</td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla' valign='top'>Acr\363nimo</td>");
                    printwriter.println("<td class='textdesttabla' colspan='3'><input name='indicador' value='' language='javascript' style='WIDTH: 50px' maxlength='10' onkeypress='return ValidarCaracteres(9);'></td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla' valign='top'>Valor M\355nimo</td>");
                    printwriter.println("<td class='textdesttabla' colspan='3'><input name='rango_minimo' value='' language='javascript' style='WIDTH: 50px' maxlength='10' onkeypress='return ValidarCaracteres(4);'></td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla' valign='top'>Valor M\341ximo</td>");
                    printwriter.println("<td class='textdesttabla' colspan='3'><input name='rango_maximo' value='' language='javascript' style='WIDTH: 50px' maxlength='10' onkeypress='return ValidarCaracteres(4);'></td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla' valign='top'>Rango Bueno</td>");
                    printwriter.println("<td class='textdesttabla' colspan='3'><input name='bueno1' value='' language='javascript' style='WIDTH: 50px' maxlength='10' onkeypress='return ValidarCaracteres(4);'>");
                    printwriter.println("&nbsp;&nbsp;<input name='bueno2' value='' language='javascript' style='WIDTH: 50px' maxlength='10' onkeypress='return ValidarCaracteres(4);'></td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla' valign='top'>Rango Regular</td>");
                    printwriter.println("<td class='textdesttabla' colspan='3'><input name='regular1' value='' language='javascript' style='WIDTH: 50px' maxlength='10' onkeypress='return ValidarCaracteres(4);'>");
                    printwriter.println("&nbsp;&nbsp;<input name='regular2' value='' language='javascript' style='WIDTH: 50px' maxlength='10' onkeypress='return ValidarCaracteres(4);'></td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla' valign='top'>Rango Malo</td>");
                    printwriter.println("<td class='textdesttabla' colspan='3'><input name='malo1' value='' language='javascript' style='WIDTH: 50px' maxlength='10' onkeypress='return ValidarCaracteres(4);'>");
                    printwriter.println("&nbsp;&nbsp;<input name='malo2' value='' language='javascript' style='WIDTH: 50px' maxlength='10' onkeypress='return ValidarCaracteres(4);'></td>");
                    printwriter.println("</tr>");
                    printwriter.println("</table>");
                    printwriter.println("<table border='1' width='95%' align='center'>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='texttitulotabla' colspan='4'>Responsable</td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    String s3 = "select login,nombre from sgc.usuarios UNION select login,nombre from gdc.usuariosad";
                    rs = new Vector();
                    ADatos.connect();
                    i = ADatos.ConsultaDB(s3);
                    rs = ADatos.getResult();
                    totalusuarios = rs.size() / 2;
                    for(int l2 = 0; l2 < rs.size(); l2 += 2)
                    {
                        String s21 = (String)rs.elementAt(l2);
                        s26 = (String)rs.elementAt(l2 + 1);
                        if(j >= 4)
                        {
                            j = 0;
                            printwriter.println("</tr>");
                            if(l2 < rs.size())
                                printwriter.println("<tr>");
                        }
                        printwriter.println("<td class='textdesttabla' width='25%'><input type='checkbox' name='responsable' value='" + s21 + "'>" + s26 + "</td>");
                        j++;
                    }

                    printwriter.println("</tr></table>");
                    printwriter.println("<BR><center><input type='button' name='addproc' value='Grabar' class='fondoinput'  language='javascript' onclick='return grabaprocedimiento(1);'></center>");
                    printwriter.println("<input name='userresp' value='' language='javascript' style='WIDTH: 220px' type='hidden'");
                }
                if(Tipo.compareTo("ADDPROC") == 0)
                {
                    printwriter.println("<td class='textdesttabla' width='12%'><B>Proceso</B></td>");
                    printwriter.println("<td class='textdesttabla' width='38%'>" + s10 + "</td></tr>");
                    printwriter.println("<tr><td class='textdesttabla' width='12%'><B>Lider</B></td>");
                    printwriter.println("<td class='textdesttabla' width='38%'>" + s26 + "</td></tr>");
                    printwriter.println("<tr><td class='textdesttabla' width='12%'><B>Backup Lider</B></td>");
                    printwriter.println("<td class='textdesttabla' width='38%'>" + s29 + "</td></tr>");
                    printwriter.println("<tr><td class='textdesttabla' width='12%'><B>Definici\363n</B></td>");
                    printwriter.println("<td class='textdesttabla' colspan='3'>" + s17 + "</td></tr></table>");
                    printwriter.println("<BR>");
                    if(IdProc != null)
                    {
                        String s4 = "select proceso,procedimiento,rol,definicionrol,responsables,indicador,rangominimo,rangomaximo,bueno_minimo,bueno_maximo,regular_minimo,regular_maximo,malo_minimo,malo_maximo from gdc.procedimientos where idproc = " + IdProc.intValue();
                        rs = new Vector();
                        ADatos.connect();
                        int j1 = ADatos.ConsultaDB(s4);
                        rs = ADatos.getResult();
                        if(rs.size() > 0)
                        {
                            s10 = (String)rs.elementAt(0);
                            String s35 = (String)rs.elementAt(1);
                            String s39 = (String)rs.elementAt(2);
                            String s43 = (String)rs.elementAt(3);
                            String s45 = (String)rs.elementAt(4);
                            printwriter.println("<table border='1' width='95%' align='center'>");
                            printwriter.println("<tr><td width='95%' colspan='4' class='texttitulotabla'>Procedimiento</td></tr>");
                            printwriter.println("<tr>");
                            printwriter.println("<td class='textdesttabla' width='12%'>Procedimiento</td>");
                            printwriter.println("<td class='textdesttabla' width='38%'><input name='procedimiento' value='" + s35 + "' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(9);' maxlength='60'></td></tr>");
                            printwriter.println("<tr>");
                            printwriter.println("<td class='textdesttabla' valign='top'>Indicador</td>");
                            printwriter.println("<td class='textdesttabla' colspan='3'>");
                            printwriter.println("<input name='definicionrol' language='javascript' value='" + s43 + "' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(9);'>");
                            printwriter.println("</td>");
                            printwriter.println("</tr>");
                            printwriter.println("<tr><td class='textdesttabla' width='12%'>HOSHIN Meta</td>");
                            printwriter.println("<td class='textdesttabla' width='38%'><input name='rol' value='" + s39 + "' language='javascript' style='WIDTH: 50px' onkeypress='return ValidarCaracteres(9);' maxlength='60'></td>");
                            printwriter.println("</tr>");
                            printwriter.println("<tr>");
                            printwriter.println("<td class='textdesttabla' valign='top'>Acr\363nimo</td>");
                            printwriter.println("<td class='textdesttabla' colspan='3'><input name='indicador' value='" + (String)rs.elementAt(5) + "' language='javascript' style='WIDTH: 50px' maxlength='10' onkeypress='return ValidarCaracteres(9);'></td>");
                            printwriter.println("</tr>");
                            printwriter.println("<tr>");
                            printwriter.println("<td class='textdesttabla' valign='top'>Valor M\355nimo</td>");
                            printwriter.println("<td class='textdesttabla' colspan='3'><input name='rango_minimo' value='" + ((Integer)rs.elementAt(6)).toString() + "' language='javascript' style='WIDTH: 50px' maxlength='10' onkeypress='return ValidarCaracteres(4);'></td>");
                            printwriter.println("</tr>");
                            printwriter.println("<tr>");
                            printwriter.println("<td class='textdesttabla' valign='top'>Valor M\341ximo</td>");
                            printwriter.println("<td class='textdesttabla' colspan='3'><input name='rango_maximo' value='" + ((Integer)rs.elementAt(7)).toString() + "' language='javascript' style='WIDTH: 50px' maxlength='10' onkeypress='return ValidarCaracteres(4);'></td>");
                            printwriter.println("</tr>");
                            printwriter.println("<tr>");
                            printwriter.println("<td class='textdesttabla' valign='top'>Rango Bueno</td>");
                            printwriter.println("<td class='textdesttabla' colspan='3'><input name='bueno1' value='" + ((Integer)rs.elementAt(8)).toString() + "' language='javascript' style='WIDTH: 50px' maxlength='10' onkeypress='return ValidarCaracteres(4);'>");
                            printwriter.println("&nbsp;&nbsp;<input name='bueno2' value='" + ((Integer)rs.elementAt(9)).toString() + "' language='javascript' style='WIDTH: 50px' maxlength='10' onkeypress='return ValidarCaracteres(4);'></td>");
                            printwriter.println("</tr>");
                            printwriter.println("<tr>");
                            printwriter.println("<td class='textdesttabla' valign='top'>Rango Regular</td>");
                            printwriter.println("<td class='textdesttabla' colspan='3'><input name='regular1' value='" + ((Integer)rs.elementAt(10)).toString() + "' language='javascript' style='WIDTH: 50px' maxlength='10' onkeypress='return ValidarCaracteres(4);'>");
                            printwriter.println("&nbsp;&nbsp;<input name='regular2' value='" + ((Integer)rs.elementAt(11)).toString() + "' language='javascript' style='WIDTH: 50px' maxlength='10' onkeypress='return ValidarCaracteres(4);'></td>");
                            printwriter.println("</tr>");
                            printwriter.println("<tr>");
                            printwriter.println("<td class='textdesttabla' valign='top'>Rango Malo</td>");
                            printwriter.println("<td class='textdesttabla' colspan='3'><input name='malo1' value='" + ((Integer)rs.elementAt(12)).toString() + "' language='javascript' style='WIDTH: 50px' maxlength='10' onkeypress='return ValidarCaracteres(4);'>");
                            printwriter.println("&nbsp;&nbsp;<input name='malo2' value='" + ((Integer)rs.elementAt(13)).toString() + "' language='javascript' style='WIDTH: 50px' maxlength='10' onkeypress='return ValidarCaracteres(4);'></td>");
                            printwriter.println("</tr>");
                            printwriter.println("<tr>");
                            printwriter.println("</table>");
                            printwriter.println("<table border='1' width='95%' align='center'>");
                            printwriter.println("<tr>");
                            printwriter.println("<td class='texttitulotabla' colspan='4'>Responsable</td>");
                            printwriter.println("</tr>");
                            printwriter.println("<tr>");
                            String s5 = "select login,nombre from sgc.usuarios UNION select login,nombre from gdc.usuariosad";
                            rs = new Vector();
                            ADatos.connect();
                            i = ADatos.ConsultaDB(s5);
                            rs = ADatos.getResult();
                            totalusuarios = rs.size() / 2;
                            for(int j3 = 0; j3 < rs.size(); j3 += 2)
                            {
                                String s22 = (String)rs.elementAt(j3);
                                s26 = (String)rs.elementAt(j3 + 1);
                                if(j >= 4)
                                {
                                    j = 0;
                                    printwriter.println("</tr>");
                                    if(j3 < rs.size())
                                        printwriter.println("<tr>");
                                }
                                if(ChequeaUsuario(s45, s22) > 0)
                                    printwriter.println("<td class='textdesttabla' width='25%'><input type='checkbox' name='responsable' value='" + s22 + "' checked>" + s26 + "</td>");
                                else
                                    printwriter.println("<td class='textdesttabla' width='25%'><input type='checkbox' name='responsable' value='" + s22 + "'>" + s26 + "</td>");
                                j++;
                            }

                            printwriter.println("</tr></table>");
                            printwriter.println("<BR><center><input type='button' name='addrol' value='Nuevo Indicador' class='fondoinput'  language='javascript' onclick='window.open(\"verproceso.jsp?ID=" + s10 + "&TIPO=ADDROL\",\"datos\");'>&nbsp;<input type='button' name='eliproc' value='Eliminar' class='fondoinput'  language='javascript' onclick='return borraprocedimiento();'>&nbsp;<input type='button' name='addproc' value='Grabar' class='fondoinput'  language='javascript' onclick='return grabaprocedimiento(2);'></center>");
                            printwriter.println("</table>");
                            printwriter.println("<input name='userresp' value='' language='javascript' style='WIDTH: 220px' type='hidden'");
                        }
                    }
                }
                if(Tipo.compareTo("ADDROL") == 0)
                {
                    printwriter.println("<td class='textdesttabla' width='12%'><B>Proceso</B></td>");
                    printwriter.println("<td class='textdesttabla' width='38%'>" + s10 + "</td></tr>");
                    printwriter.println("<tr><td class='textdesttabla' width='12%'><B>Lider</B></td>");
                    printwriter.println("<td class='textdesttabla' width='38%'>" + s26 + "</td></tr>");
                    printwriter.println("<tr><td class='textdesttabla' width='12%'><B>Backup Lider</B></td>");
                    printwriter.println("<td class='textdesttabla' width='38%'>" + s29 + "</td></tr>");
                    printwriter.println("<tr><td class='textdesttabla' width='12%'><B>Definici\363n</B></td>");
                    printwriter.println("<td class='textdesttabla' colspan='3'>" + s17 + "</td></tr></table>");
                    printwriter.println("<BR>");
                    if(IdProc != null)
                    {
                        String s6 = "select proceso,procedimiento,rol,definicionrol,responsables from gdc.procedimientos where idproc = " + IdProc.intValue();
                        rs = new Vector();
                        ADatos.connect();
                        int k1 = ADatos.ConsultaDB(s6);
                        rs = ADatos.getResult();
                        if(rs.size() > 0)
                        {
                            String s11 = (String)rs.elementAt(0);
                            String s36 = (String)rs.elementAt(1);
                            String s40 = (String)rs.elementAt(2);
                            String s44 = (String)rs.elementAt(3);
                            String s46 = (String)rs.elementAt(4);
                            printwriter.println("<table border='1' width='95%' align='center'>");
                            printwriter.println("<tr><td width='95%' colspan='4' class='texttitulotabla'>Procedimiento</td></tr>");
                            printwriter.println("<tr>");
                            printwriter.println("<td class='textdesttabla' width='12%'>Procedimiento</td>");
                            printwriter.println("<td class='textdesttabla' width='38%' >");
                            printwriter.println("<input name='procedimiento' value='" + s36 + "' language='javascript' style='WIDTH: 220px' onkeypress='' maxlength='60' type='hidden'>");
                            printwriter.println("<input name='procedimientovisible' value='" + s36 + "' language='javascript' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(9);' maxlength='60' disabled></td></tr>");
                            printwriter.println("<tr>");
                            printwriter.println("<td class='textdesttabla' valign='top'>Indicador</td>");
                            printwriter.println("<td class='textdesttabla' colspan='3'>");
                            printwriter.println("<input name='definicionrol' style='WIDTH: 220px' onkeypress='return ValidarCaracteres(9);'>");
                            printwriter.println("</td>");
                            printwriter.println("</tr>");
                            printwriter.println("<tr><td class='textdesttabla' width='12%'>HOSHIN Meta</td>");
                            printwriter.println("<td class='textdesttabla' width='38%'><input name='rol' value='' language='javascript' style='WIDTH: 50px' onkeypress='return ValidarCaracteres(9);' maxlength='60'></td>");
                            printwriter.println("</tr>");
                            printwriter.println("<tr>");
                            printwriter.println("<td class='texttitulotabla' valign='top' colspan='4'>Indicador</td>");
                            printwriter.println("</td>");
                            printwriter.println("</tr>");
                            printwriter.println("<tr>");
                            printwriter.println("<td class='textdesttabla' valign='top'>Acr\363nimo</td>");
                            printwriter.println("<td class='textdesttabla' colspan='3'><input name='indicador' value='' language='javascript' style='WIDTH: 50px' maxlength='10' onkeypress='return ValidarCaracteres(9);'></td>");
                            printwriter.println("</tr>");
                            printwriter.println("<tr>");
                            printwriter.println("<td class='textdesttabla' valign='top'>Valor M\355nimo</td>");
                            printwriter.println("<td class='textdesttabla' colspan='3'><input name='rango_minimo' value='' language='javascript' style='WIDTH: 50px' maxlength='10' onkeypress='return ValidarCaracteres(4);'></td>");
                            printwriter.println("</tr>");
                            printwriter.println("<tr>");
                            printwriter.println("<td class='textdesttabla' valign='top'>Valor M\341ximo</td>");
                            printwriter.println("<td class='textdesttabla' colspan='3'><input name='rango_maximo' value='' language='javascript' style='WIDTH: 50px' maxlength='10' onkeypress='return ValidarCaracteres(4);'></td>");
                            printwriter.println("</tr>");
                            printwriter.println("<tr>");
                            printwriter.println("<td class='textdesttabla' valign='top'>Rango Bueno</td>");
                            printwriter.println("<td class='textdesttabla' colspan='3'><input name='bueno1' value='' language='javascript' style='WIDTH: 50px' maxlength='10' onkeypress='return ValidarCaracteres(4);'>");
                            printwriter.println("&nbsp;&nbsp;<input name='bueno2' value='' language='javascript' style='WIDTH: 50px' maxlength='10' onkeypress='return ValidarCaracteres(4);'></td>");
                            printwriter.println("</tr>");
                            printwriter.println("<tr>");
                            printwriter.println("<td class='textdesttabla' valign='top'>Rango Regular</td>");
                            printwriter.println("<td class='textdesttabla' colspan='3'><input name='regular1' value='' language='javascript' style='WIDTH: 50px' maxlength='10' onkeypress='return ValidarCaracteres(4);'>");
                            printwriter.println("&nbsp;&nbsp;<input name='regular2' value='' language='javascript' style='WIDTH: 50px' maxlength='10' onkeypress='return ValidarCaracteres(4);'></td>");
                            printwriter.println("</tr>");
                            printwriter.println("<tr>");
                            printwriter.println("<td class='textdesttabla' valign='top'>Rango Malo</td>");
                            printwriter.println("<td class='textdesttabla' colspan='3'><input name='malo1' value='' language='javascript' style='WIDTH: 50px' maxlength='10' onkeypress='return ValidarCaracteres(4);'>");
                            printwriter.println("&nbsp;&nbsp;<input name='malo2' value='' language='javascript' style='WIDTH: 50px' maxlength='10' onkeypress='return ValidarCaracteres(4);'></td>");
                            printwriter.println("</tr>");
                            printwriter.println("</table>");
                            printwriter.println("<table border='1' width='95%' align='center'>");
                            printwriter.println("<tr>");
                            printwriter.println("<td class='texttitulotabla' colspan='4'>Responsable</td>");
                            printwriter.println("</tr>");
                            printwriter.println("<tr>");
                            String s7 = "select login,nombre from sgc.usuarios UNION select login,nombre from gdc.usuariosad";
                            rs = new Vector();
                            ADatos.connect();
                            i = ADatos.ConsultaDB(s7);
                            rs = ADatos.getResult();
                            for(int k3 = 0; k3 < rs.size(); k3 += 2)
                            {
                                String s23 = (String)rs.elementAt(k3);
                                String s27 = (String)rs.elementAt(k3 + 1);
                                if(j >= 4)
                                {
                                    j = 0;
                                    printwriter.println("</tr>");
                                    if(k3 < rs.size())
                                        printwriter.println("<tr>");
                                }
                                printwriter.println("<td class='textdesttabla' width='25%'><input type='checkbox' name='responsable' value='" + s23 + "'>" + s27 + "</td>");
                                j++;
                            }

                            printwriter.println("</tr></table>");
                            printwriter.println("<BR><center><input type='button' name='addrol' value='Grabar' class='fondoinput'  language='javascript' onclick='return grabaprocedimiento(1);'></center>");
                            printwriter.println("<input name='userresp' value='' language='javascript' style='WIDTH: 220px' type='hidden'");
                        }
                    }
                }
            }
        }
        if(Tipo.compareTo("ADDP") == 0)
        {
            printwriter.println("<table border='1' width='95%' align='center'>");
            printwriter.println("<tr><td width='95%' colspan='4' class='texttituloarea'>Nuevo Proceso</td></tr>");
            printwriter.println("</table><BR>");
            printwriter.println("<table border='1' width='95%' align='center'>");
            printwriter.println("<tr><td class='textdesttabla' width='12%'>Proceso</td>");
            printwriter.println("<td class='textdesttabla'>");
            printwriter.println("<input name='idproceso' value='' language='javascript' style='WIDTH: 35px' maxlength='2' onkeypress='return ValidarCaracteres(12);'>");
            printwriter.println("<input name='proceso' value='' language='javascript' style='WIDTH: 220px' maxlength='50' onkeypress='return ValidarCaracteres(9);'></td></tr>");
            printwriter.println("<tr><td class='textdesttabla' width='12%'>Lider</td>");
            printwriter.println("<td class='textdesttabla'>");
            printwriter.println("<select name='lider'>");
            String s8 = "select login,nombre from sgc.usuarios order by login";
            rs = new Vector();
            ADatos.connect();
            int l1 = ADatos.ConsultaDB(s8);
            rs = ADatos.getResult();
            for(int i3 = 0; i3 < rs.size(); i3 += 2)
            {
                String s24 = (String)rs.elementAt(i3);
                String s28 = (String)rs.elementAt(i3 + 1);
                printwriter.println("<option value='" + s24 + "'>" + s28 + "</option>");
            }

            printwriter.println("</select>");
            printwriter.println("</td></tr>");
            printwriter.println("<tr>");
            printwriter.println("<td class='textdesttabla' width='12%' valign='top'>Definici\363n</td>");
            printwriter.println("<td class='textdesttabla' colspan='3'>");
            printwriter.println("<textarea rows=4 cols=50 name='definicion' onkeypress='return ValidarCaracteres(3);'></textarea>");
            printwriter.println("</td></tr></table>");
            printwriter.println("<BR><center><input type='button' name='anadirproceso' value='Grabar Proceso' class='fondoinput'  language='javascript' onclick='return grabaprocesonew();'></center>");
        }
        return i;
    }

    int ChequeaUsuario(String s, String s1)
    {
        String s2 = "";
        int i = 0;
        s = s + ";";
        StringTokenizer stringtokenizer = new StringTokenizer(s, ";");
        do
        {
            if(!stringtokenizer.hasMoreTokens())
                break;
            String s3 = stringtokenizer.nextToken();
            if(s3.compareTo(s1) != 0)
                continue;
            i = 1;
            break;
        } while(true);
        return i;
    }

    void CargaScript(PrintWriter printwriter)
    {
        printwriter.println("function borraproceso()");
        printwriter.println("{");
        printwriter.println("if (confirm(\"\277Esta que desea eliminar el proceso.?\") == true)");
        printwriter.println("    if (confirm(\"Para eliminar un proceso este no debe estar asociado con: Procedimientos, Lista Maestra, Documentos, Permisos. \277Esta seguro que desea eliminar el proceso?\")==true)");
        printwriter.println("    {");
        printwriter.println("       document.frmproceso.action = \"eliminaproceso.jsp?CODPROCESO=" + IdProceso + "\";");
        printwriter.println("       document.frmproceso.submit();");
        printwriter.println("    }");
        printwriter.println("else");
        printwriter.println("    alert(\"No se borra\");");
        printwriter.println("}");
        printwriter.println("function grabaproceso()");
        printwriter.println("{");
        printwriter.println("var sigue = false;");
        printwriter.println("if(document.frmproceso.proceso.value != \"\")");
        printwriter.println("{");
        printwriter.println("  if(document.frmproceso.lider.value != \"\")");
        printwriter.println("  {");
        printwriter.println("    if(document.frmproceso.definicion.value != \"\")");
        printwriter.println("    {");
        printwriter.println("      document.frmproceso.action=\"grabaproceso.jsp?ID=2&CODPROCESO=" + IdProceso + "\";");
        printwriter.println("      if(document.frmproceso.chkbackup.checked == true)");
        printwriter.println("      {");
        printwriter.println("        document.frmproceso.hddbackup.value = \"S\";");
        printwriter.println("        if(document.frmproceso.backuplider.value != \"\")");
        printwriter.println("          sigue = true;");
        printwriter.println("        else");
        printwriter.println("        {");
        printwriter.println("          alert(\"Debe especificar un Backup para Habilitarlo\")");
        printwriter.println("        }");
        printwriter.println("      }");
        printwriter.println("      else");
        printwriter.println("      {");
        printwriter.println("        document.frmproceso.hddbackup.value = \"N\";");
        printwriter.println("        sigue = true;");
        printwriter.println("      }");
        printwriter.println("      if (sigue == true)");
        printwriter.println("         document.frmproceso.submit();");
        printwriter.println("    }");
        printwriter.println("    else");
        printwriter.println("    {");
        printwriter.println("      alert(\"Debe especificar definici\363n del proceso\")");
        printwriter.println("      document.frmproceso.definicion.focus();");
        printwriter.println("    }");
        printwriter.println("  }");
        printwriter.println("  else");
        printwriter.println("  {");
        printwriter.println("    alert(\"Debe especificar lider del proceso\")");
        printwriter.println("    document.frmproceso.lider.focus();");
        printwriter.println("  }");
        printwriter.println("}");
        printwriter.println("else");
        printwriter.println("{");
        printwriter.println("    alert(\"Debe especificar descripci\363n del proceso\")");
        printwriter.println("    document.frmproceso.proceso.focus();");
        printwriter.println("}");
        printwriter.println("  return 0;");
        printwriter.println("}");
        printwriter.println("function grabaprocesonew()");
        printwriter.println("{");
        printwriter.println("if(document.frmproceso.idproceso.value != \"\")");
        printwriter.println("{");
        printwriter.println("if(document.frmproceso.proceso.value != \"\")");
        printwriter.println("{");
        printwriter.println("  if(document.frmproceso.lider.value != \"\")");
        printwriter.println("  {");
        printwriter.println("    if(document.frmproceso.definicion.value != \"\")");
        printwriter.println("    {");
        printwriter.println("      document.frmproceso.action=\"grabaproceso.jsp?ID=1\";");
        printwriter.println("      document.frmproceso.submit();");
        printwriter.println("    }");
        printwriter.println("    else");
        printwriter.println("    {");
        printwriter.println("      alert(\"Debe especificar definici\363n del proceso\")");
        printwriter.println("      document.frmproceso.definicion.focus();");
        printwriter.println("    }");
        printwriter.println("  }");
        printwriter.println("  else");
        printwriter.println("  {");
        printwriter.println("    alert(\"Debe especificar lider del proceso\")");
        printwriter.println("    document.frmproceso.lider.focus();");
        printwriter.println("  }");
        printwriter.println("  }");
        printwriter.println("  else");
        printwriter.println("  {");
        printwriter.println("    alert(\"Debe especificar abreviatura del proceso\")");
        printwriter.println("    document.frmproceso.proceso.focus();");
        printwriter.println("  }");
        printwriter.println("}");
        printwriter.println("else");
        printwriter.println("{");
        printwriter.println("    alert(\"Debe especificar descripci\363n del proceso\")");
        printwriter.println("    document.frmproceso.idproceso.focus();");
        printwriter.println("}");
        printwriter.println("  return 0;");
        printwriter.println("}");
        if(totalusuarios > 0)
        {
            printwriter.println("function grabaprocedimiento(TipoAdd)");
            printwriter.println("{");
            printwriter.println("  var i;");
            printwriter.println("  var sigue;");
            printwriter.println("  var usresp;");
            printwriter.println("  usresp=\"\";");
            printwriter.println("  if(document.frmproceso.procedimiento.value != \"\") {");
            printwriter.println("    if(document.frmproceso.rol.value != \"\") {");
            
            printwriter.println("      if(document.frmproceso.definicionrol.value != \"\") {");  
            printwriter.println("        for(i=0; i<" + totalusuarios + "; i++) {");
            
            if (totalusuarios == 1)
            	printwriter.println("          if(document.frmproceso.responsable.checked == true) {");
            else
            	printwriter.println("          if(document.frmproceso.responsable(i).checked == true) {");
            
            printwriter.println("            if(usresp.length > 0) {");
            printwriter.println("              usresp = usresp + \";\" + document.frmproceso.responsable(i).value;");
            printwriter.println("            } else {");
            
            if (totalusuarios == 1)
            	printwriter.println("              usresp = document.frmproceso.responsable.value;");
            else
            	printwriter.println("              usresp = document.frmproceso.responsable(i).value;");
            
            printwriter.println("            }");
            
            
            printwriter.println("          }");
            printwriter.println("        }");

            printwriter.println("        if(usresp.length>0) {");
            printwriter.println("		   sigue = true;");
            printwriter.println("          if(isNaN((parseFloat(document.frmproceso.rango_minimo.value)))) ");
            printwriter.println("            sigue = false; ");
            printwriter.println("          if(isNaN((parseFloat(document.frmproceso.rango_maximo.value)))) ");
            printwriter.println("            sigue = false;");
            printwriter.println("          if(isNaN((parseFloat(document.frmproceso.bueno1.value)))) ");
            printwriter.println("            sigue = false;");
            printwriter.println("          if(isNaN((parseFloat(document.frmproceso.bueno2.value))))");
            printwriter.println("            sigue = false;");
            printwriter.println("          if(isNaN((parseFloat(document.frmproceso.regular1.value))))");
            printwriter.println("            sigue = false;");
            printwriter.println("          if(isNaN((parseFloat(document.frmproceso.regular2.value))))");
            printwriter.println("            sigue = false;");
            printwriter.println("          if(isNaN((parseFloat(document.frmproceso.malo1.value))))");
            printwriter.println("            sigue = false;");
            printwriter.println("          if(isNaN((parseFloat(document.frmproceso.malo2.value))))");
            printwriter.println("            sigue = false;");
            printwriter.println("           if(document.frmproceso.rango_minimo.value != \"\") {");
            printwriter.println("              if(document.frmproceso.rango_maximo.value != \"\") {");
            printwriter.println("                 if((document.frmproceso.bueno1.value != \"\") && (document.frmproceso.bueno2.value != \"\")) {");
            printwriter.println("                    if((document.frmproceso.regular1.value != \"\") && (document.frmproceso.regular2.value != \"\")) {");
            printwriter.println("                       if((document.frmproceso.malo1.value != \"\") && (document.frmproceso.malo2.value != \"\")) {");
            printwriter.println("                          if(document.frmproceso.indicador.value != \"\") {");
            printwriter.println("                             if(sigue == true) {");
            printwriter.println("                                document.frmproceso.userresp.value = usresp;");
            printwriter.println("                                document.frmproceso.action=\"grabaprocedimiento.jsp?ID=\"+TipoAdd+\"&CODPROCESO=" + IdProceso + "&IDPROC=" + IdProc.intValue() + "\";");
            printwriter.println("                                document.frmproceso.submit();");
            printwriter.println("                             } else {");
            printwriter.println("                                alert(\"Todos los valores en Rango deben ser num\351ricos\");");
            printwriter.println("                             }");
            printwriter.println("                          } else {");
            printwriter.println("                             alert(\"Necesariamente debe especificar acr\363nimo del indicador de gesti\363n\");");
            printwriter.println("                             document.frmproceso.indicador.focus();");
            printwriter.println("                          }");
            printwriter.println("                       } else {");
            printwriter.println("                          alert(\"Necesariamente debe especificar Rango Malo\");");
            printwriter.println("                       }");
            printwriter.println("                    } else {");
            printwriter.println("                       alert(\"Necesariamente debe especificar Rango Regular\");");
            printwriter.println("                    }");
            printwriter.println("                 } else {");
            printwriter.println("                    alert(\"Necesariamente debe especificar Rango Bueno\");");
            printwriter.println("                 }");
            printwriter.println("              } else {");
            printwriter.println("                 alert(\"Debe ingresar un rango m\341ximo del indicador\");");
            printwriter.println("                 document.frmproceso.rango_maximo.focus();");
            printwriter.println("              }");
            printwriter.println("           } else {");
            printwriter.println("              alert(\"Debe ingresar un rango m\355nimo del indicador\");");
            printwriter.println("              document.frmproceso.rango_minimo.focus();");
            printwriter.println("           }");
            printwriter.println("        } else {");
            printwriter.println("          alert(\"Debe seleccionar al menos un responsable\");");
            printwriter.println("        }");
            printwriter.println("      } else {");
            printwriter.println("        alert(\"Debe especificar indicador\");");
            printwriter.println("        document.frmproceso.definicionrol.focus();");
            printwriter.println("      }");
            printwriter.println("    } else {");
            printwriter.println("      alert(\"Debe ingresar HOSHIN Meta\");");
            printwriter.println("      document.frmproceso.rol.focus();");
            printwriter.println("    }");
            printwriter.println("  } else {");
            printwriter.println("    alert(\"Debe ingresar procedimiento\");");
            printwriter.println("    document.frmproceso.procedimiento.focus();");
            printwriter.println("  }");
            printwriter.println("  return 0;");
            printwriter.println("}");
        }
        printwriter.println("function borraprocedimiento()");
        printwriter.println("{");
        printwriter.println("  document.frmproceso.action=\"grabaprocedimiento.jsp?ID=3&IDPROC=" + IdProc.intValue() + "&CODPROCESO=" + SiglaProceso + "\";");
        printwriter.println("  document.frmproceso.submit();");
        printwriter.println("}");
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    String IdProceso;
    String Tipo;
    Integer IdProc;
    int totalusuarios;
    String SiglaProceso;
}