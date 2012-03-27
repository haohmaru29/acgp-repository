package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class inicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public inicio() {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        Licencia = "0";
        Usuario = "";
        Sistema = "SAD";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        httpsession.putValue("SerapisSis", "SAD");
        Licencia = (String)httpsession.getValue("SerapisLic");
        if(Licencia == null || Licencia.length() == 0)
            Licencia = "";
        Usuario = (String)httpsession.getValue("SerapisUser");
        String s = httpservletrequest.getParameter("TIPO");
        if(s == null)
            s = "USR";
        if(Usuario != null && Usuario.length() > 0)
        {
            if(Licencia.compareTo("1") == 0)
            {
                String s1 = "select login from sgc.perfil where login='" + Usuario + "' and administrador='S'";
                rs = new Vector();
                ADatos.connect();
                int i = ADatos.ConsultaDB(s1);
                rs = ADatos.getResult();
                if(rs.size() > 0)
                {
                    printwriter.println("<html>");
                    printwriter.println("<head>");
                    printwriter.println("</head>");
                    printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='serapis.css'>");
                    printwriter.println("<TITLE>SERAPIS. Sistema de Gesti\363n de Calidad.</TITLE>");
                    printwriter.println("<frameset framespacing='0' border='true' frameborder='0' cols='21%,*'>");
                    printwriter.println("  <frame name='menu' src='menu.jsp?TIPO=" + s + "' scrolling='no' noresize>");
                    if(s.compareTo("USR") == 0)
                        printwriter.println("  <frame name='datos' src='usuarios.jsp' scrolling='auto' noresize>");
                    if(s.compareTo("PEF") == 0)
                        printwriter.println("  <frame name='datos' src='perfiles.jsp' scrolling='auto' noresize>");
                    if(s.compareTo("CLI") == 0)
                        printwriter.println("  <frame name='datos' src='clientes.jsp' scrolling='auto' noresize>");
                    if(s.compareTo("PROC") == 0)
                        printwriter.println("  <frame name='datos' src='procesos.jsp' scrolling='auto' noresize>");
                    if(s.compareTo("TDOC") == 0)
                        printwriter.println("  <frame name='datos' src='listadocumentos.jsp' scrolling='auto' noresize>");
                    if(s.compareTo("EXT") == 0)
                        printwriter.println("  <frame name='datos' src='listaextensiones.jsp' scrolling='auto' noresize>");
                    if(s.compareTo("CARO") == 0)
                        printwriter.println("  <frame name='datos' src='listacargosroles.jsp' scrolling='auto' noresize>");
                    if(s.compareTo("LIMA") == 0)
                        printwriter.println("  <frame name='datos' src='listamaestra.jsp' scrolling='auto' noresize>");
                    if(s.compareTo("DIST") == 0)
                        printwriter.println("  <frame name='datos' src='listadistribucion.jsp' scrolling='auto' noresize>");
                    if(s.compareTo("WF") == 0)
                        printwriter.println("  <frame name='datos' src='workflow.jsp' scrolling='auto' noresize>");
                    if(s.compareTo("DG") == 0)
                        printwriter.println("  <frame name='datos' src='datos_generales.jsp' scrolling='auto' noresize>");
                    if(s.compareTo("ORG") == 0)
                        printwriter.println("  <frame name='datos' src='datgen_map_org.jsp?TIPO=ORG' scrolling='auto' noresize>");
                    if(s.compareTo("MAPA") == 0)
                        printwriter.println("  <frame name='datos' src='datgen_map_org.jsp?TIPO=MAPA' scrolling='auto' noresize>");
                    if(s.compareTo("LIDER") == 0)
                        printwriter.println("  <frame name='datos' src='datgen_lideres.jsp?TIPO=LISTA' scrolling='auto' noresize>");
                    if(s.compareTo("AUDI") == 0)
                        printwriter.println("  <frame name='datos' src='plan_auditoria.jsp' scrolling='auto' noresize>");
                    if(s.compareTo("GPROC") == 0)
                        printwriter.println("  <frame name='datos' src='gestion_procesos.jsp' scrolling='auto' noresize>");
                    if(s.length() == 0)
                        printwriter.println("  <frame name='datos' src='vacio.htm' scrolling='yes' noresize>");
                    printwriter.println("</frameset>");
                    printwriter.println("</body>");
                    printwriter.println("</html>");
                } else
                {
                    printwriter.println("<HTML>");
                    printwriter.println("<HEAD>");
                    printwriter.println("<title>SERAPIS. Sistema de Gesti\363n de Calidad.</title>");
                    printwriter.println("</HEAD>");
                    printwriter.println("</HEAD>");
                    printwriter.println("<script language=\"javascript\">");
                    printwriter.println("alert(\"Debe ser administrador para ingresar a esta opci\363n.\");");
                    printwriter.println("window.open(\"../cuerpo.jsp\",\"cuerpo\");");
                    printwriter.println("</script>");
                    printwriter.println("<body bgcolor='#FFFFFF'>");
                    printwriter.println("</BODY>");
                    printwriter.println("</HTML>");
                }
            } else
            {
                AFunc.reindex(httpservletrequest, printwriter, 1, "CONF", 8);
            }
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "CONF", 0);
        }
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    String sql;
    String Licencia;
    String Usuario;
    String Sistema;
}