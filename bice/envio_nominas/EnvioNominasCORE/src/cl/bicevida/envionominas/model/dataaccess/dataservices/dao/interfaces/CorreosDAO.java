package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces;

import cl.bicevida.envionominas.model.bo.CorreoBO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;

import java.util.List;

public interface CorreosDAO {

    public List<CorreoBO> queryCorreos() throws DAOException;

    public void queryCrearNuevoCorreo( String _nombre, String _subject, String _mensaje, String _tipo, Long _idEstado ) throws DAOException;

    public void queryEliminarCorreo( Long _idCorreo ) throws DAOException;

    public void queryModificarCorreo( Long _idCorreo, String _nombre, String _subject, String _mensaje, String _tipo, Long _idEstado ) throws DAOException;

    public Long queryBuscarCorreoByNombre( String _nombre ) throws DAOException;

    public CorreoBO queryCorreoByEstadoNomina( Long _idEstado ) throws DAOException;
}
