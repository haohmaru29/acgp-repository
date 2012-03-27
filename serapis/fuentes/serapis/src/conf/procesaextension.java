package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;

public class procesaextension extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public procesaextension() {
        ADatos = new AccDataBase();
        func = new funciones();
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        int i = 0;
        long l = 1L;
        Vector vector = new Vector();
        
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s = (String)httpsession.getValue("SerapisUser");
        if(s != null && s.length() > 0)
        {
            String s1 = httpservletrequest.getParameter("TIPO");
            if(s1 == null)
                s1 = "";
            String s2 = "";
            String s4 = "";
            String s6 = "";
            if(s1.compareTo("UPD") == 0)
            {
                s2 = httpservletrequest.getParameter("idextension");
                s4 = httpservletrequest.getParameter("descripcion");
                sql = "update gdc.extension set ";
                sql = sql + " descripcion = '" + s4 + "'";
                sql = sql + " where sigla = '" + s2 + "'";
                i = ADatos.ModificaDB(sql);
            }
            if(s1.compareTo("ADD") == 0)
                try
                {
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
                        if(fileitem.getFieldName().equals("idextension"))
                            s2 = fileitem.getString();
                        if(fileitem.getFieldName().equals("descripcion"))
                            s4 = fileitem.getString();
                        String s7;
                        if(fileitem.getFieldName().equals("archivo"))
                            s7 = fileitem.getName();
                    } while(true);
                    sql = "select dirsitio,dirfiles from gdc.datosgenerales";
                    ADatos.ConsultaDB(sql);
                    rs = ADatos.getResult();
                    if(rs.size() > 0)
                    {
                        String s8 = (String)rs.elementAt(0);
                        String s9 = (String)rs.elementAt(1);
                        File file = new File(s8 + "images\\ext_" + s2 + ".gif");
                        fileitem.write(file);
                        sql = "insert into gdc.extension (sigla,imagen,descripcion) values (";
                        sql = sql + "'" + s2 + "',";
                        sql = sql + "'" + s2 + ".gif',";
                        sql = sql + "'" + s4 + "')";
                        i = ADatos.ModificaDB(sql);
                    }
                }
                catch(Exception exception)
                {
                    printwriter.println("<BR> Error de Aplicaci\363n " + exception.getMessage());
                }
            if(s1.compareTo("DEL") == 0)
            {
                String s3 = httpservletrequest.getParameter("idextension");
                String s5 = httpservletrequest.getParameter("descripcion");
                sql = "select distinct extension from gdc.documentos where extension='" + s3 + "' UNION ";
                sql = sql + "select distinct extension from gdc.historico where extension='" + s3 + "' UNION ";
                sql = sql + "select distinct extension from gdc.reciclaje where extension='" + s3 + "' UNION ";
                sql = sql + "select distinct extension from sad.documentos where extension='" + s3 + "' UNION ";
                sql = sql + "select distinct extension from sad.documentosp where extension='" + s3 + "' UNION ";
                sql = sql + "select distinct extension from sad.reciclaje where extension='" + s3 + "'";
                l = ADatos.ConsultaDB(sql);
                rs = ADatos.getResult();
                l = rs.size();
                if(l == 0L)
                {
                    sql = "delete from gdc.extension where sigla = '" + s3 + "'";
                    i = ADatos.ModificaDB(sql);
                }
            }
            printwriter.println("<HTML>");
            printwriter.println("<HEAD>");
            printwriter.println("<title>SERAPIS. Sistema de Gesti\363n de Calidad.</title>");
            printwriter.println("</HEAD>");
            printwriter.println("</HEAD>");
            printwriter.println("<script language=\"javascript\">");
            if(i == -1)
                printwriter.println("alert(\"Problemas al agregar o quitar en la Base de datos. Es posible que el c\363digo este duplicado.\");");
            if(s1.compareTo("DEL") == 0)
            {
                if(l > 0L)
                    printwriter.println("alert(\"La extensi\363n esta siendo usada. No es posible eliminarla.\");");
                if(l < 0L)
                    printwriter.println("alert(\"Problemas al eliminar la extension.\");");
            }
            printwriter.println("window.open(\"listaextensiones.jsp\",\"datos\");");
            printwriter.println("</script>");
            printwriter.println("<body bgcolor='#FFFFFF'>");
            printwriter.println("</BODY>");
            printwriter.println("</HTML>");
        } else
        {
            func.reindex(httpservletrequest, printwriter, 1, "CONF", 6);
        }
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    AccDataBase ADatos;
    funciones func;
    Vector rs;
    String sql;
}