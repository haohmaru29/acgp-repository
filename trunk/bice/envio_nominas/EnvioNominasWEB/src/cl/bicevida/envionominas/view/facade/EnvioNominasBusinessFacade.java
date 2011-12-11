package cl.bicevida.envionominas.view.facade;

import cl.bicevida.envionominas.model.bo.ArchivoBO;
import cl.bicevida.envionominas.model.bo.BancoBO;
import cl.bicevida.envionominas.model.bo.BancoProcesoBO;
import cl.bicevida.envionominas.model.bo.DetalleGastoBO;
import cl.bicevida.envionominas.model.bo.EstadoNominaBO;
import cl.bicevida.envionominas.model.bo.EstadoTransaccionBO;
import cl.bicevida.envionominas.model.bo.GastoNominaBO;
import cl.bicevida.envionominas.model.bo.NominaBO;
import cl.bicevida.envionominas.model.bo.RegistroNominaBO;
import cl.bicevida.envionominas.model.bo.TipoCuentaBO;
import cl.bicevida.envionominas.model.bo.TipoNominaBO;
import cl.bicevida.envionominas.model.bo.TipoFeriadoBO;
import cl.bicevida.envionominas.model.bo.FeriadoBO;
import cl.bicevida.envionominas.model.bo.ParametroBO;
import cl.bicevida.envionominas.model.bo.OrigenBO;
import cl.bicevida.envionominas.model.bo.ListaDistribucionBO;

import cl.bicevida.envionominas.model.bo.ComisionBO;

import cl.bicevida.envionominas.model.bo.CorreoBO;

import java.io.InputStream;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

public interface EnvioNominasBusinessFacade {

    public List<TipoNominaBO> obtenerTiposNomina() throws Exception;

    public List<BancoBO> obtenerBancosPago() throws Exception;

    public List<EstadoTransaccionBO> obtenerEstadosTransaccion() throws Exception;

    public List<EstadoNominaBO> obtenerEstadosNomina() throws Exception;

    public List<TipoCuentaBO> obtenerTiposCuenta() throws Exception;

    public List<NominaBO> obtenerNominasCancelables( Long _bancoPago, Date _fechaDesde, Date _fechaHasta, Long _numeroNomina, Long _tipoNomina, Long _tipoCuenta ) throws Exception;

    public List<NominaBO> obtenerNominasExtraibles( Date _fechaDesde, Date _fechaHasta, String _loteNomina, Long _tipoNomina, Long _bancoProceso ) throws Exception;

    public List<NominaBO> obtenerNominasAutorizables( Long _bancoPago, Date _fechaDesde, Date _fechaHasta, Long _numeroNomina, Long _tipoNomina, Long _tipoCuenta ) throws Exception;

    public List<RegistroNominaBO> consultaEstadoTransaccionesNomina( Long _bancoPago, Long _estadoNomina, Long _estadoTransaccion, Date _fechaDesde, Date _fechaHasta, String _loteNomina, String _rutTitular, Long _bancoProceso, Long _tipoNomina ) throws Exception;

    public List<TipoFeriadoBO> obtenerTiposFeriado() throws Exception;

    public List<FeriadoBO> obtenerFeriado() throws Exception;

    public Long buscarFeriado( Date _fechaFeriado ) throws Exception;

    public void agregarFeriado( String _tipoFeriado, String _descripcionFeriado, Date _fechaFeriado ) throws Exception;

    public List<RegistroNominaBO> obtenerRegistrosPorNomina( Long _idNomina ) throws Exception;

    public List<GastoNominaBO> obtenerGastosNominas( Long _bancoPago, Date _fechaDesde, Date _fechaHasta, String _loteNomina, Long _bancoProceso, Long _tipoNomina ) throws Exception;

    public void eliminarFeriado( Date _fechaFeriado ) throws Exception;

    public void modificarFeriado( Long idFeriado, String tipoFeriado, String descripcionFeriado, Date fechaFeriado ) throws Exception;

    public Long buscarCorreo( String _nombre ) throws Exception;

    public List<CorreoBO> obtenerCorreos() throws Exception;

    public void eliminarCorreo( Long _idCorreo ) throws Exception;

    public Long buscarParametro( String _claveParametro, Long _idBancoProceso ) throws Exception;

    public void agregarParametro( String _claveParametro, String _valorParametro, String _descripcionParametro, String _tipoParametro ) throws Exception;

    public List<ParametroBO> obtenerParametros() throws Exception;

    public void eliminarParametro( Long _idParametro ) throws Exception;

    public void modificarParametro( Long idParametro, String claveParametro, String valorParametro, String descripcionParametro, String _tipoParametro ) throws Exception;

    public Long buscarListaDistribucion( String _origenLista ) throws Exception;

    public void agregarListaDistribucion( String origenLista, String nombreLista ) throws Exception;

    public List<ListaDistribucionBO> obtenerListasDistribucion() throws Exception;

    public void eliminarListaDistribucion( Long _idLista ) throws Exception;

    public void modificarListaDistribucion( Long idLista, String origenLista, String nombreLista ) throws Exception;

    public List<OrigenBO> obtenerOrigen() throws Exception;

    public List<DetalleGastoBO> obtenerDetalleGastoNomina( Long _idNomina ) throws Exception;

    public List<ComisionBO> obtenerComisionesByBancoProceso( Long _idBancoProceso ) throws Exception;

    public void agregarComision( Long _idBancoProceso, Date _fechaInicioVigencia, Date _fechaTerminoVigencia, BigDecimal _montoMismoBanco, BigDecimal _montoOtrosBancos ) throws Exception;

    public List<BancoProcesoBO> obtenerBancosProceso() throws Exception;

    public void eliminarComisionById( Long _idComision ) throws Exception;

    public void guardarCambiosComision( Long _idComision, Date _fechaInicioVigencia, Date _fechaTerminoVigencia, BigDecimal _montoMismoBanco, BigDecimal _montoOtrosBancos ) throws Exception;

    public void agregarParametroBancoProceso( Long _idBancoProceso, String _claveParametro, String _valorParametro, String _descripcionParametro, String _tipoParametro ) throws Exception;

    public List<ParametroBO> obtenerParametrosByBancoProceso( Long _idBancoProceso ) throws Exception;

    public void guardarCambiosParametro( Long _idParametro, String _claveParametro, String _valorParametro, String _descripcionParametro, String _tipoParametro ) throws Exception;

    public void eliminarBancoProceso( Long _idBancoProceso ) throws Exception;

    public void crearBancoProceso( String _nombre, Long _idBanco ) throws Exception;

    public Boolean isBancoProcesoEnUso( Long _idBancoProceso ) throws Exception;

    public void agregarCorreo( String _nombre, String _subject, String _mensaje, String _tipo, Long _idEstado ) throws Exception;

    public void modificarCorreo( Long _idCorreo, String _nombre, String _subject, String _mensaje, String _tipo, Long _idEstado ) throws Exception;

    public List<NominaBO> obtenerNominasSinFolio( Date _fechaDesde, Date _fechaHasta, String _loteNomina, Long _tipoNomina, Long bancoProceso );

    public void registrarFolio( Long _idNomina, String _folioExterno );

    public int validaBancoFolio( String banco, Long numeroFolioIngresado );

    public void extraerNomina( Long _idNomina ) throws Exception;

    public List<ArchivoBO> obtenerArchivosNominas() throws Exception;

    public InputStream obtenerArchivoNomina( String _nombreArchivo ) throws Exception;

    public void procesarNominaTEST( Long _idNomina );

    public void reprocesarEnvioTEST( Long _idProceso );

    public void rendirCargaTEST( Long _idProceso );

    public void rendirPagoTEST( Long _idProceso );
}
