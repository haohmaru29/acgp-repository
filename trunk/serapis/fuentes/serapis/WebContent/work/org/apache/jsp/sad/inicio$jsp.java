package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;


public class inicio$jsp extends HttpJspBase {


    static {
    }
    public inicio$jsp( ) {
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

            // HTML // begin [file="/sad/inicio.jsp";from=(0,0);to=(1,0)]
                out.write("<!-- Forward to a servlet -->\r\n");

            // end
            // begin [file="/sad/inicio.jsp";from=(1,0);to=(1,42)]
                if (true) {
                    out.clear();
                    String _jspx_qfStr = "";
                    pageContext.forward("/servlet/sad.inicio" +  _jspx_qfStr);
                    return;
                }
            // end
            // HTML // begin [file="/sad/inicio.jsp";from=(1,42);to=(3,0)]
                out.write("\r\n\r\n");

            // end
            // begin [file="/sad/inicio.jsp";from=(3,2);to=(17,1)]
                
                HttpSession Session = request.getSession(true);
                String UserGDC = (String)session.getValue("SerapisUser");
                if((UserGDC!=null) && (UserGDC.length()>0))
                {
                	//Session.TimeOut = 60;
                	
                	String Tipo = request.getParameter("TIPO");
                	String SubTipo = request.getParameter("SUBTIPO");
                	
                	if(Tipo == null)
                		Tipo="DOC";
                	if(SubTipo == null)
                		SubTipo="A";
                	
            // end
            // HTML // begin [file="/sad/inicio.jsp";from=(17,3);to=(24,40)]
                out.write("\r\n\t<html>\r\n\t<head>\r\n\t</head>\r\n\t<LINK REL=\"stylesheet\" TYPE=\"text/css\" HREF=\"../sgcba.css\">\r\n\t<TITLE>Sistema de registros - BYTE ACGP S.A.</TITLE>\r\n\t<frameset framespacing=\"0\" border=\"true\" frameborder=\"0\" cols=\"21%,*\">\t  \t  \r\n\t\t<frame name=\"menu\" src=\"menu.jsp?TIPO=");

            // end
            // begin [file="/sad/inicio.jsp";from=(24,43);to=(24,47)]
                out.print(Tipo);
            // end
            // HTML // begin [file="/sad/inicio.jsp";from=(24,49);to=(24,58)]
                out.write("&SUBTIPO=");

            // end
            // begin [file="/sad/inicio.jsp";from=(24,61);to=(24,68)]
                out.print(SubTipo);
            // end
            // HTML // begin [file="/sad/inicio.jsp";from=(24,70);to=(25,2)]
                out.write("\" scrolling=\"no\" noresize>\r\n\t\t");

            // end
            // begin [file="/sad/inicio.jsp";from=(25,4);to=(26,5)]
                if(Tipo.compareTo("DOC")==0)
                		  {
            // end
            // HTML // begin [file="/sad/inicio.jsp";from=(26,7);to=(27,48)]
                out.write("\r\n\t\t\t<frame name=\"datos\" src=\"muestradoc.jsp?TIPO=");

            // end
            // begin [file="/sad/inicio.jsp";from=(27,51);to=(27,55)]
                out.print(Tipo);
            // end
            // HTML // begin [file="/sad/inicio.jsp";from=(27,57);to=(27,66)]
                out.write("&SUBTIPO=");

            // end
            // begin [file="/sad/inicio.jsp";from=(27,69);to=(27,76)]
                out.print(SubTipo);
            // end
            // HTML // begin [file="/sad/inicio.jsp";from=(27,78);to=(28,2)]
                out.write("\" scrolling=\"yes\" noresize>\r\n\t\t");

            // end
            // begin [file="/sad/inicio.jsp";from=(28,4);to=(28,5)]
                }
            // end
            // HTML // begin [file="/sad/inicio.jsp";from=(28,7);to=(29,2)]
                out.write("\r\n\t\t");

            // end
            // begin [file="/sad/inicio.jsp";from=(29,4);to=(31,34)]
                if(Tipo.compareTo("PUB")==0)
                		  {
                		  	if(SubTipo.compareTo("A")==0)
            // end
            // HTML // begin [file="/sad/inicio.jsp";from=(31,36);to=(33,3)]
                out.write("\r\n\t\t\t\t<frame name=\"datos\" src=\"publicaarea.jsp\" scrolling=\"yes\" noresize>\r\n\t\t\t");

            // end
            // begin [file="/sad/inicio.jsp";from=(33,5);to=(34,33)]
                else
                				if(SubTipo.compareTo("P")==0)
            // end
            // HTML // begin [file="/sad/inicio.jsp";from=(34,35);to=(36,4)]
                out.write("\r\n\t\t\t\t\t<frame name=\"datos\" src=\"publicaproyecto.jsp\" scrolling=\"yes\" noresize>\r\n\t\t\t\t");

            // end
            // begin [file="/sad/inicio.jsp";from=(36,6);to=(36,10)]
                else
            // end
            // HTML // begin [file="/sad/inicio.jsp";from=(36,12);to=(37,50)]
                out.write("\r\n\t\t\t\t\t<frame name=\"datos\" src=\"muestradoc.jsp?TIPO=");

            // end
            // begin [file="/sad/inicio.jsp";from=(37,53);to=(37,57)]
                out.print(Tipo);
            // end
            // HTML // begin [file="/sad/inicio.jsp";from=(37,59);to=(37,68)]
                out.write("&SUBTIPO=");

            // end
            // begin [file="/sad/inicio.jsp";from=(37,71);to=(37,78)]
                out.print(SubTipo);
            // end
            // HTML // begin [file="/sad/inicio.jsp";from=(37,80);to=(38,2)]
                out.write("\" scrolling=\"yes\" noresize>\r\n\t\t");

            // end
            // begin [file="/sad/inicio.jsp";from=(38,4);to=(38,5)]
                }
            // end
            // HTML // begin [file="/sad/inicio.jsp";from=(38,7);to=(39,2)]
                out.write("\r\n\t\t");

            // end
            // begin [file="/sad/inicio.jsp";from=(39,4);to=(40,5)]
                if(Tipo.compareTo("CLI")==0)
                		  {
            // end
            // HTML // begin [file="/sad/inicio.jsp";from=(40,7);to=(42,2)]
                out.write("\r\n\t\t\t<frame name=\"datos\" src=\"vercliente.jsp\" scrolling=\"yes\" noresize>\r\n\t\t");

            // end
            // begin [file="/sad/inicio.jsp";from=(42,4);to=(42,5)]
                }
            // end
            // HTML // begin [file="/sad/inicio.jsp";from=(42,7);to=(46,0)]
                out.write("\r\n\t</frameset>\r\n\t</body>\r\n\t</html>\r\n");

            // end
            // begin [file="/sad/inicio.jsp";from=(46,2);to=(49,1)]
                
                }
                else
                {
            // end
            // HTML // begin [file="/sad/inicio.jsp";from=(49,3);to=(58,0)]
                out.write("\r\n\t<HTML>\r\n\t<HEAD>\r\n\t<title>BYTE ACGP S.A</title>\r\n\t<meta http-equiv=\"refresh\" content=\"0;URL=../autentica.jsp\">\r\n\t</HEAD>\r\n\t<body bgcolor=\"#FFFFFF\">\r\n\t</body>\r\n\t</html>\r\n");

            // end
            // begin [file="/sad/inicio.jsp";from=(58,2);to=(58,3)]
                }
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
