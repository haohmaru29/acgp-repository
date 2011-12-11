package _infraestructura;

import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import oracle.jsp.el.*;
import javax.servlet.jsp.el.*;


public class _Error_jspx extends com.orionserver.http.OrionHttpJspPage {


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
    _Error_jspx page = this;
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
                  __jsp_taghandler_3.setTitle("index");
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
                          oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag __jsp_taghandler_7=(oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag.class,"oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag width background text");
                          __jsp_taghandler_7.setParent(__jsp_taghandler_6);
                          __jsp_taghandler_7.setWidth("770px");
                          __jsp_taghandler_7.setBackground("medium");
                          __jsp_taghandler_7.setText("#{msgs['bicevida.error.titulo']}");
                          __jsp_tag_starteval=__jsp_taghandler_7.doStartTag();
                          if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                          {
                            {
                              oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag __jsp_taghandler_8=(oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag.class,"oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag halign");
                              __jsp_taghandler_8.setParent(__jsp_taghandler_7);
                              __jsp_taghandler_8.setHalign("center");
                              __jsp_tag_starteval=__jsp_taghandler_8.doStartTag();
                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                              {
                                {
                                  oracle.adfinternal.view.faces.taglib.core.output.CoreObjectImageTag __jsp_taghandler_9=(oracle.adfinternal.view.faces.taglib.core.output.CoreObjectImageTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreObjectImageTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreObjectImageTag source");
                                  __jsp_taghandler_9.setParent(__jsp_taghandler_8);
                                  __jsp_taghandler_9.setSource("/skins/informatica/CoreSkin/skin_images/exclamation.gif");
                                  __jsp_tag_starteval=__jsp_taghandler_9.doStartTag();
                                  if (__jsp_taghandler_9.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_9,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag __jsp_taghandler_10=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag value");
                                  __jsp_taghandler_10.setParent(__jsp_taghandler_8);
                                  __jsp_taghandler_10.setValue("#{msgs['bicevida.error.text']}");
                                  __jsp_tag_starteval=__jsp_taghandler_10.doStartTag();
                                  if (__jsp_taghandler_10.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_10,7);
                                }
                              }
                              if (__jsp_taghandler_8.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_8,6);
                            }
                            {
                              oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag __jsp_taghandler_11=(oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag width height");
                              __jsp_taghandler_11.setParent(__jsp_taghandler_7);
                              __jsp_taghandler_11.setWidth("10");
                              __jsp_taghandler_11.setHeight("10");
                              __jsp_tag_starteval=__jsp_taghandler_11.doStartTag();
                              if (__jsp_taghandler_11.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_11,6);
                            }
                            {
                              oracle.adfinternal.view.faces.taglib.core.layout.CoreShowOneTabTag __jsp_taghandler_12=(oracle.adfinternal.view.faces.taglib.core.layout.CoreShowOneTabTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.layout.CoreShowOneTabTag.class,"oracle.adfinternal.view.faces.taglib.core.layout.CoreShowOneTabTag position");
                              __jsp_taghandler_12.setParent(__jsp_taghandler_7);
                              __jsp_taghandler_12.setPosition("above");
                              __jsp_tag_starteval=__jsp_taghandler_12.doStartTag();
                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                              {
                                {
                                  oracle.adfinternal.view.faces.taglib.core.layout.CoreShowDetailItemTag __jsp_taghandler_13=(oracle.adfinternal.view.faces.taglib.core.layout.CoreShowDetailItemTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.layout.CoreShowDetailItemTag.class,"oracle.adfinternal.view.faces.taglib.core.layout.CoreShowDetailItemTag text disclosed");
                                  __jsp_taghandler_13.setParent(__jsp_taghandler_12);
                                  __jsp_taghandler_13.setText("#{msgs['bicevida.error.tab.motivo']}");
                                  __jsp_taghandler_13.setDisclosed("true");
                                  __jsp_tag_starteval=__jsp_taghandler_13.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag __jsp_taghandler_14=(oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag.class,"oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag halign");
                                      __jsp_taghandler_14.setParent(__jsp_taghandler_13);
                                      __jsp_taghandler_14.setHalign("center");
                                      __jsp_tag_starteval=__jsp_taghandler_14.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag __jsp_taghandler_15=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag value");
                                          __jsp_taghandler_15.setParent(__jsp_taghandler_14);
                                          __jsp_taghandler_15.setValue("#{MbError.mensajeAmigable}");
                                          __jsp_tag_starteval=__jsp_taghandler_15.doStartTag();
                                          if (__jsp_taghandler_15.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_15,9);
                                        }
                                      }
                                      if (__jsp_taghandler_14.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_14,8);
                                    }
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag __jsp_taghandler_16=(oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag.class,"oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag halign");
                                      __jsp_taghandler_16.setParent(__jsp_taghandler_13);
                                      __jsp_taghandler_16.setHalign("center");
                                      __jsp_tag_starteval=__jsp_taghandler_16.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag __jsp_taghandler_17=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag value");
                                          __jsp_taghandler_17.setParent(__jsp_taghandler_16);
                                          __jsp_taghandler_17.setValue("#{MbError.exceptionMessage}");
                                          __jsp_tag_starteval=__jsp_taghandler_17.doStartTag();
                                          if (__jsp_taghandler_17.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_17,9);
                                        }
                                      }
                                      if (__jsp_taghandler_16.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_16,8);
                                    }
                                  }
                                  if (__jsp_taghandler_13.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_13,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.layout.CoreShowDetailItemTag __jsp_taghandler_18=(oracle.adfinternal.view.faces.taglib.core.layout.CoreShowDetailItemTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.layout.CoreShowDetailItemTag.class,"oracle.adfinternal.view.faces.taglib.core.layout.CoreShowDetailItemTag text disclosed rendered");
                                  __jsp_taghandler_18.setParent(__jsp_taghandler_12);
                                  __jsp_taghandler_18.setText("#{msgs['bicevida.error.tab.detalles']}");
                                  __jsp_taghandler_18.setDisclosed("false");
                                  __jsp_taghandler_18.setRendered("#{MbError.showDetail}");
                                  __jsp_tag_starteval=__jsp_taghandler_18.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag __jsp_taghandler_19=(oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag.class,"oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag halign inlineStyle");
                                      __jsp_taghandler_19.setParent(__jsp_taghandler_18);
                                      __jsp_taghandler_19.setHalign("center");
                                      __jsp_taghandler_19.setInlineStyle("background-color:rgb(200,200,200);");
                                      __jsp_tag_starteval=__jsp_taghandler_19.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag __jsp_taghandler_20=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag value rendered");
                                          __jsp_taghandler_20.setParent(__jsp_taghandler_19);
                                          __jsp_taghandler_20.setValue("#{MbError.stackTrace}");
                                          __jsp_taghandler_20.setRendered("false");
                                          __jsp_tag_starteval=__jsp_taghandler_20.doStartTag();
                                          if (__jsp_taghandler_20.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_20,9);
                                        }
                                        {
                                          oracle.adfinternal.view.faces.taglib.core.output.CoreOutputFormattedTag __jsp_taghandler_21=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputFormattedTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputFormattedTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputFormattedTag styleClass value");
                                          __jsp_taghandler_21.setParent(__jsp_taghandler_19);
                                          __jsp_taghandler_21.setStyleClass("AFErrorText");
                                          __jsp_taghandler_21.setValue("#{MbError.stackTrace}");
                                          __jsp_tag_starteval=__jsp_taghandler_21.doStartTag();
                                          if (__jsp_taghandler_21.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_21,9);
                                        }
                                      }
                                      if (__jsp_taghandler_19.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_19,8);
                                    }
                                  }
                                  if (__jsp_taghandler_18.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_18,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.layout.CoreShowDetailItemTag __jsp_taghandler_22=(oracle.adfinternal.view.faces.taglib.core.layout.CoreShowDetailItemTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.layout.CoreShowDetailItemTag.class,"oracle.adfinternal.view.faces.taglib.core.layout.CoreShowDetailItemTag text");
                                  __jsp_taghandler_22.setParent(__jsp_taghandler_12);
                                  __jsp_taghandler_22.setText("#{msgs['bicevida.error.tab.quehacer']}");
                                  __jsp_tag_starteval=__jsp_taghandler_22.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag __jsp_taghandler_23=(oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag.class,"oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag halign");
                                      __jsp_taghandler_23.setParent(__jsp_taghandler_22);
                                      __jsp_taghandler_23.setHalign("center");
                                      __jsp_tag_starteval=__jsp_taghandler_23.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.core.output.CoreOutputFormattedTag __jsp_taghandler_24=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputFormattedTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputFormattedTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputFormattedTag styleUsage styleClass value");
                                          __jsp_taghandler_24.setParent(__jsp_taghandler_23);
                                          __jsp_taghandler_24.setStyleUsage("instruction");
                                          __jsp_taghandler_24.setStyleClass("AFLabelText");
                                          __jsp_taghandler_24.setValue("#{msgs['bicevida.error.text.quehacer']}");
                                          __jsp_tag_starteval=__jsp_taghandler_24.doStartTag();
                                          if (__jsp_taghandler_24.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_24,9);
                                        }
                                      }
                                      if (__jsp_taghandler_23.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_23,8);
                                    }
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag __jsp_taghandler_25=(oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag width height");
                                      __jsp_taghandler_25.setParent(__jsp_taghandler_22);
                                      __jsp_taghandler_25.setWidth("10");
                                      __jsp_taghandler_25.setHeight("10");
                                      __jsp_tag_starteval=__jsp_taghandler_25.doStartTag();
                                      if (__jsp_taghandler_25.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_25,8);
                                    }
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag __jsp_taghandler_26=(oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag.class,"oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag halign rendered");
                                      __jsp_taghandler_26.setParent(__jsp_taghandler_22);
                                      __jsp_taghandler_26.setHalign("center");
                                      __jsp_taghandler_26.setRendered("#{MbError.hasSend}");
                                      __jsp_tag_starteval=__jsp_taghandler_26.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.core.output.CoreOutputFormattedTag __jsp_taghandler_27=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputFormattedTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputFormattedTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputFormattedTag styleUsage styleClass value");
                                          __jsp_taghandler_27.setParent(__jsp_taghandler_26);
                                          __jsp_taghandler_27.setStyleUsage("instruction");
                                          __jsp_taghandler_27.setStyleClass("AFLabelText");
                                          __jsp_taghandler_27.setValue("#{msgs['bicevida.error.text.errorenviado']}");
                                          __jsp_tag_starteval=__jsp_taghandler_27.doStartTag();
                                          if (__jsp_taghandler_27.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_27,9);
                                        }
                                      }
                                      if (__jsp_taghandler_26.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_26,8);
                                    }
                                  }
                                  if (__jsp_taghandler_22.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_22,7);
                                }
                              }
                              if (__jsp_taghandler_12.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_12,6);
                            }
                            {
                              oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSeparatorTag __jsp_taghandler_28=(oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSeparatorTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSeparatorTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSeparatorTag");
                              __jsp_taghandler_28.setParent(__jsp_taghandler_7);
                              __jsp_tag_starteval=__jsp_taghandler_28.doStartTag();
                              if (__jsp_taghandler_28.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_28,6);
                            }
                            {
                              oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag __jsp_taghandler_29=(oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag.class,"oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag halign");
                              __jsp_taghandler_29.setParent(__jsp_taghandler_7);
                              __jsp_taghandler_29.setHalign("center");
                              __jsp_tag_starteval=__jsp_taghandler_29.doStartTag();
                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                              {
                                {
                                  oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag __jsp_taghandler_30=(oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag text blocking action");
                                  __jsp_taghandler_30.setParent(__jsp_taghandler_29);
                                  __jsp_taghandler_30.setText("#{msgs['bicevida.error.cmd.volver']}");
                                  __jsp_taghandler_30.setBlocking("true");
                                  __jsp_taghandler_30.setAction("#{MbError.getOutcome}");
                                  __jsp_tag_starteval=__jsp_taghandler_30.doStartTag();
                                  if (__jsp_taghandler_30.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_30,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag __jsp_taghandler_31=(oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag width height");
                                  __jsp_taghandler_31.setParent(__jsp_taghandler_29);
                                  __jsp_taghandler_31.setWidth("10");
                                  __jsp_taghandler_31.setHeight("10");
                                  __jsp_tag_starteval=__jsp_taghandler_31.doStartTag();
                                  if (__jsp_taghandler_31.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_31,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag __jsp_taghandler_32=(oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag text blocking action");
                                  __jsp_taghandler_32.setParent(__jsp_taghandler_29);
                                  __jsp_taghandler_32.setText("#{msgs['bicevida.error.cmd.inicio']}");
                                  __jsp_taghandler_32.setBlocking("true");
                                  __jsp_taghandler_32.setAction("#{MbError.getInicio}");
                                  __jsp_tag_starteval=__jsp_taghandler_32.doStartTag();
                                  if (__jsp_taghandler_32.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_32,7);
                                }
                              }
                              if (__jsp_taghandler_29.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_29,6);
                            }
                          }
                          if (__jsp_taghandler_7.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                            return;
                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_7,5);
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
}
