package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.oracle;

import cl.bicevida.envionominas.model.bo.CorreoBO;
import cl.bicevida.envionominas.model.bo.EstadoNominaBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.CorreosDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementa el acceso a datos de la tabla "EVNM_CORREOS".
 * 
 * Registro de versiones:
 *      - 1.0 17/11/2010 Giorgio Gortaire (ACGP) : Version inicial. 
 */
public class OracleCorreosDAO implements CorreosDAO {
    private EnvioNominasConfig config;

    public OracleCorreosDAO() {
        config = new EnvioNominasConfig();
    }

    public List<CorreoBO> queryCorreos() throws DAOException {
        List<CorreoBO> lista = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select c.ID, c.SUBJECT, c.MENSAJE, c.REFERENCIA_ESTADO_NOMINA, e.NOMBRE as NOMBRE_ESTADO, c.TIPO, c.NOMBRE from EVNM_CORREOS c left outer join ESTADOS_NOMINA e on e.ID = c.REFERENCIA_ESTADO_NOMINA";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                if ( lista == null ) {
                    lista = new ArrayList<CorreoBO>();
                }
                CorreoBO correo = new CorreoBO();
                if ( rs.getLong( "REFERENCIA_ESTADO_NOMINA" ) != 0 ) {
                    correo.setEstado( EstadoNominaBO.crear( rs.getLong( "REFERENCIA_ESTADO_NOMINA" ), rs.getString( "NOMBRE_ESTADO" ) ) );
                }
                correo.setId( rs.getLong( "ID" ) );
                correo.setNombre( rs.getString( "NOMBRE" ) );
                correo.setSubject( rs.getString( "SUBJECT" ) );
                correo.setMensaje( rs.getString( "MENSAJE" ) );
                correo.setTipo( rs.getString( "TIPO" ) );
                lista.add( correo );
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

    public void queryCrearNuevoCorreo( String _nombre, String _subject, String _mensaje, String _tipo, Long _idEstado ) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "INSERT INTO XXBV_SEGPAG.EVNM_CORREOS(ID, SUBJECT, MENSAJE, REFERENCIA_ESTADO_NOMINA, TIPO, NOMBRE) VALUES(EVNM_CORREOS_ID_SEQ.nextval,'" + _subject + "','" + _mensaje + "'," + _idEstado + ",'" + _tipo + "','" + _nombre + "')";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
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

    public void queryEliminarCorreo( Long _idCorreo ) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "delete EVNM_CORREOS where ID = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong( 1, _idCorreo );
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

    public void queryModificarCorreo( Long _idCorreo, String _nombre, String _subject, String _mensaje, String _tipo, Long _idEstado ) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "update EVNM_CORREOS set NOMBRE= ? ,SUBJECT = ? ,MENSAJE= ? ,TIPO = ?  ,REFERENCIA_ESTADO_NOMINA = ? where ID = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setString( 1, _nombre );
            stmt.setString( 2, _subject );
            stmt.setString( 3, _mensaje );
            stmt.setString( 4, _tipo );
            stmt.setLong( 5, _idEstado );
            stmt.setLong( 6, _idCorreo );
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

    public Long queryBuscarCorreoByNombre( String _nombre ) throws DAOException {
        Long idCorreo = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select tn.ID from  EVNM_CORREOS tn where NOMBRE  = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql.toString() );
            stmt.setString( 1, _nombre );
            rs = stmt.executeQuery();
            if ( rs.next() )
                idCorreo = rs.getLong( "ID" );
        } catch ( RuntimeException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } catch ( DAOException e ) {
            throw e;
        } catch ( SQLException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } finally {
            OracleDAOFactory.closeAll( conn, stmt, rs );
        }
        return idCorreo;
    }

    public CorreoBO queryCorreoByEstadoNomina( Long _idEstado ) throws DAOException {
        CorreoBO correo = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select c.ID, c.SUBJECT, c.MENSAJE, c.REFERENCIA_ESTADO_NOMINA, c.TIPO, c.NOMBRE, e.NOMBRE as NOMBRE_ESTADO from EVNM_CORREOS c join ESTADOS_NOMINA e on c.REFERENCIA_ESTADO_NOMINA = e.ID where c.REFERENCIA_ESTADO_NOMINA = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong( 1, _idEstado );
            rs = stmt.executeQuery();
            if ( rs.next() ) {
                correo = new CorreoBO();
                if ( rs.getLong( "REFERENCIA_ESTADO_NOMINA" ) != 0 ) {
                    correo.setEstado( EstadoNominaBO.crear( rs.getLong( "REFERENCIA_ESTADO_NOMINA" ), rs.getString( "NOMBRE_ESTADO" ) ) );
                }
                correo.setId( rs.getLong( "ID" ) );
                correo.setNombre( rs.getString( "NOMBRE" ) );
                correo.setSubject( rs.getString( "SUBJECT" ) );
                correo.setMensaje( rs.getString( "MENSAJE" ) );
                correo.setTipo( rs.getString( "TIPO" ) );
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
        return correo;
    }
}
