package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces;

import cl.bicevida.envionominas.model.bo.NominaBO;
import cl.bicevida.envionominas.model.bo.ProcesoEnvioBO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;

import java.util.List;

public interface ProcesosEnvioDAO {

    public List<ProcesoEnvioBO> queryProcesosByNomina( Long _idNomina ) throws DAOException;

    public void queryCrearProcesosEnvio( NominaBO _nomina ) throws DAOException;

    public void updateProceso( ProcesoEnvioBO proceso ) throws DAOException;

    public ProcesoEnvioBO queryProcesoById( Long _idProceso ) throws DAOException;

    public void queryActualizaEstadoProceso( Long _idProceso, Long _idEstado, String _observacion ) throws DAOException;

    public void queryEliminaProcesosByNomina( Long _idNomina ) throws DAOException;

    public void queryActualizarEstadoProcesosByNomina( Long _idNomina, Long _idEstadoProceso, String _observacion ) throws DAOException;

    public ProcesoEnvioBO queryProcesoByIdRegistro( Long _idRegistro ) throws DAOException;

    public void queryRegistrarFolioProcesoExterno( Long _idProceso, String _folioExterno ) throws DAOException;

    public Long queryProcesosSinFolioByNomina( Long _idNomina ) throws DAOException;

    public ProcesoEnvioBO queryProcesoByFolioExterno( String _folioExterno ) throws DAOException;
}
