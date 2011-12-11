package cl.bicevida.envionominas.model.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Clase utilitaria que permite manejar una fecha de forma simple, permitiendo 
 * crearla desde distintas fuentes, String, Calendar, Date, y con los formatos 
 * indicados. Además permite obtener literales de esta fecha en distintos 
 * formatos. Implementa métodos de comparación con otros SimpleDate.
 * @author Giorgio Gortaire V.
 */
public class SimpleDate implements java.io.Serializable, Cloneable, Comparable {
    private static final String s_defaultPattern = "dd/MM/yyyy";
    private static final double MILLIS_IN_ONE_SECOND = 1000;
    private static final double MILLIS_IN_ONE_MINUTE = 60000;
    private static final double MILLIS_IN_ONE_HOUR = 3600000;
    private static final double MILLIS_IN_ONE_DAY = 86400000;
    private static final String[] DAY_OF_WEEKS = { "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday" };

    public static String getDefaultPattern() {
        return s_defaultPattern;
    }
    private int year = 1900;
    private int month = 1;
    private int day = 1;
    private int hour = 0;
    private int min = 0;
    private int sec = 0;
    private int millisec = 0;
    private int amPm = Calendar.PM;

    /**
     * Constructor por defecto, construye una fecha en base al calendar por defecto.
     */
    public SimpleDate() {
        load( Calendar.getInstance() );
    }

    public SimpleDate( int p_year, int p_month, int p_day ) {
        this( p_year, p_month, p_day, 0, 0, 0, 0 );
    }

    public SimpleDate( int p_year, int p_month, int p_day, int p_hour ) {
        this( p_year, p_month, p_day, p_hour, 0, 0, 0 );
    }

    public SimpleDate( int p_year, int p_month, int p_day, int p_hour, int p_min ) {
        this( p_year, p_month, p_day, p_hour, p_min, 0, 0 );
    }

    public SimpleDate( int p_year, int p_month, int p_day, int p_hour, int p_min, int p_sec ) {
        this( p_year, p_month, p_day, p_hour, p_min, p_sec, 0 );
    }

    /**
     * Inicializa este objeto con el año, mes, dia, hora, minutos, segundos, y
     * milisegundos especificados.
     * @param p_year int
     * @param p_month int
     * @param p_day int
     * @param p_hour int
     * @param p_min int
     * @param p_sec int
     * @param p_millisec int
     */
    public SimpleDate( int p_year, int p_month, int p_day, int p_hour, int p_min, int p_sec, int p_millisec ) {
        setYear( p_year );
        setMonth( p_month );
        setDay( p_day );
        setHour( p_hour );
        setMin( p_min );
        setSec( p_sec );
        setMillisec( p_millisec );
    }

    /**
     * Parsea la fecha contenida en el String con el patron por defecto, la
     * normaliza y carga el año, mes y dia en este objeto. 
     * @param p_date la fecha como String
     * @throws ParseException si falla el parseo de la fecha.
     */
    public SimpleDate( String p_date ) throws ParseException {
        load( p_date );
    }

    /**
     * Parsea la fecha contenida en el String con el patron especificado, la
     * normaliza y carga el año, mes y dia en este objeto.
     * @param p_date la fecha como String
     * @param p_pattern el patron para interpretar la fecha, igual a 
     * java.text.SimpleDateFormat.
     * @throws ParseException si falla el parseo de la fecha.
     */
    public SimpleDate( String p_date, String p_pattern ) throws ParseException {
        load( p_date, p_pattern );
    }

    /**
     * Inicializa este objeto con la fecha especificada.
     * @param p_date Date
     */
    public SimpleDate( Date p_date ) {
        load( p_date );
    }

    /**
     * Inicializa este objeto con el calendario especificado.
     * @param p_calendar Calendar
     */
    public SimpleDate( Calendar p_calendar ) {
        load( p_calendar );
    }

    public int hashCode() {
        return year + month + day + hour + min + sec + millisec;
    }

    public boolean equals( Object p_other ) {
        boolean areEqual = this == p_other;
        if ( !areEqual && getClass().isInstance( p_other ) && p_other.getClass().isInstance( this ) ) {
            SimpleDate o = ( SimpleDate ) p_other;
            areEqual = year == o.year && month == o.month && day == o.day && hour == o.hour && min == o.min && sec == o.sec && millisec == o.millisec;
        }
        return areEqual;
    }

    /**
     * Retorna una copia de este objeto.
     * @return Object
     */
    public Object clone() {
        return new SimpleDate( year, month, day, hour, min, sec, millisec );
    }

    /**
     * Retorna un String que representa este SimpleDate con el formato utilizado
     * por defecto.
     * @return String
     */
    public String toString() {
        return toString( getDefaultPattern() );
    }

    /**
     * Retorna un String con una fecha formateada de acuerdo al patron
     * especificado. El patron sigue la norma de java.text.SimpleDateFormat.
     * @param p_pattern patron para formatear fecha.
     * @return String
     */
    public String toString( String p_pattern ) {
        Locale locale = new Locale( "es" );
        SimpleDateFormat sdf = new SimpleDateFormat( p_pattern, locale );
        return sdf.format( toDate() );
    }

    public int getMillisec() {
        return millisec;
    }

    public void setMillisec( int p_millisec ) {
        millisec = p_millisec;
    }

    public int getSec() {
        return sec;
    }

    public void setSec( int p_sec ) {
        sec = p_sec;
    }

    public int getMin() {
        return min;
    }

    public void setMin( int p_min ) {
        min = p_min;
    }

    public int getHour() {
        return hour;
    }

    public void setHour( int p_hour ) {
        hour = p_hour;
    }

    public void setAmPm( int p_amPm ) {
        amPm = p_amPm;
    }

    public int getAmPm() {
        return amPm;
    }

    public int getDay() {
        return day;
    }

    public void setDay( int p_day ) {
        day = p_day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth( int p_month ) {
        month = p_month;
    }

    public int getYear() {
        return year;
    }

    public void setYear( int p_year ) {
        year = p_year;
    }

    /**
     * Obtiene un entero que representa el dia de la semana. <br>
     * Utiliza las constantes de Calendar
     * @return int
     */
    public int getDayOfWeek() {
        return toCalendar().get( Calendar.DAY_OF_WEEK );
    }

    /**
     * Normaliza este SimpleDate, convirtiendolo a calendar y volviendolo a
     * SimpleDate
     * @return
     */
    public SimpleDate normalize() {
        return new SimpleDate( toCalendar() );
    }

    /**
     * Extrae el año, mes y dia desde el calendario especificado y carga este
     * objeto. Si p_cal es null, no hace nada.
     * @param p_cal Calendar
     */
    private void load( Calendar p_cal ) {
        if ( p_cal != null ) {
            setYear( p_cal.get( Calendar.YEAR ) );
            setMonth( p_cal.get( Calendar.MONTH ) - Calendar.JANUARY + 1 );
            setDay( p_cal.get( Calendar.DAY_OF_MONTH ) );
            setHour( p_cal.get( Calendar.HOUR_OF_DAY ) );
            setAmPm( p_cal.get( Calendar.AM_PM ) );
            setMin( p_cal.get( Calendar.MINUTE ) );
            setSec( p_cal.get( Calendar.SECOND ) );
            setMillisec( p_cal.get( Calendar.MILLISECOND ) );
        }
    }

    /**
     * Carga una fecha con la cadena pasada como parametro y el formato
     * utilizado por defecto.
     * @param p_date String, cadena de texto que representa la fecha a cargar.
     * @throws ParseException
     */
    private void load( String p_date ) throws ParseException {
        load( p_date, getDefaultPattern() );
    }

    /**
     * Carga una fecha pasada como String, con el formato indicado. El patron
     * sigue la norma de java.text.SimpleDateFormat.
     * @param p_date String, cadena en texto a convertir.
     * @param p_pattern String, formato de la cadena.
     * @throws ParseException
     */
    private void load( String p_date, String p_pattern ) throws ParseException {
        Locale locale = new Locale( "es" );
        SimpleDateFormat sdf = new SimpleDateFormat( p_pattern, locale );
        load( ( Date ) sdf.parse( p_date ) );
    }

    /**
     * Utiliza un calendar por defecto para extraer la fecha especificada en el
     * parametro de entrada y cargarla en este objeto. Si la fecha es null, no
     * hace nada.
     * @param p_date Date
     */
    private void load( Date p_date ) {
        if ( p_date != null ) {
            Calendar cal = Calendar.getInstance();
            cal.setTime( p_date );
            load( cal );
        }
    }

    /**
     * Retorna un Date que representa esta fecha. Utiliza el Calendar
     * especificado.
     * @param p_cal Calendar
     * @return Date
     */
    public Date toDate( Calendar p_cal ) {
        if ( p_cal == null ) {
            p_cal = Calendar.getInstance();
        }
        p_cal.set( Calendar.YEAR, getYear() );
        p_cal.set( Calendar.MONTH, ( getMonth() - 1 ) + Calendar.JANUARY );
        p_cal.set( Calendar.DAY_OF_MONTH, getDay() );
        p_cal.set( Calendar.HOUR_OF_DAY, getHour() );
        p_cal.set( Calendar.MINUTE, getMin() );
        p_cal.set( Calendar.SECOND, getSec() );
        p_cal.set( Calendar.MILLISECOND, getMillisec() );
        // p_cal.set(Calendar.AM_PM, getAmPm());
        return p_cal.getTime();
    }

    /**
     * Retorna un Date que representa esta fecha. Utiliza el Calendar por
     * defecto.
     * @return Date
     */
    public Date toDate() {
        return toDate( null );
    }

    /**
     * Retorna un Calendar (por defecto).
     * @return Calendar
     */
    public Calendar toCalendar() {
        Calendar result = Calendar.getInstance();
        toDate( result );
        return result;
    }

    /**
     * Agrega la cantidad especificada en dias.
     * @param p_days double, dias a agregar
     * @return SimpleDate
     */
    public SimpleDate addDays( double p_days ) {
        int completeDays = ( int ) p_days;
        double datePart = ( p_days - completeDays ) * MILLIS_IN_ONE_DAY;
        Calendar c = Calendar.getInstance();
        Date currentDate = toDate( c );
        c.setTime( currentDate );
        c.add( Calendar.DATE, ( int ) p_days );
        c.add( Calendar.MILLISECOND, ( int ) datePart );
        return new SimpleDate( c );
    }

    /**
     * Agrega la cantidad especificada en meses.
     * @param p_months int, meses a agregar
     * @return SimpleDate
     */
    public SimpleDate addMonths( int p_months ) {
        Calendar c = Calendar.getInstance();
        Date currentDate = toDate( c );
        c.setTime( currentDate );
        c.add( Calendar.MONTH, p_months );
        return new SimpleDate( c );
    }

    /**
     * Agrega la cantidad especificada en horas.
     * @param p_hours double, horas a agregar
     * @return SimpleDate
     */
    public SimpleDate addHours( double p_hours ) {
        return addMillis( p_hours * MILLIS_IN_ONE_HOUR );
    }

    /**
     * Agrega la cantidad especificada en minutos.
     * @param p_minutes double, minutos a agregar
     * @return SimpleDate
     */
    public SimpleDate addMinutes( double p_minutes ) {
        return addMillis( p_minutes * MILLIS_IN_ONE_MINUTE );
    }

    /**
     * Agrega la cantidad especificada en segundos.
     * @param p_seconds double, segundos a agregar
     * @return SimpleDate
     */
    public SimpleDate addSeconds( double p_seconds ) {
        return addMillis( p_seconds * MILLIS_IN_ONE_SECOND );
    }

    /**
     * Agrega la cantidad especificada en milisegundos.
     * @param p_millis double, milisegundos a agregar
     * @return SimpleDate
     */
    public SimpleDate addMillis( double p_millis ) {
        Calendar c = Calendar.getInstance();
        Date currentDate = toDate( c );
        c.setTime( currentDate );
        c.add( Calendar.MILLISECOND, ( int ) p_millis );
        return new SimpleDate( c );
    }

    /**
     * Compara este SimpleDate con otro objeto pasado por parametro. <br>
     * Debe ser un SimpleDate 
     * @param p_other SimpleDate, fecha a comparar
     * @return -1, 0 or 1, corresponding to interface Comparable.
     */
    public int compareTo( Object p_other ) {
        return toDate().compareTo( ( ( SimpleDate ) p_other ).toDate() );
    }

    /**
     * Devuelve un boolean que indica si la fecha pasada por parametro es mayor
     * @param p_date SimpleDate
     * @return boolean, verdadero si el SimpleDate pasado por parametro es mayor, 
     * falso de otra manera.
     */
    public boolean lessThan( SimpleDate p_date ) {
        if ( compareTo( p_date ) < 0 ) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Devuelve un boolean que indica si la fecha pasada por parametro es mayor
     * o igual.
     * @param p_date SimpleDate
     * @return boolean, verdadero si el SimpleDate pasado por parametro es igual 
     * o mayor, falso de otra manera.
     */
    public boolean lessOrEqualThan( SimpleDate p_date ) {
        if ( compareTo( p_date ) <= 0 ) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Permite agregar unidades p_amount al campo del SimpleDate indicado por
     * p_field <br>
     * Utiliza las constantes del Calendar
     * @param p_field int, campo al cual agregar unidades
     * @param p_amount int, unidades a agregar
     * @return
     */
    public SimpleDate add( int p_field, int p_amount ) {
        Calendar c = Calendar.getInstance();
        c.setTime( toDate() );
        c.add( p_field, p_amount );
        return new SimpleDate( c.getTime() );
    }

    /**
     * Devuelve un boolean que indica si la fecha pasada por parametro es menor
     * @param p_date SimpleDate
     * @return boolean, verdadero si el SimpleDate pasado por parametro es menor, 
     * falso de otra manera.
     */
    public boolean greaterThan( SimpleDate p_date ) {
        if ( compareTo( p_date ) > 0 ) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Devuelve un boolean que indica si la fecha pasada por parametro es menor
     * o igual 
     * @param p_date SimpleDate
     * @return boolean, verdadero si el SimpleDate pasado por parametro es igual 
     * o menor, falso de otra manera.
     */
    public boolean greaterOrEqualThan( SimpleDate p_date ) {
        if ( compareTo( p_date ) >= 0 ) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Obtiene una cadena para ser utilizada como key de archivos de recursos,
     * representando el nombre del mes 
     * @return String, cadena de texto que representa una key
     */
    public String getMonthResourceKey() {
        String result = null;
        result = SimpleDate.class.getName() + ".month." + getMonth();
        return result;
    }

    /**
     * Obtiene una cadena para ser utilizada como key de archivos de recursos,
     * representando el dia de la semana 
     * @return String, cadena de texto que representa una key
     */
    public String getDayOfWeekResourceKey() {
        String result = null;
        result = SimpleDate.class.getName() + ".dayOfWeek." + DAY_OF_WEEKS[ getDayOfWeek() - Calendar.MONDAY ];
        return result;
    }

    /**
     * Método que a partir de 2 fechas retorna la cantidad de dias entre esta 
     * fechas.
     * Registro de versiones:
     * 1.0 (04/07/2008 Giorgio Gortaire)- versión inicial
     */
    public long getNumberOfDaysBetweenTwoDates( SimpleDate simpledate ) {
        long time = this.toDate().getTime() - simpledate.toDate().getTime();
        //Muestro el resultado en días
        long dias = time / ( 3600 * 24 * 1000 );
        long resto = time % ( 3600 * 24 * 1000 );
        if(resto >0){
            dias += 1;
        }
        return Math.abs( dias );
    }

    public long getMonthsBetween( SimpleDate simpledate ) {
        long anio1 = simpledate.greaterOrEqualThan( this ) ? simpledate.getYear() : this.getYear();
        long anio2 = this.lessOrEqualThan( simpledate ) ? this.getYear() : simpledate.getYear();
        long mes1 = simpledate.greaterOrEqualThan( this ) ? simpledate.getMonth() : this.getMonth();
        long mes2 = this.lessOrEqualThan( simpledate ) ? this.getMonth() : simpledate.getMonth();
        return Math.abs( anio1 - anio2 ) * 12 - mes2 + mes1;
    }
}
