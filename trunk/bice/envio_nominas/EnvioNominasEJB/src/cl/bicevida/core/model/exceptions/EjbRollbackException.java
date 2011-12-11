/*
  * ===========================================================================
  * Licensed Materials - Property of BiceVida
  * "Restricted Materials of BiceVida"
  * ===========================================================================
  * Created on 02-10-2007
  * BiceVida
  * ===========================================================================
  * Copyright 2007 BiceVida, Inc. All rights reserved.
  * ===========================================================================
  */
package cl.bicevida.core.model.exceptions;

import javax.ejb.ApplicationException;

/**
 * <br>La clase EjbRollbackException es la clase padre las excepciones de 
 * Modelo de Negocio que provocan Rollback en las transaciones de EJBs 
 * manejadas por el contenedor.
 * @author Rodrigo López G.
 * @version 1.0, 24-03-2008
 * @since CoreWebApp1.0
 */
@ApplicationException( rollback = true )
public class EjbRollbackException extends ApplicationModelException {
    protected Throwable causa;

    public EjbRollbackException() {
    }

    public EjbRollbackException( String message ) {
        super( message );
    }

    public EjbRollbackException( String message, Throwable causa ) {
        super( message, causa );
    }
}
