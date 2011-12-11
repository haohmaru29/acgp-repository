package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.oracle;

import cl.bicevida.envionominas.model.bo.BancoBO;
import cl.bicevida.envionominas.model.bo.OrigenBO;
import cl.bicevida.envionominas.model.bo.TipoNominaBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.DAOFactory;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.BancosDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.OrigenesDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.TiposNominaDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementa el acceso a datos de la tabla "TIPOS_NOMINA".
 * 
 * Registro de versiones:
 *      - 1.0 17/11/2010 Giorgio Gortaire (ACGP) : Version inicial. 
 */
public class OracleTiposNominaDAO implements TiposNominaDAO {
    private EnvioNominasConfig config;

    public OracleTiposNominaDAO() {
        config = new EnvioNominasConfig();
    }

    public List<TipoNominaBO> queryTiposNomina() throws DAOException {
        List<TipoNominaBO> tiposNomina = new ArrayList<TipoNominaBO>();
        BancosDAO bancoDAO = DAOFactory.getDAOFactory( DAOFactory.ORACLE ).getBancosDAO();
        OrigenesDAO origenesDAO = DAOFactory.getDAOFactory( DAOFactory.ORACLE ).getOrigenesDAO();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select tn.DESCRIPCION, tn.ID, tn.NOMBRE, tn.REFERENCIA_BANCO, tn.REFERENCIA_ORIGENES from TIPOS_NOMINA tn";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                TipoNominaBO tipoNomina = new TipoNominaBO();
                tipoNomina.setId( rs.getLong( "ID" ) );
                tipoNomina.setNombre( rs.getString( "NOMBRE" ) );
                tipoNomina.setDescripcion( rs.getString( "DESCRIPCION" ) );
                tipoNomina.setOrigen( OrigenBO.crear( rs.getString( "REFERENCIA_ORIGENES" ) ) );
                tipoNomina.setBanco( BancoBO.crear( rs.getLong( "REFERENCIA_BANCO" ) ) );
                tiposNomina.add( tipoNomina );
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
        for ( TipoNominaBO tipo: tiposNomina ) {
            tipo.setBanco( bancoDAO.queryBancoById( tipo.getBanco().getId() ) );
            tipo.setOrigen( origenesDAO.queryOrigenById( tipo.getOrigen().getCodigo() ) );
        }
        return tiposNomina;
    }

    public TipoNominaBO queryTipoNominaById( Long id ) throws DAOException {
        BancosDAO bancoDAO = DAOFactory.getDAOFactory( DAOFactory.ORACLE ).getBancosDAO();
        OrigenesDAO origenesDAO = DAOFactory.getDAOFactory( DAOFactory.ORACLE ).getOrigenesDAO();
        TipoNominaBO tipoNomina = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select tn.DESCRIPCION, tn.ID, tn.NOMBRE, tn.REFERENCIA_BANCO, tn.REFERENCIA_ORIGENES from TIPOS_NOMINA tn where tn.ID = " + id;
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                tipoNomina = new TipoNominaBO();
                tipoNomina.setId( rs.getLong( "ID" ) );
                tipoNomina.setNombre( rs.getString( "NOMBRE" ) );
                tipoNomina.setDescripcion( rs.getString( "DESCRIPCION" ) );
                tipoNomina.setOrigen( OrigenBO.crear( rs.getString( "REFERENCIA_ORIGENES" ) ) );
                tipoNomina.setBanco( BancoBO.crear( rs.getLong( "REFERENCIA_BANCO" ) ) );
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
        if ( tipoNomina != null ) {
            tipoNomina.setBanco( bancoDAO.queryBancoById( tipoNomina.getBanco().getId() ) );
            tipoNomina.setOrigen( origenesDAO.queryOrigenById( tipoNomina.getOrigen().getCodigo() ) );
        }
        return tipoNomina;
    }
}
