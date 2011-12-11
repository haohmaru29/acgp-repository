
import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import oracle.jsp.el.*;
import javax.servlet.jsp.el.*;


public class _CoreMenu_jspx extends com.orionserver.http.OrionHttpJspPage {


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
    _CoreMenu_jspx page = this;
    ServletConfig config = pageContext.getServletConfig();
    javax.servlet.jsp.el.VariableResolver __ojsp_varRes = (VariableResolver)new OracleVariableResolverImpl(pageContext);

    try {


      out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\" >" );
      {
        oracle.adfinternal.view.faces.taglib.html.HtmlTableLayoutTag __jsp_taghandler_1=(oracle.adfinternal.view.faces.taglib.html.HtmlTableLayoutTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlTableLayoutTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlTableLayoutTag width");
        __jsp_taghandler_1.setParent(null);
        __jsp_taghandler_1.setWidth("770px");
        __jsp_tag_starteval=__jsp_taghandler_1.doStartTag();
        if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
        {
          {
            oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag __jsp_taghandler_2=(oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag");
            __jsp_taghandler_2.setParent(__jsp_taghandler_1);
            __jsp_tag_starteval=__jsp_taghandler_2.doStartTag();
            if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
            {
              {
                oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_3=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag");
                __jsp_taghandler_3.setParent(__jsp_taghandler_2);
                __jsp_tag_starteval=__jsp_taghandler_3.doStartTag();
                if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                {
                  {
                    oracle.adfinternal.view.faces.taglib.core.nav.CoreMenuTabsTag __jsp_taghandler_4=(oracle.adfinternal.view.faces.taglib.core.nav.CoreMenuTabsTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreMenuTabsTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreMenuTabsTag");
                    __jsp_taghandler_4.setParent(__jsp_taghandler_3);
                    __jsp_tag_starteval=__jsp_taghandler_4.doStartTag();
                    if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                    {
                      {
                        oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandMenuItemTag __jsp_taghandler_5=(oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandMenuItemTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandMenuItemTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandMenuItemTag text selected");
                        __jsp_taghandler_5.setParent(__jsp_taghandler_4);
                        __jsp_taghandler_5.setText("Varios");
                        __jsp_taghandler_5.setSelected("false");
                        __jsp_tag_starteval=__jsp_taghandler_5.doStartTag();
                        if (__jsp_taghandler_5.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                          return;
                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_5,5);
                      }
                      {
                        oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandMenuItemTag __jsp_taghandler_6=(oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandMenuItemTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandMenuItemTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandMenuItemTag text");
                        __jsp_taghandler_6.setParent(__jsp_taghandler_4);
                        __jsp_taghandler_6.setText("Configuración");
                        __jsp_tag_starteval=__jsp_taghandler_6.doStartTag();
                        if (__jsp_taghandler_6.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                          return;
                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_6,5);
                      }
                      {
                        oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandMenuItemTag __jsp_taghandler_7=(oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandMenuItemTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandMenuItemTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreCommandMenuItemTag text selected");
                        __jsp_taghandler_7.setParent(__jsp_taghandler_4);
                        __jsp_taghandler_7.setText("Envio Nominas");
                        __jsp_taghandler_7.setSelected("true");
                        __jsp_tag_starteval=__jsp_taghandler_7.doStartTag();
                        if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                        {
                          {
                            com.sun.faces.taglib.jsf_core.SelectItemTag __jsp_taghandler_8=(com.sun.faces.taglib.jsf_core.SelectItemTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.jsf_core.SelectItemTag.class,"com.sun.faces.taglib.jsf_core.SelectItemTag itemLabel value");
                            __jsp_taghandler_8.setParent(__jsp_taghandler_7);
                            __jsp_taghandler_8.setItemLabel("algo");
                            __jsp_taghandler_8.setValue("0");
                            __jsp_tag_starteval=__jsp_taghandler_8.doStartTag();
                            if (__jsp_taghandler_8.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                              return;
                            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_8,6);
                          }
                        }
                        if (__jsp_taghandler_7.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                          return;
                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_7,5);
                      }
                    }
                    if (__jsp_taghandler_4.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                      return;
                    OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_4,4);
                  }
                  {
                    oracle.adfinternal.view.faces.taglib.core.nav.CoreMenuBarTag __jsp_taghandler_9=(oracle.adfinternal.view.faces.taglib.core.nav.CoreMenuBarTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreMenuBarTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreMenuBarTag");
                    __jsp_taghandler_9.setParent(__jsp_taghandler_3);
                    __jsp_tag_starteval=__jsp_taghandler_9.doStartTag();
                    if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                    {
                      {
                        oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag __jsp_taghandler_10=(oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag text destination");
                        __jsp_taghandler_10.setParent(__jsp_taghandler_9);
                        __jsp_taghandler_10.setText("Inicio");
                        __jsp_taghandler_10.setDestination("/faces/Bienvenida.jspx");
                        __jsp_tag_starteval=__jsp_taghandler_10.doStartTag();
                        if (__jsp_taghandler_10.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                          return;
                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_10,5);
                      }
                      {
                        oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag __jsp_taghandler_11=(oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag text destination");
                        __jsp_taghandler_11.setParent(__jsp_taghandler_9);
                        __jsp_taghandler_11.setText("Consulta Nominas");
                        __jsp_taghandler_11.setDestination("/faces/app/envionominas/consultaNominasEstado.jspx");
                        __jsp_tag_starteval=__jsp_taghandler_11.doStartTag();
                        if (__jsp_taghandler_11.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                          return;
                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_11,5);
                      }
                      {
                        oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag __jsp_taghandler_12=(oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag text destination");
                        __jsp_taghandler_12.setParent(__jsp_taghandler_9);
                        __jsp_taghandler_12.setText("Extraccion Nomina");
                        __jsp_taghandler_12.setDestination("/faces/app/envionominas/extraccionNomina.jspx");
                        __jsp_tag_starteval=__jsp_taghandler_12.doStartTag();
                        if (__jsp_taghandler_12.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                          return;
                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_12,5);
                      }
                      {
                        oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag __jsp_taghandler_13=(oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag text destination");
                        __jsp_taghandler_13.setParent(__jsp_taghandler_9);
                        __jsp_taghandler_13.setText("Consulta Gastos Nomina");
                        __jsp_taghandler_13.setDestination("/faces/app/envionominas/consultaGastosNomina.jspx");
                        __jsp_tag_starteval=__jsp_taghandler_13.doStartTag();
                        if (__jsp_taghandler_13.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                          return;
                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_13,5);
                      }
                      {
                        oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag __jsp_taghandler_14=(oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag text destination");
                        __jsp_taghandler_14.setParent(__jsp_taghandler_9);
                        __jsp_taghandler_14.setText("Mantenedor Feriados");
                        __jsp_taghandler_14.setDestination("/faces/app/envionominas/mantenedorFeriados.jspx");
                        __jsp_tag_starteval=__jsp_taghandler_14.doStartTag();
                        if (__jsp_taghandler_14.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                          return;
                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_14,5);
                      }
                      {
                        oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag __jsp_taghandler_15=(oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag text destination");
                        __jsp_taghandler_15.setParent(__jsp_taghandler_9);
                        __jsp_taghandler_15.setText("Mantenedor Correos");
                        __jsp_taghandler_15.setDestination("/faces/app/envionominas/mantenedorCorreos.jspx");
                        __jsp_tag_starteval=__jsp_taghandler_15.doStartTag();
                        if (__jsp_taghandler_15.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                          return;
                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_15,5);
                      }
                    }
                    if (__jsp_taghandler_9.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                      return;
                    OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_9,4);
                  }
                  {
                    oracle.adfinternal.view.faces.taglib.core.nav.CoreMenuBarTag __jsp_taghandler_16=(oracle.adfinternal.view.faces.taglib.core.nav.CoreMenuBarTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreMenuBarTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreMenuBarTag");
                    __jsp_taghandler_16.setParent(__jsp_taghandler_3);
                    __jsp_tag_starteval=__jsp_taghandler_16.doStartTag();
                    if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                    {
                      {
                        oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag __jsp_taghandler_17=(oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag text destination");
                        __jsp_taghandler_17.setParent(__jsp_taghandler_16);
                        __jsp_taghandler_17.setText("Registrar Folio");
                        __jsp_taghandler_17.setDestination("/faces/app/envionominas/registrarFolioEnvio.jspx");
                        __jsp_tag_starteval=__jsp_taghandler_17.doStartTag();
                        if (__jsp_taghandler_17.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                          return;
                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_17,5);
                      }
                      {
                        oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag __jsp_taghandler_18=(oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag text destination");
                        __jsp_taghandler_18.setParent(__jsp_taghandler_16);
                        __jsp_taghandler_18.setText("Mantenedor Listas");
                        __jsp_taghandler_18.setDestination("/faces/app/envionominas/mantenedorListasDistribucion.jspx");
                        __jsp_tag_starteval=__jsp_taghandler_18.doStartTag();
                        if (__jsp_taghandler_18.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                          return;
                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_18,5);
                      }
                      {
                        oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag __jsp_taghandler_19=(oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag text destination");
                        __jsp_taghandler_19.setParent(__jsp_taghandler_16);
                        __jsp_taghandler_19.setText("Mantenedor Bancos Proceso");
                        __jsp_taghandler_19.setDestination("/faces/app/envionominas/mantenedorBancoProceso.jspx");
                        __jsp_tag_starteval=__jsp_taghandler_19.doStartTag();
                        if (__jsp_taghandler_19.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                          return;
                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_19,5);
                      }
                      {
                        oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag __jsp_taghandler_20=(oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag text destination");
                        __jsp_taghandler_20.setParent(__jsp_taghandler_16);
                        __jsp_taghandler_20.setText("Descarga de Archivos Nominas");
                        __jsp_taghandler_20.setDestination("/faces/app/envionominas/archivosNominas.jspx");
                        __jsp_tag_starteval=__jsp_taghandler_20.doStartTag();
                        if (__jsp_taghandler_20.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                          return;
                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_20,5);
                      }
                      {
                        com.sun.faces.taglib.jsf_core.VerbatimTag __jsp_taghandler_21=(com.sun.faces.taglib.jsf_core.VerbatimTag)OracleJspRuntime.getTagHandler(pageContext,com.sun.faces.taglib.jsf_core.VerbatimTag.class,"com.sun.faces.taglib.jsf_core.VerbatimTag");
                        __jsp_taghandler_21.setParent(__jsp_taghandler_16);
                        __jsp_tag_starteval=__jsp_taghandler_21.doStartTag();
                        if (OracleJspRuntime.checkStartBodyTagEval(__jsp_tag_starteval))
                        {
                          out=OracleJspRuntime.pushBodyIfNeeded(pageContext,__jsp_taghandler_21,__jsp_tag_starteval,out);
                          do {
                            out.write(__oracle_jsp_text[0]);
                          } while (__jsp_taghandler_21.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
                          out=OracleJspRuntime.popBodyIfNeeded(pageContext,out);
                        }
                        if (__jsp_taghandler_21.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                          return;
                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_21,5);
                      }
                      {
                        oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag __jsp_taghandler_22=(oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag text targetFrame destination");
                        __jsp_taghandler_22.setParent(__jsp_taghandler_16);
                        __jsp_taghandler_22.setText("Desconectar");
                        __jsp_taghandler_22.setTargetFrame("CoreFramePaginas");
                        __jsp_taghandler_22.setDestination("/faces/infraestructura/Logout.jspx");
                        __jsp_tag_starteval=__jsp_taghandler_22.doStartTag();
                        if (__jsp_taghandler_22.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                          return;
                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_22,5);
                      }
                      {
                        oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag __jsp_taghandler_23=(oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag.class,"oracle.adfinternal.view.faces.taglib.core.nav.CoreGoMenuItemTag icon text targetFrame destination");
                        __jsp_taghandler_23.setParent(__jsp_taghandler_16);
                        __jsp_taghandler_23.setIcon("skins/informatica/Propuesta1/skin_images/quick_help.jpg");
                        __jsp_taghandler_23.setText("Ayuda");
                        __jsp_taghandler_23.setTargetFrame("CoreFramePaginas");
                        __jsp_taghandler_23.setDestination("/faces/infraestructura/CoreVersion.jspx");
                        __jsp_tag_starteval=__jsp_taghandler_23.doStartTag();
                        if (__jsp_taghandler_23.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                          return;
                        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_23,5);
                      }
                    }
                    if (__jsp_taghandler_16.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                      return;
                    OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_16,4);
                  }
                }
                if (__jsp_taghandler_3.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                  return;
                OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_3,3);
              }
            }
            if (__jsp_taghandler_2.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
              return;
            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_2,2);
          }
          {
            oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag __jsp_taghandler_24=(oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlRowLayoutTag");
            __jsp_taghandler_24.setParent(__jsp_taghandler_1);
            __jsp_tag_starteval=__jsp_taghandler_24.doStartTag();
            if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
            {
              {
                oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag __jsp_taghandler_25=(oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag.class,"oracle.adfinternal.view.faces.taglib.html.HtmlCellFormatTag halign wrappingDisabled");
                __jsp_taghandler_25.setParent(__jsp_taghandler_24);
                __jsp_taghandler_25.setHalign("right");
                __jsp_taghandler_25.setWrappingDisabled("true");
                __jsp_tag_starteval=__jsp_taghandler_25.doStartTag();
                if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                {
                  {
                    oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag __jsp_taghandler_26=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag value");
                    __jsp_taghandler_26.setParent(__jsp_taghandler_25);
                    __jsp_taghandler_26.setValue("Página Solicitada: #{facesContext.viewRoot.viewId} | ");
                    __jsp_tag_starteval=__jsp_taghandler_26.doStartTag();
                    if (__jsp_taghandler_26.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                      return;
                    OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_26,4);
                  }
                  {
                    oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag __jsp_taghandler_27=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag value");
                    __jsp_taghandler_27.setParent(__jsp_taghandler_25);
                    __jsp_taghandler_27.setValue("Usuario: ");
                    __jsp_tag_starteval=__jsp_taghandler_27.doStartTag();
                    if (__jsp_taghandler_27.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                      return;
                    OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_27,4);
                  }
                  {
                    oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag __jsp_taghandler_28=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag value");
                    __jsp_taghandler_28.setParent(__jsp_taghandler_25);
                    __jsp_taghandler_28.setValue("#{MbUserInfo.userName}");
                    __jsp_tag_starteval=__jsp_taghandler_28.doStartTag();
                    if (__jsp_taghandler_28.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                      return;
                    OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_28,4);
                  }
                  {
                    oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag __jsp_taghandler_29=(oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag width height");
                    __jsp_taghandler_29.setParent(__jsp_taghandler_25);
                    __jsp_taghandler_29.setWidth("10");
                    __jsp_taghandler_29.setHeight("10");
                    __jsp_tag_starteval=__jsp_taghandler_29.doStartTag();
                    if (__jsp_taghandler_29.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                      return;
                    OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_29,4);
                  }
                  {
                    oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag __jsp_taghandler_30=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag value");
                    __jsp_taghandler_30.setParent(__jsp_taghandler_25);
                    __jsp_taghandler_30.setValue("RUT: ");
                    __jsp_tag_starteval=__jsp_taghandler_30.doStartTag();
                    if (__jsp_taghandler_30.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                      return;
                    OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_30,4);
                  }
                  {
                    oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag __jsp_taghandler_31=(oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreOutputLabelTag value");
                    __jsp_taghandler_31.setParent(__jsp_taghandler_25);
                    __jsp_taghandler_31.setValue("#{MbUserInfo.rut}");
                    __jsp_tag_starteval=__jsp_taghandler_31.doStartTag();
                    if (__jsp_taghandler_31.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                      return;
                    OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_31,4);
                  }
                }
                if (__jsp_taghandler_25.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                  return;
                OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_25,3);
              }
            }
            if (__jsp_taghandler_24.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
              return;
            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_24,2);
          }
        }
        if (__jsp_taghandler_1.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_1,1);
      }
      {
        oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag __jsp_taghandler_32=(oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag)OracleJspRuntime.getTagHandler(pageContext,oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag.class,"oracle.adfinternal.view.faces.taglib.core.output.CoreObjectSpacerTag width height");
        __jsp_taghandler_32.setParent(null);
        __jsp_taghandler_32.setWidth("10");
        __jsp_taghandler_32.setHeight("10");
        __jsp_tag_starteval=__jsp_taghandler_32.doStartTag();
        if (__jsp_taghandler_32.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_32,1);
      }
      out.write(__oracle_jsp_text[1]);

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
  private static final char __oracle_jsp_text[][]=new char[2][];
  static {
    try {
    __oracle_jsp_text[0] = 
    "\n               \n          ".toCharArray();
    __oracle_jsp_text[1] = 
    "\n   \n  ".toCharArray();
    }
    catch (Throwable th) {
      System.err.println(th);
    }
}
}
