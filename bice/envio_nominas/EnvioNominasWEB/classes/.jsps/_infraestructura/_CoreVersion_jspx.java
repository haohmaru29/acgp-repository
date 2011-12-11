package _infraestructura;

import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import oracle.jsp.el.*;
import javax.servlet.jsp.el.*;


public class _CoreVersion_jspx extends com.orionserver.http.OrionHttpJspPage {


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
    _CoreVersion_jspx page = this;
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
                  __jsp_taghandler_3.setTitle("CoreVersion");
                  __jsp_tag_starteval=__jsp_taghandler_3.doStartTag();
                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                  {
                    out.write( "<meta"+ " http-equiv=\"" + "Content-Type"+ "\"" + " content=\"" + "text/html; charset=windows-1252"+ "\"" +"/>");
                    out.write( "<link"+ " type=\"" + "text/css"+ "\"" + " rel=\"" + "stylesheet"+ "\"" + " href=\"" + "../css/myStyle.css"+ "\"" +"/>");
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
                      String __url=OracleJspRuntime.toStr("/CoreMenu.jspx");
                      // Include 
                      pageContext.include( __url,false);
                      if (pageContext.getAttribute(OracleJspRuntime.JSP_REQUEST_REDIRECTED, PageContext.REQUEST_SCOPE) != null) return;
                    }

                    {
                      oracle.adfinternal.view.faces.taglib.core.CoreFormTag __jsp_taghandler_5=(oracle.adfinternal.view.faces.taglib.core.CoreFormTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.CoreFormTag.class,"oracle.adfinternal.view.faces.taglib.core.CoreFormTag");
                      __jsp_taghandler_5.setParent(__jsp_taghandler_4);
                      __jsp_tag_starteval=__jsp_taghandler_5.doStartTag();
                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                      {
                        {
                          oracle.adfinternal.view.faces.taglib.html.HtmlTableLayoutTag __jsp_taghandler_6=(oracle.adfinternal.view.faces.taglib.html.HtmlTableLayoutTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlTableLayoutTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlTableLayoutTag width");
                          __jsp_taghandler_6.setParent(__jsp_taghandler_5);
                          __jsp_taghandler_6.setWidth("770px");
                          __jsp_tag_starteval=__jsp_taghandler_6.doStartTag();
                          if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                          {
                            {
                              oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag __jsp_taghandler_7=(oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag");
                              __jsp_taghandler_7.setParent(__jsp_taghandler_6);
                              __jsp_tag_starteval=__jsp_taghandler_7.doStartTag();
                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                              {
                                {
                                  oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_8=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag halign");
                                  __jsp_taghandler_8.setParent(__jsp_taghandler_7);
                                  __jsp_taghandler_8.setHalign("center");
                                  __jsp_tag_starteval=__jsp_taghandler_8.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.html.HtmlTableLayoutTag __jsp_taghandler_9=(oracle.adfinternal.view.faces.taglib.html.HtmlTableLayoutTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlTableLayoutTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlTableLayoutTag");
                                      __jsp_taghandler_9.setParent(__jsp_taghandler_8);
                                      __jsp_tag_starteval=__jsp_taghandler_9.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag __jsp_taghandler_10=(oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag");
                                          __jsp_taghandler_10.setParent(__jsp_taghandler_9);
                                          __jsp_tag_starteval=__jsp_taghandler_10.doStartTag();
                                          if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                          {
                                            {
                                              oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_11=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag halign");
                                              __jsp_taghandler_11.setParent(__jsp_taghandler_10);
                                              __jsp_taghandler_11.setHalign("left");
                                              __jsp_tag_starteval=__jsp_taghandler_11.doStartTag();
                                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                              {
                                                {
                                                  oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag __jsp_taghandler_12=(oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag.class,"oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag id width background text icon");
                                                  __jsp_taghandler_12.setParent(__jsp_taghandler_11);
                                                  __jsp_taghandler_12.setId("PanelBoxVersion");
                                                  __jsp_taghandler_12.setWidth("500px");
                                                  __jsp_taghandler_12.setBackground("dark");
                                                  __jsp_taghandler_12.setText(" Versión de Core Web App");
                                                  __jsp_taghandler_12.setIcon("../skins/informatica/CoreSkin/skin_images/boton_iso.gif");
                                                  __jsp_tag_starteval=__jsp_taghandler_12.doStartTag();
                                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                                  {
                                                    {
                                                      oracle.adfinternal.view.faces.taglib.html.HtmlTableLayoutTag __jsp_taghandler_13=(oracle.adfinternal.view.faces.taglib.html.HtmlTableLayoutTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlTableLayoutTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlTableLayoutTag width");
                                                      __jsp_taghandler_13.setParent(__jsp_taghandler_12);
                                                      __jsp_taghandler_13.setWidth("100%");
                                                      __jsp_tag_starteval=__jsp_taghandler_13.doStartTag();
                                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                                      {
                                                        {
                                                          oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag __jsp_taghandler_14=(oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag");
                                                          __jsp_taghandler_14.setParent(__jsp_taghandler_13);
                                                          __jsp_tag_starteval=__jsp_taghandler_14.doStartTag();
                                                          if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                                          {
                                                            {
                                                              oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_15=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag width");
                                                              __jsp_taghandler_15.setParent(__jsp_taghandler_14);
                                                              __jsp_taghandler_15.setWidth("40%");
                                                              __jsp_tag_starteval=__jsp_taghandler_15.doStartTag();
                                                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                                              {
                                                                {
                                                                  oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag __jsp_taghandler_16=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag value");
                                                                  __jsp_taghandler_16.setParent(__jsp_taghandler_15);
                                                                  __jsp_taghandler_16.setValue("CoreWebApp");
                                                                  __jsp_tag_starteval=__jsp_taghandler_16.doStartTag();
                                                                  if (__jsp_taghandler_16.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                    return;
                                                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_16,15);
                                                                }
                                                              }
                                                              if (__jsp_taghandler_15.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                return;
                                                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_15,14);
                                                            }
                                                            {
                                                              oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_17=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag");
                                                              __jsp_taghandler_17.setParent(__jsp_taghandler_14);
                                                              __jsp_tag_starteval=__jsp_taghandler_17.doStartTag();
                                                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                                              {
                                                                {
                                                                  oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_18=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag styleClass value");
                                                                  __jsp_taghandler_18.setParent(__jsp_taghandler_17);
                                                                  __jsp_taghandler_18.setStyleClass("AFFieldText");
                                                                  __jsp_taghandler_18.setValue("0.2");
                                                                  __jsp_tag_starteval=__jsp_taghandler_18.doStartTag();
                                                                  if (__jsp_taghandler_18.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                    return;
                                                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_18,15);
                                                                }
                                                              }
                                                              if (__jsp_taghandler_17.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                return;
                                                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_17,14);
                                                            }
                                                          }
                                                          if (__jsp_taghandler_14.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                            return;
                                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_14,13);
                                                        }
                                                        {
                                                          oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag __jsp_taghandler_19=(oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag");
                                                          __jsp_taghandler_19.setParent(__jsp_taghandler_13);
                                                          __jsp_tag_starteval=__jsp_taghandler_19.doStartTag();
                                                          if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                                          {
                                                            {
                                                              oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_20=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag width");
                                                              __jsp_taghandler_20.setParent(__jsp_taghandler_19);
                                                              __jsp_taghandler_20.setWidth("40%");
                                                              __jsp_tag_starteval=__jsp_taghandler_20.doStartTag();
                                                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                                              {
                                                                {
                                                                  oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag __jsp_taghandler_21=(oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag width height");
                                                                  __jsp_taghandler_21.setParent(__jsp_taghandler_20);
                                                                  __jsp_taghandler_21.setWidth("10");
                                                                  __jsp_taghandler_21.setHeight("10");
                                                                  __jsp_tag_starteval=__jsp_taghandler_21.doStartTag();
                                                                  if (__jsp_taghandler_21.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                    return;
                                                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_21,15);
                                                                }
                                                                {
                                                                  oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag __jsp_taghandler_22=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag value");
                                                                  __jsp_taghandler_22.setParent(__jsp_taghandler_20);
                                                                  __jsp_taghandler_22.setValue("ViewController");
                                                                  __jsp_tag_starteval=__jsp_taghandler_22.doStartTag();
                                                                  if (__jsp_taghandler_22.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                    return;
                                                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_22,15);
                                                                }
                                                              }
                                                              if (__jsp_taghandler_20.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                return;
                                                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_20,14);
                                                            }
                                                            {
                                                              oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_23=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag");
                                                              __jsp_taghandler_23.setParent(__jsp_taghandler_19);
                                                              __jsp_tag_starteval=__jsp_taghandler_23.doStartTag();
                                                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                                              {
                                                                {
                                                                  oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_24=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag styleClass value");
                                                                  __jsp_taghandler_24.setParent(__jsp_taghandler_23);
                                                                  __jsp_taghandler_24.setStyleClass("AFFieldText");
                                                                  __jsp_taghandler_24.setValue("0.2");
                                                                  __jsp_tag_starteval=__jsp_taghandler_24.doStartTag();
                                                                  if (__jsp_taghandler_24.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                    return;
                                                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_24,15);
                                                                }
                                                              }
                                                              if (__jsp_taghandler_23.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                return;
                                                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_23,14);
                                                            }
                                                          }
                                                          if (__jsp_taghandler_19.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                            return;
                                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_19,13);
                                                        }
                                                        {
                                                          oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag __jsp_taghandler_25=(oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag");
                                                          __jsp_taghandler_25.setParent(__jsp_taghandler_13);
                                                          __jsp_tag_starteval=__jsp_taghandler_25.doStartTag();
                                                          if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                                          {
                                                            {
                                                              oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_26=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag width");
                                                              __jsp_taghandler_26.setParent(__jsp_taghandler_25);
                                                              __jsp_taghandler_26.setWidth("40%");
                                                              __jsp_tag_starteval=__jsp_taghandler_26.doStartTag();
                                                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                                              {
                                                                {
                                                                  oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag __jsp_taghandler_27=(oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag width height");
                                                                  __jsp_taghandler_27.setParent(__jsp_taghandler_26);
                                                                  __jsp_taghandler_27.setWidth("10");
                                                                  __jsp_taghandler_27.setHeight("10");
                                                                  __jsp_tag_starteval=__jsp_taghandler_27.doStartTag();
                                                                  if (__jsp_taghandler_27.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                    return;
                                                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_27,15);
                                                                }
                                                                {
                                                                  oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag __jsp_taghandler_28=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag value");
                                                                  __jsp_taghandler_28.setParent(__jsp_taghandler_26);
                                                                  __jsp_taghandler_28.setValue("Model");
                                                                  __jsp_tag_starteval=__jsp_taghandler_28.doStartTag();
                                                                  if (__jsp_taghandler_28.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                    return;
                                                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_28,15);
                                                                }
                                                              }
                                                              if (__jsp_taghandler_26.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                return;
                                                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_26,14);
                                                            }
                                                            {
                                                              oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_29=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag");
                                                              __jsp_taghandler_29.setParent(__jsp_taghandler_25);
                                                              __jsp_tag_starteval=__jsp_taghandler_29.doStartTag();
                                                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                                              {
                                                                {
                                                                  oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_30=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag styleClass value");
                                                                  __jsp_taghandler_30.setParent(__jsp_taghandler_29);
                                                                  __jsp_taghandler_30.setStyleClass("AFFieldText");
                                                                  __jsp_taghandler_30.setValue("0.2");
                                                                  __jsp_tag_starteval=__jsp_taghandler_30.doStartTag();
                                                                  if (__jsp_taghandler_30.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                    return;
                                                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_30,15);
                                                                }
                                                              }
                                                              if (__jsp_taghandler_29.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                return;
                                                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_29,14);
                                                            }
                                                          }
                                                          if (__jsp_taghandler_25.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                            return;
                                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_25,13);
                                                        }
                                                        {
                                                          oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag __jsp_taghandler_31=(oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag");
                                                          __jsp_taghandler_31.setParent(__jsp_taghandler_13);
                                                          __jsp_tag_starteval=__jsp_taghandler_31.doStartTag();
                                                          if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                                          {
                                                            {
                                                              oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_32=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag width");
                                                              __jsp_taghandler_32.setParent(__jsp_taghandler_31);
                                                              __jsp_taghandler_32.setWidth("40%");
                                                              __jsp_tag_starteval=__jsp_taghandler_32.doStartTag();
                                                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                                              {
                                                                {
                                                                  oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag __jsp_taghandler_33=(oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag width height");
                                                                  __jsp_taghandler_33.setParent(__jsp_taghandler_32);
                                                                  __jsp_taghandler_33.setWidth("10");
                                                                  __jsp_taghandler_33.setHeight("10");
                                                                  __jsp_tag_starteval=__jsp_taghandler_33.doStartTag();
                                                                  if (__jsp_taghandler_33.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                    return;
                                                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_33,15);
                                                                }
                                                                {
                                                                  oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag __jsp_taghandler_34=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag value");
                                                                  __jsp_taghandler_34.setParent(__jsp_taghandler_32);
                                                                  __jsp_taghandler_34.setValue("Utils");
                                                                  __jsp_tag_starteval=__jsp_taghandler_34.doStartTag();
                                                                  if (__jsp_taghandler_34.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                    return;
                                                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_34,15);
                                                                }
                                                              }
                                                              if (__jsp_taghandler_32.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                return;
                                                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_32,14);
                                                            }
                                                            {
                                                              oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_35=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag");
                                                              __jsp_taghandler_35.setParent(__jsp_taghandler_31);
                                                              __jsp_tag_starteval=__jsp_taghandler_35.doStartTag();
                                                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                                              {
                                                                {
                                                                  oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_36=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag styleClass value");
                                                                  __jsp_taghandler_36.setParent(__jsp_taghandler_35);
                                                                  __jsp_taghandler_36.setStyleClass("AFFieldText");
                                                                  __jsp_taghandler_36.setValue("0.2");
                                                                  __jsp_tag_starteval=__jsp_taghandler_36.doStartTag();
                                                                  if (__jsp_taghandler_36.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                    return;
                                                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_36,15);
                                                                }
                                                              }
                                                              if (__jsp_taghandler_35.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                return;
                                                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_35,14);
                                                            }
                                                          }
                                                          if (__jsp_taghandler_31.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                            return;
                                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_31,13);
                                                        }
                                                        {
                                                          oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag __jsp_taghandler_37=(oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag");
                                                          __jsp_taghandler_37.setParent(__jsp_taghandler_13);
                                                          __jsp_tag_starteval=__jsp_taghandler_37.doStartTag();
                                                          if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                                          {
                                                            {
                                                              oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_38=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag width");
                                                              __jsp_taghandler_38.setParent(__jsp_taghandler_37);
                                                              __jsp_taghandler_38.setWidth("40%");
                                                              __jsp_tag_starteval=__jsp_taghandler_38.doStartTag();
                                                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                                              {
                                                                {
                                                                  oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag __jsp_taghandler_39=(oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag width height");
                                                                  __jsp_taghandler_39.setParent(__jsp_taghandler_38);
                                                                  __jsp_taghandler_39.setWidth("10");
                                                                  __jsp_taghandler_39.setHeight("10");
                                                                  __jsp_tag_starteval=__jsp_taghandler_39.doStartTag();
                                                                  if (__jsp_taghandler_39.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                    return;
                                                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_39,15);
                                                                }
                                                                {
                                                                  oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag __jsp_taghandler_40=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag value");
                                                                  __jsp_taghandler_40.setParent(__jsp_taghandler_38);
                                                                  __jsp_taghandler_40.setValue("Deployment");
                                                                  __jsp_tag_starteval=__jsp_taghandler_40.doStartTag();
                                                                  if (__jsp_taghandler_40.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                    return;
                                                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_40,15);
                                                                }
                                                              }
                                                              if (__jsp_taghandler_38.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                return;
                                                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_38,14);
                                                            }
                                                            {
                                                              oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_41=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag");
                                                              __jsp_taghandler_41.setParent(__jsp_taghandler_37);
                                                              __jsp_tag_starteval=__jsp_taghandler_41.doStartTag();
                                                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                                              {
                                                                {
                                                                  oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_42=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag styleClass value");
                                                                  __jsp_taghandler_42.setParent(__jsp_taghandler_41);
                                                                  __jsp_taghandler_42.setStyleClass("AFFieldText");
                                                                  __jsp_taghandler_42.setValue("0.1");
                                                                  __jsp_tag_starteval=__jsp_taghandler_42.doStartTag();
                                                                  if (__jsp_taghandler_42.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                    return;
                                                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_42,15);
                                                                }
                                                              }
                                                              if (__jsp_taghandler_41.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                return;
                                                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_41,14);
                                                            }
                                                          }
                                                          if (__jsp_taghandler_37.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                            return;
                                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_37,13);
                                                        }
                                                      }
                                                      if (__jsp_taghandler_13.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                        return;
                                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_13,12);
                                                    }
                                                  }
                                                  if (__jsp_taghandler_12.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                    return;
                                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_12,11);
                                                }
                                              }
                                              if (__jsp_taghandler_11.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                return;
                                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_11,10);
                                            }
                                          }
                                          if (__jsp_taghandler_10.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_10,9);
                                        }
                                      }
                                      if (__jsp_taghandler_9.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_9,8);
                                    }
                                  }
                                  if (__jsp_taghandler_8.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_8,7);
                                }
                              }
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
