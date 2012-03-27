package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;


public class autentica$jsp extends HttpJspBase {


    static {
    }
    public autentica$jsp( ) {
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

            // begin [file="/autentica.jsp";from=(0,2);to=(4,0)]
                
                String Error = request.getParameter("ERR");
                if(Error==null)
                	Error="0";
            // end
            // HTML // begin [file="/autentica.jsp";from=(4,2);to=(18,0)]
                out.write("\r\n<html>\r\n<head>\r\n</head>\r\n<LINK REL=\"stylesheet\" TYPE=\"text/css\" HREF=\"serapis.css\">\r\n<TITLE>SERAPIS. Sistema de Gestión de Calidad.</TITLE>\r\n<BODY bgcolor='white' leftmargin='0' topmargin='0' onload='document.ident.Login.focus()'>\r\n\r\n<script type='text/javascript' language='JavaScript1.2' src='menu/stm31.js'></script>\r\n<script type='text/javascript' language='JavaScript1.2' src='menu/menu01.js'></script>\r\n<BR><BR><BR>\r\n<form name=\"ident\" action=\"validaclave.jsp\" method=\"post\">\r\n<table align=\"center\" width=\"778\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n<tr>\r\n");

            // end
            // begin [file="/autentica.jsp";from=(18,2);to=(19,1)]
                if(Error.compareTo("2")==0)
                {
            // end
            // HTML // begin [file="/autentica.jsp";from=(19,3);to=(21,0)]
                out.write(" \r\n\t<td class=\"texttituloerror\" align=\"center\"><B>Clave Incorrecta</B></td>\r\n");

            // end
            // begin [file="/autentica.jsp";from=(21,2);to=(21,3)]
                }
            // end
            // HTML // begin [file="/autentica.jsp";from=(21,5);to=(22,0)]
                out.write("\r\n");

            // end
            // begin [file="/autentica.jsp";from=(22,2);to=(23,1)]
                if(Error.compareTo("3")==0) 
                {
            // end
            // HTML // begin [file="/autentica.jsp";from=(23,3);to=(25,0)]
                out.write(" \r\n\t<td class=\"texttituloerror\" align=\"center\"><B>Usuario No Existe</B></td>\r\n");

            // end
            // begin [file="/autentica.jsp";from=(25,2);to=(25,3)]
                }
            // end
            // HTML // begin [file="/autentica.jsp";from=(25,5);to=(26,0)]
                out.write("\r\n");

            // end
            // begin [file="/autentica.jsp";from=(26,2);to=(27,1)]
                if(Error.compareTo("4")==0) 
                {
            // end
            // HTML // begin [file="/autentica.jsp";from=(27,3);to=(29,0)]
                out.write(" \r\n\t<td class=\"texttituloerror\" align=\"center\"><B>Clave no Válida</B></td>\r\n");

            // end
            // begin [file="/autentica.jsp";from=(29,2);to=(29,3)]
                }
            // end
            // HTML // begin [file="/autentica.jsp";from=(29,5);to=(30,0)]
                out.write("\r\n");

            // end
            // begin [file="/autentica.jsp";from=(30,2);to=(31,1)]
                if(Error.compareTo("5")==0) 
                {
            // end
            // HTML // begin [file="/autentica.jsp";from=(31,3);to=(33,0)]
                out.write(" \r\n\t<td class=\"texttituloerror\" align=\"center\"><B>Usuario Inactivo. Informe al Administrador para autorizar su ingreso.</B></td>\r\n");

            // end
            // begin [file="/autentica.jsp";from=(33,2);to=(33,3)]
                }
            // end
            // HTML // begin [file="/autentica.jsp";from=(33,5);to=(34,0)]
                out.write("\r\n");

            // end
            // begin [file="/autentica.jsp";from=(34,2);to=(35,1)]
                if(Error.compareTo("6")==0) 
                {
            // end
            // HTML // begin [file="/autentica.jsp";from=(35,3);to=(37,0)]
                out.write(" \r\n\t<td class=\"texttituloerror\" align=\"center\"><B>Sesión Finalizada. Por seguridad ingrese su autenticación nuevamente</B></td>\r\n");

            // end
            // begin [file="/autentica.jsp";from=(37,2);to=(37,3)]
                }
            // end
            // HTML // begin [file="/autentica.jsp";from=(37,5);to=(38,0)]
                out.write("\r\n");

            // end
            // begin [file="/autentica.jsp";from=(38,2);to=(39,1)]
                if(Error.compareTo("7")==0) 
                {
            // end
            // HTML // begin [file="/autentica.jsp";from=(39,3);to=(41,0)]
                out.write(" \r\n\t<td class=\"texttituloerror\" align=\"center\"><B>Usuario sin Autorización. Inicie Sesión con un usuario valido o acceda a un sistema autorizado para Usted..</B></td>\r\n");

            // end
            // begin [file="/autentica.jsp";from=(41,2);to=(41,3)]
                }
            // end
            // HTML // begin [file="/autentica.jsp";from=(41,5);to=(42,0)]
                out.write("\r\n");

            // end
            // begin [file="/autentica.jsp";from=(42,2);to=(43,1)]
                if(Error.compareTo("8")==0) 
                {
            // end
            // HTML // begin [file="/autentica.jsp";from=(43,3);to=(45,0)]
                out.write(" \r\n\t<td class=\"texttituloerror\" align=\"center\"><B>Problemas con su Licencia. Contacte a su Proveedor.</B></td>\r\n");

            // end
            // begin [file="/autentica.jsp";from=(45,2);to=(45,3)]
                }
            // end
            // HTML // begin [file="/autentica.jsp";from=(45,5);to=(109,0)]
                out.write("\r\n</tr>\r\n</table>\r\n<BR>\r\n<table align=\"center\" width=\"778\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n\t<tr>\r\n\t\t<td colspan=\"2\" align=\"middle\" height=\"60\" class=\"tituloespecial\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sistema Gestión de Calidad</td>\r\n\t</tr>\r\n\t<tr><td colspan=\"2\">&nbsp;</td></tr>\r\n\t<tr>\r\n\t\t<td colspan=\"2\" align=\"middle\" height=\"25\" class=\"texttitulomayor\">Ingrese su Usuario y Password para ingresar al Sistema</td>\r\n\t</tr>\r\n\t<tr><td colspan=\"2\">&nbsp;</td></tr>\r\n\t<tr>\r\n\t\t<td class=\"textpietabla\" align=\"right\" width=\"45%\">USUARIO</font>&nbsp;</td>\r\n\t\t<td align=\"left\" width=\"55%\"><input name=\"Login\" language=\"javascript\" style=\"WIDTH: 150px\" onkeypress=\"\" maxlength=\"20\"></td>\r\n\t</tr>\r\n\t<tr>\r\n\t\t<td  class=\"textpietabla\" align=\"right\">PASSWORD&nbsp;</td>\r\n\t\t<td align=\"left\"><input type=\"password\" name=\"Pass\" maxlength=\"10\" style=\"WIDTH: 150px\" language=\"javascript\" onkeypress=\"\"></td>\r\n\t</tr>\r\n\t<tr><td colspan=\"2\">&nbsp;</td></tr>\r\n\t<tr><td colspan=\"2\">&nbsp;</td></tr>\r\n\t<tr>\r\n\t\t<td align=\"middle\" colspan=\"2\">\r\n\t\t\t<input class=\"fondoinput\" type=\"button\" name=\"ok\" value=\"Aceptar\" language=\"javascript\" onclick=\"return aceptar();\">&nbsp;&nbsp;\r\n\t\t\t<input class=\"fondoinput\" type=\"button\" name=\"btncambio\" value=\"Cambiar Clave\" language=\"javascript\" onclick=\"return cambio();\">\r\n\t\t</td>\r\n\t</tr>\r\n</table>\r\n<script language=\"javascript\">\r\nfunction aceptar()\r\n{\r\n\tif (document.ident.Login.value != \"\")\r\n\t{\r\n\t\tfor (i = eval(document.ident.Pass.value.length + 1); i <= 10; i++)\r\n\t\t{\r\n\t\t\tdocument.ident.Pass.value = document.ident.Pass.value + ' ';\r\n\t\t}\r\n\t\tdocument.ident.submit();\r\n\t}\r\n\telse\r\n\t{\r\n\t\talert(\"Debe Ingresar Usuario\");\r\n\t\tdocument.ident.Login.focus();\r\n\t}\r\n}\r\n\r\nfunction cambio()\r\n{\r\n\tif (document.ident.Login.value != \"\")\r\n\t{\r\n\t\tvar loginuser = document.ident.Login.value;\r\n\t\twindow.open(\"cambio.jsp?USER=\" + loginuser,\"_self\");\t\r\n\t}\r\n\telse\r\n\t{\r\n\t\talert(\"Debe Ingresar Usuario\");\r\n\t\tdocument.ident.Login.focus();\r\n\t}\r\n}\r\n</script>\r\n</body>\r\n</html>\r\n");

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
