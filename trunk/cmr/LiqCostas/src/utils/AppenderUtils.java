package utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class AppenderUtils {
	private FileAppender appender;
	private static Map<String, AppenderUtils> instances = new HashMap<String, AppenderUtils>();
	private static final String system = System.getProperty("jboss.server.log.dir");
	private static final String pattern = "%d{ISO8601} [%l ] " + " %m %n %n";
	private PatternLayout layout;

	private AppenderUtils(Logger logger) {
		layout = new PatternLayout(pattern);
		logger.addAppender(getAppender());
		logger.setLevel(Level.ERROR);
	}

	public static AppenderUtils getInstance(Logger logger) {
		if (instances.get(logger.getName()) == null) {
			instances.put(logger.getName(), new AppenderUtils(logger));
		}

		return instances.get(logger.getName());
	}

	public FileAppender getAppender() {
		try {
			if (appender == null) {
				appender = new FileAppender(layout, system.concat(File.separator.concat("liqCostas.log")), false);
				appender.setImmediateFlush(true);
				appender.setLayout(layout);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return appender;
	}
}
