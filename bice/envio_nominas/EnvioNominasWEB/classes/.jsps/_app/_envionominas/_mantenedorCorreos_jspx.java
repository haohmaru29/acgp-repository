package _app._envionominas;

import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import oracle.jsp.el.*;
import javax.servlet.jsp.el.*;


public class _mantenedorCorreos_jspx extends com.orionserver.http.OrionHttpJspPage {


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
    _mantenedorCorreos_jspx page = this;
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
                  com.sun.faces.taglib.jsf_core.LoadBundleTag __jsp_taghandler_3=(com.sun.faces.taglib.jsf_core.LoadBundleTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.jsf_core.LoadBundleTag.class,"com.sun.faces.taglib.jsf_core.LoadBundleTag basename var");
                  __jsp_taghandler_3.setParent(__jsp_taghandler_2);
                  __jsp_taghandler_3.setBasename("cl.bicevida.envionominas.view.resources.messages.general_es");
                  __jsp_taghandler_3.setVar("msgs");
                  __jsp_tag_starteval=__jsp_taghandler_3.doStartTag();
                  if (__jsp_taghandler_3.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                    return;
                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_3,3);
                }
                {
                  oracle.adfinternal.view.faces.taglib.html.HtmlHeadTag __jsp_taghandler_4=(oracle.adfinternal.view.faces.taglib.html.HtmlHeadTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlHeadTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlHeadTag title");
                  __jsp_taghandler_4.setParent(__jsp_taghandler_2);
                  __jsp_taghandler_4.setTitle("#{msgs['bicevida.correos.titulo'] }");
                  __jsp_tag_starteval=__jsp_taghandler_4.doStartTag();
                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                  {
                    out.write( "<meta"+ " http-equiv=\"" + "Content-Type"+ "\"" + " content=\"" + "text/html; charset=windows-1252"+ "\"" +"/>");
                  }
                  if (__jsp_taghandler_4.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                    return;
                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_4,3);
                }
                {
                  oracle.adfinternal.view.faces.taglib.html.HtmlBodyTag __jsp_taghandler_5=(oracle.adfinternal.view.faces.taglib.html.HtmlBodyTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlBodyTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlBodyTag");
                  __jsp_taghandler_5.setParent(__jsp_taghandler_2);
                  __jsp_tag_starteval=__jsp_taghandler_5.doStartTag();
                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                  {
                    {
                      oracle.adfinternal.view.faces.taglib.core.CoreFormTag __jsp_taghandler_6=(oracle.adfinternal.view.faces.taglib.core.CoreFormTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.CoreFormTag.class,"oracle.adfinternal.view.faces.taglib.core.CoreFormTag");
                      __jsp_taghandler_6.setParent(__jsp_taghandler_5);
                      __jsp_tag_starteval=__jsp_taghandler_6.doStartTag();
                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                      {
                        {
                          oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag __jsp_taghandler_7=(oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag.class,"oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag width background text icon");
                          __jsp_taghandler_7.setParent(__jsp_taghandler_6);
                          __jsp_taghandler_7.setWidth("770px");
                          __jsp_taghandler_7.setBackground("medium");
                          __jsp_taghandler_7.setText("#{msgs['bicevida.correos.titulo'] }");
                          __jsp_taghandler_7.setIcon("../../skins/informatica/CoreSkin/skin_images/boton_iso.gif");
                          __jsp_tag_starteval=__jsp_taghandler_7.doStartTag();
                          if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                          {
                            {
                              oracle.adfinternal.view.faces.taglib.html.HtmlTableLayoutTag __jsp_taghandler_8=(oracle.adfinternal.view.faces.taglib.html.HtmlTableLayoutTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlTableLayoutTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlTableLayoutTag width halign");
                              __jsp_taghandler_8.setParent(__jsp_taghandler_7);
                              __jsp_taghandler_8.setWidth("98%");
                              __jsp_taghandler_8.setHalign("center");
                              __jsp_tag_starteval=__jsp_taghandler_8.doStartTag();
                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                              {
                                {
                                  oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag __jsp_taghandler_9=(oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag");
                                  __jsp_taghandler_9.setParent(__jsp_taghandler_8);
                                  __jsp_tag_starteval=__jsp_taghandler_9.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_10=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag");
                                      __jsp_taghandler_10.setParent(__jsp_taghandler_9);
                                      __jsp_tag_starteval=__jsp_taghandler_10.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.core.layout.CorePanelFormTag __jsp_taghandler_11=(oracle.adfinternal.view.faces.taglib.core.layout.CorePanelFormTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.layout.CorePanelFormTag.class,"oracle.adfinternal.view.faces.taglib.core.layout.CorePanelFormTag rows");
                                          __jsp_taghandler_11.setParent(__jsp_taghandler_10);
                                          __jsp_taghandler_11.setRows("7");
                                          __jsp_tag_starteval=__jsp_taghandler_11.doStartTag();
                                          if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                          {
                                            {
                                              oracle.adfinternal.view.faces.taglib.core.input.CoreInputTextTag __jsp_taghandler_12=(oracle.adfinternal.view.faces.taglib.core.input.CoreInputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.input.CoreInputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.input.CoreInputTextTag label columns maximumLength value");
                                              __jsp_taghandler_12.setParent(__jsp_taghandler_11);
                                              __jsp_taghandler_12.setLabel("#{msgs['bicevida.correos.lbl.nombre']}");
                                              __jsp_taghandler_12.setColumns("120");
                                              __jsp_taghandler_12.setMaximumLength("4000");
                                              __jsp_taghandler_12.setValue("#{mb_mantenedorCorreos.nombre}");
                                              __jsp_tag_starteval=__jsp_taghandler_12.doStartTag();
                                              if (__jsp_taghandler_12.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                return;
                                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_12,10);
                                            }
                                            {
                                              oracle.adfinternal.view.faces.taglib.core.input.CoreInputTextTag __jsp_taghandler_13=(oracle.adfinternal.view.faces.taglib.core.input.CoreInputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.input.CoreInputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.input.CoreInputTextTag label columns maximumLength value");
                                              __jsp_taghandler_13.setParent(__jsp_taghandler_11);
                                              __jsp_taghandler_13.setLabel("#{msgs['bicevida.correos.lbl.subject']}");
                                              __jsp_taghandler_13.setColumns("120");
                                              __jsp_taghandler_13.setMaximumLength("4000");
                                              __jsp_taghandler_13.setValue("#{mb_mantenedorCorreos.subject}");
                                              __jsp_tag_starteval=__jsp_taghandler_13.doStartTag();
                                              if (__jsp_taghandler_13.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                return;
                                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_13,10);
                                            }
                                            {
                                              oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag __jsp_taghandler_14=(oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag.class,"oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag unselectedLabel label value");
                                              __jsp_taghandler_14.setParent(__jsp_taghandler_11);
                                              __jsp_taghandler_14.setUnselectedLabel("#{msgs['bicevida.generales.opt.seleccione'] }");
                                              __jsp_taghandler_14.setLabel("#{msgs['bicevida.correos.lbl.estado']}");
                                              __jsp_taghandler_14.setValue("#{mb_mantenedorCorreos.estadoSeleccionado}");
                                              __jsp_tag_starteval=__jsp_taghandler_14.doStartTag();
                                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                              {
                                                {
                                                  com.sun.faces.taglib.jsf_core.SelectItemsTag __jsp_taghandler_15=(com.sun.faces.taglib.jsf_core.SelectItemsTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.jsf_core.SelectItemsTag.class,"com.sun.faces.taglib.jsf_core.SelectItemsTag id value");
                                                  __jsp_taghandler_15.setParent(__jsp_taghandler_14);
                                                  __jsp_taghandler_15.setId("sitEstadoCorreo");
                                                  __jsp_taghandler_15.setValue("#{mb_mantenedorCorreos.estadosNomina}");
                                                  __jsp_tag_starteval=__jsp_taghandler_15.doStartTag();
                                                  if (__jsp_taghandler_15.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                    return;
                                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_15,11);
                                                }
                                              }
                                              if (__jsp_taghandler_14.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                return;
                                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_14,10);
                                            }
                                            {
                                              oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag __jsp_taghandler_16=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag value");
                                              __jsp_taghandler_16.setParent(__jsp_taghandler_11);
                                              __jsp_taghandler_16.setValue("#{msgs['bicevida.correos.lbl.mensaje']}");
                                              __jsp_tag_starteval=__jsp_taghandler_16.doStartTag();
                                              if (__jsp_taghandler_16.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                return;
                                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_16,10);
                                            }
                                            {
                                              oracle.adfinternal.view.faces.taglib.core.input.CoreInputTextTag __jsp_taghandler_17=(oracle.adfinternal.view.faces.taglib.core.input.CoreInputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.input.CoreInputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.input.CoreInputTextTag rows columns maximumLength value");
                                              __jsp_taghandler_17.setParent(__jsp_taghandler_11);
                                              __jsp_taghandler_17.setRows("7");
                                              __jsp_taghandler_17.setColumns("120");
                                              __jsp_taghandler_17.setMaximumLength("4000");
                                              __jsp_taghandler_17.setValue("#{mb_mantenedorCorreos.mensaje}");
                                              __jsp_tag_starteval=__jsp_taghandler_17.doStartTag();
                                              if (__jsp_taghandler_17.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                return;
                                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_17,10);
                                            }
                                          }
                                          if (__jsp_taghandler_11.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_11,9);
                                        }
                                      }
                                      if (__jsp_taghandler_10.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_10,8);
                                    }
                                  }
                                  if (__jsp_taghandler_9.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_9,7);
                                }
                              }
                              if (__jsp_taghandler_8.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_8,6);
                            }
                            {
                              oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag __jsp_taghandler_18=(oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag width height");
                              __jsp_taghandler_18.setParent(__jsp_taghandler_7);
                              __jsp_taghandler_18.setWidth("10");
                              __jsp_taghandler_18.setHeight("10");
                              __jsp_tag_starteval=__jsp_taghandler_18.doStartTag();
                              if (__jsp_taghandler_18.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_18,6);
                            }
                            {
                              oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag __jsp_taghandler_19=(oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag width height");
                              __jsp_taghandler_19.setParent(__jsp_taghandler_7);
                              __jsp_taghandler_19.setWidth("10");
                              __jsp_taghandler_19.setHeight("10");
                              __jsp_tag_starteval=__jsp_taghandler_19.doStartTag();
                              if (__jsp_taghandler_19.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_19,6);
                            }
                            {
                              oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag __jsp_taghandler_20=(oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag.class,"oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag halign");
                              __jsp_taghandler_20.setParent(__jsp_taghandler_7);
                              __jsp_taghandler_20.setHalign("center");
                              __jsp_tag_starteval=__jsp_taghandler_20.doStartTag();
                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                              {
                                {
                                  oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag __jsp_taghandler_21=(oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag text blocking actionListener rendered");
                                  __jsp_taghandler_21.setParent(__jsp_taghandler_20);
                                  __jsp_taghandler_21.setText("#{msgs['bicevida.generales.btn.modificar'] }");
                                  __jsp_taghandler_21.setBlocking("true");
                                  __jsp_taghandler_21.setActionListener("#{mb_mantenedorCorreos.ejecutarModificar}");
                                  __jsp_taghandler_21.setRendered("#{mb_mantenedorCorreos.showBtnModificaCorreo}");
                                  __jsp_tag_starteval=__jsp_taghandler_21.doStartTag();
                                  if (__jsp_taghandler_21.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_21,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag __jsp_taghandler_22=(oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag text blocking actionListener rendered");
                                  __jsp_taghandler_22.setParent(__jsp_taghandler_20);
                                  __jsp_taghandler_22.setText("#{msgs['bicevida.generales.btn.agregar'] }");
                                  __jsp_taghandler_22.setBlocking("true");
                                  __jsp_taghandler_22.setActionListener("#{mb_mantenedorCorreos.ejecutarAgregar}");
                                  __jsp_taghandler_22.setRendered("#{mb_mantenedorCorreos.showBtnAgregaCorreo}");
                                  __jsp_tag_starteval=__jsp_taghandler_22.doStartTag();
                                  if (__jsp_taghandler_22.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_22,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.input.CoreResetButtonTag __jsp_taghandler_23=(oracle.adfinternal.view.faces.taglib.core.input.CoreResetButtonTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.input.CoreResetButtonTag.class,"oracle.adfinternal.view.faces.taglib.core.input.CoreResetButtonTag text");
                                  __jsp_taghandler_23.setParent(__jsp_taghandler_20);
                                  __jsp_taghandler_23.setText("#{msgs['bicevida.generales.btn.limpiar'] }");
                                  __jsp_tag_starteval=__jsp_taghandler_23.doStartTag();
                                  if (__jsp_taghandler_23.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_23,7);
                                }
                              }
                              if (__jsp_taghandler_20.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_20,6);
                            }
                            {
                              oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag __jsp_taghandler_24=(oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag width height");
                              __jsp_taghandler_24.setParent(__jsp_taghandler_7);
                              __jsp_taghandler_24.setWidth("10");
                              __jsp_taghandler_24.setHeight("10");
                              __jsp_tag_starteval=__jsp_taghandler_24.doStartTag();
                              if (__jsp_taghandler_24.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_24,6);
                            }
                            {
                              oracle.adfinternal.view.faces.taglib.core.data.CoreTableTag __jsp_taghandler_25=(oracle.adfinternal.view.faces.taglib.core.data.CoreTableTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreTableTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreTableTag id width emptyText banding bandingInterval var value rows");
                              __jsp_taghandler_25.setParent(__jsp_taghandler_7);
                              __jsp_taghandler_25.setId("tblCorreos");
                              __jsp_taghandler_25.setWidth("100%");
                              __jsp_taghandler_25.setEmptyText("#{msgs['bicevida.generales.lbl.listavacia'] }");
                              __jsp_taghandler_25.setBanding("row");
                              __jsp_taghandler_25.setBandingInterval("1");
                              __jsp_taghandler_25.setVar("fila");
                              __jsp_taghandler_25.setValue("#{mb_mantenedorCorreos.lista}");
                              __jsp_taghandler_25.setRows("#{mb_mantenedorCorreos.registrosPagina}");
                              __jsp_tag_starteval=__jsp_taghandler_25.doStartTag();
                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                              {
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_26=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType width headerText");
                                  __jsp_taghandler_26.setParent(__jsp_taghandler_25);
                                  __jsp_taghandler_26.setFormatType("text");
                                  __jsp_taghandler_26.setWidth("171");
                                  __jsp_taghandler_26.setHeaderText("#{msgs['bicevida.correos.col.nombre']}");
                                  __jsp_tag_starteval=__jsp_taghandler_26.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_27=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_27.setParent(__jsp_taghandler_26);
                                      __jsp_taghandler_27.setValue("#{fila.nombre}");
                                      __jsp_tag_starteval=__jsp_taghandler_27.doStartTag();
                                      if (__jsp_taghandler_27.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_27,8);
                                    }
                                  }
                                  if (__jsp_taghandler_26.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_26,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_28=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText");
                                  __jsp_taghandler_28.setParent(__jsp_taghandler_25);
                                  __jsp_taghandler_28.setFormatType("text");
                                  __jsp_taghandler_28.setHeaderText("#{msgs['bicevida.correos.col.estadonomina']}");
                                  __jsp_tag_starteval=__jsp_taghandler_28.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_29=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_29.setParent(__jsp_taghandler_28);
                                      __jsp_taghandler_29.setValue("#{fila.estado.nombre}");
                                      __jsp_tag_starteval=__jsp_taghandler_29.doStartTag();
                                      if (__jsp_taghandler_29.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_29,8);
                                    }
                                  }
                                  if (__jsp_taghandler_28.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_28,7);
                                }
                                {
                                  javax.faces.webapp.FacetTag __jsp_taghandler_30=(javax.faces.webapp.FacetTag)OracleJspRuntime.getTagHandler(pageContext,javax.faces.webapp.FacetTag.class,"javax.faces.webapp.FacetTag name");
                                  __jsp_taghandler_30.setParent(__jsp_taghandler_25);
                                  __jsp_taghandler_30.setName("selection");
                                  __jsp_tag_starteval=__jsp_taghandler_30.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    do {
                                      {
                                        oracle.adfinternal.view.faces.taglib.core.data.CoreTableSelectOneTag __jsp_taghandler_31=(oracle.adfinternal.view.faces.taglib.core.data.CoreTableSelectOneTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreTableSelectOneTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreTableSelectOneTag");
                                        __jsp_taghandler_31.setParent(__jsp_taghandler_30);
                                        __jsp_tag_starteval=__jsp_taghandler_31.doStartTag();
                                        if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                        {
                                          {
                                            oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag __jsp_taghandler_32=(oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag text blocking actionListener");
                                            __jsp_taghandler_32.setParent(__jsp_taghandler_31);
                                            __jsp_taghandler_32.setText("#{msgs['bicevida.generales.btn.editar']}");
                                            __jsp_taghandler_32.setBlocking("true");
                                            __jsp_taghandler_32.setActionListener("#{mb_mantenedorCorreos.onClickEditar}");
                                            __jsp_tag_starteval=__jsp_taghandler_32.doStartTag();
                                            if (__jsp_taghandler_32.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                              return;
                                            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_32,9);
                                          }
                                          {
                                            oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag __jsp_taghandler_33=(oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag text blocking actionListener");
                                            __jsp_taghandler_33.setParent(__jsp_taghandler_31);
                                            __jsp_taghandler_33.setText("#{msgs['bicevida.generales.btn.eliminar']}");
                                            __jsp_taghandler_33.setBlocking("true");
                                            __jsp_taghandler_33.setActionListener("#{mb_mantenedorCorreos.onClickEliminar}");
                                            __jsp_tag_starteval=__jsp_taghandler_33.doStartTag();
                                            if (__jsp_taghandler_33.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                              return;
                                            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_33,9);
                                          }
                                        }
                                        if (__jsp_taghandler_31.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                          return;
                                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_31,8);
                                      }
                                    } while (__jsp_taghandler_30.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
                                  }
                                  if (__jsp_taghandler_30.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_30,7);
                                }
                              }
                              if (__jsp_taghandler_25.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_25,6);
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
                  if (__jsp_taghandler_5.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                    return;
                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_5,3);
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
