package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces;

import cl.bicevida.envionominas.model.bo.ArchivoBO;

import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;

import java.io.InputStream;

import java.sql.Date;

import java.util.List;

public interface ArchivosNominasDAO {

    public InputStream queryArchivoByNombre( String _nombreArchivo ) throws DAOException;

    public List<ArchivoBO> queryArchivosNominas() throws DAOException;

    public List<ArchivoBO> queryArchivosNominasNotChecked()  throws DAOException;
    
    public List<ArchivoBO> queryArchivosByTipo(String idTipo) throws DAOException;
    
    public void queryActualizaEvmnArchivosNominas(Date fechaCreacion, String  nombreArchivo) throws DAOException;
    
    public int queryActualizarEstadoRegistroError( Long _idRegistro, Long _idEstado, String _observacion, String _cuentaTitular, Long rutTitular, Long monto, Long idNomina ) throws DAOException;
}
