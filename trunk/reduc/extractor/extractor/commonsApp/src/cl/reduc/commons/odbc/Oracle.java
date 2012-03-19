package cl.reduc.commons.odbc;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class Oracle {
    
      private ResourceBundle proper;
      private Connection conn;
      private Statement statement;
      private InitialContext context;
      private DataSource jdbcURL;
      private static Map instances = new HashMap();
      private static final Logger logger = Logger.getLogger(Oracle.class);
      private static ThreadLocal threadLocal = new ThreadLocal();;
	
      private Oracle(String jndiName) {
          try {
              context = new InitialContext();
              jdbcURL = (DataSource) context.lookup(jndiName);
              this.threadLocal.set(jdbcURL.getConnection() );
          } catch(NamingException e) {
              logger.error(e);
          } catch(SQLException  e ) {
              logger.error(e);
          }
      }
  
      private Oracle(ResourceBundle proper) {
          this.proper= proper;
      }
      
      public static Oracle getInstances(String jndiName) {
          if(instances.get(jndiName) == null) {
              instances.put(jndiName, new Oracle(jndiName));
          }   
          return (Oracle) instances.get(jndiName);
      } 
      
      public Connection getJNDIConnection() throws SQLException{
        /*
          Connection connection = (Connection)this.threadLocal.get();
          try {
            if(connection == null) {
              System.out.println("Es null");
              connection =jdbcURL.getConnection();
              this.threadLocal.set( connection );
            }
            
          }catch(SQLException e) {
              logger.error(e);
          }
          
          return connection;
          */
          
          return jdbcURL.getConnection();
      }
      
      public void testConnection() throws SQLException {
          conn= jdbcURL.getConnection();
      }
		
      public Statement getJNDIStatement() throws SQLException  {
          return getJNDIConnection().createStatement();
      }
    
      public void closeConnection(Connection conn) {
        try {
          if(conn != null) {
            conn.close();
          }
        } catch (SQLException e) {
            logger.error(e);
        }
      }
	
      public void closeConnection() {
        try {
          if(conn!= null) {
            conn.close();
          }
        } catch(SQLException e) {
            logger.error(e);
        }
      }
	
      public void closeAll() {
        try {
            if(statement!= null ) {
                statement.close();
            }
            
            if(conn != null ) {
              conn.close();
            }
        } catch(SQLException e ) {
            logger.error(e);
        }
      }
}