package cl.bicevida.core.view.filters;
/*
  * ===========================================================================
  * Licensed Materials - Property of BiceVida
  * "Restricted Materials of BiceVida"
  * ===========================================================================
  * Created on 17-04-2008
  * BiceVida
  * ===========================================================================
  * Copyright 2008 BiceVida, Inc. All rights reserved.
  * ===========================================================================
  */
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * <br>La clase SessionExpiredFilter es un filtro de servlet que detecta
 * cuándo una sessión http ha sido invalidada (ya sea por una llamada al método
 * invalidate() o por que se ha cumplido el timeout de la session http) y realiza
 * una redirección a una página de aviso de expiración de sesión.
 * La página de aviso de expiración de sesión por defecto está definida en la
 * constante SESSION_EXPIRED_PAGE.
 * El filtro también puede leer el valor del par´metro de contexto (definido
 * en el archivo web.xml) "cl.bicevida.core.session_expired_page" para 
 * establecer una página de aviso de expiración personalizada.
 * 
 * @author Rodrigo López G.
 * @version 1.0, 17-04-2008
 * @since CoreWebApp1.0
 */
public class SessionExpiredFilter implements Filter {
    public static final String SESSION_EXPIRED_PAGE = "/faces/infraestructura/SessionExpired.jsp";
    public static final String LOGOUT_IMG_PAGE = "/infraestructura/LogoutImg.jsp";
    private FilterConfig _filterConfig = null;
    private String sessionExpiredPage = null;

    public void init( FilterConfig filterConfig ) throws ServletException {
        sessionExpiredPage = SESSION_EXPIRED_PAGE;
        _filterConfig = filterConfig;
        if ( _filterConfig.getInitParameter( "cl.bicevida.core.session_expired_page" ) != null ) {
            sessionExpiredPage = _filterConfig.getInitParameter( "cl.bicevida.core.session_expired_page" );
        }
    }

    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException, ServletException {
        if ( ( ( HttpServletRequest ) request ).getRequestedSessionId() != null && !( ( HttpServletRequest ) request ).isRequestedSessionIdValid() && !LOGOUT_IMG_PAGE.equals( ( ( HttpServletRequest ) request ).getServletPath() ) ) {
            System.out.println( ( ( HttpServletRequest ) request ).getServletPath() );
            RequestDispatcher rd = request.getRequestDispatcher( sessionExpiredPage );
            rd.forward( request, response );
        } else
            chain.doFilter( request, response );
    }

    public void destroy() {
        _filterConfig = null;
    }
}
