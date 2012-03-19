package cl.reduc.commons.odbc.controller;
import cl.reduc.commons.odbc.Oracle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Map;

import org.apache.log4j.Logger;

public class OracleController {
  
    protected Oracle odbc;
    private ResultSet rs;
    
  
    public ResultSet select(String sql) throws SQLException {
        Statement st = odbc.getJNDIStatement();
        Logger.getLogger(getClass()).info(sql);
        return st.executeQuery(sql);
    }
    
    public int save(Map params, String tableName) {
        int result = 0;
        String sql = "";
        
        return result;
    }
}