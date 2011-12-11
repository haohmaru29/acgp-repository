package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.oracle;

import cl.bicevida.envionominas.model.bo.ParametroBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.ParametrosDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementa el acceso a datos de la tabla "EVNM_PARAMETROS".
 * 
 * Registro de versiones:
 *      - 1.0 17/11/2010 Giorgio Gortaire (ACGP) : Version inicial. 
 */
public class OracleParametrosDAO implements ParametrosDAO {
    private EnvioNominasConfig config;

    public OracleParametrosDAO() {
        config = new EnvioNominasConfig();
    }

    public List<ParametroBO> queryParametro() throws DAOException {
        List<ParametroBO> Parametro = new ArrayList<ParametroBO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select tn.ID, tn.CLAVE, tn.VALOR, tn.DESCRIPCION from EVNM_PARAMETROS tn";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                ParametroBO tParametro = new ParametroBO();
                tParametro.setId( rs.getLong( "ID" ) );
                tParametro.setClaveParametro( rs.getString( "CLAVE" ) );
                tParametro.setValorParametro( rs.getString( "VALOR" ) );
                tParametro.setDescripcionParametro( rs.getString( "DESCRIPCION" ) );
                Parametro.add( tParametro );
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
        return Parametro;
    }

    public void agregarParametro( String _claveParametro, String _valorParametro, String _descripcionParametro, String _tipoParametro ) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = " insert into EVNM_PARAMETROS values (EVNM_PARAMETROS_ID_SEQ.nextval,?,?,?,?)";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setString( 1, _claveParametro );
            stmt.setString( 2, _valorParametro );
            stmt.setString( 3, _descripcionParametro );
            stmt.setString( 4, _tipoParametro );
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

    public void eliminarParametro( Long _idParametro ) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "delete EVNM_PARAMETROS where ID = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong( 1, _idParametro );
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

    public void modificarParametro( Long idParametro, String claveParametro, String valorParametro, String descripcionParametro, String _tipoParametro ) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "update EVNM_PARAMETROS set CLAVE = ?, VALOR = ?, DESCRIPCION = ?, TIPO = ? where ID = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setString( 1, claveParametro );
            stmt.setString( 2, valorParametro );
            stmt.setString( 3, descripcionParametro );
            stmt.setString( 4, _tipoParametro );
            stmt.setLong( 5, idParametro );
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

    public Long buscarParametro( String claveParametro, Long _idBancoProceso ) throws DAOException {
        Long idParametro = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select p.ID from EVNM_PARAMETROS p join EVNM_PARAMETROS_BANCO_PROCESO pbp on pbp.REFERENCIA_PARAMETRO = p.ID where p.CLAVE = ? and pbp.REFERENCIA_BANCO_PROCESO = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setString( 1, claveParametro );
            stmt.setLong( 2, _idBancoProceso );
            rs = stmt.executeQuery();
            if ( rs.next() )
                idParametro = rs.getLong( "ID" );
        } catch ( RuntimeException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } catch ( DAOException e ) {
            throw e;
        } catch ( SQLException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } finally {
            OracleDAOFactory.closeAll( conn, stmt, rs );
        }
        return idParametro;
    }

    public void queryCrearParametroBancoProceso( Long _idBancoProceso, String _claveParametro, String _valorParametro, String _descripcionParametro, String _tipoParametro ) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Long idNuevoParametro = null;
        boolean insertOK = false;
        String sql = "select EVNM_PARAMETROS_ID_SEQ.nextval as NEXTVAL from EVNM_BANCOS_PROCESO where ID_BANCO_PROCESO = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong( 1, _idBancoProceso );
            rs = stmt.executeQuery();
            if ( rs.next() ) {
                idNuevoParametro = rs.getLong( "NEXTVAL" );
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
        if ( idNuevoParametro != null ) {
            sql = "insert into EVNM_PARAMETROS(ID,CLAVE,VALOR,DESCRIPCION,TIPO) values(?,?,?,?,?)";
            try {
                conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
                stmt = conn.prepareStatement( sql );
                stmt.setLong( 1, idNuevoParametro );
                stmt.setString( 2, _claveParametro );
                stmt.setString( 3, _valorParametro );
                stmt.setString( 4, _descripcionParametro );
                stmt.setString( 5, _tipoParametro );
                stmt.execute();
                insertOK = true;
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
        if ( insertOK ) {
            sql = "insert into EVNM_PARAMETROS_BANCO_PROCESO(REFERENCIA_BANCO_PROCESO, REFERENCIA_PARAMETRO) values(?,?)";
            try {
                conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
                stmt = conn.prepareStatement( sql );
                stmt.setLong( 1, _idBancoProceso );
                stmt.setLong( 2, idNuevoParametro );
                stmt.execute();
                insertOK = true;
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

    public List<ParametroBO> queryParametrosByBancoProceso( Long _idBancoProceso ) throws DAOException {
        List<ParametroBO> lista = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select p.ID, p.CLAVE, p.VALOR, p.DESCRIPCION, p.TIPO from EVNM_PARAMETROS p where p.ID in (select r.REFERENCIA_PARAMETRO from EVNM_PARAMETROS_BANCO_PROCESO r where r.REFERENCIA_BANCO_PROCESO = ?)";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong( 1, _idBancoProceso );
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                if ( lista == null ) {
                    lista = new ArrayList<ParametroBO>();
                }
                ParametroBO parametro = new ParametroBO();
                parametro.setId( rs.getLong( "ID" ) );
                parametro.setClaveParametro( rs.getString( "CLAVE" ) );
                parametro.setValorParametro( rs.getString( "VALOR" ) );
                parametro.setDescripcionParametro( rs.getString( "DESCRIPCION" ) );
                parametro.setTipoParametro( rs.getString( "TIPO" ) );
                lista.add( parametro );
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
}
