package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces;

import cl.bicevida.envionominas.model.bo.RegistroNominaBO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;

import java.util.List;
import java.util.Map;

public interface RegistroNominaDAO {

    public Map<Long, String> queryBancosPagoNomina( Long _idNomina ) throws DAOException;

    public Long queryTotalRegistrosNomina( Long _idNomina ) throws DAOException;

    public List<RegistroNominaBO> queryRegistrosByNomina( Long _idNomina ) throws DAOException;

    public List<RegistroNominaBO> queryRegistrosByProcesoId( Long _idProceso ) throws DAOException;

    public void queryAsociaRegistroProceso( RegistroNominaBO _registro ) throws DAOException;

    public void queryActualizarEstadoRegistrosByNomina( Long _idNomina, Integer _idEstado ) throws DAOException;

    public void queryActualizarEstadoRegistro( Long _idRegistro, Long _idEstado, String _observacion ) throws DAOException;
    
    public void queryActualizarEstadoRegistro( Long _idNomina, Integer _idEstado, String _rutTitular, String _nombreTitular ) throws DAOException;

    public Long queryRegistrosPendientesRendicionByNomina( Long _idNomina ) throws DAOException;
}
