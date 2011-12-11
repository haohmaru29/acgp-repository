package cl.bicevida.envionominas.view.facade;

import cl.bicevida.core.view.utils.BvWebLog;
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
import cl.bicevida.envionominas.model.bo.FeriadoBO;
import cl.bicevida.envionominas.model.bo.DetalleGastoBO;
import cl.bicevida.envionominas.model.bo.ParametroBO;
import cl.bicevida.envionominas.model.bo.TipoFeriadoBO;
import cl.bicevida.envionominas.model.bo.ListaDistribucionBO;

import cl.bicevida.envionominas.model.bo.ComisionBO;
import cl.bicevida.envionominas.model.bo.CorreoBO;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.DAOFactory;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.NominaDAO;
import cl.bicevida.envionominas.model.exceptions.EnvioNominasException;
import cl.bicevida.envionominas.model.logic.EnvioNominasManager;
import cl.bicevida.envionominas.model.services.ejb.EnvioNominasEJBLocal;

import java.io.InputStream;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EnvioNominasBusinessFacadeImpl implements EnvioNominasBusinessFacade {
    private EnvioNominasEJBLocal ejbLocal;

    private Object cargaEjb( String _ejbName ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "cargaEjb([" + _ejbName + "]) " );
        Object ejbReference = null;
        InitialContext ctx = null;
        try {
            ctx = this.getInitialContext();
        } catch ( NamingException e ) {
            BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "Ha ocurrido un error al obtener el contexto", e );
            e.printStackTrace();
        }
        try {
            ejbReference = ctx.lookup( "ejb/local/" + _ejbName );
        } catch ( NamingException e ) {
            BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "Ha ocurrido un error al obtener la instancia del EJB", e );
            e.printStackTrace();
        }
        return ejbReference;
    }

    private InitialContext getInitialContext() throws NamingException {
        return new InitialContext();
    }

    public EnvioNominasBusinessFacadeImpl() {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "EnvioNominasBusinessFacadeImpl()" );
        ejbLocal = ( EnvioNominasEJBLocal ) cargaEjb( "EnvioNominasEJB" );
    }

    public List<TipoNominaBO> obtenerTiposNomina() {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "obtenerListaTiposNomina() " );
        return ejbLocal.obtenerTiposNomina();
    }

    public List<TipoFeriadoBO> obtenerTiposFeriado() {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "obtenerTiposFeriado() " );
        return ejbLocal.obtenerTiposFeriado();
    }

    public List<BancoBO> obtenerBancosPago() {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "obtenerBancosPago() " );
        return ejbLocal.obtenerBancosPago();
    }

    public List<EstadoTransaccionBO> obtenerEstadosTransaccion() {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "obtenerEstadosTransaccion() " );
        return ejbLocal.obtenerEstadosTransaccion();
    }

    public List<EstadoNominaBO> obtenerEstadosNomina() {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "obtenerEstadosNomina() " );
        return ejbLocal.obtenerEstadosNomina();
    }

    public List<TipoCuentaBO> obtenerTiposCuenta() {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "obtenerTiposCuenta() " );
        return ejbLocal.obtenerTiposCuenta();
    }

    public List<NominaBO> obtenerNominasCancelables( Long _bancoPago, Date _fechaDesde, Date _fechaHasta, Long _numeroNomina, Long _tipoNomina, Long _tipoCuenta ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "obtenerNominasCancelables([" + _bancoPago + "],[" + _fechaDesde + "],[" + _fechaHasta + "],[" + _numeroNomina + "],[" + _tipoNomina + "],[" + _tipoCuenta + "])" );
        return ejbLocal.obtenerNominasCancelables( _bancoPago, _fechaDesde, _fechaHasta, _numeroNomina, _tipoNomina, _tipoCuenta );
    }

    public List<RegistroNominaBO> consultaEstadoTransaccionesNomina( Long _bancoPago, Long _estadoNomina, Long _estadoTransaccion, Date _fechaDesde, Date _fechaHasta, String _loteNomina, String _rutTitular, Long _bancoProceso, Long _tipoNomina ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "consultaEstadoTransaccionesNomina([" + _bancoPago + "],[" + _estadoNomina + "],[" + _estadoTransaccion + "],[" + _fechaDesde + "],[" + _fechaHasta + "],[" + _loteNomina + "],[" + _rutTitular + "],[" + _bancoProceso + "],[" + _tipoNomina + "])" );
        return ejbLocal.consultaEstadoTransaccionesNomina( _bancoPago, _estadoNomina, _estadoTransaccion, _fechaDesde, _fechaHasta, _loteNomina, _rutTitular, _bancoProceso, _tipoNomina );
    }

    public List<NominaBO> obtenerNominasExtraibles( Date _fechaDesde, Date _fechaHasta, String _loteNomina, Long _tipoNomina, Long _bancoProceso ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "obtenerNominasExtraibles([" + _bancoProceso + "],[" + _fechaDesde + "],[" + _fechaHasta + "],[" + _loteNomina + "],[" + _tipoNomina + "])" );
        return ejbLocal.obtenerNominasExtraibles( _fechaDesde, _fechaHasta, _loteNomina, _tipoNomina, _bancoProceso );
    }

    public List<NominaBO> obtenerNominasAutorizables( Long _bancoPago, Date _fechaDesde, Date _fechaHasta, Long _numeroNomina, Long _tipoNomina, Long _tipoCuenta ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "obtenerNominasAutorizables([" + _bancoPago + "],[" + _fechaDesde + "],[" + _fechaHasta + "],[" + _numeroNomina + "],[" + _tipoNomina + "],[" + _tipoCuenta + "])" );
        return ejbLocal.obtenerNominasAutorizables( _bancoPago, _fechaDesde, _fechaHasta, _numeroNomina, _tipoNomina, _tipoCuenta );
    }

    public List<RegistroNominaBO> obtenerRegistrosPorNomina( Long _idNomina ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "obtenerRegistrosPorNomina([" + _idNomina + "])" );
        return ejbLocal.obtenerRegistrosPorNomina( _idNomina );
    }

    public List<FeriadoBO> obtenerFeriado() {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "obtenerFeriado() " );
        return ejbLocal.obtenerFeriado();
    }

    public void agregarFeriado( String _tipoFeriado, String _descripcionFeriado, Date _fechaFeriado ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "agregarFeriado() " );
        ejbLocal.agregarFeriado( _tipoFeriado, _descripcionFeriado, _fechaFeriado );
        return;
    }

    public Long buscarFeriado( Date _fechaFeriado ) {
        return ejbLocal.buscarFeriado( _fechaFeriado );
    }

    public void eliminarFeriado( Date _fechaFeriado ) {
        ejbLocal.eliminarFeriado( _fechaFeriado );
        return;
    }

    public List<GastoNominaBO> obtenerGastosNominas( Long _bancoPago, Date _fechaDesde, Date _fechaHasta, String _loteNomina, Long _bancoProceso, Long _tipoNomina ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "obtenerGastosNominas([" + _bancoPago + "],[" + _fechaDesde + "],[" + _fechaHasta + "],[" + _loteNomina + "],[" + _bancoProceso + "],[" + _tipoNomina + "])" );
        return ejbLocal.obtenerGastosNominas( _bancoPago, _fechaDesde, _fechaHasta, _loteNomina, _bancoProceso, _tipoNomina );
    }

    public void modificarFeriado( Long idFeriado, String tipoFeriado, String descripcionFeriado, Date fechaFeriado ) {
        ejbLocal.modificarFeriado( idFeriado, tipoFeriado, descripcionFeriado, fechaFeriado );
        return;
    }

    public List<CorreoBO> obtenerCorreos() {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "obtenerCorreos() " );
        return ejbLocal.obtenerCorreos();
    }

    public Long buscarCorreo( String _nombreCorreo ) {
        return ejbLocal.buscarCorreo( _nombreCorreo );
    }

    public void eliminarCorreo( Long _idCorreo ) {
        ejbLocal.eliminarCorreo( _idCorreo );
        return;
    }

    public List<ParametroBO> obtenerParametros() {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "obtenerParametros() " );
        return ejbLocal.obtenerParametros();
    }

    public void agregarParametro( String _claveParametro, String _valorParametro, String _descripcionParametro, String _tipoParametro ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "agregarParametro() " );
        ejbLocal.agregarParametro( _claveParametro, _valorParametro, _descripcionParametro, _tipoParametro );
    }

    public Long buscarParametro( String _claveParametro, Long _idBancoProceso ) {
        return ejbLocal.buscarParametro( _claveParametro, _idBancoProceso );
    }

    public void eliminarParametro( Long _idParametro ) {
        ejbLocal.eliminarParametro( _idParametro );
    }

    public void modificarParametro( Long idParametro, String claveParametro, String valorParametro, String descripcionParametro, String _tipoParametro ) {
        ejbLocal.modificarParametro( idParametro, claveParametro, valorParametro, descripcionParametro, _tipoParametro );
    }

    public List<ListaDistribucionBO> obtenerListasDistribucion() {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "obtenerListasDistribucion() " );
        return ejbLocal.obtenerListasDistribucion();
    }

    public void agregarListaDistribucion( String _origenLista, String _nombreLista ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "agregarListaDistribucion() " );
        ejbLocal.agregarListaDistribucion( _origenLista, _nombreLista );
    }

    public Long buscarListaDistribucion( String _origenLista ) {
        return ejbLocal.buscarListaDistribucion( _origenLista );
    }

    public void eliminarListaDistribucion( Long _idLista ) {
        ejbLocal.eliminarListaDistribucion( _idLista );
    }

    public void modificarListaDistribucion( Long idLista, String origenLista, String nombreLista ) {
        ejbLocal.modificarListaDistribucion( idLista, origenLista, nombreLista );
    }

    public List<OrigenBO> obtenerOrigen() {
        return ejbLocal.obtenerOrigen();
    }

    public List<DetalleGastoBO> obtenerDetalleGastoNomina( Long _idNomina ) {
        return ejbLocal.obtenerDetalleGastoNomina( _idNomina );
    }

    public List<ComisionBO> obtenerComisionesByBancoProceso( Long _idBancoProceso ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "obtenerComisionesByBancoProceso() " );
        return ejbLocal.obtenerComisionesByBancoProceso( _idBancoProceso );
    }

    public void agregarComision( Long _idBancoProceso, Date _fechaInicioVigencia, Date _fechaTerminoVigencia, BigDecimal _montoMismoBanco, BigDecimal _montoOtrosBancos ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "agregarComision() " );
        ejbLocal.agregarComision( _idBancoProceso, _montoMismoBanco, _montoOtrosBancos, _fechaInicioVigencia, _fechaTerminoVigencia );
    }

    public List<BancoProcesoBO> obtenerBancosProceso() {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "obtenerBancosProceso()" );
        return ejbLocal.obtenerBancosProceso();
    }

    public void eliminarComisionById( Long _idComision ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "eliminarComisionById([" + _idComision + "]) " );
        ejbLocal.eliminarComisionById( _idComision );
    }

    public void guardarCambiosComision( Long _idComision, Date _fechaInicioVigencia, Date _fechaTerminoVigencia, BigDecimal _montoMismoBanco, BigDecimal _montoOtrosBancos ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "modificarComision([" + _idComision + "],[" + _montoMismoBanco + "],[" + _montoOtrosBancos + "],[" + _fechaInicioVigencia + "],[" + _fechaTerminoVigencia + "])" );
        ejbLocal.modificarComision( _idComision, _montoMismoBanco, _montoOtrosBancos, _fechaInicioVigencia, _fechaTerminoVigencia );
    }

    public void agregarParametroBancoProceso( Long _idBancoProceso, String _claveParametro, String _valorParametro, String _descripcionParametro, String _tipoParametro ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "agregarParametroBancoProceso([" + _idBancoProceso + "],[" + _claveParametro + "],[" + _valorParametro + "],[" + _descripcionParametro + "],[" + _tipoParametro + "])" );
        ejbLocal.agregarParametroBancoProceso( _idBancoProceso, _claveParametro, _valorParametro, _descripcionParametro, _tipoParametro );
    }

    public List<ParametroBO> obtenerParametrosByBancoProceso( Long _idBancoProceso ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "obtenerParametrosByBancoProceso([" + _idBancoProceso + "])" );
        return ejbLocal.obtenerParametrosByBancoProceso( _idBancoProceso );
    }

    public void guardarCambiosParametro( Long _idParametro, String _claveParametro, String _valorParametro, String _descripcionParametro, String _tipoParametro ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "modificarParametro([" + _idParametro + "],[" + _claveParametro + "],[" + _valorParametro + "],[" + _descripcionParametro + "],[" + _tipoParametro + "])" );
        ejbLocal.modificarParametro( _idParametro, _claveParametro, _valorParametro, _descripcionParametro, _tipoParametro );
    }

    public void eliminarBancoProceso( Long _idBancoProceso ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "eliminarBancoProceso([" + _idBancoProceso + "]) " );
        ejbLocal.eliminarBancoProceso( _idBancoProceso );
    }

    public void crearBancoProceso( String _nombre, Long _idBanco ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "crearBancoProceso() " );
        ejbLocal.crearBancoProceso( _nombre, _idBanco );
    }

    public Boolean isBancoProcesoEnUso( Long _idBancoProceso ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "isBancoProcesoEnUso([" + _idBancoProceso + "])" );
        return ejbLocal.isBancoProcesoEnUso( _idBancoProceso );
    }

    public void agregarCorreo( String _nombre, String _subject, String _mensaje, String _tipo, Long _idEstado ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "agregarCorreo()" );
        ejbLocal.agregarCorreo( _nombre, _subject, _mensaje, _tipo, _idEstado );
    }

    public void modificarCorreo( Long _idCorreo, String _nombre, String _subject, String _mensaje, String _tipo, Long _idEstado ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "modificarCorreo([" + _idCorreo + "],[" + _nombre + "],[" + _subject + "],[" + _mensaje + "],[" + _tipo + "],[" + _idEstado + "])" );
        ejbLocal.modificarCorreo( _idCorreo, _nombre, _subject, _mensaje, _tipo, _idEstado );
    }

    public List<NominaBO> obtenerNominasSinFolio( Date _fechaDesde, Date _fechaHasta, String _loteNomina, Long _tipoNomina, Long _bancoProceso ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "obtenerNominasSinFolio([" + _bancoProceso + "],[" + _fechaDesde + "],[" + _fechaHasta + "],[" + _loteNomina + "],[" + _tipoNomina + "])" );
        return ejbLocal.obtenerNominasSinFolio( _fechaDesde, _fechaHasta, _loteNomina, _tipoNomina, _bancoProceso );
    }

    public void registrarFolio( Long _idNomina, String _folioExterno ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "ingresarFolioEnNomina([" + _idNomina + "],[" + _folioExterno + "])" );
        ejbLocal.registrarFolio( _idNomina, _folioExterno );
    }

    public int validaBancoFolio( String banco, Long numeroFolioIngresado ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "validaBancoFolio([" + banco + "],[" + numeroFolioIngresado + "])" );
        return ejbLocal.validaBancoFolio( banco, numeroFolioIngresado );
    }

    public void extraerNomina( Long _idNomina ) {
        BvWebLog.log( this.getClass().getName(), BvWebLog.LEVEL_INFO, "extraerNomina([" + _idNomina + "])" );
        ejbLocal.extraerNomina( _idNomina );
    }

    public List<ArchivoBO> obtenerArchivosNominas() {
        return ejbLocal.obtenerArchivosNominas();
    }

    public InputStream obtenerArchivoNomina( String _nombreArchivo ) {
        return ejbLocal.obtenerArchivoNomina( _nombreArchivo );
    }

    public void procesarNominaTEST( Long _idNomina ) {
        EnvioNominasManager manager = new EnvioNominasManager( ejbLocal.getContext() );
        try {
            manager.procesarNomina( _idNomina );
        } catch ( EnvioNominasException e ) {
            e.printStackTrace();
        }
    }

    public void reprocesarEnvioTEST( Long _idProceso ) {
        EnvioNominasManager manager = new EnvioNominasManager( ejbLocal.getContext() );
        try {
            manager.reprocesarEnvio( _idProceso );
        } catch ( EnvioNominasException e ) {
            e.printStackTrace();
        }
    }

    public void rendirCargaTEST( Long _idProceso ) {
        EnvioNominasManager manager = new EnvioNominasManager( ejbLocal.getContext() );
        try {
            manager.rindeCargaProceso( _idProceso );
        } catch ( EnvioNominasException e ) {
            e.printStackTrace();
        }
    }

    public void rendirPagoTEST( Long _idNomina ) {
        EnvioNominasManager manager = new EnvioNominasManager( ejbLocal.getContext() );
        NominaDAO dao = DAOFactory.getDAOFactory( DAOFactory.ORACLE ).getNominaDAO();
        NominaBO nomina = null;
        try {
            nomina = dao.queryNominaById(_idNomina);
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
        try {
            manager.rindePagoNomina( nomina.getId() );
        } catch ( EnvioNominasException e ) {
            e.printStackTrace();
        }
    }
}
