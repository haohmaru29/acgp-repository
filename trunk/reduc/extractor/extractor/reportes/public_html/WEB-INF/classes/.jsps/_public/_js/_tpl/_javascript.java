  /*@lineinfo:filename=/public/js/tpl/javascript.jsp*/
  /*@lineinfo:generated-code*/
package _public._js._tpl;

import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;


public class _javascript extends oracle.jsp.runtime.HttpJsp {

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
    _javascript page = this;
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
    "<script type=\"text/javascript\" src=\"library/system/namespace.js\"></script>\n<script type=\"text/javascript\" src=\"library/system/system.js\"></script>\n\n<script type=\"text/javascript\" src=\"library/extjs/4.0.2a/adapter/ext/ext-all-debug.js\" ></script>\n\n<!-- ExtJS Lenguaje -->\n<script type=\"text/javascript\" src=\"library/extjs/4.0.2a/locale/ext-lang-es.js\" ></script>\n\n<!-- ExtJS User Extensions -->\n<script type=\"text/javascript\" src=\"public/js/pool/Ext.pool.js\"></script>\n\n<!-- ExtJS System depends -->\n<script type=\"text/javascript\" src=\"library/system/ext.system.js\"></script>\n\n<!-- Namespace Define -->\n<script type=\"text/javascript\" src=\"public/js/environment.js\"></script>\n\n<script type=\"text/javascript\">\n    Ext.onReady(function(){\n        Ext.QuickTips.init();\n    });\n</script>\n\n<script type=\"text/javascript\">\nExt.onReady(function(){\n      GlobalMask = new Ext.LoadMask(Ext.getBody(), {\n          msg:\"<b>Cargando Aplicaci&oacute;n</b>... Espere por favor.\"\n      });\n     GlobalMask.show()\n     \n});\n</script>".toCharArray();
    }
    catch (Throwable th) {
      System.err.println(th);
    }
}
}
