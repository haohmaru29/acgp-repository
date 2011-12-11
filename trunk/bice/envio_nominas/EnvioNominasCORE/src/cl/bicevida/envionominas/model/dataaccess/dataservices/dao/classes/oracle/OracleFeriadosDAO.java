package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.oracle;

import cl.bicevida.envionominas.model.bo.FeriadoBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.FeriadosDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Implementa el acceso a datos de la tabla "EVNM_FERIADOS".
 * 
 * Registro de versiones:
 *      - 1.0 17/11/2010 Giorgio Gortaire (ACGP) : Version inicial. 
 */
public class OracleFeriadosDAO implements FeriadosDAO {
    private EnvioNominasConfig config;

    public OracleFeriadosDAO() {
        config = new EnvioNominasConfig();
    }

    public List<FeriadoBO> queryFeriado() throws DAOException {
        List<FeriadoBO> Feriado = new ArrayList<FeriadoBO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select tn.TIPO, tn.DESCRIPCION, tn.ID, tn.FECHA from EVNM_FERIADOS tn";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                FeriadoBO tFeriado = new FeriadoBO();
                tFeriado.setId( rs.getLong( "ID" ) );
                tFeriado.setFechaFeriado( rs.getDate( "FECHA" ) );
                tFeriado.setDescripcionFeriado( rs.getString( "DESCRIPCION" ) );
                tFeriado.setTipoFeriado( rs.getString( "TIPO" ) );
                tFeriado.setDescTipoFeriado( rs.getString( "TIPO" ) );
                Feriado.add( tFeriado );
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
        return Feriado;
    }

    public void agregarFeriado( String _tipoFeriado, String _descripcionFeriado, Date _fechaFeriado ) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = " insert into EVNM_FERIADOS values ((select nvl(max(tn.ID),0) + 1 from EVNM_FERIADOS tn),?,?,?)";
        //TODO:Usar sequencia.
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setDate( 1, new java.sql.Date( _fechaFeriado.getTime() ) );
            stmt.setString( 2, _descripcionFeriado );
            stmt.setString( 3, _tipoFeriado );
            stmt.execute();
        } catch ( RuntimeException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } catch ( DAOException e ) {
            throw e;
        } catch ( SQLException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } finally {
            OracleDAOFactory.closeAll( conn, stmt, rs );
        }
        return;
    }

    public void eliminarFeriado( Date _fechaFeriado ) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "delete EVNM_FERIADOS where FECHA = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setDate( 1, new java.sql.Date( _fechaFeriado.getTime() ) );
            stmt.execute();
        } catch ( RuntimeException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } catch ( DAOException e ) {
            throw e;
        } catch ( SQLException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } finally {
            OracleDAOFactory.closeAll( conn, stmt, rs );
        }
        return;
    }

    public void modificarFeriado( Long idFeriado, String tipoFeriado, String descripcionFeriado, Date fechaFeriado ) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "update EVNM_FERIADOS set FECHA = ?, DESCRIPCION = ?, TIPO = ? where ID = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setDate( 1, new java.sql.Date( fechaFeriado.getTime() ) );
            stmt.setString( 2, descripcionFeriado );
            stmt.setString( 3, tipoFeriado );
            stmt.setLong( 4, idFeriado );
            stmt.execute();
        } catch ( RuntimeException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } catch ( DAOException e ) {
            throw e;
        } catch ( SQLException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } finally {
            OracleDAOFactory.closeAll( conn, stmt, rs );
        }
        return;
    }

    public Long buscarFeriado( Date fechaFeriado ) throws DAOException {
        Long idFeriado = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select tn.ID from  EVNM_FERIADOS tn where FECHA = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setDate( 1, new java.sql.Date( fechaFeriado.getTime() ) );
            rs = stmt.executeQuery();
            if ( rs.next() )
                idFeriado = rs.getLong( "ID" );
        } catch ( RuntimeException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } catch ( DAOException e ) {
            throw e;
        } catch ( SQLException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } finally {
            OracleDAOFactory.closeAll( conn, stmt, rs );
        }
        return idFeriado;
    }
}
