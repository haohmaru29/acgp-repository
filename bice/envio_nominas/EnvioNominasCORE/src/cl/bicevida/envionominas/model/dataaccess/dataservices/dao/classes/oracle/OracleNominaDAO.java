package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.oracle;

import cl.bicevida.envionominas.model.bo.BancoBO;
import cl.bicevida.envionominas.model.bo.BancoProcesoBO;
import cl.bicevida.envionominas.model.bo.EstadoNominaBO;
import cl.bicevida.envionominas.model.bo.EstadoTransaccionBO;
import cl.bicevida.envionominas.model.bo.NominaBO;
import cl.bicevida.envionominas.model.bo.OrigenBO;
import cl.bicevida.envionominas.model.bo.ProcesoEnvioBO;
import cl.bicevida.envionominas.model.bo.RegistroNominaBO;
import cl.bicevida.envionominas.model.bo.TipoCuentaBO;
import cl.bicevida.envionominas.model.bo.TipoNominaBO;
import cl.bicevida.envionominas.model.bo.TipoPagoBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.NominaDAO;

import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;

import cl.bicevida.envionominas.model.logic.EnvioCorreosManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Implementa el acceso a datos de la tabla "NOMINAS".
 * 
 * Registro de versiones:
 *      - 1.0 17/11/2010 Giorgio Gortaire (ACGP) : Version inicial. 
 */
public class OracleNominaDAO implements NominaDAO {
    private static final Logger log = Logger.getLogger( OracleNominaDAO.class );
    private EnvioNominasConfig config;

    public OracleNominaDAO() {
        config = new EnvioNominasConfig();
    }

    public List<RegistroNominaBO> consultaEstadoTransaccionesNomina( Long _bancoPago, Long _estadoNomina, Long _estadoTransaccion, Date _fechaDesde, Date _fechaHasta, String _loteNomina, String _rutTitular, Long _bancoProceso, Long _tipoNomina ) throws DAOException {
        List<RegistroNominaBO> registros = new ArrayList<RegistroNominaBO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = " select " + 
        "    n.LOTE as LOTE, " + 
        "    n.REFERENCIA_ORIGEN as ORIGEN, " + 
        "    tn.NOMBRE||'-'||tn.DESCRIPCION  as TIPO_NOMINA_NOMBRE, " + 
        "    n.ID as NOMINA_ID, " + 
        "    e.NOMBRE as ESTADO_NOMINA_NOMBRE, " + 
        "    tp.ID as TRANSACCION_ID, " + 
        "    et.NOMBRE as ESTADO_TRANSACCION_NOMBRE, " + 
        "    tp.RUT_TITULAR as TRANSACCION_RUT, " + 
        "    tp.DV_TITULAR as TRANSACCION_DIGITO_VERIFICADOR, " + 
        "    tp.NOMBRE_TITULAR as TRANSACCION_NOMBRE, " + 
        "    tc.NOMBRE as TIPO_CUENTA_NOMBRE, " + 
        "    tp.CUENTA_TITULAR as TRANSACCION_CUENTA, " + 
        "    b.NOMBRE as TRANSACCION_BANCO_NOMBRE, " + 
        "    tp.MONTO as TRANSACCION_MONTO, " + 
        "    bp.NOMBRE as PROCESO_BANCO_PROCESO_NOMBRE, " + 
        "    pe.FECHA_CREACION as PROCESO_FECHA_CREACION, " + 
        "    pe.FOLIO_PROCESO as FOLIO_EXTERNO, " + 
        "    case when tp.FECHA_ESTADO is null then n.FECHA_ESTADO else tp.FECHA_ESTADO end as TRANSACCION_FECHA_ESTADO " + 
        "from " + 
        "    TRANSACCIONES_PAGO tp left outer join " + 
        "    NOMINAS n on tp.REFERENCIA_NOMINA = n.ID left outer join " + 
        "    TIPOS_NOMINA tn on tn.ID = n.REFERENCIA_TIPO_NOMINA left outer join " + 
        "    ESTADOS_NOMINA e on e.ID = n.REFERENCIA_ESTADO_NOMINA left outer join " + 
        "    ESTADOS_TRANSACCION_PAGO et on et.ID = tp.REFERENCIA_ESTADO_TRANSACCION left outer join " + 
        "    TIPOS_CUENTA tc on tc.ID = tp.REFERENCIA_TIPO_CUENTA left outer join " + 
        "    BANCOS b on b.ID = tp.REFERENCIA_BANCO left outer join " + 
        "    EVNM_REGISTROS_PROCESO rp on rp.REFERENCIA_TRANSACCION_PAGO = tp.ID left outer join " + 
        "    EVNM_PROCESOS pe on pe.ID_PROCESO = rp.REFERENCIA_PROCESO left outer join " + 
        "    EVNM_BANCOS_PROCESO bp on pe.REFERENCIA_BANCO_PROCESO = bp.ID_BANCO_PROCESO left outer join " + 
        "    BANCOS bs on bs.ID = bp.REFERENCIA_BANCO " + 
        "where n.REFERENCIA_ESTADO_NOMINA > 100 ";
        if ( _bancoPago != null ) {
            sql = sql + " and tp.REFERENCIA_BANCO = " + _bancoPago;
        }
        if ( _estadoNomina != null ) {
            sql = sql + " and n.REFERENCIA_ESTADO_NOMINA = " + _estadoNomina;
        }
        if ( _estadoTransaccion != null ) {
            sql = sql + " and tp.REFERENCIA_ESTADO_TRANSACCION = " + _estadoTransaccion;
        }
        if ( _fechaDesde != null && _fechaHasta != null ) {
            SimpleDateFormat formatter = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );
            sql = sql + " and n.FECHA_CURSE between to_timestamp('" + formatter.format( _fechaDesde ) + "','DD/MM/YYYY HH24:MI:SS')";
            sql = sql + " and to_timestamp('" + formatter.format( _fechaHasta ) + "','DD/MM/YYYY HH24:MI:SS')";
        }
        if ( _loteNomina != null && !"".equals(_loteNomina.trim())) {
            sql = sql + " and upper(n.LOTE) like '%" + _loteNomina.trim().toUpperCase() + "%'";
        }
        if ( _rutTitular != null ) {
            sql = sql + " and tp.RUT_TITULAR||'-'||tp.DV_TITULAR = '" + _rutTitular + "'";
        }
        if ( _bancoProceso != null ) {
            sql = sql + " and bp.ID_BANCO_PROCESO = " + _bancoProceso;
        }
        if ( _tipoNomina != null ) {
            sql = sql + " and n.REFERENCIA_TIPO_NOMINA = " + _tipoNomina;
        }
        sql = sql + " order by n.ID desc, tp.ID asc";
        System.out.println("sql:"+sql);
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                RegistroNominaBO registro = new RegistroNominaBO();
                registro.setNomina( NominaBO.crear( rs.getLong( "NOMINA_ID" ),rs.getString("LOTE"), OrigenBO.crear(rs.getString("ORIGEN"),rs.getString("ORIGEN")), TipoNominaBO.crear( rs.getString( "TIPO_NOMINA_NOMBRE" ) ), EstadoNominaBO.crear( rs.getString( "ESTADO_NOMINA_NOMBRE" ) ) ) );
                registro.setId( rs.getLong( "TRANSACCION_ID" ) );
                registro.setEstado( EstadoTransaccionBO.crear( rs.getString( "ESTADO_TRANSACCION_NOMBRE" ) ) );
                registro.setRut( rs.getInt( "TRANSACCION_RUT" ) );
                registro.setDv( rs.getString( "TRANSACCION_DIGITO_VERIFICADOR" ) );
                registro.setNombre( rs.getString( "TRANSACCION_NOMBRE" ) );
                registro.setCuenta( rs.getString( "TRANSACCION_CUENTA" ) );
                registro.setTipoCuenta( TipoCuentaBO.crear( rs.getString( "TIPO_CUENTA_NOMBRE" ) ) );
                registro.setBanco( BancoBO.crear( rs.getString( "TRANSACCION_BANCO_NOMBRE" ) ) );
                registro.setMonto( rs.getBigDecimal( "TRANSACCION_MONTO" ) );
                registro.setProceso( ProcesoEnvioBO.crear( rs.getDate( "PROCESO_FECHA_CREACION" ), BancoProcesoBO.crear( BancoBO.crear( rs.getString( "PROCESO_BANCO_PROCESO_NOMBRE" ) ) ) ) );
                registro.getProceso().setFolioProcesoExterno( rs.getString( "FOLIO_EXTERNO" ) );
                registro.setFechaEstado( rs.getDate( "TRANSACCION_FECHA_ESTADO" ) );
                registros.add( registro );
            }
        } catch ( RuntimeException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } catch ( DAOException e ) {
            throw e;
        } catch ( SQLException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } finally {
            OracleDAOFactory.closeAll( conn, stmt, rs );
        }
        return registros;
    }

    /**
     * Ejecuta la consulta en busca de nominas que tengan asociado el estado ""
     * y que no hayan sido ingresadas al proceso de envio de nominas, es decir
     * que no tengan registros relacionados en la tabla 
     * EVNM_PROCESOS.
     * @return Lista de nominas encontradas.
     * @throws DAOException
     */
    public List<NominaBO> queryNuevasNominas() throws DAOException {
        List<NominaBO> nominas = new ArrayList<NominaBO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select n.ID as ID_NOMINA, n.ID_SISTEMA_CONTABLE as ID_SISTEMA_CONTABLE, n.FECHA_CURSE as FECHA_PAGO, n.referencia_cuenta as REFERENCIA_CUENTA, n.referencia_origen as REFERENCIA_ORIGEN from NOMINAS n left outer join EVNM_PROCESOS pe on pe.REFERENCIA_NOMINA = n.ID where pe.ID_PROCESO is null and n.REFERENCIA_ESTADO_NOMINA = " + config.get( EnvioNominasConfig.ID_NOMINA_NUEVA ) + " and n.referencia_cuenta in (10112) order by n.id_sistema_contable desc ";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                NominaBO nomina = new NominaBO();
                nomina.setId( rs.getLong( "ID_NOMINA" ) );
                nomina.setFechaPago( rs.getDate( "FECHA_PAGO" ) );
                nominas.add( nomina );
            }
        } catch ( RuntimeException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } catch ( DAOException e ) {
            throw e;
        } catch ( SQLException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } finally {
            OracleDAOFactory.closeAll( conn, stmt, rs );
        }
        return nominas;
    }

    public List<NominaBO> queryNominasCancelables( Long _bancoPago, Date _fechaDesde, Date _fechaHasta, Long _numeroNomina, Long _tipoNomina, Long _tipoCuenta ) throws DAOException {
        List<NominaBO> nominas = new ArrayList<NominaBO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select tn.NOMBRE||'-'||tn.DESCRIPCION as NOMBRE_TIPO_NOMINA, n.ID as ID_NOMINA, en.NOMBRE as NOMBRE_ESTADO_NOMINA, t.MONTO_TOTAL as MONTO_TOTAL, t.TOTAL_REGISTROS as TOTAL_REGISTROS, b.NOMBRE as NOMBRE_BANCO_PROCESO, r.FECHA_CREACION as FECHA_INICIO_PROCESO from NOMINAS n join TIPOS_NOMINA tn on tn.ID = n.REFERENCIA_TIPO_NOMINA join ESTADOS_NOMINA en on en.ID = n.REFERENCIA_ESTADO_NOMINA join (select sn.ID as ID_NOMINA, sum(stp.MONTO) as MONTO_TOTAL, count(stp.ID) as TOTAL_REGISTROS from NOMINAS sn join TRANSACCIONES_PAGO stp on stp.REFERENCIA_NOMINA = sn.ID group by sn.ID ) t on t.ID_NOMINA = n.ID join (select pn.ID as ID_NOMINA, max(spe.ID_PROCESO) as ID_PROCESO, min(spe.FECHA_CREACION) as FECHA_CREACION from NOMINAS pn join EVNM_PROCESOS spe on spe.REFERENCIA_NOMINA = pn.ID group by pn.ID) r on r.ID_NOMINA = n.ID join EVNM_PROCESOS pe on pe.ID_PROCESO = r.ID_PROCESO join EVNM_BANCOS_PROCESO bp on bp.ID_BANCO_PROCESO = pe.REFERENCIA_BANCO_PROCESO join BANCOS b on b.ID = bp.REFERENCIA_BANCO where en.ID in( " + config.get( EnvioNominasConfig.ID_NOMINA_PENDIENTE ) + "," + config.get( EnvioNominasConfig.ID_NOMINA_REPROCESO ) + ")";
        if ( _numeroNomina != null ) {
            sql = sql + " and n.ID = " + _numeroNomina;
        }
        if ( _tipoNomina != null ) {
            sql = sql + " and tn.ID = " + _tipoNomina;
        }
        if ( _bancoPago != null ) {
            sql = sql + " and n.ID in (select sstp.REFERENCIA_NOMINA from TRANSACCIONES_PAGO sstp where sstp.REFERENCIA_BANCO = " + _bancoPago + ")";
        }
        if ( _tipoCuenta != null ) {
            sql = sql + " and n.ID in (select ssstp.REFERENCIA_NOMINA from TRANSACCIONES_PAGO ssstp where ssstp.REFERENCIA_TIPO_CUENTA = " + _tipoCuenta + ")";
        }
        if ( _fechaDesde != null && _fechaHasta != null ) {
            SimpleDateFormat formatter = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );
            sql = sql + " and n.FECHA_CURSE between to_timestamp('" + formatter.format( _fechaDesde ) + "','DD/MM/YYYY HH24:MI:SS')";
            sql = sql + " and to_timestamp('" + formatter.format( _fechaHasta ) + "','DD/MM/YYYY HH24:MI:SS')";
        }
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                NominaBO nomina = new NominaBO();
                nomina.setTipo( TipoNominaBO.crear( rs.getString( "NOMBRE_TIPO_NOMINA" ) ) );
                nomina.setId( rs.getLong( "ID_NOMINA" ) );
                nomina.setEstado( EstadoNominaBO.crear( rs.getString( "NOMBRE_ESTADO_NOMINA" ) ) );
                nomina.setMontoTotal( rs.getBigDecimal( "MONTO_TOTAL" ) );
                nomina.setTotalRegistros( rs.getLong( "TOTAL_REGISTROS" ) );
                nomina.setBancoProceso( rs.getString( "NOMBRE_BANCO_PROCESO" ) );
                nomina.setFechaInicioProceso( rs.getDate( "FECHA_INICIO_PROCESO" ) );
                nominas.add( nomina );
            }
        } catch ( RuntimeException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } catch ( DAOException e ) {
            throw e;
        } catch ( SQLException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } finally {
            OracleDAOFactory.closeAll( conn, stmt, rs );
        }
        return nominas;
    }

    public List<NominaBO> queryNominasExtraibles( Date _fechaDesde, Date _fechaHasta, String _loteNomina, Long _tipoNomina, Long _bancoProceso ) throws DAOException {
        List<NominaBO> nominas = new ArrayList<NominaBO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select " + 
        "    tn.NOMBRE||'-'||tn.DESCRIPCION as NOMBRE_TIPO_NOMINA, " + 
        "    n.ID as ID_NOMINA, en.NOMBRE as NOMBRE_ESTADO_NOMINA, " +
        "    n.LOTE as LOTE, " + 
        "    t.MONTO_TOTAL as MONTO_TOTAL, " + 
        "    t.TOTAL_REGISTROS as TOTAL_REGISTROS, " + 
        "    b.NOMBRE as NOMBRE_BANCO_PROCESO, " + 
        "    r.FECHA_CREACION as FECHA_INICIO_PROCESO, " + 
        "    n.FECHA_CURSE as FECHA_PAGO " + 
        " from " + 
        "    NOMINAS n join TIPOS_NOMINA tn on tn.ID = n.REFERENCIA_TIPO_NOMINA join " + 
        "    ESTADOS_NOMINA en on en.ID = n.REFERENCIA_ESTADO_NOMINA join " + 
        "    (select sn.ID as ID_NOMINA, sum(stp.MONTO) as MONTO_TOTAL, count(stp.ID) as TOTAL_REGISTROS from NOMINAS sn join TRANSACCIONES_PAGO stp on stp.REFERENCIA_NOMINA = sn.ID group by sn.ID ) t on t.ID_NOMINA = n.ID join " + 
        "    (select pn.ID as ID_NOMINA, max(spe.ID_PROCESO) as ID_PROCESO, min(spe.FECHA_CREACION) as FECHA_CREACION from NOMINAS pn join EVNM_PROCESOS spe on spe.REFERENCIA_NOMINA = pn.ID group by pn.ID) r on r.ID_NOMINA = n.ID join " + 
        "    EVNM_PROCESOS pe on pe.ID_PROCESO = r.ID_PROCESO join " + 
        "    EVNM_BANCOS_PROCESO bp on bp.ID_BANCO_PROCESO = pe.REFERENCIA_BANCO_PROCESO join " + 
        "    BANCOS b on b.ID = bp.REFERENCIA_BANCO " + 
        "where " + 
        "    n.REFERENCIA_ESTADO_NOMINA in(" + config.get( EnvioNominasConfig.ID_NOMINA_REPROCESO ) + ") ";
        if ( _loteNomina != null && !"".equals(_loteNomina.trim()) ) {
            sql = sql + " and upper(n.LOTE) like '%" + _loteNomina.trim().toUpperCase() + "%'";
        }
        if ( _tipoNomina != null ) {
            sql = sql + " and tn.ID = " + _tipoNomina;
        }
        if ( _bancoProceso != null ) {
            sql = sql + " and bp.ID_BANCO_PROCESO = " + _bancoProceso;
        }
        if ( _fechaDesde != null && _fechaHasta != null ) {
            SimpleDateFormat formatter = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );
            sql = sql + " and n.FECHA_CURSE between to_timestamp('" + formatter.format( _fechaDesde ) + "','DD/MM/YYYY HH24:MI:SS')";
            sql = sql + " and to_timestamp('" + formatter.format( _fechaHasta ) + "','DD/MM/YYYY HH24:MI:SS')";
        }
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                NominaBO nomina = new NominaBO();
                nomina.setTipo( TipoNominaBO.crear( rs.getString( "NOMBRE_TIPO_NOMINA" ) ) );
                nomina.setLote( rs.getString("LOTE"));
                nomina.setId( rs.getLong( "ID_NOMINA" ) );
                nomina.setEstado( EstadoNominaBO.crear( rs.getString( "NOMBRE_ESTADO_NOMINA" ) ) );
                nomina.setMontoTotal( rs.getBigDecimal( "MONTO_TOTAL" ) );
                nomina.setTotalRegistros( rs.getLong( "TOTAL_REGISTROS" ) );
                nomina.setBancoProceso( rs.getString( "NOMBRE_BANCO_PROCESO" ) );
                nomina.setFechaInicioProceso( rs.getDate( "FECHA_INICIO_PROCESO" ) );
                nomina.setFechaPago(rs.getDate( "FECHA_PAGO" ));
                nominas.add( nomina );
            }
        } catch ( RuntimeException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } catch ( DAOException e ) {
            throw e;
        } catch ( SQLException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } finally {
            OracleDAOFactory.closeAll( conn, stmt, rs );
        }
        return nominas;
    }

    public List<NominaBO> queryNominasAutorizables( Long _bancoPago, Date _fechaDesde, Date _fechaHasta, Long _numeroNomina, Long _tipoNomina, Long _tipoCuenta ) throws DAOException {
        List<NominaBO> nominas = new ArrayList<NominaBO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select tn.NOMBRE||'-'||tn.DESCRIPCION as NOMBRE_TIPO_NOMINA, n.ID as ID_NOMINA, n.FECHA_ESTADO as FECHA_ENVIO, en.NOMBRE as NOMBRE_ESTADO_NOMINA, t.MONTO_TOTAL as MONTO_TOTAL, t.TOTAL_REGISTROS as TOTAL_REGISTROS, b.NOMBRE as NOMBRE_BANCO_PROCESO, t.FECHA_CREACION as FECHA_INICIO_PROCESO from NOMINAS n join TIPOS_NOMINA tn on tn.ID = n.REFERENCIA_TIPO_NOMINA join ESTADOS_NOMINA en on en.ID = n.REFERENCIA_ESTADO_NOMINA join (select sp.REFERENCIA_NOMINA as ID_NOMINA, count(srp.REFERENCIA_TRANSACCION_PAGO) as TOTAL_REGISTROS, sum(stp.MONTO) as MONTO_TOTAL, max(sp.ID_PROCESO) as ID_PROCESO, min(sp.FECHA_CREACION) as FECHA_CREACION from EVNM_REGISTROS_PROCESO srp left outer join EVNM_PROCESOS sp on sp.ID_PROCESO = srp.REFERENCIA_PROCESO join TRANSACCIONES_PAGO stp on stp.ID = srp.REFERENCIA_TRANSACCION_PAGO group by sp.REFERENCIA_NOMINA ) t on t.ID_NOMINA = n.ID join EVNM_PROCESOS pe on pe.ID_PROCESO = t.ID_PROCESO join EVNM_BANCOS_PROCESO bp on bp.ID_BANCO_PROCESO = pe.REFERENCIA_BANCO_PROCESO join BANCOS b on b.ID = bp.REFERENCIA_BANCO where n.REFERENCIA_ESTADO_NOMINA in( " + config.get( EnvioNominasConfig.ID_NOMINA_ENVIADA ) + ")"; 
        if ( _numeroNomina != null ) {
            sql = sql + " and n.ID = " + _numeroNomina;
        }
        if ( _tipoNomina != null ) {
            sql = sql + " and tn.ID = " + _tipoNomina;
        }
        if ( _bancoPago != null ) {
            sql = sql + " and n.ID in (select sstp.REFERENCIA_NOMINA from TRANSACCIONES_PAGO sstp where sstp.REFERENCIA_BANCO = " + _bancoPago + ")";
        }
        if ( _tipoCuenta != null ) {
            sql = sql + " and n.ID in (select ssstp.REFERENCIA_NOMINA from TRANSACCIONES_PAGO ssstp where ssstp.REFERENCIA_TIPO_CUENTA = " + _tipoCuenta + ")";
        }
        if ( _fechaDesde != null && _fechaHasta != null ) {
            SimpleDateFormat formatter = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );
            sql = sql + " and n.FECHA_CURSE between to_timestamp('" + formatter.format( _fechaDesde ) + "','DD/MM/YYYY HH24:MI:SS')";
            sql = sql + " and to_timestamp('" + formatter.format( _fechaHasta ) + "','DD/MM/YYYY HH24:MI:SS')";
        }
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                NominaBO nomina = new NominaBO();
                nomina.setTipo( TipoNominaBO.crear( rs.getString( "NOMBRE_TIPO_NOMINA" ) ) );
                nomina.setId( rs.getLong( "ID_NOMINA" ) );
                nomina.setMontoTotal( rs.getBigDecimal( "MONTO_TOTAL" ) );
                nomina.setTotalRegistros( rs.getLong( "TOTAL_REGISTROS" ) );
                nomina.setBancoProceso( rs.getString( "NOMBRE_BANCO_PROCESO" ) );
                nomina.setFechaInicioProceso( rs.getDate( "FECHA_INICIO_PROCESO" ) );
                nomina.setFechaEnvio( rs.getDate( "FECHA_ENVIO" ) );
                nominas.add( nomina );
            }
        } catch ( RuntimeException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } catch ( DAOException e ) {
            throw e;
        } catch ( SQLException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } finally {
            OracleDAOFactory.closeAll( conn, stmt, rs );
        }
        return nominas;
    }

    public NominaBO queryNominaById( Long _idNomina ) throws DAOException {
        NominaBO nomina = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select n.ID as ID_NOMINA, n.FECHA_CURSE as FECHA_PAGO, n.LOTE as LOTE, n.REFERENCIA_ESTADO_NOMINA as ID_ESTADO_NOMINA, e.NOMBRE as NOMBRE_ESTADO_NOMINA, e.DESCRIPCION as DESCRIPCION_ESTADO_NOMINA, n.REFERENCIA_ORIGEN as ID_ORIGEN, o.NOMBRE as NOMBRE_ORIGEN, n.REFERENCIA_TIPO_NOMINA as ID_TIPO_NOMINA, tn.NOMBRE as NOMBRE_TIPO_NOMINA, tn.DESCRIPCION as DESCRIPCION_TIPO_NOMINA, n.REFERENCIA_TIPO_PAGO as ID_TIPO_PAGO, tp.NOMBRE as NOMBRE_TIPO_PAGO, tp.DESCRIPCION as DESCRIPCION_TIPO_PAGO from NOMINAS n join ESTADOS_NOMINA e on n.REFERENCIA_ESTADO_NOMINA = e.ID join ORIGENES o on o.ID = n.REFERENCIA_ORIGEN join TIPOS_NOMINA tn on tn.ID = n.REFERENCIA_TIPO_NOMINA left outer join TIPOS_PAGO tp on tp.ID = n.REFERENCIA_TIPO_PAGO where n.ID = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong( 1, _idNomina );
            rs = stmt.executeQuery();
            if ( rs.next() ) {
                nomina = new NominaBO();
                nomina.setId( rs.getLong( "ID_NOMINA" ) );
                nomina.setFechaPago( rs.getDate( "FECHA_PAGO" ) );
                nomina.setEstado( EstadoNominaBO.crear( rs.getLong( "ID_ESTADO_NOMINA" ), rs.getString( "NOMBRE_ESTADO_NOMINA" ), rs.getString( "DESCRIPCION_ESTADO_NOMINA" ) ) );
                nomina.setLote( rs.getString( "LOTE" ) );
                nomina.setOrigen( OrigenBO.crear( rs.getString( "ID_ORIGEN" ), rs.getString( "NOMBRE_ORIGEN" ) ) );
                nomina.setTipo( TipoNominaBO.crear( rs.getLong( "ID_TIPO_NOMINA" ), rs.getString( "NOMBRE_TIPO_NOMINA" ), rs.getString( "DESCRIPCION_TIPO_NOMINA" ) ) );
                nomina.setTipoPago( TipoPagoBO.crear( rs.getLong( "ID_TIPO_PAGO" ), rs.getString( "NOMBRE_TIPO_PAGO" ), rs.getString( "DESCRIPCION_TIPO_PAGO" ) ) );
            }
        } catch ( RuntimeException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } catch ( DAOException e ) {
            throw e;
        } catch ( SQLException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } finally {
            OracleDAOFactory.closeAll( conn, stmt, rs );
        }
        return nomina;
    }

    public void queryActualizaEstadoNomina( Long _idNomina, Long _idEstado ) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        StringBuffer sql = new StringBuffer();
        sql.append( " update NOMINAS set FECHA_ESTADO = current_timestamp, REFERENCIA_ESTADO_NOMINA = ? where ID = ?" );
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql.toString() );
            stmt.setLong( 1, _idEstado );
            stmt.setLong( 2, _idNomina );
            stmt.execute();
        } catch ( RuntimeException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } catch ( DAOException e ) {
            throw e;
        } catch ( SQLException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } finally {
            OracleDAOFactory.closeAll( conn, stmt, null );
        }
    }

    public List<NominaBO> queryNominasSinFolio( Date _fechaDesde, Date _fechaHasta, String _loteNomina, Long _tipoNomina, Long _bancoProceso ) throws DAOException {
        List<NominaBO> nominas = new ArrayList<NominaBO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = " select tn.NOMBRE||'-'||tn.DESCRIPCION as NOMBRE_TIPO_NOMINA, n.LOTE as LOTE, n.ID as ID_NOMINA, en.NOMBRE as NOMBRE_ESTADO_NOMINA, t.MONTO_TOTAL as MONTO_TOTAL, t.TOTAL_REGISTROS as TOTAL_REGISTROS, b.NOMBRE as NOMBRE_BANCO_PROCESO, r.FECHA_CREACION as FECHA_INICIO_PROCESO, pe.FECHA_ENVIO as FECHA_ENVIO from  NOMINAS n join TIPOS_NOMINA tn on tn.ID = n.REFERENCIA_TIPO_NOMINA join ESTADOS_NOMINA en on en.ID = n.REFERENCIA_ESTADO_NOMINA join (select sn.ID as ID_NOMINA, sum(stp.MONTO) as MONTO_TOTAL, count(stp.ID) as TOTAL_REGISTROS from NOMINAS sn join TRANSACCIONES_PAGO stp on stp.REFERENCIA_NOMINA = sn.ID group by sn.ID  ) t on t.ID_NOMINA = n.ID join (select pn.ID as ID_NOMINA, max(spe.ID_PROCESO) as ID_PROCESO,  min(spe.FECHA_CREACION) as FECHA_CREACION from NOMINAS pn join EVNM_PROCESOS spe on spe.REFERENCIA_NOMINA = pn.ID group by  pn.ID) r on r.ID_NOMINA = n.ID join EVNM_PROCESOS pe on pe.ID_PROCESO = r.ID_PROCESO join EVNM_BANCOS_PROCESO bp on bp.ID_BANCO_PROCESO = pe.REFERENCIA_BANCO_PROCESO join BANCOS b on b.ID = bp.REFERENCIA_BANCO where 1 = 1 and n.REFERENCIA_ESTADO_NOMINA = (" + config.get( EnvioNominasConfig.ID_NOMINA_ENVIADA_MANUAL ) + ") and pe.FOLIO_PROCESO is null";
        if ( _loteNomina != null && !"".equals(_loteNomina.trim())) {
            sql = sql + " and upper(n.LOTE) like '%" + _loteNomina.trim().toUpperCase() + "%' ";
        }
        if ( _tipoNomina != null ) {
            sql = sql + " and tn.ID = " + _tipoNomina;
        }
        if ( _bancoProceso != null ) {
            sql = sql + " and pe.REFERENCIA_BANCO_PROCESO = " + _bancoProceso;
        }
        if ( _fechaDesde != null && _fechaHasta != null ) {
            SimpleDateFormat formatter = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );
            sql = sql + " and n.FECHA_CURSE between to_timestamp('" + formatter.format( _fechaDesde ) + "','DD/MM/YYYY HH24:MI:SS')";
            sql = sql + " and to_timestamp('" + formatter.format( _fechaHasta ) + "','DD/MM/YYYY HH24:MI:SS')";
        }
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                NominaBO nomina = new NominaBO();
                nomina.setTipo( TipoNominaBO.crear( rs.getString( "NOMBRE_TIPO_NOMINA" ) ) );
                nomina.setId( rs.getLong( "ID_NOMINA" ) );
                nomina.setLote( rs.getString("LOTE"));
                nomina.setEstado( EstadoNominaBO.crear( rs.getString( "NOMBRE_ESTADO_NOMINA" ) ) );
                nomina.setMontoTotal( rs.getBigDecimal( "MONTO_TOTAL" ) );
                nomina.setFechaEnvio( rs.getDate("FECHA_ENVIO") );
                nomina.setTotalRegistros( rs.getLong( "TOTAL_REGISTROS" ) );
                nomina.setBancoProceso( rs.getString( "NOMBRE_BANCO_PROCESO" ) );
                nomina.setFechaInicioProceso( rs.getDate( "FECHA_INICIO_PROCESO" ) );
                nominas.add( nomina );
            }
        } catch ( RuntimeException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } catch ( DAOException e ) {
            throw e;
        } catch ( SQLException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } finally {
            OracleDAOFactory.closeAll( conn, stmt, rs );
        }
        return nominas;
    }

    public int queryValidaBancoFolio( String _bancoPago, Long _numeroNomina ) throws DAOException {
        int irc = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select count(*) CONTADOR from EVNM_PROCESOS where FOLIO_PROCESO = ? and REFERENCIA_BANCO_PROCESO = ( select ID_BANCO_PROCESO from EVNM_BANCOS_PROCESO where REFERENCIA_BANCO=(select ID from BANCOS where NOMBRE = ?))";
        try {
            irc = 0;
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong( 1, _numeroNomina );
            stmt.setString( 2, _bancoPago );
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                irc = rs.getInt( "CONTADOR" );
            }
        } catch ( RuntimeException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } catch ( DAOException e ) {
            throw e;
        } catch ( SQLException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } finally {
            OracleDAOFactory.closeAll( conn, stmt, rs );
        }
        return irc;
    }
}
