package cl.cmr.bd;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class DBAcceso {
    private Connection conn = null;
    private static DBAcceso instance;
    private DataSource ds;
    private static final Logger logger = Logger.getLogger(DBAcceso.class);
    
    private DBAcceso() {
    	try {
    		Context context =(Context) new InitialContext().lookup("java:comp/env");
    		ds = (DataSource) context.lookup("jdbc/cyber8");
    	} catch(Exception e) {
    		logger.error(" [LiqCostas] " , e);
    	}
    }
    
    public static DBAcceso getInstance() {
    	if(instance == null) {
    		instance = new DBAcceso();
    	}
    	
    	return instance;
    }

    public Connection connect() throws SQLException, IOException, NamingException {
  		try {
  			//logger.info(" [LiqCostas][Build conection] ");
  			conn = ds.getConnection();
  		} catch (Exception e){
  			logger.error(" [LiqCostas] " , e);
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
    		logger.error(" [LiqCostas] " , e);
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
    
    public static void close(Statement s, Connection c, PreparedStatement pst) {
        try { if (s != null) s.close(); } catch (Exception e) {}
        try { if (c != null) c.close(); } catch (Exception e) {}
        try { if (pst != null) pst.close(); } catch (Exception e) {}
    }
}