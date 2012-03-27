// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   elimina.java

package gdc;

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
        ADatosOtros = new AccDataBase();
        CopiaFile = new CopiadorDeArchivos();
        AFunc = new funciones();
        RutaSitio = "";
        RutaDocumentos = "";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s = (String)httpsession.getValue("SerapisSis");
        String s1 = (String)httpsession.getValue("SerapisUser");
        String sTipo = (httpservletrequest.getParameter("T")!=null)?httpservletrequest.getParameter("T").toString().trim():"";
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        String s2 = "";
        String s3 = "";
        String s4 = "";
        String s6 = "";
        if(s != null && s.length() > 0)
        {
            Integer integer = new Integer(0);
            Vector vector = new Vector();
            Integer integer3 = new Integer(0);
            boolean flag = false;
            String s14 = httpservletrequest.getParameter("TIPO");
            String s15 = httpservletrequest.getParameter("DOC");
            vector = AFunc.ObtieneFecha();
            integer3 = new Integer((String)vector.elementAt(0));
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            ObtieneRutas();
            RutaSitio = RutaDocumentos;
            printwriter.println("<form name='procesadoc' method='POST' action='procesadocumento.jsp?TIPO=" + s14 + "&T=" + sTipo + "'>");
            printwriter.println("<table border='1' align='center' width='60%'>");
            printwriter.println("<tr>");
            printwriter.println("<td class='texttitulotabla' align='center'>Detalle Solicitud</td>");
            printwriter.println("</tr>");
            rs = new Vector();
            if(s14 != null && s15 != null)
            {
                if(s14.length() > 0 && s15.length() > 0)
                {
                	if (sTipo.equals("R"))
                		sql = "select tipodoc,proceso,version,descripcion  from sad.documentos where id = " + s15;
                	else
                		sql = "select tipodoc,proceso,version,descripcion  from gdc.documentos where id = " + s15;
                    int i = ADatosOtros.ConsultaDB(sql);
                    rsOtros = ADatosOtros.getResult();
                    if(rsOtros.size() > 0)
                    {
                        s2 = (String)rsOtros.elementAt(0);
                        s3 = (String)rsOtros.elementAt(1);
                        String s5 = (String)rsOtros.elementAt(2);
                        String s7 = (String)rsOtros.elementAt(3);
                        if (sTipo.equals("R"))
                        	sql = "select tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,nombreorigen,extension,comentario,'',fechapublica,fechacaducidad,usuario,id,idwf,estado from sad.documentos ";
                        else
                        	sql = "select tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,nombreorigen,extension,comentario,fuente,fechapublica,fechacaducidad,usuario,id,idwf,estado from gdc.documentos ";                        
                        sql = sql + " where tipodoc = '" + s2 + "'";
                        sql = sql + " and proceso = '" + s3 + "'";
                        sql = sql + " and version = '" + s5 + "'";
                        sql = sql + " and descripcion = '" + s7 + "'";
                        rsOtros = new Vector();
                        int j = ADatosOtros.ConsultaDB(sql);
                        rsOtros = ADatosOtros.getResult();
                        for(int k2 = 0; k2 < rsOtros.size(); k2 += 18)
                        {
                            String s16 = (String)rsOtros.elementAt(k2);
                            String s17 = (String)rsOtros.elementAt(k2 + 1);
                            String s18 = (String)rsOtros.elementAt(k2 + 2);
                            String s19 = (String)rsOtros.elementAt(k2 + 3);
                            String s20 = (String)rsOtros.elementAt(k2 + 4);
                            String s21 = (String)rsOtros.elementAt(k2 + 5);
                            String s22 = (String)rsOtros.elementAt(k2 + 6);
                            String s23 = (String)rsOtros.elementAt(k2 + 7);
                            String s24 = (String)rsOtros.elementAt(k2 + 8);
                            String s25 = (String)rsOtros.elementAt(k2 + 9);
                            String s26 = (String)rsOtros.elementAt(k2 + 10);
                            String s27 = (String)rsOtros.elementAt(k2 + 11);
                            Integer integer4 = (Integer)rsOtros.elementAt(k2 + 12);
                            Integer integer5 = (Integer)rsOtros.elementAt(k2 + 13);
                            String s28 = (String)rsOtros.elementAt(k2 + 14);
                            Integer integer6 = (Integer)rsOtros.elementAt(k2 + 15);
                            Integer integer7 = (Integer)rsOtros.elementAt(k2 + 16);
                            String s29 = (String)rsOtros.elementAt(k2 + 17);
                            if(s14.compareTo("E") == 0)
                            {
                                sql = "select count(*) from " + (sTipo.equals("R")?"sad":"gdc")+ ".reciclaje";
                                int k = ADatos.ConsultaDB(sql);
                                rs = ADatos.getResult();
                                if(rs.size() > 0)
                                {
                                    Integer integer1 = (Integer)rs.elementAt(0);
                                    int i2 = integer1.intValue() + 1;
                                    String s8 = i2 + "." + s25;
                                    String s10 = RutaSitio + (sTipo.equals("R")?"sad":"gdc")+ "\\documentos\\" + s17 + "\\" + s23;
                                    String s12 = RutaSitio + (sTipo.equals("R")?"sad":"gdc")+ "\\reciclaje\\" + s17 + "\\" + s8;
                                    //CopiadorDeArchivos _tmp = CopiaFile;
                                    CopiadorDeArchivos.copia(s10, s12);
                                    if (sTipo.equals("R"))
                                    	sql = "insert into sad.reciclaje (tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,nombreorigen,extension,comentario,fechapublica,fechacaducidad,usuario,fechahistorico,usuariohistorico)";
                                    else
                                    	sql = "insert into gdc.reciclaje (tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,nombreorigen,archivofinal,extension,comentario,fuente,fechapublica,fechacaducidad,usuario,idwf,estado,fechahistorico,usuariohistorico)";
                                    sql = sql + " values ('" + s16 + "',";
                                    sql = sql + "'" + s17 + "',";
                                    sql = sql + "'" + s18 + "',";
                                    sql = sql + "'" + s19 + "',";
                                    sql = sql + "'" + s20 + "',";
                                    sql = sql + "'" + s21 + "',";
                                    sql = sql + "'" + s22 + "',";
                                    sql = sql + "'" + s23 + "',";
                                    sql = sql + "'" + s24 + "',";
                                    if (!sTipo.equals("R"))
                                    	sql = sql + "'" + s8 + "',"; //archivofinal
                                    sql = sql + "'" + s25 + "',";
                                    sql = sql + "'" + s26 + "',";
                                    if (!sTipo.equals("R"))
                                    	sql = sql + "'" + s27 + "',"; //fuente
                                    sql = sql + integer4 + ",";
                                    sql = sql + integer5 + ",";
                                    sql = sql + "'" + s28 + "',";
                                    if (!sTipo.equals("R")) {
                                    	sql = sql + integer7.toString() + ",";
                                        sql = sql + "'" + s29 + "',";
                                    }
                                    sql = sql + integer3.toString() + ",";
                                    sql = sql + "'" + s1 + "')";
                                    int l = ADatos.ModificaDB(sql);
                                    if(l != -1)
                                    {
                                        sql = "delete from " + (sTipo.equals("R")?"sad":"gdc")+ ".documentos where id = " + integer6;
                                        int i1 = ADatos.ModificaDB(sql);
                                        if(i1 != -1)
                                        {
                                            EscribeMensaje(printwriter, "El documento <B>" + s23 + "</B> se ha eliminado correctamente");
                                            File file = new File(s10);
                                            file.delete();
                                        } else
                                        {
                                            EscribeMensaje(printwriter, "No se pudo eliminar el archivo de la tabla de documentos Documento Nro. <B>" + s15 + "</B>");
                                        }
                                    } else
                                    {
                                        EscribeMensaje(printwriter, "No se pudo insertar en tabla reciclaje Documento Nro. <B>" + s15 + "</B>");
                                    }
                                }
                            } else
                            {
                            	rs.clear();
                            	
                                sql = "select count(*) from " + (sTipo.equals("R")?"sad":"gdc") + ".historico";
                                int j1 = ADatos.ConsultaDB(sql);
                                rs = ADatos.getResult();
                                if(rs.size() > 0 && j1==1)
                                {
                                    Integer integer2 = (Integer)rs.elementAt(0);
                                    int j2 = integer2.intValue() + 1;
                                    String s9 = j2 + "." + s25;
                                    String s11 = RutaSitio + (sTipo.equals("R")?"sad":"gdc") + "\\documentos\\" + s17 + "\\" + s23;
                                    String s13 = RutaSitio + (sTipo.equals("R")?"sad":"gdc") + "\\historico\\" + s17 + "\\" + s9;
                                    //CopiadorDeArchivos _tmp1 = CopiaFile;
                                    CopiadorDeArchivos.copia(s11, s13);
                                    //if (sTipo.equals("R"))
                                    //	sql = "insert into sad.historico (tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,nombreorigen,archivofinal,extension,comentario,fechapublica,fechacaducidad,usuario,idwf,estado,fechahistorico,usuariohistorico)";
                                    //else
                                    	sql = "insert into gdc.historico (tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,nombreorigen,archivofinal,extension,comentario,fuente,fechapublica,fechacaducidad,usuario,idwf,estado,fechahistorico,usuariohistorico)";                                    		
                                    sql = sql + " values ('" + s16 + "',";
                                    sql = sql + "'" + s17 + "',";
                                    sql = sql + "'" + s18 + "',";
                                    sql = sql + "'" + s19 + "',";
                                    sql = sql + "'" + s20 + "',";
                                    sql = sql + "'" + s21 + "',";
                                    sql = sql + "'" + s22 + "',";
                                    sql = sql + "'" + s23 + "',";
                                    sql = sql + "'" + s24 + "',";
                                    sql = sql + "'" + s9 + "',";
                                    sql = sql + "'" + s25 + "',";
                                    sql = sql + "'" + s26 + "',";
                                    //if (!sTipo.equals("R"))                                    
                                    	sql = sql + "'" + s27 + "',";
                                    sql = sql + integer4 + ",";
                                    sql = sql + integer5 + ",";
                                    sql = sql + "'" + s28 + "',";
                                    sql = sql + integer7.toString() + ",";
                                    sql = sql + "'" + s29 + "',";
                                    sql = sql + integer3.toString() + ",";
                                    sql = sql + "'" + s1 + "')";
                                    int k1 = ADatos.ModificaDB(sql);
                                    if(k1 != -1)
                                    {
                                        sql = "delete from " + (sTipo.equals("R")?"sad":"gdc") + ".documentos where id = " + integer6;
                                        int l1 = ADatos.ModificaDB(sql);
                                        if(l1 != -1)
                                        {
                                            EscribeMensaje(printwriter, "El documento <B>" + s23 + "</B> se ha copiado al historico correctamente");
                                            File file1 = new File(s11);
                                            file1.delete();
                                        } else
                                        {
                                            EscribeMensaje(printwriter, "No se pudo eliminar el archivo de la tabla de documentos Documento Nro. <B>" + s15 + "</B>");
                                        }
                                    } else
                                    {
                                        EscribeMensaje(printwriter, "No se pudo insertar en tabla historico Documento Nro. <B>" + s15 + "</B>");
                                    }
                                }
                            }
                        }

                    } else
                    {
                        EscribeMensaje(printwriter, "El documento no existe (" + s15 + ")");
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
            //printwriter.println("<center><input class='fondoinput' type='button' value='Aceptar' name='btnOk' language='javascript' onclick=\"window.open('muestradoc.jsp?TIPO=DOC&AREA=" + s3 + "&TIPODOC=" + s2 + "','datos');\"></center>");
            
            printwriter.println("<center><input class='fondoinput' type='button' value='Aceptar' name='btnOk' language='javascript' onclick=\"window.open('inicio.jsp?TIPO=LM" + sTipo+ "','cuerpo');\"></center>");
            printwriter.println("</form>");
            printwriter.println("</body>");
            printwriter.println("</html>");
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "GDC", 6);
        }
    }

    void EscribeError(PrintWriter printwriter, String s)
    {
        printwriter.println("<table border='1' align='center' width='60%'>");
        printwriter.println("<tr>");
        printwriter.println("<td class='texttituloarea' align='center'>Detalle Solicitud</td>");
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
    AccDataBase ADatosOtros;
    CopiadorDeArchivos CopiaFile;
    funciones AFunc;
    Vector rs;
    Vector rsOtros;
    String sql;
    String RutaSitio;
    String RutaDocumentos;
}