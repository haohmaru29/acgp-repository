package _app._envionominas;

import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import oracle.jsp.el.*;
import javax.servlet.jsp.el.*;


public class _extraccionNomina_jspx extends com.orionserver.http.OrionHttpJspPage {


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
    _extraccionNomina_jspx page = this;
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
                  __jsp_taghandler_3.setTitle("#{msgs['bicevida.extraccion_nominas.titulo']}");
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
                      __jsp_taghandler_5.setBasename("cl.bicevida.envionominas.view.resources.messages.general_es");
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
                          oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag __jsp_taghandler_7=(oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag.class,"oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag width background text icon");
                          __jsp_taghandler_7.setParent(__jsp_taghandler_6);
                          __jsp_taghandler_7.setWidth("770px");
                          __jsp_taghandler_7.setBackground("medium");
                          __jsp_taghandler_7.setText("#{msgs['bicevida.extraccion_nominas.titulo'] }");
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
                                          __jsp_taghandler_11.setRows("2");
                                          __jsp_tag_starteval=__jsp_taghandler_11.doStartTag();
                                          if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                          {
                                            {
                                              oracle.adfinternal.view.faces.taglib.core.input.CoreInputTextTag __jsp_taghandler_12=(oracle.adfinternal.view.faces.taglib.core.input.CoreInputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.input.CoreInputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.input.CoreInputTextTag label maximumLength value");
                                              __jsp_taghandler_12.setParent(__jsp_taghandler_11);
                                              __jsp_taghandler_12.setLabel("#{msgs['bicevida.extraccion_nominas.lbl.lotenomina'] }");
                                              __jsp_taghandler_12.setMaximumLength("8");
                                              __jsp_taghandler_12.setValue("#{mb_extraccionNomina.loteNomina}");
                                              __jsp_tag_starteval=__jsp_taghandler_12.doStartTag();
                                              if (__jsp_taghandler_12.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                return;
                                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_12,10);
                                            }
                                            {
                                              oracle.adfinternal.view.faces.taglib.core.input.CoreSelectInputDateTag __jsp_taghandler_13=(oracle.adfinternal.view.faces.taglib.core.input.CoreSelectInputDateTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.input.CoreSelectInputDateTag.class,"oracle.adfinternal.view.faces.taglib.core.input.CoreSelectInputDateTag id label validator value");
                                              __jsp_taghandler_13.setParent(__jsp_taghandler_11);
                                              __jsp_taghandler_13.setId("sitFechaDesde");
                                              __jsp_taghandler_13.setLabel("#{msgs['bicevida.extraccion_nominas.lbl.fechadesde'] }");
                                              __jsp_taghandler_13.setValidator("#{mb_extraccionNomina.validarFechas}");
                                              __jsp_taghandler_13.setValue("#{mb_extraccionNomina.fechaDesde}");
                                              __jsp_tag_starteval=__jsp_taghandler_13.doStartTag();
                                              if (__jsp_taghandler_13.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                return;
                                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_13,10);
                                            }
                                            {
                                              oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag __jsp_taghandler_14=(oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag.class,"oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag unselectedLabel label value");
                                              __jsp_taghandler_14.setParent(__jsp_taghandler_11);
                                              __jsp_taghandler_14.setUnselectedLabel("#{msgs['bicevida.generales.opt.seleccione'] }");
                                              __jsp_taghandler_14.setLabel("#{msgs['bicevida.extraccion_nominas.lbl.tiponomina'] }");
                                              __jsp_taghandler_14.setValue("#{mb_extraccionNomina.tipoNominaSeleccionada}");
                                              __jsp_tag_starteval=__jsp_taghandler_14.doStartTag();
                                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                              {
                                                {
                                                  com.sun.faces.taglib.jsf_core.SelectItemsTag __jsp_taghandler_15=(com.sun.faces.taglib.jsf_core.SelectItemsTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.jsf_core.SelectItemsTag.class,"com.sun.faces.taglib.jsf_core.SelectItemsTag id value");
                                                  __jsp_taghandler_15.setParent(__jsp_taghandler_14);
                                                  __jsp_taghandler_15.setId("sitTiposNomina");
                                                  __jsp_taghandler_15.setValue("#{mb_extraccionNomina.tiposNomina}");
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
                                              oracle.adfinternal.view.faces.taglib.core.input.CoreSelectInputDateTag __jsp_taghandler_16=(oracle.adfinternal.view.faces.taglib.core.input.CoreSelectInputDateTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.input.CoreSelectInputDateTag.class,"oracle.adfinternal.view.faces.taglib.core.input.CoreSelectInputDateTag id label validator value");
                                              __jsp_taghandler_16.setParent(__jsp_taghandler_11);
                                              __jsp_taghandler_16.setId("sitFechaHasta");
                                              __jsp_taghandler_16.setLabel("#{msgs['bicevida.extraccion_nominas.lbl.fechahasta'] }");
                                              __jsp_taghandler_16.setValidator("#{mb_extraccionNomina.validarFechas}");
                                              __jsp_taghandler_16.setValue("#{mb_extraccionNomina.fechaHasta}");
                                              __jsp_tag_starteval=__jsp_taghandler_16.doStartTag();
                                              if (__jsp_taghandler_16.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                return;
                                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_16,10);
                                            }
                                            {
                                              oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag __jsp_taghandler_17=(oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag.class,"oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag unselectedLabel label value");
                                              __jsp_taghandler_17.setParent(__jsp_taghandler_11);
                                              __jsp_taghandler_17.setUnselectedLabel("#{msgs['bicevida.generales.opt.seleccione'] }");
                                              __jsp_taghandler_17.setLabel("#{msgs['bicevida.extraccion_nominas.lbl.bancoproceso'] }");
                                              __jsp_taghandler_17.setValue("#{mb_extraccionNomina.bancoProcesoSeleccionado}");
                                              __jsp_tag_starteval=__jsp_taghandler_17.doStartTag();
                                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                              {
                                                {
                                                  com.sun.faces.taglib.jsf_core.SelectItemsTag __jsp_taghandler_18=(com.sun.faces.taglib.jsf_core.SelectItemsTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.jsf_core.SelectItemsTag.class,"com.sun.faces.taglib.jsf_core.SelectItemsTag id value");
                                                  __jsp_taghandler_18.setParent(__jsp_taghandler_17);
                                                  __jsp_taghandler_18.setId("sitBancoPago");
                                                  __jsp_taghandler_18.setValue("#{mb_extraccionNomina.bancosProceso}");
                                                  __jsp_tag_starteval=__jsp_taghandler_18.doStartTag();
                                                  if (__jsp_taghandler_18.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                    return;
                                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_18,11);
                                                }
                                              }
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
                                  oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag __jsp_taghandler_21=(oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag text blocking actionListener");
                                  __jsp_taghandler_21.setParent(__jsp_taghandler_20);
                                  __jsp_taghandler_21.setText("#{msgs['bicevida.generales.btn.buscar'] }");
                                  __jsp_taghandler_21.setBlocking("true");
                                  __jsp_taghandler_21.setActionListener("#{mb_extraccionNomina.ejecutarBusqueda}");
                                  __jsp_tag_starteval=__jsp_taghandler_21.doStartTag();
                                  if (__jsp_taghandler_21.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_21,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag __jsp_taghandler_22=(oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag text blocking action immediate");
                                  __jsp_taghandler_22.setParent(__jsp_taghandler_20);
                                  __jsp_taghandler_22.setText("#{msgs['bicevida.generales.btn.limpiar'] }");
                                  __jsp_taghandler_22.setBlocking("true");
                                  __jsp_taghandler_22.setAction("#{mb_extraccionNomina.limpiarFormulario}");
                                  __jsp_taghandler_22.setImmediate("true");
                                  __jsp_tag_starteval=__jsp_taghandler_22.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.listener.ResetActionListenerTag __jsp_taghandler_23=(oracle.adfinternal.view.faces.taglib.listener.ResetActionListenerTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.listener.ResetActionListenerTag.class,"oracle.adfinternal.view.faces.taglib.listener.ResetActionListenerTag");
                                      __jsp_taghandler_23.setParent(__jsp_taghandler_22);
                                      __jsp_tag_starteval=__jsp_taghandler_23.doStartTag();
                                      if (__jsp_taghandler_23.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_23,8);
                                    }
                                  }
                                  if (__jsp_taghandler_22.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_22,7);
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
                              oracle.adfinternal.view.faces.taglib.core.data.CoreTableTag __jsp_taghandler_25=(oracle.adfinternal.view.faces.taglib.core.data.CoreTableTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreTableTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreTableTag id emptyText banding var value rows binding");
                              __jsp_taghandler_25.setParent(__jsp_taghandler_7);
                              __jsp_taghandler_25.setId("tblNominas");
                              __jsp_taghandler_25.setEmptyText("#{msgs['bicevida.generales.lbl.listavacia'] }");
                              __jsp_taghandler_25.setBanding("row");
                              __jsp_taghandler_25.setVar("fila");
                              __jsp_taghandler_25.setValue("#{mb_extraccionNomina.lista}");
                              __jsp_taghandler_25.setRows("#{mb_extraccionNomina.registrosPagina}");
                              __jsp_taghandler_25.setBinding("#{mb_extraccionNomina.tblNominas}");
                              __jsp_tag_starteval=__jsp_taghandler_25.doStartTag();
                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                              {
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_26=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType width headerText inlineStyle");
                                  __jsp_taghandler_26.setParent(__jsp_taghandler_25);
                                  __jsp_taghandler_26.setFormatType("text");
                                  __jsp_taghandler_26.setWidth("141");
                                  __jsp_taghandler_26.setHeaderText("#{msgs['bicevida.extraccion_nominas.col.numnomina']}");
                                  __jsp_taghandler_26.setInlineStyle("text-align:right;");
                                  __jsp_tag_starteval=__jsp_taghandler_26.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_27=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_27.setParent(__jsp_taghandler_26);
                                      __jsp_taghandler_27.setValue("#{fila.id}");
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
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_28=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType width headerText inlineStyle");
                                  __jsp_taghandler_28.setParent(__jsp_taghandler_25);
                                  __jsp_taghandler_28.setFormatType("text");
                                  __jsp_taghandler_28.setWidth("141");
                                  __jsp_taghandler_28.setHeaderText("#{msgs['bicevida.extraccion_nominas.col.lotenomina']}");
                                  __jsp_taghandler_28.setInlineStyle("text-align:right;");
                                  __jsp_tag_starteval=__jsp_taghandler_28.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_29=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_29.setParent(__jsp_taghandler_28);
                                      __jsp_taghandler_29.setValue("#{fila.lote}");
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
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_30=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType width headerText");
                                  __jsp_taghandler_30.setParent(__jsp_taghandler_25);
                                  __jsp_taghandler_30.setFormatType("text");
                                  __jsp_taghandler_30.setWidth("150");
                                  __jsp_taghandler_30.setHeaderText("#{msgs['bicevida.extraccion_nominas.col.tiponomina']}");
                                  __jsp_tag_starteval=__jsp_taghandler_30.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_31=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_31.setParent(__jsp_taghandler_30);
                                      __jsp_taghandler_31.setValue("#{fila.tipo.nombre}");
                                      __jsp_tag_starteval=__jsp_taghandler_31.doStartTag();
                                      if (__jsp_taghandler_31.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_31,8);
                                    }
                                  }
                                  if (__jsp_taghandler_30.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_30,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_32=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText");
                                  __jsp_taghandler_32.setParent(__jsp_taghandler_25);
                                  __jsp_taghandler_32.setFormatType("text");
                                  __jsp_taghandler_32.setHeaderText("#{msgs['bicevida.extraccion_nominas.col.estadonomina']}");
                                  __jsp_tag_starteval=__jsp_taghandler_32.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_33=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_33.setParent(__jsp_taghandler_32);
                                      __jsp_taghandler_33.setValue("#{fila.estado.nombre}");
                                      __jsp_tag_starteval=__jsp_taghandler_33.doStartTag();
                                      if (__jsp_taghandler_33.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_33,8);
                                    }
                                  }
                                  if (__jsp_taghandler_32.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_32,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_34=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType width headerText");
                                  __jsp_taghandler_34.setParent(__jsp_taghandler_25);
                                  __jsp_taghandler_34.setFormatType("text");
                                  __jsp_taghandler_34.setWidth("141");
                                  __jsp_taghandler_34.setHeaderText("#{msgs['bicevida.extraccion_nominas.col.banco']}");
                                  __jsp_tag_starteval=__jsp_taghandler_34.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_35=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_35.setParent(__jsp_taghandler_34);
                                      __jsp_taghandler_35.setValue("#{fila.bancoProceso}");
                                      __jsp_tag_starteval=__jsp_taghandler_35.doStartTag();
                                      if (__jsp_taghandler_35.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_35,8);
                                    }
                                  }
                                  if (__jsp_taghandler_34.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_34,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_36=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType width headerText");
                                  __jsp_taghandler_36.setParent(__jsp_taghandler_25);
                                  __jsp_taghandler_36.setFormatType("text");
                                  __jsp_taghandler_36.setWidth("141");
                                  __jsp_taghandler_36.setHeaderText("#{msgs['bicevida.extraccion_nominas.col.ingreso']}");
                                  __jsp_tag_starteval=__jsp_taghandler_36.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_37=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_37.setParent(__jsp_taghandler_36);
                                      __jsp_taghandler_37.setValue("#{fila.fechaInicioProceso}");
                                      __jsp_tag_starteval=__jsp_taghandler_37.doStartTag();
                                      if (__jsp_taghandler_37.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_37,8);
                                    }
                                  }
                                  if (__jsp_taghandler_36.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_36,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_38=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType width headerText inlineStyle");
                                  __jsp_taghandler_38.setParent(__jsp_taghandler_25);
                                  __jsp_taghandler_38.setFormatType("text");
                                  __jsp_taghandler_38.setWidth("141");
                                  __jsp_taghandler_38.setHeaderText("#{msgs['bicevida.extraccion_nominas.col.totalregistros']}");
                                  __jsp_taghandler_38.setInlineStyle("text-align:right;");
                                  __jsp_tag_starteval=__jsp_taghandler_38.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_39=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_39.setParent(__jsp_taghandler_38);
                                      __jsp_taghandler_39.setValue("#{fila.totalRegistros}");
                                      __jsp_tag_starteval=__jsp_taghandler_39.doStartTag();
                                      if (__jsp_taghandler_39.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_39,8);
                                    }
                                  }
                                  if (__jsp_taghandler_38.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_38,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_40=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType width headerText inlineStyle");
                                  __jsp_taghandler_40.setParent(__jsp_taghandler_25);
                                  __jsp_taghandler_40.setFormatType("text");
                                  __jsp_taghandler_40.setWidth("141");
                                  __jsp_taghandler_40.setHeaderText("#{msgs['bicevida.extraccion_nominas.col.totalmonto']}");
                                  __jsp_taghandler_40.setInlineStyle("text-align:right;");
                                  __jsp_tag_starteval=__jsp_taghandler_40.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_41=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_41.setParent(__jsp_taghandler_40);
                                      __jsp_taghandler_41.setValue("#{fila.montoTotal}");
                                      __jsp_tag_starteval=__jsp_taghandler_41.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag __jsp_taghandler_42=(oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag.class,"oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag groupingUsed");
                                          __jsp_taghandler_42.setParent(__jsp_taghandler_41);
                                          __jsp_taghandler_42.setGroupingUsed("true");
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
                                  if (__jsp_taghandler_40.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_40,7);
                                }
                                {
                                  javax.faces.webapp.FacetTag __jsp_taghandler_43=(javax.faces.webapp.FacetTag)OracleJspRuntime.getTagHandler(pageContext,javax.faces.webapp.FacetTag.class,"javax.faces.webapp.FacetTag name");
                                  __jsp_taghandler_43.setParent(__jsp_taghandler_25);
                                  __jsp_taghandler_43.setName("selection");
                                  __jsp_tag_starteval=__jsp_taghandler_43.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    do {
                                      {
                                        oracle.adfinternal.view.faces.taglib.core.data.CoreTableSelectOneTag __jsp_taghandler_44=(oracle.adfinternal.view.faces.taglib.core.data.CoreTableSelectOneTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreTableSelectOneTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreTableSelectOneTag");
                                        __jsp_taghandler_44.setParent(__jsp_taghandler_43);
                                        __jsp_tag_starteval=__jsp_taghandler_44.doStartTag();
                                        if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                        {
                                          {
                                            oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag __jsp_taghandler_45=(oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag id text partialSubmit useWindow windowWidth windowHeight action returnListener launchListener");
                                            __jsp_taghandler_45.setParent(__jsp_taghandler_44);
                                            __jsp_taghandler_45.setId("btnExtraerNomina");
                                            __jsp_taghandler_45.setText("#{msgs['bicevida.extraccion_nominas.btn.extraer']}");
                                            __jsp_taghandler_45.setPartialSubmit("true");
                                            __jsp_taghandler_45.setUseWindow("true");
                                            __jsp_taghandler_45.setWindowWidth("520");
                                            __jsp_taghandler_45.setWindowHeight("220");
                                            __jsp_taghandler_45.setAction("dialog:PopupExtraerNomina");
                                            __jsp_taghandler_45.setReturnListener("#{mb_extraccionNomina.returnListener}");
                                            __jsp_taghandler_45.setLaunchListener("#{mb_extraccionNomina.launchListener}");
                                            __jsp_tag_starteval=__jsp_taghandler_45.doStartTag();
                                            if (__jsp_taghandler_45.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                              return;
                                            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_45,9);
                                          }
                                          {
                                            oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag __jsp_taghandler_46=(oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag text blocking action");
                                            __jsp_taghandler_46.setParent(__jsp_taghandler_44);
                                            __jsp_taghandler_46.setText("#{msgs['bicevida.generales.btn.detalle']}");
                                            __jsp_taghandler_46.setBlocking("true");
                                            __jsp_taghandler_46.setAction("#{mb_extraccionNomina.onClickVerDetalle}");
                                            __jsp_tag_starteval=__jsp_taghandler_46.doStartTag();
                                            if (__jsp_taghandler_46.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                              return;
                                            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_46,9);
                                          }
                                        }
                                        if (__jsp_taghandler_44.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                          return;
                                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_44,8);
                                      }
                                    } while (__jsp_taghandler_43.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
                                  }
                                  if (__jsp_taghandler_43.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_43,7);
                                }
                                {
                                  javax.faces.webapp.FacetTag __jsp_taghandler_47=(javax.faces.webapp.FacetTag)OracleJspRuntime.getTagHandler(pageContext,javax.faces.webapp.FacetTag.class,"javax.faces.webapp.FacetTag name");
                                  __jsp_taghandler_47.setParent(__jsp_taghandler_25);
                                  __jsp_taghandler_47.setName("actions");
                                  __jsp_tag_starteval=__jsp_taghandler_47.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    do {
                                      {
                                        oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag __jsp_taghandler_48=(oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag text blocking actionListener");
                                        __jsp_taghandler_48.setParent(__jsp_taghandler_47);
                                        __jsp_taghandler_48.setText("#{msgs['bicevida.generales.btn.descargaxls']}");
                                        __jsp_taghandler_48.setBlocking("true");
                                        __jsp_taghandler_48.setActionListener("#{MbExcelVisor.onClickGenerarExcel}");
                                        __jsp_tag_starteval=__jsp_taghandler_48.doStartTag();
                                        if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                        {
                                          {
                                            javax.faces.webapp.AttributeTag __jsp_taghandler_49=(javax.faces.webapp.AttributeTag)OracleJspRuntime.getTagHandler(pageContext,javax.faces.webapp.AttributeTag.class,"javax.faces.webapp.AttributeTag name value");
                                            __jsp_taghandler_49.setParent(__jsp_taghandler_48);
                                            __jsp_taghandler_49.setName("nombre");
                                            __jsp_taghandler_49.setValue("extraccion_nomina");
                                            __jsp_tag_starteval=__jsp_taghandler_49.doStartTag();
                                            if (__jsp_taghandler_49.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                              return;
                                            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_49,9);
                                          }
                                          {
                                            javax.faces.webapp.AttributeTag __jsp_taghandler_50=(javax.faces.webapp.AttributeTag)OracleJspRuntime.getTagHandler(pageContext,javax.faces.webapp.AttributeTag.class,"javax.faces.webapp.AttributeTag name value");
                                            __jsp_taghandler_50.setParent(__jsp_taghandler_48);
                                            __jsp_taghandler_50.setName("titulo");
                                            __jsp_taghandler_50.setValue("Número Nómina:Lote:Tipo Nómina:Estado Nómina:Banco Proceso:Ingreso:Registros:Monto($)");
                                            __jsp_tag_starteval=__jsp_taghandler_50.doStartTag();
                                            if (__jsp_taghandler_50.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                              return;
                                            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_50,9);
                                          }
                                        }
                                        if (__jsp_taghandler_48.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                          return;
                                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_48,8);
                                      }
                                    } while (__jsp_taghandler_47.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
                                  }
                                  if (__jsp_taghandler_47.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_47,7);
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
