package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.oracle;

import cl.bicevida.envionominas.model.bo.EmpresaBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.EmpresasDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementa el acceso a datos de la tabla "EMPRESAS".
 * 
 * Registro de versiones:
 *      - 1.0 17/11/2010 Giorgio Gortaire (ACGP) : Version inicial. 
 */
public class OracleEmpresasDAO implements EmpresasDAO {
    private EnvioNominasConfig config;

    public OracleEmpresasDAO() {
        config = new EnvioNominasConfig();
    }

    /**
     * Consulta la tabla "EMPRESAS" por la totalidad de sus registros.
     * @return Listado 
     * @throws DAOException
     */
    public List<EmpresaBO> queryEmpresas() throws DAOException {
        List<EmpresaBO> empresas = new ArrayList<EmpresaBO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select e.ID, e.NOMBRE, e.RUT, e.DV from EMPRESAS e";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                EmpresaBO empresa = new EmpresaBO();
                empresa.setId( rs.getLong( "ID" ) );
                empresa.setNombre( rs.getString( "NOMBRE" ) );
                empresa.setRut( rs.getString( "RUT" ) );
                empresa.setDvRut( rs.getString( "DV" ) );
                empresas.add( empresa );
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
        return empresas;
    }

    /**
     * 
     * @param id
     * @return
     * @throws DAOException
     */
    public EmpresaBO queryEmpresaById( Long id ) throws DAOException {
        EmpresaBO empresa = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select e.ID, e.NOMBRE, e.RUT, e.DV from EMPRESAS e where e.ID = " + id;
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                empresa = new EmpresaBO();
                empresa.setId( rs.getLong( "ID" ) );
                empresa.setNombre( rs.getString( "NOMBRE" ) );
                empresa.setRut( rs.getString( "RUT" ) );
                empresa.setDvRut( rs.getString( "DV" ) );
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
        return empresa;
    }
}
