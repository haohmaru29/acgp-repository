package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.oracle;

import cl.bicevida.envionominas.model.bo.BancoBO;
import cl.bicevida.envionominas.model.bo.BancoProcesoBO;
import cl.bicevida.envionominas.model.bo.ParametroBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.BancosProcesoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementa el acceso a datos de la tabla "EVNM_BANCOS_PROCESO".
 * 
 * Registro de versiones:
 *      - 1.0 17/11/2010 Giorgio Gortaire (ACGP) : Version inicial. 
 */
public class OracleBancosProcesoDAO implements BancosProcesoDAO {
    private EnvioNominasConfig config;

    public OracleBancosProcesoDAO() {
        config = new EnvioNominasConfig();
    }

    public BancoProcesoBO queryBancoProcesoById( Long _id ) throws DAOException {
        BancoProcesoBO bancoProceso = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select bp.ID_BANCO_PROCESO as ID_BANCO_PROCESO, bp.REFERENCIA_BANCO as ID_BANCO, b.CODIGO as COD_BANCO, b.CODIGO_MATRIZ as COD_MATRIZ_BANCO, b.NOMBRE as NOMBRE_BANCO, bp.NOMBRE as NOMBRE, pr.CLAVE as NOMBRE_PARAMETRO, pr.VALOR as VALOR_PARAMETRO, pr.TIPO as TIPO_PARAMETRO from EVNM_BANCOS_PROCESO bp join BANCOS b on bp.REFERENCIA_BANCO = b.ID join (select pbp.REFERENCIA_BANCO_PROCESO, p.CLAVE, p.VALOR, p.TIPO from EVNM_PARAMETROS_BANCO_PROCESO pbp join EVNM_PARAMETROS p on p.ID = pbp.REFERENCIA_PARAMETRO) pr on pr.REFERENCIA_BANCO_PROCESO = bp.ID_BANCO_PROCESO where bp.ID_BANCO_PROCESO = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong( 1, _id );
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                if ( bancoProceso == null ) {
                    bancoProceso = new BancoProcesoBO();
                    bancoProceso.setId( rs.getLong( "ID_BANCO_PROCESO" ) );
                    bancoProceso.setNombre( "NOMBRE" );
                    bancoProceso.setBanco( BancoBO.crear( rs.getLong( "ID_BANCO" ), rs.getString( "COD_BANCO" ), rs.getString( "COD_MATRIZ_BANCO" ), rs.getString( "NOMBRE_BANCO" ) ) );
                }
                if ( bancoProceso.getParametros() == null ) {
                    bancoProceso.setParametros( new ArrayList<ParametroBO>() );
                }
                bancoProceso.getParametros().add( ParametroBO.crear( rs.getString( "NOMBRE_PARAMETRO" ), rs.getString( "VALOR_PARAMETRO" ), rs.getString( "TIPO_PARAMETRO" ) ) );
            }
            //TODO:Agregar comisiones a la consulta de banco proceso por ID.
        } catch ( RuntimeException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } catch ( DAOException e ) {
            throw e;
        } catch ( SQLException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } finally {
            OracleDAOFactory.closeAll( conn, stmt, rs );
        }
        return bancoProceso;
    }

    public List<BancoProcesoBO> queryBancosProceso() throws DAOException {
        List<BancoProcesoBO> lista = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select bp.ID_BANCO_PROCESO as ID_BANCO_PROCESO, bp.NOMBRE as NOMBRE, b.NOMBRE as NOMBRE_BANCO  from EVNM_BANCOS_PROCESO bp left outer join BANCOS b on bp.REFERENCIA_BANCO = b.ID";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                if ( lista == null ) {
                    lista = new ArrayList<BancoProcesoBO>();
                }
                BancoProcesoBO bancoProceso = new BancoProcesoBO();
                bancoProceso.setId( rs.getLong( "ID_BANCO_PROCESO" ) );
                bancoProceso.setNombre( rs.getString( "NOMBRE" ) );
                bancoProceso.setBanco( BancoBO.crear( rs.getString( "NOMBRE_BANCO" ) ) );
                lista.add( bancoProceso );
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

    public void queryCrearBancoProceso( String _nombre, Long _idBanco ) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "insert into EVNM_BANCOS_PROCESO(ID_BANCO_PROCESO, REFERENCIA_BANCO,NOMBRE) values(EVNM_BANCOS_PROCESO_ID_SEQ.nextval,?,?)";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong( 1, _idBanco );
            stmt.setString( 2, _nombre );
            stmt.execute();
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

    public void queryEliminarBancoProceso( Long _idBanco ) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;
        try {
            sql = "delete from EVNM_COMISIONES where REFERENCIA_BANCO_PROCESO = ?";
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong( 1, _idBanco );
            stmt.execute();
        } catch ( RuntimeException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } catch ( DAOException e ) {
            throw e;
        } catch ( SQLException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } finally {
            OracleDAOFactory.closeAll( conn, stmt, null );
        }
        try {
            sql = "delete from EVNM_PARAMETROS where ID in(select REFERENCIA_PARAMETRO from EVNM_PARAMETROS_BANCO_PROCESO where REFERENCIA_BANCO_PROCESO = ?)";
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong( 1, _idBanco );
            stmt.execute();
        } catch ( RuntimeException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } catch ( DAOException e ) {
            throw e;
        } catch ( SQLException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } finally {
            OracleDAOFactory.closeAll( conn, stmt, null );
        }
        try {
            sql = "delete from EVNM_PARAMETROS_BANCO_PROCESO where REFERENCIA_BANCO_PROCESO = ?";
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong( 1, _idBanco );
            stmt.execute();
        } catch ( RuntimeException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } catch ( DAOException e ) {
            throw e;
        } catch ( SQLException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } finally {
            OracleDAOFactory.closeAll( conn, stmt, null );
        }
        try {
            sql = "delete from EVNM_BANCOS_PROCESO where ID_BANCO_PROCESO = ?";
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong( 1, _idBanco );
            stmt.execute();
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

    public Boolean queryIsBancoProcesoEnUso( Long _idBancoProceso ) throws DAOException {
        Boolean result = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        String sql = "select case when count(1) = 0 then 'false' else 'true' end as EN_USO from EVNM_PROCESOS where REFERENCIA_BANCO_PROCESO = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong( 1, _idBancoProceso );
            rs = stmt.executeQuery();
            if ( rs.next() ) {
                if ( "true".equals( rs.getString( "EN_USO" ) ) ) {
                    result = Boolean.TRUE;
                } else {
                    result = Boolean.FALSE;
                }
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
        return result;
    }
}
