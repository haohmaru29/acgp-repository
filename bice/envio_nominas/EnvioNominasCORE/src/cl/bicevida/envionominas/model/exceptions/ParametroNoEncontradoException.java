package cl.bicevida.envionominas.model.exceptions;

public class ParametroNoEncontradoException extends Exception {

    public ParametroNoEncontradoException( String mensaje ) {
        super( mensaje );
    }

    public ParametroNoEncontradoException( String mensaje, Throwable cause ) {
        super( mensaje, cause );
    }
}
