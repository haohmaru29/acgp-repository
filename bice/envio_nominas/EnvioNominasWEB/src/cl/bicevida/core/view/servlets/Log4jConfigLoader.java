package cl.bicevida.core.view.servlets;
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
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * <br>La clase Log4jConfigLoader es un servlet que tiene por objetivo cargar
 * la configuración de Log4j.
 * Este servlet está configurado en el archivo web.xml de la siguiente manera:
      <servlet>
        <servlet-name>Log4jConfigLoader</servlet-name>
        <servlet-class>cl.bicevida.app.view.servlets.Log4jConfigLoader</servlet-class>
        <load-on-startup>10</load-on-startup>   
    </servlet>
    <servlet-mapping>
         <servlet-name>Log4jConfigLoader</servlet-name>
         <url-pattern>/log4jconfigloader</url-pattern>
    </servlet-mapping>
 * Lo importante es la presencia del elemento load-on-startup para que el servlet
 * se inicie cuando se carga la aplicación web.
 * @author Rodrigo López G.
 * @version 1.0, 17-04-2008
 * @since CoreWebApp1.0
 */
public class Log4jConfigLoader extends HttpServlet {
    @Override
    public void destroy() {
        super.destroy();
    }

    public void init() throws ServletException {
        super.init();
        PropertyConfigurator.configure( Log4jConfigLoader.class.getResource( "/log4j.properties" ) );
    }
}
