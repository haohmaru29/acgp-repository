package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces;

import cl.bicevida.envionominas.model.bo.EstadoTransaccionBO;

import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;

import java.util.List;

public interface EstadosTransaccionDAO {

    public List<EstadoTransaccionBO> queryEstadosTransaccion() throws DAOException;

    public EstadoTransaccionBO queryEstadosTransaccionById( Long idEstadoTransaccion ) throws DAOException;
}
