package gdc;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class workflowprocesa extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public workflowprocesa() {
        ADatos = new AccDataBase();
        func = new funciones();
        MsgError = "";
        IdWf = new Integer(0);
        PasoWfI = new Integer(0);
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        String s = "";
        String s11 = "";
        boolean flag = false;
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s13 = (String)httpsession.getValue("SerapisUser");
        if(s13 != null && s13.length() > 0)
        {
            String s12 = httpservletrequest.getParameter("TIPO");
            if(s12 != null)
            {
                if(s12.compareTo("NUEVO") == 0)
                {
                    String s1 = "SELECT MAX(idwf) FROM gdc.workflow";
                    rs = new Vector();
                    ADatos.connect();
                    int i = ADatos.ConsultaDB(s1);
                    rs = ADatos.getResult();
                    if(rs.size() > 0)
                    {
                        IdWf = (Integer)rs.elementAt(0);
                        if(IdWf != null)
                        {
                            int j = IdWf.intValue();
                            j++;
                            IdWf = new Integer(j);
                        } else
                        {
                            IdWf = new Integer(1);
                        }
                    } else
                    {
                        IdWf = new Integer(1);
                    }
                    String s14 = httpservletrequest.getParameter("txtnombre");
                    String s20 = httpservletrequest.getParameter("seltipo");
                    Vector vector = new Vector();
                    vector = func.ObtieneFecha();
                    s1 = "insert into gdc.workflow (idwf,nombre,estado,visible,tipowf,creadopor,fechacreacion,fechamodificacion) values (";
                    s1 = s1 + IdWf.toString() + ",";
                    s1 = s1 + "'" + s14 + "',";
                    s1 = s1 + "'E',";
                    s1 = s1 + "'S',";
                    s1 = s1 + "'" + s20 + "',";
                    s1 = s1 + "'" + s13 + "',";
                    s1 = s1 + (String)vector.elementAt(0) + ",";
                    s1 = s1 + (String)vector.elementAt(0) + ")";
                    int l = ADatos.ModificaDB(s1);
                    if(l != -1)
                    {
                        String s30 = httpservletrequest.getParameter("txtidpaso");
                        PasoWfI = new Integer(s30);
                        String s31 = httpservletrequest.getParameter("txtpaso");
                        String s32 = httpservletrequest.getParameter("selautoriza");
                        String s35 = httpservletrequest.getParameter("selbackup");
                        String s38 = httpservletrequest.getParameter("selfirma");
                        String s41 = httpservletrequest.getParameter("seluserfirma");
                        String s44 = httpservletrequest.getParameter("selaprueba");
                        String s47 = httpservletrequest.getParameter("selrechaza");
                        String s2 = "insert into gdc.workflow_pasos(idwf,idpaso,nombre,idok,idredireccion,tipo,usuarioautoriza,usuariobackup,debefirmar,usuariofirma) values (";
                        s2 = s2 + IdWf.toString() + ",";
                        s2 = s2 + s30.toString() + ",";
                        s2 = s2 + "'" + s31 + "',";
                        s2 = s2 + "'" + s44 + "',";
                        s2 = s2 + "'" + s47 + "',";
                        s2 = s2 + "'I',";
                        s2 = s2 + "'" + s32 + "',";
                        s2 = s2 + "'" + s35 + "',";
                        s2 = s2 + "'" + s38 + "',";
                        s2 = s2 + "'" + s41 + "')";
                        int i1 = ADatos.ModificaDB(s2);
                    }
                    printwriter.println("<HTML>");
                    printwriter.println("<HEAD>");
                    printwriter.println("<title>SERAPIS. Sistema de Gesti\363n de Calidad.</title>");
                    printwriter.println("</HEAD>");
                    printwriter.println("</HEAD>");
                    printwriter.println("<script language=\"javascript\">");
                    int j1 = PasoWfI.intValue() + 1;
                    printwriter.println("   window.open('workflowver.jsp?ID=" + IdWf.toString() + "&TIPO=AGREGAPASO&PASO=" + j1 + "','datos');");
                    printwriter.println("</script>");
                    printwriter.println("<body bgcolor='#FFFFFF'>");
                    printwriter.println("</BODY>");
                    printwriter.println("</HTML>");
                }
                if(s12.compareTo("EDITAWF") == 0)
                {
                    String s15 = "N";
                    String s21 = httpservletrequest.getParameter("txtidwf");
                    IdWf = new Integer(s21);
                    String s24 = httpservletrequest.getParameter("txtnombre");
                    String s27 = httpservletrequest.getParameter("seltipo");
                    Vector vector1 = new Vector();
                    vector1 = func.ObtieneFecha();
                    String s3 = "update gdc.workflow set nombre = '" + s24 + "'";
                    s3 = s3 + ", tipowf = '" + s27 + "'";
                    s3 = s3 + " where idwf = " + IdWf.toString();
                    int k1 = ADatos.ModificaDB(s3);
                    printwriter.println("<HTML>");
                    printwriter.println("<HEAD>");
                    printwriter.println("<title>SERAPIS. Sistema de Gesti\363n de Calidad.</title>");
                    printwriter.println("</HEAD>");
                    printwriter.println("</HEAD>");
                    printwriter.println("<script language=\"javascript\">");
                    printwriter.println("   window.open('workflowver.jsp?ID=" + IdWf.toString() + "&PASO=-1','datos');");
                    printwriter.println("</script>");
                    printwriter.println("<body bgcolor='#FFFFFF'>");
                    printwriter.println("</BODY>");
                    printwriter.println("</HTML>");
                }
                if(s12.compareTo("AGREGAPASO") == 0)
                {
                    String s16 = "N";
                    String s22 = httpservletrequest.getParameter("txtidwf");
                    IdWf = new Integer(s22);
                    String s25 = httpservletrequest.getParameter("txtnombre");
                    String s28 = httpservletrequest.getParameter("seltipo");
                    Vector vector2 = new Vector();
                    vector2 = func.ObtieneFecha();
                    String s4 = "update gdc.workflow set nombre = '" + s25 + "'";
                    s4 = s4 + ", tipowf = '" + s28 + "'";
                    s4 = s4 + " where idwf = " + IdWf.toString();
                    int l1 = ADatos.ModificaDB(s4);
                    if(l1 != -1)
                    {
                        String s33 = httpservletrequest.getParameter("txtidpaso");
                        PasoWfI = new Integer(s33);
                        String s36 = httpservletrequest.getParameter("txtpaso");
                        String s39 = httpservletrequest.getParameter("selautoriza");
                        String s42 = httpservletrequest.getParameter("selbackup");
                        String s45 = httpservletrequest.getParameter("selfirma");
                        String s48 = httpservletrequest.getParameter("seluserfirma");
                        String s50 = httpservletrequest.getParameter("selaprueba");
                        String s52 = httpservletrequest.getParameter("selrechaza");
                        s16 = httpservletrequest.getParameter("selfinal");
                        String s5 = "insert into gdc.workflow_pasos(idwf,idpaso,nombre,idok,idredireccion,tipo,usuarioautoriza,usuariobackup,debefirmar,usuariofirma) values (";
                        s5 = s5 + IdWf.toString() + ",";
                        s5 = s5 + s33.toString() + ",";
                        s5 = s5 + "'" + s36 + "',";
                        s5 = s5 + "'" + s50 + "',";
                        s5 = s5 + "'" + s52 + "',";
                        if(s16.compareTo("S") == 0)
                            s5 = s5 + "'F',";
                        else
                            s5 = s5 + "'M',";
                        s5 = s5 + "'" + s39 + "',";
                        s5 = s5 + "'" + s42 + "',";
                        s5 = s5 + "'" + s45 + "',";
                        s5 = s5 + "'" + s48 + "')";
                        int i2 = ADatos.ModificaDB(s5);
                        int k3 = 0;
                        k3 = PasoWfI.intValue();
                        k3--;
                        s5 = "update gdc.workflow_pasos set idok = " + s33.toString();
                        s5 = s5 + " where idok = 0 ";
                        s5 = s5 + " and idwf = " + IdWf.toString();
                        s5 = s5 + " and idpaso = " + k3;
                        i2 = ADatos.ModificaDB(s5);
                        if(s16.compareTo("S") == 0)
                        {
                            String s6 = "update gdc.workflow set estado = 'A'";
                            s6 = s6 + " where idwf = " + IdWf.toString();
                            int j2 = ADatos.ModificaDB(s6);
                        }
                    }
                    printwriter.println("<HTML>");
                    printwriter.println("<HEAD>");
                    printwriter.println("<title>SERAPIS. Sistema de Gesti\363n de Calidad.</title>");
                    printwriter.println("</HEAD>");
                    printwriter.println("</HEAD>");
                    printwriter.println("<script language=\"javascript\">");
                    int j3 = PasoWfI.intValue() + 1;
                    if(s16.compareTo("S") == 0)
                        printwriter.println("   window.open('workflowver.jsp?ID=" + IdWf.toString() + "&PASO=-1','datos');");
                    else
                        printwriter.println("   window.open('workflowver.jsp?ID=" + IdWf.toString() + "&TIPO=AGREGAPASO&PASO=" + j3 + "','datos');");
                    printwriter.println("</script>");
                    printwriter.println("<body bgcolor='#FFFFFF'>");
                    printwriter.println("</BODY>");
                    printwriter.println("</HTML>");
                }
                if(s12.compareTo("EDITAR") == 0)
                {
                    String s17 = httpservletrequest.getParameter("selfinal");
                    String s23 = httpservletrequest.getParameter("txtidwf");
                    IdWf = new Integer(s23);
                    String s26 = httpservletrequest.getParameter("txtnombre");
                    String s29 = httpservletrequest.getParameter("seltipo");
                    Vector vector3 = new Vector();
                    vector3 = func.ObtieneFecha();
                    String s7 = "update gdc.workflow set nombre = '" + s26 + "'";
                    s7 = s7 + ", tipowf = '" + s29 + "'";
                    if(s17.compareTo("S") == 0)
                        s7 = s7 + ", estado = 'A'";
                    s7 = s7 + " where idwf = " + IdWf.toString();
                    int k2 = ADatos.ModificaDB(s7);
                    if(k2 != -1)
                    {
                        String s34 = httpservletrequest.getParameter("txtidpaso");
                        PasoWfI = new Integer(s34);
                        String s37 = httpservletrequest.getParameter("txtpaso");
                        String s40 = httpservletrequest.getParameter("selautoriza");
                        String s43 = httpservletrequest.getParameter("selbackup");
                        String s46 = httpservletrequest.getParameter("selfirma");
                        String s49 = httpservletrequest.getParameter("seluserfirma");
                        String s51 = httpservletrequest.getParameter("selaprueba");
                        String s53 = httpservletrequest.getParameter("selrechaza");
                        String s18 = httpservletrequest.getParameter("selfinal");
                        String s8 = "update gdc.workflow_pasos set ";
                        s8 = s8 + "nombre = '" + s37 + "'";
                        s8 = s8 + ",idredireccion = " + s53;
                        s8 = s8 + ",usuarioautoriza = '" + s40 + "'";
                        s8 = s8 + ",usuariobackup = '" + s43 + "'";
                        s8 = s8 + ",debefirmar = '" + s46 + "'";
                        s8 = s8 + ",usuariofirma = '" + s49 + "'";
                        if(s18.compareTo("S") == 0)
                        {
                            s8 = s8 + ",tipo = 'F'";
                            s8 = s8 + ",idok = 0";
                        }
                        s8 = s8 + " where idwf  = " + IdWf.toString();
                        s8 = s8 + " and idpaso  = " + s34.toString();
                        int l2 = ADatos.ModificaDB(s8);
                        if(s18.compareTo("S") == 0)
                        {
                            String s9 = "delete from gdc.workflow_pasos ";
                            s9 = s9 + " where idwf  = " + IdWf.toString();
                            s9 = s9 + " and idpaso  > " + s34.toString();
                            int i3 = ADatos.ModificaDB(s9);
                        }
                    }
                    printwriter.println("<HTML>");
                    printwriter.println("<HEAD>");
                    printwriter.println("<title>SERAPIS. Sistema de Gesti\363n de Calidad.</title>");
                    printwriter.println("</HEAD>");
                    printwriter.println("</HEAD>");
                    printwriter.println("<script language=\"javascript\">");
                    printwriter.println("   window.open('workflowver.jsp?ID=" + IdWf.toString() + "&PASO=-1','datos');");
                    printwriter.println("</script>");
                    printwriter.println("<body bgcolor='#FFFFFF'>");
                    printwriter.println("</BODY>");
                    printwriter.println("</HTML>");
                }
                if(s12.compareTo("ELIMINAR") == 0)
                {
                    String s19 = httpservletrequest.getParameter("txtidwf");
                    Integer integer = new Integer(s19);
                    String s10 = "update gdc.workflow set visible='N'";
                    s10 = s10 + " where idwf = " + s19.toString();
                    int k = ADatos.ModificaDB(s10);
                    printwriter.println("<HTML>");
                    printwriter.println("<HEAD>");
                    printwriter.println("<title>SERAPIS. Sistema de Gesti\363n de Calidad.</title>");
                    printwriter.println("</HEAD>");
                    printwriter.println("</HEAD>");
                    printwriter.println("<script language=\"javascript\">");
                    printwriter.println("   window.open('workflow.jsp','datos');");
                    printwriter.println("</script>");
                    printwriter.println("<body bgcolor='#FFFFFF'>");
                    printwriter.println("</BODY>");
                    printwriter.println("</HTML>");
                }
            }
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
    String MsgError;
    Integer IdWf;
    Integer PasoWfI;
}