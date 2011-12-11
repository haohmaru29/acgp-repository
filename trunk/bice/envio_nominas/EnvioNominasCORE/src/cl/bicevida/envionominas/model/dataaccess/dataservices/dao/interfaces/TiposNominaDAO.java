package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces;

import cl.bicevida.envionominas.model.bo.TipoNominaBO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;

import java.util.List;

public interface TiposNominaDAO {

    public List<TipoNominaBO> queryTiposNomina() throws DAOException;

    public TipoNominaBO queryTipoNominaById( Long id ) throws DAOException;
}
