package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.oracle;

import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.DAOFactory;

import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.ArchivosNominasDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.BancosDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.BancosProcesoDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.ComisionesDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.CuentasDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.EmpresasDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.EstadosNominaDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.EstadosProcesoDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.EstadosTransaccionDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.GastosNominaDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.ListasDistribucionDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.NominaDAO;

import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.OrigenesDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.ParametrosDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.ProcesosEnvioDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.RegistroNominaDAO;

import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.TiposCuentaDAO;

import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.TiposNominaDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.TiposFeriadoDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.FeriadosDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.CorreosDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * Implementacion de un FactoryPattern para el acceso a datos de recursos ORACLE.
 * 
 * Registro de versiones:
 *      - 1.0 17/11/2010 Giorgio Gortaire (ACGP) : Version inicial. 
 */
public class OracleDAOFactory extends DAOFactory {
    private static final Logger log = Logger.getLogger( OracleEmpresasDAO.class );

    /**
     * Crea una conexión a una base de datos Oracle.
     *
     * @param jndi : ruta JNDI del DataSource para conecxión a la base de datos
     * Oracle.
     */
    public static Connection createConnection( String jndi ) throws DAOException {
        Connection myConn = null;
        try {
            Context ic = new InitialContext();
            DataSource ds = ( DataSource ) ic.lookup( jndi );
            myConn = ds.getConnection();
        } catch ( SQLException e ) {
            new DAOException( "Imposible obtener la conexión a la fuente de datos.", e );
        } catch ( NamingException e ) {
            new DAOException( "No se encontró el DataSource en la ruta especificada.", e );
        }
        return myConn;
    }

    /**
     * Cierra los recursos asociados a una conexion de base de datos Oracle.
     *
     * @param conn : conexion a la base de datos oracle
     * @param stmt : statement usado
     * @param rs   : result set obtenido
     */
    public static void closeAll( Connection conn, Statement stmt, ResultSet rs ) {
        // Cierra el ResultSet
        try {
            if ( rs != null )
                rs.close();
        } catch ( SQLException e ) {
            log.error( e.getMessage() );
        }
        // Cierra el Statement
        try {
            if ( stmt != null )
                stmt.close();
        } catch ( SQLException e ) {
            log.error( e.getMessage() );
        }
        // Cierra la Connection
        try {
            if ( conn != null )
                conn.close();
        } catch ( SQLException e ) {
            log.error( e.getMessage() );
        }
    }
    //*** INICIO: Getters para objetos DAO ***

    public NominaDAO getNominaDAO() {
        return new OracleNominaDAO();
    }

    public RegistroNominaDAO getRegistroNominaDAO() {
        return new OracleRegistroNominaDAO();
    }

    public BancosDAO getBancosDAO() {
        return new OracleBancosDAO();
    }

    public EmpresasDAO getEmpresasDAO() {
        return new OracleEmpresasDAO();
    }

    public TiposCuentaDAO getTiposCuentaDAO() {
        return new OracleTiposCuentaDAO();
    }

    public TiposNominaDAO getTiposNominaDAO() {
        return new OracleTiposNominaDAO();
    }

    public TiposFeriadoDAO getTiposFeriadoDAO() {
        return new OracleTiposFeriadoDAO();
    }

    public FeriadosDAO getFeriadoDAO() {
        return new OracleFeriadosDAO();
    }

    public CorreosDAO getCorreosDAO() {
        return new OracleCorreosDAO();
    }

    public OrigenesDAO getOrigenesDAO() {
        return new OracleOrigenesDAO();
    }

    public EstadosTransaccionDAO getEstadosTransaccionDAO() {
        return new OracleEstadosTransaccionDAO();
    }

    public EstadosNominaDAO getEstadosNominaDAO() {
        return new OracleEstadosNominaDAO();
    }

    public BancosProcesoDAO getBancosProcesoDAO() {
        return new OracleBancosProcesoDAO();
    }

    public CuentasDAO getCuentasDAO() {
        return new OracleCuentasDAO();
    }

    public ProcesosEnvioDAO getProcesosEnvioDAO() {
        return new OracleProcesosEnvioDAO();
    }

    public EstadosProcesoDAO getEstadosProcesoDAO() {
        return new OracleEstadosProcesoDAO();
    }

    public ParametrosDAO getParametrosDAO() {
        return new OracleParametrosDAO();
    }

    public GastosNominaDAO getGastosNominaDAO() {
        return new OracleGastosNominaDAO();
    }

    public ListasDistribucionDAO getListasDistribucionDAO() {
        return new OracleListasDistribucionDAO();
    }

    public ComisionesDAO getComisionesDAO() {
        return new OracleComisionesDAO();
    }

    public ArchivosNominasDAO getArchivosNominasDAO() {
        return new OracleArchivosNominasDAO();
    }
    //*** FIN: Getters para objetos DAO ***
}
