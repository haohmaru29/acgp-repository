package cl.reduc.commons.odbc.orm;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class ResultManager {
    
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private CallableStatement callableStatement;
    private Statement statement;
    private String poolName;
    private ConnectionManager connectionManager;
    private Connection connection;
    private List resultList;
    private String errorMessage;
    private Integer rowsAffected;
    
    public ResultManager() {
        resultList = new ArrayList();
    }
    
    private synchronized void initConnection() {
        try {
        connectionManager = ConnectionManager.getInstance();
        connection = connectionManager.getConnection();
        } catch(SQLException e ) {}
    }
    
    public void execute(String query) {
        try {
            initConnection();            
            preparedStatement = connection.prepareStatement(query);
            Logger.getLogger(getClass()).info("Query [executed]: ".concat(query) );
            buildMap();
            
        } catch(SQLException e) {
            Logger.getLogger(getClass()).error(e);
        } finally {
            release();
        }
    }
    
    public void executeCall(String query) {
        try {
            initConnection();
            callableStatement = connection.prepareCall("{ call " + query + " }");
            
            resultSet = callableStatement.executeQuery();
            buildMap();
            
        } catch(SQLException e) {
            Logger.getLogger(getClass()).error(e);
        } finally {
            release();
        }
    }
    
    private void buildMap() {
        ResultSetMetaData rsMetaData;
        try {
            rsMetaData = resultSet.getMetaData();
            if(!resultList.isEmpty()) resultList.clear();
            
            while(resultSet.next()) {
                Map o = new HashMap();
                for(int x=1; x <= rsMetaData.getColumnCount(); x++) {
                    String columnName = rsMetaData.getColumnName(x);
                    o.put(columnName, resultSet.getString(columnName) );
                }
                resultList.add(o);
                
            }
        } catch(SQLException e ) {
            Logger.getLogger(getClass()).error(e);
        }
    }
    
    public List getList() {
        return (List) ((ArrayList)resultList).clone();
    }
    
    public void closeConnection() {
        try {
            connection.close();
        } catch(SQLException e ) {
            Logger.getLogger(getClass()).error(e);
        } 
    }
    
    public void release() {
        try {
            if(resultSet!= null) resultSet.close();
            if(callableStatement != null) callableStatement.close();
            if(preparedStatement != null ) preparedStatement.close();
        } catch(SQLException e) {
            Logger.getLogger(getClass()).error(e);
        }
    }
    
}