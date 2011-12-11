package cl.bicevida.envionominas.model.config;

import java.io.FileInputStream;

import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * Clase que permite leer los archivos de propiedades .properties
 */
public class PropertiesLoader {
    private static final String SUFIJO = ".properties";
    private static final Logger log = Logger.getLogger( PropertiesLoader.class );

    /**
     * Carga un archivo de propiedades. El archivo a cargar debe encontrarse en
     * el contexto del classloader de la aplicacion.
     * 
     * @param fileName
     * @return Una instancia que representa la informacion almacenada en el 
     * archivo identificado por el parametro "fileName".
     * @throws Exception
     */
    public Properties loadProperties( String fileName ) throws Exception {
        log.info( "Iniciando carga de recurso [" + fileName + "]" );
        Properties propInstance = null;
        if ( fileName == null ) {
            throw new IllegalArgumentException( "El parametro de entrada fileName = " + fileName );
        }
        if ( fileName.endsWith( this.SUFIJO ) ) {
            fileName = fileName.substring( 0, fileName.length() - this.SUFIJO.length() );
        }
        final ResourceBundle aResourceBundle = ResourceBundle.getBundle( fileName );
        System.out.println( "Recurso ENCONTRADO " );
        propInstance = new Properties();
        Enumeration keys = aResourceBundle.getKeys();
        while ( keys.hasMoreElements() ) {
            String key = ( String ) keys.nextElement();
            String value = aResourceBundle.getString( key );
            log.info( "Cargando " + key + ":" + value );
            propInstance.put( key, value );
        }
        return ( propInstance );
    }

    /**
     * Carga un archivo de propiedades. El archivo a cargar se encuentra en el
     * file system del servidor.
     * 
     * @param fileName nombre del archivo, debe indicarse el path o ruta 
     * completa al archivo, incluyendo su extension (properties).
     * @return La instancia de Properties asociada al archivo pasado por 
     * parametro.
     * @throws Exception
     */
    public Properties loadPropertiesFromFileSystem( String fileName ) throws Exception {
        log.info( "Iniciando carga de recurso [" + fileName + "]" );
        Properties propInstance = null;
        if ( fileName == null ) {
            throw new IllegalArgumentException( "El parametro de entrada fileName = [" + fileName + "]" );
        }
        log.info( "Buscando el recurso :" + fileName );
        FileInputStream input = new FileInputStream( fileName );
        log.info( "Recurso ENCONTRADO " );
        propInstance = new Properties();
        propInstance.load( input );
        input.close();
        return ( propInstance );
    }
}
