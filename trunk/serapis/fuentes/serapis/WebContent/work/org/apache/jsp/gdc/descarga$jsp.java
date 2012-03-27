package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;


public class descarga$jsp extends HttpJspBase {

    // begin [file="/gdc/descarga.jsp";from=(6,0);to=(6,92)]
    // end

    static {
    }
    public descarga$jsp( ) {
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

            // begin [file="/gdc/descarga.jsp";from=(0,2);to=(4,0)]
                
                String Archivo = request.getParameter("FILEDESC");
                if(Archivo==null)
                	Archivo="";
            // end
            // HTML // begin [file="/gdc/descarga.jsp";from=(4,2);to=(6,0)]
                out.write("\r\n<html>\r\n");

            // end
            // begin [file="/gdc/descarga.jsp";from=(6,0);to=(6,92)]
                javazoom.download.DownloadBean downloadbean = null;
                boolean _jspx_specialdownloadbean  = false;
                 synchronized (application) {
                    downloadbean= (javazoom.download.DownloadBean)
                    pageContext.getAttribute("downloadbean",PageContext.APPLICATION_SCOPE);
                    if ( downloadbean == null ) {
                        _jspx_specialdownloadbean = true;
                        try {
                            downloadbean = (javazoom.download.DownloadBean) java.beans.Beans.instantiate(this.getClass().getClassLoader(), "javazoom.download.DownloadBean");
                        } catch (ClassNotFoundException exc) {
                             throw new InstantiationException(exc.getMessage());
                        } catch (Exception exc) {
                             throw new ServletException (" Cannot create bean of class "+"javazoom.download.DownloadBean", exc);
                        }
                        pageContext.setAttribute("downloadbean", downloadbean, PageContext.APPLICATION_SCOPE);
                    }
                 } 
                if(_jspx_specialdownloadbean == true) {
            // end
            // begin [file="/gdc/descarga.jsp";from=(6,0);to=(6,92)]
                }
            // end
            // HTML // begin [file="/gdc/descarga.jsp";from=(6,92);to=(25,29)]
                out.write("\r\n<head>\r\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">\r\n</head>\r\n<LINK REL=\"stylesheet\" TYPE=\"text/css\" HREF=\"../serapis.css\">\r\n<TITLE>SERAPIS. Sistema de Gestión de Calidad.</TITLE>\r\n<BODY bgcolor='white' leftmargin='0' topmargin='0' onload=''>\r\n<BR><BR>\r\n<table border=\"0\" cellspacing=\"1\" cellpadding=\"0\" align=\"center\">\r\n    <tr><td class='tituloespecial' width='95%' height='60'>&nbsp;&nbsp;DESCARGA DE ARCHIVOS</td></tr>\r\n</table>\r\n<BR>\r\n<table border=\"0\" cellspacing=\"1\" cellpadding=\"0\" align=\"center\">\r\n    <tr><td width='95%'>Se ha solicitado la descarga de un archivo del sistema de Gestión Documental.</td></tr>\r\n    <tr><td width='95%'>Presione sobre el link y guarde el archivo solicitado.</td></tr>\r\n</table>\r\n<BR><BR>\r\n<table border=\"0\" cellspacing=\"1\" cellpadding=\"0\" align=\"center\">\r\n    <tr>\r\n    <td width='15%'><a href=\"");

            // end
            // begin [file="/gdc/descarga.jsp";from=(25,32);to=(25,58)]
                out.print( request.getContextPath() );
            // end
            // HTML // begin [file="/gdc/descarga.jsp";from=(25,60);to=(25,70)]
                out.write("/download/");

            // end
            // begin [file="/gdc/descarga.jsp";from=(25,73);to=(25,80)]
                out.print(Archivo);
            // end
            // HTML // begin [file="/gdc/descarga.jsp";from=(25,82);to=(26,32)]
                out.write("\"><img src='../images/extdefault.gif' border='0'></a></td>\r\n    <td width='80%'><b><a href=\"");

            // end
            // begin [file="/gdc/descarga.jsp";from=(26,35);to=(26,61)]
                out.print( request.getContextPath() );
            // end
            // HTML // begin [file="/gdc/descarga.jsp";from=(26,63);to=(26,73)]
                out.write("/download/");

            // end
            // begin [file="/gdc/descarga.jsp";from=(26,76);to=(26,83)]
                out.print(Archivo);
            // end
            // HTML // begin [file="/gdc/descarga.jsp";from=(26,85);to=(26,87)]
                out.write("\">");

            // end
            // begin [file="/gdc/descarga.jsp";from=(26,90);to=(26,97)]
                out.print(Archivo);
            // end
            // HTML // begin [file="/gdc/descarga.jsp";from=(26,99);to=(31,0)]
                out.write("</a></b></td>\r\n    </tr>\r\n</table>\r\n</body>\r\n</html>\r\n");

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
