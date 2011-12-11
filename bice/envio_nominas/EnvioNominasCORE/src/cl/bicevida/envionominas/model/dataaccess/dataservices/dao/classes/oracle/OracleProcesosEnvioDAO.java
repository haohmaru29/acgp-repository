package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.oracle;

import cl.bicevida.envionominas.model.bo.BancoProcesoBO;
import cl.bicevida.envionominas.model.bo.EstadoProcesoBO;
import cl.bicevida.envionominas.model.bo.NominaBO;
import cl.bicevida.envionominas.model.bo.ProcesoEnvioBO;
import cl.bicevida.envionominas.model.bo.RegistroNominaBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.DAOFactory;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.ProcesosEnvioDAO;

import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.RegistroNominaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementa el acceso a datos de la tabla "EVNM_PROCESOS".
 * 
 * Registro de versiones:
 *      - 1.0 17/11/2010 Giorgio Gortaire (ACGP) : Version inicial. 
 */
public class OracleProcesosEnvioDAO implements ProcesosEnvioDAO {
    private EnvioNominasConfig config;

    public OracleProcesosEnvioDAO() {
        config = new EnvioNominasConfig();
    }

    public List<ProcesoEnvioBO> queryProcesosByNomina( Long _idNomina ) throws DAOException {
        List<ProcesoEnvioBO> procesos = new ArrayList<ProcesoEnvioBO>();
        RegistroNominaDAO registrosNominaDAO = DAOFactory.getDAOFactory( DAOFactory.ORACLE ).getRegistroNominaDAO();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select ID_PROCESO, FECHA_CREACION, FOLIO_PROCESO, REFERENCIA_BANCO_PROCESO, REFERENCIA_NOMINA, FECHA_ENVIO, REFERENCIA_ESTADO_PROCESO, FECHA_ESTADO, OBSERVACIONES from EVNM_PROCESOS where REFERENCIA_NOMINA = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong( 1, _idNomina );
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                ProcesoEnvioBO proceso = new ProcesoEnvioBO();
                proceso.setId( rs.getLong( "ID_PROCESO" ) );
                proceso.setFechaCreacion( rs.getTimestamp( "FECHA_CREACION" ) );
                proceso.setFolioProcesoExterno( rs.getString( "FOLIO_PROCESO" ) );
                proceso.setBancoProceso( BancoProcesoBO.crear( rs.getLong( "REFERENCIA_BANCO_PROCESO" ) ) );
                proceso.setNomina( NominaBO.crear( rs.getLong( "REFERENCIA_NOMINA" ) ) );
                proceso.setFechaEnvio( rs.getTimestamp( "FECHA_ENVIO" ) );
                proceso.setEstado( EstadoProcesoBO.crear( rs.getLong( "REFERENCIA_ESTADO_PROCESO" ) ) );
                proceso.setFechaEstado( rs.getTimestamp( "FECHA_ESTADO" ) );
                proceso.setObservaciones( rs.getString( "OBSERVACIONES" ) );
                procesos.add( proceso );
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
        for ( ProcesoEnvioBO proceso: procesos ) {
            proceso.setRegistros( registrosNominaDAO.queryRegistrosByProcesoId( proceso.getId() ) );
        }
        return procesos;
    }

    /**
     * Inserta un nuevo registro en la tabla EVNM_PROCESOS a
     * partir de los datos de la nomina de entrada. Primero se consulta por el 
     * id que corresponde al nuevo registro, segun la secuencia de la tabla y 
     * posteriormente, se ejecuta el insert con los datos obtenidos de la 
     * nomina.
     * @param _proceso Proceso a partir del cual se crea el nuevo proceso.
     * @return Idenificador unico del proceso creado. En caso de que no se haya 
     * logrado insertar el nuevo registro, se retorna "NULL".
     * @throws DAOException
     */
    public void queryCrearProcesoEnvio( ProcesoEnvioBO _proceso ) throws DAOException {
        RegistroNominaDAO registroNominaDAO = DAOFactory.getDAOFactory( DAOFactory.ORACLE ).getRegistroNominaDAO();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Long idNuevoProceso = null;
        String sql = "select EVNM_PROCESOS_ID_SEQ.nextval from NOMINAS where ID = " + _proceso.getNomina().getId();
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            rs = stmt.executeQuery();
            if ( rs.next() ) {
                idNuevoProceso = rs.getLong( "NEXTVAL" );
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
        if ( idNuevoProceso != null ) {
            String strSql = "insert into EVNM_PROCESOS(ID_PROCESO,FECHA_CREACION, FOLIO_PROCESO, REFERENCIA_BANCO_PROCESO, REFERENCIA_NOMINA, FECHA_ENVIO, REFERENCIA_ESTADO_PROCESO, FECHA_ESTADO, OBSERVACIONES) values(?,current_timestamp,null,?,?,null,?,current_timestamp,null)";
            try {
                conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
                stmt = conn.prepareStatement( strSql.toString() );
                stmt.setLong( 1, idNuevoProceso );
                stmt.setLong( 2, _proceso.getBancoProceso().getId() );
                stmt.setLong( 3, _proceso.getNomina().getId() );
                stmt.setLong( 4, _proceso.getEstado().getId() );
                stmt.execute();
                _proceso.setId( idNuevoProceso );
            } catch ( RuntimeException e ) {
                throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
            } catch ( DAOException e ) {
                throw e;
            } catch ( SQLException e ) {
                throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
            } finally {
                OracleDAOFactory.closeAll( conn, stmt, null );
            }
            for ( RegistroNominaBO registro: _proceso.getRegistros() ) {
                registro.setProceso( _proceso );
                registroNominaDAO.queryAsociaRegistroProceso( registro );
            }
        }
    }

    public void queryCrearProcesosEnvio( NominaBO _nomina ) throws DAOException {
        for ( ProcesoEnvioBO proceso: _nomina.getProcesos() ) {
            queryCrearProcesoEnvio( proceso );
        }
    }

    public void updateProceso( ProcesoEnvioBO proceso ) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rs;
        String sql = "update EVNM_PROCESOS set FECHA_ENVIO = ? where ID_PROCESO = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setDate( 1, new java.sql.Date( proceso.getFechaEnvio().getTime() ) );
            stmt.setLong( 2, proceso.getId() );
            rs = stmt.executeUpdate();
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

    public ProcesoEnvioBO queryProcesoById( Long _idProceso ) throws DAOException {
        ProcesoEnvioBO proceso = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select ID_PROCESO, FECHA_CREACION, FOLIO_PROCESO, REFERENCIA_BANCO_PROCESO, REFERENCIA_NOMINA, FECHA_ENVIO, REFERENCIA_ESTADO_PROCESO, FECHA_ESTADO, OBSERVACIONES from EVNM_PROCESOS where ID_PROCESO = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong( 1, _idProceso );
            rs = stmt.executeQuery();
            if ( rs.next() ) {
                proceso = new ProcesoEnvioBO();
                proceso.setId( rs.getLong( "ID_PROCESO" ) );
                proceso.setFechaCreacion( rs.getTimestamp( "FECHA_CREACION" ) );
                proceso.setFolioProcesoExterno( rs.getString( "FOLIO_PROCESO" ) );
                proceso.setBancoProceso( BancoProcesoBO.crear( rs.getLong( "REFERENCIA_BANCO_PROCESO" ) ) );
                proceso.setNomina( NominaBO.crear( rs.getLong( "REFERENCIA_NOMINA" ) ) );
                proceso.setFechaEnvio( rs.getTimestamp( "FECHA_ENVIO" ) );
                proceso.setEstado( EstadoProcesoBO.crear( rs.getLong( "REFERENCIA_ESTADO_PROCESO" ) ) );
                proceso.setFechaEstado( rs.getTimestamp( "FECHA_ESTADO" ) );
                proceso.setObservaciones( rs.getString( "OBSERVACIONES" ) );
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
        if ( proceso != null ) {
        }
        return proceso;
    }

    public void queryActualizaEstadoProceso( Long _idProceso, Long _idEstado, String _observacion ) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = " update EVNM_PROCESOS set FECHA_ESTADO = current_timestamp, REFERENCIA_ESTADO_PROCESO = ?, OBSERVACIONES = ? ";
        if ( _idEstado.toString().equals( config.get( EnvioNominasConfig.ID_PROCESO_ESPERANDO_FOLIO ) ) ) {
            sql = sql + ", FECHA_ENVIO = current_timestamp ";
        }
        sql = sql + " where ID_PROCESO = ? ";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql.toString() );
            stmt.setLong( 1, _idEstado );
            stmt.setString( 2, _observacion );
            stmt.setLong( 3, _idProceso );
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

    public void queryEliminaProcesosByNomina( Long _idNomina ) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "delete from EVNM_REGISTROS_PROCESO where REFERENCIA_PROCESO in (select ID_PROCESO from EVNM_PROCESOS where REFERENCIA_NOMINA = ?)";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql.toString() );
            stmt.setLong( 1, _idNomina );
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
        conn = null;
        stmt = null;
        sql = "delete from EVNM_PROCESOS where ID_PROCESO in (select ID_PROCESO from EVNM_PROCESOS where REFERENCIA_NOMINA = ?)";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql.toString() );
            stmt.setLong( 1, _idNomina );
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

    public void queryActualizarEstadoProcesosByNomina( Long _idNomina, Long _idEstadoProceso, String _observacion ) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "update EVNM_PROCESOS set FECHA_ESTADO = current_timestamp, REFERENCIA_ESTADO_PROCESO = ? ";
        if ( _idEstadoProceso.toString().equals( config.get( EnvioNominasConfig.ID_PROCESO_ESPERANDO_FOLIO ) ) ) {
            sql = sql + ", FECHA_ENVIO = current_timestamp ";
        }
        sql = sql + ", OBSERVACIONES = ? where REFERENCIA_NOMINA = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql.toString() );
            stmt.setLong( 1, _idEstadoProceso );
            stmt.setString( 2, _observacion );
            stmt.setLong( 3, _idNomina );
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

    public ProcesoEnvioBO queryProcesoByIdRegistro( Long _idRegistro ) throws DAOException {
        ProcesoEnvioBO proceso = null;
        Long idProceso = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select REFERENCIA_PROCESO from EVNM_REGISTROS_PROCESO where REFERENCIA_TRANSACCION_PAGO = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong( 1, _idRegistro );
            rs = stmt.executeQuery();
            if ( rs.next() ) {
                idProceso = rs.getLong( "REFERENCIA_PROCESO" );
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
        if ( idProceso != null ) {
            proceso = queryProcesoById( idProceso );
        }
        return proceso;
    }

    public void queryRegistrarFolioProcesoExterno( Long _idProceso, String _folioExterno ) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "update EVNM_PROCESOS set FOLIO_PROCESO = ? where ID_PROCESO = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql.toString() );
            stmt.setString( 1, _folioExterno );
            stmt.setLong( 2, _idProceso );
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

    public Long queryProcesosSinFolioByNomina( Long _idNomina ) throws DAOException {
        Long cantidadProcesosSinFolio = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select count(distinct(p.ID_PROCESO)) as CANTIDAD_PROCESOS from EVNM_PROCESOS p where p.REFERENCIA_NOMINA = ? and p.FOLIO_PROCESO is null";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setLong( 1, _idNomina );
            rs = stmt.executeQuery();
            if ( rs.next() ) {
                cantidadProcesosSinFolio = rs.getLong( "CANTIDAD_PROCESOS" );
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
        return cantidadProcesosSinFolio;
    }

    public ProcesoEnvioBO queryProcesoByFolioExterno( String _folioExterno ) throws DAOException {
        ProcesoEnvioBO proceso = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select ID_PROCESO, FECHA_CREACION, FOLIO_PROCESO, REFERENCIA_BANCO_PROCESO, REFERENCIA_NOMINA, FECHA_ENVIO, REFERENCIA_ESTADO_PROCESO, FECHA_ESTADO, OBSERVACIONES from EVNM_PROCESOS where FOLIO_PROCESO = ?";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.prepareStatement( sql );
            stmt.setString( 1, _folioExterno );
            rs = stmt.executeQuery();
            if ( rs.next() ) {
                proceso = new ProcesoEnvioBO();
                proceso.setId( rs.getLong( "ID_PROCESO" ) );
                proceso.setFechaCreacion( rs.getTimestamp( "FECHA_CREACION" ) );
                proceso.setFolioProcesoExterno( rs.getString( "FOLIO_PROCESO" ) );
                proceso.setBancoProceso( BancoProcesoBO.crear( rs.getLong( "REFERENCIA_BANCO_PROCESO" ) ) );
                proceso.setNomina( NominaBO.crear( rs.getLong( "REFERENCIA_NOMINA" ) ) );
                proceso.setFechaEnvio( rs.getTimestamp( "FECHA_ENVIO" ) );
                proceso.setEstado( EstadoProcesoBO.crear( rs.getLong( "REFERENCIA_ESTADO_PROCESO" ) ) );
                proceso.setFechaEstado( rs.getTimestamp( "FECHA_ESTADO" ) );
                proceso.setObservaciones( rs.getString( "OBSERVACIONES" ) );
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
        return proceso;
    }
}
