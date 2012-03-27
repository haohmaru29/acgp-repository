package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class procesatipodoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public procesatipodoc() {
        ADatos = new AccDataBase();
        func = new funciones();
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        int i = 0;
        Vector vector = new Vector();
        long l = 1L;
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s = (String)httpsession.getValue("SerapisUser");
        if(s != null && s.length() > 0)
        {
            String s1 = httpservletrequest.getParameter("TIPO");
            if(s1 == null)
                s1 = "";
            String s2 = httpservletrequest.getParameter("idtipodoc");
            String s3 = httpservletrequest.getParameter("descripcion");
            if(s1.compareTo("UPD") == 0)
            {
                sql = "update gdc.tipodocumentos set ";
                sql = sql + " descripcion = '" + s3 + "'";
                sql = sql + " where sigla = '" + s2 + "'";
                i = ADatos.ModificaDB(sql);
            }
            if(s1.compareTo("ADD") == 0)
            {
                sql = "insert into gdc.tipodocumentos (sigla,descripcion) values (";
                sql = sql + "'" + s2 + "',";
                sql = sql + "'" + s3 + "')";
                i = ADatos.ModificaDB(sql);
            }
            if(s1.compareTo("DEL") == 0)
            {
                sql = "select distinct tipodoc  from gdc.documentos where tipodoc ='" + s2 + "' UNION ";
                sql = sql + "select distinct tipodoc  from gdc.historico where tipodoc ='" + s2 + "' UNION ";
                sql = sql + "select distinct tipodoc  from gdc.reciclaje where tipodoc ='" + s2 + "' UNION ";
                sql = sql + "select distinct tipodoc  from sad.documentos where tipodoc ='" + s2 + "' UNION ";
                sql = sql + "select distinct tipodoc  from sad.documentosp where tipodoc ='" + s2 + "' UNION ";
                sql = sql + "select distinct tipodoc  from sad.reciclaje where tipodoc ='" + s2 + "'";
                l = ADatos.ConsultaDB(sql);
                rs = ADatos.getResult();
                l = rs.size();
                if(l == 0L)
                {
                    sql = "delete from gdc.tipodocumentos where sigla = '" + s2 + "'";
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
                printwriter.println("alert(\"Problemas al agregar en la Base de datos. Es posible que el c\363digo este duplicado.\");");
            if(s1.compareTo("DEL") == 0)
            {
                if(l > 0L)
                    printwriter.println("alert(\"El tipo de documento esta siendo usado. No es posible eliminarlo.\");");
                if(l < 0L)
                    printwriter.println("alert(\"Problemas al eliminar la extension.\");");
            }
            printwriter.println("window.open(\"listadocumentos.jsp\",\"datos\");");
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