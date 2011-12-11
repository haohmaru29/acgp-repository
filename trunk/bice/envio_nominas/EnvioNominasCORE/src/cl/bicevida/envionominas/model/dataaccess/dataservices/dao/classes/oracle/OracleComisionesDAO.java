package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.oracle;

import cl.bicevida.envionominas.model.bo.ComisionBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.ComisionesDAO;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementa el acceso a datos de la tabla "EVNM_COMISIONES".
 * 
 * Registro de versiones:
 *      - 1.0 17/11/2010 Giorgio Gortaire (ACGP) : Version inicial. 
 */
public class OracleComisionesDAO implements ComisionesDAO {
    private EnvioNominasConfig config;

    public OracleComisionesDAO() {
        config = new EnvioNominasConfig();
    }

    public void agregarComision( Long _idBancoProceso, BigDecimal _montoMismoBanco, BigDecimal _montoOtrosBancos, Date _fechaInicioVigencia, Date _fechaFinVigencia ) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "insert into EVNM_COMISIONES(ID_COMISION, MONTO_MISMO_BANCO, MONTO_OTROS_BANCOS, FECHA_INICIO_VIGENCIA, FECHA_TERMINO_VIGENCIA, REFERENCIA_BANCO_PROCESO) values(EVNM_COMISIONES_ID_SEQ.nextval,?,?,?,?,?)";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setBigDecimal( 1, _montoMismoBanco );
            stmt.setBigDecimal( 2, _montoOtrosBancos );
            stmt.setDate( 3, new java.sql.Date( _fechaInicioVigencia.getTime() ) );
            stmt.setDate( 4, new java.sql.Date( _fechaFinVigencia.getTime() ) );
            stmt.setLong( 5, _idBancoProceso );
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

    public List<ComisionBO> queryComisionesByBancoProceso( Long _idBancoProceso ) throws DAOException {
        List<ComisionBO> lista = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select ID_COMISION, MONTO_MISMO_BANCO, MONTO_OTROS_BANCOS, FECHA_INICIO_VIGENCIA, FECHA_TERMINO_VIGENCIA from EVNM_COMISIONES where REFERENCIA_BANCO_PROCESO = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong( 1, _idBancoProceso );
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                if ( lista == null ) {
                    lista = new ArrayList<ComisionBO>();
                }
                ComisionBO comision = new ComisionBO();
                comision.setId( rs.getLong( "ID_COMISION" ) );
                comision.setFechaInicioVigencia( rs.getDate( "FECHA_INICIO_VIGENCIA" ) );
                comision.setFechaTerminoVigencia( rs.getDate( "FECHA_TERMINO_VIGENCIA" ) );
                comision.setMontoMismoBanco( rs.getBigDecimal( "MONTO_MISMO_BANCO" ) );
                comision.setMontoOtrosBancos( rs.getBigDecimal( "MONTO_OTROS_BANCOS" ) );
                lista.add( comision );
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
        return lista;
    }

    public ComisionBO queryComisionById( Long _idComision ) throws DAOException {
        ComisionBO comision = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select MONTO_MISMO_BANCO, MONTO_OTROS_BANCOS, FECHA_INICIO_VIGENCIA, FECHA_TERMINO_VIGENCIA from EVNM_COMISIONES where ID_COMISION = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong( 1, _idComision );
            rs = stmt.executeQuery();
            if ( rs.next() ) {
                comision = new ComisionBO();
                comision.setFechaInicioVigencia( rs.getDate( "FECHA_INICIO_VIGENCIA" ) );
                comision.setFechaTerminoVigencia( rs.getDate( "FECHA_TERMINO_VIGENCIA" ) );
                comision.setMontoMismoBanco( rs.getBigDecimal( "MONTO_MISMO_BANCO" ) );
                comision.setMontoOtrosBancos( rs.getBigDecimal( "MONTO_OTROS_BANCOS" ) );
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
        return comision;
    }

    public void updateComision( Long _idComision, BigDecimal _montoMismoBanco, BigDecimal _montoOtrosBancos, Date _fechaInicioVigencia, Date _fechaFinVigencia ) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "update EVNM_COMISIONES set MONTO_MISMO_BANCO = ?, MONTO_OTROS_BANCOS = ?, FECHA_INICIO_VIGENCIA = ?, FECHA_TERMINO_VIGENCIA = ? where ID_COMISION = ? ";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setBigDecimal( 1, _montoMismoBanco );
            stmt.setBigDecimal( 2, _montoOtrosBancos );
            stmt.setDate( 3, new java.sql.Date( _fechaInicioVigencia.getTime() ) );
            stmt.setDate( 4, new java.sql.Date( _fechaFinVigencia.getTime() ) );
            stmt.setLong( 5, _idComision );
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

    public void eliminarComision( Long _idComision ) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "delete from EVNM_COMISIONES where ID_COMISION = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong( 1, _idComision );
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
}
