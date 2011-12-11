package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.oracle;

import cl.bicevida.envionominas.model.bo.ArchivoBO;
import cl.bicevida.envionominas.model.bo.BancoProcesoBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;

import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.ArchivosNominasDAO;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementa el acceso a datos de la tabla "EVNM_ARCHIVOS_NOMINAS".
 * 
 * Registro de versiones:
 *      - 1.0 17/11/2010 Giorgio Gortaire (ACGP) : Version inicial. 
 */
public class OracleArchivosNominasDAO implements ArchivosNominasDAO {
    private EnvioNominasConfig config;

    public OracleArchivosNominasDAO() {
        config = new EnvioNominasConfig();
    }

    public InputStream queryArchivoByNombre( String _nombreArchivo ) throws DAOException {
        InputStream output = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select CUERPO from EVNM_ARCHIVOS_NOMINAS where NOMBRE_ARCHIVO = ? ";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setString( 1, _nombreArchivo );
            rs = stmt.executeQuery();
            if ( rs.next() ) {
                output = rs.getClob( "CUERPO" ).getAsciiStream();
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
        return output;
    }

    public List<ArchivoBO> queryArchivosNominas() throws DAOException {
        List<ArchivoBO> lista = new ArrayList<ArchivoBO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select AN.NOMBRE_ARCHIVO as NOMBRE_ARCHIVO, AN.REFERENCIA_BANCO_PROCESO as ID_BANCO_PROCESO, AN.FECHA_CREACION as FECHA_CREACION_ARCHIVO, AN.CUERPO as CUERPO_ARCHIVO, BP.NOMBRE as NOMBRE_BANCO_PROCESO from EVNM_ARCHIVOS_NOMINAS AN join EVNM_BANCOS_PROCESO BP on BP.ID_BANCO_PROCESO = AN.REFERENCIA_BANCO_PROCESO";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                ArchivoBO archivo = new ArchivoBO();
                archivo.setNombre( rs.getString( "NOMBRE_ARCHIVO" ) );
                archivo.setFechaCreacion( rs.getDate( "FECHA_CREACION_ARCHIVO" ) );
                archivo.setBancoProceso( BancoProcesoBO.crear( rs.getLong( "ID_BANCO_PROCESO" ), rs.getString( "NOMBRE_BANCO_PROCESO" ) ) );
                lista.add( archivo );
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
    
    public List<ArchivoBO> queryArchivosByTipo(String idTipo) throws DAOException {
        List<ArchivoBO> lista = new ArrayList<ArchivoBO>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM EVNM_ARCHIVOS_NOMINAS WHERE ID_TIPO=?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong(1, Long.valueOf(idTipo) );
            System.out.println(sql);
            rs = stmt.executeQuery();
            ArchivoBO archivo = null;
            while ( rs.next() ) {
                archivo = new ArchivoBO();
                archivo.setIdTipo(rs.getString("ID_TIPO"));
                archivo.setCuerpoArchivo(rs.getString("CUERPO"));
                archivo.setFechaCreacion(rs.getDate("FECHA_CREACION"));
                archivo.setIdEstado(rs.getLong("ID_ESTADO_EVNM_NOMINA") );
                archivo.setNombre(rs.getString("NOMBRE_ARCHIVO") );
                lista.add(archivo);
            }
        } catch ( RuntimeException e ) {
            e.printStackTrace();
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
    
    public int queryActualizarEstadoRegistroError( Long _idRegistro, Long _idEstado, String _observacion, String _cuentaTitular, 
                Long rutTitular, Long monto, Long idNomina ) throws DAOException {
            Connection conn = null;
            int response = -1;
            PreparedStatement stmt = null;
            String sql = "update TRANSACCIONES_PAGO tp set tp.REFERENCIA_ESTADO_TRANSACCION = ?, tp.FECHA_ESTADO = current_timestamp, where tp.ID = ?" 
                    + " and tp.CUENTA_TITULAR = ? and tp.RUT_TITULAR = ? and tp.REFERENCIA_NOMINA = ? and tp.MONTO = ? ";
            try {
                conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
                stmt = conn.prepareStatement( sql );
                stmt.setLong( 1, _idEstado );
                stmt.setLong( 2, _idRegistro );
                stmt.setString(3, _cuentaTitular);
                stmt.setLong(4, rutTitular);
                stmt.setLong(5, idNomina);
                stmt.setLong(6, monto);
                response = stmt.executeUpdate();
            } catch ( RuntimeException e ) {
                throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
            } catch ( SQLException e ) {
                throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
            } finally {
                OracleDAOFactory.closeAll( conn, stmt, null );
            }
            
            return response;
    }
    
    public void queryActualizaEvmnArchivosNominas(java.sql.Date fechaCreacion, String  nombreArchivo) throws DAOException {
        Connection conn = null;
        int response = -1;
        PreparedStatement stmt = null;
        String sql = "update EVNM_ARCHIVOS_NOMINAS tp set tp.ID_ESTADO_EVNM_NOMINA = ? WHERE NOMBRE_ARCHIVO=? AND FECHA_CREACION = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong( 1, 1 );
            stmt.setString(2, nombreArchivo);
            stmt.setDate(3, fechaCreacion);
            response = stmt.executeUpdate();
        } catch ( RuntimeException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } catch ( SQLException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } finally {
            OracleDAOFactory.closeAll( conn, stmt, null );
        }
    }
}
