package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces;

import cl.bicevida.envionominas.model.bo.EstadoProcesoBO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;

public interface EstadosProcesoDAO {

    public EstadoProcesoBO queryEstadoById( Long _idEstado ) throws DAOException;
}
