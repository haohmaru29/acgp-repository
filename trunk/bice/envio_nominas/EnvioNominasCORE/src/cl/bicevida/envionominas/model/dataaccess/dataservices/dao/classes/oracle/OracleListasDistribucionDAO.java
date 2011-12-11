package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.oracle;

import cl.bicevida.envionominas.model.bo.ListaDistribucionBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.ListasDistribucionDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementa el acceso a datos de la tabla "EVNM_LISTAS_ORIGEN".
 * 
 * Registro de versiones:
 *      - 1.0 17/11/2010 Giorgio Gortaire (ACGP) : Version inicial. 
 */
public class OracleListasDistribucionDAO implements ListasDistribucionDAO {
    private EnvioNominasConfig config;

    public OracleListasDistribucionDAO() {
        config = new EnvioNominasConfig();
    }

    public List<ListaDistribucionBO> queryListaDistribucion() throws DAOException {
        List<ListaDistribucionBO> ListaDistribucion = new ArrayList<ListaDistribucionBO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select tn.ID, tn.ORIGEN, tn.NOMBRE from EVNM_LISTAS_ORIGEN tn";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                ListaDistribucionBO tListaDistribucion = new ListaDistribucionBO();
                tListaDistribucion.setId( rs.getLong( "ID" ) );
                tListaDistribucion.setOrigenLista( rs.getString( "ORIGEN" ) );
                tListaDistribucion.setNombreLista( rs.getString( "NOMBRE" ) );
                ListaDistribucion.add( tListaDistribucion );
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
        return ListaDistribucion;
    }

    public void agregarListaDistribucion( String _origenLista, String _nombreLista ) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "insert into EVNM_LISTAS_ORIGEN values ((select nvl(max(tn.ID), 0) + 1 from EVNM_LISTAS_ORIGEN tn),?,?)";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setString( 1, _nombreLista );
            stmt.setString( 2, _origenLista );
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

    public void eliminarListaDistribucion( Long _idLista ) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "delete EVNM_LISTAS_ORIGEN where ID = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong( 1, _idLista );
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

    public void modificarListaDistribucion( Long idLista, String origenLista, String nombreLista ) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "update EVNM_LISTAS_ORIGEN set ORIGEN = ?, NOMBRE = ? where ID = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setString( 1, origenLista );
            stmt.setString( 2, nombreLista );
            stmt.setLong( 3, idLista );
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

    public Long buscarListaDistribucion( String origenLista ) throws DAOException {
        Long idLista = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select tn.ID from  EVNM_LISTAS_ORIGEN tn where ORIGEN = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setString( 1, origenLista );
            rs = stmt.executeQuery();
            if ( rs.next() )
                idLista = rs.getLong( "ID" );
        } catch ( RuntimeException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } catch ( DAOException e ) {
            throw e;
        } catch ( SQLException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } finally {
            OracleDAOFactory.closeAll( conn, stmt, rs );
        }
        return idLista;
    }

    public List<ListaDistribucionBO> queryListasByOrigenNomina( String origenLista ) throws DAOException {
        List<ListaDistribucionBO> ListaDistribucion = new ArrayList<ListaDistribucionBO>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select tn.ID, tn.NOMBRE, tn.ORIGEN from EVNM_LISTAS_ORIGEN  tn where ORIGEN = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setString( 1, origenLista );
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                ListaDistribucionBO tListaDistribucion = new ListaDistribucionBO();
                tListaDistribucion.setId( rs.getLong( "ID" ) );
                tListaDistribucion.setOrigenLista( rs.getString( "ORIGEN" ) );
                tListaDistribucion.setNombreLista( rs.getString( "NOMBRE" ) );
                ListaDistribucion.add( tListaDistribucion );
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
        return ListaDistribucion;
    }
}
