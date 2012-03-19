package cl.reduc.commons.odbc.orm;
import cl.reduc.commons.odbc.Oracle;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionManager {
    
    final boolean defaultReadOnly = false;
    final boolean defaultAutoCommit = true;
    
    private static ConnectionManager instance = new ConnectionManager();
    
    private ConnectionManager() {
        
    }
    
    public static ConnectionManager getInstance() {
        return instance;
    }
    
    public synchronized Connection getConnection() throws SQLException {
        return Oracle.getInstances(ResourceBundle.getBundle("db").getString("jndiName")).getJNDIConnection();
    }
}