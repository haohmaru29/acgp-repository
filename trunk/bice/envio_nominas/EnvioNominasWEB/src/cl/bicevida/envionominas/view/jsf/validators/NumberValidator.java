package cl.bicevida.envionominas.view.jsf.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class NumberValidator implements Validator {
    public static final String MENSAJE_ERROR = "Numero no válido";
    public static final String MENSAJE_FORMATO_INCORRECTO = "Formato incorrecto";

    public void validate( FacesContext facesContext, UIComponent uiComponent, Object object ) {
        String valor = ( String ) object;
        FacesMessage nv;
        boolean num = false;
        boolean letra = false;
        for ( int i = 0; i < valor.length(); i++ ) {
            if ( Character.isDigit( valor.charAt( i ) ) ) {
                num = true;
            } else {
                letra = true;
                nv = new FacesMessage( MENSAJE_ERROR, MENSAJE_FORMATO_INCORRECTO );
                throw new ValidatorException( nv );
            }
        }
    }
}
