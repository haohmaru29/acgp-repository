// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   procesawf.java

package workflow;

import Acc.*;
import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;

public class procesawf extends HttpServlet
{

    public procesawf()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        EnvMail = new correo();
        UserGDC = "";
        NroDoc = "";
        Comentarios = "";
        Tabla = "";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        UserGDC = (String)httpsession.getValue("SerapisUser");
        printwriter.println("<html>");
        printwriter.println("<head>");
        printwriter.println("</head>");
        printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
        printwriter.println("<title>SERAPIS. FLUJO DE APROBACI\323N</title>");
        printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
        printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
        if(UserGDC != null && UserGDC.length() > 0)
        {
            ADatos.connect();
            procesaFicheros(httpservletrequest, printwriter);
        } else
        {
            printwriter.println("<BR><BR><BR><BR>");
            printwriter.println("<table border='0' width='99%' align='center'>");
            printwriter.println("<tr><td align='center' class='texttitulomayor'>Aviso</td></tr>");
            printwriter.println("<tr><td align='center' class='textgral'>&nbsp;</td></tr>");
            printwriter.println("<tr><td align='center' class='textgral'>No se encuentra su informaci\363n de sesi\363n.</td></tr>");
            printwriter.println("<tr><td align='center' class='textgral'>Inicie su sesi\363n en SERAPIS para ver las autorizaciones pendientes.</td></tr>");
            printwriter.println("<tr><td align='center' class='textgral'>&nbsp;</td></tr>");
            printwriter.println("<tr><td align='center' class='textgral'><input class='fondoinput' name='btnCerrar' value='Aceptar' type='button' language='javascript' onclick='window.close();'></td></tr>");
            printwriter.println("</table>");
        }
        printwriter.println("</body>");
        printwriter.println("</html>");
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    public boolean procesaFicheros(HttpServletRequest httpservletrequest, PrintWriter printwriter)
    {
        String s = "";
        String s2 = "";
        String s4 = "";
        String s5 = "";
        String s6 = "";
        String s7 = "";
        String s8 = "";
        String s9 = "";
        String s11 = "";
        String s13 = "";
        String s15 = "";
        String s17 = "";
        String s19 = "";
        boolean flag = true;
        Vector vector = new Vector();
        try
        {
        	rs = new Vector();
            String DriveTemporal = "";
            String sqlDrive = "select dirsitio,dirfiles from gdc.datosgenerales";
            long lDrive = ADatos.ConsultaDB(sqlDrive);
            rs = ADatos.getResult();
            if(rs.size() > 0){
            	DriveTemporal = (String)rs.elementAt(0);
            	DriveTemporal = DriveTemporal.substring(0,1);
            } else
            	DriveTemporal = "C";
        	
            DiskFileUpload diskfileupload = new DiskFileUpload();
            diskfileupload.setSizeMax(0x1000000L);
            diskfileupload.setSizeThreshold(4096);
            diskfileupload.setRepositoryPath(DriveTemporal + ":\\");
            List list = diskfileupload.parseRequest(httpservletrequest);
            Iterator iterator = list.iterator();
            FileItem fileitem = null;
            do
            {
                if(!iterator.hasNext())
                    break;
                fileitem = (FileItem)iterator.next();
                if(fileitem.getFieldName().equals("txtIdFlujo"))
                    s5 = fileitem.getString();
                if(fileitem.getFieldName().equals("txtNroDoc"))
                    NroDoc = fileitem.getString();
                if(fileitem.getFieldName().equals("txtNroWf"))
                    s6 = fileitem.getString();
                if(fileitem.getFieldName().equals("txtNroPaso"))
                    s7 = fileitem.getString();
                String s3;
                if(fileitem.getFieldName().equals("txtdecision"))
                    s3 = fileitem.getString();
                if(fileitem.getFieldName().equals("txtComentarios"))
                    Comentarios = fileitem.getString();
                if(fileitem.getFieldName().equals("txtclave"))
                    s4 = fileitem.getString();
                if(fileitem.getFieldName().equals("txtAccion"))
                    s8 = fileitem.getString();
                if(fileitem.getFieldName().equals("txtTipoDoc"))
                    s9 = fileitem.getString();
                String s1;
                if(fileitem.getFieldName().equals("txtarchivo"))
                    s1 = fileitem.getName();
            } while(true);
            int i = ValidaClave(s4);
            switch(i)
            {
            default:
                break;

            case -1:
                flag = false;
                printwriter.println("<BR><BR>");
                printwriter.println("<table border='0' width='99%' align='center'>");
                printwriter.println("<tr><td align='center' class='texttitulomayor'>Aviso</td></tr>");
                printwriter.println("<tr><td align='center' class='textgral'>&nbsp;</td></tr>");
                printwriter.println("<tr><td align='center' class='textgral'>Clave Incorrecta</td></tr>");
                printwriter.println("<tr><td align='center' class='textgral'>&nbsp;</td></tr>");
                printwriter.println("<tr><td align='center' class='textgral'><input class='fondoinput' name='btnCerrar' value='Aceptar' type='button' language='javascript' onclick='window.open(\"../aviso.jsp\",\"_self\");'></td></tr>");
                printwriter.println("</table>");
                break;

            case 0: // '\0'
                flag = false;
                printwriter.println("<BR><BR>");
                printwriter.println("<table border='0' width='99%' align='center'>");
                printwriter.println("<tr><td align='center' class='texttitulomayor'>Aviso</td></tr>");
                printwriter.println("<tr><td align='center' class='textgral'>&nbsp;</td></tr>");
                printwriter.println("<tr><td align='center' class='textgral'>Usuario no Existe</td></tr>");
                printwriter.println("<tr><td align='center' class='textgral'>&nbsp;</td></tr>");
                printwriter.println("<tr><td align='center' class='textgral'><input class='fondoinput' name='btnCerrar' value='Aceptar' type='button' language='javascript' onclick='window.open(\"../aviso.jsp\",\"_self\");'></td></tr>");
                printwriter.println("</table>");
                break;

            case 1: // '\001'
                if(s9.compareTo("gdc") == 0)
                    Tabla = "gdc.documentos";
                else
                if(s9.compareTo("sad") == 0)
                    Tabla = "sad.documentos";
                else
                    Tabla = "sad.documentosp";
                if(s8.compareTo("M") == 0)
                {
                    String s10 = "select dirsitio,dirfiles from gdc.datosgenerales";
                    int j = ADatos.ConsultaDB(s10);
                    Vector vector1 = ADatos.getResult();
                    if(vector1.size() <= 0)
                        break;
                    String s21 = (String)vector1.elementAt(0);
                    String s22 = (String)vector1.elementAt(1);
                    String s12;
                    if(s9.compareTo("gdc") == 0)
                    {
                        s12 = s22 + "gdc\\documentos";
                        s10 = "select proceso,cliente,'' proyecto,nombrearchivo from gdc.documentos where id = " + NroDoc.toString();
                    } else
                    if(s9.compareTo("sad") == 0)
                    {
                        s12 = s22 + "sad\\documentos";
                        s10 = "select proceso,cliente,'' proyecto,nombrearchivo from sad.documentos where id = " + NroDoc.toString();
                    } else
                    {
                        s12 = s22 + "sad\\proyecto";
                        s10 = "select proceso,cliente,proyecto,nombrearchivo from sad.documentosp where id = " + NroDoc.toString();
                    }
                    vector1 = new Vector();
                    ADatos.ConsultaDB(s10);
                    vector1 = ADatos.getResult();
                    if(vector1.size() > 0)
                    {
                        String s14 = (String)vector1.elementAt(0);
                        String s16 = (String)vector1.elementAt(1);
                        String s18 = (String)vector1.elementAt(2);
                        String s20 = (String)vector1.elementAt(3);
                        if(s9.compareTo("gdc") == 0)
                            s12 = s12 + s18 + "\\" + s20;
                        else
                        if(s9.compareTo("sad") == 0)
                            s12 = s12 + s18 + "\\" + s20;
                        else
                            s12 = s12 + s16 + "\\" + s18 + "\\" + s20;
                    }
                    File file = new File(s12);
                    fileitem.write(file);
                    SiguientePaso(printwriter, s6, s7, s5, NroDoc, s8);
                } else
                {
                    SiguientePaso(printwriter, s6, s7, s5, NroDoc, s8);
                }
                break;
            }
        }
        catch(Exception exception)
        {
            flag = false;
            printwriter.println("<BR><BR>");
            printwriter.println("<table border='0' width='99%' align='center'>");
            printwriter.println("<tr><td align='center' class='texttitulomayor'>Aviso</td></tr>");
            printwriter.println("<tr><td align='center' class='textgral'>&nbsp;</td></tr>");
            printwriter.println("<tr><td align='center' class='textgral'>Error de Aplicaci\363n</td></tr>");
            printwriter.println("<tr><td align='center' class='textgral'>" + exception.getMessage() + "</td></tr>");
            printwriter.println("<tr><td align='center' class='textgral'>&nbsp;</td></tr>");
            printwriter.println("<tr><td align='center' class='textgral'><input class='fondoinput' name='btnCerrar' value='Aceptar' type='button' language='javascript' onclick='window.open(\"../aviso.jsp\",\"_self\");'></td></tr>");
            printwriter.println("</table>");
        }
        return flag;
    }

    public void SiguientePaso(PrintWriter printwriter, String s, String s1, String s2, String s3, String s4)
    {
        Integer integer = new Integer(0);
        Integer integer2 = new Integer(0);
        String s15 = "";
        String s17 = "";
        Integer integer4 = new Integer(0);
        Integer integer6 = new Integer(0);
        String s20 = "";
        String s23 = "";
        String s26 = "";
        String s29 = "";
        String s32 = "";
        String s35 = "";
        String s38 = "";
        String s41 = "";
        String s44 = "";
        String s47 = "";
        String s50 = "";
        String s53 = "";
        Vector vector = new Vector();
        Vector vector6 = new Vector();
        vector6 = AFunc.ObtieneFechaHora();
        if(s4.compareTo("E") == 0)
        {
            String s5 = "update gdc.workflow_flujo set accion = 'F', decision = 'E' , usuarioautorizador = '" + UserGDC.toString() + "' , fecha_decision = " + vector6.elementAt(0).toString() + ", hora_decision = " + vector6.elementAt(1).toString() + ", comentarios = '" + Comentarios + "' where id_flujo = " + s2;
            int i = ADatos.ModificaDB(s5);
            s5 = "select tipodoc,proceso,version,descripcion,cliente,adicional,verdoc from " + Tabla + " where id = " + s3;
            Vector vector1 = new Vector();
            i = ADatos.ConsultaDB(s5);
            vector1 = ADatos.getResult();
            if(vector1.size() > 0)
            {
                String s18 = (String)vector1.elementAt(0);
                String s21 = (String)vector1.elementAt(1);
                String s24 = (String)vector1.elementAt(2);
                String s27 = (String)vector1.elementAt(3);
                String s30 = (String)vector1.elementAt(4);
                String s33 = (String)vector1.elementAt(5);
                String s36 = (String)vector1.elementAt(6);
                String s6 = "update " + Tabla + " set estado = 'R' where ";
                s6 = s6 + " tipodoc = '" + s18.toString() + "' and ";
                s6 = s6 + " proceso = '" + s21.toString() + "' and ";
                s6 = s6 + " version = " + s24.toString() + " and ";
                s6 = s6 + " descripcion = '" + s27.toString() + "' and ";
                s6 = s6 + " cliente = '" + s30.toString() + "' and";
                s6 = s6 + " adicional = '" + s33.toString() + "' and";
                s6 = s6 + " verdoc = '" + s36.toString() + "'";
                int j = ADatos.ModificaDB(s6);
                printwriter.println("<BR><BR><TABLE border='1' width='80%' align='center'>");
                printwriter.println("<TR><TD class='textdesttabla'>El documento ha finalizado el proceso de flujo de aprobaci\363n, ya que este fue rechazado.</TD></TR>");
                printwriter.println("<TR><TD align='center' class='textdesttabla'><input class='fondoinput' name='btnOK' value='Aceptar' language='javascript' onclick='window.open(\"../aviso.jsp\",\"_self\");' type='button'></TD></TR>");
                printwriter.println("</TABLE>");
            }
        } else
        {
            String s7 = "select idok, idredireccion,tipo from gdc.workflow_pasos where idwf = " + s + " and idpaso = " + s1;
            int k = ADatos.ConsultaDB(s7);
            Vector vector2 = ADatos.getResult();
            if(vector2.size() > 0)
            {
                Integer integer1 = (Integer)vector2.elementAt(0);
                Integer integer3 = (Integer)vector2.elementAt(1);
                String s16 = (String)vector2.elementAt(2);
                String s8 = "select tipodoc, iddocumento,idwf from gdc.workflow_flujo where id_flujo = " + s2;
                int l = ADatos.ConsultaDB(s8);
                vector2 = ADatos.getResult();
                if(vector2.size() > 0)
                {
                    String s19 = (String)vector2.elementAt(0);
                    Integer integer5 = (Integer)vector2.elementAt(1);
                    Integer integer7 = (Integer)vector2.elementAt(2);
                    String s9 = "update gdc.workflow_flujo set accion = 'F', decision = '" + s4.toString() + "' , usuarioautorizador = '" + UserGDC.toString() + "' , fecha_decision = " + vector6.elementAt(0).toString() + ", hora_decision = " + vector6.elementAt(1).toString() + ", comentarios = '" + Comentarios + "' where id_flujo = " + s2;
                    int i1 = ADatos.ModificaDB(s9);
                    if(s16.compareTo("F") == 0)
                    {
                        String s10 = "select tipodoc,proceso,version,descripcion,cliente,adicional,verdoc from " + Tabla + " where id = " + s3;
                        int j1 = ADatos.ConsultaDB(s10);
                        Vector vector3 = ADatos.getResult();
                        if(vector3.size() > 0)
                        {
                            s19 = (String)vector3.elementAt(0);
                            String s22 = (String)vector3.elementAt(1);
                            String s25 = (String)vector3.elementAt(2);
                            String s28 = (String)vector3.elementAt(3);
                            String s31 = (String)vector3.elementAt(4);
                            String s34 = (String)vector3.elementAt(5);
                            String s37 = (String)vector3.elementAt(6);
                            String s11 = "update " + Tabla + " set estado = 'A' where ";
                            s11 = s11 + " tipodoc = '" + s19.toString() + "' and ";
                            s11 = s11 + " proceso = '" + s22.toString() + "' and ";
                            s11 = s11 + " version = " + s25.toString() + " and ";
                            s11 = s11 + " descripcion = '" + s28.toString() + "' and ";
                            s11 = s11 + " cliente = '" + s31.toString() + "' and ";
                            s11 = s11 + " adicional = '" + s34.toString() + "' and ";
                            s11 = s11 + " verdoc = '" + s37.toString() + "'";
                            int k1 = ADatos.ModificaDB(s11);
                            s11 = "SELECT u.mail ";
                            s11 = s11 + " from gdc.listadistribucion_contenido ld, gdc.documentoscalidad dc, sgc.usuarios u ";
                            s11 = s11 + " where ld.idlista = dc.idlistadistribucion ";
                            s11 = s11 + " and u.login = ld.usuario ";
                            s11 = s11 + " and dc.tipodocumento = '" + s19 + "' ";
                            s11 = s11 + " and dc.proceso = '" + s22 + "'";
                            s11 = s11 + " and dc.descripcion = '" + s28 + "' ";
                            k1 = ADatos.ConsultaDB(s11);
                            vector3 = ADatos.getResult();
                            String s58 = "";
                            for(int k2 = 0; k2 < vector3.size(); k2++)
                                if(s58.length() > 0)
                                    s58 = s58 + "," + (String)vector3.elementAt(k2);
                                else
                                    s58 = (String)vector3.elementAt(k2);

                            if(s58.length() > 0)
                            {
                                String s12 = "select wfp.nombre nombrepaso,wfp.usuarioautoriza, u.nombre nombreuser, u.mail, wf.nombre nombrewf, d.nombrearchivo from gdc.workflow_pasos wfp, sgc.usuarios u, gdc.workflow wf, " + Tabla + " d where d.id = " + s3 + " and wf.idwf = wfp.idwf and wfp.usuarioautoriza = u.login and wfp.idwf = " + s + " and wfp.idpaso = " + s1;
                                Vector vector4 = new Vector();
                                int l1 = ADatos.ConsultaDB(s12);
                                vector4 = ADatos.getResult();
                                if(vector4.size() > 0)
                                {
                                    String s42 = (String)vector4.elementAt(0);
                                    String s39 = (String)vector4.elementAt(1);
                                    String s45 = (String)vector4.elementAt(2);
                                    String s48 = (String)vector4.elementAt(3);
                                    String s51 = (String)vector4.elementAt(4);
                                    String s54 = (String)vector4.elementAt(5);
                                    String s56 = EnvMail.FormateaMail("Distribuci\363n", "Se informa que el archivo siguiente ha finalizado su flujo de aprobaci\363n.", "\332ltimo Paso", "Quien Autoriz\363", "WorkFlow", "Documento", "Fecha", s42, s45, s51, s54, vector6.elementAt(0).toString(), "", "Importante: <BR>Este mail es generado de manera autom\341tica, por favor no responda a este mensaje.");
                                    EnvMail.Envio(s58, "SERAPIS. Distribuci\363n", s56);
                                }
                            }
                            printwriter.println("<BR><BR><TABLE border='1' width='80%' align='center'>");
                            printwriter.println("<TR><TD class='textdesttabla'>El documento ha finalizado el proceso de flujo de aprobaci\363n, por lo que el documento ha quedado autorizado.</TD></TR>");
                            printwriter.println("<TR><TD class='textdesttabla'>Adicionalmente, se ha realizado la distribuci\363n del documento a los usuarios pertinentes.</TD></TR>");
                            printwriter.println("<TR><TD align='center' class='textdesttabla'><input class='fondoinput' name='btnOK' value='Aceptar' language='javascript' onclick='window.open(\"../aviso.jsp\",\"_self\");' type='button'></TD></TR>");
                            printwriter.println("</TABLE>");
                        }
                    } else
                    {
                        String s13;
                        if(s4.compareTo("A") == 0 || s4.compareTo("M") == 0)
                            s13 = "select wfp.nombre nombrepaso,wfp.usuarioautoriza, u.nombre nombreuser, u.mail, wf.nombre nombrewf, d.nombrearchivo from gdc.workflow_pasos wfp, sgc.usuarios u, gdc.workflow wf, " + Tabla + " d where d.id = " + s3 + " and wf.idwf = wfp.idwf and wfp.usuarioautoriza = u.login and wfp.idwf = " + integer7.toString() + " and wfp.idpaso = " + integer1.toString();
                        else
                            s13 = "select wfp.nombre nombrepaso,wfp.usuarioautoriza, u.nombre nombreuser, u.mail, wf.nombre nombrewf, d.nombrearchivo from gdc.workflow_pasos wfp, sgc.usuarios u, gdc.workflow wf, " + Tabla + " d where d.id = " + s3 + " and wf.idwf = wfp.idwf and wfp.usuarioautoriza = u.login and wfp.idwf = " + integer7.toString() + " and wfp.idpaso = " + integer3.toString();
                        Vector vector5 = new Vector();
                        int i2 = ADatos.ConsultaDB(s13);
                        vector5 = ADatos.getResult();
                        if(vector5.size() > 0)
                        {
                            String s43 = (String)vector5.elementAt(0);
                            String s40 = (String)vector5.elementAt(1);
                            String s46 = (String)vector5.elementAt(2);
                            String s49 = (String)vector5.elementAt(3);
                            String s52 = (String)vector5.elementAt(4);
                            String s55 = (String)vector5.elementAt(5);
                            String s57 = EnvMail.FormateaMail("Solicitud de Aprobaci\363n", "Se requiere su aprobaci\363n para autorizar un documento.", "Paso", "Quien Autoriza", "WorkFlow", "Documento", "", s43, s46, s52, s55, "", "Ingrese al sistema y se le informar\341n los documentos que estan pendientes de autorizaci\363n o firma", "Importante: <BR>Este mail es generado de manera autom\341tica, por favor no responda a este mensaje.");
                            EnvMail.Envio(s49, "SERAPIS. Aviso de Autorizaci\363n", s57);
                            String s14 = "insert into gdc.workflow_flujo (tipodoc,iddocumento,idwf,idpaso,detalle,accion,usuario,fecha,hora) values (";
                            s14 = s14 + "'" + s19.toString() + "',";
                            s14 = s14 + integer5.toString() + ",";
                            s14 = s14 + integer7.toString() + ",";
                            if(s4.compareTo("A") == 0 || s4.compareTo("M") == 0)
                                s14 = s14 + integer1.intValue() + ",";
                            else
                                s14 = s14 + integer3.intValue() + ",";
                            s14 = s14 + "'Solicitud de Aprobaci\363n',";
                            s14 = s14 + "'P',";
                            s14 = s14 + "'" + s40 + "',";
                            s14 = s14 + vector6.elementAt(0).toString() + ",";
                            s14 = s14 + vector6.elementAt(1).toString() + ")";
                            int j2 = ADatos.ModificaDB(s14);
                            printwriter.println("<BR><BR><TABLE border='1' width='80%' align='center'>");
                            printwriter.println("<TR><TD class='textdesttabla' colspan='2'>El documento contin\372a en un proceso de flujo de aprobaci\363n de acuerdo a la siguiente informaci\363n:");
                            printwriter.println("<B><BR>WorkFlow: " + s52);
                            printwriter.println("<BR>" + s43 + ": " + s46);
                            printwriter.println("</B></TD></TR>");
                            printwriter.println("<TR><TD class='textdesttabla' colspan='2'>Se han enviado las notificaciones necesarias para informar la solicitud de aprobaci\363n.</TD></TR>");
                            printwriter.println("<TR><TD class='textdesttabla' colspan='2'align='center'><input class='fondoinput' name='btnOK' value='Aceptar' language='javascript' onclick='window.open(\"../aviso.jsp\",\"_self\");' type='button'></TD></TR>");
                            printwriter.println("</TABLE>");
                        }
                    }
                }
            }
        }
    }

    public int ValidaClave(String s)
    {
        String s2 = "";
        byte byte0 = 0;
        String s1 = "select password from sgc.usuarios where login = '" + UserGDC + "'";
        int i = ADatos.ConsultaDB(s1);
        Vector vector = new Vector();
        vector = ADatos.getResult();
        if(vector.size() > 0)
        {
            String s3 = (String)vector.elementAt(0);
            s3 = AFunc.desencripta(s3);
            if(s.compareTo(s3) == 0)
                byte0 = 1;
            else
                byte0 = -1;
        }
        return byte0;
    }

    AccDataBase ADatos;
    funciones AFunc;
    correo EnvMail;
    Vector rs;
    String UserGDC;
    String NroDoc;
    String Comentarios;
    String Tabla;
}