package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces;

import cl.bicevida.envionominas.model.bo.NominaBO;

import cl.bicevida.envionominas.model.bo.RegistroNominaBO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;

import java.util.Date;
import java.util.List;

public interface NominaDAO {

    public List<RegistroNominaBO> consultaEstadoTransaccionesNomina( Long _bancoPago, Long _estadoNomina, Long _estadoTransaccion, Date _fechaDesde, Date _fechaHasta, String _loteNomina, String _rutTitular, Long _bancoProceso, Long _tipoNomina ) throws DAOException;

    public List<NominaBO> queryNuevasNominas() throws DAOException;

    public List<NominaBO> queryNominasCancelables( Long _bancoPago, Date _fechaDesde, Date _fechaHasta, Long _numeroNomina, Long _tipoNomina, Long _tipoCuenta ) throws DAOException;

    public List<NominaBO> queryNominasExtraibles( Date _fechaDesde, Date _fechaHasta, String _loteNomina, Long _tipoNomina, Long _bancoProceso ) throws DAOException;

    public List<NominaBO> queryNominasAutorizables( Long _bancoPago, Date _fechaDesde, Date _fechaHasta, Long _numeroNomina, Long _tipoNomina, Long _tipoCuenta ) throws DAOException;

    public NominaBO queryNominaById( Long _idNomina ) throws DAOException;

    public void queryActualizaEstadoNomina( Long _idNomina, Long _idEstado ) throws DAOException;

    public List<NominaBO> queryNominasSinFolio( Date _fechaDesde, Date _fechaHasta, String _loteNomina, Long _tipoNomina, Long _bancoProceso ) throws DAOException;

    public int queryValidaBancoFolio( String _bancoPago, Long _numeroNomina ) throws DAOException;
}
