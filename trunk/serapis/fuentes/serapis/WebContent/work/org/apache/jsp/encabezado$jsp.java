package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;


public class encabezado$jsp extends HttpJspBase {


    static {
    }
    public encabezado$jsp( ) {
    }

    private static boolean _jspx_inited = false;

    public final void _jspx_init() throws org.apache.jasper.runtime.JspException {
    }

    public void _jspService(HttpServletRequest request, HttpServletResponse  response)
        throws java.io.IOException, ServletException {

        JspFactory _jspxFactory = null;
        PageContext pageContext = null;
        HttpSession session = null;
        ServletContext application = null;
        ServletConfig config = null;
        JspWriter out = null;
        Object page = this;
        String  _value = null;
        try {

            if (_jspx_inited == false) {
                synchronized (this) {
                    if (_jspx_inited == false) {
                        _jspx_init();
                        _jspx_inited = true;
                    }
                }
            }
            _jspxFactory = JspFactory.getDefaultFactory();
            response.setContentType("text/html;ISO-8859-1");
            pageContext = _jspxFactory.getPageContext(this, request, response,
            			"", true, 8192, true);

            application = pageContext.getServletContext();
            config = pageContext.getServletConfig();
            session = pageContext.getSession();
            out = pageContext.getOut();

            // begin [file="/encabezado.jsp";from=(0,2);to=(7,0)]
                
                HttpSession varsession;
                
                varsession = request.getSession(true);
                String Nombre = (String)varsession.getValue("SerapisNombre");
                if(Nombre == null)
                   Nombre = "&nbsp;";
            // end
            // HTML // begin [file="/encabezado.jsp";from=(7,2);to=(20,7)]
                out.write("\r\n<html>\r\n<head>\r\n</head>\r\n<LINK REL=\"stylesheet\" TYPE=\"text/css\" HREF=\"serapis.css\">\r\n<TITLE>SERAPIS. Sistema de Gestión de Calidad</TITLE>\r\n<body leftmargin='0' topmargin='0' >\r\n<table align=\"left\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n  <tr>\r\n  \t <td width=\"168px\" valign='top' align=\"left\" class='fondoencabserapisp1'>\r\n  \t \t<table align=\"left\" width=\"168px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n  \t \t\t<tr><td height='46px'>&nbsp;</td></tr>\r\n  \t \t\t<tr><td height='22px'><B>&nbsp;\r\n  \t \t\t\t");

            // end
            // begin [file="/encabezado.jsp";from=(20,9);to=(20,32)]
                if(Nombre.length() > 0)
            // end
            // HTML // begin [file="/encabezado.jsp";from=(20,34);to=(21,10)]
                out.write("\r\n  \t    \t\t\t");

            // end
            // begin [file="/encabezado.jsp";from=(21,13);to=(21,19)]
                out.print(Nombre);
            // end
            // HTML // begin [file="/encabezado.jsp";from=(21,21);to=(37,0)]
                out.write("\r\n  \t \t\t</B></td></tr>\r\n  \t \t</table>\r\n  \t </td>\r\n    <td align=\"left\" valign='top' width=\"632px\"><a name=\"principio\"></a> \r\n    <object classid=\"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000\" codebase=\"http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0\" width=\"632px\" height=\"68px\">\r\n        <param name=\"movie\" value=\"images/encab_parte02.swf\">\r\n        <param name=\"quality\" value=\"high\"><param name=\"SCALE\" value=\"exactfit\">\r\n        <embed src=\"images/encab_parte02.swf\" quality=\"high\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" type=\"application/x-shockwave-flash\" width=\"100%\" height=\"68px\" scale=\"exactfit\"></embed>\r\n    </object>\r\n    </td>\r\n    <td valign='top' align=\"left\"><img src='images/encab_parte03.jpg' border='0' height=\"68px\"></td>\r\n  </tr>\r\n</table>\r\n</body>\r\n</html>\r\n");

            // end

        } catch (Throwable t) {
            if (out != null && out.getBufferSize() != 0)
                out.clearBuffer();
            if (pageContext != null) pageContext.handlePageException(t);
        } finally {
            if (_jspxFactory != null) _jspxFactory.releasePageContext(pageContext);
        }
    }
}
