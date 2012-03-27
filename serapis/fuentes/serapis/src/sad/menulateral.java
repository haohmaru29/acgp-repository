// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   menulateral.java

package sad;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class menulateral extends HttpServlet
{

    public menulateral()
    {
        ADatos = new AccDataBase();
        ADatosD = new AccDataBase();
        AFunc = new funciones();
        Tipo = "";
        SubTipo = "";
        Proceso = "";
        TipoDoc = "";
        Cliente = "";
        Proyecto = "";
        IdID = "";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s = (String)httpsession.getValue("SerapisUser");
        String s1 = (String)httpsession.getValue("SerapisGlosa");
        if(s1 != null && s1.length() > 0)
            s1 = AFunc.desencripta(s1);
        else
            s1 = "Proyecto";
        if(s != null && s.length() > 0)
        {
            ADatos.connect();
            Tipo = httpservletrequest.getParameter("TIPO");
            SubTipo = httpservletrequest.getParameter("SUBTIPO");
            Proceso = httpservletrequest.getParameter("PROCESO");
            TipoDoc = httpservletrequest.getParameter("TIPODOC");
            Cliente = httpservletrequest.getParameter("CLI");
            Proyecto = httpservletrequest.getParameter("PROY");
            IdID = httpservletrequest.getParameter("ID");
            if(Tipo == null)
                Tipo = "";
            if(SubTipo == null)
                SubTipo = "A";
            if(Proceso == null)
                Proceso = "";
            if(TipoDoc == null)
                TipoDoc = "";
            if(Cliente == null)
                Cliente = "";
            if(Proyecto == null)
                Proyecto = "";
            if(IdID == null)
                IdID = "0";
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<BODY bgcolor='#FAE5B9' leftmargin='0' topmargin='0'>");
            printwriter.println("<table cellpadding='0' cellspacing='0' border='0' width='100%'>");
            printwriter.println("<tr class='fondogris'>");
            printwriter.println("<td height='28px' class='fondogris' valign='middle'><img src='../images/menu_sad.jpg' widht='207' height='28'></td>");
            printwriter.println("</tr>");
            printwriter.println("</table>");
            printwriter.println("<table border='0' width='100%' cellspacing='0' cellpadding='0'>");
            EscribeOpcion(printwriter, -1, "", "", "N");
            if(Tipo.compareTo("DOC") == 0 || Tipo.compareTo("RWF") == 0 || Tipo.compareTo("RRE") == 0 || Tipo.compareTo("PUB") == 0)
                EscribeOpcion(printwriter, 1, "Estado Registros", "<a href='inicio.jsp?TIPO=DOC' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 1, "Estado Registros", "<a href='inicio.jsp?TIPO=DOC' target = 'cuerpo'>", "N");
            if(Tipo.compareTo("DOC") == 0)
            {
                EscribeOpcion(printwriter, 2, "Registros Aprobados", "<a href='inicio.jsp?TIPO=DOC' target = 'cuerpo'>", "S");
                if(SubTipo.compareTo("A") == 0)
                {
                    EscribeOpcion(printwriter, 3, "Proceso", "<a href='inicio.jsp?TIPO=DOC&SUBTIPO=A' target = 'cuerpo'>", "S");
                    ObtieneProcesos1(printwriter);
                    EscribeOpcion(printwriter, 3, "Proyecto", "<a href='inicio.jsp?TIPO=DOC&SUBTIPO=P' target = 'cuerpo'>", "N");
                } else
                if(SubTipo.compareTo("P") == 0)
                {
                    EscribeOpcion(printwriter, 3, "Proceso", "<a href='inicio.jsp?TIPO=DOC&SUBTIPO=A' target = 'cuerpo'>", "N");
                    EscribeOpcion(printwriter, 3, "Proyecto", "<a href='inicio.jsp?TIPO=DOC&SUBTIPO=P' target = 'cuerpo'>", "S");
                    ObtieneClientes1(printwriter);
                } else
                {
                    EscribeOpcion(printwriter, 3, "Proceso", "<a href='inicio.jsp?TIPO=DOC&SUBTIPO=A' target = 'cuerpo'>", "S");
                    ObtieneProcesos1(printwriter);
                    EscribeOpcion(printwriter, 3, "Proyecto", "<a href='inicio.jsp?TIPO=DOC&SUBTIPO=P' target = 'cuerpo'>", "N");
                }
            } else
            {
                EscribeOpcion(printwriter, 2, "Registros Aprobados", "<a href='inicio.jsp?TIPO=DOC' target = 'cuerpo'>", "N");
            }
            if(Tipo.compareTo("RWF") == 0)
                EscribeOpcion(printwriter, 2, "Registros Flujo", "<a href='inicio.jsp?TIPO=RWF' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 2, "Registros Flujo", "<a href='inicio.jsp?TIPO=RWF' target = 'cuerpo'>", "N");
            if(Tipo.compareTo("RRE") == 0)
                EscribeOpcion(printwriter, 2, "Registros Rechazados", "<a href='inicio.jsp?TIPO=RRE' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 2, "Registros Rechazados", "<a href='inicio.jsp?TIPO=RRE' target = 'cuerpo'>", "N");
            if(Tipo.compareTo("PUB") == 0)
            {
                EscribeOpcion(printwriter, 2, "Publicar", "<a href='inicio.jsp?TIPO=PUB' target = 'cuerpo'>", "S");
                if(SubTipo.compareTo("A") == 0)
                {
                    EscribeOpcion(printwriter, 3, "Proceso", "<a href='inicio.jsp?TIPO=PUB&SUBTIPO=A' target = 'cuerpo'>", "S");
                    EscribeOpcion(printwriter, 3, s1, "<a href='inicio.jsp?TIPO=PUB&SUBTIPO=P' target = 'cuerpo'>", "N");
                } else
                {
                    EscribeOpcion(printwriter, 3, "Proceso", "<a href='inicio.jsp?TIPO=PUB&SUBTIPO=A' target = 'cuerpo'>", "N");
                    EscribeOpcion(printwriter, 3, s1, "<a href='inicio.jsp?TIPO=PUB&SUBTIPO=P' target = 'cuerpo'>", "S");
                }
            } else
            {
                EscribeOpcion(printwriter, 2, "Publicar", "<a href='inicio.jsp?TIPO=PUB' target = 'cuerpo'>", "N");
            }
            if(Tipo.compareTo("CLI") == 0)
                EscribeOpcion(printwriter, 1, "Clientes", "<a href='inicio.jsp?TIPO=CLI' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 1, "Clientes", "<a href='inicio.jsp?TIPO=CLI' target = 'cuerpo'>", "N");
            if(Tipo.compareTo("ENC") == 0)
                EscribeOpcion(printwriter, 1, "Responsables", "<a href='inicio.jsp?TIPO=ENC' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 1, "Responsables", "<a href='inicio.jsp?TIPO=ENC' target = 'cuerpo'>", "N");
            if(Tipo.compareTo("CARO") == 0)
                EscribeOpcion(printwriter, 1, "Cargos y Roles", "<a href='inicio.jsp?TIPO=CARO' target = 'cuerpo'>", "S");
            else
                EscribeOpcion(printwriter, 1, "Cargos y Roles", "<a href='inicio.jsp?TIPO=CARO' target = 'cuerpo'>", "N");
            if(Tipo.compareTo("PERM") == 0)
            {
                EscribeOpcion(printwriter, 1, "Permisos", "<a href='inicio.jsp?TIPO=PERM' target = '_parent'>", "S");
                if(SubTipo.compareTo("P") == 0)
                {
                    EscribeOpcion(printwriter, 2, "Proceso", "<a href='inicio.jsp?TIPO=PERM&SUBTIPO=P' target = '_parent'>", "S");
                    EscribeOpcion(printwriter, 2, "Proyecto", "<a href='inicio.jsp?TIPO=PERM&SUBTIPO=Y' target = '_parent'>", "N");
                } else
                if(SubTipo.compareTo("Y") == 0)
                {
                    EscribeOpcion(printwriter, 2, "Proceso", "<a href='inicio.jsp?TIPO=PERM&SUBTIPO=P' target = '_parent'>", "N");
                    EscribeOpcion(printwriter, 2, "Proyecto", "<a href='inicio.jsp?TIPO=PERM&SUBTIPO=Y' target = '_parent'>", "S");
                } else
                {
                    EscribeOpcion(printwriter, 2, "Proceso", "<a href='inicio.jsp?TIPO=PERM&SUBTIPO=P' target = '_parent'>", "S");
                    EscribeOpcion(printwriter, 2, "Proyecto", "<a href='inicio.jsp?TIPO=PERM&SUBTIPO=Y' target = '_parent'>", "N");
                }
            } else
            {
                EscribeOpcion(printwriter, 1, "Permisos", "<a href='inicio.jsp?TIPO=PERM' target = '_parent'>", "N");
            }
            if(Tipo.compareTo("PERF") == 0)
                EscribeOpcion(printwriter, 1, "Usuarios por Perfil", "<a href='inicio.jsp?TIPO=PERF' target = '_parent'>", "S");
            else
                EscribeOpcion(printwriter, 1, "Usuarios por Perfil", "<a href='inicio.jsp?TIPO=PERF' target = '_parent'>", "N");
            printwriter.println("</table>");
            printwriter.println("</table>");
            printwriter.println("</body>");
            printwriter.println("</html>");
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "SAD", 6);
        }
    }

    void ObtieneTipoDocumentosProyecto(String s, String s1, String s2, String s3, PrintWriter printwriter)
    {
        rs = new Vector();
        String s4 = "";
        Integer integer = new Integer(0);
        String s5 = "";
        ADatos.connect();
        s4 = "select distinct dc.id, dc.descripcion from sad.documentosp d, gdc.documentoscalidad dc ";
        s4 = s4 + " where dc.tipodocumento = d.tipodoc and dc.proceso = d.proceso and d.descripcion = dc.descripcion";
        s4 = s4 + " and d.cliente = '" + s + "' and d.proyecto = '" + s1 + "' and d.proceso = '" + s2 + "' and d.tipodoc = '" + s3 + "'";
        int i = ADatos.ConsultaDB(s4);
        rs = ADatos.getResult();
        for(int j = 0; j < rs.size(); j += 2)
        {
            Integer integer1 = (Integer)rs.elementAt(j);
            String s6 = (String)rs.elementAt(j + 1);
            printwriter.println("<tr><td bgcolor='#FAE5B9' colspan='6'>&nbsp;</td>");
            printwriter.println("<td bgcolor='#FAE5B9'  width='3%' valign='top'><a href='muestradoc.jsp?TIPO=DOC&SUBTIPO=P&CLI=" + Cliente + "&PROY=" + Proyecto + "&PROCESO=" + s2 + "&TIPODOC=" + s3 + "&ID=" + integer1 + "' target = 'datos'><IMG src='../images/folderterminal.gif' border=0 width=17 height=13></a></td>");
            printwriter.println("<td bgcolor='#FAE5B9' valign='top'><a href='muestradoc.jsp?TIPO=DOC&SUBTIPO=P&CLI=" + Cliente + "&PROY=" + Proyecto + "&PROCESO=" + s2 + "&TIPODOC=" + s3 + "&ID=" + integer1 + "' target = 'datos'>" + s6 + "</a></td></tr>");
        }

    }

    void ObtieneTipoDocumentosProyecto1(String s, String s1, String s2, String s3, PrintWriter printwriter)
    {
        rs = new Vector();
        String s4 = "";
        Integer integer = new Integer(0);
        Integer integer2 = new Integer(0);
        String s5 = "";
        integer2 = new Integer(IdID);
        ADatos.connect();
        s4 = "select distinct dc.id, dc.descripcion from sad.documentosp d, gdc.documentoscalidad dc ";
        s4 = s4 + " where dc.tipodocumento = d.tipodoc and dc.proceso = d.proceso and d.descripcion = dc.descripcion";
        s4 = s4 + " and d.cliente = '" + s + "' and d.proyecto = '" + s1 + "' and d.proceso = '" + s2 + "' and d.tipodoc = '" + s3 + "'";
        int i = ADatos.ConsultaDB(s4);
        rs = ADatos.getResult();
        for(int j = 0; j < rs.size(); j += 2)
        {
            Integer integer1 = (Integer)rs.elementAt(j);
            String s6 = (String)rs.elementAt(j + 1);
            printwriter.println("<tr><td bgcolor='#FAE5B9' colspan='8'>&nbsp;</td>");
            printwriter.println("<td bgcolor='#FAE5B9'  width='1%' valign='top'><a href='muestradoc.jsp?TIPO=DOC&SUBTIPO=P&CLI=" + Cliente + "&PROY=" + Proyecto + "&PROCESO=" + s2 + "&TIPODOC=" + s3 + "&ID=" + integer1 + "' target = 'datos'><IMG src='../images/folderterminal.gif' border=0 width=17 height=13></a></td>");
            printwriter.println("<td bgcolor='#FAE5B9' valign='top'><a href='muestradoc.jsp?TIPO=DOC&SUBTIPO=P&CLI=" + Cliente + "&PROY=" + Proyecto + "&PROCESO=" + s2 + "&TIPODOC=" + s3 + "&ID=" + integer1 + "' target = 'datos'>" + s6 + "</a></td></tr>");
        }

    }

    void ObtieneSubProcesosProyectos(String s, String s1, String s2, PrintWriter printwriter)
    {
        Vector vector = new Vector();
        rs = new Vector();
        String s3 = "";
        String s5 = "";
        int i = ADatos.ConsultaDB("select distinct td.sigla,td.descripcion from sad.documentosp d, gdc.tipodocumentos td where d.tipodoc = td.sigla and d.cliente = '" + s + "' and d.proyecto = '" + s1 + "' and d.proceso = '" + s2 + "'");
        rs = ADatos.getResult();
        vector = (Vector)rs.clone();
        for(int j = 0; j < vector.size(); j += 2)
        {
            String s4 = (String)vector.elementAt(j);
            String s6 = (String)vector.elementAt(j + 1);
            printwriter.println("<tr><td bgcolor='#FAE5B9' colspan='5'>&nbsp;</td>");
            if(s4.compareTo(TipoDoc) == 0)
            {
                printwriter.println("<td bgcolor='#FAE5B9'  width='3%'><a href='inicio.jsp?TIPO=DOC&SUBTIPO=P&CLI=" + Cliente + "&PROY=" + Proyecto + "&PROCESO=" + s2 + "&TIPODOC=" + s4 + "' target = '_parent'><IMG src='../images/folderabierto.gif' border=0 width=17 height=13></a></td>");
                printwriter.println("<td bgcolor='#FAE5B9' valign='top' colspan='2'><a href='inicio.jsp?TIPO=DOC&SUBTIPO=P&CLI=" + Cliente + "&PROY=" + Proyecto + "&PROCESO=" + s2 + "&TIPODOC=" + s4 + "' target = '_parent'>" + s6 + "</a></td></tr>");
                ObtieneTipoDocumentosProyecto(s, s1, s2, s4, printwriter);
            } else
            {
                printwriter.println("<td bgcolor='#FAE5B9'  width='3%'><a href='inicio.jsp?TIPO=DOC&SUBTIPO=P&CLI=" + Cliente + "&PROY=" + Proyecto + "&PROCESO=" + s2 + "&TIPODOC=" + s4 + "' target = '_parent'><IMG src='../images/foldercerrado.gif' border=0 width=17 height=13></a></td>");
                printwriter.println("<td bgcolor='#FAE5B9' valign='top' colspan='3'><a href='inicio.jsp?TIPO=DOC&SUBTIPO=P&CLI=" + Cliente + "&PROY=" + Proyecto + "&PROCESO=" + s2 + "&TIPODOC=" + s4 + "' target = '_parent'>" + s6 + "</a></td></tr>");
            }
        }

    }

    void ObtieneSubProcesosProyectos1(String s, String s1, String s2, PrintWriter printwriter)
    {
        Vector vector = new Vector();
        rs = new Vector();
        String s3 = "";
        String s5 = "";
        int i = ADatos.ConsultaDB("select distinct td.sigla,td.descripcion from sad.documentosp d, gdc.tipodocumentos td where d.tipodoc = td.sigla and d.cliente = '" + s + "' and d.proyecto = '" + s1 + "' and d.proceso = '" + s2 + "'");
        rs = ADatos.getResult();
        vector = (Vector)rs.clone();
        for(int j = 0; j < vector.size(); j += 2)
        {
            String s4 = (String)vector.elementAt(j);
            String s6 = (String)vector.elementAt(j + 1);
            if(s4.compareTo(TipoDoc) == 0)
            {
                EscribeOpcion(printwriter, 7, s6, "<a href='inicio.jsp?TIPO=DOC&SUBTIPO=P&CLI=" + Cliente + "&PROY=" + Proyecto + "&PROCESO=" + s2 + "&TIPODOC=" + s4 + "' target = '_parent'>", "S");
                ObtieneTipoDocumentosProyecto1(s, s1, s2, s4, printwriter);
            } else
            {
                EscribeOpcion(printwriter, 7, s6, "<a href='inicio.jsp?TIPO=DOC&SUBTIPO=P&CLI=" + Cliente + "&PROY=" + Proyecto + "&PROCESO=" + s2 + "&TIPODOC=" + s4 + "' target = '_parent'>", "N");
            }
        }

    }

    void ObtieneProcesosProyectos(String s, String s1, PrintWriter printwriter)
    {
        Vector vector = new Vector();
        rs = new Vector();
        String s2 = "";
        String s4 = "";
        int i = ADatos.ConsultaDB("select distinct p.sigla, p.descripcion from sad.documentosp d, gdc.procesos p where d.proceso = p.sigla and d.cliente = '" + s + "' and d.proyecto = '" + s1 + "'");
        rs = ADatos.getResult();
        vector = (Vector)rs.clone();
        for(int j = 0; j < vector.size(); j += 2)
        {
            String s3 = (String)vector.elementAt(j);
            String s5 = (String)vector.elementAt(j + 1);
            printwriter.println("<tr><td bgcolor='#FAE5B9' colspan='4'>&nbsp;</td>");
            if(s3.compareTo(Proceso) == 0)
            {
                printwriter.println("<td bgcolor='#FAE5B9'  width='3%'><a href='inicio.jsp?TIPO=DOC&SUBTIPO=P&CLI=" + Cliente + "&PROY=" + Proyecto + "&PROCESO=" + s3 + "' target = '_parent'><IMG src='../images/folderabierto.gif' border=0 width=17 height=13></a></td>");
                printwriter.println("<td bgcolor='#FAE5B9' valign='top' colspan='3'><a href='inicio.jsp?TIPO=DOC&SUBTIPO=P&CLI=" + Cliente + "&PROY=" + Proyecto + "&PROCESO=" + s3 + "' target = '_parent'>" + s5 + "</a></td></tr>");
                ObtieneSubProcesosProyectos(s, s1, s3, printwriter);
            } else
            {
                printwriter.println("<td bgcolor='#FAE5B9'  width='3%'><a href='inicio.jsp?TIPO=DOC&SUBTIPO=P&CLI=" + Cliente + "&PROY=" + Proyecto + "&PROCESO=" + s3 + "' target = '_parent'><IMG src='../images/foldercerrado.gif' border=0 width=17 height=13></a></td>");
                printwriter.println("<td bgcolor='#FAE5B9' valign='top' colspan='3'><a href='inicio.jsp?TIPO=DOC&SUBTIPO=P&CLI=" + Cliente + "&PROY=" + Proyecto + "&PROCESO=" + s3 + "' target = '_parent'>" + s5 + "</a></td></tr>");
            }
        }

    }

    void ObtieneProcesosProyectos1(String s, String s1, PrintWriter printwriter)
    {
        Vector vector = new Vector();
        rs = new Vector();
        String s2 = "";
        String s4 = "";
        int i = ADatos.ConsultaDB("select distinct p.sigla, p.descripcion from sad.documentosp d, gdc.procesos p where d.proceso = p.sigla and d.cliente = '" + s + "' and d.proyecto = '" + s1 + "'");
        rs = ADatos.getResult();
        vector = (Vector)rs.clone();
        for(int j = 0; j < vector.size(); j += 2)
        {
            String s3 = (String)vector.elementAt(j);
            String s5 = (String)vector.elementAt(j + 1);
            if(s3.compareTo(Proceso) == 0)
            {
                EscribeOpcion(printwriter, 6, s5, "<a href='inicio.jsp?TIPO=DOC&SUBTIPO=P&CLI=" + Cliente + "&PROY=" + Proyecto + "&PROCESO=" + s3 + "' target = '_parent'>", "S");
                ObtieneSubProcesosProyectos1(s, s1, s3, printwriter);
            } else
            {
                EscribeOpcion(printwriter, 6, s5, "<a href='inicio.jsp?TIPO=DOC&SUBTIPO=P&CLI=" + Cliente + "&PROY=" + Proyecto + "&PROCESO=" + s3 + "' target = '_parent'>", "N");
            }
        }

    }

    void ObtieneProyectos(String s, PrintWriter printwriter)
    {
        rs = new Vector();
        Vector vector = new Vector();
        String s1 = "";
        String s3 = "";
        ADatos.connect();
        int i = ADatos.ConsultaDB("select p.abreviatura, p.proyecto from gdc.proyectos p, sgc.clientes c where c.rutcliente = p.cliente and c.abreviatura='" + s + "'");
        rs = ADatos.getResult();
        vector = (Vector)rs.clone();
        for(int j = 0; j < vector.size(); j += 2)
        {
            String s2 = (String)vector.elementAt(j);
            String s4 = (String)vector.elementAt(j + 1);
            printwriter.println("<tr><td bgcolor='#FAE5B9' colspan='3'>&nbsp;</td>");
            if(Proyecto.compareTo(s2) == 0)
            {
                printwriter.println("<td bgcolor='#FAE5B9'  width='3%' valign='top'><a href='inicio.jsp?TIPO=DOC&SUBTIPO=P&CLI=" + s + "&PROY=" + s2 + "' target = '_parent'><IMG src='../images/folderabierto.gif' border=0 width=17 height=13></a></td>");
                printwriter.println("<td bgcolor='#FAE5B9' valign='top' colspan='4'><a href='inicio.jsp?TIPO=DOC&SUBTIPO=P&CLI=" + s + "&PROY=" + s2 + "' target = '_parent'>" + s4 + "</a></td></tr>");
                ObtieneProcesosProyectos(s, s2, printwriter);
            } else
            {
                printwriter.println("<td bgcolor='#FAE5B9'  width='3%' valign='top'><a href='inicio.jsp?TIPO=DOC&SUBTIPO=P&CLI=" + s + "&PROY=" + s2 + "' target = '_parent'><IMG src='../images/foldercerrado.gif' border=0 width=17 height=13></a></td>");
                printwriter.println("<td bgcolor='#FAE5B9' valign='top' colspan='4'><a href='inicio.jsp?TIPO=DOC&SUBTIPO=P&CLI=" + s + "&PROY=" + s2 + "' target = '_parent'>" + s4 + "</a></td></tr>");
            }
        }

    }

    void ObtieneProyectos1(String s, PrintWriter printwriter)
    {
        rs = new Vector();
        Vector vector = new Vector();
        String s1 = "";
        String s3 = "";
        ADatos.connect();
        int i = ADatos.ConsultaDB("select p.abreviatura, p.proyecto from gdc.proyectos p, sgc.clientes c where c.rutcliente = p.cliente and c.abreviatura='" + s + "'");
        rs = ADatos.getResult();
        vector = (Vector)rs.clone();
        for(int j = 0; j < vector.size(); j += 2)
        {
            String s2 = (String)vector.elementAt(j);
            String s4 = (String)vector.elementAt(j + 1);
            if(Proyecto.compareTo(s2) == 0)
            {
                EscribeOpcion(printwriter, 5, s4, "<a href='inicio.jsp?TIPO=DOC&SUBTIPO=P&CLI=" + s + "&PROY=" + s2 + "' target = '_parent'>", "S");
                ObtieneProcesosProyectos1(s, s2, printwriter);
            } else
            {
                EscribeOpcion(printwriter, 5, s4, "<a href='inicio.jsp?TIPO=DOC&SUBTIPO=P&CLI=" + s + "&PROY=" + s2 + "' target = '_parent'>", "N");
            }
        }

    }

    void ObtieneClientes1(PrintWriter printwriter)
    {
        Vector vector = new Vector();
        rs = new Vector();
        String s = "";
        String s2 = "";
        int i = ADatos.ConsultaDB("select distinct c.razonsocial,c.abreviatura from sgc.clientes c, gdc.proyectos p where c.rutcliente = p.cliente");
        rs = ADatos.getResult();
        vector = (Vector)rs.clone();
        for(int j = 0; j < vector.size(); j += 2)
        {
            String s1 = (String)vector.elementAt(j);
            String s3 = (String)vector.elementAt(j + 1);
            if(s3.compareTo(Cliente) == 0)
            {
                EscribeOpcion(printwriter, 4, s1, "<a href='inicio.jsp?TIPO=DOC&SUBTIPO=P&CLI=" + s3 + "' target = '_parent'>", "S");
                ObtieneProyectos1(s3, printwriter);
            } else
            {
                EscribeOpcion(printwriter, 4, s1, "<a href='inicio.jsp?TIPO=DOC&SUBTIPO=P&CLI=" + s3 + "' target = '_parent'>", "N");
            }
        }

    }

    void ObtieneClientes(PrintWriter printwriter)
    {
        Vector vector = new Vector();
        rs = new Vector();
        String s = "";
        String s2 = "";
        int i = ADatos.ConsultaDB("select distinct c.razonsocial,c.abreviatura from sgc.clientes c, gdc.proyectos p where c.rutcliente = p.cliente");
        rs = ADatos.getResult();
        vector = (Vector)rs.clone();
        for(int j = 0; j < vector.size(); j += 2)
        {
            String s1 = (String)vector.elementAt(j);
            String s3 = (String)vector.elementAt(j + 1);
            printwriter.println("<tr><td bgcolor='#FAE5B9' colspan='2'>&nbsp;</td>");
            if(s3.compareTo(Cliente) == 0)
            {
                printwriter.println("<td bgcolor='#FAE5B9'  width='3%'><a href='inicio.jsp?TIPO=DOC&SUBTIPO=P&CLI=" + s3 + "' target = '_parent'><IMG src='../images/folderabierto.gif' border=0 width=17 height=13></a></td>");
                printwriter.println("<td bgcolor='#FAE5B9' valign='top' colspan='5'><a href='inicio.jsp?TIPO=DOC&SUBTIPO=P&CLI=" + s3 + "' target = '_parent'>" + s1 + "</a></td></tr>");
                ObtieneProyectos(s3, printwriter);
            } else
            {
                printwriter.println("<td bgcolor='#FAE5B9'  width='3%'><a href='inicio.jsp?TIPO=DOC&SUBTIPO=P&CLI=" + s3 + "' target = '_parent'><IMG src='../images/foldercerrado.gif' border=0 width=17 height=13></a></td>");
                printwriter.println("<td bgcolor='#FAE5B9' valign='top' colspan='5'><a href='inicio.jsp?TIPO=DOC&SUBTIPO=P&CLI=" + s3 + "' target = '_parent'>" + s1 + "</a></td></tr>");
            }
        }

    }

    void ObtieneTipoDocumentos1(String s, String s1, PrintWriter printwriter)
    {
        rs = new Vector();
        ADatos.connect();
        int i = ADatos.ConsultaDB("select id,descripcion from gdc.documentoscalidad where tipodocumento='" + s1 + "' and proceso='" + s + "' and UPPER(LTrim(RTrim(tipo))) ='R' order by descripcion");
        rs = ADatos.getResult();
        for(int j = 0; j < rs.size(); j += 2)
        {
            printwriter.println("<tr><td bgcolor='#FAE5B9' colspan='6'>&nbsp;</td>");
            printwriter.println("<td bgcolor='#FAE5B9'  width='1%' valign='top'><a href='muestradoc.jsp?ID=" + (Integer)rs.elementAt(j) + "' target = 'datos'><IMG src='../images/folderterminal.gif' border=0 width=17 height=13></a></td>");
            printwriter.println("<td bgcolor='#FAE5B9' valign='top' colspan='3'><a href='muestradoc.jsp?ID=" + (Integer)rs.elementAt(j) + "' target = 'datos'>" + (String)rs.elementAt(1 + j) + "</a></td></tr>");
        }

    }

    void ObtieneTipoDocumentos(String s, String s1, PrintWriter printwriter)
    {
        rs = new Vector();
        ADatos.connect();
        int i = ADatos.ConsultaDB("select id,descripcion from gdc.documentoscalidad where tipodocumento='" + s1 + "' and proceso='" + s + "' order by descripcion");
        rs = ADatos.getResult();
        for(int j = 0; j < rs.size(); j += 2)
        {
            printwriter.println("<tr><td bgcolor='#FAE5B9' colspan='4'>&nbsp;</td>");
            printwriter.println("<td bgcolor='#FAE5B9'  width='3%' valign='top'><a href='muestradoc.jsp?ID=" + (Integer)rs.elementAt(j) + "' target = 'datos'><IMG src='../images/folderterminal.gif' border=0 width=17 height=13></a></td>");
            printwriter.println("<td bgcolor='#FAE5B9' valign='top' colspan='3'><a href='muestradoc.jsp?ID=" + (Integer)rs.elementAt(j) + "' target = 'datos'>" + (String)rs.elementAt(1 + j) + "</a></td></tr>");
        }

    }

    void ObtieneSubProcesos(String s, PrintWriter printwriter)
    {
        rs = new Vector();
        Vector vector = new Vector();
        String s1 = "";
        String s3 = "";
        ADatos.connect();
        int i = ADatos.ConsultaDB("select distinct d.tipodoc, td.descripcion from sad.documentos d, gdc.tipodocumentos td  where d.tipodoc = td.sigla and d.proceso='" + s + "' ");
        rs = ADatos.getResult();
        vector = (Vector)rs.clone();
        for(int j = 0; j < vector.size(); j += 2)
        {
            String s2 = (String)vector.elementAt(j);
            String s4 = (String)vector.elementAt(j + 1);
            printwriter.println("<tr><td bgcolor='#FAE5B9' colspan='3'>&nbsp;</td>");
            if(TipoDoc.compareTo(s2) == 0)
            {
                printwriter.println("<td bgcolor='#FAE5B9'  width='3%' valign='top'><a href='inicio.jsp?TIPO=" + Tipo + "&SUBTIPO=" + SubTipo + "&PROCESO=" + s2 + "&TIPODOC=" + s2 + "' target = '_parent'><IMG src='../images/folderabierto.gif' border=0 width=17 height=13></a></td>");
                printwriter.println("<td bgcolor='#FAE5B9' valign='top' colspan='4'><a href='inicio.jsp?TIPO=" + Tipo + "&SUBTIPO=" + SubTipo + "&PROCESO=" + s + "&TIPODOC=" + s2 + "' target = '_parent'>" + s4 + "</a></td></tr>");
                ObtieneTipoDocumentos(s, TipoDoc, printwriter);
            } else
            {
                printwriter.println("<td bgcolor='#FAE5B9'  width='3%' valign='top'><a href='inicio.jsp?TIPO=" + Tipo + "&SUBTIPO=" + SubTipo + "&PROCESO=" + s2 + "&TIPODOC=" + s2 + "' target = '_parent'><IMG src='../images/foldercerrado.gif' border=0 width=17 height=13></a></td>");
                printwriter.println("<td bgcolor='#FAE5B9' valign='top' colspan='4'><a href='inicio.jsp?TIPO=" + Tipo + "&SUBTIPO=" + SubTipo + "&PROCESO=" + s + "&TIPODOC=" + s2 + "' target = '_parent'>" + s4 + "</a></td></tr>");
            }
        }

    }

    void ObtieneSubProcesos1(String s, PrintWriter printwriter)
    {
        rs = new Vector();
        Vector vector = new Vector();
        String s1 = "";
        String s3 = "";
        ADatos.connect();
        int i = ADatos.ConsultaDB("select distinct d.tipodoc, td.descripcion from sad.documentos d, gdc.tipodocumentos td  where d.tipodoc = td.sigla and d.proceso='" + s + "' ");
        rs = ADatos.getResult();
        vector = (Vector)rs.clone();
        for(int j = 0; j < vector.size(); j += 2)
        {
            String s2 = (String)vector.elementAt(j);
            String s4 = (String)vector.elementAt(j + 1);
            if(TipoDoc.compareTo(s2) == 0)
            {
                EscribeOpcion(printwriter, 5, s4, "<a href='inicio.jsp?TIPO=" + Tipo + "&SUBTIPO=" + SubTipo + "&PROCESO=" + s + "&TIPODOC=" + s2 + "' target = '_parent'>", "S");
                ObtieneTipoDocumentos1(s, TipoDoc, printwriter);
            } else
            {
                EscribeOpcion(printwriter, 5, s4, "<a href='inicio.jsp?TIPO=" + Tipo + "&SUBTIPO=" + SubTipo + "&PROCESO=" + s + "&TIPODOC=" + s2 + "' target = '_parent'>", "N");
            }
        }

    }

    void ObtieneProcesos1(PrintWriter printwriter)
    {
        Vector vector = new Vector();
        rs = new Vector();
        String s = "";
        String s2 = "";
        int i = ADatos.ConsultaDB("select sigla,descripcion from gdc.procesos order by descripcion");
        rs = ADatos.getResult();
        vector = (Vector)rs.clone();
        for(int j = 0; j < vector.size(); j += 2)
        {
            String s1 = (String)vector.elementAt(j);
            String s3 = (String)vector.elementAt(j + 1);
            if(s1.compareTo(Proceso) == 0)
            {
                EscribeOpcion(printwriter, 4, s3, "<a href='inicio.jsp?TIPO=DOC&SUBTIPO=A&PROCESO=" + s1 + "' target = 'cuerpo'>", "S");
                ObtieneSubProcesos1(Proceso, printwriter);
            } else
            {
                EscribeOpcion(printwriter, 4, s3, "<a href='inicio.jsp?TIPO=DOC&SUBTIPO=A&PROCESO=" + s1 + "' target = 'cuerpo'>", "N");
            }
        }

    }

    void ObtieneProcesos(PrintWriter printwriter)
    {
        Vector vector = new Vector();
        rs = new Vector();
        String s = "";
        String s2 = "";
        int i = ADatos.ConsultaDB("select sigla,descripcion from gdc.procesos order by descripcion");
        rs = ADatos.getResult();
        vector = (Vector)rs.clone();
        for(int j = 0; j < vector.size(); j += 2)
        {
            String s1 = (String)vector.elementAt(j);
            String s3 = (String)vector.elementAt(j + 1);
            printwriter.println("<tr><td bgcolor='#FAE5B9' colspan='2'>&nbsp;</td>");
            if(s1.compareTo(Proceso) == 0)
            {
                printwriter.println("<td bgcolor='#FAE5B9'  width='3%'><a href='inicio.jsp?TIPO=" + Tipo + "&SUBTIPO=" + SubTipo + "&PROCESO=" + s1 + "' target = '_parent'><IMG src='../images/folderabierto.gif' border=0 width=17 height=13></a></td>");
                printwriter.println("<td bgcolor='#FAE5B9' valign='top' colspan='5'><a href='inicio.jsp?TIPO=" + Tipo + "&SUBTIPO=" + SubTipo + "&PROCESO=" + s1 + "' target = '_parent'>" + s3 + "</a></td></tr>");
                ObtieneSubProcesos(Proceso, printwriter);
            } else
            {
                printwriter.println("<td bgcolor='#FAE5B9'  width='3%'><a href='inicio.jsp?TIPO=" + Tipo + "&SUBTIPO=" + SubTipo + "&PROCESO=" + s1 + "' target = '_parent'><IMG src='../images/foldercerrado.gif' border=0 width=17 height=13></a></td>");
                printwriter.println("<td bgcolor='#FAE5B9' valign='top' colspan='5'><a href='inicio.jsp?TIPO=" + Tipo + "&SUBTIPO=" + SubTipo + "&PROCESO=" + s1 + "' target = '_parent'>" + s3 + "</a></td></tr>");
            }
        }

    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    void EscribeOpcion(PrintWriter printwriter, int i, String s, String s1, String s2)
    {
        String s3;
        if(s2.compareTo("S") == 0)
            s3 = "folderabierto.gif";
        else
            s3 = "foldercerrado.gif";
        switch(i)
        {
        case 0: // '\0'
        default:
            break;

        case -1:
            printwriter.println("<tr>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' valign='center' align='left'>&nbsp;</td>");
            printwriter.println("</tr>");
            break;

        case 1: // '\001'
            printwriter.println("<tr>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='center' align='left'><IMG src='../images/" + s3 + "' width=17 height=13 border=0></td>");
            if(s1.length() > 0)
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='8' valign='center' align='left'>" + s1 + s + "</a></td>");
            else
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='8' valign='center' align='left'>" + s + "</a></td>");
            printwriter.println("</tr>");
            break;

        case 2: // '\002'
            printwriter.println("<tr>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='center' align='left'><IMG src='../images/" + s3 + "' width=17 height=13 border=0></td>");
            if(s1.length() > 0)
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='7' valign='center' align='left'>" + s1 + s + "</a></td>");
            else
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='7' valign='center' align='left'>" + s + "</a></td>");
            printwriter.println("</tr>");
            break;

        case 3: // '\003'
            printwriter.println("<tr>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='center' align='left'><IMG src='../images/" + s3 + "' width=17 height=13 border=0></td>");
            if(s1.length() > 0)
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='6' valign='center' align='left'>" + s1 + s + "</a></td>");
            else
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='6' valign='center' align='left'>" + s + "</a></td>");
            printwriter.println("</tr>");
            break;

        case 4: // '\004'
            printwriter.println("<tr>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='center' align='left'><IMG src='../images/" + s3 + "' width=17 height=13 border=0></td>");
            if(s1.length() > 0)
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='5' valign='center' align='left'>" + s1 + s + "</a></td>");
            else
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='5' valign='center' align='left'>" + s + "</a></td>");
            printwriter.println("</tr>");
            break;

        case 5: // '\005'
            printwriter.println("<tr>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='center' align='left'><IMG src='../images/" + s3 + "' width=17 height=13 border=0></td>");
            if(s1.length() > 0)
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='4' valign='center' align='left'>" + s1 + s + "</a></td>");
            else
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='4' valign='center' align='left'>" + s + "</a></td>");
            printwriter.println("</tr>");
            break;

        case 6: // '\006'
            printwriter.println("<tr>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='center' align='left'><IMG src='../images/" + s3 + "' width=17 height=13 border=0></td>");
            if(s1.length() > 0)
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='3' valign='center' align='left'>" + s1 + s + "</a></td>");
            else
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='3' valign='center' align='left'>" + s + "</a></td>");
            printwriter.println("</tr>");
            break;

        case 7: // '\007'
            printwriter.println("<tr>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='center' align='left'><IMG src='../images/" + s3 + "' width=17 height=13 border=0></td>");
            if(s1.length() > 0)
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='2' valign='center' align='left'>" + s1 + s + "</a></td>");
            else
                printwriter.println("   <td bgcolor='#FAE5B9' colspan='2' valign='center' align='left'>" + s + "</a></td>");
            printwriter.println("</tr>");
            break;

        case 8: // '\b'
            printwriter.println("<tr>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='top'>&nbsp;</td>");
            printwriter.println("   <td bgcolor='#FAE5B9' width='1%' valign='center' align='left'><IMG src='../images/" + s3 + "' width=17 height=13 border=0></td>");
            if(s1.length() > 0)
                printwriter.println("   <td bgcolor='#FAE5B9' valign='center' align='left'>" + s1 + s + "</a></td>");
            else
                printwriter.println("   <td bgcolor='#FAE5B9' valign='center' align='left'>" + s + "</a></td>");
            printwriter.println("</tr>");
            break;
        }
    }

    AccDataBase ADatos;
    AccDataBase ADatosD;
    funciones AFunc;
    Vector rsD;
    Vector rs;
    String Tipo;
    String SubTipo;
    String Proceso;
    String TipoDoc;
    String Cliente;
    String Proyecto;
    String IdID;
}