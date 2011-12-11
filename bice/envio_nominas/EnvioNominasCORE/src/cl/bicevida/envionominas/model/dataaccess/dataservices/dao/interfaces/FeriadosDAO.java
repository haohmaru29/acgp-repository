package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces;

import cl.bicevida.envionominas.model.bo.FeriadoBO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;

import java.util.Date;
import java.util.List;

public interface FeriadosDAO {

    public List<FeriadoBO> queryFeriado() throws DAOException;

    public void agregarFeriado( String _tipoFeriado, String _descripcionFeriado, Date _fechaFeriado ) throws DAOException;

    public void eliminarFeriado( Date _fechaFeriado ) throws DAOException;

    public void modificarFeriado( Long idFeriado, String tipoFeriado, String descripcionFeriado, Date fechaFeriado ) throws DAOException;

    public Long buscarFeriado( Date _fechaFeriado ) throws DAOException;
}
