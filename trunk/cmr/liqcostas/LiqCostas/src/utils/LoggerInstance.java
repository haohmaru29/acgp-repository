package utils;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.File;

public class LoggerInstance {

	private static Logger logger;
	private static FileHandler f;
	private static String fechaActual;
	
	private static Logger getLogger(Object ob) {
		
		if(fechaActual==null) {
			fechaActual = getFecha("ddMMyyyy");
		} else {
			if(!fechaActual.equals(getFecha("ddMMyyyy"))) {
				f.close();
				f=null;
				fechaActual = getFecha("ddMMyyyy");
			}
		}
		
		if(f==null) {
			ResourceBundle rs = ResourceBundle.getBundle("log4j");
			try {
				f = new FileHandler(System.getProperty(rs.getString("file.appender")) + File.separator + rs.getString("file.Name") +"_" + fechaActual +  ".log", true);
				f.setFormatter(new Formatter(){
					public String format(LogRecord rec) {
			            StringBuffer buf = new StringBuffer(1000);
			            buf.append(getFecha("dd-MM-yyyy HH:mm:ss"));
			            buf.append(' ');
			            buf.append(rec.getLoggerName() );
			            buf.append(' ');
			            buf.append(rec.getLevel());
			            buf.append(' ');
			            buf.append(rec.getMessage());
			            buf.append('\n');
			            return buf.toString();
			        }
				});
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		logger =  Logger.getLogger(ob.toString());
		logger.addHandler(f);
		
		return logger;
	}
	
	public static void error(Object clazz, Throwable e) {
		getLogger(clazz).log(Level.SEVERE, e.getMessage());
		f.flush();
		f.close();
		f=null;
	}
	
	private static String getFecha(String format) {
		SimpleDateFormat sd = new SimpleDateFormat(format);		
		return sd.format(new Date()); 
	}
}
