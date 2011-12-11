package cl.bicevida.envionominas.model.exceptions;

public class EnvioNominasException extends Exception {

    public EnvioNominasException( String mensaje ) {
        super( mensaje );
    }

    public EnvioNominasException( String mensaje, Throwable cause ) {
        super( mensaje, cause );
    }
}
