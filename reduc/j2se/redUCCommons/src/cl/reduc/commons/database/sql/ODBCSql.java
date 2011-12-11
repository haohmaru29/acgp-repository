package cl.reduc.commons.database.sql;

public class ODBCSql {
	
	private static ODBCSql instance;
	
	private ODBCSql() {
	}

	public static synchronized ODBCSql getInstance() {
		if(instance==null) {
			instance = new ODBCSql();
		} 
		
		return instance;	
	}
	
}
