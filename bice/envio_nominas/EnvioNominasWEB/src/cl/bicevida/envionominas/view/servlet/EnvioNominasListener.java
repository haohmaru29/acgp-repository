package cl.bicevida.envionominas.view.servlet;

import cl.bicevida.envionominas.model.services.ejb.EnvioNominasEJBLocal;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class EnvioNominasListener implements ServletContextListener {
    protected EnvioNominasEJBLocal ejbNominas;

    public void contextInitialized( ServletContextEvent servletContextEvent ) {
        System.out.println( "contextInitialized([" + servletContextEvent + "])" );
        ejbNominas = ( EnvioNominasEJBLocal ) loadEjbReferences( "EnvioNominasEJB" );
        try {
            ejbNominas.inicializarScheduler();
        } catch ( Exception e ) {
            // TODO
        }
    }

    public void contextDestroyed( ServletContextEvent servletContextEvent ) {
    }

    private Object loadEjbReferences( String ejbName ) {
        Object ejbReference = null;
        InitialContext ctx = null;
        try {
            ctx = this.getInitialContext();
        } catch ( NamingException e ) {
            e.printStackTrace();
        }
        try {
            ejbReference = ctx.lookup( "ejb/local/" + ejbName );
        } catch ( NamingException e ) {
            e.printStackTrace();
        }
        return ejbReference;
    }

    private InitialContext getInitialContext() throws NamingException {
        return new InitialContext();
    }
}
