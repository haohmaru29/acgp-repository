package cl.reduc.commons.odbc.controller;
import cl.reduc.commons.Application;
import cl.reduc.commons.odbc.Oracle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class OracleJNDIController {
    private static final Logger logger = Logger.getLogger(OracleJNDIController.class);
    private String jndiName;
    
    public OracleJNDIController() {
        jndiName =Application.getInstance().jndiDB();
    }
    
    public ResultSet select(String sql, Statement statement) throws SQLException, OutOfMemoryError {
        ResultSet rs=null;
        rs = statement.executeQuery(sql); 
        return rs;
    }
    
    public Connection getConnection() throws SQLException {
        return Oracle.getInstances(jndiName).getJNDIConnection();
    }
    
    public void closeConnection(Statement st) {
        try {
            if(st != null) st.close();
        } catch(SQLException e) {
            logger.error(e);
        }
    }
}