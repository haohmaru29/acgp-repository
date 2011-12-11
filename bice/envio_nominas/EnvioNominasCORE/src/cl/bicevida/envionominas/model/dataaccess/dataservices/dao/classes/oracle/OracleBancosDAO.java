package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.oracle;

import cl.bicevida.envionominas.model.bo.BancoBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.BancosDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementa el acceso a datos de la tabla "BANCOS".
 * 
 * Registro de versiones:
 *      - 1.0 17/11/2010 Giorgio Gortaire (ACGP) : Version inicial. 
 */
public class OracleBancosDAO implements BancosDAO {
    private EnvioNominasConfig config;

    public OracleBancosDAO() {
        config = new EnvioNominasConfig();
    }

    /**
     * Consulta los registros de la tabla "BANCOS".
     * @return Listado de bancos contenidos en la tala BANCOS. En caso de no
     * econtrar registros, retorna una lista vacia.
     * @throws DAOException
     */
    public List<BancoBO> queryBancos() throws DAOException {
        List<BancoBO> bancos = new ArrayList<BancoBO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select b.ID, b.CODIGO, b.CODIGO_MATRIZ, b.NOMBRE from BANCOS b";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                BancoBO banco = new BancoBO();
                banco.setId( rs.getLong( "ID" ) );
                banco.setCodigo( rs.getString( "CODIGO" ) );
                banco.setCodigoMatriz( rs.getString( "CODIGO_MATRIZ" ) );
                banco.setNombre( rs.getString( "NOMBRE" ) );
                bancos.add( banco );
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
        return bancos;
    }

    /**
     * Consulta los datos de un banco a partir de su identificador.
     * @param _id Identificador del banco que se busca.
     * @return Datos asociados al banco especificado por su identificador.
     * @throws DAOException
     */
    public BancoBO queryBancoById( Long _id ) throws DAOException {
        BancoBO banco = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select b.ID, b.CODIGO, b.CODIGO_MATRIZ, b.NOMBRE from BANCOS b where b.ID = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong( 1, _id );
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                banco = new BancoBO();
                banco.setId( rs.getLong( "ID" ) );
                banco.setCodigo( rs.getString( "CODIGO" ) );
                banco.setCodigoMatriz( rs.getString( "CODIGO_MATRIZ" ) );
                banco.setNombre( rs.getString( "NOMBRE" ) );
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
        return banco;
    }
}
