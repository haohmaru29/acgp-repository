package cl.bicevida.envionominas.model.services.ejb;

import cl.bicevida.envionominas.model.bo.ArchivoBO;
import cl.bicevida.envionominas.model.bo.BancoBO;
import cl.bicevida.envionominas.model.bo.BancoProcesoBO;
import cl.bicevida.envionominas.model.bo.EstadoNominaBO;
import cl.bicevida.envionominas.model.bo.EstadoTransaccionBO;

import cl.bicevida.envionominas.model.bo.GastoNominaBO;

import cl.bicevida.envionominas.model.bo.NominaBO;
import cl.bicevida.envionominas.model.bo.OrigenBO;
import cl.bicevida.envionominas.model.bo.RegistroNominaBO;
import cl.bicevida.envionominas.model.bo.TipoCuentaBO;
import cl.bicevida.envionominas.model.bo.TipoNominaBO;
import cl.bicevida.envionominas.model.bo.TipoFeriadoBO;
import cl.bicevida.envionominas.model.bo.FeriadoBO;
import cl.bicevida.envionominas.model.bo.CorreoBO;
import cl.bicevida.envionominas.model.bo.DetalleGastoBO;
import cl.bicevida.envionominas.model.bo.ParametroBO;
import cl.bicevida.envionominas.model.bo.ListaDistribucionBO;

import cl.bicevida.envionominas.model.bo.ComisionBO;

import cl.bicevida.envionominas.model.exceptions.EnvioNominasException;
import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.CargaNominaInputType;

import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.RindeNominaInputType;

import java.io.InputStream;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.SessionContext;

@Local
public interface EnvioNominasEJBLocal {

    public List<TipoNominaBO> obtenerTiposNomina();

    public List<BancoBO> obtenerBancosPago();

    public List<EstadoTransaccionBO> obtenerEstadosTransaccion();

    public List<EstadoNominaBO> obtenerEstadosNomina();

    public List<TipoCuentaBO> obtenerTiposCuenta();

    public List<NominaBO> obtenerNominasCancelables( Long _bancoPago, Date _fechaDesde, Date _fechaHasta, Long _numeroNomina, Long _tipoNomina, Long _tipoCuenta );

    public List<RegistroNominaBO> consultaEstadoTransaccionesNomina( Long _bancoPago, Long _estadoNomina, Long _estadoTransaccion, Date _fechaDesde, Date _fechaHasta, String _loteNomina, String _rutTitular, Long _bancoProceso, Long _tipoNomina );

    public List<NominaBO> obtenerNominasExtraibles( Date _fechaDesde, Date _fechaHasta, String _loteNomina, Long _tipoNomina, Long _bancoProceso );

    public List<NominaBO> obtenerNominasAutorizables( Long _bancoPago, Date _fechaDesde, Date _fechaHasta, Long _numeroNomina, Long _tipoNomina, Long _tipoCuenta );

    public void inicializarScheduler();

    public List<TipoFeriadoBO> obtenerTiposFeriado();

    public List<FeriadoBO> obtenerFeriado();

    public void agregarFeriado( String _tipoFeriado, String _descripcionFeriado, Date _fechaFeriado );

    public List<RegistroNominaBO> obtenerRegistrosPorNomina( Long _idNomina );

    public List<GastoNominaBO> obtenerGastosNominas( Long _bancoPago, Date _fechaDesde, Date _fechaHasta, String _loteNomina, Long _bancoProceso, Long _tipoNomina );

    public void eliminarFeriado( Date _fechaFeriado );

    public void modificarFeriado( Long _idFeriado, String _tipoFeriado, String _descripcionFeriado, Date _fechaFeriado );

    public Long buscarFeriado( Date _fechaFeriado );

    public List<CorreoBO> obtenerCorreos();

    public void eliminarCorreo( Long _idCorreo );

    public Long buscarCorreo( String _nombre );

    public List<ParametroBO> obtenerParametros();

    public void agregarParametro( String _claveParametro, String _valorParametro, String _descripcionParametro, String _tipoParametro );

    public void eliminarParametro( Long _idParametro );

    public void modificarParametro( Long idParametro, String _claveParametro, String _valorParametro, String _descripcionParametro, String _tipoParametro );

    public Long buscarParametro( String _claveParametro, Long _idBancoProceso );

    public List<ListaDistribucionBO> obtenerListasDistribucion();

    public void agregarListaDistribucion( String _origenLista, String _nombreLista );

    public void eliminarListaDistribucion( Long _idLista );

    public void modificarListaDistribucion( Long idLista, String _origenLista, String _nombreLista );

    public Long buscarListaDistribucion( String _origenLista );

    public List<OrigenBO> obtenerOrigen();

    public List<DetalleGastoBO> obtenerDetalleGastoNomina( Long _idNomina );

    public List<ComisionBO> obtenerComisionesByBancoProceso( Long _idBancoProceso );

    public ComisionBO obtenerComisionById( Long _idComision );

    public void modificarComision( Long _idComision, BigDecimal montoMismoBanco, BigDecimal montoOtrosBancos, Date fechaInicioVigencia, Date fechaFinVigencia );

    public void eliminarComisionById( Long _idComision );

    public void agregarComision( Long _idBancoProceso, BigDecimal _montoMismoBanco, BigDecimal _montoOtrosBancos, Date _fechaInicioVigencia, Date _fechaFinVigencia );

    public List<BancoProcesoBO> obtenerBancosProceso();

    public void agregarParametroBancoProceso( Long _idBancoProceso, String clave, String valor, String descripcion, String _tipoParametro );

    public List<ParametroBO> obtenerParametrosByBancoProceso( Long _idBancoProceso );

    public void crearBancoProceso( String _nombre, Long _idBanco );

    public void eliminarBancoProceso( Long _idBanco );

    public void agregarCorreo( String _nombre, String _subject, String _mensaje, String _tipo, Long _idEstado );

    public void modificarCorreo( Long _idCorreo, String _nombre, String _subject, String _mensaje, String _tipo, Long _idEstado );

    public Boolean isBancoProcesoEnUso( Long _idBancoProceso );

    public void cancelarNomina( Long _idNomina, String motivoCancelacion );

    public List<NominaBO> obtenerNominasSinFolio( Date _fechaDesde, Date _fechaHasta, String _loteNomina, Long _tipoNomina, Long _bancoProceso );

    public void registrarFolio( Long _idNomina, String _folioExterno );

    public int validaBancoFolio( String banco, Long numeroFolioIngresado );

    public void extraerNomina( Long _idNomina );

    public List<ArchivoBO> obtenerArchivosNominas();

    public InputStream obtenerArchivoNomina( String _nombreArchivo );

    public void ejecutarCargaProcesoEnvioNomina( CargaNominaInputType _type ) throws EnvioNominasException;

    public void ejecutarRendicionCargaProcesoEnvioNomina( RindeNominaInputType _type ) throws EnvioNominasException;

    public void ejecutarRendicionPagoProcesoEnvioNomina( RindeNominaInputType _type ) throws EnvioNominasException;

    public SessionContext getContext();
}
