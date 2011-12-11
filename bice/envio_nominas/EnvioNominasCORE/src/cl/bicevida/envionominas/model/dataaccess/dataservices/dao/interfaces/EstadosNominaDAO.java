package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces;

import cl.bicevida.envionominas.model.bo.EstadoNominaBO;

import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;

import java.util.List;

public interface EstadosNominaDAO {

    public List<EstadoNominaBO> queryEstadosNomina() throws DAOException;

    public EstadoNominaBO queryEstadoNominaById( Long idEstadoNomina ) throws DAOException;
}
