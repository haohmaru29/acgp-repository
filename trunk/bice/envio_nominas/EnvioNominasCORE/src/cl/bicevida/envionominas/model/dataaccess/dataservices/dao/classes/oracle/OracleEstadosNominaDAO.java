package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.oracle;

import cl.bicevida.envionominas.model.bo.EstadoNominaBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.EstadosNominaDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Implementa el acceso a datos de la tabla "ESTADOS_NOMINA".
 * 
 * Registro de versiones:
 *      - 1.0 17/11/2010 Giorgio Gortaire (ACGP) : Version inicial. 
 */
public class OracleEstadosNominaDAO implements EstadosNominaDAO {
    private EnvioNominasConfig config;

    public OracleEstadosNominaDAO() {
        config = new EnvioNominasConfig();
    }

    public List<EstadoNominaBO> queryEstadosNomina() throws DAOException {
        List<EstadoNominaBO> estadosNomina = new ArrayList<EstadoNominaBO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select en.DESCRIPCION, en.ID, en.NOMBRE from ESTADOS_NOMINA en where en.ID > 100";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                EstadoNominaBO estadoNomina = new EstadoNominaBO();
                estadoNomina.setId( rs.getLong( "ID" ) );
                estadoNomina.setNombre( rs.getString( "NOMBRE" ) );
                estadosNomina.add( estadoNomina );
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
        return estadosNomina;
    }

    public EstadoNominaBO queryEstadoNominaById( Long idEstadoNomina ) throws DAOException {
        EstadoNominaBO estadoNomina = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select en.DESCRIPCION, en.ID, en.NOMBRE from ESTADOS_NOMINA en where en.ID = " + idEstadoNomina;
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                estadoNomina = new EstadoNominaBO();
                estadoNomina.setId( rs.getLong( "ID" ) );
                estadoNomina.setNombre( rs.getString( "NOMBRE" ) );
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
        return estadoNomina;
    }
}
