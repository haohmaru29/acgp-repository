package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes;

import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.oracle.OracleDAOFactory;
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
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.NominaDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.OrigenesDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.ProcesosEnvioDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.RegistroNominaDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.TiposCuentaDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.TiposFeriadoDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.FeriadosDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.CorreosDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.ParametrosDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.ListasDistribucionDAO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.TiposNominaDAO;

public abstract class DAOFactory {
    public static final int ORACLE = 1;

    public abstract NominaDAO getNominaDAO();

    public abstract RegistroNominaDAO getRegistroNominaDAO();

    public abstract BancosDAO getBancosDAO();

    public abstract EmpresasDAO getEmpresasDAO();

    public abstract TiposCuentaDAO getTiposCuentaDAO();

    public abstract TiposNominaDAO getTiposNominaDAO();

    public abstract TiposFeriadoDAO getTiposFeriadoDAO();

    public abstract FeriadosDAO getFeriadoDAO();

    public abstract CorreosDAO getCorreosDAO();

    public abstract ParametrosDAO getParametrosDAO();

    public abstract ListasDistribucionDAO getListasDistribucionDAO();

    public abstract OrigenesDAO getOrigenesDAO();

    public abstract EstadosTransaccionDAO getEstadosTransaccionDAO();

    public abstract EstadosNominaDAO getEstadosNominaDAO();

    public abstract BancosProcesoDAO getBancosProcesoDAO();

    public abstract CuentasDAO getCuentasDAO();

    public abstract ProcesosEnvioDAO getProcesosEnvioDAO();

    public abstract EstadosProcesoDAO getEstadosProcesoDAO();

    public abstract GastosNominaDAO getGastosNominaDAO();

    public abstract ComisionesDAO getComisionesDAO();

    public abstract ArchivosNominasDAO getArchivosNominasDAO();

    public static DAOFactory getDAOFactory( int whichFactory ) {
        switch ( whichFactory ) {
        case ORACLE:
            return new OracleDAOFactory();
        default:
            return null;
        }
    }
}
