package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces;

import cl.bicevida.envionominas.model.bo.ListaDistribucionBO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;

import java.util.List;

public interface ListasDistribucionDAO {

    public List<ListaDistribucionBO> queryListaDistribucion() throws DAOException;

    public void agregarListaDistribucion( String _origenLista, String _nombreLista ) throws DAOException;

    public void eliminarListaDistribucion( Long _idLista ) throws DAOException;

    public void modificarListaDistribucion( Long idLista, String _origenLista, String _nombreLista ) throws DAOException;

    public Long buscarListaDistribucion( String _origenLista ) throws DAOException;

    public List<ListaDistribucionBO> queryListasByOrigenNomina( String origenLista ) throws DAOException;
}
