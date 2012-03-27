package gdc;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class workflow extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public workflow() {
        ADatos = new AccDataBase();
        AFunc = new funciones();
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s = (String)httpsession.getValue("SerapisUser");
        if(s != null && s.length() > 0)
        {
            String s1 = "select login from sgc.perfil where login='" + s + "' and administrador='S'";
            rs = new Vector();
            ADatos.connect();
            int i = ADatos.ConsultaDB(s1);
            rs = ADatos.getResult();
            if(rs.size() > 0)
            {
                printwriter.println("<html>");
                printwriter.println("<head>");
                printwriter.println("</head>");
                printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
                printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
                printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
                AFunc.cargamenu(printwriter, 1);
                String s2 = httpservletrequest.getParameter("TIPO");
                if(s2 == null || s2.length() == 0)
                    s2 = "LISTA";
                if(s2.compareTo("LISTA") == 0)
                {
                    printwriter.println("<table border='0' width='80%' align='center'>");
                    printwriter.println("<tr><td align='center'  class='texttituloarea'>WorkFlow</td></tr>");
                    printwriter.println("<tr><td align='center'>");
                    printwriter.println("<table border='1' width='90%' align='center'>");
                    printwriter.println("<tr>");
                    printwriter.println("<td width='35%' class='texttitulotabla'>WorkFlow</td>");
                    printwriter.println("<td width='20%' class='texttitulotabla'>Tipo</td>");
                    printwriter.println("<td width='35%' class='texttitulotabla'>Cerrado por</td>");
                    printwriter.println("<td width='10%' class='texttitulotabla'>Estado</td>");
                    printwriter.println("</tr>");
                    CargaWorkFlow(printwriter);
                    printwriter.println("</table>");
                    printwriter.println("<br><br>");
                    printwriter.println("<center>");
                    printwriter.println("<input type='button' name='nuevo' value='Nuevo' class='fondoinput' language='javascript' onclick='window.open(\"workflownuevo.jsp\",\"datos\")'>&nbsp;<input type='button' name='BtnImprimir' value='Imprimir' class='fondoinput' language='javascript' onclick='window.print()'></td>");
                    printwriter.println("</center>");
                }
                printwriter.println("</body>");
                printwriter.println("</html>");
            } else
            {
                AFunc.reindex(httpservletrequest, printwriter, 1, "CONF", 7);
            }
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "CONF", 6);
        }
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    void CargaWorkFlow(PrintWriter printwriter)
    {
        Integer integer = new Integer(0);
        String s = "";
        String s2 = "";
        String s4 = "";
        String s6 = "";
        Integer integer2 = new Integer(0);
        rs = new Vector();
        String s8 = "select wf.idwf,wf.nombre,wf.tipowf,wf.estado,wf.creadopor,wf.fechacreacion,u.nombre as nombreusuario";
        s8 = s8 + " from gdc.workflow wf, sgc.usuarios u";
        s8 = s8 + " where wf.visible = 'S' and u.login = wf.creadopor";
        s8 = s8 + " order by wf.nombre,wf.estado";
        int i = ADatos.ConsultaDB(s8);
        rs = ADatos.getResult();
        if(rs.size() > 0)
        {
            for(int j = 0; j < rs.size(); j += 7)
            {
                Integer integer1 = (Integer)rs.elementAt(j);
                String s1 = (String)rs.elementAt(j + 1);
                String s3 = (String)rs.elementAt(j + 2);
                String s5 = (String)rs.elementAt(j + 3);
                String s7 = (String)rs.elementAt(j + 6);
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'><a href='workflowver.jsp?ID=" + integer1.toString() + "&PASO=-1'>" + s1 + "</a></td>");
                if(s3.compareTo("A") == 0)
                    printwriter.println("<td class='textdesttabla'>Aprobaci\363n de Documentos</td>");
                else
                    printwriter.println("<td class='textdesttabla'>Aprobaci\363n de Registros</td>");
                printwriter.println("<td class='textdesttabla'>" + s7 + "</td>");
                printwriter.println("<td class='textdesttabla'>");
                if(s5.compareTo("A") == 0)
                    printwriter.println("Cerrado");
                else
                    printwriter.println("En Construcci\363n");
                printwriter.println("</td></tr>");
            }

        } else
        {
            printwriter.println("<tr><td class='textdesttabla' colspan='3'>No existen Workflows disponibles.</td></tr>");
        }
    }

    void GrabaPerfil(PrintWriter printwriter, String s)
    {
        String s1 = "insert into sgc.perfiles (perfil,estado) values ('" + s + "','A')";
        int i = ADatos.ModificaDB(s1);
    }

    void UpdatePerfil(PrintWriter printwriter, String s, String s1, String s2)
    {
        String s3 = "update sgc.perfiles set perfil = '" + s1 + "', estado='" + s2 + "' where idperfil = " + s;
        int i = ADatos.ModificaDB(s3);
    }

    int EliminaPerfil(PrintWriter printwriter, String s)
    {
        Integer integer = new Integer(0);
        Vector vector = new Vector();
        byte byte0 = -1;
        String s1 = "SELECT count(*) from sgc.perfilusuario WHERE  idperfil = " + s;
        int i = ADatos.ConsultaDB(s1);
        vector = ADatos.getResult();
        if(vector.size() > 0)
        {
            Integer integer1 = (Integer)vector.elementAt(0);
            if(integer1.longValue() > 0L)
            {
                byte0 = -2;
            } else
            {
                Vector vector1 = new Vector();
                String s2 = "SELECT count(*) from sad.permisosproceso WHERE idperfil = " + s;
                int j = ADatos.ConsultaDB(s2);
                vector1 = ADatos.getResult();
                if(vector1.size() > 0)
                {
                    Integer integer2 = (Integer)vector1.elementAt(0);
                    if(integer2.longValue() > 0L)
                    {
                        byte0 = -3;
                    } else
                    {
                        Vector vector2 = new Vector();
                        String s3 = "SELECT count(*) from sad.permisosproyecto WHERE idperfil = " + s;
                        int k = ADatos.ConsultaDB(s3);
                        vector2 = ADatos.getResult();
                        if(vector2.size() > 0)
                        {
                            Integer integer3 = (Integer)vector2.elementAt(0);
                            if(integer3.longValue() > 0L)
                            {
                                byte0 = -4;
                            } else
                            {
                                String s4 = "delete from sgc.perfiles where idperfil = " + s;
                                int l = ADatos.ModificaDB(s4);
                                byte0 = 0;
                            }
                        }
                    }
                }
            }
        }
        return byte0;
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
}