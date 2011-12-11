package cl.bicevida.envionominas.model.config;

import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Permite centralizar el acceso a los parametros de configuracion del sistema 
 * de envio de nominas.
 * 
 * IMPORTANTE: Las constantes definidas en esta clase, deben hacer referencia a
 * parametros definidos en el archivo 'config.properties' que concentra las 
 * configuraciones del sistema.
 */
public class EnvioNominasConfig {
    private static final Logger log = Logger.getLogger( EnvioNominasConfig.class );
    private static final String PROPERTIES = "config";
    public static final String NOMBRE_USUARIO = "NOMBRE.USUARIO";
    public static final String BANCO_PROCESO = "BANCO.PROCESO";
    public static final String ID_NOMINA_NUEVA = "ID.NOMINA.NUEVA";
    public static final String ID_NOMINA_AUTORIZADA = "ID.NOMINA.AUTORIZADA";
    public static final String ID_NOMINA_CANCELADA = "ID.NOMINA.CANCELADA";
    public static final String ID_NOMINA_REPROCESO = "ID.NOMINA.REPROCESO";
    public static final String ID_NOMINA_PENDIENTE = "ID.NOMINA.PENDIENTE";
    public static final String ID_NOMINA_ENVIADA = "ID.NOMINA.ENVIADA";
    public static final String ID_NOMINA_ENVIADA_MANUAL = "ID.NOMINA.ENVIADA.MANUAL";
    public static final String ID_NOMINA_CONCILIADA = "ID.NOMINA.CONCILIADA";
    public static final String ID_NOMINA_CONCILIADA_CON_ERROR = "ID.NOMINA.CONCILIADA.CON.ERROR";
    public static final String ID_NOMINA_EN_RENDICION = "ID.NOMINA.EN.RENDICION";
    public static final String ID_REGISTRO_CANCELADO = "ID.REGISTRO.CANCELADO";
    public static final String ID_REGISTRO_PAGADO = "ID.REGISTRO.PAGADO";
    public static final String ID_REGISTRO_RECHAZADO = "ID.REGISTRO.RECHAZADO";
    public static final String ID_REGISTRO_PENDIENTE = "ID.REGISTRO.PENDIENTE";
    public static final String ID_REGISTRO_ERROR = "ID.REGISTRO.ERROR";
    public static final String ID_PROCESO_PREPARANDO = "ID.PROCESO.PREPARANDO";
    public static final String ID_PROCESO_CANCELADO = "ID.PROCESO.CANCELADO";
    public static final String ID_PROCESO_ESPERANDO_AUTORIZACION = "ID.PROCESO.ESPERANDO.AUTORIZACION";
    public static final String ID_PROCESO_ESPERANDO_FOLIO = "ID.PROCESO.ESPERANDO.FOLIO";
    public static final String ID_PROCESO_ESPERANDO_RENDICION = "ID.PROCESO.ESPERANDO.RENDICION";
    public static final String ID_PROCESO_CONCILIANDO_REGISTROS = "ID.PROCESO.CONCILIANDO.REGISTROS";
    public static final String CONTEXT_FACTORY_CLASS = "CONTEXT.FACTORY.CLASS";
    public static final String CONTEXT_PROVIDER_URL = "CONTEXT.PROVIDER.URL";
    public static final String CONTEXT_SECURITY_PRINCIPAL = "CONTEXT.SECURITY.PRINCIPAL";
    public static final String CONTEXT_SECURITY_CREDENTIAL = "CONTEXT.SECURITY.CREDENTIAL";
    public static final String INTERVALO_PROCESO_BUSQUEDA = "INTERVALO.PROCESO.BUSQUEDA";
    public static final String INTERVALO_REPROCESO_ENVIO = "INTERVALO.REPROCESO.ENVIO";
    public static final String INTERVALO_RENDICION_CARGA = "INTERVALO.RENDICION.CARGA";
    public static final String INICIO_RENDICION_PAGO = "INICIO.RENDICION.PAGO";
    public static final String INTERVALO_RENDICION_PAGO = "INTERVALO.RENDICION.PAGO";
    public static final String REGISTROS_PAGINA = "REGISTROS.PAGINA";
    public static final String JNDI_DATASOURCE = "JNDI.DATASOURCE";
    public static final String JMS_Q_FACTORY = "JMS.Q.FACTORY";
    public static final String JMS_Q_CARGA_NOMINAS = "JMS.Q.CARGA_NOMINAS";
    public static final String JMS_Q_RINDE_NOMINAS = "JMS.Q.RINDE_NOMINAS";
    public static final String JMS_Q_RINDE_CARGA_NOMINAS = "JMS.Q.RINDE.CARGA.NOMINAS";
    public static final String ENVIO_OK = "ENVIO.OK";
    public static final String RENDICION_OK = "RENDICION.OK";
    public static final String CORREO_SEPARADOR = "CORREO.SEPARADOR";
    public static final String CORREO_ORIGEN = "CORREO.ORIGEN";
    public static final String CORREO_SERVIDOR = "CORREO.SERVIDOR";
    public static final String ESTADO_PROCESO_CONCILIADO = "ESTADO.PROCESO.CONCILIADO";
    public static final String ESTADO_TRANSACCION_PROCESADO = "ESTADO.TRANSACCION.PROCESADO";
    public static final String ENDPOINT_ENVIONOMINAS_RS = "ENDPOINT.ENVIONOMINAS.RS";
    public static final String LIMPIAR_SCHEDULER = "LIMPIAR.SCHEDULER";
    public static final String FIN_DIA_BANCARIO = "FIN.DIA.BANCARIO";
    public static final String HORA_INICIO_RENDICION = "HORA.INICIO.RENDICION";
    
    private static volatile Properties propiedades = null;

    /**
     * Constructor por defecto
     */
    public EnvioNominasConfig() {
        try {
            if ( this.propiedades == null ) {
                synchronized ( EnvioNominasConfig.class ) {
                    if ( this.propiedades == null ) {
                        PropertiesLoader loader = new PropertiesLoader();
                        this.propiedades = loader.loadProperties( PROPERTIES );
                    }
                }
            }
        } catch ( Exception e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
    }

    /**
     * Devuelve el valor de una propiedad a partir de su clave.
     * 
     * @param clave de la propiedad cuyo valor se desea obtener.
     * @return El valor de la propiedad especificada, null en caso de no 
     * que no exista un valor definido para la propiedad.
     */
    public String get( String clave ) {
        String valor = ( String ) this.propiedades.get( clave );
        return valor;
    }
}
