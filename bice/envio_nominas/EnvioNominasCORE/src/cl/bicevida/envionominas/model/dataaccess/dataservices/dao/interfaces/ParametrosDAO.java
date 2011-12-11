package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces;

import cl.bicevida.envionominas.model.bo.ParametroBO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;

import java.util.List;

public interface ParametrosDAO {

    public List<ParametroBO> queryParametro() throws DAOException;

    public void agregarParametro( String _claveParametro, String _valorParametro, String _descripcionParametro, String _tipoParametro ) throws DAOException;

    public void eliminarParametro( Long _idParametro ) throws DAOException;

    public void modificarParametro( Long idParametro, String _claveParametro, String _valorParametro, String _descripcionParametro, String _tipoParametro ) throws DAOException;

    public Long buscarParametro( String _claveParametro, Long _idBancoProceso ) throws DAOException;

    public void queryCrearParametroBancoProceso( Long _idBancoProceso, String _claveParametro, String _valorParametro, String _descripcionParametro, String _tipoParametro ) throws DAOException;

    public List<ParametroBO> queryParametrosByBancoProceso( Long _idBancoProceso ) throws DAOException;
}
