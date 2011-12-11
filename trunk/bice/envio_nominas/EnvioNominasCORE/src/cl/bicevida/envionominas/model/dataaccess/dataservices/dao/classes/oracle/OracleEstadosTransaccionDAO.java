package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.oracle;

import cl.bicevida.envionominas.model.bo.EstadoTransaccionBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.EstadosTransaccionDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementa el acceso a datos de la tabla "ESTADOS_TRANSACCION_PAGO".
 * 
 * Registro de versiones:
 *      - 1.0 17/11/2010 Giorgio Gortaire (ACGP) : Version inicial. 
 */
public class OracleEstadosTransaccionDAO implements EstadosTransaccionDAO {
    private EnvioNominasConfig config;

    public OracleEstadosTransaccionDAO() {
        config = new EnvioNominasConfig();
    }

    public List<EstadoTransaccionBO> queryEstadosTransaccion() throws DAOException {
        List<EstadoTransaccionBO> estadosTransaccion = new ArrayList<EstadoTransaccionBO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select et.DESCRIPCION, et.ID, et.NOMBRE from ESTADOS_TRANSACCION_PAGO et";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                EstadoTransaccionBO estadoTransaccion = new EstadoTransaccionBO();
                estadoTransaccion.setId( rs.getLong( "ID" ) );
                estadoTransaccion.setNombre( rs.getString( "NOMBRE" ) );
                estadoTransaccion.setDescripcion( rs.getString( "DESCRIPCION" ) );
                estadosTransaccion.add( estadoTransaccion );
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
        return estadosTransaccion;
    }

    public EstadoTransaccionBO queryEstadosTransaccionById( Long _idEstadoTransaccion ) throws DAOException {
        EstadoTransaccionBO estadoTransaccion = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select et.DESCRIPCION, et.ID, et.NOMBRE from ESTADOS_TRANSACCION_PAGO et where et.ID = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong( 1, _idEstadoTransaccion );
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                estadoTransaccion = new EstadoTransaccionBO();
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
        return estadoTransaccion;
    }
}
