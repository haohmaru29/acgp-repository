package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions;

public class DAOException extends Exception {
    protected Throwable causa;

    public DAOException() {
    }

    public DAOException( String message ) {
        super( message );
    }

    public DAOException( String message, Throwable cause ) {
        super( message, cause );
    }
}
