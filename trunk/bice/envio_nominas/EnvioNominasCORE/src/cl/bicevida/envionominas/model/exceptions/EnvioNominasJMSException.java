package cl.bicevida.envionominas.model.exceptions;

public class EnvioNominasJMSException extends Exception {

    public EnvioNominasJMSException( String mensaje ) {
        super( mensaje );
    }

    public EnvioNominasJMSException( String mensaje, Throwable cause ) {
        super( mensaje, cause );
    }
}
