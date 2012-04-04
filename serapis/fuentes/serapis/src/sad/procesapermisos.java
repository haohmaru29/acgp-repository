package sad;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class procesapermisos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public procesapermisos() {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        Tipo = "";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException {
        String s = "";
        String s3 = "";
        String s4 = "";
        httpservletresponse.setContentType("text/html");
        java.io.PrintWriter printwriter = httpservletresponse.getWriter();
        Tipo = httpservletrequest.getParameter("TIPO");
        if(Tipo == null && Tipo.length() == 0)
            Tipo = "1";
        HttpSession httpsession = httpservletrequest.getSession(true);
        UserReg = (String)httpsession.getValue("SerapisUser");
        if(UserReg != null && UserReg.length() > 0) {
            ADatos.connect();
            rs = new Vector();
            if(Tipo.compareTo("1") == 0)
            {
                String s10 = httpservletrequest.getParameter("lstprocesos");
                String s11 = httpservletrequest.getParameter("lstperfiles");
                String s12 = httpservletrequest.getParameter("lstusuarios");
                String s1 = httpservletrequest.getParameter("hddopcion");
                byte byte0 = 0;
                String s13 = "";
                String s17 = "U";
                if(s10.length() > 0)
                {
                    if(s11.length() > 0 || s12.length() > 0)
                    {
                        if(s1.length() > 0)
                        {
                            byte0 = 1;
                        } else
                        {
                            String s14 = "Debe seleccionar una opci\363n de acceso";
                            byte0 = 2;
                        }
                    } else
                    {
                        String s15 = "Debe seleccionar un perfil o usuario";
                        byte0 = 2;
                    }
                } else
                {
                    String s16 = "Debe seleccionar un proceso";
                    byte0 = 2;
                }
                if(byte0 == 1)
                {
                    if(s11.length() > 0)
                        s17 = "P";
                    else
                        s11 = "0";
                    String s7 = "insert into sad.permisosproceso (proceso,tipo,usuario,idperfil,tipopermiso) values (";
                    s7 = s7 + "'" + s10 + "',";
                    s7 = s7 + "'" + s17 + "',";
                    s7 = s7 + "'" + s12 + "',";
                    s7 = s7 + s11 + ",";
                    s7 = s7 + "'" + s1 + "')";
                    int i = ADatos.ModificaDB(s7);
                }
            }
            if(Tipo.compareTo("2") == 0)
            {
                String s2 = httpservletrequest.getParameter("hddopcion");
                String s5 = httpservletrequest.getParameter("hddid");
                String s8 = "update sad.permisosproceso set tipopermiso='" + s2 + "' where idpermiso = " + s5.toString();
                int j = ADatos.ModificaDB(s8);
            }
            if(Tipo.compareTo("3") == 0)
            {
                String s6 = httpservletrequest.getParameter("hddid");
                String s9 = "delete from sad.permisosproceso where idpermiso = " + s6.toString();
                int k = ADatos.ModificaDB(s9);
            }
            httpservletresponse.sendRedirect("permisos.jsp?TIPO=PERM&SUBTIPO=P");
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "SAD", 6);
        }
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    String UserReg;
    String Tipo;
}