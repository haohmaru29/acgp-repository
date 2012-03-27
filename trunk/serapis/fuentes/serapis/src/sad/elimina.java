// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   elimina.java

package sad;

import Acc.*;
import java.io.*;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class elimina extends HttpServlet
{

    public elimina()
    {
        ADatos = new AccDataBase();
        CopiaFile = new CopiadorDeArchivos();
        AFunc = new funciones();
        RutaDir = "";
        IdMax = new Integer(0);
        RutaSitio = "";
        RutaDocumentos = "";
        FechaReciclaje = AFunc.ObtieneFecha();
        FechaPaso = new Integer((String)FechaReciclaje.elementAt(0));
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
            Integer integer = new Integer(0);
            String s1 = "";
            String s3 = "";
            String s5 = "";
            String s7 = "";
            String s9 = "";
            String s11 = "";
            String s12 = "";
            String s14 = "";
            String s16 = "";
            String s18 = "";
            String s21 = "";
            String s23 = "";
            String s25 = "";
            Integer integer3 = new Integer(0);
            Integer integer5 = new Integer(0);
            String s27 = "";
            String s29 = "";
            String s31 = httpservletrequest.getParameter("TIPO");
            String s32 = httpservletrequest.getParameter("DOC");
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            ObtieneRutas();
            s27 = RutaDocumentos;
            RutaDir = RutaDocumentos;
            printwriter.println("<form name='procesadoc' method='POST' action='procesadocumento.jsp?TIPO=" + s31 + "'>");
            printwriter.println("<table border='1' align='center' width='60%'>");
            printwriter.println("<tr>");
            printwriter.println("<td class='texttitulotabla' align='center'>Detalle Solicitud</td>");
            printwriter.println("</tr>");
            rs = new Vector();
            if(s31 != null && s32 != null)
            {
                if(s31.length() > 0 && s32.length() > 0)
                {
                    if(s31.compareTo("A") == 0)
                        sql = "select id,tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,nombreorigen,extension,comentario,usuario,fechapublica,fechacaducidad from sad.documentos where id = " + s32;
                    else
                        sql = "select id,tipodoc,proceso,version,descripcion,cliente,proyecto,adicional,verdoc,nombrearchivo,nombreorigen,extension,comentario,usuario,fechapublica,fechacaducidad from sad.documentosp where id = " + s32;
                    int i = ADatos.ConsultaDB(sql);
                    rs = ADatos.getResult();
                    if(rs.size() > 0)
                    {
                        String s2;
                        String s4;
                        String s6;
                        String s8;
                        String s10;
                        String s13;
                        String s15;
                        String s17;
                        String s22;
                        String s24;
                        String s26;
                        Integer integer4;
                        Integer integer6;
                        if(s31.compareTo("A") == 0)
                        {
                            Integer integer1 = (Integer)rs.elementAt(0);
                            s2 = (String)rs.elementAt(1);
                            s4 = (String)rs.elementAt(2);
                            s6 = (String)rs.elementAt(3);
                            s8 = (String)rs.elementAt(4);
                            s10 = (String)rs.elementAt(5);
                            s13 = (String)rs.elementAt(6);
                            s15 = (String)rs.elementAt(7);
                            s17 = (String)rs.elementAt(8);
                            String s19 = (String)rs.elementAt(9);
                            s22 = (String)rs.elementAt(10);
                            s24 = (String)rs.elementAt(11);
                            s26 = (String)rs.elementAt(12);
                            integer4 = (Integer)rs.elementAt(13);
                            integer6 = (Integer)rs.elementAt(14);
                        } else
                        {
                            Integer integer2 = (Integer)rs.elementAt(0);
                            s2 = (String)rs.elementAt(1);
                            s4 = (String)rs.elementAt(2);
                            s6 = (String)rs.elementAt(3);
                            s8 = (String)rs.elementAt(4);
                            s10 = (String)rs.elementAt(5);
                            s11 = (String)rs.elementAt(6);
                            s13 = (String)rs.elementAt(7);
                            s15 = (String)rs.elementAt(8);
                            s17 = (String)rs.elementAt(9);
                            String s20 = (String)rs.elementAt(10);
                            s22 = (String)rs.elementAt(11);
                            s24 = (String)rs.elementAt(12);
                            s26 = (String)rs.elementAt(13);
                            integer4 = (Integer)rs.elementAt(14);
                            integer6 = (Integer)rs.elementAt(15);
                        }
                        String s28 = RutaDir;
                        if(s31.compareTo("A") == 0)
                        {
                            sql = "delete from sad.documentos where id = " + s32;
                            s28 = s28 + "sad\\documentos\\" + s4 + "\\" + s17;
                        } else
                        {
                            sql = "delete from sad.documentosp where id = " + s32;
                            s28 = s28 + "sad\\proyecto\\" + s10 + "\\" + s11 + "\\" + s17;
                        }
                        int j = ADatos.ModificaDB(sql);
                        if(j != -1)
                        {
                            sql = "select max(id) from sad.reciclaje";
                            int k = ADatos.ConsultaDB(sql);
                            rs = ADatos.getResult();
                            if(rs.size() >= 0)
                            {
                                IdMax = (Integer)rs.elementAt(0);
                                idmax = 1;
                                if(IdMax != null)
                                    idmax = IdMax.intValue() + 1;
                                sql = "insert into sad.reciclaje (tipo,tipodoc,proceso,version,descripcion,cliente,proyecto,adicional,verdoc,nombrearchivo,nombreorigen,extension,comentario,usuario,fechapublica,fechacaducidad,fechahistorico,usuariohistorico)";
                                sql = sql + "values ('" + s31 + "',";
                                sql = sql + "'" + s2 + "',";
                                sql = sql + "'" + s4 + "',";
                                sql = sql + "'" + s6 + "',";
                                sql = sql + "'" + s8 + "',";
                                sql = sql + "'" + s10 + "',";
                                sql = sql + "'" + s11 + "',";
                                sql = sql + "'" + s13 + "',";
                                sql = sql + "'" + s15 + "',";
                                sql = sql + "'" + idmax + "." + s22 + "',";
                                sql = sql + "'" + s17 + "',";
                                sql = sql + "'" + s22 + "',";
                                sql = sql + "'" + s24 + "',";
                                sql = sql + "'" + s26 + "',";
                                sql = sql + integer4 + ",";
                                sql = sql + integer6 + ",";
                                sql = sql + FechaPaso.toString() + ",";
                                sql = sql + "'" + s + "')";
                                int l = ADatos.ModificaDB(sql);
                                if(l != -1)
                                {
                                    String s30 = RutaDocumentos + "\\sad\\reciclaje\\" + idmax + "." + s22;
                                    //CopiadorDeArchivos _tmp = CopiaFile;
                                    CopiadorDeArchivos.copia(s28, s30);
                                    EscribeMensaje(printwriter, "El documento <B>" + s17 + "</B> se ha eliminado correctamente");
                                    File file = new File(s28);
                                    file.delete();
                                } else
                                {
                                    EscribeMensaje(printwriter, "El documento <B>" + s17 + "</B> no se ha copiado a reciclaje");
                                }
                            } else
                            {
                                EscribeMensaje(printwriter, "No es posible pasar a reciclaje. <B>" + s32 + "</B>");
                            }
                        } else
                        {
                            EscribeMensaje(printwriter, "No se pudo eliminar el archivo de la tabla de documentos Documento Nro. <B>" + s32 + "</B>");
                        }
                    } else
                    {
                        EscribeMensaje(printwriter, "El documento no existe (" + s32 + ")");
                    }
                } else
                {
                    EscribeMensaje(printwriter, "Los Par\341metros de la p\341gina son vacios");
                }
            } else
            {
                EscribeMensaje(printwriter, "Los Par\341metros de la p\341gina son nulos");
            }
            printwriter.println("</table>");
            printwriter.println("</form>");
            printwriter.println("</body>");
            printwriter.println("</html>");
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "SAD", 6);
        }
    }

    void EscribeError(PrintWriter printwriter, String s)
    {
        printwriter.println("<table border='1' align='center' width='60%'>");
        printwriter.println("<tr>");
        printwriter.println("<td class='texttitulotabla' align='center'>Detalle Solicitud</td>");
        printwriter.println("</tr>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla' align='center'>" + s + "</td>");
        printwriter.println("</tr>");
        printwriter.println("</table>");
    }

    void EscribeMensaje(PrintWriter printwriter, String s)
    {
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla' align='left'>" + s + "</td>");
        printwriter.println("</tr>");
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    private int ObtieneRutas()
    {
        Vector vector = new Vector();
        int i = 0;
        String s = "select dirsitio,dirfiles from gdc.datosgenerales";
        ADatos.ConsultaDB(s);
        vector = ADatos.getResult();
        if(vector.size() > 0)
        {
            RutaSitio = (String)vector.elementAt(0);
            RutaDocumentos = (String)vector.elementAt(1);
            i = 1;
        }
        return i;
    }

    AccDataBase ADatos;
    CopiadorDeArchivos CopiaFile;
    funciones AFunc;
    Vector rs;
    Vector rsOtros;
    String sql;
    String RutaDir;
    Integer IdMax;
    int idmax;
    String RutaSitio;
    String RutaDocumentos;
    Vector FechaReciclaje;
    Integer FechaPaso;
}