package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.oracle;

import cl.bicevida.envionominas.model.bo.TipoCuentaBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.TiposCuentaDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementa el acceso a datos de la tabla "TIPOS_CUENTA".
 * 
 * Registro de versiones:
 *      - 1.0 17/11/2010 Giorgio Gortaire (ACGP) : Version inicial. 
 */
public class OracleTiposCuentaDAO implements TiposCuentaDAO {
    private EnvioNominasConfig config;

    public OracleTiposCuentaDAO() {
        config = new EnvioNominasConfig();
    }

    public List<TipoCuentaBO> queryTiposCuenta() throws DAOException {
        List<TipoCuentaBO> tiposCuenta = new ArrayList<TipoCuentaBO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select tc.DESCRIPCION, tc.ID, tc.NOMBRE from TIPOS_CUENTA tc";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                TipoCuentaBO tipoCuenta = new TipoCuentaBO();
                tipoCuenta.setDescripcion( rs.getString( "DESCRIPCION" ) );
                tipoCuenta.setId( rs.getLong( "ID" ) );
                tipoCuenta.setNombre( rs.getString( "NOMBRE" ) );
                tiposCuenta.add( tipoCuenta );
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
        return tiposCuenta;
    }

    /**
     * 
     * @param id
     * @return
     * @throws DAOException
     */
    public TipoCuentaBO queryTipoCuentaById( Long id ) throws DAOException {
        TipoCuentaBO tipoCuenta = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select tc.DESCRIPCION, tc.ID, tc.NOMBRE from TIPOS_CUENTA tc where tc.ID = " + id;
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                tipoCuenta = new TipoCuentaBO();
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
        return tipoCuenta;
    }
}
