package sad;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class muestradoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public muestradoc()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        rutUsuario="";
        Tipo = "";
        SubTipo = "";
        Proceso = "";
        TipoDoc = "";
        Cliente = "";
        Proyecto = "";
        IdDoc = "";
        UserReg = "";
        GlosaProy = "";
    	sPath = "";
    	String s0 = "select dirsitio from gdc.datosgenerales";
    	ADatos.connect();
        int k1 = ADatos.ConsultaDB(s0);
        if (k1>0) {
	        rs = ADatos.getResult();
	        if (rs.size()>0) {
	        	sPath = (String)rs.elementAt(0);
	        }
	        rs.clear();
    	}           
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        UserReg = (String)httpsession.getValue("SerapisUser");
        GlosaProy = (String)httpsession.getValue("SerapisGlosa");
        rutUsuario = httpsession.getAttribute("SerapisRut").toString();
        if(GlosaProy != null && GlosaProy.length() > 0)
            GlosaProy = AFunc.desencripta(GlosaProy);
        else
            GlosaProy = "Proyecto";
        Tipo = httpservletrequest.getParameter("TIPO");
        SubTipo = httpservletrequest.getParameter("SUBTIPO");
        Proceso = httpservletrequest.getParameter("PROCESO");
        TipoDoc = httpservletrequest.getParameter("TIPODOC");
        Cliente = httpservletrequest.getParameter("CLI");
        Proyecto = httpservletrequest.getParameter("PROY");
        IdDoc = httpservletrequest.getParameter("ID");
        if(UserReg != null && UserReg.length() > 0)
        {
            if(Tipo == null || Tipo.length() == 0)
                Tipo = "DOC";
            if(SubTipo == null || SubTipo.length() == 0)
                SubTipo = "A";
            ADatos.connect();
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            if(Tipo.compareTo("DOC") == 0 && SubTipo.compareTo("A") == 0)
            {
                if(IdDoc != null && IdDoc.length() > 0)
                    ObtieneDocumentos(IdDoc, printwriter);
                else
                    AFunc.reindex(httpservletrequest, printwriter, 1, "SAD", 6);
            } else
            if(Tipo.compareTo("DOC") == 0 && SubTipo.compareTo("P") == 0)
                ObtieneDocumentosProyecto(Cliente, Proyecto, IdDoc, printwriter);
            else
                AFunc.reindex(httpservletrequest, printwriter, 1, "SAD", 6);
            printwriter.println("</body>");
            printwriter.println("</html>");
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "SAD", 6);
        }
    }

    public String TieneAcceso(PrintWriter printwriter, String s, String s1, long l, String s2)
    {
        String s9 = "00000";
        Integer integer = new Integer(0);
        Integer integer1 = new Integer(0);
        Vector vector = new Vector();
        if(s.compareTo("PROCESO") == 0)
        {
            String s3 = "SELECT tipopermiso from sad.permisosproceso where proceso='" + s1 + "' and usuario = '" + s2 + "'";
            int k = ADatos.ConsultaDB(s3);
            Vector vector1 = ADatos.getResult();
            if(vector1.size() > 0)
            {
                s9 = (String)vector1.elementAt(0);
            } else
            {
                s9 = "00000";
                Vector vector7 = new Vector();
                String s4 = "SELECT idperfil FROM sgc.perfilusuario where usuario = '" + s2 + "'";
                int i1 = ADatos.ConsultaDB(s4);
                Vector vector2 = ADatos.getResult();
                vector7 = (Vector)vector2.clone();
                vector2.clear();
                vector2 = new Vector();
                int i = 0;
                do
                {
                    if(i >= vector7.size())
                        break;
                    s9 = "00000";
                    Integer integer2 = (Integer)vector7.elementAt(i);
                    String s5 = "select tipopermiso from sad.permisosproceso where proceso='" + s1 + "' and idperfil = " + integer2.toString();
                    int j1 = ADatos.ConsultaDB(s5);
                    Vector vector3 = ADatos.getResult();
                    if(vector3.size() > 0)
                    {
                        s9 = (String)vector3.elementAt(0);
                        break;
                    }
                    i++;
                } while(true);
            }
        } else
        {
            String s6 = "SELECT tipopermiso from sad.permisosproyecto where idproyecto='" + l + "' and usuario = '" + s2 + "'";
            int k1 = ADatos.ConsultaDB(s6);
            Vector vector4 = ADatos.getResult();
            if(vector4.size() > 0)
            {
                s9 = (String)vector4.elementAt(0);
            } else
            {
                s9 = "00000";
                Vector vector8 = new Vector();
                String s7 = "SELECT idperfil FROM sgc.perfilusuario where usuario = '" + s2 + "'";
                int l1 = ADatos.ConsultaDB(s7);
                Vector vector5 = ADatos.getResult();
                vector8 = (Vector)vector5.clone();
                vector5.clear();
                vector5 = new Vector();
                int j = 0;
                do
                {
                    if(j >= vector8.size())
                        break;
                    s9 = "00000";
                    Integer integer3 = (Integer)vector8.elementAt(j);
                    String s8 = "select tipopermiso from sad.permisosproyecto where idproyecto=" + l + " and idperfil = " + integer3.toString();
                    int i2 = ADatos.ConsultaDB(s8);
                    Vector vector6 = ADatos.getResult();
                    if(vector6.size() > 0)
                    {
                        s9 = (String)vector6.elementAt(0);
                        break;
                    }
                    j++;
                } while(true);
            }
        }
        return s9;
    }

    void ObtieneDocumentos(String s, PrintWriter printwriter) {
        String s1 = "";
        String s3 = "";
        String s5 = "";
        String s7 = "";
        String s9 = "00000";
        int i = 0;
        String s11 = "";
        String s13 = "";
        String s15 = "";
        Integer integer = new Integer(0);
        String s17 = "";
        String s19 = "";
        String s20 = "";
        Integer integer2 = new Integer(0);
        rs = new Vector();
        s7 = "select tipodocumento,proceso,descripcion from gdc.documentoscalidad where id = " + s;
        int j = ADatos.ConsultaDB(s7);
        rs = ADatos.getResult();
        if(rs.size() > 0) {
            String s2 = (String)rs.elementAt(0);
            String s4 = (String)rs.elementAt(1);
            String s6 = (String)rs.elementAt(2);
            String s10 = TieneAcceso(printwriter, "PROCESO", s4, 0L, UserReg);
            if(s10.compareTo("00000") != 0 && s10.compareTo("00001") != 0) {
                String s8 = "select r.tipodoc,r.proceso,r.version,r.descripcion,r.cliente,r.adicional,r.verdoc," +
                		"r.nombrearchivo,r.extension,r.comentario,r.id,r.usuario,p.descripcion, r.fechapublica , " +
                		"r.rut_usuario ";
                s8 = s8 + " from sad.documentos r, gdc.procesos p ";
                s8 = s8 + " where p.sigla = r.proceso and r.tipodoc = '" + s2 + "' and r.proceso = '" + s4 + "' and  r.descripcion = '" + s6 + "' and r.estado = 'A'";
                              
                s8 = s8 + " order by r.verdoc desc,p.descripcion,r.tipodoc,r.version,r.descripcion ";
                int k = ADatos.ConsultaDB(s8);
                rs = ADatos.getResult();
                if(rs.size() > 0) {
                    for(int l = 0; l < rs.size(); l += 15) {
                        String s12 = (String)rs.elementAt(7 + l);
                        String s14 = (String)rs.elementAt(8 + l);
                        String s16 = (String)rs.elementAt(9 + l);
                        Integer integer1 = (Integer)rs.elementAt(10 + l);
                        String s18 = (String)rs.elementAt(11 + l);
                        String s21 = rs.elementAt(12 + l) + "";
                        Integer integer3 = (Integer)rs.elementAt(13 + l);
                        String rutUsuarioDB = rs.elementAt(14 + l) + "";
                        if("null".equals(rutUsuarioDB ) || rutUsuario.equals(rutUsuarioDB) || "10000".equals(s10)) {
                        	if(s21.compareTo(s19) != 0) {
                                if(s19.length() > 0) {
                                    printwriter.println("</table>");
                                    printwriter.println("<BR>");
                                }
                                printwriter.println("<table border='1' width='95%' align='center'>");
                                printwriter.println("<tr>");
                                printwriter.println("<td class='texttitulotabla' align='center'>Archivos</td>");
                                printwriter.println("</tr>");
                                printwriter.println("</table>");
                                printwriter.println("<table border='1' width='90%' align='center'>");
                                printwriter.println("<tr><td class='texttituloarea' align='left' colspan='5'>" + s21 + "</td></tr>");
                                printwriter.println("<tr>");
                                printwriter.println("<td class='texttitulotabla' align='center' width='8%'>Tipo</td>");
                                printwriter.println("<td class='texttitulotabla' align='center' width='35%'>Archivo</td>");
                                printwriter.println("<td class='texttitulotabla' align='center' width='25%'>Descripci\363n</td>");
                                printwriter.println("<td class='texttitulotabla' align='center' width='20%'>Usuario</td>");
                                printwriter.println("<td class='texttitulotabla' align='center' width='12%'>Fecha Publicaci\363n</td>");
                                printwriter.println("</tr>");
                                s19 = s21;
                            }
                            printwriter.println("<tr>");
                            if(AFunc.ExisteArchivo(sPath + "\\images\\ext_" + s14 + ".gif") == 1)
                                printwriter.println("<td class='textdesttabla'> <a href='verarchivo.jsp?SUBTIPO=N&TIPO=A&ID=" + integer1.toString() + "&PERMISO=" + s10 + "','datos'><IMG src=../images/ext_" + s14 + ".gif width=32 height=32 border=0></a> </td>");
                            else
                                printwriter.println("<td class='textdesttabla'> <a href='verarchivo.jsp?SUBTIPO=N&TIPO=A&ID=" + integer1.toString() + "&PERMISO=" + s10 + "','datos'><IMG src=../images/extdefault.gif width=32 height=32 border=0></a> </td>");
                            printwriter.println("<td class='textdesttabla'> " + s12 + "</td>");
                            if(s16.length() > 0)
                                printwriter.println("<td class='textdesttabla'>" + s16 + "</td>");
                            else
                                printwriter.println("<td class='textdesttabla'>&nbsp;</td>");
                            printwriter.println("<td class='textdesttabla'>" + s18 + "</td>");
                            printwriter.println("<td class='textdesttabla'>" + AFunc.ConstruyeFecha(integer3.toString(), "/", "dmy") + "</td>");
                            printwriter.println("</tr>");
                        } else {
                        	System.out.println("-------------> " + rutUsuarioDB);
                        	
                        }
                        
                        
                        i++;
                    }

                    printwriter.println("</table>");
                } else
                {
                    printwriter.println("<table border='1' width='90%'  align='center'>");
                    printwriter.println("<tr><td class='texttitulotabla' align='center'>No hay documentos disponibles</td></tr>");
                    printwriter.println("</table>");
                }
                s19 = "";
                String s22 = "";
            } else
            {
                printwriter.println("<table border='1' width='90%'  align='center'>");
                printwriter.println("<tr><td class='texttitulotabla' align='center'>Permisos</td></tr>");
                printwriter.println("<tr><td class='textdesttabla' align='center'>No tiene permisos para acceder a los archivos que solicita.</td></tr>");
                printwriter.println("</table>");
            }
        }
    }

    void ObtieneDocumentosProyecto(String s, String s1, String s2, PrintWriter printwriter)
    {
        String s3 = "";
        String s5 = "";
        String s7 = "";
        String s9 = "";
        String s12 = "00000";
        Integer integer = new Integer(0);
        Integer integer2 = new Integer(0);
        rs = new Vector();
        s9 = "select id from gdc.proyectos where abreviatura = '" + s1 + "'";
        int i = ADatos.ConsultaDB(s9);
        rs = ADatos.getResult();
        if(rs.size() > 0)
        {
            Integer integer1 = (Integer)rs.elementAt(0);
            String s13 = TieneAcceso(printwriter, "PROYECTO", "", integer1.longValue(), UserReg);
            if(s13.compareTo("00000") != 0 && s13.compareTo("00001") != 0)
            {
                rs.clear();
                String s10 = "select tipodocumento,proceso,descripcion from gdc.documentoscalidad where id = " + s2;
                int j = ADatos.ConsultaDB(s10);
                rs = ADatos.getResult();
                if(rs.size() > 0)
                {
                    String s4 = (String)rs.elementAt(0);
                    String s6 = (String)rs.elementAt(1);
                    String s8 = (String)rs.elementAt(2);
                    rs = new Vector();
                    String s11 = "select dp.tipodoc,dp.proceso,dp.version,dp.descripcion,dp.cliente,dp.adicional,dp.verdoc,dp.nombrearchivo,dp.extension,dp.comentario,dp.id,p.proyecto,dp.usuario,c.razonsocial,dp.fechapublica";
                    s11 = s11 + " from sad.documentosp dp, gdc.proyectos p, sgc.clientes c ";
                    s11 = s11 + " where dp.cliente = '" + s + "' ";
                    s11 = s11 + " and dp.proyecto = '" + s1 + "' ";
                    s11 = s11 + " and dp.proceso = '" + s6 + "' ";
                    s11 = s11 + " and dp.tipodoc = '" + s4 + "' ";
                    s11 = s11 + " and dp.descripcion = '" + s8 + "'";
                    s11 = s11 + " and dp.proyecto = p.abreviatura and dp.cliente = c.abreviatura ";
                    s11 = s11 + " and dp.estado = 'A'";
                    s11 = s11 + " order by dp.verdoc desc,c.razonsocial,p.proyecto,dp.tipodoc,dp.proceso,dp.version,dp.descripcion ";
                    int k = ADatos.ConsultaDB(s11);
                    rs = ADatos.getResult();
                    if(rs.size() > 0)
                    {
                        int l = 0;
                        String s14 = "";
                        String s16 = "";
                        String s18 = "";
                        String s20 = "";
                        Integer integer4 = new Integer(0);
                        String s22 = "";
                        String s24 = "";
                        String s25 = "";
                        String s26 = "";
                        String s28 = "";
                        printwriter.println("<table border='1' width='90%' align='center'>");
                        printwriter.println("<tr>");
                        printwriter.println("<td class='texttitulotabla' align='center'>Archivos por " + GlosaProy + "</td>");
                        printwriter.println("</tr>");
                        printwriter.println("</table>");
                        for(int i1 = 0; i1 < rs.size(); i1 += 15)
                        {
                            String s15 = (String)rs.elementAt(7 + i1);
                            String s17 = (String)rs.elementAt(8 + i1);
                            String s19 = (String)rs.elementAt(9 + i1);
                            Integer integer5 = (Integer)rs.elementAt(10 + i1);
                            String s21 = (String)rs.elementAt(11 + i1);
                            String s23 = (String)rs.elementAt(12 + i1);
                            String s27 = (String)rs.elementAt(13 + i1);
                            String s29 = s21;
                            Integer integer3 = (Integer)rs.elementAt(14 + i1);
                            if(s27.compareTo(s24) != 0 || s29.compareTo(s25) != 0)
                            {
                                if(s24.length() > 0)
                                {
                                    printwriter.println("</table>");
                                    printwriter.println("<BR>");
                                }
                                printwriter.println("<table border='1' width='90%' align='center'>");
                                printwriter.println("<tr><td class='texttituloarea' align='left' colspan='5'>" + s27 + " / " + s29 + "</td></tr>");
                                printwriter.println("<tr>");
                                printwriter.println("<td class='texttitulotabla' align='center' width='8%'>Tipo</td>");
                                printwriter.println("<td class='texttitulotabla' align='center' width='35%'>Archivo</td>");
                                printwriter.println("<td class='texttitulotabla' align='center' width='25%'>Descripci\363n</td>");
                                printwriter.println("<td class='texttitulotabla' align='center' width='20%'>Usuario</td>");
                                printwriter.println("<td class='texttitulotabla' align='center' width='12%'>Fecha Publicaci\363n</td>");
                                printwriter.println("</tr>");
                                s24 = s27;
                                s25 = s29;
                            }
                            printwriter.println("<tr>");
                            if(AFunc.ExisteArchivo(sPath + "\\images\\ext_" + s17 + ".gif") == 1)
                                printwriter.println("<td class='textdesttabla'> <a href='verarchivo.jsp?TIPO=P&ID=" + integer5.toString() + "&PERMISO=" + s13 + "','datos'><IMG src=../images/ext_" + s17 + ".gif width=32 height=32 border=0></a> </td>");
                            else
                                printwriter.println("<td class='textdesttabla'> <a href='verarchivo.jsp?TIPO=P&ID=" + integer5.toString() + "&PERMISO=" + s13 + "','datos'><IMG src=../images/extdefault.gif width=32 height=32 border=0></a> </td>");
                            printwriter.println("<td class='textdesttabla'> " + s15 + "</td>");
                            if(s19.length() > 0)
                                printwriter.println("<td class='textdesttabla'>" + s19 + "</td>");
                            else
                                printwriter.println("<td class='textdesttabla'>&nbsp;</td>");
                            printwriter.println("<td class='textdesttabla'>" + s23 + "</td>");
                            printwriter.println("<td class='textdesttabla'>" + AFunc.ConstruyeFecha(integer3.toString(), "/", "dmy") + "</td>");
                            printwriter.println("</tr>");
                            l++;
                        }

                        printwriter.println("</table>");
                    } else
                    {
                        printwriter.println("<table border='1' width='90%' align='center'>");
                        printwriter.println("<tr>");
                        printwriter.println("<td class='texttitulotabla' align='center'>No hay documentos disponibles</td>");
                        printwriter.println("</tr>");
                        printwriter.println("</table>");
                    }
                }
            } else
            {
                printwriter.println("<table border='1' width='90%'  align='center'>");
                printwriter.println("<tr><td class='texttitulotabla' align='center'>Permisos</td></tr>");
                printwriter.println("<tr><td class='textdesttabla' align='center'>No tiene permisos para acceder a los archivos que solicita.</td></tr>");
                printwriter.println("</table>");
            }
        } else
        {
            printwriter.println("<table border='1' width='90%'  align='center'>");
            printwriter.println("<tr><td class='texttitulotabla' align='center'>Permisos (Proyecto)</td></tr>");
            printwriter.println("<tr><td class='textdesttabla' align='center'>No tiene permisos para acceder a los archivos que solicita.</td></tr>");
            printwriter.println("</table>");
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
    String Tipo;
    String SubTipo;
    String Proceso;
    String TipoDoc;
    String Cliente;
    String Proyecto;
    String IdDoc;
    String UserReg;
    String GlosaProy;
    String sPath;
    private String rutUsuario;
}