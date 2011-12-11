package cl.bicevida.envionominas.model.exceptions;

public class ActualizaArchivoNominaException extends Exception {

    public ActualizaArchivoNominaException() {
        super();
    }
    
    public ActualizaArchivoNominaException(String message) {
        super(message);
    }
    
    public ActualizaArchivoNominaException(String message, Throwable cause) {
        super(message, cause);
    }
}
