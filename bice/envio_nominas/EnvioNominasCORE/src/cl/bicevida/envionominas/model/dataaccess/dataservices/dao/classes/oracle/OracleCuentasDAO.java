package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.oracle;

import cl.bicevida.envionominas.model.bo.BancoBO;
import cl.bicevida.envionominas.model.bo.CuentaBO;
import cl.bicevida.envionominas.model.bo.EmpresaBO;
import cl.bicevida.envionominas.model.bo.MonedaBO;
import cl.bicevida.envionominas.model.bo.TipoCuentaBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.DAOFactory;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.BancosDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.CuentasDAO;

import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.EmpresasDAO;

import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.TiposCuentaDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementa el acceso a datos a la tabla "CUENTAS".
 * 
 * Registro de versiones:
 *      - 1.0 17/11/2010 Giorgio Gortaire (ACGP) : Version inicial. 
 */
public class OracleCuentasDAO implements CuentasDAO {
    private EnvioNominasConfig config;

    public OracleCuentasDAO() {
        config = new EnvioNominasConfig();
    }

    public List<CuentaBO> queryCuentas() throws DAOException {
        BancosDAO bancosDAO = DAOFactory.getDAOFactory( DAOFactory.ORACLE ).getBancosDAO();
        EmpresasDAO empresasDAO = DAOFactory.getDAOFactory( DAOFactory.ORACLE ).getEmpresasDAO();
        TiposCuentaDAO tiposCuentaDAO = DAOFactory.getDAOFactory( 1 ).getTiposCuentaDAO();
        List<CuentaBO> cuentas = new ArrayList<CuentaBO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select c.ID, c.NUMEROCUENTA, c.REFERENCIA_BANCO, c.REFERENCIA_EMPRESA, c.REFERENCIA_MONEDA, c.REFERENCIA_TIPO_CUENTA from CUENTAS c";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                CuentaBO cuenta = new CuentaBO();
                cuenta.setBanco( BancoBO.crear( rs.getLong( "REFERENCIA_BANCO" ) ) );
                cuenta.setEmpresa( EmpresaBO.crear( rs.getLong( "REFERENCIA_EMPRESA" ) ) );
                cuenta.setId( rs.getLong( "ID" ) );
                cuenta.setMoneda( MonedaBO.crear( rs.getLong( "REFERENCIA_MONEDA" ) ) );
                cuenta.setNumeroCuenta( rs.getString( "NUMEROCUENTA" ) );
                cuenta.setTipoCuenta( TipoCuentaBO.crear( rs.getLong( "REFERENCIA_TIPO_CUENTA" ) ) );
                cuentas.add( cuenta );
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
        for ( CuentaBO cuenta: cuentas ) {
            cuenta.setBanco( bancosDAO.queryBancoById( cuenta.getBanco().getId() ) );
            cuenta.setEmpresa( empresasDAO.queryEmpresaById( cuenta.getEmpresa().getId() ) );
            cuenta.setTipoCuenta( tiposCuentaDAO.queryTipoCuentaById( cuenta.getTipoCuenta().getId() ) );
        }
        return cuentas;
    }

    public CuentaBO queryCuentaById( Long id ) throws DAOException {
        BancosDAO bancosDAO = DAOFactory.getDAOFactory( 1 ).getBancosDAO();
        EmpresasDAO empresasDAO = DAOFactory.getDAOFactory( 1 ).getEmpresasDAO();
        TiposCuentaDAO tiposCuentaDAO = DAOFactory.getDAOFactory( 1 ).getTiposCuentaDAO();
        CuentaBO cuenta = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select c.ID, c.NUMEROCUENTA, c.REFERENCIA_BANCO, c.REFERENCIA_EMPRESA, c.REFERENCIA_MONEDA, c.REFERENCIA_TIPO_CUENTA from CUENTAS c where c.ID = " + id;
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            if ( rs.next() ) {
                cuenta = new CuentaBO();
                cuenta.setBanco( BancoBO.crear( rs.getLong( "REFERENCIA_BANCO" ) ) );
                cuenta.setEmpresa( EmpresaBO.crear( rs.getLong( "REFERENCIA_EMPRESA" ) ) );
                cuenta.setId( rs.getLong( "ID" ) );
                cuenta.setMoneda( MonedaBO.crear( rs.getLong( "REFERENCIA_MONEDA" ) ) );
                cuenta.setNumeroCuenta( rs.getString( "NUMEROCUENTA" ) );
                cuenta.setTipoCuenta( TipoCuentaBO.crear( rs.getLong( "REFERENCIA_TIPO_CUENTA" ) ) );
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
        if ( cuenta != null ) {
            cuenta.setBanco( bancosDAO.queryBancoById( cuenta.getBanco().getId() ) );
            cuenta.setEmpresa( empresasDAO.queryEmpresaById( cuenta.getEmpresa().getId() ) );
            cuenta.setTipoCuenta( tiposCuentaDAO.queryTipoCuentaById( cuenta.getTipoCuenta().getId() ) );
        }
        return cuenta;
    }
}
