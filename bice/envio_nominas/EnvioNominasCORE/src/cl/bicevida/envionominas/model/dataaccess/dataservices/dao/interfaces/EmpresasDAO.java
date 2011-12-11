package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces;

import cl.bicevida.envionominas.model.bo.EmpresaBO;

import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;

import java.util.List;

public interface EmpresasDAO {

    public List<EmpresaBO> queryEmpresas() throws DAOException;

    public EmpresaBO queryEmpresaById( Long id ) throws DAOException;
}
