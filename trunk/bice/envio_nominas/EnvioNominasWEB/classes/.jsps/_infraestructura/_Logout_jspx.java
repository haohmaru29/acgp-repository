package _infraestructura;

import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import oracle.jsp.el.*;
import javax.servlet.jsp.el.*;


public class _Logout_jspx extends com.orionserver.http.OrionHttpJspPage {


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
    _Logout_jspx page = this;
    ServletConfig config = pageContext.getServletConfig();
    javax.servlet.jsp.el.VariableResolver __ojsp_varRes = (VariableResolver)new OracleVariableResolverImpl(pageContext);

    try {


      out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\" >" );
      {
        com.sun.faces.taglib.jsf_core.ViewTag __jsp_taghandler_1=(com.sun.faces.taglib.jsf_core.ViewTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.jsf_core.ViewTag.class,"com.sun.faces.taglib.jsf_core.ViewTag");
        __jsp_taghandler_1.setParent(null);
        __jsp_tag_starteval=__jsp_taghandler_1.doStartTag();
        if (OracleJspRuntime.checkStartBodyTagEval(__jsp_tag_starteval))
        {
          out=OracleJspRuntime.pushBodyIfNeeded(pageContext,__jsp_taghandler_1,__jsp_tag_starteval,out);
          do {
            {
              oracle.adfinternal.view.faces.taglib.html.HtmlHtmlTag __jsp_taghandler_2=(oracle.adfinternal.view.faces.taglib.html.HtmlHtmlTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlHtmlTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlHtmlTag");
              __jsp_taghandler_2.setParent(__jsp_taghandler_1);
              __jsp_tag_starteval=__jsp_taghandler_2.doStartTag();
              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
              {
                {
                  oracle.adfinternal.view.faces.taglib.html.HtmlHeadTag __jsp_taghandler_3=(oracle.adfinternal.view.faces.taglib.html.HtmlHeadTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlHeadTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlHeadTag title");
                  __jsp_taghandler_3.setParent(__jsp_taghandler_2);
                  __jsp_taghandler_3.setTitle("Logout");
                  __jsp_tag_starteval=__jsp_taghandler_3.doStartTag();
                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                  {
                    out.write( "<meta"+ " http-equiv=\"" + "Content-Type"+ "\"" + " content=\"" + "text/html; charset=windows-1252"+ "\"" +"/>");
                  }
                  if (__jsp_taghandler_3.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                    return;
                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_3,3);
                }
                {
                  oracle.adfinternal.view.faces.taglib.html.HtmlBodyTag __jsp_taghandler_4=(oracle.adfinternal.view.faces.taglib.html.HtmlBodyTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlBodyTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlBodyTag");
                  __jsp_taghandler_4.setParent(__jsp_taghandler_2);
                  __jsp_tag_starteval=__jsp_taghandler_4.doStartTag();
                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                  {
                    {
                      oracle.adfinternal.view.faces.taglib.core.CoreFormTag __jsp_taghandler_5=(oracle.adfinternal.view.faces.taglib.core.CoreFormTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.CoreFormTag.class,"oracle.adfinternal.view.faces.taglib.core.CoreFormTag");
                      __jsp_taghandler_5.setParent(__jsp_taghandler_4);
                      __jsp_tag_starteval=__jsp_taghandler_5.doStartTag();
                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                      {
                        {
                          oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag __jsp_taghandler_6=(oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag.class,"oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag width background text");
                          __jsp_taghandler_6.setParent(__jsp_taghandler_5);
                          __jsp_taghandler_6.setWidth("770px");
                          __jsp_taghandler_6.setBackground("medium");
                          __jsp_taghandler_6.setText("Logout");
                          __jsp_tag_starteval=__jsp_taghandler_6.doStartTag();
                          if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                          {
                            {
                              oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag __jsp_taghandler_7=(oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag text actionListener");
                              __jsp_taghandler_7.setParent(__jsp_taghandler_6);
                              __jsp_taghandler_7.setText("Logout");
                              __jsp_taghandler_7.setActionListener("#{backing_Logout.logoutActionListener}");
                              __jsp_tag_starteval=__jsp_taghandler_7.doStartTag();
                              if (__jsp_taghandler_7.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_7,6);
                            }
                          }
                          if (__jsp_taghandler_6.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                            return;
                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_6,5);
                        }
                      }
                      if (__jsp_taghandler_5.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                        return;
                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_5,4);
                    }
                  }
                  if (__jsp_taghandler_4.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                    return;
                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_4,3);
                }
              }
              if (__jsp_taghandler_2.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                return;
              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_2,2);
            }
          } while (__jsp_taghandler_1.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
          out=OracleJspRuntime.popBodyIfNeeded(pageContext,out);
        }
        if (__jsp_taghandler_1.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_1,1);
      }

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
}
