package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class procesausuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public procesausuario() {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        GlosaError = "";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException {
        String s21 = "";
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s23 = (String)httpsession.getValue("SerapisUser");
        if(s23 != null && s23.length() > 0)
        {
            String s24 = httpservletrequest.getParameter("login");
            String s28 = httpservletrequest.getParameter("nombre");
            String s29 = httpservletrequest.getParameter("rut1");
            String s30 = httpservletrequest.getParameter("rut2");
            String s31 = httpservletrequest.getParameter("fecha1");
            String s32 = httpservletrequest.getParameter("fecha2");
            String s33 = httpservletrequest.getParameter("fecha3");
            String s34 = httpservletrequest.getParameter("cargo");
            String s35 = httpservletrequest.getParameter("mail");
            String s36 = httpservletrequest.getParameter("tipoadmin");
            String s37 = httpservletrequest.getParameter("opcionsel");
            String s38 = httpservletrequest.getParameter("alias");
            if(s37.compareTo("G") == 0) {
                String s4 = "insert into sgc.usuarios(login,nombre,idcargo,rut,password,fechapass,mail,fechanac,estado,alias) values (";
                s4 = s4 + "'" + s24 + "',";
                s4 = s4 + "'" + s28 + "',";
                s4 = s4 + s34 + ",";
                s29 = s29 + "" + s30;
                s4 = s4 + "'" + s29 + "',";
                s4 = s4 + "'',";
                Fechav = AFunc.ObtieneFecha();
                String s22 = Fechav.elementAt(0).toString();
                s4 = s4 + s22 + ",";
                s4 = s4 + "'" + s35 + "',";
                s31 = s33 + s32 + s31;
                s4 = s4 + s31 + ",";
                s4 = s4 + "'A',";
                s4 = s4 + "'" + s38 + "')";
                int i = ADatos.ModificaDB(s4);
                if(i != -1)
                {
                    String s5 = "delete from sgc.perfil where login = '" + s24 + "'";
                    int j = ADatos.ModificaDB(s5);
                    j = 0;
                    if(j != -1)
                    {
                        rs = new Vector();
                        String s6 = "select sigla from gdc.procesos";
                        int k = ADatos.ConsultaDB(s6);
                        rs = ADatos.getResult();
                        for(int k2 = 0; k2 < rs.size(); k2++)
                        {
                            String s = (String)rs.elementAt(k2);
                            String s2 = httpservletrequest.getParameter(s);
                            String s7 = "insert into sgc.perfil(login,proceso,perfil,administrador) values ('" + s24 + "',";
                            s7 = s7 + "'" + s + "',";
                            s7 = s7 + "'" + s2 + "',";
                            s7 = s7 + "'" + s36 + "')";
                            int l = ADatos.ModificaDB(s7);
                        }

                        GlosaError = "Los accesos se han almacenado correctamente";
                    } else
                    {
                        GlosaError = "No se pudo borrar el perfil del usuario";
                    }
                } else
                {
                    GlosaError = "Problemas al agregar el usuario. Chequee que el Login no exista y vuelva a intentar.";
                }
            } else
            if(s37.compareTo("R") == 0)
            {
                String s25 = httpservletrequest.getParameter("loginuser");
                if(s25 == null || s25.length() == 0)
                    s25 = "";
                String s8 = "Update sgc.usuarios set password = '' where login='" + s25 + "'";
                int i1 = ADatos.ModificaDB(s8);
                if(i1 != -1)
                    GlosaError = "La Password se ha reiniciado correctamente. El usuario deber\341 cambiar su clave la pr\363xima vez que inicie seci\363n.";
                else
                    GlosaError = "Problemas al reiniciar la clave. Informe al Administrador";
            } else
            if(s37.compareTo("M") == 0)
            {
                String s26 = httpservletrequest.getParameter("loginuser");
                String s39 = httpservletrequest.getParameter("tipoestado");
                String s9 = "update sgc.usuarios set ";
                s9 = s9 + "nombre = '" + s28 + "',";
                s9 = s9 + "idcargo = " + s34 + ",";
                s9 = s9 + "mail = '" + s35 + "',";
                s9 = s9 + "estado = '" + s39 + "',";
                s31 = s33 + s32 + s31;
                s9 = s9 + "fechanac = " + s31 + ",";
                s9 = s9 + "alias = '" + s38 + "' ";
                s9 = s9 + " where login='" + s26 + "'";
                int j1 = ADatos.ModificaDB(s9);
                if(j1 != -1)
                {
                    String s10 = "delete from sgc.perfil where login = '" + s26 + "'";
                    int k1 = ADatos.ModificaDB(s10);
                    k1 = 0;
                    if(k1 != -1)
                    {
                        rs = new Vector();
                        String s11 = "select sigla from gdc.procesos";
                        int l1 = ADatos.ConsultaDB(s11);
                        rs = ADatos.getResult();
                        for(int l2 = 0; l2 < rs.size(); l2++)
                        {
                            String s1 = (String)rs.elementAt(l2);
                            String s3 = httpservletrequest.getParameter(s1);
                            s11 = "insert into sgc.perfil(login,proceso,perfil,administrador) values ('" + s26 + "',";
                            s11 = s11 + "'" + s1 + "',";
                            s11 = s11 + "'" + s3 + "',";
                            s11 = s11 + "'" + s36 + "')";
                            l1 = ADatos.ModificaDB(s11);
                        }

                        s11 = "delete from sgc.perfilusuario where usuario = '" + s26 + "'";
                        l1 = ADatos.ModificaDB(s11);
                        String s40 = httpservletrequest.getParameter("hddperfiles");
                        if(s40.length() > 0)
                        {
                            for(StringTokenizer stringtokenizer = new StringTokenizer(s40, ";"); stringtokenizer.hasMoreTokens();)
                            {
                                String s12 = "insert into sgc.perfilusuario (usuario,idperfil) values ('" + s26 + "'," + stringtokenizer.nextToken() + ")";
                                int i2 = ADatos.ModificaDB(s12);
                            }

                        }
                        GlosaError = "Los accesos se han modificado correctamente";
                    } else
                    {
                        GlosaError = "No se pudo borrar el perfil del usuario";
                    }
                } else
                {
                    GlosaError = "Problemas al modificar el usuario.";
                }
            } else
            if(s37.compareTo("E") == 0)
            {
                GlosaError = "";
                String s27 = httpservletrequest.getParameter("loginuser");
                Integer integer = new Integer(0);
                rs = new Vector();
                String s13 = "select count(*) from gdc.documentos where usuario = '" + s27 + "'";
                ADatos.ConsultaDB(s13);
                rs = ADatos.getResult();
                if(rs.size() > 0)
                {
                    Integer integer1 = (Integer)rs.elementAt(0);
                    if(integer1.longValue() > 0L)
                    {
                        GlosaError = "No es posible eliminar al usuario ya que esta asociado a uno a m\341s documentos";
                    } else
                    {
                        rs.clear();
                        String s14 = "select count(*) from gdc.procesos where lider = '" + s27 + "' or backuplider = '" + s27 + "'";
                        ADatos.ConsultaDB(s14);
                        rs = ADatos.getResult();
                        if(rs.size() > 0)
                        {
                            Integer integer2 = (Integer)rs.elementAt(0);
                            if(integer2.longValue() > 0L)
                            {
                                GlosaError = "No es posible eliminar al usuario ya que esta asociado como lider o backup de uno o m\341s procesos";
                            } else
                            {
                                rs.clear();
                                String s15 = "select count(*) from gdc.proyectos where jefeproyecto = '" + s27 + "' or usuario = '" + s27 + "' or jpbackup = '" + s27 + "'";
                                ADatos.ConsultaDB(s15);
                                rs = ADatos.getResult();
                                if(rs.size() > 0)
                                {
                                    Integer integer3 = (Integer)rs.elementAt(0);
                                    if(integer3.longValue() > 0L)
                                    {
                                        GlosaError = "No es posible eliminar al usuario ya que esta asociado como como jefe de proyecto o backup de algun proyecto";
                                    } else
                                    {
                                        rs.clear();
                                        String s16 = "select count(*) from sad.documentos where usuario = '" + s27 + "'";
                                        ADatos.ConsultaDB(s16);
                                        rs = ADatos.getResult();
                                        if(rs.size() > 0)
                                        {
                                            Integer integer4 = (Integer)rs.elementAt(0);
                                            if(integer4.longValue() > 0L)
                                            {
                                                GlosaError = "No es posible eliminar al usuario ya que esta asociado a por lo menos un documento de operaciones";
                                            } else
                                            {
                                                rs.clear();
                                                String s17 = "select count(*) from sad.documentosp where usuario = '" + s27 + "'";
                                                ADatos.ConsultaDB(s17);
                                                rs = ADatos.getResult();
                                                if(rs.size() > 0)
                                                {
                                                    Integer integer5 = (Integer)rs.elementAt(0);
                                                    if(integer5.longValue() > 0L)
                                                    {
                                                        GlosaError = "No es posible eliminar al usuario ya que esta asociado a por lo menos un documento de operaciones de alg\372n cliente";
                                                    } else
                                                    {
                                                        rs.clear();
                                                        String s18 = "select count(*) from sad.permisosproceso where usuario = '" + s27 + "'";
                                                        ADatos.ConsultaDB(s18);
                                                        rs = ADatos.getResult();
                                                        if(rs.size() > 0)
                                                        {
                                                            Integer integer6 = (Integer)rs.elementAt(0);
                                                            if(integer6.longValue() > 0L)
                                                            {
                                                                GlosaError = "No es posible eliminar al usuario ya posee permisos de acceso a documentos de operaciones";
                                                            } else
                                                            {
                                                                rs.clear();
                                                                String s19 = "select count(*) from sad.permisosproyecto where usuario = '" + s27 + "'";
                                                                ADatos.ConsultaDB(s19);
                                                                rs = ADatos.getResult();
                                                                if(rs.size() > 0)
                                                                {
                                                                    Integer integer7 = (Integer)rs.elementAt(0);
                                                                    if(integer7.longValue() > 0L)
                                                                    {
                                                                        GlosaError = "No es posible eliminar al usuario ya posee permisos de acceso a documentos de operaciones de alg\372n cliente";
                                                                    } else
                                                                    {
                                                                        String s20 = "delete  from gdc.perfil where login = '" + s27 + "'";
                                                                        int j2 = ADatos.ModificaDB(s20);
                                                                        s20 = "delete  from sgc.perfilusuario where usuario = '" + s27 + "'";
                                                                        j2 = ADatos.ModificaDB(s20);
                                                                        s20 = "delete  from sgc.usuarios where login = '" + s27 + "'";
                                                                        j2 = ADatos.ModificaDB(s20);
                                                                        GlosaError = "Usuario eliminado correctamente";
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            printwriter.println("<HTML>");
            printwriter.println("<HEAD>");
            printwriter.println("<title>SERAPIS. Sistema de Gesti\363n de Calidad.</title>");
            printwriter.println("</HEAD>");
            printwriter.println("</HEAD>");
            printwriter.println("<script language=\"javascript\">");
            if(GlosaError.length() > 0)
                printwriter.println("alert(\"" + GlosaError + "\");");
            printwriter.println("window.open(\"usuarios.jsp\",\"datos\");");
            printwriter.println("</script>");
            printwriter.println("<body bgcolor='#FFFFFF'>");
            printwriter.println("</BODY>");
            printwriter.println("</HTML>");
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "CONF", 6);
        }
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException {
        doGet(httpservletrequest, httpservletresponse);
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    Vector Fechav;
    String GlosaError;
}