// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   download.java

package workflow;

import Acc.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;

public class download extends HttpServlet
{

    public download()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        ACopia = new CopiadorDeArchivos();
        UserGDC = "";
        RutaSitio = "";
        RutaDocumentos = "";
        DireccionArchivo = "";
        TipoDoc = "";
        TipoDespliegue = "N";
        RutaBase = "sad\\documentos";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        String s = "";
        String s1 = "";
        String s2 = "";
        String s3 = "";
        String s4 = "";
        String s5 = "";
        String s6 = "";
        String s7 = "";
        boolean flag = true;
        HttpSession httpsession = httpservletrequest.getSession(true);
        UserGDC = (String)httpsession.getValue("SerapisUser");
        if(UserGDC != null && UserGDC.length() > 0)
        {
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            ADatos.connect();
            if(ObtieneRutas() == 1)
            {
                boolean flag1;
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
                    Object obj = null;
                    do
                    {
                        if(!iterator.hasNext())
                            break;
                        FileItem fileitem = (FileItem)iterator.next();
                        if(fileitem.getFieldName().equals("PROCESO"))
                            s = fileitem.getString();
                        if(fileitem.getFieldName().equals("ARCHIVO"))
                            s1 = fileitem.getString();
                        if(fileitem.getFieldName().equals("DIRECTORIO"))
                            s2 = fileitem.getString();
                        if(fileitem.getFieldName().equals("ID"))
                            s3 = fileitem.getString();
                        if(fileitem.getFieldName().equals("IDDOC"))
                            s4 = fileitem.getString();
                        if(fileitem.getFieldName().equals("IDWF"))
                            s5 = fileitem.getString();
                        if(fileitem.getFieldName().equals("IDPASO"))
                            s6 = fileitem.getString();
                        if(fileitem.getFieldName().equals("BD"))
                            s7 = fileitem.getString();
                    } while(true);
                }
                catch(Exception exception)
                {
                    flag1 = false;
                }
                //CopiadorDeArchivos _tmp = ACopia;

                if(s7.compareTo("sad") == 0 || s7.compareTo("sadp") == 0)
                    RutaBase = "sad\\documentos";
                else
                    RutaBase = "gdc\\documentos";
                
                CopiadorDeArchivos.copia(RutaDocumentos + RutaBase + "\\" + s + "\\" + s1, RutaSitio + "WEB-INF\\downloadroot\\" + s1);
                printwriter.println("<script language='javascript'>");
                printwriter.println("window.open(\"../" + s2 + "/descarga.jsp?FILEDESC=" + s1 + "\",\"_blank\");");
                printwriter.println("window.open(\"ejecutawf.jsp?ID=" + s3 + "&IDDOC=" + s4 + "&IDWF=" + s5 + "&BD=" + s7 + "&IDPASO=" + s6 + "\",\"_self\");");
                printwriter.println("</script>");
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
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "GDC", 6);
        }
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
    funciones AFunc;
    CopiadorDeArchivos ACopia;
    Vector rs;
    Vector rsfile;
    String UserGDC;
    String RutaSitio;
    String RutaDocumentos;
    String DireccionArchivo;
    String TipoDoc;
    String TipoDespliegue;
    String RutaBase;
}