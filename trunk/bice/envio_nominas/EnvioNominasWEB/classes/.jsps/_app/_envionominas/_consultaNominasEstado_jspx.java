package _app._envionominas;

import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import oracle.jsp.el.*;
import javax.servlet.jsp.el.*;


public class _consultaNominasEstado_jspx extends com.orionserver.http.OrionHttpJspPage {


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
    _consultaNominasEstado_jspx page = this;
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
                  __jsp_taghandler_4.setTitle("#{msgs['bicevida.consulta_nominas.titulo'] }");
                  __jsp_tag_starteval=__jsp_taghandler_4.doStartTag();
                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                  {
                    out.write( "<meta"+ " http-equiv=\"" + "Content-Type"+ "\"" + " content=\"" + "text/html; charset=windows-1252"+ "\"" +"/>");
                    {
                      oracle.adfinternal.view.faces.taglib.html.HtmlScriptTag __jsp_taghandler_5=(oracle.adfinternal.view.faces.taglib.html.HtmlScriptTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlScriptTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlScriptTag source");
                      __jsp_taghandler_5.setParent(__jsp_taghandler_4);
                      __jsp_taghandler_5.setSource("test.js");
                      __jsp_tag_starteval=__jsp_taghandler_5.doStartTag();
                      if (__jsp_taghandler_5.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                        return;
                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_5,4);
                    }
                  }
                  if (__jsp_taghandler_4.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                    return;
                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_4,3);
                }
                {
                  oracle.adfinternal.view.faces.taglib.html.HtmlBodyTag __jsp_taghandler_6=(oracle.adfinternal.view.faces.taglib.html.HtmlBodyTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlBodyTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlBodyTag");
                  __jsp_taghandler_6.setParent(__jsp_taghandler_2);
                  __jsp_tag_starteval=__jsp_taghandler_6.doStartTag();
                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                  {
                    {
                      oracle.adfinternal.view.faces.taglib.core.CoreFormTag __jsp_taghandler_7=(oracle.adfinternal.view.faces.taglib.core.CoreFormTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.CoreFormTag.class,"oracle.adfinternal.view.faces.taglib.core.CoreFormTag id");
                      __jsp_taghandler_7.setParent(__jsp_taghandler_6);
                      __jsp_taghandler_7.setId("formNominas");
                      __jsp_tag_starteval=__jsp_taghandler_7.doStartTag();
                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                      {
                        {
                          oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag __jsp_taghandler_8=(oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag.class,"oracle.adfinternal.view.faces.taglib.core.layout.CorePanelBoxTag width background text icon");
                          __jsp_taghandler_8.setParent(__jsp_taghandler_7);
                          __jsp_taghandler_8.setWidth("770px");
                          __jsp_taghandler_8.setBackground("medium");
                          __jsp_taghandler_8.setText("#{msgs['bicevida.consulta_nominas.titulo'] }");
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
                                              __jsp_taghandler_13.setLabel("#{msgs['bicevida.consulta_nominas.lbl.lote'] }");
                                              __jsp_taghandler_13.setMaximumLength("200");
                                              __jsp_taghandler_13.setValue("#{mb_consultaNominasEstado.loteNomina}");
                                              __jsp_tag_starteval=__jsp_taghandler_13.doStartTag();
                                              if (__jsp_taghandler_13.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                return;
                                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_13,10);
                                            }
                                            {
                                              oracle.adfinternal.view.faces.taglib.core.input.CoreInputTextTag __jsp_taghandler_14=(oracle.adfinternal.view.faces.taglib.core.input.CoreInputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.input.CoreInputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.input.CoreInputTextTag label value");
                                              __jsp_taghandler_14.setParent(__jsp_taghandler_12);
                                              __jsp_taghandler_14.setLabel("#{msgs['bicevida.consulta_nominas.lbl.ruttitular'] }");
                                              __jsp_taghandler_14.setValue("#{mb_consultaNominasEstado.rutTitular}");
                                              __jsp_tag_starteval=__jsp_taghandler_14.doStartTag();
                                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                              {
                                                {
                                                  oracle.adfinternal.view.faces.taglib.ValidatorTag __jsp_taghandler_15=(oracle.adfinternal.view.faces.taglib.ValidatorTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.ValidatorTag.class,"oracle.adfinternal.view.faces.taglib.ValidatorTag validatorId");
                                                  __jsp_taghandler_15.setParent(__jsp_taghandler_14);
                                                  __jsp_taghandler_15.setValidatorId("RutValidator");
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
                                              __jsp_taghandler_16.setLabel("#{msgs['bicevida.consulta_nominas.lbl.fechadesde'] }");
                                              __jsp_taghandler_16.setValidator("#{mb_consultaNominasEstado.validarFechas}");
                                              __jsp_taghandler_16.setValue("#{mb_consultaNominasEstado.fechaDesde}");
                                              __jsp_tag_starteval=__jsp_taghandler_16.doStartTag();
                                              if (__jsp_taghandler_16.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                return;
                                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_16,10);
                                            }
                                            {
                                              oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag __jsp_taghandler_17=(oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag.class,"oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag unselectedLabel label value");
                                              __jsp_taghandler_17.setParent(__jsp_taghandler_12);
                                              __jsp_taghandler_17.setUnselectedLabel("#{msgs['bicevida.generales.opt.seleccione'] }");
                                              __jsp_taghandler_17.setLabel("#{msgs['bicevida.consulta_nominas.lbl.tiponomina'] }");
                                              __jsp_taghandler_17.setValue("#{mb_consultaNominasEstado.tipoNominaSeleccionada}");
                                              __jsp_tag_starteval=__jsp_taghandler_17.doStartTag();
                                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                              {
                                                {
                                                  com.sun.faces.taglib.jsf_core.SelectItemsTag __jsp_taghandler_18=(com.sun.faces.taglib.jsf_core.SelectItemsTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.jsf_core.SelectItemsTag.class,"com.sun.faces.taglib.jsf_core.SelectItemsTag id value");
                                                  __jsp_taghandler_18.setParent(__jsp_taghandler_17);
                                                  __jsp_taghandler_18.setId("sitTiposNomina");
                                                  __jsp_taghandler_18.setValue("#{mb_consultaNominasEstado.tiposNomina}");
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
                                              __jsp_taghandler_19.setLabel("#{msgs['bicevida.consulta_nominas.lbl.bancoproceso'] }");
                                              __jsp_taghandler_19.setValue("#{mb_consultaNominasEstado.bancoProcesoSeleccionado}");
                                              __jsp_tag_starteval=__jsp_taghandler_19.doStartTag();
                                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                              {
                                                {
                                                  com.sun.faces.taglib.jsf_core.SelectItemsTag __jsp_taghandler_20=(com.sun.faces.taglib.jsf_core.SelectItemsTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.jsf_core.SelectItemsTag.class,"com.sun.faces.taglib.jsf_core.SelectItemsTag id value");
                                                  __jsp_taghandler_20.setParent(__jsp_taghandler_19);
                                                  __jsp_taghandler_20.setId("sitBancoProceso");
                                                  __jsp_taghandler_20.setValue("#{mb_consultaNominasEstado.bancosProceso}");
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
                                              __jsp_taghandler_21.setLabel("#{msgs['bicevida.consulta_nominas.lbl.fechahasta'] }");
                                              __jsp_taghandler_21.setValidator("#{mb_consultaNominasEstado.validarFechas}");
                                              __jsp_taghandler_21.setValue("#{mb_consultaNominasEstado.fechaHasta}");
                                              __jsp_tag_starteval=__jsp_taghandler_21.doStartTag();
                                              if (__jsp_taghandler_21.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                return;
                                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_21,10);
                                            }
                                            {
                                              oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag __jsp_taghandler_22=(oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag.class,"oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag unselectedLabel label value");
                                              __jsp_taghandler_22.setParent(__jsp_taghandler_12);
                                              __jsp_taghandler_22.setUnselectedLabel("#{msgs['bicevida.generales.opt.seleccione'] }");
                                              __jsp_taghandler_22.setLabel("#{msgs['bicevida.consulta_nominas.lbl.estadonomina'] }");
                                              __jsp_taghandler_22.setValue("#{mb_consultaNominasEstado.estadoNominaSeleccionado}");
                                              __jsp_tag_starteval=__jsp_taghandler_22.doStartTag();
                                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                              {
                                                {
                                                  com.sun.faces.taglib.jsf_core.SelectItemsTag __jsp_taghandler_23=(com.sun.faces.taglib.jsf_core.SelectItemsTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.jsf_core.SelectItemsTag.class,"com.sun.faces.taglib.jsf_core.SelectItemsTag id value");
                                                  __jsp_taghandler_23.setParent(__jsp_taghandler_22);
                                                  __jsp_taghandler_23.setId("sitEstadoNomina");
                                                  __jsp_taghandler_23.setValue("#{mb_consultaNominasEstado.estadosNomina}");
                                                  __jsp_tag_starteval=__jsp_taghandler_23.doStartTag();
                                                  if (__jsp_taghandler_23.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                    return;
                                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_23,11);
                                                }
                                              }
                                              if (__jsp_taghandler_22.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                return;
                                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_22,10);
                                            }
                                            {
                                              oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag __jsp_taghandler_24=(oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag.class,"oracle.adfinternal.view.faces.taglib.core.input.CoreSelectOneChoiceTag unselectedLabel label value");
                                              __jsp_taghandler_24.setParent(__jsp_taghandler_12);
                                              __jsp_taghandler_24.setUnselectedLabel("#{msgs['bicevida.generales.opt.seleccione'] }");
                                              __jsp_taghandler_24.setLabel("#{msgs['bicevida.consulta_nominas.lbl.estadotransaccion'] }");
                                              __jsp_taghandler_24.setValue("#{mb_consultaNominasEstado.estadoTransaccionSeleccionado}");
                                              __jsp_tag_starteval=__jsp_taghandler_24.doStartTag();
                                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                              {
                                                {
                                                  com.sun.faces.taglib.jsf_core.SelectItemsTag __jsp_taghandler_25=(com.sun.faces.taglib.jsf_core.SelectItemsTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.jsf_core.SelectItemsTag.class,"com.sun.faces.taglib.jsf_core.SelectItemsTag id value");
                                                  __jsp_taghandler_25.setParent(__jsp_taghandler_24);
                                                  __jsp_taghandler_25.setId("sitEstadoTransaccion");
                                                  __jsp_taghandler_25.setValue("#{mb_consultaNominasEstado.estadosTransaccion}");
                                                  __jsp_tag_starteval=__jsp_taghandler_25.doStartTag();
                                                  if (__jsp_taghandler_25.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                    return;
                                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_25,11);
                                                }
                                              }
                                              if (__jsp_taghandler_24.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                return;
                                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_24,10);
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
                              oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag __jsp_taghandler_26=(oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag width height");
                              __jsp_taghandler_26.setParent(__jsp_taghandler_8);
                              __jsp_taghandler_26.setWidth("10");
                              __jsp_taghandler_26.setHeight("10");
                              __jsp_tag_starteval=__jsp_taghandler_26.doStartTag();
                              if (__jsp_taghandler_26.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_26,6);
                            }
                            {
                              oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag __jsp_taghandler_27=(oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag.class,"oracle.adfinternal.view.faces.taglib.core.layout.CorePanelHorizontalTag halign");
                              __jsp_taghandler_27.setParent(__jsp_taghandler_8);
                              __jsp_taghandler_27.setHalign("center");
                              __jsp_tag_starteval=__jsp_taghandler_27.doStartTag();
                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                              {
                                {
                                  oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag __jsp_taghandler_28=(oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag text blocking actionListener");
                                  __jsp_taghandler_28.setParent(__jsp_taghandler_27);
                                  __jsp_taghandler_28.setText("#{msgs['bicevida.generales.btn.buscar'] }");
                                  __jsp_taghandler_28.setBlocking("true");
                                  __jsp_taghandler_28.setActionListener("#{mb_consultaNominasEstado.ejecutarBusqueda}");
                                  __jsp_tag_starteval=__jsp_taghandler_28.doStartTag();
                                  if (__jsp_taghandler_28.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_28,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag __jsp_taghandler_29=(oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag text blocking action immediate");
                                  __jsp_taghandler_29.setParent(__jsp_taghandler_27);
                                  __jsp_taghandler_29.setText("#{msgs['bicevida.generales.btn.limpiar'] }");
                                  __jsp_taghandler_29.setBlocking("true");
                                  __jsp_taghandler_29.setAction("#{mb_consultaNominasEstado.limpiarFormulario}");
                                  __jsp_taghandler_29.setImmediate("true");
                                  __jsp_tag_starteval=__jsp_taghandler_29.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.listener.ResetActionListenerTag __jsp_taghandler_30=(oracle.adfinternal.view.faces.taglib.listener.ResetActionListenerTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.listener.ResetActionListenerTag.class,"oracle.adfinternal.view.faces.taglib.listener.ResetActionListenerTag");
                                      __jsp_taghandler_30.setParent(__jsp_taghandler_29);
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
                              }
                              if (__jsp_taghandler_27.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_27,6);
                            }
                            {
                              oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag __jsp_taghandler_31=(oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag width height");
                              __jsp_taghandler_31.setParent(__jsp_taghandler_8);
                              __jsp_taghandler_31.setWidth("10");
                              __jsp_taghandler_31.setHeight("10");
                              __jsp_tag_starteval=__jsp_taghandler_31.doStartTag();
                              if (__jsp_taghandler_31.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_31,6);
                            }
                            {
                              oracle.adfinternal.view.faces.taglib.core.data.CoreTableTag __jsp_taghandler_32=(oracle.adfinternal.view.faces.taglib.core.data.CoreTableTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreTableTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreTableTag id width emptyText banding bandingInterval var value rows");
                              __jsp_taghandler_32.setParent(__jsp_taghandler_8);
                              __jsp_taghandler_32.setId("tabla_nominas");
                              __jsp_taghandler_32.setWidth("100%");
                              __jsp_taghandler_32.setEmptyText("#{msgs['bicevida.generales.lbl.listavacia'] }");
                              __jsp_taghandler_32.setBanding("row");
                              __jsp_taghandler_32.setBandingInterval("1");
                              __jsp_taghandler_32.setVar("fila");
                              __jsp_taghandler_32.setValue("#{mb_consultaNominasEstado.lista}");
                              __jsp_taghandler_32.setRows("#{mb_consultaNominasEstado.registrosPagina}");
                              __jsp_tag_starteval=__jsp_taghandler_32.doStartTag();
                              if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                              {
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_33=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType gridVisible headerText inlineStyle");
                                  __jsp_taghandler_33.setParent(__jsp_taghandler_32);
                                  __jsp_taghandler_33.setFormatType("text");
                                  __jsp_taghandler_33.setGridVisible("false");
                                  __jsp_taghandler_33.setHeaderText("#{msgs['bicevida.consulta_nominas.col.codigonomina'] }");
                                  __jsp_taghandler_33.setInlineStyle("text-align:right;");
                                  __jsp_tag_starteval=__jsp_taghandler_33.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_34=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_34.setParent(__jsp_taghandler_33);
                                      __jsp_taghandler_34.setValue("#{fila.nomina.id}");
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
                                  __jsp_taghandler_35.setParent(__jsp_taghandler_32);
                                  __jsp_taghandler_35.setFormatType("text");
                                  __jsp_taghandler_35.setHeaderText("#{msgs['bicevida.consulta_nominas.col.folioproceso'] }");
                                  __jsp_taghandler_35.setInlineStyle("text-align:center;");
                                  __jsp_tag_starteval=__jsp_taghandler_35.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_36=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_36.setParent(__jsp_taghandler_35);
                                      __jsp_taghandler_36.setValue("#{fila.proceso.folioProcesoExterno}");
                                      __jsp_tag_starteval=__jsp_taghandler_36.doStartTag();
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
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_37=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText");
                                  __jsp_taghandler_37.setParent(__jsp_taghandler_32);
                                  __jsp_taghandler_37.setFormatType("text");
                                  __jsp_taghandler_37.setHeaderText("#{msgs['bicevida.consulta_nominas.col.lote'] }");
                                  __jsp_tag_starteval=__jsp_taghandler_37.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_38=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_38.setParent(__jsp_taghandler_37);
                                      __jsp_taghandler_38.setValue("#{fila.nomina.lote}");
                                      __jsp_tag_starteval=__jsp_taghandler_38.doStartTag();
                                      if (__jsp_taghandler_38.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_38,8);
                                    }
                                  }
                                  if (__jsp_taghandler_37.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_37,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_39=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText");
                                  __jsp_taghandler_39.setParent(__jsp_taghandler_32);
                                  __jsp_taghandler_39.setFormatType("text");
                                  __jsp_taghandler_39.setHeaderText("#{msgs['bicevida.consulta_nominas.col.origen'] }");
                                  __jsp_tag_starteval=__jsp_taghandler_39.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_40=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_40.setParent(__jsp_taghandler_39);
                                      __jsp_taghandler_40.setValue("#{fila.nomina.origen.nombre}");
                                      __jsp_tag_starteval=__jsp_taghandler_40.doStartTag();
                                      if (__jsp_taghandler_40.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_40,8);
                                    }
                                  }
                                  if (__jsp_taghandler_39.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_39,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_41=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText");
                                  __jsp_taghandler_41.setParent(__jsp_taghandler_32);
                                  __jsp_taghandler_41.setFormatType("text");
                                  __jsp_taghandler_41.setHeaderText("#{msgs['bicevida.consulta_nominas.col.tiponomina'] }");
                                  __jsp_tag_starteval=__jsp_taghandler_41.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_42=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_42.setParent(__jsp_taghandler_41);
                                      __jsp_taghandler_42.setValue("#{fila.nomina.tipo.nombre}");
                                      __jsp_tag_starteval=__jsp_taghandler_42.doStartTag();
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
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_43=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText");
                                  __jsp_taghandler_43.setParent(__jsp_taghandler_32);
                                  __jsp_taghandler_43.setFormatType("text");
                                  __jsp_taghandler_43.setHeaderText("#{msgs['bicevida.consulta_nominas.col.estadonomina'] }");
                                  __jsp_tag_starteval=__jsp_taghandler_43.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_44=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_44.setParent(__jsp_taghandler_43);
                                      __jsp_taghandler_44.setValue("#{fila.nomina.estado.nombre}");
                                      __jsp_tag_starteval=__jsp_taghandler_44.doStartTag();
                                      if (__jsp_taghandler_44.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_44,8);
                                    }
                                  }
                                  if (__jsp_taghandler_43.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_43,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_45=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType width headerText inlineStyle");
                                  __jsp_taghandler_45.setParent(__jsp_taghandler_32);
                                  __jsp_taghandler_45.setFormatType("text");
                                  __jsp_taghandler_45.setWidth("105");
                                  __jsp_taghandler_45.setHeaderText("#{msgs['bicevida.consulta_nominas.col.numerotransaccion'] }");
                                  __jsp_taghandler_45.setInlineStyle("text-align:right;");
                                  __jsp_tag_starteval=__jsp_taghandler_45.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_46=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_46.setParent(__jsp_taghandler_45);
                                      __jsp_taghandler_46.setValue(" #{fila.id}");
                                      __jsp_tag_starteval=__jsp_taghandler_46.doStartTag();
                                      if (__jsp_taghandler_46.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_46,8);
                                    }
                                  }
                                  if (__jsp_taghandler_45.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_45,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_47=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText");
                                  __jsp_taghandler_47.setParent(__jsp_taghandler_32);
                                  __jsp_taghandler_47.setFormatType("text");
                                  __jsp_taghandler_47.setHeaderText("#{msgs['bicevida.consulta_nominas.col.estadotransaccion'] }");
                                  __jsp_tag_starteval=__jsp_taghandler_47.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_48=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_48.setParent(__jsp_taghandler_47);
                                      __jsp_taghandler_48.setValue("#{fila.estado.nombre}");
                                      __jsp_tag_starteval=__jsp_taghandler_48.doStartTag();
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
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_49=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText inlineStyle");
                                  __jsp_taghandler_49.setParent(__jsp_taghandler_32);
                                  __jsp_taghandler_49.setFormatType("text");
                                  __jsp_taghandler_49.setHeaderText("#{msgs['bicevida.consulta_nominas.col.ruttitular'] }");
                                  __jsp_taghandler_49.setInlineStyle("text-align:right;");
                                  __jsp_tag_starteval=__jsp_taghandler_49.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_50=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_50.setParent(__jsp_taghandler_49);
                                      __jsp_taghandler_50.setValue("#{fila.rut}-#{fila.dv}");
                                      __jsp_tag_starteval=__jsp_taghandler_50.doStartTag();
                                      if (__jsp_taghandler_50.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_50,8);
                                    }
                                  }
                                  if (__jsp_taghandler_49.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_49,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_51=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText");
                                  __jsp_taghandler_51.setParent(__jsp_taghandler_32);
                                  __jsp_taghandler_51.setFormatType("text");
                                  __jsp_taghandler_51.setHeaderText("#{msgs['bicevida.consulta_nominas.col.nombretitular'] }");
                                  __jsp_tag_starteval=__jsp_taghandler_51.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_52=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_52.setParent(__jsp_taghandler_51);
                                      __jsp_taghandler_52.setValue("#{fila.nombre}");
                                      __jsp_tag_starteval=__jsp_taghandler_52.doStartTag();
                                      if (__jsp_taghandler_52.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_52,8);
                                    }
                                  }
                                  if (__jsp_taghandler_51.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_51,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_53=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText");
                                  __jsp_taghandler_53.setParent(__jsp_taghandler_32);
                                  __jsp_taghandler_53.setFormatType("text");
                                  __jsp_taghandler_53.setHeaderText("#{msgs['bicevida.consulta_nominas.col.tipocuenta'] }");
                                  __jsp_tag_starteval=__jsp_taghandler_53.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_54=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_54.setParent(__jsp_taghandler_53);
                                      __jsp_taghandler_54.setValue("#{fila.tipoCuenta.nombre}");
                                      __jsp_tag_starteval=__jsp_taghandler_54.doStartTag();
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
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_55=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText inlineStyle");
                                  __jsp_taghandler_55.setParent(__jsp_taghandler_32);
                                  __jsp_taghandler_55.setFormatType("text");
                                  __jsp_taghandler_55.setHeaderText("#{msgs['bicevida.consulta_nominas.col.numerocuenta'] }");
                                  __jsp_taghandler_55.setInlineStyle("text-align:right;");
                                  __jsp_tag_starteval=__jsp_taghandler_55.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_56=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_56.setParent(__jsp_taghandler_55);
                                      __jsp_taghandler_56.setValue("#{fila.cuenta}");
                                      __jsp_tag_starteval=__jsp_taghandler_56.doStartTag();
                                      if (__jsp_taghandler_56.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_56,8);
                                    }
                                  }
                                  if (__jsp_taghandler_55.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_55,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_57=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText");
                                  __jsp_taghandler_57.setParent(__jsp_taghandler_32);
                                  __jsp_taghandler_57.setFormatType("text");
                                  __jsp_taghandler_57.setHeaderText("#{msgs['bicevida.consulta_nominas.col.bancocuenta'] }");
                                  __jsp_tag_starteval=__jsp_taghandler_57.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_58=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_58.setParent(__jsp_taghandler_57);
                                      __jsp_taghandler_58.setValue("#{fila.banco.nombre}");
                                      __jsp_tag_starteval=__jsp_taghandler_58.doStartTag();
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
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_59=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText inlineStyle");
                                  __jsp_taghandler_59.setParent(__jsp_taghandler_32);
                                  __jsp_taghandler_59.setFormatType("text");
                                  __jsp_taghandler_59.setHeaderText("#{msgs['bicevida.consulta_nominas.col.monto'] }");
                                  __jsp_taghandler_59.setInlineStyle("text-align:left;");
                                  __jsp_tag_starteval=__jsp_taghandler_59.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_60=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_60.setParent(__jsp_taghandler_59);
                                      __jsp_taghandler_60.setValue("#{fila.monto}");
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
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_62=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText");
                                  __jsp_taghandler_62.setParent(__jsp_taghandler_32);
                                  __jsp_taghandler_62.setFormatType("text");
                                  __jsp_taghandler_62.setHeaderText("#{msgs['bicevida.consulta_nominas.col.bancoproceso'] }");
                                  __jsp_tag_starteval=__jsp_taghandler_62.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_63=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_63.setParent(__jsp_taghandler_62);
                                      __jsp_taghandler_63.setValue("#{fila.proceso.bancoProceso.banco.nombre}");
                                      __jsp_tag_starteval=__jsp_taghandler_63.doStartTag();
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
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_64=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText inlineStyle");
                                  __jsp_taghandler_64.setParent(__jsp_taghandler_32);
                                  __jsp_taghandler_64.setFormatType("text");
                                  __jsp_taghandler_64.setHeaderText("#{msgs['bicevida.consulta_nominas.col.fechainicioproceso'] }");
                                  __jsp_taghandler_64.setInlineStyle("text-align:right;");
                                  __jsp_tag_starteval=__jsp_taghandler_64.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_65=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_65.setParent(__jsp_taghandler_64);
                                      __jsp_taghandler_65.setValue("#{fila.proceso.fechaCreacion}");
                                      __jsp_tag_starteval=__jsp_taghandler_65.doStartTag();
                                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                      {
                                        {
                                          com.sun.faces.taglib.jsf_core.ConvertDateTimeTag __jsp_taghandler_66=(com.sun.faces.taglib.jsf_core.ConvertDateTimeTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.jsf_core.ConvertDateTimeTag.class,"com.sun.faces.taglib.jsf_core.ConvertDateTimeTag pattern");
                                          __jsp_taghandler_66.setParent(__jsp_taghandler_65);
                                          __jsp_taghandler_66.setPattern("dd-MM-yyyy");
                                          __jsp_tag_starteval=__jsp_taghandler_66.doStartTag();
                                          if (__jsp_taghandler_66.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                            return;
                                          OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_66,9);
                                        }
                                      }
                                      if (__jsp_taghandler_65.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                        return;
                                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_65,8);
                                    }
                                  }
                                  if (__jsp_taghandler_64.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_64,7);
                                }
                                {
                                  oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag __jsp_taghandler_67=(oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag.class,"oracle.adfinternal.view.faces.taglib.core.data.CoreColumnTag formatType headerText inlineStyle");
                                  __jsp_taghandler_67.setParent(__jsp_taghandler_32);
                                  __jsp_taghandler_67.setFormatType("text");
                                  __jsp_taghandler_67.setHeaderText("#{msgs['bicevida.consulta_nominas.col.fechaestado'] }");
                                  __jsp_taghandler_67.setInlineStyle("text-align:right;");
                                  __jsp_tag_starteval=__jsp_taghandler_67.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    {
                                      oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag __jsp_taghandler_68=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputTextTag value");
                                      __jsp_taghandler_68.setParent(__jsp_taghandler_67);
                                      __jsp_taghandler_68.setValue("#{fila.fechaEstado}");
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
                                  javax.faces.webapp.FacetTag __jsp_taghandler_70=(javax.faces.webapp.FacetTag)OracleJspRuntime.getTagHandler(pageContext,javax.faces.webapp.FacetTag.class,"javax.faces.webapp.FacetTag name");
                                  __jsp_taghandler_70.setParent(__jsp_taghandler_32);
                                  __jsp_taghandler_70.setName("actions");
                                  __jsp_tag_starteval=__jsp_taghandler_70.doStartTag();
                                  if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                  {
                                    do {
                                      {
                                        oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag __jsp_taghandler_71=(oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandButtonTag text blocking actionListener");
                                        __jsp_taghandler_71.setParent(__jsp_taghandler_70);
                                        __jsp_taghandler_71.setText("#{msgs['bicevida.generales.btn.descargaxls'] }");
                                        __jsp_taghandler_71.setBlocking("true");
                                        __jsp_taghandler_71.setActionListener("#{MbExcelVisor.onClickGenerarExcel}");
                                        __jsp_tag_starteval=__jsp_taghandler_71.doStartTag();
                                        if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                                        {
                                          {
                                            javax.faces.webapp.AttributeTag __jsp_taghandler_72=(javax.faces.webapp.AttributeTag)OracleJspRuntime.getTagHandler(pageContext,javax.faces.webapp.AttributeTag.class,"javax.faces.webapp.AttributeTag name value");
                                            __jsp_taghandler_72.setParent(__jsp_taghandler_71);
                                            __jsp_taghandler_72.setName("nombre");
                                            __jsp_taghandler_72.setValue("consulta_estado_nominas");
                                            __jsp_tag_starteval=__jsp_taghandler_72.doStartTag();
                                            if (__jsp_taghandler_72.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                              return;
                                            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_72,9);
                                          }
                                          {
                                            javax.faces.webapp.AttributeTag __jsp_taghandler_73=(javax.faces.webapp.AttributeTag)OracleJspRuntime.getTagHandler(pageContext,javax.faces.webapp.AttributeTag.class,"javax.faces.webapp.AttributeTag name value");
                                            __jsp_taghandler_73.setParent(__jsp_taghandler_71);
                                            __jsp_taghandler_73.setName("titulo");
                                            __jsp_taghandler_73.setValue("Número Nómina:Folio Proceso Externo:Lote:Origen:Tipo Nómina:Estado Nómina:Número Transacción:Estado Transacción:Rut Beneficiario:Nombre Beneficiario:Tipo Cuenta Beneficiario:Número Cuenta Beneficiario:Banco Beneficiario:Monto($):Banco Proceso:Fecha Inicio Proceso:Fecha Estado");
                                            __jsp_tag_starteval=__jsp_taghandler_73.doStartTag();
                                            if (__jsp_taghandler_73.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                              return;
                                            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_73,9);
                                          }
                                        }
                                        if (__jsp_taghandler_71.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                          return;
                                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_71,8);
                                      }
                                    } while (__jsp_taghandler_70.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
                                  }
                                  if (__jsp_taghandler_70.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                    return;
                                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_70,7);
                                }
                              }
                              if (__jsp_taghandler_32.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                return;
                              OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_32,6);
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
                  if (__jsp_taghandler_6.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                    return;
                  OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_6,3);
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
