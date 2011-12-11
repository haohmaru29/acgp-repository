package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces;

import cl.bicevida.envionominas.model.bo.TipoCuentaBO;

import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;

import java.util.List;

public interface TiposCuentaDAO {

    public List<TipoCuentaBO> queryTiposCuenta() throws DAOException;

    public TipoCuentaBO queryTipoCuentaById( Long id ) throws DAOException;
}
