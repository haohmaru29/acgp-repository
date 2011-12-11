package cl.bicevida.envionominas.model.exceptions;

public class FechaPagoInvalidaException extends Exception {

    public FechaPagoInvalidaException( String mensaje ) {
        super( mensaje );
    }

    public FechaPagoInvalidaException( String mensaje, Throwable cause ) {
        super( mensaje, cause );
    }
}
