package _app._envionominas;

import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import oracle.jsp.el.*;
import javax.servlet.jsp.el.*;


public class _detalleGastosNomina_jspx extends com.orionserver.http.OrionHttpJspPage {


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
    _detalleGastosNomina_jspx page = this;
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
                  __jsp_taghandler_3.setTitle("#{msgs['bicevida.gastos_nominas.detalle.titulo']}");
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
                      com.sun.faces.taglib.jsf_core.VerbatimTag __jsp_taghandler_5=(com.sun.faces.taglib.jsf_core.VerbatimTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.jsf_core.VerbatimTag.class,"com.sun.faces.taglib.jsf_core.VerbatimTag");
                      __jsp_taghandler_5.setParent(__jsp_taghandler_4);
                      __jsp_tag_starteval=__jsp_taghandler_5.doStartTag();
                      if (OracleJspRuntime.checkStartBodyTagEval(__jsp_tag_starteval))
                      {
                        out=OracleJspRuntime.pushBodyIfNeeded(pageContext,__jsp_taghandler_5,__jsp_tag_starteval,out);
                        do {
                          out.write(__oracle_jsp_text[0]);
                        } while (__jsp_taghandler_5.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
                        out=OracleJspRuntime.popBodyIfNeeded(pageContext,out);
                      }
                      if (__jsp_taghandler_5.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                        return;
                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_5,4);
                    }
                    {
                      com.sun.faces.taglib.jsf_core.LoadBundleTag __jsp_taghandler_6=(com.sun.faces.taglib.jsf_core.LoadBundleTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.jsf_core.LoadBundleTag.class,"com.sun.faces.taglib.jsf_core.LoadBundleTag basename var");
                      __jsp_taghandler_6.setParent(__jsp_taghandler_4);
                      __jsp_taghandler_6.setBasename("cl.bicevida.envionominas.view.resources.messages.general_es");
                      __jsp_taghandler_6.setVar("msgs");
                      __jsp_tag_starteval=__jsp_taghandler_6.doStartTag();
                      if (__jsp_taghandler_6.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                        return;
                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_6,4);
                    }
                    {
                      oracle.adfinternal.view.faces.taglib.core.CoreFormTag __jsp_taghandler_7=(oracle.adfinternal.view.faces.taglib.core.CoreFormTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.CoreFormTag.class,"oracle.adfinternal.view.faces.taglib.core.CoreFormTag");
                      __jsp_taghandler_7.setParent(__jsp_taghandler_4);
                      __jsp_tag_starteval=__jsp_taghandler_7.doStartTag();
                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                      {
                        {
                          oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag __jsp_taghandler_8=(oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag.class,"oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag width background text icon");
                          __jsp_taghandler_8.setParent(__jsp_taghandler_7);
                          __jsp_taghandler_8.setWidth("770px");
                          __jsp_taghandler_8.setBackground("medium");
                          __jsp_taghandler_8.setText("#{msgs['bicevida.gastos_nominas.detalle.titulo']}");
                          __jsp_taghandler_8.setIcon("../../skins/informatica/CoreSkin/skin_images/boton_iso.gif");
                          __jsp_tag_starteval=__jsp_taghandler_8.doStartTag();
                          if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                          {
                            {
                              oracle.adfinternal.view.faces.taglib.html.HtmlTableLayoutTag __jsp_taghandler_9=(oracle.adfinternal.view.faces.taglib.html.HtmlTableLayoutTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlTableLayoutTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlTableLayoutTag width halign");
                              __jsp_taghandler_9.setParent(__jsp_taghandler_8);
                              __jsp_taghandler_9.setWidth("98%");
                              __jsp_taghandler_9.setHalign("center");
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
                                      oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_11=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag");
                                      __jsp_taghandler_11.setParent(__jsp_taghandler_10);
                                      __jsp_tag_starteval=__jsp_taghandler_11.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag __jsp_taghandler_12=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag value");
                                          __jsp_taghandler_12.setParent(__jsp_taghandler_11);
                                          __jsp_taghandler_12.setValue("#{msgs['bicevida.gastos_nominas.lbl.tiponomina'] }");
                                          __jsp_tag_starteval=__jsp_taghandler_12.doStartTag();
                                          if (__jsp_taghandler_12.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_12,9);
                                        }
                                      }
                                      if (__jsp_taghandler_11.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_11,8);
                                    }
                                    {
                                      oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_13=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag");
                                      __jsp_taghandler_13.setParent(__jsp_taghandler_10);
                                      __jsp_tag_starteval=__jsp_taghandler_13.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag __jsp_taghandler_14=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag value");
                                          __jsp_taghandler_14.setParent(__jsp_taghandler_13);
                                          __jsp_taghandler_14.setValue(":");
                                          __jsp_tag_starteval=__jsp_taghandler_14.doStartTag();
                                          if (__jsp_taghandler_14.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_14,9);
                                        }
                                      }
                                      if (__jsp_taghandler_13.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_13,8);
                                    }
                                    {
                                      oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_15=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag");
                                      __jsp_taghandler_15.setParent(__jsp_taghandler_10);
                                      __jsp_tag_starteval=__jsp_taghandler_15.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_16=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag styleClass value");
                                          __jsp_taghandler_16.setParent(__jsp_taghandler_15);
                                          __jsp_taghandler_16.setStyleClass("AFFieldText");
                                          __jsp_taghandler_16.setValue("#{mb_consultaGastosNomina.gastoSeleccionado.nomina.tipo.nombre}");
                                          __jsp_tag_starteval=__jsp_taghandler_16.doStartTag();
                                          if (__jsp_taghandler_16.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_16,9);
                                        }
                                      }
                                      if (__jsp_taghandler_15.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_15,8);
                                    }
                                    {
                                      oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_17=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag");
                                      __jsp_taghandler_17.setParent(__jsp_taghandler_10);
                                      __jsp_tag_starteval=__jsp_taghandler_17.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag __jsp_taghandler_18=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag value");
                                          __jsp_taghandler_18.setParent(__jsp_taghandler_17);
                                          __jsp_taghandler_18.setValue("#{msgs['bicevida.gastos_nominas.lbl.numeronomina'] }");
                                          __jsp_tag_starteval=__jsp_taghandler_18.doStartTag();
                                          if (__jsp_taghandler_18.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_18,9);
                                        }
                                      }
                                      if (__jsp_taghandler_17.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_17,8);
                                    }
                                    {
                                      oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_19=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag");
                                      __jsp_taghandler_19.setParent(__jsp_taghandler_10);
                                      __jsp_tag_starteval=__jsp_taghandler_19.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag __jsp_taghandler_20=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag value");
                                          __jsp_taghandler_20.setParent(__jsp_taghandler_19);
                                          __jsp_taghandler_20.setValue(":");
                                          __jsp_tag_starteval=__jsp_taghandler_20.doStartTag();
                                          if (__jsp_taghandler_20.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_20,9);
                                        }
                                      }
                                      if (__jsp_taghandler_19.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_19,8);
                                    }
                                    {
                                      oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_21=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag");
                                      __jsp_taghandler_21.setParent(__jsp_taghandler_10);
                                      __jsp_tag_starteval=__jsp_taghandler_21.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_22=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag styleClass value");
                                          __jsp_taghandler_22.setParent(__jsp_taghandler_21);
                                          __jsp_taghandler_22.setStyleClass("AFFieldText");
                                          __jsp_taghandler_22.setValue("#{mb_consultaGastosNomina.gastoSeleccionado.nomina.id}");
                                          __jsp_tag_starteval=__jsp_taghandler_22.doStartTag();
                                          if (__jsp_taghandler_22.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_22,9);
                                        }
                                      }
                                      if (__jsp_taghandler_21.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_21,8);
                                    }
                                  }
                                  if (__jsp_taghandler_10.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_10,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag __jsp_taghandler_23=(oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag");
                                  __jsp_taghandler_23.setParent(__jsp_taghandler_9);
                                  __jsp_tag_starteval=__jsp_taghandler_23.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_24=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag");
                                      __jsp_taghandler_24.setParent(__jsp_taghandler_23);
                                      __jsp_tag_starteval=__jsp_taghandler_24.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag __jsp_taghandler_25=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag value");
                                          __jsp_taghandler_25.setParent(__jsp_taghandler_24);
                                          __jsp_taghandler_25.setValue("#{msgs['bicevida.gastos_nominas.lbl.banco'] }");
                                          __jsp_tag_starteval=__jsp_taghandler_25.doStartTag();
                                          if (__jsp_taghandler_25.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_25,9);
                                        }
                                      }
                                      if (__jsp_taghandler_24.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_24,8);
                                    }
                                    {
                                      oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_26=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag");
                                      __jsp_taghandler_26.setParent(__jsp_taghandler_23);
                                      __jsp_tag_starteval=__jsp_taghandler_26.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag __jsp_taghandler_27=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag value");
                                          __jsp_taghandler_27.setParent(__jsp_taghandler_26);
                                          __jsp_taghandler_27.setValue(":");
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
                                    {
                                      oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_28=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag");
                                      __jsp_taghandler_28.setParent(__jsp_taghandler_23);
                                      __jsp_tag_starteval=__jsp_taghandler_28.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_29=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag styleClass value");
                                          __jsp_taghandler_29.setParent(__jsp_taghandler_28);
                                          __jsp_taghandler_29.setStyleClass("AFFieldText");
                                          __jsp_taghandler_29.setValue("#{mb_consultaGastosNomina.gastoSeleccionado.nomina.bancoProceso}");
                                          __jsp_tag_starteval=__jsp_taghandler_29.doStartTag();
                                          if (__jsp_taghandler_29.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_29,9);
                                        }
                                      }
                                      if (__jsp_taghandler_28.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_28,8);
                                    }
                                    {
                                      oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_30=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag");
                                      __jsp_taghandler_30.setParent(__jsp_taghandler_23);
                                      __jsp_tag_starteval=__jsp_taghandler_30.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag __jsp_taghandler_31=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag value");
                                          __jsp_taghandler_31.setParent(__jsp_taghandler_30);
                                          __jsp_taghandler_31.setValue("#{msgs['bicevida.gastos_nominas.lbl.fechaconciliacion'] }");
                                          __jsp_tag_starteval=__jsp_taghandler_31.doStartTag();
                                          if (__jsp_taghandler_31.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_31,9);
                                        }
                                      }
                                      if (__jsp_taghandler_30.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_30,8);
                                    }
                                    {
                                      oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_32=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag");
                                      __jsp_taghandler_32.setParent(__jsp_taghandler_23);
                                      __jsp_tag_starteval=__jsp_taghandler_32.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag __jsp_taghandler_33=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag value");
                                          __jsp_taghandler_33.setParent(__jsp_taghandler_32);
                                          __jsp_taghandler_33.setValue(":");
                                          __jsp_tag_starteval=__jsp_taghandler_33.doStartTag();
                                          if (__jsp_taghandler_33.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_33,9);
                                        }
                                      }
                                      if (__jsp_taghandler_32.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_32,8);
                                    }
                                    {
                                      oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_34=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag");
                                      __jsp_taghandler_34.setParent(__jsp_taghandler_23);
                                      __jsp_tag_starteval=__jsp_taghandler_34.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_35=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag styleClass value");
                                          __jsp_taghandler_35.setParent(__jsp_taghandler_34);
                                          __jsp_taghandler_35.setStyleClass("AFFieldText");
                                          __jsp_taghandler_35.setValue("#{mb_consultaGastosNomina.gastoSeleccionado.fechaConciliacionProceso}");
                                          __jsp_tag_starteval=__jsp_taghandler_35.doStartTag();
                                          if (__jsp_taghandler_35.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_35,9);
                                        }
                                      }
                                      if (__jsp_taghandler_34.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_34,8);
                                    }
                                  }
                                  if (__jsp_taghandler_23.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_23,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag __jsp_taghandler_36=(oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag");
                                  __jsp_taghandler_36.setParent(__jsp_taghandler_9);
                                  __jsp_tag_starteval=__jsp_taghandler_36.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_37=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag");
                                      __jsp_taghandler_37.setParent(__jsp_taghandler_36);
                                      __jsp_tag_starteval=__jsp_taghandler_37.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag __jsp_taghandler_38=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag value");
                                          __jsp_taghandler_38.setParent(__jsp_taghandler_37);
                                          __jsp_taghandler_38.setValue("#{msgs['bicevida.gastos_nominas.lbl.fechainicioproceso'] }");
                                          __jsp_tag_starteval=__jsp_taghandler_38.doStartTag();
                                          if (__jsp_taghandler_38.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_38,9);
                                        }
                                      }
                                      if (__jsp_taghandler_37.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_37,8);
                                    }
                                    {
                                      oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_39=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag");
                                      __jsp_taghandler_39.setParent(__jsp_taghandler_36);
                                      __jsp_tag_starteval=__jsp_taghandler_39.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag __jsp_taghandler_40=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag value");
                                          __jsp_taghandler_40.setParent(__jsp_taghandler_39);
                                          __jsp_taghandler_40.setValue(":");
                                          __jsp_tag_starteval=__jsp_taghandler_40.doStartTag();
                                          if (__jsp_taghandler_40.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_40,9);
                                        }
                                      }
                                      if (__jsp_taghandler_39.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_39,8);
                                    }
                                    {
                                      oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_41=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag");
                                      __jsp_taghandler_41.setParent(__jsp_taghandler_36);
                                      __jsp_tag_starteval=__jsp_taghandler_41.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_42=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag styleClass value");
                                          __jsp_taghandler_42.setParent(__jsp_taghandler_41);
                                          __jsp_taghandler_42.setStyleClass("AFFieldText");
                                          __jsp_taghandler_42.setValue("#{mb_consultaGastosNomina.gastoSeleccionado.fechaInicioProceso}");
                                          __jsp_tag_starteval=__jsp_taghandler_42.doStartTag();
                                          if (__jsp_taghandler_42.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_42,9);
                                        }
                                      }
                                      if (__jsp_taghandler_41.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_41,8);
                                    }
                                  }
                                  if (__jsp_taghandler_36.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_36,7);
                                }
                              }
                              if (__jsp_taghandler_9.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_9,6);
                            }
                            {
                              oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag __jsp_taghandler_43=(oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag width height");
                              __jsp_taghandler_43.setParent(__jsp_taghandler_8);
                              __jsp_taghandler_43.setWidth("10");
                              __jsp_taghandler_43.setHeight("10");
                              __jsp_tag_starteval=__jsp_taghandler_43.doStartTag();
                              if (__jsp_taghandler_43.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_43,6);
                            }
                            {
                              oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag __jsp_taghandler_44=(oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag.class,"oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag halign");
                              __jsp_taghandler_44.setParent(__jsp_taghandler_8);
                              __jsp_taghandler_44.setHalign("center");
                              __jsp_tag_starteval=__jsp_taghandler_44.doStartTag();
                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                              {
                                {
                                  oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag __jsp_taghandler_45=(oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag text blocking action");
                                  __jsp_taghandler_45.setParent(__jsp_taghandler_44);
                                  __jsp_taghandler_45.setText("#{msgs['bicevida.generales.btn.volver'] }");
                                  __jsp_taghandler_45.setBlocking("true");
                                  __jsp_taghandler_45.setAction("ConsultaGastosNomina");
                                  __jsp_tag_starteval=__jsp_taghandler_45.doStartTag();
                                  if (__jsp_taghandler_45.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_45,7);
                                }
                              }
                              if (__jsp_taghandler_44.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_44,6);
                            }
                            {
                              oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag __jsp_taghandler_46=(oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag width height");
                              __jsp_taghandler_46.setParent(__jsp_taghandler_8);
                              __jsp_taghandler_46.setWidth("10");
                              __jsp_taghandler_46.setHeight("10");
                              __jsp_tag_starteval=__jsp_taghandler_46.doStartTag();
                              if (__jsp_taghandler_46.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_46,6);
                            }
                            {
                              oracle.adfinternal.view.faces.taglib.core.data.CoreTableTag __jsp_taghandler_47=(oracle.adfinternal.view.faces.taglib.core.data.CoreTableTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreTableTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreTableTag width emptyText banding bandingInterval var value rows");
                              __jsp_taghandler_47.setParent(__jsp_taghandler_8);
                              __jsp_taghandler_47.setWidth("100%");
                              __jsp_taghandler_47.setEmptyText("#{msgs['bicevida.generales.lbl.listavacia'] }");
                              __jsp_taghandler_47.setBanding("row");
                              __jsp_taghandler_47.setBandingInterval("1");
                              __jsp_taghandler_47.setVar("fila");
                              __jsp_taghandler_47.setValue("#{mb_consultaGastosNomina.gastoSeleccionado.detalle}");
                              __jsp_taghandler_47.setRows("#{mb_consultaGastosNomina.registrosPagina}");
                              __jsp_tag_starteval=__jsp_taghandler_47.doStartTag();
                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                              {
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_48=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText sortable sortProperty");
                                  __jsp_taghandler_48.setParent(__jsp_taghandler_47);
                                  __jsp_taghandler_48.setFormatType("text");
                                  __jsp_taghandler_48.setHeaderText("#{msgs['bicevida.gastos_nominas.col.banco']}");
                                  __jsp_taghandler_48.setSortable("true");
                                  __jsp_taghandler_48.setSortProperty("banco");
                                  __jsp_tag_starteval=__jsp_taghandler_48.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_49=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_49.setParent(__jsp_taghandler_48);
                                      __jsp_taghandler_49.setValue("#{fila.banco.nombre}");
                                      __jsp_tag_starteval=__jsp_taghandler_49.doStartTag();
                                      if (__jsp_taghandler_49.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_49,8);
                                    }
                                  }
                                  if (__jsp_taghandler_48.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_48,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_50=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText inlineStyle");
                                  __jsp_taghandler_50.setParent(__jsp_taghandler_47);
                                  __jsp_taghandler_50.setFormatType("text");
                                  __jsp_taghandler_50.setHeaderText("#{msgs['bicevida.gastos_nominas.col.registros']}");
                                  __jsp_taghandler_50.setInlineStyle("text-align:right;");
                                  __jsp_tag_starteval=__jsp_taghandler_50.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_51=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_51.setParent(__jsp_taghandler_50);
                                      __jsp_taghandler_51.setValue("#{fila.cantidadProcesados}");
                                      __jsp_tag_starteval=__jsp_taghandler_51.doStartTag();
                                      if (__jsp_taghandler_51.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_51,8);
                                    }
                                  }
                                  if (__jsp_taghandler_50.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_50,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_52=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText inlineStyle");
                                  __jsp_taghandler_52.setParent(__jsp_taghandler_47);
                                  __jsp_taghandler_52.setFormatType("text");
                                  __jsp_taghandler_52.setHeaderText("#{msgs['bicevida.gastos_nominas.col.registrosnoprocesado']}");
                                  __jsp_taghandler_52.setInlineStyle("text-align:right;");
                                  __jsp_tag_starteval=__jsp_taghandler_52.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_53=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_53.setParent(__jsp_taghandler_52);
                                      __jsp_taghandler_53.setValue("#{fila.cantidadNoProcesados}");
                                      __jsp_tag_starteval=__jsp_taghandler_53.doStartTag();
                                      if (__jsp_taghandler_53.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_53,8);
                                    }
                                  }
                                  if (__jsp_taghandler_52.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_52,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_54=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText inlineStyle");
                                  __jsp_taghandler_54.setParent(__jsp_taghandler_47);
                                  __jsp_taghandler_54.setFormatType("text");
                                  __jsp_taghandler_54.setHeaderText("#{msgs['bicevida.gastos_nominas.col.montoprocesado']}");
                                  __jsp_taghandler_54.setInlineStyle("text-align:right;");
                                  __jsp_tag_starteval=__jsp_taghandler_54.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_55=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_55.setParent(__jsp_taghandler_54);
                                      __jsp_taghandler_55.setValue("#{fila.montoTotalProcesados}");
                                      __jsp_tag_starteval=__jsp_taghandler_55.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag __jsp_taghandler_56=(oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag.class,"oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag groupingUsed");
                                          __jsp_taghandler_56.setParent(__jsp_taghandler_55);
                                          __jsp_taghandler_56.setGroupingUsed("true");
                                          __jsp_tag_starteval=__jsp_taghandler_56.doStartTag();
                                          if (__jsp_taghandler_56.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_56,9);
                                        }
                                      }
                                      if (__jsp_taghandler_55.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_55,8);
                                    }
                                  }
                                  if (__jsp_taghandler_54.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_54,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_57=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText inlineStyle");
                                  __jsp_taghandler_57.setParent(__jsp_taghandler_47);
                                  __jsp_taghandler_57.setFormatType("text");
                                  __jsp_taghandler_57.setHeaderText("#{msgs['bicevida.gastos_nominas.col.montonoprocesado']}");
                                  __jsp_taghandler_57.setInlineStyle("text-align:right;");
                                  __jsp_tag_starteval=__jsp_taghandler_57.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_58=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_58.setParent(__jsp_taghandler_57);
                                      __jsp_taghandler_58.setValue("#{fila.montoTotalNoProcesados}");
                                      __jsp_tag_starteval=__jsp_taghandler_58.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag __jsp_taghandler_59=(oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag.class,"oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag groupingUsed");
                                          __jsp_taghandler_59.setParent(__jsp_taghandler_58);
                                          __jsp_taghandler_59.setGroupingUsed("true");
                                          __jsp_tag_starteval=__jsp_taghandler_59.doStartTag();
                                          if (__jsp_taghandler_59.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_59,9);
                                        }
                                      }
                                      if (__jsp_taghandler_58.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_58,8);
                                    }
                                  }
                                  if (__jsp_taghandler_57.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_57,7);
                                }
                                {
                                  javax.faces.webapp.FacetTag __jsp_taghandler_60=(javax.faces.webapp.FacetTag)OracleJspRuntime.getTagHandler(pageContext,javax.faces.webapp.FacetTag.class,"javax.faces.webapp.FacetTag name");
                                  __jsp_taghandler_60.setParent(__jsp_taghandler_47);
                                  __jsp_taghandler_60.setName("actions");
                                  __jsp_tag_starteval=__jsp_taghandler_60.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    do {
                                      {
                                        oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag __jsp_taghandler_61=(oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag.class,"oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag halign");
                                        __jsp_taghandler_61.setParent(__jsp_taghandler_60);
                                        __jsp_taghandler_61.setHalign("center");
                                        __jsp_tag_starteval=__jsp_taghandler_61.doStartTag();
                                        if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                        {
                                          {
                                            oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag __jsp_taghandler_62=(oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag text blocking actionListener");
                                            __jsp_taghandler_62.setParent(__jsp_taghandler_61);
                                            __jsp_taghandler_62.setText("#{msgs['bicevida.generales.btn.descargaxls']}");
                                            __jsp_taghandler_62.setBlocking("true");
                                            __jsp_taghandler_62.setActionListener("#{MbExcelVisor.onClickGenerarExcel}");
                                            __jsp_tag_starteval=__jsp_taghandler_62.doStartTag();
                                            if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                            {
                                              {
                                                javax.faces.webapp.AttributeTag __jsp_taghandler_63=(javax.faces.webapp.AttributeTag)OracleJspRuntime.getTagHandler(pageContext,javax.faces.webapp.AttributeTag.class,"javax.faces.webapp.AttributeTag name value");
                                                __jsp_taghandler_63.setParent(__jsp_taghandler_62);
                                                __jsp_taghandler_63.setName("nombre");
                                                __jsp_taghandler_63.setValue("detalle_gastos_nomina");
                                                __jsp_tag_starteval=__jsp_taghandler_63.doStartTag();
                                                if (__jsp_taghandler_63.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                  return;
                                                OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_63,10);
                                              }
                                              {
                                                javax.faces.webapp.AttributeTag __jsp_taghandler_64=(javax.faces.webapp.AttributeTag)OracleJspRuntime.getTagHandler(pageContext,javax.faces.webapp.AttributeTag.class,"javax.faces.webapp.AttributeTag name value");
                                                __jsp_taghandler_64.setParent(__jsp_taghandler_62);
                                                __jsp_taghandler_64.setName("titulo");
                                                __jsp_taghandler_64.setValue("Banco:Registros Procesados:Registros No Procesados:Monto Registros Procesados:Monto Registros No Procesados");
                                                __jsp_tag_starteval=__jsp_taghandler_64.doStartTag();
                                                if (__jsp_taghandler_64.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                  return;
                                                OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_64,10);
                                              }
                                            }
                                            if (__jsp_taghandler_62.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                              return;
                                            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_62,9);
                                          }
                                        }
                                        if (__jsp_taghandler_61.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                          return;
                                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_61,8);
                                      }
                                    } while (__jsp_taghandler_60.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
                                  }
                                  if (__jsp_taghandler_60.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_60,7);
                                }
                              }
                              if (__jsp_taghandler_47.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_47,6);
                            }
                          }
                          if (__jsp_taghandler_8.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                            return;
                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_8,5);
                        }
                      }
                      if (__jsp_taghandler_7.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                        return;
                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_7,4);
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
    "\n                        \n                ".toCharArray();
    }
    catch (Throwable th) {
      System.err.println(th);
    }
}
}
