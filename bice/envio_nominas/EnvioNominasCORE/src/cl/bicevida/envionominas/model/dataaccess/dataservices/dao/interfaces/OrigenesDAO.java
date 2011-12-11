package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces;

import cl.bicevida.envionominas.model.bo.OrigenBO;

import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;

import java.util.List;

public interface OrigenesDAO {

    public List<OrigenBO> queryOrigenes() throws DAOException;

    public OrigenBO queryOrigenById( String string ) throws DAOException;
}
