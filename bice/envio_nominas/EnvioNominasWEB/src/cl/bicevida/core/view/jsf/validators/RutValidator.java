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
package cl.bicevida.core.view.jsf.validators;

import cl.bicevida.core.utils.empresa.RutUtils;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * <br>La clase RutValidator es un validador de RUTs para para componentes JSF. 
 * @author Rodrigo López G.
 * @version 1.0, 20-03-2008
 * @since CoreWebApp1.0
 */
public class RutValidator implements Validator {
    //TODO: Llevar estos mensajes a un archivo de recursos.
    public static final String MSG_RUT_NO_VALIDO_TITLE = "RUT no válido";
    public static final String MSG_RUT_NO_VALIDO_FORMATO_INCORRECTO = "Formato incorrecto";
    public static final String MSG_RUT_NO_VALIDO_DV = "Dígito verificador no corresponde";

    public RutValidator() {
    }

    public void validate( FacesContext facesContext, UIComponent uiComponent, Object object ) {
        String theRut;
        FacesMessage fm;
        theRut = object.toString();
        if ( !RutUtils.isRutMediumFormat( theRut ) ) {
            //Si el RUT no está en el formato esperado (Formato Medio, es decir sin puntos pero con guión)
            //se agrega un mensaje JSF con la descripción del problema.
            fm = new FacesMessage( MSG_RUT_NO_VALIDO_TITLE, MSG_RUT_NO_VALIDO_FORMATO_INCORRECTO );
            throw new ValidatorException( fm );
        }
        if ( !RutUtils.esRutValido( theRut ) ) {
            //Si el RUT no es válido, de acuerdo a la comprobación de su dígito verificador,
            //se agrega un mensaje JSF con la descripción del problema.
            fm = new FacesMessage( MSG_RUT_NO_VALIDO_TITLE, MSG_RUT_NO_VALIDO_DV );
            throw new ValidatorException( fm );
        }
    }
}
