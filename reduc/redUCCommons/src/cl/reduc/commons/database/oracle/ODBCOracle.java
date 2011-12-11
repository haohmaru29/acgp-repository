package cl.reduc.commons.database.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;

import cl.reduc.commons.utils.MessagesUtils;

public class ODBCOracle {
	
	private static ODBCOracle instance;
	private ResourceBundle proper;
	private Connection conn;
	private Statement statement;
	
	private ODBCOracle() {
	}
	
	private ODBCOracle(ResourceBundle proper) {
		this.proper= proper;
	}
	
	public static synchronized ODBCOracle getInstance() {
		if(instance == null ) {
			instance = new ODBCOracle();
		}
		
		return instance;
	} 
	
	public static synchronized ODBCOracle getInstance(ResourceBundle proper) {
		if(instance == null ) {
			instance = new ODBCOracle(proper);
		}
		
		return instance;
	} 
	
	public Connection getConnection() {
		Locale.setDefault(new Locale("es", "ES"));
		proper = ResourceBundle.getBundle("db");
		if(conn== null) {
			System.out.println("Creando conexion");
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				String cadenaConexion ="jdbc:oracle:thin:" + proper.getString("db.user") 
		                + "/" +proper.getString("db.pass") + "@" + proper.getString("db.server")
		                + ":" + proper.getString("db.port") + ":" + proper.getString("db.sid");
		        
		        conn = DriverManager.getConnection(cadenaConexion);
			} catch (InstantiationException e) {
				MessagesUtils.errorMessage(e); System.exit(0);
			} catch (IllegalAccessException e) {
				MessagesUtils.errorMessage(e); System.exit(0);
			} catch (ClassNotFoundException e) {
				MessagesUtils.errorMessage(e); System.exit(0);
			} catch (SQLException e) {
				MessagesUtils.errorMessage(e); System.exit(0);
			}
		} else {
			System.out.println("Conexion existente");
		}
        
        return conn;
	}
	
	public Connection getConnection(String user, String pass, String ip, String port, String sid) {
		Locale.setDefault(new Locale("es", "ES"));
		proper = ResourceBundle.getBundle("db");
		if(conn== null) {
			System.out.println("Creando conexion");
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				String cadenaConexion ="jdbc:oracle:thin:" + user.trim() + "/" + pass.trim() + "@" + ip.trim()
		                + ":" + port.trim() + ":" + sid.trim();
		        
		        conn = DriverManager.getConnection(cadenaConexion);
			} catch (InstantiationException e) {
				MessagesUtils.errorMessage(e); System.exit(0);
			} catch (IllegalAccessException e) {
				MessagesUtils.errorMessage(e); System.exit(0);
			} catch (ClassNotFoundException e) {
				MessagesUtils.errorMessage(e); System.exit(0);
			} catch (SQLException e) {
				MessagesUtils.errorMessage(e); System.exit(0);
			}
		} 
        
        return conn;
	}
	
	public Statement getStatement() {
		Statement statement = null;
		try {
			closeStatement();
			statement = getConnection().createStatement();
		} catch (SQLException e) {
			MessagesUtils.errorMessage(e); System.exit(0);
		}
		
		return statement;
	}

	public void closeConnection(Connection conn) {
		try {
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			if(conn!= null) {
				conn.close();
				conn=null;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void closeStatement() {
		if(statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
