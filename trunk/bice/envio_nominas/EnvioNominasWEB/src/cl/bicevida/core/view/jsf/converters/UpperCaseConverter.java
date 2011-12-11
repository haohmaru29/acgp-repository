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
package cl.bicevida.core.view.jsf.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * <br>La clase UpperCaseConverter es un convertidor de minúsculas a mayúsculas para componentes JSF.
 * @author Rodrigo López G.
 * @version 1.0, 20-03-2008
 * @since CoreWebApp1.0
 */
public class UpperCaseConverter implements Converter {
    public UpperCaseConverter() {
    }

    public Object getAsObject( FacesContext facesContext, UIComponent uIComponent, String string ) {
        return string;
    }

    public String getAsString( FacesContext facesContext, UIComponent uIComponent, Object object ) {
        return ( ( String ) object ).toUpperCase();
    }
}
