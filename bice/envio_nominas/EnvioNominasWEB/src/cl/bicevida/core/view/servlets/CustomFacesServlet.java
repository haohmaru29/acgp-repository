package cl.bicevida.core.view.servlets;

import cl.bicevida.core.view.jsf.mb.MbError;
import cl.bicevida.core.view.jsf.utils.JsfUtils;

import java.io.IOException;

import javax.faces.webapp.FacesServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CustomFacesServlet extends HttpServlet implements javax.servlet.Servlet {
    private static final String INIT_PARAM_ERROR_PAGE = "errorPage";
    private FacesServlet delegate;
    private String errorPage;

    public void init( ServletConfig servletConfig ) throws ServletException {
        delegate = new FacesServlet();
        delegate.init( servletConfig );
        errorPage = servletConfig.getInitParameter( INIT_PARAM_ERROR_PAGE );
    }

    public void destroy() {
        delegate.destroy();
    }

    public ServletConfig getServletConfig() {
        return delegate.getServletConfig();
    }

    public String getServletInfo() {
        return delegate.getServletInfo();
    }

    public void service( ServletRequest request, ServletResponse response ) throws ServletException, IOException {
        try {
            delegate.service( request, response );
        } catch ( Throwable e ) {
            HttpServletRequest req = ( HttpServletRequest ) request;
            HttpSession sess = req.getSession();
            MbError mbError = ( MbError ) sess.getAttribute( "MbError" );
            mbError.prepareErrorPage( null, e.getMessage(), ( Exception ) e, "javascript:history.back(1)", "bienvenida" );
            redirectToErrorPage( ( HttpServletRequest ) request, ( HttpServletResponse ) response );
        }
    }

    private void redirectToErrorPage( HttpServletRequest request, HttpServletResponse response ) throws IOException {
        if ( !"".equals( errorPage ) ) {
            response.sendRedirect( request.getContextPath() + errorPage );
        }
    }
}
