// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   uploadficherop.java

package sad;

import Acc.*;
import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;

public class uploadficherop extends HttpServlet
{

    public uploadficherop()
    {
        tipodocumentos = "";
        tipoprocesos = "";
        versionp = "";
        descripcion = "";
        clientes = "";
        proyecto = "";
        adicional = "";
        versiond = "";
        tipoextension = "";
        fuente = "";
        sigue = "";
        ficherosel = "";
        comentario = "";
        ComentarioAdicional = "";
        UsuarioReg = "";
        Fechav = new Vector();
        FechaAdd = new Vector();
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
        UsuarioReg = (String)httpsession.getValue("SerapisUser");
        if(UsuarioReg != null && UsuarioReg.length() > 0)
        {
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<form name='publica' method='POST' enctype='multipart/form-data' action='uploadfichero2.jsp'>");
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
            printwriter.println("</form>");
            printwriter.println("</body>");
            printwriter.println("</html>");
            printwriter.close();
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "SAD", 6);
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
        Integer integer2 = new Integer(0x5f5e0ff);
        Integer integer4 = new Integer(0);
        Integer integer6 = new Integer(0);
        String s3 = "";
        boolean flag2 = false;
        boolean flag3 = false;
        Integer integer8 = new Integer(0);
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
                if(fileitem.getFieldName().equals("tipodocumentos"))
                    tipodocumentos = fileitem.getString();
                if(fileitem.getFieldName().equals("tipoprocesos"))
                    tipoprocesos = fileitem.getString();
                if(fileitem.getFieldName().equals("versionp"))
                    versionp = fileitem.getString();
                if(fileitem.getFieldName().equals("descripcion"))
                    descripcion = fileitem.getString();
                if(fileitem.getFieldName().equals("clientes"))
                    clientes = fileitem.getString();
                if(fileitem.getFieldName().equals("proyecto"))
                    proyecto = fileitem.getString();
                if(fileitem.getFieldName().equals("adicional"))
                    adicional = fileitem.getString();
                if(fileitem.getFieldName().equals("versiond"))
                    versiond = fileitem.getString();
                if(fileitem.getFieldName().equals("tipoextension"))
                    tipoextension = fileitem.getString();
                if(fileitem.getFieldName().equals("comentario"))
                    comentario = fileitem.getString();
                if(fileitem.getFieldName().equals("comadicional"))
                {
                    ComentarioAdicional = fileitem.getString();
                    ComentarioAdicional = AFunc.encripta(ComentarioAdicional);
                }
                if(fileitem.getFieldName().equals("fichero"))
                    s = fileitem.getName();
            } while(true);
            String s5 = tipodocumentos + "-" + tipoprocesos + "-" + versionp + "-" + descripcion;
            if(versiond != null)
                if(versiond.length() > 0)
                {
                    s5 = s5 + "-" + clientes + " " + proyecto;
                    if(adicional.length() > 0)
                        s5 = s5 + " " + adicional;
                    s5 = s5 + " c" + versiond + "." + tipoextension;
                } else
                {
                    s5 = s5 + "." + tipoextension;
                }
            String s6 = "select count(*) from sad.documentosp where tipodoc = '" + tipodocumentos + "' and ";
            s6 = s6 + "proceso = '" + tipoprocesos + "' and ";
            s6 = s6 + "version = '" + versionp + "' and ";
            s6 = s6 + "descripcion = '" + descripcion + "' and ";
            s6 = s6 + "cliente = '" + clientes + "' and ";
            s6 = s6 + "verdoc = '" + versiond + "' and ";
            s6 = s6 + "extension = '" + tipoextension + "' and ";
            s6 = s6 + "proyecto = '" + proyecto + "'";
            if(adicional != null && adicional.length() > 0)
                s6 = s6 + " and adicional = '" + adicional + "'";
            long l = ADatos.ConsultaDB(s6);
            rs = ADatos.getResult();
            if(l == 1L)
                integer = (Integer)rs.elementAt(0);
            if(integer.intValue() == 0)
            {
                File file = new File(RutaDocumentos + "sad\\proyecto\\" + clientes + "\\" + proyecto + "\\" + s5);
                fileitem.write(file);
                Fechav = AFunc.ObtieneFecha();
                Integer integer5 = new Integer((String)Fechav.elementAt(0));
                String s7 = "select tiemporetencion,tiempomedida,wf_ejecucion  from gdc.documentoscalidad";
                s7 = s7 + " where tipodocumento='" + tipodocumentos + "'";
                s7 = s7 + " and proceso='" + tipoprocesos + "'";
                s7 = s7 + " and descripcion='" + descripcion + "'";
                s7 = s7 + " and tiemporetencion > 0";
                long l1 = ADatos.ConsultaDB(s7);
                rs = ADatos.getResult();
                Integer integer3;
                if(rs.size() > 0)
                {
                    integer8 = (Integer)rs.elementAt(2);
                    Integer integer7 = (Integer)rs.elementAt(0);
                    String s4 = (String)rs.elementAt(1);
                    FechaAdd = AFunc.AddFecha(integer5.toString(), 5);
                    int j = integer7.intValue();
                    if(s4.compareTo("D\355a") == 0)
                        FechaAdd = AFunc.AddFecha(integer5.toString(), j);
                    if(s4.compareTo("Mes") == 0)
                    {
                        j *= 30;
                        FechaAdd = AFunc.AddFecha(integer5.toString(), j);
                    }
                    if(s4.compareTo("A\361o") == 0)
                    {
                        j *= 365;
                        FechaAdd = AFunc.AddFecha(integer5.toString(), j);
                    }
                    integer3 = new Integer((String)FechaAdd.elementAt(0));
                } else
                {
                    integer3 = new Integer(0x5f5e0ff);
                }
                s7 = "insert into sad.documentosp (tipodoc,proceso,version,descripcion,cliente,proyecto,adicional,verdoc,nombrearchivo,nombreorigen,extension,comentario,usuario,fechapublica,fechacaducidad,comentarioadicional,idwf,estado)";
                s7 = s7 + "values(";
                s7 = s7 + "'" + tipodocumentos + "',";
                s7 = s7 + "'" + tipoprocesos + "',";
                s7 = s7 + "'" + versionp + "',";
                s7 = s7 + "'" + descripcion + "',";
                s7 = s7 + "'" + clientes + "',";
                s7 = s7 + "'" + proyecto + "',";
                s7 = s7 + "'" + adicional + "',";
                s7 = s7 + "'" + versiond + "',";
                s7 = s7 + "'" + s5 + "',";
                s7 = s7 + "'" + s + "',";
                s7 = s7 + "'" + tipoextension + "',";
                s7 = s7 + "'" + comentario + "',";
                s7 = s7 + "'" + UsuarioReg + "',";
                s7 = s7 + integer5 + ",";
                s7 = s7 + integer3 + ",";
                s7 = s7 + "'" + ComentarioAdicional + "',";
                s7 = s7 + integer8.toString() + ",";
                if(integer8.intValue() > 0)
                    s7 = s7 + "'E')";
                else
                    s7 = s7 + "'A')";
                int i = ADatos.ModificaDB(s7);
                printwriter.println("<TABLE border='1' align='center' width='80%'>");
                printwriter.println("<tr>");
                printwriter.println("<td colspan='2' class='texttitulotabla'>Informaci\363n del Archivo</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Archivo Origen</td>");
                printwriter.println("<td class='textdesttabla'>" + s + "</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Archivo Destino</td>");
                printwriter.println("<td class='textdesttabla'>" + s5 + "</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Resultado BD</td>");
                if(i == 1)
                {
                    String s8 = "select id from sad.documentosp where tipodoc = '" + tipodocumentos + "'";
                    s8 = s8 + " and proceso = '" + tipoprocesos + "'";
                    s8 = s8 + " and version = '" + versionp + "'";
                    s8 = s8 + " and descripcion = '" + descripcion + "'";
                    s8 = s8 + " and verdoc = '" + versiond + "'";
                    ADatos.ConsultaDB(s8); 
                    rs = ADatos.getResult();
                    Integer integer1 = new Integer(0);
                    if(rs.size() > 0)
                        integer1 = (Integer)rs.elementAt(0);
                    printwriter.println("<TD class='textdesttabla'>El archivo se agreg\363 correctamente a la Base de datos (1)</TD>");
                    printwriter.println("</TR>");
                    printwriter.println("</TABLE>");
                    if(integer8.intValue() > 0)
                    {
                        String s9 = "SELECT wf.nombre nombrewf, wfp.usuarioautoriza, u.nombre nombreuser, u.mail, wfp.nombre nombrepaso from gdc.workflow wf, gdc.workflow_pasos wfp, sgc.usuarios u where wf.idwf = wfp.idwf and u.login = wfp.usuarioautoriza and wf.idwf = " + integer8.toString() + " and wfp.idpaso = 1";
                        long l2 = ADatos.ConsultaDB(s9);
                        rs = new Vector();
                        rs = ADatos.getResult();
                        String s11 = "";
                        String s13 = "";
                        String s15 = "";
                        String s17 = "";
                        System.out.println("data size=" + String.valueOf(rs.size()));
                        if(rs.size() > 0)
                        {
                            String s12 = (String)rs.elementAt(3);
                            String s14 = (String)rs.elementAt(2);
                            String s16 = (String)rs.elementAt(1);
                            String s18 = EnvMail.FormateaMail("Solicitud de Ejecuci\363n", "Se ha publicado un documento que requiere su aprobaci\363n.", "Paso", "Quien Autoriza", "WorkFlow", "Documento", "", ((String)rs.elementAt(4)).toString(), ((String)rs.elementAt(2)).toString(), ((String)rs.elementAt(0)).toString(), s5.toString(), "", "Ingrese al sistema y se le informar\341n los documentos que estan pendientes de autorizaci\363n o firma", "Importante: <BR>Este mail es generado de manera autom\341tica, por favor no responda a este mensaje.");
                            System.out.println("email formateado");
                            EnvMail.Envio(s12, "SERAPIS. Aviso de Autorizaci\363n", s18);
                            printwriter.println("<BR><BR><TABLE border='1' width='80%' align='center'>");
                            printwriter.println("<TR><TD class='textdesttabla' colspan='2'>El documento ha entrado a un proceso de flujo de aprobaci\363n de acuerdo a la siguiente informaci\363n:");
                            printwriter.println("<B><BR>WorkFlow: " + ((String)rs.elementAt(0)).toString());
                            printwriter.println("<BR>" + ((String)rs.elementAt(4)).toString() + ": " + ((String)rs.elementAt(2)).toString());
                            printwriter.println("</B></TD></TR>");
                            printwriter.println("<TR><TD class='textdesttabla' colspan='2'>Se han enviado las notificaciones necesarias para informar la solicitud de aprobaci\363n.</TD></TR>");
                            printwriter.println("</TABLE>");
                            Vector vector = new Vector();
                            vector = AFunc.ObtieneFechaHora();
                            String s10 = "insert into gdc.workflow_flujo (tipodoc,iddocumento,idwf,idpaso,detalle,accion,usuario,fecha,hora) values (";
                            s10 = s10 + "'sadp',";
                            s10 = s10 + integer1.toString() + ",";
                            s10 = s10 + integer8.toString() + ",";
                            s10 = s10 + "1,";
                            s10 = s10 + "'Publicaci\363n del archivo y solicitud de autorizaci\363n a: " + ((String)rs.elementAt(2)).toString() + "',";
                            s10 = s10 + "'P',";
                            s10 = s10 + "'" + s16 + "',";
                            s10 = s10 + vector.elementAt(0).toString() + ",";
                            s10 = s10 + vector.elementAt(1).toString() + ")";
                            i = ADatos.ModificaDB(s10);
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
                printwriter.println("<TABLE border='1' align='center' width='80%'>");
                printwriter.println("<tr>");
                printwriter.println("<td colspan='2' class='texttitulotabla'>Informaci\363n del Archivo</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Archivo Origen</td>");
                printwriter.println("<td class='textdesttabla'>" + s + "</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Archivo Destino</td>");
                printwriter.println("<td class='textdesttabla'>" + s5 + "</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Resultado BD</td>");
                printwriter.println("<TD class='textdesttabla'>El archivo ya existe. La copia se cancel\363</TD>");
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

    String tipodocumentos;
    String tipoprocesos;
    String versionp;
    String descripcion;
    String clientes;
    String proyecto;
    String adicional;
    String versiond;
    String tipoextension;
    String fuente;
    String sigue;
    String ficherosel;
    String comentario;
    String ComentarioAdicional;
    Vector rs;
    String UsuarioReg;
    Vector Fechav;
    Vector FechaAdd;
    String RutaSitio;
    String RutaDocumentos;
    AccDataBase ADatos;
    funciones AFunc;
    correo EnvMail;
}