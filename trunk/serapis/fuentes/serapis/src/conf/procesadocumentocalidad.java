package conf;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class procesadocumentocalidad extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public procesadocumentocalidad() {
        ADatos = new AccDataBase();
        AFunc = new funciones();
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s = (String)httpsession.getValue("SerapisUser");
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        if(s != null && s.length() > 0)
        {
            Integer integer = new Integer(0);
            int l1 = 0;
            int i2 = 1;
            String s1 = httpservletrequest.getParameter("TIPO");
            String s2 = httpservletrequest.getParameter("DOC");
            String s3 = httpservletrequest.getParameter("tipodocumentos");
            String s4 = httpservletrequest.getParameter("tipoprocesos");
            String s5 = httpservletrequest.getParameter("descripcion");
            String s6 = httpservletrequest.getParameter("tiempo");
            String s7 = httpservletrequest.getParameter("tipotiempo");
            String s8 = httpservletrequest.getParameter("selwfa") == null ? "0" : httpservletrequest.getParameter("selwfa");
            String s9 = httpservletrequest.getParameter("selwfe") == null ? "0" : httpservletrequest.getParameter("selwfe");
            String s10 = httpservletrequest.getParameter("sellista");
            String s11 = httpservletrequest.getParameter("seltipo");
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            rs = new Vector();
            if(s1 != null && s2 != null)
            {
                if(s1.length() > 0 && s2.length() > 0)
                {
                    if(s1.compareTo("E") == 0)
                    {
                        sql = "select count(*) from gdc.documentos where tipodoc  = '" + s3 + "' and proceso = '" + s4 + "' and descripcion = '" + s5 + "'";
                        int i = ADatos.ConsultaDB(sql);
                        rs = ADatos.getResult();
                        if(rs.size() >= 0)
                        {
                            Integer integer1 = new Integer(0);
                            try {
                            	integer1 = (Integer)rs.elementAt(0);
                            } catch(ClassCastException e) {}
                            i2 = integer1.intValue();
                        }
                        if(i2 == 0)
                        {
                            sql = "delete from gdc.documentoscalidad where id = " + s2;
                            int j = ADatos.ModificaDB(sql);
                            if(j != -1)
                                EscribeError(printwriter, "El documento se ha eliminado correctamente");
                            else
                                EscribeError(printwriter, "No se pudo eliminar el archivo de la tabla de documentos Documento Nro. <B>" + s2 + "</B>");
                        } else
                        {
                            EscribeError(printwriter, "No es posible eliminar la tupla ya que esta usada en documentos existentes.");
                        }
                    } else
                    if(s1.compareTo("N") == 0)
                    {
                        sql = "select count(*) from gdc.documentoscalidad where tipodocumento = '" + s3 + "'";
                        sql = sql + " and proceso = '" + s4 + "'";
                        sql = sql + " and descripcion = '" + s5 + "'";
                        int k = ADatos.ConsultaDB(sql);
                        rs = ADatos.getResult();
                        if(rs.size() >= 0) {
                            Integer integer2 = new Integer(0);
                            try {
	                            integer2 = (Integer)rs.elementAt(0);
                            } catch(ClassCastException e ) { }
                            l1 = integer2.intValue();
                        }
                        if(l1 == 0)
                        {
                            sql = "insert into gdc.documentoscalidad (tipodocumento,proceso,descripcion,tiemporetencion,tiempomedida,wf_aprobacion,wf_ejecucion,idlistadistribucion,tipo) values (";
                            sql = sql + "'" + s3 + "',";
                            sql = sql + "'" + s4 + "',";
                            sql = sql + "'" + s5 + "',";
                            sql = sql + s6 + ",";
                            sql = sql + "'" + s7 + "',";
                            sql = sql + s8.toString() + ",";
                            sql = sql + s9.toString() + ",";
                            sql = sql + s10.toString() + ", ";
                            sql = sql + "'" + s11.toString() + "')";
                            int l = ADatos.ModificaDB(sql);
                            if(l != -1)
                                EscribeError(printwriter, "Documento agregado exitosamente.");
                            else
                                EscribeError(printwriter, "El documento no se puede agregar. Intente m\341s tarde o informe al administrador.");
                        } else
                        {
                            EscribeError(printwriter, "Documento ya existe.");
                        }
                    } else
                    if(s1.compareTo("M") == 0)
                    {
                        String s12 = "";
                        sql = "select descripcion from gdc.documentoscalidad where id = " + s2;
                        int i1 = ADatos.ConsultaDB(sql);
                        rs = ADatos.getResult();
                        if(rs.size() >= 0)
                        {
                            String s13 = (String)rs.elementAt(0);
                            sql = "update gdc.documentoscalidad set descripcion='" + s5 + "', tiemporetencion = '" + s6.toString() + "', tiempomedida='" + s7.toString() + "', wf_aprobacion = " + s8.toString() + ", wf_ejecucion = " + s9.toString() + ", idlistadistribucion = " + s10.toString() + ", tipo = '" + s11.toString() + "' where id = " + s2;
                            int j1 = ADatos.ModificaDB(sql);
                            if(j1 > 0) {
                                sql = "update gdc.documentos set descripcion='" + s5 + "' where descripcion = '" + s13 + "' and tipodoc = '" + s3 + "' and proceso = '" + s4 + "'";
                                int k1 = ADatos.ModificaDB(sql);
                                sql = "update gdc.historico set descripcion='" + s5 + "' where descripcion = '" + s13 + "' and tipodoc = '" + s3 + "' and proceso = '" + s4 + "'";
                                k1 = ADatos.ModificaDB(sql);
                                sql = "update gdc.reciclaje set descripcion='" + s5 + "' where descripcion = '" + s13 + "' and tipodoc = '" + s3 + "' and proceso = '" + s4 + "'";
                                k1 = ADatos.ModificaDB(sql);
                                sql = "update sad.documentos set descripcion='" + s5 + "' where descripcion = '" + s13 + "' and tipodoc = '" + s3 + "' and proceso = '" + s4 + "'";
                                k1 = ADatos.ModificaDB(sql);
                                sql = "update gdc.documentos set descripcion='" + s5 + "' where descripcion = '" + s13 + "' and tipodoc = '" + s3 + "' and proceso = '" + s4 + "'";
                                k1 = ADatos.ModificaDB(sql);
                                sql = "update gdc.reciclaje set descripcion='" + s5 + "' where descripcion = '" + s13 + "' and tipodoc = '" + s3 + "' and proceso = '" + s4 + "'";
                                k1 = ADatos.ModificaDB(sql);
                                EscribeError(printwriter, "Modificaci\363n realizada");
                            } else
                            {
                                EscribeError(printwriter, "No fue posible modificar el nombre.");
                            }
                        } else
                        {
                            EscribeError(printwriter, "Documento no encontrado.");
                        }
                    }
                } else
                {
                    EscribeError(printwriter, "Los Par\341metros de la p\341gina estan vacios");
                }
            } else
            {
                EscribeError(printwriter, "Los Par\341metros de la p\341gina son nulos");
            }
            printwriter.println("</body>");
            printwriter.println("</html>");
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "GDC", 6);
        }
    }
    
    private byte extractValue(Object object) {
    	byte value;
    	try {
    		byte[] b = (byte[])object; 
    		System.out.println(object.getClass().getName());
    		value = b[0];
    		
    	} catch(Exception e ) {
    		value = ((Byte)object ).byteValue();
    	}
    	
    	return value;
    }
    
    void EscribeError(PrintWriter printwriter, String s) {
        printwriter.println("<table border='1' align='center' width='80%'>");
        printwriter.println("<tr>");
        printwriter.println("<td class='texttitulotabla' align='center'>Resultado de la Solicitud</td>");
        printwriter.println("</tr>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla' align='center'>" + s + "</td>");
        printwriter.println("</tr>");
        printwriter.println("<tr><td class='textdesttabla'>&nbsp;</td></tr>");
        printwriter.println("<tr>");
        printwriter.println("<td class='textdesttabla' align='center'><input class='fondoinput' type='button' name='btnAceptar' value='Aceptar' laguage='javascript' onclick=\"window.open('listamaestra.jsp','datos');\"</td>");
        printwriter.println("</tr>");
        printwriter.println("</table>");
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException {
        doGet(httpservletrequest, httpservletresponse);
    }

    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    String sql;
}