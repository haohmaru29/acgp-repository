
import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import oracle.jsp.el.*;
import javax.servlet.jsp.el.*;


public class _Bienvenida_jspx extends com.orionserver.http.OrionHttpJspPage {


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
    _Bienvenida_jspx page = this;
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
                  __jsp_taghandler_3.setTitle("bienvenida");
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
                      com.sun.faces.taglib.jsf_core.LoadBundleTag __jsp_taghandler_5=(com.sun.faces.taglib.jsf_core.LoadBundleTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.jsf_core.LoadBundleTag.class,"com.sun.faces.taglib.jsf_core.LoadBundleTag basename var");
                      __jsp_taghandler_5.setParent(__jsp_taghandler_4);
                      __jsp_taghandler_5.setBasename("cl.bicevida.core.view.resources.messages.general");
                      __jsp_taghandler_5.setVar("msgs");
                      __jsp_tag_starteval=__jsp_taghandler_5.doStartTag();
                      if (__jsp_taghandler_5.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                        return;
                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_5,4);
                    }
                    {
                      oracle.adfinternal.view.faces.taglib.core.CoreFormTag __jsp_taghandler_6=(oracle.adfinternal.view.faces.taglib.core.CoreFormTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.CoreFormTag.class,"oracle.adfinternal.view.faces.taglib.core.CoreFormTag");
                      __jsp_taghandler_6.setParent(__jsp_taghandler_4);
                      __jsp_tag_starteval=__jsp_taghandler_6.doStartTag();
                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                      {
                        {
                          String __url=OracleJspRuntime.toStr("/CoreMenu.jspx");
                          // Include 
                          pageContext.include( __url,false);
                          if (pageContext.getAttribute(OracleJspRuntime.JSP_REQUEST_REDIRECTED, PageContext.REQUEST_SCOPE) != null) return;
                        }

                        {
                          oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag __jsp_taghandler_7=(oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag.class,"oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag width background text");
                          __jsp_taghandler_7.setParent(__jsp_taghandler_6);
                          __jsp_taghandler_7.setWidth("770px");
                          __jsp_taghandler_7.setBackground("medium");
                          __jsp_taghandler_7.setText("#{msgs['bienvenida.titulo']}");
                          __jsp_tag_starteval=__jsp_taghandler_7.doStartTag();
                          if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                          {
                            {
                              oracle.adfinternal.view.faces.taglib.core.layout.CorePanelFormTag __jsp_taghandler_8=(oracle.adfinternal.view.faces.taglib.core.layout.CorePanelFormTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.layout.CorePanelFormTag.class,"oracle.adfinternal.view.faces.taglib.core.layout.CorePanelFormTag");
                              __jsp_taghandler_8.setParent(__jsp_taghandler_7);
                              __jsp_tag_starteval=__jsp_taghandler_8.doStartTag();
                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                              {
                                {
                                  javax.faces.webapp.FacetTag __jsp_taghandler_9=(javax.faces.webapp.FacetTag)OracleJspRuntime.getTagHandler(pageContext,javax.faces.webapp.FacetTag.class,"javax.faces.webapp.FacetTag name");
                                  __jsp_taghandler_9.setParent(__jsp_taghandler_8);
                                  __jsp_taghandler_9.setName("footer");
                                  __jsp_tag_starteval=__jsp_taghandler_9.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    do {
                                      {
                                        com.sun.faces.taglib.html_basic.PanelGroupTag __jsp_taghandler_10=(com.sun.faces.taglib.html_basic.PanelGroupTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.html_basic.PanelGroupTag.class,"com.sun.faces.taglib.html_basic.PanelGroupTag");
                                        __jsp_taghandler_10.setParent(__jsp_taghandler_9);
                                        __jsp_tag_starteval=__jsp_taghandler_10.doStartTag();
                                        if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                        {
                                          {
                                            oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag __jsp_taghandler_11=(oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag text blocking action");
                                            __jsp_taghandler_11.setParent(__jsp_taghandler_10);
                                            __jsp_taghandler_11.setText("#{msgs['generales.cmdSiguinte']}");
                                            __jsp_taghandler_11.setBlocking("true");
                                            __jsp_taghandler_11.setAction("firstPage");
                                            __jsp_tag_starteval=__jsp_taghandler_11.doStartTag();
                                            if (__jsp_taghandler_11.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                              return;
                                            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_11,9);
                                          }
                                          {
                                            oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag __jsp_taghandler_12=(oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag text actionListener");
                                            __jsp_taghandler_12.setParent(__jsp_taghandler_10);
                                            __jsp_taghandler_12.setText("Generar Excepción");
                                            __jsp_taghandler_12.setActionListener("#{backing_Bienvenida.onClickGenerarExcepcion}");
                                            __jsp_tag_starteval=__jsp_taghandler_12.doStartTag();
                                            if (__jsp_taghandler_12.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                              return;
                                            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_12,9);
                                          }
                                        }
                                        if (__jsp_taghandler_10.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                          return;
                                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_10,8);
                                      }
                                    } while (__jsp_taghandler_9.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
                                  }
                                  if (__jsp_taghandler_9.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_9,7);
                                }
                                {
                                  com.sun.faces.taglib.html_basic.OutputFormatTag __jsp_taghandler_13=(com.sun.faces.taglib.html_basic.OutputFormatTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.html_basic.OutputFormatTag.class,"com.sun.faces.taglib.html_basic.OutputFormatTag value styleClass");
                                  __jsp_taghandler_13.setParent(__jsp_taghandler_8);
                                  __jsp_taghandler_13.setValue("#{msgs['bienvenida.texto']}");
                                  __jsp_taghandler_13.setStyleClass("AFLabelText");
                                  __jsp_tag_starteval=__jsp_taghandler_13.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      com.sun.faces.taglib.jsf_core.ParameterTag __jsp_taghandler_14=(com.sun.faces.taglib.jsf_core.ParameterTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.jsf_core.ParameterTag.class,"com.sun.faces.taglib.jsf_core.ParameterTag name value");
                                      __jsp_taghandler_14.setParent(__jsp_taghandler_13);
                                      __jsp_taghandler_14.setName("buttonName");
                                      __jsp_taghandler_14.setValue("#{msgs['generales.cmdSiguinte']}");
                                      __jsp_tag_starteval=__jsp_taghandler_14.doStartTag();
                                      if (__jsp_taghandler_14.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_14,8);
                                    }
                                  }
                                  if (__jsp_taghandler_13.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_13,7);
                                }
                              }
                              if (__jsp_taghandler_8.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_8,6);
                            }
                          }
                          if (__jsp_taghandler_7.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                            return;
                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_7,5);
                        }
                        {
                          com.sun.faces.taglib.jsf_core.VerbatimTag __jsp_taghandler_15=(com.sun.faces.taglib.jsf_core.VerbatimTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.jsf_core.VerbatimTag.class,"com.sun.faces.taglib.jsf_core.VerbatimTag");
                          __jsp_taghandler_15.setParent(__jsp_taghandler_6);
                          __jsp_tag_starteval=__jsp_taghandler_15.doStartTag();
                          if (OracleJspRuntime.checkStartBodyTagEval(__jsp_tag_starteval))
                          {
                            out=OracleJspRuntime.pushBodyIfNeeded(pageContext,__jsp_taghandler_15,__jsp_tag_starteval,out);
                            do {
                              out.write( "<p>");
                              out.write(__oracle_jsp_text[0]);
                              out.write( "</p>");
                            } while (__jsp_taghandler_15.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
                            out=OracleJspRuntime.popBodyIfNeeded(pageContext,out);
                          }
                          if (__jsp_taghandler_15.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                            return;
                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_15,5);
                        }
                      }
                      if (__jsp_taghandler_6.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                        return;
                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_6,4);
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
  private static final char __oracle_jsp_text[][]=new char[1][];
  static {
    try {
    __oracle_jsp_text[0] = 
    "\n               \n            ".toCharArray();
    }
    catch (Throwable th) {
      System.err.println(th);
    }
}
}
