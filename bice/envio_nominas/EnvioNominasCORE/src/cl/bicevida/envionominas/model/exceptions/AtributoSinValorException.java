package cl.bicevida.envionominas.model.exceptions;

public class AtributoSinValorException extends Exception {

    public AtributoSinValorException( String mensaje ) {
        super( mensaje );
    }

    public AtributoSinValorException( String mensaje, Throwable cause ) {
        super( mensaje, cause );
    }
}
