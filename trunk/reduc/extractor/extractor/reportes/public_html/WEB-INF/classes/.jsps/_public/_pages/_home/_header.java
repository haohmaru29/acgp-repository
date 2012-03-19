  /*@lineinfo:filename=/public/pages/home/header.jsp*/
  /*@lineinfo:generated-code*/
package _public._pages._home;

import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;


public class _header extends oracle.jsp.runtime.HttpJsp {

  public final String _globalsClassName = null;

  // ** Begin Declarations


  // ** End Declarations

  public void _jspService(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException, ServletException {

    response.setContentType( "text/html;charset=UTF-8");
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
    if (pageContext.getAttribute(OracleJspRuntime.JSP_REQUEST_REDIRECTED, PageContext.REQUEST_SCOPE) != null) {
      pageContext.setAttribute(OracleJspRuntime.JSP_PAGE_DONTNOTIFY, "true", PageContext.PAGE_SCOPE);
      JspFactory.getDefaultFactory().releasePageContext(pageContext);
      return;
}
    int __jsp_tag_starteval;
    ServletContext application = pageContext.getServletContext();
    JspWriter out = pageContext.getOut();
    _header page = this;
    ServletConfig config = pageContext.getServletConfig();

    try {
      // global beans
      // end global beans


      out.write(__oracle_jsp_text[0]);


    }
    catch( Throwable e) {
      try {
        if (out != null) out.clear();
      }
      catch( Exception clearException) {
      }
      pageContext.handlePageException( e);
    }
    finally {
      OracleJspRuntime.extraHandlePCFinally(pageContext,false);
      JspFactory.getDefaultFactory().releasePageContext(pageContext);
    }

  }
  private static final char __oracle_jsp_text[][]=new char[1][];
  static {
    try {
    __oracle_jsp_text[0] = 
    "<div id=\"north\">\n  <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >\n      <tr>\n        <td align=\"right\" valign=\"top\" background=\"public/images/top/fondo_cabecera_gral2.jpg\" style=\"vertical-align:top;\">\n          <table  border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"textobco\">\n            <tr>\n              <td height=\"20\" align=\"right\"><strong>RED SALUD UC</strong></td>\n              <td width=\"17\" align=\"right\">&nbsp;</td>\n            </tr>\n            <tr>\n              <td height=\"21\" colspan=\"2\" align=\"right\"><img src=\"public/images/punto.gif\" width=\"1\" height=\"1\" /></td> \n            </tr>\n            <tr>\n              <td height=\"24\" colspan=\"2\" align=\"right\">\n                <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n                    <tr>\n                      <td></td>\n                      <td></td>\n                      <td></td>\n                      <td>&nbsp;</td>\n                      <td></td>\n                      <td ></td>\n                      <td></td>\n                      <td>&nbsp;</td>\n                    </tr>\n              </table>\n          </td>\n        </tr>\n      </table>\n      </td>\n    </tr>\n    <tr>\n      <td >\n      </td>\n    </tr>\n  </table>\n</div>".toCharArray();
    }
    catch (Throwable th) {
      System.err.println(th);
    }
}
}
