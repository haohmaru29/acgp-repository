package cl.cmr.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class AppenderUtils {
	private FileAppender appender;
	private static Map<String, AppenderUtils> instances = new HashMap<String, AppenderUtils>();
	private static AppenderUtils instance;
	private static final String system = System.getProperty("jboss.server.log.dir");
	private static final String pattern = "%d{yyyy-MM-dd HH:mm:ss,SS} %-5p (%F:%L) - %m%n ";
	private PatternLayout layout;

	private AppenderUtils(){
	}
	
	public void setParams(Logger logger ) {
		layout = new PatternLayout(pattern);
		logger.addAppender(getAppender());
		logger.setLevel(Level.WARN);
	}

	public static AppenderUtils getInstance() {
		if(instance == null ){
			instance = new AppenderUtils();
		}
		
		return instance;
	}
	
	public static AppenderUtils getInstances(Logger logger) {
		if (instances.get(logger.getName()) == null) {
			//instances.put(logger.getName(), new AppenderUtils(logger));
		}

		return instances.get(logger.getName());
	}

	public FileAppender getAppender() {
		try {
			if (appender == null) {
				appender = new FileAppender(layout, system.concat(File.separator.concat("liqCostas-" + getActualDate() + ".log")), true);
				appender.setImmediateFlush(true);
				appender.setLayout(layout);
				appender.setAppend(true);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return appender;
	}
	
	public static String getActualDate() {
		String response = "";
		Calendar cal = GregorianCalendar.getInstance();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		response =simple.format(cal.getTime()); 
		
		return response;
	}
}
