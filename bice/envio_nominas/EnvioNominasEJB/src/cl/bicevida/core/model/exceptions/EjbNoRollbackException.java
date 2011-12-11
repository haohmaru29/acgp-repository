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
 * <br>La clase EjbNoRollbackException es la clase padre las excepciones de 
 * Modelo de Negocio que no provocan Rollback en las transaciones de EJBs 
 * manejadas por el contenedor.
 * @author Rodrigo López G.
 * @version 1.0, 24-03-2008
 * @since CoreWebApp1.0
 */
@ApplicationException( rollback = false )
public class EjbNoRollbackException extends ApplicationModelException {
    public EjbNoRollbackException() {
    }

    public EjbNoRollbackException( String message ) {
        super( message );
    }

    public EjbNoRollbackException( String message, Exception ex ) {
        super( message, ex );
    }
}
