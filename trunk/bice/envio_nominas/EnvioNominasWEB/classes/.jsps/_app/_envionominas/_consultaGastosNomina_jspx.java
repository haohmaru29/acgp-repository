package _app._envionominas;

import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import oracle.jsp.el.*;
import javax.servlet.jsp.el.*;


public class _consultaGastosNomina_jspx extends com.orionserver.http.OrionHttpJspPage {


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
    _consultaGastosNomina_jspx page = this;
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
                  __jsp_taghandler_4.setTitle("#{msgs['bicevida.gastos_nominas.titulo'] }");
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
                          oracle.adfinternal.view.faces.taglib.core.input.CoreInputHiddenTag __jsp_taghandler_7=(oracle.adfinternal.view.faces.taglib.core.input.CoreInputHiddenTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.input.CoreInputHiddenTag.class,"oracle.adfinternal.view.faces.taglib.core.input.CoreInputHiddenTag id value");
                          __jsp_taghandler_7.setParent(__jsp_taghandler_6);
                          __jsp_taghandler_7.setId("titulo");
                          __jsp_taghandler_7.setValue("titulooo");
                          __jsp_tag_starteval=__jsp_taghandler_7.doStartTag();
                          if (__jsp_taghandler_7.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                            return;
                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_7,5);
                        }
                        {
                          oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag __jsp_taghandler_8=(oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag.class,"oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag width background text icon");
                          __jsp_taghandler_8.setParent(__jsp_taghandler_6);
                          __jsp_taghandler_8.setWidth("770px");
                          __jsp_taghandler_8.setBackground("medium");
                          __jsp_taghandler_8.setText("#{msgs['bicevida.gastos_nominas.titulo'] }");
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
                                          oracle.adfinternal.view.faces.taglib.core.layout.CorePanelFormTag __jsp_taghandler_12=(oracle.adfinternal.view.faces.taglib.core.layout.CorePanelFormTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.layout.CorePanelFormTag.class,"oracle.adfinternal.view.faces.taglib.core.layout.CorePanelFormTag rows");
                                          __jsp_taghandler_12.setParent(__jsp_taghandler_11);
                                          __jsp_taghandler_12.setRows("3");
                                          __jsp_tag_starteval=__jsp_taghandler_12.doStartTag();
                                          if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                          {
                                            {
                                              oracle.adfinternal.view.faces.taglib.core.input.CoreInputTextTag __jsp_taghandler_13=(oracle.adfinternal.view.faces.taglib.core.input.CoreInputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.input.CoreInputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.input.CoreInputTextTag label maximumLength value");
                                              __jsp_taghandler_13.setParent(__jsp_taghandler_12);
                                              __jsp_taghandler_13.setLabel("#{msgs['bicevida.gastos_nominas.lbl.lote'] }");
                                              __jsp_taghandler_13.setMaximumLength("200");
                                              __jsp_taghandler_13.setValue("#{mb_consultaGastosNomina.loteNomina}");
                                              __jsp_tag_starteval=__jsp_taghandler_13.doStartTag();
                                              if (__jsp_taghandler_13.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                return;
                                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_13,10);
                                            }
                                            {
                                              oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag __jsp_taghandler_14=(oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag.class,"oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag unselectedLabel label value");
                                              __jsp_taghandler_14.setParent(__jsp_taghandler_12);
                                              __jsp_taghandler_14.setUnselectedLabel("#{msgs['bicevida.generales.opt.seleccione'] }");
                                              __jsp_taghandler_14.setLabel("#{msgs['bicevida.gastos_nominas.lbl.tiponomina'] }");
                                              __jsp_taghandler_14.setValue("#{mb_consultaGastosNomina.tipoNominaSeleccionada}");
                                              __jsp_tag_starteval=__jsp_taghandler_14.doStartTag();
                                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                              {
                                                {
                                                  com.sun.faces.taglib.jsf_core.SelectItemsTag __jsp_taghandler_15=(com.sun.faces.taglib.jsf_core.SelectItemsTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.jsf_core.SelectItemsTag.class,"com.sun.faces.taglib.jsf_core.SelectItemsTag id value");
                                                  __jsp_taghandler_15.setParent(__jsp_taghandler_14);
                                                  __jsp_taghandler_15.setId("sitTiposNomina");
                                                  __jsp_taghandler_15.setValue("#{mb_consultaGastosNomina.tiposNomina}");
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
                                              __jsp_taghandler_16.setParent(__jsp_taghandler_12);
                                              __jsp_taghandler_16.setId("sitFechaDesde");
                                              __jsp_taghandler_16.setLabel("#{msgs['bicevida.gastos_nominas.lbl.fechadesde'] }");
                                              __jsp_taghandler_16.setValidator("#{mb_consultaGastosNomina.validarFechas}");
                                              __jsp_taghandler_16.setValue("#{mb_consultaGastosNomina.fechaDesde}");
                                              __jsp_tag_starteval=__jsp_taghandler_16.doStartTag();
                                              if (__jsp_taghandler_16.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                return;
                                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_16,10);
                                            }
                                            {
                                              oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag __jsp_taghandler_17=(oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag.class,"oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag unselectedLabel label value");
                                              __jsp_taghandler_17.setParent(__jsp_taghandler_12);
                                              __jsp_taghandler_17.setUnselectedLabel("#{msgs['bicevida.generales.opt.seleccione'] }");
                                              __jsp_taghandler_17.setLabel("#{msgs['bicevida.gastos_nominas.lbl.bancopago'] }");
                                              __jsp_taghandler_17.setValue("#{mb_consultaGastosNomina.bancoPagoSeleccionado}");
                                              __jsp_tag_starteval=__jsp_taghandler_17.doStartTag();
                                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                              {
                                                {
                                                  com.sun.faces.taglib.jsf_core.SelectItemsTag __jsp_taghandler_18=(com.sun.faces.taglib.jsf_core.SelectItemsTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.jsf_core.SelectItemsTag.class,"com.sun.faces.taglib.jsf_core.SelectItemsTag id value");
                                                  __jsp_taghandler_18.setParent(__jsp_taghandler_17);
                                                  __jsp_taghandler_18.setId("sitBancoPago");
                                                  __jsp_taghandler_18.setValue("#{mb_consultaGastosNomina.bancosPago}");
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
                                            {
                                              oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag __jsp_taghandler_19=(oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag.class,"oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag unselectedLabel label value");
                                              __jsp_taghandler_19.setParent(__jsp_taghandler_12);
                                              __jsp_taghandler_19.setUnselectedLabel("#{msgs['bicevida.generales.opt.seleccione'] }");
                                              __jsp_taghandler_19.setLabel("#{msgs['bicevida.gastos_nominas.lbl.bancoproceso'] }");
                                              __jsp_taghandler_19.setValue("#{mb_consultaGastosNomina.bancoProcesoSeleccionado}");
                                              __jsp_tag_starteval=__jsp_taghandler_19.doStartTag();
                                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                              {
                                                {
                                                  com.sun.faces.taglib.jsf_core.SelectItemsTag __jsp_taghandler_20=(com.sun.faces.taglib.jsf_core.SelectItemsTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.jsf_core.SelectItemsTag.class,"com.sun.faces.taglib.jsf_core.SelectItemsTag id value");
                                                  __jsp_taghandler_20.setParent(__jsp_taghandler_19);
                                                  __jsp_taghandler_20.setId("sitBancoProceso");
                                                  __jsp_taghandler_20.setValue("#{mb_consultaGastosNomina.bancosProceso}");
                                                  __jsp_tag_starteval=__jsp_taghandler_20.doStartTag();
                                                  if (__jsp_taghandler_20.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                    return;
                                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_20,11);
                                                }
                                              }
                                              if (__jsp_taghandler_19.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                return;
                                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_19,10);
                                            }
                                            {
                                              oracle.adfinternal.view.faces.taglib.core.input.CoreSelectInputDateTag __jsp_taghandler_21=(oracle.adfinternal.view.faces.taglib.core.input.CoreSelectInputDateTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.input.CoreSelectInputDateTag.class,"oracle.adfinternal.view.faces.taglib.core.input.CoreSelectInputDateTag id label validator value");
                                              __jsp_taghandler_21.setParent(__jsp_taghandler_12);
                                              __jsp_taghandler_21.setId("sitFechaHasta");
                                              __jsp_taghandler_21.setLabel("#{msgs['bicevida.gastos_nominas.lbl.fechahasta'] }");
                                              __jsp_taghandler_21.setValidator("#{mb_consultaGastosNomina.validarFechas}");
                                              __jsp_taghandler_21.setValue("#{mb_consultaGastosNomina.fechaHasta}");
                                              __jsp_tag_starteval=__jsp_taghandler_21.doStartTag();
                                              if (__jsp_taghandler_21.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                return;
                                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_21,10);
                                            }
                                          }
                                          if (__jsp_taghandler_12.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_12,9);
                                        }
                                      }
                                      if (__jsp_taghandler_11.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_11,8);
                                    }
                                  }
                                  if (__jsp_taghandler_10.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_10,7);
                                }
                              }
                              if (__jsp_taghandler_9.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_9,6);
                            }
                            {
                              oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag __jsp_taghandler_22=(oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag width height");
                              __jsp_taghandler_22.setParent(__jsp_taghandler_8);
                              __jsp_taghandler_22.setWidth("10");
                              __jsp_taghandler_22.setHeight("10");
                              __jsp_tag_starteval=__jsp_taghandler_22.doStartTag();
                              if (__jsp_taghandler_22.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_22,6);
                            }
                            {
                              oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag __jsp_taghandler_23=(oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag.class,"oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag halign");
                              __jsp_taghandler_23.setParent(__jsp_taghandler_8);
                              __jsp_taghandler_23.setHalign("center");
                              __jsp_tag_starteval=__jsp_taghandler_23.doStartTag();
                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                              {
                                {
                                  oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag __jsp_taghandler_24=(oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag text blocking actionListener");
                                  __jsp_taghandler_24.setParent(__jsp_taghandler_23);
                                  __jsp_taghandler_24.setText("#{msgs['bicevida.generales.btn.buscar'] }");
                                  __jsp_taghandler_24.setBlocking("true");
                                  __jsp_taghandler_24.setActionListener("#{mb_consultaGastosNomina.ejecutarBusqueda}");
                                  __jsp_tag_starteval=__jsp_taghandler_24.doStartTag();
                                  if (__jsp_taghandler_24.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_24,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag __jsp_taghandler_25=(oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag text blocking action immediate");
                                  __jsp_taghandler_25.setParent(__jsp_taghandler_23);
                                  __jsp_taghandler_25.setText("#{msgs['bicevida.generales.btn.limpiar'] }");
                                  __jsp_taghandler_25.setBlocking("true");
                                  __jsp_taghandler_25.setAction("#{mb_consultaGastosNomina.limpiarFormulario}");
                                  __jsp_taghandler_25.setImmediate("true");
                                  __jsp_tag_starteval=__jsp_taghandler_25.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.listener.ResetActionListenerTag __jsp_taghandler_26=(oracle.adfinternal.view.faces.taglib.listener.ResetActionListenerTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.listener.ResetActionListenerTag.class,"oracle.adfinternal.view.faces.taglib.listener.ResetActionListenerTag");
                                      __jsp_taghandler_26.setParent(__jsp_taghandler_25);
                                      __jsp_tag_starteval=__jsp_taghandler_26.doStartTag();
                                      if (__jsp_taghandler_26.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_26,8);
                                    }
                                  }
                                  if (__jsp_taghandler_25.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_25,7);
                                }
                              }
                              if (__jsp_taghandler_23.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_23,6);
                            }
                            {
                              oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag __jsp_taghandler_27=(oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag width height");
                              __jsp_taghandler_27.setParent(__jsp_taghandler_8);
                              __jsp_taghandler_27.setWidth("10");
                              __jsp_taghandler_27.setHeight("10");
                              __jsp_tag_starteval=__jsp_taghandler_27.doStartTag();
                              if (__jsp_taghandler_27.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_27,6);
                            }
                            {
                              oracle.adfinternal.view.faces.taglib.core.data.CoreTableTag __jsp_taghandler_28=(oracle.adfinternal.view.faces.taglib.core.data.CoreTableTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreTableTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreTableTag id width emptyText banding bandingInterval var value rows");
                              __jsp_taghandler_28.setParent(__jsp_taghandler_8);
                              __jsp_taghandler_28.setId("tblNominas");
                              __jsp_taghandler_28.setWidth("100%");
                              __jsp_taghandler_28.setEmptyText("#{msgs['bicevida.generales.lbl.listavacia'] }");
                              __jsp_taghandler_28.setBanding("row");
                              __jsp_taghandler_28.setBandingInterval("1");
                              __jsp_taghandler_28.setVar("fila");
                              __jsp_taghandler_28.setValue("#{mb_consultaGastosNomina.lista}");
                              __jsp_taghandler_28.setRows("#{mb_consultaGastosNomina.registrosPagina}");
                              __jsp_tag_starteval=__jsp_taghandler_28.doStartTag();
                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                              {
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_29=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText inlineStyle");
                                  __jsp_taghandler_29.setParent(__jsp_taghandler_28);
                                  __jsp_taghandler_29.setFormatType("text");
                                  __jsp_taghandler_29.setHeaderText("#{msgs['bicevida.gastos_nominas.col.numnomina']}");
                                  __jsp_taghandler_29.setInlineStyle("text-align:right;");
                                  __jsp_tag_starteval=__jsp_taghandler_29.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_30=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_30.setParent(__jsp_taghandler_29);
                                      __jsp_taghandler_30.setValue("#{fila.nomina.id}");
                                      __jsp_tag_starteval=__jsp_taghandler_30.doStartTag();
                                      if (__jsp_taghandler_30.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_30,8);
                                    }
                                  }
                                  if (__jsp_taghandler_29.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_29,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_31=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText inlineStyle");
                                  __jsp_taghandler_31.setParent(__jsp_taghandler_28);
                                  __jsp_taghandler_31.setFormatType("text");
                                  __jsp_taghandler_31.setHeaderText("#{msgs['bicevida.gastos_nominas.col.tiponomina']}");
                                  __jsp_taghandler_31.setInlineStyle("text-align:left;");
                                  __jsp_tag_starteval=__jsp_taghandler_31.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_32=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_32.setParent(__jsp_taghandler_31);
                                      __jsp_taghandler_32.setValue("#{fila.nomina.tipo.nombre}");
                                      __jsp_tag_starteval=__jsp_taghandler_32.doStartTag();
                                      if (__jsp_taghandler_32.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_32,8);
                                    }
                                  }
                                  if (__jsp_taghandler_31.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_31,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_33=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText inlineStyle");
                                  __jsp_taghandler_33.setParent(__jsp_taghandler_28);
                                  __jsp_taghandler_33.setFormatType("text");
                                  __jsp_taghandler_33.setHeaderText("#{msgs['bicevida.gastos_nominas.col.tpregistros']}");
                                  __jsp_taghandler_33.setInlineStyle("text-align:right;");
                                  __jsp_tag_starteval=__jsp_taghandler_33.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_34=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_34.setParent(__jsp_taghandler_33);
                                      __jsp_taghandler_34.setValue("#{fila.cantidadProcesados}");
                                      __jsp_tag_starteval=__jsp_taghandler_34.doStartTag();
                                      if (__jsp_taghandler_34.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_34,8);
                                    }
                                  }
                                  if (__jsp_taghandler_33.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_33,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_35=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText inlineStyle");
                                  __jsp_taghandler_35.setParent(__jsp_taghandler_28);
                                  __jsp_taghandler_35.setFormatType("text");
                                  __jsp_taghandler_35.setHeaderText("#{msgs['bicevida.gastos_nominas.col.tpmonto']}");
                                  __jsp_taghandler_35.setInlineStyle("text-align:right;");
                                  __jsp_tag_starteval=__jsp_taghandler_35.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_36=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_36.setParent(__jsp_taghandler_35);
                                      __jsp_taghandler_36.setValue("#{fila.montoTotalProcesados}");
                                      __jsp_tag_starteval=__jsp_taghandler_36.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag __jsp_taghandler_37=(oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag.class,"oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag groupingUsed");
                                          __jsp_taghandler_37.setParent(__jsp_taghandler_36);
                                          __jsp_taghandler_37.setGroupingUsed("true");
                                          __jsp_tag_starteval=__jsp_taghandler_37.doStartTag();
                                          if (__jsp_taghandler_37.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_37,9);
                                        }
                                      }
                                      if (__jsp_taghandler_36.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_36,8);
                                    }
                                  }
                                  if (__jsp_taghandler_35.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_35,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_38=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText inlineStyle");
                                  __jsp_taghandler_38.setParent(__jsp_taghandler_28);
                                  __jsp_taghandler_38.setFormatType("text");
                                  __jsp_taghandler_38.setHeaderText("#{msgs['bicevida.gastos_nominas.col.trregistros']}");
                                  __jsp_taghandler_38.setInlineStyle("text-align:right;");
                                  __jsp_tag_starteval=__jsp_taghandler_38.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_39=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_39.setParent(__jsp_taghandler_38);
                                      __jsp_taghandler_39.setValue("#{fila.cantidadRechazados}");
                                      __jsp_tag_starteval=__jsp_taghandler_39.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag __jsp_taghandler_40=(oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag.class,"oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag groupingUsed");
                                          __jsp_taghandler_40.setParent(__jsp_taghandler_39);
                                          __jsp_taghandler_40.setGroupingUsed("true");
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
                                  }
                                  if (__jsp_taghandler_38.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_38,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_41=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText inlineStyle");
                                  __jsp_taghandler_41.setParent(__jsp_taghandler_28);
                                  __jsp_taghandler_41.setFormatType("text");
                                  __jsp_taghandler_41.setHeaderText("#{msgs['bicevida.gastos_nominas.col.trmonto']}");
                                  __jsp_taghandler_41.setInlineStyle("text-align:right;");
                                  __jsp_tag_starteval=__jsp_taghandler_41.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_42=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_42.setParent(__jsp_taghandler_41);
                                      __jsp_taghandler_42.setValue("#{fila.montoTotalRechazados}");
                                      __jsp_tag_starteval=__jsp_taghandler_42.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag __jsp_taghandler_43=(oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag.class,"oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag groupingUsed");
                                          __jsp_taghandler_43.setParent(__jsp_taghandler_42);
                                          __jsp_taghandler_43.setGroupingUsed("true");
                                          __jsp_tag_starteval=__jsp_taghandler_43.doStartTag();
                                          if (__jsp_taghandler_43.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_43,9);
                                        }
                                      }
                                      if (__jsp_taghandler_42.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_42,8);
                                    }
                                  }
                                  if (__jsp_taghandler_41.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_41,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_44=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText inlineStyle");
                                  __jsp_taghandler_44.setParent(__jsp_taghandler_28);
                                  __jsp_taghandler_44.setFormatType("text");
                                  __jsp_taghandler_44.setHeaderText("#{msgs['bicevida.gastos_nominas.col.tpcomision']}");
                                  __jsp_taghandler_44.setInlineStyle("text-align:right;");
                                  __jsp_tag_starteval=__jsp_taghandler_44.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_45=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_45.setParent(__jsp_taghandler_44);
                                      __jsp_taghandler_45.setValue("#{fila.totalComision}");
                                      __jsp_tag_starteval=__jsp_taghandler_45.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag __jsp_taghandler_46=(oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag.class,"oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag groupingUsed");
                                          __jsp_taghandler_46.setParent(__jsp_taghandler_45);
                                          __jsp_taghandler_46.setGroupingUsed("true");
                                          __jsp_tag_starteval=__jsp_taghandler_46.doStartTag();
                                          if (__jsp_taghandler_46.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_46,9);
                                        }
                                      }
                                      if (__jsp_taghandler_45.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_45,8);
                                    }
                                  }
                                  if (__jsp_taghandler_44.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_44,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_47=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText inlineStyle");
                                  __jsp_taghandler_47.setParent(__jsp_taghandler_28);
                                  __jsp_taghandler_47.setFormatType("text");
                                  __jsp_taghandler_47.setHeaderText("#{msgs['bicevida.gastos_nominas.col.tbpregistros']}");
                                  __jsp_taghandler_47.setInlineStyle("text-align:right;");
                                  __jsp_tag_starteval=__jsp_taghandler_47.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_48=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_48.setParent(__jsp_taghandler_47);
                                      __jsp_taghandler_48.setValue("#{fila.cantidadMismoBanco}");
                                      __jsp_tag_starteval=__jsp_taghandler_48.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag __jsp_taghandler_49=(oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag.class,"oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag groupingUsed");
                                          __jsp_taghandler_49.setParent(__jsp_taghandler_48);
                                          __jsp_taghandler_49.setGroupingUsed("true");
                                          __jsp_tag_starteval=__jsp_taghandler_49.doStartTag();
                                          if (__jsp_taghandler_49.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_49,9);
                                        }
                                      }
                                      if (__jsp_taghandler_48.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_48,8);
                                    }
                                  }
                                  if (__jsp_taghandler_47.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_47,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_50=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText inlineStyle");
                                  __jsp_taghandler_50.setParent(__jsp_taghandler_28);
                                  __jsp_taghandler_50.setFormatType("text");
                                  __jsp_taghandler_50.setHeaderText("#{msgs['bicevida.gastos_nominas.col.tbpmonto']}");
                                  __jsp_taghandler_50.setInlineStyle("text-align:right;");
                                  __jsp_tag_starteval=__jsp_taghandler_50.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_51=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_51.setParent(__jsp_taghandler_50);
                                      __jsp_taghandler_51.setValue("#{fila.montoTotalMismoBanco}");
                                      __jsp_tag_starteval=__jsp_taghandler_51.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag __jsp_taghandler_52=(oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag.class,"oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag groupingUsed");
                                          __jsp_taghandler_52.setParent(__jsp_taghandler_51);
                                          __jsp_taghandler_52.setGroupingUsed("true");
                                          __jsp_tag_starteval=__jsp_taghandler_52.doStartTag();
                                          if (__jsp_taghandler_52.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_52,9);
                                        }
                                      }
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
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_53=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText inlineStyle");
                                  __jsp_taghandler_53.setParent(__jsp_taghandler_28);
                                  __jsp_taghandler_53.setFormatType("text");
                                  __jsp_taghandler_53.setHeaderText("#{msgs['bicevida.gastos_nominas.col.tbpcomision']}");
                                  __jsp_taghandler_53.setInlineStyle("text-align:right;");
                                  __jsp_tag_starteval=__jsp_taghandler_53.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_54=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_54.setParent(__jsp_taghandler_53);
                                      __jsp_taghandler_54.setValue("#{fila.comisionMismoBanco}");
                                      __jsp_tag_starteval=__jsp_taghandler_54.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag __jsp_taghandler_55=(oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag.class,"oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag groupingUsed");
                                          __jsp_taghandler_55.setParent(__jsp_taghandler_54);
                                          __jsp_taghandler_55.setGroupingUsed("true");
                                          __jsp_tag_starteval=__jsp_taghandler_55.doStartTag();
                                          if (__jsp_taghandler_55.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_55,9);
                                        }
                                      }
                                      if (__jsp_taghandler_54.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_54,8);
                                    }
                                  }
                                  if (__jsp_taghandler_53.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_53,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_56=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText inlineStyle");
                                  __jsp_taghandler_56.setParent(__jsp_taghandler_28);
                                  __jsp_taghandler_56.setFormatType("text");
                                  __jsp_taghandler_56.setHeaderText("#{msgs['bicevida.gastos_nominas.col.tobregistros']}");
                                  __jsp_taghandler_56.setInlineStyle("text-align:right;");
                                  __jsp_tag_starteval=__jsp_taghandler_56.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_57=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_57.setParent(__jsp_taghandler_56);
                                      __jsp_taghandler_57.setValue("#{fila.cantidadOtrosBancos}");
                                      __jsp_tag_starteval=__jsp_taghandler_57.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag __jsp_taghandler_58=(oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag.class,"oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag groupingUsed");
                                          __jsp_taghandler_58.setParent(__jsp_taghandler_57);
                                          __jsp_taghandler_58.setGroupingUsed("true");
                                          __jsp_tag_starteval=__jsp_taghandler_58.doStartTag();
                                          if (__jsp_taghandler_58.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_58,9);
                                        }
                                      }
                                      if (__jsp_taghandler_57.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_57,8);
                                    }
                                  }
                                  if (__jsp_taghandler_56.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_56,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_59=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText inlineStyle");
                                  __jsp_taghandler_59.setParent(__jsp_taghandler_28);
                                  __jsp_taghandler_59.setFormatType("text");
                                  __jsp_taghandler_59.setHeaderText("#{msgs['bicevida.gastos_nominas.col.tobmonto']}");
                                  __jsp_taghandler_59.setInlineStyle("text-align:right;");
                                  __jsp_tag_starteval=__jsp_taghandler_59.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_60=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_60.setParent(__jsp_taghandler_59);
                                      __jsp_taghandler_60.setValue("#{fila.montoTotalOtrosBancos}");
                                      __jsp_tag_starteval=__jsp_taghandler_60.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag __jsp_taghandler_61=(oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag.class,"oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag groupingUsed");
                                          __jsp_taghandler_61.setParent(__jsp_taghandler_60);
                                          __jsp_taghandler_61.setGroupingUsed("true");
                                          __jsp_tag_starteval=__jsp_taghandler_61.doStartTag();
                                          if (__jsp_taghandler_61.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_61,9);
                                        }
                                      }
                                      if (__jsp_taghandler_60.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_60,8);
                                    }
                                  }
                                  if (__jsp_taghandler_59.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_59,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_62=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText inlineStyle");
                                  __jsp_taghandler_62.setParent(__jsp_taghandler_28);
                                  __jsp_taghandler_62.setFormatType("text");
                                  __jsp_taghandler_62.setHeaderText("#{msgs['bicevida.gastos_nominas.col.tobcomision']}");
                                  __jsp_taghandler_62.setInlineStyle("text-align:right;");
                                  __jsp_tag_starteval=__jsp_taghandler_62.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_63=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_63.setParent(__jsp_taghandler_62);
                                      __jsp_taghandler_63.setValue("#{fila.totalComisionOtrosBancos}");
                                      __jsp_tag_starteval=__jsp_taghandler_63.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag __jsp_taghandler_64=(oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag.class,"oracle.adfinternal.view.faces.taglib.convert.ConvertNumberTag groupingUsed");
                                          __jsp_taghandler_64.setParent(__jsp_taghandler_63);
                                          __jsp_taghandler_64.setGroupingUsed("true");
                                          __jsp_tag_starteval=__jsp_taghandler_64.doStartTag();
                                          if (__jsp_taghandler_64.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_64,9);
                                        }
                                      }
                                      if (__jsp_taghandler_63.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_63,8);
                                    }
                                  }
                                  if (__jsp_taghandler_62.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_62,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_65=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText inlineStyle");
                                  __jsp_taghandler_65.setParent(__jsp_taghandler_28);
                                  __jsp_taghandler_65.setFormatType("text");
                                  __jsp_taghandler_65.setHeaderText("#{msgs['bicevida.gastos_nominas.col.banco']}");
                                  __jsp_taghandler_65.setInlineStyle("text-align:left;");
                                  __jsp_tag_starteval=__jsp_taghandler_65.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_66=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_66.setParent(__jsp_taghandler_65);
                                      __jsp_taghandler_66.setValue("#{fila.nomina.bancoProceso}");
                                      __jsp_tag_starteval=__jsp_taghandler_66.doStartTag();
                                      if (__jsp_taghandler_66.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_66,8);
                                    }
                                  }
                                  if (__jsp_taghandler_65.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_65,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_67=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText inlineStyle");
                                  __jsp_taghandler_67.setParent(__jsp_taghandler_28);
                                  __jsp_taghandler_67.setFormatType("text");
                                  __jsp_taghandler_67.setHeaderText("#{msgs['bicevida.gastos_nominas.col.ingreso']}");
                                  __jsp_taghandler_67.setInlineStyle("text-align:right;");
                                  __jsp_tag_starteval=__jsp_taghandler_67.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_68=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_68.setParent(__jsp_taghandler_67);
                                      __jsp_taghandler_68.setValue("#{fila.fechaInicioProceso}");
                                      __jsp_tag_starteval=__jsp_taghandler_68.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          com.sun.faces.taglib.jsf_core.ConvertDateTimeTag __jsp_taghandler_69=(com.sun.faces.taglib.jsf_core.ConvertDateTimeTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.jsf_core.ConvertDateTimeTag.class,"com.sun.faces.taglib.jsf_core.ConvertDateTimeTag pattern");
                                          __jsp_taghandler_69.setParent(__jsp_taghandler_68);
                                          __jsp_taghandler_69.setPattern("dd-MM-yyyy");
                                          __jsp_tag_starteval=__jsp_taghandler_69.doStartTag();
                                          if (__jsp_taghandler_69.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_69,9);
                                        }
                                      }
                                      if (__jsp_taghandler_68.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_68,8);
                                    }
                                  }
                                  if (__jsp_taghandler_67.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_67,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_70=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText inlineStyle");
                                  __jsp_taghandler_70.setParent(__jsp_taghandler_28);
                                  __jsp_taghandler_70.setFormatType("text");
                                  __jsp_taghandler_70.setHeaderText("#{msgs['bicevida.gastos_nominas.col.fechaconciliacion']}");
                                  __jsp_taghandler_70.setInlineStyle("text-align:right;");
                                  __jsp_tag_starteval=__jsp_taghandler_70.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_71=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_71.setParent(__jsp_taghandler_70);
                                      __jsp_taghandler_71.setValue("#{fila.fechaConciliacionProceso}");
                                      __jsp_tag_starteval=__jsp_taghandler_71.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          com.sun.faces.taglib.jsf_core.ConvertDateTimeTag __jsp_taghandler_72=(com.sun.faces.taglib.jsf_core.ConvertDateTimeTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.jsf_core.ConvertDateTimeTag.class,"com.sun.faces.taglib.jsf_core.ConvertDateTimeTag pattern");
                                          __jsp_taghandler_72.setParent(__jsp_taghandler_71);
                                          __jsp_taghandler_72.setPattern("dd-MM-yyyy");
                                          __jsp_tag_starteval=__jsp_taghandler_72.doStartTag();
                                          if (__jsp_taghandler_72.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_72,9);
                                        }
                                      }
                                      if (__jsp_taghandler_71.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_71,8);
                                    }
                                  }
                                  if (__jsp_taghandler_70.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_70,7);
                                }
                                {
                                  javax.faces.webapp.FacetTag __jsp_taghandler_73=(javax.faces.webapp.FacetTag)OracleJspRuntime.getTagHandler(pageContext,javax.faces.webapp.FacetTag.class,"javax.faces.webapp.FacetTag name");
                                  __jsp_taghandler_73.setParent(__jsp_taghandler_28);
                                  __jsp_taghandler_73.setName("selection");
                                  __jsp_tag_starteval=__jsp_taghandler_73.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    do {
                                      {
                                        oracle.adfinternal.view.faces.taglib.core.data.CoreTableSelectOneTag __jsp_taghandler_74=(oracle.adfinternal.view.faces.taglib.core.data.CoreTableSelectOneTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreTableSelectOneTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreTableSelectOneTag");
                                        __jsp_taghandler_74.setParent(__jsp_taghandler_73);
                                        __jsp_tag_starteval=__jsp_taghandler_74.doStartTag();
                                        if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                        {
                                          {
                                            oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag __jsp_taghandler_75=(oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag text blocking action");
                                            __jsp_taghandler_75.setParent(__jsp_taghandler_74);
                                            __jsp_taghandler_75.setText("#{msgs['bicevida.generales.btn.detalle']}");
                                            __jsp_taghandler_75.setBlocking("true");
                                            __jsp_taghandler_75.setAction("#{mb_consultaGastosNomina.onClickVerDetalle}");
                                            __jsp_tag_starteval=__jsp_taghandler_75.doStartTag();
                                            if (__jsp_taghandler_75.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                              return;
                                            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_75,9);
                                          }
                                        }
                                        if (__jsp_taghandler_74.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                          return;
                                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_74,8);
                                      }
                                    } while (__jsp_taghandler_73.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
                                  }
                                  if (__jsp_taghandler_73.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_73,7);
                                }
                                {
                                  javax.faces.webapp.FacetTag __jsp_taghandler_76=(javax.faces.webapp.FacetTag)OracleJspRuntime.getTagHandler(pageContext,javax.faces.webapp.FacetTag.class,"javax.faces.webapp.FacetTag name");
                                  __jsp_taghandler_76.setParent(__jsp_taghandler_28);
                                  __jsp_taghandler_76.setName("actions");
                                  __jsp_tag_starteval=__jsp_taghandler_76.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    do {
                                      {
                                        oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag __jsp_taghandler_77=(oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag text blocking actionListener");
                                        __jsp_taghandler_77.setParent(__jsp_taghandler_76);
                                        __jsp_taghandler_77.setText("#{msgs['bicevida.generales.btn.descargaxls']}");
                                        __jsp_taghandler_77.setBlocking("true");
                                        __jsp_taghandler_77.setActionListener("#{MbExcelVisor.onClickGenerarExcel}");
                                        __jsp_tag_starteval=__jsp_taghandler_77.doStartTag();
                                        if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                        {
                                          {
                                            javax.faces.webapp.AttributeTag __jsp_taghandler_78=(javax.faces.webapp.AttributeTag)OracleJspRuntime.getTagHandler(pageContext,javax.faces.webapp.AttributeTag.class,"javax.faces.webapp.AttributeTag name value");
                                            __jsp_taghandler_78.setParent(__jsp_taghandler_77);
                                            __jsp_taghandler_78.setName("nombre");
                                            __jsp_taghandler_78.setValue("consulta_gasto_nominas");
                                            __jsp_tag_starteval=__jsp_taghandler_78.doStartTag();
                                            if (__jsp_taghandler_78.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                              return;
                                            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_78,9);
                                          }
                                          {
                                            javax.faces.webapp.AttributeTag __jsp_taghandler_79=(javax.faces.webapp.AttributeTag)OracleJspRuntime.getTagHandler(pageContext,javax.faces.webapp.AttributeTag.class,"javax.faces.webapp.AttributeTag name value");
                                            __jsp_taghandler_79.setParent(__jsp_taghandler_77);
                                            __jsp_taghandler_79.setName("titulo");
                                            __jsp_taghandler_79.setValue("Número Nómina:Tipo Nómina:Registros Procesados:Monto Registros Procesados:Registros Rechazados:Monto Total Rechazados:Comision Registros Procesados (UF):Registros Mismo Banco:Monto Registros Mismo Banco:Comision Registros Mismo Banco (UF):Registros Otros Bancos:Monto Registros Otros Bancos:Comision Registros Otros Bancos (UF):Banco:Ingreso:Fecha Conciliación");
                                            __jsp_tag_starteval=__jsp_taghandler_79.doStartTag();
                                            if (__jsp_taghandler_79.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                              return;
                                            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_79,9);
                                          }
                                        }
                                        if (__jsp_taghandler_77.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                          return;
                                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_77,8);
                                      }
                                    } while (__jsp_taghandler_76.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
                                  }
                                  if (__jsp_taghandler_76.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_76,7);
                                }
                              }
                              if (__jsp_taghandler_28.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_28,6);
                            }
                          }
                          if (__jsp_taghandler_8.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                            return;
                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_8,5);
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
