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

/**
 * <br>La clase ApplicationModelException es la clase padre de la jerarquía de
 * excepciones para la capa de Modelo de Negocio de la aplicación.
 * @author Rodrigo López G.
 * @version 1.0, 24-03-2008
 * @since CoreWebApp1.0
 */
public class ApplicationModelException extends Exception {
    protected Throwable causa;

    public ApplicationModelException() {
    }

    public ApplicationModelException( String message ) {
        super( message );
    }

    public ApplicationModelException( String message, Throwable cause ) {
        super( message, cause );
    }
}
