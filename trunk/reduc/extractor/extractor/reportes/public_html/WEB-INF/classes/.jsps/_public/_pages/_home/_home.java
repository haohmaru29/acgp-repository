  /*@lineinfo:filename=/public/pages/home/home.jsp*/
  /*@lineinfo:generated-code*/
package _public._pages._home;

import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;


public class _home extends oracle.jsp.runtime.HttpJsp {

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
    _home page = this;
    ServletConfig config = pageContext.getServletConfig();

    try {
      // global beans
      // end global beans


      out.write(__oracle_jsp_text[0]);
      /*@lineinfo:translated-code*//*@lineinfo:9^3*/      {
        String __url=OracleJspRuntime.toStr("../prestacion/include.jsp");
        // Include 
        pageContext.include( __url);
        if (pageContext.getAttribute(OracleJspRuntime.JSP_REQUEST_REDIRECTED, PageContext.REQUEST_SCOPE) != null) return;
      }

      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[1]);
      /*@lineinfo:translated-code*//*@lineinfo:10^3*/      {
        String __url=OracleJspRuntime.toStr("../institucion/include.jsp");
        // Include 
        pageContext.include( __url);
        if (pageContext.getAttribute(OracleJspRuntime.JSP_REQUEST_REDIRECTED, PageContext.REQUEST_SCOPE) != null) return;
      }

      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[2]);
      /*@lineinfo:translated-code*//*@lineinfo:11^3*/      {
        String __url=OracleJspRuntime.toStr("../profesional/include.jsp");
        // Include 
        pageContext.include( __url);
        if (pageContext.getAttribute(OracleJspRuntime.JSP_REQUEST_REDIRECTED, PageContext.REQUEST_SCOPE) != null) return;
      }

      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[3]);
      /*@lineinfo:translated-code*//*@lineinfo:12^3*/      {
        String __url=OracleJspRuntime.toStr("../paciente/include.jsp");
        // Include 
        pageContext.include( __url);
        if (pageContext.getAttribute(OracleJspRuntime.JSP_REQUEST_REDIRECTED, PageContext.REQUEST_SCOPE) != null) return;
      }

      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[4]);
      /*@lineinfo:translated-code*//*@lineinfo:13^3*/      {
        String __url=OracleJspRuntime.toStr("../atencion/include.jsp");
        // Include 
        pageContext.include( __url);
        if (pageContext.getAttribute(OracleJspRuntime.JSP_REQUEST_REDIRECTED, PageContext.REQUEST_SCOPE) != null) return;
      }

      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[5]);
      /*@lineinfo:translated-code*//*@lineinfo:17^3*/      {
        String __url=OracleJspRuntime.toStr("header.jsp");
        // Include 
        pageContext.include( __url);
        if (pageContext.getAttribute(OracleJspRuntime.JSP_REQUEST_REDIRECTED, PageContext.REQUEST_SCOPE) != null) return;
      }

      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[6]);
      /*@lineinfo:translated-code*//*@lineinfo:42^3*/      {
        String __url=OracleJspRuntime.toStr("footer.jsp");
        // Include 
        pageContext.include( __url);
        if (pageContext.getAttribute(OracleJspRuntime.JSP_REQUEST_REDIRECTED, PageContext.REQUEST_SCOPE) != null) return;
      }

      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[7]);


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
  private static final char __oracle_jsp_text[][]=new char[8][];
  static {
    try {
    __oracle_jsp_text[0] = 
    "  <script type=\"text/javascript\" src=\"public/pages/historial/js/historial.js\"></script>\n  <script type=\"text/javascript\" src=\"public/pages/home/js/home_dnd.js\" ></script>\n  <script type=\"text/javascript\" src=\"public/pages/campos/js/campos.js\" ></script>\n  <script type=\"text/javascript\" src=\"public/pages/prestacion/js/prestacion_grid.js\" ></script>\n  <script type=\"text/javascript\" src=\"public/pages/seccion/js/seccion.js\" ></script>\n  <script type=\"text/javascript\" src=\"public/pages/especialidad/js/especialidad.js\"></script>\n  <script type=\"text/javascript\" src=\"public/pages/home/js/centro_resultado.js\" ></script>\n  \n  ".toCharArray();
    __oracle_jsp_text[1] = 
    "\n  ".toCharArray();
    __oracle_jsp_text[2] = 
    "\n  ".toCharArray();
    __oracle_jsp_text[3] = 
    "\n  ".toCharArray();
    __oracle_jsp_text[4] = 
    "\n  ".toCharArray();
    __oracle_jsp_text[5] = 
    "\n  \n  <script type=\"text/javascript\" src=\"public/pages/home/js/crGrid.js\" ></script>\n  \n  ".toCharArray();
    __oracle_jsp_text[6] = 
    "\n  <div id=\"div_centro_resultados\" style=\"display:none;\"></div>     \n  <div id=\"fileHistory\" style=\"margin:15px;width:220px;\"></div>\n  \n  <div id=\"finalizados\" style=\"margin:15px;width:220px;display:none;\"></div>     \n  \n  <div id=\"fileHistoryBar\" style=\"height:180px; display:none; margin-top: 2px;\">\n      <table width=\"100%\" border=\"1\" cellspacing=\"0\" cellpadding=\"2\" style=\"text-align:center;font-size:12px;\">\n          <tr>\n              <td colspan=\"2\"><div id=\"fileText\"></div></td>\n          </tr>\n          \n          <tr>\n              <td>Estado:</td>\n              <td><div id=\"statusText\"></div></div></td>\n          </tr>\n          <tr>\n            <td colspan=\"2\">\n                <div style=\"float:left;\"><div id=\"verArchivo\"><a href=\"javascript:void(0);\" ></a></div></div>\n                <div style=\"float:right;\"><div id=\"delArchivo\"><a href=\"javascript:void(0);\"></a></div></div>\n            </td>\n          </tr>\n      </table>\n  </div>\n    \n  ".toCharArray();
    __oracle_jsp_text[7] = 
    "\n  <div style=\"display:none;\">\n      <iframe  id=\"iframeDownload\"></iframe>\n  </div>".toCharArray();
    }
    catch (Throwable th) {
      System.err.println(th);
    }
}
}
