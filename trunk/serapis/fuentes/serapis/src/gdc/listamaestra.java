// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   listamaestra.java

package gdc;

import Acc.AccDataBase;
import Acc.funciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class listamaestra extends HttpServlet
{

    public listamaestra()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        TipoSel = "";        
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
    	this.request = httpservletrequest;
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s = (String)httpsession.getValue("SerapisUser");
        if(s != null && s.length() > 0)
        {
            TipoSel = httpservletrequest.getParameter("TIPO");
            printwriter.println("<html>");
            printwriter.println("<head>");
            printwriter.println("</head>");
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<table border='0' width='95%' align='center'>");
            if(TipoSel.compareTo("D") == 0)
                printwriter.println("<tr><td align='center'  class='texttitulomayor'>Control de Documentos</td></tr>");
            else
                printwriter.println("<tr><td align='center'  class='texttitulomayor'>Control de Registros</td></tr>");
            printwriter.println("</table>");
            CargaDocumentos(printwriter,TipoSel);
            /*if (TipoSel.equals("R")) {
            	printwriter.println("<BR>");
                printwriter.println("<table border='0' width='95%' align='center'>");
                printwriter.println("<tr><td align='center'  class='texttitulomayor'>Control de Proyectos</td></tr>");
                printwriter.println("</table>");            	
            	CargaClientesProyecto(printwriter, TipoSel);
            }*/
            printwriter.println("<BR><center><input type='button' name='BtnImprimir' value='Imprimir' class='fondoinput' language='javascript' onclick='window.print()'></center></td>");
            printwriter.println("</body>");
            printwriter.println("</html>");
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "GDC", 6);
        }
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    void CargaDocumentos(PrintWriter printwriter) {
    	CargaDocumentos(printwriter, "");
    }
    
    void CargaDocumentos(PrintWriter printwriter, String sTipo)
    {
        rs = new Vector();
        Integer integer = new Integer(0);
        Integer integer2 = new Integer(0);
        String s = "";
        String s1 = "";
        ADatos.connect();
        int i = ADatos.ConsultaDB("select dc.id,p.descripcion,td.descripcion,dc.descripcion,dc.tiemporetencion,dc.tiempomedida,p.sigla,td.sigla from gdc.documentoscalidad dc,gdc.procesos p,gdc.tipodocumentos td where dc.tipo = '" + TipoSel + "' and dc.tipodocumento = td.sigla and dc.proceso = p.sigla order by p.descripcion,td.descripcion,dc.descripcion");
        rs = ADatos.getResult();
        if(rs.size() > 0)
        {
            for(int j = 0; j < rs.size(); j += 8)
            {
                if(s.compareTo((String)rs.elementAt(1 + j)) != 0)
                {
                    if(s.length() > 0)
                    {
                        printwriter.println("</table>");
                        printwriter.println("<BR>");
                    }
                     
                    if (sTipo.equals("R"))
                    {                    	
                    	sad.muestradoc oAcc = new sad.muestradoc();
                    	String sAcceso = oAcc.TieneAcceso(printwriter, "PROCESO", (String)rs.elementAt(6 + j) , 0L, (String)this.request.getSession(true).getValue("SerapisUser"));
                    	if(sAcceso.compareTo("00000") == 0 || sAcceso.compareTo("00001") == 0)
                    		continue;
                    		
                    }
                    printwriter.println("<table border='1' align='center' width='95%'>");
                    printwriter.println("<tr><td class='texttituloarea' colspan='4'>" + (String)rs.elementAt(1 + j) + "</td></tr>");
                    printwriter.println("</table>");
                    printwriter.println("<table border='1' align='center' width='95%'>");
                    s = (String)rs.elementAt(1 + j);
                    printwriter.println("<tr>");
                    printwriter.println("<td class='texttitulotabla'>&nbsp;</td>");
                    printwriter.println("<td class='texttitulotabla'>Tipo Documento</a></td>");
                    printwriter.println("<td class='texttitulotabla'>Nombre</td>");
                    printwriter.println("<td class='texttitulotabla'>Tiempo Retenci\363n</td>");
                    printwriter.println("</tr>");
                }
                Integer integer1 = (Integer)rs.elementAt(j);
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'><a href='verdocumentocalidad.jsp?ID=" + integer1.intValue() + "'><IMG src='../images/folderterminal.gif' width=16 height=16 border='0'></a></td>");
                printwriter.println("<td class='textdesttabla'>" + (String)rs.elementAt(2 + j) + "</a></td>");
                //printwriter.println("<td class='textdesttabla'><a href=\"javascript:window.open('cargadocumento.jsp?AREA=" + (String)rs.elementAt(6 + j) + "&TIPODOC=" + (String)rs.elementAt(7 + j) + "&DESCRIPCION=" + (String)rs.elementAt(3 + j) + "&TIPO=" + sTipo + "','cuerpo')\">" + (String)rs.elementAt(3 + j) + "</a></td>");
                printwriter.println("<td class='textdesttabla3'>" + (String)rs.elementAt(3 + j) + "</td>");
                Integer integer3 = (Integer)rs.elementAt(4 + j);
                if(integer3.intValue() > 0)
                {
                    String s2 = (String)rs.elementAt(5 + j);
                    if(s2.compareTo("A\361o") == 0)
                        printwriter.println("<td class='textdesttabla'>" + integer3.toString() + " A\361os</td>");
                    if(s2.compareTo("Mes") == 0)
                        printwriter.println("<td class='textdesttabla'>" + integer3.toString() + " Meses</td>");
                    if(s2.compareTo("D\355a") == 0)
                        printwriter.println("<td class='textdesttabla'>" + integer3.toString() + " Dias</td>");
                } else
                {
                    printwriter.println("<td class='textdesttabla'>&nbsp;</td>");
                }
                printwriter.println("</tr>");
            }

            printwriter.println("</table>");
        } else
        {
            printwriter.println("<table border='0' align='center' width='80%'>");
            printwriter.println("<tr>");
            printwriter.println("<td class='texttitulotabla' align='center'>No hay documentos por desplegar</td>");
            printwriter.println("<tr>");
            printwriter.println("</table>");
        }
    }

    void CargaClientesProyecto(PrintWriter printwriter, String sTipo) {
        rs = new Vector();
        Integer integer = new Integer(0);
        Integer integer2 = new Integer(0);
        String s = "";
        String st= "";
        String s1 = "";
        ADatos.connect();
        String sSql = "select distinct p.id, dc.rutcliente, dc.razonsocial, p.proyecto, s.proceso, pr.descripcion";
        sSql += " from sgc.clientes dc, gdc.proyectos p, sad.documentosp s, gdc.procesos pr";
        sSql += " where p.cliente=dc.rutcliente and s.proyecto=p.abreviatura and s.cliente=dc.abreviatura and pr.sigla = s.proceso";
        sSql += " group by dc.rutcliente asc, dc.razonsocial, s.proceso, p.proyecto";

        int i = ADatos.ConsultaDB(sSql);
        rs = ADatos.getResult();
        if(rs.size() > 0)
        {
            for(int j = 0; j < rs.size(); j += 6)
            {
                if(s.compareTo((String)rs.elementAt(1 + j)) != 0 || st.compareTo((String)rs.elementAt(4+j)) != 0 ) //cliente o proceso
                {
                    if(s.length() > 0)
                    {
                        printwriter.println("</table>");
                        printwriter.println("<BR>");
                    }
                    printwriter.println("<table border='1' align='center' width='95%'>");
                    printwriter.println("<tr><td class='texttituloarea' colspan='4'>" + (String)rs.elementAt(2 + j) + "</td></tr>");
                    printwriter.println("<tr><td class='texttituloarea' colspan='4'>" + (String)rs.elementAt(5 + j) + "</td></tr>");
                    printwriter.println("</table>");
                    printwriter.println("<table border='1' align='center' width='95%'>");
                    printwriter.println("<tr>");
                    printwriter.println("<td class='texttitulotabla' width='30'>&nbsp;</td>");
                    printwriter.println("<td class='texttitulotabla' colspan='3'>Proyecto</a></td>");
                    //printwriter.println("<td class='texttitulotabla'></td>");
                    printwriter.println("</tr>");
                }
                s = (String)rs.elementAt(1 + j);
                st = (String)rs.elementAt(4 + j);
                Integer integer1 = (Integer)rs.elementAt(j);
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'><a href='verdocumentocalidad.jsp?ID=" + integer1.intValue() + "'><IMG src='../images/folderterminal.gif' width=16 height=16 border='0'></a></td>");
                printwriter.println("<td class='textdesttabla' colspan='3'>" + (String)rs.elementAt(3 + j) + "</a></td>");
                //printwriter.println("<td class='textdesttabla'>&nbsp;</td>");
               // printwriter.println("<td class='textdesttabla'><a href=\"javascript:window.open('cargadocumento.jsp?AREA=" + (String)rs.elementAt(6 + j) + "&TIPODOC=" + (String)rs.elementAt(7 + j) + "&DESCRIPCION=" + (String)rs.elementAt(3 + j) + "&TIPO=" + sTipo + "','cuerpo')\">" + (String)rs.elementAt(3 + j) + "</a></td>");
                //printwriter.println("<td class='textdesttabla'>&nbsp;</td>");
                printwriter.println("</tr>");
            }

            printwriter.println("</table>");
        } else
        {
            printwriter.println("<table border='0' align='center' width='80%'>");
            printwriter.println("<tr>");
            printwriter.println("<td class='texttitulotabla' align='center'>No hay proyectos por desplegar</td>");
            printwriter.println("<tr>");
            printwriter.println("</table>");
        }    	
    }
    
    AccDataBase ADatos;
    funciones AFunc;
    Vector rs;
    String TipoSel;
    HttpServletRequest request;
}