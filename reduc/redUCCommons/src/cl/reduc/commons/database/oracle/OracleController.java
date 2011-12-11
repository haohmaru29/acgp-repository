package cl.reduc.commons.database.oracle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import cl.reduc.commons.utils.MessagesUtils;

public class OracleController {
	
	protected ODBCOracle oracle;
	
	public OracleController() {
		oracle = ODBCOracle.getInstance();
	}
	
	public OracleController(ResourceBundle proper) {
		oracle = ODBCOracle.getInstance(proper);
	}
	
	public ResultSet select(String query) {
		Statement st = oracle.getStatement();
		ResultSet rs = null;
		try {
			System.out.println("Ejecutando Consulta: " + query);
			rs = st.executeQuery(query);
		} catch (SQLException e) {
			MessagesUtils.errorMessage(e);
		}
		
		return rs;
	}
	
	public int update(String query )  {
		Connection conn = oracle.getConnection();
		Statement st =null;
		int value=-1;
		try {
			conn.setAutoCommit(false);
			st = oracle.getStatement();
			System.out.println(query);
			value = st.executeUpdate(query);
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if(st!= null) closeStatement(st);
			//if(conn != null ) closeConnection(conn);
		}
		
		return value;

	}
	
	public void closeStatement(Statement st) {
		try {
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
	
	public void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeResultSet(ResultSet rs) {
		if(rs != null ) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
