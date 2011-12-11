package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces;

import cl.bicevida.envionominas.model.bo.BancoBO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;

import java.util.List;

public interface BancosDAO {

    public List<BancoBO> queryBancos() throws DAOException;

    public BancoBO queryBancoById( Long id ) throws DAOException;
}
