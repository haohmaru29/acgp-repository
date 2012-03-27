package gdc;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;

import Acc.AccDataBase;
import Acc.correo;
import Acc.funciones;

public class uploadfichero extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public uploadfichero() {
        tipodocumentos = "";
        tipoprocesos = "";
        versionp = "";
        descripcion = "";
        clientes = "";
        adicional = "";
        versiond = "";
        tipoextension = "";
        fuente = "";
        publico = "";
        sigue = "";
        ficherosel = "";
        comentario = "";
        Fechav = new Vector();
        FechaAdd = new Vector();
        RutaSitio = "";
        RutaDocumentos = "";
        DriveTemporal = "";
        ADatos = new AccDataBase();
        AFunc = new funciones();
        EnvMail = new correo();
    }

    protected void processRequest(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        UserGDC = (String)httpsession.getValue("SerapisUser");
        NombreUser = (String)httpsession.getValue("SerapisNombre");
        if(UserGDC != null && UserGDC.length() > 0) {
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<form name='publica' method='POST' enctype='multipart/form-data' action='uploadfichero2.jsp'>");
            procesaFicheros(httpservletrequest, printwriter);
            printwriter.println("</form>");
            printwriter.println("<script language='javascript'>");
            printwriter.println("function valida()");
            printwriter.println("{");
            printwriter.println("  var DirArchivo;");
            printwriter.println("  if (document.publica.fichero.value != \"\")");
            printwriter.println("  {");
            printwriter.println("    DirArchivo = document.publica.fichero.value ;");
            printwriter.println("    document.publica.tipoextension.value = DirArchivo.substring(DirArchivo.length-3 ,DirArchivo.length); ");
            printwriter.println("    document.publica.submit()");
            printwriter.println("  }");
            printwriter.println("  else");
            printwriter.println("  {");
            printwriter.println("    alert(\"Debe especificar un archivo\");");
            printwriter.println("  }");
            printwriter.println("}");
            printwriter.println("</script>");
            printwriter.println("</body>");
            printwriter.println("</html>");
            printwriter.close();
        } else {
            AFunc.reindex(httpservletrequest, printwriter, 1, "GDC", 6);
        }
    }

    void depura(String s, PrintWriter printwriter) {
        printwriter.println(s);
    }

    public boolean procesaFicheros(HttpServletRequest httpservletrequest, PrintWriter printwriter) {
        String s = "";
        String s1 = "";
        String s2 = "";
        boolean flag = true;
        Integer integer = new Integer(0);
        String s3 = "";
        String s4 = "";
        String s5 = "";
        String s6 = "";
        String s7 = "";
        String s8 = "";
        String s9 = "";
        Integer integer1 = new Integer(0x5f5e0ff);
        Integer integer2 = new Integer(0);
        Integer integer3 = new Integer(0);
        String s10 = "";
        Integer integer4 = new Integer(0);
        try
        {

            rs = new Vector();
            ADatos.connect();
        	
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
                if(fileitem.getFieldName().equals("adicional"))
                    adicional = fileitem.getString();
                if(fileitem.getFieldName().equals("versiond"))
                    versiond = fileitem.getString();
                if(fileitem.getFieldName().equals("tipoextension"))
                    tipoextension = fileitem.getString();
                if(fileitem.getFieldName().equals("esfuente"))
                    fuente = fileitem.getString();
                if(fileitem.getFieldName().equals("sigue"))
                    sigue = fileitem.getString();
                if(fileitem.getFieldName().equals("comentario"))
                    comentario = fileitem.getString();
                if(fileitem.getFieldName().equals("fichero"))
                    s = fileitem.getName();
            } while(true);
            String s11 = tipodocumentos + "-" + tipoprocesos + "-" + versionp + "-" + descripcion;
            if(versiond != null)
                if(versiond.length() > 0)
                {
                    s11 = s11 + "-" + clientes;
                    if(adicional.length() > 0)
                        s11 = s11 + " " + adicional;
                    s11 = s11 + " v" + versiond + "." + tipoextension;
                } else
                {
                    s11 = s11 + "." + tipoextension;
                }
            
            String s12 = "select count(*) from gdc.documentos where tipodoc = '" + tipodocumentos + "' and ";
            s12 = s12 + "proceso = '" + tipoprocesos + "' and ";
            s12 = s12 + "version = '" + versionp + "' and ";
            s12 = s12 + "descripcion = '" + descripcion + "' and ";
            s12 = s12 + "extension = '" + tipoextension + "'";
            if(adicional != null && adicional.length() > 0)
                s12 = s12 + " and adicional = '" + adicional + "'";
            long l = ADatos.ConsultaDB(s12);
            rs = ADatos.getResult();
            if(l == 1L)
                integer = (Integer)rs.elementAt(0);
            if(integer.intValue() == 0)
            {
                String s13 = "select dirsitio,dirfiles from gdc.datosgenerales";
                long l1 = ADatos.ConsultaDB(s13);
                rs = ADatos.getResult();
                if(rs.size() > 0)
                {
                    RutaSitio = (String)rs.elementAt(0);
                    RutaDocumentos = (String)rs.elementAt(1);
                    File file = new File(RutaDocumentos + "\\gdc\\documentos\\" + tipoprocesos + "\\" + s11);
                    fileitem.write(file);
                    Fechav = AFunc.ObtieneFecha();
                    Integer integer5 = new Integer((String)Fechav.elementAt(0));
                    String s14 = "select tiemporetencion,tiempomedida,wf_aprobacion from gdc.documentoscalidad";
                    s14 = s14 + " where tipodocumento='" + tipodocumentos + "'";
                    s14 = s14 + " and proceso='" + tipoprocesos + "'";
                    s14 = s14 + " and descripcion='" + descripcion + "'";
                    s14 = s14 + " and tiemporetencion > 0";
                    long l2 = ADatos.ConsultaDB(s14);
                    rs = ADatos.getResult();
                    Integer integer6;
                    if(rs.size() > 0)
                    {
                        Integer integer7 = (Integer)rs.elementAt(0);
                        String s15 = (String)rs.elementAt(1);
                        if(s15.compareTo("D\355a") == 0)
                            FechaAdd = AFunc.AddFecha(integer5.toString(), integer7.intValue());
                        if(s15.compareTo("Mes") == 0)
                            FechaAdd = AFunc.AddFecha(integer5.toString(), integer7.intValue() * 30);
                        if(s15.compareTo("A\361o") == 0)
                            FechaAdd = AFunc.AddFecha(integer5.toString(), integer7.intValue() * 365);
                        integer6 = new Integer((String)FechaAdd.elementAt(0));
                        integer4 = (Integer)rs.elementAt(2);
                    } else
                    {
                        integer6 = new Integer(0x5f5e0ff);
                    }
                    s14 = "insert into gdc.documentos (tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,nombreorigen,extension,comentario,fuente,fechapublica,fechacaducidad,usuario,idwf,estado)";
                    s14 = s14 + "values(";
                    s14 = s14 + "'" + tipodocumentos + "',";
                    s14 = s14 + "'" + tipoprocesos + "',";
                    s14 = s14 + "'" + versionp + "',";
                    s14 = s14 + "'" + descripcion + "',";
                    s14 = s14 + "'" + clientes + "',";
                    s14 = s14 + "'" + adicional + "',";
                    s14 = s14 + "'" + versiond + "',";
                    s14 = s14 + "'" + s11 + "',";
                    s14 = s14 + "'" + s + "',";
                    s14 = s14 + "'" + tipoextension + "',";
                    s14 = s14 + "'" + comentario + "',";
                    s14 = s14 + "'" + fuente + "',";
                    s14 = s14 + Fechav.elementAt(0).toString() + ",";
                    s14 = s14 + integer6.toString() + ",";
                    s14 = s14 + "'" + UserGDC + "',";
                    s14 = s14 + integer4.toString() + ",";
                    if(integer4.intValue() > 0)
                        s14 = s14 + "'E')";
                    else
                        s14 = s14 + "'A')";
                    int i = ADatos.ModificaDB(s14);
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
                    printwriter.println("<td class='textdesttabla'>" + s11 + "</td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla'>Resultado BD</td>");
                    if(i == 1)
                    {
                        printwriter.println("<TD class='textdesttabla'>El archivo se agreg\363 correctamente a la Base de datos (1)</TD></TR>");
                        printwriter.println("</TABLE>");
                        if(fuente.compareTo("N") == 0)
                        {
                            String s16 = "select td.descripcion,p.descripcion from gdc.procesos p, gdc.tipodocumentos td where td.sigla = '" + tipodocumentos + "' and p.sigla = '" + tipoprocesos + "'";
                            long l3 = ADatos.ConsultaDB(s16);
                            rs = new Vector();
                            rs = ADatos.getResult();
                            if(rs.size() > 0)
                            {
                                String s18 = (String)rs.elementAt(0);
                                String s19 = (String)rs.elementAt(1);
                                String s21 = "INSERT into gdc.notificaciones (fecha,tiponotificacion,proceso,tipo,version,archivo,estado,fechaestado,usuario) VALUES (";
                                s21 = s21 + "10" + ",";
                                s21 = s21 + "'A',";
                                s21 = s21 + "'" + s19 + "',";
                                s21 = s21 + "'" + s18 + "',";
                                s21 = s21 + versionp + ",";
                                s21 = s21 + "'" + s11 + "',";
                                s21 = s21 + "'P',0,";
                                s21 = s21 + "'" + NombreUser + "')";
                                i = ADatos.ModificaDB(s21);
                            }
                        }
                        String s17 = "select id from gdc.documentos where tipodoc = '" + tipodocumentos + "'";
                        s17 = s17 + " and proceso = '" + tipoprocesos + "'";
                        s17 = s17 + " and version = '" + versionp + "'";
                        s17 = s17 + " and descripcion = '" + descripcion + "'";
                        long l4 = ADatos.ConsultaDB(s17);
                        rs = ADatos.getResult();
                        Integer integer8 = new Integer(0);
                        if(rs.size() > 0)
                            integer8 = (Integer)rs.elementAt(0);
                        if(sigue.compareTo("S") == 0)
                        {
                            printwriter.println("<br><br>");
                            printwriter.println("<input name='nombrearchivo' language='javascript' style='WIDTH: 70px' value='" + integer8.toString() + "' type='hidden'>");
                            printwriter.println("<input type='hidden' name='tipoextension' value=''>");
                            printwriter.println("<TABLE border='1' width='80%' align='center'>");
                            printwriter.println("<tr>");
                            if(fuente.compareTo("S") == 0)
                                printwriter.println("<td colspan='2' class='texttitulotabla'>Seleccione archivo a publicar</td>");
                            else
                                printwriter.println("<td colspan='2' class='texttitulotabla'>Seleccione archivo fuente</td>");
                            printwriter.println("</tr>");
                            printwriter.println("<tr>");
                            printwriter.println("<td class='textdesttabla'>Archivo Origen</td>");
                            printwriter.println("<td class='textdesttabla'>" + s11 + "</td>");
                            printwriter.println("</tr>");
                            printwriter.println("<tr>");
                            printwriter.println("<td class='textdesttabla'>Archivo</td>");
                            printwriter.println("<td class='textdesttabla'><input type='file' class='btnNormal' name='fichero' style='WIDTH: 300px'></td>");
                            printwriter.println("</tr>");
                            printwriter.println("</TABLE>");
                            printwriter.println("<BR><BR>");
                            printwriter.println("<center>");
                            printwriter.println("<input class='fondoinput' type='button' name='ok' value='Subir' language='javascript' onclick='return valida();'>");
                            printwriter.println("</center>");
                        } else
                        if(integer4.intValue() > 0)
                        {
                            String s20 = "SELECT wf.nombre nombrewf, wfp.usuarioautoriza, u.nombre nombreuser, u.mail, wfp.nombre nombrepaso from gdc.workflow wf, gdc.workflow_pasos wfp, sgc.usuarios u where wf.idwf = wfp.idwf and u.login = wfp.usuarioautoriza and wf.idwf = " + integer4.toString() + " and wfp.idpaso = 1";
                            long l5 = ADatos.ConsultaDB(s20);
                            rs = new Vector();
                            rs = ADatos.getResult();
                            String s22 = "";
                            String s23 = "";
                            String s24 = "";
                            String s25 = "";
                            if(rs.size() > 0)
                            {
                                String s26 = (String)rs.elementAt(3);
                                String s27 = (String)rs.elementAt(2);
                                String s28 = (String)rs.elementAt(1);
                                String s29 = EnvMail.FormateaMail("Solicitud de Aprobaci\363n", "Se ha publicado un documento que requiere su aprobaci\363n.", "Paso", "Quien Autoriza", "WorkFlow", "Documento", "", ((String)rs.elementAt(4)).toString(), ((String)rs.elementAt(2)).toString(), ((String)rs.elementAt(0)).toString(), s11.toString(), "", "Ingrese al sistema y se le informar\341n los documentos que estan pendientes de autorizaci\363n o firma", "Importante: <BR>Este mail es generado de manera autom\341tica, por favor no responda a este mensaje.");
                                EnvMail.Envio(s26, "SERAPIS. Aviso de Autorizaci\363n", s29);
                                printwriter.println("<BR><BR><TABLE border='1' width='80%' align='center'>");
                                printwriter.println("<TR><TD class='textdesttabla' colspan='2'>El documento ha entrado a un proceso de flujo de aprobaci\363n de acuerdo a la siguiente informaci\363n:");
                                printwriter.println("<B><BR>WorkFlow: " + ((String)rs.elementAt(0)).toString());
                                printwriter.println("<BR>" + ((String)rs.elementAt(4)).toString() + ": " + ((String)rs.elementAt(2)).toString());
                                printwriter.println("</B></TD></TR>");
                                printwriter.println("<TR><TD class='textdesttabla' colspan='2'>Se han enviado las notificaciones necesarias para informar la solicitud de aprobaci\363n.</TD></TR>");
                                printwriter.println("</TABLE>");
                                Vector vector = new Vector();
                                vector = AFunc.ObtieneFechaHora();
                                String s30 = "insert into gdc.workflow_flujo (tipodoc,iddocumento,idwf,idpaso,detalle,accion,usuario,fecha,hora) values (";
                                s30 = s30 + "'gdc',";
                                s30 = s30 + integer8.toString() + ",";
                                s30 = s30 + integer4.toString() + ",";
                                s30 = s30 + "1,";
                                s30 = s30 + "'Publicaci\363n del archivo y solicitud de autorizaci\363n a: " + ((String)rs.elementAt(2)).toString() + "',";
                                s30 = s30 + "'P',";
                                s30 = s30 + "'" + s28 + "',";
                                s30 = s30 + vector.elementAt(0).toString() + ",";
                                s30 = s30 + vector.elementAt(1).toString() + ")";
                                i = ADatos.ModificaDB(s30);
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
                    printwriter.println("<td colspan='2' class='texttitulotabla'>Informaci\363n de Publicaci\363n</td>");
                    printwriter.println("</tr>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla' colspan='2'><B>No esta definido la ruta de destino de los archivos. <BR>Informe al administrador para solucionar el problema.</B></td>");
                    printwriter.println("</tr>");
                    printwriter.println("</table>");
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
                printwriter.println("<td class='textdesttabla'>" + s11 + "</td>");
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

    String tipodocumentos;
    String tipoprocesos;
    String versionp;
    String descripcion;
    String clientes;
    String adicional;
    String versiond;
    String tipoextension;
    String fuente;
    String publico;
    String sigue;
    String ficherosel;
    String comentario;
    Vector rs;
    Vector Fechav;
    Vector FechaAdd;
    String UserGDC;
    String NombreUser;
    String RutaSitio;
    String RutaDocumentos;
    String DriveTemporal;
    AccDataBase ADatos;
    funciones AFunc;
    correo EnvMail;
}