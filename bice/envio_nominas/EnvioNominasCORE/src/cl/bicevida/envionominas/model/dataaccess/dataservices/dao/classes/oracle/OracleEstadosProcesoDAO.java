package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.oracle;

import cl.bicevida.envionominas.model.bo.EstadoProcesoBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.EstadosProcesoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementa el acceso a datos de la tabla "EVNM_ESTADOS_PROCESO".
 * 
 * Registro de versiones:
 *      - 1.0 17/11/2010 Giorgio Gortaire (ACGP) : Version inicial. 
 */
public class OracleEstadosProcesoDAO implements EstadosProcesoDAO {
    private EnvioNominasConfig config;

    public OracleEstadosProcesoDAO() {
        config = new EnvioNominasConfig();
    }

    public EstadoProcesoBO queryEstadoById( Long _idEstado ) throws DAOException {
        EstadoProcesoBO estado = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = " select ep.ID_ESTADO_PROCESO as ID, ep.NOMBRE as NOMBRE from EVNM_ESTADOS_PROCESO ep where ep.ID_ESTADO_PROCESO = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong( 1, _idEstado );
            rs = stmt.executeQuery();
            if ( rs.next() ) {
                estado = new EstadoProcesoBO();
                estado.setId( rs.getLong( "ID" ) );
                estado.setNombre( rs.getString( "NOMBRE" ) );
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
        return estado;
    }
}
