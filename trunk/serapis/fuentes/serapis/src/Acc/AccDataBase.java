package Acc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Vector;

public class AccDataBase {

	private static final String driver="org.gjt.mm.mysql.Driver";
	private static final String url="jdbc:mysql://localhost/test";
	private static final String user="root";
	private static final String pass="";
	//private static AccDataBase instance;
	
	public AccDataBase() {
		result = new Vector();
	}

	public int connect() {
		try {
			Class.forName(driver).newInstance();
			System.out.println("Driver JDBC Cargado!");
			return 1;
		} catch (Exception exception) {
			System.out.println("Imposible Cargar Driver JDBC");
		}
		return 0;
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, pass);
	}
	
	public void closeAll(Connection conn, ResultSet rs, Statement st) {
		try {
			if(rs != null) rs.close();
			if(st != null ) st.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int ConsultaDB(String s) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			connection = getConnection();
			connection.setReadOnly(true);
			statement = connection.createStatement();
			resultset = statement.executeQuery(s);
			ResultSetMetaData resultsetmetadata = resultset.getMetaData();
			new Integer(0);
			new Double(0.0D);
			new Long(0L);
			System.out.println(s);
			result.clear();
			int j = resultsetmetadata.getColumnCount();
			while (resultset.next()) {
				int k = 1;
				while (k <= j) {
					int i = resultsetmetadata.getColumnType(k);
					switch (i) {
					case -5: 
						Integer integer3 = new Integer(resultset.getInt(k));
						result.addElement(integer3);
						break;
					case -1:
					case 1: // '\001'
					case 12: // '\f'
						result.addElement(resultset.getString(k));
						break;

					case 4: // '\004'
					case 5: // '\005'
						Integer integer1 = new Integer(resultset.getInt(k));
						result.addElement(integer1);
						break;

					case 2: // '\002'
					case 3: // '\003'
					case 6: // '\006'
					case 8: // '\b'
						Double double2 = new Double(resultset.getDouble(k));
						result.addElement(double2);
						break;

					case 0: // '\0'
					case 7: // '\007'
					case 9: // '\t'
					case 10: // '\n'
					case 11: // '\013'
					default:
						byte abyte0[] = resultset.getBytes(k);
						result.addElement(abyte0);
						break;
					}
					k++;
				}
			}
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(connection, resultset, statement);
		}
		return 0;
	}

	public int ModificaDB(String s) {
		int i = -1;
		Connection conn = null;
		Statement st = null;
		try {
			conn = getConnection();
			st = conn.createStatement();
			i = st.executeUpdate(s);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, null, st);
		}
		return i;
	}

	public Vector getResult() {
		return result;
	}

	public void setResult(Vector vector) {
		result = vector;
	}

	private Vector result;
}