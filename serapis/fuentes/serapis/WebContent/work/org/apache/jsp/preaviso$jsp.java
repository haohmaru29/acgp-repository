package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;


public class preaviso$jsp extends HttpJspBase {


    static {
    }
    public preaviso$jsp( ) {
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

            // HTML // begin [file="/preaviso.jsp";from=(0,0);to=(11,7)]
                out.write("<HTML>\r\n<HEAD>\r\n<title>SERAPIS. Sistema de Gesti\\363n de Calidad.</title>\r\n</HEAD>\r\n</HEAD>\r\n<script language=\"javascript\">\r\n\twindow.open(\"aviso.jsp\",\"_blank\",\"toolbar=no,left=0,top=20,width=750,height=400,directories=no,status=yes,scrollbars=yes,resize=yes,menubar=no\");\r\n\twindow.open(\"index.jsp\",\"_top\");\r\n</script>\r\n<body bgcolor='#FFFFFF'>\r\n</BODY>\r\n</HTML>");

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
