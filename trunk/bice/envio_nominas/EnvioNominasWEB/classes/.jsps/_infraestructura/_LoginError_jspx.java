package _infraestructura;

import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import oracle.jsp.el.*;
import javax.servlet.jsp.el.*;


public class _LoginError_jspx extends com.orionserver.http.OrionHttpJspPage {


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
    _LoginError_jspx page = this;
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
      out.write( "</head>");
      out.write( "<body>");
      out.write( "<h2>");
      out.write( "<strong>");
      out.write(__oracle_jsp_text[1]);
      out.write( "</strong>");
      out.write( "</h2>");
      out.write( "<p" + " class=\"" + "AFLabelText"+ "\"" +">");
      out.write(__oracle_jsp_text[2]);
      out.write( "</p>");
      out.write( "<p" + " class=\"" + "AFLabelText"+ "\"" +">");
      out.write(__oracle_jsp_text[3]);
      out.write( "</p>");
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
  private static final char __oracle_jsp_text[][]=new char[4][];
  static {
    try {
    __oracle_jsp_text[0] = 
    "loginerror".toCharArray();
    __oracle_jsp_text[1] = 
    "Error de Acceso".toCharArray();
    __oracle_jsp_text[2] = 
    "\n        No tiene acceso a la página o ha ingresado con una clave incorrecta.\n      ".toCharArray();
    __oracle_jsp_text[3] = 
    "\n        Por favor intente nuevamente.\n      ".toCharArray();
    }
    catch (Throwable th) {
      System.err.println(th);
    }
}
}
