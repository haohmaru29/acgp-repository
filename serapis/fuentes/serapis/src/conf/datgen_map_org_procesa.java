package conf;

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
import Acc.funciones;

public class datgen_map_org_procesa extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public datgen_map_org_procesa()
    {
        ADatos = new AccDataBase();
        func = new funciones();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        boolean flag = false;
        long l = 1L;
        Vector vector = new Vector();
        response.setContentType("text/html");
        PrintWriter printwriter = response.getWriter();
        HttpSession httpsession = request.getSession(true);
        String s = (String)httpsession.getValue("SerapisUser");
        if(s != null && s.length() > 0) {
            String s1 = "";
            String s2 = "";
            try {
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
                List list = diskfileupload.parseRequest(request);
                Iterator iterator = list.iterator();
                FileItem fileitem = null;
                do {
                    if(!iterator.hasNext())
                        break;
                    fileitem = (FileItem)iterator.next();
                    if(fileitem.getFieldName().equals("tipoarch"))
                        s1 = fileitem.getString();
                    if(fileitem.getFieldName().equals("archivo"))
                        s2 = fileitem.getName();
                } while(true);
                sql = "select dirsitio,dirfiles from gdc.datosgenerales";
                ADatos.ConsultaDB(sql);
                rs = ADatos.getResult();
                if(rs.size() > 0) {
                    String s3 = (String)rs.elementAt(0);
                    String s4 = (String)rs.elementAt(1);
                    String s5 = s2.substring(s2.length() - 3, s2.length());
                    File file;
                    if(s1.compareTo("ORG") == 0)
                        file = new File(s3 + "images\\SERAPIS_ORGANIGRAMA." + s5);
                    else
                        file = new File(s3 + "images\\SERAPIS_MAPA." + s5);
                    sql = "update  gdc.datosgenerales set ";
                    if(s1.compareTo("ORG") == 0)
                        sql = sql + " organigrama = 'SERAPIS_ORGANIGRAMA." + s5 + "'";
                    else
                        sql = sql + " mapa = 'SERAPIS_MAPA." + s5 + "'";
                    int i = ADatos.ModificaDB(sql);
                    fileitem.write(file);
                }
            }
            catch(Exception exception) {
                printwriter.println("<BR> Error de Aplicaci\363n " + exception.getMessage());
            } 
            printwriter.println("<HTML>");
            printwriter.println("<HEAD>");
            printwriter.println("<title>SERAPIS. Sistema de Gesti\363n de Calidad.</title>");
            printwriter.println("</HEAD>");
            printwriter.println("</HEAD>");
            printwriter.println("<script language=\"javascript\">");
            printwriter.println("alert(\"El archivo se ha modificado. Para ver el cambio, ingrese a la opci\363n en el menu principal de SERAPIS.\");");
            printwriter.println("window.open(\"datos_generales.jsp\",\"datos\");");
            printwriter.println("</script>");
            printwriter.println("<body bgcolor='#FFFFFF'>");
            printwriter.println("</BODY>");
            printwriter.println("</HTML>");
        } else {
            func.reindex(request, printwriter, 1, "CONF", 6);
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