package bd;

import java.io.*; 
import javax.naming.*; 
import javax.sql.*;
import utils.LoggerInstance;

import java.sql.*;

public class DBAcceso {
    private Connection conn = null;
    private static DBAcceso instance;
    private DataSource ds;
    
    private DBAcceso() {
    	conn = null;
    	try {
			ds = (DataSource)new InitialContext().lookup("java:comp/env/jdbc/cyber8");
    	} catch(Exception e) {
    		LoggerInstance.error(Thread.currentThread().getStackTrace()[2] , e);
    	}
    }
    
    public static synchronized DBAcceso getInstance() {
    	if(instance == null) {
    		instance = new DBAcceso();
    	}
    	
    	return instance;
    }

    public Connection connect() throws SQLException, IOException, NamingException {
  		try {
  			conn = ds.getConnection();
  		} catch (Exception e){
  			LoggerInstance.error(Thread.currentThread().getStackTrace()[2] , e);
  			return null;
  		}
        return conn;
    }
    
    public static String buildProcedureCall(String packageName, String procedureName, int paramCount) {
        StringBuffer sb = new StringBuffer("{call "+packageName+"."+procedureName+"(");
        for (int n = 1; n <= paramCount; n++) {
            sb.append("?");
            if (n < paramCount) sb.append(",");
        }
        return sb.append(")}").toString();
    }
    
    public void rollback(Connection conn) {
    	try {
    		conn.rollback();
    	} catch(SQLException e) {
    		LoggerInstance.error(Thread.currentThread().getStackTrace()[2] , e);
    	}
    }

    public static void close(ResultSet rs, Statement s, Connection c) {
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (s != null) s.close(); } catch (Exception e) {}
        try { if (c != null) c.close(); } catch (Exception e) {}
    }
    
    public static void close(ResultSet rs, ResultSet rs2,Statement s, Connection c) {
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (rs2 != null) rs2.close(); } catch (Exception e) {}
        try { if (s != null) s.close(); } catch (Exception e) {}
        try { if (c != null) c.close(); } catch (Exception e) {}
    }
    
    public static void close(Statement s, Connection c) {
        try { if (s != null) s.close(); } catch (Exception e) {}
        try { if (c != null) c.close(); } catch (Exception e) {}
    }
}