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
package cl.bicevida.core.view.resources.messages;

import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * <br>La clase Resources permite leer propiedqades desde archivos de recurso.
 * @author Rodrigo López G.
 * @version 1.0, 17-04-2008
 * @since CoreWebApp1.0
 */
public class Resources {
    public Resources() {
    }

    public static String getMessage( String key ) {
        String out = "";
        ResourceBundle res = PropertyResourceBundle.getBundle( "cl.bicevida.app.view.resources.messages.general" );
        out = res.getString( key );
        return out;
    }
}
