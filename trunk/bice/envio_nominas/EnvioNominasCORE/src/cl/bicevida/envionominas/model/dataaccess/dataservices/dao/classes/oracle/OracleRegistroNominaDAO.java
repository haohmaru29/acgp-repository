package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.oracle;

import cl.bicevida.envionominas.model.bo.BancoBO;
import cl.bicevida.envionominas.model.bo.EstadoTransaccionBO;
import cl.bicevida.envionominas.model.bo.RegistroNominaBO;
import cl.bicevida.envionominas.model.bo.TipoCuentaBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.DAOFactory;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.BancosDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.RegistroNominaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementa el acceso a datos de la tabla "TRANSACCIONES_PAGO".
 * 
 * Registro de versiones:
 *      - 1.0 17/11/2010 Giorgio Gortaire (ACGP) : Version inicial. 
 */
public class OracleRegistroNominaDAO implements RegistroNominaDAO {
    private EnvioNominasConfig config;

    public OracleRegistroNominaDAO() {
        config = new EnvioNominasConfig();
    }

    /**
     * 
     * @param _id
     * @return
     * @throws DAOException
     */
    public Map<Long, String> queryBancosPagoNomina( Long _id ) throws DAOException {
        Map<Long, String> bancosPago = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select distinct(tp.REFERENCIA_BANCO), b.NOMBRE from TRANSACCIONES_PAGO tp left outer join BANCOS b on b.ID = tp.REFERENCIA_BANCO where tp.REFERENCIA_NOMINA = " + _id;
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                if ( bancosPago == null ) {
                    bancosPago = new HashMap<Long, String>();
                }
                bancosPago.put( rs.getLong( "REFERENCIA_BANCO" ), rs.getString( "NOMBRE" ) );
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
        return bancosPago;
    }

    /**
     * 
     * @param _idNomina
     * @return
     */
    public Long queryTotalRegistrosNomina( Long _idNomina ) throws DAOException {
        Long totalRegistros = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select count(1) as TOTAL_REGISTROS from TRANSACCIONES_PAGO tp where tp.REFERENCIA_NOMINA = " + _idNomina;
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            if ( rs.next() ) {
                totalRegistros = rs.getLong( "TOTAL_REGISTROS" );
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
        return totalRegistros;
    }

    public List<RegistroNominaBO> queryRegistrosByNomina( Long _idNomina ) throws DAOException {
        List<RegistroNominaBO> registros = new ArrayList<RegistroNominaBO>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = " select tp.ID as NUMERO, tp.RUT_TITULAR as RUT, tp.DV_TITULAR as DV, tp.NOMBRE_TITULAR as NOMBRE, tp.OFICINA_DESTINO as OFICINA_DESTINO, tc.NOMBRE as NOMBRE_TIPO_CUENTA, tc.ID as ID_TIPO_CUENTA, tp.CUENTA_TITULAR as CUENTA, bp.NOMBRE as NOMBRE_BANCO, bp.CODIGO_MATRIZ as CODIGO_MATRIZ_BANCO, tp.MONTO as MONTO from TRANSACCIONES_PAGO tp join BANCOS bp on bp.ID = tp.REFERENCIA_BANCO join TIPOS_CUENTA tc on tc.ID = tp.REFERENCIA_TIPO_CUENTA where tp.REFERENCIA_NOMINA = ? order by tp.ID asc";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong( 1, _idNomina );
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                RegistroNominaBO registro = new RegistroNominaBO();
                registro.setId( rs.getLong( "NUMERO" ) );
                registro.setRut( rs.getInt( "RUT" ) );
                registro.setDv( rs.getString( "DV" ) );
                registro.setNombre( rs.getString( "NOMBRE" ) );
                registro.setCuenta( rs.getString( "CUENTA" ) );
                registro.setOficinaDestino( rs.getString( "OFICINA_DESTINO" ) );
                registro.setTipoCuenta( TipoCuentaBO.crear( rs.getLong( "ID_TIPO_CUENTA" ), rs.getString( "NOMBRE_TIPO_CUENTA" ) ) );
                registro.setMonto( rs.getBigDecimal( "MONTO" ) );
                registro.setBanco( BancoBO.crear( rs.getString( "NOMBRE_BANCO" ) ) );
                registro.getBanco().setCodigoMatriz( rs.getString( "CODIGO_MATRIZ_BANCO" ) );
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

    public List<RegistroNominaBO> queryRegistrosByProcesoId( Long _idProceso ) throws DAOException {
        List<RegistroNominaBO> registros = new ArrayList<RegistroNominaBO>();
        BancosDAO bancosDAO = DAOFactory.getDAOFactory( DAOFactory.ORACLE ).getBancosDAO();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = " select tp.ID as ID, tp.MONTO as MONTO, tp.RUT_TITULAR as RUT, tp.DV_TITULAR as DV, tp.REFERENCIA_BANCO as ID_BANCO, tp.CUENTA_TITULAR as CUENTA, tp.REFERENCIA_ESTADO_TRANSACCION  as ETX_ID, tp.FECHA_ESTADO as FECHA_ESTADO, tp.NOMBRE_TITULAR as NOMBRE, tp.OFICINA_DESTINO as OFICINA_DESTINO, tp.OFICINA_ORIGEN as OFICINA_ORIGEN, tp.REFERENCIA_TIPO_CUENTA as TCT_ID, et.DESCRIPCION as ETX_DESCRIPCION, et.NOMBRE as ETX_NOMBRE, tc.DESCRIPCION as TCT_DESCRIPCION, tc.NOMBRE as TCT_NOMBRE from TRANSACCIONES_PAGO tp join ESTADOS_TRANSACCION_PAGO et on tp.REFERENCIA_ESTADO_TRANSACCION = et.ID join TIPOS_CUENTA tc on tp.REFERENCIA_TIPO_CUENTA = tc.ID where tp.ID in (select rp.REFERENCIA_TRANSACCION_PAGO from EVNM_REGISTROS_PROCESO rp where rp.REFERENCIA_PROCESO = " + _idProceso + ")";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql.toString() );
            while ( rs.next() ) {
                RegistroNominaBO registro = new RegistroNominaBO();
                registro.setId( rs.getLong( "ID" ) );
                registro.setMonto( rs.getBigDecimal( "MONTO" ) );
                registro.setRut( rs.getInt( "RUT" ) );
                registro.setDv( rs.getString( "DV" ) );
                registro.setBanco( bancosDAO.queryBancoById( rs.getLong( "ID_BANCO" ) ) );
                registro.setCuenta( rs.getString( "CUENTA" ) );
                registro.setEstado( EstadoTransaccionBO.crear( rs.getLong( "ETX_ID" ), rs.getString( "ETX_DESCRIPCION" ), rs.getString( "ETX_NOMBRE" ) ) );
                registro.setFechaEstado( rs.getDate( "FECHA_ESTADO" ) );
                registro.setNombre( rs.getString( "NOMBRE" ) );
                registro.setOficinaDestino( rs.getString( "OFICINA_DESTINO" ) );
                registro.setOficinaOrigen( rs.getString( "OFICINA_ORIGEN" ) );
                registro.setTipoCuenta( TipoCuentaBO.crear( rs.getLong( "TCT_ID" ), rs.getString( "TCT_DESCRIPCION" ), rs.getString( "TCT_NOMBRE" ) ) );
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

    public void queryAsociaRegistroProceso( RegistroNominaBO _registro ) throws DAOException {
        Connection conn = null;
        Statement stmt = null;
        String sql = "insert into EVNM_REGISTROS_PROCESO(ID_REGISTRO, REFERENCIA_TRANSACCION_PAGO,REFERENCIA_PROCESO) values(EVNM_REGISTROS_PROCESO_ID_SEQ.nextval," + _registro.getId() + "," + _registro.getProceso().getId() + ")";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            stmt.execute( sql );
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

    public void queryActualizarEstadoRegistrosByNomina( Long _idNomina, Integer _idEstado ) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "update TRANSACCIONES_PAGO tp set tp.REFERENCIA_ESTADO_TRANSACCION = ?, tp.FECHA_ESTADO = current_timestamp where tp.REFERENCIA_NOMINA = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setInt( 1, _idEstado );
            stmt.setLong( 2, _idNomina );
            stmt.execute();
        } catch ( RuntimeException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } catch ( SQLException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } finally {
            OracleDAOFactory.closeAll( conn, stmt, null );
        }
    }
    
    public void queryActualizarEstadoRegistro( Long _idNomina, Integer _idEstado, String _rutTitular, String _nombreTitular ) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "update TRANSACCIONES_PAGO tp set tp.REFERENCIA_ESTADO_TRANSACCION = ?, tp.FECHA_ESTADO = current_timestamp where"
            + "tp.REFERENCIA_NOMINA = ? AND RUT_TITULAR= ? AND NOMBRE_TITULAR=?";
        //String sql = "update TRANSACCIONES_PAGO tp set tp.REFERENCIA_ESTADO_TRANSACCION = ?, tp.FECHA_ESTADO = current_timestamp where tp.REFERENCIA_NOMINA = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setInt( 1, _idEstado );
            stmt.setLong( 2, _idNomina );
            stmt.setString( 3, _rutTitular);
            stmt.setString( 2, _nombreTitular );
            stmt.execute();
        } catch ( RuntimeException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } catch ( SQLException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } finally {
            OracleDAOFactory.closeAll( conn, stmt, null );
        }
    }

    public void queryActualizarEstadoRegistro( Long _idRegistro, Long _idEstado, String _observacion ) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "update TRANSACCIONES_PAGO tp set tp.REFERENCIA_ESTADO_TRANSACCION = ?, tp.FECHA_ESTADO = current_timestamp where tp.ID = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong( 1, _idEstado );
            stmt.setLong( 2, _idRegistro );
            stmt.execute();
        } catch ( RuntimeException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } catch ( SQLException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } finally {
            OracleDAOFactory.closeAll( conn, stmt, null );
        }
    }

    public Long queryRegistrosPendientesRendicionByNomina( Long _idNomina ) throws DAOException {
        Long cantidad = Long.valueOf(-1);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = " select count(tp.ID) as CANTIDAD from TRANSACCIONES_PAGO tp where tp.REFERENCIA_NOMINA =  ? and tp.REFERENCIA_ESTADO_TRANSACCION = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,_idNomina);
            stmt.setLong(2,Long.valueOf(config.get(EnvioNominasConfig.ID_REGISTRO_PENDIENTE)));
            rs = stmt.executeQuery();
            if ( rs.next() ) {
               cantidad = rs.getLong("CANTIDAD");
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
        return cantidad;
    }
}
