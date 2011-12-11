package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces;

import cl.bicevida.envionominas.model.bo.TipoFeriadoBO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;

import java.util.List;

public interface TiposFeriadoDAO {

    public List<TipoFeriadoBO> queryTiposFeriado() throws DAOException;
}
