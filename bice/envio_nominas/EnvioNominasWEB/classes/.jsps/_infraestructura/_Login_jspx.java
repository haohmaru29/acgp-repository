package _infraestructura;

import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import oracle.jsp.el.*;
import javax.servlet.jsp.el.*;


public class _Login_jspx extends com.orionserver.http.OrionHttpJspPage {


  // ** Begin Declarations


  // ** End Declarations

  public void _jspService(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException, ServletException {

    response.setContentType( "text/html;charset=windows-1252");
    /* set up the intrinsic variables using the pageContext goober:
    ** session = HttpSession
    ** application = ServletContext
    ** out = JspWriter
    ** page = this
    ** config = ServletConfig
    ** all session/app beans declared in globals.jsa
    */
    PageContext pageContext = JspFactory.getDefaultFactory().getPageContext( this, request, response, null, true, JspWriter.DEFAULT_BUFFER, true);
    // Note: this is not emitted if the session directive == false
    HttpSession session = pageContext.getSession();
    int __jsp_tag_starteval;
    ServletContext application = pageContext.getServletContext();
    JspWriter out = pageContext.getOut();
    _Login_jspx page = this;
    ServletConfig config = pageContext.getServletConfig();
    javax.servlet.jsp.el.VariableResolver __ojsp_varRes = (VariableResolver)new OracleVariableResolverImpl(pageContext);

    try {


      out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\" >" );
      out.write( "<html>");
      out.write( "<head>");
      out.write( "<meta"+ " http-equiv=\"" + "Content-Type"+ "\"" + " content=\"" + "text/html; charset=windows-1252"+ "\"" +"/>");
      out.write( "<title>");
      out.write(__oracle_jsp_text[0]);
      out.write( "</title>");
      out.write( "<style" + " type=\"" + "text/css"+ "\"" +">");
      out.write( "</style>");
      out.write( "</head>");
      out.write( "<body>");
      out.write( "<h3" + " class=\"" + "Estilo4"+ "\"" +">");
      out.write(__oracle_jsp_text[1]);
      out.write( "</h3>");
      out.write( "<form" + " action=\"" + "j_security_check"+ "\"" + " method=\"" + "post"+ "\"" +">");
      out.write( "<table" + " cellspacing=\"" + "3"+ "\"" + " cellpadding=\"" + "2"+ "\"" + " border=\"" + "0"+ "\"" + " width=\"" + "770"+ "\"" +">");
      out.write( "<tr" + " valign=\"" + "top"+ "\"" +">");
      out.write( "<td" + " colspan=\"" + "3"+ "\"" +">");
      out.write( "<div>");
      out.write( "<table" + " width=\"" + "100%"+ "\"" + " border=\"" + "0"+ "\"" +">");
      out.write( "<tr>");
      out.write( "<td>");
      out.write(__oracle_jsp_text[2]);
      out.write( "</td>");
      out.write( "<td" + " valign=\"" + "top"+ "\"" +">");
      out.write( "<p>");
      out.write( "<font" + " color=\"" + "#c60000"+ "\"" +">");
      out.write( "<span" + " class=\"" + "Estilo3"+ "\"" +">");
      out.write( "<strong>");
      out.write(__oracle_jsp_text[3]);
      out.write( "</strong>");
      out.write(__oracle_jsp_text[4]);
      out.write( "</span>");
      out.write( "</font>");
      out.write( "</p>");
      out.write( "<p>");
      out.write( "<font" + " color=\"" + "#c60000"+ "\"" +">");
      out.write( "<span" + " class=\"" + "Estilo3"+ "\"" +">");
      out.write(__oracle_jsp_text[5]);
      out.write( "<a" + " href=\"" + "http://andromeda.bicevida.cl/wpmu/arquitectura/servidores-de-aplicaciones#appserv-desarrollo"+ "\"" +">");
      out.write(__oracle_jsp_text[6]);
      out.write( "</a>");
      out.write(__oracle_jsp_text[7]);
      out.write( "<a" + " href=\"" + "http://blog/appservers.html"+ "\"" +">");
      out.write(__oracle_jsp_text[8]);
      out.write( "</a>");
      out.write( "</span>");
      out.write( "</font>");
      out.write(__oracle_jsp_text[9]);
      out.write( "</p>");
      out.write( "<p>");
      out.write( "<font" + " color=\"" + "#c60000"+ "\"" +">");
      out.write( "<span" + " class=\"" + "Estilo3"+ "\"" +">");
      out.write(__oracle_jsp_text[10]);
      out.write( "<a" + " href=\"" + "http://andromeda.bicevida.cl/wpmu/arquitectura/servidores-de-aplicaciones#appserv-produccion"+ "\"" +">");
      out.write(__oracle_jsp_text[11]);
      out.write( "</a>");
      out.write(__oracle_jsp_text[12]);
      out.write( "<strong>");
      out.write(__oracle_jsp_text[13]);
      out.write( "</strong>");
      out.write(__oracle_jsp_text[14]);
      out.write( "<a" + " href=\"" + "http://blog/appservers.html"+ "\"" +">");
      out.write(__oracle_jsp_text[15]);
      out.write( "</a>");
      out.write( "</span>");
      out.write( "</font>");
      out.write(__oracle_jsp_text[16]);
      out.write( "</p>");
      out.write( "</td>");
      out.write( "</tr>");
      out.write( "</table>");
      out.write( "</div>");
      out.write( "<div"+ " align=\"" + "justify"+ "\"" +"/>");
      out.write( "</td>");
      out.write( "</tr>");
      out.write( "<tr>");
      out.write( "<td" + " width=\"" + "76"+ "\"" + " class=\"" + "Estilo3"+ "\"" +">");
      out.write( "<b>");
      out.write(__oracle_jsp_text[17]);
      out.write( "</b>");
      out.write( "</td>");
      out.write( "<td" + " width=\"" + "429"+ "\"" + " class=\"" + "Estilo3"+ "\"" +">");
      out.write( "<input"+ " type=\"" + "text"+ "\"" + " name=\"" + "j_username"+ "\"" +"/>");
      out.write( "</td>");
      out.write( "<td"+ " width=\"" + "241"+ "\"" +"/>");
      out.write( "</tr>");
      out.write( "<tr>");
      out.write( "<td" + " width=\"" + "76"+ "\"" + " class=\"" + "Estilo3"+ "\"" +">");
      out.write( "<b>");
      out.write(__oracle_jsp_text[18]);
      out.write( "</b>");
      out.write( "</td>");
      out.write( "<td" + " width=\"" + "429"+ "\"" + " class=\"" + "Estilo3"+ "\"" +">");
      out.write( "<input"+ " type=\"" + "password"+ "\"" + " name=\"" + "j_password"+ "\"" +"/>");
      out.write( "</td>");
      out.write( "<td"+ " width=\"" + "241"+ "\"" +"/>");
      out.write( "</tr>");
      out.write( "<tr>");
      out.write( "<td" + " width=\"" + "76"+ "\"" + " class=\"" + "Estilo3"+ "\"" +">");
      out.write(__oracle_jsp_text[19]);
      out.write( "</td>");
      out.write( "<td" + " width=\"" + "429"+ "\"" + " class=\"" + "Estilo3"+ "\"" +">");
      out.write( "<input"+ " type=\"" + "submit"+ "\"" + " name=\"" + "logon"+ "\"" + " value=\"" + "Ingresar"+ "\"" +"/>");
      out.write( "</td>");
      out.write( "<td"+ " width=\"" + "241"+ "\"" +"/>");
      out.write( "</tr>");
      out.write( "<tr>");
      out.write( "<td" + " colspan=\"" + "3"+ "\"" + " class=\"" + "Estilo3"+ "\"" +">");
      out.write(__oracle_jsp_text[20]);
      out.write( "<p>");
      out.write(__oracle_jsp_text[21]);
      out.write( "</p>");
      out.write( "<ul>");
      out.write( "<li>");
      out.write( "<p>");
      out.write(__oracle_jsp_text[22]);
      out.write( "</p>");
      out.write( "</li>");
      out.write( "<li>");
      out.write( "<p>");
      out.write(__oracle_jsp_text[23]);
      out.write( "</p>");
      out.write( "</li>");
      out.write( "</ul>");
      out.write( "<p>");
      out.write(__oracle_jsp_text[24]);
      out.write( "</p>");
      out.write( "<ul>");
      out.write( "<li>");
      out.write( "<p>");
      out.write(__oracle_jsp_text[25]);
      out.write( "</p>");
      out.write( "</li>");
      out.write( "<li>");
      out.write( "<p>");
      out.write(__oracle_jsp_text[26]);
      out.write( "</p>");
      out.write( "</li>");
      out.write( "</ul>");
      out.write( "</td>");
      out.write( "</tr>");
      out.write( "</table>");
      out.write( "</form>");
      out.write( "</body>");
      out.write( "</html>");

    }
    catch (Throwable e) {
      if (!(e instanceof javax.servlet.jsp.SkipPageException)){
        try {
          if (out != null) out.clear();
        }
        catch (Exception clearException) {
        }
        pageContext.handlePageException(e);
      }
    }
    finally {
      OracleJspRuntime.extraHandlePCFinally(pageContext, true);
      JspFactory.getDefaultFactory().releasePageContext(pageContext);
    }

  }
  private static final char __oracle_jsp_text[][]=new char[27][];
  static {
    try {
    __oracle_jsp_text[0] = 
    "login".toCharArray();
    __oracle_jsp_text[1] = 
    "Ingreso de Usuarios a BICE CoreWebApp".toCharArray();
    __oracle_jsp_text[2] = 
    " ".toCharArray();
    __oracle_jsp_text[3] = 
    "Importante: ".toCharArray();
    __oracle_jsp_text[4] = 
    " Esta página de login está pensada para ser utilizada en pruebas locales de\n                  aplicación (ejecutando la aplicación desde JDeloper). ".toCharArray();
    __oracle_jsp_text[5] = 
    "                Para despliegues en ".toCharArray();
    __oracle_jsp_text[6] = 
    "servidores de aplicaciones de desarrollo".toCharArray();
    __oracle_jsp_text[7] = 
    " se recomienda utilizar integración con ".toCharArray();
    __oracle_jsp_text[8] = 
    "Oracle SSO".toCharArray();
    __oracle_jsp_text[9] = 
    ".".toCharArray();
    __oracle_jsp_text[10] = 
    "Para despliegues en ".toCharArray();
    __oracle_jsp_text[11] = 
    "servidores de aplicaciones de producción".toCharArray();
    __oracle_jsp_text[12] = 
    " se ".toCharArray();
    __oracle_jsp_text[13] = 
    "debe".toCharArray();
    __oracle_jsp_text[14] = 
    " utilizar integración con ".toCharArray();
    __oracle_jsp_text[15] = 
    "Oracle SSO".toCharArray();
    __oracle_jsp_text[16] = 
    ".".toCharArray();
    __oracle_jsp_text[17] = 
    "Username:".toCharArray();
    __oracle_jsp_text[18] = 
    "Password:".toCharArray();
    __oracle_jsp_text[19] = 
    " ".toCharArray();
    __oracle_jsp_text[20] = 
    "Existen dos usuarios de ejemplo en el proveedor de seguridad  local de\n                CoreWebApp (archivo jazn-data.xml.xml):\n              ".toCharArray();
    __oracle_jsp_text[21] = 
    "\n                1.- Usuario de grupo BICEVIDANET:              ".toCharArray();
    __oracle_jsp_text[22] = 
    "\n                    Usuario: 12631155-9                  ".toCharArray();
    __oracle_jsp_text[23] = 
    "\n                    Password: 123456                  ".toCharArray();
    __oracle_jsp_text[24] = 
    "\n                2.- Usuario de grupo INTRANET:              ".toCharArray();
    __oracle_jsp_text[25] = 
    "\n                    Usuario: jose.arias                  ".toCharArray();
    __oracle_jsp_text[26] = 
    "\n                    Password: 123456                  ".toCharArray();
    }
    catch (Throwable th) {
      System.err.println(th);
    }
}
}
