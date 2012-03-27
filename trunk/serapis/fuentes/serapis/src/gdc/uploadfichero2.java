// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   uploadfichero2.java

package gdc;

import Acc.*;
import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;

public class uploadfichero2 extends HttpServlet {

	private static final ResourceBundle bundle;
	
	static {
		bundle = ResourceBundle.getBundle("module");
	}
	
    public uploadfichero2() {
        Extension = "";
        NroArchivo = "";
        NombreArchivo = "";
        RutaSitio = "";
        RutaDocumentos = "";
        ADatos = new AccDataBase();
        AFunc = new funciones();
        EnvMail = new correo();
    }

    protected void processRequest(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        UserGDC = (String)httpsession.getValue("SerapisUser");
        NombreUser = (String)httpsession.getValue("SerapisNombre");
        if(UserGDC != null && UserGDC.length() > 0)
        {
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            if(ObtieneRutas() == 1)
            {
                procesaFicheros(httpservletrequest, printwriter);
            } else
            {
                printwriter.println("<TABLE border='1' align='center' width='80%'>");
                printwriter.println("<tr>");
                printwriter.println("<td colspan='2' class='texttitulotabla'>Informaci\363n de Publicaci\363n</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla' colspan='2'><B>No esta definido la ruta de destino de los archivos. <BR>Informe al administrador para solucionar el problema.</B></td>");
                printwriter.println("</tr>");
                printwriter.println("</table>");
            }
            printwriter.println("</body>");
            printwriter.println("</html>");
            printwriter.close();
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "GDC", 6);
        }
    }

    void depura(String s, PrintWriter printwriter)
    {
        printwriter.println(s);
    }

    public boolean procesaFicheros(HttpServletRequest httpservletrequest, PrintWriter printwriter)
    {
        String s = "";
        String s1 = "";
        String s2 = "";
        boolean flag = true;
        Integer integer = new Integer(0);
        Vector vector = new Vector();
        String s3 = "";
        String s4 = "";
        String s5 = "";
        String s6 = "";
        String s7 = "";
        String s9 = "";
        String s11 = "";
        try
        {
            rs = new Vector();
            ADatos.connect();
            
            String DriveTemporal = "";
            String sqlDrive = "select dirsitio,dirfiles from gdc.datosgenerales";
            long lDrive = ADatos.ConsultaDB(sqlDrive);
            rs = ADatos.getResult();
            if(rs.size() > 0){
            	DriveTemporal = (String)rs.elementAt(0);
            	DriveTemporal = DriveTemporal.substring(0,1);
            } else {
            	DriveTemporal = bundle.getString("file.upload.temp");
            	//DriveTemporal = "C";
            }
            
            DiskFileUpload diskfileupload = new DiskFileUpload();
            diskfileupload.setSizeMax(0x1000000L);
            diskfileupload.setSizeThreshold(4096);
            diskfileupload.setRepositoryPath(DriveTemporal + ":\\");
            //diskfileupload.setRepositoryPath(DriveTemporal + ":\\");
            List list = diskfileupload.parseRequest(httpservletrequest);
            Iterator iterator = list.iterator();
            FileItem fileitem = null;
            do
            {
                if(!iterator.hasNext())
                    break;
                fileitem = (FileItem)iterator.next();
                if(fileitem.getFieldName().equals("tipoextension"))
                    Extension = fileitem.getString();
                if(fileitem.getFieldName().equals("nombrearchivo"))
                    NroArchivo = fileitem.getString();
                if(fileitem.getFieldName().equals("fichero"))
                    NombreArchivo = fileitem.getName();
            } while(true);
            String s12 = "select tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,nombreorigen,extension,comentario,fuente,fechapublica,fechacaducidad,usuario,idwf";
            s12 = s12 + " from gdc.documentos where id = " + NroArchivo;
            long l = ADatos.ConsultaDB(s12);
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                String s18 = (String)rs.elementAt(0);
                String s19 = (String)rs.elementAt(1);
                String s20 = (String)rs.elementAt(2);
                String s21 = (String)rs.elementAt(3);
                String s22 = (String)rs.elementAt(4);
                String s23 = (String)rs.elementAt(5);
                String s24 = (String)rs.elementAt(6);
                String s25 = (String)rs.elementAt(7);
                String s26 = (String)rs.elementAt(8);
                String s27 = (String)rs.elementAt(9);
                String s28 = (String)rs.elementAt(10);
                String s29 = (String)rs.elementAt(11);
                Integer integer1 = (Integer)rs.elementAt(12);
                Integer integer2 = (Integer)rs.elementAt(13);
                String s30 = (String)rs.elementAt(14);
                Integer integer3 = (Integer)rs.elementAt(15);
                NombreArchivo = s25.substring(0, s25.length() - 4) + "." + Extension;
                s25 = NombreArchivo;
                NombreArchivo = RutaDocumentos + "gdc\\documentos\\" + s19 + "\\" + NombreArchivo;
                File file = new File(NombreArchivo);
                fileitem.write(file);
                if(s29.compareTo("S") == 0)
                    s29 = "N";
                else
                    s29 = "S";
                String s13 = "insert into gdc.documentos (tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,nombreorigen,extension,comentario,fuente,fechapublica,fechacaducidad,usuario,idwf,estado)";
                s13 = s13 + "values(";
                s13 = s13 + "'" + s18 + "',";
                s13 = s13 + "'" + s19 + "',";
                s13 = s13 + "'" + s20 + "',";
                s13 = s13 + "'" + s21 + "',";
                s13 = s13 + "'" + s22 + "',";
                s13 = s13 + "'" + s23 + "',";
                s13 = s13 + "'" + s24 + "',";
                s13 = s13 + "'" + s25 + "',";
                s13 = s13 + "'" + NombreArchivo + "',";
                s13 = s13 + "'" + Extension + "',";
                s13 = s13 + "'" + s28 + "',";
                s13 = s13 + "'" + s29 + "',";
                s13 = s13 + integer1 + ",";
                s13 = s13 + integer2 + ",";
                s13 = s13 + "'" + s30 + "',";
                s13 = s13 + integer3.toString() + ",";
                if(integer3.intValue() > 0)
                    s13 = s13 + "'E')";
                else
                    s13 = s13 + "'A')";
                int i = ADatos.ModificaDB(s13);
                printwriter.println("<TABLE border='1' width='80%' align='center'>");
                printwriter.println("<tr>");
                printwriter.println("<td colspan='2' class='texttitulotabla'>Informaci\363n del Archivo</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Archivo Origen</td>");
                printwriter.println("<td class='textdesttabla'>" + s25 + "</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Resultado BD</td>");
                if(i == 1)
                {
                    if(s29.compareTo("N") == 0)
                    {
                        String s14 = "select td.descripcion,p.descripcion from gdc.procesos p, gdc.tipodocumentos td where td.sigla = '" + s18 + "' and p.sigla = '" + s19 + "'";
                        long l1 = ADatos.ConsultaDB(s14);
                        rs = new Vector();
                        rs = ADatos.getResult();
                        if(rs.size() > 0)
                        {
                            String s8 = (String)rs.elementAt(0);
                            String s10 = (String)rs.elementAt(1);
                            String s15 = "INSERT into gdc.notificaciones (fecha,tiponotificacion,proceso,tipo,version,archivo,estado,fechaestado,usuario) VALUES (";
                            s15 = s15 + "10" + ",";
                            s15 = s15 + "'A',";
                            s15 = s15 + "'" + s10 + "',";
                            s15 = s15 + "'" + s8 + "',";
                            s15 = s15 + s20 + ",";
                            s15 = s15 + "'" + s25 + "',";
                            s15 = s15 + "'P',0,";
                            s15 = s15 + "'" + NombreUser + "')";
                            i = ADatos.ModificaDB(s15);
                        }
                    }
                    printwriter.println("<TD class='textdesttabla'>El archivo se agreg\363 correctamente a la Base de datos (1)</TD>");
                    printwriter.println("</TR>");
                    printwriter.println("</TABLE>");
                    if(integer3.intValue() > 0)
                    {
                        String s16 = "SELECT wf.nombre nombrewf, wfp.usuarioautoriza, u.nombre nombreuser, u.mail, wfp.nombre nombrepaso from gdc.workflow wf, gdc.workflow_pasos wfp, sgc.usuarios u where wf.idwf = wfp.idwf and u.login = wfp.usuarioautoriza and wf.idwf = " + integer3.toString() + " and wfp.idpaso = 1";
                        long l2 = ADatos.ConsultaDB(s16);
                        rs = new Vector();
                        rs = ADatos.getResult();
                        String s31 = "";
                        String s33 = "";
                        String s35 = "";
                        String s37 = "";
                        String s39 = "";
                        if(rs.size() > 0)
                        {
                            String s32 = (String)rs.elementAt(3);
                            String s34 = (String)rs.elementAt(2);
                            String s36 = (String)rs.elementAt(4);
                            String s40 = (String)rs.elementAt(1);
                            String s38 = EnvMail.FormateaMail("Solicitud de Aprobaci\363n", "Se ha publicado un documento que requiere su aprobaci\363n.", "Paso", "Quien Autoriza", "WorkFlow", "Documento", "", s36, s34, ((String)rs.elementAt(0)).toString(), s25.toString(), "", "Ingrese al sistema y se le informar\341n los documentos que estan pendientes de autorizaci\363n o firma", "Importante: <BR>Este mail es generado de manera autom\341tica, por favor no responda a este mensaje.");
                            EnvMail.Envio(s32, "SERAPIS. Aviso de Autorizaci\363n", s38);
                            printwriter.println("<BR><BR><TABLE border='1' width='80%' align='center'>");
                            printwriter.println("<TR><TD class='textdesttabla' colspan='2'>El documento ha entrado a un proceso de flujo de aprobaci\363n de acuerdo a la siguiente informaci\363n:");
                            printwriter.println("<B><BR>WorkFlow: " + ((String)rs.elementAt(0)).toString());
                            printwriter.println("<BR>" + s36 + ": " + ((String)rs.elementAt(2)).toString());
                            printwriter.println("</B></TD></TR>");
                            printwriter.println("<TR><TD class='textdesttabla' colspan='2'>Se han enviado las notificaciones necesarias para informar la solicitud de aprobaci\363n.</TD></TR>");
                            printwriter.println("</TABLE>");
                            Vector vector1 = new Vector();
                            vector1 = AFunc.ObtieneFechaHora();
                            String s17 = "insert into gdc.workflow_flujo (tipodoc,iddocumento,idwf,idpaso,detalle,accion,usuario,fecha,hora) values (";
                            s17 = s17 + "'gdc',";
                            s17 = s17 + NroArchivo.toString() + ",";
                            s17 = s17 + integer3.toString() + ",";
                            s17 = s17 + "1,";
                            s17 = s17 + "'Publicaci\363n del archivo y solicitud de autorizaci\363n a: " + ((String)rs.elementAt(2)).toString() + "',";
                            s17 = s17 + "'P',";
                            s17 = s17 + "'" + s40 + "',";
                            s17 = s17 + vector1.elementAt(0).toString() + ",";
                            s17 = s17 + vector1.elementAt(1).toString() + ")";
                            i = ADatos.ModificaDB(s17);
                        }
                    }
                } else
                {
                    printwriter.println("<TD class='textdesttabla'>Problemas al agregar informaci\363n a la base de Datos (" + i + ")</TD>");
                    printwriter.println("</TR>");
                    printwriter.println("</TABLE>");
                }
            } else
            {
                printwriter.println("<TABLE border='1' width='80%' align='center'>");
                printwriter.println("<tr>");
                printwriter.println("<td colspan='2' class='texttitulotabla'>Informaci\363n del Archivo</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Archivo Origen</td>");
                printwriter.println("<td class='textdesttabla'>" + s + "</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Resultado BD</td>");
                printwriter.println("<TD class='textdesttabla'>El archivo origen no existe. La copia se cancel\363</TD>");
                printwriter.println("</TR>");
                printwriter.println("</TABLE>");
            }
        }
        catch(Exception exception)
        {
            boolean flag1 = false;
            printwriter.println("<BR> Error de Aplicaci\363n " + exception.getMessage());
        }
        return true;
    }

    protected void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        processRequest(httpservletrequest, httpservletresponse);
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

    String Extension;
    String NroArchivo;
    String NombreArchivo;
    Vector rs;
    String UserGDC;
    String NombreUser;
    String RutaSitio;
    String RutaDocumentos;
    AccDataBase ADatos;
    funciones AFunc;
    correo EnvMail;
}