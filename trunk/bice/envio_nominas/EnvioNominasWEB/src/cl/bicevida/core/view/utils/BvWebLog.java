package cl.bicevida.core.view.utils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class BvWebLog {
    public static final int LEVEL_ALL = Level.ALL_INT;
    public static final int LEVEL_DEBUG = Level.DEBUG_INT;
    public static final int LEVEL_ERROR = Level.ERROR_INT;
    public static final int LEVEL_FATAL = Level.FATAL_INT;
    public static final int LEVEL_INFO = Level.INFO_INT;
    public static final int LEVEL_OFF = Level.OFF_INT;
    public static final int LEVEL_TRACE = Level.TRACE_INT;
    public static final int LEVEL_WARN = Level.WARN_INT;

    public BvWebLog() {
    }

    public static void log( String name, int nivel, String mensaje, Throwable throwable ) {
        Logger log = Logger.getLogger( name );
        log.log( Level.toLevel( nivel ), mensaje, throwable );
    }

    public static void log( String name, int nivel, String mensaje ) {
        Logger log = Logger.getLogger( name );
        log.log( Level.toLevel( nivel ), mensaje );
    }
}
