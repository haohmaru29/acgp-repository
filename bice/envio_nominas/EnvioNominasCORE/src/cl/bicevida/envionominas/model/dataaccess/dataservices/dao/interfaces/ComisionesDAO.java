package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces;

import cl.bicevida.envionominas.model.bo.ComisionBO;

import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

public interface ComisionesDAO {

    public void agregarComision( Long _idBancoProceso, BigDecimal _montoMismoBanco, BigDecimal _montoOtrosBancos, Date _fechaInicioVigencia, Date _fechaFinVigencia ) throws DAOException;

    public List<ComisionBO> queryComisionesByBancoProceso( Long _idBancoProceso ) throws DAOException;

    public ComisionBO queryComisionById( Long _idComision ) throws DAOException;

    public void updateComision( Long _idComision, BigDecimal _montoMismoBanco, BigDecimal _montoOtrosBancos, Date _fechaInicioVigencia, Date _fechaFinVigencia ) throws DAOException;

    public void eliminarComision( Long _idComision ) throws DAOException;
}
