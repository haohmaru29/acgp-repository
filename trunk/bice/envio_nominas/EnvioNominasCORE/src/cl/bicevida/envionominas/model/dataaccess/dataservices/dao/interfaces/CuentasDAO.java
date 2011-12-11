package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces;

import cl.bicevida.envionominas.model.bo.CuentaBO;

import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;

import java.util.List;

public interface CuentasDAO {

    public List<CuentaBO> queryCuentas() throws DAOException;

    public CuentaBO queryCuentaById( Long id ) throws DAOException;
}
