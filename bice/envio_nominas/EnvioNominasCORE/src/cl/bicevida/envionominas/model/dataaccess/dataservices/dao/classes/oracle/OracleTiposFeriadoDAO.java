package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.oracle;

import cl.bicevida.envionominas.model.bo.TipoFeriadoBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.TiposFeriadoDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementa el acceso a datos de la tabla "EVNM_TIPOS_FERIADO".
 * 
 * Registro de versiones:
 *      - 1.0 17/11/2010 Giorgio Gortaire (ACGP) : Version inicial. 
 */
public class OracleTiposFeriadoDAO implements TiposFeriadoDAO {
    private EnvioNominasConfig config;

    public OracleTiposFeriadoDAO() {
        config = new EnvioNominasConfig();
    }

    public List<TipoFeriadoBO> queryTiposFeriado() throws DAOException {
        List<TipoFeriadoBO> tiposFeriado = new ArrayList<TipoFeriadoBO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select tn.DESCRIPCION, tn.ID, tn.NOMBRE from EVNM_TIPOS_FERIADO tn";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                TipoFeriadoBO tipoFeriado = new TipoFeriadoBO();
                tipoFeriado.setId( rs.getLong( "ID" ) );
                tipoFeriado.setNombre( rs.getString( "NOMBRE" ) );
                tipoFeriado.setDescripcion( rs.getString( "DESCRIPCION" ) );
                tiposFeriado.add( tipoFeriado );
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
        return tiposFeriado;
    }
}
