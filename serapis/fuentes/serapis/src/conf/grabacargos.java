package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.*;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class grabacargos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public grabacargos() {
        AFunc = new funciones();
        ADatos = new AccDataBase();
        ADatos.connect();
    }

    protected void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s = "INSERT INTO gdc.def_cargos(ID, Descripcion, Departamento, Reporta, RangoSalarial, Rating, Objetivo, Edad, Idioma, OtrosCarGen, Viajes, Horario, OtrosReq) VALUES(";
        String s1 = "INSERT INTO gdc.descripciones_cargos(ID, ID_Tipo, correlativo, Descripcion) VALUES(";
        String s2 = "";
        String s3 = "";
        String s4 = "";
        UserGDC = (String)httpsession.getValue("SerapisUser");
        if(UserGDC != null && UserGDC.length() > 0)
        {
            String s8 = httpservletrequest.getParameter("ID");
            String s9 = httpservletrequest.getParameter("TIPO");
            if(s9 == null || s9.length() == 0)
                s9 = "ADD";
            if(s9.compareTo("UPD") == 0)
            {
                String s5 = "delete from gdc.def_cargos where ID = " + s8;
                ADatos.ModificaDB(s5);
                s5 = "delete from gdc.descripciones_cargos where ID = " + s8;
                ADatos.ModificaDB(s5);
            }
            if(s9.compareTo("DEL") != 0)
            {
                int i;
                if(s9.compareTo("UPD") == 0)
                {
                    Integer integer = new Integer(s8);
                    i = integer.intValue();
                } else
                {
                    i = getIdentificador();
                }
                s = s + i + ",'" + (httpservletrequest.getParameter("puesto") == null ? "" : httpservletrequest.getParameter("puesto").toString().trim());
                s = s + "','" + (httpservletrequest.getParameter("departamento") == null ? "" : httpservletrequest.getParameter("departamento").toString().trim());
                s = s + "','" + (httpservletrequest.getParameter("reporta") == null ? "" : httpservletrequest.getParameter("reporta").toString().trim());
                s = s + "','" + (httpservletrequest.getParameter("rango") == null ? "" : httpservletrequest.getParameter("rango").toString().trim());
                s = s + "','" + (httpservletrequest.getParameter("rating") == null ? "" : httpservletrequest.getParameter("rating").toString().trim());
                s = s + "','" + (httpservletrequest.getParameter("objetivo") == null ? "" : httpservletrequest.getParameter("objetivo").toString().trim());
                s = s + "','" + (httpservletrequest.getParameter("edad") == null ? "" : httpservletrequest.getParameter("edad").toString().trim());
                s = s + "','" + (httpservletrequest.getParameter("idioma") == null ? "" : httpservletrequest.getParameter("idioma").toString().trim());
                s = s + "','" + (httpservletrequest.getParameter("otros1") == null ? "" : httpservletrequest.getParameter("otros1").toString().trim());
                s = s + "','" + (httpservletrequest.getParameter("viajes") == null ? "" : httpservletrequest.getParameter("viajes").toString().trim());
                s = s + "','" + (httpservletrequest.getParameter("horario") == null ? "" : httpservletrequest.getParameter("horario").toString().trim());
                s = s + "','" + (httpservletrequest.getParameter("otros2") == null ? "" : httpservletrequest.getParameter("otros2").toString().trim()) + "')";
                int k = ADatos.ModificaDB(s);
                if(k != -1)
                {
                    if(httpservletrequest.getParameterValues("puesto") != null)
                    {
                        String as[] = httpservletrequest.getParameterValues("puesto");
                        for(int l = 0; l < as.length; l++)
                        {
                            String s10 = s1 + i + ",1," + (l + 1) + ",'" + as[l] + "')";
                            ADatos.ModificaDB(s10);
                        }

                    }
                    if(httpservletrequest.getParameterValues("departamento") != null)
                    {
                        String as1[] = httpservletrequest.getParameterValues("departamento");
                        for(int i1 = 0; i1 < as1.length; i1++)
                        {
                            String s11 = s1 + i + ",2," + (i1 + 1) + ",'" + as1[i1] + "')";
                            ADatos.ModificaDB(s11);
                        }

                    }
                    if(httpservletrequest.getParameterValues("reporta") != null)
                    {
                        String as2[] = httpservletrequest.getParameterValues("reporta");
                        for(int j1 = 0; j1 < as2.length; j1++)
                        {
                            String s12 = s1 + i + ",3," + (j1 + 1) + ",'" + as2[j1] + "')";
                            ADatos.ModificaDB(s12);
                        }

                    }
                    if(httpservletrequest.getParameterValues("rango") != null)
                    {
                        String as3[] = httpservletrequest.getParameterValues("rango");
                        for(int k1 = 0; k1 < as3.length; k1++)
                        {
                            String s13 = s1 + i + ",4," + (k1 + 1) + ",'" + as3[k1] + "')";
                            ADatos.ModificaDB(s13);
                        }

                    }
                    /*if(httpservletrequest.getParameterValues("rating") != null)
                    {
                        String as4[] = httpservletrequest.getParameterValues("rating");
                        for(int l1 = 0; l1 < as4.length; l1++)
                        {
                            String s14 = s1 + i + ",5," + (l1 + 1) + ",'" + as4[l1] + "')";
                            ADatos.ModificaDB(s14);
                        }

                    }*/
                    if(httpservletrequest.getParameterValues("objetivo") != null)
                    {
                        String as5[] = httpservletrequest.getParameterValues("objetivo");
                        for(int i2 = 0; i2 < as5.length; i2++)
                        {
                            String s15 = s1 + i + ",5," + (i2 + 1) + ",'" + as5[i2] + "')";
                            ADatos.ModificaDB(s15);
                        }

                    }
                    if(httpservletrequest.getParameterValues("edad") != null)
                    {
                        String as6[] = httpservletrequest.getParameterValues("edad");
                        for(int j2 = 0; j2 < as6.length; j2++)
                        {
                            String s16 = s1 + i + ",14," + (j2 + 1) + ",'" + as6[j2] + "')";
                            ADatos.ModificaDB(s16);
                        }

                    }
                    if(httpservletrequest.getParameterValues("idioma") != null)
                    {
                        String as7[] = httpservletrequest.getParameterValues("idioma");
                        for(int k2 = 0; k2 < as7.length; k2++)
                        {
                            String s17 = s1 + i + ",15," + (k2 + 1) + ",'" + as7[k2] + "')";
                            ADatos.ModificaDB(s17);
                        }

                    }
                    if(httpservletrequest.getParameterValues("otros1") != null)
                    {
                        String as8[] = httpservletrequest.getParameterValues("otros1");
                        for(int l2 = 0; l2 < as8.length; l2++)
                        {
                            String s18 = s1 + i + ",16," + (l2 + 1) + ",'" + as8[l2] + "')";
                            ADatos.ModificaDB(s18);
                        }

                    }
                    if(httpservletrequest.getParameterValues("viajes") != null)
                    {
                        String as9[] = httpservletrequest.getParameterValues("viajes");
                        for(int i3 = 0; i3 < as9.length; i3++)
                        {
                            String s19 = s1 + i + ",17," + (i3 + 1) + ",'" + as9[i3] + "')";
                            ADatos.ModificaDB(s19);
                        }

                    }
                    if(httpservletrequest.getParameterValues("horario") != null)
                    {
                        String as10[] = httpservletrequest.getParameterValues("horario");
                        for(int j3 = 0; j3 < as10.length; j3++)
                        {
                            String s20 = s1 + i + ",18," + (j3 + 1) + ",'" + as10[j3] + "')";
                            ADatos.ModificaDB(s20);
                        }

                    }
                    if(httpservletrequest.getParameterValues("otros2") != null)
                    {
                        String as11[] = httpservletrequest.getParameterValues("otros2");
                        for(int k3 = 0; k3 < as11.length; k3++)
                        {
                            String s21 = s1 + i + ",19," + (k3 + 1) + ",'" + as11[k3] + "')";
                            ADatos.ModificaDB(s21);
                        }

                    }
                    if(httpservletrequest.getParameterValues("funciones") != null)
                    {
                        String as12[] = httpservletrequest.getParameterValues("funciones");
                        for(int l3 = 0; l3 < as12.length; l3++)
                        {
                            String s22 = s1 + i + ",6," + (l3 + 1) + ",'" + as12[l3] + "')";
                            ADatos.ModificaDB(s22);
                        }

                    }
                    if(httpservletrequest.getParameterValues("conocimiento") != null)
                    {
                        String as13[] = httpservletrequest.getParameterValues("conocimiento");
                        for(int i4 = 0; i4 < as13.length; i4++)
                        {
                            String s23 = s1 + i + ",7," + (i4 + 1) + ",'" + as13[i4] + "')";
                            ADatos.ModificaDB(s23);
                        }

                    }
                    if(httpservletrequest.getParameterValues("destrezas") != null)
                    {
                        String as14[] = httpservletrequest.getParameterValues("destrezas");
                        for(int j4 = 0; j4 < as14.length; j4++)
                        {
                            String s24 = s1 + i + ",8," + (j4 + 1) + ",'" + as14[j4] + "')";
                            ADatos.ModificaDB(s24);
                        }

                    }
                    if(httpservletrequest.getParameterValues("habilidades") != null)
                    {
                        String as15[] = httpservletrequest.getParameterValues("habilidades");
                        for(int k4 = 0; k4 < as15.length; k4++)
                        {
                            String s25 = s1 + i + ",9," + (k4 + 1) + ",'" + as15[k4] + "')";
                            ADatos.ModificaDB(s25);
                        }

                    }
                    if(httpservletrequest.getParameterValues("reqseleccion") != null)
                    {
                        String as16[] = httpservletrequest.getParameterValues("reqseleccion");
                        for(int l4 = 0; l4 < as16.length; l4++)
                        {
                            String s26 = s1 + i + ",10," + (l4 + 1) + ",'" + as16[l4] + "')";
                            ADatos.ModificaDB(s26);
                        }

                    }
                    if(httpservletrequest.getParameterValues("reqcapacitacion") != null)
                    {
                        String as17[] = httpservletrequest.getParameterValues("reqcapacitacion");
                        for(int i5 = 0; i5 < as17.length; i5++)
                        {
                            String s27 = s1 + i + ",11," + (i5 + 1) + ",'" + as17[i5] + "')";
                            ADatos.ModificaDB(s27);
                        }

                    }
                    if(httpservletrequest.getParameterValues("experiencia") != null)
                    {
                        String as18[] = httpservletrequest.getParameterValues("experiencia");
                        for(int j5 = 0; j5 < as18.length; j5++)
                        {
                            String s28 = s1 + i + ",12," + (j5 + 1) + ",'" + as18[j5] + "')";
                            ADatos.ModificaDB(s28);
                        }

                    }
                    s4 = "Cargo Ingresado correctamente";
                    System.out.println("Cargo Ingresado correctamente");
                } else
                {
                    System.out.println("Error en el ingreso del Cargo");
                    s4 = "Error en el ingreso del Cargo";
                }
            } else
            {
                String s6 = "SELECT login from sgc.usuarios WHERE idcargo = " + s8;
                int j = ADatos.ConsultaDB(s6);
                rs = ADatos.getResult();
                if(rs.size() > 0)
                {
                    s4 = "El Rol/Cargo que desea eliminar esta asociado al menos a un usuario.";
                } else
                {
                    String s7 = "delete from gdc.def_cargos where ID = " + s8;
                    ADatos.ModificaDB(s7);
                    s7 = "delete from gdc.descripciones_cargos where ID = " + s8;
                    ADatos.ModificaDB(s7);
                }
            }
            printwriter.println("<HTML>");
            printwriter.println("<HEAD>");
            printwriter.println("<title>SERAPIS. Sistema de Gesti\363n de Calidad.</title>");
            printwriter.println("</HEAD>");
            printwriter.println("</HEAD>");
            printwriter.println("<script language=\"javascript\">");
            if(s4.length() > 0)
                printwriter.println("alert(\"" + s4 + "\");");
            printwriter.println("window.open(\"listacargosroles.jsp\",\"datos\");");
            printwriter.println("</script>");
            printwriter.println("<body bgcolor='#FFFFFF'>");
            printwriter.println("</BODY>");
            printwriter.println("</HTML>");
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "CONF", 6);
        }
    }

    protected void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        System.out.println("entre por doPost");
        doGet(httpservletrequest, httpservletresponse);
    }

    private int getIdentificador()
    {
        String s = "SELECT MAX(ID) FROM gdc.def_cargos";
        int i = ADatos.ConsultaDB(s);
        int j = 0;
        rs = ADatos.getResult();
        if(rs.size() > 0)
            j = Integer.parseInt(rs.elementAt(0).toString());
        return j + 1;
    }

    funciones AFunc;
    AccDataBase ADatos;
    Vector rs;
    String UserGDC;
}