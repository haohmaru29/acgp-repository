package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces;

import cl.bicevida.envionominas.model.bo.DetalleGastoBO;
import cl.bicevida.envionominas.model.bo.GastoNominaBO;

import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;

import java.util.Date;
import java.util.List;

public interface GastosNominaDAO {

    public List<GastoNominaBO> queryGastosNominas( Long _bancoPago, Date _fechaDesde, Date _fechaHasta, String _loteNomina, Long _bancoProceso, Long _tipoNomina ) throws DAOException;

    public List<DetalleGastoBO> queryDetalleGastosbyNomina( Long _idNomina ) throws DAOException;
}
