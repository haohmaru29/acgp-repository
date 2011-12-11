package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces;

import cl.bicevida.envionominas.model.bo.BancoProcesoBO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;

import java.util.List;

public interface BancosProcesoDAO {

    public BancoProcesoBO queryBancoProcesoById( Long id ) throws DAOException;

    public List<BancoProcesoBO> queryBancosProceso() throws DAOException;

    public void queryCrearBancoProceso( String _nombre, Long _idBanco ) throws DAOException;

    public void queryEliminarBancoProceso( Long _idBanco ) throws DAOException;

    public Boolean queryIsBancoProcesoEnUso( Long _idBancoProceso ) throws DAOException;
}
